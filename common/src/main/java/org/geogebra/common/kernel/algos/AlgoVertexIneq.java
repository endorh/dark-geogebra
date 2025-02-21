package org.geogebra.common.kernel.algos;

import java.util.ArrayList;
import java.util.List;

import org.geogebra.common.kernel.Construction;
import org.geogebra.common.kernel.EquationSolver;
import org.geogebra.common.kernel.StringTemplate;
import org.geogebra.common.kernel.arithmetic.ExpressionNode;
import org.geogebra.common.kernel.arithmetic.Function;
import org.geogebra.common.kernel.arithmetic.FunctionVariable;
import org.geogebra.common.kernel.arithmetic.IneqTree;
import org.geogebra.common.kernel.arithmetic.Inequality;
import org.geogebra.common.kernel.commands.Commands;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.common.kernel.geos.GeoFunction;
import org.geogebra.common.kernel.geos.GeoFunctionNVar;
import org.geogebra.common.kernel.geos.GeoLine;
import org.geogebra.common.kernel.geos.GeoPoint;
import org.geogebra.common.kernel.geos.GeoVec3D;
import org.geogebra.common.kernel.kernelND.GeoPointND;
import org.geogebra.common.util.DoubleUtil;
import org.geogebra.common.util.debug.Log;

public class AlgoVertexIneq extends AlgoElement {

	private OutputHandler<GeoElement> outputPoints;
	private GeoFunctionNVar p;
	private List<GeoPoint> vertices;
	private AlgoElement[][] helpers;
	private int validVertices;
	private GeoLine helperLine;
	private GeoFunction helperFunction;
	private double[] co = new double[3];

	/**
	 * Creates algo for Vertex[poly] (many output points) Creates new unlabeled
	 * vertex algo
	 * 
	 * @param cons
	 *            construction
	 * @param p
	 *            polygon or polyline
	 */
	AlgoVertexIneq(Construction cons, GeoFunctionNVar p) {
		super(cons);
		this.p = p;
		vertices = new ArrayList<>();
		helperLine = new GeoLine(cons);
		outputPoints = createOutputPoints();
		setInputOutput(); // for AlgoElement
		compute();
	}

	/**
	 * @param cons
	 *            construction
	 * @param labels
	 *            labels for output
	 * @param p
	 *            inequality
	 */
	public AlgoVertexIneq(Construction cons, String[] labels,
			GeoFunctionNVar p) {
		this(cons, p);
		// if only one label (e.g. "A"), new labels will be A_1, A_2, ...
		setLabels(labels);

		update();
	}

	private void setLabels(String[] labels) {
		// if only one label (e.g. "A") for more than one output, new labels
		// will be A_1, A_2, ...
		if (labels != null && labels.length == 1 && labels[0] != null
				&& !labels[0].equals("")) {
			outputPoints.setIndexLabels(labels[0]);
		} else {

			outputPoints.setLabels(labels);
			outputPoints.setIndexLabels(outputPoints.getElement(0)
					.getLabel(StringTemplate.defaultTemplate));
		}
	}

	@Override
	protected void setInputOutput() {
		input = new GeoElement[] { p };

		setDependencies();

	}

	@Override
	public void compute() {
		validVertices = 0;
		IneqTree ineqs = p.getIneqs();
		int size = ineqs.getSize();
		int ai, bi;
		for (int i = 0; i < size; i++) {
			for (int j = i + 1; j < size; j++) {
				Inequality a, b;
				if (ineqs.get(i).getType().ordinal() < ineqs.get(j).getType()
						.ordinal()) {
					ai = i;
					bi = j;
				} else {
					ai = j;
					bi = i;
				}
				a = ineqs.get(ai);
				b = ineqs.get(bi);
				typeSwitch(a, b, ai, bi);

			}
		}

		outputPoints.adjustOutputSize(validVertices > 0 ? validVertices : 1);

		for (int i = 0; i < validVertices; i++) {
			GeoPointND point = (GeoPointND) outputPoints.getElement(i);
			point.set(vertices.get(i));
		}
		// other points are undefined
		for (int i = validVertices; i < outputPoints.size(); i++) {
			outputPoints.getElement(i).setUndefined();
		}
	}

