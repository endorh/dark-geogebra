/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

 */

package org.geogebra.common.kernel.algos;

import java.util.Arrays;
import java.util.TreeMap;

import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.arithmetic.MyDouble;
import org.geogebra.common.kernel.arithmetic.NumberValue;
import org.geogebra.common.kernel.commands.Commands;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoList;
import org.geogebra.common.kernel.geos.GeoNumeric;

/**
 * Find median of a list. Adapted from AlgoSort
 * 
 * @author Michael Borcherds
 * @version 2008-02-16
 */

public class AlgoMedian extends AlgoElement {

	private GeoList inputList; // input
	private GeoList freqList; // input
	private GeoNumeric median; // output
	private int size;

	/**
	 * @param cons
	 *            construction
	 * @param inputList
	 *            raw data
	 */
	public AlgoMedian(Construction cons, GeoList inputList) {
		this(cons, inputList, null);
	}

	/**
	 * @param cons
	 *            construction
	 * @param inputList
	 *            data
	 * @param freqList
	 *            frequencies
	 */
	public AlgoMedian(Construction cons, GeoList inputList, GeoList freqList) {
		super(cons);
		this.inputList = inputList;
		this.freqList = freqList;

		median = new GeoNumeric(cons);

		setInputOutput();
		compute();
	}

	@Override
	public Commands getClassName() {
		return Commands.Median;
	}

	@Override
	protected void setInputOutput() {

		if (freqList == null) {
			input = new GeoElement[1];
			input[0] = inputList;
		} else {
			input = new GeoElement[2];
			input[0] = inputList;
			input[1] = freqList;
		}

		setOnlyOutput(median);
		setDependencies(); // done by AlgoElement
	}

	public GeoNumeric getMedian() {
		return median;
	}

	@Override
	public final void compute() {

		size = inputList.size();
		if (!inputList.isDefined() || size == 0) {
			median.setUndefined();
			return;
		}

		// ========================================
		// CASE 1: raw data
		// ========================================
		if (freqList == null) {
			double[] sortList = new double[size];

			// copy inputList into an array
			for (int i = 0; i < size; i++) {
				GeoElement geo = inputList.get(i);
				if (geo instanceof NumberValue) {
					sortList[i] = geo.evaluateDouble();
				} else {
					median.setUndefined();
					return;
				}
			}

			// do the sorting
			Arrays.sort(sortList);

			if (MyDouble.exactEqual(Math.floor((double) size / 2),
					size / 2.0)) {
				median.setValue(
						(sortList[size / 2] + sortList[size / 2 - 1]) / 2);
			} else {
				median.setValue(sortList[(size - 1) / 2]);
			}
		}

		// ================================================
		// CASE 2: data from value/frequency lists
		// ================================================
		else if (inputList.size() == freqList.size()) {

			if (!freqList.isDefined() || !(inputList.size() == freqList.size()
					|| inputList.size() == freqList.size() + 1)) {
				median.setUndefined();
				return;
			}

			// check for bad frequency
			for (int i = 0; i < freqList.size(); i++) {
				if (!(freqList.get(i) instanceof NumberValue)
						|| freqList.get(i).evaluateDouble() < 0) {
					median.setUndefined();
					return;
				}
			}

			for (int i = 0; i < inputList.size(); i++) {
				if (!(inputList.get(i) instanceof NumberValue)) {
					median.setUndefined();
					return;
				}
			}

			// extract value and frequency arrays
			Object[] obj = convertValueFreqListToArrays(inputList, freqList);
			Double[] v = (Double[]) obj[0];
			Integer[] f = (Integer[]) obj[1];
			int n = (Integer) obj[2];

			if (n == 0) {
				median.setUndefined();
				return;
			}

			// find the median
			if (MyDouble.exactEqual(Math.floor((double) n / 2), n / 2.0)) {
				median.setValue(
						(getValueAt(n / 2, v, f) + getValueAt(n / 2 - 1, v, f))
								/ 2);
			} else {
				median.setValue(getValueAt((n - 1) / 2, v, f));
			}
		}

		// ============================================
		// CASE 3: data grouped by class and frequency
		// ============================================

		else {

			if (!freqList.isDefined() || !(inputList.size() == freqList.size()
					|| inputList.size() == freqList.size() + 1)) {
				median.setUndefined();
				return;
			}

			// TODO ok to assume classes are valid? (sorted no gaps)
			double n = 0;
			for (int i = 0; i < freqList.size(); i++) {
				n += freqList.get(i).evaluateDouble();
			}
			int cf = 0;
			int f = 0;
			double lowBound, highBound;
			for (int i = 0; i < freqList.size(); i++) {
				lowBound = inputList.get(i).evaluateDouble();
				highBound = inputList.get(i + 1).evaluateDouble();
				f = (int) freqList.get(i).evaluateDouble();

				if (f < 0) {
					median.setUndefined();
					return;
				}

				if (cf + f >= n / 2) {
					double width = highBound - lowBound;

					// apply grouped median linear interpolation formula
					median.setValue(lowBound + width * (n / 2 - cf) / f);

					break;
				}
				cf += f;
			}
		}
	}

	/**
	 * Returns the value at an index position in a list of data constructed from
	 * a sorted list of values and frequencies
	 * 
	 * @param index
	 *            index in expanded list
	 * @param val
	 *            data
	 * @param freq
	 *            frequencies
	 * @return value from expanded list
	 */
	public static Double getValueAt(int index, Double[] val, Integer[] freq) {

		int cf = 0; // cumulative frequency
		for (int i = 0; i < val.length; i++) {
			cf += freq[i];
			if (index < cf) {
				return val[i];
			}
		}
		return null;
	}

	/**
	 * @param inputList
	 *            raw data
	 * @param freqList
	 *            frequencies
	 * @return {sorted unique values, frequencies, sum of frequencies}
	 */
	public static Object[] convertValueFreqListToArrays(GeoList inputList,
			GeoList freqList) {

		// create a tree map to sort the value/frequency pairs
		double val;
		int freq;
		TreeMap<Double, Integer> tm = new TreeMap<>();
		for (int i = 0; i < freqList.size(); i++) {

			val = inputList.get(i).evaluateDouble();
			freq = (int) freqList.get(i).evaluateDouble();
			// handle repeated values
			if (tm.containsKey(val)) {
				freq += tm.get(val);
			}

			tm.put(val, freq);
		}

		// extract value and frequency arrays
		Double[] v = tm.keySet().toArray(new Double[0]);
		Integer[] f = tm.values().toArray(new Integer[0]);

		// get data size n
		int n = 0;
		for (Integer integer : f) {
			n += integer; // add the frequency for this value
		}

		// return the arrays in an Object array
		Object[] obj = { v, f, n };
		return obj;
	}

}
