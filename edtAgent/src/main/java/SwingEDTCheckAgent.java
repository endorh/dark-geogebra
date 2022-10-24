import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.Instrumentation;
import java.security.ProtectionDomain;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Label;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

/**
 * A java agent which transforms the Swing Component classes in such a way that a stack
 * trace will be dumped or an exception will be thrown when they are accessed from a wrong thread.
 * <br>
 * To use it, add
 * <pre>
 * -javaagent:${workspace_loc:MyProject/tool/util/swingEDTCheck}/swingEDTCheck.jar
 * </pre>
 * to the VM arguments of a run configuration. This will cause the stack traces to be dumped.
 * <br>
 * Use
 * <pre>
 * -javaagent:${workspace_loc:MyProject/tool/util/swingEDTCheck}/swingEDTCheck.jar=throw
 * </pre>
 * to throw exceptions.
 */
public class SwingEDTCheckAgent {

	public static void premain(String args, Instrumentation inst) {
		boolean throwing = "throw".equals(args);
		inst.addTransformer(new Transformer(throwing));
	}

	private static class Transformer implements ClassFileTransformer {

		private final boolean throwing;

		public Transformer(boolean throwing) {
			this.throwing = throwing;
		}

		@Override
		public byte[] transform(
				ClassLoader loader,
				String className,
				Class classBeingRedefined,
				ProtectionDomain protectionDomain,
				byte[] classfileBuffer
		) {
			// Process all classes in javax.swing package which names start with J
			if (className.startsWith("javax/swing/J")) {
				ClassReader cr = new ClassReader(classfileBuffer);
				ClassWriter cw = new ClassWriter(cr, ClassWriter.COMPUTE_FRAMES);
				ClassVisitor cv = new EdtCheckerClassAdapter(cw, throwing);
				cr.accept(cv, 0);
				return cw.toByteArray();
			}
			return classfileBuffer;
		}
	}

	private static class EdtCheckerClassAdapter extends ClassVisitor {

		private final boolean throwing;

		public EdtCheckerClassAdapter(ClassVisitor classVisitor, boolean throwing) {
			super(Opcodes.ASM4, classVisitor);
			this.throwing = throwing;
		}

		@Override
		public MethodVisitor visitMethod(final int access,
				final String name,
				final String desc,
				final String signature,
				final String[] exceptions) {
			MethodVisitor mv =
					cv.visitMethod(access, name, desc, signature, exceptions);

			if (name.startsWith("set") || name.startsWith("get") || name.startsWith("is")) {
				return new EdtCheckerMethodAdapter(mv, throwing);
			} else {
				return mv;
			}
		}
	}

	private static class EdtCheckerMethodAdapter extends MethodVisitor {

		private final boolean throwing;

		public EdtCheckerMethodAdapter(MethodVisitor methodVisitor, boolean throwing) {
			super(Opcodes.ASM4, methodVisitor);
			this.throwing = throwing;
		}

		@Override
		public void visitCode() {
			mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/awt/EventQueue", "isDispatchThread", "()Z", false);
			Label l1 = new Label();
			mv.visitJumpInsn(Opcodes.IFNE, l1);
			Label l2 = new Label();
			mv.visitLabel(l2);

			if (throwing) {
				// more Aggressive: throw exception
				mv.visitTypeInsn(Opcodes.NEW, "java/lang/RuntimeException");
				mv.visitInsn(Opcodes.DUP);
				mv.visitLdcInsn("Swing Component called from outside the EDT");
				mv.visitMethodInsn(
						Opcodes.INVOKESPECIAL, "java/lang/RuntimeException", "<init>",
						"(Ljava/lang/String;)V", false);
				mv.visitInsn(Opcodes.ATHROW);

			} else {
				// this just dumps the Stack Trace
				mv.visitMethodInsn(Opcodes.INVOKESTATIC, "java/lang/Thread", "dumpStack", "()V", false);
			}
			mv.visitLabel(l1);
		}
	}
}