	private void typeSwitch(Inequality a, Inequality b, int ai, int bi) {
		switch (a.getType()) {
		case INEQUALITY_PARAMETRIC_X:
			switch (b.getType()) {
			case INEQUALITY_PARAMETRIC_X:
				intParamParam(a, b, ai, bi, true);
				break;
			case INEQUALITY_PARAMETRIC_Y:
				intParamXParamY(a, b, ai, bi);
				break;
			case INEQUALITY_LINEAR:
				intParamXLinear(a, b, ai, bi);
				break;
			case INEQUALITY_CONIC:
				intParamConic(a, b, ai, bi, true);
				break;
			case INEQUALITY_1VAR_X:
				intParamOneVar(a, b, ai, bi, true);
				break;
			case INEQUALITY_1VAR_Y:
				intParamXY(a, b);
				break;
			case INEQUALITY_IMPLICIT:
				break;
			case INEQUALITY_INVALID:
				break;
			}
			break;
		case INEQUALITY_PARAMETRIC_Y:
			switch (b.getType()) {
			case INEQUALITY_PARAMETRIC_Y -> intParamParam(a, b, ai, bi, false);
			case INEQUALITY_LINEAR -> intParamYLinear(a, b, ai, bi);
			case INEQUALITY_CONIC -> intParamConic(a, b, ai, bi, false);
			case INEQUALITY_1VAR_X -> intParamYX(a, b);
			case INEQUALITY_1VAR_Y -> intParamOneVar(a, b, ai, bi, false);
			default -> {
			}
			}
			break;
		case INEQUALITY_LINEAR:
			switch (b.getType()) {
			case INEQUALITY_LINEAR -> intLinearLinear(a, b);
			case INEQUALITY_CONIC -> intLinearConic(a, b, ai, bi);
			case INEQUALITY_1VAR_X -> intLinearX(a, b);
			case INEQUALITY_1VAR_Y -> intLinearY(a, b);
			default -> {
			}
			}
			break;
		case INEQUALITY_CONIC:
			switch (b.getType()) {
			case INEQUALITY_CONIC:
				intConicConic(a, b, ai, bi);
				break;
			case INEQUALITY_1VAR_X:
				intConicX(a, b);
				break;
			case INEQUALITY_1VAR_Y:
				intConicY(a, b);
			default:
				break;
			}
			break;
		case INEQUALITY_1VAR_X:
			switch (b.getType()) {
			case INEQUALITY_1VAR_X:
				// no intersections possible
				break;
			case INEQUALITY_1VAR_Y:
				intXY(a, b);
				break;
			default:
				break;
			}
			break;
		case INEQUALITY_1VAR_Y:
			// no intersections possible
			break;
		default:
			Log.debug("Missing case" + a.getType());
		}

	}

	private void intParamOneVar(Inequality a, Inequality b, int i, int j,
			boolean transpose) {
		initHelpers();

		if (helpers[i][j] == null) {
			if (a.getFunBorder().isPolynomialFunction(false)) {
				setHelper(i, j,
						kernel.getAlgoDispatcher().getIntersectionAlgorithm(
								a.getFunBorder(), helperLine));
			} else {
				setHelper(i, j, new AlgoIntersectFunctionLineNewton(cons,
						a.getFunBorder(), helperLine, new GeoPoint(cons)));
			}
		}
		ArrayList<Double> bz = b.getZeros();
		for (double bRoot : bz) {
			helperLine.setCoords(0, 1, -bRoot);
			helpers[i][j].compute();
			addVertices(helpers[i][j], transpose, true);
		}
	}

	private void intParamYX(Inequality a, Inequality b) {
		ArrayList<Double> bz = b.getZeros();
		GeoFunction af = a.getFunBorder();
		for (double bRoot : bz) {
			ensurePoint();
			vertices.get(validVertices).setCoords(bRoot,
					af.value(bRoot), 1);
			validVertices++;
		}

	}

	private void intParamYLinear(Inequality a, Inequality b, int i, int j) {
		initHelpers();

		if (helpers[i][j] == null) {
			if (a.getFunBorder().isPolynomialFunction(false)) {
				setHelper(i, j,
						kernel.getAlgoDispatcher().getIntersectionAlgorithm(
								a.getFunBorder(), b.getLineBorder()));
			} else {
				setHelper(i, j,
						new AlgoIntersectFunctionLineNewton(cons,
								a.getFunBorder(), b.getLineBorder(),
								new GeoPoint(cons)));
			}
		} else {
			helpers[i][j].compute();
		}
		addVertices(helpers[i][j], false);

	}

	private void intParamParam(Inequality a, Inequality b, int i, int j,
			boolean transpose) {
		initHelpers();

		if (helpers[i][j] == null) {
			if (a.getFunBorder().isPolynomialFunction(false)) {
				setHelper(i, j, new AlgoIntersectPolynomials(cons,
						a.getFunBorder(), b.getFunBorder()));
			} else {
				setHelper(i, j,
						new AlgoIntersectFunctionsNewton(cons, a.getFunBorder(),
								b.getFunBorder(), new GeoPoint(cons)));
			}
		} else {
			helpers[i][j].compute();
		}
		addVertices(helpers[i][j], transpose);

	}

