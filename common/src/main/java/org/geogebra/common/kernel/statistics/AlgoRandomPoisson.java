/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

 */

package org.geogebra.common.kernel.statistics;

import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.SetRandomValue;
import org.geogebra.common.kernel.algos.AlgoElement;
import org.geogebra.common.kernel.commands.Commands;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoNumberValue;
import org.geogebra.common.kernel.geos.GeoNumeric;
import org.geogebra.common.kernel.kernelND.GeoElementND;
import org.geogebra.common.util.DoubleUtil;
import org.geogebra.common.util.MyMath2;

/**
 * Computes RandomPoisson[lambda]
 * 
 * @author Michael Borcherds
 */
public class AlgoRandomPoisson extends AlgoElement implements SetRandomValue {

	private GeoNumberValue lambda; // input
	private GeoNumeric num; // output

	private static double halflog2pi = 0.5 * Math.log(2 * Math.PI);

	private static double[] logtable = new double[10];

	/**
	 * 
	 * @param cons
	 *            construction
	 * @param label
	 *            output label
	 * @param lambda
	 *            mean
	 */
	public AlgoRandomPoisson(Construction cons, String label,
			GeoNumberValue lambda) {
		super(cons);
		this.lambda = lambda;

		// output is random number
		num = new GeoNumeric(cons);
		cons.addRandomGeo(num);

		setInputOutput(); // for AlgoElement
		compute();
		num.setLabel(label);
	}

	// for AlgoElement
	@Override
	protected void setInputOutput() {
		input = new GeoElement[1];
		input[0] = lambda.toGeoElement();

		setOnlyOutput(num);
		setDependencies(); // done by AlgoElement
	}

	/**
	 * @return random number
	 */
	public GeoNumeric getResult() {
		return num;
	}

	@Override
	public Commands getClassName() {
		return Commands.RandomPoisson;
	}

	@Override
	public final void compute() {
		if (input[0].isDefined()) {
			double lambdaVal = lambda.getDouble();
			if (lambdaVal > 0) {
				num.setValue(randomPoissonTRS(lambdaVal));
			} else {
				num.setUndefined();
			}
		} else {
			num.setUndefined();
		}
	}

	/*
	 * poisson random number (Knuth)
	 */
	private int randomPoisson(double lambdaVal) {
		double L = Math.exp(-lambdaVal);
		double p = 1;
		int k = 0;
		do {
			k++;
			p *= kernel.getApplication().getRandomNumber();
		} while (p >= L);

		return k - 1;

	}

	/*
	 * 
	 * Hermann, Wolfgang: The transformed rejection method for generating
	 * Poisson random variables Algorithm PTRS
	 * http://statmath.wu-wien.ac.at/papers/92-04-13.wh.ps.gz
	 * http://epub.wu-wien
	 * .ac.at/dyn/virlib/wp/eng/mediate/epub-wu-01_6f2.pdf?ID=epub-wu-01_6f2
	 */
	private int randomPoissonTRS(double mu) {

		if (mu < 10) {
			return randomPoisson(mu);
		}

		double b = 0.931 + 2.53 * Math.sqrt(mu);
		double a1 = -0.059 + 0.02438 * b;
		double v_r = 0.9277 - 3.6224 / (b - 2);

		double us = 0;
		double v = 1;

		while (true) {

			int k = -1;
			while (k < 0 || (us < 0.013 && v > us)) {
				double u = kernel.getApplication().getRandomNumber() - 0.5;
				v = kernel.getApplication().getRandomNumber();
				us = 0.5 - Math.abs(u);
				k = (int) Math.floor((2 * a1 / us + b) * u + mu + 0.43);
				if (us >= 0.07 && v < v_r) {
					return k;
				}
			}

			double alpha = 1.1239 + 1.1328 / (b - 3.4);
			double lnmu = Math.log(mu);

			v = v * alpha / (a1 / (us * us) + b);

			if (Math.log(v * alpha / (a1 / us / us + b)) <= -mu + k * lnmu
					- logOfKFactorial(k)) {
				return k;
			}
		}

	}

	private static double logOfKFactorial(int k) {
		if (k < 10) {
			if (logtable[k] == 0) {
				logtable[k] = Math.log(MyMath2.factorial(k));
			}
			return logtable[k];
		}

		// Stirling approximation
		return halflog2pi + (k + 0.5) * Math.log(k + 1) - (k + 1)
				+ (1 / 12.0 - (1 / 360.0 - 1 / 1260.0 / (k + 1) / (k + 1))
						/ (k + 1) / (k + 1)) / (k + 1);
	}

	@Override
	public boolean setRandomValue(GeoElementND d0) {
		double d = Math.round(DoubleUtil.checkInteger(d0.evaluateDouble()));
		num.setValue(Math.max(0, d));
		return true;
	}

}
