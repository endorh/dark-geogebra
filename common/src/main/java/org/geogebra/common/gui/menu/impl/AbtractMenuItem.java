package org.geogebra.common.gui.menu.impl;

import org.geogebra.common.gui.menu.Icon;
import org.geogebra.common.gui.menu.MenuItem;

abstract class AbtractMenuItem implements MenuItem {

	private final Icon icon;
	private final String label;

	AbtractMenuItem(Icon icon, String label) {
		this.icon = icon;
		this.label = label;
	}

	@Override
	public Icon getIcon() {
		return icon;
	}

	@Override
	public String getLabel() {
		return label;
	}
}
