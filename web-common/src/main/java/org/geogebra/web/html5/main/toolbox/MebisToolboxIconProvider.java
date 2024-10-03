package org.geogebra.web.html5.main.toolbox;

import org.geogebra.web.html5.gui.view.IconSpec;
import org.gwtproject.dom.client.Element;
import org.gwtproject.user.client.DOM;

public class MebisToolboxIconProvider extends DefaultToolboxIconProvider {

	@Override
	public IconSpec matchIconWithResource(ToolboxIcon icon) {
		switch (icon) {
		case MOUSE_CURSOR:
			return new FaIconSpec("fa-arrow-pointer");
		case PEN:
			return new FaIconSpec("fa-pen");
		case HIGHLIGHTER:
			return new FaIconSpec("fa-highlighter");
		case ERASER:
			return new FaIconSpec("fa-eraser");
		case PLUS:
			return new FaIconSpec("fa-plus");
		case SHAPES:
			return new FaIconSpec("fa-shapes");
		case TEXTS:
			return new FaIconSpec("fa-text-size");
		case TEXT:
			return new FaIconSpec("fa-square-t");
		case EQUATION:
			return new FaIconSpec("fa-calculator-simple");

		case APPS:
			return new FaIconSpec("fa-square-plus");
		case UPLOAD:
			return new FaIconSpec("fa-arrow-up-from-line");
		case LINK:
			return new FaIconSpec("fa-link");
		case SPOTLIGHT:
			return new FaIconSpec("fa-location-crosshairs");
		case RULER:
			return new FaIconSpec("fa-ruler-horizontal");
		}
		return super.matchIconWithResource(icon);
	}

	public static class FaIconSpec implements IconSpec {
		String name;

		public FaIconSpec(String name) {
			this.name = name;
		}

		@Override
		public Element toElement() {
			Element icon = DOM.createElement("I");
			icon.setClassName(name);
			icon.addClassName("fa-light");
			return icon;
		}

		@Override
		public IconSpec withFill(String color) {
			// not needed, solved through css
			return this;
		}
	}
}
