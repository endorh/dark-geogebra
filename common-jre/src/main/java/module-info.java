open module geogebra.common.jre.main {
	requires java.compiler;
	requires java.desktop;
	requires geogebra.common.main;
	requires geogebra.giac.jni.main;
	requires geogebra.renderer.base.main;
	requires com.google.code.findbugs;

	exports org.geogebra.common.jre.util;
	exports org.geogebra.common.jre.gui;
	exports org.geogebra.common.jre.openGL;
	exports org.geogebra.common.jre.cas.giac;
	exports org.geogebra.common.jre.factory;
	exports org.geogebra.common.jre.headless;
	exports org.geogebra.common.jre.kernel.commands;
	exports org.geogebra.common.jre.main;
	exports org.geogebra.common.jre.plugin;
	exports org.geogebra.common.jre.io;
	exports org.mozilla.javascript;
}