package org.geogebra.common.gui.menu.impl;

import org.geogebra.common.gui.menu.Action;
import org.geogebra.common.gui.menu.ActionableItem;
import org.geogebra.common.gui.menu.MenuIcon;

class ActionableItemImpl extends AbtractMenuItem implements ActionableItem {

	private final Action action;

	ActionableItemImpl(String label, Action action) {
		this(null, label, action);
	}

	ActionableItemImpl(MenuIcon icon, String label, Action action) {
		super(icon, label);
		this.action = action;
	}

	@Override
	public Action getAction() {
		return action;
	}
}
