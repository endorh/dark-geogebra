/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.UndeclaredThrowableException;
import java.net.URL;
import java.security.CodeSource;
import java.security.SecureClassLoader;
import java.util.Map;
import java.util.WeakHashMap;

/**
 * @author Attila Szegedi
 */
public abstract class SecureCaller
{
    private static final byte[] secureCallerImplBytecode = loadBytecode();

    // We're storing a CodeSource -> (ClassLoader -> SecureRenderer), since we
    // need to have one renderer per class loader. We're using weak hash maps
    // and soft references all the way, since we don't want to interfere with
    // cleanup of either CodeSource or ClassLoader objects.
    private static final Map<CodeSource,Map<ClassLoader,SoftReference<SecureCaller>>>
    callers =
            new WeakHashMap<>();

    public abstract Object call(Callable callable, Context cx,
            Scriptable scope, Scriptable thisObj, Object[] args);

    /**
     * Call the specified callable using a protection domain belonging to the
     * specified code source.
     */
    static Object callSecurely(final CodeSource codeSource, Callable callable,
            Context cx, Scriptable scope, Scriptable thisObj, Object[] args)
    {
        final Thread thread = Thread.currentThread();
        // Run in doPrivileged as we might be checked for "getClassLoader"
        // runtime permission
        final ClassLoader classLoader = thread.getContextClassLoader();
        Map<ClassLoader,SoftReference<SecureCaller>> classLoaderMap;
        synchronized(callers)
        {
            classLoaderMap = callers.computeIfAbsent(codeSource,
                    k -> new WeakHashMap<>());
        }
        SecureCaller caller;
        synchronized(classLoaderMap)
        {
            SoftReference<SecureCaller> ref = classLoaderMap.get(classLoader);
            if (ref != null) {
                caller = ref.get();
            } else {
                caller = null;
            }
            if (caller == null) {
                try
                {
                    ClassLoader effectiveClassLoader;
                    Class<?> thisClass = SecureCaller.class;
                    if (classLoader.loadClass(thisClass.getName()) != thisClass) {
                        effectiveClassLoader = thisClass.getClassLoader();
                    } else {
                        effectiveClassLoader = classLoader;
                    }
                    SecureClassLoaderImpl secCl =
                            new SecureClassLoaderImpl(effectiveClassLoader);
                    Class<?> c = secCl.defineAndLinkClass(
                            SecureCaller.class.getName() + "Impl",
                            secureCallerImplBytecode, codeSource);
                    caller = (SecureCaller) c.getConstructor().newInstance();
                    classLoaderMap.put(classLoader, new SoftReference<>(caller));
                } catch (ClassNotFoundException
                         | InstantiationException
                         | IllegalAccessException
                         | NoSuchMethodException
                         | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return caller.call(callable, cx, scope, thisObj, args);
    }

    private static class SecureClassLoaderImpl extends SecureClassLoader
    {
        SecureClassLoaderImpl(ClassLoader parent)
        {
            super(parent);
        }

        Class<?> defineAndLinkClass(String name, byte[] bytes, CodeSource cs)
        {
            Class<?> cl = defineClass(name, bytes, 0, bytes.length, cs);
            resolveClass(cl);
            return cl;
        }
    }

    private static byte[] loadBytecode()
    {
        return loadBytecodePrivileged();
    }

    private static byte[] loadBytecodePrivileged()
    {
        URL url = SecureCaller.class.getResource("SecureCallerImpl.clazz");
        try
        {
            try (InputStream in = url.openStream()) {
                ByteArrayOutputStream bout = new ByteArrayOutputStream();
                for (; ; ) {
                    int r = in.read();
                    if (r == -1) {
                        return bout.toByteArray();
                    }
                    bout.write(r);
                }
            }
        }
        catch(IOException e)
        {
            throw new UndeclaredThrowableException(e);
        }
    }
}
