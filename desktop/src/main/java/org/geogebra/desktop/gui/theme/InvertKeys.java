package org.geogebra.desktop.gui.theme;

public enum InvertKeys implements ThemeD.Key {
	ICONS("icons", null),
	IMAGES("images", null),
	COLORS("colors", null),
	VIEW_2D("view.2d", null),
	VIEW_3D("view.3d", VIEW_2D);

	private final String name;
	private final ThemeD.Key parent;

	InvertKeys(String name, ThemeD.Key parent) {
		this.name = name;
		this.parent = parent;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public ThemeD.Key getParent() {
		return parent;
	}
}
