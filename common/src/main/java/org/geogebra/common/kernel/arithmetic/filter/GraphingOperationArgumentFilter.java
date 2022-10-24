package org.geogebra.common.kernel.arithmetic.filter;

import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.arithmetic.ExpressionValue;
import org.geogebra.common.kernel.arithmetic.FunctionalNVar;
import org.geogebra.common.kernel.arithmetic.NumberValue;
import org.geogebra.common.kernel.arithmetic.VectorNDValue;
import org.geogebra.common.plugin.Operation;

/**
 * OperationArgumentFilter for the Graphing app.
 */
public class GraphingOperationArgumentFilter implements OperationArgumentFilter {

	@Override
	public boolean isAllowed(Operation operation, ExpressionValue left, ExpressionValue right) {
		return switch (operation) {
			case ABS -> allowAbs(left);
			case MULTIPLY -> !isInnerProduct(left, right);
			case VECTORPRODUCT -> false;
			default -> true;
		};
	}

	private boolean isInnerProduct(ExpressionValue left,
			ExpressionValue right) {
		return left instanceof VectorNDValue && right instanceof VectorNDValue
				&& (!isComplex(left) || !isComplex(right));
	}

	private boolean isComplex(ExpressionValue left) {
		return ((VectorNDValue) left).getToStringMode() == Kernel.COORD_COMPLEX;
	}

	private boolean allowAbs(ExpressionValue left) {
		return left instanceof NumberValue || left instanceof FunctionalNVar;
	}
}
