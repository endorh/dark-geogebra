package org.geogebra.web.full.gui.toolbar.mow.toolbox.icons;

import org.geogebra.web.resources.SVGResource;
import org.gwtproject.resources.client.ClientBundle;
import org.gwtproject.resources.client.Resource;

@Resource
public interface DefaultToolboxIconResources extends ClientBundle {

	DefaultToolboxIconResources INSTANCE = new DefaultToolboxIconResourcesImpl();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/toolbox/mouse_cursor.svg")
	SVGResource mouse_cursor();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/toolbox/tool_shapes.svg")
	SVGResource shapes();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/toolbox/add_box.svg")
	SVGResource apps();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/toolbox/text_fields.svg")
	SVGResource texts();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/dialog/upload_black_24dp.svg")
	SVGResource upload();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/openFileView/link_black_24dp.svg")
	SVGResource link();
}
