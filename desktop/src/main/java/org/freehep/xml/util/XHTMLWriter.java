package org.freehep.xml.util;

import java.io.Writer;

import org.geogebra.common.util.debug.Log;

/**
 * A class that makes it easy to write XHTML documents.
 *
 * @author Mark Donszelmann
 * @version $Id: XHTMLWriter.java,v 1.5 2008-10-23 19:04:05 hohenwarter Exp $
 */
public class XHTMLWriter extends XMLWriter {
	/**
	 * @param type
	 *            [strict, transitional, frameset]
	 */
	public XHTMLWriter(Writer w, String indentString, String type) {
		super(w, indentString, "xhtml");
		openDoc("1.0", "UTF-8", false);
		switch (type) {
		case "strict" -> referToDTD("html", "-//W3C//DTD XHTML 1.0 Strict//EN",
				"http://wwww.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd");
		case "transitional" -> referToDTD("html", "-//W3C//DTD XHTML 1.0 Transitional//EN",
				"http://wwww.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd");
		case "frameset" -> referToDTD("html", "-//W3C//DTD XHTML 1.0 Frameset//EN",
				"http://wwww.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd");
		default -> Log.debug("XHTMLWriter: unknown type: " + type
				+ ", allowed are: strict, transitional, frameset");
		}
		setAttribute("xmlns", "http://www.w3.org/1999/xhtml");
		if (!type.equals("strict")) {
			setAttribute("xml", "lang", "en");
		}
		setAttribute("lang", "en");
		openTag("html");
	}

	public XHTMLWriter(Writer w) {
		this(w, "  ", "strict");
	}

	@Override
	public void closeDoc() {
		if (!closed) {
			closeTag();
		}
		super.closeDoc();
	}

	@Override
	public void printTag(String name) {
		checkNameValid(name);
		writer.print("<" + name);
		printAttributes(name.length());
		writer.println(" />");
	}

	@Override
	public void setAttribute(String name, boolean value) {
		if (value) {
			setAttribute(name, name);
		}
	}

	/**
	 * Prints text as is (no escaping of anything)
	 */
	public void printPlain(String text) {
		writer.print(text);
	}
}
