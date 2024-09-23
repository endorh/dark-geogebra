package org.geogebra.web.full.gui.toolbar.mow.toolbox.icons;

import org.geogebra.web.full.gui.toolbar.mow.toolbox.ToolboxIcon;
import org.geogebra.web.html5.gui.view.IconSpec;

public interface ToolboxIconProvider {

	IconSpec matchIconWithResource(ToolboxIcon icon);
}
