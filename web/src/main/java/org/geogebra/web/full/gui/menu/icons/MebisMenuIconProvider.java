package org.geogebra.web.full.gui.menu.icons;

import org.geogebra.web.resources.SVGResource;
import org.gwtproject.resources.client.Resource;

/**
 * Gives access to Mebis menu icons.
 */
@Resource
public interface MebisMenuIconProvider extends DefaultMenuIconProvider {

	MebisMenuIconProvider INSTANCE = new MebisMenuIconProviderImpl();

	/*public IconSpec matchIconWithResource(Icon icon) {
		switch (icon) {
		case CLEAR:
			return new FaIconSpec("fa-file");
		case SEARCH:
			return new FaIconSpec("fa-folder-open");
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
			icon.addClassName("fa-regular");
			return icon;
		}
	}*/

	@Override
	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/file_plus.svg")
	SVGResource clear();

	@Override
	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/folder-open.svg")
	SVGResource search();
}