	private void intParamXY(Inequality a, Inequality b) {
		ArrayList<Double> bz = b.getZeros();
		GeoFunction af = a.getFunBorder();
		for (double bRoot : bz) {
			ensurePoint();
			vertices.get(validVertices).setCoords(af.value(bRoot),
					bRoot, 1);
			validVertices++;
		}

	}

	private void intParamConic(Inequality a, Inequality b, int i, int j,
			boolean transpose) {
		initHelpers();
		double[] mat = b.getConicBorder().getMatrix();
		if (transpose) {
			b.getConicBorder().setMatrix(new double[] { mat[1], mat[0], mat[2],
					mat[3], mat[5], mat[4] });
		}
		if (helpers[i][j] == null) {
			if (a.getFunBorder().isPolynomialFunction(false)) {
				setHelper(i, j, new AlgoIntersectPolynomialConic(cons,
						a.getFunBorder(), b.getConicBorder()));
			} else {
				// TODO
			}
		} else {
			helpers[i][j].compute();
		}
		if (transpose) {
			b.getConicBorder().setMatrix(mat);
		}
		if (helpers[i][j] != null) {
			addVertices(helpers[i][j], transpose);
		}

	}

	private void intParamXLinear(Inequality a, Inequality b, int i, int j) {
		initHelpers();

		GeoLine bl = b.getLineBorder();
		double x = bl.getX();
		double y = bl.getY();
		bl.setCoords(y, x, bl.getZ());

		if (helpers[i][j] == null) {

			if (a.getFunBorder().isPolynomialFunction(false)) {
				setHelper(i, j,
						kernel.getAlgoDispatcher().getIntersectionAlgorithm(
								a.getFunBorder(), b.getLineBorder()));
			} else {
				setHelper(i, j,
						new AlgoIntersectFunctionLineNewton(cons,
								a.getFunBorder(), b.getLineBorder(),
								new GeoPoint(cons)));
			}
		} else {
			helpers[i][j].compute();
		}

		bl.setCoords(x, y, bl.getZ());

		addVertices(helpers[i][j], true);

	}

	private void intParamXParamY(Inequality a, Inequality b, int i, int j) {
		initHelpers();
		ExpressionNode exp = a.getFunBorder().getFunctionExpression()
				.getCopy(kernel).wrap();
		FunctionVariable aVar = a.getFunBorder().getFunction()
				.getFunctionVariable();
		exp = exp.replace(aVar, b.getFunBorder().getFunctionExpression())
				.wrap();
		if (helperFunction == null) {
			helperFunction = new GeoFunction(cons);
		}
		helperFunction.setFunction(new Function(exp,
				b.getFunBorder().getFunction().getFunctionVariable()));
		helperLine.setCoords(1, -1, 0);
		if (helpers[i][j] == null) {

			if (helperFunction.isPolynomialFunction(false)) {
				setHelper(i, j, kernel.getAlgoDispatcher()
						.getIntersectionAlgorithm(helperFunction, helperLine));
			} else {
				setHelper(i, j, new AlgoIntersectFunctionLineNewton(cons,
						helperFunction, helperLine, new GeoPoint(cons)));
			}
		} else {
			helpers[i][j].compute();
		}

		GeoElement[] output = helpers[i][j].getOutput();
		for (GeoElement geoElement : output) {
			GeoPoint pt = (GeoPoint) geoElement;
			double x = pt.getX() / pt.getZ();
			pt.setCoords(x, b.getFunBorder().value(x), 1);
			if (vertices.size() <= validVertices) {
				vertices.add(pt);
			} else {
				vertices.set(validVertices, pt);
			}
			validVertices++;
		}
	}

	private void intXY(Inequality a, Inequality b) {
		ArrayList<Double> az = a.getZeros();
		ArrayList<Double> bz = b.getZeros();
		for (double aRoot : az) {
			for (double bRoot : bz) {
				ensurePoint();
				vertices.get(validVertices).setCoords(aRoot, bRoot, 1);
				validVertices++;
				Log.debug(aRoot + "," + bRoot);
			}
		}

	}

	private void intConicY(Inequality a, Inequality b) {
		ArrayList<Double> bz = b.getZeros();
		double[] coef = a.getConicBorder().getMatrix();
		for (double bRoot : bz) {
			co[2] = coef[0];
			co[1] = 2 * coef[3] * bRoot + 2 * coef[4];
			co[0] = coef[1] * bRoot * bRoot + 2 * coef[5] * bRoot
					+ coef[2];
			kernel.getEquationSolver();
			int n = EquationSolver.solveQuadratic(co);

			for (int k = 0; k < n; k++) {
				ensurePoint();
				vertices.get(validVertices).setCoords(co[k], bRoot, 1);
				validVertices++;
			}
		}
	}

