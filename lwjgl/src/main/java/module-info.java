module geogebra.jogl.main {
	exports org.geogebra.desktop.geogebra3D.euclidian3D.opengl;

	requires java.datatransfer;
	requires java.desktop;

	requires org.lwjgl;
	requires org.lwjgl.glfw;
	requires org.lwjgl.jawt;
	requires org.lwjgl.opengl;
	requires lwjgl3.awt;
}