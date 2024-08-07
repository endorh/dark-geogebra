package org.geogebra.common.kernel.commands;

import org.geogebra.common.kernel.StringTemplate;
import org.geogebra.common.kernel.geos.GeoFunction;
import org.geogebra.test.annotation.Issue;
import org.junit.Assert;
import org.junit.Test;

import com.himamis.retex.editor.share.util.Unicode;

/**
 * Tests for derivatives.
 */
public class DerivativeTest extends AlgebraTest {

	@Test
	public void testNoCasDerivative() {
		app.enableCAS(false);
		t("Derivative(sin(x))", "NDerivative[sin(x)]");
	}

	@Test
	public void differentDerivativeCharsShouldReproduceDerivative() {
		app.enableCAS(false);
		add("f = x*x");
		add("g(x) = f‘(x)");
		add("h(x) = f’(x)");

		GeoFunction g = (GeoFunction) app.getKernel().lookupLabel("g");
		Assert.assertEquals("NDerivative(f)",
				g.getFunction().toString(StringTemplate.defaultTemplate));

		GeoFunction h = (GeoFunction) app.getKernel().lookupLabel("h");
		Assert.assertEquals("NDerivative(f)",
				h.getFunction().toString(StringTemplate.defaultTemplate));
	}

	@Test
	@Issue("APPS-5662")
	public void fastDerivativeWithMixedNumbers() {
		add("f:NDerivative(7x+1" + Unicode.INVISIBLE_PLUS + "2/3)");
		t("f(5)", "7");
	}

	/**
	 * @param command
	 *            algebra input to be processed
	 */
	protected void add(String command) {
		app.getKernel().getAlgebraProcessor().processAlgebraCommand(command, false);
	}
}
