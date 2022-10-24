package org.geogebra.common.kernel.arithmetic;

import org.geogebra.common.plugin.Operation;

/**
 * Replaces asin(0.5) with asind(0.5)
 *
 */
public class ArcTrigReplacer implements Traversing {

	private static ArcTrigReplacer replacer = new ArcTrigReplacer();

	@Override
	public ExpressionValue process(ExpressionValue ev) {
		if (ev instanceof ExpressionNode en) {
			Operation op = en.getOperation();
			Operation newOp = getDegreeInverseTrigOp(op);

			if (newOp != op) {
				en.setOperation(newOp);
			}
		}
		return ev;
	}

	/**
	 * Maps asin -> asind, acos -> acosd, uses input operation as fallback
	 * @param op operation
	 * @return inverse trig function that produces degrees or input operation
	 */
	public static Operation getDegreeInverseTrigOp(Operation op) {
		if (op == null) {
			return null;
		}
		return switch (op) {
			default ->
				// do nothing
					op;
			case ARCSIN -> Operation.ARCSIND;
			case ARCCOS -> Operation.ARCCOSD;
			case ARCTAN -> Operation.ARCTAND;
			case ARCTAN2 -> Operation.ARCTAN2D;
		};
	}

	/**
	 * @return replacer
	 */
	public static ArcTrigReplacer getReplacer() {
		return replacer;
	}
}
