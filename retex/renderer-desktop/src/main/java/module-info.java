module geogebra.renderer.desktop.main {
	requires java.datatransfer;
	requires java.desktop;
	requires geogebra.renderer.base.main;

	exports com.himamis.retex.renderer.desktop.graphics;
	exports com.himamis.retex.renderer.desktop;
	exports com.himamis.retex.renderer.desktop.font;
}