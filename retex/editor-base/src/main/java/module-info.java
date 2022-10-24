module geogebra.editor.base.main {
	requires geogebra.renderer.base.main;
	requires com.google.j2objc;

	exports com.himamis.retex.editor.share.controller;
	exports com.himamis.retex.editor.share.editor;
	exports com.himamis.retex.editor.share.event;
	exports com.himamis.retex.editor.share.input;
	exports com.himamis.retex.editor.share.io.latex;
	exports com.himamis.retex.editor.share.meta;
	exports com.himamis.retex.editor.share.model;
	exports com.himamis.retex.editor.share.serializer;
	exports com.himamis.retex.editor.share.util;
}