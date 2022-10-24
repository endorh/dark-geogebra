package org.geogebra.desktop.gui.theme;

public enum ColorKeys implements ThemeD.Key {
	BACKGROUND("background", null),
	FOREGROUND("foreground", null),
	BACKGROUND_ALGEBRA("background.algebra", BACKGROUND),
	BACKGROUND_SURFACE("background.surface", BACKGROUND),
	BACKGROUND_SELECTED("background.selected", BACKGROUND_SURFACE),
	SELECTION("selection", BACKGROUND),
	SELECTION_DISABLED("selection.disabled", SELECTION), // Remove
	BACKGROUND_CONTROL("background.control", BACKGROUND),
	OUTLINE_LIGHT("outline.light", BACKGROUND), // LIGHT_GRAY
	OUTLINE("outline", BACKGROUND), // GRAY
	OUTLINE_DARK("outline.dark", OUTLINE_LIGHT), // DARK_GRAY
	CONTROL_HIGHLIGHT("control.border", OUTLINE_LIGHT), // SystemColor.controlHighlight, SystemColor.controlLtHighlight
	CONTROL_SHADOW("control.shadow", OUTLINE_DARK), // SystemColor.controlShadow, SystemColor.controlDkShadow
	ERROR("error", SELECTION),
	TEXT("text", FOREGROUND),
	TEXT_SURFACE("text.surface", TEXT),
	TEXT_SELECTED("text.selected", TEXT_SURFACE),
	TEXT_ERROR("text.error", TEXT),
	TEXT_DISABLED("text.disabled", TEXT);

	private final String name;
	private final ThemeD.Key parent;

	ColorKeys(String name, ThemeD.Key parent) {
		this.name = name;
		this.parent = parent;
	}

	@Override public String getName() {
		return name;
	}
	@Override public ThemeD.Key getParent() {
		return parent;
	}
}
