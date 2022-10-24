/* -*- Mode: java; tab-width: 8; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.javascript;

import java.security.ProtectionDomain;

/**
 * @author Attila Szegedi
 */
public class SecurityUtilities
{
    /**
     * Retrieves a system property within a privileged block. Use it only when
     * the property is used from within Rhino code and is not passed out of it.
     * @param name the name of the system property
     * @return the value of the system property
     * @deprecated Java's Security Manager has been deprecated for removal
     */
    @Deprecated
    public static String getSystemProperty(final String name) {
        return System.getProperty(name);
    }

    /**
     * @deprecated Java's Security Manager has been deprecated for removal
     */
    @Deprecated
    public static ProtectionDomain getProtectionDomain(final Class<?> clazz) {
        return clazz.getProtectionDomain();
    }

    /**
     * Look up the top-most element in the current stack representing a
     * script and return its protection domain.
     * @return The protection of the top-most script in the current stack, or null
     * @deprecated Java's Security Manager has been deprecated for removal
     */
    @Deprecated
    public static ProtectionDomain getScriptProtectionDomain() {
        // TODO: Adapt to the removal of SecurityManager
        return null;
    }
}
