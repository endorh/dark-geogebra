package org.geogebra.common.kernel.interval;

import org.geogebra.common.kernel.arithmetic.ExpressionNode;
import org.geogebra.common.kernel.arithmetic.ExpressionValue;
import org.geogebra.common.kernel.arithmetic.Inspecting;
import org.geogebra.common.plugin.Operation;

/**
 * Checker to determine ia an operation is supported by the interval arithmetic.
 */
public class UnsupportedOperatorChecker implements Inspecting {

	@Override
	public boolean check(ExpressionValue v) {
		ExpressionNode wrap = v.wrap();
		Operation operation = wrap.getOperation();
		return switch (operation) {
			case PLUS, MINUS, DIVIDE, NROOT, DIFF, SIN, COS, SEC, COT, CSC, SQRT, TAN, EXP, LOG, ARCCOS, ARCSIN, ARCTAN, ABS, COSH, SINH, TANH, LOG10, LOG2, IF, IF_ELSE, NO_OPERATION ->
					false;
			case MULTIPLY -> checkMultiply(wrap);
			case POWER -> checkPower(wrap);
			default -> true;
		};
	}

	private boolean checkMultiply(ExpressionNode node) {
		return isVector(node.getLeft()) ^ isVector(node.getRight());
	}

	private boolean isVector(ExpressionValue node) {
		return node.evaluatesToNDVector() || node.evaluatesToNonComplex2DVector();
	}

	private boolean checkPower(ExpressionNode node) {
		double power = node.getRight().evaluateDouble();
		if (Double.isNaN(power)) {
			return true;
		}

		return node.getRightTree().containsFunctionVariable()
				|| power >= 100;
	}
}
