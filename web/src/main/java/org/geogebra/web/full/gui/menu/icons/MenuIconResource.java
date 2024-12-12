package org.geogebra.web.full.gui.menu.icons;

import org.geogebra.common.gui.menu.MenuIcon;
import org.geogebra.web.html5.gui.view.IconSpec;

public class MenuIconResource {
	private final MenuIconProvider menuIconProvider;

	public MenuIconResource(MenuIconProvider menuIconProvider) {
		this.menuIconProvider = menuIconProvider;
	}

	public IconSpec getImageResource(MenuIcon icon) {
		return menuIconProvider.matchIconWithResource(icon);
	}

}
