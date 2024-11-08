package org.geogebra.common.gui.view.algebra.contextmenu.impl;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.geogebra.common.contextmenu.AlgebraContextMenuItem;
import org.geogebra.common.contextmenu.ContextMenuFactory;
import org.geogebra.common.kernel.geos.BaseSymbolicTest;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoNumeric;
import org.junit.Ignore;
import org.junit.Test;

public class RemoveLabelActionTest extends BaseSymbolicTest {

	@Test
	@Ignore("Should be fixed in APPS-6003")
	public void shouldForceLabelsForSliders() {
		GeoElement integral = add("Integral(sin(x))");
		GeoNumeric constant = (GeoNumeric) lookup("c_{1}");
		constant.initAlgebraSlider();
		List<AlgebraContextMenuItem> items = ContextMenuFactory
				.makeAlgebraContextMenu(constant, ap, app.getConfig().getAppCode());
		assertFalse("Remove label should not be available for sliders in CAS",
				items.contains(AlgebraContextMenuItem.RemoveLabel));
		items = ContextMenuFactory
				.makeAlgebraContextMenu(integral, ap, app.getConfig().getAppCode());
		assertTrue("Remove label should be available for integral in CAS",
				items.contains(AlgebraContextMenuItem.RemoveLabel));
	}
}
