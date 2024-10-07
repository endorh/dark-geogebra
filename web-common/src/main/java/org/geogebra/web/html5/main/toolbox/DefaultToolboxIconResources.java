package org.geogebra.web.html5.main.toolbox;

import org.geogebra.web.resources.SVGResource;
import org.gwtproject.resources.client.ClientBundle;
import org.gwtproject.resources.client.Resource;

@Resource
public interface DefaultToolboxIconResources extends ClientBundle {

	DefaultToolboxIconResources INSTANCE = new DefaultToolboxIconResourcesImpl();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/toolbox/mouse_cursor.svg")
	SVGResource mouse_cursor();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_pen.svg")
	SVGResource mode_pen();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_highlighter.svg")
	SVGResource mode_highlighter_32();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_eraser.svg")
	SVGResource mode_eraser_32();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/av/plusMenu/add.svg")
	SVGResource add_black();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/toolbox/text_fields.svg")
	SVGResource texts();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_media_text.svg")
	SVGResource mode_media_text();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_equation.svg")
	SVGResource mode_equation();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/toolbox/tool_shapes.svg")
	SVGResource shapes();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/mow/toolbox/add_box.svg")
	SVGResource apps();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/dialog/upload_black_24dp.svg")
	SVGResource upload();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/openFileView/link_black_24dp.svg")
	SVGResource link();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/ev/target.svg")
	SVGResource target();

	@Source("org/geogebra/common/icons/svg/web/matDesignIcons/ev/drag_pad.svg")
	SVGResource drag();

	@Source("org/geogebra/common/icons/svg/web/toolIcons/mode_ruler.svg")
	SVGResource mode_ruler();
}
