package org.geogebra.desktop.gui.util;

import org.w3c.dom.svg.SVGDocument;
import org.w3c.dom.svg.SVGSVGElement;

import io.sf.carte.echosvg.bridge.BridgeContext;
import io.sf.carte.echosvg.bridge.DocumentLoader;
import io.sf.carte.echosvg.bridge.GVTBuilder;
import io.sf.carte.echosvg.bridge.UserAgent;
import io.sf.carte.echosvg.bridge.UserAgentAdapter;
import io.sf.carte.echosvg.gvt.GraphicsNode;

class JSVGModel {
	public static final int MAX_TRIES = 2;
	SVGDocument doc;
	String content;
	private int tries = 0;
	GraphicsNode node;
	private int width;
	private int height;

	public JSVGModel(String content) {
		this.content = content;
	}

	public JSVGModel(SVGDocument doc, String content) {
		this(content);
		this.doc = doc;
	}

	public JSVGModel(SVGDocument doc) {
		this.doc = doc;
	}

	public void nextTry() {
		tries++;
	}

	public boolean isMaxTriesReached() {
		return tries > MAX_TRIES;
	}

	public void setDoc(SVGDocument doc) {
		this.doc = doc;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void build() {
		UserAgent userAgent = new UserAgentAdapter();

		DocumentLoader loader = new SVGDocumentLoaderNoError(userAgent);

		BridgeContext ctx = new BridgeContext(userAgent, loader);
		ctx.setDynamicState(BridgeContext.DYNAMIC);
		GVTBuilder builder = new GVTBuilder();
		node = builder.build(ctx, doc);
		SVGSVGElement root = doc.getRootElement();
		this.width = (int) root.getWidth().getBaseVal().getValue();
		this.height = (int) root.getHeight().getBaseVal().getValue();

	}
}
