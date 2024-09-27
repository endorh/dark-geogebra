package org.geogebra.web.full.gui.toolbar.mow.toolbox;

import org.geogebra.web.full.gui.toolbar.mow.toolbox.icons.ToolboxIconProvider;
import org.geogebra.web.html5.gui.view.IconSpec;

public class ToolboxIconResource {
	private final ToolboxIconProvider toolboxIconProvider;

	ToolboxIconResource(ToolboxIconProvider toolboxIconProvider) {
		this.toolboxIconProvider = toolboxIconProvider;
	}

	public IconSpec getImageResource(ToolboxIcon icon) {
		return toolboxIconProvider.matchIconWithResource(icon);
	}
}
