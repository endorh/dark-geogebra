package org.geogebra.web.html5.main.toolbox;

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
		case PEN:
			return res.mode_pen();
		case HIGHLIGHTER:
			return res.mode_highlighter_32();
		case ERASER:
			return res.mode_eraser_32();
		case PLUS:
			return res.add_black();
		case SHAPES:
			return res.shapes();
		case TEXTS:
			return res.texts();
		case TEXT:
			return res.mode_media_text();
		case EQUATION:
			return res.mode_equation();
		case APPS:
			return res.apps();
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