	private void intConicX(Inequality a, Inequality b) {
		ArrayList<Double> bz = b.getZeros();
		double[] coef = a.getConicBorder().getMatrix();
		for (double bRoot : bz) {
			co[2] = coef[1];
			co[1] = 2 * coef[3] * bRoot + 2 * coef[5];
			co[0] = coef[0] * bRoot * bRoot + 2 * coef[4] * bRoot
					+ coef[2];
			kernel.getEquationSolver();
			int n = EquationSolver.solveQuadratic(co);

			for (int k = 0; k < n; k++) {
				ensurePoint();
				vertices.get(validVertices).setCoords(bRoot, co[k], 1);
				validVertices++;
			}
		}

	}

	private void intConicConic(Inequality a, Inequality b, int i, int j) {
		initHelpers();
		if (helpers[i][j] == null) {
			setHelper(i, j, new AlgoIntersectConics(cons, a.getConicBorder(),
					b.getConicBorder()));
		} else {
			helpers[i][j].compute();
		}
		addVertices(helpers[i][j], false);

	}

	private void intLinearY(Inequality a, Inequality b) {
		ArrayList<Double> bz = b.getZeros();
		GeoLine af = a.getLineBorder();
		if (DoubleUtil.isZero(af.getX())) {
			return;
		}
		for (double bRoot : bz) {
			ensurePoint();
			vertices.get(validVertices).setCoords(
					(-af.getY() * bRoot - af.getZ()) / af.getX(), bRoot,
					1);
			validVertices++;
		}

	}

	private void intLinearX(Inequality a, Inequality b) {
		ArrayList<Double> bz = b.getZeros();
		GeoLine af = a.getLineBorder();
		if (DoubleUtil.isZero(af.getY())) {
			return;
		}
		for (double bRoot : bz) {
			ensurePoint();
			vertices.get(validVertices).setCoords(bRoot,
					(-af.getX() * bRoot - af.getZ()) / af.getY(), 1);
			validVertices++;
		}

	}

	private void intLinearConic(Inequality a, Inequality b, int i, int j) {
		initHelpers();
		if (helpers[i][j] == null) {
			setHelper(i, j, new AlgoIntersectLineConic(cons, a.getLineBorder(),
					b.getConicBorder()));
		} else {
			updateHelper(i, j, b.getConicBorder(), a.getLineBorder());
		}
		addVertices(helpers[i][j], false);
	}

	private void updateHelper(int i, int j, GeoElement first, GeoElement second) {
		if (helpers[i][j].getInput(0) != first) {
			helpers[i][j].getInput(0).set(first);
		}
		if (helpers[i][j].getInput(1) != second) {
			helpers[i][j].getInput(1).set(second);
		}
		helpers[i][j].compute();

	}

	private void setHelper(int i, int j, AlgoElement algo) {
		helpers[i][j] = algo;
		algo.setProtectedInput(true);
		algo.remove();
	}

	private void addVertices(AlgoElement algoElement, boolean transpose,
			boolean copy) {
		GeoElement[] output = algoElement.getOutput();
		for (GeoElement geoElement : output) {
			GeoPoint pt = copy ? (GeoPoint) geoElement.copy()
					: (GeoPoint) geoElement;
			if (transpose) {
				double x = pt.getX() / pt.getZ();
				double y = pt.getY() / pt.getZ();
				pt.setCoords(y, x, 1);
			}
			if (vertices.size() <= validVertices) {
				vertices.add(pt);
			} else {
				vertices.set(validVertices, pt);
			}
			validVertices++;
		}

	}

	private void addVertices(AlgoElement algoElement, boolean transpose) {
		addVertices(algoElement, transpose, false);
	}

	private void intLinearLinear(Inequality a, Inequality b) {
		ensurePoint();
		GeoVec3D.cross(a.getLineBorder(), b.getLineBorder(),
				vertices.get(validVertices));
		validVertices++;
	}

	private void initHelpers() {
		int n = p.getIneqs().getSize();
		if (helpers == null || helpers.length != n) {
			helpers = new AlgoElement[n][n];
		}
	}

	private void ensurePoint() {
		while (vertices.size() <= validVertices
				|| vertices.get(validVertices) == null) {
			vertices.add(new GeoPoint(cons));
		}
	}

	@Override
	public Commands getClassName() {
		return Commands.Vertex;
	}

	/**
	 * @return resulting vertices
	 */
	public GeoElement[] getVertex() {
		return getOutput();
	}

	private OutputHandler<GeoElement> createOutputPoints() {
		return new OutputHandler<>(() -> {
			GeoPoint pt = new GeoPoint(cons);
			pt.setCoords(0, 0, 1);
			pt.setParentAlgorithm(AlgoVertexIneq.this);
			return pt;
		});
	}

}
