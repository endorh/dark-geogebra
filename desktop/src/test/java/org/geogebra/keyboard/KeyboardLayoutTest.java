package org.geogebra.keyboard;

import org.geogebra.keyboard.base.KeyboardFactory;
import org.geogebra.keyboard.base.model.KeyboardModel;
import org.geogebra.keyboard.base.model.Row;
import org.geogebra.keyboard.base.model.WeightedButton;
import org.junit.Assert;
import org.junit.Test;

public class KeyboardLayoutTest {
	@Test
	public void testSpecialTab() {
		KeyboardFactory kbf = KeyboardFactory.INSTANCE;
		KeyboardModel kb = kbf.createSpecialSymbolsKeyboard().getModel();
		StringBuilder actions = new StringBuilder();
		StringBuilder resources = new StringBuilder();
		for (Row row : kb.getRows()) {
			for (WeightedButton button : row.getButtons()) {
				resources.append(button.getResourceName()).append(",");
				actions.append(button.getPrimaryActionName()).append(",");
			}
		}

		Assert.assertEquals("∞,≟,≠,∧,∨,→,¬,⊗,∥,⟂,∈,⊂,⊆,∠,FLOOR,CEIL,[,],:,&,@,#,"
						+ "Translate.currency,BACKSPACE_DELETE,,,',\",′,"
						+ "″,LEFT_ARROW,RIGHT_ARROW,RETURN_ENTER,",
				resources.toString());
		Assert.assertEquals("∞,≟,≠,∧,∨,→,¬,⊗,∥,⟂,∈,⊂,⊆,∠,⌊,⌈,[,],:,&,@,#,Translate.currency,"
						+ "BACKSPACE_DELETE,,,',\",′,″,LEFT_CURSOR,RIGHT_CURSOR,RETURN_ENTER,",
				actions.toString());

	}
}
