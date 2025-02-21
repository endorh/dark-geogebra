/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

 */

package org.geogebra.common.kernel.algos;

import java.util.ArrayList;

import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.arithmetic.NumberValue;
import org.geogebra.common.kernel.geos.GeoAngle;
import org.geogebra.common.kernel.geos.GeoAngle.AngleStyle;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoList;
import org.geogebra.common.kernel.geos.GeoNumeric;

/**
 * Mean, variance, sum, sum of squares, standard deviation of a list adapted
 * from AlgoListMin to replace AlgoMean, AlgoSum
 * 
 * @author Michael Borcherds
 * @version 2008-02-18
 */

public abstract class AlgoStats1D extends AlgoElement {

	private GeoList geoList; // data
	private GeoList geoList2; // frequencies
	private GeoNumeric Truncate; // input
	private GeoNumeric result; // output

	private int stat;

	protected final static int STATS_MEAN = 0;
	protected final static int STATS_VARIANCE = 1;
	protected final static int STATS_SIGMAX = 2;
	protected final static int STATS_SIGMAXX = 3;
	protected final static int STATS_SD = 4;
	protected final static int STATS_PRODUCT = 5;
	protected final static int STATS_SXX = 6;
	protected final static int STATS_SAMPLE_VARIANCE = 7;
	protected final static int STATS_SAMPLE_SD = 8;
	protected final static int STATS_MEAN_ABSOLUTE_DEVIATION = 9;

	public AlgoStats1D(Construction cons, GeoList geoList, int stat) {
		this(cons, geoList, null, null, stat);
	}

	public AlgoStats1D(Construction cons, GeoList geoList, GeoList geoList2,
			int stat) {
		this(cons, geoList, geoList2, null, stat);
	}

	/**
	 * @param cons
	 *            construction
	 * @param geoList
	 *            input list
	 * @param geoList2
	 *            frequencies
	 * @param truncate
	 *            truncate index
	 * @param stat
	 *            stat type
	 */
	protected AlgoStats1D(Construction cons, GeoList geoList, GeoList geoList2,
			GeoNumeric truncate, int stat) {
		super(cons);
		this.geoList = geoList;
		this.geoList2 = geoList2;
		this.stat = stat;
		this.Truncate = truncate;

		if (geoList.size() > 0 && geoList.get(0).isAngle()) {
			result = new GeoAngle(cons);

			// allow unbounded angles (from ggb44). This could break old files
			// (unlikely)
			((GeoAngle) result).setAngleStyle(AngleStyle.UNBOUNDED);
		} else {
			result = new GeoNumeric(cons);
		}

		setInputOutput();
		compute();
	}

	@Override
	protected void setInputOutput() {
		if (geoList.wasDefinedWithCurlyBrackets()) {
			ArrayList<GeoElement> inputList = new ArrayList<>();
			inputList.add(geoList);
			if (geoList2 != null) {
				inputList.add(geoList2);
			}
			if (Truncate != null) {
				inputList.add(Truncate);
			}

			input = new GeoElement[inputList.size()];
			inputList.toArray(input);
			inputList.clear();
		} else {
			input = geoList.elements().toArray(GeoElement[]::new);
		}

		setOnlyOutput(result);
		setDependencies(); // done by AlgoElement
	}

	public GeoNumeric getResult() {
		return result;
	}

