package org.geogebra.desktop.gui.editor;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.text.Element;
import javax.swing.text.View;

import org.geogebra.common.awt.GColor;
import org.geogebra.desktop.gui.theme.ThemeD;
import org.geogebra.desktop.main.AppD;

public class LaTeXContext extends ViewContext {

	/**
	 * TOKENS : A Map which contains the names of keywords
	 */
	private static final Map<String, Integer> TOKENS = new HashMap<>(
			14);

	static {
		TOKENS.put("Default", LexerConstants.DEFAULT);
		TOKENS.put("Ampersand", LaTeXLexerConstants.AMP);
		TOKENS.put("SubSup", LaTeXLexerConstants.SUBSUP);
		TOKENS.put("Dollar", LaTeXLexerConstants.DOLLAR);
		TOKENS.put("Number", LaTeXLexerConstants.NUMBER);
		TOKENS.put("OpenClose", LaTeXLexerConstants.OPENCLOSE);
		TOKENS.put("Command", LaTeXLexerConstants.COMMAND);
		TOKENS.put("Comments", LaTeXLexerConstants.COMMENTS);
		TOKENS.put("White", LexerConstants.WHITE);
		TOKENS.put("Tabulation", LexerConstants.TAB);
	}

	private View view;
	private List<Integer> typeToDefault = new ArrayList<>();

	private static final Map<String, Color> colorMap = new HashMap<>();
	static {
		colorMap.put("Default", ThemeD.awtColor(GColor.newColorRGB(0x000000)));
		colorMap.put("Ampersand", ThemeD.awtColor(GColor.newColorRGB(0xfb24f8)));
		colorMap.put("SubSup", ThemeD.awtColor(GColor.newColorRGB(0x01a801)));
		colorMap.put("Dollar", ThemeD.awtColor(GColor.newColorRGB(0xffaa00)));
		colorMap.put("Number", ThemeD.awtColor(GColor.newColorRGB(0x0b873d)));
		colorMap.put("OpenClose", ThemeD.awtColor(GColor.newColorRGB(0x4a55db)));
		colorMap.put("Command", ThemeD.awtColor(GColor.newColorRGB(0x099ec3)));
		colorMap.put("Comments", ThemeD.awtColor(GColor.newColorRGB(0xc34209)));
		colorMap.put("White", ThemeD.awtColor(GColor.newColorRGB(0xdcdcdc)));
		colorMap.put("Tabulation", ThemeD.awtColor(GColor.newColorRGB(0xdcdcdc)));
	}

	private static final Map<String, Integer> attribMap = new HashMap<>();
	static {
		attribMap.put("Default", 0);
		attribMap.put("Ampersand", 0);
		attribMap.put("Number", 0);
		attribMap.put("OpenClose", 0);
		attribMap.put("SubSup", 0);
		attribMap.put("Dollar", 0);
		attribMap.put("Command", 0);
		attribMap.put("Comments", 0);
		attribMap.put("White", 0);
		attribMap.put("Tabulation", 0);
	}

	/**
	 * The constructor
	 * 
	 * @param app
	 *            the Application where this context is needed
	 */
	public LaTeXContext(AppD app) {
		super();
		tokenFont = app.getPlainFont();
		genColors();
		genAttributes();
	}

	/**
	 * Generate an attribute for a type of keyword
	 * 
	 * @param keyword
	 *            the name can be found in scinotesConfiguration.xml
	 * @param type
	 *            the type to use
	 */
	public void genAttribute(String keyword, int type) {
		tokenAttrib[TOKENS.get(keyword)] = type;
		if (TOKENS.get(keyword) == LexerConstants.DEFAULT) {
			for (Integer i : typeToDefault) {
				tokenAttrib[i] = tokenAttrib[0];
			}
		}
	}

	/**
	 * Generate attributes to use to render the document
	 */
	public void genAttributes() {
		tokenAttrib = new int[LaTeXLexerConstants.NUMBEROFTOKENS];
		Map<String, Integer> map = attribMap;
		for (Entry<String, Integer> entry : map.entrySet()) {
			String tokenType = entry.getKey();
			tokenAttrib[TOKENS.get(tokenType)] = entry.getValue();
		}

		for (Integer i : typeToDefault) {
			tokenAttrib[i] = tokenAttrib[0];
		}
	}

	/**
	 * Generate the colors to use to render the document
	 */
	public void genColors() {
		tokenColors = new Color[LaTeXLexerConstants.NUMBEROFTOKENS];
		Map<String, Color> map = colorMap;
		for (Entry<String, Color> entry : map.entrySet()) {
			String tokenType = entry.getKey();
			tokenColors[TOKENS.get(tokenType)] = entry.getValue();
		}

		typeToDefault.clear();
		for (int i = 0; i < tokenColors.length; i++) {
			if (tokenColors[i] == null) {
				tokenColors[i] = tokenColors[0];
				typeToDefault.add(i);
			}
		}
	}

	/**
	 * Generate a color for a type of keyword
	 * 
	 * @param name
	 *            the name can be found in scinotesConfiguration.xml
	 * @param color
	 *            the color to use
	 */
	public void genColors(String name, Color color) {
		if (tokenColors == null) {
			genColors();
		}

		tokenColors[TOKENS.get(name)] = color;

		if (TOKENS.get(name) == LexerConstants.DEFAULT) {
			for (Integer i : typeToDefault) {
				tokenColors[i] = tokenColors[0];
			}
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public View getCurrentView() {
		return view;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public View create(Element elem) {
		view = new GeoGebraView(elem, new LaTeXLexer(), this);
		return view;
	}
}
