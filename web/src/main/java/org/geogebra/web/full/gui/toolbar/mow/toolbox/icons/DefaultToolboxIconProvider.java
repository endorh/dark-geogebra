package org.geogebra.web.full.gui.toolbar.mow.toolbox.icons;

import org.geogebra.web.full.gui.toolbar.mow.toolbox.ToolboxIcon;
import org.geogebra.web.html5.gui.view.IconSpec;
import org.geogebra.web.html5.gui.view.ImageIconSpec;
import org.geogebra.web.resources.SVGResource;

public class DefaultToolboxIconProvider implements ToolboxIconProvider {

	private static final DefaultToolboxIconResources res = DefaultToolboxIconResources.INSTANCE;

	@Override
	public IconSpec matchIconWithResource(ToolboxIcon icon) {
		return new ImageIconSpec(findImage(icon));
	}

	private SVGResource findImage(ToolboxIcon icon) {
		switch (icon) {
		case MOUSE_CURSOR:
			return res.mouse_cursor();
		case SHAPES:
			return res.shapes();
		case APPS:
			return res.apps();
		case TEXTS:
			return res.texts();
		case UPLOAD:
			return res.upload();
		case LINK:
			return res.link();
		case SPOTLIGHT:
			return res.target();
		case DRAG:
			return res.drag();
		case RULER:
			return res.mode_ruler();
		default:
			return null;
		}
	}
}
