package org.geogebra.common.kernel.advanced;

import org.geogebra.common.kernel.Kernel;
import org.geogebra.common.kernel.algos.AlgoElement;
import org.geogebra.common.kernel.algos.AlgoLocus;
import org.geogebra.common.kernel.algos.AlgoPointOnPath;
import org.geogebra.common.kernel.arithmetic.BooleanValue;
import org.geogebra.common.kernel.arithmetic.Command;
import org.geogebra.common.kernel.commands.CommandProcessor;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoLocus;
import org.geogebra.common.kernel.geos.GeoPoint;
import org.geogebra.common.kernel.implicit.GeoImplicit;
import org.geogebra.common.kernel.prover.AlgoLocusEquation;
import org.geogebra.common.main.MyError;

/**
 * LocusEquation[ &lt;GeoLocus> ]
 * 
 * LocusEquation[ &lt;GeoPoint>, &lt;GeoPoint> ]
 * 
 * LocusEquation[ &lt;Boolean Expression>, &lt;Free Point> ] TODO: add this
 * syntax to command.properties
 */
public class CmdLocusEquation extends CommandProcessor {
	/**
	 * Create new command processor
	 * 
	 * @param kernel
	 *            kernel
	 */
	public CmdLocusEquation(Kernel kernel) {
		super(kernel);
	}

	@Override
	public GeoElement[] process(Command c) throws MyError {
		int n = c.getArgumentNumber();
		boolean[] ok = new boolean[n];
		GeoElement[] arg = resArgs(c);
		GeoPoint locusPoint, movingPoint;
		GeoElement implicitLocus;

		switch (n) {
		case 1:
			if (arg[0] instanceof GeoLocus locus
					&& arg[0].getParentAlgorithm() != null
					&& arg[0].getParentAlgorithm() instanceof AlgoLocus) {
				AlgoLocus algo = (AlgoLocus) locus.getParentAlgorithm();
				locusPoint = (GeoPoint) algo.getLocusPoint();
				movingPoint = (GeoPoint) algo.getMovingPoint();
			} else {
				throw argErr(c, arg[0]);
			}
			break;

		case 2:
			if ((ok[0] = (arg[0].isGeoPoint()))
					&& (ok[1] = (arg[1].isGeoPoint()))) {
				locusPoint = (GeoPoint) arg[0];
				movingPoint = (GeoPoint) arg[1];
			} else {
				AlgoElement ae;
				if ((ok[0] = (arg[0] instanceof BooleanValue))
						// second parameter should be a (semi-)free point
						&& (ok[1] = (arg[1].isGeoPoint()
								&& ((ae = arg[1].getParentAlgorithm()) == null
										|| ae instanceof AlgoPointOnPath)))) {
					implicitLocus = arg[0];
					movingPoint = (GeoPoint) arg[1];
					return new GeoElement[] { locusEquation(c.getLabel(),
							implicitLocus, movingPoint).toGeoElement() };

				} // else
				throw argErr(c, getBadArg(ok, arg));
			}
			break;

		default:
			throw argNumErr(c);
		}

		return new GeoElement[] {
				locusEquation(c.getLabel(), locusPoint, movingPoint)
						.toGeoElement() };
	}

	/**
	 * locus equation for Q dependent on P.
	 * 
	 * @param label
	 *            output label
	 * @param locusPoint
	 *            generating point
	 * @param movingPoint
	 *            moving point
	 * @return implicit locus curve
	 */
	final public GeoImplicit locusEquation(String label, GeoPoint locusPoint,
			GeoPoint movingPoint) {
		if (movingPoint.getPath() == null || locusPoint.getPath() != null
				|| !movingPoint.isParentOf(locusPoint)) {
			return null;
		}
		AlgoLocusEquation algo = new AlgoLocusEquation(cons, locusPoint,
				movingPoint);
		GeoImplicit poly = algo.getPoly();

		poly.setLabel(label);
		return poly;
	}

	/**
	 * locus equation for computing implicit locus
	 * 
	 * @param label
	 *            output label
	 * @param implicitLocus
	 *            generating point or condition
	 * @param movingPoint
	 *            moving point
	 * @return implicit locus curve
	 */
	final public GeoImplicit locusEquation(String label,
			GeoElement implicitLocus, GeoPoint movingPoint) {
		AlgoLocusEquation algo = new AlgoLocusEquation(cons, implicitLocus,
				movingPoint);
		GeoImplicit poly = algo.getPoly();

		poly.setLabel(label);
		return poly;
	}

}