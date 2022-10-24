module geogebra.desktop.main {
	requires geogebra.common.main;
	requires geogebra.common.jre.main;
	requires geogebra.renderer.base.main;
	requires geogebra.renderer.desktop.main;
	requires geogebra.ggbjdk.main;
	requires geogebra.editor.base.main;
	requires geogebra.editor.desktop.main;
	requires geogebra.giac.jni.main;
	requires geogebra.input3D.impl.main;
	requires geogebra.jogl.main;

	requires java.desktop;
	requires com.jogamp.jogl;
	requires com.jogamp.gluegen;
	requires java.logging;
	requires java.naming;
	requires com.github.spotbugs.annotations;
	requires com.formdev.flatlaf;
	requires java.scripting;
	requires java.prefs;
	requires jdk.httpserver;
	requires com.ogprover.open_geo_prover;
	requires com.apple.mac_extensions;
}