package org.geogebra.common.kernel.interval;

import static org.geogebra.common.kernel.interval.IntervalOperands.abs;
import static org.geogebra.common.kernel.interval.IntervalOperands.acos;
import static org.geogebra.common.kernel.interval.IntervalOperands.asin;
import static org.geogebra.common.kernel.interval.IntervalOperands.atan;
import static org.geogebra.common.kernel.interval.IntervalOperands.cos;
import static org.geogebra.common.kernel.interval.IntervalOperands.cosh;
import static org.geogebra.common.kernel.interval.IntervalOperands.cot;
import static org.geogebra.common.kernel.interval.IntervalOperands.difference;
import static org.geogebra.common.kernel.interval.IntervalOperands.divide;
import static org.geogebra.common.kernel.interval.IntervalOperands.exp;
import static org.geogebra.common.kernel.interval.IntervalOperands.log;
import static org.geogebra.common.kernel.interval.IntervalOperands.log10;
import static org.geogebra.common.kernel.interval.IntervalOperands.log2;
import static org.geogebra.common.kernel.interval.IntervalOperands.multiply;
import static org.geogebra.common.kernel.interval.IntervalOperands.nthRoot;
import static org.geogebra.common.kernel.interval.IntervalOperands.pow;
import static org.geogebra.common.kernel.interval.IntervalOperands.sec;
import static org.geogebra.common.kernel.interval.IntervalOperands.sin;
import static org.geogebra.common.kernel.interval.IntervalOperands.sinh;
import static org.geogebra.common.kernel.interval.IntervalOperands.sqrt;
import static org.geogebra.common.kernel.interval.IntervalOperands.tan;
import static org.geogebra.common.kernel.interval.IntervalOperands.tanh;

import org.geogebra.common.plugin.Operation;

import com.google.j2objc.annotations.Weak;

/**
 * Evaluates expression using interval arithmetic
 */
class IntervalEvaluate {
	@Weak
	private final Interval interval;

	/**
	 *
	 * @param interval to evaluate on.
	 */
	IntervalEvaluate(Interval interval) {
		this.interval = interval;
	}

	/**
	 * Executes the operation on two intervals
	 *
	 * @param operation to execute.
	 * @param other interval as parameter
	 * @return result interval of the operation
	 */
	Interval evaluate(Operation operation, Interval other) {
		switch (operation) {
		case PLUS:
			return interval.add(other);
		case MINUS:
			return interval.subtract(other);
		case MULTIPLY:
			return multiply(interval, other);
		case DIVIDE:
			return divide(interval, other);
		case POWER:
			return pow(interval, other);
		case NROOT:
			return nthRoot(interval, other);
		case INTEGRAL:
		case INVERSE_NORMAL:
			break;
		case DIFF:
			return difference(interval, other);
		default:
			throw new IllegalStateException("Unexpected value: " + operation);
		}
		return interval;
	}

	/**
	 * Executes unary operation on the interval.
	 *
	 * @param operation to execute
	 * @return the result interval
	 */
	Interval evaluate(Operation operation) {
		switch (operation) {
		case COS:
			return cos(interval);
		case SIN:
			return sin(interval);
		case SEC:
			return sec(interval);
		case COT:
			return cot(interval);
		case TAN:
			return tan(interval);
		case EXP:
			return exp(interval);
		case LOG:
			return log(interval);
		case ARCCOS:
			return acos(interval);
		case ARCSIN:
			return asin(interval);
		case ARCTAN:
			return atan(interval);
		case SQRT:
			return sqrt(interval);
		case SQRT_SHORT:
			break;
		case ABS:
			return abs(interval);
		case COSH:
			return cosh(interval);
		case SINH:
			return sinh(interval);
		case TANH:
			return tanh(interval);
		case LOG10:
			return log10(interval);
		case LOG2:
			return log2(interval);
		default:
			return interval;
		}
		return interval;
	}
}