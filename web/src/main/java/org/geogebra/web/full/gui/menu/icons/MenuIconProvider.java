package org.geogebra.web.full.gui.menu.icons;

import org.geogebra.common.gui.menu.MenuIcon;
import org.geogebra.web.html5.gui.view.IconSpec;

public interface MenuIconProvider {

	IconSpec matchIconWithResource(MenuIcon icon);
}