	@Override
	public final void compute() {

		// Application.debug("compute: " + geoList);
		if (!geoList.isDefined()) {
			result.setUndefined();
			return;
		}

		if (geoList2 != null) {
			if (!geoList2.isDefined()
					// return undefined if we can't use number * freq or
					// midpoint * freq
					|| !(geoList.size() == geoList2.size()
							|| geoList.size() == geoList2.size() + 1)) {
				result.setUndefined();
				return;
			}
		}

		// eg SigmaXX[{1, 2, 3}, {2, 4, 8}]
		// Sum[{1, 2, 3}, {2, 4, 8}]
		// Product[{1, 2, 3}, {2, 4, 8}]

		int truncate;
		double size = geoList.size();

		if (Truncate != null) {
			if (!Truncate.isDefined()) {
				// this change is done for AlgoProduct,
				// and is probably useful for AlgoSum and other algos too
				result.setUndefined();
				return;
			}
			truncate = (int) Truncate.getDouble();
			if (truncate == 0) {
				result.setValue(0);
				return;
			}
			if (truncate < 1 || truncate > size) {
				result.setUndefined();
				return;
			}
			size = truncate; // truncate the list
		}

		if (size == 0) {
			switch (stat) {
			case STATS_SIGMAX, STATS_SIGMAXX -> {
				result.setValue(0);
				return;
			}
			case STATS_PRODUCT -> {
				result.setValue(1);
				return;
			}
			default -> {
				result.setUndefined();
				return;
			}
			}
		}

		double sumVal = 0;
		double sumSquares = 0;
		double product = 1;
		double sumFreq = 0;
		double frequency = 1;
		double var, mu;
		GeoElement geo, geoFreq, geo2;
		boolean useMidpoint = false;
		double n = 0;

		// list of numbers only, no frequencies
		if (geoList2 == null) {
			double val;
			for (int i = 0; i < size; i++) {
				geo = geoList.get(i);
				if (geo instanceof NumberValue) {
					val = geo.evaluateDouble();
					sumVal += val;
					sumSquares += val * val;
					product *= val;
				} else {
					result.setUndefined();
					return;
				}
			}

			n = size;
		}

		// list of numbers with list of frequencies
		else {
			// if the number list is a list of classes, then we must use a
			// midpoint
			useMidpoint = geoList.size() == geoList2.size() + 1;
			size = useMidpoint ? size - 1 : size;

			double val;
			double val_by_freq;
			for (int i = 0; i < size; i++) {
				geo = geoList.get(i);
				geoFreq = geoList2.get(i);
				if (!(geo instanceof NumberValue)
						|| !(geoFreq instanceof NumberValue)) {
					result.setUndefined();
					return;
				}

				val = geo.evaluateDouble();

				// compute midpoint value if needed
				if (useMidpoint) {
					geo2 = geoList.get(i + 1);
					if (!(geo2 instanceof NumberValue)) {
						result.setUndefined();
						return;
					}
					val = (val + (geo2.evaluateDouble())) / 2;
				}

				frequency = geoFreq.evaluateDouble();

				// handle bad frequency
				if (frequency < 0) {
					result.setUndefined();
					return;
				}

				val_by_freq = val * frequency;
				sumVal += val_by_freq;
				sumSquares += val * val_by_freq;
				sumFreq += frequency;
				product *= Math.pow(val, frequency);

			}

			n = sumFreq;
		}

		mu = sumVal / n;

		switch (stat) {
		default -> result.setValue(Double.NaN);
		case STATS_MEAN_ABSOLUTE_DEVIATION -> {
			double sumAbsoluteDeviation = 0;
			if (geoList2 == null) {
				double val;
				for (int i = 0; i < size; i++) {
					geo = geoList.get(i);
					val = geo.evaluateDouble();
					sumAbsoluteDeviation += Math.abs(mu - val);
				}
			}
			// list of numbers with list of frequencies
			else {

				double val;
				for (int i = 0; i < size; i++) {
					geo = geoList.get(i);
					geoFreq = geoList2.get(i);

					val = geo.evaluateDouble();

					// compute midpoint value if needed
					if (useMidpoint) {
						geo2 = geoList.get(i + 1);
						val = (val + (geo2.evaluateDouble())) / 2;
					}

					frequency = geoFreq.evaluateDouble();

					// handle bad frequency
					if (frequency < 0) {
						result.setUndefined();
						return;
					}

					sumAbsoluteDeviation += Math.abs(mu - val) * frequency;

				}
			}
			result.setValue(sumAbsoluteDeviation / n);
		}
		case STATS_MEAN -> result.setValue(mu);
		case STATS_SD -> {
			var = sumSquares / n - mu * mu;
			result.setValue(Math.sqrt(var));
		}
		case STATS_SAMPLE_SD -> {
			var = (sumSquares - sumVal * sumVal / n) / (n - 1);
			result.setValue(Math.sqrt(var));
		}
		case STATS_VARIANCE -> {
			var = sumSquares / n - mu * mu;
			result.setValue(var);
		}
		case STATS_SAMPLE_VARIANCE -> {
			var = (sumSquares - sumVal * sumVal / n) / (n - 1);
			result.setValue(var);
		}
		case STATS_SXX -> {
			var = sumSquares - (sumVal * sumVal) / n;
			result.setValue(var);
		}
		case STATS_SIGMAX -> result.setValue(sumVal);
		case STATS_SIGMAXX -> result.setValue(sumSquares);
		case STATS_PRODUCT -> result.setValue(product);
		}
	}

}
