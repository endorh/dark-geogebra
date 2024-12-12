package org.geogebra.common.gui.menu.impl;

import org.geogebra.common.gui.menu.MenuIcon;
import org.geogebra.common.gui.menu.MenuItem;

abstract class AbtractMenuItem implements MenuItem {

	private final MenuIcon icon;
	private final String label;

	AbtractMenuItem(MenuIcon icon, String label) {
		this.icon = icon;
		this.label = label;
	}

	@Override
	public MenuIcon getIcon() {
		return icon;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
