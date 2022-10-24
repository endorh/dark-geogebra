package org.geogebra.common.kernel.cas;

import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.arithmetic.BooleanValue;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.commands.CommandNotFoundError;
import org.geogebra.common.kernel.commands.CommandProcessor;
import org.geogebra.common.kernel.commands.Commands;
import org.geogebra.common.kernel.commands.EvalInfo;
import org.geogebra.common.kernel.geos.CasEvaluableFunction;
import org.geogebra.common.kernel.geos.GeoBoolean;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoFunctionable;
import org.geogebra.common.kernel.geos.GeoNumberValue;
import org.geogebra.common.kernel.geos.GeoNumeric;
import org.geogebra.common.main.MyError;

/**
 * Integral[ &lt;GeoFunction> ]
 *
 * Integral[ &lt;GeoFunction>, &lt;Number a>, &lt;Number b> ]
 *
 * Integral[ &lt;GeoFunction f>, &lt;GeoFunction g>, &lt;Number a>, &lt;Number b> ]
 */
public class CmdIntegral extends CommandProcessor implements UsesCAS {

	// from GeoGebra 4.0, Integral has been split into Integral and
	// IntegralBetween
	// old syntax and files will still work
	private String internalCommandName;

	/**
	 * Create new command processor
	 * 
	 * @param command
	 *            IntegralBetween, Integral or NIntegral
	 * @param kernel
	 *            kernel
	 */
	public CmdIntegral(Kernel kernel, Commands command) {
		super(kernel);
		internalCommandName = command.name();
	}

	@Override
	final public GeoElement[] process(Command c, EvalInfo info) throws MyError {
		if (c.getArgumentNumber() < 3
				&& !app.getSettings().getCasSettings().isEnabled()) {
			throw new CommandNotFoundError(app.getLocalization(), c);
		}
		int n = c.getArgumentNumber();
		boolean[] ok = new boolean[n];
		GeoElement[] arg;

		switch (n) {
		case 1 -> {
			arg = resArgs(c);
			if (arg[0].isRealValuedFunction()) {
				GeoElement[] ret = {
						integral(((GeoFunctionable) arg[0]).getGeoFunction(),
								null, info)};
				ret[0].setLabel(c.getLabel());
				return ret;
			}
			throw argErr(c, arg[0]);
		}
		case 2 -> {
			// Integral[ f(x,y), x]
			arg = resArgsLocalNumVar(c, 1, 1, -1);
			if ((ok[0] = arg[0] instanceof CasEvaluableFunction)
					&& (ok[1] = arg[1].isGeoNumeric())) {
				GeoElement[] ret = {integral((CasEvaluableFunction) arg[0], // function
						(GeoNumeric) arg[1], info)}; // var
				ret[0].setLabel(c.getLabel());
				return ret;
			}
			throw argErr(c, getBadArg(ok, arg));
		}
		case 3 -> {
			arg = resArgs(c);
			if ((ok[0] = (arg[0].isRealValuedFunction()))
					&& (ok[1] = (arg[1] instanceof GeoNumberValue))
					&& (ok[2] = (arg[2] instanceof GeoNumberValue))) {

				AlgoIntegralDefinite algo = new AlgoIntegralDefinite(cons,
						c.getLabel(),
						((GeoFunctionable) arg[0]).getGeoFunction(),
						(GeoNumberValue) arg[1], (GeoNumberValue) arg[2],
						"NIntegral".equals(internalCommandName));

				return algo.getIntegral().asArray();
			}
			throw argErr(c, getBadArg(ok, arg));
		}
		case 4 -> {
			arg = resArgs(c);
			// difference of two functions
			if ((ok[0] = (arg[0].isRealValuedFunction()))
					&& (ok[1] = (arg[1].isRealValuedFunction()))
					&& (ok[2] = (arg[2] instanceof GeoNumberValue))
					&& (ok[3] = (arg[3] instanceof GeoNumberValue
					&& !(arg[3] instanceof BooleanValue)))
					&& !"NIntegral".equals(internalCommandName)) {

				AlgoIntegralFunctions algo = new AlgoIntegralFunctions(cons,
						c.getLabel(),
						((GeoFunctionable) arg[0]).getGeoFunction(),
						((GeoFunctionable) arg[1]).getGeoFunction(),
						(GeoNumberValue) arg[2], (GeoNumberValue) arg[3]);

				return algo.getIntegral().asArray();
			}
			// single function integral with evaluate option
			else if ((ok[0] = (arg[0].isRealValuedFunction()))
					&& (ok[1] = (arg[1] instanceof GeoNumberValue))
					&& (ok[2] = (arg[2] instanceof GeoNumberValue))
					&& (ok[3] = (arg[3].isGeoBoolean()))) {

				AlgoIntegralDefinite algo = new AlgoIntegralDefinite(cons,
						c.getLabel(),
						((GeoFunctionable) arg[0]).getGeoFunction(),
						(GeoNumberValue) arg[1], (GeoNumberValue) arg[2],
						(GeoBoolean) arg[3]);

				return algo.getIntegral().asArray();
			} else {
				throw argErr(c, getBadArg(ok, arg));
			}
		}
		case 5 -> {
			arg = resArgs(c);
			// difference of two functions with evaluate option
			if ((ok[0] = (arg[0].isRealValuedFunction()))
					&& (ok[1] = (arg[1].isRealValuedFunction()))
					&& (ok[2] = (arg[2] instanceof GeoNumberValue))
					&& (ok[3] = (arg[3] instanceof GeoNumberValue)
					&& (ok[4] = (arg[4].isGeoBoolean())))) {

				AlgoIntegralFunctions algo = new AlgoIntegralFunctions(cons,
						c.getLabel(),
						((GeoFunctionable) arg[0]).getGeoFunction(),
						((GeoFunctionable) arg[1]).getGeoFunction(),
						(GeoNumberValue) arg[2], (GeoNumberValue) arg[3],
						(GeoBoolean) arg[4]);

				return algo.getIntegral().asArray();
			}
			throw argErr(c, getBadArg(ok, arg));
		}
		default -> throw argNumErr(c);
		}
	}

	/**
	 * Integral of function f
	 * 
	 * @param info
	 *            evaluation flags
	 * @param f
	 *            function
	 * @param var
	 *            variable
	 * @return integral of given function wrt given variable
	 */
	final public GeoElement integral(CasEvaluableFunction f, GeoNumeric var,
			EvalInfo info) {
		AlgoIntegral algo = new AlgoIntegral(cons, f, var, true, info,
				"NIntegral".equals(internalCommandName));
		return algo.getResult();
	}
}
