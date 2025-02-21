/*
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.
This code has been written initially for Scilab (http://www.scilab.org/).

This program is free software; you can redistribute it and/or modify it
under the terms of the GNU General Public License as published by
the Free Software Foundation.

 */

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
import org.geogebra.desktop.gui.theme.ColorKeys;
import org.geogebra.desktop.gui.theme.ThemeD;
import org.geogebra.desktop.main.AppD;

/**
 *
 * @author Calixte DENIZET
 *
 */
public class GeoGebraContext extends ViewContext {

	/**
	 * TOKENS : A Map which contains the names of keywords
	 */
	private static final Map<String, Integer> TOKENS = new HashMap<>(
			14);

	static {
		TOKENS.put("Default", LexerConstants.DEFAULT);
		TOKENS.put("Operator", GeoGebraLexerConstants.OPERATOR);
		TOKENS.put("Constante", GeoGebraLexerConstants.CONSTANTE);
		TOKENS.put("Number", GeoGebraLexerConstants.NUMBER);
		TOKENS.put("OpenClose", GeoGebraLexerConstants.OPENCLOSE);
		TOKENS.put("String", GeoGebraLexerConstants.STRING);
		TOKENS.put("Built-in function", GeoGebraLexerConstants.BUILTINFUNCTION);
		TOKENS.put("Function", GeoGebraLexerConstants.FUNCTION);
		TOKENS.put("Command", GeoGebraLexerConstants.COMMAND);
		TOKENS.put("Unknown", LexerConstants.UNKNOWN);
		TOKENS.put("Variable", GeoGebraLexerConstants.VARIABLE);
		TOKENS.put("White", LexerConstants.WHITE);
		TOKENS.put("Tabulation", LexerConstants.TAB);
	}

	private View view;
	private AppD app;
	private List<Integer> typeToDefault = new ArrayList<>();

	private static final Map<String, Color> colorMap = new HashMap<>();

	/* Tokens color */
	static {
		// FIXME: Add colors from theme
		colorMap.put("Default", ThemeD.color(ColorKeys.TEXT));
		colorMap.put("Operator", ThemeD.color(ColorKeys.TEXT));
		colorMap.put("Constante", ThemeD.color(ColorKeys.TEXT));
		colorMap.put("Number", ThemeD.color(ColorKeys.TEXT));
		colorMap.put("OpenClose", ThemeD.color(ColorKeys.TEXT));
		colorMap.put("String", ThemeD.color(ColorKeys.TEXT));
		colorMap.put("Built-in function", ThemeD.color(ColorKeys.TEXT));
		colorMap.put("Function", ThemeD.color(ColorKeys.TEXT));
		colorMap.put("Unknown", ThemeD.color(ColorKeys.TEXT_ERROR));
		colorMap.put("Command", ThemeD.color(ColorKeys.TEXT));
		colorMap.put("Variable", ThemeD.color(ColorKeys.TEXT));
		colorMap.put("White", ThemeD.awtColor(GColor.newColorRGB(0xdcdcdc)));
		colorMap.put("Tabulation", ThemeD.awtColor(GColor.newColorRGB(0xdcdcdc)));
	}

	private static final Map<String, Integer> attribMap = new HashMap<>();

	/*
	 * 0 for nothing 1 to underline 2 to stroke 4 to highlight Just add 1,2 & 4
	 * to combine
	 */
	static {
		attribMap.put("Default", 0);
		attribMap.put("Operator", 0);
		attribMap.put("Constante", 0);
		attribMap.put("Number", 0);
		attribMap.put("OpenClose", 0);
		attribMap.put("String", 0);
		attribMap.put("Built-in function", 0);
		attribMap.put("Function", 0);
		attribMap.put("Command", 4);
		attribMap.put("Unknown", 0);
		attribMap.put("Variable", 0);
		attribMap.put("White", 0);
		attribMap.put("Tabulation", 0);
	}

	/**
	 * The constructor
	 *
	 * @param app
	 *            the Application where this context is needed
	 */
	public GeoGebraContext(AppD app) {
		super();
		this.app = app;
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
		tokenAttrib = new int[GeoGebraLexerConstants.NUMBEROFTOKENS];
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
		tokenColors = new Color[GeoGebraLexerConstants.NUMBEROFTOKENS];
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
		view = new GeoGebraView(elem, new GeoGebraLexer(app), this);
		return view;
	}
}
