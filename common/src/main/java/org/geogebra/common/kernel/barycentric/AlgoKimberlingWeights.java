package org.geogebra.common.kernel.barycentric;

/**
 * Most of the content of this class is moved here from AlgoKimberling, to
 * facilitate asyncronous running... comment from AlgoKimberling:
 *
 * credit goes to Jason Cantarella of the Univerity of Georgia for creating a
 * perl script which was used to create this class.
 */

public class AlgoKimberlingWeights {

	private double a2, a3, a4, a5, a6, a7, a8, a9, a10;
	private double b2, b3, b4, b5, b6, b7, b8, b9, b10;
	private double c2, c3, c4, c5, c6, c7, c8, c9, c10;
	private double Q, R, S, T, U, V, angleA, angleB, angleC;

	private static double p(double a, double b) {
		return Math.pow(a, b);
	}

	private static double u(double a) {
		return Math.sqrt(a);
	}

	public double weight(int k, double a, double b, double c) {
		a2 = a * a;
		a3 = a * a2;
		a4 = a * a3;
		a5 = a * a4;
		a6 = a * a5;
		a7 = a * a6;
		a8 = a * a7;
		a9 = a * a8;
		a10 = a * a9;

		b2 = b * b;
		b3 = b * b2;
		b4 = b * b3;
		b5 = b * b4;
		b6 = b * b5;
		b7 = b * b6;
		b8 = b * b7;
		b9 = b * b8;
		b10 = b * b9;

		c2 = c * c;
		c3 = c * c2;
		c4 = c * c3;
		c5 = c * c4;
		c6 = c * c5;
		c7 = c * c6;
		c8 = c * c7;
		c9 = c * c8;
		c10 = c * c9;

		R = b2 + c2;
		Q = (b2 - c2) * (b2 - c2);
		angleA = Math.acos(0.5 * (b2 + c2 - a2) / b / c);
		angleB = Math.acos(0.5 * (-b2 + c2 + a2) / a / c);
		angleC = Math.acos(0.5 * (b2 - c2 + a2) / b / a);
		T = (a2 - b2 - c2);
		U = (a2 + b2 - c2);
		V = (a2 - b2 + c2);

		// four times the area of the triangle using Heron's formula
		S = u((a + b + c) * (-a + b + c) * (a - b + c) * (a + b - c));

		if (k < 100) {
			return weight0to99(k, a, b, c);
		}
		if (k < 200) {
			return weight100to199(k, a, b, c);
		}
		if (k < 300) {
			return weight200to299(k, a, b, c);
		}
		if (k < 400) {
			return weight300to399(k, a, b, c);
		}
		if (k < 500) {
			return weight400to499(k, a, b, c);
		}
		if (k < 600) {
			return weight500to599(k, a, b, c);
		}
		if (k < 700) {
			return weight600to699(k, a, b, c);
		}
		if (k < 800) {
			return weight700to799(k, a, b, c);
		}
		if (k < 900) {
			return weight800to899(k, a, b, c);
		}
		if (k < 1000) {
			return weight900to999(k, a, b, c);
		}
		if (k < 1100) {
			return weight1000to1099(k, a, b, c);
		}
		if (k < 1200) {
			return weight1100to1199(k, a, b, c);
		}
		if (k < 1300) {
			return weight1200to1299(k, a, b, c);
		}
		if (k < 1400) {
			return weight1300to1399(k, a, b, c);
		}
		if (k < 1500) {
			return weight1400to1499(k, a, b, c);
		}
		if (k < 1600) {
			return weight1500to1599(k, a, b, c);
		}
		if (k < 1700) {
			return weight1600to1699(k, a, b, c);
		}
		if (k < 1800) {
			return weight1700to1799(k, a, b, c);
		}
		if (k < 1900) {
			return weight1800to1899(k, a, b, c);
		}
		if (k < 2000) {
			return weight1900to1999(k, a, b, c);
		}
		if (k < 2100) {
			return weight2000to2099(k, a, b, c);
		}
		if (k < 2200) {
			return weight2100to2199(k, a, b, c);
		}
		if (k < 2300) {
			return weight2200to2299(k, a, b, c);
		}
		if (k < 2400) {
			return weight2300to2399(k, a, b, c);
		}
		if (k < 2500) {
			return weight2400to2499(k, a, b, c);
		}
		if (k < 2600) {
			return weight2500to2599(k, a, b, c);
		}
		if (k < 2700) {
			return weight2600to2699(k, a, b, c);
		}
		if (k < 2750) {
			return weight2700to2749(k, a, b, c);
		}
		if (k < 2800) {
			return weight2750to2799(k, a, b, c);
		}
		if (k < 2850) {
			return weight2800to2849(k, a, b, c);
		}
		if (k < 2900) {
			return weight2850to2899(k, a, b, c);
		}
		if (k < 2950) {
			return weight2900to2949(k, a, b, c);
		}
		if (k < 3000) {
			return weight2950to2999(k, a, b, c);
		}
		return weight3000plus(k, a, b, c);
	}

	private double weight0to99(int k, double a, double b, double c) {
		return switch (k) {
			case 1 -> a;
			case 2 -> 1;
			case 3 -> a2 * T;
			case 4 -> -a4 + Q;
			case 5 -> Q - a2 * R;
			case 6 -> a2;
			case 7 -> -((a + b - c) * (a - b + c));
			case 8 -> -a + b + c;
			case 9 -> a * (a - b - c);
			case 10 -> b + c;
			case 11 -> -((a - b - c) * p(b - c, 2));
			case 12 -> -((a + b - c) * (a - b + c) * p(b + c, 2));
			case 13 -> a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4
					+ u(3) * a2 * S;
			case 14 -> -a4 - a2 * b2 + 2 * b4 - a2 * c2 - 4 * b2 * c2 + 2 * c4
					+ u(3) * a2 * S;
			case 15 -> u(3) * a2 * T - a2 * S;
			case 16 -> u(3) * a2 * T + a2 * S;
			case 17 -> 1 / (-a2 + b2 + c2 + u(3) * S);
			case 18 -> 1 / (-a2 + b2 + c2 - u(3) * S);
			case 19 -> a * (a4 - Q);
			case 20 -> 3 * a4 - Q - 2 * a2 * R;
			case 21 -> a * (a + b) * (a - b - c) * (a + c);
			case 22 -> a2 * (a4 - b4 - c4);
			case 23 -> a2 * (a4 - b4 + b2 * c2 - c4);
			case 24 -> a2 * U * V * (a4 + b4 + c4 - 2 * a2 * R);
			case 25 -> a2 * U * V;
			case 26 -> a2 * (a8 - 2 * a6 * R - Q * (b4 + c4) + 2 * a2 * (b6 + c6));
			case 27 -> (a + b) * (a + c) * U * V;
			case 28 -> a * (a + b) * (a + c) * U * V;
			case 29 -> (a + b) * (a - b - c) * (a + c) * U * V;
			case 30 -> 2 * a4 - Q - a2 * R;
			case 31 -> a3;
			case 32 -> a4;
			case 33 -> a * (a - b - c) * U * V;
			case 34 -> a * (a + b - c) * (a - b + c) * U * V;
			case 35 -> a2 * (a2 - b2 - b * c - c2);
			case 36 -> a2 * (a2 - b2 + b * c - c2);
			case 37 -> a * (b + c);
			case 38 -> a * R;
			case 39 -> a2 * R;
			case 40 -> a * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					- a * p(b + c, 2));
			case 41 -> a3 * (a - b - c);
			case 42 -> a2 * (b + c);
			case 43 -> a * (-(b * c) + a * (b + c));
			case 44 -> a * (2 * a - b - c);
			case 45 -> a * (a - 2 * (b + c));
			case 46 -> a * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c) - a * R);
			case 47 -> a3 * (a4 + b4 + c4 - 2 * a2 * R);
			case 48 -> a3 * T;
			case 49 -> a4 * T * (a4 + b4 - b2 * c2 + c4 - 2 * a2 * R);
			case 50 -> a4 * (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2);
			case 51 -> a2 * (-Q + a2 * R);
			case 52 -> a2 * (a4 + b4 + c4 - 2 * a2 * R) * (-Q + a2 * R);
			case 53 -> U * V * (-Q + a2 * R);
			case 54 -> a2 * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 55 -> a2 * (a - b - c);
			case 56 -> a2 * (a + b - c) * (a - b + c);
			case 57 -> a * (a + b - c) * (a - b + c);
			case 58 -> a2 * (a + b) * (a + c);
			case 59 -> a2 * p(a - b, 2) * p(a - c, 2) * (a + b - c) * (a - b + c);
			case 60 -> a2 * p(a + b, 2) * (a - b - c) * p(a + c, 2);
			case 61 -> a2 * T - u(3) * a2 * S;
			case 62 -> a2 * T + u(3) * a2 * S;
			case 63 -> a * T;
			case 64 -> a2 * (a4 + b4 + 2 * b2 * c2 - 3 * c4 - 2 * a2 * (b2 - c2))
					* (a4 - 3 * b4 + 2 * b2 * c2 + c4 + 2 * a2 * (b2 - c2));
			case 65 -> a * (a + b - c) * (a - b + c) * (b + c);
			case 66 -> -a8 + p(b4 - c4, 2);
			case 67 -> -((a4 - a2 * b2 + b4 - c4) * (a4 - b4 - a2 * c2 + c4));
			case 68 -> -(T * (a4 - 2 * a2 * b2 + Q) * (a4 - 2 * a2 * c2 + Q));
			case 69 -> -a2 + b2 + c2;
			case 70 -> -((a8 + 2 * a4 * b4 - 2 * a6 * R + p(b2 - c2, 3) * R
					- 2 * a2 * (b6 - c6))
					* (a8 + 2 * a4 * c4 - 2 * a6 * R - p(b2 - c2, 3) * R
					+ 2 * a2 * (b6 - c6)));
			case 71 -> a2 * (b + c) * T;
			case 72 -> -(a * (b + c) * T);
			case 73 -> a2 * (a + b - c) * (a - b + c) * (b + c) * T;
			case 74 -> a2 * (a4 - 2 * b4 + b2 * c2 + c4 + a2 * (b2 - 2 * c2))
					* (a4 + b4 + b2 * c2 - 2 * c4 + a2 * (-2 * b2 + c2));
			case 75 -> b * c;
			case 76 -> b2 * c2;
			case 77 -> a * (a + b - c) * (a - b + c) * T;
			case 78 -> a * (a - b - c) * T;
			case 79 -> -((a2 + a * b + b2 - c2) * (a2 - b2 + a * c + c2));
			case 80 -> -((a2 - a * b + b2 - c2) * (a2 - b2 - a * c + c2));
			case 81 -> a * (a + b) * (a + c);
			case 82 -> a * (a2 + b2) * (a2 + c2);
			case 83 -> (a2 + b2) * (a2 + c2);
			case 84 -> a * (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 85 -> b * (-a + b - c) * (a + b - c) * c;
			case 86 -> (a + b) * (a + c);
			case 87 -> a * (a * (b - c) - b * c) * (a * (b - c) + b * c);
			case 88 -> a * (a + b - 2 * c) * (a - 2 * b + c);
			case 89 -> a * (2 * a + 2 * b - c) * (2 * a - b + 2 * c);
			case 90 -> a * (a3 + a2 * (b - c) - (b - c) * p(b + c, 2) - a * R)
					* (a3 + a2 * (-b + c) + (b - c) * p(b + c, 2) - a * R);
			case 91 -> b * c * (a4 - 2 * a2 * b2 + Q) * (a4 - 2 * a2 * c2 + Q);
			case 92 -> b * c * (-a4 + Q);
			case 93 -> b2 * c2 * (-V) * U * (a4 + Q - a2 * (2 * b2 + c2))
					* (a4 + Q - a2 * (b2 + 2 * c2));
			case 94 -> b2 * c2 * (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (-a2 + b2 - a * c - c2) * (-a2 + b2 + a * c - c2);
			case 95 -> (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 96 -> (a4 - 2 * a2 * b2 + Q) * (a4 - 2 * a2 * c2 + Q)
					* (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 97 -> a2 * T * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 98 -> (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4);
			case 99 -> (a - b) * (a + b) * (a - c) * (a + c);
			default -> Double.NaN;
		};
	}

	private double weight100to199(int k, double a, double b, double c) {

		return switch (k) {
			case 100 -> a * (a - b) * (a - c);
			case 101 -> a2 * (a - b) * (a - c);
			case 102 -> a2
					* (a4 - a3 * b - 2 * b4 + a * b * p(b - c, 2) + b3 * c
					+ b2 * c2 - b * c3 + c4
					+ a2 * (b2 + b * c - 2 * c2))
					* (a4 + b4 - a3 * c - b3 * c + a * p(b - c, 2) * c + b2 * c2
					+ b * c3 - 2 * c4 + a2 * (-2 * b2 + b * c + c2));
			case 103 -> a2 * (a3 - 2 * b3 - a2 * c + b2 * c + c3 + a * (b2 - c2))
					* (a3 - a2 * b + b3 + b * c2 - 2 * c3 + a * (-b2 + c2));
			case 104 -> a * (a3 - a2 * b + b3 - a * p(b - c, 2) - b * c2)
					* (a3 - a * p(b - c, 2) - a2 * c - b2 * c + c3);
			case 105 -> a * (a2 + b * (b - c) - a * c) * (a2 - a * b + c * (-b + c));
			case 106 -> a2 * (a + b - 2 * c) * (a - 2 * b + c);
			case 107 -> (a - b) * (a + b) * (a - c) * (a + c) * p(a4 - Q, 2);
			case 108 -> a * (a - b) * (a - c) * (a + b - c) * (a - b + c) * U * V;
			case 109 -> a2 * (a - b) * (a - c) * (a + b - c) * (a - b + c);
			case 110 -> a2 * (a - b) * (a + b) * (a - c) * (a + c);
			case 111 -> a2 * (a2 + b2 - 2 * c2) * (a2 - 2 * b2 + c2);
			case 112 -> a2 * (a - b) * (a + b) * (a - c) * (a + c) * U * V;
			case 113 -> -((2 * a4 - Q - a2 * R)
					* (a4 * R + Q * R - 2 * a2 * (b4 - b2 * c2 + c4)));
			case 114 -> -((2 * a4 + Q - a2 * R) * (-b4 - c4 + a2 * R));
			case 115 -> p(b - c, 2) * p(b + c, 2);
			case 116 -> p(b - c, 2) * (b2 + b * c + c2 - a * (b + c));
			case 117 -> -((2 * a4 - a2 * p(b - c, 2) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) - Q)
					* (-(a3 * b * c * (b + c))
					+ a * b * p(b - c, 2) * c * (b + c) + a4 * R
					+ Q * (b2 - b * c + c2) - a2 * p(b - c, 2)
					* (2 * b2 + 3 * b * c + 2 * c2)));
			case 118 -> -((2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c))
					* (b5 - b3 * c2 - b2 * c3 + c5 - a * Q + a3 * R
					- a2 * (b3 + c3)));
			case 119 -> -((-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c))
					* (a3 * (b + c) - a * p(b - c, 2) * (b + c) + Q - a2 * R));
			case 120 -> -((-b2 - c2 + a * (b + c))
					* (-2 * a * b * c + a2 * (b + c) + p(b - c, 2) * (b + c)));
			case 121 -> -((2 * a - b - c)
					* (b3 - 2 * b2 * c - 2 * b * c2 + c3 + a * R));
			case 122 -> p(b - c, 2) * p(b + c, 2) * (T * T)
					* (-3 * a4 + Q + 2 * a2 * R);
			case 123 -> -((a - b - c) * p(b - c, 2) * T
					* (a4 + 2 * a2 * b * c - 2 * a * b * c * (b + c) - Q));
			case 124 -> (a - b - c) * p(b - c, 2)
					* (-b3 - a * b * c - c3 + a2 * (b + c));
			case 125 -> p(b - c, 2) * p(b + c, 2) * (-T);
			case 126 -> -((2 * a2 - b2 - c2) * (b4 - 4 * b2 * c2 + c4 + a2 * R));
			case 127 -> p(b - c, 2) * p(b + c, 2) * (-T) * (-a4 + b4 + c4);
			case 128 -> -((a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2)
					* (-Q + a2 * R) * (2 * a8 + p(b2 - c2, 4) - 4 * a6 * R
					- 2 * a2 * Q * R + 3 * a4 * (b4 + c4)));
			case 129 -> -(a2 * (-Q + a2 * R)
					* (a8 + b2 * c2 * Q - 2 * a6 * R + a4 * (b4 + b2 * c2 + c4))
					* (a8 * (b4 + c4) - 4 * a6 * (b6 + c6) + Q * (b8 + c8)
					+ a4 * (6 * b8 - 2 * b6 * c2 - 2 * b4 * c4
					- 2 * b2 * c6 + 6 * c8)
					- 4 * a2 * (b10 - b8 * c2 - b2 * c8 + c10)));
			case 130 -> a2 * p(b - c, 2) * p(b + c, 2) * (T * T) * (-Q + a2 * R)
					* (a8 - b2 * c2 * Q - 2 * a6 * R
					+ a4 * (b4 + 3 * b2 * c2 + c4));
			case 131 -> -(T
					* (2 * a8 + p(b2 - c2, 4) - 3 * a6 * R - a2 * Q * R
					+ a4 * p(b2 + c2, 2))
					* (a4 * R + Q * R - 2 * a2 * (b4 - b2 * c2 + c4)));
			case 132 -> -(U * V * (-b4 - c4 + a2 * R) * (2 * a6 - a4 * R - Q * R));
			case 133 -> -(U * V * (2 * a4 - Q - a2 * R)
					* (a6 * R + 3 * a2 * Q * R
					+ a4 * (-3 * b4 + 4 * b2 * c2 - 3 * c4)
					- Q * (b4 + 4 * b2 * c2 + c4)));
			case 134 -> a2
					* p(b - c,
					2)
					* p(b + c,
					2)
					* p(a4 + b4 + c4 - 2 * a2 * R, 2) * (-Q + a2 * R)
					* (a10 * a2 - b2 * c2 * p(b2 - c2, 4) - 4 * a10 * R
					+ 2 * a2 * b2 * c2 * Q * R
					+ a8 * (6 * b4 + 5 * b2 * c2 + 6 * c4)
					+ a6 * (-4 * b6 + 2 * b4 * c2 + 2 * b2 * c4
					- 4 * c6)
					+ a4 * (b8 - 4 * b6 * c2 + 2 * b4 * c4 - 4 * b2 * c6
					+ c8));
			case 135 -> p(b - c, 2) * p(b + c, 2) * (-V) * U
					* (a4 + b4 + c4 - 2 * a2 * R) * (-a6 + 3 * a4 * R + Q * R
					+ a2 * (-3 * b4 + 2 * b2 * c2 - 3 * c4));
			case 136 -> p(b - c, 2) * p(b + c, 2) * (-V) * U
					* (a4 + b4 + c4 - 2 * a2 * R);
			case 137 -> p(b - c, 2) * p(b + c, 2)
					* (a4 + b4 - b2 * c2 + c4 - 2 * a2 * R) * (Q - a2 * R);
			case 138 -> -(U * V * (-Q + a2 * R)
					* (2 * a8 - 4 * a6 * R + 2 * a2 * Q * R - Q * (b4 + c4)
					+ a4 * (b4 + 4 * b2 * c2 + c4))
					* (a8 - 2 * a6 * R - 2 * a2 * Q * R
					+ Q * (b4 + 3 * b2 * c2 + c4)
					+ a4 * (2 * b4 - b2 * c2 + 2 * c4)));
			case 139 -> p(b - c, 2) * p(b + c, 2) * (-V) * U
					* (a4 + b4 + c4 - 2 * a2 * R) * (Q - a2 * R)
					* (a10 * a2 - 4 * a10 * R
					+ p(b2 - c2, 4) * (b4 + b2 * c2 + c4)
					+ a8 * (7 * b4 + 11 * b2 * c2 + 7 * c4)
					- 2 * a2 * Q * (2 * b6 + b4 * c2 + b2 * c4 + 2 * c6)
					- 2 * a6 * (4 * b6 + 5 * b4 * c2 + 5 * b2 * c4
					+ 4 * c6)
					+ a4 * (7 * b8 + 2 * b4 * c4 + 7 * c8));
			case 140 -> 2 * a4 + Q - 3 * a2 * R;
			case 141 -> b2 + c2;
			case 142 -> p(b - c, 2) - a * (b + c);
			case 143 -> a2 * (a4 + b4 - b2 * c2 + c4 - 2 * a2 * R) * (-Q + a2 * R);
			case 144 -> 3 * a2 - p(b - c, 2) - 2 * a * (b + c);
			case 145 -> 3 * a - b - c;
			case 146 -> -a10 - a8 * R + p(b2 - c2, 4) * R
					+ a2 * Q * (b4 + 9 * b2 * c2 + c4)
					+ a6 * (8 * b4 - 9 * b2 * c2 + 8 * c4)
					+ a4 * (-8 * b6 + 6 * b4 * c2 + 6 * b2 * c4 - 8 * c6);
			case 147 -> -a8 + b8 - b6 * c2 - b2 * c6 + c8 - a6 * R
					+ a4 * (2 * b4 + 3 * b2 * c2 + 2 * c4)
					- a2 * (b6 + b4 * c2 + b2 * c4 + c6);
			case 148 -> -a4 + b4 - 3 * b2 * c2 + c4 + a2 * R;
			case 149 -> -a3 + a2 * (b + c) + p(b - c, 2) * (b + c)
					- a * (b2 - b * c + c2);
			case 150 -> -a4 - a2 * b * c + a3 * (b + c) - a * p(b - c, 2) * (b + c)
					+ p(b - c, 2) * (b2 + b * c + c2);
			case 151 -> -a10 + a9 * (b + c) - 12 * a5 * b * p(b - c, 2) * c * (b + c)
					- a * p(b - c, 6) * p(b + c, 3)
					+ p(b2 - c2, 4) * (b2 - b * c + c2)
					- a8 * (b2 + 3 * b * c + c2)
					- 4 * a4 * Q * (2 * b2 - 3 * b * c + 2 * c2)
					- 2 * a7 * (b3 - 3 * b2 * c - 3 * b * c2 + c3)
					+ 2 * a3 * p(b - c, 4) * (b3 + 5 * b2 * c + 5 * b * c2 + c3)
					+ a2 * Q * (b4 - 6 * b3 * c + 14 * b2 * c2 - 6 * b * c3
					+ c4)
					+ 2 * a6 * (4 * b4 - b3 * c - 8 * b2 * c2 - b * c3
					+ 4 * c4);
			case 152 -> -a8 + a7 * (b + c) - a * p(b - c, 4) * p(b + c, 3)
					+ a4 * b * c * (b2 - 6 * b * c + c2)
					+ p(b - c, 4) * p(b + c, 2) * (b2 + b * c + c2)
					- a6 * (2 * b2 + b * c + 2 * c2)
					+ a5 * (5 * b3 - b2 * c - b * c2 + 5 * c3)
					- a3 * p(b - c, 2)
					* (5 * b3 + 11 * b2 * c + 11 * b * c2 + 5 * c3)
					+ a2 * p(b - c, 2) * (2 * b4 + 5 * b3 * c + 10 * b2 * c2
					+ 5 * b * c3 + 2 * c4);
			case 153 -> -a7 + a6 * (b + c) + p(b - c, 4) * p(b + c, 3)
					+ a5 * (b2 - 7 * b * c + c2) - a * Q * (b2 - 5 * b * c + c2)
					- a4 * (b3 - 5 * b2 * c - 5 * b * c2 + c3)
					- a2 * p(b - c, 2) * (b3 + 7 * b2 * c + 7 * b * c2 + c3)
					+ a3 * (b4 + 2 * b3 * c - 10 * b2 * c2 + 2 * b * c3 + c4);
			case 154 -> a2 * (3 * a4 - Q - 2 * a2 * R);
			case 155 -> a2 * (a8 - 4 * a6 * R + p(b4 - c4, 2)
					+ a4 * (6 * b4 + 4 * b2 * c2 + 6 * c4)
					- 4 * a2 * (b6 + c6));
			case 156 -> a2 * (a8 + b2 * c2 * Q - 3 * a6 * R
					+ a4 * (3 * b4 + 2 * b2 * c2 + 3 * c4) - a2 * (b6 + c6));
			case 157 -> a2 * (a6 - a4 * R - Q * R + a2 * (b4 + c4));
			case 158 -> b * c * p(a4 - Q, 2);
			case 159 -> a2 * (a6 + a4 * R - Q * R - a2 * p(b2 + c2, 2));
			case 160 -> a4 * (-b4 - b2 * c2 - c4 + a2 * R);
			case 161 -> a2 * (a10 - a8 * R - p(b2 - c2, 4) * R + a2 * Q * (b4 + c4)
					- 2 * a6 * (b4 + b2 * c2 + c4)
					+ 2 * a4 * (b6 + b4 * c2 + b2 * c4 + c6));
			case 162 -> a * (a - b) * (a + b) * (a - c) * (a + c) * U * V;
			case 163 -> a3 * (a - b) * (a + b) * (a - c) * (a + c);
			case 164 -> a * (-(a * (a + b - c) * (a - b + c)
					* u(-(b * (a - b - c) * c * (a + b + c))))
					+ b * (a + b - c) * (-a + b + c)
					* u(a * c * (a - b + c) * (a + b + c))
					+ c * (a - b + c) * (-a + b + c)
					* u(a * b * (a + b - c) * (a + b + c)));
			case 165 -> a * (3 * a2 - p(b - c, 2) - 2 * a * (b + c));
			case 166 -> a * (a4 - 4 * a3 * b + 6 * a2 * b2 - 4 * a * b3 + b4
					- 4 * a3 * c - 4 * a2 * b * c + 4 * a * b2 * c + 4 * b3 * c
					+ 6 * a2 * c2 + 4 * a * b * c2 - 10 * b2 * c2 - 4 * a * c3
					+ 4 * b * c3 + c4
					- 2 * (a - b + c) * (-a + b + c) * u(a * (-a + b + c))
					* u(b * (a - b + c))
					- 2 * (a + b - c) * (-a + b + c) * u(a * (-a + b + c))
					* u(c * (a + b - c))
					+ 2 * (a + b - c) * (a - b + c) * u(b * (a - b + c))
					* u(c * (a + b - c)));
			case 167 -> a * ((a2 - 2 * a * b + b2 - 2 * a * c - 2 * b * c + c2)
					* u(a * (-a + b + c))
					+ (a2 - 2 * a * b + b2 + 2 * a * c + 2 * b * c - 3 * c2)
					* u(b * (a - b + c))
					+ (a2 + 2 * a * b - 3 * b2 - 2 * a * c + 2 * b * c + c2)
					* u(c * (a + b - c)));
			case 168 -> a * ((-a + b + c) * S
					- 2 * a * u(-(b * (a - b - c) * c * (a + b + c)))
					+ 2 * (a - c) * u(a * c * (a - b + c) * (a + b + c))
					+ 2 * (a - b) * u(a * b * (a + b - c) * (a + b + c)));
			case 169 -> a * (a3 - a2 * (b + c) - p(b - c, 2) * (b + c) + a * R);
			case 170 -> a * (-(b * p(b - c, 4) * c) + a5 * (b + c)
					+ a * p(b - c, 2) * p(b + c, 3)
					- 2 * a2 * p(b - c, 2) * (2 * b2 + 3 * b * c + 2 * c2)
					- a4 * (4 * b2 + b * c + 4 * c2)
					+ a3 * (6 * b3 - 2 * b2 * c - 2 * b * c2 + 6 * c3));
			case 171 -> a3 + a * b * c;
			case 172 -> a4 + a2 * b * c;
			case 173 -> a * (-u(a * (-a + b + c)) + u(b * (a - b + c))
					+ u(c * (a + b - c)));
			case 174 -> a * u(b * (a - b + c)) * u(c * (a + b - c));
			case 175 -> -2 * a * (a - b - c) * (a + b - c) * (a - b + c)
					- (a + b - c) * (a - b + c) * S;
			case 176 -> 2 * a * (a - b - c) * (a + b - c) * (a - b + c)
					- (a + b - c) * (a - b + c) * S;
			case 177 -> (a + b - c) * (a - b + c) * u(a * (-a + b + c))
					* (u(b * (a - b + c)) + u(c * (a + b - c)));
			case 178 -> u(b * (a - b + c)) + u(c * (a + b - c));
			case 179 -> b * c
					* p(2 * a * c + u(a * c * (a - b + c) * (a + b + c)), 2)
					* p(2 * a * b + u(a * b * (a + b - c) * (a + b + c)), 2);
			case 180 -> a * ((c2
					* (2 * a * b + u(a * b * (a + b - c) * (a + b + c))))
					/ ((2 * b * c + u(-(b * (a - b - c) * c * (a + b + c))))
					* (2 * a * c
					+ u(a * c * (a - b + c) * (a + b + c)))
					+ 2 * c2 * (2 * a * b
					+ u(a * b * (a + b - c) * (a + b + c))))
					+ (b2 * (2 * a * c + u(a * c * (a - b + c) * (a + b + c))))
					/ (2 * b2
					* (2 * a * c + u(
					a * c * (a - b + c) * (a + b + c)))
					+ (2 * b * c + u(-(b * (a - b - c) * c
					* (a + b + c)))) * (2 * a * b
					+ u(a * b * (a + b - c)
					* (a + b + c))))
					- (a2 * (2 * b * c
					+ u(-(b * (a - b - c) * c * (a + b + c)))))
					/ (2 * a2
					* (2 * b * c + u(-(b * (a - b - c) * c
					* (a + b + c))))
					+ (2 * a * c + u(
					a * c * (a - b + c) * (a + b + c)))
					* (2 * a * b + u(a * b * (a + b - c)
					* (a + b + c)))));
			case 181 -> a2 * (a + b - c) * (a - b + c) * p(b + c, 2);
			case 182 -> a6 - 2 * a2 * b2 * c2 - a4 * R;
			case 183 -> a4 - 2 * b2 * c2 - a2 * R;
			case 184 -> a4 * T;
			case 185 -> a2 * T * (-2 * a2 * Q + a4 * R + Q * R);
			case 186 -> a2 * U * (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2) * V;
			case 187 -> a2 * (2 * a2 - b2 - c2);
			case 188 -> u(a * (-a + b + c));
			case 189 -> -((a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2)));
			case 190 -> (a - b) * (a - c);
			case 191 -> a * (a3 - b3 - b2 * c - b * c2 - c3 + a2 * (b + c)
					- a * (b2 + b * c + c2));
			case 192 -> -(b * c) + a * (b + c);
			case 193 -> 3 * a2 - b2 - c2;
			case 194 -> -(b2 * c2) + a2 * R;
			case 195 -> a2 * (a8 - 4 * a6 * R + Q * (b4 + c4)
					+ a4 * (6 * b4 + 5 * b2 * c2 + 6 * c4)
					+ a2 * (-4 * b6 + b4 * c2 + b2 * c4 - 4 * c6));
			case 196 -> -((a + b - c) * (a - b + c) * U * V * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * p(b + c, 2)));
			case 197 -> a2 * (a4 + 2 * a2 * b * c - 2 * a * b * c * (b + c) - Q);
			case 198 -> a2 * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					- a * p(b + c, 2));
			case 199 -> a2 * (a4 + a2 * b * c + a3 * (b + c)
					- p(b + c, 2) * (b2 - b * c + c2)
					- a * (b3 + b2 * c + b * c2 + c3));
			default -> Double.NaN;
		};
	}

	private double weight200to299(int k, double a, double b, double c) {

		return switch (k) {
			case 200 -> a * p(-a + b + c, 2);
			case 201 -> a * (a + b - c) * (a - b + c) * p(b + c, 2) * T;
			case 202 -> 2 * a2
					* (a5 + 2 * a4 * b - 2 * a3 * b2 - 4 * a2 * b3 + a * b4
					+ 2 * b5 + 2 * a4 * c - a2 * b2 * c - b4 * c
					- 2 * a3 * c2 - a2 * b * c2 + 4 * a * b2 * c2
					- b3 * c2 - 4 * a2 * c3 - b2 * c3 + a * c4 - b * c4
					+ 2 * c5)
					- 2 * u(3) * a2 * (a3 - a * b2 + b2 * c - a * c2 + b * c2)
					* S;
			case 203 -> -2 * a2
					* (a5 + 2 * a4 * b - 2 * a3 * b2 - 4 * a2 * b3 + a * b4
					+ 2 * b5 + 2 * a4 * c - a2 * b2 * c - b4 * c
					- 2 * a3 * c2 - a2 * b * c2 + 4 * a * b2 * c2
					- b3 * c2 - 4 * a2 * c3 - b2 * c3 + a * c4 - b * c4
					+ 2 * c5)
					- 2 * u(3) * a2 * (a3 - a * b2 + b2 * c - a * c2 + b * c2)
					* S;
			case 204 -> a * U * V * (3 * a4 - Q - 2 * a2 * R);
			case 205 -> a3 * (a4 + 2 * a2 * b * c - 2 * a * b * c * (b + c) - Q);
			case 206 -> a4 * (a4 - b4 - c4);
			case 207 -> a * (a + b - c) * (a - b + c) * U * V
					* (a6 - 2 * a5 * (b + c) - a4 * p(b + c, 2)
					+ p(b - c, 2) * p(b + c, 4) - a2 * Q
					+ 4 * a3 * (b3 + c3)
					- 2 * a * (b5 - b4 * c - b * c4 + c5));
			case 208 -> a * (a + b - c) * (a - b + c) * U * V * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * p(b + c, 2));
			case 209 -> a2 * (b + c) * (-b3 + a * b * c - c3 + a2 * (b + c));
			case 210 -> -(a * (a - b - c) * (b + c));
			case 211 -> a4 * (-b6 - c6 + a2 * p(b2 + c2, 2));
			case 212 -> a3 * (a - b - c) * T;
			case 213 -> a3 * (b + c);
			case 214 -> a * (2 * a - b - c) * (a2 - b2 + b * c - c2);
			case 215 -> a4 * (a - b - c) * p(-a2 + b2 - b * c + c2, 2);
			case 216 -> a2 * T * (-Q + a2 * R);
			case 217 -> a4 * T * (-Q + a2 * R);
			case 218 -> a2 * (a2 + b2 + c2 - 2 * a * (b + c));
			case 219 -> a2 * (a - b - c) * T;
			case 220 -> a2 * p(-a + b + c, 2);
			case 221 -> a2 * (a + b - c) * (a - b + c) * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * p(b + c, 2));
			case 222 -> a2 * (a + b - c) * (a - b + c) * T;
			case 223 -> a * (a + b - c) * (a - b + c) * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * p(b + c, 2));
			case 224 -> a * T * (a4 - 2 * a2 * b * c - 2 * a3 * (b + c) - Q
					+ 2 * a * (b3 + c3));
			case 225 -> (a + b - c) * (a - b + c) * (b + c) * U * V;
			case 226 -> -((a + b - c) * (a - b + c) * (b + c));
			case 227 -> a * (a + b - c) * (a - b + c) * (b + c) * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * p(b + c, 2));
			case 228 -> a3 * (b + c) * T;
			case 229 -> a * (a + b) * (a + c)
					* (a4 + a2 * b * c + a * b * c * (b + c) - Q);
			case 230 -> 2 * a4 + Q - a2 * R;
			case 231 -> 2 * a8 + p(b2 - c2, 4) - 4 * a6 * R - 2 * a2 * Q * R
					+ 3 * a4 * (b4 + c4);
			case 232 -> a2 * U * V * (-b4 - c4 + a2 * R);
			case 233 -> -((2 * a4 + Q - 3 * a2 * R) * (-Q + a2 * R));
			case 234 -> (a + b - c) * (a - b + c)
					* (u(b * (a - b + c)) + u(c * (a + b - c)));
			case 235 -> -(U * V * (-2 * a2 * Q + a4 * R + Q * R));
			case 236 -> a * (-a + b + c) - u(a * (-a + b + c))
					* (u(b * (a - b + c)) + u(c * (a + b - c)));
			case 237 -> a4 * (-b4 - c4 + a2 * R);
			case 238 -> a3 - a * b * c;
			case 239 -> a2 - b * c;
			case 240 -> a * U * V * (-b4 - c4 + a2 * R);
			case 241 -> a * (a + b - c) * (a - b + c) * (-b2 - c2 + a * (b + c));
			case 242 -> (a2 - b * c) * U * V;
			case 243 -> (a - b - c) * U * V
					* (a4 - b * p(b - c, 2) * c - a2 * (b2 - b * c + c2));
			case 244 -> a * p(b - c, 2);
			case 245 -> a * p(b - c,
					2)
					* (b
					+ c)
					* (a7 + b7 + b6 * c + b * c6 + c7
					- a * b2 * c2 * (b2 + b * c + c2)
					- a5 * (2 * b2 + b * c + 2 * c2)
					+ a4 * (b3 + b2 * c + b * c2 + c3)
					+ a3 * (b4 + b3 * c + 3 * b2 * c2 + b * c3 + c4)
					- 2 * a2 * (b5 + b4 * c + b * c4 + c5));
			case 246 -> a2 * p(b - c, 2) * p(b + c, 2) * (a8 + b8 + 2 * b6 * c2
					+ 2 * b2 * c6 + c8 - 4 * a6 * R
					+ a4 * (6 * b4 + 7 * b2 * c2 + 6 * c4)
					- a2 * (4 * b6 + 5 * b4 * c2 + 5 * b2 * c4 + 4 * c6));
			case 247 -> p(b - c, 2) * p(b + c, 2)
					* (a10 - 4 * a8 * R + a6 * (5 * b4 + 7 * b2 * c2 + 5 * c4)
					+ Q * (b6 + c6)
					- a4 * (b6 + 6 * b4 * c2 + 6 * b2 * c4 + c6)
					+ a2 * (-2 * b8 + 5 * b6 * c2 - 2 * b4 * c4
					+ 5 * b2 * c6 - 2 * c8));
			case 248 -> a2 * T * (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4);
			case 249 -> a2 * p(a - b, 2) * p(a + b, 2) * p(a - c, 2) * p(a + c, 2);
			case 250 -> a2 * p(a - b, 2) * p(a + b, 2) * p(a - c, 2) * p(a + c, 2)
					* U * V;
			case 251 -> a2 * (a2 + b2) * (a2 + c2);
			case 252 -> (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 + Q - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2))
					* (a4 + Q - a2 * (b2 + 2 * c2));
			case 253 -> -((a4 + b4 + 2 * b2 * c2 - 3 * c4 - 2 * a2 * (b2 - c2))
					* (a4 - 3 * b4 + 2 * b2 * c2 + c4 + 2 * a2 * (b2 - c2)));
			case 254 -> U * V
					* (a6 - p(b2 - c2, 3) - a4 * (3 * b2 + c2)
					+ a2 * (3 * b4 + 2 * b2 * c2 - c4))
					* (a6 + p(b2 - c2, 3) - a4 * (b2 + 3 * c2)
					+ a2 * (-b4 + 2 * b2 * c2 + 3 * c4));
			case 255 -> a3 * (T * T);
			case 256 -> a * (b2 + a * c) * (a * b + c2);
			case 257 -> (b2 + a * c) * (a * b + c2);
			case 258 -> a * (-a2 + p(b - c, 2)
					+ 2 * u(b * (a - b + c)) * u(c * (a + b - c)));
			case 259 -> a * u(a * (-a + b + c));
			case 260 -> a * (u(a * (-a + b + c)) * u(b * (a - b + c))
					* u(c * (a + b - c))
					+ a * (-a + b + c) * (u(a * (-a + b + c))
					+ u(b * (a - b + c)) + u(c * (a + b - c))));
			case 261 -> p(a + b, 2) * (a - b - c) * p(a + c, 2);
			case 262 -> -((c2 * (b2 - c2) + a2 * (2 * b2 + c2))
					* (-b4 + b2 * c2 + a2 * (b2 + 2 * c2)));
			case 263 -> a2 * (c2 * (b2 - c2) + a2 * (2 * b2 + c2))
					* (-b4 + b2 * c2 + a2 * (b2 + 2 * c2));
			case 264 -> b2 * c2 * (-V) * U;
			case 265 -> -(T * (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (a2 - b2 - a * c + c2) * (a2 - b2 + a * c + c2));
			case 266 -> a2 * u(b * (a - b + c)) * u(c * (a + b - c));
			case 267 -> a * (a3 + a2 * (b + c) + (b - c) * p(b + c, 2)
					+ a * (b2 + b * c - c2))
					* (a3 + a2 * (b + c) - (b - c) * p(b + c, 2)
					+ a * (-b2 + b * c + c2));
			case 268 -> a2 * (a - b - c) * T
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 269 -> a * p(a + b - c, 2) * p(a - b + c, 2);
			case 270 -> a * p(a + b, 2) * (a - b - c) * p(a + c, 2) * U * V;
			case 271 -> a * (a - b - c) * T
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 272 -> (a + b) * (a + c) * (a3 - b2 * c + c3 - a * b * (b + c))
					* (a3 + b3 - b * c2 - a * c * (b + c));
			case 273 -> b * (-a + b - c) * (a + b - c) * c * (-V) * U;
			case 274 -> b * (a + b) * c * (a + c);
			case 275 -> U * V * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 276 -> b2 * c2 * (-V) * U * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (-a4 + c2 * (b2 - c2) + a2 * (b2 + 2 * c2));
			case 277 -> (a2 - 2 * a * b + p(b - c, 2))
					* (a2 + p(b - c, 2) - 2 * a * c);
			case 278 -> (a + b - c) * (a - b + c) * U * V;
			case 279 -> p(a + b - c, 2) * p(a - b + c, 2);
			case 280 -> (a - b - c)
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 281 -> (a - b - c) * U * V;
			case 282 -> a * (a - b - c)
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 283 -> a2 * (a + b) * (a - b - c) * (a + c) * T;
			case 284 -> a2 * (a + b) * (a - b - c) * (a + c);
			case 285 -> a * (a + b) * (a - b - c) * (a + c)
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 286 -> b * (a + b) * c * (a + c) * (-V) * U;
			case 287 -> T * (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4);
			case 288 -> a2 * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2))
					* (a4 + 2 * b4 - 3 * b2 * c2 + c4 - a2 * (3 * b2 + 2 * c2))
					* (a4 + b4 - 3 * b2 * c2 + 2 * c4 - a2 * (2 * b2 + 3 * c2));
			case 289 -> a2 * (a + b - c) * (a - b + c)
					* (2 * b * c - u(b * (a - b + c)) * u(c * (a + b - c)));
			case 290 -> b2 * c2 * (a4 + b4 - a2 * c2 - b2 * c2)
					* (-a4 + a2 * b2 + b2 * c2 - c4);
			case 291 -> -(a * (-b2 + a * c) * (a * b - c2));
			case 292 -> a2 * (-b2 + a * c) * (a * b - c2);
			case 293 -> a * T * (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4);
			case 294 -> a * (a - b - c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 295 -> a2 * (-b2 + a * c) * (a * b - c2) * T;
			case 296 -> a2 * (a + b - c) * (a - b + c) * T
					* (a3 * b + c2 * (b2 - c2) + a2 * (-2 * b2 + c2)
					+ a * (b3 - b * c2))
					* (-b4 + a3 * c + b2 * c2 + a2 * (b2 - 2 * c2)
					+ a * (-(b2 * c) + c3));
			case 297 -> U * V * (-b4 - c4 + a2 * R);
			case 298 -> -(u(3) * T) + S;
			case 299 -> -(u(3) * T) - S;
			default -> Double.NaN;
		};
	}

	private double weight300to399(int k, double a, double b, double c) {

		return switch (k) {
			case 300 -> (b2 * c2) / (u(3) * (-T) + S);
			case 301 -> (b2 * c2) / (u(3) * (-T) - S);
			case 302 -> -a2 + b2 + c2 + u(3) * S;
			case 303 -> a2 - b2 - c2 + u(3) * S;
			case 304 -> b * c * (-T);
			case 305 -> b2 * c2 * (-T);
			case 306 -> (b + c) * (-T);
			case 307 -> (a + b - c) * (a - b + c) * (b + c) * T;
			case 308 -> b2 * (a2 + b2) * c2 * (a2 + c2);
			case 309 -> b * c
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2))
					* (-a3 + a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 310 -> b2 * (a + b) * c2 * (a + c);
			case 311 -> b2 * c2 * (Q - a2 * R);
			case 312 -> b * c * (-a + b + c);
			case 313 -> b2 * c2 * (b + c);
			case 314 -> b * (a + b) * c * (a + c) * (-a + b + c);
			case 315 -> -a4 + b4 + c4;
			case 316 -> -a4 + b4 - b2 * c2 + c4;
			case 317 -> -(U * V * (a4 + b4 + c4 - 2 * a2 * R));
			case 318 -> b * c * (-a + b + c) * (-V) * U;
			case 319 -> -a2 + b2 + b * c + c2;
			case 320 -> -a2 + b2 - b * c + c2;
			case 321 -> b * c * (b + c);
			case 322 -> b * c * (-a3 - a2 * (b + c) + p(b - c, 2) * (b + c)
					+ a * p(b + c, 2));
			case 323 -> a2 * (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2);
			case 324 -> b2 * c2 * (-V) * U * (Q - a2 * R);
			case 325 -> b4 + c4 - a2 * R;
			case 326 -> a * (T * T);
			case 327 -> b2 * c2 * (c2 * (b2 - c2) + a2 * (2 * b2 + c2))
					* (b4 - b2 * c2 - a2 * (b2 + 2 * c2));
			case 328 -> b2 * c2 * (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (-a2 + b2 - a * c - c2) * (-a2 + b2 + a * c - c2) * (-T);
			case 329 -> -a3 - a2 * (b + c) + p(b - c, 2) * (b + c) + a * p(b + c, 2);
			case 330 -> (a * (b - c) - b * c) * (a * (b - c) + b * c);
			case 331 -> b2 * (-a + b - c) * (a + b - c) * c2 * (-V) * U;
			case 332 -> (a + b) * (a - b - c) * (a + c) * T;
			case 333 -> (a + b) * (a - b - c) * (a + c);
			case 334 -> b * c * (b2 - a * c) * (a * b - c2);
			case 335 -> (b2 - a * c) * (a * b - c2);
			case 336 -> b * c * (-T) * (a4 + b4 - a2 * c2 - b2 * c2)
					* (-a4 + a2 * b2 + b2 * c2 - c4);
			case 337 -> (b2 - a * c) * (a * b - c2) * (-T);
			case 338 -> b2 * p(b - c, 2) * c2 * p(b + c, 2);
			case 339 -> b2 * p(b - c, 2) * c2 * p(b + c, 2) * (-T);
			case 340 -> -(U * (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2) * V);
			case 341 -> b * c * p(-a + b + c, 2);
			case 342 -> b * (-a + b - c) * (a + b - c) * c * (-V) * U * (-a3
					- a2 * (b + c) + p(b - c, 2) * (b + c) + a * p(b + c, 2));
			case 343 -> T * (-Q + a2 * R);
			case 344 -> a2 + b2 + c2 - 2 * a * (b + c);
			case 345 -> (a - b - c) * T;
			case 346 -> p(-a + b + c, 2);
			case 347 -> (a + b - c) * (a - b + c) * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * p(b + c, 2));
			case 348 -> (a + b - c) * (a - b + c) * T;
			case 349 -> b2 * (-a + b - c) * (a + b - c) * c2 * (b + c);
			case 350 -> b * c * (-a2 + b * c);
			case 351 -> a2 * (b2 - c2) * (2 * a2 - b2 - c2);
			case 352 -> a2 * (a4 + b4 + 5 * b2 * c2 + c4 - 4 * a2 * R);
			case 353 -> a2 * (4 * a4 - 2 * b4 - b2 * c2 - 2 * c4 - 4 * a2 * R);
			case 354 -> a * (-p(b - c, 2) + a * (b + c));
			case 355 -> -a4 - 2 * a2 * b * c + a3 * (b + c)
					- a * p(b - c, 2) * (b + c) + Q;
			case 356 -> a * (Math.cos(angleA / 3)
					+ 2 * Math.cos(angleB / 3) * Math.cos(angleC / 3));
			case 357 -> a / Math.cos(angleA / 3);
			case 358 -> a * Math.cos(angleA / 3);
			case 359 -> a2 / angleA;
			case 360 -> angleA;
			case 361 -> a * (-u(-(b * (a - b - c) * c * (a + b + c)))
					+ u(a * c * (a - b + c) * (a + b + c))
					+ u(a * b * (a + b - c) * (a + b + c)));
			case 362 -> a * (-(a2 * u(-(b * (a - b - c) * c * (a + b + c))))
					+ b2 * u(a * c * (a - b + c) * (a + b + c))
					+ c2 * u(a * b * (a + b - c) * (a + b + c)));
			case 363 -> a * (-4 * a * b * c * (-a + b + c)
					+ c * (a - 3 * b + c) * u(a * (-a + b + c))
					* u(b * (a - b + c))
					+ b * (a + b - 3 * c) * u(a * (-a + b + c))
					* u(c * (a + b - c))
					- a * (a + b + c) * u(b * (a - b + c))
					* u(c * (a + b - c)));
			case 364 -> a * (-u(a) + u(b) + u(c));
			case 365 -> a * u(a);
			case 366 -> u(a);
			case 367 -> a * (u(b) + u(c));
			/* case 368 to 370: perl script returns 0 */
			case 371 -> a2 * T - a2 * S;
			case 372 -> a2 * T + a2 * S;
			case 373 -> a2 * (-b4 + 6 * b2 * c2 - c4 + a2 * R);
			case 374 -> -(a * (a3 * (b + c) - Q + a2 * (b2 - 6 * b * c + c2)
					- a * (b3 - 5 * b2 * c - 5 * b * c2 + c3)));
			case 375 -> a2 * (-(a * b * c * (b + c)) + a2 * R
					- p(b + c, 2) * (b2 - 3 * b * c + c2));
			case 376 -> 5 * a4 - Q - 4 * a2 * R;
			case 377 -> -a4 - 2 * a2 * b * c - 2 * a * b * c * (b + c) + Q;
			case 378 -> a2 * U * V * (a4 + b4 + 4 * b2 * c2 + c4 - 2 * a2 * R);
			case 379 -> a5 + a2 * b * c * (b + c) - b * p(b - c, 2) * c * (b + c)
					- a * Q;
			case 380 -> a * (a - b - c) * (3 * a3 + 3 * a2 * (b + c)
					+ p(b - c, 2) * (b + c) + a * p(b + c, 2));
			case 381 -> -a4 + 2 * Q - a2 * R;
			case 382 -> -3 * a4 + 2 * Q + a2 * R;
			case 383 -> -(u(3) * U * V * (a2 + b2 + c2))
					- (a - b - c) * (a + b - c) * (a - b + c) * (a + b + c) * S;
			case 384 -> a4 + b2 * c2;
			case 385 -> a4 - b2 * c2;
			case 386 -> a2 * (b2 + b * c + c2 + a * (b + c));
			case 387 -> a4 + 4 * a3 * (b + c) + 2 * a2 * p(b + c, 2) + Q;
			case 388 -> -a4 - 4 * a2 * b * c + Q;
			case 389 -> a2 * (a6 * R + 3 * a2 * Q * R - 3 * a4 * (b4 + c4)
					- Q * (b4 + c4));
			case 390 -> (3 * a2 + p(b - c, 2)) * (a - b - c);
			case 391 -> (a - b - c) * (3 * a + b + c);
			case 392 -> a * (b3 + 4 * a * b * c + b2 * c + b * c2 + c3
					- a2 * (b + c));
			case 393 -> p(a4 - Q, 2);
			case 394 -> a2 * (T * T);
			case 395 -> (a - b - c) * (a + b - c) * (a - b + c) * (a + b + c)
					+ u(3) * a2 * S;
			case 396 -> -((a - b - c) * (a + b - c) * (a - b + c) * (a + b + c))
					+ u(3) * a2 * S;
			case 397 -> U * V + u(3) * a2 * S;
			case 398 -> -(U * V) + u(3) * a2 * S;
			case 399 -> a2 * (a8 - 4 * a6 * R + Q * (b4 + 4 * b2 * c2 + c4)
					+ a4 * (6 * b4 + b2 * c2 + 6 * c4)
					+ a2 * (-4 * b6 + b4 * c2 + b2 * c4 - 4 * c6));
			default -> Double.NaN;
		};
	}

	private double weight400to499(int k, double a, double b, double c) {

		return switch (k) {
			case 400 -> (a2 - b2 + 6 * a * c + c2
					- 4 * u(a * c * (a - b + c) * (a + b + c)))
					* (a2 + 6 * a * b + b2 - c2
					- 4 * u(a * b * (a + b - c) * (a + b + c)));
			case 401 -> a8 + b2 * c2 * Q - 2 * a6 * R + a4 * (b4 + b2 * c2 + c4);
			case 402 -> (2 * a4 - Q - a2 * R) * (a8 - a6 * R + 3 * a2 * Q * R
					+ a4 * (-2 * b4 + 5 * b2 * c2 - 2 * c4)
					- Q * (b4 + 3 * b2 * c2 + c4));
			case 403 -> -(U * V * (a4 * R + Q * R - 2 * a2 * (b4 - b2 * c2 + c4)));
			case 404 -> a * (a3 + b * c * (b + c) - a * (b2 - b * c + c2));
			case 405 -> a * (a3 - 2 * b * c * (b + c) - a * p(b + c, 2));
			case 406 -> U * V * (a3 - b3 - b2 * c - b * c2 - c3 + a2 * (b + c)
					- a * p(b + c, 2));
			case 407 -> (b + c) * (-V) * U * (-2 * a2 + p(b - c, 2) - a * (b + c));
			case 408 -> a3 * (b + c) * (T * T)
					* (a4 * (b + c) - a2 * p(b - c, 2) * (b + c)
					- 2 * b * p(b - c, 2) * c * (b + c) - a * Q
					+ a3 * R);
			case 409 -> a * (a + b) * (a + c)
					* (a4 + a2 * b * c - a3 * (b + c)
					- p(b + c, 2) * (b2 - 3 * b * c + c2)
					+ a * (b3 + b2 * c + b * c2 + c3));
			case 410 -> (a + b) * (a + c) * U * V
					* (-(a7 * b * c * (b + c))
					+ a3 * b * p(b - c, 2) * c * p(b + c, 3)
					- b2 * p(b - c, 2) * c2 * p(b + c, 4)
					- a * b * p(b - c, 2) * c * p(b + c, 3) * R
					+ a8 * (b2 + 3 * b * c + c2)
					+ a5 * b * c * (b3 + b2 * c + b * c2 + c3)
					- a2 * Q * (b4 - b3 * c + b2 * c2 - b * c3 + c4)
					+ a4 * p(b + c, 2)
					* (3 * b4 - 5 * b3 * c + 9 * b2 * c2
					- 5 * b * c3 + 3 * c4)
					- a6 * (3 * b4 + 5 * b3 * c + 3 * b2 * c2
					+ 5 * b * c3 + 3 * c4));
			case 411 -> a * (a6 - a5 * (b + c) + b * c * Q + a2 * p(b2 + c2, 2)
					- a4 * (2 * b2 + b * c + 2 * c2) + 2 * a3 * (b3 + c3)
					- a * (b5 - b4 * c - b * c4 + c5));
			case 412 -> U * V
					* (a6 + a5 * (b + c) + b * c * Q + a2 * p(b2 + c2, 2)
					- a4 * (2 * b2 + b * c + 2 * c2)
					- 2 * a3 * (b3 + c3)
					+ a * (b5 - b4 * c - b * c4 + c5));
			case 413 -> a * (a + b) * p(a - b - c, 3) * (a + c)
					* (a4 + a2 * b * c + a3 * (b + c)
					+ a * p(b - c, 2) * (b + c)
					+ p(b - c, 2) * (b2 + b * c + c2));
			case 414 -> (a + b) * p(a - b - c, 3) * (a + c) * U * V
					* (b2 * p(b - c, 4) * c2 * p(b + c, 2)
					+ a * b * p(b - c, 4) * c * p(b + c, 3)
					+ a8 * (b2 - b * c + c2)
					+ a7 * (2 * b3 - b2 * c - b * c2 + 2 * c3)
					- a5 * p(b - c, 2)
					* (4 * b3 + 5 * b2 * c + 5 * b * c2
					+ 4 * c3)
					- a6 * (b4 - 3 * b3 * c + 3 * b2 * c2 - 3 * b * c3
					+ c4)
					+ a2 * Q * (b4 + b3 * c - b2 * c2 + b * c3 + c4)
					- a4 * p(b - c, 2)
					* (b4 + 5 * b3 * c + 5 * b2 * c2
					+ 5 * b * c3 + c4)
					+ a3 * (2 * b7 - 3 * b6 * c + b5 * c2 + b2 * c5
					- 3 * b * c6 + 2 * c7));
			case 415 -> (a + b) * (a + c) * U * V
					* (a3 + b3 + a * b * c + c3 - 2 * a2 * (b + c));
			case 416 -> a * (a + b) * (a + c)
					* (b2 * p(b - c, 2) * c2 * p(b + c, 3)
					- a2 * p(b - c, 2) * p(b + c, 3) * R
					+ a3 * Q * (b2 - b * c + c2)
					+ a * b * c * Q * (b2 - b * c + c2)
					+ a7 * (b2 + b * c + c2)
					- a6 * (b3 + b2 * c + b * c2 + c3)
					- a5 * (2 * b4 + b3 * c - b2 * c2 + b * c3 + 2 * c4)
					+ a4 * (2 * b5 + 2 * b4 * c - b3 * c2 - b2 * c3
					+ 2 * b * c4 + 2 * c5));
			case 417 -> a4 * p(a2 - b2 - c2, 3) * (-2 * a2 * Q + a4 * R + Q * R);
			case 418 -> a4 * (T * T) * (-Q + a2 * R);
			case 419 -> (a2 - b * c) * (a2 + b * c) * U * V;
			case 420 -> U * V * (a4 - b4 - b2 * c2 - c4 + a2 * R);
			case 421 -> U * V * (a8 - b2 * c2 * Q - 2 * a6 * R
					+ a4 * (b4 + b2 * c2 + c4));
			case 422 -> (a + b) * (a + c) * U * V
					* (a3 + a * b * c - b * c * (b + c));
			case 423 -> (a + b) * (a + c) * U * V
					* (a2 - b2 - b * c - c2 + a * (b + c));
			case 424 -> (b + c) * (-V) * U
					* (b4 + c4 - a3 * (b + c) - a2 * R + a * (b3 + c3));
			case 425 -> (a + b) * (a + c) * U * V
					* (a6 + 2 * a2 * b2 * c2 - a5 * (b + c)
					- 2 * a * b2 * c2 * (b + c) - b * c * Q
					- a4 * (b2 - b * c + c2)
					+ a3 * (b3 + b2 * c + b * c2 + c3));
			case 426 -> a2 * p(a2 - b2 - c2, 3) * (a4 + Q);
			case 427 -> -(U * V * R);
			case 428 -> U * V * (2 * a2 + b2 + c2);
			case 429 -> (b + c) * (-V) * U * (b2 + c2 + a * (b + c));
			case 430 -> (b + c) * (2 * a + b + c) * (-V) * U;
			case 431 -> (b + c) * (-V) * U * (b5 + 2 * a3 * b * c - b4 * c - b * c4
					+ c5 + a4 * (b + c) - 2 * a2 * p(b - c, 2) * (b + c));
			case 432 -> -(U * V * (a10 * a6 * R + p(b2 - c2, 8) * R
					- 8 * a10 * a4 * (b4 + c4)
					- 8 * a2 * p(b2 - c2, 6) * (b4 + b2 * c2 + c4)
					+ 4 * a10 * a2 * (7 * b6 - b4 * c2 - b2 * c4 + 7 * c6)
					- 8 * a10
					* (7 * b8 - b6 * c2 - 4 * b4 * c4 - b2 * c6
					+ 7 * c8)
					- 8 * a6 * Q
					* (7 * b8 + 4 * b6 * c2 + 6 * b4 * c4 + 4 * b2 * c6
					+ 7 * c8)
					+ 4 * a4 * Q
					* (7 * b10 - 7 * b8 * c2 + 4 * b6 * c4 + 4 * b4 * c6
					- 7 * b2 * c8 + 7 * c10)
					+ a8 * (70 * b10 - 34 * b8 * c2 - 20 * b6 * c4
					- 20 * b4 * c6 - 34 * b2 * c8 + 70 * c10)));
			case 433 -> -(U * V
					* (2 * a10 * Q + a10 * a2 * R - a8 * Q * R
					+ 2 * a2 * p(b2 - c2, 4) * p(b2 + c2, 2)
					+ Q * p(R, 5) - 4 * a6 * Q * (b4 + c4)
					- a4 * Q * (b6 + 7 * b4 * c2 + 7 * b2 * c4 + c6)));
			case 434 -> -(U * V * (a10 * a6 * R + p(b2 - c2, 8) * R
					- 8 * a10 * a4 * (b4 + b2 * c2 + c4)
					- 2 * a2 * p(b2 - c2, 6) * (4 * b4 + 5 * b2 * c2 + 4 * c4)
					+ a10 * a2
					* (28 * b6 + 26 * b4 * c2 + 26 * b2 * c4 + 28 * c6)
					- 2 * a10
					* (28 * b8 + 17 * b6 * c2 + 18 * b4 * c4
					+ 17 * b2 * c6 + 28 * c8)
					- 2 * a6 * Q
					* (28 * b8 + 22 * b6 * c2 + 25 * b4 * c4
					+ 22 * b2 * c6 + 28 * c8)
					+ a4 * Q * (28 * b10 - 22 * b8 * c2 - 5 * b6 * c4
					- 5 * b4 * c6 - 22 * b2 * c8 + 28 * c10)
					+ a8 * (70 * b10 - 6 * b8 * c2 + 17 * b6 * c4 + 17 * b4 * c6
					- 6 * b2 * c8 + 70 * c10)));
			case 435 -> -(U * V * (a10 * a6 * R + p(b2 - c2, 8) * R
					- 8 * a10 * a4 * (b4 - b2 * c2 + c4)
					- 2 * a2 * p(b2 - c2, 6) * (4 * b4 + 5 * b2 * c2 + 4 * c4)
					- 2 * a10 * p(b2 + c2, 2)
					* (28 * b4 - 55 * b2 * c2 + 28 * c4)
					+ a10 * a2
					* (28 * b6 - 22 * b4 * c2 - 22 * b2 * c4 + 28 * c6)
					- 2 * a6 * Q
					* (28 * b8 + 46 * b6 * c2 + 81 * b4 * c4
					+ 46 * b2 * c6 + 28 * c8)
					+ a4 * Q * (28 * b10 - 6 * b8 * c2 + 59 * b6 * c4
					+ 59 * b4 * c6 - 6 * b2 * c8 + 28 * c10)
					+ a8 * (70 * b10 + 26 * b8 * c2 - 95 * b6 * c4
					- 95 * b4 * c6 + 26 * b2 * c8 + 70 * c10)));
			case 436 -> U * V * (a8 - b2 * c2 * Q - 2 * a6 * R
					+ a4 * (b4 + 3 * b2 * c2 + c4));
			case 437 -> -(U * V
					* (5 * a6 - 6 * a5 * (b + c)
					- 2 * a4 * (4 * b2 - 9 * b * c + 4 * c2)
					- 3 * a2 * b * c * (4 * b2 - 7 * b * c + 4 * c2)
					+ a3 * (11 * b3 - 5 * b2 * c - 5 * b * c2 + 11 * c3)
					+ p(b + c, 2) * (3 * b4 - 11 * b3 * c + 17 * b2 * c2
					- 11 * b * c3 + 3 * c4)
					- a * (5 * b5 - 12 * b4 * c + 11 * b3 * c2
					+ 11 * b2 * c3 - 12 * b * c4 + 5 * c5)));
			case 438 -> U * V
					* (5 * a10 * a2 - 7 * a10 * R + 18 * a6 * Q * R
					+ a8 * (-8 * b4 + 26 * b2 * c2 - 8 * c4)
					- a4 * Q * (11 * b4 + 38 * b2 * c2 + 11 * c4)
					+ a2 * Q * (5 * b6 + 27 * b4 * c2 + 27 * b2 * c4
					+ 5 * c6)
					- 2 * Q * (b8 + 3 * b6 * c2 + 8 * b4 * c4
					+ 3 * b2 * c6 + c8));
			case 439 -> p(-3 * a2 + b2 + c2, 2);
			case 440 -> (b + c) * (-T)
					* (2 * a3 + a2 * (b + c) + p(b - c, 2) * (b + c));
			case 441 -> T * (2 * a6 - a4 * R - Q * R);
			case 442 -> (b + c)
					* (-2 * a * b * c - a2 * (b + c) + p(b - c, 2) * (b + c));
			case 443 -> -a4 - 4 * a2 * b * c - 4 * a * b * c * (b + c) + Q;
			case 444 -> a * (a2 + b * c) * U * V * (b2 + c2 + a * (b + c));
			case 445 -> -(U * (a2 - b2 - b * c - c2) * V
					* (2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c)));
			case 446 -> a2 * (-b4 - c4 + a2 * R) * (2 * a2 * b2 * c2 * Q + a8 * R
					+ b2 * c2 * Q * R - 2 * a6 * (b4 + c4) + a4 * (b6 + c6));
			case 447 -> (a + b) * (a + c) * U * V
					* (a4 + a2 * b * c - a3 * (b + c)
					- p(b + c, 2) * (b2 - b * c + c2)
					+ a * (b3 + b2 * c + b * c2 + c3));
			case 448 -> (a + b) * (a + c)
					* (a6 - a5 * (b + c) + b * c * Q - a4 * (b2 + b * c + c2)
					+ a3 * (b3 + b2 * c + b * c2 + c3));
			case 449 -> 2 * a8 - 8 * a5 * b * c * (b + c)
					+ 4 * a * b * p(b - c, 2) * c * p(b + c, 3)
					- a2 * Q * (b2 - 4 * b * c + c2)
					- a6 * (3 * b2 + 8 * b * c + 3 * c2)
					+ 4 * a3 * b * c * (b3 + b2 * c + b * c2 + c3)
					+ p(b4 - c4, 2)
					+ a4 * (b4 + 4 * b3 * c + 2 * b2 * c2 + 4 * b * c3 + c4);
			case 450 -> U * V * (a4 - b * p(b - c, 2) * c - a2 * (b2 - b * c + c2))
					* (a4 + b * c * p(b + c, 2) - a2 * (b2 + b * c + c2));
			case 451 -> U * V * (a3 - b3 - b2 * c - b * c2 - c3 + a2 * (b + c)
					- a * (b2 + b * c + c2));
			case 452 -> (a - b - c) * (3 * a3 + 3 * a2 * (b + c)
					+ p(b - c, 2) * (b + c) + a * p(b + c, 2));
			case 453 -> a * (a + b) * (a - b - c) * (a + c)
					* p(a3 + a2 * (b + c) - p(b - c, 2) * (b + c) - a * R, 2);
			case 454 -> a2 * T * p(a6 - 3 * a4 * R - Q * R
					+ a2 * (3 * b4 - 2 * b2 * c2 + 3 * c4), 2);
			case 455 -> a2 * U * V * p(a6 + a4 * R - Q * R - a2 * p(b2 + c2, 2), 2);
			case 456 -> a2 * U * V
					* p(a8 - 4 * a6 * R + Q * (b4 + c4)
							+ a4 * (6 * b4 + 5 * b2 * c2 + 6 * c4) + a2
							* (-4 * b6 + b4 * c2 + b2 * c4 - 4 * c6),
					2);
			case 457 -> a2 * U * V
					* p(a8 - 4 * a6 * R + Q * (b4 + 4 * b2 * c2 + c4)
							+ a4 * (6 * b4 + b2 * c2 + 6 * c4) + a2
							* (-4 * b6 + b4 * c2 + b2 * c4 - 4 * c6),
					2);
			case 458 -> U * V * (a4 - 2 * b2 * c2 - a2 * R);
			case 459 -> (3 * a2 - b2 - c2) * U * V;
			case 460 -> U * V * (2 * a4 + Q - a2 * R);
			case 461 -> (a - b - c) * (3 * a + b + c) * U * V;
			case 462 -> (a - b - c) * (a + b - c) * (a - b + c) * (a + b + c) * U * V
					+ u(3) * a2 * U * V * S;
			case 463 -> -((a - b - c) * (a + b - c) * (a - b + c) * (a + b + c) * U
					* V) + u(3) * a2 * U * V * S;
			case 464 -> -(T * (a4 + 4 * a3 * (b + c) + 2 * a2 * p(b + c, 2) + Q));
			case 465 -> T * U * V + u(3) * a2 * T * S;
			case 466 -> -(T * U * V) + u(3) * a2 * T * S;
			case 467 -> U * V * (a4 + b4 + c4 - 2 * a2 * R) * (-Q + a2 * R);
			case 468 -> (2 * a2 - b2 - c2) * U * V;
			case 469 -> -(U * V * (b2 + b * c + c2 + a * (b + c)));
			case 470 -> 3 * T * U * V - u(3) * U * V * S;
			case 471 -> -3 * T * U * V - u(3) * U * V * S;
			case 472 -> -(T * U * V) - u(3) * U * V * S;
			case 473 -> T * U * V - u(3) * U * V * S;
			case 474 -> a * (a3 - a * p(b - c, 2) + 2 * b * c * (b + c));
			case 475 -> U * V * (a3 - b3 - a * p(b - c, 2) - b2 * c - b * c2 - c3
					+ a2 * (b + c));
			case 476 -> (a - b) * (a + b) * (a - c) * (a + c)
					* (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (a2 - b2 - a * c + c2) * (a2 - b2 + a * c + c2);
			case 477 -> (a8 + a6 * (b2 - 3 * c2) + b2 * p(b2 - c2, 3)
					+ a4 * (-4 * b4 + 2 * b2 * c2 + 3 * c4)
					+ a2 * (b6 + 2 * b4 * c2 - 2 * b2 * c4 - c6))
					* (a8 + a6 * (-3 * b2 + c2) + c2 * p(-b2 + c2, 3)
					+ a4 * (3 * b4 + 2 * b2 * c2 - 4 * c4)
					+ a2 * (-b6 - 2 * b4 * c2 + 2 * b2 * c4 + c6));
			case 478 -> a2 * (a + b - c) * (a - b + c)
					* (a4 + 2 * a2 * b * c - 2 * a * b * c * (b + c) - Q);
			case 479 -> -(p(a + b - c, 3) * p(a - b + c, 3));
			case 480 -> a2 * p(a - b - c, 3);
			case 481 -> -(a * (a - b - c) * (a + b - c) * (a - b + c))
					- (a + b - c) * (a - b + c) * S;
			case 482 -> a * (a - b - c) * (a + b - c) * (a - b + c)
					- (a + b - c) * (a - b + c) * S;
			case 483 -> (2 * a * c + u(a * c * (a - b + c) * (a + b + c)))
					* (2 * a * b + u(a * b * (a + b - c) * (a + b + c)));
			case 484 -> a * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					- a * (b2 + b * c + c2));
			case 485 -> a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4 + a2 * S;
			case 486 -> -(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4 + a2 * S;
			case 487 -> 2 * a2 * T + (-T) * S;
			case 488 -> -2 * a2 * T + (-T) * S;
			case 489 -> 3 * a4 - 2 * a2 * b2 - b4 - 2 * a2 * c2 + 2 * b2 * c2 - c4
					+ (-T) * S;
			case 490 -> -3 * a4 + 2 * a2 * b2 + b4 + 2 * a2 * c2 - 2 * b2 * c2 + c4
					+ (-T) * S;
			case 491 -> a2 - b2 - c2 + S;
			case 492 -> -a2 + b2 + c2 + S;
			case 493 -> -(a2
					* (a4 - 2 * a2 * b2 + b4 - 2 * a2 * c2 - 6 * b2 * c2 + c4))
					+ 2 * a2 * R * S;
			case 494 -> a2 * (a4 - 2 * a2 * b2 + b4 - 2 * a2 * c2 - 6 * b2 * c2 + c4)
					+ 2 * a2 * R * S;
			case 495 -> Q - a2 * (b2 + 4 * b * c + c2);
			case 496 -> Q - a2 * (b2 - 4 * b * c + c2);
			case 497 -> -((a2 + p(b - c, 2)) * (a - b - c));
			case 498 -> a4 + Q - 2 * a2 * (b2 + b * c + c2);
			case 499 -> a4 + Q - 2 * a2 * (b2 - b * c + c2);
			default -> Double.NaN;
		};
	}

	private double weight500to599(int k, double a, double b, double c) {

		return switch (k) {
			case 500 -> a2 * (a2 - b2 - b * c - c2)
					* (2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 501 -> a2 * (a + b) * (a + c) * (a3 - b3 - b2 * c - b * c2 - c3
					+ a2 * (b + c) - a * (b2 + b * c + c2));
			case 502 -> (b + c)
					* (-a3 - a2 * (b + c) + (b - c) * p(b + c, 2)
					+ a * (b2 - b * c - c2))
					* (a3 + a2 * (b + c) + (b - c) * p(b + c, 2)
					+ a * (b2 + b * c - c2));
			case 503 -> a * (-(b * (a + b - c) * c * (a - b + c)
					* u(a * (-a + b + c)))
					+ a * (a + b - c) * c * (-a + b + c) * u(b * (a - b + c))
					+ a * b * (a - b + c) * (-a + b + c) * u(c * (a + b - c)));
			case 504 -> a * (-(a2 * (a + b - c) * (a - b + c)
					* u(-(b * (a - b - c) * c * (a + b + c))))
					+ b2 * (a + b - c) * (-a + b + c)
					* u(a * c * (a - b + c) * (a + b + c))
					+ c2 * (a - b + c) * (-a + b + c)
					* u(a * b * (a + b - c) * (a + b + c)));
			case 505 -> a * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					- a * p(b + c, 2) + 2 * (-a + b + c) * u(b * (a - b + c))
					* u(c * (a + b - c)));
			case 506 -> -((a + b - c) * (a - b + c) * p(a * (-a + b + c), 2d / 3));
			case 507 -> -((a + b - c) * (a - b + c) * p(a * (-a + b + c), 3d / 4));
			case 508 -> u(a - b + c) * u(a + b - c);
			case 509 -> a * u(a - b + c) * u(a + b - c);
			case 510 -> a * (-a * u(a) + b * u(b) + c * u(c));
			case 511 -> a2 * (-b4 - c4 + a2 * R);
			case 512 -> a2 * (b2 - c2);
			case 513 -> a * (b - c);
			case 514 -> b - c;
			case 515 -> 2 * a4 - a2 * p(b - c, 2) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) - Q;
			case 516 -> 2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c);
			case 517 -> a * (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 518 -> a * (-b2 - c2 + a * (b + c));
			case 519 -> 2 * a - b - c;
			case 520 -> a2 * (b2 - c2) * (T * T);
			case 521 -> a * (a - b - c) * (b - c) * T;
			case 522 -> (a - b - c) * (b - c);
			case 523 -> (b2 - c2);
			case 524 -> 2 * a2 - b2 - c2;
			case 525 -> -((b2 - c2) * (-T));
			case 526 -> a2 * (b6 - c6 + a4 * (b2 - c2) - 2 * a2 * (b4 - c4));
			case 527 -> 2 * a2 - p(b - c, 2) - a * (b + c);
			case 528 -> 2 * a3 - 2 * a2 * (b + c) - p(b - c, 2) * (b + c) + a * R;
			case 529 -> 2 * a4 - 2 * a * b * c * (b + c) - Q
					- a2 * (b2 - 4 * b * c + c2);
			case 530 -> 3 * (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					+ u(3) * (2 * a2 - b2 - c2) * S;
			case 531 -> 3 * (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					- u(3) * (2 * a2 - b2 - c2) * S;
			case 532 -> 2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4
					+ u(3) * (2 * a2 - b2 - c2) * S;
			case 533 -> 2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4
					- u(3) * (2 * a2 - b2 - c2) * S;
			case 534 -> 2 * a5 - b5 + b4 * c + b * c4 - c5 + a4 * (b + c)
					- 2 * a2 * b * c * (b + c) - 2 * a * Q;
			case 535 -> 2 * a4 - a2 * p(b - c, 2) - a * b * c * (b + c) - Q;
			case 536 -> -2 * b * c + a * (b + c);
			case 537 -> a2 * (b + c) + b * c * (b + c) - 2 * a * R;
			case 538 -> -2 * b2 * c2 + a2 * R;
			case 539 -> T * (2 * a8 + p(b2 - c2, 4) - 4 * a6 * R - 2 * a2 * Q * R
					+ 3 * a4 * (b4 + c4));
			case 540 -> 2 * a4 + 2 * a2 * b * c + 2 * a3 * (b + c)
					- p(b + c, 2) * (b2 - b * c + c2)
					- a * (b3 + b2 * c + b * c2 + c3);
			case 541 -> 2 * a10 - p(b2 - c2, 4) * R
					+ a6 * (-11 * b4 + 16 * b2 * c2 - 11 * c4)
					- a2 * Q * (3 * b4 + 14 * b2 * c2 + 3 * c4)
					+ a4 * (13 * b6 - 11 * b4 * c2 - 11 * b2 * c4 + 13 * c6);
			case 542 -> 2 * a6 - 2 * a4 * R - Q * R + a2 * (b4 + c4);
			case 543 -> 2 * a4 - b4 + 4 * b2 * c2 - c4 - 2 * a2 * R;
			case 544 -> 2 * a4 + 2 * a2 * b * c - 2 * a3 * (b + c)
					+ a * p(b - c, 2) * (b + c)
					- p(b - c, 2) * (b2 + b * c + c2);
			case 545 -> 2 * a2 - b2 + 4 * b * c - c2 - 2 * a * (b + c);
			case 546 -> -2 * a4 + 3 * Q - a2 * R;
			case 547 -> 2 * a4 + 5 * Q - 7 * a2 * R;
			case 548 -> 6 * a4 - Q - 5 * a2 * R;
			case 549 -> 4 * a4 + Q - 5 * a2 * R;
			case 550 -> 4 * a4 - Q - 3 * a2 * R;
			case 551 -> 4 * a + b + c;
			case 552 -> p(a + b, 2) * (a + b - c) * p(a + c, 2) * (a - b + c);
			case 553 -> (a + b - c) * (a - b + c) * (2 * a + b + c);
			case 554 -> 1 / (u(3) * (-a + b + c) * (a + b + c) + S);
			case 555 -> b * p(a + b - c, 2) * c * p(a - b + c, 2)
					* u(a * (-a + b + c));
			case 556 -> b * c * u(a * (-a + b + c));
			case 557 -> a * (2 * b * c + u(-(b * (a - b - c) * c * (a + b + c))));
			case 558 -> a * (2 * b * c - u(-(b * (a - b - c) * c * (a + b + c))));
			case 559 -> a * (a + b - c) * (a - b + c) + u(3) * a * S;
			case 560 -> a5;
			case 561 -> b3 * c3;
			case 562 -> -(U * (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2) * V
					* (a4 + Q - a2 * (2 * b2 + c2))
					* (a4 + Q - a2 * (b2 + 2 * c2)));
			case 563 -> a5 * T * (a4 + b4 + c4 - 2 * a2 * R);
			case 564 -> b * c * (p(b2 - c2, 4) - 2 * a2 * Q * R + a4 * (b4 + c4));
			case 565 -> b2 * c2 * (Q - a2 * R) * (p(b2 - c2, 4) - 2 * a2 * Q * R
					+ a4 * (b4 - b2 * c2 + c4));
			case 566 -> a2 * (a4 * R + Q * R - a2 * (2 * b4 + b2 * c2 + 2 * c4));
			case 567 -> a2 * (a8 - 2 * b2 * c2 * Q - 3 * a6 * R
					+ 3 * a4 * (b4 + b2 * c2 + c4)
					- a2 * (b6 - 2 * b4 * c2 - 2 * b2 * c4 + c6));
			case 568 -> a2 * (a6 * R - Q * (b4 + c4)
					- a4 * (3 * b4 + b2 * c2 + 3 * c4)
					+ a2 * (3 * b6 - 2 * b4 * c2 - 2 * b2 * c4 + 3 * c6));
			case 569 -> a2 * (a8 - 2 * b2 * c2 * Q - 3 * a6 * R
					+ a4 * (3 * b4 + 2 * b2 * c2 + 3 * c4)
					- a2 * (b6 - 3 * b4 * c2 - 3 * b2 * c4 + c6));
			case 570 -> a2 * (a4 * R + Q * R - 2 * a2 * (b4 + b2 * c2 + c4));
			case 571 -> a4 * (a4 + b4 + c4 - 2 * a2 * R);
			case 572 -> a2 * (a3 - b * c * (b + c) - a * (b2 - b * c + c2));
			case 573 -> a2 * (-b3 - a * b * c - c3 + a2 * (b + c));
			case 574 -> a2 * (a2 - 2 * R);
			case 575 -> a2 * (2 * a4 + b4 - 4 * b2 * c2 + c4 - 3 * a2 * R);
			case 576 -> a2 * (a4 - 3 * a2 * R + 2 * (b4 - b2 * c2 + c4));
			case 577 -> a4 * (T * T);
			case 578 -> a2 * (a8 - 2 * b2 * c2 * Q - 3 * a6 * R - a2 * Q * R
					+ a4 * (3 * b4 + 4 * b2 * c2 + 3 * c4));
			case 579 -> a2 * (-b3 + a * b * c - c3 + a2 * (b + c));
			case 580 -> a2
					* (a5 - a2 * b * c * (b + c) + b * p(b - c, 2) * c * (b + c)
					- a3 * (2 * b2 + b * c + 2 * c2)
					+ a * (b4 + b3 * c + b * c3 + c4));
			case 581 -> a2 * (b5 + a3 * b * c - b3 * c2 - b2 * c3 + c5 + a4 * (b + c)
					- a * b * c * p(b + c, 2)
					- a2 * (2 * b3 + b2 * c + b * c2 + 2 * c3));
			case 582 -> a2 * (a5 - a2 * b * c * (b + c)
					+ b * p(b - c, 2) * c * (b + c) - 2 * a3 * (b2 + b * c + c2)
					+ a * (b4 + 2 * b3 * c + 2 * b * c3 + c4));
			case 583 -> a2 * (-b3 + 2 * a * b * c - c3 + a2 * (b + c));
			case 584 -> a2 * (a3 - b * c * (b + c) - a * p(b + c, 2));
			case 585 -> -2 * a * b * (a - b - c) * c + (a * b + a * c - b * c) * S;
			case 586 -> 2 * a * b * (a - b - c) * c + (a * b + a * c - b * c) * S;
			case 587 -> U * V * (2 * (a + b + c) * (-T) - (-a + b + c) * S);
			case 588 -> a2 / (a2 + S);
			case 589 -> a2 / (a2 - S);
			case 590 -> a2 + S;
			case 591 -> -2 * a6 + 5 * a4 * R + Q * R - 4 * a2 * (b4 + c4)
					+ 2 * (Q - a2 * R) * S;
			case 592 -> a2
					* (a6 * (b2 + 2 * c2)
					+ b2 * c2 * (2 * b4 - 3 * b2 * c2 + c4)
					- a4 * (2 * b4 + 3 * b2 * c2 + 3 * c4)
					+ a2 * (b6 - 3 * b4 * c2 - 6 * b2 * c4 + c6))
					* (a6 * (2 * b2 + c2)
					+ b2 * c2 * (b4 - 3 * b2 * c2 + 2 * c4)
					- a4 * (3 * b4 + 3 * b2 * c2 + 2 * c4)
					+ a2 * (b6 - 6 * b4 * c2 - 3 * b2 * c4 + c6));
			case 593 -> a2 * p(a + b, 2) * p(a + c, 2);
			case 594 -> p(b + c, 2);
			case 595 -> a2 * (a2 - b * c + a * (b + c));
			case 596 -> -((a * (b - c) + b * (b + c)) * (a * (b - c) - c * (b + c)));
			case 597 -> 4 * a2 + b2 + c2;
			case 598 -> (2 * a2 + 2 * b2 - c2) * (2 * a2 - b2 + 2 * c2);
			case 599 -> -a2 + 2 * R;
			default -> Double.NaN;
		};
	}

	private double weight600to699(int k, double a, double b, double c) {

		return switch (k) {
			case 600 -> a2 * (b * c + S) * (a * b * c * (b + c - a) + (b2 + c2 - a2) * S);
			case 601 -> a3 * (a4 + b4 - 2 * b3 * c - 2 * b2 * c2 - 2 * b * c3 + c4
					- 2 * a2 * (b2 - b * c + c2));
			case 602 -> a3 * (a4 + b4 + 2 * b3 * c - 2 * b2 * c2 + 2 * b * c3 + c4
					- 2 * a2 * (b2 + b * c + c2));
			case 603 -> a3 * (a + b - c) * (a - b + c) * T;
			case 604 -> a3 * (a + b - c) * (a - b + c);
			case 605 -> 2 * a3 * b * c + a3 * S;
			case 606 -> -2 * a3 * b * c + a3 * S;
			case 607 -> a2 * (a - b - c) * U * V;
			case 608 -> a2 * (a + b - c) * (a - b + c) * U * V;
			case 609 -> 2 * a4 + a2 * b * c;
			case 610 -> a * (3 * a4 - Q - 2 * a2 * R);
			case 611 -> a2 * (a4 + b4 - 2 * b3 * c - 2 * b2 * c2 - 2 * b * c3 + c4
					- 2 * a2 * (b2 + b * c + c2));
			case 612 -> a * (a2 + p(b + c, 2));
			case 613 -> a2 * (a4 + b4 + 2 * b3 * c - 2 * b2 * c2 + 2 * b * c3 + c4
					- 2 * a2 * (b2 - b * c + c2));
			case 614 -> a * (a2 + p(b - c, 2));
			case 615 -> -a2 + S;
			case 616 -> -5 * a4 + 4 * a2 * b2 + b4 + 4 * a2 * c2 - 2 * b2 * c2 + c4
					- u(3) * T * S;
			case 617 -> 5 * a4 - 4 * a2 * b2 - b4 - 4 * a2 * c2 + 2 * b2 * c2 - c4
					- u(3) * T * S;
			case 618 -> -4 * a4 + 5 * a2 * b2 - b4 + 5 * a2 * c2 + 2 * b2 * c2 - c4
					+ u(3) * R * S;
			case 619 -> 4 * a4 - 5 * a2 * b2 + b4 - 5 * a2 * c2 - 2 * b2 * c2 + c4
					+ u(3) * R * S;
			case 620 -> 2 * a4 + b4 + c4 - 2 * a2 * R;
			case 621 -> 3 * U * V - u(3) * T * S;
			case 622 -> -3 * U * V - u(3) * T * S;
			case 623 -> 3 * (a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4)
					+ u(3) * R * S;
			case 624 -> -3 * (a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4)
					+ u(3) * R * S;
			case 625 -> -(a2 * R) + 2 * (b4 - b2 * c2 + c4);
			case 626 -> b4 + c4;
			case 627 -> -3 * a4 + 4 * a2 * b2 - b4 + 4 * a2 * c2 + 2 * b2 * c2 - c4
					- u(3) * T * S;
			case 628 -> 3 * a4 - 4 * a2 * b2 + b4 - 4 * a2 * c2 - 2 * b2 * c2 + c4
					- u(3) * T * S;
			case 629 -> -4 * a4 + 7 * a2 * b2 - 3 * b4 + 7 * a2 * c2 + 6 * b2 * c2
					- 3 * c4 + u(3) * R * S;
			case 630 -> 4 * a4 - 7 * a2 * b2 + 3 * b4 - 7 * a2 * c2 - 6 * b2 * c2
					+ 3 * c4 + u(3) * R * S;
			case 631 -> 3 * a4 + Q - 4 * a2 * R;
			case 632 -> 4 * a4 + 3 * Q - 7 * a2 * R;
			case 633 -> U * V - u(3) * T * S;
			case 634 -> -(U * V) - u(3) * T * S;
			case 635 -> a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4 + u(3) * R * S;
			case 636 -> -(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4 + u(3) * R * S;
			case 637 -> U * V + (-T) * S;
			case 638 -> -(U * V) + (-T) * S;
			case 639 -> a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4 + R * S;
			case 640 -> -(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4 + R * S;
			case 641 -> -2 * a4 + 3 * a2 * b2 - b4 + 3 * a2 * c2 + 2 * b2 * c2 - c4
					+ R * S;
			case 642 -> 2 * a4 - 3 * a2 * b2 + b4 - 3 * a2 * c2 - 2 * b2 * c2 + c4
					+ R * S;
			case 643 -> a * (a - b) * (a + b) * (a - c) * (a - b - c) * (a + c);
			case 644 -> a * (a - b) * (a - c) * (a - b - c);
			case 645 -> (a - b) * (a + b) * (a - c) * (a - b - c) * (a + c);
			case 646 -> -((a - b) * b * (a - c) * (a - b - c) * c);
			case 647 -> a2 * (b2 - c2) * T;
			case 648 -> (a - b) * (a + b) * (a - c) * (a + c) * U * V;
			case 649 -> a2 * (b - c);
			case 650 -> a * (a - b - c) * (b - c);
			case 651 -> a * (a - b) * (a - c) * (a + b - c) * (a - b + c);
			case 652 -> a2 * (a - b - c) * (b - c) * T;
			case 653 -> (a - b) * (a - c) * (a + b - c) * (a - b + c) * U * V;
			case 654 -> a2 * (a - b - c) * (b - c) * (a2 - b2 + b * c - c2);
			case 655 -> (a - b) * (a - c) * (a + b - c) * (a - b + c)
					* (a2 - a * b + b2 - c2) * (a2 - b2 - a * c + c2);
			case 656 -> a * (b2 - c2) * T;
			case 657 -> a2 * (b - c) * p(-a + b + c, 2);
			case 658 -> (a - b) * (a - c) * p(a + b - c, 2) * p(a - b + c, 2);
			case 659 -> a * (b - c) * (a2 - b * c);
			case 660 -> a * (a - b) * (a - c) * (-b2 + a * c) * (a * b - c2);
			case 661 -> -(a * (b2 - c2));
			case 662 -> a * (a - b) * (a + b) * (a - c) * (a + c);
			case 663 -> a2 * (a - b - c) * (b - c);
			case 664 -> (a - b) * (a - c) * (a + b - c) * (a - b + c);
			case 665 -> a2 * (b - c) * (-b2 - c2 + a * (b + c));
			case 666 -> (a - b) * (a - c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 667 -> a3 * (b - c);
			case 668 -> (a - b) * b * (a - c) * c;
			case 669 -> a4 * (b2 - c2);
			case 670 -> (a - b) * b2 * (a + b) * (a - c) * c2 * (a + c);
			case 671 -> -((a2 + b2 - 2 * c2) * (a2 - 2 * b2 + c2));
			case 672 -> a2 * (-b2 - c2 + a * (b + c));
			case 673 -> (a2 + b * (b - c) - a * c) * (a2 - a * b + c * (-b + c));
			case 674 -> a2 * (-b3 - c3 + a * R);
			case 675 -> (a3 + b2 * (b - c) - a2 * c) * (a3 - a2 * b + c2 * (-b + c));
			case 676 -> -((b - c)
					* (-2 * a3 + a2 * (b + c) + p(b - c, 2) * (b + c)));
			case 677 -> a2 * (a - b) * (a - c)
					* (a3 - 2 * b3 - a2 * c + b2 * c + c3 + a * (b2 - c2))
					* (a3 - a2 * b + b3 + b * c2 - 2 * c3 + a * (-b2 + c2));
			case 678 -> a * p(-2 * a + b + c, 2);
			case 679 -> a * p(a + b - 2 * c, 2) * p(a - 2 * b + c, 2);
			case 680 -> a3 * (b - c) * (T * T)
					* (a4 * (b2 + b * c + c2) + Q * (b2 + b * c + c2)
					- 2 * a2 * (b4 + b3 * c + b * c3 + c4));
			case 681 -> 1 / (a * (b - c) * (T * T)
					* (a4 * (b2 + b * c + c2) + Q * (b2 + b * c + c2)
					- 2 * a2 * (b4 + b3 * c + b * c3 + c4)));
			case 682 -> a4 * T * (Q + a2 * R);
			case 683 -> b2 * c2 * (-V) * U * (a4 + a2 * (-2 * b2 + c2) + b2 * R)
					* (a4 + a2 * (b2 - 2 * c2) + c2 * R);
			case 684 -> a2 * (-b8 + c8 + a4 * (-b4 + c4) + 2 * a2 * (b6 - c6));
			case 685 -> (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4);
			case 686 -> a2 * (b2 - c2) * T
					* (a4 * R + Q * R - 2 * a2 * (b4 - b2 * c2 + c4));
			case 687 -> (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (a6 - a4 * (2 * b2 + c2) + p(-(b2 * c) + c3, 2)
					+ a2 * (b4 + 2 * b2 * c2 - c4))
					* (a6 - a4 * (b2 + 2 * c2) + p(b3 - b * c2, 2)
					+ a2 * (-b4 + 2 * b2 * c2 + c4));
			case 688 -> a4 * (b4 - c4);
			case 689 -> (a - b) * b2 * (a + b) * (a2 + b2) * (a - c) * c2 * (a + c)
					* (a2 + c2);
			case 690 -> -((b2 - c2) * (-2 * a2 + b2 + c2));
			case 691 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a2 + b2 - 2 * c2) * (a2 - 2 * b2 + c2);
			case 692 -> a3 * (a - b) * (a - c);
			case 693 -> b * (b - c) * c;
			case 694 -> -(a6 * b2 * c2) - a2 * b4 * c4 + a4 * (b6 + c6);
			case 695 -> a2 * (b4 + a2 * c2) * (a2 * b2 + c4);
			case 696 -> -(b * c * (b3 + c3)) + a * (b4 + c4);
			case 697 -> a2 * (a * b4 + a4 * (b - c) - b4 * c)
					* (a4 * (b - c) - a * c4 + b * c4);
			case 698 -> -(b2 * c2 * R) + a2 * (b4 + c4);
			case 699 -> a2 * (a2 * b4 - b4 * c2 + a4 * (b2 - c2))
					* (-(a2 * c4) + b2 * c4 + a4 * (b2 - c2));
			default -> Double.NaN;
		};
	}

	private double weight700to799(int k, double a, double b, double c) {

		return switch (k) {
			case 700 -> -(b3 * c3 * (b + c)) + a3 * (b4 + c4);
			case 701 -> a2 * (a3 * b4 - b4 * c3 + a4 * (b3 - c3))
					* (-(a3 * c4) + b3 * c4 + a4 * (b3 - c3));
			case 702 -> -2 * b4 * c4 + a4 * (b4 + c4);
			case 703 -> a2 * (b4 * c4 + a4 * (b4 - 2 * c4))
					* (-(b4 * c4) + a4 * (2 * b4 - c4));
			case 704 -> -(b4 * c4 * (b + c)) + a5 * (b4 + c4);
			case 705 -> a2 * (a5 * b4 - b4 * c5 + a4 * (b5 - c5))
					* (a5 * c4 - b5 * c4 + a4 * (-b5 + c5));
			case 706 -> -(b4 * c4 * R) + a6 * (b4 + c4);
			case 707 -> a2 * (a6 * b4 - b4 * c6 + a4 * (b6 - c6))
					* (a6 * c4 - b6 * c4 + a4 * (-b6 + c6));
			case 708 -> -(b4 * c4 * (b3 + c3)) + a7 * (b4 + c4);
			case 709 -> a2 * (a7 * b4 - b4 * c7 + a4 * (b7 - c7))
					* (a7 * c4 - b7 * c4 + a4 * (-b7 + c7));
			case 710 -> (b4 + c4) * (a8 - b4 * c4);
			case 711 -> a2 * (a4 + b4) * (-b2 + a * c) * (b2 + a * c) * (a * b - c2)
					* (a * b + c2) * (b4 + a2 * c2) * (a4 + c4)
					* (a2 * b2 + c4);
			case 712 -> -(b * c * R) + a * (b3 + c3);
			case 713 -> a2 * (a * b3 + a3 * (b - c) - b3 * c)
					* (a3 * (b - c) - a * c3 + b * c3);
			case 714 -> -(b2 * c2 * (b + c)) + a2 * (b3 + c3);
			case 715 -> a2 * (a + b) * (a + c)
					* (a * b * c2 - b2 * c2 + a2 * (b2 - c2))
					* (-(a * b2 * c) + b2 * c2 + a2 * (b2 - c2));
			case 716 -> -2 * b3 * c3 + a3 * (b3 + c3);
			case 717 -> a2 * (b3 * c3 + a3 * (b3 - 2 * c3))
					* (-(b3 * c3) + a3 * (2 * b3 - c3));
			case 718 -> -(b3 * c3 * (b + c)) + a4 * (b3 + c3);
			case 719 -> a2 * (a + b) * (a + c)
					* (-(a2 * b4) + a * b4 * c - b4 * c2 + a3 * c3)
					* (a3 * b3 - a2 * c4 + a * b * c4 - b2 * c4);
			case 720 -> -(b3 * c3 * R) + a5 * (b3 + c3);
			case 721 -> a2 * (a5 * b3 - b3 * c5 + a3 * (b5 - c5))
					* (a5 * c3 - b5 * c3 + a3 * (-b5 + c5));
			case 722 -> (b3 + c3) * (a6 - b3 * c3);
			case 723 -> a2 * (a + b) * (a2 - a * b + b2) * (a + c) * (-b2 + a * c)
					* (a * b - c2) * (a2 - a * c + c2)
					* (b4 + a * b2 * c + a2 * c2) * (a2 * b2 + a * b * c2 + c4);
			case 724 -> a7 * (b3 + c3) - b3 * c3 * (b4 + c4);
			case 725 -> a2 * (a7 * b3 - b3 * c7 + a3 * (b7 - c7))
					* (a7 * c3 - b7 * c3 + a3 * (-b7 + c7));
			case 726 -> -(b * c * (b + c)) + a * R;
			case 727 -> a2 * (a * b2 + a2 * (b - c) - b2 * c)
					* (a2 * (b - c) - a * c2 + b * c2);
			case 728 -> a * p(a - b - c, 3);
			case 729 -> a2 * (b2 * c2 + a2 * (b2 - 2 * c2))
					* (-(b2 * c2) + a2 * (2 * b2 - c2));
			case 730 -> -(b2 * c2 * (b + c)) + a3 * R;
			case 731 -> a2 * (a3 * b2 - b2 * c3 + a2 * (b3 - c3))
					* (a3 * c2 - b3 * c2 + a2 * (-b3 + c3));
			case 732 -> (a2 - b * c) * (a2 + b * c) * R;
			case 733 -> a2 * (a2 + b2) * (-b2 + a * c) * (b2 + a * c) * (a * b - c2)
					* (a2 + c2) * (a * b + c2);
			case 734 -> a5 * R - b2 * c2 * (b3 + c3);
			case 735 -> a2 * (a5 * b2 - b2 * c5 + a2 * (b5 - c5))
					* (a5 * c2 - b5 * c2 + a2 * (-b5 + c5));
			case 736 -> a6 * R - b2 * c2 * (b4 + c4);
			case 737 -> a2 * (a6 * b2 - b2 * c6 + a2 * (b6 - c6))
					* (a6 * c2 - b6 * c2 + a2 * (-b6 + c6));
			case 738 -> a * p(a + b - c, 3) * p(a - b + c, 3);
			case 739 -> a2 * (2 * a * b - a * c - b * c) * (a * (b - 2 * c) + b * c);
			case 740 -> (b + c) * (-a2 + b * c);
			case 741 -> a2 / ((b + c) * (-a2 + b * c));
			case 742 -> a3 * (b + c) - b * c * R;
			case 743 -> a2 * (a3 * b - b * c3 + a * (b3 - c3))
					* (a3 * c - b3 * c + a * (-b3 + c3));
			case 744 -> a4 * (b + c) - b * c * (b3 + c3);
			case 745 -> a2 * (a + b) * (a + c) * (a3 * b - a2 * b2 + a * b3 - c4)
					* (-b4 + a * c * (a2 - a * c + c2));
			case 746 -> a5 * (b + c) - b * c * (b4 + c4);
			case 747 -> a2 / (a5 * (b + c) - b * c * (b4 + c4));
			case 748 -> a3 - 2 * a * b * c;
			case 749 -> -(a * (-b2 + 2 * a * c) * (2 * a * b - c2));
			case 750 -> a3 + 2 * a * b * c;
			case 751 -> a * (b2 + 2 * a * c) * (2 * a * b + c2);
			case 752 -> 2 * a3 - b3 - c3;
			case 753 -> a2 * (a3 + b3 - 2 * c3) * (a3 - 2 * b3 + c3);
			case 754 -> 2 * a4 - b4 - c4;
			case 755 -> a2 * (a4 + b4 - 2 * c4) * (a4 - 2 * b4 + c4);
			case 756 -> a * p(b + c, 2);
			case 757 -> a * p(a + b, 2) * p(a + c, 2);
			case 758 -> a * (b + c) * (a2 - b2 + b * c - c2);
			case 759 -> a * (a + b) * (a + c) * (a2 - a * b + b2 - c2)
					* (a2 - b2 - a * c + c2);
			case 760 -> a * (-b4 - c4 + a3 * (b + c));
			case 761 -> a * (a4 + b4 - a * c3 - b * c3)
					* (a4 - a * b3 - b3 * c + c4);
			case 762 -> a * p(b + c, 3);
			case 763 -> a * p(a + b, 3) * p(a + c, 3);
			case 764 -> a * p(b - c, 3);
			case 765 -> a * p(a - b, 2) * p(a - c, 2);
			case 766 -> a3 * (-b4 - c4 + a * (b3 + c3));
			case 767 -> b * c * (a4 + b3 * (b - c) - a3 * c)
					* (-a4 + a3 * b + (b - c) * c3);
			case 768 -> a * b4 + b4 * c - a * c4 - b * c4;
			case 769 -> a2 * (a - b) * (a - c)
					* (b3 * c + a3 * (b + c) + a2 * b * (b + c)
					+ a * b2 * (b + c))
					* (b * c3 + a3 * (b + c) + a2 * c * (b + c)
					+ a * c2 * (b + c));
			case 770 -> a * (a - b - c) * (b - c) * (-2 * a2 * Q
					+ Q * (b2 - b * c + c2) + a4 * (b2 + b * c + c2));
			case 771 -> a * (a - b) * (a - c) * (a + b - c) * (a - b + c)
					* (a6 - a5 * c + 2 * a3 * c3 - a4 * (2 * b2 + c2)
					+ p(-(b2 * c) + c3, 2) + a * c * (b4 - c4)
					+ a2 * (b4 + 4 * b2 * c2 - c4))
					* (a6 - a5 * b + 2 * a3 * b3 - a4 * (b2 + 2 * c2)
					+ p(b3 - b * c2, 2) + a2 * (-b4 + 4 * b2 * c2 + c4)
					+ a * (-b5 + b * c4));
			case 772 -> b3 * (b - c) * c3 + a3 * (b4 - c4);
			case 773 -> a2 * (a - b) * (a - c)
					* (a2 * b3 * c + a * b3 * c2 + b3 * c3 + a3 * (b3 + c3))
					* (a2 * b * c3 + a * b2 * c3 + b3 * c3 + a3 * (b3 + c3));
			case 774 -> a * (-2 * a2 * Q + a4 * R + Q * R);
			case 775 -> a * (a6 - a4 * (2 * b2 + c2) + p(-(b2 * c) + c3, 2)
					+ a2 * (b4 + 4 * b2 * c2 - c4))
					* (a6 - a4 * (b2 + 2 * c2) + p(b3 - b * c2, 2)
					+ a2 * (-b4 + 4 * b2 * c2 + c4));
			case 776 -> b4 * c4 * (-b + c) + a5 * (b4 - c4);
			case 777 -> a2 * (a - b) * (a - c)
					* (-(a3 * b5) - a2 * b5 * c - a * b5 * c2 - b5 * c3
					+ a4 * c4)
					* (a4 * b4 - a3 * c5 - a2 * b * c5 - a * b2 * c5 - b3 * c5);
			case 778 -> -(b6 * c4) + b4 * c6 + a6 * (b4 - c4);
			case 779 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (-(a2 * b6) - b6 * c2 + a4 * c4)
					* (a4 * b4 - a2 * c6 - b2 * c6);
			case 780 -> -(b7 * c4) + b4 * c7 + a7 * (b4 - c4);
			case 781 -> a2 * (a - b) * (a - c)
					* (-(a3 * b7) - a2 * b7 * c - a * b7 * c2 - b7 * c3
					+ a6 * c4 + a5 * c5 + a4 * c6)
					* (a6 * b4 + a5 * b5 + a4 * b6 - a3 * c7 - a2 * b * c7
					- a * b2 * c7 - b3 * c7);
			case 782 -> (b4 - c4) * (a8 - b4 * c4);
			case 783 -> a2 * (a - b) * (a + b) * (a2 + b2) * (a - c) * (a + c)
					* (-b2 + a * c) * (b2 + a * c) * (a * b - c2) * (a2 + c2)
					* (a * b + c2) * (b4 + a2 * c2) * (a2 * b2 + c4);
			case 784 -> a * b3 + b3 * c - a * c3 - b * c3;
			case 785 -> a2 * (a - b) * (a - c)
					* (b2 * c + a2 * (b + c) + a * b * (b + c))
					* (b * c2 + a2 * (b + c) + a * c * (b + c));
			case 786 -> b2 * (b - c) * c2 + a2 * (b3 - c3);
			case 787 -> a2 * (a - b) * (a - c) * (a * b2 * c + b2 * c2 + a2 * R)
					* (a * b * c2 + b2 * c2 + a2 * R);
			case 788 -> a3 * (b3 - c3);
			case 789 -> b * (a3 - b3) * c * (a3 - c3);
			case 790 -> b3 * c3 * (-b + c) + a4 * (b3 - c3);
			case 791 -> a2 * (a - b) * (a - c)
					* (-(a2 * b4) - a * b4 * c - b4 * c2 + a3 * c3)
					* (a3 * b3 - a2 * c4 - a * b * c4 - b2 * c4);
			case 792 -> -(b5 * c3) + b3 * c5 + a5 * (b3 - c3);
			case 793 -> a2 * (a - b) * (a - c)
					* (-(a2 * b5) - a * b5 * c - b5 * c2 + a4 * c3 + a3 * c4)
					* (a4 * b3 + a3 * b4 - a2 * c5 - a * b * c5 - b2 * c5);
			case 794 -> (b3 - c3) * (a6 - b3 * c3);
			case 795 -> a2 * (a - b) * (a2 + a * b + b2) * (a - c) * (-b2 + a * c)
					* (a * b - c2) * (a2 + a * c + c2)
					* (b4 + a * b2 * c + a2 * c2) * (a2 * b2 + a * b * c2 + c4);
			case 796 -> -(b7 * c3) + b3 * c7 + a7 * (b3 - c3);
			case 797 -> a2 * (a - b) * (a - c)
					* (-(a2 * b7) - a * b7 * c - b7 * c2 + a6 * c3 + a5 * c4
					+ a4 * c5 + a3 * c6)
					* (a6 * b3 + a5 * b4 + a4 * b5 + a3 * b6 - a2 * c7
					- a * b * c7 - b2 * c7);
			case 798 -> a3 * (b2 - c2);
			case 799 -> (a - b) * b * (a + b) * (a - c) * c * (a + c);
			default -> Double.NaN;
		};
	}

	private double weight800to899(int k, double a, double b, double c) {

		return switch (k) {
			case 800 -> a2 * (-2 * a2 * Q + a4 * R + Q * R);
			case 801 -> (a6 - a4 * (2 * b2 + c2) + p(-(b2 * c) + c3, 2)
					+ a2 * (b4 + 4 * b2 * c2 - c4))
					* (a6 - a4 * (b2 + 2 * c2) + p(b3 - b * c2, 2)
					+ a2 * (-b4 + 4 * b2 * c2 + c4));
			case 802 -> (b - c) * (-(b2 * c2) + a3 * (b + c));
			case 803 -> a2 * (a - b) * (a - c) * (-(a * b3) - b3 * c + a2 * c2)
					* (a2 * b2 - a * c3 - b * c3);
			case 804 -> -((b2 - c2) * (-a2 + b * c) * (a2 + b * c));
			case 805 -> a2 * (a - b) * (a + b) * (a - c) * (a + c) * (-b2 + a * c)
					* (b2 + a * c) * (a * b - c2) * (a * b + c2);
			case 806 -> -(b5 * c2) + b2 * c5 + a5 * (b2 - c2);
			case 807 -> a2 * (a - b) * (a - c)
					* (-(a * b5) - b5 * c + a4 * c2 + a3 * c3 + a2 * c4)
					* (a4 * b2 + a3 * b3 + a2 * b4 - a * c5 - b * c5);
			case 808 -> -((b2 - c2) * (-a6 + b2 * c2 * R));
			case 809 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a4 * b2 + a2 * b4 - c6) * (-b6 + a2 * c2 * (a2 + c2));
			case 810 -> a3 * (b2 - c2) * T;
			case 811 -> (a - b) * b * (a + b) * (a - c) * c * (a + c) * U * V;
			case 812 -> (b - c) * (a2 - b * c);
			case 813 -> a2 * (a - b) * (a - c) * (-b2 + a * c) * (a * b - c2);
			case 814 -> (b - c) * (a3 - b * c * (b + c));
			case 815 -> a2 * (a - b) * (a - c) * (a2 * b + a * b2 - c3)
					* (-b3 + a * c * (a + c));
			case 816 -> a4 * (b - c) - b4 * c + b * c4;
			case 817 -> a2 * (a - b) * (a - c) * (a3 * b + a2 * b2 + a * b3 - c4)
					* (-b4 + a * c * (a2 + a * c + c2));
			case 818 -> a5 * (b - c) - b5 * c + b * c5;
			case 819 -> a2 * (a - b) * (a - c)
					* (a4 * b + a3 * b2 + a2 * b3 + a * b4 - c5)
					* (-b5 + a * c * (a3 + a2 * c + a * c2 + c3));
			case 820 -> a3 * (T * T) * (-2 * a2 * Q + a4 * R + Q * R);
			case 821 -> b * c * p(a4 - Q, 2)
					* (a6 - a4 * (2 * b2 + c2) + p(-(b2 * c) + c3, 2)
					+ a2 * (b4 + 4 * b2 * c2 - c4))
					* (a6 - a4 * (b2 + 2 * c2) + p(b3 - b * c2, 2)
					+ a2 * (-b4 + 4 * b2 * c2 + c4));
			case 822 -> a3 * (b2 - c2) * (T * T);
			case 823 -> (a - b) * b * (a + b) * (a - c) * c * (a + c) * p(a4 - Q, 2);
			case 824 -> b3 - c3;
			case 825 -> a2 * (a3 - b3) * (a3 - c3);
			case 826 -> b4 - c4;
			case 827 -> a2 * (a4 - b4) * (a4 - c4);
			case 828 -> a3 * (b + c) * (T * T)
					* (a4 * (b2 - b * c + c2) + Q * (b2 - b * c + c2)
					- 2 * a2 * p(b - c, 2) * (b2 + b * c + c2));
			case 829 -> b * (a + b) * c * (a + c) * p(a4 - Q, 2)
					* (a6 - a5 * c - a * c * Q + 2 * a3 * c * R
					- a4 * (2 * b2 + c2) + p(-(b2 * c) + c3, 2)
					+ a2 * (b4 - c4))
					* (a6 - a5 * b - a * b * Q + 2 * a3 * b * R
					- a4 * (b2 + 2 * c2) + p(b3 - b * c2, 2)
					+ a2 * (-b4 + c4));
			case 830 -> a * (b - c) * (a2 + b2 + b * c + c2);
			case 831 -> a * (a - b) * (a - c) * (a2 + a * b + b2 + c2)
					* (a2 + b2 + a * c + c2);
			case 832 -> a * (b4 + a3 * (b - c) - c4);
			case 833 -> a * (a - b) * (a - c) * (a3 + a2 * b + a * b2 + b3 + c3)
					* (a3 + b3 + a2 * c + a * c2 + c3);
			case 834 -> a2 * (b - c) * (b2 + b * c + c2 + a * (b + c));
			case 835 -> (a - b) * (a - c) * (a2 + a * (b + c) + b * (b + c))
					* (a2 + a * (b + c) + c * (b + c));
			case 836 -> a2 * (b + c) * (T * T) * (a4 - 2 * a2 * p(b - c, 2) + Q);
			case 837 -> (a + b) * (a + c) * p(a4 - Q, 2)
					* (a4 + 4 * a * b2 * c + Q - 2 * a2 * R)
					* (a4 + 4 * a * b * c2 + Q - 2 * a2 * R);
			case 838 -> a3 * (b4 - c4 + a * (b3 - c3));
			case 839 -> (a - b) * b * (a - c) * c
					* (a3 + a2 * (b + c) + a * b * (b + c) + b2 * (b + c))
					* (a3 + a2 * (b + c) + a * c * (b + c) + c2 * (b + c));
			case 840 -> a2
					* (a3 + b3 - b2 * c + 2 * b * c2 - 2 * c3 - a2 * (b + c)
					- a * (b2 - 2 * c2))
					* (a3 - 2 * b3 + 2 * b2 * c - b * c2 + c3 - a2 * (b + c)
					+ a * (2 * b2 - c2));
			case 841 -> a2
					* (a10 - 3 * a8 * (b2 - c2)
					+ a6 * (2 * b4 + 8 * b2 * c2 - 13 * c4)
					+ p(b2 - c2, 3) * (b4 + 6 * b2 * c2 + 2 * c4)
					+ a4 * (2 * b6 - 22 * b4 * c2 + 11 * b2 * c4
					+ 11 * c6)
					+ a2 * (-3 * b8 + 8 * b6 * c2 + 11 * b4 * c4
					- 16 * b2 * c6))
					* (a10 + 3 * a8 * (b2 - c2)
					- p(b2 - c2, 3) * (2 * b4 + 6 * b2 * c2 + c4)
					+ a6 * (-13 * b4 + 8 * b2 * c2 + 2 * c4)
					+ a4 * (11 * b6 + 11 * b4 * c2 - 22 * b2 * c4
					+ 2 * c6)
					+ a2 * (-16 * b6 * c2 + 11 * b4 * c4 + 8 * b2 * c6
					- 3 * c8));
			case 842 -> a2
					* (a6 + b6 - b4 * c2 + 2 * b2 * c4 - 2 * c6 - a4 * R
					- a2 * (b4 - 2 * c4))
					* (a6 - 2 * b6 + 2 * b4 * c2 - b2 * c4 + c6 - a4 * R
					+ a2 * (2 * b4 - c4));
			case 843 -> a2
					* (a4 - 2 * b4 + 2 * b2 * c2 + c4 + 2 * a2 * (b2 - 2 * c2))
					* (a4 + b4 + 2 * b2 * c2 - 2 * c4
					+ a2 * (-4 * b2 + 2 * c2));
			case 844 -> a * (-(b * p(a + b - c, 2) * c * p(a - b + c, 2)
					* u(a * (-a + b + c)))
					+ a * p(a + b - c, 2) * c * p(-a + b + c, 2)
					* u(b * (a - b + c))
					+ a * b * p(a - b + c, 2) * p(-a + b + c, 2)
					* u(c * (a + b - c)));
			case 845 -> a * (-(p(a + b - c, 2) * p(a - b + c, 2)
					* u(a * (-a + b + c)))
					+ p(a + b - c, 2) * p(-a + b + c, 2) * u(b * (a - b + c))
					+ p(a - b + c, 2) * p(-a + b + c, 2) * u(c * (a + b - c)));
			case 846 -> a * (a2 - b2 - b * c - c2 - a * (b + c));
			case 847 -> b2 * c2 * (-V) * U * (a4 - 2 * a2 * b2 + Q)
					* (a4 - 2 * a2 * c2 + Q);
			case 848 -> Math.sin((2 * a * Math.PI) / (a + b + c))
					* ((a4 + Q - 2 * a2 * R)
					* Math.cos((2 * b * Math.PI) / (a + b + c))
					+ V * S * Math.sin((2 * b * Math.PI) / (a + b + c)))
					* ((a4 + Q - 2 * a2 * R)
					* Math.cos((2 * c * Math.PI) / (a + b + c))
					+ U * S * Math
					.sin((2 * c * Math.PI) / (a + b + c)));
			case 849 -> a3 * p(a + b, 2) * p(a + c, 2);
			case 850 -> b2 * (b - c) * c2 * (b + c);
			case 851 -> a * (b + c)
					* (a4 - b * p(b - c, 2) * c - a2 * (b2 - b * c + c2));
			case 852 -> a2 * T * (-2 * b2 * c2 * Q + a6 * R + a2 * Q * R
					- 2 * a4 * (b4 - b2 * c2 + c4));
			case 853 -> a2 * (a - b - c)
					* (-(a4 * p(b - c, 2) * (b + c))
					- 2 * b2 * p(b - c, 2) * c2 * (b + c) + a5 * R
					- a3 * (b4 + c4)
					+ a2 * (b5 - b4 * c - b * c4 + c5));
			case 854 -> a2 * (a4 * p(b - c, 2) * (b + c)
					+ 2 * b2 * p(b - c, 2) * c2 * (b + c) + a5 * R
					- a3 * (b4 + c4) - a2 * (b5 - b4 * c - b * c4 + c5));
			case 855 -> a * (a4 * p(b - c, 2) + a5 * (b + c)
					- a * b * p(b - c, 2) * c * (b + c) + b * c * Q
					- a2 * p(b - c, 2) * (b2 + b * c + c2) - a3 * (b3 + c3));
			case 856 -> a * (b + c) * T * (a6 + a4 * (-2 * b2 + 3 * b * c - 2 * c2)
					- b * c * Q + a2 * p(b - c, 2) * R);
			case 857 -> (b + c)
					* (-a4 + a2 * b * c + p(b - c, 2) * (b2 + b * c + c2));
			case 858 -> 2 * a2 * b2 * c2 - a4 * R + Q * R;
			case 859 -> a2 * (a + b) * (a + c)
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 860 -> (b + c) * (-V) * U * (-a2 + b2 - b * c + c2);
			case 861 -> a * (a - b - c)
					* (-(a4 * p(b - c, 2)) + a5 * (b + c)
					- a * b * p(b - c, 2) * c * (b + c) - b * c * Q
					+ a2 * p(b - c, 2) * (b2 + b * c + c2)
					- a3 * (b3 + c3));
			case 862 -> a * (b + c) * (a2 - b * c) * U * V;
			case 863 -> a3 * (b2 * p(b - c, 2) * c2 * (b + c) + a4 * (b3 + c3)
					- a2 * (b5 + c5));
			case 864 -> a4 * (b2 * c2 * Q + a4 * (b4 + c4) - a2 * (b6 + c6));
			case 865 -> a2 * p(b - c, 2) * p(b + c, 2)
					* (a6 + 3 * a2 * b2 * c2 - a4 * R - b2 * c2 * R);
			case 866 -> a * p(b - c, 2)
					* (a5 + a2 * b * c * (b + c) + a * b * c * p(b + c, 2)
					- a3 * (b2 + b * c + c2)
					- b * c * (b3 + b2 * c + b * c2 + c3));
			case 867 -> p(b - c, 2) * (-a4 + b4 - a2 * b * c + b3 * c + b * c3 + c4
					+ a3 * (b + c) - a * (b3 + b2 * c + b * c2 + c3));
			case 868 -> p(b - c, 2) * p(b + c, 2) * (b4 + c4 - a2 * R);
			case 869 -> a3 * (b2 + b * c + c2);
			case 870 -> b * (a2 + a * b + b2) * c * (a2 + a * c + c2);
			case 871 -> b3 * (a2 + a * b + b2) * c3 * (a2 + a * c + c2);
			case 872 -> a3 * p(b + c, 2);
			case 873 -> b * p(a + b, 2) * c * p(a + c, 2);
			case 874 -> (a - b) * b * (a - c) * c * (a2 - b * c);
			case 875 -> a3 * (b - c) * (-b2 + a * c) * (a * b - c2);
			case 876 -> a * (b - c) * (-b2 + a * c) * (a * b - c2);
			case 877 -> (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (-b4 - c4 + a2 * R);
			case 878 -> a2 * (b2 - c2) * T * (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4);
			case 879 -> (b2 - c2) * (-T) * (a4 + b4 - a2 * c2 - b2 * c2)
					* (-a4 + a2 * b2 + b2 * c2 - c4);
			case 880 -> (a - b) * b2 * (a + b) * (a - c) * c2 * (a + c)
					* (a2 - b * c) * (a2 + b * c);
			case 881 -> a4 * (b2 - c2) * (-b2 + a * c) * (b2 + a * c) * (a * b - c2)
					* (a * b + c2);
			case 882 -> a2 * (b2 - c2) * (-b2 + a * c) * (b2 + a * c) * (a * b - c2)
					* (a * b + c2);
			case 883 -> (a - b) * (a - c) * (a + b - c) * (a - b + c)
					* (-b2 - c2 + a * (b + c));
			case 884 -> a2 * (a - b - c) * (b - c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 885 -> (a - b - c) * (b - c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 886 -> -((a - b) * b2 * (a + b) * (a - c) * c2 * (a + c)
					* (b2 * c2 + a2 * (b2 - 2 * c2))
					* (-(b2 * c2) + a2 * (2 * b2 - c2)));
			case 887 -> a4 * (b2 - c2) * (-2 * b2 * c2 + a2 * R);
			case 888 -> a2 * (b2 - c2) * (-2 * b2 * c2 + a2 * R);
			case 889 -> -((a - b) * b * (a - c) * c * (2 * a * b - a * c - b * c)
					* (a * (b - 2 * c) + b * c));
			case 890 -> a3 * (b - c) * (-2 * b * c + a * (b + c));
			case 891 -> a * (b - c) * (-2 * b * c + a * (b + c));
			case 892 -> (a - b) * (a + b) * (a - c) * (a + c) * (a2 + b2 - 2 * c2)
					* (a2 - 2 * b2 + c2);
			case 893 -> a2 * (b2 + a * c) * (a * b + c2);
			case 894 -> a2 + b * c;
			case 895 -> a2 * (a2 + b2 - 2 * c2) * T * (a2 - 2 * b2 + c2);
			case 896 -> a * (2 * a2 - b2 - c2);
			case 897 -> a * (a2 + b2 - 2 * c2) * (a2 - 2 * b2 + c2);
			case 898 -> a * (a - b) * (a - c) * (2 * a * b - a * c - b * c)
					* (a * (b - 2 * c) + b * c);
			case 899 -> a * (-2 * b * c + a * (b + c));
			default -> Double.NaN;
		};
	}

	private double weight900to999(int k, double a, double b, double c) {

		return switch (k) {
			case 900 -> (2 * a - b - c) * (b - c);
			case 901 -> a2 * (a - b) * (a + b - 2 * c) * (a - c) * (a - 2 * b + c);
			case 902 -> a2 * (2 * a - b - c);
			case 903 -> -((a + b - 2 * c) * (a - 2 * b + c));
			case 904 -> a3 * (b2 + a * c) * (a * b + c2);
			case 905 -> a * (b - c) * T;
			case 906 -> a3 * (a - b) * (a - c) * T;
			case 907 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a2 + 3 * b2 + c2) * (a2 + b2 + 3 * c2);
			case 908 -> 2 * a * b * c - a2 * (b + c) + p(b - c, 2) * (b + c);
			case 909 -> a2 * (a3 - a2 * b + b3 - a * p(b - c, 2) - b * c2)
					* (a3 - a * p(b - c, 2) - a2 * c - b2 * c + c3);
			case 910 -> a * (2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c));
			case 911 -> a3 * (a3 - 2 * b3 - a2 * c + b2 * c + c3 + a * (b2 - c2))
					* (a3 - a2 * b + b3 + b * c2 - 2 * c3 + a * (-b2 + c2));
			case 912 -> a * T
					* (a3 * (b + c) - a * p(b - c, 2) * (b + c) + Q - a2 * R);
			case 913 -> a2 * U * V
					* (a4 - a3 * c + b * p(b - c, 2) * (b + c) + a * c * R
					- a2 * (2 * b2 - b * c + c2))
					* (a4 - a3 * b + p(b - c, 2) * c * (b + c) + a * b * R
					- a2 * (b2 - b * c + 2 * c2));
			case 914 -> -(T
					* (a3 * (b + c) - a * p(b - c, 2) * (b + c) + Q - a2 * R));
			case 915 -> a * U * V
					* (a4 - a3 * c + b * p(b - c, 2) * (b + c) + a * c * R
					- a2 * (2 * b2 - b * c + c2))
					* (a4 - a3 * b + p(b - c, 2) * c * (b + c) + a * b * R
					- a2 * (b2 - b * c + 2 * c2));
			case 916 -> a2 * T * (b5 - b3 * c2 - b2 * c3 + c5 - a * Q + a3 * R
					- a2 * (b3 + c3));
			case 917 -> U * V
					* (a5 - a4 * b + p(b - c, 2) * c2 * (b + c) - a3 * R
					+ a2 * (b3 + 2 * b * c2 - c3))
					* (a5 - a4 * c + b2 * p(b - c, 2) * (b + c) - a3 * R
					+ a2 * (-b3 + 2 * b2 * c + c3));
			case 918 -> -((b - c) * (b2 + c2 - a * (b + c)));
			case 919 -> a2 * (a - b) * (a - c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 920 -> a * (a6 - 3 * a4 * R - Q * R
					+ a2 * (3 * b4 - 2 * b2 * c2 + 3 * c4));
			case 921 -> a * (a6 - p(b2 - c2, 3) - a4 * (3 * b2 + c2)
					+ a2 * (3 * b4 + 2 * b2 * c2 - c4))
					* (a6 + p(b2 - c2, 3) - a4 * (b2 + 3 * c2)
					+ a2 * (-b4 + 2 * b2 * c2 + 3 * c4));
			case 922 -> a3 * (2 * a2 - b2 - c2);
			case 923 -> a3 * (a2 + b2 - 2 * c2) * (a2 - 2 * b2 + c2);
			case 924 -> a2 * (b2 - c2) * (a4 + b4 + c4 - 2 * a2 * R);
			case 925 -> (a - b) * (a + b) * (a - c) * (a + c)
					* (a4 - 2 * a2 * b2 + Q) * (a4 - 2 * a2 * c2 + Q);
			case 926 -> a2 * (a - b - c) * (b - c) * (-b2 - c2 + a * (b + c));
			case 927 -> (a - b) * (a - c) * (a + b - c) * (a - b + c)
					* (a2 + b * (b - c) - a * c) * (a2 - a * b + c * (-b + c));
			case 928 -> a2 * (b - c) * (b4 + c4 + a3 * (b + c)
					- a * p(b - c, 2) * (b + c) - a2 * p(b + c, 2));
			case 929 -> (a - b) * (a - c)
					* (a4 - a3 * c + a2 * (b - c) * c + a * p(b - c, 2) * c
					+ b * p(b - c, 2) * (b + c))
					* (a4 - a3 * b + a * b * p(b - c, 2) + a2 * b * (-b + c)
					+ p(b - c, 2) * c * (b + c));
			case 930 -> (a - b) * (a + b) * (a - c) * (a + c)
					* (a4 + Q - a2 * (2 * b2 + c2))
					* (a4 + Q - a2 * (b2 + 2 * c2));
			case 931 -> a * (a - b) * (a + b) * (a - c) * (a + c)
					* (c * (b + c) + a * (2 * b + c))
					* (b * (b + c) + a * (b + 2 * c));
			case 932 -> a * (a - b) * (a - c) * (a * (b - c) - b * c)
					* (a * (b - c) + b * c);
			case 933 -> a2 * (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 934 -> a * (a - b) * (a - c) * p(a + b - c, 2) * p(a - b + c, 2);
			case 935 -> (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (a4 - a2 * b2 + b4 - c4) * (a4 - b4 - a2 * c2 + c4);
			case 936 -> a * (a3 - a * p(b - c, 2) - a2 * (b + c) + p(b + c, 3));
			case 937 -> a * (a3 + a2 * (3 * b - c) + p(b - c, 2) * (b + c)
					+ a * (3 * b2 + 2 * b * c - c2))
					* (a3 - a2 * (b - 3 * c) + p(b - c, 2) * (b + c)
					+ a * (-b2 + 2 * b * c + 3 * c2));
			case 938 -> -a4 + 4 * a2 * b * c + 2 * a3 * (b + c)
					- 2 * a * p(b - c, 2) * (b + c) + Q;
			case 939 -> a2
					* (a4 - 2 * a3 * b + 2 * a2 * (b - c) * c
					- p(b - c, 3) * (b + c) + 2 * a * b * p(b + c, 2))
					* (a4 - 2 * a2 * b * (b - c) - 2 * a3 * c
					+ p(b - c, 3) * (b + c) + 2 * a * c * p(b + c, 2));
			case 940 -> a * (a2 + 2 * b * c + a * (b + c));
			case 941 -> a * (c * (b + c) + a * (2 * b + c))
					* (b * (b + c) + a * (b + 2 * c));
			case 942 -> a * (2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 943 -> a * (a3 - a2 * b + b3 - b * c2 - a * p(b + c, 2))
					* (a3 - a2 * c - b2 * c + c3 - a * p(b + c, 2));
			case 944 -> 3 * a4 - 2 * a2 * p(b - c, 2) - 2 * a3 * (b + c)
					+ 2 * a * p(b - c, 2) * (b + c) - Q;
			case 945 -> a2
					* (a4 - 2 * a3 * b - 3 * b4 + 2 * a * b * p(b - c, 2)
					+ 2 * b3 * c + 2 * b2 * c2 - 2 * b * c3 + c4
					+ 2 * a2 * (b2 + b * c - c2))
					* (a4 + b4 - 2 * a3 * c - 2 * b3 * c
					+ 2 * a * p(b - c, 2) * c + 2 * b2 * c2 + 2 * b * c3
					- 3 * c4 + 2 * a2 * (-b2 + b * c + c2));
			case 946 -> -(a2 * p(b - c, 2)) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) + Q;
			case 947 -> a2
					* (a4 + a3 * c - a * p(b - c, 2) * c
					+ b * (b - c) * p(b + c, 2)
					- a2 * (2 * b2 + b * c + c2))
					* (a4 + a3 * b - a * b * p(b - c, 2)
					- (b - c) * c * p(b + c, 2)
					- a2 * (b2 + b * c + 2 * c2));
			case 948 -> (a + b - c) * (a - b + c) * (a3 - a2 * (b + c)
					- p(b - c, 2) * (b + c) + a * p(b + c, 2));
			case 949 -> a2 * (a - b - c)
					* (a3 - b3 + b2 * c - b * c2 + c3 - a2 * (b + c)
					+ a * (b2 - 2 * b * c - c2))
					* (a3 + b3 - b2 * c + b * c2 - c3 - a2 * (b + c)
					+ a * (-b2 - 2 * b * c + c2));
			case 950 -> -((a - b - c)
					* (2 * a3 + a2 * (b + c) + p(b - c, 2) * (b + c)));
			case 951 -> a2 * (a + b - c) * (a - b + c)
					* (a3 + 2 * b3 - a2 * c + b2 * c + c3 + a * (b2 - c2))
					* (a3 - a2 * b + b3 + b * c2 + 2 * c3 + a * (-b2 + c2));
			case 952 -> 2 * a4 - 2 * a3 * (b + c) + 2 * a * p(b - c, 2) * (b + c) - Q
					- a2 * (b2 - 4 * b * c + c2);
			case 953 -> a2
					* (a4 - 2 * a3 * b - 2 * b4 + 2 * a * b * p(b - c, 2)
					+ 2 * b3 * c + b2 * c2 - 2 * b * c3 + c4
					+ a2 * (b2 + 2 * b * c - 2 * c2))
					* (a4 + b4 - 2 * a3 * c - 2 * b3 * c
					+ 2 * a * p(b - c, 2) * c + b2 * c2 + 2 * b * c3
					- 2 * c4 + a2 * (-2 * b2 + 2 * b * c + c2));
			case 954 -> a * (a5 - 2 * a4 * (b + c)
					- 2 * b * p(b - c, 2) * c * (b + c) - a * Q
					+ 2 * a2 * (b3 + 2 * b2 * c + 2 * b * c2 + c3));
			case 955 -> a * (-(b * p(b - c, 3) * (b + c)) + a4 * (b + 2 * c)
					+ 2 * a * Q - 2 * a3 * R
					- 2 * a2 * c * (2 * b2 + b * c + c2))
					* (p(b - c, 3) * c * (b + c) + a4 * (2 * b + c) + 2 * a * Q
					- 2 * a3 * R - 2 * a2 * b * (b2 + b * c + 2 * c2));
			case 956 -> a * (a3 - a * p(b - c, 2) - 2 * b * c * (b + c));
			case 957 -> a * (-b3 + b * c2 + 2 * a * c * (-b + c) + a2 * (b + 2 * c))
					* (2 * a * b * (b - c) + a2 * (2 * b + c) + c * (b2 - c2));
			case 958 -> a * (a - b - c) * (a2 + 2 * b * c + a * (b + c));
			case 959 -> a * (a + b - c) * (a - b + c)
					* (c * (b + c) + a * (2 * b + c))
					* (b * (b + c) + a * (b + 2 * c));
			case 960 -> -(a * (a - b - c) * (b2 + c2 + a * (b + c)));
			case 961 -> a * (a + b - c) * (a - b + c) * (a2 + a * c + b * (b + c))
					* (a2 + a * b + c * (b + c));
			case 962 -> -a4 + 4 * a2 * b * c - 2 * a3 * (b + c)
					+ 2 * a * p(b - c, 2) * (b + c) + Q;
			case 963 -> a2
					* (a4 + 2 * a3 * b - 2 * a * b * p(b - c, 2)
					- 2 * a2 * c * (b + c) - (b - c) * p(b + c, 3))
					* (a4 + 2 * a3 * c - 2 * a * p(b - c, 2) * c
					- 2 * a2 * b * (b + c) + (b - c) * p(b + c, 3));
			case 964 -> a4 + a3 * (b + c) + a2 * p(b + c, 2) + b * c * p(b + c, 2)
					+ a * (b3 + 2 * b2 * c + 2 * b * c2 + c3);
			case 965 -> a * (a4 - a3 * (b + c) + 2 * b * c * p(b + c, 2)
					+ a * p(b + c, 3) - a2 * R);
			case 966 -> -a2 + 2 * a * (b + c) + p(b + c, 2);
			case 967 -> a2 * (a2 + b2 + 2 * b * c - c2 + 2 * a * (b + c))
					* (a2 - b2 + 2 * b * c + c2 + 2 * a * (b + c));
			case 968 -> a * (a2 - 2 * a * (b + c) - p(b + c, 2));
			case 969 -> a * (a2 + b2 + 2 * b * c - c2 + 2 * a * (b + c))
					* (a2 - b2 + 2 * b * c + c2 + 2 * a * (b + c));
			case 970 -> a2 * (-b5 - b4 * c - b * c4 - c5 + a3 * p(b + c, 2)
					+ a2 * (b3 + b2 * c + b * c2 + c3)
					- a * (b4 + 2 * b3 * c + 2 * b * c3 + c4));
			case 971 -> a * (a4 * (b + c) - p(b - c, 2) * p(b + c, 3)
					- 2 * a3 * (b2 - b * c + c2)
					+ 2 * a * p(b - c, 2) * (b2 + b * c + c2));
			case 972 -> a * (a5 + a4 * (b - 2 * c) - 2 * a3 * b * (b - c)
					+ b * p(b - c, 3) * (b + c) + a * (b - c) * p(b + c, 3)
					- 2 * a2 * (b3 - c3))
					* (a5 + 2 * a3 * (b - c) * c + a4 * (-2 * b + c)
					- p(b - c, 3) * c * (b + c)
					- a * (b - c) * p(b + c, 3) + 2 * a2 * (b3 - c3));
			case 973 -> a2 * (-Q + a2 * R)
					* (a10 - 3 * a8 * R + a6 * (2 * b4 + 3 * b2 * c2 + 2 * c4)
					- a2 * Q * (3 * b4 + 5 * b2 * c2 + 3 * c4)
					+ Q * (b6 + c6)
					+ a4 * (2 * b6 + b4 * c2 + b2 * c4 + 2 * c6));
			case 974 -> a2 * T
					* (a10 * R + a8 * (-3 * b4 + 2 * b2 * c2 - 3 * c4)
					+ p(b2 - c2, 4) * (b4 + b2 * c2 + c4)
					+ a4 * Q * (2 * b4 - 7 * b2 * c2 + 2 * c4)
					- 3 * a2 * Q * (b6 - 2 * b4 * c2 - 2 * b2 * c4 + c6)
					+ a6 * (2 * b6 - b4 * c2 - b2 * c4 + 2 * c6));
			case 975 -> a * (a3 + a2 * (b + c) + p(b + c, 3)
					+ a * (b2 + 4 * b * c + c2));
			case 976 -> a * (a3 + b3 + b2 * c + b * c2 + c3);
			case 977 -> a * (a3 + a2 * b + a * b2 + b3 + c3)
					* (a3 + b3 + a2 * c + a * c2 + c3);
			case 978 -> a * (a2 * (b + c) - b * c * (b + c) + a * (b2 - b * c + c2));
			case 979 -> a * (a2 * (b - c) + b * c * (b + c) + a * (b2 - b * c - c2))
					* (a2 * (b - c) - b * c * (b + c) + a * (b2 + b * c - c2));
			case 980 -> a * (b * c * R + a2 * (b2 + b * c + c2)
					+ a * (b3 + b2 * c + b * c2 + c3));
			case 981 -> a * (a3 * (b + c) + a2 * c * (b + c) + b2 * c * (b + c)
					+ a * b * (b2 + b * c + c2))
					* (a3 * (b + c) + a2 * b * (b + c) + b * c2 * (b + c)
					+ a * c * (b2 + b * c + c2));
			case 982 -> a * (b2 - b * c + c2);
			case 983 -> a * (a2 - a * b + b2) * (a2 - a * c + c2);
			case 984 -> a * (b2 + b * c + c2);
			case 985 -> a * (a2 + a * b + b2) * (a2 + a * c + c2);
			case 986 -> a * (b3 + c3 + a * (b2 + b * c + c2));
			case 987 -> a * (a3 + a2 * c + a * b * c + b2 * (b + c))
					* (a3 + a2 * b + a * b * c + c2 * (b + c));
			case 988 -> a * (a3 - b3 - b2 * c - b * c2 - c3 - a2 * (b + c)
					- 3 * a * R);
			case 989 -> a * (a3 - b3 + b2 * c + 3 * b * c2 + c3 + a2 * (3 * b + c)
					+ a * R)
					* (a3 + b3 + 3 * b2 * c + b * c2 - c3 + a2 * (b + 3 * c)
					+ a * R);
			case 990 -> a * (a5 - 2 * a3 * b * c - a4 * (b + c)
					+ p(b - c, 2) * p(b + c, 3) - a * p(b - c, 2) * R);
			case 991 -> a2 * (a3 * (b + c) - a2 * (b2 - b * c + c2)
					+ p(b - c, 2) * (b2 + b * c + c2)
					- a * (b3 + b2 * c + b * c2 + c3));
			case 992 -> a * (a3 * (b + c) - a * b * c * (b + c) - b * c * p(b + c, 2)
					+ a2 * R);
			case 993 -> a * (a3 - b * c * (b + c) - a * R);
			case 994 -> a * (-b3 + a * c2 + b * c2 + a2 * (b + c))
					* (a * b2 + a2 * (b + c) + c * (b2 - c2));
			case 995 -> a2 * (b2 - b * c + c2 + a * (b + c));
			case 996 -> (a2 + a * (-b + c) + b * (b + c))
					* (a2 + a * (b - c) + c * (b + c));
			case 997 -> a * (a3 + b3 - a * p(b - c, 2) + b2 * c + b * c2 + c3
					- a2 * (b + c));
			case 998 -> a * (a3 + a2 * (b - c) + p(b - c, 2) * (b + c)
					+ a * (b2 + 2 * b * c - c2))
					* (a3 + a2 * (-b + c) + p(b - c, 2) * (b + c)
					+ a * (-b2 + 2 * b * c + c2));
			case 999 -> a2 * (a2 - b2 + 4 * b * c - c2);
			default -> Double.NaN;
		};
	}

	private double weight1000to1099(int k, double a, double b, double c) {

		return switch (k) {
			case 1000 -> -((a2 - 4 * a * b + b2 - c2) * (a2 - b2 - 4 * a * c + c2));
			case 1001 -> a * (a2 - 2 * b * c - a * (b + c));
			case 1002 -> a * ((b - c) * c + a * (2 * b + c))
					* (b * (-b + c) + a * (b + 2 * c));
			case 1003 -> 3 * a4 + 2 * b2 * c2 - a2 * R;
			case 1004 -> a * (a5 - 2 * a4 * (b + c)
					+ 2 * b * p(b - c, 2) * c * (b + c) - a * p(b2 + c2, 2)
					+ 2 * a2 * (b3 + c3));
			case 1005 -> a * (a5 - a3 * b * c - 2 * a4 * (b + c)
					+ b * p(b - c, 2) * c * (b + c)
					- a * p(b + c, 2) * (b2 - 3 * b * c + c2)
					+ a2 * (2 * b3 + b2 * c + b * c2 + 2 * c3));
			case 1006 -> a * (a6 - a5 * (b + c) - b * c * Q + a2 * p(b + c, 2) * R
					- a4 * (2 * b2 + b * c + 2 * c2) + 2 * a3 * (b3 + c3)
					- a * (b5 - b4 * c - b * c4 + c5));
			case 1007 -> a4 + 3 * b4 - 2 * b2 * c2 + 3 * c4 - 4 * a2 * R;
			case 1008 -> a5 * (b + c) + a4 * p(b + c, 2) + b2 * c2 * p(b + c, 2)
					+ a * b * c * p(b + c, 3) + a2 * p(b2 + b * c + c2, 2)
					+ a3 * (b3 + 2 * b2 * c + 2 * b * c2 + c3);
			case 1009 -> a * (2 * a3 * b * c + a4 * (b + c) - a2 * (b3 + c3)
					+ b * c * (b3 + b2 * c + b * c2 + c3));
			case 1010 -> (a + b) * (a + c) * (a2 + p(b + c, 2));
			case 1011 -> a2 * (a2 * b * c + a3 * (b + c) - b * c * p(b + c, 2)
					- a * (b3 + b2 * c + b * c2 + c3));
			case 1012 -> a * (a6 - 2 * a4 * p(b - c, 2) - a5 * (b + c) - 2 * b * c * Q
					+ a2 * p(b - c, 2) * R + 2 * a3 * (b3 + c3)
					- a * (b5 - b4 * c - b * c4 + c5));
			case 1013 -> a * U * V * (a4 + 2 * b2 * c2 - a3 * (b + c) - a2 * R
					+ a * (b3 + b2 * c + b * c2 + c3));
			case 1014 -> a * (a + b) * (a + b - c) * (a + c) * (a - b + c);
			case 1015 -> a2 * p(b - c, 2);
			case 1016 -> p(a - b, 2) * p(a - c, 2);
			case 1017 -> a2 * p(-2 * a + b + c, 2);
			case 1018 -> a * (a - b) * (a - c) * (b + c);
			case 1019 -> a * (a + b) * (b - c) * (a + c);
			case 1020 -> a * (a - b) * (a - c) * p(a + b - c, 2) * p(a - b + c, 2)
					* (b + c);
			case 1021 -> a * (a + b) * (b - c) * (a + c) * p(-a + b + c, 2);
			case 1022 -> a * (a + b - 2 * c) * (b - c) * (a - 2 * b + c);
			case 1023 -> a * (a - b) * (a - c) * (2 * a - b - c);
			case 1024 -> a * (a - b - c) * (b - c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 1025 -> a * (a - b) * (a - c) * (a + b - c) * (a - b + c)
					* (-b2 - c2 + a * (b + c));
			case 1026 -> a * (a - b) * (a - c) * (-b2 - c2 + a * (b + c));
			case 1027 -> a * (b - c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 1028 -> a * p(angleB * angleC, 2);
			case 1029 -> -((a3 + a2 * (b + c) + (b - c) * p(b + c, 2)
					+ a * (b2 + b * c - c2))
					* (a3 + a2 * (b + c) - (b - c) * p(b + c, 2)
					+ a * (-b2 + b * c + c2)));
			case 1030 -> a2 * (a3 - b3 - b2 * c - b * c2 - c3 + a2 * (b + c)
					- a * (b2 + b * c + c2));
			case 1031 -> -((a4 + b4 + b2 * c2 - c4 + a2 * R)
					* (a4 - b4 + b2 * c2 + c4 + a2 * R));
			case 1032 -> -(T
					* (a8 - 4 * a6 * (b2 - c2) + p(b2 - c2, 4)
					- 4 * a2 * (b2 - c2) * p(b2 + c2, 2)
					+ 2 * a4 * (3 * b4 + 2 * b2 * c2 - 5 * c4))
					* (a8 + 4 * a6 * (b2 - c2) + p(b2 - c2, 4)
					+ 4 * a2 * (b2 - c2) * p(b2 + c2, 2)
					+ a4 * (-10 * b4 + 4 * b2 * c2 + 6 * c4)));
			case 1033 -> a2 * U * V
					* (a8 - 4 * a6 * R - 4 * a2 * Q * R
					+ Q * (b4 + 6 * b2 * c2 + c4)
					+ a4 * (6 * b4 - 4 * b2 * c2 + 6 * c4));
			case 1034 -> -((a - b - c)
					* (a6 - 2 * a5 * (b - c) - a4 * p(b - c, 2)
					+ p(b - c, 4) * p(b + c, 2) - a2 * Q
					+ 4 * a3 * (b3 - c3)
					- 2 * a * (b5 + b4 * c - b * c4 - c5))
					* (a6 + 2 * a5 * (b - c) - a4 * p(b - c, 2)
					+ p(b - c, 4) * p(b + c, 2) - a2 * Q
					- 4 * a3 * (b3 - c3)
					+ 2 * a * (b5 + b4 * c - b * c4 - c5)));
			case 1035 -> a2 * (a + b - c) * (a - b + c)
					* (a6 - 2 * a5 * (b + c) - a4 * p(b + c, 2)
					+ p(b - c, 2) * p(b + c, 4) - a2 * Q
					+ 4 * a3 * (b3 + c3)
					- 2 * a * (b5 - b4 * c - b * c4 + c5));
			case 1036 -> a2 * (a - b - c) * (a2 + 2 * a * b + b2 + c2)
					* (a2 + b2 + 2 * a * c + c2);
			case 1037 -> a2 * (a + b - c) * (a - b + c) * (a2 - 2 * a * b + b2 + c2)
					* (a2 + b2 - 2 * a * c + c2);
			case 1038 -> a * (a + b - c) * (a - b + c) * T * (a2 + p(b + c, 2));
			case 1039 -> a * (a - b - c) * U * V * (a2 + 2 * a * b + b2 + c2)
					* (a2 + b2 + 2 * a * c + c2);
			case 1040 -> a * (a2 + p(b - c, 2)) * (a - b - c) * T;
			case 1041 -> a * (a + b - c) * (a - b + c) * U * V
					* (a2 - 2 * a * b + b2 + c2) * (a2 + b2 - 2 * a * c + c2);
			case 1042 -> a2 * p(a + b - c, 2) * p(a - b + c, 2) * (b + c);
			case 1043 -> (a + b) * (a + c) * p(-a + b + c, 2);
			case 1044 -> a * (3 * a4 * b * c + a5 * (b + c)
					- 2 * a3 * p(b - c, 2) * (b + c) + a * p(b - c, 4) * (b + c)
					- b * c * Q - 2 * a2 * b * c * R);
			case 1045 -> a * (-(b2 * c2) + a * b * c * (b + c)
					+ a2 * (b2 + b * c + c2));
			case 1046 -> a * (a3 - b3 + a * b * c - c3 + 2 * a2 * (b + c));
			case 1047 -> a * (-(b2 * p(b - c, 2) * c2 * p(b + c, 3))
					+ a2 * p(b - c, 2) * p(b + c, 3) * R
					+ a3 * Q * (b2 - b * c + c2)
					+ a * b * c * Q * (b2 - b * c + c2) + a7 * (b2 + b * c + c2)
					+ a6 * (b3 + b2 * c + b * c2 + c3)
					- a5 * (2 * b4 + b3 * c - b2 * c2 + b * c3 + 2 * c4)
					+ a4 * (-2 * b5 - 2 * b4 * c + b3 * c2 + b2 * c3
					- 2 * b * c4 - 2 * c5));
			case 1048 -> a * (-(b2 * p(b - c, 2) * c2 * p(b + c, 3))
					+ a * b * c * Q * R
					- 2 * a4 * p(b + c, 3) * (b2 - b * c + c2)
					+ a7 * (b2 + b * c + c2)
					+ a6 * (b3 + 2 * b2 * c + 2 * b * c2 + c3)
					- a5 * (2 * b4 + b3 * c + b * c3 + 2 * c4)
					+ a3 * (b6 - b5 * c - 2 * b4 * c2 - b3 * c3 - 2 * b2 * c4
					- b * c5 + c6)
					+ a2 * (b7 + 2 * b6 * c - 2 * b4 * c3 - 2 * b3 * c4
					+ 2 * b * c6 + c7));
			case 1049 -> a * angleA;
			case 1050 -> a * (-(b2 * c2 * p(b + c, 2)) + a4 * (b2 + b * c + c2)
					+ a * b * c * (b3 + b2 * c + b * c2 + c3)
					+ a3 * (2 * b3 - 3 * b2 * c - 3 * b * c2 + 2 * c3)
					+ a2 * (b4 - 3 * b3 * c + 7 * b2 * c2 - 3 * b * c3 + c4));
			case 1051 -> a * (3 * a2 + b2 + b * c + c2 + 5 * a * (b + c));
			case 1052 -> a * (a4 - b4 + 2 * b3 * c - b2 * c2 + 2 * b * c3 - c4
					- 2 * a3 * (b + c) - a2 * (b2 - 8 * b * c + c2)
					+ 2 * a * (b3 - 2 * b2 * c - 2 * b * c2 + c3));
			case 1053 -> a * (a6 - 2 * a5 * (b + c) + 2 * a * p(b - c, 4) * (b + c)
					+ 2 * a4 * (b2 + b * c + c2)
					+ a2 * b * c * (4 * b2 - 7 * b * c + 4 * c2)
					- 2 * a3 * (b3 + c3) - p(b - c, 2) * (b4 - b2 * c2 + c4));
			case 1054 -> a * (a2 - b2 + 3 * b * c - c2 - a * (b + c));
			case 1055 -> a2 * (2 * a2 - p(b - c, 2) - a * (b + c));
			case 1056 -> -a4 - 8 * a2 * b * c + Q;
			case 1057 -> a2 * (a4 - 2 * a2 * b2 + b4 - 8 * a * b * c2 - c4)
					* (a4 - b4 - 8 * a * b2 * c - 2 * a2 * c2 + c4);
			case 1058 -> -a4 + 8 * a2 * b * c + Q;
			case 1059 -> a2 * (a4 - 2 * a2 * b2 + b4 + 8 * a * b * c2 - c4)
					* (a4 - b4 + 8 * a * b2 * c - 2 * a2 * c2 + c4);
			case 1060 -> a * T * (a4 + 2 * a2 * b * c - Q);
			case 1061 -> a * U * V * (a4 - 2 * a2 * b2 + b4 - 2 * a * b * c2 - c4)
					* (a4 - b4 - 2 * a * b2 * c - 2 * a2 * c2 + c4);
			case 1062 -> a * T * (a4 - 2 * a2 * b * c - Q);
			case 1063 -> a * U * V * (a4 - 2 * a2 * b2 + b4 + 2 * a * b * c2 - c4)
					* (a4 - b4 + 2 * a * b2 * c - 2 * a2 * c2 + c4);
			case 1064 -> a2 * (b5 - b4 * c - 4 * a * b2 * c2 - b * c4 + c5
					+ a4 * (b + c) - 2 * a2 * (b3 + c3));
			case 1065 -> (a5 - 2 * a3 * b2 - a4 * c - 4 * a2 * b * c2 + c * Q
					+ a * (b4 - c4))
					* (a5 - a4 * b - 4 * a2 * b2 * c - 2 * a3 * c2 + b * Q
					+ a * (-b4 + c4));
			case 1066 -> a2 * (b5 - b4 * c + 4 * a * b2 * c2 - b * c4 + c5
					+ a4 * (b + c) - 2 * a2 * (b3 + c3));
			case 1067 -> (a5 - 2 * a3 * b2 - a4 * c + 4 * a2 * b * c2 + c * Q
					+ a * (b4 - c4))
					* (a5 - a4 * b + 4 * a2 * b2 * c - 2 * a3 * c2 + b * Q
					+ a * (-b4 + c4));
			case 1068 -> U * V * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c) - a * R);
			case 1069 -> a2 * T * (a3 + a2 * (b - c) - (b - c) * p(b + c, 2) - a * R)
					* (a3 + a2 * (-b + c) + (b - c) * p(b + c, 2) - a * R);
			case 1070 -> 8 * a3 * b2 * c2 + a6 * (b + c) - a4 * p(b - c, 2) * (b + c)
					- a2 * p(b - c, 2) * p(b + c, 3)
					+ p(b - c, 4) * p(b + c, 3);
			case 1071 -> a2 * T * (-(a2 * p(b - c, 2)) + a3 * (b + c) + Q);
			case 1072 -> -8 * a3 * b2 * c2 + a6 * (b + c) - a4 * p(b - c, 2) * (b + c)
					- a2 * p(b - c, 2) * p(b + c, 3)
					+ p(b - c, 4) * p(b + c, 3);
			case 1073 -> a2 * T
					* (a4 + b4 + 2 * b2 * c2 - 3 * c4 - 2 * a2 * (b2 - c2))
					* (a4 - 3 * b4 + 2 * b2 * c2 + c4 + 2 * a2 * (b2 - c2));
			case 1074 -> -4 * a5 * b * c + a6 * (b + c) - a4 * p(b - c, 2) * (b + c)
					- a2 * p(b - c, 2) * p(b + c, 3) + p(b - c, 4) * p(b + c, 3)
					+ 4 * a3 * b * c * R;
			case 1075 -> U * V * (-(b2 * c2 * p(b2 - c2, 4)) + a10 * R
					+ a2 * Q * p(b2 + c2, 3)
					- 2 * a4 * Q * (2 * b4 + 3 * b2 * c2 + 2 * c4)
					- a8 * (4 * b4 + b2 * c2 + 4 * c4)
					+ a6 * (6 * b6 - 2 * b4 * c2 - 2 * b2 * c4 + 6 * c6));
			case 1076 -> 4 * a5 * b * c + a6 * (b + c) - a4 * p(b - c, 2) * (b + c)
					- a2 * p(b - c, 2) * p(b + c, 3) + p(b - c, 4) * p(b + c, 3)
					- 4 * a3 * b * c * R;
			case 1077 -> a * angleB * angleC;
			case 1078 -> a4 - b2 * c2 - a2 * R;
			case 1079 -> a * p(a3 + a2 * (b + c) - p(b - c, 2) * (b + c) - a * R, 2);
			case 1080 -> -3 * U * V * (a2 + b2 + c2) + u(3) * (a - b - c)
					* (a + b - c) * (a - b + c) * (a + b + c) * S;
			case 1081 -> 1 / (u(3) * (-a + b + c) * (a + b + c) - S);
			case 1082 -> a * (a + b - c) * (a - b + c) - u(3) * a * S;
			case 1083 -> a * (a4 - a2 * b * c - a3 * (b + c) + 2 * a * b * c * (b + c)
					- b * c * R);
			case 1084 -> a4 * p(b - c, 2) * p(b + c, 2);
			case 1085 -> a * angleA * angleA;
			case 1086 -> p(b - c, 2);
			case 1087 -> b * c * p(Q - a2 * R, 2);
			case 1088 -> b * p(a + b - c, 2) * c * p(a - b + c, 2);
			case 1089 -> b * c * p(b + c, 2);
			case 1090 -> b * p(b - c, 4) * c * p(-a + b + c, 2);
			case 1091 -> b * p(a + b - c, 2) * c * p(a - b + c, 2) * p(b + c, 4);
			case 1092 -> a4 * p(a2 - b2 - c2, 3);
			case 1093 -> b2 * c2 * p(-a4 + Q, 3);
			case 1094 -> -(a3
					* (a4 - 2 * a2 * b2 + b4 - 2 * a2 * c2 + 4 * b2 * c2 + c4))
					+ u(3) * a3 * T * S;
			case 1095 -> a3 * (a4 - 2 * a2 * b2 + b4 - 2 * a2 * c2 + 4 * b2 * c2 + c4)
					+ u(3) * a3 * T * S;
			case 1096 -> a * p(a4 - Q, 2);
			case 1097 -> b * c * p(-3 * a4 + Q + 2 * a2 * R, 2);
			case 1098 -> a * p(a + b, 2) * p(a + c, 2) * p(-a + b + c, 2);
			case 1099 -> b * c * p(-2 * a4 + Q + a2 * R, 2);
			default -> Double.NaN;
		};
	}

	private double weight1100to1199(int k, double a, double b, double c) {

		return switch (k) {
			case 1100 -> a * (2 * a + b + c);
			case 1101 -> a3 * p(a - b, 2) * p(a + b, 2) * p(a - c, 2) * p(a + c, 2);
			case 1102 -> a * p(a2 - b2 - c2, 3);
			case 1103 -> a * p(
					a3 + a2 * (b + c) - p(b - c, 2) * (b + c) - a * p(b + c, 2),
					2);
			case 1104 -> a * (2 * a3 + a2 * (b + c) + p(b - c, 2) * (b + c));
			case 1105 -> U * V
					* (a6 - a4 * (2 * b2 + c2) + p(-(b2 * c) + c3, 2)
					+ a2 * (b4 + 4 * b2 * c2 - c4))
					* (a6 - a4 * (b2 + 2 * c2) + p(b3 - b * c2, 2)
					+ a2 * (-b4 + 4 * b2 * c2 + c4));
			case 1106 -> a3 * p(a + b - c, 2) * p(a - b + c, 2);
			case 1107 -> a * (b * c * (b + c) + a * R);
			case 1108 -> a * (-(a2 * p(b - c, 2)) + a3 * (b + c)
					- a * p(b - c, 2) * (b + c) + Q);
			case 1109 -> b * p(b - c, 2) * c * p(b + c, 2);
			case 1110 -> a3 * p(a - b, 2) * p(a - c, 2);
			case 1111 -> b * p(b - c, 2) * c;
			case 1112 -> a2 * U * V * (b6 + c6 + a4 * R - 2 * a2 * (b4 + c4));
			case 1113 -> -(a * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)) + a2
					* T
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 1114 -> a * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4) + a2
					* T
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 1115 -> -angleA + Math.PI;
			case 1116 -> -((b2 - c2) * (-2 * a8 + p(b2 - c2, 4) + 5 * a6 * R
					- a4 * (3 * b4 + 8 * b2 * c2 + 3 * c4)
					- a2 * (b6 - 4 * b4 * c2 - 4 * b2 * c4 + c6)));
			case 1117 -> (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (a2 - b2 - a * c + c2) * (a2 - b2 + a * c + c2)
					* (a6 - a4 * b2 - a2 * b4 + b6 - 3 * a4 * c2 + a2 * b2 * c2
					- 3 * b4 * c2 + 3 * a2 * c4 + 3 * b2 * c4 - c6)
					* (a6 - 3 * a4 * b2 + 3 * a2 * b4 - b6 - a4 * c2
					+ a2 * b2 * c2 + 3 * b4 * c2 - a2 * c4 - 3 * b2 * c4
					+ c6)
					* (a8 - 4 * a6 * b2 + 6 * a4 * b4 - 4 * a2 * b6 + b8
					- 4 * a6 * c2 + a4 * b2 * c2 + a2 * b4 * c2
					+ 2 * b6 * c2 + 6 * a4 * c4 + a2 * b2 * c4
					- 6 * b4 * c4 - 4 * a2 * c6 + 2 * b2 * c6 + c8);
			case 1118 -> -((a + b - c) * (a - b + c) * p(a4 - Q, 2));
			case 1119 -> -(p(a + b - c, 2) * p(a - b + c, 2) * U * V);
			case 1120 -> (a2 + a * (-4 * b + c) + b * (b + c))
					* (a2 + a * (b - 4 * c) + c * (b + c));
			case 1121 -> -((a2 - 2 * b2 + a * (b - 2 * c) + b * c + c2)
					* (a2 + b2 + b * c - 2 * c2 + a * (-2 * b + c)));
			case 1122 -> a * (a + b - c) * (a - b + c) * (p(b - c, 2) + a * (b + c));
			case 1123 -> -a4 + 2 * a2 * b2 - b4 + 4 * a2 * b * c + 2 * a2 * c2
					+ 2 * b2 * c2 - c4 + 2 * a * (b + c) * S;
			case 1124 -> 2 * a2 * b * c + a2 * S;
			case 1125 -> 2 * a + b + c;
			case 1126 -> a2 * (a + 2 * b + c) * (a + b + 2 * c);
			case 1127 -> (a * c + u(a * c * (a - b + c) * (a + b + c)))
					* (a * b + u(a * b * (a + b - c) * (a + b + c)));
			case 1128 -> a * (a * b * c + c * u(a * (-a + b + c)) * u(b * (a - b + c))
					+ b * u(a * (-a + b + c)) * u(c * (a + b - c))
					+ (-a + b + c) * u(b * (a - b + c)) * u(c * (a + b - c)));
			case 1129 -> a2 * (b * c + u(-(b * (a - b - c) * c * (a + b + c))));
			case 1130 -> a2 * (b * c + u(b * (a - b + c)) * u(c * (a + b - c)));
			case 1131 -> 3 * a4 + 2 * a2 * b2 - 5 * b4 + 2 * a2 * c2 + 10 * b2 * c2
					- 5 * c4 + 4 * a2 * S;
			case 1132 -> -3 * a4 - 2 * a2 * b2 + 5 * b4 - 2 * a2 * c2 - 10 * b2 * c2
					+ 5 * c4 + 4 * a2 * S;
			case 1133 -> a * (u(3) * Math.cos(angleA / 3) - Math.sin(angleA / 3))
					* (u(3) * Math.cos(angleB / 3) + Math.sin(angleB / 3))
					* (u(3) * Math.cos(angleC / 3) + Math.sin(angleC / 3));
			case 1134 -> -(a * (Math.cos(angleB / 3) + u(3) * Math.sin(angleB / 3))
					* (Math.cos(angleC / 3) + u(3) * Math.sin(angleC / 3)));
			case 1135 -> -(a * (Math.cos(angleA / 3) + u(3) * Math.sin(angleA / 3)));
			case 1136 -> a * (-Math.cos(angleB / 3) + u(3) * Math.sin(angleB / 3))
					* (-Math.cos(angleC / 3) + u(3) * Math.sin(angleC / 3));
			case 1137 -> a * (-Math.cos(angleA / 3) + u(3) * Math.sin(angleA / 3));
			case 1138 -> (a8 + 2 * a6 * (b2 - 2 * c2) + p(b2 - c2, 4)
					+ a4 * (-6 * b4 + b2 * c2 + 6 * c4)
					+ a2 * (2 * b6 + b4 * c2 + b2 * c4 - 4 * c6))
					* (a8 + p(b2 - c2, 4) + a6 * (-4 * b2 + 2 * c2)
					+ a4 * (6 * b4 + b2 * c2 - 6 * c4)
					+ a2 * (-4 * b6 + b4 * c2 + b2 * c4 + 2 * c6));
			case 1139 -> 2 * (a4 + u(5) * a4 + 3 * a2 * b2 - u(5) * a2 * b2 - 4 * b4
					+ 3 * a2 * c2 - u(5) * a2 * c2 + 8 * b2 * c2 - 4 * c4)
					+ (-1 + u(5)) * u(2 * (5 + u(5))) * a2 * S;
			case 1140 -> -2
					* (a4 + u(5) * a4 + 3 * a2 * b2 - u(5) * a2 * b2 - 4 * b4
					+ 3 * a2 * c2 - u(5) * a2 * c2 + 8 * b2 * c2
					- 4 * c4)
					+ (-1 + u(5)) * u(2 * (5 + u(5))) * a2 * S;
			case 1141 -> (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (a2 - b2 - a * c + c2) * (a2 - b2 + a * c + c2)
					* (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 1142 -> 2 * (a + b + c)
					* p(1 + (2 * b * c
					- u(-(b * (a - b - c) * c * (a + b + c))))
					/ (u(b * (a - b + c)) * u(c * (a + b - c))), 2)
					- (b + c) * (2 + (2
					* (2 * b * c
					- u(-(b * (a - b - c) * c * (a + b + c))))
					* (2 * a * c - u(a * c * (a - b + c) * (a + b + c)))
					* (2 * a * b
					- u(a * b * (a + b - c) * (a + b + c))))
					/ (a * b * (a + b - c) * c * (a - b + c)
					* (-a + b + c)));
			case 1143 -> u(a * (-a + b + c))
					* (2 * b * c - u(-(b * (a - b - c) * c * (a + b + c))));
			/* case 1144: perl script returns zero */
			case 1145 -> (2 * a - b - c)
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1146 -> p(b - c, 2) * p(-a + b + c, 2);
			case 1147 -> a4 * T * (a4 + b4 + c4 - 2 * a2 * R);
			case 1148 -> U * V
					* (a4 * b * c + a5 * (b + c) - b * c * Q
					- 2 * a3 * (b3 + c3)
					+ a * (b5 - b4 * c - b * c4 + c5));
			case 1149 -> a2 * (b2 - 4 * b * c + c2 + a * (b + c));
			case 1150 -> a3 - b * c * (b + c) - a * R;
			case 1151 -> 2 * a2 * T - a2 * S;
			case 1152 -> 2 * a2 * T + a2 * S;
			case 1153 -> 10 * a4 + 4 * b4 - 10 * b2 * c2 + 4 * c4 - 13 * a2 * R;
			case 1154 -> a2 * (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2)
					* (-Q + a2 * R);
			case 1155 -> a * (2 * a2 - p(b - c, 2) - a * (b + c));
			case 1156 -> a * (a2 - 2 * b2 + a * (b - 2 * c) + b * c + c2)
					* (a2 + b2 + b * c - 2 * c2 + a * (-2 * b + c));
			case 1157 -> a2 * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2))
					* (a6 - 3 * a4 * R - Q * R
					+ a2 * (3 * b4 - b2 * c2 + 3 * c4));
			case 1158 -> a * (a6 + 2 * a3 * b * c * (b + c)
					- 2 * a * b * p(b - c, 2) * c * (b + c)
					+ a4 * (-3 * b2 + 2 * b * c - 3 * c2) - Q * R
					+ a2 * p(b - c, 2) * (3 * b2 + 4 * b * c + 3 * c2));
			case 1159 -> a * (a3 - 4 * a2 * (b + c) + 4 * p(b - c, 2) * (b + c)
					- a * R);
			case 1160 -> 4 * a2 * (a2 * b2 - b4 + a2 * c2 - c4) + a2 * T * S;
			case 1161 -> -4 * a2 * (a2 * b2 - b4 + a2 * c2 - c4) + a2 * T * S;
			case 1162 -> -(U * V * (a4 - 10 * a2 * b2 + b4 - 10 * a2 * c2
					- 2 * b2 * c2 + c4)) + 2 * U * V * (2 * a2 + b2 + c2) * S;
			case 1163 -> U * V
					* (a4 - 10 * a2 * b2 + b4 - 10 * a2 * c2 - 2 * b2 * c2 + c4)
					+ 2 * U * V * (2 * a2 + b2 + c2) * S;
			case 1164 -> -(U * V
					* (a4 + 6 * a2 * b2 + b4 + 6 * a2 * c2 - 2 * b2 * c2 + c4))
					+ 2 * (2 * a2 - b2 - c2) * U * V * S;
			case 1165 -> U * V
					* (a4 + 6 * a2 * b2 + b4 + 6 * a2 * c2 - 2 * b2 * c2 + c4)
					+ 2 * (2 * a2 - b2 - c2) * U * V * S;
			case 1166 -> a2 * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2))
					* (a6 - a4 * (2 * b2 + c2) + p(-(b2 * c) + c3, 2)
					+ a2 * (b4 - 2 * b2 * c2 - c4))
					* (a6 - a4 * (b2 + 2 * c2) + p(b3 - b * c2, 2)
					+ a2 * (-b4 - 2 * b2 * c2 + c4));
			case 1167 -> a2
					* (a4 - a3 * c + b * p(b - c, 2) * (b + c)
					+ a * c * p(b + c, 2) - a2 * (2 * b2 - b * c + c2))
					* (a4 - a3 * b + p(b - c, 2) * c * (b + c)
					+ a * b * p(b + c, 2) - a2 * (b2 - b * c + 2 * c2));
			case 1168 -> a * (a + b - 2 * c) * (a - 2 * b + c)
					* (a2 - a * b + b2 - c2) * (a2 - b2 - a * c + c2);
			case 1169 -> a2 * (a + b) * (a + c) * (a2 + a * c + b * (b + c))
					* (a2 + a * b + c * (b + c));
			case 1170 -> a * (a + b - c) * (a - b + c)
					* (a2 + b * (b - c) - a * (2 * b + c))
					* (a2 + c * (-b + c) - a * (b + 2 * c));
			case 1171 -> a2 * (a + b) * (a + c) * (a + 2 * b + c) * (a + b + 2 * c);
			case 1172 -> a * (a + b) * (a - b - c) * (a + c) * U * V;
			case 1173 -> a2
					* (a4 + 2 * b4 - 3 * b2 * c2 + c4 - a2 * (3 * b2 + 2 * c2))
					* (a4 + b4 - 3 * b2 * c2 + 2 * c4 - a2 * (2 * b2 + 3 * c2));
			case 1174 -> a2 * (a2 + b * (b - c) - a * (2 * b + c))
					* (a2 + c * (-b + c) - a * (b + 2 * c));
			case 1175 -> a2 * (a + b) * (a + c)
					* (a3 - a2 * b + b3 - b * c2 - a * p(b + c, 2))
					* (a3 - a2 * c - b2 * c + c3 - a * p(b + c, 2));
			case 1176 -> a2 * (a2 + b2) * T * (a2 + c2);
			case 1177 -> a2 * (a6 - a4 * b2 + b6 - b2 * c4 - a2 * Q)
					* (a6 - a4 * c2 - b4 * c2 + c6 - a2 * Q);
			case 1178 -> a2 * (a + b) * (a + c) * (b2 + a * c) * (a * b + c2);
			case 1179 -> U * V
					* (a6 - a4 * (2 * b2 + c2) + p(-(b2 * c) + c3, 2)
					+ a2 * (b4 - 2 * b2 * c2 - c4))
					* (a6 - a4 * (b2 + 2 * c2) + p(b3 - b * c2, 2)
					+ a2 * (-b4 - 2 * b2 * c2 + c4));
			case 1180 -> a2 * (b4 + b2 * c2 + c4 + a2 * R);
			case 1181 -> a2 * T * (a6 + 3 * a2 * Q - 3 * a4 * R - Q * R);
			case 1182 -> a2 * (a5 * (b + c) + 2 * a2 * Q - Q * (b2 - b * c + c2)
					- a4 * (b2 + b * c + c2) - 2 * a3 * (b3 + c3)
					+ a * (b5 - b4 * c - b * c4 + c5));
			case 1183 -> a * (a - b - c)
					* (a5 + 2 * a4 * (b + c) + b * p(b - c, 2) * c * (b + c)
					+ a * p(b - c, 2) * (b2 + b * c + c2)
					+ a3 * (2 * b2 + 5 * b * c + 2 * c2)
					+ a2 * (2 * b3 + b2 * c + b * c2 + 2 * c3));
			case 1184 -> a2 * (a2 + p(b - c, 2)) * (a2 + p(b + c, 2));
			case 1185 -> a3 * (b * c * (b + c) + a * (b2 + b * c + c2));
			case 1186 -> a4 * (b2 * c2 * R + a2 * (b4 + b2 * c2 + c4));
			case 1187 -> a2 * (b + c) * (b5 + b4 * c + b3 * c2 + b2 * c3 + b * c4 + c5
					+ a4 * (b + c) + 2 * a3 * (b2 + b * c + c2)
					+ 2 * a2 * (b3 + 2 * b2 * c + 2 * b * c2 + c3)
					+ 2 * a * (b4 + 2 * b3 * c + b2 * c2 + 2 * b * c3 + c4));
			case 1188 -> a3 * (a - b - c)
					* (-(b * p(b - c, 4) * c) - a * p(b - c, 4) * (b + c)
					+ a4 * (b2 + b * c + c2)
					+ a2 * p(b - c, 2) * (3 * b2 + 4 * b * c + 3 * c2)
					- a3 * (3 * b3 + b2 * c + b * c2 + 3 * c3));
			case 1189 -> a2 * (-(b * c) + a * (b + c)) * (a4 * p(b - c, 4)
					+ a3 * p(b - c, 4) * (b + c) - b3 * c3 * (b2 + b * c + c2)
					- a2 * b * p(b - c, 2) * c * (3 * b2 + 4 * b * c + 3 * c2)
					+ a * b2 * c2 * (3 * b3 + b2 * c + b * c2 + 3 * c3));
			case 1190 -> a2 * (a - b - c) * (a3 + 3 * a * p(b - c, 2)
					- 3 * a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1191 -> a2 * (a2 + p(b - c, 2) + 2 * a * (b + c));
			case 1192 -> a2 * (3 * a8 - 6 * a4 * Q - 4 * a6 * R + 12 * a2 * Q * R
					- Q * (5 * b4 + 6 * b2 * c2 + 5 * c4));
			case 1193 -> a2 * (b2 + c2 + a * (b + c));
			case 1194 -> a2 * (b4 + c4 + a2 * R);
			case 1195 -> a2 * (a - b - c) * (b5 + 2 * a3 * b * c - b4 * c - b * c4
					+ c5 + a4 * (b + c) - 2 * a2 * p(b - c, 2) * (b + c));
			case 1196 -> a2 * (Q + a2 * R);
			case 1197 -> a3 * (b * c * (b + c) + a * R);
			case 1198 -> a2 * (-(b * c) + a * (b + c))
					* (a4 * p(b - c, 4) - b3 * c3 * R
					- a2 * b * p(b - c, 2) * c
					* (3 * b2 + 5 * b * c + 3 * c2)
					+ a3 * p(b - c, 2) * (b3 + c3)
					+ 3 * a * b2 * c2 * (b3 + c3));
			case 1199 -> a2 * (a8 - 4 * a6 * R - 4 * a2 * Q * R
					+ Q * (b4 - b2 * c2 + c4)
					+ 3 * a4 * (2 * b4 + b2 * c2 + 2 * c4));
			default -> Double.NaN;
		};
	}

	private double weight1200to1299(int k, double a, double b, double c) {

		return switch (k) {
			case 1200 -> a2 * (a - b - c) * (-2 * a * p(b - c, 2) + a2 * (b + c)
					+ p(b - c, 2) * (b + c));
			case 1201 -> a2 * (p(b - c, 2) + a * (b + c));
			case 1202 -> a2 * (a3 * (b + c) + 3 * a * p(b - c, 2) * (b + c)
					- 3 * a2 * R - p(b - c, 2) * R);
			case 1203 -> a2 * (a2 + b2 + b * c + c2 + 2 * a * (b + c));
			case 1204 -> a2 * T * (a6 - 3 * a2 * Q + 2 * Q * R);
			case 1205 -> -(a2
					* (a10 * R - a8 * p(b2 + c2, 2) - p(b4 - c4, 2) * (b4 + c4)
					+ 2 * a4 * Q * (b4 + 3 * b2 * c2 + c4)
					+ a6 * (-2 * b6 + 3 * b4 * c2 + 3 * b2 * c4
					- 2 * c6)
					+ a2 * Q * (b6 - 2 * b4 * c2 - 2 * b2 * c4 + c6)));
			case 1206 -> a2 * (b2 * c2 + 2 * a * b * c * (b + c)
					+ a2 * (b2 + b * c + c2));
			case 1207 -> a2 * b4 * c4 + 2 * a4 * b2 * c2 * R
					+ a6 * (b4 + b2 * c2 + c4);
			case 1208 -> a2
					* (a7 * (b + c) - a * p(b - c, 4) * p(b + c, 3) + a6 * R
					- p(b - c, 2) * p(b + c, 4) * R
					- a4 * p(b - c, 2) * (3 * b2 + 8 * b * c + 3 * c2)
					- a5 * (3 * b3 + b2 * c + b * c2 + 3 * c3)
					+ a3 * p(b - c, 2)
					* (3 * b3 + 5 * b2 * c + 5 * b * c2
					+ 3 * c3)
					+ a2 * p(b - c, 2) * (3 * b4 + 10 * b3 * c
					+ 6 * b2 * c2 + 10 * b * c3 + 3 * c4));
			case 1209 -> -((-Q + a2 * R)
					* (a4 * R + Q * R - 2 * a2 * (b4 + b2 * c2 + c4)));
			case 1210 -> -(a2 * p(b - c, 2)) + a3 * (b + c)
					- a * p(b - c, 2) * (b + c) + Q;
			case 1211 -> (b + c) * (b2 + c2 + a * (b + c));
			case 1212 -> a * (a - b - c) * (-p(b - c, 2) + a * (b + c));
			case 1213 -> (b + c) * (2 * a + b + c);
			case 1214 -> a * (a + b - c) * (a - b + c) * (b + c) * T;
			case 1215 -> (b + c) * (a2 + b * c);
			case 1216 -> -(a2 * T * (a4 * R + Q * R - 2 * a2 * (b4 + b2 * c2 + c4)));
			case 1217 -> U * V
					* (a6 - p(b2 - c2, 3) - a4 * (3 * b2 + c2)
					+ a2 * (3 * b4 + 6 * b2 * c2 - c4))
					* (a6 + p(b2 - c2, 3) - a4 * (b2 + 3 * c2)
					+ a2 * (-b4 + 6 * b2 * c2 + 3 * c4));
			case 1218 -> b * c * (b2 * c + a2 * (b + c) + a * b * (b + c))
					* (b * c2 + a2 * (b + c) + a * c * (b + c));
			case 1219 -> (a2 - 2 * a * (b - c) + p(b + c, 2))
					* (a2 + 2 * a * (b - c) + p(b + c, 2));
			case 1220 -> (a2 + a * c + b * (b + c)) * (a2 + a * b + c * (b + c));
			case 1221 -> b * c * (a * b2 + b2 * c + a2 * (b + c))
					* (a * c2 + b * c2 + a2 * (b + c));
			case 1222 -> (a2 + a * (-2 * b + c) + b * (b + c))
					* (a2 + a * (b - 2 * c) + c * (b + c));
			case 1223 -> (a4 - p(b - c, 3) * c - a * p(b - c, 2) * (b + 2 * c)
					- a3 * (3 * b + 2 * c) + a2 * (3 * b2 + 3 * b * c + 2 * c2))
					* (a4 + b * p(b - c, 3) - a * p(b - c, 2) * (2 * b + c)
					- a3 * (2 * b + 3 * c)
					+ a2 * (2 * b2 + 3 * b * c + 3 * c2));
			case 1224 -> (a2 + p(b + c, 2) + a * (2 * b + c))
					* (a2 + p(b + c, 2) + a * (b + 2 * c));
			case 1225 -> b2 * c2 * (Q - a2 * R)
					* (a4 * R + Q * R - 2 * a2 * (b4 + b2 * c2 + c4));
			case 1226 -> b2 * c2 * (-(a2 * p(b - c, 2)) + a3 * (b + c)
					- a * p(b - c, 2) * (b + c) + Q);
			case 1227 -> b * c * (-2 * a + b + c) * (-a2 + b2 - b * c + c2);
			case 1228 -> b2 * c2 * (b + c) * (b2 + c2 + a * (b + c));
			case 1229 -> b * c * (-a + b + c) * (p(b - c, 2) - a * (b + c));
			case 1230 -> b2 * c2 * (b + c) * (2 * a + b + c);
			case 1231 -> b * (-a + b - c) * (a + b - c) * c * (b + c) * (-T);
			case 1232 -> b2 * c2 * (2 * a4 + Q - 3 * a2 * R);
			case 1233 -> b2 * c2 * (p(b - c, 2) - a * (b + c));
			case 1234 -> b2 * c2 * (b + c)
					* (-2 * a * b * c - a2 * (b + c) + p(b - c, 2) * (b + c));
			case 1235 -> b2 * c2 * (-V) * U * R;
			case 1236 -> b2 * c2 * (2 * a2 * b2 * c2 - a4 * R + Q * R);
			case 1237 -> b2 * c2 * (b + c) * (a2 + b * c);
			case 1238 -> -(T * (a4 * R + Q * R - 2 * a2 * (b4 + b2 * c2 + c4)));
			case 1239 -> b2 * c2 * (a4 + a2 * R + b2 * R) * (a4 + a2 * R + c2 * R);
			case 1240 -> b2 * c2 * (a2 + a * c + b * (b + c))
					* (a2 + a * b + c * (b + c));
			case 1241 -> b2 * c2 * (a4 + b4 + a2 * c2 + b2 * c2)
					* (a4 + a2 * b2 + b2 * c2 + c4);
			case 1242 -> -(a
					* (a4 * (b - c) - p(b - c, 3) * c * (b + c)
					+ a2 * b * (-b2 + 4 * b * c + c2)
					+ a3 * (-b2 + b * c + 2 * c2)
					+ a * (b4 + b3 * c + b2 * c2 - b * c3 - 2 * c4))
					* (a4 * (b - c) - b * p(b - c, 3) * (b + c)
					+ a2 * c * (-b2 - 4 * b * c + c2)
					+ a3 * (-2 * b2 - b * c + c2)
					+ a * (2 * b4 + b3 * c - b2 * c2 - b * c3 - c4)));
			case 1243 -> a * (a5 * (b + c) - a4 * b * (b + c)
					- b * p(b - c, 3) * p(b + c, 2) + 2 * a2 * (b4 - b2 * c2)
					- 2 * a3 * (b3 + b2 * c + c3)
					+ a * (b5 + b4 * c - 2 * b2 * c3 - b * c4 + c5))
					* (a5 * (b + c) - a4 * c * (b + c)
					+ p(b - c, 3) * c * p(b + c, 2)
					- 2 * a3 * (b3 + b * c2 + c3)
					+ a2 * (-2 * b2 * c2 + 2 * c4)
					+ a * (b5 - b4 * c - 2 * b3 * c2 + b * c4 + c5));
			case 1244 -> a * (a4 * c + a2 * c3 + b2 * c * (b2 - c2) + a3 * (-b2 + c2)
					+ a * (b4 + 2 * b3 * c + c4))
					* (a4 * b + a2 * b3 - b3 * c2 + b * c4 + a3 * (b2 - c2)
					+ a * (b4 + 2 * b * c3 + c4));
			case 1245 -> a2 * (b + c) * (a2 + 2 * a * b + b2 + c2)
					* (a2 + b2 + 2 * a * c + c2);
			case 1246 -> -((-(b3 * c) + b * c3 + a3 * (b + c)
					- a * (b - c) * p(b + c, 2) + a2 * c * (b + 2 * c))
					* (a3 * (b + c) + a * (b - c) * p(b + c, 2)
					+ a2 * b * (2 * b + c) + b * c * (b2 - c2)));
			case 1247 -> a * (a3 - b3 - 2 * b2 * c + c3 - a * b * (2 * b + c))
					* (a3 + b3 - 2 * b * c2 - c3 - a * c * (b + 2 * c));
			case 1248 -> a * (a6 * (b - c) * p(b + c, 2)
					+ b2 * p(b - c, 2) * c2 * p(b + c, 3)
					+ a7 * (b2 + b * c - c2) + a * b * c * Q * (b2 + b * c + c2)
					+ a2 * (b - c) * p(b3 + b2 * c + b * c2 + c3, 2)
					- a5 * (2 * b4 + b3 * c + b2 * c2 + b * c3 - 2 * c4)
					- a4 * (2 * b5 + 2 * b4 * c + b3 * c2 + b2 * c3 - 2 * b * c4
					- 2 * c5)
					+ a3 * (b6 - b5 * c + b4 * c2 + 2 * b3 * c3 - b2 * c4
					- b * c5 - c6))
					* (a6 * (b - c) * p(b + c, 2)
					- b2 * p(b - c, 2) * c2 * p(b + c, 3)
					+ a7 * (b2 - b * c - c2)
					- a * b * c * Q * (b2 + b * c + c2)
					+ a2 * (b - c) * p(b3 + b2 * c + b * c2 + c3, 2)
					+ a5 * (-2 * b4 + b3 * c + b2 * c2 + b * c3
					+ 2 * c4)
					+ a4 * (-2 * b5 - 2 * b4 * c + b3 * c2 + b2 * c3
					+ 2 * b * c4 + 2 * c5)
					+ a3 * (b6 + b5 * c + b4 * c2 - 2 * b3 * c3
					- b2 * c4 + b * c5 - c6));
			case 1249 -> U * V * (3 * a4 - Q - 2 * a2 * R);
			case 1250 -> a2 * (u(3) * (-a + b + c) * (a + b + c) - S);
			case 1251 -> a / ((a + b - c) * (a - b + c) - u(3) * S);
			case 1252 -> a2 * p(a - b, 2) * p(a - c, 2);
			case 1253 -> a3 * p(-a + b + c, 2);
			case 1254 -> a * p(a + b - c, 2) * p(a - b + c, 2) * p(b + c, 2);
			case 1255 -> a * (a + 2 * b + c) * (a + b + 2 * c);
			case 1256 -> a * p(
					a3 + a2 * (b - c) - a * p(b - c, 2) - (b - c) * p(b + c, 2),
					2)
					* p(a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2), 2);
			case 1257 -> a * (a3 + 2 * b3 - a2 * c + b2 * c + c3 + a * (b2 - c2))
					* (a3 - a2 * b + b3 + b * c2 + 2 * c3 + a * (-b2 + c2));
			case 1258 -> a * (a * b2 + b2 * c + a2 * (b + c))
					* (a * c2 + b * c2 + a2 * (b + c));
			case 1259 -> a2 * (a - b - c) * (T * T);
			case 1260 -> a2 * p(-a + b + c, 2) * T;
			case 1261 -> a * (a - b - c) * (a2 + a * (-2 * b + c) + b * (b + c))
					* (a2 + a * (b - 2 * c) + c * (b + c));
			case 1262 -> a2 * p(a - b, 2) * p(a - c, 2) * p(a + b - c, 2)
					* p(a - b + c, 2);
			case 1263 -> (-Q + a2 * R)
					* (a6 - p(b2 - c2, 3) - a4 * (3 * b2 + c2)
					+ a2 * (3 * b4 + b2 * c2 - c4))
					* (a6 + p(b2 - c2, 3) - a4 * (b2 + 3 * c2)
					+ a2 * (-b4 + b2 * c2 + 3 * c4));
			case 1264 -> -((a - b - c) * (T * T));
			case 1265 -> -(p(-a + b + c, 2) * T);
			case 1266 -> b2 - 4 * b * c + c2 + a * (b + c);
			case 1267 -> 2 * b * c + S;
			case 1268 -> (a + 2 * b + c) * (a + b + 2 * c);
			case 1269 -> b2 * c2 * (2 * a + b + c);
			case 1270 -> -2 * T + S;
			case 1271 -> 2 * T + S;
			case 1272 -> a8 - 4 * a6 * R + Q * (b4 + 4 * b2 * c2 + c4)
					+ a4 * (6 * b4 + b2 * c2 + 6 * c4)
					+ a2 * (-4 * b6 + b4 * c2 + b2 * c4 - 4 * c6);
			case 1273 -> -((a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2)
					* (-Q + a2 * R));
			case 1274 -> 1 / (u(a * (-a + b + c))
					* (2 * b * c - u(-(b * (a - b - c) * c * (a + b + c)))));
			case 1275 -> p(a - b, 2) * p(a - c, 2) * p(a + b - c, 2)
					* p(a - b + c, 2);
			case 1276 -> u(3) * a
					* (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)
					- a * (a - b - c) * S;
			case 1277 -> u(3) * a
					* (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)
					+ a * (a - b - c) * S;
			case 1278 -> 3 * b * c - a * (b + c);
			case 1279 -> a * (2 * a2 + p(b - c, 2) - a * (b + c));
			case 1280 -> a * (a2 + b2 - b * c + 2 * c2 - a * (2 * b + c))
					* (a2 + 2 * b2 - b * c + c2 - a * (b + 2 * c));
			case 1281 -> (a2 - b * c) * (a3 - b3 + a * b * c - c3);
			case 1282 -> a * (a4 + a3 * (b + c) - p(b - c, 2) * (b2 + b * c + c2)
					- a2 * (2 * b2 + 3 * b * c + 2 * c2)
					+ a * (b3 + b2 * c + b * c2 + c3));
			case 1283 -> a2 * (a4 - b4 + b2 * c2 - c4 - a3 * (b + c) + a * (b3 + c3));
			case 1284 -> a * (a + b - c) * (a - b + c) * (b + c) * (a2 - b * c);
			case 1285 -> a * (9 * a4 - Q);
			case 1286 -> (a - b) * (a + b) * (a - c) * (a + c)
					* (a6 + a4 * (b2 - c2) + Q * R
					+ a2 * (b4 - 2 * b2 * c2 - c4))
					* (a6 + a4 * (-b2 + c2) + Q * R
					+ a2 * (-b4 - 2 * b2 * c2 + c4));
			case 1287 -> (a - b) * (a + b) * (a - c) * (a + c)
					* (a6 + a4 * (b2 - c2) + Q * R + a2 * (b4 - b2 * c2 - c4))
					* (a6 + a4 * (-b2 + c2) + Q * R - a2 * (b4 + b2 * c2 - c4));
			case 1288 -> (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (a8 + 2 * a4 * b4 - 2 * a6 * R + p(b2 - c2, 3) * R
					- 2 * a2 * (b6 - c6))
					* (a8 + 2 * a4 * c4 - 2 * a6 * R - p(b2 - c2, 3) * R
					+ 2 * a2 * (b6 - c6));
			case 1289 -> (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (a4 + b4 - c4) * (a4 - b4 + c4);
			case 1290 -> a * (a - b) * (a - c)
					* (a3 + a2 * (b - c) + p(b - c, 2) * (b + c)
					+ a * (b2 - b * c - c2))
					* (a3 + a2 * (-b + c) + p(b - c, 2) * (b + c)
					- a * (b2 + b * c - c2));
			case 1291 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a6 - p(b2 - c2, 3) - a4 * (3 * b2 + c2)
					+ a2 * (3 * b4 + b2 * c2 - c4))
					* (a6 + p(b2 - c2, 3) - a4 * (b2 + 3 * c2)
					+ a2 * (-b4 + b2 * c2 + 3 * c4));
			case 1292 -> a * (a - b) * (a2 - 2 * a * b + p(b - c, 2)) * (a - c)
					* (a2 + p(b - c, 2) - 2 * a * c);
			case 1293 -> a2 * (a - b) * (a + b - 3 * c) * (a - c) * (a - 3 * b + c);
			case 1294 -> (a8 + c2 * p(-b2 + c2, 3) + a6 * (-3 * b2 + 2 * c2)
					+ 3 * a4 * (b4 + b2 * c2 - 2 * c4)
					- a2 * (b6 + 4 * b4 * c2 - 3 * b2 * c4 - 2 * c6))
					* (a8 + a6 * (2 * b2 - 3 * c2) + b2 * p(b2 - c2, 3)
					+ 3 * a4 * (-2 * b4 + b2 * c2 + c4)
					+ a2 * (2 * b6 + 3 * b4 * c2 - 4 * b2 * c4 - c6));
			case 1295 -> a * (a6 - a5 * b - p(b - c, 3) * c * p(b + c, 2)
					- a4 * (2 * b2 - 3 * b * c + c2) + 2 * a3 * (b3 - b * c2)
					+ a2 * (b4 - 2 * b3 * c + 4 * b2 * c2 - 2 * b * c3 - c4)
					- a * (b5 + 2 * b3 * c2 - 3 * b * c4))
					* (a6 - a5 * c + b * p(b - c, 3) * p(b + c, 2)
					- a4 * (b2 - 3 * b * c + 2 * c2)
					+ a3 * (-2 * b2 * c + 2 * c3)
					+ a2 * (-b4 - 2 * b3 * c + 4 * b2 * c2 - 2 * b * c3
					+ c4)
					+ a * (3 * b4 * c - 2 * b2 * c3 - c5));
			case 1296 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a2 + b2 - 5 * c2) * (a2 - 5 * b2 + c2);
			case 1297 -> a2 * (a6 - 2 * b6 - a4 * c2 + b4 * c2 + c6 + a2 * (b4 - c4))
					* (a6 - a4 * b2 + b6 + b2 * c4 - 2 * c6 + a2 * (-b4 + c4));
			case 1298 -> a2 * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2))
					* (a6 * b2 + c4 * Q + a4 * (-2 * b4 + c4)
					+ a2 * (b6 + b2 * c4 - 2 * c6))
					* (a6 * c2 + b4 * Q + a4 * (b4 - 2 * c4)
					+ a2 * (-2 * b6 + b4 * c2 + c6));
			case 1299 -> a2 * U * V
					* (a8 - a6 * (b2 + 4 * c2) + Q * (2 * b4 + b2 * c2 + c4)
					+ a4 * (b4 + b2 * c2 + 6 * c4)
					+ a2 * (-3 * b6 + 2 * b4 * c2 + b2 * c4 - 4 * c6))
					* (a8 - a6 * (4 * b2 + c2) + a4 * (6 * b4 + b2 * c2 + c4)
					+ Q * (b4 + b2 * c2 + 2 * c4)
					+ a2 * (-4 * b6 + b4 * c2 + 2 * b2 * c4 - 3 * c6));
			default -> Double.NaN;
		};
	}

	private double weight1300to1399(int k, double a, double b, double c) {

		return switch (k) {
			case 1300 -> U * V
					* (a6 - a4 * (2 * b2 + c2) + p(-(b2 * c) + c3, 2)
					+ a2 * (b4 + 2 * b2 * c2 - c4))
					* (a6 - a4 * (b2 + 2 * c2) + p(b3 - b * c2, 2)
					+ a2 * (-b4 + 2 * b2 * c2 + c4));
			case 1301 -> a2 * (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (a4 + b4 + 2 * b2 * c2 - 3 * c4 - 2 * a2 * (b2 - c2))
					* (a4 - 3 * b4 + 2 * b2 * c2 + c4 + 2 * a2 * (b2 - c2));
			case 1302 -> (a - b) * (a + b) * (a - c) * (a + c)
					* (a4 - 2 * a2 * (b2 - 2 * c2) + Q)
					* (a4 + a2 * (4 * b2 - 2 * c2) + Q);
			case 1303 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a6 * c2 - b4 * Q - a4 * (b4 + 2 * c4)
					+ a2 * (2 * b6 - 3 * b4 * c2 + c6))
					* (a6 * b2 - c4 * Q - a4 * (2 * b4 + c4)
					+ a2 * (b6 - 3 * b2 * c4 + 2 * c6));
			case 1304 -> a2 * (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (a4 - 2 * b4 + b2 * c2 + c4 + a2 * (b2 - 2 * c2))
					* (a4 + b4 + b2 * c2 - 2 * c4 + a2 * (-2 * b2 + c2));
			case 1305 -> (a - b) * (a - c) * (a + b - c) * (a - b + c)
					* (a3 - b2 * c + c3 - a * b * (b + c))
					* (a3 + b3 - b * c2 - a * c * (b + c));
			case 1306 -> -(a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a4 - 2 * a2 * b2 + b4 - 2 * a2 * c2 - 6 * b2 * c2 + c4))
					+ 2 * a2 * (a - b) * (a + b) * (a - c) * (a + c) * R * S;
			case 1307 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a4 - 2 * a2 * b2 + b4 - 2 * a2 * c2 - 6 * b2 * c2 + c4)
					+ 2 * a2 * (a - b) * (a + b) * (a - c) * (a + c) * R * S;
			case 1308 -> a * (a - b) * (a2 + a * (b - 2 * c) + p(b - c, 2)) * (a - c)
					* (a2 + p(b - c, 2) + a * (-2 * b + c));
			case 1309 -> (a - b) * (a - c) * U * V
					* (a3 - a2 * b + b3 - a * p(b - c, 2) - b * c2)
					* (a3 - a * p(b - c, 2) - a2 * c - b2 * c + c3);
			case 1310 -> a * (a - b) * (a - c) * (a2 + 2 * a * b + b2 + c2)
					* (a2 + b2 + 2 * a * c + c2);
			case 1311 -> (a4 - a3 * c - b2 * c2 + a * (b - c) * c2 + c4
					+ a2 * b * (-b + c))
					* (a4 - a3 * b + b4 + a2 * (b - c) * c - b2 * c2
					+ a * b2 * (-b + c));
			case 1312 -> -((a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4)
					* (a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2 + 3 * a2 * b2 * c2
					- b4 * c2 - a2 * c4 - b2 * c4 + c6))
					- a * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2
					- c4)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 1313 -> -((a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4)
					* (a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2 + 3 * a2 * b2 * c2
					- b4 * c2 - a2 * c4 - b2 * c4 + c6))
					+ a * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2
					- c4)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 1314 -> (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* u(-a10 - 2 * a7 * p(b - c, 2) * (b + c)
					+ 2 * a * b * p(b - c, 2) * c * p(b + c, 3) * R
					+ 2 * a8 * (b2 + b * c + c2)
					- a6 * p(b2 + b * c + c2, 2)
					- p(b + c, 4)
					* p(b3 - 2 * b2 * c + 2 * b * c2 - c3, 2)
					+ 2 * a5 * p(b - c, 2)
					* (2 * b3 + 3 * b2 * c + 3 * b * c2
					+ 2 * c3)
					- a4 * p(b + c, 2) * (b4 - 3 * b2 * c2 + c4)
					+ a2 * Q * (2 * b4 + 2 * b3 * c + b2 * c2
					+ 2 * b * c3 + 2 * c4)
					- 2 * a3 * p(b - c, 2)
					* (b5 + 3 * b4 * c + 6 * b3 * c2
					+ 6 * b2 * c3 + 3 * b * c4 + c5))
					+ (a + b) * (a + c)
					* (2 * a5 - 2 * a4 * b + a2 * b3 - 2 * a * b4 + b5
					- 2 * a4 * c + 2 * a3 * b * c - a2 * b2 * c
					+ b4 * c - a2 * b * c2 + 4 * a * b2 * c2
					- 2 * b3 * c2 + a2 * c3 - 2 * b2 * c3
					- 2 * a * c4 + b * c4 + c5)
					* S;
			case 1315 -> -((2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* u(-a10 - 2 * a7 * p(b - c, 2) * (b + c)
					+ 2 * a * b * p(b - c, 2) * c * p(b + c, 3) * R
					+ 2 * a8 * (b2 + b * c + c2)
					- a6 * p(b2 + b * c + c2, 2)
					- p(b + c, 4)
					* p(b3 - 2 * b2 * c + 2 * b * c2 - c3, 2)
					+ 2 * a5 * p(b - c, 2)
					* (2 * b3 + 3 * b2 * c + 3 * b * c2
					+ 2 * c3)
					- a4 * p(b + c, 2) * (b4 - 3 * b2 * c2 + c4)
					+ a2 * Q * (2 * b4 + 2 * b3 * c + b2 * c2
					+ 2 * b * c3 + 2 * c4)
					- 2 * a3 * p(b - c, 2)
					* (b5 + 3 * b4 * c + 6 * b3 * c2
					+ 6 * b2 * c3 + 3 * b * c4 + c5)))
					+ (a + b) * (a + c)
					* (2 * a5 - 2 * a4 * b + a2 * b3 - 2 * a * b4 + b5
					- 2 * a4 * c + 2 * a3 * b * c - a2 * b2 * c
					+ b4 * c - a2 * b * c2 + 4 * a * b2 * c2
					- 2 * b3 * c2 + a2 * c3 - 2 * b2 * c3
					- 2 * a * c4 + b * c4 + c5)
					* S;
			case 1316 -> a8 + a4 * b2 * c2 + b2 * c2 * Q - a6 * R;
			case 1317 -> (a + b - c) * (a - b + c) * p(-2 * a + b + c, 2);
			case 1318 -> a2 * p(a + b - 2 * c, 2) * (a - b - c) * p(a - 2 * b + c, 2);
			case 1319 -> a * (2 * a - b - c) * (a + b - c) * (a - b + c);
			case 1320 -> a * (a + b - 2 * c) * (a - b - c) * (a - 2 * b + c);
			case 1321 -> U * V
					* (a6 - 3 * a2 * b4 + 2 * b6 - 2 * a2 * b2 * c2
					- 2 * b4 * c2 - 3 * a2 * c4 - 2 * b2 * c4 + 2 * c6)
					+ U * V * (2 * a4 - 3 * a2 * b2 + b4 - 3 * a2 * c2
					- 2 * b2 * c2 + c4) * S;
			case 1322 -> -(U * V
					* (a6 - 3 * a2 * b4 + 2 * b6 - 2 * a2 * b2 * c2
					- 2 * b4 * c2 - 3 * a2 * c4 - 2 * b2 * c4 + 2 * c6))
					+ U * V * (2 * a4 - 3 * a2 * b2 + b4 - 3 * a2 * c2
					- 2 * b2 * c2 + c4) * S;
			case 1323 -> (a + b - c) * (a - b + c)
					* (2 * a2 - p(b - c, 2) - a * (b + c));
			case 1324 -> a2 * (a5 - b5 + b3 * c2 + b2 * c3 - c5 - a * b * c * R
					- a3 * (b2 - b * c + c2) + a2 * (b3 + c3));
			case 1325 -> a * (a + b) * (a + c)
					* (a4 + a2 * b * c - a * b * c * (b + c) - Q);
			case 1326 -> a2 * (a + b) * (a + c)
					* (a2 - b2 - b * c - c2 + a * (b + c));
			case 1327 -> 4 * a4 + a2 * b2 - 5 * b4 + a2 * c2 + 10 * b2 * c2 - 5 * c4
					+ 3 * a2 * S;
			case 1328 -> -4 * a4 - a2 * b2 + 5 * b4 - a2 * c2 - 10 * b2 * c2 + 5 * c4
					+ 3 * a2 * S;
			case 1329 -> -((a - b - c) * (p(b - c, 2) * (b + c) + a * R));
			case 1330 -> -a4 + b4 - a2 * b * c + b3 * c + b * c3 + c4 - a3 * (b + c)
					+ a * (b3 + b2 * c + b * c2 + c3);
			case 1331 -> a2 * (a - b) * (a - c) * T;
			case 1332 -> a * (a - b) * (a - c) * T;
			case 1333 -> a3 * (a + b) * (a + c);
			case 1334 -> a2 * (a - b - c) * (b + c);
			case 1335 -> -2 * a2 * b * c + a2 * S;
			case 1336 -> a4 - 2 * a2 * b2 + b4 - 4 * a2 * b * c - 2 * a2 * c2
					- 2 * b2 * c2 + c4 + 2 * a * (b + c) * S;
			case 1337 -> 3 * a2
					* (a8 - a6 * b2 - 3 * a4 * b4 + 5 * a2 * b6 - 2 * b8
					- a6 * c2 - 9 * a4 * b2 * c2 + 3 * a2 * b4 * c2
					+ 7 * b6 * c2 - 3 * a4 * c4 + 3 * a2 * b2 * c4
					- 10 * b4 * c4 + 5 * a2 * c6 + 7 * b2 * c6 - 2 * c8)
					+ u(3) * a2
					* (a6 - 6 * a4 * b2 + 3 * a2 * b4 + 2 * b6
					- 6 * a4 * c2 - a2 * b2 * c2 + b4 * c2
					+ 3 * a2 * c4 + b2 * c4 + 2 * c6)
					* S;
			case 1338 -> -3 * a2
					* (a8 - a6 * b2 - 3 * a4 * b4 + 5 * a2 * b6 - 2 * b8
					- a6 * c2 - 9 * a4 * b2 * c2 + 3 * a2 * b4 * c2
					+ 7 * b6 * c2 - 3 * a4 * c4 + 3 * a2 * b2 * c4
					- 10 * b4 * c4 + 5 * a2 * c6 + 7 * b2 * c6 - 2 * c8)
					+ u(3) * a2
					* (a6 - 6 * a4 * b2 + 3 * a2 * b4 + 2 * b6
					- 6 * a4 * c2 - a2 * b2 * c2 + b4 * c2
					+ 3 * a2 * c4 + b2 * c4 + 2 * c6)
					* S;
			case 1339 -> a * (a + b - 3 * c) * (2 * a - b - c) * (a - 3 * b + c)
					* (a3 - b3 + 3 * b2 * c + 3 * b * c2 - c3 + a2 * (b + c)
					- a * (b2 + 4 * b * c + c2));
			case 1340 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					+ a2 * T * u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 1341 -> -(a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2))
					+ a2 * T * u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 1342 -> -a4 + a2 * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1343 -> -(a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2))
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1344 -> -(a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4))
					+ a2 * T * u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4 - b2 * c4
					+ c6);
			case 1345 -> a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					+ a2 * T * u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4 - b2 * c4
					+ c6);
			case 1346 -> -(a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4))
					+ (-(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 1347 -> a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					+ (-(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 1348 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					+ (-(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 1349 -> -(a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2))
					+ (-(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 1350 -> a2 * (a4 - 3 * b4 - 2 * b2 * c2 - 3 * c4 + 2 * a2 * R);
			case 1351 -> a2 * (a4 + 3 * b4 - 2 * b2 * c2 + 3 * c4 - 4 * a2 * R);
			case 1352 -> -a6 + a4 * R + Q * R - a2 * p(b2 + c2, 2);
			case 1353 -> 4 * a6 - 7 * a4 * R - Q * R + 4 * a2 * (b4 - b2 * c2 + c4);
			case 1354 -> (a + b - c) * (a - b + c) * p(-2 * a4 + Q + a2 * R, 2);
			case 1355 -> a4 * (a + b - c) * (a - b + c) * p(b4 + c4 - a2 * R, 2);
			case 1356 -> -(a4 * p(b - c, 2) * (a + b - c) * (a - b + c)
					* p(b + c, 2));
			case 1357 -> -(a2 * p(b - c, 2) * (a + b - c) * (a - b + c));
			case 1358 -> p(b - c, 2) * (-a + b - c) * (a + b - c);
			case 1359 -> (a + b - c) * (a - b + c) * p(-2 * a4 + a2 * p(b - c, 2)
					+ a3 * (b + c) - a * p(b - c, 2) * (b + c) + Q, 2);
			case 1360 -> (a + b - c) * (a - b + c)
					* p((-2 * a3 + a2 * (b + c) + p(b - c, 2) * (b + c)), 2);
			case 1361 -> a2 * (a + b - c) * (a - b + c) * p(
					2 * a * b * c - a2 * (b + c) + p(b - c, 2) * (b + c), 2);
			case 1362 -> a2 * (a + b - c) * (a - b + c) * (b2 + c2 - a * (b + c))
					* (b2 + c2 - a * (b + c));
			case 1363 -> -(a4 * p(b - c, 2) * (a + b - c) * (a - b + c) * p(b + c, 2)
					* p(-a2 + b2 + c2, 4));
			case 1364 -> -(a2 * (a - b - c) * p(b - c, 2) * (T * T));
			case 1365 -> p(b - c, 2) * (-a + b - c) * (a + b - c) * p(b + c, 2);
			case 1366 -> (a + b - c) * (a - b + c) * p(-2 * a2 + b2 + c2, 2);
			case 1367 -> p(b - c, 2) * (-a + b - c) * (a + b - c) * p(b + c, 2)
					* (T * T);
			case 1368 -> -(T * (Q + a2 * R));
			case 1369 -> -a6 + b6 + b4 * c2 + b2 * c4 + c6 - a4 * R
					+ a2 * (b4 + b2 * c2 + c4);
			case 1370 -> -a6 - a4 * R + Q * R + a2 * p(b2 + c2, 2);
			case 1371 -> 3 * a * (a - b - c) * (a + b - c) * (a - b + c)
					- 2 * (a + b - c) * (a - b + c) * S;
			case 1372 -> -3 * a * (a - b - c) * (a + b - c) * (a - b + c)
					- 2 * (a + b - c) * (a - b + c) * S;
			case 1373 -> a * (a - b - c) * (a + b - c) * (a - b + c)
					- 2 * (a + b - c) * (a - b + c) * S;
			case 1374 -> -(a * (a - b - c) * (a + b - c) * (a - b + c))
					- 2 * (a + b - c) * (a - b + c) * S;
			case 1375 -> 2 * a5 + b5 - b4 * c - b * c4 + c5
					- a2 * p(b - c, 2) * (b + c) - a * Q - a3 * R;
			case 1376 -> a * (a2 + 2 * b * c - a * (b + c));
			case 1377 -> 2 * a * b * c * (b + c) + a2 * S;
			case 1378 -> -2 * a * b * c * (b + c) + a2 * S;
			case 1379 -> -(a2 * (a2 * b2 - b4 + a2 * c2 - c4))
					+ a2 * T * u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 1380 -> a2 * (a2 * b2 - b4 + a2 * c2 - c4)
					+ a2 * T * u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 1381 -> -(a2 * b * c
					* (a2 * b - b3 + a2 * c - 2 * a * b * c + b2 * c + b * c2
					- c3))
					+ a2 * T * u(a * b * c
					* (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 3 * a * b * c - b2 * c - a * c2 - b * c2
					+ c3));
			case 1382 -> a2 * b * c
					* (a2 * b - b3 + a2 * c - 2 * a * b * c
					+ b2 * c + b
					* c2
					- c3)
					+ a2 * T * u(a * b * c
					* (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 3 * a * b * c - b2 * c - a * c2 - b * c2
					+ c3));
			case 1383 -> a2 * (2 * a2 + 2 * b2 - c2) * (2 * a2 - b2 + 2 * c2);
			case 1384 -> a2 * (5 * a2 - b2 - c2);
			case 1385 -> a * (2 * a3 - a2 * (b + c) + p(b - c, 2) * (b + c)
					- 2 * a * (b2 - b * c + c2));
			case 1386 -> a * (2 * a2 + b2 + c2 + a * (b + c));
			case 1387 -> 2 * a4 - 2 * a3 * (b + c) + 2 * a * p(b - c, 2) * (b + c)
					+ a2 * (-3 * b2 + 8 * b * c - 3 * c2) + Q;
			case 1388 -> a * (a + b - c) * (a - b + c) * (3 * a - 2 * (b + c));
			case 1389 -> a * (a3 + 2 * b3 - a * p(b - c, 2) - b2 * c - 2 * b * c2 + c3
					- a2 * (2 * b + c))
					* (a3 + b3 - a * p(b - c, 2) - 2 * b2 * c - b * c2 + 2 * c3
					- a2 * (b + 2 * c));
			case 1390 -> a * (a2 + a * b + 2 * b2 + b * c + c2)
					* (a2 + b2 + a * c + b * c + 2 * c2);
			case 1391 -> a2
					* (a4 + 2 * a3 * b + 2 * b4 - 2 * b3 * c - 3 * b2 * c2
					+ 2 * b * c3 + c4
					- 2 * a * b * (b2 - 4 * b * c + c2)
					- a2 * (3 * b2 + 2 * b * c + 2 * c2))
					* (a4 + b4 + 2 * a3 * c + 2 * b3 * c - 3 * b2 * c2
					- 2 * b * c3 + 2 * c4
					- 2 * a * c * (b2 - 4 * b * c + c2)
					- a2 * (2 * b2 + 2 * b * c + 3 * c2));
			case 1392 -> a * (2 * a + 2 * b - 3 * c) * (a - b - c)
					* (2 * a - 3 * b + 2 * c);
			case 1393 -> a * (a + b - c) * (a - b + c) * (-Q + a2 * R);
			case 1394 -> a * (a + b - c) * (a - b + c) * (3 * a4 - Q - 2 * a2 * R);
			case 1395 -> a3 * (a + b - c) * (a - b + c) * U * V;
			case 1396 -> a * (a + b) * (a + b - c) * (a + c) * (a - b + c) * U * V;
			case 1397 -> a4 * (a + b - c) * (a - b + c);
			case 1398 -> a2 * p(a + b - c, 2) * p(a - b + c, 2) * U * V;
			case 1399 -> a3 * (a + b - c) * (a - b + c) * (a2 - b2 - b * c - c2);
			default -> Double.NaN;
		};
	}

	private double weight1400to1499(int k, double a, double b, double c) {

		return switch (k) {
			case 1400 -> a2 * (a + b - c) * (a - b + c) * (b + c);
			case 1401 -> a2 * (a + b - c) * (a - b + c) * R;
			case 1402 -> a3 * (a + b - c) * (a - b + c) * (b + c);
			case 1403 -> a2 * (a + b - c) * (a - b + c) * (-(b * c) + a * (b + c));
			case 1404 -> a2 * (2 * a - b - c) * (a + b - c) * (a - b + c);
			case 1405 -> a2 * (a + b - c) * (a - b + c) * (a - 2 * (b + c));
			case 1406 -> a2 * (a + b - c) * (a - b + c)
					* (a3 + a2 * (b + c) - p(b - c, 2) * (b + c) - a * R);
			case 1407 -> a2 * p(a + b - c, 2) * p(a - b + c, 2);
			case 1408 -> a3 * (a + b) * (a + b - c) * (a + c) * (a - b + c);
			case 1409 -> a3 * (a + b - c) * (a - b + c) * (b + c) * T;
			case 1410 -> a3 * p(a + b - c, 2) * p(a - b + c, 2) * (b + c) * T;
			case 1411 -> a * (a + b - c) * (a - b + c) * (a2 - a * b + b2 - c2)
					* (a2 - b2 - a * c + c2);
			case 1412 -> a2 * (a + b) * (a + b - c) * (a + c) * (a - b + c);
			case 1413 -> a2 * (a + b - c) * (a - b + c)
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 1414 -> a * (a - b) * (a + b) * (a - c) * (a + b - c) * (a + c)
					* (a - b + c);
			case 1415 -> a3 * (a - b) * (a - c) * (a + b - c) * (a - b + c);
			case 1416 -> a2 * (a + b - c) * (a - b + c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 1417 -> a3 * (a + b - 2 * c) * (a + b - c) * (a - 2 * b + c)
					* (a - b + c);
			case 1418 -> a * (a + b - c) * (a - b + c) * (-p(b - c, 2) + a * (b + c));
			case 1419 -> a * (a + b - c) * (a - b + c)
					* (3 * a2 - p(b - c, 2) - 2 * a * (b + c));
			case 1420 -> a * (3 * a - b - c) * (a + b - c) * (a - b + c);
			case 1421 -> a * (a + b - c) * (a - b + c) * (a3 - a2 * (b + c)
					- p(b - c, 2) * (b + c) + a * (b2 - b * c + c2));
			case 1422 -> a * (a + b - c) * (a - b + c)
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 1423 -> a * (a + b - c) * (a - b + c) * (-(b * c) + a * (b + c));
			case 1424 -> a * (a + b - c) * (a - b + c) * (-(b2 * c2) + a2 * R);
			case 1425 -> a2 * p(a + b - c, 2) * p(a - b + c, 2) * p(b + c, 2) * T;
			case 1426 -> a * p(a + b - c, 2) * p(a - b + c, 2) * (b + c) * U * V;
			case 1427 -> a * p(a + b - c, 2) * p(a - b + c, 2) * (b + c);
			case 1428 -> a2 * (a + b - c) * (a - b + c) * (a2 - b * c);
			case 1429 -> a * (a + b - c) * (a - b + c) * (a2 - b * c);
			case 1430 -> a * U * V
					* (a4 - b * p(b - c, 2) * c - a2 * (b2 - b * c + c2));
			case 1431 -> a2 * (a + b - c) * (a - b + c) * (b2 + a * c) * (a * b + c2);
			case 1432 -> -(a * (a + b - c) * (a - b + c) * (b2 + a * c)
					* (a * b + c2));
			case 1433 -> a2 * T
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 1434 -> (a + b) * (a + b - c) * (a + c) * (a - b + c);
			case 1435 -> a * p(a + b - c, 2) * p(a - b + c, 2) * U * V;
			case 1436 -> a2
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 1437 -> a3 * (a + b) * (a + c) * T;
			case 1438 -> a2 * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 1439 -> a * p(a + b - c, 2) * p(a - b + c, 2) * (b + c) * T;
			case 1440 -> (a + b - c) * (a - b + c)
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 1441 -> b * (-a + b - c) * (a + b - c) * c * (b + c);
			case 1442 -> a * (a + b - c) * (a - b + c) * (a2 - b2 - b * c - c2);
			case 1443 -> a * (a + b - c) * (a - b + c) * (a2 - b2 + b * c - c2);
			case 1444 -> a * (a + b) * (a + c) * T;
			case 1445 -> a * (a + b - c) * (a - b + c)
					* (a2 + b2 + c2 - 2 * a * (b + c));
			case 1446 -> b * p(a + b - c, 2) * c * p(a - b + c, 2) * (b + c);
			case 1447 -> (a + b - c) * (a - b + c) * (a2 - b * c);
			case 1448 -> a * (a + b - c) * (a - b + c)
					* (a4 + 2 * a2 * b * c + 2 * a * b * c * (b + c) - Q);
			case 1449 -> a * (3 * a + b + c);
			case 1450 -> a2 * (a + b - c) * (a - b + c) * (a2 * b - b3 + a2 * c
					- 4 * a * b * c - b2 * c - b * c2 - c3);
			case 1451 -> a2 * (a + b - c) * (a - b + c)
					* (a3 - 2 * b * c * (b + c) - a * p(b + c, 2));
			case 1452 -> a * (a + b - c) * (a - b + c) * U * V * (a3 - b3 - b2 * c
					- b * c2 - c3 + a2 * (b + c) - a * p(b + c, 2));
			case 1453 -> a * (3 * a3 + 3 * a2 * (b + c) + p(b - c, 2) * (b + c)
					+ a * p(b + c, 2));
			case 1454 -> a * (a + b - c) * (a - b + c)
					* (a4 + Q - 2 * a2 * (b2 + b * c + c2));
			case 1455 -> a * (a + b - c) * (a - b + c) * (2 * a4 - a2 * p(b - c, 2)
					- a3 * (b + c) + a * p(b - c, 2) * (b + c) - Q);
			case 1456 -> a * (a + b - c) * (a - b + c)
					* (2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1457 -> a2 * (a + b - c) * (a - b + c)
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1458 -> a2 * (a + b - c) * (a - b + c) * (-b2 - c2 + a * (b + c));
			case 1459 -> a2 * (b - c) * T;
			case 1460 -> a2 * (a + b - c) * (a - b + c) * (a2 + p(b + c, 2));
			case 1461 -> a2 * (a - b) * (a - c) * p(a + b - c, 2) * p(a - b + c, 2);
			case 1462 -> a * (a + b - c) * (a - b + c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 1463 -> a * (a + b - c) * (a - b + c) * (-(b * c * (b + c)) + a * R);
			case 1464 -> a2 * (a + b - c) * (a - b + c) * (b + c)
					* (a2 - b2 + b * c - c2);
			case 1465 -> a * (a + b - c) * (a - b + c)
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1466 -> a2 * (a + b - c) * (a - b + c)
					* (a3 - a * p(b - c, 2) - a2 * (b + c) + p(b + c, 3));
			case 1467 -> a * (a + b - c) * (a - b + c) * (a4 - 4 * a2 * b * c
					- 2 * a3 * (b + c) + 2 * a * p(b - c, 2) * (b + c) - Q);
			case 1468 -> a2 * (a2 + 2 * b * c + a * (b + c));
			case 1469 -> a2 * (a + b - c) * (a - b + c) * (b2 + b * c + c2);
			case 1470 -> a2 * (a + b - c) * (a - b + c) * (a3 + b3 - a * p(b - c, 2)
					+ b2 * c + b * c2 + c3 - a2 * (b + c));
			case 1471 -> a2 * (a + b - c) * (a - b + c)
					* (a2 - 2 * b * c - a * (b + c));
			case 1472 -> a3 * (a2 + 2 * a * b + b2 + c2) * (a2 + b2 + 2 * a * c + c2);
			case 1473 -> a2 * (a2 + p(b - c, 2)) * T;
			case 1474 -> a2 * (a + b) * (a + c) * U * V;
			case 1475 -> a2 * (-p(b - c, 2) + a * (b + c));
			case 1476 -> a * (a + b - c) * (a - b + c)
					* (a2 + a * (-2 * b + c) + b * (b + c))
					* (a2 + a * (b - 2 * c) + c * (b + c));
			case 1477 -> a2 * (a + b - c) * (a - b + c)
					* (a2 + b2 - b * c + 2 * c2 - a * (2 * b + c))
					* (a2 + 2 * b2 - b * c + c2 - a * (b + 2 * c));
			case 1478 -> -a4 - 2 * a2 * b * c + Q;
			case 1479 -> -a4 + 2 * a2 * b * c + Q;
			case 1480 -> a2 * (a2 - b2 + 4 * b * c - c2) * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * (b2 + 4 * b * c + c2));
			case 1481 -> a2 * (a2 - b2 + 4 * b * c - c2)
					* (a3 + a2 * (b - c) - (b - c) * p(b + c, 2)
					- a * (b2 - 4 * b * c + c2))
					* (a3 + a2 * (-b + c) + (b - c) * p(b + c, 2)
					- a * (b2 - 4 * b * c + c2));
			case 1482 -> a * (a3 - 2 * a2 * (b + c) + 2 * p(b - c, 2) * (b + c)
					- a * (b2 - 4 * b * c + c2));
			case 1483 -> 4 * a4 - 4 * a3 * (b + c) + 4 * a * p(b - c, 2) * (b + c)
					+ a2 * (-3 * b2 + 8 * b * c - 3 * c2) - Q;
			case 1484 -> p(b - c, 4) * p(b + c, 3) - a5 * (b2 - 4 * b * c + c2)
					- a * Q * (b2 - b * c + c2)
					- 2 * a2 * p(b - c, 2) * (b3 + c3)
					+ a4 * (b3 - 3 * b2 * c - 3 * b * c2 + c3) + a3 * (2 * b4
					- 5 * b3 * c + 8 * b2 * c2 - 5 * b * c3 + 2 * c4);
			case 1485 -> a2
					* (a6 - b6 + b4 * c2 - b2 * c4 + c6 - a4 * R
					+ a2 * (b4 - c4))
					* (a6 + b6 - b4 * c2 + b2 * c4 - c6 - a4 * R
					+ a2 * (-b4 + c4));
			case 1486 -> a2 * (a3 - a2 * (b + c) - p(b - c, 2) * (b + c) + a * R);
			case 1487 -> (a4 + Q - a2 * (2 * b2 + c2)) * (a4 + Q - a2 * (b2 + 2 * c2))
					* (a4 + 2 * b4 - 3 * b2 * c2 + c4 - a2 * (3 * b2 + 2 * c2))
					* (a4 + b4 - 3 * b2 * c2 + 2 * c4 - a2 * (2 * b2 + 3 * c2));
			case 1488 -> a * (b * (2 * u(a * (-a + b + c)) + u(b * (a - b + c)))
					* u(c * (a + b - c))
					+ c * u(b * (a - b + c))
					* (2 * u(a * (-a + b + c)) + u(c * (a + b - c)))
					+ a * (4 * b * c
					- u(b * (a - b + c)) * u(c * (a + b - c))));
			case 1489 -> a * (-2 * b * c + u(b * (a - b + c)) * u(c * (a + b - c))
					+ u(-(b * (a - b - c) * c * (a + b + c))));
			case 1490 -> a * (a6 - 2 * a5 * (b + c) - a4 * p(b + c, 2)
					+ p(b - c, 2) * p(b + c, 4) - a2 * Q + 4 * a3 * (b3 + c3)
					- 2 * a * (b5 - b4 * c - b * c4 + c5));
			case 1491 -> a * (-b3 + c3);
			case 1492 -> a * (a3 - b3) * (a3 - c3);
			case 1493 -> a2 * (2 * a4 + Q - 3 * a2 * R)
					* (a4 + b4 - b2 * c2 + c4 - 2 * a2 * R);
			case 1494 -> -((a4 - 2 * b4 + b2 * c2 + c4 + a2 * (b2 - 2 * c2))
					* (a4 + b4 + b2 * c2 - 2 * c4 + a2 * (-2 * b2 + c2)));
			case 1495 -> a2 * (2 * a4 - Q - a2 * R);
			case 1496 -> a3 * (a4 + b4 + 6 * b2 * c2 + c4 - 2 * a2 * R);
			case 1497 -> a3 * (a4 + b4 - 6 * b2 * c2 + c4 - 2 * a2 * R);
			case 1498 -> a2 * (a8 - 4 * a6 * R - 4 * a2 * Q * R
					+ Q * (b4 + 6 * b2 * c2 + c4)
					+ a4 * (6 * b4 - 4 * b2 * c2 + 6 * c4));
			case 1499 -> -((b2 - c2) * (-5 * a2 + b2 + c2));
			default -> Double.NaN;
		};
	}

	private double weight1500to1599(int k, double a, double b, double c) {

		return switch (k) {
			case 1500 -> a2 * p(b + c, 2);
			case 1501 -> a6;
			case 1502 -> b4 * c4;
			case 1503 -> 2 * a6 - a4 * R - Q * R;
			case 1504 -> a2 * R + a2 * S;
			case 1505 -> -(a2 * R) + a2 * S;
			case 1506 -> Q - 2 * a2 * R;
			case 1507 -> a * (1 - 2 * Math.cos(angleA / 3) + 2 * Math.cos(angleB / 3)
					+ 2 * Math.cos(angleC / 3));
			case 1508 -> a * (2 - 1 / Math.cos(angleA / 3) + 1 / Math.cos(angleB / 3)
					+ 1 / Math.cos(angleC / 3));
			case 1509 -> p(a + b, 2) * p(a + c, 2);
			case 1510 -> a2 * (b2 - c2) * (a4 + b4 - b2 * c2 + c4 - 2 * a2 * R);
			case 1511 -> a2 * (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2)
					* (2 * a4 - Q - a2 * R);
			case 1512 -> (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c)) * (a4
					- 2 * a3 * (b + c) + 2 * a * p(b - c, 2) * (b + c) - Q);
			case 1513 -> -((3 * a4 + Q) * (-b4 - c4 + a2 * R));
			case 1514 -> -((2 * a4 - Q - a2 * R)
					* (a6 - 5 * a2 * Q + a4 * R + 3 * Q * R));
			case 1515 -> -(U * V * (5 * a4 - Q - 4 * a2 * R)
					* (a6 * R + 3 * a2 * Q * R
					+ a4 * (-3 * b4 + 4 * b2 * c2 - 3 * c4)
					- Q * (b4 + 4 * b2 * c2 + c4)));
			case 1516 -> -(T
					* (4 * a8 - a4 * Q + p(b2 - c2, 4) - 5 * a6 * R
					+ a2 * Q * R)
					* (a4 * R + Q * R - 2 * a2 * (b4 - b2 * c2 + c4)));
			case 1517 -> -((5 * a6 - a4 * p(b - c, 2) - 4 * a5 * (b + c)
					- 4 * a3 * p(b - c, 2) * (b + c) + p(b - c, 4) * p(b + c, 2)
					+ 3 * a2 * Q)
					* (2 * a2 * b2 * c2 + a4 * R - 2 * a3 * (b3 + c3)
					- p(b - c, 2) * (b4 + 2 * b3 * c + 4 * b2 * c2
					+ 2 * b * c3 + c4)
					+ 2 * a * (b5 - b3 * c2 - b2 * c3 + c5)));
			case 1518 -> -((5 * a5 - a4 * (b + c) + 4 * a2 * p(b - c, 2) * (b + c)
					+ p(b - c, 2) * p(b + c, 3) - a * Q)
					* (-b5 + b4 * c - 2 * b3 * c2 - 2 * b2 * c3 + b * c4 - c5
					+ a2 * p(b - c, 2) * (b + c) + a3 * R
					- a * (b4 - 4 * b2 * c2 + c4)));
			case 1519 -> -((-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c))
					* (a4 - 2 * a2 * p(b - c, 2) + Q));
			case 1520 -> -((4 * a5 + a4 * (b + c) + 2 * a2 * p(b - c, 2) * (b + c)
					+ p(b - c, 2) * p(b + c, 3))
					* (-b5 - b3 * c2 - b2 * c3 - c5 - a * Q + a3 * R
					+ a2 * (b3 + c3)));
			case 1521 -> -((2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c))
					* (2 * a4 - a2 * p(b - c, 2) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) - Q)
					* (a5 * R - (b + c) * p(b3 - b2 * c + b * c2 - c3, 2)
					- a4 * (b3 + b2 * c + b * c2 + c3)
					+ a * p(b - c, 2) * (b4 + c4)
					- 2 * a3 * (b4 - b3 * c - b2 * c2 - b * c3 + c4)
					+ 2 * a2 * (b5 - b3 * c2 - b2 * c3 + c5)));
			case 1522 -> -((a6 * b2 - 3 * a4 * b4 + 3 * a2 * b6 - b8 + a6 * c2
					+ 2 * a4 * b2 * c2 - 2 * a2 * b4 * c2 - b6 * c2
					- 3 * a4 * c4 - 2 * a2 * b2 * c4 + 4 * b4 * c4 + 3 * a2 * c6
					- b2 * c6 - c8)
					* (9 * a8 - 9 * a6 * b2 - 7 * a4 * b4 + 5 * a2 * b6 + 2 * b8
					- 9 * a6 * c2 + 14 * a4 * b2 * c2 - 5 * a2 * b4 * c2
					- 8 * b6 * c2 - 7 * a4 * c4 - 5 * a2 * b2 * c4
					+ 12 * b4 * c4 + 5 * a2 * c6 - 8 * b2 * c6
					+ 2 * c8))
					- u(3) * a2 * (a - b - c) * (a + b - c) * (a - b + c)
					* (a + b + c)
					* (a6 * b2 - 3 * a4 * b4 + 3 * a2 * b6 - b8
					+ a6 * c2 + 2 * a4 * b2 * c2
					- 2 * a2 * b4 * c2 - b6 * c2 - 3 * a4 * c4
					- 2 * a2 * b2 * c4 + 4 * b4 * c4
					+ 3 * a2 * c6 - b2 * c6 - c8)
					* S;
			case 1523 -> -((a6 * b2 - 3 * a4 * b4 + 3 * a2 * b6 - b8 + a6 * c2
					+ 2 * a4 * b2 * c2 - 2 * a2 * b4 * c2 - b6 * c2
					- 3 * a4 * c4 - 2 * a2 * b2 * c4 + 4 * b4 * c4 + 3 * a2 * c6
					- b2 * c6 - c8)
					* (9 * a8 - 9 * a6 * b2 - 7 * a4 * b4 + 5 * a2 * b6 + 2 * b8
					- 9 * a6 * c2 + 14 * a4 * b2 * c2 - 5 * a2 * b4 * c2
					- 8 * b6 * c2 - 7 * a4 * c4 - 5 * a2 * b2 * c4
					+ 12 * b4 * c4 + 5 * a2 * c6 - 8 * b2 * c6
					+ 2 * c8))
					+ u(3) * a2 * (a - b - c) * (a + b - c) * (a - b + c)
					* (a + b + c)
					* (a6 * b2 - 3 * a4 * b4 + 3 * a2 * b6 - b8
					+ a6 * c2 + 2 * a4 * b2 * c2
					- 2 * a2 * b4 * c2 - b6 * c2 - 3 * a4 * c4
					- 2 * a2 * b2 * c4 + 4 * b4 * c4
					+ 3 * a2 * c6 - b2 * c6 - c8)
					* S;
			case 1524 -> -(u(3) * (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* (a6 + a4 * b2 - 5 * a2 * b4 + 3 * b6 + a4 * c2
					+ 10 * a2 * b2 * c2 - 3 * b4 * c2 - 5 * a2 * c4
					- 3 * b2 * c4 + 3 * c6))
					+ (a - b - c)
					* (a + b - c) * (a - b + c) * (a + b + c) * (2 * a4
					- a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* S;
			case 1525 -> -(u(3) * (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* (a6 + a4 * b2 - 5 * a2 * b4 + 3 * b6 + a4 * c2
					+ 10 * a2 * b2 * c2 - 3 * b4 * c2 - 5 * a2 * c4
					- 3 * b2 * c4 + 3 * c6))
					- (a - b - c)
					* (a + b - c) * (a - b + c) * (a + b + c) * (2 * a4
					- a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* S;
			case 1526 -> -((a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2)
					* (a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4)
					* (7 * a8 - 11 * a6 * b2 + 3 * a4 * b4 - a2 * b6 + 2 * b8
					- 11 * a6 * c2 - 6 * a4 * b2 * c2 + a2 * b4 * c2
					- 8 * b6 * c2 + 3 * a4 * c4 + a2 * b2 * c4
					+ 12 * b4 * c4 - a2 * c6 - 8 * b2 * c6 + 2 * c8))
					- u(3) * a2 * (a - b - c) * (a + b - c) * (a - b + c)
					* (a + b + c) * (a2 - b2 - b * c - c2)
					* (a2 - b2 + b * c - c2)
					* (a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4) * S;
			case 1527 -> -((a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2)
					* (a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4)
					* (7 * a8 - 11 * a6 * b2 + 3 * a4 * b4 - a2 * b6 + 2 * b8
					- 11 * a6 * c2 - 6 * a4 * b2 * c2 + a2 * b4 * c2
					- 8 * b6 * c2 + 3 * a4 * c4 + a2 * b2 * c4
					+ 12 * b4 * c4 - a2 * c6 - 8 * b2 * c6 + 2 * c8))
					+ u(3) * a2 * (a - b - c) * (a + b - c) * (a - b + c)
					* (a + b + c) * (a2 - b2 - b * c - c2)
					* (a2 - b2 + b * c - c2)
					* (a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4) * S;
			case 1528 -> -(U * V
					* (a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					- a * p(b + c, 2))
					* (a5 * (b + c) - 2 * a3 * p(b - c, 2) * (b + c)
					+ a * p(b - c, 4) * (b + c) + 2 * a2 * Q - a4 * R
					- Q * R));
			case 1529 -> -(U * V * (a4 - 3 * b4 - 2 * b2 * c2 - 3 * c4 + 2 * a2 * R)
					* (2 * a6 - a4 * R - Q * R));
			case 1530 -> -((2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c))
					* (a5 - a * Q - 2 * a2 * (b3 + c3)
					+ 2 * (b5 - b3 * c2 - b2 * c3 + c5)));
			case 1531 -> -(T * (2 * a4 - Q - a2 * R) * (a4 - 2 * Q + a2 * R));
			case 1532 -> -((-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c))
					* (-(a2 * p(b - c, 2)) + a3 * (b + c)
					- a * p(b - c, 2) * (b + c) + Q));
			case 1533 -> -((2 * a4 - Q - a2 * R)
					* (a4 * R + Q * R - 2 * a2 * (b4 - 4 * b2 * c2 + c4)));
			case 1534 -> -((a7 + 3 * a6 * (b + c) - 5 * a4 * p(b - c, 2) * (b + c)
					+ p(b - c, 4) * p(b + c, 3)
					+ 3 * a * p(b - c, 2) * p(b + c, 4) - 5 * a3 * Q
					+ a5 * (b2 - 6 * b * c + c2)
					+ a2 * p(b - c, 2) * (b3 - 5 * b2 * c - 5 * b * c2 + c3))
					* (a5 * (b + c) - p(b - c, 2) * p(b + c, 4)
					- a4 * (b2 - 4 * b * c + c2)
					+ 2 * a2 * p(b + c, 2) * (b2 - 3 * b * c + c2)
					+ a * p(b - c, 2)
					* (b3 - 3 * b2 * c - 3 * b * c2 + c3)
					- 2 * a3 * (b3 - 2 * b2 * c - 2 * b * c2 + c3)));
			case 1535 -> -((2 * a4 - a2 * p(b - c, 2) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) - Q)
					* (a6 - a5 * (b + c) + 2 * Q * (b2 - b * c + c2)
					- a2 * p(b - c, 2) * (3 * b2 + 4 * b * c + 3 * c2)
					+ 2 * a3 * (b3 + c3)
					- a * (b5 - b4 * c - b * c4 + c5)));
			case 1536 -> (2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c)) * (-b5
					+ b4 * c + b * c4 - c5 + a4 * (b + c)
					- 2 * a2 * b * c * (b + c) + 2 * a * Q - 2 * a3 * R);
			case 1537 -> -((-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c))
					* (2 * a4 - 3 * a2 * p(b - c, 2) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) + Q));
			case 1538 -> -((-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c))
					* (a3 - 3 * a * p(b - c, 2) + 2 * p(b - c, 2) * (b + c)));
			case 1539 -> -((2 * a4 - Q - a2 * R)
					* (a6 + 2 * Q * R + a2 * (-3 * b4 + 5 * b2 * c2 - 3 * c4)));
			case 1540 -> -((2 * a10 - 4 * a8 * R + a4 * Q * R - p(b2 - c2, 4) * R
					+ a2 * Q * (b4 + c4) + a6
					* (b4 + 4 * b2 * c2 + c4))
					* (a10 * a2 - 3 * a10 * R
					+ p(b2 - c2, 4) * (2 * b4 + b2 * c2 + 2 * c4)
					+ a8 * (4 * b4 + 9 * b2 * c2 + 4 * c4)
					+ a4 * Q * (9 * b4 + 8 * b2 * c2 + 9 * c4)
					- 6 * a6 * (b6 + b4 * c2 + b2 * c4 + c6)
					- a2 * Q * (7 * b6 - 3 * b4 * c2 - 3 * b2 * c4
					+ 7 * c6)));
			case 1541 -> -((2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c)) * (a5
					- a4 * (b + c) - 2 * a2 * p(b - c, 2) * (b + c) - 3 * a * Q
					+ 2 * a3 * R + p(b - c, 2)
					* (3 * b3 + 5 * b2 * c + 5 * b * c2 + 3 * c3)));
			case 1542 -> -((2 * a4 - a2 * p(b - c, 2) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) - Q)
					* (a6 - 4 * a3 * b * c * (b + c)
					+ 4 * a * b * p(b - c, 2) * c * (b + c)
					+ a4 * p(b + c, 2) - 5 * a2 * Q
					+ Q * (3 * b2 - 2 * b * c + 3 * c2)));
			case 1543 -> -((a4 * (b + c) - p(b - c, 2) * p(b + c, 3)
					- 2 * a3 * (b2 - b * c + c2)
					+ 2 * a * p(b - c, 2) * (b2 + b * c + c2))
					* (a6 + 2 * a5 * (b + c) - 4 * a3 * p(b - c, 2) * (b + c)
					+ p(b - c, 4) * p(b + c, 2)
					+ 2 * a * p(b - c, 2) * p(b + c, 3) - a2 * Q
					- a4 * (b2 + 6 * b * c + c2)));
			case 1544 -> -((2 * a4 - Q - a2 * R) * (a6 + a4 * b * c + a5 * (b + c)
					+ a * p(b - c, 2) * p(b + c, 3)
					+ Q * (2 * b2 + b * c + 2 * c2)
					- a2 * p(b + c, 2) * (3 * b2 - 4 * b * c + 3 * c2)
					- 2 * a3 * (b3 + b2 * c + b * c2 + c3)));
			case 1545 -> -((2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* (a6 + a4 * b2 - 5 * a2 * b4 + 3 * b6 + a4 * c2
					+ 10 * a2 * b2 * c2 - 3 * b4 * c2 - 5 * a2 * c4
					- 3 * b2 * c4 + 3 * c6))
					+ u(3) * (a - b - c)
					* (a + b - c) * (a - b + c) * (a + b + c) * (2 * a4
					- a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* S;
			case 1546 -> -((2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* (a6 + a4 * b2 - 5 * a2 * b4 + 3 * b6 + a4 * c2
					+ 10 * a2 * b2 * c2 - 3 * b4 * c2 - 5 * a2 * c4
					- 3 * b2 * c4 + 3 * c6))
					- u(3) * (a - b - c)
					* (a + b - c) * (a - b + c) * (a + b + c) * (2 * a4
					- a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* S;
			case 1547 -> -((2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c))
					* (a4 * p(b - c, 2) + a5 * (b + c)
					- 2 * a3 * p(b - c, 2) * (b + c)
					+ a * p(b - c, 4) * (b + c)
					+ p(b - c, 2) * p(b + c, 4) - 2 * a2 * Q));
			case 1548 -> -((-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c))
					* (4 * a5 * b * c + 3 * a6 * (b + c)
					+ a2 * p(b - c, 2) * p(b + c, 3) + 4 * a * b * c * Q
					+ p(b - c, 2) * p(b + c, 3) * R + a4 * (-5 * b3
					+ 3 * b2 * c + 3 * b * c2 - 5 * c3)));
			case 1549 -> (2 * a4 - a2 * p(b - c, 2)
					- a3 * (b + c) + a
					* p(b - c,
					2)
					* (b + c)
					- Q)
					* (a8 * (b + c) - 2 * a6 * p(b - c, 2) * (b + c)
					- 4 * a4 * b * p(b - c, 2) * c * (b + c)
					+ 2 * a2 * p(b - c, 4) * p(b + c, 3)
					- p(b - c, 6) * p(b + c, 3) - 2 * a7 * R
					- 6 * a3 * Q * R + 2 * a * p(b4 - c4, 2)
					+ a5 * (6 * b4 - 4 * b2 * c2 + 6 * c4));
			case 1550 -> -((2 * a6 - 2 * a4 * R - Q * R + a2 * (b4 + c4))
					* (a8 - a6 * R - 3 * a2 * Q * R + a4 * (b4 - b2 * c2 + c4)
					+ Q * (2 * b4 - b2 * c2 + 2 * c4)));
			case 1551 -> -((2 * a6 - 2 * a4 * R - Q * R + a2 * (b4 + c4)) * (a8
					+ 3 * a6 * R - 5 * a4 * (b4 + b2 * c2 + c4)
					+ Q * (4 * b4 - b2 * c2 + 4 * c4)
					+ a2 * (-3 * b6 + 7 * b4 * c2 + 7 * b2 * c4 - 3 * c6)));
			case 1552 -> -(U * V * (a4 - 2 * b4 + b2 * c2 + c4 + a2 * (b2 - 2 * c2))
					* (a4 + b4 + b2 * c2 - 2 * c4 + a2 * (-2 * b2 + c2))
					* (2 * a10 - 2 * a8 * R + 7 * a4 * Q * R - p(b2 - c2, 4) * R
					+ a6 * (-5 * b4 + 12 * b2 * c2 - 5 * c4)
					- a2 * Q * (b4 + 8 * b2 * c2 + c4)));
			case 1553 -> -(p(-2 * a4 + Q + a2 * R, 2) * (a6 * R
					+ a4 * (-3 * b4 + 2 * b2 * c2 - 3 * c4)
					- Q * (b4 + 3 * b2 * c2 + c4)
					+ a2 * (3 * b6 - 2 * b4 * c2 - 2 * b2 * c4 + 3 * c6)));
			case 1554 -> -((2 * a4 - Q - a2 * R) * (2 * a6 - a4 * R - Q * R)
					* (-b10 + b6 * c4 + b4 * c6 - c10 + a8 * R
					+ a4 * b2 * c2 * R - 2 * a6 * (b4 + c4)
					+ 2 * a2 * (b8 - b6 * c2 - b2 * c6 + c8)));
			case 1555 -> -((2 * a4 - Q - a2 * R) * (a8 - a6 * R - 3 * a2 * Q * R
					+ a4 * (b4 + 8 * b2 * c2 + c4)
					+ 2 * (b8 - b6 * c2 - b2 * c6 + c8)));
			case 1556 -> -((2 * a6 - Q * R - a2 * (b4 + c4)) * (a8 + a6 * R
					- a2 * p(b2 + c2, 3) + Q * (2 * b4 + 3 * b2 * c2 + 2 * c4)
					- a4 * (3 * b4 + b2 * c2 + 3 * c4)));
			case 1557 -> -((-2 * b2 * c2 * Q + 3 * a6 * R - a2 * Q * R
					- 2 * a4 * (b4 + b2 * c2 + c4))
					* (b2 * c2 * Q * (b4 + 6 * b2 * c2 + c4)
					+ a8 * (6 * b4 + 9 * b2 * c2 + 6 * c4)
					+ 2 * a2 * Q * (b6 + 5 * b4 * c2 + 5 * b2 * c4 + c6)
					- 2 * a6 * (5 * b6 + b4 * c2 + b2 * c4 + 5 * c6)
					+ 2 * a4 * (b8 - 7 * b6 * c2 - 7 * b2 * c6 + c8)));
			case 1558 -> -((2 * a4 - Q - a2 * R) * (a6 + a4 * b * c - a5 * (b + c)
					- 3 * a2 * Q + Q * (2 * b2 - b * c + 2 * c2)
					+ 2 * a3 * (b3 + c3) - a * (b5 - b4 * c - b * c4 + c5)));
			case 1559 -> -(U * V * (3 * a4 - Q - 2 * a2 * R)
					* (a6 * R + 3 * a2 * Q * R
					+ a4 * (-3 * b4 + 4 * b2 * c2 - 3 * c4)
					- Q * (b4 + 4 * b2 * c2 + c4)));
			case 1560 -> -((2 * a2 - b2 - c2) * U * V
					* (-2 * a2 * b2 * c2 + a4 * R - Q * R));
			case 1561 -> -((2 * a4 - Q - a2 * R) * (3 * a6 * R + a2 * Q * R
					+ a4 * (-5 * b4 + 4 * b2 * c2 - 5 * c4)
					+ Q * (b4 + 4 * b2 * c2 + c4)));
			case 1562 -> p(b - c, 2) * p(b + c, 2) * (-T)
					* (-3 * a4 + Q + 2 * a2 * R);
			case 1563 -> a10 * a2 * b2 - 4 * a10 * b4 + 5 * a8 * b6 - 5 * a4 * b10
					+ 4 * a2 * b10 * b2 - b4 * b10 + a10 * a2 * c2
					+ 14 * a10 * b2 * c2 - 44 * a8 * b4 * c2 + 27 * a6 * b6 * c2
					+ 18 * a4 * b8 * c2 - 21 * a2 * b10 * c2 + 5 * b10 * b2 * c2
					- 4 * a10 * c4 - 44 * a8 * b2 * c4 + 16 * a6 * b4 * c4
					- 13 * a4 * b6 * c4 + 50 * a2 * b8 * c4 - 9 * b10 * c4
					+ 5 * a8 * c6 + 27 * a6 * b2 * c6 - 13 * a4 * b4 * c6
					- 66 * a2 * b6 * c6 + 5 * b8 * c6 + 18 * a4 * b2 * c8
					+ 50 * a2 * b4 * c8 + 5 * b6 * c8 - 5 * a4 * c10
					- 21 * a2 * b2 * c10 - 9 * b4 * c10 + 4 * a2 * c10 * c2
					+ 5 * b2 * c10 * c2 - c10 * c4
					+ (-2 * a10 * a2 + 9 * a10 * b2 - 16 * a8 * b4
					+ 13 * a6 * b6 - 3 * a4 * b8 - 2 * a2 * b10
					+ b10 * b2 + 9 * a10 * c2 - 26 * a8 * b2 * c2
					+ 4 * a6 * b4 * c2 + 9 * a4 * b6 * c2
					+ 9 * a2 * b8 * c2 - 5 * b10 * c2 - 16 * a8 * c4
					+ 4 * a6 * b2 * c4 - 4 * a4 * b4 * c4
					- 7 * a2 * b6 * c4 + 11 * b8 * c4 + 13 * a6 * c6
					+ 9 * a4 * b2 * c6 - 7 * a2 * b4 * c6 - 14 * b6 * c6
					- 3 * a4 * c8 + 9 * a2 * b2 * c8 + 11 * b4 * c8
					- 2 * a2 * c10 - 5 * b2 * c10 + c10 * c2) * S;
			case 1564 -> -(a10 * a2 * b2) + 4 * a10 * b4 - 5 * a8 * b6 + 5 * a4 * b10
					- 4 * a2 * b10 * b2 + b4 * b10 - a10 * a2 * c2
					- 14 * a10 * b2 * c2 + 44 * a8 * b4 * c2 - 27 * a6 * b6 * c2
					- 18 * a4 * b8 * c2 + 21 * a2 * b10 * c2 - 5 * b10 * b2 * c2
					+ 4 * a10 * c4 + 44 * a8 * b2 * c4 - 16 * a6 * b4 * c4
					+ 13 * a4 * b6 * c4 - 50 * a2 * b8 * c4 + 9 * b10 * c4
					- 5 * a8 * c6 - 27 * a6 * b2 * c6 + 13 * a4 * b4 * c6
					+ 66 * a2 * b6 * c6 - 5 * b8 * c6 - 18 * a4 * b2 * c8
					- 50 * a2 * b4 * c8 - 5 * b6 * c8 + 5 * a4 * c10
					+ 21 * a2 * b2 * c10 + 9 * b4 * c10 - 4 * a2 * c10 * c2
					- 5 * b2 * c10 * c2 + c10 * c4
					+ (-2 * a10 * a2 + 9 * a10 * b2 - 16 * a8 * b4
					+ 13 * a6 * b6 - 3 * a4 * b8 - 2 * a2 * b10
					+ b10 * b2 + 9 * a10 * c2 - 26 * a8 * b2 * c2
					+ 4 * a6 * b4 * c2 + 9 * a4 * b6 * c2
					+ 9 * a2 * b8 * c2 - 5 * b10 * c2 - 16 * a8 * c4
					+ 4 * a6 * b2 * c4 - 4 * a4 * b4 * c4
					- 7 * a2 * b6 * c4 + 11 * b8 * c4 + 13 * a6 * c6
					+ 9 * a4 * b2 * c6 - 7 * a2 * b4 * c6 - 14 * b6 * c6
					- 3 * a4 * c8 + 9 * a2 * b2 * c8 + 11 * b4 * c8
					- 2 * a2 * c10 - 5 * b2 * c10 + c10 * c2) * S;
			case 1565 -> p(b - c, 2) * (-T);
			case 1566 -> -((a - b - c) * p(b - c, 2) * (-b2 - c2 + a * (b + c))
					* (2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c)));
			case 1567 -> -((-(b2 * c2 * Q) + a6 * R + a2 * b2 * c2 * R
					- a4 * p(b2 + c2, 2))
					* (b4 * c4 * Q + a8 * (2 * b4 + b2 * c2 + 2 * c4)
					- 3 * a6 * (b6 + c6)
					+ a4 * (-5 * b6 * c2 + 7 * b4 * c4 - 5 * b2 * c6)
					+ a2 * (b10 + b6 * c4 + b4 * c6 + c10)));
			case 1568 -> -(T * (2 * a4 - Q - a2 * R) * (-Q + a2 * R));
			case 1569 -> -(b2 * c2 * Q) + 2 * a6 * R - 2 * a4 * p(b2 + c2, 2)
					+ a2 * (b6 + b4 * c2 + b2 * c4 + c6);
			case 1570 -> a2 * (2 * a4 + 3 * b4 - 2 * b2 * c2 + 3 * c4 - 3 * a2 * R);
			case 1571 -> a * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					- a * (3 * b2 + 2 * b * c + 3 * c2));
			case 1572 -> a * (a3 + a * p(b - c, 2) + a2 * (b + c)
					- p(b - c, 2) * (b + c));
			case 1573 -> a * (2 * b * c * (b + c) + a * R);
			case 1574 -> a * (-2 * b * c * (b + c) + a * R);
			case 1575 -> a * (-(b * c * (b + c)) + a * R);
			case 1576 -> a4 * (a - b) * (a + b) * (a - c) * (a + c);
			case 1577 -> b * (b - c) * c * (b + c);
			case 1578 -> 8 * a4 * b2 * c2 * T + a2 * T * U * V * S;
			case 1579 -> -8 * a4 * b2 * c2 * T + a2 * T * U * V * S;
			case 1580 -> a5 - a * b2 * c2;
			case 1581 -> -(a5 * b2 * c2) - a * b4 * c4 + a3 * (b6 + c6);
			case 1582 -> a5 + a * b2 * c2;
			case 1583 -> -4 * a2 * b2 * c2 + a2 * T * S;
			case 1584 -> 4 * a2 * b2 * c2 + a2 * T * S;
			case 1585 -> T * U * V - U * V * S;
			case 1586 -> -(T * U * V) - U * V * S;
			case 1587 -> U * V + 2 * a2 * S;
			case 1588 -> -(U * V) + 2 * a2 * S;
			case 1589 -> T * U * V + 2 * a2 * T * S;
			case 1590 -> -(T * U * V) + 2 * a2 * T * S;
			case 1591 -> -4 * a2 * b2 * c2
					+ (-(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4) * S;
			case 1592 -> 4 * a2 * b2 * c2
					+ (-(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4) * S;
			case 1593 -> a2 * U * V * (a4 + b4 + 6 * b2 * c2 + c4 - 2 * a2 * R);
			case 1594 -> -(U * V * (a4 * R + Q * R - 2 * a2 * (b4 + b2 * c2 + c4)));
			case 1595 -> -(U * V
					* (a4 * R + Q * R - 2 * a2 * (b4 + 4 * b2 * c2 + c4)));
			case 1596 -> -(U * V
					* (a4 * R + Q * R - 2 * a2 * (b4 - 4 * b2 * c2 + c4)));
			case 1597 -> a2 * U * V * (a4 + b4 + 10 * b2 * c2 + c4 - 2 * a2 * R);
			case 1598 -> a2 * U * V * (a4 + b4 - 6 * b2 * c2 + c4 - 2 * a2 * R);
			case 1599 -> -2 * a2 * b2 * c2 + a2 * T * S;
			default -> Double.NaN;
		};
	}

	private double weight1600to1699(int k, double a, double b, double c) {

		return switch (k) {
			case 1600 -> 2 * a2 * b2 * c2 + a2 * T * S;
			case 1601 -> a2
					* (a10 * a10 - 6 * a10 * a8 * R
					+ 3 * a10 * a6 * (5 * b4 + 8 * b2 * c2 + 5 * c4)
					- 2 * a10 * a4
					* (10 * b6 + 17 * b4 * c2 + 17 * b2 * c4
					+ 10 * c6)
					- p(b2 - c2, 6) * (b8 + c8)
					+ a10 * a2
					* (14 * b8 + 14 * b6 * c2 + 17 * b4 * c4
					+ 14 * b2 * c6 + 14 * c8)
					+ 2 * a10
					* (7 * b8 * c2 + 5 * b6 * c4 + 5 * b4 * c6
					+ 7 * b2 * c8)
					+ 2 * a2 * p(b2 - c2, 4)
					* (3 * b10 - b6 * c4 - b4 * c6 + 3 * c10)
					+ 2 * a6 * Q
					* (10 * b10 + 13 * b8 * c2 + 15 * b6 * c4
					+ 15 * b4 * c6 + 13 * b2 * c8 + 10
					* c10)
					- 2 * a8 * (7 * b10 * b2 + 7 * b10 * c2
					+ 3 * b8 * c4 + 2 * b6 * c6 + 3 * b4 * c8
					+ 7 * b2 * c10 + 7 * c10 * c2)
					- a4 * Q * (15 * b10 * b2 - 4 * b10 * c2
					- 4 * b8 * c4 - 6 * b6 * c6 - 4 * b4 * c8
					- 4 * b2 * c10 + 15 * c10 * c2));
			case 1602 -> a2 * (a6 - 2 * a5 * (b + c) + a4 * p(b + c, 2)
					- a2 * (b4 + c4) - p(b - c, 2) * (b4 + c4)
					+ 2 * a * (b5 - b4 * c - b * c4 + c5));
			case 1603 -> a2 * (a8 - 2 * a6 * p(b - c, 2)
					- 2 * a4 * b * c * (2 * b2 - 3 * b * c + 2 * c2)
					+ 4 * a3 * b * c * (b3 + c3) - Q * (b4 + c4)
					- 4 * a * b * c * (b5 - b3 * c2 - b2 * c3 + c5)
					+ 2 * a2 * (b6 - 3 * b4 * c2 - 3 * b2 * c4 + c6));
			case 1604 -> a2 * (a6 + 4 * a3 * b * c * (b + c)
					- 4 * a * b * p(b - c, 2) * c * (b + c)
					+ a4 * (-3 * b2 + 4 * b * c - 3 * c2) - Q * R + a2 * (3 * b4
					- 4 * b3 * c - 6 * b2 * c2 - 4 * b * c3 + 3 * c4));
			case 1605 -> -(a2 * (a8 - 2 * a6 * b2 + 2 * a2 * b6 - b8 - 2 * a6 * c2
					+ 4 * a4 * b2 * c2 - 4 * a2 * b4 * c2 + 2 * b6 * c2
					- 4 * a2 * b2 * c4 - 2 * b4 * c4 + 2 * a2 * c6 + 2 * b2 * c6
					- c8))
					+ u(3) * a2 * (a6 - a4 * b2 + a2 * b4 - b6 - a4 * c2
					+ b4 * c2 + a2 * c4 + b2 * c4 - c6) * S;
			case 1606 -> a2
					* (a8 - 2 * a6 * b2 + 2 * a2 * b6 - b8 - 2 * a6 * c2
					+ 4 * a4 * b2 * c2 - 4 * a2 * b4 * c2 + 2 * b6 * c2
					- 4 * a2 * b2 * c4 - 2 * b4 * c4 + 2 * a2 * c6
					+ 2 * b2 * c6 - c8)
					+ u(3) * a2 * (a6 - a4 * b2 + a2 * b4 - b6 - a4 * c2
					+ b4 * c2 + a2 * c4 + b2 * c4 - c6) * S;
			case 1607 -> a2
					* (a8 - 2 * a6 * b2 + 2 * a2 * b6 - b8 - 2 * a6 * c2
					- 4 * a4 * b2 * c2 + 4 * a2 * b4 * c2 + 2 * b6 * c2
					+ 4 * a2 * b2 * c4 - 2 * b4 * c4 + 2 * a2 * c6
					+ 2 * b2 * c6 - c8)
					+ u(3) * a2 * (a6 - a4 * b2 + a2 * b4 - b6 - a4 * c2
					+ b4 * c2 + a2 * c4 + b2 * c4 - c6) * S;
			case 1608 -> -(a2 * (a8 - 2 * a6 * b2 + 2 * a2 * b6 - b8 - 2 * a6 * c2
					- 4 * a4 * b2 * c2 + 4 * a2 * b4 * c2 + 2 * b6 * c2
					+ 4 * a2 * b2 * c4 - 2 * b4 * c4 + 2 * a2 * c6 + 2 * b2 * c6
					- c8))
					+ u(3) * a2 * (a6 - a4 * b2 + a2 * b4 - b6 - a4 * c2
					+ b4 * c2 + a2 * c4 + b2 * c4 - c6) * S;
			case 1609 -> a2 * (a6 - 3 * a4 * R - Q * R
					+ a2 * (3 * b4 - 2 * b2 * c2 + 3 * c4));
			case 1610 -> a * (a6 + 3 * a4 * b * c + a5 * (b + c)
					- 2 * a3 * b * c * (b + c) - b * c * Q
					- a2 * (b4 + 2 * b3 * c - 2 * b2 * c2 + 2 * b * c3 + c4)
					- a * (b5 - b4 * c - b * c4 + c5));
			case 1611 -> a2 * (a4 + b4 - 6 * b2 * c2 + c4 + 2 * a2 * R);
			case 1612 -> a * (a6 - a5 * (b + c) + 2 * a3 * p(b - c, 2) * (b + c)
					- a * p(b - c, 2) * p(b + c, 3) - b * c * Q
					+ a2 * p(b + c, 2) * R
					- a4 * (2 * b2 + 5 * b * c + 2 * c2));
			case 1613 -> -(a2 * b2 * c2) + a4 * R;
			case 1614 -> a2 * (a8 + b2 * c2 * Q - 3 * a6 * R - a2 * Q * R
					+ a4 * (3 * b4 + b2 * c2 + 3 * c4));
			case 1615 -> a2 * (a4 - 4 * a3 * (b + c) - 4 * a * p(b - c, 2) * (b + c)
					+ p(b - c, 2) * (b2 + 6 * b * c + c2)
					+ a2 * (6 * b2 - 4 * b * c + 6 * c2));
			case 1616 -> a2 * (a2 + b2 - 6 * b * c + c2 + 2 * a * (b + c));
			case 1617 -> a2 * (a + b - c) * (a - b + c)
					* (a2 + b2 + c2 - 2 * a * (b + c));
			case 1618 -> a2 * (a - b) * (a - c)
					* (a4 + b * p(b - c, 2) * c - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c)
					- a2 * (b2 - 3 * b * c + c2));
			case 1619 -> a2 * (a10 + a2 * p(b2 - c2, 4) - a8 * R - Q * p(b2 + c2, 3)
					- 2 * a6 * (b4 - 6 * b2 * c2 + c4)
					+ 2 * a4 * (b6 - 3 * b4 * c2 - 3 * b2 * c4 + c6));
			case 1620 -> a2 * (7 * a8 - 12 * a6 * R + 20 * a2 * Q * R
					+ a4 * (-6 * b4 + 28 * b2 * c2 - 6 * c4)
					- Q * (9 * b4 + 14 * b2 * c2 + 9 * c4));
			case 1621 -> a * (a2 - b * c - a * (b + c));
			case 1622 -> a2
					* (a8 + 2 * a7 * (b + c) - 2 * a * p(b - c, 4) * p(b + c, 3)
					- p(b - c, 2) * p(b + c, 4) * R
					- 2 * a6 * (b2 + b * c + c2)
					+ 2 * a4 * b * c * (b2 + 6 * b * c + c2)
					- 2 * a5 * (3 * b3 + b2 * c + b * c2 + 3 * c3)
					+ 2 * a3 * p(b - c, 2)
					* (3 * b3 + 5 * b2 * c + 5 * b * c2
					+ 3 * c3)
					+ 2 * a2 * p(b - c, 2)
					* (b4 + 3 * b3 * c + 3 * b * c3 + c4));
			case 1623 -> a2 * (2 * a4 - 2 * b4 + a2 * b * c + 2 * b3 * c - b2 * c2
					+ 2 * b * c3 - 2 * c4 - 2 * a3 * (b + c)
					+ a * (2 * b3 - b2 * c - b * c2 + 2 * c3));
			case 1624 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (-2 * a2 * Q + a4 * R + Q * R);
			case 1625 -> a2 * (a - b) * (a + b) * (a - c) * (a + c) * (-Q + a2 * R);
			case 1626 -> a2 * (a4 - a3 * (b + c) - p(b - c, 2) * (b2 + b * c + c2)
					+ a * (b3 + c3));
			case 1627 -> a6 - a2 * b2 * c2 + a4 * R;
			case 1628 -> a2 * U * V * (a10 * a6 - 8 * a10 * a4 * R
					+ p(b2 - c2, 6) * (b4 + c4)
					+ a10 * a2 * (28 * b4 + 38 * b2 * c2 + 28 * c4)
					- 8 * a2 * p(b2 - c2, 4) * (b6 + b4 * c2 + b2 * c4 + c6)
					- 8 * a10 * (7 * b6 + 9 * b4 * c2 + 9 * b2 * c4 + 7 * c6)
					+ a8 * (70 * b8 + 62 * b6 * c2 + 72 * b4 * c4 + 62 * b2 * c6
					+ 70 * c8)
					- 8 * a6 * (7 * b10 + b8 * c2 + 4 * b6 * c4 + 4 * b4 * c6
					+ b2 * c8 + 7 * c10)
					+ 2 * a4 * (14 * b10 * b2 - 15 * b10 * c2 + 6 * b8 * c4
					+ 6 * b6 * c6 + 6 * b4 * c8 - 15 * b2 * c10
					+ 14 * c10 * c2));
			case 1629 -> p(a4 - Q, 2) * (a4 - b2 * c2 - a2 * R);
			case 1630 -> a2 * (a6 - a5 * (b + c) + b * c * Q + a2 * p(b2 + c2, 2)
					- a4 * (2 * b2 + b * c + 2 * c2) + 2 * a3 * (b3 + c3)
					- a * (b5 - b4 * c - b * c4 + c5));
			case 1631 -> a2 * (a3 - b3 - c3);
			case 1632 -> (a - b) * (a + b) * (a - c) * (a + c) * (a4 + Q);
			case 1633 -> a * (a - b) * (a2 + p(b - c, 2)) * (a - c);
			case 1634 -> a2 * (a - b) * (a + b) * (a - c) * (a + c) * R;
			case 1635 -> a * (2 * a - b - c) * (b - c);
			case 1636 -> a2 * (b2 - c2) * (T * T) * (2 * a4 - Q - a2 * R);
			case 1637 -> -((b2 - c2) * (-2 * a4 + Q + a2 * R));
			case 1638 -> -((b - c) * (-2 * a2 + p(b - c, 2) + a * (b + c)));
			case 1639 -> (a - b - c) * (2 * a - b - c) * (b - c);
			case 1640 -> -((b2 - c2)
					* (-2 * a6 + 2 * a4 * R + Q * R - a2 * (b4 + c4)));
			case 1641 -> 4 * a6 + b6 + 12 * a2 * b2 * c2 - 3 * b4 * c2 - 3 * b2 * c4
					+ c6 - 6 * a4 * R;
			case 1642 -> a * (-b2 - c2 + a * (b + c)) * (2 * a3 - 2 * a2 * (b + c)
					- p(b - c, 2) * (b + c) + a * R);
			case 1643 -> a * (b - c) * (2 * a3 - 2 * a2 * (b + c)
					- p(b - c, 2) * (b + c) + a * R);
			case 1644 -> (2 * a - b - c)
					* (2 * a2 - b2 + 4 * b * c - c2 - 2 * a * (b + c));
			case 1645 -> a4 * p(b - c, 2) * p(b + c, 2) * (-2 * b2 * c2 + a2 * R);
			case 1646 -> a2 * p(b - c, 2) * (-2 * b * c + a * (b + c));
			case 1647 -> -((2 * a - b - c) * p(b - c, 2));
			case 1648 -> p(b - c, 2) * p(b + c, 2) * (-2 * a2 + b2 + c2);
			case 1649 -> (b2 - c2) * p(-2 * a2 + b2 + c2, 2);
			case 1650 -> p(b - c, 2) * p(b + c, 2) * (T * T) * (-2 * a4 + Q + a2 * R);
			case 1651 -> (2 * a4 - Q - a2 * R) * (2 * a8 - 2 * a6 * R + 4 * a2 * Q * R
					+ a4 * (-3 * b4 + 8 * b2 * c2 - 3 * c4)
					- Q * (b4 + 4 * b2 * c2 + c4));
			case 1652 -> u(3) * a * (a + b - c) * (a - b + c) * (a + b + c)
					- a * (a - b - c) * S;
			case 1653 -> u(3) * a * (a + b - c) * (a - b + c) * (a + b + c)
					+ a * (a - b - c) * S;
			case 1654 -> -a2 + b2 + b * c + c2 + a * (b + c);
			case 1655 -> -(b2 * c2) + a * b * c * (b + c) + a2 * (b2 + b * c + c2);
			case 1656 -> a4 + 2 * Q - 3 * a2 * R;
			case 1657 -> 5 * a4 - 2 * Q - 3 * a2 * R;
			case 1658 -> a2 * (a8 - b8 + 2 * a4 * b2 * c2 + b6 * c2 + b2 * c6 - c8
					- 2 * a6 * R + a2 * (2 * b6 - b4 * c2 - b2 * c4 + 2 * c6));
			case 1659 -> (a2 + 2 * a * b + b2 - c2 + S)
					* (a2 - b2 + 2 * a * c + c2 + S);
			case 1660 -> a4 * (a8 + 10 * a4 * b2 * c2 - 2 * a6 * R
					- Q * (b4 + 4 * b2 * c2 + c4)
					+ 2 * a2 * (b6 - 3 * b4 * c2 - 3 * b2 * c4 + c6));
			case 1661 -> a2 * (a10 + 3 * a8 * R + 14 * a4 * Q * R - p(b2 - c2, 4) * R
					- 3 * a2 * Q * (b4 + 6 * b2 * c2 + c4)
					- 2 * a6 * (7 * b4 - 10 * b2 * c2 + 7 * c4));
			case 1662 -> a2 * (a2 * b2 + a2 * c2 + b2 * c2)
					* (a2 * b2 - b4 + a2 * c2 - c4)
					+ a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1663 -> -(a2 * (a2 * b2 + a2 * c2 + b2 * c2)
					* (a2 * b2 - b4 + a2 * c2 - c4))
					+ a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1664 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2)
					+ a2 * (a2 * b2 - b4 + a2 * c2 - c4)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1665 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2)
					- a2 * (a2 * b2 - b4 + a2 * c2 - c4)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1666 -> a2 * (a2 * b2 + a2 * c2 + b2 * c2)
					* (a2 * b2 - b4 + a2 * c2 - c4)
					- a2 * S * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1667 -> a2 * (a2 * b2 + a2 * c2 + b2 * c2)
					* (a2 * b2 - b4 + a2 * c2 - c4)
					+ a2 * S * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1668 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2)
					+ a2 * S * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1669 -> -(a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2))
					+ a2 * S * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1670 -> a2 * R - a2 * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1671 -> a2 * (-b4 - c4 + a2 * R)
					+ a2 * (-T) * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1672 -> a2 * (2 * b2 * c2 + a2 * (-T))
					+ 2 * a2 * b * c * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1673 -> a2 * (2 * b2 * c2 + a2 * (-T))
					- 2 * a2 * b * c * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1674 -> -(a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2))
					+ 2 * a2 * b * c * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1675 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2)
					+ 2 * a2 * b * c * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1676 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					+ (-(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1677 -> -(a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2))
					+ (-(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1678 -> -(a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2))
					+ 2 * a * b * c * (b + c) * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1679 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2)
					+ 2 * a * b * c * (b + c) * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1680 -> -(a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2))
					+ 2 * a * b * c * (b + c) * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1681 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					+ 2 * a * b * c * (b + c) * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1682 -> a2 * (-a + b + c) * (b2 + c2 + a * (b + c))
					* (b2 + c2 + a * (b + c));
			case 1683 -> a2
					* (a4 * b - a2 * b3 + a4 * c - a3 * b * c - a * b2 * c2
					- b3 * c2 - a2 * c3 - b2 * c3)
					+ a2 * (a2 * b - b3 + a2 * c - a * b * c - c3)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1684 -> -(a2 * (a4 * b - a2 * b3 + a4 * c - a3 * b * c - a * b2 * c2
					- b3 * c2 - a2 * c3 - b2 * c3))
					+ a2 * (a2 * b - b3 + a2 * c - a * b * c - c3)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1685 -> -(a2 * (a + b + c) * (a2 * b - b3 + a2 * c - a * b * c - c3))
					+ a2 * (a * b + b2 + a * c + b * c + c2) * S;
			case 1686 -> a2 * (a + b + c) * (a2 * b - b3 + a2 * c - a * b * c - c3)
					+ a2 * (a * b + b2 + a * c + b * c + c2) * S;
			case 1687 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					- a2 * S * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1688 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					+ a2 * S * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1689 -> a2 * (a2 * b2 - b4 + a2 * c2 - c4)
					+ a2 * S * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1690 -> a2 * (a2 * b2 - b4 + a2 * c2 - c4)
					- a2 * S * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1691 -> a6 - a2 * b2 * c2;
			case 1692 -> a2 * (2 * a4 + Q - a2 * R);
			case 1693 -> a2
					* (a2 * b + a * b2 + a2 * c + a * b * c + b2 * c + a * c2
					+ b * c2)
					* (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2) + a2
					* (a3 * b2 + a2 * b3 - a * b4 - b5 + 2 * a3 * b * c
					+ a2 * b2 * c - 2 * a * b3 * c - b4 * c
					+ a3 * c2 + a2 * b * c2 + a2 * c3
					- 2 * a * b * c3 - a * c4 - b * c4 - c5)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 1694 -> -(a2 * (a2 * b + a * b2 + a2 * c + a * b * c + b2 * c
					+ a * c2 + b * c2) * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2))
					+ a2 * (a3 * b2 + a2 * b3 - a * b4 - b5 + 2 * a3 * b * c
					+ a2 * b2 * c - 2 * a * b3 * c - b4 * c + a3 * c2
					+ a2 * b * c2 + a2 * c3 - 2 * a * b * c3 - a * c4
					- b * c4 - c5)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 1695 -> a * (a5 * (b + c) - b * c * Q
					+ a4 * (4 * b2 + 7 * b * c + 4 * c2)
					+ 2 * a3 * (b3 + b2 * c + b * c2 + c3)
					- 2 * a2 * (2 * b4 + 3 * b3 * c + 3 * b * c3 + 2 * c4)
					- a * (3 * b5 + 3 * b4 * c + 2 * b3 * c2 + 2 * b2 * c3
					+ 3 * b * c4 + 3 * c5));
			case 1696 -> a2 * (a3 - b3 + 5 * b2 * c + 5 * b * c2 - c3 + a2 * (b + c)
					- a * p(b + c, 2));
			case 1697 -> a * (a - b - c) * (a2 + p(b - c, 2) + 2 * a * (b + c));
			case 1698 -> a + 2 * (b + c);
			case 1699 -> -a3 - a * p(b - c, 2) + 2 * p(b - c, 2) * (b + c);
			default -> Double.NaN;
		};
	}

	private double weight1700to1799(int k, double a, double b, double c) {

		return switch (k) {
			case 1700 -> 2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2) + a
					* (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1701 -> -2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2) + a
					* (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 1702 -> -(a * (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)) + 2 * a2 * S;
			case 1703 -> a * (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3) + 2 * a2 * S;
			case 1704 -> 2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2) + a
					* (a3 + a2 * b - a * b2 - b3
					+ a2 * c - 2 * a * b * c + b2 * c - a * c2
					+ b * c2 - c3)
					* u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1705 -> -2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2) + a
					* (a3 + a2 * b - a * b2 - b3
					+ a2 * c - 2 * a * b * c + b2 * c - a * c2
					+ b * c2 - c3)
					* u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 1706 -> a * (a3 - b3 + 5 * b2 * c + 5 * b * c2 - c3 + a2 * (b + c)
					- a * p(b + c, 2));
			case 1707 -> a * (3 * a2 - b2 - c2);
			case 1708 -> a * (a + b - c) * (a - b + c) * (a3 + b3 + b2 * c + b * c2
					+ c3 - a2 * (b + c) - a * p(b + c, 2));
			case 1709 -> a * (a5 - a4 * (b + c) + 2 * a2 * p(b - c, 2) * (b + c)
					- p(b - c, 2) * p(b + c, 3) + a * p(b - c, 2) * R
					- 2 * a3 * (b2 - 3 * b * c + c2));
			case 1710 -> a * (a6 + a5 * (b + c) - a * p(b - c, 2) * p(b + c, 3)
					- a4 * (b2 - b * c + c2) - Q * (b2 + b * c + c2)
					+ a2 * (b4 + c4));
			case 1711 -> a * (a5 - a4 * (b + c) + 2 * a2 * p(b - c, 2) * (b + c)
					- p(b - c, 2) * p(b + c, 3) - 2 * a3 * R
					+ a * p(b2 + c2, 2));
			case 1712 -> a * U * V
					* (a8 - 4 * a6 * R - 4 * a2 * Q * R
					+ Q * (b4 + 6 * b2 * c2 + c4)
					+ a4 * (6 * b4 - 4 * b2 * c2 + 6 * c4));
			case 1713 -> a * (a6 * (b + c) - b * p(b - c, 2) * c * p(b + c, 3)
					+ 2 * a3 * Q - a5 * R - a * Q * R
					- a4 * (2 * b3 + 3 * b2 * c + 3 * b * c2 + 2 * c3)
					+ a2 * (b5 + 3 * b4 * c + 3 * b * c4 + c5));
			case 1714 -> a4 + 2 * a3 * (b + c) - 2 * a * b * c * (b + c) + Q;
			case 1715 -> a * (a8 * (b + c) - b * p(b - c, 4) * c * p(b + c, 3)
					+ a7 * R + 3 * a3 * Q * R
					- a2 * p(b - c, 4) * (b3 + 2 * b2 * c + 2 * b * c2 + c3)
					- a6 * (3 * b3 + 2 * b2 * c + 2 * b * c2 + 3 * c3)
					+ a5 * (-3 * b4 + 2 * b2 * c2 - 3 * c4) - a * p(b4 - c4, 2)
					+ a4 * (3 * b5 + b3 * c2 + b2 * c3 + 3 * c5));
			case 1716 -> a * (a2 * b * c + a3 * (b + c) + a * p(b - c, 2) * (b + c)
					- b * c * R);
			case 1717 -> a * (a6 - a3 * b * c * (b + c)
					+ a * b * p(b - c, 2) * c * (b + c)
					+ p(b - c, 2) * p(b + c, 4) - a4 * (b2 + 3 * b * c + c2)
					+ a2 * (-b4 + b3 * c + 2 * b2 * c2 + b * c3 - c4));
			case 1718 -> a * (a6 - a3 * b * c * (b + c)
					+ a * b * p(b - c, 2) * c * (b + c)
					+ p(b - c, 4) * p(b + c, 2) - a4 * (b2 - b * c + c2)
					+ a2 * (-b4 + b3 * c + 2 * b2 * c2 + b * c3 - c4));
			case 1719 -> a * (a5 + 3 * a3 * b * c + a4 * (b + c)
					- p(b - c, 2) * p(b + c, 3)
					- a * (b4 + b3 * c - 2 * b2 * c2 + b * c3 + c4));
			case 1720 -> a * (a9 - a8 * (b + c) - p(b - c, 4) * p(b + c, 5)
					+ a7 * (-4 * b2 + 2 * b * c - 4 * c2)
					+ 4 * a2 * p(b - c, 2) * p(b + c, 3) * (b2 - b * c + c2)
					+ 2 * a5 * p(b - c, 2) * (3 * b2 + b * c + 3 * c2)
					+ 4 * a6 * (b3 + c3)
					- 2 * a4 * p(b - c, 2)
					* (3 * b3 + 5 * b2 * c + 5 * b * c2 + 3 * c3)
					+ a * Q * (b4 - 6 * b3 * c + 2 * b2 * c2 - 6 * b * c3 + c4)
					- 2 * a3 * p(b - c, 2) * (2 * b4 - 3 * b3 * c - 6 * b2 * c2
					- 3 * b * c3 + 2 * c4));
			case 1721 -> a * (a4 - 2 * a3 * (b + c) - 2 * a * p(b - c, 2) * (b + c)
					+ 2 * a2 * (b2 - b * c + c2)
					+ p(b - c, 2) * (b2 + 4 * b * c + c2));
			case 1722 -> a * (a3 + b3 - 3 * b2 * c - 3 * b * c2 + c3 + a2 * (b + c)
					+ a * R);
			case 1723 -> a * (a4 - 2 * a2 * b * c - 2 * a3 * (b + c) - Q
					+ 2 * a * (b3 + c3));
			case 1724 -> a * (a3 + a2 * (b + c) - b * c * (b + c));
			case 1725 -> a * (a4 * R + Q * R - 2 * a2 * (b4 - b2 * c2 + c4));
			case 1726 -> a * (a5 - b5 + b3 * c2 + b2 * c3 - c5 - a3 * R
					+ a2 * (b3 + c3));
			case 1727 -> a * (a6 + a3 * b * c * (b + c)
					- a * b * p(b - c, 2) * c * (b + c)
					+ a4 * (-3 * b2 + b * c - 3 * c2) - Q * R
					+ a2 * (3 * b4 - b3 * c - 2 * b2 * c2 - b * c3 + 3 * c4));
			case 1728 -> a * (a6 - 2 * a3 * b * c * (b + c)
					+ 2 * a * b * p(b - c, 2) * c * (b + c)
					- p(b - c, 2) * p(b + c, 4) - 3 * a4 * R
					+ a2 * p(b + c, 2) * (3 * b2 - 4 * b * c + 3 * c2));
			case 1729 -> a * (a6 - a5 * (b + c) + a3 * b * c * (b + c) - a4 * R
					- Q * (b2 - b * c + c2)
					+ a2 * p(b - c, 2) * (b2 + b * c + c2)
					+ a * (b5 - b3 * c2 - b2 * c3 + c5));
			case 1730 -> a * (a4 * (b + c) - b * p(b - c, 2) * c * (b + c) - a * Q
					+ a3 * R - a2 * (b3 + c3));
			case 1731 -> a * (a - b - c) * (a3 - a * b * c + p(b - c, 2) * (b + c));
			case 1732 -> a * (a4 - 4 * a2 * b * c - 4 * a3 * (b + c) - Q
					+ 4 * a * (b3 + c3));
			case 1733 -> b * c * (2 * a4 + Q - a2 * R);
			case 1734 -> a * (-b3 + c3 + a * (b2 - c2));
			case 1735 -> a * (-(a3 * b * c * (b + c))
					+ a * b * p(b - c, 2) * c * (b + c) + a4 * R
					+ Q * (b2 - b * c + c2)
					- a2 * p(b - c, 2) * (2 * b2 + 3 * b * c + 2 * c2));
			case 1736 -> a * (b5 - b3 * c2 - b2 * c3 + c5 - a * Q + a3 * R
					- a2 * (b3 + c3));
			case 1737 -> a3 * (b + c) - a * p(b - c, 2) * (b + c) + Q - a2 * R;
			case 1738 -> -2 * a * b * c + a2 * (b + c) + p(b - c, 2) * (b + c);
			case 1739 -> a * (b3 - 2 * b2 * c - 2 * b * c2 + c3 + a * R);
			case 1740 -> -(a * b2 * c2) + a3 * R;
			case 1741 -> a * (a - b - c)
					* (a6 - a4 * p(b - c, 2) + 2 * a5 * (b + c) + Q * R
					- a2 * p(b - c, 2) * (b2 + 4 * b * c + c2)
					+ a3 * (-4 * b3 + 2 * b2 * c + 2 * b * c2 - 4 * c3)
					+ 2 * a * p(b - c, 2) * (b3 + c3));
			case 1742 -> a * (-(b * p(b - c, 2) * c) + a3 * (b + c)
					+ a * p(b - c, 2) * (b + c)
					+ a2 * (-2 * b2 + b * c - 2 * c2));
			case 1743 -> a * (3 * a - b - c);
			case 1744 -> a * (a7 + a * b * c * Q
					- p(b - c, 2) * p(b + c, 3) * (b2 - b * c + c2)
					- a5 * (2 * b2 + b * c + 2 * c2)
					- a4 * (b3 + 2 * b2 * c + 2 * b * c2 + c3) + a3 * (b4 + c4)
					+ 2 * a2 * (b5 + b4 * c - b3 * c2 - b2 * c3 + b * c4 + c5));
			case 1745 -> a * (a4 * b * c + a5 * (b + c) - b * c * Q
					- 2 * a3 * (b3 + c3) + a * (b5 - b4 * c - b * c4 + c5));
			case 1746 -> a6 - b * c * Q - a4 * (b2 - b * c + c2) + a3 * (b3 + c3)
					- a * (b5 - b3 * c2 - b2 * c3 + c5);
			case 1747 -> a * (a8 - 4 * a4 * b2 * c2 - 2 * a6 * R - Q * (b4 + c4)
					+ 2 * a2 * (b6 + c6));
			case 1748 -> a * U * V * (a4 + b4 + c4 - 2 * a2 * R);
			case 1749 -> a * (a6 - 3 * a4 * R - Q * R
					+ a2 * (3 * b4 - b2 * c2 + 3 * c4));
			case 1750 -> a * (a5 - 3 * a4 * (b + c) + 2 * a2 * p(b - c, 2) * (b + c)
					- 3 * a * Q + 2 * a3 * R
					+ p(b - c, 2) * (b3 + 7 * b2 * c + 7 * b * c2 + c3));
			case 1751 -> (a3 - b2 * c + c3 - a * b * (b + c))
					* (a3 + b3 - b * c2 - a * c * (b + c));
			case 1752 -> a * (a6 - 2 * a5 * (b + c) + 2 * a3 * b * c * (b + c)
					- p(b - c, 4) * p(b + c, 2) + a4 * R
					- a2 * (b4 + 2 * b3 * c - 2 * b2 * c2 + 2 * b * c3 + c4)
					+ 2 * a * (b5 - b3 * c2 - b2 * c3 + c5));
			case 1753 -> a * U * V
					* (a5 + a4 * (b + c) + p(b - c, 2) * p(b + c, 3)
					- 2 * a3 * R - 2 * a2 * (b3 + b2 * c + b * c2 + c3)
					+ a * (b4 + 6 * b2 * c2 + c4));
			case 1754 -> a * (a5 - a4 * (b + c) + b * p(b - c, 2) * c * (b + c)
					- a3 * R + a2 * (b3 + c3));
			case 1755 -> a3 * (-b4 - c4 + a2 * R);
			case 1756 -> a * (a4 * (b + c) + b * p(b - c, 2) * c * (b + c) + a3 * R
					- a2 * (b3 + c3) - a * (b4 + c4));
			case 1757 -> a * (a2 - b2 - b * c - c2 + a * (b + c));
			case 1758 -> a * (a + b - c) * (a - b + c)
					* (a3 + b3 + a * b * c + c3 - 2 * a2 * (b + c));
			case 1759 -> a * (a3 - b3 - c3);
			case 1760 -> a * (a4 - b4 - c4);
			case 1761 -> a * (a4 + a2 * b * c + a3 * (b + c)
					- p(b + c, 2) * (b2 - b * c + c2)
					- a * (b3 + b2 * c + b * c2 + c3));
			case 1762 -> a * (a5 - b5 + b3 * c2 + b2 * c3 - c5
					+ a * b * c * p(b + c, 2) - a3 * (b2 + b * c + c2)
					+ a2 * (b3 + c3));
			case 1763 -> a * (a5 - b5 + b4 * c + b * c4 - c5 + a4 * (b + c)
					- 2 * a2 * b * c * (b + c) - a * Q);
			case 1764 -> a * (a4 * (b + c) - b * p(b - c, 2) * c * (b + c) + a3 * R
					- a * p(b2 + c2, 2) - a2 * (b3 + c3));
			case 1765 -> a * (a6 * (b + c) - b * p(b - c, 2) * c * p(b + c, 3)
					- a5 * R - a * Q * R
					+ a4 * (-2 * b3 + b2 * c + b * c2 - 2 * c3)
					+ 2 * a3 * (b4 + c4) + a2 * (b5 - b4 * c - b * c4 + c5));
			case 1766 -> a * (a4 + 2 * a2 * b * c - 2 * a * b * c * (b + c) - Q);
			case 1767 -> a * (a + b - c) * (a - b + c) * U * V * (a5
					- 2 * a3 * p(b - c, 2) - a4 * (b + c)
					- p(b - c, 2) * p(b + c, 3)
					+ 2 * a2 * (b3 + b2 * c + b * c2 + c3)
					+ a * (b4 - 4 * b3 * c - 2 * b2 * c2 - 4 * b * c3 + c4));
			case 1768 -> a * (a5 - b5 + b4 * c + b * c4 - c5 - a4 * (b + c)
					+ 2 * a2 * p(b - c, 2) * (b + c)
					+ a3 * (-2 * b2 + 5 * b * c - 2 * c2)
					+ a * p(b - c, 2) * (b2 - b * c + c2));
			case 1769 -> a * (b - c)
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1770 -> 2 * a4 + a3 * (b + c) - a * p(b - c, 2) * (b + c) - Q
					- a2 * R;
			case 1771 -> a * (a6 + a3 * b * c * (b + c)
					- a * b * p(b - c, 2) * c * (b + c) + b * c * Q - 2 * a4 * R
					+ a2 * p(b - c, 2) * (b2 + b * c + c2));
			case 1772 -> a * (-2 * a3 * b * c * (b + c)
					+ 2 * a * b * p(b - c, 2) * c * (b + c)
					+ p(b - c, 4) * p(b + c, 2) + a4 * R
					- 2 * a2 * (b4 - b3 * c - b2 * c2 - b * c3 + c4));
			case 1773 -> a * (a6 + 2 * a5 * (b + c) + 2 * a3 * b * c * (b + c)
					+ a4 * p(b + c, 2) - Q * R
					- a2 * (b4 - 2 * b3 * c - 2 * b2 * c2 - 2 * b * c3 + c4)
					- 2 * a * (b5 - 3 * b3 * c2 - 3 * b2 * c3 + c5));
			case 1774 -> a * (a9 + a8 * (b + c)
					- 2 * a4 * b * p(b - c, 2) * c * (b + c)
					- p(b - c, 4) * p(b + c, 3) * R
					- a * p(b - c, 2) * p(b + c, 4) * R
					- 2 * a7 * (b2 - b * c + c2)
					+ 2 * a2 * p(b - c, 2) * p(b + c, 3) * (b2 - b * c + c2)
					- 2 * a5 * b * c * (3 * b2 - 2 * b * c + 3 * c2)
					- 2 * a6 * (b3 + c3) + 2 * a3 * (b6 + 3 * b5 * c - b4 * c2
					- 2 * b3 * c3 - b2 * c4 + 3 * b * c5 + c6));
			case 1775 -> a * (a9 + a8 * (b + c)
					- 2 * a4 * b * p(b - c, 2) * c * (b + c)
					- a * p(b - c, 4) * p(b + c, 2) * R
					- p(b - c, 4) * p(b + c, 3) * R
					+ 2 * a2 * p(b - c, 2) * p(b + c, 3) * (b2 - b * c + c2)
					- 2 * a7 * (b2 + b * c + c2)
					+ 2 * a5 * b * c * (3 * b2 + 2 * b * c + 3 * c2)
					- 2 * a6 * (b3 + c3) + 2 * a3 * (b6 - 3 * b5 * c - b4 * c2
					+ 2 * b3 * c3 - b2 * c4 - 3 * b * c5 + c6));
			case 1776 -> a * (a - b - c) * (a4 + a2 * (-2 * b2 + 3 * b * c - 2 * c2)
					+ p(b - c, 2) * (b2 + b * c + c2));
			case 1777 -> a * (a6 + a3 * b * c * (b + c)
					- a * b * p(b - c, 2) * c * (b + c) - b * c * Q
					- 2 * a4 * (b2 - b * c + c2)
					+ a2 * p(b - c, 2) * (b2 + b * c + c2));
			case 1778 -> a * (a + b) * (a + c) * (a2 + 2 * a * (b + c) - p(b + c, 2));
			case 1779 -> a2 * (-b7 - a5 * b * c + b4 * c3 + b3 * c4 - c7
					+ a6 * (b + c) + a * b * c * Q - 3 * a4 * (b3 + c3)
					+ a2 * (3 * b5 - b4 * c - b * c4 + 3 * c5));
			case 1780 -> a2 * (a + b) * (a + c) * (a3 + b3 + b2 * c + b * c2 + c3
					- a2 * (b + c) - a * p(b + c, 2));
			case 1781 -> a * (a4 + a2 * b * c + a * b * c * (b + c) - Q);
			case 1782 -> a * (a6 + a5 * (b + c) - a3 * b * c * (b + c) - Q * R
					- a4 * (b2 + b * c + c2) + a2 * (b4 + b3 * c + b * c3 + c4)
					- a * (b5 - b3 * c2 - b2 * c3 + c5));
			case 1783 -> a * (a - b) * (a - c) * U * V;
			case 1784 -> b * c * (-V) * U * (-2 * a4 + Q + a2 * R);
			case 1785 -> U * V
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1786 -> a * (a + b - c) * (a - b + c)
					* (a6 + a5 * (b + c) - a2 * Q - a4 * (b2 + b * c + c2)
					+ Q * (b2 + b * c + c2)
					- 2 * a3 * (b3 + b2 * c + b * c2 + c3)
					+ a * (b5 + b4 * c + b * c4 + c5));
			case 1787 -> a * (a + b - c) * (a - b + c)
					* (a6 - a5 * (b + c) - a2 * p(b + c, 2) * R
					- a4 * (b2 - 3 * b * c + c2) + Q * (b2 - b * c + c2)
					+ 2 * a3 * (b3 + c3) + a * (-b5 + b4 * c
					+ 2 * b3 * c2 + 2 * b2 * c3 + b * c4 - c5));
			case 1788 -> (a + b - c) * (a - b + c)
					* (a2 + 2 * a * (b + c) - p(b + c, 2));
			case 1789 -> a * (a + b) * (a - b - c) * (a + c) * T
					* (a2 + a * b + b2 - c2) * (a2 - b2 + a * c + c2);
			case 1790 -> a2 * (a + b) * (a + c) * T;
			case 1791 -> a * T * (a2 + a * c + b * (b + c))
					* (a2 + a * b + c * (b + c));
			case 1792 -> a * (a + b) * (a + c) * p(-a + b + c, 2) * T;
			case 1793 -> a * (a + b) * (a - b - c) * (a + c) * T
					* (a2 - a * b + b2 - c2) * (a2 - b2 - a * c + c2);
			case 1794 -> a2 * T * (a3 - a2 * b + b3 - b * c2 - a * p(b + c, 2))
					* (a3 - a2 * c - b2 * c + c3 - a * p(b + c, 2));
			case 1795 -> a2 * T * (a3 - a2 * b + b3 - a * p(b - c, 2) - b * c2)
					* (a3 - a * p(b - c, 2) - a2 * c - b2 * c + c3);
			case 1796 -> a2 * (a + 2 * b + c) * (a + b + 2 * c) * T;
			case 1797 -> a2 * (a + b - 2 * c) * (a - 2 * b + c) * T;
			case 1798 -> a2 * (a + b) * (a + c) * T * (a2 + a * c + b * (b + c))
					* (a2 + a * b + c * (b + c));
			case 1799 -> (a2 + b2) * T * (a2 + c2);
			default -> Double.NaN;
		};
	}

	private double weight1800to1899(int k, double a, double b, double c) {

		return switch (k) {
			case 1800 -> a2 * (a + b) * (a - b - c) * (a + c) * T
					* (a3 + a2 * (b + c) - p(b - c, 2) * (b + c) - a * R);
			case 1801 -> a2 * (a + b) * (a + c) * T * (a3 - b3 - b2 * c - b * c2 - c3
					- a2 * (b + c) + a * p(b + c, 2));
			case 1802 -> a3 * p(-a + b + c, 2) * T;
			case 1803 -> a2 * (a + b - c) * (a - b + c) * T
					* (a2 + b * (b - c) - a * (2 * b + c))
					* (a2 + c * (-b + c) - a * (b + 2 * c));
			case 1804 -> a2 * (a + b - c) * (a - b + c) * (T * T);
			case 1805 -> a2 * (a + b) * (a - b - c) * (a + b - c) * (a + c)
					* (a - b + c) * (b + c) * T
					+ a3 * (a + b) * (a - b - c) * (a + c) * T * S;
			case 1806 -> -(a2 * (a + b) * (a - b - c) * (a + b - c) * (a + c)
					* (a - b + c) * (b + c) * T)
					+ a3 * (a + b) * (a - b - c) * (a + c) * T * S;
			case 1807 -> a * T * (a2 - a * b + b2 - c2) * (a2 - b2 - a * c + c2);
			case 1808 -> a2 * (a + b) * (a - b - c) * (a + c) * (-b2 + a * c)
					* (a * b - c2) * T;
			case 1809 -> a * (a - b - c) * T
					* (a3 - a2 * b + b3 - a * p(b - c, 2) - b * c2)
					* (a3 - a * p(b - c, 2) - a2 * c - b2 * c + c3);
			case 1810 -> a2 * T * (a2 + b2 - b * c + 2 * c2 - a * (2 * b + c))
					* (a2 + 2 * b2 - b * c + c2 - a * (b + 2 * c));
			case 1811 -> a * T * (a2 + a * (-4 * b + c) + b * (b + c))
					* (a2 + a * (b - 4 * c) + c * (b + c));
			case 1812 -> a * (a + b) * (a - b - c) * (a + c) * T;
			case 1813 -> a2 * (a - b) * (a - c) * (a + b - c) * (a - b + c) * T;
			case 1814 -> a * (a2 + b * (b - c) - a * c) * T
					* (a2 - a * b + c * (-b + c));
			case 1815 -> a2 * T * (a3 - 2 * b3 - a2 * c + b2 * c + c3 + a * (b2 - c2))
					* (a3 - a2 * b + b3 + b * c2 - 2 * c3 + a * (-b2 + c2));
			case 1816 -> a * (a + b) * (a - b - c) * (a + c)
					* (a4 * b * c + a5 * (b + c) - b * c * Q
					- 2 * a3 * (b3 + c3)
					+ a * (b5 - b4 * c - b * c4 + c5));
			case 1817 -> a * (a + b) * (a + c) * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * p(b + c, 2));
			case 1818 -> a2 * T * (-b2 - c2 + a * (b + c));
			case 1819 -> a2 * (a + b) * (a - b - c) * (a + c) * T * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * p(b + c, 2));
			case 1820 -> a * T * (a4 - 2 * a2 * b2 + Q) * (a4 - 2 * a2 * c2 + Q);
			case 1821 -> -(b * c * (a4 + b4 - a2 * c2 - b2 * c2)
					* (-a4 + a2 * b2 + b2 * c2 - c4));
			case 1822 -> (a2
					* (a2 - b2) * (a2
					- c2))
					/ (-(b * c * U * V) + a2 * b * c * (-T)
					+ a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2
					- a2 * c4 - b2 * c4 + c6));
			case 1823 -> (a2
					* (a2 - b2) * (a2
					- c2))
					/ (-(b * c * U * V) + a2 * b * c * (-T)
					- a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2
					- a2 * c4 - b2 * c4 + c6));
			case 1824 -> -(a * (b + c) * U * V);
			case 1825 -> a * (a + b - c) * (a - b + c) * (b + c) * U
					* (a2 - b2 - b * c - c2) * V;
			case 1826 -> (b + c) * (-V) * U;
			case 1827 -> a * (a - b - c) * U * V * (-p(b - c, 2) + a * (b + c));
			case 1828 -> a * U * V * (p(b - c, 2) + a * (b + c));
			case 1829 -> a * U * V * (b2 + c2 + a * (b + c));
			case 1830 -> a * U * V
					* (a3 * (b + c) - a2 * p(b + c, 2)
					+ p(b - c, 2) * (b2 + b * c + c2)
					- a * (b3 - 2 * b2 * c - 2 * b * c2 + c3));
			case 1831 -> a * (a - b - c) * U * V
					* (a3 * (b + c) + a2 * p(b + c, 2)
					- p(b - c, 2) * (b2 + b * c + c2)
					- a * (b3 - 2 * b2 * c - 2 * b * c2 + c3));
			case 1832 -> u(3) * (a + b - c) * (a - b + c) * (b + c) * U * V
					+ (b + c) * U * V * S;
			case 1833 -> u(3) * (a + b - c) * (a - b + c) * (b + c) * U * V
					- (b + c) * U * V * S;
			case 1834 -> (b + c) * (2 * a3 + a2 * (b + c) + p(b - c, 2) * (b + c));
			case 1835 -> a * (a + b - c) * (a - b + c) * (b + c) * U
					* (a2 - b2 + b * c - c2) * V;
			case 1836 -> -a3 + p(b - c, 2) * (b + c);
			case 1837 -> -((a - b - c) * (a3 + p(b - c, 2) * (b + c)));
			case 1838 -> U * V
					* (2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1839 -> -((2 * a + b + c) * U * V);
			case 1840 -> (b + c) * (a2 + b * c) * (-V) * U;
			case 1841 -> a * U * V
					* (2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1842 -> U * V * (2 * a3 + a2 * (b + c) + p(b - c, 2) * (b + c));
			case 1843 -> a2 * U * V * R;
			case 1844 -> a * U * (a2 - b2 - b * c - c2) * V
					* (2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1845 -> a * U * (a2 - b2 + b * c - c2) * V
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1846 -> (2 * a - b - c) * (a + b - c) * (a - b + c) * U * V
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1847 -> b * p(a + b - c, 2) * c * p(a - b + c, 2) * (-V) * U;
			case 1848 -> -(U * V * (b2 + c2 + a * (b + c)));
			case 1849 -> -(a * (a - b - c) * (a + b + c) * U
					* (a * b - b2 + a * c + 2 * b * c - c2) * V)
					- 4 * a * b * c * U * V * S;
			case 1850 -> a * (a - b - c) * (a + b + c) * U
					* (a * b - b2 + a * c + 2 * b * c - c2) * V
					- 4 * a * b * c * U * V * S;
			case 1851 -> -((a2 + p(b - c, 2)) * U * V);
			case 1852 -> -((a - b - c) * U * V
					* (2 * a5 - b5 + b4 * c + b * c4 - c5 + 2 * a4 * (b + c)
					- a2 * p(b - c, 2) * (b + c) - a * Q
					- a3 * (b2 - 4 * b * c + c2)));
			case 1853 -> -a6 - a2 * Q + 2 * Q * R;
			case 1854 -> a * (a - b - c) * (a5 + 2 * a2 * p(b - c, 2) * (b + c)
					- a * Q - 2 * (b5 - b4 * c - b * c4 + c5));
			case 1855 -> -((a - b - c) * U * V * (-p(b - c, 2) + a * (b + c)));
			case 1856 -> -((a - b - c) * U * V * (a2 * p(b - c, 2) + a3 * (b + c)
					- a * p(b - c, 2) * (b + c) - Q));
			case 1857 -> -((a - b - c) * p(a4 - Q, 2));
			case 1858 -> -(a * (a - b - c) * (b5 + 2 * a3 * b * c - b4 * c - b * c4
					+ c5 + a4 * (b + c) - 2 * a2 * p(b - c, 2) * (b + c)));
			case 1859 -> a * (a - b - c) * U * V
					* (2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1860 -> U * V * (a2 * b * c * (b + c) - b * p(b - c, 2) * c * (b + c)
					- a * Q + a3 * R);
			case 1861 -> U * V * (-b2 - c2 + a * (b + c));
			case 1862 -> U * V * (2 * a2 + b2 + c2 - 2 * a * (b + c));
			case 1863 -> -((a2 + p(b - c, 2)) * p(-a + b + c, 2) * U * V);
			case 1864 -> -(a * (a - b - c) * (-(a2 * p(b - c, 2)) + a3 * (b + c)
					- a * p(b - c, 2) * (b + c) + Q));
			case 1865 -> (b + c) * (-V) * U
					* (-2 * a * b * c - a2 * (b + c) + p(b - c, 2) * (b + c));
			case 1866 -> a * (a + b - c) * (a - b + c) * U * V
					* (-b3 - 2 * a * b * c - c3 + a2 * (b + c));
			case 1867 -> (b + c) * (-V) * U * (a2 + 2 * b * c + a * (b + c));
			case 1868 -> -(a * (b + c) * U * V * (a4 + b4 - 2 * a2 * p(b - c, 2)
					+ 6 * b2 * c2 + c4 + 4 * a * b * c * (b + c)));
			case 1869 -> (b + c) * (-V) * U
					* (-3 * a2 + p(b - c, 2) - 2 * a * (b + c));
			case 1870 -> a * U * (a2 - b2 + b * c - c2) * V;
			case 1871 -> -(a * U * V
					* (-4 * a * b2 * c2 + a4 * (b + c)
					+ p(b - c, 2) * p(b + c, 3)
					- 2 * a2 * (b3 + b2 * c + b * c2 + c3)));
			case 1872 -> -(a * U * V
					* (4 * a * b2 * c2 + a4 * (b + c)
					+ p(b - c, 2) * p(b + c, 3)
					- 2 * a2 * (b3 + b2 * c + b * c2 + c3)));
			case 1873 -> -((a + b - c) * (a - b + c) * (b + c) * U * V
					* (a3 - 2 * b * c * (b + c) - a * (b2 + 3 * b * c + c2)));
			case 1874 -> (a + b - c) * (a - b + c) * (b + c) * (a2 - b * c) * U * V;
			case 1875 -> a * (a + b - c) * (a - b + c) * U * V
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1876 -> a * (a + b - c) * (a - b + c) * U * V
					* (-b2 - c2 + a * (b + c));
			case 1877 -> (2 * a - b - c) * (a + b - c) * (a - b + c) * U * V;
			case 1878 -> a * U * V * (b2 - 4 * b * c + c2 + a * (b + c));
			case 1879 -> p(b2 - c2, 4) - 2 * a2 * Q * R + a4 * (b4 + c4);
			case 1880 -> a * (a + b - c) * (a - b + c) * (b + c) * U * V;
			case 1881 -> (b + c) * (-V) * U
					* (a4 * (b2 - b * c + c2) + Q * (b2 - b * c + c2)
					- 2 * a2 * p(b - c, 2) * (b2 + b * c + c2));
			case 1882 -> -((a + b - c) * (a - b + c) * (b + c) * U * V
					* (a3 - 2 * b * c * (b + c) - a * p(b + c, 2)));
			case 1883 -> -(U * V * (b3 + a * p(b - c, 2) + b2 * c + b * c2 + c3));
			case 1884 -> U * V * (2 * a3 + p(b - c, 2) * (b + c) - a * R);
			case 1885 -> -(U * V * (2 * a6 + 8 * a2 * b2 * c2 - 3 * a4 * R + Q * R));
			case 1886 -> U * V * (2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c));
			case 1887 -> a * (a + b - c) * (a - b + c) * U * V
					* (2 * a * b * c + a2 * (b + c) - p(b + c, 3));
			case 1888 -> a * U * V
					* (-2 * a3 * b * c + a4 * (b + c)
					- 2 * a2 * p(b - c, 2) * (b + c)
					+ p(b - c, 4) * (b + c)
					+ 2 * a * b * c * p(b + c, 2));
			case 1889 -> U * V * (a2 + 2 * b * c + 2 * a * (b + c));
			case 1890 -> -(U * V * (2 * a2 + b2 + c2 + a * (b + c)));
			case 1891 -> -(U * V * (2 * a3 + b3 + 2 * a * b * c + b2 * c + b * c2 + c3
					+ a2 * (b + c)));
			case 1892 -> -((a + b - c) * (a - b + c) * U * V
					* (a3 - b3 - b2 * c - b * c2 - c3));
			case 1893 -> -((a + b - c) * (a - b + c) * (b + c) * U * V
					* (a2 - 2 * b * c - a * (b + c)));
			case 1894 -> U * V * (2 * a2 * (b + c) - p(b - c, 2) * (b + c) + a * R);
			case 1895 -> b * c * (-V) * U * (-3 * a4 + Q + 2 * a2 * R);
			case 1896 -> b * (a + b) * c * (a + c) * (-a + b + c) * p(a4 - Q, 2);
			case 1897 -> (a - b) * (a - c) * U * V;
			case 1898 -> -(a * (-(a4 * p(b - c, 2)) + a5 * (b + c)
					- p(b - c, 2) * p(b + c, 4) + 2 * a2 * Q
					- 2 * a3 * (b3 + c3) + a * (b5 - b4 * c - b * c4 + c5)));
			case 1899 -> -(T * (a4 + Q));
			default -> Double.NaN;
		};
	}

	private double weight1900to1999(int k, double a, double b, double c) {

		return switch (k) {
			case 1900 -> -(a * U * V * (b2 + 4 * b * c + c2 + a * (b + c)));
			case 1901 -> (b + c) * (-2 * a4 + a2 * p(b - c, 2) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) + Q);
			case 1902 -> -(a * U * V
					* (b5 - 2 * a3 * b * c - b4 * c - b * c4 + c5 + a4 * (b + c)
					+ 2 * a * b * c * p(b + c, 2)
					- 2 * a2 * (b3 + c3)));
			case 1903 -> -(a * (b + c)
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2)));
			case 1904 -> -(U * V
					* (b3 + b2 * c + b * c2 + c3 + a * (b2 + 4 * b * c + c2)));
			case 1905 -> a * U * V * (b5 - b4 * c - 4 * a * b2 * c2 - b * c4 + c5
					+ a4 * (b + c) - 2 * a2 * (b3 + c3));
			case 1906 -> -(U * V
					* (a4 * R + Q * R - 2 * a2 * (b4 - 6 * b2 * c2 + c4)));
			case 1907 -> -(U * V
					* (a4 * R + Q * R - 2 * a2 * (b4 + 6 * b2 * c2 + c4)));
			case 1908 -> a2 * (a * b * c * (b + c) + a2 * R + b * c * R);
			case 1909 -> b * c * (a2 + b * c);
			case 1910 -> a * (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4);
			case 1911 -> a3 * (-b2 + a * c) * (a * b - c2);
			case 1912 -> a3 * (b2 * c2 * (-b + c) + a * (b4 - c4));
			case 1913 -> a3 * (a3 * R + a * b * c * R + b * c * (b3 + c3));
			case 1914 -> a4 - a2 * b * c;
			case 1915 -> a6 + a2 * b2 * c2;
			case 1916 -> (b2 - a * c) * (b2 + a * c) * (a * b - c2) * (a * b + c2);
			case 1917 -> a7;
			case 1918 -> a4 * (b + c);
			case 1919 -> a4 * (b - c);
			case 1920 -> b2 * c2 * (a2 + b * c);
			case 1921 -> b2 * c2 * (-a2 + b * c);
			case 1922 -> a4 * (-b2 + a * c) * (a * b - c2);
			case 1923 -> a5 * R;
			case 1924 -> a5 * (b2 - c2);
			case 1925 -> b3 * c3 * (a4 + b2 * c2);
			case 1926 -> -(a4 * b3 * c3) + b5 * c5;
			case 1927 -> a5 * (-b2 + a * c) * (b2 + a * c) * (a * b - c2)
					* (a * b + c2);
			case 1928 -> b5 * c5;
			case 1929 -> a * (a2 + b2 + a * (b - c) - b * c - c2)
					* (a2 - b2 - b * c + c2 + a * (-b + c));
			case 1930 -> b * c * R;
			case 1931 -> a * (a + b) * (a + c) * (a2 - b2 - b * c - c2 + a * (b + c));
			case 1932 -> a7 + a3 * b2 * c2;
			case 1933 -> a7 - a3 * b2 * c2;
			case 1934 -> b * c * (b2 - a * c) * (b2 + a * c) * (a * b - c2)
					* (a * b + c2);
			case 1935 -> a * (a + b - c) * (a - b + c)
					* (a4 + b * c * p(b + c, 2) - a2 * (b2 + b * c + c2));
			case 1936 -> a * (a - b - c)
					* (a4 - b * p(b - c, 2) * c - a2 * (b2 - b * c + c2));
			case 1937 -> a * (a + b - c) * (a - b + c)
					* (a3 * b + c2 * (b2 - c2) + a2 * (-2 * b2 + c2)
					+ a * (b3 - b * c2))
					* (-b4 + a3 * c + b2 * c2 + a2 * (b2 - 2 * c2)
					+ a * (-(b2 * c) + c3));
			case 1938 -> a * (b - c)
					* (a4 * (b + c) - b * p(b - c, 2) * c * (b + c)
					- a3 * (b2 + 3 * b * c + c2)
					- a2 * (b3 - 2 * b2 * c - 2 * b * c2 + c3)
					+ a * (b4 + b3 * c + b * c3 + c4));
			case 1939 -> a * (a4 * p(b - c, 2) - b * c * Q
					- a2 * p(b - c, 2) * (b2 + b * c + c2)
					- a3 * (b3 - 2 * b2 * c - 2 * b * c2 + c3)
					+ a * (b5 - b3 * c2 - b2 * c3 + c5));
			case 1940 -> (a + b - c) * (a - b + c) * U * V
					* (a4 + b * c * p(b + c, 2) - a2 * (b2 + b * c + c2));
			case 1941 -> U * V
					* (a10 * a2 + b2 * c2 * p(b2 - c2, 4) - 4 * a10 * R
					- 4 * a6 * p(b2 + c2, 3)
					+ a8 * (6 * b4 + 13 * b2 * c2 + 6 * c4)
					+ a4 * (b8 + 2 * b6 * c2 + 10 * b4 * c4
					+ 2 * b2 * c6 + c8));
			case 1942 -> a2 * T
					* (a3 * b - b2 * c2 + c4 + a2 * (2 * b2 - c2)
					+ a * (b3 - b * c2))
					* (a3 * b + c2 * (b2 - c2) + a2 * (-2 * b2 + c2)
					+ a * (b3 - b * c2))
					* (b4 + a3 * c - b2 * c2 - a2 * (b2 - 2 * c2)
					+ a * (-(b2 * c) + c3))
					* (-b4 + a3 * c + b2 * c2 + a2 * (b2 - 2 * c2)
					+ a * (-(b2 * c) + c3));
			case 1943 -> (a + b - c) * (a - b + c)
					* (a4 + b * c * p(b + c, 2) - a2 * (b2 + b * c + c2));
			case 1944 -> (a - b - c)
					* (a4 - b * p(b - c, 2) * c - a2 * (b2 - b * c + c2));
			case 1945 -> a2 * (a + b - c) * (a - b + c)
					* (a3 * b + c2 * (b2 - c2) + a2 * (-2 * b2 + c2)
					+ a * (b3 - b * c2))
					* (-b4 + a3 * c + b2 * c2 + a2 * (b2 - 2 * c2)
					+ a * (-(b2 * c) + c3));
			case 1946 -> a3 * (b - c)
					* (b5 + b3 * c2 + b2 * c3 + c5 + a3 * (b2 + b * c + c2)
					- a2 * (b3 + 2 * b2 * c + 2 * b * c2 + c3)
					+ a * (-b4 + b3 * c + 2 * b2 * c2 + b * c3 - c4));
			case 1947 -> b * (-a + b - c) * (a + b - c) * c * (-V) * U
					* (a4 + b * c * p(b + c, 2) - a2 * (b2 + b * c + c2));
			case 1948 -> b * c * (-a + b + c) * (-V) * U
					* (-a4 + b * p(b - c, 2) * c + a2 * (b2 - b * c + c2));
			case 1949 -> a3 * (a + b - c) * (a - b + c) * T
					* (a3 * b + c2 * (b2 - c2) + a2 * (-2 * b2 + c2)
					+ a * (b3 - b * c2))
					* (-b4 + a3 * c + b2 * c2 + a2 * (b2 - 2 * c2)
					+ a * (-(b2 * c) + c3));
			case 1950 -> a2 * (a + b - c) * (a - b + c)
					* (a4 + b * c * p(b + c, 2) - a2 * (b2 + b * c + c2));
			case 1951 -> a2 * (a - b - c)
					* (a4 - b * p(b - c, 2) * c - a2 * (b2 - b * c + c2));
			case 1952 -> (a + b - c) * (a - b + c)
					* (a3 * b + c2 * (b2 - c2) + a2 * (-2 * b2 + c2)
					+ a * (b3 - b * c2))
					* (-b4 + a3 * c + b2 * c2 + a2 * (b2 - 2 * c2)
					+ a * (-(b2 * c) + c3));
			case 1953 -> -(a * (-Q + a2 * R));
			case 1954 -> a * (a8 - b2 * c2 * Q - 2 * a6 * R
					+ a4 * (b4 + 3 * b2 * c2 + c4));
			case 1955 -> a * (a8 + b2 * c2 * Q - 2 * a6 * R
					+ a4 * (b4 + b2 * c2 + c4));
			case 1956 -> a * (a6 * b2 + c4 * Q + a4 * (-2 * b4 + c4)
					+ a2 * (b6 + b2 * c4 - 2 * c6))
					* (a6 * c2 + b4 * Q + a4 * (b4 - 2 * c4)
					+ a2 * (-2 * b6 + b4 * c2 + c6));
			case 1957 -> a * U * V * (a4 + 2 * b2 * c2 - a2 * R);
			case 1958 -> a5 + 2 * a * b2 * c2 - a3 * R;
			case 1959 -> a * (b4 + c4 - a2 * R);
			case 1960 -> a2 * (2 * a - b - c) * (b - c);
			case 1961 -> a * (a2 + b2 + 3 * b * c + c2 + a * (b + c));
			case 1962 -> a * (b + c) * (2 * a + b + c);
			case 1963 -> a * (a + b) * (a + c)
					* (a2 + b2 + 3 * b * c + c2 + a * (b + c));
			case 1964 -> a3 * R;
			case 1965 -> b * c * (a4 + b2 * c2);
			case 1966 -> b * c * (a4 - b2 * c2);
			case 1967 -> a3 * (-b2 + a * c) * (b2 + a * c) * (a * b - c2)
					* (a * b + c2);
			case 1968 -> a2 * U * V * (a4 + 2 * b2 * c2 - a2 * R);
			case 1969 -> b3 * c3 * (-V) * U;
			case 1970 -> a2 * (a8 - b2 * c2 * Q - 2 * a6 * R
					+ a4 * (b4 + 3 * b2 * c2 + c4));
			case 1971 -> a2 * (a8 + b2 * c2 * Q - 2 * a6 * R
					+ a4 * (b4 + b2 * c2 + c4));
			case 1972 -> (a6 * b2 + c4 * Q + a4 * (-2 * b4 + c4)
					+ a2 * (b6 + b2 * c4 - 2 * c6))
					* (a6 * c2 + b4 * Q + a4 * (b4 - 2 * c4)
					+ a2 * (-2 * b6 + b4 * c2 + c6));
			case 1973 -> a3 * U * V;
			case 1974 -> a4 * U * V;
			case 1975 -> a4 + 2 * b2 * c2 - a2 * R;
			case 1976 -> a2 * (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4);
			case 1977 -> a4 * p(b - c, 2);
			case 1978 -> (a - b) * b2 * (a - c) * c2;
			case 1979 -> a2 * (-(b2 * c2) + a * b * c * (b + c)
					+ a2 * (b2 - 3 * b * c + c2));
			case 1980 -> a5 * (b - c);
			case 1981 -> (a - b) * (a - c) * U * V
					* (a4 - b * p(b - c, 2) * c - a2 * (b2 - b * c + c2));
			case 1982 -> (a + b) * (a + c) * U * V
					* (a4 - 2 * a3 * (b + c) + b * c * p(b + c, 2)
					- a2 * (b2 + b * c + c2)
					+ 2 * a * (b3 + b2 * c + b * c2 + c3));
			case 1983 -> a3 * (a - b) * (a - c) * (a2 - b2 + b * c - c2);
			case 1984 -> a * (a + b) * p(b - c, 2) * (a + c) * p(-a + b + c, 4)
					* (a4 - b * p(b - c, 2) * c - a2 * (b2 - b * c + c2));
			case 1985 -> -(a4 * b * c) + b * c * Q - a3 * (b3 + c3)
					+ a * (b5 - b3 * c2 - b2 * c3 + c5);
			case 1986 -> a2 * U * (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2) * V
					* (a4 * R + Q * R - 2 * a2 * (b4 - b2 * c2 + c4));
			case 1987 -> a2
					* (a6 * b2 + c4 * Q + a4 * (-2 * b4 + c4)
					+ a2 * (b6 + b2 * c4 - 2 * c6))
					* (a6 * c2 + b4 * Q + a4 * (b4 - 2 * c4)
					+ a2 * (-2 * b6 + b4 * c2 + c6));
			case 1988 -> a2
					* (a6 * (b2 - c2) + b2 * c2 * Q
					+ a2 * (b2 - c2) * p(b2 + c2, 2)
					- a4 * (2 * b4 + b2 * c2 - 2 * c4))
					* (a6 * (b2 - c2) - b2 * c2 * Q
					+ a2 * (b2 - c2) * p(b2 + c2, 2)
					+ a4 * (-2 * b4 + b2 * c2 + 2 * c4));
			case 1989 -> (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (a2 - b2 - a * c + c2) * (a2 - b2 + a * c + c2);
			case 1990 -> U * V * (2 * a4 - Q - a2 * R);
			case 1991 -> -2 * a2 + b2 + c2 - S;
			case 1992 -> 5 * a2 - b2 - c2;
			case 1993 -> a2 * (a4 + b4 + c4 - 2 * a2 * R);
			case 1994 -> a2 * (a4 + b4 - b2 * c2 + c4 - 2 * a2 * R);
			case 1995 -> a2 * (a4 - b4 + 4 * b2 * c2 - c4);
			case 1996 -> p(a + b - c, 2) * p(a - b + c, 2)
					* (a2 + b2 + 4 * b * c + c2 - 2 * a * (b + c));
			case 1997 -> a3 + b3 - 3 * b2 * c - 3 * b * c2 + c3 - a2 * (b + c)
					- a * (b2 - 8 * b * c + c2);
			case 1998 -> a * (a5 - 3 * a4 * (b + c) + 2 * a3 * p(b + c, 2)
					+ p(b - c, 2) * p(b + c, 3)
					+ 2 * a2 * (b3 + b2 * c + b * c2 + c3) - a * (3 * b4
					+ 4 * b3 * c - 6 * b2 * c2 + 4 * b * c3 + 3 * c4));
			case 1999 -> a3 + a * b * c + a2 * (b + c) - b * c * (b + c);
			default -> Double.NaN;
		};
	}

	private double weight2000to2099(int k, double a, double b, double c) {

		return switch (k) {
			case 2000 -> a * (a5 + 2 * a3 * b * c - a4 * (b + c)
					+ p(b - c, 2) * p(b + c, 3)
					- a * (b4 + 2 * b3 * c - 2 * b2 * c2 + 2 * b * c3 + c4));
			case 2001 -> a2
					* (a8 + 2 * b4 * c4 - a6 * R - a2 * Q * R + a4 * (b4 + c4));
			case 2002 -> a * (a + b - c) * (a - b + c) * (a5 + 2 * a3 * b * c
					- a4 * (b + c) + p(b - c, 2) * p(b + c, 3)
					- a * (b4 + 2 * b3 * c - 2 * b2 * c2 + 2 * b * c3 + c4));
			case 2003 -> a2 * (a + b - c) * (a - b + c) * (a2 - b2 - b * c - c2);
			case 2004 -> a2 * (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 6 * b2 * c2 - 2 * c4)
					+ u(3) * a4 * S;
			case 2005 -> -(a2
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 6 * b2 * c2 - 2 * c4))
					+ u(3) * a4 * S;
			case 2006 -> (a + b - c) * (a - b + c) * (a2 - a * b + b2 - c2)
					* (a2 - b2 - a * c + c2);
			case 2007 -> a2 * R * S + 2 * a2 * b * c * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2008 -> a2 * R * S - 2 * a2 * b * c * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2009 -> a2 * R * S + (a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2010 -> a2 * R * S + (-(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2011 -> -(a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2))
					+ a2 * R * S
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 2012 -> a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* (a2 * b2 + a2 * c2 + b2 * c2)
					+ a2 * R * S
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 2013 -> a2 * (a - b - c) * (a + b - c) * (a - b + c) * (a + b + c)
					* R
					- 2 * a * b * c * (b + c) * S
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2014 -> a2 * (a - b - c) * (a + b - c) * (a - b + c) * (a + b + c)
					* R
					+ 2 * a * b * c * (b + c) * S
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2015 -> -(a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					* u(a2 * b2 + a2 * c2 + b2 * c2))
					- a2 * R * S
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2016 -> -(a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					* u(a2 * b2 + a2 * c2 + b2 * c2))
					+ a2 * R * S
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2017 -> 2 * a2 * R * S - a
					* (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2018 -> 2 * a2 * R * S + a
					* (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2019 -> a2 * R
					* (a2 * b + a * b2 + a2 * c + a * b * c + b2 * c + a * c2
					+ b * c2)
					* S - a2
					* (a3 * b2 + a2 * b3 - a * b4 - b5 + 2 * a3 * b * c
					+ a2 * b2 * c - 2 * a * b3 * c - b4 * c
					+ a3 * c2 + a2 * b * c2 + a2 * c3
					- 2 * a * b * c3 - a * c4 - b * c4 - c5)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2020 -> a2 * R
					* (a2 * b + a * b2 + a2 * c + a * b * c + b2 * c + a * c2
					+ b * c2)
					* S + a2
					* (a3 * b2 + a2 * b3 - a * b4 - b5 + 2 * a3 * b * c
					+ a2 * b2 * c - 2 * a * b3 * c - b4 * c
					+ a3 * c2 + a2 * b * c2 + a2 * c3
					- 2 * a * b * c3 - a * c4 - b * c4 - c5)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2021 -> a2 * (b6 + c6 + R * (3 * a4 - b2 * c2)
					- 2 * a2 * (b4 + b2 * c2 + c4));
			case 2022 -> a2 * (a8 * b + 2 * a7 * b2 - 3 * a6 * b3 - 4 * a5 * b4
					+ 3 * a4 * b5 + 2 * a3 * b6 - a2 * b7 + a8 * c - a6 * b2 * c
					- a4 * b4 * c + a2 * b6 * c + 2 * a7 * c2 - a6 * b * c2
					- 4 * a5 * b2 * c2 - a4 * b3 * c2 + 2 * a3 * b4 * c2
					+ 3 * a2 * b5 * c2 - b7 * c2 - 3 * a6 * c3 - a4 * b2 * c3
					- a2 * b4 * c3 + b6 * c3 - 4 * a5 * c4 - a4 * b * c4
					+ 2 * a3 * b2 * c4 - a2 * b3 * c4 + 3 * a4 * c5
					+ 3 * a2 * b2 * c5 + 2 * a3 * c6 + a2 * b * c6 + b3 * c6
					- a2 * c7 - b2 * c7);
			case 2023 -> b2 * c2 * Q + a6 * R - a4 * p(b2 + c2, 2)
					+ a2 * (2 * b6 - b4 * c2 - b2 * c4 + 2 * c6);
			case 2024 -> a2 * (3 * a6 * b2 - a4 * b4 + a2 * b6 + b8 + 3 * a6 * c2
					- 5 * a2 * b4 * c2 + 2 * b6 * c2 - a4 * c4
					- 5 * a2 * b2 * c4 - 2 * b4 * c4 + a2 * c6 + 2 * b2 * c6
					+ c8);
			case 2025 -> a2 * (b8 + 4 * b6 * c2 - 2 * b4 * c4 + 4 * b2 * c6 + c8
					+ 3 * a6 * R - a4 * (3 * b4 + 4 * b2 * c2 + 3 * c4)
					+ a2 * (3 * b6 - 5 * b4 * c2 - 5 * b2 * c4 + 3 * c6));
			case 2026 -> -(a2 * (a2 * b2 + a2 * c2 + b2 * c2)
					* (a2 * b2 - b4 + a2 * c2 - c4))
					+ a2 * R * S
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 2027 -> a2 * (a2 * b2 + a2 * c2 + b2 * c2)
					* (a2 * b2 - b4 + a2 * c2 - c4)
					+ a2 * R * S
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 2028 -> -(a2 * (a2 * b2 + a2 * c2 + b2 * c2)
					* (a2 * b2 - b4 + a2 * c2 - c4))
					+ a2 * R * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 2029 -> a2 * (a2 * b2 + a2 * c2 + b2 * c2)
					* (a2 * b2 - b4 + a2 * c2 - c4)
					+ a2 * R * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 2030 -> a2 * (4 * a4 + b4 - 4 * b2 * c2 + c4 - a2 * R);
			case 2031 -> a2 * (4 * a6 - 5 * a4 * b2 + 4 * a2 * b4 + b6 - 5 * a4 * c2
					- 6 * a2 * b2 * c2 + b4 * c2 + 4 * a2 * c4 + b2 * c4 + c6);
			case 2032 -> a2 * (4 * a8 - a6 * R + a2 * Q * R + Q * (b4 + c4)
					+ a4 * (3 * b4 - 8 * b2 * c2 + 3 * c4));
			case 2033 -> 2 * a2
					* (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2) * u(
					a2 * b2 + a2 * c2 + b2 * c2)
					- 2 * a2 * R
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 2034 -> 2 * a2
					* (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2) * u(
					a2 * b2 + a2 * c2 + b2 * c2)
					+ 2 * a2 * R
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 2035 -> -2 * a2 * R * (a2 * b2 + a2 * c2 + b2 * c2)
					+ 2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2036 -> a2 * R * (a2 * b2 + a2 * c2 + b2 * c2)
					+ a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2037 -> a2 * (a2 * b2 + a2 * c2 + b2 * c2)
					* (a2 * b2 - b4 + a2 * c2 - c4)
					* (a * b * c + (a + b) * c2 + b2 * (a + c) + a2 * (b + c))
					+ a2 * (-(a3 * b2) - a2 * b3 + a * b4 + b5 - 2 * a3 * b * c
					- a2 * b2 * c + 2 * a * b3 * c + b4 * c - a3 * c2
					- a2 * b * c2 - a2 * c3 + 2 * a * b * c3 + a * c4
					+ b * c4 + c5) * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 2038 -> a2 * (a2 * b2 + a2 * c2 + b2 * c2)
					* (a2 * b2 - b4 + a2 * c2 - c4)
					* (a * b * c + (a + b) * c2 + b2 * (a + c) + a2 * (b + c))
					- a2 * (-(a3 * b2) - a2 * b3 + a * b4 + b5 - 2 * a3 * b * c
					- a2 * b2 * c + 2 * a * b3 * c + b4 * c - a3 * c2
					- a2 * b * c2 - a2 * c3 + 2 * a * b * c3 + a * c4
					+ b * c4 + c5) * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u((a4 + b4 + c4 - b2 * c2 - c2 * a2 - a2 * b2)
					* (b2 * c2 + c2 * a2 + a2 * b2));
			case 2039 -> a2 * (a2 * b2 - b4 + a2 * c2 - c4) + (Q - a2 * R)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 2040 -> a2 * (a2 * b2 - b4 + a2 * c2 - c4) - (Q - a2 * R)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 2041 -> -Q - a2 * R * (-1 + u(3)) + a4 * u(3);
			case 2042 -> Q + a4 * u(3) - a2 * R * (1 + u(3));
			case 2043 -> a4 + Q * (-2 + u(3)) - a2 * R * (-1 + u(3));
			case 2044 -> -a4 - a2 * R * (1 + u(3)) + Q * (2 + u(3));
			case 2045 -> -a4 - a2 * R * (-3 + u(3)) + Q * (-2 + u(3));
			case 2046 -> a4 + Q * (2 + u(3)) - a2 * R * (3 + u(3));
			case 2047 -> -((a - b - c) * (a + b - c) * (a - b + c) * p(a + b + c, 3))
					+ U * V * S;
			case 2048 -> -((a + b + c) * U * V)
					+ (a - b - c) * (a + b - c) * (a - b + c) * S;
			case 2049 -> a4 + 2 * a3 * (b + c) + 3 * a2 * p(b + c, 2)
					+ 2 * b * c * p(b + c, 2) + 2 * a * p(b + c, 3);
			case 2050 -> a6 + 4 * a4 * b * c + 2 * a3 * p(b - c, 2) * (b + c)
					- 2 * b * c * Q - a2 * p(b - c, 2) * (b2 + 4 * b * c + c2)
					- 2 * a * (b5 - b4 * c - b * c4 + c5);
			case 2051 -> -((-b3 + b * c2 + a * c * (-b + c) + a2 * (b + c))
					* (a * b * (b - c) + a2 * (b + c) + c * (b2 - c2)));
			case 2052 -> b2 * c2 * p(a4 - Q, 2);
			case 2053 -> a2 * (a - b - c) * (a * (b - c) - b * c)
					* (a * (b - c) + b * c);
			case 2054 -> a2 * (b + c) * (a2 + b2 + a * (b - c) - b * c - c2)
					* (a2 - b2 - b * c + c2 + a * (-b + c));
			case 2055 -> a2 * T
					* (a10 * a2 + b2 * c2 * p(b2 - c2, 4) - 4 * a10 * R
					+ a4 * Q * (b4 + c4)
					+ a8 * (6 * b4 + 9 * b2 * c2 + 6 * c4)
					- 4 * a6 * (b6 + b4 * c2 + b2 * c4 + c6));
			case 2056 -> a6 + a2 * b2 * c2 - 2 * a4 * R;
			case 2057 -> a * (a - b - c) * (a5 - 2 * a3 * p(b - c, 2) - a4 * (b + c)
					- p(b - c, 2) * p(b + c, 3)
					+ 2 * a2 * (b3 + b2 * c + b * c2 + c3)
					+ a * (b4 - 4 * b3 * c - 2 * b2 * c2 - 4 * b * c3 + c4));
			case 2058 -> u(3) * a2
					* (a10 * a4 - 6 * a10 * a2 * b2 + 15 * a10 * b4
					- 20 * a8 * b6 + 15 * a6 * b8 - 6 * a4 * b10
					+ a2 * b10 * b2 - 6 * a10 * a2 * c2
					+ 24 * a10 * b2 * c2 - 31 * a8 * b4 * c2
					+ 10 * a6 * b6 * c2 + 6 * a4 * b8 * c2
					- 2 * a2 * b10 * c2 - b10 * b2 * c2 + 15 * a10 * c4
					- 31 * a8 * b2 * c4 + 5 * a6 * b4 * c4
					+ 11 * a4 * b6 * c4 - 3 * a2 * b8 * c4
					+ 3 * b10 * c4 - 20 * a8 * c6 + 10 * a6 * b2 * c6
					+ 11 * a4 * b4 * c6 + 8 * a2 * b6 * c6 - 2 * b8 * c6
					+ 15 * a6 * c8 + 6 * a4 * b2 * c8 - 3 * a2 * b4 * c8
					- 2 * b6 * c8 - 6 * a4 * c10
					- 2 * a2 * b2 * c10 + 3 * b4
					* c10
					+ a2 * c10 * c2 - b2 * c10 * c2)
					- a2 * (a10 * a2 - 3 * a10 * b2 + 2 * a8 * b4 + 2 * a6 * b6
					- 3 * a4 * b8 + a2 * b10 - 3 * a10 * c2
					- 6 * a8 * b2 * c2 + 25 * a6 * b4 * c2
					- 19 * a4 * b6 * c2 + 2 * a2 * b8 * c2 + b10 * c2
					+ 2 * a8 * c4 + 25 * a6 * b2 * c4
					- 21 * a4 * b4 * c4 - 3 * a2 * b6 * c4 - 4 * b8 * c4
					+ 2 * a6 * c6 - 19 * a4 * b2 * c6 - 3 * a2 * b4 * c6
					+ 6 * b6 * c6 - 3 * a4 * c8 + 2 * a2 * b2 * c8
					- 4 * b4 * c8 + a2 * c10 + b2 * c10) * S;
			case 2059 -> u(3) * a2
					* (a10 * a4 - 6 * a10 * a2 * b2 + 15 * a10 * b4
					- 20 * a8 * b6 + 15 * a6 * b8 - 6 * a4 * b10
					+ a2 * b10 * b2 - 6 * a10 * a2 * c2
					+ 24 * a10 * b2 * c2 - 31 * a8 * b4 * c2
					+ 10 * a6 * b6 * c2 + 6 * a4 * b8 * c2
					- 2 * a2 * b10 * c2 - b10 * b2 * c2 + 15 * a10 * c4
					- 31 * a8 * b2 * c4 + 5 * a6 * b4 * c4
					+ 11 * a4 * b6 * c4 - 3 * a2 * b8 * c4
					+ 3 * b10 * c4 - 20 * a8 * c6 + 10 * a6 * b2 * c6
					+ 11 * a4 * b4 * c6 + 8 * a2 * b6 * c6 - 2 * b8 * c6
					+ 15 * a6 * c8 + 6 * a4 * b2 * c8 - 3 * a2 * b4 * c8
					- 2 * b6 * c8 - 6 * a4 * c10
					- 2 * a2 * b2 * c10 + 3 * b4
					* c10
					+ a2 * c10 * c2 - b2 * c10 * c2)
					+ a2 * (a10 * a2 - 3 * a10 * b2 + 2 * a8 * b4 + 2 * a6 * b6
					- 3 * a4 * b8 + a2 * b10 - 3 * a10 * c2
					- 6 * a8 * b2 * c2 + 25 * a6 * b4 * c2
					- 19 * a4 * b6 * c2 + 2 * a2 * b8 * c2 + b10 * c2
					+ 2 * a8 * c4 + 25 * a6 * b2 * c4
					- 21 * a4 * b4 * c4 - 3 * a2 * b6 * c4 - 4 * b8 * c4
					+ 2 * a6 * c6 - 19 * a4 * b2 * c6 - 3 * a2 * b4 * c6
					+ 6 * b6 * c6 - 3 * a4 * c8 + 2 * a2 * b2 * c8
					- 4 * b4 * c8 + a2 * c10 + b2 * c10) * S;
			case 2060 -> (3 * a4 - Q - 2 * a2 * R) * (5 * a10 * a2 + p(b2 - c2, 6)
					- 10 * a10 * R + 36 * a6 * Q * R
					+ a8 * (-9 * b4 + 34 * b2 * c2 - 9 * c4)
					- a4 * Q * (29 * b4 + 54 * b2 * c2 + 29 * c4) + 2 * a2 * Q
					* (3 * b6 + 13 * b4 * c2 + 13 * b2 * c4 + 3 * c6));
			case 2061 -> a2
					* (a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					- a * p(b + c, 2))
					* (a10 * a4 + 2 * a10 * a3 * (b + c)
					+ 2 * a * p(b - c, 6) * p(b + c, 7)
					+ a10 * a2 * (-5 * b2 + 3 * b * c - 5 * c2)
					- 5 * a2 * p(b - c, 6) * p(b + c, 4) * R
					- 4 * a3 * p(b - c, 4) * p(b + c, 5)
					* (3 * b2 - 2 * b * c + 3 * c2)
					- 4 * a10 * a * (3 * b3 + b2 * c + b * c2 + 3 * c3)
					+ p(b - c, 4) * p(b + c, 6)
					* (b4 - 3 * b3 * c + 8 * b2 * c2
					- 3 * b * c3 + c4)
					+ a10 * (9 * b4 - 6 * b3 * c + 26 * b2 * c2
					- 6 * b * c3 + 9 * c4)
					+ 2 * a5 * p(b - c, 2) * p(b + c, 3)
					* (15 * b4 - 16 * b3 * c + 18 * b2 * c2
					- 16 * b * c3 + 15 * c4)
					- 8 * a7 * p(b - c, 2)
					* (5 * b5 + 9 * b4 * c + 10 * b3 * c2
					+ 10 * b2 * c3 + 9 * b * c4
					+ 5 * c5)
					+ a9 * (30 * b5 - 2 * b4 * c + 4 * b3 * c2
					+ 4 * b2 * c3 - 2 * b * c4 + 30 * c5)
					- a6 * p(b - c, 2)
					* (5 * b6 - 18 * b5 * c - 101 * b4 * c2
					- 60 * b3 * c3 - 101 * b2 * c4
					- 18 * b * c5 + 5 * c6)
					- a8 * (5 * b6 + 7 * b5 * c + 55 * b4 * c2
					- 70 * b3 * c3 + 55 * b2 * c4 + 7 * b * c5
					+ 5 * c6)
					+ a4 * p(b - c, 2)
					* (9 * b8 - 9 * b7 * c - 62 * b6 * c2
					- 39 * b5 * c3 - 54 * b4 * c4
					- 39 * b3 * c5 - 62 * b2 * c6
					- 9 * b * c7 + 9 * c8));
			case 2062 -> a2 * (a + b - c) * (a - b + c) * T
					* (a8 + 5 * a4 * b * c * p(b + c, 2)
					- a6 * (2 * b2 + 3 * b * c + 2 * c2)
					- Q * (b4 + b3 * c + 4 * b2 * c2 + b * c3 + c4)
					+ a2 * p(b + c, 2) * (2 * b4 - 5 * b3 * c
					+ 2 * b2 * c2 - 5 * b * c3 + 2 * c4));
			case 2063 -> a2 * T
					* (a8 + 10 * a4 * b2 * c2 - 2 * a6 * R
					- Q * (b4 + 4 * b2 * c2 + c4)
					+ 2 * a2 * (b6 - 3 * b4 * c2 - 3 * b2 * c4 + c6));
			case 2064 -> b * c * (-a4 + b4 - a2 * b * c + b3 * c + b * c3 + c4);
			case 2065 -> a2 * (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4)
					* (a4 + b4 - b2 * c2 + 2 * c4 - a2 * (2 * b2 + c2))
					* (a4 + 2 * b4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 2066 -> a2 * (a - b - c) * (a + b + c) - a2 * S;
			case 2067 -> a2 * (a + b - c) * (a - b + c) - a2 * S;
			case 2068 -> a * (a + u(b) * u(c));
			case 2069 -> a * (a - u(b) * u(c));
			case 2070 -> a2 * (a8 + a4 * b2 * c2 - 2 * a6 * R - Q * (b4 + c4)
					+ a2 * (2 * b6 - b4 * c2 - b2 * c4 + 2 * c6));
			case 2071 -> a2 * (a8 + 7 * a4 * b2 * c2 - 2 * a6 * R
					- Q * (b4 + 3 * b2 * c2 + c4)
					+ 2 * a2 * (b6 - 2 * b4 * c2 - 2 * b2 * c4 + c6));
			case 2072 -> -(T
					* (p(b2 - c2, 4) + a6 * R - a2 * Q * R - a4 * (b4 + c4)));
			case 2073 -> a2 * (a + b) * (a + c) * U * V
					* (b4 + b3 * c + b2 * c2 + b * c3 + c4 + a3 * (b + c)
					- a2 * (b2 + b * c + c2)
					- a * (b3 + b2 * c + b * c2 + c3));
			case 2074 -> a * (a + b) * (a + c) * U * V * (a3 + b3 + b2 * c + b * c2
					+ c3 - a2 * (b + c) - a * (b2 + b * c + c2));
			case 2075 -> a2 * (a + b) * (a + c) * U * V
					* (b5 - a3 * b * c + c5 + a4 * (b + c)
					+ a * b * c * (b2 + b * c + c2)
					- a2 * (2 * b3 + b2 * c + b * c2 + 2 * c3));
			case 2076 -> a2 * (a4 - b4 - b2 * c2 - c4 + a2 * R);
			case 2077 -> a2 * (a5 - b5 + b3 * c2 + b2 * c3 - c5 - a4 * (b + c)
					+ a3 * (-2 * b2 + 3 * b * c - 2 * c2)
					+ a2 * (2 * b3 + b2 * c + b * c2 + 2 * c3)
					+ a * (b4 - 3 * b3 * c - 3 * b * c3 + c4));
			case 2078 -> a2 * (a + b - c) * (a - b + c)
					* (a2 + b2 + b * c + c2 - 2 * a * (b + c));
			case 2079 -> a2 * (a8 + 5 * a4 * b2 * c2 - 2 * a6 * R - Q * (b4 + c4)
					+ a2 * (2 * b6 - 3 * b4 * c2 - 3 * b2 * c4 + 2 * c6));
			case 2080 -> a2 * (a6 - 3 * a4 * R + b2 * c2 * R
					+ a2 * (2 * b4 - b2 * c2 + 2 * c4));
			case 2081 -> a2 * (b2 - c2) * (a2 - b2 - b * c - c2)
					* (a2 - b2 + b * c - c2) * (-Q + a2 * R);
			case 2082 -> a * (a2 + p(b - c, 2)) * (a - b - c);
			case 2083 -> a * T * (a4 + Q);
			case 2084 -> a3 * (-b4 + c4);
			case 2085 -> a3 * (b4 + c4);
			case 2086 -> a2 * p(b - c, 2) * p(b + c, 2) * (a2 - b * c) * (a2 + b * c);
			case 2087 -> -(a * (2 * a - b - c) * p(b - c, 2));
			case 2088 -> a2 * p(b - c, 2) * p(b + c, 2) * (a2 - b2 - b * c - c2)
					* (a2 - b2 + b * c - c2);
			case 2089 -> a * ((a + b - c) * c * u(b * (a - b + c))
					+ b * (a - b + c) * u(c * (a + b - c)) - u(a * (-a + b + c))
					* u(b * (a - b + c)) * u(c * (a + b - c)));
			case 2090 -> (-a + b + c) * ((a + b - c) * u(b * (a - b + c))
					+ (a - b + c) * u(c * (a + b - c)));
			case 2091 -> (a + b - c) * (a - b + c) * u(a)
					* (u(c) * u(a - b + c) + u(b) * u(a + b - c));
			case 2092 -> a2 * (b + c) * (b2 + c2 + a * (b + c));
			case 2093 -> a * (a3 + 3 * a2 * (b + c) - 3 * p(b - c, 2) * (b + c)
					- a * p(b + c, 2));
			case 2094 -> 5 * a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					+ a * (-5 * b2 + 6 * b * c - 5 * c2);
			case 2095 -> a * (a6 + a5 * (b + c) - 2 * p(b - c, 4) * p(b + c, 2)
					- 2 * a4 * (2 * b2 + b * c + 2 * c2)
					- 2 * a3 * (b3 + c3) + a2 * (5 * b4 - 2 * b3 * c
					+ 2 * b2 * c2 - 2 * b * c3 + 5 * c4)
					+ a * (b5 - b4 * c - b * c4 + c5));
			case 2096 -> 3 * a7 - a6 * (b + c) + a4 * p(b - c, 2) * (b + c)
					+ a2 * p(b - c, 2) * p(b + c, 3) - p(b - c, 4) * p(b + c, 3)
					- a * p(b - c, 2) * p(b + c, 4)
					+ a5 * (-7 * b2 + 10 * b * c - 7 * c2)
					+ a3 * p(b - c, 2) * (5 * b2 + 2 * b * c + 5 * c2);
			case 2097 -> a * (a4 + a2 * p(b - c, 2) + a3 * (b + c)
					- a * p(b - c, 2) * (b + c) - 2 * p(b - c, 2) * R);
			case 2098 -> a * (a - b - c) * (a2 - 2 * p(b - c, 2) - a * (b + c));
			case 2099 -> a * (a + b - c) * (a - b + c) * (a - 2 * (b + c));
			default -> Double.NaN;
		};
	}

	private double weight2100to2199(int k, double a, double b, double c) {
		return switch (k) {
			case 2100 -> -2 * a * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4) + a
					* (a3 + a2 * b - a * b2 - b3 + a2 * c
					- 2 * a * b * c + b2 * c - a * c2 + b * c2
					- c3)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2101 -> 2 * a * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4) + a
					* (a3 + a2 * b - a * b2 - b3 + a2 * c
					- 2 * a * b * c + b2 * c - a * c2 + b * c2
					- c3)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2102 -> a * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4) + a
					* (a3 - 2 * a2 * b - a * b2 + 2 * b3 - 2 * a2 * c
					+ 4 * a * b * c - 2 * b2 * c - a * c2
					- 2 * b * c2 + 2 * c3)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2103 -> -(a * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4))
					+ a * (a3 - 2 * a2 * b - a * b2 + 2 * b3 - 2 * a2 * c
					+ 4 * a * b * c - 2 * b2 * c - a * c2 - 2 * b * c2
					+ 2 * c3)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2104 -> a * b * c * (a2 + b2 + c2)
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2
					- c4)
					+ a2 * (a4 - 4 * a2 * b2 + 3 * b4 - 4 * a2 * c2
					- 2 * b2 * c2 + 3 * c4)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2105 -> -(a * b * c * (a2 + b2 + c2)
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4))
					+ a2 * (a4 - 4 * a2 * b2 + 3 * b4 - 4 * a2 * c2
					- 2 * b2 * c2 + 3 * c4)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2106 -> a * (a + b) * (a + c) * (-(b2 * c2) - a * b * c * (b + c)
					+ a2 * (b2 + b * c + c2));
			case 2107 -> a2 * (b + c)
					* (a * b * (b - c) * c + b2 * c2 + a2 * (b2 - b * c - c2))
					* (a * b * (b - c) * c - b2 * c2 + a2 * (b2 + b * c - c2));
			case 2108 -> a * (a3 * (b + c) - a2 * (b2 - b * c + c2)
					+ b * c * (b2 + b * c + c2)
					- a * (b3 + b2 * c + b * c2 + c3));
			case 2109 -> a2
					* (a3 * (b - c) + a2 * (b2 + b * c - c2)
					+ b * c * (-b2 + b * c + c2)
					- a * (b3 + b2 * c - b * c2 + c3))
					* (a3 * (b - c) + a2 * (b2 - b * c - c2)
					- b * c * (b2 + b * c - c2)
					+ a * (b3 - b2 * c + b * c2 + c3));
			case 2110 -> a2 * (b2 * c2 * (b + c) - a * b * c * (b2 - b * c + c2)
					+ a3 * (b2 + b * c + c2)
					- a2 * (b3 + b2 * c + b * c2 + c3));
			case 2111 -> a * (b2 * c2 * (-b + c) + a3 * (b2 - b * c - c2)
					- a * b * c * (b2 + b * c - c2)
					+ a2 * (b3 + b2 * c - b * c2 + c3))
					* (b2 * c2 * (-b + c) + a3 * (b2 + b * c - c2)
					+ a * b * c * (-b2 + b * c + c2)
					- a2 * (b3 - b2 * c + b * c2 + c3));
			case 2112 -> a2 * (a4 - a2 * b * c + 2 * b2 * c2 - a * (b3 + c3));
			case 2113 -> -(a * (a3 * b - b4 + a * b2 * c - 2 * a2 * c2 + b * c3)
					* (-2 * a2 * b2 + a3 * c + b3 * c + a * b * c2 - c4));
			case 2114 -> a * (a + b - c) * (a - b + c)
					* (a4 + a3 * (b + c) - p(b - c, 2) * (b2 + b * c + c2)
					- a2 * (2 * b2 + 3 * b * c + 2 * c2)
					+ a * (b3 + b2 * c + b * c2 + c3));
			case 2115 -> a2 * (a - b - c) * (a4 + b4 - b3 * c + 2 * b2 * c2 - b * c3
					- c4
					- a3 * (b + c) + a2 * c
					* (-b + 2
					* c)
					- a * (b3 + b2 * c - 3 * b * c2 + c3))
					* (a4 - b4 + a2 * b * (2 * b - c) - b3 * c + 2 * b2 * c2
					- b * c3 + c4 - a3 * (b + c)
					- a * (b3 - 3 * b2 * c + b * c2 + c3));
			case 2116 -> a * ((b - c) * c + a * (2 * b + c))
					* (b * (-b + c) + a * (b + 2 * c))
					* (-(b2 * p(b - c, 2) * c2) + 2 * a5 * (b + c)
					- 3 * a3 * b * c * (b + c) - a4 * (b2 - b * c + c2)
					- a * b * c * (b3 - 3 * b2 * c - 3 * b * c2 + c3)
					- a2 * (b4 - b3 * c + b2 * c2 - b * c3 + c4));
			case 2117 -> a2 * (a2 - 2 * b * c - a * (b + c)) * (a4 * (b2 + b * c + c2)
					- a3 * c * (b2 + 3 * b * c + 2 * c2)
					+ b2 * c * (-2 * b3 + b2 * c + c3)
					- a * b * (2 * b4 + b3 * c - 3 * b2 * c2 + b * c3 - c4)
					+ a2 * (b4 + 3 * b3 * c + b2 * c2 - 3 * b * c3 + c4))
					* (a4 * (b2 + b * c + c2)
					- a3 * b * (2 * b2 + 3 * b * c + c2)
					+ b * c2 * (b3 + b * c2 - 2 * c3)
					+ a * c * (b4 - b3 * c + 3 * b2 * c2 - b * c3
					- 2 * c4)
					+ a2 * (b4 - 3 * b3 * c + b2 * c2 + 3 * b * c3
					+ c4));
			case 2118 -> a * u(a) * ((a + b + c) * u(a) * u(b) * u(c)
					- (a * b + a * c + b * c) * (-u(a) + u(b) + u(c)));
			case 2119 -> a * u(a) * (-(b3 * c2) - b2 * c3 + a3 * (b2 + 3 * b * c + c2)
					+ a2 * (-b3 + b2 * c + b * c2 - c3)
					+ a * (-(b3 * c) - b2 * c2 - b * c3)
					- 2 * (a2 - b * c) * (a * b + a * c + b * c) * u(b) * u(c));
			case 2120 -> a2 * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2))
					* (a10 * a8 - 6 * a10 * a6 * R
					+ 3 * a10 * a4 * (5 * b4 + 8 * b2 * c2 + 5 * c4)
					- p(b2 - c2, 6) * (b6 + c6)
					- a10 * a2
					* (21 * b6 + 37 * b4 * c2 + 37 * b2 * c4
					+ 21 * c6)
					+ 3 * a2 * p(b2 - c2, 4)
					* (2 * b8 - b4 * c4 + 2 * c8)
					+ a6 * Q * (21 * b8 + 22 * b6 * c2 + 24 * b4 * c4
					+ 22 * b2 * c6 + 21 * c8)
					+ a10 * (21 * b8 + 28 * b6 * c2 + 31 * b4 * c4
					+ 28 * b2 * c6 + 21 * c8)
					- 3 * a8 * (7 * b10 + 2 * b8 * c2 + 3 * b6 * c4
					+ 3 * b4 * c6 + 2 * b2 * c8 + 7 * c10)
					- a4 * Q * (15 * b10 - 5 * b8 * c2 - 6 * b6 * c4
					- 6 * b4 * c6 - 5 * b2 * c8 + 15 * c10));
			case 2121 -> (-Q + a2 * R)
					* (a10 * a8 - 6 * a10 * a6 * R
					- p(b2 - c2, 7) * (b4 + b2 * c2 + c4)
					+ 3 * a10 * a4 * (5 * b4 + 8 * b2 * c2 + 5 * c4)
					- a10 * a2
					* (21 * b6 + 35 * b4 * c2 + 33 * b2 * c4
					+ 19 * c6)
					+ a2 * p(b2 - c2, 4) * (6 * b8 + b4 * c4 - 6 * c8)
					+ a10 * (21 * b8 + 20 * b6 * c2 + 19 * b4 * c4
					+ 12 * b2 * c6 + 9 * c8)
					- a4 * p(b2 - c2, 3)
					* (15 * b8 + 8 * b6 * c2 + 10 * b4 * c4
					+ 12 * b2 * c6 + 15 * c8)
					+ a8 * (-21 * b10 + 6 * b8 * c2 - b6 * c4 + b4 * c6
					+ 6 * b2 * c8 + 9 * c10)
					+ a6 * (21 * b10 * b2 - 28 * b10 * c2 + 9 * b8 * c4
					+ 4 * b6 * c6 + b4 * c8 + 12 * b2 * c10
					- 19 * c10 * c2))
					* (a10 * a8 - 6 * a10 * a6 * R
					+ p(b2 - c2, 7) * (b4 + b2 * c2 + c4)
					+ 3 * a10 * a4 * (5 * b4 + 8 * b2 * c2 + 5 * c4)
					- a10 * a2
					* (19 * b6 + 33 * b4 * c2 + 35 * b2 * c4
					+ 21 * c6)
					- a2 * p(b2 - c2, 4) * (6 * b8 - b4 * c4 - 6 * c8)
					+ a4 * p(b2 - c2, 3)
					* (15 * b8 + 12 * b6 * c2 + 10 * b4 * c4
					+ 8 * b2 * c6 + 15 * c8)
					+ a10 * (9 * b8 + 12 * b6 * c2 + 19 * b4 * c4
					+ 20 * b2 * c6 + 21 * c8)
					+ a8 * (9 * b10 + 6 * b8 * c2 + b6 * c4 - b4 * c6
					+ 6 * b2 * c8 - 21 * c10)
					+ a6 * (-19 * b10 * b2 + 12 * b10 * c2 + b8 * c4
					+ 4 * b6 * c6 + 9 * b4 * c8 - 28 * b2 * c10
					+ 21 * c10 * c2));
			case 2122 -> a2 * (a + b - c) * (a - b + c)
					* (a6 + 4 * a3 * b * c * (b + c)
					- 4 * a * b * p(b - c, 2) * c * (b + c)
					+ a4 * (-3 * b2 + 4 * b * c - 3 * c2) - Q * R
					+ a2 * (3 * b4 - 4 * b3 * c - 6 * b2 * c2
					- 4 * b * c3 + 3 * c4));
			case 2123 -> (a - b - c)
					* (a6 + 4 * a3 * b * c * (-b + c)
					+ 4 * a * b * (b - c) * c * p(b + c, 2)
					+ p(b2 - c2, 3) - a4 * (b2 - 4 * b * c + 3 * c2)
					- a2 * (b4 + 4 * b3 * c - 6 * b2 * c2 + 4 * b * c3
					- 3 * c4))
					* (a6 + 4 * a3 * b * (b - c) * c
					- 4 * a * b * (b - c) * c * p(b + c, 2)
					- p(b2 - c2, 3) - a4 * (3 * b2 - 4 * b * c + c2)
					+ a2 * (3 * b4 - 4 * b3 * c + 6 * b2 * c2
					- 4 * b * c3 - c4));
			case 2124 -> a * (a + b - c) * (a - b + c)
					* (a4 - 4 * a3 * (b + c) - 4 * a * p(b - c, 2) * (b + c)
					+ p(b - c, 2) * (b2 + 6 * b * c + c2)
					+ a2 * (6 * b2 - 4 * b * c + 6 * c2));
			case 2125 -> a * (a - b - c)
					* (a4 - 4 * a3 * (b - c) + p(b - c, 4)
					- 4 * a * (b - c) * p(b + c, 2)
					+ 2 * a2 * (3 * b2 + 2 * b * c - 5 * c2))
					* (a4 + 4 * a3 * (b - c) + p(b - c, 4)
					+ 4 * a * (b - c) * p(b + c, 2)
					+ a2 * (-10 * b2 + 4 * b * c + 6 * c2));
			case 2126 -> a2 * (a + b) * (a + c) * (a6 + 2 * a5 * (b + c)
					+ 2 * a3 * b * c * (b + c)
					- 2 * a * p(b + c, 3) * (b2 - b * c + c2)
					+ a4 * (b2 + 4 * b * c + c2) - p(b + c, 2) * (b4 + c4)
					- a2 * (b4 + 2 * b3 * c + b2 * c2 + 2 * b * c3 + c4));
			case 2127 -> (b + c)
					* (-a6 - 2 * a5 * (b + c) - 2 * a3 * b * c * (b + c)
					- a4 * (b2 + 4 * b * c + c2)
					+ 2 * a * p(b + c, 2) * (b3 - c3)
					+ p(b + c, 3) * (b3 - b2 * c + b * c2 - c3)
					+ a2 * (b4 + 2 * b3 * c - b2 * c2 - 2 * b * c3
					- c4))
					* (a6 + 2 * a5 * (b + c) + 2 * a3 * b * c * (b + c)
					+ a4 * (b2 + 4 * b * c + c2)
					+ 2 * a * p(b + c, 2) * (b3 - c3)
					+ p(b + c, 3) * (b3 - b2 * c + b * c2 - c3)
					+ a2 * (b4 + 2 * b3 * c + b2 * c2 - 2 * b * c3
					- c4));
			case 2128 -> a * T * (a4 + b4 - 6 * b2 * c2 + c4 + 2 * a2 * R);
			case 2129 -> a * U * V * (a4 + 2 * a2 * (b2 - 3 * c2) + p(b2 + c2, 2))
					* (a4 + p(b2 + c2, 2) + a2 * (-6 * b2 + 2 * c2));
			case 2130 -> a2 * T
					* (a4 + b4 + 2 * b2 * c2 - 3 * c4 - 2 * a2 * (b2 - c2))
					* (a4 - 3 * b4 + 2 * b2 * c2 + c4 + 2 * a2 * (b2 - c2))
					* (a10 * a6 - 8 * a10 * a4 * R - 56 * a10 * Q * R
					- 8 * a2 * p(b2 - c2, 6) * R
					+ p(b2 - c2, 6) * (b4 + 14 * b2 * c2 + c4)
					+ 4 * a10 * a2 * (7 * b4 - 10 * b2 * c2 + 7 * c4)
					+ 2 * a8 * Q * (35 * b4 + 114 * b2 * c2 + 35 * c4)
					- 8 * a6 * Q
					* (7 * b6 + 25 * b4 * c2 + 25 * b2 * c4
					+ 7 * c6)
					+ 4 * a4 * Q * (7 * b8 + 50 * b4 * c4 + 7 * c8));
			case 2131 -> U * V * (3 * a4 - Q - 2 * a2 * R) * (a10 * a6
					- 8 * a10 * a4 * (b2 - c2) + p(b2 - c2, 8)
					+ 4 * a10 * a2 * (7 * b4 + 10 * b2 * c2 - 17 * c4)
					- 8 * a2 * p(b2 - c2, 3) * p(b2 + c2, 2)
					* (b4 + 6 * b2 * c2 + c4)
					- 8 * a10 * (7 * b6 + 7 * b4 * c2 + 9 * b2 * c4 - 23 * c6)
					+ a8 * (70 * b8 - 88 * b6 * c2 + 228 * b4 * c4
					+ 40 * b2 * c6 - 250 * c8)
					+ 4 * a4 * Q
					* (7 * b8 + 28 * b6 * c2 - 30 * b4 * c4
					- 52 * b2 * c6 - 17 * c8)
					- 8 * a6 * (7 * b10 - 11 * b8 * c2 - 18 * b6 * c4
					+ 50 * b4 * c6 - 5 * b2 * c8 - 23 * c10))
					* (a10 * a6 + 8 * a10 * a4 * (b2 - c2) + p(b2 - c2, 8)
					+ 8 * a2 * p(b2 - c2, 3) * p(b2 + c2, 2)
					* (b4 + 6 * b2 * c2 + c4)
					+ a10 * a2 * (-68 * b4 + 40 * b2 * c2 + 28 * c4)
					+ 8 * a10
					* (23 * b6 - 9 * b4 * c2 - 7 * b2 * c4
					- 7 * c6)
					- 4 * a4 * Q
					* (17 * b8 + 52 * b6 * c2 + 30 * b4 * c4
					- 28 * b2 * c6 - 7 * c8)
					+ a8 * (-250 * b8 + 40 * b6 * c2 + 228 * b4 * c4
					- 88 * b2 * c6 + 70 * c8)
					+ 8 * a6 * (23 * b10 + 5 * b8 * c2 - 50 * b6 * c4
					+ 18 * b4 * c6 + 11 * b2 * c8 - 7 * c10));
			case 2132 -> a2 * (a4 - 2 * b4 + b2 * c2 + c4 + a2 * (b2 - 2 * c2))
					* (a4 + b4 + b2 * c2 - 2 * c4 + a2 * (-2 * b2 + c2))
					* (a10 * a8 - 6 * a10 * a6 * R
					+ a10 * a4 * (15 * b4 + 8 * b2 * c2 + 15 * c4)
					- p(b2 - c2, 6)
					* (b6 + 8 * b4 * c2 + 8 * b2 * c4 + c6)
					- a10 * a2
					* (21 * b6 + 5 * b4 * c2 + 5 * b2 * c4
					+ 21 * c6)
					+ a2 * p(b2 - c2, 4)
					* (6 * b8 + 8 * b6 * c2 - 19 * b4 * c4
					+ 8 * b2 * c6 + 6 * c8)
					+ 3 * a10
					* (7 * b8 + 12 * b6 * c2 - 27 * b4 * c4
					+ 12 * b2 * c6 + 7 * c8)
					+ a6 * Q * (21 * b8 + 22 * b6 * c2 + 120 * b4 * c4
					+ 22 * b2 * c6 + 21 * c8)
					- 3 * a4 * Q
					* (5 * b10 - 7 * b8 * c2 + 14 * b6 * c4
					+ 14 * b4 * c6 - 7 * b2 * c8
					+ 5 * c10)
					- a8 * (21 * b10 + 46 * b8 * c2 - 63 * b6 * c4
					- 63 * b4 * c6 + 46 * b2 * c8 + 21 * c10));
			case 2133 -> (2 * a4 - Q - a2 * R)
					* (a10 * a8 + 2 * a10 * a6 * (b2 - 3 * c2)
					+ p(b2 - c2, 7) * (b4 + b2 * c2 + c4)
					+ a10 * a4 * (-25 * b4 + 16 * b2 * c2 + 15 * c4)
					+ a10 * a2
					* (53 * b6 + 15 * b4 * c2 - 51 * b2 * c4
					- 21 * c6)
					- a4 * p(b2 - c2, 3)
					* (25 * b8 + 60 * b6 * c2 + 6 * b4 * c4
					- 40 * b2 * c6 - 15 * c8)
					+ a2 * p(b2 - c2, 4)
					* (2 * b8 + 24 * b6 * c2 + 33 * b4 * c4
					+ 16 * b2 * c6 + 6 * c8)
					+ a10 * (-31 * b8 - 108 * b6 * c2 + 99 * b4 * c4
					+ 20 * b2 * c6 + 21 * c8)
					- a8 * (31 * b10 - 166 * b8 * c2 + 63 * b6 * c4
					+ 97 * b4 * c6 - 46 * b2 * c8 + 21 * c10)
					+ a6 * (53 * b10 * b2 - 108 * b10 * c2
					- 63 * b8 * c4 + 196 * b6 * c6
					- 63 * b4 * c8 - 36 * b2 * c10
					+ 21 * c10 * c2))
					* (a10 * a8 + a10 * a6 * (-6 * b2 + 2 * c2)
					+ a10 * a4 * (15 * b4 + 16 * b2 * c2 - 25 * c4)
					- p(b2 - c2, 7) * (b4 + b2 * c2 + c4)
					+ a10 * a2
					* (-21 * b6 - 51 * b4 * c2 + 15 * b2 * c4
					+ 53 * c6)
					+ a10 * (21 * b8 + 20 * b6 * c2 + 99 * b4 * c4
					- 108 * b2 * c6 - 31 * c8)
					- a4 * p(b2 - c2, 3)
					* (15 * b8 + 40 * b6 * c2 - 6 * b4 * c4
					- 60 * b2 * c6 - 25 * c8)
					+ a2 * p(b2 - c2, 4)
					* (6 * b8 + 16 * b6 * c2 + 33 * b4 * c4
					+ 24 * b2 * c6 + 2 * c8)
					- a8 * (21 * b10 - 46 * b8 * c2 + 97 * b6 * c4
					+ 63 * b4 * c6 - 166 * b2 * c8 + 31 * c10)
					+ a6 * (21 * b10 * b2 - 36 * b10 * c2 - 63 * b8 * c4
					+ 196 * b6 * c6 - 63 * b4 * c8
					- 108 * b2 * c10 + 53 * c10 * c2));
			case 2134 -> a * (a + b) * (a + c)
					* (a4 - b4 - 2 * b3 * c - b2 * c2 - 2 * b * c3 - c4
					+ 2 * a3 * (b + c) - a2 * R
					- 2 * a * (b3 + 2 * b2 * c + 2 * b * c2 + c3));
			case 2135 -> -(a * (b + c)
					* (a4 + b4 + 2 * b3 * c + b2 * c2 - 2 * b * c3 - c4
					+ 2 * a3 * (b + c) + a2 * (b2 + 4 * b * c + c2)
					+ 2 * a * (b3 + 2 * b2 * c - c3))
					* (a4 - b4 - 2 * b3 * c + b2 * c2 + 2 * b * c3 + c4
					+ 2 * a3 * (b + c) + a2 * (b2 + 4 * b * c + c2)
					+ a * (-2 * b3 + 4 * b * c2 + 2 * c3)));
			case 2136 -> a * (a - b - c)
					* (a2 + b2 - 6 * b * c + c2 + 2 * a * (b + c));
			case 2137 -> a * (a + b - c) * (a - b + c)
					* (a2 + 2 * a * (b - 3 * c) + p(b + c, 2))
					* (a2 + p(b + c, 2) + a * (-6 * b + 2 * c));
			case 2138 -> a2 * U * V
					* (a10 + a2 * p(b2 - c2, 4) - a8 * R - Q * p(b2 + c2, 3)
					- 2 * a6 * (b4 - 6 * b2 * c2 + c4)
					+ 2 * a4 * (b6 - 3 * b4 * c2 - 3 * b2 * c4 + c6));
			case 2139 -> T * (a10 + a8 * (b2 - c2) - 2 * a6 * Q
					+ p(b2 - c2, 3) * p(b2 + c2, 2)
					- 2 * a4 * (b6 + 3 * b4 * c2 - 3 * b2 * c4 - c6)
					+ a2 * (b8 + 4 * b6 * c2 + 6 * b4 * c4 - 12 * b2 * c6 + c8))
					* (a10 - 2 * a6 * Q + a8 * (-b2 + c2)
					- p(b2 - c2, 3) * p(b2 + c2, 2)
					+ 2 * a4 * (b6 + 3 * b4 * c2 - 3 * b2 * c4 - c6)
					+ a2 * (b8 - 12 * b6 * c2 + 6 * b4 * c4
					+ 4 * b2 * c6 + c8));
			case 2140 -> b * p(b - c, 2) * c + a * p(b - c, 2) * (b + c) - a2 * R;
			case 2141 -> a2
					* (a * b2 * (b - c) + b2 * (b - c) * c + a3 * (b + c)
					- a2 * (2 * b2 + b * c + c2))
					* (a * c2 * (-b + c) + b * c2 * (-b + c) + a3 * (b + c)
					- a2 * (b2 + b * c + 2 * c2));
			case 2142 -> (a - b) * (a + b) * (a - c) * (a + c)
					* (a8 * b2 * c2 + b6 * c6
					- a6 * (b6 + b4 * c2 + b2 * c4 + c6)
					- a2 * b2 * c2 * (b6 + b4 * c2 + b2 * c4 + c6)
					+ a4 * (5 * b6 * c2 - 4 * b4 * c4 + 5 * b2 * c6));
			case 2143 -> a2 * (b2 - c2)
					* (a8 * b2 * c2 + b6 * c6
					+ a4 * b2 * c2 * (b4 + 4 * b2 * c2 + c4)
					+ a2 * b2 * c2 * (b6 - 5 * b4 * c2 + b2 * c4 - c6)
					+ a6 * (-b6 + b4 * c2 - 5 * b2 * c4 + c6))
					* (a8 * b2 * c2 + b6 * c6
					+ a4 * b2 * c2 * (b4 + 4 * b2 * c2 + c4)
					+ a6 * (b6 - 5 * b4 * c2 + b2 * c4 - c6)
					+ a2 * (-(b8 * c2) + b6 * c4 - 5 * b4 * c6
					+ b2 * c8));
			case 2144 -> a2 * (-b2 + a * c) * (a * b - c2)
					* (-5 * a5 * b2 * c2 - 5 * a * b4 * c4 + a6 * (b3 + c3)
					+ a4 * b * c * (b3 + c3) + a2 * b2 * c2 * (b3 + c3)
					+ b3 * c3 * (b3 + c3)
					- a3 * (b6 - 4 * b3 * c3 + c6));
			case 2145 -> a * (a2 - b * c)
					* (-(a5 * b2 * c2) - b6 * c3 - a * b4 * c4 + b3 * c6
					+ a6 * (b3 - c3) + a4 * (-(b4 * c) + 5 * b * c4)
					+ a2 * (5 * b5 * c2 - b2 * c5)
					- a3 * (b6 + 4 * b3 * c3 + c6))
					* (a5 * b2 * c2 - b6 * c3 + a * b4 * c4 + b3 * c6
					+ a2 * b2 * c2 * (b3 - 5 * c3) + a6 * (b3 - c3)
					+ a4 * (-5 * b4 * c + b * c4)
					+ a3 * (b6 + 4 * b3 * c3 + c6));
			case 2146 -> a * u(a)
					* (-((b2 - a * c) * (-(a * b) + c2) * u(a))
					+ (a2 - b * c) * (-(a * b) + c2) * u(b)
					+ (b2 - a * c) * (a2 - b * c) * u(c));
			case 2147 -> (a * u(a)) / (-(u(a) / (a2 - b * c)) + u(b) / (b2 - a * c)
					+ u(c) / (-(a * b) + c2));
			case 2148 -> a3 * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 2149 -> a3 * p(a - b, 2) * p(a - c, 2) * (a + b - c) * (a - b + c);
			case 2150 -> a3 * p(a + b, 2) * (a - b - c) * p(a + c, 2);
			case 2151 -> u(3) * a3 * T - a3 * S;
			case 2152 -> u(3) * a3 * T + a3 * S;
			case 2153 -> a * (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					+ u(3) * a3 * S;
			case 2154 -> -(a
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4))
					+ u(3) * a3 * S;
			case 2155 -> a3 * (a4 + b4 + 2 * b2 * c2 - 3 * c4 - 2 * a2 * (b2 - c2))
					* (a4 - 3 * b4 + 2 * b2 * c2 + c4 + 2 * a2 * (b2 - c2));
			case 2156 -> a * (a8 - p(b4 - c4, 2));
			case 2157 -> a * (a4 - a2 * b2 + b4 - c4) * (a4 - b4 - a2 * c2 + c4);
			case 2158 -> a * (a8 + 2 * a4 * b4 - 2 * a6 * R + p(b2 - c2, 3) * R
					- 2 * a2 * (b6 - c6))
					* (a8 + 2 * a4 * c4 - 2 * a6 * R - p(b2 - c2, 3) * R
					+ 2 * a2 * (b6 - c6));
			case 2159 -> a3 * (a4 - 2 * b4 + b2 * c2 + c4 + a2 * (b2 - 2 * c2))
					* (a4 + b4 + b2 * c2 - 2 * c4 + a2 * (-2 * b2 + c2));
			case 2160 -> a * (a2 + a * b + b2 - c2) * (a2 - b2 + a * c + c2);
			case 2161 -> a * (a2 - a * b + b2 - c2) * (a2 - b2 - a * c + c2);
			case 2162 -> a2 * (a * (b - c) - b * c) * (a * (b - c) + b * c);
			case 2163 -> a2 * (2 * a + 2 * b - c) * (2 * a - b + 2 * c);
			case 2164 -> a2 * (a3 + a2 * (b - c) - (b - c) * p(b + c, 2) - a * R)
					* (a3 + a2 * (-b + c) + (b - c) * p(b + c, 2) - a * R);
			case 2165 -> (a4 - 2 * a2 * b2 + Q) * (a4 - 2 * a2 * c2 + Q);
			case 2166 -> b * c * (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (-a2 + b2 - a * c - c2) * (-a2 + b2 + a * c - c2);
			case 2167 -> a * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 2168 -> a * (a4 - 2 * a2 * b2 + Q) * (a4 - 2 * a2 * c2 + Q)
					* (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 2169 -> a3 * T * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 2170 -> -(a * (a - b - c) * p(b - c, 2));
			case 2171 -> -(a * (a + b - c) * (a - b + c) * p(b + c, 2));
			case 2172 -> a3 * (a4 - b4 - c4);
			case 2173 -> a * (2 * a4 - Q - a2 * R);
			case 2174 -> a3 * (a2 - b2 - b * c - c2);
			case 2175 -> a4 * (a - b - c);
			case 2176 -> a2 * (-(b * c) + a * (b + c));
			case 2177 -> a2 * (a - 2 * (b + c));
			case 2178 -> a2 * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c) - a * R);
			case 2179 -> a3 * (-Q + a2 * R);
			case 2180 -> a3 * (a4 + b4 + c4 - 2 * a2 * R) * (-Q + a2 * R);
			case 2181 -> a * U * V * (-Q + a2 * R);
			case 2182 -> a * (2 * a4 - a2 * p(b - c, 2) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) - Q);
			case 2183 -> a2 * (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 2184 -> a * (a4 + b4 + 2 * b2 * c2 - 3 * c4 - 2 * a2 * (b2 - c2))
					* (a4 - 3 * b4 + 2 * b2 * c2 + c4 + 2 * a2 * (b2 - c2));
			case 2185 -> a * p(a + b, 2) * (a - b - c) * p(a + c, 2);
			case 2186 -> -(a * (c2 * (b2 - c2) + a2 * (2 * b2 + c2))
					* (-b4 + b2 * c2 + a2 * (b2 + 2 * c2)));
			case 2187 -> a3 * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					- a * p(b + c, 2));
			case 2188 -> a3 * (a - b - c) * T
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 2189 -> a2 * p(a + b, 2) * (a - b - c) * p(a + c, 2) * U * V;
			case 2190 -> a * U * V * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 2191 -> a * (a2 - 2 * a * b + p(b - c, 2))
					* (a2 + p(b - c, 2) - 2 * a * c);
			case 2192 -> a2 * (a - b - c)
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 2193 -> a3 * (a + b) * (a - b - c) * (a + c) * T;
			case 2194 -> a3 * (a + b) * (a - b - c) * (a + c);
			case 2195 -> a2 * (a - b - c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c));
			case 2196 -> a3 * (-b2 + a * c) * (a * b - c2) * T;
			case 2197 -> a2 * (a + b - c) * (a - b + c) * p(b + c, 2) * T;
			case 2198 -> a3 * (b + c) * (-b3 + a * b * c - c3 + a2 * (b + c));
			case 2199 -> a3 * (a + b - c) * (a - b + c) * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * p(b + c, 2));
			default -> Double.NaN;
		};
	}

	private double weight2200to2299(int k, double a, double b, double c) {
		return switch (k) {
			case 2200 -> a4 * (b + c) * T;
			case 2201 -> a * (a2 - b * c) * U * V;
			case 2202 -> a * (a - b - c) * U * V
					* (a4 - b * p(b - c, 2) * c - a2 * (b2 - b * c + c2));
			case 2203 -> a3 * (a + b) * (a + c) * U * V;
			case 2204 -> a3 * (a + b) * (a - b - c) * (a + c) * U * V;
			case 2205 -> a5 * (b + c);
			case 2206 -> a4 * (a + b) * (a + c);
			case 2207 -> a2 * p(a4 - Q, 2);
			case 2208 -> a3
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 2209 -> a3 * (-(b * c) + a * (b + c));
			case 2210 -> a5 - a3 * b * c;
			case 2211 -> a4 * U * V * (-b4 - c4 + a2 * R);
			case 2212 -> a3 * (a - b - c) * U * V;
			case 2213 -> a2 * (a + b - c) * (a - b + c)
					* (a3 + 3 * b3 + a2 * (b - c) + 3 * b2 * c + b * c2 + c3
					+ a * (3 * b2 + 2 * b * c - c2))
					* (a3 + b3 + b2 * c + 3 * b * c2 + 3 * c3 + a2 * (-b + c)
					+ a * (-b2 + 2 * b * c + 3 * c2));
			case 2214 -> a * (a2 + a * (b + c) + b * (b + c))
					* (a2 + a * (b + c) + c * (b + c));
			case 2215 -> a2 * (-b3 + b * c2 + 2 * a * c * (b + c) + a2 * (b + 2 * c))
					* (2 * a * b * (b + c) + a2 * (2 * b + c) + c * (b2 - c2));
			case 2216 -> a * (a6 - a4 * (2 * b2 + c2) + p(-(b2 * c) + c3, 2)
					+ a2 * (b4 - 2 * b2 * c2 - c4))
					* (a6 - a4 * (b2 + 2 * c2) + p(b3 - b * c2, 2)
					+ a2 * (-b4 - 2 * b2 * c2 + c4));
			case 2217 -> a * (a3 + b3 + a * (b - c) * c - b * c2)
					* (a3 - b2 * c + c3 + a * b * (-b + c));
			case 2218 -> a * (a3 - b2 * c + c3 - a * b * (b + c))
					* (a3 + b3 - b * c2 - a * c * (b + c));
			case 2219 -> a * (a5 + a * b * (b - c) * p(b + c, 2)
					- a2 * c * p(b + c, 2) + c * Q - a3 * (2 * b2 + b * c + c2))
					* (a5 - a2 * b * p(b + c, 2) - a * (b - c) * c * p(b + c, 2)
					+ b * Q - a3 * (b2 + b * c + 2 * c2));
			case 2220 -> a3 * (a2 - b * c + a * (b + c));
			case 2221 -> a2 * (a2 + 2 * a * b + b2 + c2) * (a2 + b2 + 2 * a * c + c2);
			case 2222 -> a * (a - b) * (a - c) * (a + b - c) * (a - b + c)
					* (a2 - a * b + b2 - c2) * (a2 - b2 - a * c + c2);
			case 2223 -> a3 * (-b2 - c2 + a * (b + c));
			case 2224 -> a * (a3 + b2 * (b - c) - a2 * c)
					* (a3 - a2 * b + c2 * (-b + c));
			case 2225 -> a3 * (-b3 - c3 + a * R);
			case 2226 -> a2 * p(a + b - 2 * c, 2) * p(a - 2 * b + c, 2);
			case 2227 -> a * (-(b2 * c2 * R) + a2 * (b4 + c4));
			case 2228 -> a * (-(b * c * R) + a * (b3 + c3));
			case 2229 -> -(a * b2 * c2 * (b + c)) + a3 * (b3 + c3);
			case 2230 -> -2 * a * b3 * c3 + a4 * (b3 + c3);
			case 2231 -> a * (-(b3 * c3 * (b + c)) + a4 * (b3 + c3));
			case 2232 -> a * (-(b3 * c3 * R) + a5 * (b3 + c3));
			case 2233 -> a * (b3 + c3) * (a6 - b3 * c3);
			case 2234 -> -2 * a * b2 * c2 + a3 * R;
			case 2235 -> a * (-(b2 * c2 * (b + c)) + a3 * R);
			case 2236 -> a * (a2 - b * c) * (a2 + b * c) * R;
			case 2237 -> a * (a5 * R - b2 * c2 * (b3 + c3));
			case 2238 -> a * (b + c) * (a2 - b * c);
			case 2239 -> a * (a3 * (b + c) - b * c * R);
			case 2240 -> a * (a4 * (b + c) - b * c * (b3 + c3));
			case 2241 -> a4 - 2 * a2 * b * c;
			case 2242 -> a4 + 2 * a2 * b * c;
			case 2243 -> a * (2 * a3 - b3 - c3);
			case 2244 -> a * (2 * a4 - b4 - c4);
			case 2245 -> a2 * (b + c) * (a2 - b2 + b * c - c2);
			case 2246 -> a * (2 * a3 - 2 * a2 * (b + c) - p(b - c, 2) * (b + c)
					+ a * R);
			case 2247 -> a * (2 * a6 - 2 * a4 * R - Q * R + a2 * (b4 + c4));
			case 2248 -> a2 * (a2 + b2 + b * c - c2 + a * (b + c))
					* (a2 - b2 + b * c + c2 + a * (b + c));
			case 2249 -> a2 * (a + b) * (a + c)
					* (a3 * b + c2 * (b2 - c2) + a2 * (-2 * b2 + c2)
					+ a * (b3 - b * c2))
					* (-b4 + a3 * c + b2 * c2 + a2 * (b2 - 2 * c2)
					+ a * (-(b2 * c) + c3));
			case 2250 -> a * (b + c) * (a3 - a2 * b + b3 - a * p(b - c, 2) - b * c2)
					* (a3 - a * p(b - c, 2) - a2 * c - b2 * c + c3);
			case 2251 -> a3 * (2 * a - b - c);
			case 2252 -> a2 * T
					* (a3 * (b + c) - a * p(b - c, 2) * (b + c) + Q - a2 * R);
			case 2253 -> a3 * T * (b5 - b3 * c2 - b2 * c3 + c5 - a * Q + a3 * R
					- a2 * (b3 + c3));
			case 2254 -> a * (b - c) * (-b2 - c2 + a * (b + c));
			case 2255 -> a2
					* (a3 + a2 * (3 * b - c) + p(b - c, 2) * (b + c)
					+ a * (3 * b2 + 2 * b * c - c2))
					* (a3 - a2 * (b - 3 * c) + p(b - c, 2) * (b + c)
					+ a * (-b2 + 2 * b * c + 3 * c2));
			case 2256 -> a2 * (a3 - a * p(b - c, 2) - a2 * (b + c) + p(b + c, 3));
			case 2257 -> a * (a4 - 4 * a2 * b * c - 2 * a3 * (b + c)
					+ 2 * a * p(b - c, 2) * (b + c) - Q);
			case 2258 -> a2 * (c * (b + c) + a * (2 * b + c))
					* (b * (b + c) + a * (b + 2 * c));
			case 2259 -> a2 * (a3 - a2 * b + b3 - b * c2 - a * p(b + c, 2))
					* (a3 - a2 * c - b2 * c + c3 - a * p(b + c, 2));
			case 2260 -> a2 * (2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 2261 -> a * (3 * a4 - 2 * a2 * p(b - c, 2) - 2 * a3 * (b + c)
					+ 2 * a * p(b - c, 2) * (b + c) - Q);
			case 2262 -> -(a * (a2 * p(b - c, 2) + a3 * (b + c)
					- a * p(b - c, 2) * (b + c) - Q));
			case 2263 -> a * (a + b - c) * (a - b + c) * (a3 - a2 * (b + c)
					- p(b - c, 2) * (b + c) + a * p(b + c, 2));
			case 2264 -> a * (a - b - c)
					* (2 * a3 + a2 * (b + c) + p(b - c, 2) * (b + c));
			case 2265 -> a * (2 * a4 - 2 * a3 * (b + c)
					+ 2 * a * p(b - c, 2) * (b + c) - Q
					- a2 * (b2 - 4 * b * c + c2));
			case 2266 -> a2 * (a5 - 2 * a4 * (b + c)
					- 2 * b * p(b - c, 2) * c * (b + c) - a * Q
					+ 2 * a2 * (b3 + 2 * b2 * c + 2 * b * c2 + c3));
			case 2267 -> a2 * (a3 - a * p(b - c, 2) - 2 * b * c * (b + c));
			case 2268 -> a2 * (a - b - c) * (a2 + 2 * b * c + a * (b + c));
			case 2269 -> a2 * (a - b - c) * (b2 + c2 + a * (b + c));
			case 2270 -> a * (a4 - 4 * a2 * b * c + 2 * a3 * (b + c)
					- 2 * a * p(b - c, 2) * (b + c) - Q);
			case 2271 -> a2 * (a2 - 2 * a * (b + c) - p(b + c, 2));
			case 2272 -> a2 * (a4 * (b + c) - p(b - c, 2) * p(b + c, 3)
					- 2 * a3 * (b2 - b * c + c2)
					+ 2 * a * p(b - c, 2) * (b2 + b * c + c2));
			case 2273 -> a2 * (a3 + b3 + b2 * c + b * c2 + c3);
			case 2274 -> a2 * (b * c * R + a2 * (b2 + b * c + c2)
					+ a * (b3 + b2 * c + b * c2 + c3));
			case 2275 -> a2 * (b2 - b * c + c2);
			case 2276 -> a2 * (b2 + b * c + c2);
			case 2277 -> a2 * (b3 + c3 + a * (b2 + b * c + c2));
			case 2278 -> a2 * (a3 - b * c * (b + c) - a * R);
			case 2279 -> a2 * ((b - c) * c + a * (2 * b + c))
					* (b * (-b + c) + a * (b + 2 * c));
			case 2280 -> a2 * (a2 - 2 * b * c - a * (b + c));
			case 2281 -> a3 * (b + c) * (a2 + 2 * a * b + b2 + c2)
					* (a2 + b2 + 2 * a * c + c2);
			case 2282 -> a * (-(b3 * c) + b * c3 + a3 * (b + c)
					- a * (b - c) * p(b + c, 2) + a2 * c * (b + 2 * c))
					* (a3 * (b + c) + a * (b - c) * p(b + c, 2)
					+ a2 * b * (2 * b + c) + b * c * (b2 - c2));
			case 2283 -> a2 * (a - b) * (a - c) * (a + b - c) * (a - b + c)
					* (-b2 - c2 + a * (b + c));
			case 2284 -> a2 * (a - b) * (a - c) * (-b2 - c2 + a * (b + c));
			case 2285 -> a * (a + b - c) * (a - b + c) * (a2 + p(b + c, 2));
			case 2286 -> a2 * (a + b - c) * (a - b + c) * T * (a2 + p(b + c, 2));
			case 2287 -> a * (a + b) * (a + c) * p(-a + b + c, 2);
			case 2288 -> a3 * (b5 - b4 * c - 4 * a * b2 * c2 - b * c4 + c5
					+ a4 * (b + c) - 2 * a2 * (b3 + c3));
			case 2289 -> a3 * (a - b - c) * (T * T);
			case 2290 -> a3 * (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2)
					* (-Q + a2 * R);
			case 2291 -> a2 * (a2 - 2 * b2 + a * (b - 2 * c) + b * c + c2)
					* (a2 + b2 + b * c - 2 * c2 + a * (-2 * b + c));
			case 2292 -> a * (b + c) * (b2 + c2 + a * (b + c));
			case 2293 -> a2 * (a - b - c) * (-p(b - c, 2) + a * (b + c));
			case 2294 -> -(a * (b + c)
					* (2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c)));
			case 2295 -> a * (b + c) * (a2 + b * c);
			case 2296 -> (b2 * c + a2 * (b + c) + a * b * (b + c))
					* (b * c2 + a2 * (b + c) + a * c * (b + c));
			case 2297 -> a * (a2 - 2 * a * (b - c) + p(b + c, 2))
					* (a2 + 2 * a * (b - c) + p(b + c, 2));
			case 2298 -> a * (a2 + a * c + b * (b + c)) * (a2 + a * b + c * (b + c));
			case 2299 -> a2 * (a + b) * (a - b - c) * (a + c) * U * V;
			default -> Double.NaN;
		};
	}

	private double weight2300to2399(int k, double a, double b, double c) {
		return switch (k) {
			case 2300 -> a3 * (b2 + c2 + a * (b + c));
			case 2301 -> a2 * (a5 - a3 * b * c - 2 * a4 * (b + c)
					+ b * p(b - c, 2) * c * (b + c)
					- a * p(b + c, 2) * (b2 - 3 * b * c + c2)
					+ a2 * (2 * b3 + b2 * c + b * c2 + 2 * c3));
			case 2302 -> a2 * (a6 - a5 * (b + c) - b * c * Q + a2 * p(b + c, 2) * R
					- a4 * (2 * b2 + b * c + 2 * c2) + 2 * a3 * (b3 + c3)
					- a * (b5 - b4 * c - b * c4 + c5));
			case 2303 -> a * (a + b) * (a + c) * (a2 + p(b + c, 2));
			case 2304 -> a3 * (a2 * b * c + a3 * (b + c) - b * c * p(b + c, 2)
					- a * (b3 + b2 * c + b * c2 + c3));
			case 2305 -> a2 * (a3 - b3 + a * b * c - c3 + 2 * a2 * (b + c));
			case 2306 -> a / (u(3) * (-a2 + p(b + c, 2)) - S);
			case 2307 -> u(3) * a2 * (a - b - c) * (a + b - c) * (a - b + c)
					* (a + b + c) + a2 * (a + b - c) * (a - b + c) * S;
			case 2308 -> a2 * (2 * a + b + c);
			case 2309 -> a2 * (b * c * (b + c) + a * R);
			case 2310 -> a * p(b - c, 2) * p(-a + b + c, 2);
			case 2311 -> a2 * (a + b) * (a - b - c) * (a + c) * (-b2 + a * c)
					* (a * b - c2);
			case 2312 -> a * (2 * a6 - a4 * R - Q * R);
			case 2313 -> a * (-Q + a2 * R) * (a8 + b2 * c2 * Q - 2 * a6 * R
					+ a4 * (b4 + b2 * c2 + c4));
			case 2314 -> a * T * (2 * a8 + p(b2 - c2, 4) - 3 * a6 * R - a2 * Q * R
					+ a4 * p(b2 + c2, 2));
			case 2315 -> a3 * T * (a4 * R + Q * R - 2 * a2 * (b4 - b2 * c2 + c4));
			case 2316 -> a2 * (a + b - 2 * c) * (a - b - c) * (a - 2 * b + c);
			case 2317 -> a2 * (2 * a3 - a2 * (b + c) + p(b - c, 2) * (b + c)
					- 2 * a * (b2 - b * c + c2));
			case 2318 -> a2 * (a - b - c) * (b + c) * T;
			case 2319 -> a * (a - b - c) * (a * (b - c) - b * c)
					* (a * (b - c) + b * c);
			case 2320 -> a * (a - b - c) * (2 * a + 2 * b - c) * (2 * a - b + 2 * c);
			case 2321 -> -((a - b - c) * (b + c));
			case 2322 -> (a + b) * (a + c) * p(-a + b + c, 2) * U * V;
			case 2323 -> a2 * (a - b - c) * (a2 - b2 + b * c - c2);
			case 2324 -> a * (a - b - c) * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					- a * p(b + c, 2));
			case 2325 -> (a - b - c) * (2 * a - b - c);
			case 2326 -> a * p(a + b, 2) * p(a + c, 2) * p(-a + b + c, 2) * U * V;
			case 2327 -> a2 * (a + b) * (a + c) * p(-a + b + c, 2) * T;
			case 2328 -> a2 * (a + b) * (a + c) * p(-a + b + c, 2);
			case 2329 -> a * (a - b - c) * (a2 + b * c);
			case 2330 -> a2 * (a - b - c) * (a2 + b * c);
			case 2331 -> a * U * V * (a3 + a2 * (b + c) - p(b - c, 2) * (b + c)
					- a * p(b + c, 2));
			case 2332 -> a2 * (a + b) * (a + c) * p(-a + b + c, 2) * U * V;
			case 2333 -> a2 * (b + c) * U * V;
			case 2334 -> a2 * (a + 3 * b + c) * (a + b + 3 * c);
			case 2335 -> a * (a - b - c)
					* (-b3 + b * c2 + 2 * a * c * (b + c) + a2 * (b + 2 * c))
					* (2 * a * b * (b + c) + a2 * (2 * b + c) + c * (b2 - c2));
			case 2336 -> a2
					* (a3 + 3 * b3 + a2 * (b - c) + 3 * b2 * c + b * c2 + c3
					+ a * (3 * b2 + 2 * b * c - c2))
					* (a3 + b3 + b2 * c + 3 * b * c2 + 3 * c3 + a2 * (-b + c)
					+ a * (-b2 + 2 * b * c + 3 * c2));
			case 2337 -> a2 * (a - b - c) * (a4 - 2 * a * b2 * c + Q - 2 * a2 * R)
					* (a4 - 2 * a * b * c2 + Q - 2 * a2 * R);
			case 2338 -> a2 * (a - b - c)
					* (a3 - 2 * b3 - a2 * c + b2 * c + c3 + a * (b2 - c2))
					* (a3 - a2 * b + b3 + b * c2 - 2 * c3 + a * (-b2 + c2));
			case 2339 -> a * (a - b - c) * (a2 + 2 * a * b + b2 + c2)
					* (a2 + b2 + 2 * a * c + c2);
			case 2340 -> a2 * (a - b - c) * (-b2 - c2 + a * (b + c));
			case 2341 -> a * (a + b) * (a - b - c) * (a + c) * (a2 - a * b + b2 - c2)
					* (a2 - b2 - a * c + c2);
			case 2342 -> a2 * (a - b - c)
					* (a3 - a2 * b + b3 - a * p(b - c, 2) - b * c2)
					* (a3 - a * p(b - c, 2) - a2 * c - b2 * c + c3);
			case 2343 -> a2 * (a - b - c)
					* (a4 - 2 * a3 * b + 2 * a2 * (b - c) * c
					- p(b - c, 3) * (b + c) + 2 * a * b * p(b + c, 2))
					* (a4 - 2 * a2 * b * (b - c) - 2 * a3 * c
					+ p(b - c, 3) * (b + c) + 2 * a * c * p(b + c, 2));
			case 2344 -> a * (a2 + a * b + b2) * (a - b - c) * (a2 + a * c + c2);
			case 2345 -> a2 + p(b + c, 2);
			case 2346 -> a * (a2 + b * (b - c) - a * (2 * b + c))
					* (a2 + c * (-b + c) - a * (b + 2 * c));
			case 2347 -> a2 * (a - b - c) * (p(b - c, 2) + a * (b + c));
			case 2348 -> a * (a - b - c) * (2 * a2 + p(b - c, 2) - a * (b + c));
			case 2349 -> a * (a4 - 2 * b4 + b2 * c2 + c4 + a2 * (b2 - 2 * c2))
					* (a4 + b4 + b2 * c2 - 2 * c4 + a2 * (-2 * b2 + c2));
			case 2350 -> a2 * ((b - c) * c + a * (b + c))
					* (b * (-b + c) + a * (b + c));
			case 2351 -> a2 * T * (a4 - 2 * a2 * b2 + Q) * (a4 - 2 * a2 * c2 + Q);
			case 2352 -> a3 * (-b3 + a * b * c - c3 + a2 * (b + c));
			case 2353 -> a2 * (a4 + b4 - c4) * (a4 - b4 + c4);
			case 2354 -> a2 * U * V * (b2 + c2 + a * (b + c));
			case 2355 -> a * (2 * a + b + c) * U * V;
			case 2356 -> a2 * U * V * (-b2 - c2 + a * (b + c));
			case 2357 -> a2 * (b + c)
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2));
			case 2358 -> -(a * (a + b - c) * (a - b + c) * (b + c) * U * V
					* (a3 + a2 * (b - c) - a * p(b - c, 2)
					- (b - c) * p(b + c, 2))
					* (a3 - a * p(b - c, 2) + a2 * (-b + c)
					+ (b - c) * p(b + c, 2)));
			case 2359 -> a2 * T * (a2 + a * c + b * (b + c))
					* (a2 + a * b + c * (b + c));
			case 2360 -> a2 * (a + b) * (a + c) * (a3 + a2 * (b + c)
					- p(b - c, 2) * (b + c) - a * p(b + c, 2));
			case 2361 -> a3 * (a - b - c) * (a2 - b2 + b * c - c2);
			case 2362 -> a * (a2 + 2 * a * b + b2 - c2 + S)
					* (a2 - b2 + 2 * a * c + c2 + S);
			case 2363 -> a * (a + b) * (a + c) * (a2 + a * c + b * (b + c))
					* (a2 + a * b + c * (b + c));
			case 2364 -> a2 * (a - b - c) * (2 * a + 2 * b - c) * (2 * a - b + 2 * c);
			case 2365 -> a2
					* (a6 - 2 * b6 + a5 * (b - 2 * c) + b5 * c
					+ a4 * (b - c) * c + b4 * c2 - 2 * b3 * c3 + b * c5
					+ c6 + a2 * (b - c) * p(b + c, 3)
					- 2 * a3 * (b3 + b * c2 - 2 * c3)
					+ a * (b5 - 2 * b4 * c + 2 * b3 * c2 + b * c4
					- 2 * c5))
					* (a6 + b6 + b5 * c - 2 * b3 * c3 + b2 * c4 + b * c5
					- 2 * c6 + a5 * (-2 * b + c) + a4 * b * (-b + c)
					- a2 * (b - c) * p(b + c, 3)
					+ a3 * (4 * b3 - 2 * b2 * c - 2 * c3) + a * (-2 * b5
					+ b4 * c + 2 * b2 * c3 - 2 * b * c4 + c5));
			case 2366 -> (a8 + a6 * (-2 * b2 + c2) + b2 * (b2 - c2) * p(b2 + c2, 2)
					+ a4 * (2 * b4 - b2 * c2 - c4)
					- a2 * (2 * b6 + b4 * c2 - 4 * b2 * c4 + c6))
					* (a8 + a6 * (b2 - 2 * c2) + c2 * (-b2 + c2) * p(b2 + c2, 2)
					- a4 * (b4 + b2 * c2 - 2 * c4)
					- a2 * (b6 - 4 * b4 * c2 + b2 * c4 + 2 * c6));
			case 2367 -> -(b2 * c2 * (a6 + b6 - a4 * c2 - b4 * c2)
					* (-a6 + a4 * b2 + b2 * c4 - c6));
			case 2368 -> (a + b) * (a + c)
					* (a * c2 * (-b + c) + b * c2 * (-b + c) + a3 * (b + c)
					- a2 * b * (b + c))
					* (a * b2 * (b - c) + b2 * (b - c) * c + a3 * (b + c)
					- a2 * c * (b + c));
			case 2369 -> (a + b - c) * (a - b + c)
					* (a4 + a * (b - c) * c2 + p(b - c, 2) * c2
					+ a2 * b * (b + c) - a3 * (2 * b + c))
					* (a4 + b2 * p(b - c, 2) + a * b2 * (-b + c)
					+ a2 * c * (b + c) - a3 * (b + 2 * c));
			case 2370 -> (a5 + a4 * (-2 * b + c) + b2 * (b - c) * p(b + c, 2)
					+ a3 * (b2 - c2) - 2 * a * (b4 - b2 * c2)
					+ a2 * (b3 - 2 * b2 * c + 2 * b * c2 - c3))
					* (a5 + a4 * (b - 2 * c) - (b - c) * c2 * p(b + c, 2)
					+ 2 * a * c2 * (b2 - c2) + a3 * (-b2 + c2)
					+ a2 * (-b3 + 2 * b2 * c - 2 * b * c2 + c3));
			case 2371 -> a2
					* (a4 - 2 * b4 + a3 * (3 * b - 4 * c) + b3 * c - 3 * b2 * c2
					+ 3 * b * c3 + c4 - 3 * a2 * (b2 + b * c - 2 * c2)
					+ a * (b3 + 6 * b2 * c - 3 * b * c2 - 4 * c3))
					* (a4 + b4 + 3 * b3 * c - 3 * b2 * c2 + b * c3 - 2 * c4
					+ a3 * (-4 * b + 3 * c)
					+ a2 * (6 * b2 - 3 * b * c - 3 * c2)
					+ a * (-4 * b3 - 3 * b2 * c + 6 * b * c2 + c3));
			case 2372 -> (a5 + a4 * c - a3 * c2 - a2 * c3
					+ b2 * (b - c) * p(b + c, 2))
					* (a5 + a4 * b - a3 * b2 - a2 * b3
					- (b - c) * c2 * p(b + c, 2));
			case 2373 -> (a6 - a4 * b2 + b6 - b2 * c4 - a2 * Q)
					* (a6 - a4 * c2 - b4 * c2 + c6 - a2 * Q);
			case 2374 -> U * V * (a4 + a2 * (-4 * b2 + c2) + b2 * R)
					* (a4 + a2 * (b2 - 4 * c2) + c2 * R);
			case 2375 -> a2
					* (a3 * b + a2 * b * c - b * c3
					+ a * (b3 + b2 * c - 2 * b * c2 - c3))
					* (a3 * c + a2 * b * c - b3 * c
					+ a * (-b3 - 2 * b2 * c + b * c2 + c3));
			case 2376 -> a * (a + b - c) * (a - b + c) * U * V
					* (a5 - p(b - c, 3) * c * (b + c) - a4 * (2 * b + c)
					+ 2 * a2 * (b3 + 2 * b * c2)
					- a * (b4 + 2 * b3 * c + c4))
					* (a5 + b * p(b - c, 3) * (b + c) - a4 * (b + 2 * c)
					+ 2 * a2 * (2 * b2 * c + c3)
					- a * (b4 + 2 * b * c3 + c4));
			case 2377 -> a * p(a + b - c, 2)
					* p(a - b + c,
					2)
					* (a4 + b * p(b - c, 3) - a3 * (2 * b + 3 * c)
					+ a2 * (2 * b2 + 3 * b * c + 3 * c2)
					- a * (2 * b3 - 3 * b2 * c + 4 * b * c2 + c3))
					* (a4 - p(b - c, 3) * c - a3 * (3 * b + 2 * c)
					+ a2 * (3 * b2 + 3 * b * c + 2 * c2)
					- a * (b3 + 4 * b2 * c - 3 * b * c2 + 2 * c3));
			case 2378 -> a2
					* (a8 - 5 * a4 * b4 + 6 * a2 * b6 - 2 * b8
					+ 7 * a4 * b2 * c2 - 5 * a2 * b4 * c2 - 6 * b6 * c2
					- 5 * a4 * c4 - 5 * a2 * b2 * c4
					+ 16 * b4
					* c4
					+ 6 * a2 * c6 - 6 * b2 * c6 - 2 * c8)
					+ u(3) * a2
					* (a6 - a4 * b2 + 2 * a2 * b4 - 2 * b6 - a4 * c2
					- 3 * a2 * b2 * c2 + 2 * b4 * c2
					+ 2 * a2 * c4 + 2 * b2 * c4 - 2 * c6)
					* S;
			case 2379 -> -(a2 * (a8 - 5 * a4 * b4 + 6 * a2 * b6 - 2 * b8
					+ 7 * a4 * b2 * c2 - 5 * a2 * b4 * c2 - 6 * b6 * c2
					- 5 * a4 * c4 - 5 * a2 * b2 * c4 + 16 * b4 * c4
					+ 6 * a2 * c6 - 6 * b2 * c6 - 2 * c8))
					+ u(3) * a2
					* (a6 - a4 * b2 + 2 * a2 * b4 - 2 * b6 - a4 * c2
					- 3 * a2 * b2 * c2 + 2 * b4 * c2
					+ 2 * a2 * c4 + 2 * b2 * c4 - 2 * c6)
					* S;
			case 2380 -> -(a2 * (a8 - 4 * a6 * b2 + 3 * a4 * b4 + 2 * a2 * b6 - 2 * b8
					- 4 * a6 * c2 + 7 * a4 * b2 * c2 - 5 * a2 * b4 * c2
					+ 14 * b6 * c2 + 3 * a4 * c4 - 5 * a2 * b2 * c4
					- 24 * b4 * c4 + 2 * a2 * c6 + 14 * b2 * c6 - 2 * c8))
					+ u(3) * a2
					* (a6 - a4 * b2 + 2 * a2 * b4 - 2 * b6 - a4 * c2
					- 3 * a2 * b2 * c2 + 2 * b4 * c2
					+ 2 * a2 * c4 + 2 * b2 * c4 - 2 * c6)
					* S;
			case 2381 -> a2
					* (a8 - 4 * a6 * b2 + 3 * a4 * b4 + 2 * a2 * b6 - 2 * b8
					- 4 * a6 * c2 + 7 * a4 * b2 * c2 - 5 * a2 * b4 * c2
					+ 14 * b6 * c2 + 3 * a4 * c4 - 5 * a2 * b2 * c4
					- 24 * b4
					* c4
					+ 2 * a2 * c6 + 14 * b2 * c6 - 2 * c8)
					+ u(3) * a2
					* (a6 - a4 * b2 + 2 * a2 * b4 - 2 * b6 - a4 * c2
					- 3 * a2 * b2 * c2 + 2 * b4 * c2
					+ 2 * a2 * c4 + 2 * b2 * c4 - 2 * c6)
					* S;
			case 2382 -> a2 * (a2 * (2 * b - c) - b * (b - 2 * c) * c - a * R)
					* (a2 * (b - 2 * c) + b * c * (-2 * b + c) + a * R);
			case 2383 -> a2 * U * V
					* (a8 - 2 * a6 * (2 * b2 + c2) + Q * (b4 + 2 * c4)
					+ a4 * (6 * b4 + 2 * b2 * c2 + 3 * c4)
					+ a2 * (-4 * b6 + 2 * b4 * c2 - 4 * c6))
					* (a8 - 2 * a6 * (b2 + 2 * c2) + Q * (2 * b4 + c4)
					+ a4 * (3 * b4 + 2 * b2 * c2 + 6 * c4)
					+ a2 * (-4 * b6 + 2 * b2 * c4 - 4 * c6));
			case 2384 -> a2 * (a2 - 4 * a * b + b2 + 2 * a * c + 2 * b * c - 2 * c2)
					* (a2 - 2 * b2 + 2 * a * (b - 2 * c) + 2 * b * c + c2);
			case 2385 -> 2 * a6 - a5 * (b + c) - a4 * p(b - c, 2)
					+ 2 * a3 * p(b - c, 2) * (b + c)
					- a * p(b - c, 2) * p(b + c, 3) - p(b - c, 4) * p(b + c, 2);
			case 2386 -> a2 * (a6 * R - a2 * Q * R - Q * (b4 + c4)
					+ a4 * (b4 - 4 * b2 * c2 + c4));
			case 2387 -> a4 * (-b6 - c6 + a2 * (b4 + c4));
			case 2388 -> a2 * (b + c) * (a2 * R + a * (-b3 + b2 * c + b * c2 - c3)
					- b3 * c - b * c3);
			case 2389 -> a2 * (-a + b + c)
					* (a2 * R + a * (-2 * b3 + b2 * c + b * c2 - 2 * c3) + b4
					- b3 * c - b * c3 + c4);
			case 2390 -> a2 * (-(a * Q) + a3 * R - p(b - c, 2) * (b3 + c3)
					+ a2 * (b3 - 2 * b2 * c - 2 * b * c2 + c3));
			case 2391 -> 2 * a4 + 3 * a2 * p(b - c, 2) - p(b - c, 4) - a3 * (b + c)
					- 3 * a * p(b - c, 2) * (b + c);
			case 2392 -> a2 * (-b5 - c5 + a3 * R + a2 * (b3 + c3) - a * (b4 + c4));
			case 2393 -> a2 * (-2 * a2 * b2 * c2 + a4 * R - Q * R);
			case 2394 -> (b2 - c2)
					* (-a4 - a2 * b2 + 2 * b4 + 2 * a2 * c2 - b2 * c2 - c4)
					* (a4 + b4 + b2 * c2 - 2 * c4 + a2 * (-2 * b2 + c2));
			case 2395 -> -((b2 - c2) * (a4 + b4 - a2 * c2 - b2 * c2)
					* (-a4 + a2 * b2 + b2 * c2 - c4));
			case 2396 -> (a - b) * (a + b) * (a - c) * (a + c) * (-b4 - c4 + a2 * R);
			case 2397 -> (a - b) * (a - c)
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 2398 -> (a - b) * (a - c)
					* (2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c));
			case 2399 -> (a - b - c) * (b - c)
					* (a4 - a3 * b - 2 * b4 + a * b * p(b - c, 2) + b3 * c
					+ b2 * c2 - b * c3 + c4
					+ a2 * (b2 + b * c - 2 * c2))
					* (a4 + b4 - a3 * c - b3 * c + a * p(b - c, 2) * c + b2 * c2
					+ b * c3 - 2 * c4 + a2 * (-2 * b2 + b * c + c2));
			default -> Double.NaN;
		};
	}

	private double weight2400to2499(int k, double a, double b, double c) {
		return switch (k) {
			case 2400 -> -((b - c)
					* (-a3 - a * b2 + 2 * b3 + a2 * c - b2 * c + a * c2 - c3)
					* (a3 - a2 * b + b3 + b * c2 - 2 * c3 + a * (-b2 + c2)));
			case 2401 -> -((b - c) * (a3 - a2 * b + b3 - a * p(b - c, 2) - b * c2)
					* (-a3 + a * p(b - c, 2) + a2 * c + c * (b2 - c2)));
			case 2402 -> -((b - c) * (a2 + b * (b - c) - a * c)
					* (-a2 + a * b + (b - c) * c)
					* (a2 + b2 + c2 - 2 * a * (b + c)));
			case 2403 -> (a + b - 2 * c) * (3 * a - b - c) * (b - c)
					* (a - 2 * b + c);
			case 2404 -> (a - b) * (a + b)
					* (a - c) * (a
					+ c)
					* p(a4 - Q, 2)
					* (a6 * R + 3 * a2 * Q * R
					+ a4 * (-3 * b4 + 4 * b2 * c2 - 3 * c4)
					- Q * (b4 + 4 * b2 * c2 + c4));
			case 2405 -> (a - b) * (a - c) * (a + b - c) * (a - b + c) * U * V
					* (a5 * (b + c) - 2 * a3 * p(b - c, 2) * (b + c)
					+ a * p(b - c, 4) * (b + c) + 2 * a2 * Q - a4 * R
					- Q * R);
			case 2406 -> (a - b) * (a - c) * (a + b - c) * (a - b + c)
					* (2 * a4 - a2 * p(b - c, 2) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) - Q);
			case 2407 -> (a - b) * (a + b) * (a - c) * (a + c)
					* (2 * a4 - Q - a2 * R);
			case 2408 -> (b2 - c2) * (a2 + b2 - 2 * c2) * (-a2 + 2 * b2 - c2)
					* (-5 * a2 + b2 + c2);
			case 2409 -> (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (2 * a6 - a4 * R - Q * R);
			case 2410 -> (a - b) * (a + b) * (a - c) * (a + c)
					* (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (a2 - b2 - a * c
					+ c2)
					* (a2 - b2 + a * c + c2)
					* (a6 * R + a4 * (-3 * b4 + 2 * b2 * c2 - 3 * c4)
					- Q * (b4 + 3 * b2 * c2 + c4) + a2 * (3 * b6
					- 2 * b4 * c2 - 2 * b2 * c4 + 3 * c6));
			case 2411 -> -((b2 - c2) * (-a2 + b2 - b * c + c2)
					* (-a2 + b2 + b * c + c2)
					* (a8 + a6 * (b2 - 3 * c2) + b2 * p(b2 - c2, 3)
					+ a4 * (-4 * b4 + 2 * b2 * c2 + 3 * c4)
					+ a2 * (b6 + 2 * b4 * c2 - 2 * b2 * c4 - c6))
					* (-a8 + c2 * p(b2 - c2, 3) + a6 * (3 * b2 - c2)
					+ a4 * (-3 * b4 - 2 * b2 * c2 + 4 * c4)
					+ a2 * (b6 + 2 * b4 * c2 - 2 * b2 * c4 - c6)));
			case 2412 -> -((b - c) * (a3 + b2 * (b - c) - a2 * c)
					* (-a3 + a2 * b + (b - c) * c2)
					* (b4 + b3 * c - 2 * b2 * c2 + b * c3 + c4 + a3 * (b + c)
					- a2 * (b2 + b * c + c2)
					- a * (b3 + b2 * c + b * c2 + c3)));
			case 2413 -> -((b2 - c2) * (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (-a2 + b2 - a * c - c2) * (-a2 + b2 + a * c - c2)
					* (a4 + b4 - b2 * c2 + c4 - 2 * a2 * R)
					* (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (-a4 + c2 * (b2 - c2) + a2 * (b2 + 2 * c2)));
			case 2414 -> (a - b) * (a2 - 2 * a * b + p(b - c, 2)) * (a - c)
					* (a2 + p(b - c, 2) - 2 * a * c) * (-b2 - c2 + a * (b + c));
			case 2415 -> (a - b) * (a + b - 3 * c) * (a - c) * (2 * a - b - c)
					* (a - 3 * b + c);
			case 2416 -> -((b2 - c2) * (T * T)
					* (-a8 + a6 * (3 * b2 - 2 * c2) + c2 * p(b2 - c2, 3)
					- 3 * a4 * (b4 + b2 * c2 - 2 * c4)
					+ a2 * (b6 + 4 * b4 * c2 - 3 * b2 * c4 - 2 * c6))
					* (a8 + a6 * (2 * b2 - 3 * c2) + b2 * p(b2 - c2, 3)
					+ 3 * a4 * (-2 * b4 + b2 * c2 + c4)
					+ a2 * (2 * b6 + 3 * b4 * c2 - 4 * b2 * c4 - c6)));
			case 2417 -> (a - b - c) * (b - c) * T
					* (a6 - a5 * b - p(b - c, 3) * c * p(b + c, 2)
					- a4 * (2 * b2 - 3 * b * c + c2)
					+ 2 * a3 * (b3 - b * c2)
					+ a2 * (b4 - 2 * b3 * c + 4 * b2 * c2 - 2 * b * c3
					- c4)
					- a * (b5 + 2 * b3 * c2 - 3 * b * c4))
					* (a6 - a5 * c + b * p(b - c, 3) * p(b + c, 2)
					- a4 * (b2 - 3 * b * c + 2 * c2)
					+ a3 * (-2 * b2 * c + 2 * c3)
					+ a2 * (-b4 - 2 * b3 * c + 4 * b2 * c2 - 2 * b * c3
					+ c4)
					+ a * (3 * b4 * c - 2 * b2 * c3 - c5));
			case 2418 -> (a - b) * (a + b) * (a - c) * (a + c) * (a2 + b2 - 5 * c2)
					* (2 * a2 - b2 - c2) * (a2 - 5 * b2 + c2);
			case 2419 -> (b2 - c2) * (-T)
					* (-a6 - a2 * b4 + 2 * b6 + a4 * c2 - b4 * c2 + a2 * c4
					- c6)
					* (a6 - a4 * b2 + b6 + b2 * c4 - 2 * c6 + a2 * (-b4 + c4));
			case 2420 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (2 * a4 - Q - a2 * R);
			case 2421 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (-b4 - c4 + a2 * R);
			case 2422 -> a2 * (b2 - c2) * (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4);
			case 2423 -> a2 * (b - c) * (a3 - a2 * b + b3 - a * p(b - c, 2) - b * c2)
					* (a3 - a * p(b - c, 2) - a2 * c - b2 * c + c3);
			case 2424 -> a2 * (b - c)
					* (a3 - 2 * b3 - a2 * c + b2 * c + c3 + a * (b2 - c2))
					* (a3 - a2 * b + b3 + b * c2 - 2 * c3 + a * (-b2 + c2));
			case 2425 -> a2 * (a - b) * (a - c) * (a + b - c) * (a - b + c)
					* (2 * a4 - a2 * p(b - c, 2) - a3 * (b + c)
					+ a * p(b - c, 2) * (b + c) - Q);
			case 2426 -> a2 * (a - b) * (a - c)
					* (2 * a3 - a2 * (b + c) - p(b - c, 2) * (b + c));
			case 2427 -> a2 * (a - b) * (a - c)
					* (-2 * a * b * c + a2 * (b + c) - p(b - c, 2) * (b + c));
			case 2428 -> a2 * (a - b) * (a2 - 2 * a * b + p(b - c, 2)) * (a - c)
					* (a2 + p(b - c, 2) - 2 * a * c) * (-b2 - c2 + a * (b + c));
			case 2429 -> a2 * (a - b) * (a + b - 3 * c) * (a - c) * (2 * a - b - c)
					* (a - 3 * b + c);
			case 2430 -> a2 * (b2 - c2) * (T * T)
					* (a8 + c2 * p(-b2 + c2, 3) + a6 * (-3 * b2 + 2 * c2)
					+ 3 * a4 * (b4 + b2 * c2 - 2 * c4)
					- a2 * (b6 + 4 * b4 * c2 - 3 * b2 * c4 - 2 * c6))
					* (a8 + a6 * (2 * b2 - 3 * c2) + b2 * p(b2 - c2, 3)
					+ 3 * a4 * (-2 * b4 + b2 * c2 + c4)
					+ a2 * (2 * b6 + 3 * b4 * c2 - 4 * b2 * c4 - c6));
			case 2431 -> a2 * (a - b - c) * (b - c) * T
					* (a6 - a5 * b - p(b - c, 3) * c * p(b + c, 2)
					- a4 * (2 * b2 - 3 * b * c + c2)
					+ 2 * a3 * (b3 - b * c2)
					+ a2 * (b4 - 2 * b3 * c + 4 * b2 * c2 - 2 * b * c3
					- c4)
					- a * (b5 + 2 * b3 * c2 - 3 * b * c4))
					* (a6 - a5 * c + b * p(b - c, 3) * p(b + c, 2)
					- a4 * (b2 - 3 * b * c + 2 * c2)
					+ a3 * (-2 * b2 * c + 2 * c3)
					+ a2 * (-b4 - 2 * b3 * c + 4 * b2 * c2 - 2 * b * c3
					+ c4)
					+ a * (3 * b4 * c - 2 * b2 * c3 - c5));
			case 2432 -> a2 * (a - b - c) * (b - c)
					* (a4 - a3 * b - 2 * b4 + a * b * p(b - c, 2) + b3 * c
					+ b2 * c2 - b * c3 + c4
					+ a2 * (b2 + b * c - 2 * c2))
					* (a4 + b4 - a3 * c - b3 * c + a * p(b - c, 2) * c + b2 * c2
					+ b * c3 - 2 * c4 + a2 * (-2 * b2 + b * c + c2));
			case 2433 -> a2 * (b2 - c2)
					* (a4 - 2 * b4 + b2 * c2 + c4 + a2 * (b2 - 2 * c2))
					* (a4 + b4 + b2 * c2 - 2 * c4 + a2 * (-2 * b2 + c2));
			case 2434 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a2 + b2 - 5 * c2) * (2 * a2 - b2 - c2)
					* (a2 - 5 * b2 + c2);
			case 2435 -> -(a2 * (b2 - c2) * T
					* (a6 - 2 * b6 - a4 * c2 + b4 * c2 + c6 + a2 * (b4 - c4))
					* (a6 - a4 * b2 + b6 + b2 * c4 - 2 * c6 + a2 * (-b4 + c4)));
			case 2436 -> a2 * (b2 - c2) * (a2 - b2 - b * c - c2)
					* (a2 - b2 + b * c - c2)
					* (a8 + a6 * (b2 - 3 * c2) + b2 * p(b2 - c2, 3)
					+ a4 * (-4 * b4 + 2 * b2 * c2 + 3 * c4)
					+ a2 * (b6 + 2 * b4 * c2 - 2 * b2 * c4 - c6))
					* (a8 + a6 * (-3 * b2 + c2) + c2 * p(-b2 + c2, 3)
					+ a4 * (3 * b4 + 2 * b2 * c2 - 4 * c4)
					+ a2 * (-b6 - 2 * b4 * c2 + 2 * b2 * c4 + c6));
			case 2437 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a2 - a * b + b2 - c2) * (a2 + a * b + b2 - c2)
					* (a2 - b2 - a * c
					+ c2)
					* (a2 - b2 + a * c + c2)
					* (a6 * R + a4 * (-3 * b4 + 2 * b2 * c2 - 3 * c4)
					- Q * (b4 + 3 * b2 * c2 + c4) + a2 * (3 * b6
					- 2 * b4 * c2 - 2 * b2 * c4 + 3 * c6));
			case 2438 -> a2 * (a - b) * (a - c) * (-b3 - c3 + a * R)
					* (a4 + a3 * (b - c) + a * p(b - c, 2) * (b + c)
					+ b * p(b - c, 2) * (b + c)
					- a2 * (2 * b2 + b * c + c2))
					* (a4 + a3 * (-b + c) + a * p(b - c, 2) * (b + c)
					+ p(b - c, 2) * c * (b + c)
					- a2 * (b2 + b * c + 2 * c2));
			case 2439 -> a2 * (a - b) * (a + b) * (a - c) * (a + c)
					* (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2)
					* (-Q + a2 * R) * (a4 + Q - a2 * (2 * b2 + c2))
					* (a4 + Q - a2 * (b2 + 2 * c2));
			case 2440 -> a2 * (b - c) * (a2 + b * (b - c) - a * c)
					* (a2 - a * b + c * (-b + c))
					* (a2 + b2 + c2 - 2 * a * (b + c));
			case 2441 -> a2 * (a + b - 2 * c) * (3 * a - b - c) * (b - c)
					* (a - 2 * b + c);
			case 2442 -> a2 * (a - b) * (a + b)
					* (a - c) * (a
					+ c)
					* p(a4 - Q, 2)
					* (a6 * R + 3 * a2 * Q * R
					+ a4 * (-3 * b4 + 4 * b2 * c2 - 3 * c4)
					- Q * (b4 + 4 * b2 * c2 + c4));
			case 2443 -> a2
					* (a - b) * (a
					- c)
					* (a + b - c) * (a - b + c) * U * V
					* (a5 * (b + c) - 2 * a3 * p(b - c, 2) * (b + c)
					+ a * p(b - c, 4) * (b + c) + 2 * a2 * Q - a4 * R
					- Q * R);
			case 2444 -> a2 * (b2 - c2) * (a2 + b2 - 2 * c2) * (5 * a2 - b2 - c2)
					* (a2 - 2 * b2 + c2);
			case 2445 -> a2 * (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (2 * a6 - a4 * R - Q * R);
			case 2446 -> -(a * (a2 * b - b3 + a2 * c - 2 * a * b * c + b2 * c + b * c2
					- c3)) + 2
					* a
					* u(a * b * c
					* (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 3 * a * b * c - b2 * c - a * c2
					- b * c2 + c3));
			case 2447 -> a * (a2 * b - b3 + a2 * c - 2 * a * b * c + b2 * c + b * c2
					- c3) + 2
					* a
					* u(a * b * c
					* (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 3 * a * b * c - b2 * c - a * c2
					- b * c2 + c3));
			case 2448 -> -2 * a2 * b * c
					* (a2 * b - b3 + a2 * c - 2 * a * b * c + b2 * c + b * c2
					- c3)
					+ a * (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)
					* u(a * b * c
					* (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 3 * a * b * c - b2 * c - a * c2
					- b * c2 + c3));
			case 2449 -> 2 * a2 * b * c
					* (a2 * b - b3 + a2 * c - 2 * a * b * c + b2 * c + b * c2
					- c3)
					+ a * (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)
					* u(a * b * c
					* (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 3 * a * b * c - b2 * c - a * c2
					- b * c2 + c3));
			case 2450 -> -((a4 + Q) * (-b4 - c4 + a2 * R));
			case 2451 -> a2 * (b2 - c2) * (a4 + 2 * b2 * c2 - a2 * R);
			case 2452 -> a8 - b2 * c2 * Q - a6 * R + 2 * a2 * Q * R
					+ a4 * (-2 * b4 + 5 * b2 * c2 - 2 * c4);
			case 2453 -> a8 + 2 * b2 * c2 * Q - a6 * R - a2 * Q * R
					+ a4 * (b4 - b2 * c2 + c4);
			case 2454 -> -2 * a4 + a2 * b2 + b4 + a2 * c2 - 2 * b2 * c2 + c4
					+ 2 * u(a8 - a6 * b2 - a2 * b6 + b8 - a6 * c2 + a4 * b2 * c2
					+ a2 * b4 * c2 - b6 * c2 + a2 * b2 * c4 - a2 * c6
					- b2 * c6 + c8);
			case 2455 -> 2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4
					+ 2 * u(a8 - a6 * b2 - a2 * b6 + b8 - a6 * c2 + a4 * b2 * c2
					+ a2 * b4 * c2 - b6 * c2 + a2 * b2 * c4 - a2 * c6
					- b2 * c6 + c8);
			case 2456 -> a2 * (a8 - 2 * a6 * b2 + 3 * a4 * b4 - 2 * a2 * b6
					- 2 * a6 * c2 + a4 * b2 * c2 + 2 * a2 * b4 * c2
					- 3 * b6 * c2 + 3 * a4 * c4 + 2 * a2 * b2 * c4 + 2 * b4 * c4
					- 2 * a2 * c6 - 3 * b2 * c6);
			case 2457 -> -((b2 - c2) * (-2 * a3 - a2 * (b + c) + p(b - c, 2) * (b + c)
					+ 2 * a * (b2 - b * c + c2)));
			case 2458 -> a2 * (a8 + 2 * a4 * b4 - a2 * b6 + a4 * b2 * c2 - 2 * b6 * c2
					+ 2 * a4 * c4 - a2 * c6 - 2 * b2 * c6);
			case 2459 -> 2 * a2 * (a2 * b2 - b4 + a2 * c2 - c4)
					+ 2 * a2 * (2 * a2 - b2 - c2) * S;
			case 2460 -> 2 * a2 * (a2 * b2 - b4 + a2 * c2 - c4)
					- 2 * a2 * (2 * a2 - b2 - c2) * S;
			case 2461 -> a2
					* (a8 - 2 * a6 * b2 + 3 * a4 * b4 - 2 * a2 * b6
					- 2 * a6 * c2 + a4 * b2 * c2 + 2 * a2 * b4 * c2
					- 3 * b6 * c2 + 3 * a4 * c4 + 2 * a2 * b2 * c4
					+ 2 * b4 * c4 - 2 * a2 * c6 - 3 * b2 * c6)
					+ a2 * (a2 - b * c) * (a2 + b * c) * (a2 + b2 + c2) * S;
			case 2462 -> a2
					* (a8 - 2 * a6 * b2 + 3 * a4 * b4 - 2 * a2 * b6
					- 2 * a6 * c2 + a4 * b2 * c2 + 2 * a2 * b4 * c2
					- 3 * b6 * c2 + 3 * a4 * c4 + 2 * a2 * b2 * c4
					+ 2 * b4 * c4 - 2 * a2 * c6 - 3 * b2 * c6)
					- a2 * (a2 - b * c) * (a2 + b * c) * (a2 + b2 + c2) * S;
			case 2463 -> a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4
					+ 2 * a * u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4 - b2 * c4
					+ c6);
			case 2464 -> -a4 - a2 * b2 + 2 * b4 - a2 * c2 - 4 * b2 * c2 + 2 * c4
					+ 2 * a * u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4 - b2 * c4
					+ c6);
			case 2465 -> a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					+ a2 * S * u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4 - b2 * c4
					+ c6);
			case 2466 -> a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					- a2 * S * u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4 - b2 * c4
					+ c6);
			case 2467 -> a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4
					+ 2 * (b + c)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2468 -> -a4 - a2 * b2 + 2 * b4 - a2 * c2 - 4 * b2 * c2 + 2 * c4
					+ 2 * (b + c)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2469 -> -2 * a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4)
					+ 2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2470 -> -2 * a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4)
					- 2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2471 -> -2 * a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					* u(a2 * b2 + a2 * c2 + b2 * c2)
					+ 2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2472 -> -2 * a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					* u(a2 * b2 + a2 * c2 + b2 * c2)
					- 2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2473 -> a * (b - c) * (a4 + 2 * a2 * b * c - a3 * (b + c)
					- a * p(b + c, 3) + p(b - c, 2) * R);
			case 2474 -> -(a2 * (a2 + p(b - c, 2)) * (b2 - c2) * R
					* (a2 + p(b + c, 2)));
			case 2475 -> -a4 - a2 * b * c - a * b * c * (b + c) + Q;
			case 2476 -> -(a * b * c * (b + c)) + Q - a2 * (b2 + b * c + c2);
			case 2477 -> a4 * (a + b - c) * (a - b + c) * p(-a2 + b2 + b * c + c2, 2);
			case 2478 -> -a4 + 2 * a2 * b * c + 2 * a * b * c * (b + c) + Q;
			case 2479 -> -2 * a4 + a2 * b2 + b4 + a2 * c2 - 2 * b2 * c2 + c4
					+ u(a8 - a6 * b2 - a2 * b6 + b8 - a6 * c2 + a4 * b2 * c2
					+ a2 * b4 * c2 - b6 * c2 + a2 * b2 * c4 - a2 * c6
					- b2 * c6 + c8);
			case 2480 -> 2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4
					+ u(a8 - a6 * b2 - a2 * b6 + b8 - a6 * c2 + a4 * b2 * c2
					+ a2 * b4 * c2 - b6 * c2 + a2 * b2 * c4 - a2 * c6
					- b2 * c6 + c8);
			case 2481 -> b * c * (a2 + b * (b - c) - a * c)
					* (-a2 + a * b + (b - c) * c);
			case 2482 -> p(-2 * a2 + b2 + c2, 2);
			case 2483 -> a2 * (b - c) * (a2 + b2 + b * c + c2);
			case 2484 -> a2 * (b - c) * (a2 + p(b + c, 2));
			case 2485 -> a2 * (b2 - c2) * (a4 - b4 - c4);
			case 2486 -> p(b - c, 2) * (b + c) * (-a2 + b * c + a * (b + c));
			case 2487 -> -((b - c) * (-4 * a2 + p(b - c, 2) + a * (b + c)));
			case 2488 -> a2 * (a - b - c) * (b - c) * (-p(b - c, 2) + a * (b + c));
			case 2489 -> a2 * (b2 - c2) * U * V;
			case 2490 -> (b - c) * (4 * a2 - 3 * a * (b + c) + p(b + c, 2));
			case 2491 -> a4 * (b2 - c2) * (-b4 - c4 + a2 * R);
			case 2492 -> a2 * (b2 - c2) * (a4 - b4 + b2 * c2 - c4);
			case 2493 -> a2 * (a6 * R - a4 * p(b2 + c2, 2) + Q * (b4 - b2 * c2 + c4)
					- a2 * (b6 - 2 * b4 * c2 - 2 * b2 * c4 + c6));
			case 2494 -> a2 * (b - c) * (b5 - b4 * c - b * c4 + c5 + a4 * (b + c)
					- 2 * a3 * R + 2 * a2 * (b3 + c3) - 2 * a * (b4 + c4));
			case 2495 -> a2 * (b - c) * (b5 - b4 * c - b * c4 + c5 + a4 * (b + c)
					- 2 * a * Q - 2 * a3 * R + 2 * a2 * (b3 + c3));
			case 2496 -> -((b - c)
					* (-7 * a3 + 4 * a2 * (b + c) + 2 * p(b - c, 2) * (b + c)
					+ a * (-3 * b2 + 2 * b * c - 3 * c2)));
			case 2497 -> a2 * (b - c)
					* (b2 * p(b - c, 2) * c2 * (b + c)
					+ a2 * (b + c) * p(b2 - b * c + c2, 2)
					+ a4 * (b3 + b2 * c + b * c2 + c3)
					- 2 * a3 * (b4 + b2 * c2 + c4));
			case 2498 -> a2 * (b - c)
					* (b5 - b4 * c - b * c4 + c5 + a4 * (b + c) - 2 * a3 * R
					+ 2 * a2 * (b3 + c3) - 2 * a * (b4 - b2 * c2 + c4));
			case 2499 -> a2 * (b - c) * (b3 - 3 * b2 * c - 3 * b * c2 + c3
					+ a2 * (b + c) - 2 * a * (b2 + b * c + c2));
			default -> Double.NaN;
		};
	}

	private double weight2500to2599(int k, double a, double b, double c) {

		return switch (k) {
			case 2500 -> a * (b - c) * (5 * a3 * (b + c) - 4 * b * c * p(b + c, 2)
					+ 2 * a2 * R - a * (3 * b3 + b2 * c + b * c2 + 3 * c3));
			case 2501 -> (b2 - c2) * V * U;
			case 2502 -> a2 * (2 * a4 - b4 + 4 * b2 * c2 - c4 - 2 * a2 * R);
			case 2503 -> a2 * (b + c) * (a4 - b4 - 2 * a2 * b * c + b3 * c + b2 * c2
					+ b * c3 - c4);
			case 2504 -> (b - c) * (-T)
					* (2 * a3 - a2 * (b + c) + p(b - c, 2) * (b + c));
			case 2505 -> (b - c)
					* (-2 * a3 + 5 * a2 * (b + c) + p(b + c, 3) - 8 * a * R);
			case 2506 -> a2 * (b2 - c2)
					* (a6 + (b2 + c2) * (a4 - b4 + 4 * b2 * c2 - c4)
					- a2 * (b4 + 4 * b2 * c2 + c4));
			case 2507 -> a2 * (2 * b4 * c4 * (b2 - c2) + a6 * (b4 - c4)
					+ a2 * (-b8 + c8));
			case 2508 -> a2 * (b2 - c2)
					* (a8 - b8 - a4 * b2 * c2 + b6 * c2 + b2 * c6 - c8);
			case 2509 -> a * (b - c)
					* (a3 - a2 * (b + c) + a * p(b + c, 2)
					- (b + c) * (b2 + c2))
					* (a3 + a2 * (b + c) + a * p(b + c, 2)
					+ (b + c) * (b2 + c2));
			case 2510 -> a2 * (b2 - c2) * T * (a4 + b4 - 3 * b2 * c2 + c4);
			case 2511 -> a * (b - c) * p(b + c, 2)
					* (a4 - a2 * p(b - c, 2) - b2 * c2);
			case 2512 -> -(a * (b2 - c2)
					* (b * c * (b + c) + a * (2 * b2 + b * c + 2 * c2)));
			case 2513 -> -(a2 * (b2 - c2)
					* (b2 * c2 * R + a2 * (2 * b4 + 3 * b2 * c2 + 2 * c4)));
			case 2514 -> -(a2 * (b2 - c2) * (b4 + c4 + a2 * R));
			case 2515 -> a2 * (b - c) * (2 * a2 + 2 * b2 + 3 * b * c + 2 * c2);
			case 2516 -> a * (b - c) * (5 * a - 3 * (b + c));
			case 2517 -> b * (b - c) * c * (a2 + p(b + c, 2));
			case 2518 -> -(a2 * (b2 - c2) * R
					* (2 * a4 + 2 * b4 - 3 * b2 * c2 + 2 * c4));
			case 2519 -> a2 * (b2 - c2) * T
					* (a4 + b4 - 6 * b2 * c2 + c4 + 2 * a2 * R);
			case 2520 -> -(a * (a - b - c) * (b - c) * (a3 - p(b - c, 2) * (b + c)));
			case 2521 -> a2 * (b - c) * (2 * a4 + 2 * b4 + 3 * a2 * b * c + 3 * b3 * c
					+ 3 * b * c3 + 2 * c4 + 3 * a * b * c * (b + c));
			case 2522 -> a * (b - c) * T * (a2 + p(b + c, 2));
			case 2523 -> a * (b - c) * T * (a2 + a * (b + c) + p(b + c, 2));
			case 2524 -> a2 * (b2 - c2) * T * (-(b2 * c2) + a2 * R);
			case 2525 -> (b2 - c2) * R * (-T);
			case 2526 -> -(a * (b - c) * (a2 + 3 * b2 + 2 * b * c + 3 * c2));
			case 2527 -> (b - c) * (6 * a2 - a * (b + c) + p(b + c, 2));
			case 2528 -> (b2 - c2) * p(b2 + c2, 2);
			case 2529 -> (b - c) * (7 * a2 + a * (b + c) + 2 * p(b + c, 2));
			case 2530 -> -(a * (b - c) * R);
			case 2531 -> -(a4 * (b2 - c2) * p(b2 + c2, 2));
			case 2532 -> a * (b - c)
					* (5 * a3 * (b + c) - 4 * b * c * p(b + c, 2)
					+ 2 * a2 * (b2 - b * c + c2)
					- 3 * a * (b3 + b2 * c + b * c2 + c3));
			case 2533 -> (b2 - c2) * (a2 + b * c);
			case 2534 -> (b + c) * S + 2 * a * u((a + b + c)
					* (a2 * (b + c) + b2 * (c + a) + c2 * (a + b) + a * b * c));
			case 2535 -> (b + c) * S - 2 * a * u((a + b + c)
					* (a2 * (b + c) + b2 * (c + a) + c2 * (a + b) + a * b * c));
			case 2536 -> a * b * c * (b + c) * S - a2 * T * u((a + b + c)
					* (a2 * (b + c) + b2 * (c + a) + c2 * (a + b) + a * b * c));
			case 2537 -> a * b * c * (b + c) * S + a2 * T * u((a + b + c)
					* (a2 * (b + c) + b2 * (c + a) + c2 * (a + b) + a * b * c));
			case 2538 -> a * b * c * (b + c)
					* (a2 * b + a * b2 + a2 * c + a * b * c + b2 * c + a * c2
					+ b * c2)
					* S - a2
					* (a3 * b2 + a2 * b3 - a * b4 - b5 + 2 * a3 * b * c
					+ a2 * b2 * c - 2 * a * b3 * c - b4 * c
					+ a3 * c2 + a2 * b * c2 + a2 * c3
					- 2 * a * b * c3 - a * c4 - b * c4 - c5)
					* u((a + b + c) * (a2 * (b + c) + b2 * (c + a)
					+ c2 * (a + b) + a * b * c));
			case 2539 -> a * b * c * (b + c)
					* (a2 * b + a * b2 + a2 * c + a * b * c + b2 * c + a * c2
					+ b * c2)
					* S + a2
					* (a3 * b2 + a2 * b3 - a * b4 - b5 + 2 * a3 * b * c
					+ a2 * b2 * c - 2 * a * b3 * c - b4 * c
					+ a3 * c2 + a2 * b * c2 + a2 * c3
					- 2 * a * b * c3 - a * c4 - b * c4 - c5)
					* u((a + b + c) * (a2 * (b + c) + b2 * (c + a)
					+ c2 * (a + b) + a * b * c));
			case 2540 -> a * b * c * (b + c) * S
					+ (a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4)
					* u((a + b + c) * (a2 * (b + c) + b2 * (c + a)
					+ c2 * (a + b) + a * b * c));
			case 2541 -> a * b * c * (b + c) * S
					+ (-(a2 * b2) + b4 - a2 * c2 - 2 * b2 * c2 + c4)
					* u((a + b + c) * (a2 * (b + c) + b2 * (c + a)
					+ c2 * (a + b) + a * b * c));
			case 2542 -> -2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					+ U * V * u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 2543 -> -2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					- U * V * u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 2544 -> -2 * (a2 * b2 - b4 + 2 * a2 * c2 + b2 * c2)
					* (2 * a2 * b2 + a2 * c2 + b2 * c2 - c4)
					+ (-a4 - 4 * a2 * b2 + b4 - 4 * a2 * c2 - 2 * b2 * c2 + c4)
					* S * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2545 -> 2 * (a2 * b2 - b4 + 2 * a2 * c2 + b2 * c2)
					* (2 * a2 * b2 + a2 * c2 + b2 * c2 - c4)
					+ (-a4 - 4 * a2 * b2 + b4 - 4 * a2 * c2 - 2 * b2 * c2 + c4)
					* S * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2546 -> 2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					- U * V * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2547 -> -2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					- U * V * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2548 -> -a4 + Q - 2 * a2 * R;
			case 2549 -> -a4 + Q + 2 * a2 * R;
			case 2550 -> -a3 + a2 * (b + c) + p(b - c, 2) * (b + c) - a * p(b + c, 2);
			case 2551 -> -a4 + 4 * a * b * c * (b + c) + Q;
			case 2552 -> -2 * a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					- U * V * u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4 - b2 * c4
					+ c6);
			case 2553 -> 2 * a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					- U * V * u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4 - b2 * c4
					+ c6);
			case 2554 -> -(a * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4))
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2555 -> a * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4)
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2556 -> a2 * b * c
					* (a2 * b - b3 + a2 * c - 2 * a * b * c + b2 * c + b * c2
					- c3)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2
					+ c4)
					+ a2 * (-T)
					* u(a2 * b2
					+ a2 * c2 + b2
					* c2)
					* u(a * b * c
					* (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 3 * a * b * c - b2 * c - a * c2
					- b * c2 + c3));
			case 2557 -> a2 * b * c
					* (a2 * b - b3 + a2 * c - 2 * a * b * c + b2 * c + b * c2
					- c3)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2
					+ c4)
					- a2 * (-T)
					* u(a2 * b2
					+ a2 * c2 + b2
					* c2)
					* u(a * b * c
					* (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 3 * a * b * c - b2 * c - a * c2
					- b * c2 + c3));
			case 2558 -> -2 * a2 * T * (a2 * b2 + a2 * c2 + b2 * c2)
					- 2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 2559 -> 2 * a2 * T * (a2 * b2 + a2 * c2 + b2 * c2)
					- 2 * a2 * (a4 - a2 * b2 - a2 * c2 - 2 * b2 * c2)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4);
			case 2560 -> -(a2 * u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4) * S)
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2561 -> a2 * u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4) * S
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2562 -> -(a2 * T * (a2 * b2 + a2 * c2 + b2 * c2)) + a2 * R
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4) * S;
			case 2563 -> -(a2 * T * (a2 * b2 + a2 * c2 + b2 * c2)) - a2 * R
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4) * S;
			case 2564 -> -2 * a2 * b * c
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4)
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2565 -> 2 * a2 * b * c
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4)
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2566 -> -((a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4))
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2567 -> (a2 * b2 - b4 + a2 * c2 + 2 * b2 * c2 - c4)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4)
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2568 -> -2 * a * b * c * (b + c)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4)
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2569 -> 2 * a * b * c * (b + c)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4)
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2570 -> -(a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4))
					+ a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2571 -> -(a * b * c
					* (a4 + a2 * b2 - 2 * b4 + a2 * c2 + 4 * b2 * c2 - 2 * c4)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4))
					- a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2572 -> a * (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4)
					+ 2 * a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2573 -> -(a
					* (a3 + a2 * b - a * b2 - b3 + a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 - c3)
					* u(a4 - a2 * b2 + b4 - a2 * c2 - b2 * c2 + c4))
					+ 2 * a2 * T * u(a2 * b2 + a2 * c2 + b2 * c2);
			case 2574 -> a / (-(b * c * U * V) + a2 * b * c * (-T)
					- a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6));
			case 2575 -> a / (-(b * c * U * V) + a2 * b * c * (-T)
					+ a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6));
			case 2576 -> -(a2 * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)) + a3
					* T
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2577 -> a2 * b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4) + a3
					* T
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2578 -> a2 / (-(b * c * U * V) + a2 * b * c * (-T)
					- a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6));
			case 2579 -> a2 / (-(b * c * U * V) + a2 * b * c * (-T)
					+ a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6));
			case 2580 -> -(b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)) + a
					* T
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2581 -> b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4) + a
					* T
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2582 -> 1 / (b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					+ a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6));
			case 2583 -> 1 / (b * c
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					- a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6));
			case 2584 -> (a2 * (-T)) / (-(b * c * U * V) + a2 * b * c * (-T)
					- a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6));
			case 2585 -> (a2 * (-T)) / (-(b * c * U * V) + a2 * b * c * (-T)
					+ a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6));
			case 2586 -> -(b * c * U * V
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4))
					+ a * T * U * V
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2587 -> b * c * U * V
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4)
					+ a * T * U * V
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2588 -> 1 / ((-T) * (-(b * c * U * V) + a2 * b * c * (-T)
					- a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6)));
			case 2589 -> 1 / ((-T) * (-(b * c * U * V) + a2 * b * c * (-T)
					+ a * (-T)
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6)));
			case 2590 -> a / (-(b4 * c) - 2 * a * b2 * c2 + b3 * c2
					+ b2 * c3 - b
					* c4
					+ a2 * b * c * (b + c)
					+ (-T) * u(a * b * c
					* (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 3 * a * b * c - b2 * c - a * c2 - b * c2
					+ c3)));
			case 2591 -> a / (-(b4 * c) - 2 * a * b2 * c2 + b3 * c2
					+ b2 * c3 - b
					* c4
					+ a2 * b * c * (b + c)
					- (-T) * u(a * b * c
					* (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 3 * a * b * c - b2 * c - a * c2 - b * c2
					+ c3)));
			case 2592 -> -(b2 * (b - c) * c2 * (b + c)
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4))
					- a * b * (b - c) * c * (b + c) * T
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2593 -> -(b2 * (b - c) * c2 * (b + c)
					* (2 * a4 - a2 * b2 - b4 - a2 * c2 + 2 * b2 * c2 - c4))
					+ a * b * (b - c) * c * (b + c) * T
					* u(a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2
					+ 3 * a2 * b2 * c2 - b4 * c2 - a2 * c4
					- b2 * c4 + c6);
			case 2594 -> a2 * (a + b - c) * (a - b + c) * (b + c)
					* (a2 - b2 - b * c - c2);
			case 2595 -> (a + b - c) * (a - b + c)
					* (a8 - b * p(b - c, 2) * c * p(b + c, 4)
					- 2 * a6 * (b2 + b * c + c2)
					+ a4 * p(b2 + b * c + c2, 2)
					+ a2 * b * c * (b4 + b3 * c + b * c3 + c4));
			case 2596 -> (a - b - c) * (a8 + b * p(b - c, 4) * c * p(b + c, 2)
					- 2 * a6 * (b2 - b * c + c2) + a4 * p(b2 - b * c + c2, 2)
					- a2 * b * p(b - c, 2) * c * (b2 + b * c + c2));
			case 2597 -> a2 * (a + b - c) * (a - b + c)
					* (a7 * b - 2 * a6 * b2 + c4 * Q - a5 * b * R
					+ a4 * (4 * b4 + b2 * c2 + c4)
					- a3 * (b5 + 2 * b * c4)
					+ a2 * (-2 * b6 + b4 * c2 + 3 * b2 * c4 - 2 * c6)
					+ a * (b7 - b5 * c2 - 2 * b3 * c4 + 2 * b * c6))
					* (a7 * c - 2 * a6 * c2 + b4 * Q - a5 * c * R
					+ a4 * (b4 + b2 * c2 + 4 * c4)
					- a3 * (2 * b4 * c + c5)
					+ a2 * (-2 * b6 + 3 * b4 * c2 + b2 * c4 - 2 * c6)
					+ a * (2 * b6 * c - 2 * b4 * c3 - b2 * c5 + c7));
			case 2598 -> a2 * (2 * a9 * b * c + b2 * p(b - c, 4) * c2 * p(b + c, 3)
					+ a8 * (b3 - 2 * b2 * c - 2 * b * c2 + c3)
					- a7 * (b4 + 5 * b3 * c - 4 * b2 * c2 + 5 * b * c3 + c4)
					- a6 * (3 * b5 - 6 * b4 * c + b3 * c2 + b2 * c3 - 6 * b * c4
					+ 3 * c5)
					+ a * Q * (b6 - b5 * c + b3 * c3 - b * c5 + c6)
					+ a5 * (3 * b6 + 3 * b5 * c - 7 * b4 * c2 + 8 * b3 * c3
					- 7 * b2 * c4 + 3 * b * c5 + 3 * c6)
					- a3 * p(b - c, 2)
					* (3 * b6 + 5 * b5 * c + 2 * b4 * c2 + 3 * b3 * c3
					+ 2 * b2 * c4 + 5 * b * c5 + 3 * c6)
					- a2 * p(b - c, 2)
					* (b7 + 3 * b5 * c2 + 7 * b4 * c3 + 7 * b3 * c4
					+ 3 * b2 * c5 + c7)
					+ 3 * a4 * (b7 - 2 * b6 * c + 2 * b5 * c2 + 2 * b2 * c5
					- 2 * b * c6 + c7));
			case 2599 -> -(a * (a + b - c) * (a - b + c) * (b + c)
					* (a2 - b2 - b * c - c2) * (-Q + a2 * R));
			default -> Double.NaN;
		};
	}

	private double weight2600to2699(int k, double a, double b, double c) {
		return switch (k) {
			case 2600 -> a * (a - b - c) * (b - c) * (a2 - b2 + b * c - c2)
					* (-Q + a2 * R);
			case 2601 -> a * (a + b - c) * (a - b + c)
					* (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2))
					* (a8 - b * p(b - c, 2) * c * p(b + c, 4)
					- 2 * a6 * (b2 + b * c + c2)
					+ a4 * p(b2 + b * c + c2, 2)
					+ a2 * b * c * (b4 + b3 * c + b * c3 + c4));
			case 2602 -> a * (a - b - c) * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a8 + b * p(b - c, 4) * c * p(b + c, 2)
					- 2 * a6 * (b2 - b * c + c2)
					+ a4 * p(b2 - b * c + c2, 2)
					- a2 * b * p(b - c, 2) * c * (b2 + b * c + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 2603 -> a * (a + b - c) * (a - b + c) * (-Q + a2 * R)
					* (a7 * b - 2 * a6 * b2 + c4 * Q - a5 * b * R
					+ a4 * (4 * b4 + b2 * c2 + c4)
					- a3 * (b5 + 2 * b * c4)
					+ a2 * (-2 * b6 + b4 * c2 + 3 * b2 * c4 - 2 * c6)
					+ a * (b7 - b5 * c2 - 2 * b3 * c4 + 2 * b * c6))
					* (a7 * c - 2 * a6 * c2 + b4 * Q - a5 * c * R
					+ a4 * (b4 + b2 * c2 + 4 * c4)
					- a3 * (2 * b4 * c + c5)
					+ a2 * (-2 * b6 + 3 * b4 * c2 + b2 * c4 - 2 * c6)
					+ a * (2 * b6 * c - 2 * b4 * c3 - b2 * c5 + c7));
			case 2604 -> -(a * (-Q + a2 * R) * (a10 * a2 * R
					- a10 * a * (b3 + b2 * c + b * c2 + c3)
					- a * b * p(b - c, 4) * c * p(b + c, 3)
					* (b4 + 3 * b2 * c2 + c4)
					+ b * c * p(b2 - c2, 4)
					* (b4 - b3 * c + b2 * c2 - b * c3 + c4)
					- 2 * a10 * (2 * b4 + 3 * b2 * c2 + 2 * c4)
					+ a9 * (4 * b5 + 3 * b4 * c + 5 * b3 * c2 + 5 * b2 * c3
					+ 3 * b * c4 + 4 * c5)
					+ a4 * Q * (b6 + 6 * b5 * c - b4 * c2 + 5 * b3 * c3
					- b2 * c4 + 6 * b * c5 + c6)
					- a2 * b * c * Q
					* (4 * b6 - 3 * b5 * c - 3 * b * c5 + 4 * c6)
					+ a8 * (6 * b6 + b5 * c + 9 * b4 * c2 - 2 * b3 * c3
					+ 9 * b2 * c4 + b * c5 + 6 * c6)
					+ a5 * p(b - c, 2)
					* (4 * b7 + 6 * b6 * c + 12 * b5 * c2 + 15 * b4 * c3
					+ 15 * b3 * c4 + 12 * b2 * c5 + 6 * b * c6
					+ 4 * c7)
					- a7 * (6 * b7 + 2 * b6 * c + 7 * b5 * c2 + 3 * b4 * c3
					+ 3 * b3 * c4 + 7 * b2 * c5 + 2 * b * c6 + 6 * c7)
					- a6 * (4 * b8 + 4 * b7 * c + 3 * b6 * c2 - 4 * b5 * c3
					+ 4 * b4 * c4 - 4 * b3 * c5 + 3 * b2 * c6
					+ 4 * b * c7 + 4 * c8)
					- a3 * p(b - c, 2)
					* (b9 - b8 * c - b7 * c2 - 3 * b6 * c3 - 8 * b5 * c4
					- 8 * b4 * c5 - 3 * b3 * c6 - b2 * c7
					- b * c8 + c9)));
			case 2605 -> a2 * (-b3 + a2 * (b - c) + c3);
			case 2606 -> a6 + a2 * b2 * c2 - b * c * Q - a4 * R;
			case 2607 -> a6 + a2 * b2 * c2 + b * c * Q - a4 * R;
			case 2608 -> -(a2
					* (a5 * b - 2 * a3 * b3 + a * b5 - b2 * c4 + c6
					+ a2 * c2 * (b2 - c2))
					* (b6 + a5 * c - b4 * c2 - 2 * a3 * c3 + a * c5
					+ a2 * (-b4 + b2 * c2)));
			case 2609 -> a2 * (2 * a6 * b * c + 2 * a2 * b3 * c3 + b2 * c2 * Q
					- 2 * a4 * b * c * R - a5 * (b3 + c3) + 2 * a3 * (b5 + c5)
					- a * (b7 + c7));
			case 2610 -> -(a * (b - c) * p(b + c, 2) * (a2 - b2 + b * c - c2));
			case 2611 -> a * p(b - c, 2) * (b + c) * (a2 - b2 - b * c - c2);
			case 2612 -> -(a * (a - b) * (a + b) * (a - c) * (a + c)
					* (a6 + a2 * b2 * c2 - b * c * Q - a4 * R));
			case 2613 -> -(a * (a - b) * (a + b) * (a - c) * (a + c)
					* (a6 + a2 * b2 * c2 + b * c * Q - a4 * R));
			case 2614 -> a * (b2 - c2)
					* (a5 * b - 2 * a3 * b3 + a * b5 - b2 * c4 + c6
					+ a2 * c2 * (b2 - c2))
					* (b6 + a5 * c - b4 * c2 - 2 * a3 * c3 + a * c5
					+ a2 * (-b4 + b2 * c2));
			case 2615 -> a * p(b - c, 2) * p(b + c, 2)
					* (a7 - a * b2 * p(b - c, 2) * c2 - a4 * b * c * (b + c)
					- 2 * a5 * (b2 - b * c + c2)
					+ a3 * p(b2 - b * c + c2, 2)
					+ 2 * a2 * b * c * (b3 + c3) - b * c * (b5 + c5));
			case 2616 -> a * (b2 - c2) * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 2617 -> -(a * (a - b) * (a + b) * (a - c) * (a + c) * (-Q + a2 * R));
			case 2618 -> b * (b - c) * c * (b + c) * (Q - a2 * R);
			case 2619 -> -(b * c
					* (2 * a8 + 2 * a4 * b2 * c2 + p(b2 - c2, 4) - 2 * a6 * R
					- a2 * Q * R)
					* (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (-a4 + c2 * (b2 - c2) + a2 * (b2 + 2 * c2)));
			case 2620 -> a3 * (a4 + b4
					- b2 * c2 - a2
					* (2 * b2 + c2))
					* (a4 - b2 * c2 + c4
					- a2 * (b2 + 2 * c2))
					* (a6 * (b4 + c4)
					+ a4 * (-3 * b6 + b4 * c2 + b2 * c4 - 3 * c6)
					- Q * (b6 + c6) + a2 * (3 * b8 - 3 * b6 * c2
					+ 2 * b4 * c4 - 3 * b2 * c6 + 3 * c8));
			case 2621 -> -(b * c * (Q - a2 * R)
					* (a10 + b4 * p(b2 - c2, 3) - a8 * (2 * b2 + 3 * c2)
					- a2 * b4 * (2 * b4 - 3 * b2 * c2 + c4)
					+ a6 * (b4 + 3 * b2 * c2 + 3 * c4)
					+ a4 * (b6 - 2 * b4 * c2 - b2 * c4 - c6))
					* (-a10 + c4 * p(b2 - c2, 3) + a8 * (3 * b2 + 2 * c2)
					- a6 * (3 * b4 + 3 * b2 * c2 + c4)
					+ a2 * c4 * (b4 - 3 * b2 * c2 + 2 * c4)
					+ a4 * (b6 + b4 * c2 + 2 * b2 * c4 - c6)));
			case 2622 -> a2 * (-Q + a2 * R)
					* (b4 * c4 * p(b2 - c2, 4) + a10 * a4 * R
					- a2 * b4 * c4 * Q * R - a10 * a3 * (b3 + c3)
					- a10 * a2 * (5 * b4 + 6 * b2 * c2 + 5 * c4)
					+ a10 * a
					* (5 * b5 + 3 * b3 * c2 + 3 * b2 * c3
					+ 5 * c5)
					- a4 * p(b6 - c6, 2)
					+ a * b2 * p(b - c, 2) * c2 * p(b + c, 3)
					* (b6 - 3 * b5 * c + 3 * b4 * c2
					- 3 * b3 * c3 + 3 * b2 * c4
					- 3 * b * c5 + c6)
					+ 2 * a10
					* (5 * b6 + 6 * b4 * c2 + 6 * b2 * c4
					+ 5 * c6)
					- a9 * (10 * b7 + 9 * b5 * c2 + 3 * b4 * c3
					+ 3 * b3 * c4 + 9 * b2 * c5 + 10 * c7)
					- 2 * a8 * (5 * b8 + 5 * b6 * c2 + 4 * b4 * c4
					+ 5 * b2 * c6 + 5 * c8)
					+ a7 * (10 * b9 + 8 * b7 * c2 + 3 * b6 * c3
					+ 3 * b5 * c4 + 3 * b4 * c5 + 3 * b3 * c6
					+ 8 * b2 * c7 + 10 * c9)
					+ a6 * (5 * b10 + 3 * b8 * c2 + b6 * c4 + b4 * c6
					+ 3 * b2 * c8 + 5 * c10)
					+ a3 * p(b - c, 2)
					* (b10 * b + 2 * b10 * c + 4 * b8 * c3
					+ 11 * b7 * c4 + 12 * b6 * c5
					+ 12 * b5 * c6 + 11 * b4 * c7
					+ 4 * b3 * c8 + 2 * b * c10
					+ c10 * c)
					- a5 * (5 * b10 * b + 6 * b8 * c3 + b7 * c4
					- 3 * b6 * c5 - 3 * b5 * c6 + b4 * c7
					+ 6 * b3 * c8 + 5 * c10 * c));
			case 2623 -> a2 * (b2 - c2) * (a4 + b4 - b2 * c2 - a2 * (2 * b2 + c2))
					* (a4 - b2 * c2 + c4 - a2 * (b2 + 2 * c2));
			case 2624 -> a3 * (-b6 + c6 + a4 * (-b2 + c2) + 2 * a2 * (b4 - c4));
			case 2625 -> -((a - b) * b * (a + b) * (a - c) * c * (a + c)
					* (2 * a8 + 2 * a4 * b2 * c2 + p(b2 - c2, 4) - 2 * a6 * R
					- a2 * Q * R));
			case 2626 -> -(a3 * (a - b)
					* (a + b) * (a
					- c)
					* (a + c)
					* (a6 * (b4 + c4)
					+ a4 * (-3 * b6 + b4 * c2 + b2 * c4 - 3 * c6)
					- Q * (b6 + c6) + a2 * (3 * b8 - 3 * b6 * c2
					+ 2 * b4 * c4 - 3 * b2 * c6 + 3 * c8)));
			case 2627 -> b * (b - c) * c * (b + c)
					* (a10 + b4 * p(b2 - c2, 3) - a8 * (2 * b2 + 3 * c2)
					- a2 * b4 * (2 * b4 - 3 * b2 * c2 + c4)
					+ a6 * (b4 + 3 * b2 * c2 + 3 * c4)
					+ a4 * (b6 - 2 * b4 * c2 - b2 * c4 - c6))
					* (-a10 + c4 * p(b2 - c2, 3) + a8 * (3 * b2 + 2 * c2)
					- a6 * (3 * b4 + 3 * b2 * c2 + c4)
					+ a2 * c4 * (b4 - 3 * b2 * c2 + 2 * c4)
					+ a4 * (b6 + b4 * c2 + 2 * b2 * c4 - c6));
			case 2628 -> -(a2 * p(b - c, 2) * (b + c) * (a10 * a2 * (b + c)
					- b4 * p(b - c, 2) * c4 * p(b + c, 3)
					- a10 * a * (b2 + b * c + c2)
					- 4 * a10 * (b3 + b2 * c + b * c2 + c3)
					+ a4 * (b + c) * p(b4 + b2 * c2 + c4, 2)
					+ a9 * (4 * b4 + 4 * b3 * c + 6 * b2 * c2 + 4 * b * c3
					+ 4 * c4)
					+ 2 * a8 * (3 * b5 + 3 * b4 * c + 5 * b3 * c2 + 5 * b2 * c3
					+ 3 * b * c4 + 3 * c5)
					- a7 * (6 * b6 + 6 * b5 * c + 11 * b4 * c2 + 10 * b3 * c3
					+ 11 * b2 * c4 + 6 * b * c5 + 6 * c6)
					- 4 * a6 * (b7 + b6 * c + 2 * b5 * c2 + 2 * b4 * c3
					+ 2 * b3 * c4 + 2 * b2 * c5 + b * c6 + c7)
					+ a * b2 * c2
					* (b8 - b7 * c - 2 * b6 * c2 - 2 * b2 * c6 - b * c7
					+ c8)
					+ a5 * (4 * b8 + 4 * b7 * c + 9 * b6 * c2 + 7 * b5 * c3
					+ 6 * b4 * c4 + 7 * b3 * c5 + 9 * b2 * c6
					+ 4 * b * c7 + 4 * c8)
					- a3 * (b10 + b9 * c + 4 * b8 * c2 - b6 * c4 + 3 * b5 * c5
					- b4 * c6 + 4 * b2 * c8 + b * c9 + c10)));
			case 2629 -> a * (a8 - a6 * R + 3 * a2 * Q * R
					+ a4 * (-2 * b4 + 5 * b2 * c2 - 2 * c4)
					- Q * (b4 + 3 * b2 * c2 + c4));
			case 2630 -> a * (2 * a9 - a8 * (b + c) - 2 * a * b2 * c2 * Q - 2 * a7 * R
					+ 2 * a3 * Q * R - a6 * (b3 - 3 * b2 * c - 3 * b * c2 + c3)
					- 2 * a5 * (b4 - 3 * b2 * c2 + c4)
					- p(b - c, 2) * p(b + c, 3)
					* (b4 - 2 * b3 * c + b2 * c2 - 2 * b * c3 + c4)
					- a2 * p(b - c, 2)
					* (b5 + 3 * b4 * c + 8 * b3 * c2 + 8 * b2 * c3
					+ 3 * b * c4 + c5)
					+ a4 * (4 * b5 - 2 * b4 * c - 3 * b3 * c2 - 3 * b2 * c3
					- 2 * b * c4 + 4 * c5));
			case 2631 -> a * (b2 - c2) * T * (2 * a4 - Q - a2 * R);
			case 2632 -> -(a * p(b - c, 2) * p(b + c, 2) * (T * T));
			case 2633 -> -(a * (a - b) * (a + b) * (a - c) * (a + c) * U * V
					* (a8 - a6 * R + 3 * a2 * Q * R
					+ a4 * (-2 * b4 + 5 * b2 * c2 - 2 * c4)
					- Q * (b4 + 3 * b2 * c2 + c4)));
			case 2634 -> -(a * p(b - c, 2) * (b + c) * T
					* (a10 * a2 + 3 * a10 * b * c - 2 * a10 * a * (b + c)
					- 10 * a7 * b2 * c2 * (b + c)
					- 2 * a * b2 * p(b - c, 2) * c2 * p(b + c, 3) * R
					- b * c * p(b + c, 4)
					* p(b3 - 2 * b2 * c + 2 * b * c2 - c3, 2)
					+ 4 * a9 * (b3 + b2 * c + b * c2 + c3)
					+ 2 * a3 * p(b - c, 2) * p(b + c, 3)
					* (b4 + 3 * b2 * c2 + c4)
					+ a4 * b * c
					* p(b + c, 2)
					* (3 * b4 - 13 * b3 * c + 17 * b2 * c2
					- 13 * b * c3 + 3 * c4)
					- a8 * (5 * b4 + 6 * b3 * c - b2 * c2 + 6 * b * c3
					+ 5 * c4)
					- a2 * Q * (b6 - 2 * b4 * c2 + 3 * b3 * c3
					- 2 * b2 * c4 + c6)
					+ a6 * (5 * b6 + b5 * c + 2 * b4 * c2 + 13 * b3 * c3
					+ 2 * b2 * c4 + b * c5 + 5 * c6)
					+ a5 * (-4 * b7 - 4 * b6 * c + 6 * b5 * c2
					+ 6 * b4 * c3 + 6 * b3 * c4 + 6 * b2 * c5
					- 4 * b * c6 - 4 * c7)));
			case 2635 -> a * (a4 * (b + c) - a2 * p(b - c, 2) * (b + c)
					- 2 * b * p(b - c, 2) * c * (b + c) + a * Q - a3 * R);
			case 2636 -> a * (-(b2 * p(b - c, 4) * c2 * p(b + c, 2))
					+ a * b * p(b - c, 4) * c * p(b + c, 3)
					+ a8 * (b2 - 3 * b * c + c2)
					+ a7 * (-2 * b3 + 3 * b2 * c + 3 * b * c2 - 2 * c3)
					+ a5 * p(b - c, 2)
					* (4 * b3 + 3 * b2 * c + 3 * b * c2 + 4 * c3)
					- a6 * (b4 - 5 * b3 * c + 9 * b2 * c2 - 5 * b * c3 + c4)
					+ a2 * Q * (b4 - b3 * c + 5 * b2 * c2 - b * c3 + c4)
					- a4 * p(b - c, 2)
					* (b4 + 3 * b3 * c - b2 * c2 + 3 * b * c3 + c4)
					- a3 * p(b - c, 2) * (2 * b5 + 3 * b4 * c + 7 * b3 * c2
					+ 7 * b2 * c3 + 3 * b * c4 + 2 * c5));
			case 2637 -> a2 * (a - b - c) * (b - c) * T
					* (a4 * (b + c) - a2 * p(b - c, 2) * (b + c)
					- 2 * b * p(b - c, 2) * c * (b + c) + a * Q
					- a3 * R);
			case 2638 -> a3 * p(b - c, 2) * p(-a + b + c, 2) * (T * T);
			case 2639 -> (a - b) * (a - c) * (a + b - c) * (a - b + c) * U * V
					* (-(b2 * p(b - c, 4) * c2 * p(b + c, 2))
					+ a * b * p(b - c, 4) * c * p(b + c, 3)
					+ a8 * (b2 - 3 * b * c + c2)
					+ a7 * (-2 * b3 + 3 * b2 * c + 3 * b * c2 - 2 * c3)
					+ a5 * p(b - c, 2)
					* (4 * b3 + 3 * b2 * c + 3 * b * c2
					+ 4 * c3)
					- a6 * (b4 - 5 * b3 * c + 9 * b2 * c2 - 5 * b * c3
					+ c4)
					+ a2 * Q * (b4 - b3 * c + 5 * b2 * c2 - b * c3 + c4)
					- a4 * p(b - c, 2)
					* (b4 + 3 * b3 * c - b2 * c2 + 3 * b * c3
					+ c4)
					- a3 * p(b - c, 2)
					* (2 * b5 + 3 * b4 * c + 7 * b3 * c2
					+ 7 * b2 * c3 + 3 * b * c4
					+ 2 * c5));
			case 2640 -> a * (a4 - b4 + 3 * b2 * c2 - c4 - a2 * R);
			case 2641 -> a * (2 * a5 + 2 * a * b2 * c2 - a4 * (b + c) - 2 * a3 * R
					- (b + c) * p(b2 - b * c + c2, 2)
					+ a2 * (3 * b3 - b2 * c - b * c2 + 3 * c3));
			case 2642 -> a * (b2 - c2) * (-2 * a2 + b2 + c2);
			case 2643 -> -(a * p(b - c, 2) * p(b + c, 2));
			case 2644 -> -(a * (a - b) * (a + b) * (a - c) * (a + c)
					* (a4 - b4 + 3 * b2 * c2 - c4 - a2 * R));
			case 2645 -> a * (b + c) * p(b - c, 2)
					* (a6 + 2 * a5 * (b + c) + 2 * a * b2 * c2 * (b + c)
					- 3 * a4 * (b2 + b * c + c2)
					- 2 * a3 * (b3 + b2 * c + b * c2 + c3) - b
					* c
					* (b4 + 2 * b3 * c + b2 * c2 + 2 * b * c3
					+ c4)
					+ a2 * (b4 + 3 * b3 * c + 7 * b2 * c2 + 3 * b * c3
					+ c4));
			case 2646 -> a * (a - b - c) * (2 * a2 - p(b - c, 2) + a * (b + c));
			case 2647 -> a * (a + b - c) * (a - b + c)
					* (a4 + a2 * b * c - a3 * (b + c)
					- p(b + c, 2) * (b2 - 3 * b * c + c2)
					+ a * (b3 + b2 * c + b * c2 + c3));
			case 2648 -> a * (a - b - c)
					* (a3 + b3 + a * (b - 2 * c) * c - 2 * b * c2 + c3)
					* (a3 + b3 - 2 * b2 * c + c3 + a * b * (-2 * b + c));
			case 2649 -> a * (-a + b + c)
					* (2 * a5 - a4 * b - 5 * a3 * b2 + 2 * a2 * b3 + 3 * a * b4
					- b5 - a4 * c + 4 * a3 * b * c - 3 * a2 * b2 * c
					- 4 * a * b3 * c + 2 * b4 * c - 5 * a3 * c2
					- 3 * a2 * b * c2 + 2 * a * b2 * c2 - b3 * c2
					+ 2 * a2 * c3 - 4 * a * b * c3 - b2 * c3
					+ 3 * a * c4 + 2 * b * c4 - c5);
			case 2650 -> a * (b + c) * (2 * a2 - p(b - c, 2) + a * (b + c));
			case 2651 -> a * (a + b) * (a + c)
					* (a3 + b3 + a * b * c + c3 - 2 * a2 * (b + c));
			case 2652 -> a * (b + c)
					* (a3 + b3 + a * (b - 2 * c) * c - 2 * b * c2 + c3)
					* (a3 + b3 - 2 * b2 * c + c3 + a * b * (-2 * b + c));
			case 2653 -> a2 * (b + c)
					* (-b4 + b3 * c + 2 * b2 * c2 + b * c3 - c4 + a3 * (b + c)
					+ 3 * a * b * c * (b + c)
					+ 2 * a2 * (b2 + b * c + c2));
			case 2654 -> a * (-2 * a4 * b * c + a5 * (b + c) + 2 * b * c * Q
					- 2 * a3 * (b3 + c3) + a * (b5 - b4 * c - b * c4 + c5));
			case 2655 -> a * (a + b - c) * (a - b + c)
					* (b2 * p(b - c, 2) * c2 * p(b + c, 3)
					- a2 * p(b - c, 2) * p(b + c, 3) * R
					+ a3 * Q * (b2 - b * c + c2)
					+ a * b * c * Q * (b2 - b * c + c2)
					+ a7 * (b2 + b * c + c2)
					- a6 * (b3 + b2 * c + b * c2 + c3)
					- a5 * (2 * b4 + b3 * c - b2 * c2 + b * c3 + 2 * c4)
					+ a4 * (2 * b5 + 2 * b4 * c - b3 * c2 - b2 * c3
					+ 2 * b * c4 + 2 * c5));
			case 2656 -> a * (a - b - c)
					* (a6 * p(b - c, 2) * (b + c)
					- b2 * p(b - c, 3) * c2 * p(b + c, 2)
					+ a7 * (b2 + b * c - c2)
					+ a * b * c * Q * (b2 - b * c + c2)
					+ a2 * (b + c) * p(b3 - b2 * c + b * c2 - c3, 2)
					- a5 * (2 * b4 + b3 * c - b2 * c2 + b * c3 - 2 * c4)
					+ a4 * (-2 * b5 + 2 * b4 * c + b3 * c2 - b2 * c3
					+ 2 * b * c4 - 2 * c5)
					+ a3 * (b6 - b5 * c + b4 * c2 + 2 * b3 * c3
					- b2 * c4 - b * c5 - c6))
					* (-(a6 * p(b - c, 2) * (b + c))
					- b2 * p(b - c, 3) * c2 * p(b + c, 2)
					+ a7 * (b2 - b * c - c2)
					- a * b * c * Q * (b2 - b * c + c2)
					- a2 * (b + c) * p(b3 - b2 * c + b * c2 - c3, 2)
					+ a5 * (-2 * b4 + b3 * c - b2 * c2 + b * c3
					+ 2 * c4)
					+ a4 * (2 * b5 - 2 * b4 * c + b3 * c2 - b2 * c3
					- 2 * b * c4 + 2 * c5)
					+ a3 * (b6 + b5 * c + b4 * c2 - 2 * b3 * c3
					- b2 * c4 + b * c5 - c6));
			case 2657 -> a * (a - b - c)
					* (2 * a9 * b * c - b2 * p(b - c, 4) * c2 * p(b + c, 3)
					- a5 * b * c * Q - a * b * c * p(b2 - c2, 4)
					- 3 * a7 * b * c * R + 3 * a3 * b * c * Q * R
					+ a8 * (b3 + c3) - 3 * a6 * (b5 + c5)
					+ a4 * (3 * b7 - b5 * c2 - 2 * b4 * c3 - 2 * b3 * c4
					- b2 * c5 + 3 * c7)
					- a2 * (b9 - 2 * b7 * c2 + b5 * c4 + b4 * c5
					- 2 * b2 * c7 + c9));
			case 2658 -> a2 * (b + c) * T
					* (a4 * (b + c) - a2 * p(b - c, 2) * (b + c)
					- 2 * b * p(b - c, 2) * c * (b + c) - a * Q
					+ a3 * R);
			case 2659 -> (a + b) * (a + c) * U * V
					* (b2 * p(b - c, 2) * c2 * p(b + c, 3)
					- a2 * p(b - c, 2) * p(b + c, 3) * R
					+ a3 * Q * (b2 - b * c + c2)
					+ a * b * c * Q * (b2 - b * c + c2)
					+ a7 * (b2 + b * c + c2)
					- a6 * (b3 + b2 * c + b * c2 + c3)
					- a5 * (2 * b4 + b3 * c - b2 * c2 + b * c3 + 2 * c4)
					+ a4 * (2 * b5 + 2 * b4 * c - b3 * c2 - b2 * c3
					+ 2 * b * c4 + 2 * c5));
			case 2660 -> a2 * (b + c) * T
					* (a6 * p(b - c, 2) * (b + c)
					- b2 * p(b - c, 3) * c2 * p(b + c, 2)
					+ a7 * (b2 + b * c - c2)
					+ a * b * c * Q * (b2 - b * c + c2)
					+ a2 * (b + c) * p(b3 - b2 * c + b * c2 - c3, 2)
					- a5 * (2 * b4 + b3 * c - b2 * c2 + b * c3 - 2 * c4)
					+ a4 * (-2 * b5 + 2 * b4 * c + b3 * c2 - b2 * c3
					+ 2 * b * c4 - 2 * c5)
					+ a3 * (b6 - b5 * c + b4 * c2 + 2 * b3 * c3
					- b2 * c4 - b * c5 - c6))
					* (-(a6 * p(b - c, 2) * (b + c))
					- b2 * p(b - c, 3) * c2 * p(b + c, 2)
					+ a7 * (b2 - b * c - c2)
					- a * b * c * Q * (b2 - b * c + c2)
					- a2 * (b + c) * p(b3 - b2 * c + b * c2 - c3, 2)
					+ a5 * (-2 * b4 + b3 * c - b2 * c2 + b * c3
					+ 2 * c4)
					+ a4 * (2 * b5 - 2 * b4 * c + b3 * c2 - b2 * c3
					- 2 * b * c4 + 2 * c5)
					+ a3 * (b6 + b5 * c + b4 * c2 - 2 * b3 * c3
					- b2 * c4 + b * c5 - c6));
			case 2661 -> a2 * (b + c) * T * (2 * a10 * a * b * c * (b + c)
					- b3 * c3 * p(b2 - c2, 4)
					- a * b2 * p(b - c, 4) * c2 * p(b + c, 3)
					* (2 * b2 + b * c + 2 * c2)
					+ a10 * (b4 + b3 * c + 2 * b2 * c2 + b * c3 + c4)
					- a2 * b * p(b - c, 4) * c * p(b + c, 2)
					* (2 * b4 + 3 * b3 * c + 5 * b2 * c2 + 3 * b * c3
					+ 2 * c4)
					+ a9 * (b5 - 4 * b4 * c - 4 * b * c4 + c5)
					- a4 * Q * (b6 - 5 * b5 * c + 2 * b4 * c2 - 7 * b3 * c3
					+ 2 * b2 * c4 - 5 * b * c5 + c6)
					- a3 * p(b - c, 2) * p(b + c, 3)
					* (b6 + b5 * c - 4 * b4 * c2 + 5 * b3 * c3
					- 4 * b2 * c4 + b * c5 + c6)
					- a8 * (3 * b6 + b5 * c + 2 * b4 * c2 + 2 * b3 * c3
					+ 2 * b2 * c4 + b * c5 + 3 * c6)
					+ a6 * p(b - c, 2)
					* (3 * b6 + 3 * b5 * c + 2 * b4 * c2 + 3 * b3 * c3
					+ 2 * b2 * c4 + 3 * b * c5 + 3 * c6)
					- a7 * (3 * b7 + 5 * b5 * c2 - 5 * b4 * c3 - 5 * b3 * c4
					+ 5 * b2 * c5 + 3 * c7)
					+ a5 * (3 * b9 + 4 * b8 * c - 9 * b6 * c3 + 2 * b5 * c4
					+ 2 * b4 * c5 - 9 * b3 * c6 + 4 * b * c8 + 3 * c9));
			case 2662 -> a * (a + b - c) * (a - b + c)
					* (-(a7 * b * c * (b + c))
					+ a3 * b * p(b - c, 2) * c * p(b + c, 3)
					- b2 * p(b - c, 2) * c2 * p(b + c, 4)
					- a * b * p(b - c, 2) * c * p(b + c, 3) * R
					+ a8 * (b2 + 3 * b * c + c2)
					+ a5 * b * c * (b3 + b2 * c + b * c2 + c3)
					- a2 * Q * (b4 - b3 * c + b2 * c2 - b * c3 + c4)
					+ a4 * p(b + c, 2)
					* (3 * b4 - 5 * b3 * c + 9 * b2 * c2
					- 5 * b * c3 + 3 * c4)
					- a6 * (3 * b4 + 5 * b3 * c + 3 * b2 * c2
					+ 5 * b * c3 + 3 * c4));
			case 2663 -> a * (b2 * c2 + a * b * c * (b + c)
					+ a2 * (b2 + 3 * b * c + c2));
			case 2664 -> a * (-(b2 * c2) - a * b * c * (b + c)
					+ a2 * (b2 + b * c + c2));
			case 2665 -> a * (a * b * (b - c) * c + b2 * c2 + a2 * (b2 - b * c - c2))
					* (a * b * (b - c) * c - b2 * c2 + a2 * (b2 + b * c - c2));
			case 2666 -> a * ((3 + a / b + b / c + c / a) * (1 / c + 1 / a)
					+ (3 + b / a + c / b + a / c) * (1 / b + 1 / a));
			case 2667 -> a2 * (b + c) * (2 * b * c + a * (b + c));
			case 2668 -> (a + b) * (a + c) * (b2 * c2 + a * b * c * (b + c)
					+ a2 * (b2 + 3 * b * c + c2));
			case 2669 -> (a + b) * (a + c) * (-(b2 * c2) - a * b * c * (b + c)
					+ a2 * (b2 + b * c + c2));
			case 2670 -> a * ((b * a2 / (b + a) + c * b2 / (c + b) + a * c2 / (a + c))
					* c * a / (c + a)
					+ (c * a2 / (c + a) + a * b2 / (a + b) + b * c2 / (b + c))
					* b * a / (b + a));
			case 2671 -> 1 / (4 * (-T) + (3 + u(5)) * S);
			case 2672 -> 1 / (4 * (-T) - (3 + u(5)) * S);
			case 2673 -> a2 * (4 * (-T) + (3 + u(5)) * S);
			case 2674 -> a2 * (4 * (-T) - (3 + u(5)) * S);
			case 2675 -> 17 * a4 - 15 * Q - 2 * a2 * R + 3 * (a + b - c) * (a - b + c)
					* (-a + b + c) * (a + b + c) * u(5);
			case 2676 -> a4 - 15 * Q + 14 * a2 * R + 3 * (a + b - c) * (a - b + c)
					* (-a + b + c) * (a + b + c) * u(5);
			case 2677 -> p(b - c, 2) * (b + c)
					* (-(a2 * b) + b3 - a2 * c + 2 * a * b * c - b2 * c - b * c2
					+ c3)
					* (a3 - a2 * b - a * b2 + b3 - a2 * c - a * b * c + b2 * c
					- a * c2 + b * c2 + c3);
			case 2678 -> p(b - c, 2) * (b + c)
					* (-2 * a3 + a2 * b + b3 + a2 * c - b2 * c - b * c2 + c3)
					* (a3 * b - a2 * b2 - a * b3 + b4 + a3 * c - a2 * b * c
					- a * b2 * c + b3 * c - a2 * c2 - a * b * c2
					+ b2 * c2 - a * c3 + b * c3 + c4);
			case 2679 -> a2 * p(b - c, 2) * p(b + c, 2) * (a2 - b * c) * (a2 + b * c)
					* (-(a2 * b2) + b4 - a2 * c2 + c4);
			case 2680 -> a * p(b - c, 2) * (b + c)
					* (a3 + a * b * c - b2 * c - b * c2) * (-(a2 * b) + b3
					- a2 * c + 2 * a * b * c - b2 * c - b * c2 + c3);
			case 2681 -> p(b - c, 2) * (b + c)
					* (a2 + a * b - b2 + a * c - b * c - c2)
					* (-2 * a3 + a2 * b + b3 + a2 * c - b2 * c - b * c2 + c3);
			case 2682 -> p(b - c, 2) * p(b + c, 2) * (2 * a2 - b2 - c2)
					* (-2 * a4 + a2 * b2 + b4 + a2 * c2 - 2 * b2 * c2 + c4);
			case 2683 -> a * (-(a2 * b) + b3 - a2 * c + 2 * a * b * c - b2 * c
					- b * c2 + c3)
					* (-(a2 * b2) + b4 - a2 * c2 + c4)
					* (a5 * b - a3 * b3 + a5 * c - 2 * a4 * b * c + a2 * b3 * c
					+ a * b4 * c - b5 * c - a * b3 * c2 - a3 * c3
					+ a2 * b * c3 - a * b2 * c3 + 2 * b3 * c3
					+ a * b * c4 - b * c5);
			case 2684 -> (-2 * a3 + a2 * b
					+ b3 + a2
					* c
					- b2 * c - b * c2 + c3)
					* (-(a2 * b2) + b4 - a2 * c2 + c4)
					* (2 * a5 - a4 * b - a3 * b2 + a * b4 - b5 - a4 * c
					+ a2 * b2 * c - a3 * c2 + a2 * b * c2
					- 2 * a * b2 * c2 + b3 * c2 + b2 * c3 + a * c4
					- c5);
			case 2685 -> (-2 * a3 + a2 * b + b3 + a2 * c - b2 * c - b * c2 + c3)
					* (-2 * a4 + a2 * b2 + b4 + a2 * c2 - 2 * b2 * c2 + c4)
					* (a5 * b2 - a4 * b3 - 2 * a3 * b4 + 2 * a2 * b5 + a * b6
					- b7 + a5 * c2 + 2 * a3 * b2 * c2 - a2 * b3 * c2
					- a * b4 * c2 - b5 * c2 - a4 * c3 - a2 * b2 * c3
					+ 2 * b4 * c3 - 2 * a3 * c4 - a * b2 * c4
					+ 2 * b3 * c4 + 2 * a2 * c5 - b2 * c5 + a * c6
					- c7);
			case 2686 -> p(b - c, 2) * p(b + c, 2) * (-5 * a2 + b2 + c2)
					* (-2 * a4 + a2 * b2 + b4 + a2 * c2 - 2 * b2 * c2 + c4)
					* (a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2 + 9 * a2 * b2 * c2
					- 4 * b4 * c2 - a2 * c4 - 4 * b2 * c4 + c6);
			case 2687 -> a * (-a6 + a4 * b2 + a2 * b4 - b6 + a5 * c - 2 * a4 * b * c
					+ a3 * b2 * c + a2 * b3 * c - 2 * a * b4 * c + b5 * c
					+ 2 * a4 * c2 - 2 * a2 * b2 * c2 + 2 * b4 * c2 - 2 * a3 * c3
					+ a2 * b * c3 + a * b2 * c3 - 2 * b3 * c3 - a2 * c4
					- b2 * c4 + a * c5 + b * c5)
					* (-a6 + a5 * b + 2 * a4 * b2 - 2 * a3 * b3 - a2 * b4
					+ a * b5 - 2 * a4 * b * c + a2 * b3 * c + b5 * c
					+ a4 * c2 + a3 * b * c2 - 2 * a2 * b2 * c2
					+ a * b3 * c2 - b4 * c2 + a2 * b * c3 - 2 * b3 * c3
					+ a2 * c4 - 2 * a * b * c4 + 2 * b2 * c4 + b * c5
					- c6);
			case 2688 -> (-a7 - a5 * b2 + 2 * a4 * b3 + 2 * a3 * b4 - a2 * b5 - b7
					+ a6 * c - a4 * b2 * c - a2 * b4 * c + b6 * c + 2 * a5 * c2
					- a3 * b2 * c2 - a2 * b3 * c2 + 2 * b5 * c2 - 2 * a4 * c3
					+ 2 * a2 * b2 * c3 - 2 * b4 * c3 - a3 * c4 - b3 * c4
					+ a2 * c5 + b2 * c5)
					* (-a7 + a6 * b + 2 * a5 * b2 - 2 * a4 * b3 - a3 * b4
					+ a2 * b5 - a5 * c2 - a4 * b * c2 - a3 * b2 * c2
					+ 2 * a2 * b3 * c2 + b5 * c2 + 2 * a4 * c3
					- a2 * b2 * c3 - b4 * c3 + 2 * a3 * c4 - a2 * b * c4
					- 2 * b3 * c4 - a2 * c5 + 2 * b2 * c5 + b * c6
					- c7);
			case 2689 -> (a
					- b)
					* (-a
					+ c)
					* (a5 + b5 + a3 * b * c + a2 * b2 * c + a * b3 * c
					- 2 * a3 * c2 - a2 * b * c2
					- a * b2 * c2 - 2 * b3
					* c2
					- a * b * c3 + a * c4 + b * c4)
					* (a5 - 2 * a3 * b2 + a * b4 + a3 * b * c - a2 * b2 * c
					- a * b3 * c + b4 * c + a2 * b * c2 - a * b2 * c2
					+ a * b * c3 - 2 * b2 * c3 + c5);
			case 2690 -> (a - b) * (-a + c)
					* (a4 + a3 * b + a2 * b2 + a * b3 + b4 - a3 * c - a2 * b * c
					- a * b2 * c - b3 * c - a2 * c2 - a * b * c2
					- b2 * c2 + a * c3 + b * c3)
					* (a4 - a3 * b - a2 * b2 + a * b3 + a3 * c - a2 * b * c
					- a * b2 * c + b3 * c + a2 * c2 - a * b * c2
					- b2 * c2 + a * c3 - b * c3 + c4);
			case 2691 -> a * (a
					- b)
					* (-a
					+ c)
					* (a5 - a4 * b - a * b4 + b5 - a4 * c - a3 * b * c
					+ 4 * a2 * b2 * c - a * b3 * c - b4 * c
					- 2 * a2 * b * c2
					+ 4 * a * b2
					* c2
					- a * b * c3 - a * c4 - b * c4 + c5)
					* (a5 - a4 * b - a * b4 + b5 - a4 * c - a3 * b * c
					- 2 * a2 * b2 * c - a * b3 * c - b4 * c
					+ 4 * a2 * b * c2 + 4 * a * b2 * c2 - a * b * c3
					- a * c4 - b * c4 + c5);
			case 2692 -> (a - b) * (-a + c)
					* (a5 - 2 * a4 * b - 2 * a3 * b2 - 2 * a2 * b3 - 2 * a * b4
					+ b5 + 3 * a3 * b * c + 3 * a2 * b2 * c
					+ 3 * a * b3 * c - 2 * a3 * c2 + a2 * b * c2
					+ a * b2 * c2 - 2 * b3 * c2 - 3 * a * b * c3
					+ a * c4 + b * c4)
					* (a5 - 2 * a3 * b2 + a * b4 - 2 * a4 * c + 3 * a3 * b * c
					+ a2 * b2 * c - 3 * a * b3 * c + b4 * c
					- 2 * a3 * c2 + 3 * a2 * b * c2 + a * b2 * c2
					- 2 * a2 * c3 + 3 * a * b * c3 - 2 * b2 * c3
					- 2 * a * c4 + c5);
			case 2693 -> a2
					* (-a10 - a8 * b2 + 7 * a6 * b4 - 5 * a4 * b6 - 2 * a2 * b8
					+ 2 * b10 + 3 * a8 * c2 - 6 * a6 * b2 * c2
					- 7 * a4 * b4 * c2 + 12 * a2 * b6 * c2 - 2 * b8 * c2
					- 2 * a6 * c4 + 14 * a4 * b2 * c4 - 7 * a2 * b4 * c4
					- 5 * b6 * c4 - 2 * a4 * c6 - 6 * a2 * b2 * c6
					+ 7 * b4 * c6 + 3 * a2 * c8 - b2 * c8 - c10)
					* (-a10 + 3 * a8 * b2 - 2 * a6 * b4 - 2 * a4 * b6
					+ 3 * a2 * b8 - b10 - a8 * c2 - 6 * a6 * b2 * c2
					+ 14 * a4 * b4 * c2 - 6 * a2 * b6 * c2 - b8 * c2
					+ 7 * a6 * c4 - 7 * a4 * b2 * c4 - 7 * a2 * b4 * c4
					+ 7 * b6 * c4 - 5 * a4 * c6 + 12 * a2 * b2 * c6
					- 5 * b4 * c6 - 2 * a2 * c8 - 2 * b2 * c8
					+ 2 * c10);
			case 2694 -> a * (-a9 + a8 * b + 2 * a7 * b2 - 2 * a6 * b3 - 2 * a3 * b6
					+ 2 * a2 * b7 + a * b8 - b9 - 3 * a7 * b * c
					+ 3 * a5 * b3 * c + 3 * a3 * b5 * c - 3 * a * b7 * c
					+ 2 * a7 * c2 + a6 * b * c2 - 7 * a5 * b2 * c2
					+ 4 * a4 * b3 * c2 + 4 * a3 * b4 * c2 - 7 * a2 * b5 * c2
					+ a * b6 * c2 + 2 * b7 * c2 + 4 * a5 * b * c3
					- 8 * a3 * b3 * c3 + 4 * a * b5 * c3 - 4 * a4 * b * c4
					+ 4 * a3 * b2 * c4 + 4 * a2 * b3 * c4 - 4 * a * b4 * c4
					+ a3 * b * c5 + a * b3 * c5 - 2 * a3 * c6 + a2 * b * c6
					+ a * b2 * c6 - 2 * b3 * c6 - 2 * a * b * c7 + a * c8
					+ b * c8)
					* (-a9 + 2 * a7 * b2 - 2 * a3 * b6 + a * b8 + a8 * c
					- 3 * a7 * b * c + a6 * b2 * c + 4 * a5 * b3 * c
					- 4 * a4 * b4 * c + a3 * b5 * c + a2 * b6 * c
					- 2 * a * b7 * c + b8 * c + 2 * a7 * c2
					- 7 * a5 * b2 * c2 + 4 * a3 * b4 * c2 + a * b6 * c2
					- 2 * a6 * c3 + 3 * a5 * b * c3 + 4 * a4 * b2 * c3
					- 8 * a3 * b3 * c3 + 4 * a2 * b4 * c3 + a * b5 * c3
					- 2 * b6 * c3 + 4 * a3 * b2 * c4 - 4 * a * b4 * c4
					+ 3 * a3 * b * c5 - 7 * a2 * b2 * c5
					+ 4 * a * b3 * c5 - 2 * a3 * c6 + a * b2 * c6
					+ 2 * a2 * c7 - 3 * a * b * c7 + 2 * b2 * c7
					+ a * c8 - c9);
			case 2695 -> (-a8 + a7 * b - a6 * b2 - a5 * b3 + 4 * a4 * b4 - a3 * b5
					- a2 * b6 + a * b7 - b8 - a6 * b * c + 2 * a5 * b2 * c
					- a4 * b3 * c - a3 * b4 * c + 2 * a2 * b5 * c - a * b6 * c
					+ 3 * a6 * c2 - 2 * a5 * b * c2 - 2 * a4 * b2 * c2
					+ 2 * a3 * b3 * c2 - 2 * a2 * b4 * c2 - 2 * a * b5 * c2
					+ 3 * b6 * c2 + 2 * a4 * b * c3 - a3 * b2 * c3
					- a2 * b3 * c3 + 2 * a * b4 * c3 - 3 * a4 * c4 + a3 * b * c4
					+ 2 * a2 * b2 * c4 + a * b3 * c4 - 3 * b4 * c4 - a2 * b * c5
					- a * b2 * c5 + a2 * c6 + b2 * c6)
					* (-a8 + 3 * a6 * b2 - 3 * a4 * b4 + a2 * b6 + a7 * c
					- a6 * b * c - 2 * a5 * b2 * c + 2 * a4 * b3 * c
					+ a3 * b4 * c - a2 * b5 * c - a6 * c2
					+ 2 * a5 * b * c2 - 2 * a4 * b2 * c2 - a3 * b3 * c2
					+ 2 * a2 * b4 * c2 - a * b5 * c2 + b6 * c2 - a5 * c3
					- a4 * b * c3 + 2 * a3 * b2 * c3 - a2 * b3 * c3
					+ a * b4 * c3 + 4 * a4 * c4 - a3 * b * c4
					- 2 * a2 * b2 * c4 + 2 * a * b3 * c4 - 3 * b4 * c4
					- a3 * c5 + 2 * a2 * b * c5 - 2 * a * b2 * c5
					- a2 * c6 - a * b * c6 + 3 * b2 * c6 + a * c7 - c8);
			case 2696 -> (a - b) * (a + b) * (-a + c) * (a + c)
					* (a6 - a4 * b2 - a2 * b4 + b6 - 4 * a4 * c2
					+ 9 * a2 * b2 * c2 - b4 * c2 - 4 * a2 * c4 - b2 * c4
					+ c6)
					* (a6 - 4 * a4 * b2 - 4 * a2 * b4 + b6 - a4 * c2
					+ 9 * a2 * b2 * c2 - b4 * c2 - a2 * c4 - b2 * c4
					+ c6);
			case 2697 -> (-a10 + a6 * b4 + a4 * b6 - b10 + 2 * a8 * c2
					- 2 * a6 * b2 * c2 - 2 * a2 * b6 * c2 + 2 * b8 * c2
					+ a4 * b2 * c4 + a2 * b4 * c4 - 2 * a4 * c6 - 2 * b4 * c6
					+ a2 * c8 + b2 * c8)
					* (-a10 + 2 * a8 * b2 - 2 * a4 * b6 + a2 * b8
					- 2 * a6 * b2 * c2 + a4 * b4 * c2 + b8 * c2
					+ a6 * c4 + a2 * b4 * c4 - 2 * b6 * c4 + a4 * c6
					- 2 * a2 * b2 * c6 + 2 * b2 * c8 - c10);
			case 2698 -> a2
					* (-(a4 * b4) + a2 * b6 - a6 * c2 + a4 * b2 * c2
					- 2 * a2 * b4 * c2 + b6 * c2 + 2 * a4 * c4
					+ a2 * b2 * c4 - b4 * c4 - a2 * c6)
					* (-(a6 * b2) + 2 * a4 * b4 - a2 * b6 + a4 * b2 * c2
					+ a2 * b4 * c2 - a4 * c4 - 2 * a2 * b2 * c4
					- b4 * c4 + a2 * c6 + b2 * c6);
			case 2699 -> a2
					* (-(a3 * b3) + a * b5 - a5 * c + a4 * b * c + a3 * b2 * c
					- 2 * a * b4 * c + b5 * c - a3 * b * c2
					+ 2 * a3 * c3 - a2 * b * c3 + a * b2 * c3 - b3 * c3
					+ a * b * c4 - a * c5)
					* (-(a5 * b) + 2 * a3 * b3 - a * b5 + a4 * b * c
					- a3 * b2 * c - a2 * b3 * c + a * b4 * c
					+ a3 * b * c2 + a * b3 * c2 - a3 * c3 - b3 * c3
					- 2 * a * b * c4 + a * c5 + b * c5);
			default -> Double.NaN;
		};
	}

	private double weight2700to2749(int k, double a, double b, double c) {
		return switch (k) {
			case 2700 -> a2
					* (-a5 + a4 * b - a2 * b3 - a * b4 + 2 * b5 + a2 * b2 * c
					- b4 * c + a3 * c2
					- 2 * a2 * b * c2 + a * b2
					* c2
					- b3 * c2 + a2 * c3 + b * c4 - c5)
					* (-a5 + a3 * b2 + a2 * b3 - b5 + a4 * c - 2 * a2 * b2 * c
					+ b4 * c + a2 * b * c2 + a * b2 * c2 - a2 * c3
					- b2 * c3 - a * c4 - b * c4 + 2 * c5);
			case 2701 -> a2 * (a - b) * (-a + c)
					* (a3 - 2 * a * b2 + b3 + a * b * c - 2 * b2 * c + c3)
					* (a3 + b3 + a * b * c - 2 * a * c2 - 2 * b * c2 + c3);
			case 2702 -> a2 * (a - b) * (-a + c)
					* (-a2 + a * b + b2 - a * c + b * c - c2)
					* (-a2 - a * b - b2 + a * c + b * c + c2);
			case 2703 -> a2 * (a - b) * (-a + c) * (b3 - a2 * c + a * b * c - a * c2)
					* (-(a2 * b) - a * b2 + a * b * c + c3);
			case 2704 -> a2 * (a - b) * (-a + c)
					* (a2 * b3 - 2 * a * b4 + b5 - a4 * c + a3 * b * c
					- a2 * b2 * c + a * b3 * c - 2 * b4 * c + a3 * c2
					- a * b2 * c2 + b3 * c2 + a2 * c3 + a * b * c3
					- a * c4)
					* (-(a4 * b) + a3 * b2 + a2 * b3 - a * b4 + a3 * b * c
					+ a * b3 * c - a2 * b * c2 - a * b2 * c2 + a2 * c3
					+ a * b * c3 + b2 * c3 - 2 * a * c4 - 2 * b * c4
					+ c5);
			case 2705 -> a2 * (a - b) * (-a + c)
					* (a3 - 2 * a * b2 + 3 * b3 - 2 * a2 * c + 3 * a * b * c
					- 2 * b2 * c - 2 * a * c2 + c3)
					* (a3 - 2 * a2 * b - 2 * a * b2 + b3 + 3 * a * b * c
					- 2 * a * c2 - 2 * b * c2 + 3 * c3);
			case 2706 -> a2
					* (-(a8 * b4) + 3 * a6 * b6 - 3 * a4 * b8 + a2 * b10
					- a10 * c2 - a8 * b2 * c2 + a6 * b4 * c2
					- 2 * a4 * b6 * c2 + 2 * a2 * b8 * c2 + b10 * c2
					+ 4 * a8 * c4 + a6 * b2 * c4 - 2 * a2 * b6 * c4
					- 3 * b8 * c4 - 6 * a6 * c6 + a4 * b2 * c6
					+ a2 * b4 * c6 + 3 * b6 * c6 + 4 * a4 * c8
					- a2 * b2 * c8 - b4 * c8 - a2 * c10)
					* (-(a10 * b2) + 4 * a8 * b4 - 6 * a6 * b6 + 4 * a4 * b8
					- a2 * b10 - a8 * b2 * c2 + a6 * b4 * c2
					+ a4 * b6 * c2 - a2 * b8 * c2 - a8 * c4
					+ a6 * b2 * c4 + a2 * b6 * c4 - b8 * c4
					+ 3 * a6 * c6 - 2 * a4 * b2 * c6 - 2 * a2 * b4 * c6
					+ 3 * b6 * c6 - 3 * a4 * c8 + 2 * a2 * b2 * c8
					- 3 * b4 * c8 + a2 * c10 + b2 * c10);
			case 2707 -> a2 * (-(a6 * b3) + a5 * b4 + 2 * a4 * b5 - 2 * a3 * b6
					- a2 * b7 + a * b8 - a8 * c + a6 * b2 * c - 2 * a4 * b4 * c
					+ a2 * b6 * c + b8 * c + a7 * c2 - 2 * a6 * b * c2
					+ a5 * b2 * c2 + a4 * b3 * c2 + a3 * b4 * c2
					- 2 * a2 * b5 * c2 + a * b6 * c2 - b7 * c2 + 3 * a6 * c3
					- 2 * a4 * b2 * c3 + a2 * b4 * c3 - 2 * b6 * c3
					- 3 * a5 * c4 + 4 * a4 * b * c4 - 2 * a3 * b2 * c4
					+ a2 * b3 * c4 - 2 * a * b4 * c4 + 2 * b5 * c4 - 3 * a4 * c5
					+ a2 * b2 * c5 + b4 * c5 + 3 * a3 * c6 - 2 * a2 * b * c6
					+ a * b2 * c6 - b3 * c6 + a2 * c7 - a * c8)
					* (-(a8 * b) + a7 * b2 + 3 * a6 * b3 - 3 * a5 * b4
					- 3 * a4 * b5 + 3 * a3 * b6 + a2 * b7 - a * b8
					- 2 * a6 * b2 * c + 4 * a4 * b4 * c
					- 2 * a2 * b6 * c + a6 * b * c2 + a5 * b2 * c2
					- 2 * a4 * b3 * c2 - 2 * a3 * b4 * c2 + a2 * b5 * c2
					+ a * b6 * c2 - a6 * c3 + a4 * b2 * c3
					+ a2 * b4 * c3 - b6 * c3 + a5 * c4 - 2 * a4 * b * c4
					+ a3 * b2 * c4 + a2 * b3 * c4 - 2 * a * b4 * c4
					+ b5 * c4 + 2 * a4 * c5 - 2 * a2 * b2 * c5
					+ 2 * b4 * c5 - 2 * a3 * c6 + a2 * b * c6
					+ a * b2 * c6 - 2 * b3 * c6 - a2 * c7 - b2 * c7
					+ a * c8 + b * c8);
			case 2708 -> a2
					* (-a6 + a4 * b2 + a3 * b3 - 2 * a2 * b4 - a * b5 + 2 * b6
					+ a5 * c - a4 * b * c - a3 * b2 * c + 2 * a * b4 * c
					- b5 * c + a4 * c2 + a3 * b * c2 - 2 * b4 * c2
					- 2 * a3 * c3 + a2 * b * c3 - a * b2 * c3 + b3 * c3
					+ a2 * c4 - a * b * c4 + b2 * c4 + a * c5 - c6)
					* (-a6 + a5 * b + a4 * b2 - 2 * a3 * b3 + a2 * b4 + a * b5
					- b6 - a4 * b * c + a3 * b2 * c + a2 * b3 * c
					- a * b4 * c + a4 * c2 - a3 * b * c2 - a * b3 * c2
					+ b4 * c2 + a3 * c3 + b3 * c3 - 2 * a2 * c4
					+ 2 * a * b * c4 - 2 * b2 * c4 - a * c5 - b * c5
					+ 2 * c6);
			case 2709 -> a2 * (a - b) * (a + b) * (-a + c) * (a + c)
					* (a4 - a2 * b2 + 4 * b4 - 4 * a2 * c2 - b2 * c2 + c4)
					* (a4 - 4 * a2 * b2 + b4 - a2 * c2 - b2 * c2 + 4 * c4);
			case 2710 -> a2
					* (-a8 + a4 * b4 - 2 * a2 * b6 + 2 * b8 + 2 * a6 * c2
					- 2 * b6 * c2 - 2 * a4 * c4 + b4 * c4 + 2 * a2 * c6
					- c8)
					* (-a8 + 2 * a6 * b2 - 2 * a4 * b4 + 2 * a2 * b6 - b8
					+ a4 * c4 + b4 * c4 - 2 * a2 * c6 - 2 * b2 * c6
					+ 2 * c8);
			case 2711 -> a2
					* (-(a2 * b3) + a * b4 - a4 * c - a2 * b2 * c + b4 * c
					+ a3 * c2 + 2 * a2 * b * c2 - a * b2 * c2 - b3 * c2
					+ a2 * c3 - a * c4)
					* (-(a4 * b) + a3 * b2 + a2 * b3 - a * b4 + 2 * a2 * b2 * c
					- a2 * b * c2 - a * b2 * c2 - a2 * c3 - b2 * c3
					+ a * c4 + b * c4);
			case 2712 -> a2
					* (-a3 - a2 * b - a * b2 + 2 * b3 + 2 * a2 * c - b2 * c
					+ 2 * a * c2 - b * c2 - c3)
					* (-a3 + 2 * a2 * b + 2 * a * b2 - b3 - a2 * c - b2 * c
					- a * c2 - b * c2 + 2 * c3);
			case 2713 -> a2 * (a - b) * (a + b) * (-a + c) * (a + c)
					* (-(a2 * b2) + b4 - a3 * c + a * b2 * c + 2 * a2 * c2
					- b2 * c2 - a * c3)
					* (-(a2 * b2) + b4 + a3 * c - a * b2 * c + 2 * a2 * c2
					- b2 * c2 + a * c3)
					* (a3 * b + 2 * a2 * b2 + a * b3 - a2 * c2 - a * b * c2
					- b2 * c2 + c4)
					* (-(a3 * b) + 2 * a2 * b2 - a * b3 - a2 * c2 + a * b * c2
					- b2 * c2 + c4);
			case 2714 -> a2 * (a - b) * (-a + c)
					* (a3 * b3 - a2 * b4 - a * b5 + b6 - a5 * c + a2 * b3 * c
					+ a * b4 * c - b5 * c - 2 * a3 * b * c2
					+ 2 * a2 * b2 * c2 + a * b3 * c2 - b4 * c2
					+ 2 * a3 * c3 - 2 * a2 * b * c3 + b3 * c3 - a * c5)
					* (-(a5 * b) + 2 * a3 * b3 - a * b5 - 2 * a3 * b2 * c
					- 2 * a2 * b3 * c + 2 * a2 * b2 * c2 + a3 * c3
					+ a2 * b * c3 + a * b2 * c3 + b3 * c3 - a2 * c4
					+ a * b * c4 - b2 * c4 - a * c5 - b * c5 + c6);
			case 2715 -> a2 * (a - b) * (a + b) * (-a + c) * (a + c)
					* (-a4 - b4 + a2 * c2 + b2 * c2)
					* (-a4 + a2 * b2 + b2 * c2 - c4);
			case 2716 -> a * (-a6 + a5 * b + a4 * b2 - 2 * a3 * b3 + a2 * b4 + a * b5
					- b6 + a5 * c - 4 * a4 * b * c + 3 * a3 * b2 * c
					+ 3 * a2 * b3 * c - 4 * a * b4 * c + b5 * c + 2 * a4 * c2
					+ a3 * b * c2 - 6 * a2 * b2 * c2 + a * b3 * c2 + 2 * b4 * c2
					- 2 * a3 * c3 + 3 * a2 * b * c3 + 3 * a * b2 * c3
					- 2 * b3 * c3 - a2 * c4 - 2 * a * b * c4 - b2 * c4 + a * c5
					+ b * c5)
					* (-a6 + a5 * b + 2 * a4 * b2 - 2 * a3 * b3 - a2 * b4
					+ a * b5 + a5 * c - 4 * a4 * b * c + a3 * b2 * c
					+ 3 * a2 * b3 * c - 2 * a * b4 * c + b5 * c
					+ a4 * c2 + 3 * a3 * b * c2 - 6 * a2 * b2 * c2
					+ 3 * a * b3 * c2 - b4 * c2 - 2 * a3 * c3
					+ 3 * a2 * b * c3 + a * b2 * c3 - 2 * b3 * c3
					+ a2 * c4 - 4 * a * b * c4 + 2 * b2 * c4 + a * c5
					+ b * c5 - c6);
			case 2717 -> a * (-a5 + a3 * b2 + a2 * b3 - b5 + 2 * a4 * c
					- 2 * a3 * b * c - 2 * a * b3 * c + 2 * b4 * c + a2 * b * c2
					+ a * b2 * c2 - 2 * a2 * c3 - 2 * b2 * c3 + a * c4 + b * c4)
					* (-a5 + 2 * a4 * b - 2 * a2 * b3 + a * b4 - 2 * a3 * b * c
					+ a2 * b2 * c + b4 * c + a3 * c2 + a * b2 * c2
					- 2 * b3 * c2 + a2 * c3 - 2 * a * b * c3
					+ 2 * b * c4 - c5);
			case 2718 -> a * (-a3 + 2 * a2 * b + 2 * a * b2 - b3 - 4 * a * b * c
					+ a * c2 + b * c2)
					* (-a3 + a * b2 + 2 * a2 * c - 4 * a * b * c + b2 * c
					+ 2 * a * c2 - c3);
			case 2719 -> a2 * (a - b) * (-a + c)
					* (a4 * b3 - 2 * a2 * b5 + b7 - a6 * c + a5 * b * c
					- 2 * a4 * b2 * c + 2 * a3 * b3 * c
					+ 3 * a2 * b4 * c - 3 * a * b5 * c - a5 * c2
					- 2 * a3 * b2 * c2 + 2 * a2 * b3 * c2
					+ 3 * a * b4 * c2 - 2 * b5 * c2 + 2 * a4 * c3
					- 2 * a3 * b * c3 - 2 * a2 * b2 * c3
					+ 2 * a * b3 * c3 + 2 * a3 * c4 - 2 * a * b2 * c4
					+ b3 * c4 - a2 * c5 + a * b * c5 - a * c6)
					* (-(a6 * b) - a5 * b2 + 2 * a4 * b3 + 2 * a3 * b4 - a2 * b5
					- a * b6 + a5 * b * c - 2 * a3 * b3 * c + a * b5 * c
					- 2 * a4 * b * c2 - 2 * a3 * b2 * c2
					- 2 * a2 * b3 * c2 - 2 * a * b4 * c2 + a4 * c3
					+ 2 * a3 * b * c3 + 2 * a2 * b2 * c3
					+ 2 * a * b3 * c3 + b4 * c3 + 3 * a2 * b * c4
					+ 3 * a * b2 * c4 - 2 * a2 * c5 - 3 * a * b * c5
					- 2 * b2 * c5 + c7);
			case 2720 -> a2 * (a - b) * (-a + b - c) * (-a + c) * (-a - b + c)
					* (-a3 + a2 * b + a * b2 - b3 - 2 * a * b * c + a * c2
					+ b * c2)
					* (-a3 + a * b2 + a2 * c - 2 * a * b * c + b2 * c + a * c2
					- c3);
			case 2721 -> a * (-a4 + 4 * a2 * b2 - b4 + a3 * c - 2 * a2 * b * c
					- 2 * a * b2 * c + b3 * c - a2 * c2 - b2 * c2 + a * c3
					+ b * c3)
					* (-a4 + a3 * b - a2 * b2 + a * b3 - 2 * a2 * b * c + b3 * c
					+ 4 * a2 * c2 - 2 * a * b * c2 - b2 * c2 + b * c3
					- c4);
			case 2722 -> a * (a - b) * (-a + c)
					* (a5 + a4 * b + a * b4 + b5 - a4 * c - a3 * b * c
					- a * b3 * c - b4 * c + a * b * c3 - a * c4 - b * c4
					+ c5)
					* (a5 - a4 * b - a * b4 + b5 + a4 * c - a3 * b * c
					+ a * b3 * c - b4 * c - a * b * c3 + a * c4 - b * c4
					+ c5);
			case 2723 -> (-a7 + a6 * b - a5 * b2 + a4 * b3 + a3 * b4 - a2 * b5
					+ a * b6 - b7 + a6 * c - 2 * a5 * b * c + a4 * b2 * c
					+ a2 * b4 * c - 2 * a * b5 * c + b6 * c + 2 * a5 * c2
					- 2 * a3 * b2 * c2 - 2 * a2 * b3 * c2 + 2 * b5 * c2
					- 2 * a4 * c3 + 2 * a3 * b * c3 + 2 * a2 * b2 * c3
					+ 2 * a * b3 * c3 - 2 * b4 * c3 - a3 * c4 - a2 * b * c4
					- a * b2 * c4 - b3 * c4 + a2 * c5 + b2 * c5)
					* (-a7 + a6 * b + 2 * a5 * b2 - 2 * a4 * b3 - a3 * b4
					+ a2 * b5 + a6 * c - 2 * a5 * b * c
					+ 2 * a3 * b3 * c - a2 * b4 * c - a5 * c2
					+ a4 * b * c2 - 2 * a3 * b2 * c2 + 2 * a2 * b3 * c2
					- a * b4 * c2 + b5 * c2 + a4 * c3 - 2 * a2 * b2 * c3
					+ 2 * a * b3 * c3 - b4 * c3 + a3 * c4 + a2 * b * c4
					- 2 * b3 * c4 - a2 * c5 - 2 * a * b * c5
					+ 2 * b2 * c5 + a * c6 + b * c6 - c7);
			case 2724 -> (-a6 - a4 * b2 + 4 * a3 * b3 - a2 * b4 - b6 + 2 * a5 * c
					- 2 * a3 * b2 * c - 2 * a2 * b3 * c + 2 * b5 * c
					+ 2 * a2 * b2 * c2 - 2 * a3 * c3 - 2 * b3 * c3 + a2 * c4
					+ b2 * c4)
					* (-a6 + 2 * a5 * b - 2 * a3 * b3 + a2 * b4 - a4 * c2
					- 2 * a3 * b * c2 + 2 * a2 * b2 * c2 + b4 * c2
					+ 4 * a3 * c3 - 2 * a2 * b * c3 - 2 * b3 * c3
					- a2 * c4 + 2 * b * c5 - c6);
			case 2725 -> a * (-a4 + a3 * b + a * b3 - b4 + a3 * c + b3 * c - a2 * c2
					- 2 * a * b * c2 - b2 * c2 + a * c3 + b * c3)
					* (-a4 + a3 * b - a2 * b2 + a * b3 + a3 * c - 2 * a * b2 * c
					+ b3 * c - b2 * c2 + a * c3 + b * c3 - c4);
			case 2726 -> (-a4 + 2 * a3 * b + 2 * a * b3 - b4 - 2 * a2 * b * c
					- 2 * a * b2 * c + a2 * c2 + b2 * c2)
					* (-a4 + a2 * b2 + 2 * a3 * c - 2 * a2 * b * c
					- 2 * a * b * c2 + b2 * c2 + 2 * a * c3 - c4);
			case 2727 -> a2 * (a - b) * (-a + c)
					* (-a6 + a5 * b - a4 * b2 + 2 * a3 * b3 + a2 * b4
					- 3 * a * b5 + b6 - a5 * c + a4 * b * c
					- 2 * a3 * b2 * c + 2 * a2 * b3 * c + 3 * a * b4 * c
					- 3 * b5 * c + a4 * c2 - 2 * a3 * b * c2
					- 2 * a2 * b2 * c2 + 2 * a * b3 * c2 + b4 * c2
					+ 2 * a3 * c3 - 2 * a2 * b * c3 - 2 * a * b2 * c3
					+ 2 * b3 * c3 + a2 * c4 + a * b * c4 - b2 * c4
					- a * c5 + b * c5 - c6)
					* (-a6 - a5 * b + a4 * b2 + 2 * a3 * b3 + a2 * b4 - a * b5
					- b6 + a5 * c + a4 * b * c - 2 * a3 * b2 * c
					- 2 * a2 * b3 * c + a * b4 * c + b5 * c - a4 * c2
					- 2 * a3 * b * c2 - 2 * a2 * b2 * c2
					- 2 * a * b3 * c2 - b4 * c2 + 2 * a3 * c3
					+ 2 * a2 * b * c3 + 2 * a * b2 * c3 + 2 * b3 * c3
					+ a2 * c4 + 3 * a * b * c4 + b2 * c4 - 3 * a * c5
					- 3 * b * c5 + c6);
			case 2728 -> a * (a
					- b)
					* (-a
					+ c)
					* (a5 - a4 * b - a * b4 + b5 + a3 * b * c - 3 * a2 * b2 * c
					+ 3 * a * b3 * c
					- b4 * c - a3 * c2 + 4 * a2 * b * c2 - 3 * a * b2
					* c2
					- a2 * c3 + a * b * c3 - b * c4 + c5)
					* (a5 - a3 * b2 - a2 * b3 + b5 - a4 * c + a3 * b * c
					+ 4 * a2 * b2 * c + a * b3 * c - b4 * c
					- 3 * a2 * b * c2 - 3 * a * b2 * c2 + 3 * a * b * c3
					- a * c4 - b * c4 + c5);
			case 2729 -> (-a5 + 2 * a3 * b2 + 2 * a2 * b3 - b5 + a4 * c
					- 4 * a2 * b2 * c + b4 * c - a3 * c2 - b3 * c2 + a2 * c3
					+ b2 * c3)
					* (-a5 + a4 * b - a3 * b2 + a2 * b3 + 2 * a3 * c2
					- 4 * a2 * b * c2 + b3 * c2 + 2 * a2 * c3 - b2 * c3
					+ b * c4 - c5);
			case 2730 -> a * (a
					- b)
					* (-a
					+ c)
					* (a5 - a4 * b - a * b4 + b5 - 2 * a4 * c + a3 * b * c
					+ 3 * a2 * b2 * c - a * b3 * c - b4 * c + a3 * c2
					- 4 * a2 * b * c2
					+ 3 * a * b2 * c2 + a2
					* c3
					+ a * b * c3 - 2 * a * c4 - b * c4 + c5)
					* (a5 - 2 * a4 * b + a3 * b2 + a2 * b3 - 2 * a * b4 + b5
					- a4 * c + a3 * b * c - 4 * a2 * b2 * c + a * b3 * c
					- b4 * c + 3 * a2 * b * c2 + 3 * a * b2 * c2
					- a * b * c3 - a * c4 - b * c4 + c5);
			case 2731 -> (a - b) * (-a + c)
					* (a5 - 3 * a4 * b - 3 * a * b4 + b5 + 4 * a3 * b * c
					- 2 * a2 * b2 * c + 4 * a * b3 * c - 2 * a3 * c2
					+ 2 * a2 * b * c2 + 2 * a * b2 * c2 - 2 * b3 * c2
					- 4 * a * b * c3 + a * c4 + b * c4)
					* (a5 - 2 * a3 * b2 + a * b4 - 3 * a4 * c + 4 * a3 * b * c
					+ 2 * a2 * b2 * c - 4 * a * b3 * c + b4 * c
					- 2 * a2 * b * c2 + 2 * a * b2 * c2 + 4 * a * b * c3
					- 2 * b2 * c3 - 3 * a * c4 + c5);
			case 2732 -> a2 * (-a10 - a8 * b2 + a7 * b3 + 7 * a6 * b4 - 3 * a5 * b5
					- 5 * a4 * b6 + 3 * a3 * b7 - 2 * a2 * b8 - a * b9 + 2 * b10
					+ a9 * c - a8 * b * c + a7 * b2 * c - 2 * a6 * b3 * c
					- 3 * a5 * b4 * c + 6 * a4 * b5 * c - a3 * b6 * c
					- 2 * a2 * b7 * c + 2 * a * b8 * c - b9 * c + 3 * a8 * c2
					+ a7 * b * c2 - 6 * a6 * b2 * c2 + 4 * a5 * b3 * c2
					- 7 * a4 * b4 * c2 - 3 * a3 * b5 * c2 + 12 * a2 * b6 * c2
					- 2 * a * b7 * c2 - 2 * b8 * c2 - 4 * a7 * c3
					+ 3 * a6 * b * c3 - a5 * b2 * c3 - 3 * a4 * b3 * c3
					+ 6 * a3 * b4 * c3 - 3 * a2 * b5 * c3 - a * b6 * c3
					+ 3 * b7 * c3 - 2 * a6 * c4 - 3 * a5 * b * c4
					+ 14 * a4 * b2 * c4 - 3 * a3 * b3 * c4 - 7 * a2 * b4 * c4
					+ 6 * a * b5 * c4 - 5 * b6 * c4 + 6 * a5 * c5
					- 3 * a4 * b * c5 - a3 * b2 * c5 + 4 * a2 * b3 * c5
					- 3 * a * b4 * c5 - 3 * b5 * c5 - 2 * a4 * c6
					+ 3 * a3 * b * c6 - 6 * a2 * b2 * c6 - 2 * a * b3 * c6
					+ 7 * b4 * c6 - 4 * a3 * c7 + a2 * b * c7 + a * b2 * c7
					+ b3 * c7 + 3 * a2 * c8 - a * b * c8 - b2 * c8 + a * c9
					- c10)
					* (-a10 + a9 * b + 3 * a8 * b2 - 4 * a7 * b3 - 2 * a6 * b4
					+ 6 * a5 * b5 - 2 * a4 * b6 - 4 * a3 * b7
					+ 3 * a2 * b8 + a * b9 - b10 - a8 * b * c
					+ a7 * b2 * c + 3 * a6 * b3 * c - 3 * a5 * b4 * c
					- 3 * a4 * b5 * c + 3 * a3 * b6 * c + a2 * b7 * c
					- a * b8 * c - a8 * c2 + a7 * b * c2
					- 6 * a6 * b2 * c2 - a5 * b3 * c2
					+ 14 * a4 * b4 * c2 - a3 * b5 * c2
					- 6 * a2 * b6 * c2 + a * b7 * c2 - b8 * c2 + a7 * c3
					- 2 * a6 * b * c3 + 4 * a5 * b2 * c3
					- 3 * a4 * b3 * c3 - 3 * a3 * b4 * c3
					+ 4 * a2 * b5 * c3 - 2 * a * b6 * c3 + b7 * c3
					+ 7 * a6 * c4 - 3 * a5 * b * c4 - 7 * a4 * b2 * c4
					+ 6 * a3 * b3 * c4 - 7 * a2 * b4 * c4
					- 3 * a * b5 * c4 + 7 * b6 * c4 - 3 * a5 * c5
					+ 6 * a4 * b * c5 - 3 * a3 * b2 * c5
					- 3 * a2 * b3 * c5 + 6 * a * b4 * c5 - 3 * b5 * c5
					- 5 * a4 * c6 - a3 * b * c6 + 12 * a2 * b2 * c6
					- a * b3 * c6 - 5 * b4 * c6 + 3 * a3 * c7
					- 2 * a2 * b * c7 - 2 * a * b2 * c7 + 3 * b3 * c7
					- 2 * a2 * c8 + 2 * a * b * c8 - 2 * b2 * c8
					- a * c9 - b * c9 + 2 * c10);
			case 2733 -> a * (-a9 + 2 * a8 * b + a7 * b2 - 5 * a6 * b3 + 3 * a5 * b4
					+ 3 * a4 * b5 - 5 * a3 * b6 + a2 * b7 + 2 * a * b8 - b9
					- 4 * a7 * b * c + 4 * a6 * b2 * c + 4 * a5 * b3 * c
					- 8 * a4 * b4 * c + 4 * a3 * b5 * c + 4 * a2 * b6 * c
					- 4 * a * b7 * c + 2 * a7 * c2 + a6 * b * c2
					- 11 * a5 * b2 * c2 + 8 * a4 * b3 * c2 + 8 * a3 * b4 * c2
					- 11 * a2 * b5 * c2 + a * b6 * c2 + 2 * b7 * c2
					+ 4 * a5 * b * c3 + 4 * a4 * b2 * c3 - 16 * a3 * b3 * c3
					+ 4 * a2 * b4 * c3 + 4 * a * b5 * c3 - 7 * a4 * b * c4
					+ 7 * a3 * b2 * c4 + 7 * a2 * b3 * c4 - 7 * a * b4 * c4
					+ 4 * a3 * b * c5 - 8 * a2 * b2 * c5 + 4 * a * b3 * c5
					- 2 * a3 * c6 + 3 * a2 * b * c6 + 3 * a * b2 * c6
					- 2 * b3 * c6 - 4 * a * b * c7 + a * c8 + b * c8)
					* (-a9 + 2 * a7 * b2 - 2 * a3 * b6 + a * b8 + 2 * a8 * c
					- 4 * a7 * b * c + a6 * b2 * c + 4 * a5 * b3 * c
					- 7 * a4 * b4 * c + 4 * a3 * b5 * c
					+ 3 * a2 * b6 * c - 4 * a * b7 * c + b8 * c
					+ a7 * c2 + 4 * a6 * b * c2 - 11 * a5 * b2 * c2
					+ 4 * a4 * b3 * c2 + 7 * a3 * b4 * c2
					- 8 * a2 * b5 * c2 + 3 * a * b6 * c2 - 5 * a6 * c3
					+ 4 * a5 * b * c3 + 8 * a4 * b2 * c3
					- 16 * a3 * b3 * c3 + 7 * a2 * b4 * c3
					+ 4 * a * b5 * c3 - 2 * b6 * c3 + 3 * a5 * c4
					- 8 * a4 * b * c4 + 8 * a3 * b2 * c4
					+ 4 * a2 * b3 * c4 - 7 * a * b4 * c4 + 3 * a4 * c5
					+ 4 * a3 * b * c5 - 11 * a2 * b2 * c5
					+ 4 * a * b3 * c5 - 5 * a3 * c6 + 4 * a2 * b * c6
					+ a * b2 * c6 + a2 * c7 - 4 * a * b * c7
					+ 2 * b2 * c7 + 2 * a * c8 - c9);
			case 2734 -> (-a8 + 2 * a7 * b - 2 * a6 * b2 - 2 * a5 * b3 + 6 * a4 * b4
					- 2 * a3 * b5 - 2 * a2 * b6 + 2 * a * b7 - b8
					- 2 * a6 * b * c + 6 * a5 * b2 * c - 4 * a4 * b3 * c
					- 4 * a3 * b4 * c + 6 * a2 * b5 * c - 2 * a * b6 * c
					+ 3 * a6 * c2 - 4 * a5 * b * c2 - 3 * a4 * b2 * c2
					+ 8 * a3 * b3 * c2 - 3 * a2 * b4 * c2 - 4 * a * b5 * c2
					+ 3 * b6 * c2 + 4 * a4 * b * c3 - 4 * a3 * b2 * c3
					- 4 * a2 * b3 * c3 + 4 * a * b4 * c3 - 3 * a4 * c4
					+ 2 * a3 * b * c4 + 4 * a2 * b2 * c4 + 2 * a * b3 * c4
					- 3 * b4 * c4 - 2 * a2 * b * c5 - 2 * a * b2 * c5 + a2 * c6
					+ b2 * c6)
					* (-a8 + 3 * a6 * b2 - 3 * a4 * b4 + a2 * b6 + 2 * a7 * c
					- 2 * a6 * b * c - 4 * a5 * b2 * c + 4 * a4 * b3 * c
					+ 2 * a3 * b4 * c - 2 * a2 * b5 * c - 2 * a6 * c2
					+ 6 * a5 * b * c2 - 3 * a4 * b2 * c2
					- 4 * a3 * b3 * c2 + 4 * a2 * b4 * c2
					- 2 * a * b5 * c2 + b6 * c2 - 2 * a5 * c3
					- 4 * a4 * b * c3 + 8 * a3 * b2 * c3
					- 4 * a2 * b3 * c3 + 2 * a * b4 * c3 + 6 * a4 * c4
					- 4 * a3 * b * c4 - 3 * a2 * b2 * c4
					+ 4 * a * b3 * c4 - 3 * b4 * c4 - 2 * a3 * c5
					+ 6 * a2 * b * c5 - 4 * a * b2 * c5 - 2 * a2 * c6
					- 2 * a * b * c6 + 3 * b2 * c6 + 2 * a * c7 - c8);
			case 2735 -> (a
					- b)
					* (-a
					+ c)
					* (a7 - 5 * a5 * b2 - 5 * a2 * b5 + b7 + a5 * b * c
					+ a4 * b2 * c - 4 * a3 * b3 * c + a2 * b4 * c
					+ a * b5 * c - a5 * c2 - a4 * b * c2
					+ 9 * a3 * b2 * c2 + 9 * a2 * b3 * c2 - a * b4 * c2
					- b5 * c2 - 5 * a2 * b2 * c3
					- a3 * c4 - b3
					* c4
					- a * b * c5 + a * c6 + b * c6)
					* (a7 - a5 * b2 - a3 * b4 + a * b6 + a5 * b * c
					- a4 * b2 * c - a * b5 * c + b6 * c - 5 * a5 * c2
					+ a4 * b * c2 + 9 * a3 * b2 * c2 - 5 * a2 * b3 * c2
					- 4 * a3 * b * c3 + 9 * a2 * b2 * c3 - b4 * c3
					+ a2 * b * c4 - a * b2 * c4 - 5 * a2 * c5
					+ a * b * c5 - b2 * c5 + c7);
			case 2736 -> a * (a - b) * (-a + c)
					* (a4 - a3 * b - a * b3 + b4 - 2 * a3 * c - 2 * b3 * c
					+ 2 * a2 * c2 + 3 * a * b * c2 + 2 * b2 * c2
					- 2 * a * c3 - 2 * b * c3 + c4)
					* (a4 - 2 * a3 * b + 2 * a2 * b2 - 2 * a * b3 + b4 - a3 * c
					+ 3 * a * b2 * c - 2 * b3 * c + 2 * b2 * c2 - a * c3
					- 2 * b * c3 + c4);
			case 2737 -> (a - b) * (-a + c)
					* (a4 - 2 * a3 * b - 2 * a2 * b2 - 2 * a * b3 + b4 - a3 * c
					+ 5 * a2 * b * c + 5 * a * b2 * c - b3 * c - a2 * c2
					- 4 * a * b * c2 - b2 * c2 + a * c3 + b * c3)
					* (a4 - a3 * b - a2 * b2 + a * b3 - 2 * a3 * c
					+ 5 * a2 * b * c - 4 * a * b2 * c + b3 * c
					- 2 * a2 * c2 + 5 * a * b * c2 - b2 * c2
					- 2 * a * c3 - b * c3 + c4);
			case 2738 -> a2
					* (-a9 + a8 * b - 2 * a7 * b2 + a6 * b3 + 6 * a5 * b4
					- 3 * a4 * b5 - 2 * a3 * b6 - a2 * b7 - a * b8
					+ 2 * b9 + a6 * b2 * c - 3 * a4 * b4 * c
					+ 3 * a2 * b6 * c - b8 * c + 3 * a7 * c2
					- 4 * a6 * b * c2 - 3 * a5 * b2 * c2 - a4 * b3 * c2
					- 3 * a3 * b4 * c2 + 6 * a2 * b5 * c2
					+ 3 * a * b6 * c2 - b7 * c2 + a6 * c3
					+ 4 * a4 * b2 * c3 - 3 * a2 * b4 * c3 - 2 * b6 * c3
					- 3 * a5 * c4 + 6 * a4 * b * c4 + 4 * a3 * b2 * c4
					- a2 * b3 * c4 - 3 * a * b4 * c4 - 3 * b5 * c4
					- 3 * a4 * c5 - 3 * a2 * b2 * c5 + 6 * b4 * c5
					+ a3 * c6 - 4 * a2 * b * c6 + a * b2 * c6 + b3 * c6
					+ 3 * a2 * c7 - 2 * b2 * c7 + b * c8 - c9)
					* (-a9 + 3 * a7 * b2 + a6 * b3 - 3 * a5 * b4 - 3 * a4 * b5
					+ a3 * b6 + 3 * a2 * b7 - b9 + a8 * c
					- 4 * a6 * b2 * c + 6 * a4 * b4 * c
					- 4 * a2 * b6 * c + b8 * c - 2 * a7 * c2
					+ a6 * b * c2 - 3 * a5 * b2 * c2 + 4 * a4 * b3 * c2
					+ 4 * a3 * b4 * c2 - 3 * a2 * b5 * c2 + a * b6 * c2
					- 2 * b7 * c2 + a6 * c3 - a4 * b2 * c3
					- a2 * b4 * c3 + b6 * c3 + 6 * a5 * c4
					- 3 * a4 * b * c4 - 3 * a3 * b2 * c4
					- 3 * a2 * b3 * c4 - 3 * a * b4 * c4 + 6 * b5 * c4
					- 3 * a4 * c5 + 6 * a2 * b2 * c5 - 3 * b4 * c5
					- 2 * a3 * c6 + 3 * a2 * b * c6 + 3 * a * b2 * c6
					- 2 * b3 * c6 - a2 * c7 - b2 * c7 - a * c8 - b * c8
					+ 2 * c9);
			case 2739 -> a * (-a8 + a7 * b + 2 * a6 * b2 - a5 * b3 - 2 * a4 * b4
					- a3 * b5 + 2 * a2 * b6 + a * b7 - b8 + a7 * c
					- 4 * a6 * b * c - 2 * a5 * b2 * c + 5 * a4 * b3 * c
					+ 5 * a3 * b4 * c - 2 * a2 * b5 * c - 4 * a * b6 * c
					+ b7 * c + a6 * c2 + 4 * a5 * b * c2 - 5 * a4 * b2 * c2
					- 5 * a2 * b4 * c2 + 4 * a * b5 * c2 + b6 * c2 - a5 * c3
					+ a4 * b * c3 + a * b4 * c3 - b5 * c3 + a4 * c4
					- 3 * a3 * b * c4 + 4 * a2 * b2 * c4 - 3 * a * b3 * c4
					+ b4 * c4 - a3 * c5 + 2 * a2 * b * c5 + 2 * a * b2 * c5
					- b3 * c5 - a2 * c6 - 2 * a * b * c6 - b2 * c6 + a * c7
					+ b * c7)
					* (-a8 + a7 * b + a6 * b2 - a5 * b3 + a4 * b4 - a3 * b5
					- a2 * b6 + a * b7 + a7 * c - 4 * a6 * b * c
					+ 4 * a5 * b2 * c + a4 * b3 * c - 3 * a3 * b4 * c
					+ 2 * a2 * b5 * c - 2 * a * b6 * c + b7 * c
					+ 2 * a6 * c2 - 2 * a5 * b * c2 - 5 * a4 * b2 * c2
					+ 4 * a2 * b4 * c2 + 2 * a * b5 * c2 - b6 * c2
					- a5 * c3 + 5 * a4 * b * c3 - 3 * a * b4 * c3
					- b5 * c3 - 2 * a4 * c4 + 5 * a3 * b * c4
					- 5 * a2 * b2 * c4 + a * b3 * c4 + b4 * c4 - a3 * c5
					- 2 * a2 * b * c5 + 4 * a * b2 * c5 - b3 * c5
					+ 2 * a2 * c6 - 4 * a * b * c6 + b2 * c6 + a * c7
					+ b * c7 - c8);
			case 2740 -> (a - b) * (-a + c)
					* (a6 + a5 * b - 4 * a4 * b2 - 4 * a3 * b3 - 4 * a2 * b4
					+ a * b5 + b6 - a5 * c - a4 * b * c
					+ 4 * a3 * b2 * c + 4 * a2 * b3 * c - a * b4 * c
					- b5 * c + 5 * a2 * b2 * c2 - a2 * c4 - a * b * c4
					- b2 * c4 + a * c5 + b * c5)
					* (a6 - a5 * b - a2 * b4 + a * b5 + a5 * c - a4 * b * c
					- a * b4 * c + b5 * c - 4 * a4 * c2
					+ 4 * a3 * b * c2 + 5 * a2 * b2 * c2 - b4 * c2
					- 4 * a3 * c3 + 4 * a2 * b * c3 - 4 * a2 * c4
					- a * b * c4 + a * c5 - b * c5 + c6);
			case 2741 -> (-a9 + 2 * a6 * b3 - a5 * b4 - a4 * b5 + 2 * a3 * b6 - b9
					+ a8 * c - 2 * a6 * b2 * c + 2 * a4 * b4 * c
					- 2 * a2 * b6 * c + b8 * c + a7 * c2 - a4 * b3 * c2
					- a3 * b4 * c2 + b7 * c2 - a6 * c3 + a4 * b2 * c3
					+ a2 * b4 * c3 - b6 * c3 + a5 * c4 + b5 * c4 - a4 * c5
					- b4 * c5 - a3 * c6 - b3 * c6 + a2 * c7 + b2 * c7)
					* (-a9 + a8 * b + a7 * b2 - a6 * b3 + a5 * b4 - a4 * b5
					- a3 * b6 + a2 * b7 - 2 * a6 * b * c2 + a4 * b3 * c2
					+ b7 * c2 + 2 * a6 * c3 - a4 * b2 * c3 - b6 * c3
					- a5 * c4 + 2 * a4 * b * c4 - a3 * b2 * c4
					+ a2 * b3 * c4 - b5 * c4 - a4 * c5 + b4 * c5
					+ 2 * a3 * c6 - 2 * a2 * b * c6 - b3 * c6 + b2 * c7
					+ b * c8 - c9);
			case 2742 -> a2 * (a - b) * (-a + c)
					* (a3 - a2 * b - a * b2 + b3 - 2 * a2 * c + 2 * a * b * c
					- 2 * b2 * c + a * c2 + b * c2)
					* (a3 - 2 * a2 * b + a * b2 - a2 * c + 2 * a * b * c
					+ b2 * c - a * c2 - 2 * b * c2 + c3);
			case 2743 -> a * (a - b) * (-a + c)
					* (a3 - a2 * b - a * b2 + b3 - 2 * a2 * c + 5 * a * b * c
					- b2 * c - 2 * a * c2 - b * c2 + c3)
					* (a3 - 2 * a2 * b - 2 * a * b2 + b3 - a2 * c
					+ 5 * a * b * c - b2 * c - a * c2 - b * c2 + c3);
			case 2744 -> a2
					* (-(a7 * b3) + 3 * a5 * b5 - 3 * a3 * b7 + a * b9 - a9 * c
					+ a8 * b * c - a7 * b2 * c + 2 * a6 * b3 * c
					+ 3 * a5 * b4 * c - 6 * a4 * b5 * c + a3 * b6 * c
					+ 2 * a2 * b7 * c - 2 * a * b8 * c + b9 * c
					- a7 * b * c2 - 4 * a5 * b3 * c2 + 3 * a3 * b5 * c2
					+ 2 * a * b7 * c2 + 4 * a7 * c3 - 3 * a6 * b * c3
					+ a5 * b2 * c3 + 3 * a4 * b3 * c3 - 6 * a3 * b4 * c3
					+ 3 * a2 * b5 * c3 + a * b6 * c3 - 3 * b7 * c3
					+ 3 * a5 * b * c4 + 3 * a3 * b3 * c4
					- 6 * a * b5 * c4 - 6 * a5 * c5 + 3 * a4 * b * c5
					+ a3 * b2 * c5 - 4 * a2 * b3 * c5 + 3 * a * b4 * c5
					+ 3 * b5 * c5 - 3 * a3 * b * c6 + 2 * a * b3 * c6
					+ 4 * a3 * c7 - a2 * b * c7 - a * b2 * c7 - b3 * c7
					+ a * b * c8 - a * c9)
					* (-(a9 * b) + 4 * a7 * b3 - 6 * a5 * b5 + 4 * a3 * b7
					- a * b9 + a8 * b * c - a7 * b2 * c
					- 3 * a6 * b3 * c + 3 * a5 * b4 * c
					+ 3 * a4 * b5 * c - 3 * a3 * b6 * c - a2 * b7 * c
					+ a * b8 * c - a7 * b * c2 + a5 * b3 * c2
					+ a3 * b5 * c2 - a * b7 * c2 - a7 * c3
					+ 2 * a6 * b * c3 - 4 * a5 * b2 * c3
					+ 3 * a4 * b3 * c3 + 3 * a3 * b4 * c3
					- 4 * a2 * b5 * c3 + 2 * a * b6 * c3 - b7 * c3
					+ 3 * a5 * b * c4 - 6 * a3 * b3 * c4
					+ 3 * a * b5 * c4 + 3 * a5 * c5 - 6 * a4 * b * c5
					+ 3 * a3 * b2 * c5 + 3 * a2 * b3 * c5
					- 6 * a * b4 * c5 + 3 * b5 * c5 + a3 * b * c6
					+ a * b3 * c6 - 3 * a3 * c7 + 2 * a2 * b * c7
					+ 2 * a * b2 * c7 - 3 * b3 * c7 - 2 * a * b * c8
					+ a * c9 + b * c9);
			case 2745 -> a2
					* (-a7 + a6 * b + 3 * a3 * b4 - 3 * a2 * b5 - 2 * a * b6
					+ 2 * b7 + a6 * c - 4 * a5 * b * c + 4 * a4 * b2 * c
					- 4 * a3 * b3 * c - 3 * a2 * b4 * c + 8 * a * b5 * c
					- 2 * b6 * c + 3 * a5 * c2 - a4 * b * c2
					- 4 * a3 * b2 * c2 + 8 * a2 * b3 * c2
					- 3 * a * b4 * c2 - 3 * b5 * c2 - 3 * a4 * c3
					+ 8 * a3 * b * c3 - 4 * a2 * b2 * c3
					- 4 * a * b3 * c3 + 3 * b4 * c3 - 3 * a3 * c4
					- a2 * b * c4 + 4 * a * b2 * c4 + 3 * a2 * c5
					- 4 * a * b * c5 + a * c6 + b * c6 - c7)
					* (-a7 + a6 * b + 3 * a5 * b2 - 3 * a4 * b3 - 3 * a3 * b4
					+ 3 * a2 * b5 + a * b6 - b7 + a6 * c
					- 4 * a5 * b * c - a4 * b2 * c + 8 * a3 * b3 * c
					- a2 * b4 * c - 4 * a * b5 * c + b6 * c
					+ 4 * a4 * b * c2 - 4 * a3 * b2 * c2
					- 4 * a2 * b3 * c2 + 4 * a * b4 * c2
					- 4 * a3 * b * c3 + 8 * a2 * b2 * c3
					- 4 * a * b3 * c3 + 3 * a3 * c4 - 3 * a2 * b * c4
					- 3 * a * b2 * c4 + 3 * b3 * c4 - 3 * a2 * c5
					+ 8 * a * b * c5 - 3 * b2 * c5 - 2 * a * c6
					- 2 * b * c6 + 2 * c7);
			case 2746 -> a * (a
					- b)
					* (-a
					+ c)
					* (a5 + a4 * b - 4 * a3 * b2 - 4 * a2 * b3 + a * b4 + b5
					- a4 * c - a3 * b * c + 4 * a2 * b2 * c
					- a * b3 * c - b4
					* c
					+ 5 * a * b * c3 - a * c4 - b * c4 + c5)
					* (a5 - a4 * b - a * b4 + b5 + a4 * c - a3 * b * c
					+ 5 * a * b3 * c - b4 * c - 4 * a3 * c2
					+ 4 * a2 * b * c2 - 4 * a2 * c3 - a * b * c3
					+ a * c4 - b * c4 + c5);
			case 2747 -> a * (-a8 + 2 * a6 * b2 - 2 * a4 * b4 + 2 * a2 * b6 - b8
					+ a7 * c - 2 * a6 * b * c + a4 * b3 * c + a3 * b4 * c
					- 2 * a * b6 * c + b7 * c + a6 * c2 - a4 * b2 * c2
					- a2 * b4 * c2 + b6 * c2 - a5 * c3 + a4 * b * c3
					+ a * b4 * c3 - b5 * c3 + a4 * c4 + b4 * c4 - a3 * c5
					- b3 * c5 - a2 * c6 - b2 * c6 + a * c7 + b * c7)
					* (-a8 + a7 * b + a6 * b2 - a5 * b3 + a4 * b4 - a3 * b5
					- a2 * b6 + a * b7 - 2 * a6 * b * c + a4 * b3 * c
					+ b7 * c + 2 * a6 * c2 - a4 * b2 * c2 - b6 * c2
					+ a4 * b * c3 - b5 * c3 - 2 * a4 * c4 + a3 * b * c4
					- a2 * b2 * c4 + a * b3 * c4 + b4 * c4 - b3 * c5
					+ 2 * a2 * c6 - 2 * a * b * c6 + b2 * c6 + b * c7
					- c8);
			case 2748 -> a * (a - b) * (-a + c) * (a2 - 3 * a * b + b2 + c2)
					* (a2 + b2 - 3 * a * c + c2);
			case 2749 -> a2
					* (-(a6 * b3) + a5 * b4 + 2 * a4 * b5 - 2 * a3 * b6
					- a2 * b7 + a * b8 - a8 * c - 3 * a6 * b2 * c
					+ 2 * a4 * b4 * c + a2 * b6 * c + b8 * c + a7 * c2
					+ 2 * a6 * b * c2 + a5 * b2 * c2 + a4 * b3 * c2
					- 3 * a3 * b4 * c2 - 2 * a2 * b5 * c2 + a * b6 * c2
					- b7 * c2 + 3 * a6 * c3 + 2 * a4 * b2 * c3
					- 3 * a2 * b4 * c3 - 2 * b6 * c3 - 3 * a5 * c4
					- 4 * a4 * b * c4 + 2 * a3 * b2 * c4 + a2 * b3 * c4
					+ 2 * a * b4 * c4 + 2 * b5 * c4 - 3 * a4 * c5
					+ a2 * b2 * c5 + b4 * c5 + 3 * a3 * c6
					+ 2 * a2 * b * c6 - 3 * a * b2 * c6 - b3 * c6
					+ a2 * c7 - a * c8)
					* (-(a8 * b) + a7 * b2 + 3 * a6 * b3 - 3 * a5 * b4
					- 3 * a4 * b5 + 3 * a3 * b6 + a2 * b7 - a * b8
					+ 2 * a6 * b2 * c - 4 * a4 * b4 * c
					+ 2 * a2 * b6 * c - 3 * a6 * b * c2 + a5 * b2 * c2
					+ 2 * a4 * b3 * c2 + 2 * a3 * b4 * c2 + a2 * b5 * c2
					- 3 * a * b6 * c2 - a6 * c3 + a4 * b2 * c3
					+ a2 * b4 * c3 - b6 * c3 + a5 * c4 + 2 * a4 * b * c4
					- 3 * a3 * b2 * c4 - 3 * a2 * b3 * c4
					+ 2 * a * b4 * c4 + b5 * c4 + 2 * a4 * c5
					- 2 * a2 * b2 * c5 + 2 * b4 * c5 - 2 * a3 * c6
					+ a2 * b * c6 + a * b2 * c6 - 2 * b3 * c6 - a2 * c7
					- b2 * c7 + a * c8 + b * c8);
			default -> Double.NaN;
		};
	}

	private double weight2750to2799(int k, double a, double b, double c) {
		return switch (k) {
			case 2750 -> a2
					* (-a6 - 2 * a4 * b2 + 2 * a3 * b3 + a2 * b4 - 2 * a * b5
					+ 2 * b6 + 2 * a5 * c + 2 * a3 * b2 * c
					- 2 * a2 * b3 * c - 2 * b5 * c + a4 * c2
					- 2 * a * b3 * c2 + b4 * c2 - 4 * a3 * c3
					+ 2 * a * b2 * c3 + 2 * b3 * c3 + a2 * c4
					- 2 * b2 * c4 + 2 * a * c5 - c6)
					* (-a6 + 2 * a5 * b + a4 * b2 - 4 * a3 * b3 + a2 * b4
					+ 2 * a * b5 - b6 - 2 * a4 * c2 + 2 * a3 * b * c2
					+ 2 * a * b3 * c2 - 2 * b4 * c2 + 2 * a3 * c3
					- 2 * a2 * b * c3 - 2 * a * b2 * c3 + 2 * b3 * c3
					+ a2 * c4 + b2 * c4 - 2 * a * c5 - 2 * b * c5
					+ 2 * c6);
			case 2751 -> a * (-a5 + 2 * a4 * b - a3 * b2 - a2 * b3 + 2 * a * b4 - b5
					+ a2 * b * c2 + a * b2 * c2 - 4 * a * b * c3 + a * c4
					+ b * c4)
					* (-a5 + a * b4 + 2 * a4 * c + a2 * b2 * c - 4 * a * b3 * c
					+ b4 * c - a3 * c2 + a * b2 * c2 - a2 * c3
					+ 2 * a * c4 - c5);
			case 2752 -> a * (-a5 + a4 * b + a * b4 - b5 + a3 * b * c + a * b3 * c
					- a2 * b * c2 - a * b2 * c2 - 2 * a * b * c3 + a * c4
					+ b * c4)
					* (-a5 + a * b4 + a4 * c + a3 * b * c - a2 * b2 * c
					- 2 * a * b3 * c + b4 * c - a * b2 * c2 + a * b * c3
					+ a * c4 - c5);
			case 2753 -> a * (a - b) * (-a + c)
					* (a4 + 2 * a2 * b2 + b4 - 3 * a2 * b * c + 3 * a * b2 * c
					- 4 * a2 * c2 - 3 * a * b * c2 + 2 * b2 * c2 + c4)
					* (a4 - 4 * a2 * b2 + b4 - 3 * a2 * b * c - 3 * a * b2 * c
					+ 2 * a2 * c2 + 3 * a * b * c2 + 2 * b2 * c2 + c4);
			case 2754 -> a * (-a7 + a6 * b + a5 * b2 - a4 * b3 - a3 * b4 + a2 * b5
					+ a * b6 - b7 + a5 * b * c - 2 * a3 * b3 * c + a * b5 * c
					- a5 * c2 + a3 * b2 * c2 + a2 * b3 * c2 - b5 * c2
					+ a3 * b * c3 + a * b3 * c3 + a3 * c4 - 2 * a2 * b * c4
					- 2 * a * b2 * c4 + b3 * c4 - 2 * a * b * c5 + a * c6
					+ b * c6)
					* (-a7 - a5 * b2 + a3 * b4 + a * b6 + a6 * c + a5 * b * c
					+ a3 * b3 * c - 2 * a2 * b4 * c - 2 * a * b5 * c
					+ b6 * c + a5 * c2 + a3 * b2 * c2 - 2 * a * b4 * c2
					- a4 * c3 - 2 * a3 * b * c3 + a2 * b2 * c3
					+ a * b3 * c3 + b4 * c3 - a3 * c4 + a2 * c5
					+ a * b * c5 - b2 * c5 + a * c6 - c7);
			case 2755 -> a2
					* (-a7 - a6 * b - 3 * a5 * b2 + 5 * a3 * b4 - a2 * b5
					- a * b6 + 2 * b7 + 2 * a6 * c + 3 * a4 * b2 * c
					- 4 * a2 * b4 * c - b6 * c + 4 * a5 * c2
					+ a4 * b * c2 - 4 * a * b4 * c2 - b5 * c2
					- 5 * a4 * c3 + 5 * b4 * c3 - 5 * a3 * c4
					+ a2 * b * c4 + 3 * a * b2 * c4 + 4 * a2 * c5
					- 3 * b2 * c5 + 2 * a * c6 - b * c6 - c7)
					* (-a7 + 2 * a6 * b + 4 * a5 * b2 - 5 * a4 * b3
					- 5 * a3 * b4 + 4 * a2 * b5 + 2 * a * b6 - b7
					- a6 * c + a4 * b2 * c + a2 * b4 * c - b6 * c
					- 3 * a5 * c2 + 3 * a4 * b * c2 + 3 * a * b4 * c2
					- 3 * b5 * c2 + 5 * a3 * c4 - 4 * a2 * b * c4
					- 4 * a * b2 * c4 + 5 * b3 * c4 - a2 * c5 - b2 * c5
					- a * c6 - b * c6 + 2 * c7);
			case 2756 -> a * (-a6 + 3 * a5 * b + a4 * b2 - 6 * a3 * b3 + a2 * b4
					+ 3 * a * b5 - b6 - a5 * c - 2 * a4 * b * c
					+ 3 * a3 * b2 * c + 3 * a2 * b3 * c - 2 * a * b4 * c
					- b5 * c + 3 * a3 * b * c2 - 6 * a2 * b2 * c2
					+ 3 * a * b3 * c2 + a2 * b * c3 + a * b2 * c3 + a2 * c4
					- 6 * a * b * c4 + b2 * c4 + a * c5 + b * c5)
					* (-a6 - a5 * b + a2 * b4 + a * b5 + 3 * a5 * c
					- 2 * a4 * b * c + 3 * a3 * b2 * c + a2 * b3 * c
					- 6 * a * b4 * c + b5 * c + a4 * c2
					+ 3 * a3 * b * c2 - 6 * a2 * b2 * c2 + a * b3 * c2
					+ b4 * c2 - 6 * a3 * c3 + 3 * a2 * b * c3
					+ 3 * a * b2 * c3 + a2 * c4 - 2 * a * b * c4
					+ 3 * a * c5 - b * c5 - c6);
			case 2757 -> (-a5 + 3 * a4 * b - 2 * a3 * b2 - 2 * a2 * b3 + 3 * a * b4
					- b5 - a4 * c + 4 * a2 * b2 * c - b4 * c + a3 * c2
					- 3 * a2 * b * c2 - 3 * a * b2 * c2 + b3 * c2 + a2 * c3
					+ b2 * c3)
					* (-a5 - a4 * b + a3 * b2 + a2 * b3 + 3 * a4 * c
					- 3 * a2 * b2 * c - 2 * a3 * c2 + 4 * a2 * b * c2
					- 3 * a * b2 * c2 + b3 * c2 - 2 * a2 * c3 + b2 * c3
					+ 3 * a * c4 - b * c4 - c5);
			case 2758 -> (-a5 + 2 * a4 * b + 2 * a * b4 - b5 - a4 * c - b4 * c
					+ a3 * c2 - 2 * a2 * b * c2 - 2 * a * b2 * c2 + b3 * c2
					+ a2 * c3 + b2 * c3)
					* (-a5 - a4 * b + a3 * b2 + a2 * b3 + 2 * a4 * c
					- 2 * a2 * b2 * c - 2 * a * b2 * c2 + b3 * c2
					+ b2 * c3 + 2 * a * c4 - b * c4 - c5);
			case 2759 -> (a - b) * (-a + c)
					* (a4 - a3 * b - 7 * a2 * b2 - a * b3 + b4 + a3 * c
					+ a2 * b * c + a * b2 * c + b3 * c + a2 * c2
					- a * b * c2 + b2 * c2 + a * c3 + b * c3)
					* (a4 + a3 * b + a2 * b2 + a * b3 - a3 * c + a2 * b * c
					- a * b2 * c + b3 * c - 7 * a2 * c2 + a * b * c2
					+ b2 * c2 - a * c3 + b * c3 + c4);
			case 2760 -> (-a7 + 2 * a6 * b + a5 * b2 - 2 * a4 * b3 - 2 * a3 * b4
					+ a2 * b5 + 2 * a * b6 - b7 - a6 * c + a4 * b2 * c
					+ a2 * b4 * c - b6 * c + a3 * b2 * c2 + a2 * b3 * c2
					- 2 * a2 * b2 * c3 + a3 * c4
					- 2 * a2 * b
					* c4
					- 2 * a * b2 * c4 + b3 * c4 + a2 * c5 + b2 * c5)
					* (-a7 - a6 * b + a3 * b4 + a2 * b5 + 2 * a6 * c
					- 2 * a2 * b4 * c + a5 * c2 + a4 * b * c2
					+ a3 * b2 * c2 - 2 * a2 * b3 * c2 - 2 * a * b4 * c2
					+ b5 * c2 - 2 * a4 * c3 + a2 * b2 * c3 + b4 * c3
					- 2 * a3 * c4 + a2 * b * c4 + a2 * c5 + 2 * a * c6
					- b * c6 - c7);
			case 2761 -> a2 * (a - b) * (-a + c) * (a7 * b3 - a6 * b4 - 3 * a5 * b5
					+ 3 * a4 * b6 + 3 * a3 * b7 - 3 * a2 * b8 - a * b9 + b10
					- a9 * c - 2 * a7 * b2 * c + a6 * b3 * c + 4 * a5 * b4 * c
					- 3 * a4 * b5 * c + 2 * a3 * b6 * c + 3 * a2 * b7 * c
					- 3 * a * b8 * c - b9 * c - 2 * a7 * b * c2
					+ 2 * a6 * b2 * c2 - a5 * b3 * c2 + a4 * b4 * c2
					+ 3 * a * b7 * c2 - 3 * b8 * c2 + 4 * a7 * c3
					- 2 * a6 * b * c3 + 2 * a5 * b2 * c3 - a4 * b3 * c3
					- 8 * a3 * b4 * c3 + 2 * a * b6 * c3 + 3 * b7 * c3
					+ 4 * a5 * b * c4 - 4 * a4 * b2 * c4 - a3 * b3 * c4
					+ a2 * b4 * c4 - 3 * a * b5 * c4 + 3 * b6 * c4 - 6 * a5 * c5
					+ 4 * a4 * b * c5 + 2 * a3 * b2 * c5 - a2 * b3 * c5
					+ 4 * a * b4 * c5 - 3 * b5 * c5 - 2 * a3 * b * c6
					+ 2 * a2 * b2 * c6 + a * b3 * c6 - b4 * c6 + 4 * a3 * c7
					- 2 * a2 * b * c7 - 2 * a * b2 * c7 + b3 * c7 - a * c9)
					* (-(a9 * b) + 4 * a7 * b3 - 6 * a5 * b5 + 4 * a3 * b7
					- a * b9 - 2 * a7 * b2 * c - 2 * a6 * b3 * c
					+ 4 * a5 * b4 * c + 4 * a4 * b5 * c
					- 2 * a3 * b6 * c - 2 * a2 * b7 * c
					- 2 * a7 * b * c2 + 2 * a6 * b2 * c2
					+ 2 * a5 * b3 * c2 - 4 * a4 * b4 * c2
					+ 2 * a3 * b5 * c2 + 2 * a2 * b6 * c2
					- 2 * a * b7 * c2 + a7 * c3 + a6 * b * c3
					- a5 * b2 * c3 - a4 * b3 * c3 - a3 * b4 * c3
					- a2 * b5 * c3 + a * b6 * c3 + b7 * c3 - a6 * c4
					+ 4 * a5 * b * c4 + a4 * b2 * c4 - 8 * a3 * b3 * c4
					+ a2 * b4 * c4 + 4 * a * b5 * c4 - b6 * c4
					- 3 * a5 * c5 - 3 * a4 * b * c5 - 3 * a * b4 * c5
					- 3 * b5 * c5 + 3 * a4 * c6 + 2 * a3 * b * c6
					+ 2 * a * b3 * c6 + 3 * b4 * c6 + 3 * a3 * c7
					+ 3 * a2 * b * c7 + 3 * a * b2 * c7 + 3 * b3 * c7
					- 3 * a2 * c8 - 3 * a * b * c8 - 3 * b2 * c8
					- a * c9 - b * c9 + c10);
			case 2762 -> a2 * (a - b) * (-a + c)
					* (a7 + a4 * b3 - 3 * a3 * b4 - 2 * a2 * b5 + 2 * a * b6
					+ b7 + a5 * b * c - 2 * a4 * b2 * c
					+ 2 * a3 * b3 * c - 3 * a * b5 * c + 2 * b6 * c
					- 2 * a5 * c2 + 2 * a3 * b2 * c2 + 2 * a2 * b3 * c2
					- 2 * b5 * c2 + a4 * c3 - 2 * a3 * b * c3
					+ 2 * a2 * b2 * c3 + 2 * a * b3 * c3 - 3 * b4 * c3
					+ a3 * c4 - 2 * a * b2 * c4 + b3 * c4 - 2 * a2 * c5
					+ a * b * c5 + c7)
					* (a7 - 2 * a5 * b2 + a4 * b3 + a3 * b4 - 2 * a2 * b5 + b7
					+ a5 * b * c - 2 * a3 * b3 * c + a * b5 * c
					- 2 * a4 * b * c2 + 2 * a3 * b2 * c2
					+ 2 * a2 * b3 * c2 - 2 * a * b4 * c2 + a4 * c3
					+ 2 * a3 * b * c3 + 2 * a2 * b2 * c3
					+ 2 * a * b3 * c3 + b4 * c3 - 3 * a3 * c4
					- 3 * b3 * c4 - 2 * a2 * c5 - 3 * a * b * c5
					- 2 * b2 * c5 + 2 * a * c6 + 2 * b * c6 + c7);
			case 2763 -> a2
					* (-a8 - 4 * a6 * b2 + 5 * a4 * b4 - 2 * a2 * b6 + 2 * b8
					+ 6 * a6 * c2 + 4 * a4 * b2 * c2 - 8 * a2 * b4 * c2
					- 2 * b6 * c2 - 10 * a4 * c4
					+ 4 * a2 * b2
					* c4
					+ 5 * b4 * c4 + 6 * a2 * c6 - 4 * b2 * c6 - c8)
					* (-a8 + 6 * a6 * b2 - 10 * a4 * b4 + 6 * a2 * b6 - b8
					- 4 * a6 * c2 + 4 * a4 * b2 * c2 + 4 * a2 * b4 * c2
					- 4 * b6 * c2 + 5 * a4 * c4 - 8 * a2 * b2 * c4
					+ 5 * b4 * c4 - 2 * a2 * c6 - 2 * b2 * c6 + 2 * c8);
			case 2764 -> a2 * (a - b) * (a + b) * (-a + c) * (a + c)
					* (a8 + a6 * b2 - a4 * b4 - 5 * a2 * b6 + 4 * b8
					- 2 * a6 * c2 - a4 * b2 * c2 + 8 * a2 * b4 * c2
					- 5 * b6 * c2 + 2 * a4 * c4 - a2 * b2 * c4 - b4 * c4
					- 2 * a2 * c6 + b2 * c6 + c8)
					* (a8 - 2 * a6 * b2 + 2 * a4 * b4 - 2 * a2 * b6 + b8
					+ a6 * c2 - a4 * b2 * c2 - a2 * b4 * c2 + b6 * c2
					- a4 * c4 + 8 * a2 * b2 * c4 - b4 * c4 - 5 * a2 * c6
					- 5 * b2 * c6 + 4 * c8);
			case 2765 -> a * (a - b) * (-a + c)
					* (a6 - a5 * b - a4 * b2 + 2 * a3 * b3 - a2 * b4 - a * b5
					+ b6 + 2 * a4 * b * c - 2 * a3 * b2 * c
					- 2 * a2 * b3 * c + 2 * a * b4 * c - a4 * c2
					+ 6 * a2 * b2 * c2 - b4 * c2 - 2 * a2 * b * c3
					- 2 * a * b2 * c3 - a2 * c4 + a * b * c4 - b2 * c4
					+ c6)
					* (a6 - a4 * b2 - a2 * b4 + b6 - a5 * c + 2 * a4 * b * c
					- 2 * a2 * b3 * c + a * b4 * c - a4 * c2
					- 2 * a3 * b * c2 + 6 * a2 * b2 * c2
					- 2 * a * b3 * c2 - b4 * c2 + 2 * a3 * c3
					- 2 * a2 * b * c3 - a2 * c4 + 2 * a * b * c4
					- b2 * c4 - a * c5 + c6);
			case 2766 -> a * (a - b) * (-a + c) * (-V) * (-U)
					* (-a4 + b4 - a2 * b * c + a * b2 * c + 2 * a2 * c2
					- a * b * c2 - c4)
					* (-a4 + 2 * a2 * b2 - b4 - a2 * b * c - a * b2 * c
					+ a * b * c2 + c4);
			case 2767 -> a * (-a7 + a6 * b + 5 * a5 * b2 - 5 * a4 * b3 - 5 * a3 * b4
					+ 5 * a2 * b5 + a * b6 - b7 - 3 * a5 * b * c
					+ 6 * a3 * b3 * c - 3 * a * b5 * c - a5 * c2
					+ 4 * a4 * b * c2 - 3 * a3 * b2 * c2 - 3 * a2 * b3 * c2
					+ 4 * a * b4 * c2 - b5 * c2 + a3 * b * c3 + a * b3 * c3
					+ a3 * c4 - 2 * a2 * b * c4 - 2 * a * b2 * c4 + b3 * c4
					- 2 * a * b * c5 + a * c6 + b * c6)
					* (-a7 - a5 * b2 + a3 * b4 + a * b6 + a6 * c
					- 3 * a5 * b * c + 4 * a4 * b2 * c + a3 * b3 * c
					- 2 * a2 * b4 * c - 2 * a * b5 * c + b6 * c
					+ 5 * a5 * c2 - 3 * a3 * b2 * c2 - 2 * a * b4 * c2
					- 5 * a4 * c3 + 6 * a3 * b * c3 - 3 * a2 * b2 * c3
					+ a * b3 * c3 + b4 * c3 - 5 * a3 * c4
					+ 4 * a * b2 * c4 + 5 * a2 * c5 - 3 * a * b * c5
					- b2 * c5 + a * c6 - c7);
			case 2768 -> (-a6 + a5 * b + 2 * a4 * b2 - 4 * a3 * b3 + 2 * a2 * b4
					+ a * b5 - b6 - a4 * b * c + 2 * a3 * b2 * c
					+ 2 * a2 * b3 * c - a * b4 * c + a3 * b * c2
					- 4 * a2 * b2 * c2 + a * b3 * c2 - a2 * b * c3 - a * b2 * c3
					+ a2 * c4 + b2 * c4)
					* (-a6 + a2 * b4 + a5 * c - a4 * b * c + a3 * b2 * c
					- a2 * b3 * c + 2 * a4 * c2 + 2 * a3 * b * c2
					- 4 * a2 * b2 * c2 - a * b3 * c2 + b4 * c2
					- 4 * a3 * c3 + 2 * a2 * b * c3 + a * b2 * c3
					+ 2 * a2 * c4 - a * b * c4 + a * c5 - c6);
			case 2769 -> (a
					- b)
					* (-a
					+ c)
					* (a7 - a5 * b2 - a2 * b5 + b7 + a5 * b * c + a4 * b2 * c
					+ a2 * b4 * c + a * b5 * c - a5 * c2 - a4 * b * c2
					+ a3 * b2 * c2 + a2 * b3 * c2 - a * b4 * c2
					- b5 * c2 - a2 * b2 * c3
					- a3 * c4 - b3
					* c4
					- a * b * c5 + a * c6 + b * c6)
					* (a7 - a5 * b2 - a3 * b4 + a * b6 + a5 * b * c
					- a4 * b2 * c - a * b5 * c + b6 * c - a5 * c2
					+ a4 * b * c2 + a3 * b2 * c2 - a2 * b3 * c2
					+ a2 * b2 * c3 - b4 * c3 + a2 * b * c4 - a * b2 * c4
					- a2 * c5 + a * b * c5 - b2 * c5 + c7);
			case 2770 -> (-a6 + 2 * a4 * b2 + 2 * a2 * b4 - b6 - 4 * a2 * b2 * c2
					+ a2 * c4 + b2 * c4)
					* (-a6 + a2 * b4 + 2 * a4 * c2 - 4 * a2 * b2 * c2 + b4 * c2
					+ 2 * a2 * c4 - c6);
			case 2771 -> a * (a5 * b - a4 * b2 - 2 * a3 * b3 + 2 * a2 * b4 + a * b5
					- b6 + a5 * c + a3 * b2 * c - 2 * a * b4 * c - a4 * c2
					+ a3 * b * c2 - 2 * a2 * b2 * c2 + a * b3 * c2 + b4 * c2
					- 2 * a3 * c3 + a * b2 * c3 + 2 * a2 * c4 - 2 * a * b * c4
					+ b2 * c4 + a * c5 - c6);
			case 2772 -> a2 * (a5 * b2 - a4 * b3 - 2 * a3 * b4 + 2 * a2 * b5 + a * b6
					- b7 + a5 * c2 + 2 * a3 * b2 * c2 - a2 * b3 * c2
					- a * b4 * c2 - b5 * c2 - a4 * c3 - a2 * b2 * c3
					+ 2 * b4 * c3 - 2 * a3 * c4 - a * b2 * c4 + 2 * b3 * c4
					+ 2 * a2 * c5 - b2 * c5 + a * c6 - c7);
			case 2773 -> a2 * (b - c)
					* (a4 * b - 2 * a2 * b3 + b5 + a4 * c - a3 * b * c
					- a2 * b2 * c + a * b3 * c - a2 * b * c2
					+ a * b2 * c2 - 2 * a2 * c3 + a * b * c3 + c5);
			case 2774 -> a2 * (b - c)
					* (a3 * b - a2 * b2 - a * b3 + b4 + a3 * c - a2 * b * c
					- a * b2 * c + b3 * c - a2 * c2 - a * b * c2
					+ b2 * c2 - a * c3 + b * c3 + c4);
			case 2775 -> a * (b - c)
					* (a5 - a4 * b - a * b4 + b5 - a4 * c - a3 * b * c
					+ 4 * a2 * b2 * c - a * b3 * c - b4 * c
					+ 4 * a2 * b * c2 - 2 * a * b2 * c2 - a * b * c3
					- a * c4 - b * c4 + c5);
			case 2776 -> a2 * (b - c)
					* (a4 * b - 2 * a2 * b3 + b5 + a4 * c - 3 * a3 * b * c
					+ a2 * b2 * c + 3 * a * b3 * c - 2 * b4 * c
					+ a2 * b * c2 + 3 * a * b2 * c2 - 2 * b3 * c2
					- 2 * a2 * c3 + 3 * a * b * c3 - 2 * b2 * c3
					- 2 * b * c4 + c5);
			case 2777 -> 2 * a10 - 2 * a8 * b2 - 5 * a6 * b4 + 7 * a4 * b6 - a2 * b8
					- b10 - 2 * a8 * c2 + 12 * a6 * b2 * c2 - 7 * a4 * b4 * c2
					- 6 * a2 * b6 * c2 + 3 * b8 * c2 - 5 * a6 * c4
					- 7 * a4 * b2 * c4 + 14 * a2 * b4 * c4 - 2 * b6 * c4
					+ 7 * a4 * c6 - 6 * a2 * b2 * c6 - 2 * b4 * c6 - a2 * c8
					+ 3 * b2 * c8 - c10;
			case 2778 -> a * (a8 * b - 2 * a6 * b3 + 2 * a2 * b7 - b9 + a8 * c
					- 2 * a7 * b * c + a6 * b2 * c + a5 * b3 * c
					- 4 * a4 * b4 * c + 4 * a3 * b5 * c + a2 * b6 * c
					- 3 * a * b7 * c + b8 * c + a6 * b * c2 + 4 * a4 * b3 * c2
					- 7 * a2 * b5 * c2 + 2 * b7 * c2 - 2 * a6 * c3 + a5 * b * c3
					+ 4 * a4 * b2 * c3 - 8 * a3 * b3 * c3 + 4 * a2 * b4 * c3
					+ 3 * a * b5 * c3 - 2 * b6 * c3 - 4 * a4 * b * c4
					+ 4 * a2 * b3 * c4 + 4 * a3 * b * c5 - 7 * a2 * b2 * c5
					+ 3 * a * b3 * c5 + a2 * b * c6 - 2 * b3 * c6 + 2 * a2 * c7
					- 3 * a * b * c7 + 2 * b2 * c7 + b * c8 - c9);
			case 2779 -> a2 * (a6 * b2 - 3 * a4 * b4 + 3 * a2 * b6 - b8 - a5 * b2 * c
					+ a4 * b3 * c + 2 * a3 * b4 * c - 2 * a2 * b5 * c
					- a * b6 * c + b7 * c + a6 * c2 - a5 * b * c2
					+ 2 * a4 * b2 * c2 - a3 * b3 * c2 - 2 * a2 * b4 * c2
					+ 2 * a * b5 * c2 - b6 * c2 + a4 * b * c3 - a3 * b2 * c3
					+ 2 * a2 * b3 * c3 - a * b4 * c3 - b5 * c3 - 3 * a4 * c4
					+ 2 * a3 * b * c4 - 2 * a2 * b2 * c4 - a * b3 * c4
					+ 4 * b4 * c4 - 2 * a2 * b * c5 + 2 * a * b2 * c5 - b3 * c5
					+ 3 * a2 * c6 - a * b * c6 - b2 * c6 + b * c7 - c8);
			case 2780 -> a2 * (b2 - c2)
					* (a6 - a4 * b2 - a2 * b4 + b6 - a4 * c2 + 9 * a2 * b2 * c2
					- 4 * b4 * c2 - a2 * c4 - 4 * b2 * c4 + c6);
			case 2781 -> a2 * (a8 * b2 - 2 * a6 * b4 + 2 * a2 * b8 - b10 + a8 * c2
					+ a4 * b4 * c2 - 2 * a2 * b6 * c2 - 2 * a6 * c4
					+ a4 * b2 * c4 + b6 * c4 - 2 * a2 * b2 * c6 + b4 * c6
					+ 2 * a2 * c8 - c10);
			case 2782 -> a6 * b2 - a4 * b4 + a6 * c2 - 2 * a4 * b2 * c2 + a2 * b4 * c2
					- b6 * c2 - a4 * c4 + a2 * b2 * c4 + 2 * b4 * c4 - b2 * c6;
			case 2783 -> a5 * b - a3 * b3 + a5 * c - 2 * a4 * b * c + a2 * b3 * c
					+ a * b4 * c - b5 * c - a * b3 * c2 - a3 * c3 + a2 * b * c3
					- a * b2 * c3 + 2 * b3 * c3 + a * b * c4 - b * c5;
			case 2784 -> 2 * a5 - a4 * b - a3 * b2 + a * b4 - b5 - a4 * c
					+ a2 * b2 * c - a3 * c2 + a2 * b * c2 - 2 * a * b2 * c2
					+ b3 * c2 + b2 * c3 + a * c4 - c5;
			case 2785 -> (b - c)
					* (a3 - 2 * a2 * b + b3 - 2 * a2 * c + a * b * c + c3);
			case 2786 -> (b - c) * (a2 + a * b - b2 + a * c - b * c - c2);
			case 2787 -> (b - c) * (a3 + a * b * c - b2 * c - b * c2);
			case 2788 -> (b - c) * (a5 - 2 * a4 * b + a3 * b2 - 2 * a4 * c
					+ a3 * b * c - a2 * b2 * c + a * b3 * c - b4 * c + a3 * c2
					- a2 * b * c2 + b3 * c2 + a * b * c3 + b2 * c3 - b * c4);
			case 2789 -> (b - c) * (3 * a3 - 2 * a2 * b + b3 - 2 * a2 * c
					+ 3 * a * b * c - 2 * b2 * c - 2 * b * c2 + c3);
			case 2790 -> a10 * b2 - 3 * a8 * b4 + 3 * a6 * b6 - a4 * b8 + a10 * c2
					+ 2 * a8 * b2 * c2 - 2 * a6 * b4 * c2 + a4 * b6 * c2
					- a2 * b8 * c2 - b10 * c2 - 3 * a8 * c4 - 2 * a6 * b2 * c4
					+ a2 * b6 * c4 + 4 * b8 * c4 + 3 * a6 * c6 + a4 * b2 * c6
					+ a2 * b4 * c6 - 6 * b6 * c6 - a4 * c8 - a2 * b2 * c8
					+ 4 * b4 * c8 - b2 * c10;
			case 2791 -> a8 * b - a7 * b2 - 2 * a6 * b3 + 2 * a5 * b4 + a4 * b5
					- a3 * b6 + a8 * c + a6 * b2 * c - 2 * a4 * b4 * c
					+ a2 * b6 * c - b8 * c - a7 * c2 + a6 * b * c2
					- 2 * a5 * b2 * c2 + a4 * b3 * c2 + a3 * b4 * c2
					+ a2 * b5 * c2 - 2 * a * b6 * c2 + b7 * c2 - 2 * a6 * c3
					+ a4 * b2 * c3 - 2 * a2 * b4 * c3 + 3 * b6 * c3
					+ 2 * a5 * c4 - 2 * a4 * b * c4 + a3 * b2 * c4
					- 2 * a2 * b3 * c4 + 4 * a * b4 * c4 - 3 * b5 * c4 + a4 * c5
					+ a2 * b2 * c5 - 3 * b4 * c5 - a3 * c6 + a2 * b * c6
					- 2 * a * b2 * c6 + 3 * b3 * c6 + b2 * c7 - b * c8;
			case 2792 -> 2 * a6 - a5 * b - 2 * a4 * b2 + a3 * b3 + a2 * b4 - b6
					- a5 * c + 2 * a4 * b * c - a2 * b3 * c - a * b4 * c
					+ b5 * c - 2 * a4 * c2 + a * b3 * c2 + b4 * c2 + a3 * c3
					- a2 * b * c3 + a * b2 * c3 - 2 * b3 * c3 + a2 * c4
					- a * b * c4 + b2 * c4 + b * c5 - c6;
			case 2793 -> (b2 - c2)
					* (4 * a4 - a2 * b2 + b4 - a2 * c2 - 4 * b2 * c2 + c4);
			case 2794 -> 2 * a8 - 2 * a6 * b2 + a4 * b4 - b8 - 2 * a6 * c2
					+ 2 * b6 * c2 + a4 * c4 - 2 * b4 * c4 + 2 * b2 * c6 - c8;
			case 2795 -> a4 * b - a3 * b2 + a4 * c - a2 * b2 * c - b4 * c - a3 * c2
					- a2 * b * c2 + 2 * a * b2 * c2 + b3 * c2 + b2 * c3
					- b * c4;
			case 2796 -> 2 * a3 - a2 * b - a * b2 - b3 - a2 * c + 2 * b2 * c - a * c2
					+ 2 * b * c2 - c3;
			case 2797 -> (b2 - c2)
					* (a4 - a2 * b2 + a2 * b * c - b3 * c - a2 * c2
					+ 2 * b2 * c2 - b * c3)
					* (a4 - a2 * b2 - a2 * b * c + b3 * c - a2 * c2
					+ 2 * b2 * c2 + b * c3);
			case 2798 -> (b - c) * (a6 - a5 * b - a4 * b2 + a3 * b3 - a5 * c
					+ a4 * b * c + a3 * b2 * c - b5 * c - a4 * c2 + a3 * b * c2
					+ 2 * a2 * b2 * c2 - 2 * a * b3 * c2 + a3 * c3
					- 2 * a * b2 * c3 + 2 * b3 * c3 - b * c5);
			case 2799 -> (b2 - c2) * (a2 * b2 - b4 + a2 * c2 - c4);
			default -> Double.NaN;
		};
	}

	private double weight2800to2849(int k, double a, double b, double c) {

		return switch (k) {
			case 2800 -> a * (a5 * b - a4 * b2 - 2 * a3 * b3 + 2 * a2 * b4 + a * b5
					- b6 + a5 * c - 2 * a4 * b * c + 3 * a3 * b2 * c
					+ a2 * b3 * c - 4 * a * b4 * c + b5 * c - a4 * c2
					+ 3 * a3 * b * c2 - 6 * a2 * b2 * c2 + 3 * a * b3 * c2
					+ b4 * c2 - 2 * a3 * c3 + a2 * b * c3 + 3 * a * b2 * c3
					- 2 * b3 * c3 + 2 * a2 * c4 - 4 * a * b * c4 + b2 * c4
					+ a * c5 + b * c5 - c6);
			case 2801 -> a * (a4 * b - 2 * a3 * b2 + 2 * a * b4 - b5 + a4 * c
					+ a2 * b2 * c - 2 * a * b3 * c - 2 * a3 * c2 + a2 * b * c2
					+ b3 * c2 - 2 * a * b * c3 + b2 * c3 + 2 * a * c4 - c5);
			case 2802 -> a * (a2 * b - b3 + a2 * c - 4 * a * b * c + 2 * b2 * c
					+ 2 * b * c2 - c3);
			case 2803 -> (b - c) * (a7 - 2 * a5 * b2 + a3 * b4 - 3 * a5 * b * c
					+ 3 * a4 * b2 * c + 2 * a3 * b3 * c - 2 * a2 * b4 * c
					+ a * b5 * c - b6 * c - 2 * a5 * c2 + 3 * a4 * b * c2
					+ 2 * a3 * b2 * c2 - 2 * a2 * b3 * c2 - b5 * c2
					+ 2 * a3 * b * c3 - 2 * a2 * b2 * c3 - 2 * a * b3 * c3
					+ 2 * b4 * c3 + a3 * c4 - 2 * a2 * b * c4 + 2 * b3 * c4
					+ a * b * c5 - b2 * c5 - b * c6);
			case 2804 -> (a - b - c) * (b - c) * (a2 * b - b3 + a2 * c - 2 * a * b * c
					+ b2 * c + b * c2 - c3);
			case 2805 -> a * (a3 * b - a2 * b2 + a * b3 - b4 + a3 * c - 2 * a * b2 * c
					- a2 * c2 - 2 * a * b * c2 + 4 * b2 * c2 + a * c3 - c4);
			case 2806 -> a * (b - c)
					* (a5 - a4 * b - a * b4 + b5 - a4 * c + a3 * b * c
					- a * b3 * c + b4 * c - a * b * c3 - a * c4 + b * c4
					+ c5);
			case 2807 -> a2 * (a5 * b2 - a4 * b3 - 2 * a3 * b4 + 2 * a2 * b5 + a * b6
					- b7 - a4 * b2 * c + 2 * a3 * b3 * c - 2 * a * b5 * c
					+ b6 * c + a5 * c2 - a4 * b * c2 + 2 * a3 * b2 * c2
					- 2 * a2 * b3 * c2 + a * b4 * c2 - b5 * c2 - a4 * c3
					+ 2 * a3 * b * c3 - 2 * a2 * b2 * c3 + b4 * c3 - 2 * a3 * c4
					+ a * b2 * c4 + b3 * c4 + 2 * a2 * c5 - 2 * a * b * c5
					- b2 * c5 + a * c6 + b * c6 - c7);
			case 2808 -> a2 * (a4 * b2 - 2 * a3 * b3 + 2 * a * b5 - b6 + a4 * c2
					+ 2 * a2 * b2 * c2 - 2 * a * b3 * c2 - b4 * c2 - 2 * a3 * c3
					- 2 * a * b2 * c3 + 4 * b3 * c3 - b2 * c4 + 2 * a * c5
					- c6);
			case 2809 -> a * (a3 * b - a2 * b2 + a * b3 - b4 + a3 * c - 2 * a2 * b * c
					+ b3 * c - a2 * c2 + a * c3 + b * c3 - c4);
			case 2810 -> a2 * (a2 * b2 - b4 - 2 * a * b2 * c + 2 * b3 * c + a2 * c2
					- 2 * a * b * c2 + 2 * b * c3 - c4);
			case 2811 -> (b - c) * (a6 - 3 * a5 * b + a4 * b2 + 2 * a3 * b3 - a2 * b4
					+ a * b5 - b6 - 3 * a5 * c + 3 * a4 * b * c
					+ 2 * a3 * b2 * c - 2 * a2 * b3 * c + a * b4 * c - b5 * c
					+ a4 * c2 + 2 * a3 * b * c2 - 2 * a2 * b2 * c2
					- 2 * a * b3 * c2 + b4 * c2 + 2 * a3 * c3 - 2 * a2 * b * c3
					- 2 * a * b2 * c3 + 2 * b3 * c3 - a2 * c4 + a * b * c4
					+ b2 * c4 + a * c5 - b * c5 - c6);
			case 2812 -> a * (b - c)
					* (a5 - a4 * b - a * b4 + b5 - a4 * c + 3 * a3 * b * c
					- 3 * a2 * b2 * c + a * b3 * c - 3 * a2 * b * c2
					+ 4 * a * b2 * c2 - b3 * c2 + a * b * c3 - b2 * c3
					- a * c4 + c5);
			case 2813 -> a2 * (a3 * b2 - a2 * b3 + a * b4 - b5 + a3 * c2
					- 4 * a * b2 * c2 + 2 * b3 * c2 - a2 * c3 + 2 * b2 * c3
					+ a * c4 - c5);
			case 2814 -> a * (b - c)
					* (a5 - a4 * b - a * b4 + b5 - a4 * c - a3 * b * c
					+ 3 * a2 * b2 * c + a * b3 * c - 2 * b4 * c
					+ 3 * a2 * b * c2 - 4 * a * b2 * c2 + b3 * c2
					+ a * b * c3 + b2 * c3 - a * c4 - 2 * b * c4 + c5);
			case 2815 -> a2 * (b - c)
					* (a4 * b - 2 * a2 * b3 + b5 + a4 * c - 4 * a3 * b * c
					+ 2 * a2 * b2 * c + 4 * a * b3 * c - 3 * b4 * c
					+ 2 * a2 * b * c2 - 2 * a * b2 * c2 - 2 * a2 * c3
					+ 4 * a * b * c3 - 3 * b * c4 + c5);
			case 2816 -> 2 * a10 - a9 * b - 2 * a8 * b2 + 3 * a7 * b3 - 5 * a6 * b4
					- 3 * a5 * b5 + 7 * a4 * b6 + a3 * b7 - a2 * b8 - b10
					- a9 * c + 2 * a8 * b * c - 2 * a7 * b2 * c - a6 * b3 * c
					+ 6 * a5 * b4 * c - 3 * a4 * b5 * c - 2 * a3 * b6 * c
					+ a2 * b7 * c - a * b8 * c + b9 * c - 2 * a8 * c2
					- 2 * a7 * b * c2 + 12 * a6 * b2 * c2 - 3 * a5 * b3 * c2
					- 7 * a4 * b4 * c2 + 4 * a3 * b5 * c2 - 6 * a2 * b6 * c2
					+ a * b7 * c2 + 3 * b8 * c2 + 3 * a7 * c3 - a6 * b * c3
					- 3 * a5 * b2 * c3 + 6 * a4 * b3 * c3 - 3 * a3 * b4 * c3
					- a2 * b5 * c3 + 3 * a * b6 * c3 - 4 * b7 * c3 - 5 * a6 * c4
					+ 6 * a5 * b * c4 - 7 * a4 * b2 * c4 - 3 * a3 * b3 * c4
					+ 14 * a2 * b4 * c4 - 3 * a * b5 * c4 - 2 * b6 * c4
					- 3 * a5 * c5 - 3 * a4 * b * c5 + 4 * a3 * b2 * c5
					- a2 * b3 * c5 - 3 * a * b4 * c5 + 6 * b5 * c5 + 7 * a4 * c6
					- 2 * a3 * b * c6 - 6 * a2 * b2 * c6 + 3 * a * b3 * c6
					- 2 * b4 * c6 + a3 * c7 + a2 * b * c7 + a * b2 * c7
					- 4 * b3 * c7 - a2 * c8 - a * b * c8 + 3 * b2 * c8 + b * c9
					- c10;
			case 2817 -> a * (a8 * b - 2 * a6 * b3 + 2 * a2 * b7 - b9 + a8 * c
					- 4 * a7 * b * c + 3 * a6 * b2 * c + 4 * a5 * b3 * c
					- 7 * a4 * b4 * c + 4 * a3 * b5 * c + a2 * b6 * c
					- 4 * a * b7 * c + 2 * b8 * c + 3 * a6 * b * c2
					- 8 * a5 * b2 * c2 + 7 * a4 * b3 * c2 + 4 * a3 * b4 * c2
					- 11 * a2 * b5 * c2 + 4 * a * b6 * c2 + b7 * c2
					- 2 * a6 * c3 + 4 * a5 * b * c3 + 7 * a4 * b2 * c3
					- 16 * a3 * b3 * c3 + 8 * a2 * b4 * c3 + 4 * a * b5 * c3
					- 5 * b6 * c3 - 7 * a4 * b * c4 + 4 * a3 * b2 * c4
					+ 8 * a2 * b3 * c4 - 8 * a * b4 * c4 + 3 * b5 * c4
					+ 4 * a3 * b * c5 - 11 * a2 * b2 * c5 + 4 * a * b3 * c5
					+ 3 * b4 * c5 + a2 * b * c6 + 4 * a * b2 * c6 - 5 * b3 * c6
					+ 2 * a2 * c7 - 4 * a * b * c7 + b2 * c7 + 2 * b * c8 - c9);
			case 2818 -> a2 * (a6 * b2 - 3 * a4 * b4 + 3 * a2 * b6 - b8
					- 2 * a5 * b2 * c + 2 * a4 * b3 * c + 4 * a3 * b4 * c
					- 4 * a2 * b5 * c - 2 * a * b6 * c + 2 * b7 * c + a6 * c2
					- 2 * a5 * b * c2 + 4 * a4 * b2 * c2 - 4 * a3 * b3 * c2
					- 3 * a2 * b4 * c2 + 6 * a * b5 * c2 - 2 * b6 * c2
					+ 2 * a4 * b * c3 - 4 * a3 * b2 * c3 + 8 * a2 * b3 * c3
					- 4 * a * b4 * c3 - 2 * b5 * c3 - 3 * a4 * c4
					+ 4 * a3 * b * c4 - 3 * a2 * b2 * c4 - 4 * a * b3 * c4
					+ 6 * b4 * c4 - 4 * a2 * b * c5 + 6 * a * b2 * c5
					- 2 * b3 * c5 + 3 * a2 * c6 - 2 * a * b * c6 - 2 * b2 * c6
					+ 2 * b * c7 - c8);
			case 2819 -> a2 * (b - c)
					* (a6 * b - a4 * b3 - a2 * b5 + b7 + a6 * c - a5 * b * c
					- a2 * b4 * c + a * b5 * c - 5 * a3 * b2 * c2
					+ 9 * a2 * b3 * c2 + a * b4 * c2 - 5 * b5 * c2
					- a4 * c3 + 9 * a2 * b2 * c3 - 4 * a * b3 * c3
					- a2 * b * c4 + a * b2 * c4 - a2 * c5 + a * b * c5
					- 5 * b2 * c5 + c7);
			case 2820 -> a * (b - c)
					* (a4 - 2 * a3 * b + 2 * a2 * b2 - 2 * a * b3 + b4
					- 2 * a3 * c + 3 * a2 * b * c - b3 * c + 2 * a2 * c2
					- 2 * a * c3 - b * c3 + c4);
			case 2821 -> a2 * (b - c)
					* (a3 * b - a2 * b2 - a * b3 + b4 + a3 * c - 4 * a2 * b * c
					+ 5 * a * b2 * c - 2 * b3 * c - a2 * c2
					+ 5 * a * b * c2 - 2 * b2 * c2 - a * c3 - 2 * b * c3
					+ c4);
			case 2822 -> 2 * a9 - a8 * b - a7 * b2 - 2 * a6 * b3 - 3 * a5 * b4
					+ 6 * a4 * b5 + a3 * b6 - 2 * a2 * b7 + a * b8 - b9 - a8 * c
					+ 3 * a6 * b2 * c - 3 * a4 * b4 * c + a2 * b6 * c - a7 * c2
					+ 3 * a6 * b * c2 + 6 * a5 * b2 * c2 - 3 * a4 * b3 * c2
					- a3 * b4 * c2 - 3 * a2 * b5 * c2 - 4 * a * b6 * c2
					+ 3 * b7 * c2 - 2 * a6 * c3 - 3 * a4 * b2 * c3
					+ 4 * a2 * b4 * c3 + b6 * c3 - 3 * a5 * c4 - 3 * a4 * b * c4
					- a3 * b2 * c4 + 4 * a2 * b3 * c4 + 6 * a * b4 * c4
					- 3 * b5 * c4 + 6 * a4 * c5 - 3 * a2 * b2 * c5 - 3 * b4 * c5
					+ a3 * c6 + a2 * b * c6 - 4 * a * b2 * c6 + b3 * c6
					- 2 * a2 * c7 + 3 * b2 * c7 + a * c8 - c9;
			case 2823 -> a * (a7 * b - a6 * b2 - a5 * b3 + a4 * b4 - a3 * b5 + a2 * b6
					+ a * b7 - b8 + a7 * c - 2 * a6 * b * c + 2 * a5 * b2 * c
					- 3 * a4 * b3 * c + a3 * b4 * c + 4 * a2 * b5 * c
					- 4 * a * b6 * c + b7 * c - a6 * c2 + 2 * a5 * b * c2
					+ 4 * a4 * b2 * c2 - 5 * a2 * b4 * c2 - 2 * a * b5 * c2
					+ 2 * b6 * c2 - a5 * c3 - 3 * a4 * b * c3 + 5 * a * b4 * c3
					- b5 * c3 + a4 * c4 + a3 * b * c4 - 5 * a2 * b2 * c4
					+ 5 * a * b3 * c4 - 2 * b4 * c4 - a3 * c5 + 4 * a2 * b * c5
					- 2 * a * b2 * c5 - b3 * c5 + a2 * c6 - 4 * a * b * c6
					+ 2 * b2 * c6 + a * c7 + b * c7 - c8);
			case 2824 -> a2 * (b - c)
					* (a5 * b - a4 * b2 - a * b5 + b6 + a5 * c - a4 * b * c
					- a * b4 * c + b5 * c - a4 * c2 + 5 * a2 * b2 * c2
					+ 4 * a * b3 * c2 - 4 * b4 * c2 + 4 * a * b2 * c3
					- 4 * b3 * c3 - a * b * c4 - 4 * b2 * c4 - a * c5
					+ b * c5 + c6);
			case 2825 -> a2 * (a7 * b2 - a6 * b3 - a5 * b4 + a4 * b5 - a3 * b6
					+ a2 * b7 + a * b8 - b9 + a7 * c2 + a3 * b4 * c2
					- 2 * a * b6 * c2 - a6 * c3 - a2 * b4 * c3 + 2 * b6 * c3
					- a5 * c4 + a3 * b2 * c4 - a2 * b3 * c4 + 2 * a * b4 * c4
					- b5 * c4 + a4 * c5 - b4 * c5 - a3 * c6 - 2 * a * b2 * c6
					+ 2 * b3 * c6 + a2 * c7 + a * c8 - c9);
			case 2826 -> (b - c) * (a2 * b - 2 * a * b2 + b3 + a2 * c + 2 * a * b * c
					- b2 * c - 2 * a * c2 - b * c2 + c3);
			case 2827 -> a * (b - c) * (a3 - a2 * b - a * b2 + b3 - a2 * c
					+ 5 * a * b * c - 2 * b2 * c - a * c2 - 2 * b * c2 + c3);
			case 2828 -> a9 * b - 3 * a7 * b3 + 3 * a5 * b5 - a3 * b7 + a9 * c
					- 2 * a8 * b * c + 2 * a7 * b2 * c + a6 * b3 * c
					- 6 * a5 * b4 * c + 3 * a4 * b5 * c + 2 * a3 * b6 * c
					- a2 * b7 * c + a * b8 * c - b9 * c + 2 * a7 * b * c2
					+ 3 * a5 * b3 * c2 - 4 * a3 * b5 * c2 - a * b7 * c2
					- 3 * a7 * c3 + a6 * b * c3 + 3 * a5 * b2 * c3
					- 6 * a4 * b3 * c3 + 3 * a3 * b4 * c3 + a2 * b5 * c3
					- 3 * a * b6 * c3 + 4 * b7 * c3 - 6 * a5 * b * c4
					+ 3 * a3 * b3 * c4 + 3 * a * b5 * c4 + 3 * a5 * c5
					+ 3 * a4 * b * c5 - 4 * a3 * b2 * c5 + a2 * b3 * c5
					+ 3 * a * b4 * c5 - 6 * b5 * c5 + 2 * a3 * b * c6
					- 3 * a * b3 * c6 - a3 * c7 - a2 * b * c7 - a * b2 * c7
					+ 4 * b3 * c7 + a * b * c8 - b * c9;
			case 2829 -> 2 * a7 - 2 * a6 * b - 3 * a5 * b2 + 3 * a4 * b3 + a * b6 - b7
					- 2 * a6 * c + 8 * a5 * b * c - 3 * a4 * b2 * c
					- 4 * a3 * b3 * c + 4 * a2 * b4 * c - 4 * a * b5 * c
					+ b6 * c - 3 * a5 * c2 - 3 * a4 * b * c2 + 8 * a3 * b2 * c2
					- 4 * a2 * b3 * c2 - a * b4 * c2 + 3 * b5 * c2 + 3 * a4 * c3
					- 4 * a3 * b * c3 - 4 * a2 * b2 * c3 + 8 * a * b3 * c3
					- 3 * b4 * c3 + 4 * a2 * b * c4 - a * b2 * c4 - 3 * b3 * c4
					- 4 * a * b * c5 + 3 * b2 * c5 + a * c6 + b * c6 - c7;
			case 2830 -> a * (b - c)
					* (a5 - a4 * b - a * b4 + b5 - a4 * c + 5 * a3 * b * c
					- a * b3 * c + b4 * c + 4 * a * b2 * c2
					- 4 * b3 * c2 - a * b * c3 - 4 * b2 * c3 - a * c4
					+ b * c4 + c5);
			case 2831 -> a * (a7 * b - a6 * b2 - a5 * b3 + a4 * b4 - a3 * b5 + a2 * b6
					+ a * b7 - b8 + a7 * c + a3 * b4 * c - 2 * a * b6 * c
					- a6 * c2 - a2 * b4 * c2 + 2 * b6 * c2 - a5 * c3
					+ a * b4 * c3 + a4 * c4 + a3 * b * c4 - a2 * b2 * c4
					+ a * b3 * c4 - 2 * b4 * c4 - a3 * c5 + a2 * c6
					- 2 * a * b * c6 + 2 * b2 * c6 + a * c7 - c8);
			case 2832 -> a * (b - c) * (a2 + b2 - 3 * b * c + c2);
			case 2833 -> a8 * b - a7 * b2 - 2 * a6 * b3 + 2 * a5 * b4 + a4 * b5
					- a3 * b6 + a8 * c + a6 * b2 * c + 2 * a4 * b4 * c
					- 3 * a2 * b6 * c - b8 * c - a7 * c2 + a6 * b * c2
					- 2 * a5 * b2 * c2 - 3 * a4 * b3 * c2 + a3 * b4 * c2
					+ a2 * b5 * c2 + 2 * a * b6 * c2 + b7 * c2 - 2 * a6 * c3
					- 3 * a4 * b2 * c3 + 2 * a2 * b4 * c3 + 3 * b6 * c3
					+ 2 * a5 * c4 + 2 * a4 * b * c4 + a3 * b2 * c4
					+ 2 * a2 * b3 * c4 - 4 * a * b4 * c4 - 3 * b5 * c4 + a4 * c5
					+ a2 * b2 * c5 - 3 * b4 * c5 - a3 * c6 - 3 * a2 * b * c6
					+ 2 * a * b2 * c6 + 3 * b3 * c6 + b2 * c7 - b * c8;
			case 2834 -> 2 * a6 - 2 * a5 * b + a4 * b2 + 2 * a3 * b3 - 2 * a2 * b4
					- b6 - 2 * a5 * c - 2 * a3 * b2 * c + 2 * a2 * b3 * c
					+ 2 * b5 * c + a4 * c2 - 2 * a3 * b * c2 + b4 * c2
					+ 2 * a3 * c3 + 2 * a2 * b * c3 - 4 * b3 * c3 - 2 * a2 * c4
					+ b2 * c4 + 2 * b * c5 - c6;
			case 2835 -> a * (a4 * b - b5 + a4 * c - 4 * a3 * b * c + a2 * b2 * c
					+ 2 * b4 * c + a2 * b * c2 - b3 * c2 - b2 * c3 + 2 * b * c4
					- c5);
			case 2836 -> a * (a4 * b - b5 + a4 * c - 2 * a3 * b * c - a2 * b2 * c
					+ a * b3 * c + b4 * c - a2 * b * c2 + a * b * c3 + b * c4
					- c5);
			case 2837 -> a * (b - c)
					* (a4 + 2 * a2 * b2 + b4 + 3 * a2 * b * c - 3 * a * b2 * c
					+ 2 * a2 * c2 - 3 * a * b * c2 - 4 * b2 * c2 + c4);
			case 2838 -> a * (a6 * b + a4 * b3 - a2 * b5 - b7 + a6 * c
					- 2 * a5 * b * c - 2 * a4 * b2 * c + a3 * b3 * c
					+ a * b5 * c + b6 * c - 2 * a4 * b * c2 + a2 * b3 * c2
					+ b5 * c2 + a4 * c3 + a3 * b * c3 + a2 * b2 * c3
					- 2 * a * b3 * c3 - b4 * c3 - b3 * c4 - a2 * c5 + a * b * c5
					+ b2 * c5 + b * c6 - c7);
			case 2839 -> 2 * a7 - a6 * b - a5 * b2 + 5 * a4 * b3 - 3 * a2 * b5
					- a * b6 - b7 - a6 * c - 4 * a4 * b2 * c + 3 * a2 * b4 * c
					+ 2 * b6 * c - a5 * c2 - 4 * a4 * b * c2 + a * b4 * c2
					+ 4 * b5 * c2 + 5 * a4 * c3 - 5 * b4 * c3 + 3 * a2 * b * c4
					+ a * b2 * c4 - 5 * b3 * c4 - 3 * a2 * c5 + 4 * b2 * c5
					- a * c6 + 2 * b * c6 - c7;
			case 2840 -> a * (a5 * b + a4 * b2 - a * b5 - b6 + a5 * c - 6 * a4 * b * c
					+ a3 * b2 * c + 3 * a2 * b3 * c - 2 * a * b4 * c
					+ 3 * b5 * c + a4 * c2 + a3 * b * c2 - 6 * a2 * b2 * c2
					+ 3 * a * b3 * c2 + b4 * c2 + 3 * a2 * b * c3
					+ 3 * a * b2 * c3 - 6 * b3 * c3 - 2 * a * b * c4 + b2 * c4
					- a * c5 + 3 * b * c5 - c6);
			case 2841 -> a2 * (a3 * b2 + a2 * b3 - a * b4 - b5 - 3 * a2 * b2 * c
					+ 3 * b4 * c + a3 * c2 - 3 * a2 * b * c2 + 4 * a * b2 * c2
					- 2 * b3 * c2 + a2 * c3 - 2 * b2 * c3 - a * c4 + 3 * b * c4
					- c5);
			case 2842 -> a2 * (a3 * b2 + a2 * b3 - a * b4 - b5 - 2 * a2 * b2 * c
					+ 2 * b4 * c + a3 * c2 - 2 * a2 * b * c2 + a2 * c3 - a * c4
					+ 2 * b * c4 - c5);
			case 2843 -> a2 * (b - c)
					* (a3 * b + a2 * b2 + a * b3 + b4 + a3 * c - a2 * b * c
					+ a * b2 * c - b3 * c + a2 * c2 + a * b * c2
					- 7 * b2 * c2 + a * c3 - b * c3 + c4);
			case 2844 -> a2 * (a5 * b2 + a4 * b3 - a * b6 - b7 - 2 * a4 * b2 * c
					+ 2 * b6 * c + a5 * c2 - 2 * a4 * b * c2 - 2 * a3 * b2 * c2
					+ a2 * b3 * c2 + a * b4 * c2 + b5 * c2 + a4 * c3
					+ a2 * b2 * c3 - 2 * b4 * c3 + a * b2 * c4 - 2 * b3 * c4
					+ b2 * c5 - a * c6 + 2 * b * c6 - c7);
			case 2845 -> (b - c) * (a10 - a9 * b - 3 * a8 * b2 + 3 * a7 * b3
					+ 3 * a6 * b4 - 3 * a5 * b5 - a4 * b6 + a3 * b7 - a9 * c
					- 3 * a8 * b * c + 3 * a7 * b2 * c + 2 * a6 * b3 * c
					- 3 * a5 * b4 * c + 4 * a4 * b5 * c + a3 * b6 * c
					- 2 * a2 * b7 * c - b9 * c - 3 * a8 * c2 + 3 * a7 * b * c2
					+ a4 * b4 * c2 - a3 * b5 * c2 + 2 * a2 * b6 * c2
					- 2 * a * b7 * c2 + 3 * a7 * c3 + 2 * a6 * b * c3
					- 8 * a4 * b3 * c3 - a3 * b4 * c3 + 2 * a2 * b5 * c3
					- 2 * a * b6 * c3 + 4 * b7 * c3 + 3 * a6 * c4
					- 3 * a5 * b * c4 + a4 * b2 * c4 - a3 * b3 * c4
					- 4 * a2 * b4 * c4 + 4 * a * b5 * c4 - 3 * a5 * c5
					+ 4 * a4 * b * c5 - a3 * b2 * c5 + 2 * a2 * b3 * c5
					+ 4 * a * b4 * c5 - 6 * b5 * c5 - a4 * c6 + a3 * b * c6
					+ 2 * a2 * b2 * c6 - 2 * a * b3 * c6 + a3 * c7
					- 2 * a2 * b * c7 - 2 * a * b2 * c7 + 4 * b3 * c7 - b * c9);
			case 2846 -> (b - c) * (a7 + 2 * a6 * b - 2 * a5 * b2 - 3 * a4 * b3
					+ a3 * b4 + b7 + 2 * a6 * c - 3 * a5 * b * c
					+ 2 * a3 * b3 * c - 2 * a2 * b4 * c + a * b5 * c
					- 2 * a5 * c2 + 2 * a3 * b2 * c2 + 2 * a2 * b3 * c2
					- 2 * b5 * c2 - 3 * a4 * c3 + 2 * a3 * b * c3
					+ 2 * a2 * b2 * c3 - 2 * a * b3 * c3 + b4 * c3 + a3 * c4
					- 2 * a2 * b * c4 + b3 * c4 + a * b * c5 - 2 * b2 * c5
					+ c7);
			case 2847 -> 2 * a8 - 2 * a6 * b2 + 5 * a4 * b4 - 4 * a2 * b6 - b8
					- 2 * a6 * c2 - 8 * a4 * b2 * c2 + 4 * a2 * b4 * c2
					+ 6 * b6 * c2 + 5 * a4 * c4 + 4 * a2 * b2 * c4
					- 10 * b4 * c4 - 4 * a2 * c6 + 6 * b2 * c6 - c8;
			case 2848 -> (b2 - c2) * (4 * a8 - 5 * a6 * b2 - a4 * b4 + a2 * b6 + b8
					- 5 * a6 * c2 + 8 * a4 * b2 * c2 - a2 * b4 * c2
					- 2 * b6 * c2 - a4 * c4 - a2 * b2 * c4 + 2 * b4 * c4
					+ a2 * c6 - 2 * b2 * c6 + c8);
			case 2849 -> a * (b - c)
					* (a6 - a4 * b2 - a2 * b4 + b6 + a4 * b * c
					- 2 * a3 * b2 * c + 2 * a * b4 * c - b5 * c
					- a4 * c2 - 2 * a3 * b * c2 + 6 * a2 * b2 * c2
					- 2 * a * b3 * c2 - b4 * c2 - 2 * a * b2 * c3
					+ 2 * b3 * c3 - a2 * c4 + 2 * a * b * c4 - b2 * c4
					- b * c5 + c6);
			default -> Double.NaN;
		};
	}

	private double weight2850to2899(int k, double a, double b, double c) {
		return switch (k) {
			case 2850 -> a * (b - c) * T * (a4 - b4 + a2 * b * c - a * b2 * c
					- a * b * c2 + 2 * b2 * c2 - c4);
			case 2851 -> a * (a6 * b + a4 * b3 - a2 * b5 - b7 + a6 * c
					- 2 * a5 * b * c - 2 * a4 * b2 * c + a3 * b3 * c
					+ 4 * a2 * b4 * c - 3 * a * b5 * c + b6 * c
					- 2 * a4 * b * c2 - 3 * a2 * b3 * c2 + 5 * b5 * c2 + a4 * c3
					+ a3 * b * c3 - 3 * a2 * b2 * c3 + 6 * a * b3 * c3
					- 5 * b4 * c3 + 4 * a2 * b * c4 - 5 * b3 * c4 - a2 * c5
					- 3 * a * b * c5 + 5 * b2 * c5 + b * c6 - c7);
			case 2852 -> a2 * (a4 * b2 - b6 - a3 * b2 * c + a2 * b3 * c - a * b4 * c
					+ b5 * c + a4 * c2 - a3 * b * c2 - 4 * a2 * b2 * c2
					+ 2 * a * b3 * c2 + 2 * b4 * c2 + a2 * b * c3
					+ 2 * a * b2 * c3 - 4 * b3 * c3 - a * b * c4 + 2 * b2 * c4
					+ b * c5 - c6);
			case 2853 -> a2 * (b - c)
					* (a6 * b - a4 * b3 - a2 * b5 + b7 + a6 * c - a5 * b * c
					- a2 * b4 * c + a * b5 * c - a3 * b2 * c2
					+ a2 * b3 * c2 + a * b4 * c2 - b5 * c2 - a4 * c3
					+ a2 * b2 * c3 - a2 * b * c4 + a * b2 * c4 - a2 * c5
					+ a * b * c5 - b2 * c5 + c7);
			case 2854 -> a2 * (a4 * b2 - b6 + a4 * c2 - 4 * a2 * b2 * c2 + 2 * b4 * c2
					+ 2 * b2 * c4 - c6);
			case 2855 -> (a - b) * (a + b) * (-a + c) * (a + c)
					* (a8 + a6 * b2 - 4 * a4 * b4 + a2 * b6 + b8 - 2 * a6 * c2
					- 2 * a4 * b2 * c2 - 2 * a2 * b4 * c2 - 2 * b6 * c2
					+ 2 * a4 * c4 + 7 * a2 * b2 * c4 + 2 * b4 * c4
					- 2 * a2 * c6 - 2 * b2 * c6 + c8)
					* (a8 - 2 * a6 * b2 + 2 * a4 * b4 - 2 * a2 * b6 + b8
					+ a6 * c2 - 2 * a4 * b2 * c2 + 7 * a2 * b4 * c2
					- 2 * b6 * c2 - 4 * a4 * c4 - 2 * a2 * b2 * c4
					+ 2 * b4 * c4 + a2 * c6 - 2 * b2 * c6 + c8);
			case 2856 -> a * (-a6 + a4 * b2 + a2 * b4 - b6 + a5 * c - a3 * b2 * c
					- a2 * b3 * c + b5 * c + 2 * a2 * b2 * c2
					- a2 * b * c3 - a * b2
					* c3
					- a2 * c4 - b2 * c4 + a * c5 + b * c5)
					* (-a6 + a5 * b - a2 * b4 + a * b5 - a2 * b3 * c + b5 * c
					+ a4 * c2 - a3 * b * c2 + 2 * a2 * b2 * c2
					- a * b3 * c2 - b4 * c2 - a2 * b * c3 + a2 * c4
					+ b * c5 - c6);
			case 2857 -> (-a8 + a6 * b2 + a2 * b6 - b8 + a6 * c2 + b6 * c2 - a4 * c4
					- 2 * a2 * b2 * c4 - b4 * c4 + a2 * c6 + b2 * c6)
					* (-a8 + a6 * b2 - a4 * b4 + a2 * b6 + a6 * c2
					- 2 * a2 * b4 * c2 + b6 * c2 - b4 * c4 + a2 * c6
					+ b2 * c6 - c8);
			case 2858 -> (a - b) * (a + b) * (-a + c) * (a + c)
					* (a4 - 3 * a2 * b2 + b4 + c4)
					* (a4 + b4 - 3 * a2 * c2 + c4);
			case 2859 -> -c * b
					* (b * c * Math.sin(2 * angleB)
					+ b * a * Math.sin(2 * angleA)
					- b * a * Math.sin(2 * angleB)
					+ c * a * Math.sin(2 * angleC)
					- c * a * Math.sin(2 * angleA)
					- c * b * Math.sin(2 * angleC))
					/ (b3 * a * Math.sin(2 * angleA)
					- b3 * a * Math.sin(2 * angleB)
					- b4 * Math.sin(2 * angleA)
					+ b4 * Math.sin(2 * angleB)
					- c4 * Math.sin(2 * angleC)
					+ c3 * a * Math.sin(2 * angleC)
					+ c4 * Math.sin(2 * angleA)
					- c3 * a * Math.sin(2 * angleA));
			case 2860 -> b * (-a
					+ b)
					* (a
					- c)
					* c
					* (a4 + a3 * b + a2 * b2 + a * b3 + b4 - 2 * a3 * c
					- 2 * a2 * b * c
					- 2 * a * b2 * c - 2 * b3
					* c
					+ a2 * c2 + a * b * c2 + b2 * c2)
					* (a4 - 2 * a3 * b + a2 * b2 + a3 * c - 2 * a2 * b * c
					+ a * b2 * c + a2 * c2 - 2 * a * b * c2 + b2 * c2
					+ a * c3 - 2 * b * c3 + c4);
			case 2861 -> (-a6 + a4 * b2 + a2 * b4 - b6 + 2 * a5 * c - 2 * a4 * b * c
					- 2 * a * b4 * c + 2 * b5 * c + 2 * a3 * b * c2
					- 2 * a2 * b2 * c2 + 2 * a * b3 * c2 - 2 * a3 * c3
					- 2 * b3 * c3 + a2 * c4 + b2 * c4)
					* (-a6 + 2 * a5 * b - 2 * a3 * b3 + a2 * b4 - 2 * a4 * b * c
					+ 2 * a3 * b2 * c + a4 * c2 - 2 * a2 * b2 * c2
					+ b4 * c2 + 2 * a * b2 * c3 - 2 * b3 * c3 + a2 * c4
					- 2 * a * b * c4 + 2 * b * c5 - c6);
			case 2862 -> (-a5 + a4 * b + a * b4 - b5 + a4 * c + b4 * c - a3 * c2
					- a2 * b * c2 - a * b2 * c2 - b3 * c2 + a2 * c3 + b2 * c3)
					* (-a5 + a4 * b - a3 * b2 + a2 * b3 + a4 * c - a2 * b2 * c
					- a * b2 * c2 + b3 * c2 - b2 * c3 + a * c4 + b * c4
					- c5);
			case 2863 -> b * c
					* (a5 - 2 * a4 * b - 2 * a * b4 + b5 + 2 * a3 * b * c
					+ 2 * a * b3 * c - a3 * c2 - b3 * c2)
					* (a5 - a3 * b2 - 2 * a4 * c + 2 * a3 * b * c
					+ 2 * a * b * c3 - b2 * c3 - 2 * a * c4 + c5);
			case 2864 -> b * (-a
					+ b)
					* (a
					- c)
					* c
					* (a5 + a4 * b + a3 * b2 + a2 * b3 + a * b4 + b5 - a4 * c
					- a3 * b * c - a2 * b2 * c - a * b3 * c - b4 * c
					- a3 * c2 - a2 * b * c2
					- a * b2 * c2 - b3
					* c2
					+ a2 * c3 + a * b * c3 + b2 * c3)
					* (a5 - a4 * b - a3 * b2 + a2 * b3 + a4 * c - a3 * b * c
					- a2 * b2 * c + a * b3 * c + a3 * c2 - a2 * b * c2
					- a * b2 * c2 + b3 * c2 + a2 * c3 - a * b * c3
					- b2 * c3 + a * c4 - b * c4 + c5);
			case 2865 -> (a - b) * (-a + c)
					* (a6 - a4 * b2 - a2 * b4 + b6 - a5 * c + a4 * b * c
					- 2 * a3 * b2 * c - 2 * a2 * b3 * c + a * b4 * c
					- b5 * c + 2 * a3 * b * c2 - 2 * a2 * b2 * c2
					+ 2 * a * b3 * c2 + 2 * a2 * b * c3
					+ 2 * a * b2 * c3 - a2 * c4 - 2 * a * b * c4
					- b2 * c4 + a * c5 + b * c5)
					* (a6 - a5 * b - a2 * b4 + a * b5 + a4 * b * c
					+ 2 * a3 * b2 * c + 2 * a2 * b3 * c - 2 * a * b4 * c
					+ b5 * c - a4 * c2 - 2 * a3 * b * c2
					- 2 * a2 * b2 * c2 + 2 * a * b3 * c2 - b4 * c2
					- 2 * a2 * b * c3 + 2 * a * b2 * c3 - a2 * c4
					+ a * b * c4 - b * c5 + c6);
			case 2866 -> b * c
					* (a7 - 2 * a6 * b + a5 * b2 + a2 * b5 - 2 * a * b6 + b7
					- a3 * b2 * c2 - a2 * b3 * c2 + 2 * a3 * b * c3
					+ 2 * a * b3 * c3 - a3 * c4 - b3 * c4)
					* (a7 - a3 * b4 - 2 * a6 * c + 2 * a3 * b3 * c + a5 * c2
					- a3 * b2 * c2 - a2 * b2 * c3 + 2 * a * b3 * c3
					- b4 * c3 + a2 * c5 - 2 * a * c6 + c7);
			case 2867 -> (a - b) * (a + b) * (-a + c) * (a + c)
					* (-a4 + b4 - a3 * c + a * b2 * c - a * c3 - c4)
					* (-a4 + b4 + a3 * c - a * b2 * c + a * c3 - c4)
					* (-a4 + a3 * b + a * b3 - b4 - a * b * c2 + c4)
					* (-a4 - a3 * b - a * b3 - b4 + a * b * c2 + c4);
			case 2868 -> b2 * c2
					* (a8 - 2 * a6 * b2 - 2 * a2 * b6 + b8 + 2 * a4 * b2 * c2
					+ 2 * a2 * b4 * c2 - a4 * c4 - b4 * c4)
					* (a8 - a4 * b4 - 2 * a6 * c2 + 2 * a4 * b2 * c2
					+ 2 * a2 * b2 * c4 - b4 * c4 - 2 * a2 * c6 + c8);
			case 2869 -> a2 * (b2 - c2)
					* (a8 - 2 * a6 * b2 + 2 * a4 * b4 - 2 * a2 * b6 + b8
					- 2 * a6 * c2 + 7 * a4 * b2 * c2 - 2 * a2 * b4 * c2
					+ b6 * c2 + 2 * a4 * c4 - 2 * a2 * b2 * c4
					- 4 * b4 * c4 - 2 * a2 * c6 + b2 * c6 + c8);
			case 2870 -> a * (a5 * b - a4 * b2 + a * b5 - b6 + a5 * c - a3 * b2 * c
					- a4 * c2 - a3 * b * c2 + 2 * a2 * b2 * c2 - a * b3 * c2
					+ b4 * c2 - a * b2 * c3 + b2 * c4 + a * c5 - c6);
			case 2871 -> a2 * (a6 * b2 - a4 * b4 + a2 * b6 - b8 + a6 * c2
					- 2 * a4 * b2 * c2 + b6 * c2 - a4 * c4 + a2 * c6 + b2 * c6
					- c8);
			case 2872 -> a2 * (b2 - c2) * (a4 + b4 - 3 * b2 * c2 + c4);
			case 2873 -> a2 / (-c * b
					* (b * c * Math.sin(2 * angleB)
					+ b * a * Math.sin(2 * angleA)
					- b * a * Math.sin(2 * angleB)
					+ c * a * Math.sin(2 * angleC)
					- c * a * Math.sin(2 * angleA)
					- c * b * Math.sin(2 * angleC))
					/ (b3 * a * Math.sin(2 * angleA)
					- b3 * a * Math.sin(2 * angleB)
					- b4 * Math.sin(2 * angleA)
					+ b4 * Math.sin(2 * angleB)
					- c4 * Math.sin(2 * angleC)
					+ c3 * a * Math.sin(2 * angleC)
					+ c4 * Math.sin(2 * angleA)
					- c3 * a * Math.sin(2 * angleA)));
			case 2874 -> a3 * (b - c)
					* (a2 * b2 - 2 * a * b3 + b4 + a2 * b * c - 2 * a * b2 * c
					+ b3 * c + a2 * c2 - 2 * a * b * c2 + b2 * c2
					- 2 * a * c3 + b * c3 + c4);
			case 2875 -> a2 * (a4 * b2 - 2 * a3 * b3 + 2 * a * b5 - b6
					+ 2 * a2 * b3 * c - 2 * a * b4 * c + a4 * c2
					- 2 * a2 * b2 * c2 + b4 * c2 - 2 * a3 * c3 + 2 * a2 * b * c3
					- 2 * a * b * c4 + b2 * c4 + 2 * a * c5 - c6);
			case 2876 -> a2 * (a3 * b2 - a2 * b3 + a * b4 - b5 - a2 * b2 * c + b4 * c
					+ a3 * c2 - a2 * b * c2 - a2 * c3 + a * c4 + b * c4 - c5);
			case 2877 -> a3 * (a2 * b3 - b5 - 2 * a * b3 * c + 2 * b4 * c + a2 * c3
					- 2 * a * b * c3 + 2 * b * c4 - c5);
			case 2878 -> a3 * (b - c)
					* (a3 * b2 - a2 * b3 - a * b4 + b5 + a3 * b * c
					- a2 * b2 * c - a * b3 * c + b4 * c + a3 * c2
					- a2 * b * c2 - a * b2 * c2 + b3 * c2 - a2 * c3
					- a * b * c3 + b2 * c3 - a * c4 + b * c4 + c5);
			case 2879 -> a2 * (b - c)
					* (a5 * b - a4 * b2 - a * b5 + b6 + a5 * c - 2 * a4 * b * c
					+ 2 * a3 * b2 * c + 2 * a2 * b3 * c + a * b4 * c
					- a4 * c2 + 2 * a3 * b * c2 - 2 * a2 * b2 * c2
					- 2 * a * b3 * c2 - b4 * c2 + 2 * a2 * b * c3
					- 2 * a * b2 * c3 + a * b * c4 - b2 * c4 - a * c5
					+ c6);
			case 2880 -> a3 * (a4 * b3 - b7 - 2 * a3 * b3 * c + 2 * b6 * c
					+ a2 * b3 * c2 - b5 * c2 + a4 * c3 - 2 * a3 * b * c3
					+ a2 * b2 * c3 - b2 * c5 + 2 * b * c6 - c7);
			case 2881 -> a2 * (b2 - c2)
					* (a4 - b4 + a2 * b * c - b3 * c - b * c3 - c4)
					* (a4 - b4 - a2 * b * c + b3 * c + b * c3 - c4);
			case 2882 -> a4 * (a4 * b4 - b8 - 2 * a2 * b4 * c2 + 2 * b6 * c2 + a4 * c4
					- 2 * a2 * b2 * c4 + 2 * b2 * c6 - c8);
			case 2883 -> (3 * a4 - 2 * a2 * b2 - b4 - 2 * a2 * c2 + 2 * b2 * c2 - c4)
					* (a4 * b2 - 2 * a2 * b4 + b6 + a4 * c2 + 4 * a2 * b2 * c2
					- b4 * c2 - 2 * a2 * c4 - b2 * c4 + c6);
			case 2884 -> (3 * a2 - 2 * a * b - b2 - 2 * a * c + 2 * b * c - c2)
					* (a2 * b2 - 2 * a * b3 + b4 + 2 * a * b2 * c + 2 * b3 * c
					+ a2 * c2 + 2 * a * b * c2 - 6 * b2 * c2
					- 2 * a * c3 + 2 * b * c3 + c4);
			case 2885 -> (3 * a - b - c)
					* (a * b2 + b3 - 3 * b2 * c + a * c2 - 3 * b * c2 + c3);
			case 2886 -> a * b2 - b3 + b2 * c + a * c2 + b * c2 - c3;
			case 2887 -> (b + c) * (b2 - b * c + c2);
			case 2888 -> a10 - 3 * a8 * b2 + 4 * a6 * b4 - 4 * a4 * b6 + 3 * a2 * b8
					- b10 - 3 * a8 * c2 + 5 * a6 * b2 * c2 - 2 * a4 * b4 * c2
					- 3 * a2 * b6 * c2 + 3 * b8 * c2 + 4 * a6 * c4
					- 2 * a4 * b2 * c4 - 2 * b6 * c4 - 4 * a4 * c6
					- 3 * a2 * b2 * c6 - 2 * b4 * c6 + 3 * a2 * c8 + 3 * b2 * c8
					- c10;
			case 2889 -> a10 - 7 * a8 * b2 + 16 * a6 * b4 - 16 * a4 * b6 + 7 * a2 * b8
					- b10 - 7 * a8 * c2 + 25 * a6 * b2 * c2 - 14 * a4 * b4 * c2
					- 7 * a2 * b6 * c2 + 3 * b8 * c2 + 16 * a6 * c4
					- 14 * a4 * b2 * c4 - 2 * b6 * c4 - 16 * a4 * c6
					- 7 * a2 * b2 * c6 - 2 * b4 * c6 + 7 * a2 * c8 + 3 * b2 * c8
					- c10;
			case 2890 -> a6 - 3 * a5 * b + 3 * a4 * b2 - 3 * a2 * b4 + 3 * a * b5 - b6
					- 3 * a5 * c + 3 * a4 * b * c + 2 * a3 * b2 * c
					- 2 * a2 * b3 * c - 3 * a * b4 * c + 3 * b5 * c
					+ 3 * a4 * c2 + 2 * a3 * b * c2 - 2 * a2 * b2 * c2
					- 3 * b4 * c2 - 2 * a2 * b * c3 + 2 * b3 * c3 - 3 * a2 * c4
					- 3 * a * b * c4 - 3 * b2 * c4 + 3 * a * c5 + 3 * b * c5
					- c6;
			case 2891 -> a4 + 3 * a3 * b - 3 * a * b3 - b4 + 3 * a3 * c
					+ 5 * a2 * b * c - 5 * a * b2 * c - 3 * b3 * c
					- 5 * a * b * c2 - 4 * b2 * c2 - 3 * a * c3 - 3 * b * c3
					- c4;
			case 2892 -> a10 * a4 - a10 * a2 * b2 - a10 * b4 + a8 * b6 - a6 * b8
					+ a4 * b10 + a2 * b10 * b2 - b4 * b10 - a10 * a2 * c2
					+ 7 * a10 * b2 * c2 - 5 * a8 * b4 * c2 - 2 * a6 * b6 * c2
					+ 5 * a4 * b8 * c2 - 5 * a2 * b10 * c2 + b10 * b2 * c2
					- a10 * c4 - 5 * a8 * b2 * c4 + 10 * a6 * b4 * c4
					- 6 * a4 * b6 * c4 - a2 * b8 * c4 + 3 * b10 * c4 + a8 * c6
					- 2 * a6 * b2 * c6 - 6 * a4 * b4 * c6 + 10 * a2 * b6 * c6
					- 3 * b8 * c6 - a6 * c8 + 5 * a4 * b2 * c8 - a2 * b4 * c8
					- 3 * b6 * c8 + a4 * c10 - 5 * a2 * b2 * c10 + 3 * b4 * c10
					+ a2 * c10 * c2 + b2 * c10 * c2 - c10 * c4;
			case 2893 -> a5 - a3 * b2 + a2 * b3 - b5 - a3 * b * c + a * b3 * c
					- a3 * c2 + 2 * a * b2 * c2 + b3 * c2 + a2 * c3 + a * b * c3
					+ b2 * c3 - c5;
			case 2894 -> a7 - a6 * b - a5 * b2 + a4 * b3 - a3 * b4 + a2 * b5 + a * b6
					- b7 - a6 * c - a5 * b * c + 3 * a4 * b2 * c
					- 2 * a3 * b3 * c - 3 * a2 * b4 * c + 3 * a * b5 * c
					+ b6 * c - a5 * c2 + 3 * a4 * b * c2 + 2 * a3 * b2 * c2
					- 6 * a2 * b3 * c2 - a * b4 * c2 + 3 * b5 * c2 + a4 * c3
					- 2 * a3 * b * c3 - 6 * a2 * b2 * c3 - 6 * a * b3 * c3
					- 3 * b4 * c3 - a3 * c4 - 3 * a2 * b * c4 - a * b2 * c4
					- 3 * b3 * c4 + a2 * c5 + 3 * a * b * c5 + 3 * b2 * c5
					+ a * c6 + b * c6 - c7;
			case 2895 -> a3 + a2 * b - a * b2 - b3 + a2 * c - a * b * c - b2 * c
					- a * c2 - b * c2 - c3;
			case 2896 -> a4 - a2 * b2 - b4 - a2 * c2 - b2 * c2 - c4;
			case 2897 -> a8 - 2 * a6 * b2 + 2 * a2 * b6 - b8 - 3 * a6 * b * c
					- 3 * a5 * b2 * c + 2 * a4 * b3 * c + 2 * a3 * b4 * c
					+ a2 * b5 * c + a * b6 * c - 2 * a6 * c2 - 3 * a5 * b * c2
					+ 4 * a4 * b2 * c2 + 2 * a3 * b3 * c2 - 2 * a2 * b4 * c2
					+ a * b5 * c2 + 2 * a4 * b * c3 + 2 * a3 * b2 * c3
					- 2 * a2 * b3 * c3 - 2 * a * b4 * c3 + 2 * a3 * b * c4
					- 2 * a2 * b2 * c4 - 2 * a * b3 * c4 + 2 * b4 * c4
					+ a2 * b * c5 + a * b2 * c5 + 2 * a2 * c6 + a * b * c6 - c8;
			case 2898 -> (a + b - c) * (a - b + c)
					* (a4 - 2 * a3 * b + 2 * a2 * b2 - 2 * a * b3 + b4
					- 2 * a3 * c - 2 * a2 * b * c + 2 * a * b2 * c
					+ 2 * b3 * c + 2 * a2 * c2 + 2 * a * b * c2
					- 6 * b2 * c2 - 2 * a * c3 + 2 * b * c3 + c4);
			case 2899 -> (a - b - c) * (a3 + a2 * b + a * b2 + b3 + a2 * c
					- 3 * b2 * c + a * c2 - 3 * b * c2 + c3);
			default -> Double.NaN;
		};
	}

	private double weight2900to2949(int k, double a, double b, double c) {

		switch (k) {
		case 2900:
			return a * (-a + b + c)
					* (-a4 + 2 * a3 * b - 2 * a * b3 + b4 + 2 * a3 * c
							+ 2 * a2 * b * c - 2 * b2 * c2 - 2 * a * c3 + c4);
		case 2901:
			return (b + c) * (a3 + a2 * b + a2 * c - b2 * c - b * c2);
		case 2902:
			return a2 * (u(3) * a2 - u(3) * b2 - u(3) * c2 - S)
					* (u(3) * a6 - 3 * u(3) * a4 * b2 + 3 * u(3) * a2 * b4
							- u(3) * b6 - 3 * u(3) * a4 * c2
							- 2 * u(3) * a2 * b2 * c2 + u(3) * b4 * c2
							+ 3 * u(3) * a2 * c4 + u(3) * b2 * c4 - u(3) * c6
							- a4 * S + b4 * S - 2 * b2 * c2 * S + c4 * S);
		case 2903:
			return a2 * (u(3) * a2 - u(3) * b2 - u(3) * c2 + S)
					* (u(3) * a6 - 3 * u(3) * a4 * b2 + 3 * u(3) * a2 * b4
							- u(3) * b6 - 3 * u(3) * a4 * c2
							- 2 * u(3) * a2 * b2 * c2 + u(3) * b4 * c2
							+ 3 * u(3) * a2 * c4 + u(3) * b2 * c4 - u(3) * c6
							+ a4 * S - b4 * S + 2 * b2 * c2 * S - c4 * S);
		case 2904:
			return a2 * (-V) * (-U) * (a4 - 2 * a2 * b2 + b4 - 2 * a2 * c2 + c4)
					* (a6 - 3 * a4 * b2 + 3 * a2 * b4 - b6 - 3 * a4 * c2
							+ b4 * c2 + 3 * a2 * c4 + b2 * c4 - c6);
		case 2905:
			return (a + b) * (a + c) * (-V)
					* (a2 - a * b - b2 - a * c - b * c - c2) * (-U);
		case 2906:
			return a * (a + b) * (a + c) * (-V) * (-U) * (-a3 - a2 * b + a * b2
					+ b3 - a2 * c + a * b * c + b2 * c + a * c2 + b * c2 + c3);
		case 2907:
			return (a + b) * (a - b - c) * (a + c) * (-V) * (-U)
					* (a3 + 2 * a2 * b - b3 + 2 * a2 * c + a * b * c - c3);
		case 2908:
			return a3 * (a5 - a3 * b2 + a2 * b3 - b5 - a3 * c2 + b3 * c2
					+ a2 * c3 + b2 * c3 - c5);
		case 2909:
			return a4 * (a6 - a4 * b2 + a2 * b4 - b6 - a4 * c2 + b4 * c2
					+ a2 * c4 + b2 * c4 - c6);
		case 2910:
			return a * (-a3 - a2 * b + a * b2 + b3 - a2 * c + 2 * a * b * c
					- b2 * c + a * c2 - b * c2 + c3)
					* (-a6 + 3 * a4 * b2 - 3 * a2 * b4 + b6 + 2 * a3 * b2 * c
							- 2 * a2 * b3 * c - 2 * a * b4 * c + 2 * b5 * c
							+ 3 * a4 * c2 + 2 * a3 * b * c2 + 2 * a2 * b2 * c2
							+ 2 * a * b3 * c2 - b4 * c2 - 2 * a2 * b * c3
							+ 2 * a * b2 * c3 - 4 * b3 * c3 - 3 * a2 * c4
							- 2 * a * b * c4 - b2 * c4 + 2 * b * c5 + c6);
		case 2911:
			return a2 * (a3 - a2 * b - a * b2 + b3 - a2 * c - 2 * a * b * c
					+ b2 * c - a * c2 + b * c2 + c3);
		case 2912:
			return a2 * (a2 - b2 - c2 - u(3) * S)
					* (a6 - 3 * a4 * b2 + 3 * a2 * b4 - b6 - 3 * a4 * c2
							- 2 * a2 * b2 * c2 + b4 * c2 + 3 * a2 * c4 + b2 * c4
							- c6 - u(3) * a4 * S + u(3) * b4 * S
							- 2 * u(3) * b2 * c2 * S + u(3) * c4 * S);
		case 2913:
			return a2 * (a2 - b2 - c2 + u(3) * S)
					* (a6 - 3 * a4 * b2 + 3 * a2 * b4 - b6 - 3 * a4 * c2
							- 2 * a2 * b2 * c2 + b4 * c2 + 3 * a2 * c4 + b2 * c4
							- c6 + u(3) * a4 * S - u(3) * b4 * S
							+ 2 * u(3) * b2 * c2 * S - u(3) * c4 * S);
		case 2914:
			return a2 * (-V) * (a2 - b2 - b * c - c2) * (a2 - b2 + b * c - c2)
					* (-U)
					* (a6 - 3 * a4 * b2 + 3 * a2 * b4 - b6 - 3 * a4 * c2
							- a2 * b2 * c2 + b4 * c2 + 3 * a2 * c4 + b2 * c4
							- c6);
		case 2915:
			return a2 * (a5 + a4 * b - a * b4 - b5 + a4 * c + a3 * b * c
					- a * b3 * c - b4 * c - a * b * c3 - a * c4 - b * c4 - c5);
		case 2916:
			return a2 * (a6 + a4 * b2 - a2 * b4 - b6 + a4 * c2 - a2 * b2 * c2
					- b4 * c2 - a2 * c4 - b2 * c4 - c6);
		case 2917:
			return a2 * (a10 * a4 - 3 * a10 * a2 * b2 + a10 * b4 + 5 * a8 * b6
					- 5 * a6 * b8 - a4 * b10 + 3 * a2 * b10 * b2 - b4 * b10
					- 3 * a10 * a2 * c2 + 3 * a10 * b2 * c2 + 5 * a8 * b4 * c2
					- 6 * a6 * b6 * c2 + 3 * a4 * b8 * c2 - 5 * a2 * b10 * c2
					+ 3 * b10 * b2 * c2 + a10 * c4 + 5 * a8 * b2 * c4
					- 2 * a6 * b4 * c4 - 2 * a4 * b6 * c4 + a2 * b8 * c4
					- 3 * b10 * c4 + 5 * a8 * c6 - 6 * a6 * b2 * c6
					- 2 * a4 * b4 * c6 + 2 * a2 * b6 * c6 + b8 * c6
					- 5 * a6 * c8 + 3 * a4 * b2 * c8 + a2 * b4 * c8 + b6 * c8
					- a4 * c10 - 5 * a2 * b2 * c10 - 3 * b4 * c10
					+ 3 * a2 * c10 * c2 + 3 * b2 * c10 * c2 - c10 * c4);
		case 2918:
			return a2 * (a10 * a4 - 3 * a10 * a2 * b2 + a10 * b4 + 5 * a8 * b6
					- 5 * a6 * b8 - a4 * b10 + 3 * a2 * b10 * b2 - b4 * b10
					- 3 * a10 * a2 * c2 - a10 * b2 * c2 + 13 * a8 * b4 * c2
					- 6 * a6 * b6 * c2 - 5 * a4 * b8 * c2 - a2 * b10 * c2
					+ 3 * b10 * b2 * c2 + a10 * c4 + 13 * a8 * b2 * c4
					- 2 * a6 * b4 * c4 - 6 * a4 * b6 * c4 - 3 * a2 * b8 * c4
					- 3 * b10 * c4 + 5 * a8 * c6 - 6 * a6 * b2 * c6
					- 6 * a4 * b4 * c6 + 2 * a2 * b6 * c6 + b8 * c6
					- 5 * a6 * c8 - 5 * a4 * b2 * c8 - 3 * a2 * b4 * c8
					+ b6 * c8 - a4 * c10 - a2 * b2 * c10 - 3 * b4 * c10
					+ 3 * a2 * c10 * c2 + 3 * b2 * c10 * c2 - c10 * c4);
		case 2919:
			return a2 * (a10 - 2 * a9 * b - a8 * b2 + 4 * a7 * b3 - 2 * a6 * b4
					+ 2 * a4 * b6 - 4 * a3 * b7 + a2 * b8 + 2 * a * b9 - b10
					- 2 * a9 * c + 4 * a8 * b * c - 2 * a7 * b2 * c
					- 2 * a6 * b3 * c + 6 * a5 * b4 * c - 6 * a4 * b5 * c
					+ 2 * a3 * b6 * c + 2 * a2 * b7 * c - 4 * a * b8 * c
					+ 2 * b9 * c - a8 * c2 - 2 * a7 * b * c2 + 5 * a6 * b2 * c2
					+ 2 * a5 * b3 * c2 - 6 * a4 * b4 * c2 + 6 * a3 * b5 * c2
					- 7 * a2 * b6 * c2 + 2 * a * b7 * c2 + b8 * c2 + 4 * a7 * c3
					- 2 * a6 * b * c3 + 2 * a5 * b2 * c3 + 8 * a4 * b3 * c3
					- 8 * a3 * b4 * c3 - 2 * a2 * b5 * c3 + 2 * a * b6 * c3
					- 4 * b7 * c3 - 2 * a6 * c4 + 6 * a5 * b * c4
					- 6 * a4 * b2 * c4 - 8 * a3 * b3 * c4 + 12 * a2 * b4 * c4
					- 2 * a * b5 * c4 - 6 * a4 * b * c5 + 6 * a3 * b2 * c5
					- 2 * a2 * b3 * c5 - 2 * a * b4 * c5 + 4 * b5 * c5
					+ 2 * a4 * c6 + 2 * a3 * b * c6 - 7 * a2 * b2 * c6
					+ 2 * a * b3 * c6 - 4 * a3 * c7 + 2 * a2 * b * c7
					+ 2 * a * b2 * c7 - 4 * b3 * c7 + a2 * c8 - 4 * a * b * c8
					+ b2 * c8 + 2 * a * c9 + 2 * b * c9 - c10);
		case 2920:
			return a2 * (a8 - 2 * a6 * b2 + 2 * a2 * b6 - b8 + 2 * a6 * b * c
					+ 2 * a5 * b2 * c - 2 * a2 * b5 * c - 2 * a * b6 * c
					- 2 * a6 * c2 + 2 * a5 * b * c2 - 3 * a4 * b2 * c2
					- 2 * a3 * b3 * c2 + a2 * b4 * c2 - 2 * a * b5 * c2
					+ 2 * b6 * c2 - 2 * a3 * b2 * c3 + 6 * a2 * b3 * c3
					+ a2 * b2 * c4 - 2 * b4 * c4 - 2 * a2 * b * c5
					- 2 * a * b2 * c5 + 2 * a2 * c6 - 2 * a * b * c6
					+ 2 * b2 * c6 - c8);
		case 2921:
			return a2 * (a7 - a6 * b - a5 * b2 + a4 * b3 - a3 * b4 + a2 * b5
					+ a * b6 - b7 - a6 * c + 7 * a5 * b * c - 3 * a4 * b2 * c
					+ 3 * a2 * b4 * c - 7 * a * b5 * c + b6 * c - a5 * c2
					- 3 * a4 * b * c2 + 2 * a3 * b2 * c2 - 2 * a2 * b3 * c2
					+ 3 * a * b4 * c2 + b5 * c2 + a4 * c3 - 2 * a2 * b2 * c3
					- 2 * a * b3 * c3 - b4 * c3 - a3 * c4 + 3 * a2 * b * c4
					+ 3 * a * b2 * c4 - b3 * c4 + a2 * c5 - 7 * a * b * c5
					+ b2 * c5 + a * c6 + b * c6 - c7);
		case 2922:
			return a2 * (a8 + a7 * b + a5 * b3 - a3 * b5 - a * b7 - b8 + a7 * c
					+ a6 * b * c + a5 * b2 * c + a4 * b3 * c - a3 * b4 * c
					- a2 * b5 * c - a * b6 * c - b7 * c + a5 * b * c2
					- 2 * a4 * b2 * c2 - 2 * a3 * b3 * c2 - a * b5 * c2
					+ a5 * c3 + a4 * b * c3 - 2 * a3 * b2 * c3
					+ 2 * a2 * b3 * c3 - a * b4 * c3 - b5 * c3 - a3 * b * c4
					- a * b3 * c4 - 2 * b4 * c4 - a3 * c5 - a2 * b * c5
					- a * b2 * c5 - b3 * c5 - a * b * c6 - a * c7 - b * c7
					- c8);
		case 2929:
			return a2 * (a10 * a4 - 3 * a10 * a2 * b2 + a10 * b4 + 5 * a8 * b6
					- 5 * a6 * b8 - a4 * b10 + 3 * a2 * b10 * b2 - b4 * b10
					- 3 * a10 * a2 * c2 + 11 * a10 * b2 * c2 - 11 * a8 * b4 * c2
					- 6 * a6 * b6 * c2 + 19 * a4 * b8 * c2 - 13 * a2 * b10 * c2
					+ 3 * b10 * b2 * c2 + a10 * c4 - 11 * a8 * b2 * c4
					+ 22 * a6 * b4 * c4 - 18 * a4 * b6 * c4 + 9 * a2 * b8 * c4
					- 3 * b10 * c4 + 5 * a8 * c6 - 6 * a6 * b2 * c6
					- 18 * a4 * b4 * c6 + 2 * a2 * b6 * c6 + b8 * c6
					- 5 * a6 * c8 + 19 * a4 * b2 * c8 + 9 * a2 * b4 * c8
					+ b6 * c8 - a4 * c10 - 13 * a2 * b2 * c10 - 3 * b4 * c10
					+ 3 * a2 * c10 * c2 + 3 * b2 * c10 * c2 - c10 * c4);
		case 2930:
			return a2
					* (a6 + a4 * b2 - a2 * b4 - b6 + a4 * c2 - 5 * a2 * b2 * c2
							+ 3 * b4 * c2 - a2 * c4 + 3 * b2 * c4 - c6);
		case 2931:
			return a2 * T
					* (a10 * a2 - 2 * a10 * b2 - a8 * b4 + 4 * a6 * b6 - a4 * b8
							- 2 * a2 * b10 + b10 * b2 - 2 * a10 * c2
							+ 3 * a8 * b2 * c2 - a6 * b4 * c2 - 3 * a4 * b6 * c2
							+ 7 * a2 * b8 * c2 - 4 * b10 * c2 - a8 * c4
							- a6 * b2 * c4 + 4 * a4 * b4 * c4 - 5 * a2 * b6 * c4
							+ 7 * b8 * c4 + 4 * a6 * c6 - 3 * a4 * b2 * c6
							- 5 * a2 * b4 * c6 - 8 * b6 * c6 - a4 * c8
							+ 7 * a2 * b2 * c8 + 7 * b4 * c8 - 2 * a2 * c10
							- 4 * b2 * c10 + c10 * c2);

		case 2923:
		case 2924:
		case 2925:
		case 2926:
		case 2927:

		case 2928:

			double x = weight(k - 2910, a, b, c);
			double y = weight(k - 2910, b, c, a);
			double z = weight(k - 2910, c, a, b);

			return a2 * (-a3 / (c * y + b * z) + b3 / (a * z + c * x)
					+ c3 / (b * x + a * y));

		case 2932:
			return a2 * (a5 - a4 * b - 2 * a3 * b2 + 2 * a2 * b3 + a * b4 - b5
					- a4 * c + 5 * a3 * b * c - 5 * a * b3 * c + b4 * c
					- 2 * a3 * c2 + 2 * b3 * c2 + 2 * a2 * c3 - 5 * a * b * c3
					+ 2 * b2 * c3 + a * c4 + b * c4 - c5);
		case 2933:
			return a2 * (a5 - a3 * b2 + a2 * b3 - b5 + 2 * a3 * b * c
					- 2 * a * b3 * c - a3 * c2 + b3 * c2 + a2 * c3
					- 2 * a * b * c3 + b2 * c3 - c5);
		case 2934:
			return a2 * (a10 * a2 - 3 * a10 * b2 + 3 * a8 * b4 - 3 * a4 * b8
					+ 3 * a2 * b10 - b10 * b2 - 3 * a10 * c2 + 3 * a8 * b2 * c2
					+ 2 * a6 * b4 * c2 - 2 * a4 * b6 * c2 - 3 * a2 * b8 * c2
					+ 3 * b10 * c2 + 3 * a8 * c4 + 2 * a6 * b2 * c4
					- 2 * a4 * b4 * c4 - 3 * b8 * c4 - 2 * a4 * b2 * c6
					+ 2 * b6 * c6 - 3 * a4 * c8 - 3 * a2 * b2 * c8 - 3 * b4 * c8
					+ 3 * a2 * c10 + 3 * b2 * c10 - c10 * c2);
		case 2935:
			return a2 * (a10 * a4 - 3 * a10 * a2 * b2 + a10 * b4 + 5 * a8 * b6
					- 5 * a6 * b8 - a4 * b10 + 3 * a2 * b10 * b2 - b4 * b10
					- 3 * a10 * a2 * c2 + 15 * a10 * b2 * c2 - 15 * a8 * b4 * c2
					- 14 * a6 * b6 * c2 + 27 * a4 * b8 * c2 - 9 * a2 * b10 * c2
					- b10 * b2 * c2 + a10 * c4 - 15 * a8 * b2 * c4
					+ 46 * a6 * b4 * c4 - 26 * a4 * b6 * c4 - 15 * a2 * b8 * c4
					+ 9 * b10 * c4 + 5 * a8 * c6 - 14 * a6 * b2 * c6
					- 26 * a4 * b4 * c6 + 42 * a2 * b6 * c6 - 7 * b8 * c6
					- 5 * a6 * c8 + 27 * a4 * b2 * c8 - 15 * a2 * b4 * c8
					- 7 * b6 * c8 - a4 * c10 - 9 * a2 * b2 * c10 + 9 * b4 * c10
					+ 3 * a2 * c10 * c2 - b2 * c10 * c2 - c10 * c4);
		case 2936:
			return a2 * (a8 - a6 * b2 + a2 * b6 - b8 - a6 * c2
					+ 5 * a4 * b2 * c2 - 5 * a2 * b4 * c2 + b6 * c2
					- 5 * a2 * b2 * c4 + 4 * b4 * c4 + a2 * c6 + b2 * c6 - c8);
		case 2937:
			return a2 * (a8 - 2 * a6 * b2 + 2 * a2 * b6 - b8 - 2 * a6 * c2
					- a4 * b2 * c2 + a2 * b4 * c2 + 2 * b6 * c2 + a2 * b2 * c4
					- 2 * b4 * c4 + 2 * a2 * c6 + 2 * b2 * c6 - c8);
		case 2938:
			return a * (a4 + 3 * a3 * b - 2 * a2 * b2 - a * b3 - b4 + 3 * a3 * c
					+ a2 * b * c - 3 * a * b2 * c - b3 * c - 2 * a2 * c2
					- 3 * a * b * c2 + 4 * b2 * c2 - a * c3 - b * c3 - c4);
		case 2939:
			return a * (a6 + 3 * a5 * b + a4 * b2 - 2 * a3 * b3 - a2 * b4
					- a * b5 - b6 + 3 * a5 * c + 3 * a4 * b * c
					- 2 * a3 * b2 * c - 2 * a2 * b3 * c - a * b4 * c - b5 * c
					+ a4 * c2 - 2 * a3 * b * c2 - 2 * a2 * b2 * c2
					+ 2 * a * b3 * c2 + b4 * c2 - 2 * a3 * c3 - 2 * a2 * b * c3
					+ 2 * a * b2 * c3 + 2 * b3 * c3 - a2 * c4 - a * b * c4
					+ b2 * c4 - a * c5 - b * c5 - c6);
		case 2940:
			return a * (a6 + 4 * a5 * b + 3 * a4 * b2 - 2 * a3 * b3
					- 3 * a2 * b4 - 2 * a * b5 - b6 + 4 * a5 * c
					+ 8 * a4 * b * c - 6 * a2 * b3 * c - 4 * a * b4 * c
					- 2 * b5 * c + 3 * a4 * c2 - 5 * a2 * b2 * c2 + b4 * c2
					- 2 * a3 * c3 - 6 * a2 * b * c3 + 4 * b3 * c3 - 3 * a2 * c4
					- 4 * a * b * c4 + b2 * c4 - 2 * a * c5 - 2 * b * c5 - c6);
		case 2941:
			return a * (a5 + a4 * b - a * b4 - b5 + a4 * c + 5 * a3 * b * c
					- 2 * a2 * b2 * c - 3 * a * b3 * c - b4 * c
					- 2 * a2 * b * c2 + 2 * b3 * c2 - 3 * a * b * c3
					+ 2 * b2 * c3 - a * c4 - b * c4 - c5);
		case 2942:
			return a * (a8 - a7 * b - 8 * a6 * b2 + 21 * a5 * b3 - 20 * a4 * b4
					+ 9 * a3 * b5 - 4 * a2 * b6 + 3 * a * b7 - b8 - a7 * c
					- 3 * a6 * b * c + 11 * a5 * b2 * c - 7 * a4 * b3 * c
					- 3 * a3 * b4 * c + 7 * a2 * b5 * c - 7 * a * b6 * c
					+ 3 * b7 * c - 8 * a6 * c2 + 11 * a5 * b * c2
					+ 6 * a4 * b2 * c2 - 6 * a3 * b3 * c2 - 8 * a2 * b4 * c2
					+ 3 * a * b5 * c2 + 2 * b6 * c2 + 21 * a5 * c3
					- 7 * a4 * b * c3 - 6 * a3 * b2 * c3 + 10 * a2 * b3 * c3
					+ a * b4 * c3 - 19 * b5 * c3 - 20 * a4 * c4
					- 3 * a3 * b * c4 - 8 * a2 * b2 * c4 + a * b3 * c4
					+ 30 * b4 * c4 + 9 * a3 * c5 + 7 * a2 * b * c5
					+ 3 * a * b2 * c5 - 19 * b3 * c5 - 4 * a2 * c6
					- 7 * a * b * c6 + 2 * b2 * c6 + 3 * a * c7 + 3 * b * c7
					- c8);
		case 2943:
			return a * (a6 + a5 * b - 3 * a4 * b2 - 2 * a3 * b3 + 3 * a2 * b4
					+ a * b5 - b6 + a5 * c - a4 * b * c + 8 * a3 * b2 * c
					- 9 * a * b4 * c + b5 * c - 3 * a4 * c2 + 8 * a3 * b * c2
					- 18 * a2 * b2 * c2 + 8 * a * b3 * c2 + b4 * c2
					- 2 * a3 * c3 + 8 * a * b2 * c3 - 2 * b3 * c3 + 3 * a2 * c4
					- 9 * a * b * c4 + b2 * c4 + a * c5 + b * c5 - c6);
		case 2944:
			return a * (a6 + 3 * a5 * b + a4 * b2 - 2 * a3 * b3 - a2 * b4
					- a * b5 - b6 + 3 * a5 * c + 3 * a4 * b * c
					+ 2 * a3 * b2 * c - 2 * a2 * b3 * c - 5 * a * b4 * c
					- b5 * c + a4 * c2 + 2 * a3 * b * c2 - 6 * a2 * b2 * c2
					- 2 * a * b3 * c2 + b4 * c2 - 2 * a3 * c3 - 2 * a2 * b * c3
					- 2 * a * b2 * c3 + 2 * b3 * c3 - a2 * c4 - 5 * a * b * c4
					+ b2 * c4 - a * c5 - b * c5 - c6);
		case 2945:
			return a * (a6 - a5 * b - 5 * a4 * b2 + 5 * a2 * b4 + a * b5 - b6
					- a5 * c - 5 * a4 * b * c + 4 * a2 * b3 * c + a * b4 * c
					+ b5 * c - 5 * a4 * c2 + 2 * a2 * b2 * c2 + 2 * a * b3 * c2
					+ b4 * c2 + 4 * a2 * b * c3 + 2 * a * b2 * c3 - 2 * b3 * c3
					+ 5 * a2 * c4 + a * b * c4 + b2 * c4 + a * c5 + b * c5 - c6
					- u(3) * a4 * S - u(3) * a3 * b * S + u(3) * a * b3 * S
					+ u(3) * b4 * S - u(3) * a3 * c * S - u(3) * a2 * b * c * S
					+ u(3) * a * b2 * c * S + u(3) * b3 * c * S
					+ u(3) * a * b * c2 * S + u(3) * a * c3 * S
					+ u(3) * b * c3 * S + u(3) * c4 * S);
		case 2946:
			return a * (a6 - a5 * b - 5 * a4 * b2 + 5 * a2 * b4 + a * b5 - b6
					- a5 * c - 5 * a4 * b * c + 4 * a2 * b3 * c + a * b4 * c
					+ b5 * c - 5 * a4 * c2 + 2 * a2 * b2 * c2 + 2 * a * b3 * c2
					+ b4 * c2 + 4 * a2 * b * c3 + 2 * a * b2 * c3 - 2 * b3 * c3
					+ 5 * a2 * c4 + a * b * c4 + b2 * c4 + a * c5 + b * c5 - c6
					+ u(3) * a4 * S + u(3) * a3 * b * S - u(3) * a * b3 * S
					- u(3) * b4 * S + u(3) * a3 * c * S + u(3) * a2 * b * c * S
					- u(3) * a * b2 * c * S - u(3) * b3 * c * S
					- u(3) * a * b * c2 * S - u(3) * a * c3 * S
					- u(3) * b * c3 * S - u(3) * c4 * S);
		case 2947:
			return a * (a7 * b - 2 * a6 * b2 - a5 * b3 + 4 * a4 * b4 - a3 * b5
					- 2 * a2 * b6 + a * b7 + a7 * c - a6 * b * c - a5 * b2 * c
					+ a4 * b3 * c - a3 * b4 * c + a2 * b5 * c + a * b6 * c
					- b7 * c - 2 * a6 * c2 - a5 * b * c2 - 2 * a4 * b2 * c2
					+ 2 * a3 * b3 * c2 + 2 * a2 * b4 * c2 - a * b5 * c2
					+ 2 * b6 * c2 - a5 * c3 + a4 * b * c3 + 2 * a3 * b2 * c3
					- 2 * a2 * b3 * c3 - a * b4 * c3 + b5 * c3 + 4 * a4 * c4
					- a3 * b * c4 + 2 * a2 * b2 * c4 - a * b3 * c4 - 4 * b4 * c4
					- a3 * c5 + a2 * b * c5 - a * b2 * c5 + b3 * c5
					- 2 * a2 * c6 + a * b * c6 + 2 * b2 * c6 + a * c7 - b * c7);
		case 2948:
			return a * (a6 + 2 * a5 * b - a4 * b2 - 2 * a3 * b3 + a2 * b4 - b6
					+ 2 * a5 * c - 2 * a3 * b2 * c - a4 * c2 - 2 * a3 * b * c2
					- a2 * b2 * c2 + 2 * a * b3 * c2 + b4 * c2 - 2 * a3 * c3
					+ 2 * a * b2 * c3 + a2 * c4 + b2 * c4 - c6);
		case 2949:
			return a * (a9 - a8 * b - 4 * a7 * b2 + 4 * a6 * b3 + 6 * a5 * b4
					- 6 * a4 * b5 - 4 * a3 * b6 + 4 * a2 * b7 + a * b8 - b9
					- a8 * c - 3 * a7 * b * c + 4 * a6 * b2 * c
					+ 7 * a5 * b3 * c - 4 * a4 * b4 * c - 5 * a3 * b5 * c
					+ a * b7 * c + b8 * c - 4 * a7 * c2 + 4 * a6 * b * c2
					+ 10 * a5 * b2 * c2 - 2 * a4 * b3 * c2 - 4 * a3 * b4 * c2
					- 4 * a2 * b5 * c2 - 2 * a * b6 * c2 + 2 * b7 * c2
					+ 4 * a6 * c3 + 7 * a5 * b * c3 - 2 * a4 * b2 * c3
					- 6 * a3 * b3 * c3 - a * b5 * c3 - 2 * b6 * c3 + 6 * a5 * c4
					- 4 * a4 * b * c4 - 4 * a3 * b2 * c4 + 2 * a * b4 * c4
					- 6 * a4 * c5 - 5 * a3 * b * c5 - 4 * a2 * b2 * c5
					- a * b3 * c5 - 4 * a3 * c6 - 2 * a * b2 * c6 - 2 * b3 * c6
					+ 4 * a2 * c7 + a * b * c7 + 2 * b2 * c7 + a * c8 + b * c8
					- c9);
		default:
			return Double.NaN;
		}
	}

	private double weight2950to2999(int k, double a, double b, double c) {

		return switch (k) {
			case 2950 -> a * (a9 - a8 * b - 4 * a7 * b2 + 4 * a6 * b3 + 6 * a5 * b4
					- 6 * a4 * b5 - 4 * a3 * b6 + 4 * a2 * b7 + a * b8 - b9
					- a8 * c + 5 * a7 * b * c + 4 * a6 * b2 * c
					- 17 * a5 * b3 * c - 4 * a4 * b4 * c + 19 * a3 * b5 * c
					- 7 * a * b7 * c + b8 * c - 4 * a7 * c2 + 4 * a6 * b * c2
					+ 2 * a5 * b2 * c2 + 14 * a4 * b3 * c2 - 4 * a3 * b4 * c2
					- 20 * a2 * b5 * c2 + 6 * a * b6 * c2 + 2 * b7 * c2
					+ 4 * a6 * c3 - 17 * a5 * b * c3 + 14 * a4 * b2 * c3
					- 22 * a3 * b3 * c3 + 16 * a2 * b4 * c3 + 7 * a * b5 * c3
					- 2 * b6 * c3 + 6 * a5 * c4 - 4 * a4 * b * c4
					- 4 * a3 * b2 * c4 + 16 * a2 * b3 * c4 - 14 * a * b4 * c4
					- 6 * a4 * c5 + 19 * a3 * b * c5 - 20 * a2 * b2 * c5
					+ 7 * a * b3 * c5 - 4 * a3 * c6 + 6 * a * b2 * c6
					- 2 * b3 * c6 + 4 * a2 * c7 - 7 * a * b * c7 + 2 * b2 * c7
					+ a * c8 + b * c8 - c9);
			case 2951 -> a * (a4 - 4 * a3 * b + 6 * a2 * b2 - 4 * a * b3 + b4
					- 4 * a3 * c - 4 * a2 * b * c + 4 * a * b2 * c + 4 * b3 * c
					+ 6 * a2 * c2 + 4 * a * b * c2 - 10 * b2 * c2 - 4 * a * c3
					+ 4 * b * c3 + c4);
			case 2952 -> a * (a6 + 3 * a5 * b + 3 * a4 * b2 - 3 * a2 * b4 - 3 * a * b5
					- b6 + 3 * a5 * c + 11 * a4 * b * c + 4 * a3 * b2 * c
					- 8 * a2 * b3 * c - 7 * a * b4 * c - 3 * b5 * c
					+ 3 * a4 * c2 + 4 * a3 * b * c2 - 6 * a2 * b2 * c2
					- 2 * a * b3 * c2 + b4 * c2 - 8 * a2 * b * c3
					- 2 * a * b2 * c3 + 6 * b3 * c3 - 3 * a2 * c4
					- 7 * a * b * c4 + b2 * c4 - 3 * a * c5 - 3 * b * c5 - c6
					+ u(3) * a4 * S + u(3) * a3 * b * S - u(3) * a * b3 * S
					- u(3) * b4 * S + u(3) * a3 * c * S + u(3) * a2 * b * c * S
					- u(3) * a * b2 * c * S - u(3) * b3 * c * S
					- u(3) * a * b * c2 * S - u(3) * a * c3 * S
					- u(3) * b * c3 * S - u(3) * c4 * S);
			case 2953 -> a * (a6 + 3 * a5 * b + 3 * a4 * b2 - 3 * a2 * b4 - 3 * a * b5
					- b6 + 3 * a5 * c + 11 * a4 * b * c + 4 * a3 * b2 * c
					- 8 * a2 * b3 * c - 7 * a * b4 * c - 3 * b5 * c
					+ 3 * a4 * c2 + 4 * a3 * b * c2 - 6 * a2 * b2 * c2
					- 2 * a * b3 * c2 + b4 * c2 - 8 * a2 * b * c3
					- 2 * a * b2 * c3 + 6 * b3 * c3 - 3 * a2 * c4
					- 7 * a * b * c4 + b2 * c4 - 3 * a * c5 - 3 * b * c5 - c6
					- u(3) * a4 * S - u(3) * a3 * b * S + u(3) * a * b3 * S
					+ u(3) * b4 * S - u(3) * a3 * c * S - u(3) * a2 * b * c * S
					+ u(3) * a * b2 * c * S + u(3) * b3 * c * S
					+ u(3) * a * b * c2 * S + u(3) * a * c3 * S
					+ u(3) * b * c3 * S + u(3) * c4 * S);
			case 2954 -> a * (a8 - 3 * a7 * b + 7 * a5 * b3 - 4 * a4 * b4
					- 5 * a3 * b5 + 4 * a2 * b6 + a * b7 - b8 - 3 * a7 * c
					+ a6 * b * c + 7 * a5 * b2 * c - a4 * b3 * c
					- 5 * a3 * b4 * c - a2 * b5 * c + a * b6 * c + b7 * c
					+ 7 * a5 * b * c2 + 2 * a4 * b2 * c2 - 6 * a3 * b3 * c2
					- 4 * a2 * b4 * c2 - a * b5 * c2 + 2 * b6 * c2 + 7 * a5 * c3
					- a4 * b * c3 - 6 * a3 * b2 * c3 + 2 * a2 * b3 * c3
					- a * b4 * c3 - b5 * c3 - 4 * a4 * c4 - 5 * a3 * b * c4
					- 4 * a2 * b2 * c4 - a * b3 * c4 - 2 * b4 * c4 - 5 * a3 * c5
					- a2 * b * c5 - a * b2 * c5 - b3 * c5 + 4 * a2 * c6
					+ a * b * c6 + 2 * b2 * c6 + a * c7 + b * c7 - c8);
			case 2955 -> a * (a9 + a8 * b - 2 * a7 * b2 - 2 * a6 * b3 + 2 * a3 * b6
					+ 2 * a2 * b7 - a * b8 - b9 + a8 * c - 3 * a7 * b * c
					+ 4 * a6 * b2 * c + 3 * a5 * b3 * c - 8 * a4 * b4 * c
					+ 3 * a3 * b5 * c - 3 * a * b7 * c + 3 * b8 * c
					- 2 * a7 * c2 + 4 * a6 * b * c2 + 10 * a5 * b2 * c2
					- 6 * a3 * b4 * c2 - 4 * a2 * b5 * c2 - 2 * a * b6 * c2
					- 2 * a6 * c3 + 3 * a5 * b * c3 - 14 * a3 * b3 * c3
					+ 2 * a2 * b4 * c3 + 3 * a * b5 * c3 - 8 * b6 * c3
					- 8 * a4 * b * c4 - 6 * a3 * b2 * c4 + 2 * a2 * b3 * c4
					+ 6 * a * b4 * c4 + 6 * b5 * c4 + 3 * a3 * b * c5
					- 4 * a2 * b2 * c5 + 3 * a * b3 * c5 + 6 * b4 * c5
					+ 2 * a3 * c6 - 2 * a * b2 * c6 - 8 * b3 * c6 + 2 * a2 * c7
					- 3 * a * b * c7 - a * c8 + 3 * b * c8 - c9);
			case 2956 -> a * (3 * a6 + 2 * a5 * b - 7 * a4 * b2 - 4 * a3 * b3
					+ 5 * a2 * b4 + 2 * a * b5 - b6 + 2 * a5 * c
					+ 6 * a4 * b * c + 4 * a3 * b2 * c - 4 * a2 * b3 * c
					- 6 * a * b4 * c - 2 * b5 * c - 7 * a4 * c2
					+ 4 * a3 * b * c2 - 2 * a2 * b2 * c2 + 4 * a * b3 * c2
					+ b4 * c2 - 4 * a3 * c3 - 4 * a2 * b * c3 + 4 * a * b2 * c3
					+ 4 * b3 * c3 + 5 * a2 * c4 - 6 * a * b * c4 + b2 * c4
					+ 2 * a * c5 - 2 * b * c5 - c6);
			case 2957 -> a * (a7 - 2 * a6 * b + 3 * a4 * b3 - 3 * a3 * b4 + 2 * a * b6
					- b7 - 2 * a6 * c + 6 * a5 * b * c - 5 * a4 * b2 * c
					- 2 * a3 * b3 * c + 7 * a2 * b4 * c - 6 * a * b5 * c
					+ 2 * b6 * c - 5 * a4 * b * c2 + 11 * a3 * b2 * c2
					- 7 * a2 * b3 * c2 + a * b4 * c2 + 3 * a4 * c3
					- 2 * a3 * b * c3 - 7 * a2 * b2 * c3 + 6 * a * b3 * c3
					- b4 * c3 - 3 * a3 * c4 + 7 * a2 * b * c4 + a * b2 * c4
					- b3 * c4 - 6 * a * b * c5 + 2 * a * c6 + 2 * b * c6 - c7);
			case 2958 -> a * (a9 - 2 * a8 * b - a7 * b2 + 3 * a6 * b3 + a5 * b4
					- a4 * b5 - 3 * a3 * b6 + a2 * b7 + 2 * a * b8 - b9
					- 2 * a8 * c + 8 * a7 * b * c - 5 * a6 * b2 * c
					- 4 * a5 * b3 * c - 6 * a4 * b4 * c + 12 * a3 * b5 * c
					+ 3 * a2 * b6 * c - 8 * a * b7 * c + 2 * b8 * c - a7 * c2
					- 5 * a6 * b * c2 + 7 * a5 * b2 * c2 + 7 * a4 * b3 * c2
					+ a3 * b4 * c2 - 19 * a2 * b5 * c2 + 9 * a * b6 * c2
					+ b7 * c2 + 3 * a6 * c3 - 4 * a5 * b * c3 + 7 * a4 * b2 * c3
					- 20 * a3 * b3 * c3 + 15 * a2 * b4 * c3 + 4 * a * b5 * c3
					- 5 * b6 * c3 + a5 * c4 - 6 * a4 * b * c4 + a3 * b2 * c4
					+ 15 * a2 * b3 * c4 - 14 * a * b4 * c4 + 3 * b5 * c4
					- a4 * c5 + 12 * a3 * b * c5 - 19 * a2 * b2 * c5
					+ 4 * a * b3 * c5 + 3 * b4 * c5 - 3 * a3 * c6
					+ 3 * a2 * b * c6 + 9 * a * b2 * c6 - 5 * b3 * c6 + a2 * c7
					- 8 * a * b * c7 + b2 * c7 + 2 * a * c8 + 2 * b * c8 - c9);
			case 2959 -> a * (a5 + 3 * a4 * b + a3 * b2 - a2 * b3 - a * b4 - b5
					+ 3 * a4 * c + 4 * a3 * b * c - a2 * b2 * c - 2 * a * b3 * c
					- b4 * c + a3 * c2 - a2 * b * c2 - a * b2 * c2 + b3 * c2
					- a2 * c3 - 2 * a * b * c3 + b2 * c3 - a * c4 - b * c4
					- c5);
			case 2960 -> a * (a6 + 2 * a5 * b + a4 * b2 - a2 * b4 - 2 * a * b5 - b6
					+ 2 * a5 * c + 5 * a4 * b * c + a3 * b2 * c
					- 3 * a2 * b3 * c - 3 * a * b4 * c - 2 * b5 * c + a4 * c2
					+ a3 * b * c2 - 2 * a2 * b2 * c2 + a * b3 * c2 + b4 * c2
					- 3 * a2 * b * c3 + a * b2 * c3 + 4 * b3 * c3 - a2 * c4
					- 3 * a * b * c4 + b2 * c4 - 2 * a * c5 - 2 * b * c5 - c6);
			case 2961 -> a * (a7 - a6 * b - a5 * b2 + a4 * b3 - a3 * b4 + a2 * b5
					+ a * b6 - b7 - a6 * c + 2 * a5 * b * c + a4 * b2 * c
					- 4 * a3 * b3 * c + 5 * a2 * b4 * c - 6 * a * b5 * c
					+ 3 * b6 * c - a5 * c2 + a4 * b * c2 + 6 * a3 * b2 * c2
					- 6 * a2 * b3 * c2 + 3 * a * b4 * c2 - 3 * b5 * c2 + a4 * c3
					- 4 * a3 * b * c3 - 6 * a2 * b2 * c3 + 4 * a * b3 * c3
					+ b4 * c3 - a3 * c4 + 5 * a2 * b * c4 + 3 * a * b2 * c4
					+ b3 * c4 + a2 * c5 - 6 * a * b * c5 - 3 * b2 * c5 + a * c6
					+ 3 * b * c6 - c7);
			case 2962 -> b * c * (a4 - a2 * b2 + b4 - 2 * a2 * c2 - 2 * b2 * c2 + c4)
					* (a4 - 2 * a2 * b2 + b4 - a2 * c2 - 2 * b2 * c2 + c4);
			case 2963 -> (a4 - a2 * b2 + b4 - 2 * a2 * c2 - 2 * b2 * c2 + c4)
					* (a4 - 2 * a2 * b2 + b4 - a2 * c2 - 2 * b2 * c2 + c4);
			case 2964 -> a3 * (a4 - 2 * a2 * b2 + b4 - 2 * a2 * c2 - b2 * c2 + c4);
			case 2965 -> a4 * (a4 - 2 * a2 * b2 + b4 - 2 * a2 * c2 - b2 * c2 + c4);
			case 2966 -> (a - b) * (a + b) * (-a + c) * (a + c)
					* (a4 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - b2 * c2 + c4);
			case 2967 -> a2 * (-V) * (-U) * p(a2 * b2 - b4 + a2 * c2 - c4, 2);
			case 2968 -> p(b - c, 2) * p(-a + b + c, 2) * (-T);
			case 2969 -> p(b - c, 2) * (-V) * (-U);
			case 2970 -> b2 * p(b - c, 2) * c2 * p(b + c, 2) * (-V) * (-U);
			case 2971 -> a2 * p(b - c, 2) * p(b + c, 2) * U * V;
			case 2972 -> a2 * p(b - c, 2) * p(b + c, 2) * p(a2 - b2 - c2, 3);
			case 2973 -> b2 * p(b - c, 2) * c2 * (-V) * (-U);
			case 2974 -> b2 * c2 * T
					* p(2 * a4 - a2 * b2 + b4 - a2 * c2 - 2 * b2 * c2 + c4, 2);
			case 2975 -> a * (a3 - a * b2 + a * b * c - b2 * c - a * c2 - b * c2);
			case 2976 -> (3 * a - b - c) * (b - c)
					* (2 * a2 - a * b + b2 - a * c - 2 * b * c + c2);
			case 2977 -> (b - c) * (2 * a3 + a2 * b - 2 * a * b2 + b3 + a2 * c
					- 4 * a * b * c + b2 * c - 2 * a * c2 + b * c2 + c3);
			case 2978 -> a2 * (b - c)
					* (a * b2 + a * c2 + a * b * c + b2 * c + b * c2);
			case 2979 -> a2 * (a2 * b2 - b4 + a2 * c2 - b2 * c2 - c4);
			case 2980 -> (-a4 - a2 * b2 - b4 + a2 * c2 + b2 * c2)
					* (-a4 + a2 * b2 - a2 * c2 + b2 * c2 - c4);
			case 2981 -> a2
					* (2 * a4 - a2 * b2 - b4 - 4 * a2 * c2 - b2 * c2 + 2 * c4
					- u(3) * b2 * S)
					* (2 * a4 - 4 * a2 * b2 + 2 * b4 - a2 * c2 - b2 * c2 - c4
					- u(3) * c2 * S)
					/ (4 * a4 - 5 * a2 * b2 + b4 - 5 * a2 * c2 - 2 * b2 * c2
					+ c4 - u(3) * b2 * S - u(3) * c2 * S);
			case 2982 -> a * (a + b - c) * (a - b + c)
					* (-a3 + a2 * b + a * b2 - b3 + 2 * a * b * c + a * c2
					+ b * c2)
					* (-a3 + a * b2 + a2 * c + 2 * a * b * c + b2 * c + a * c2
					- c3);
			case 2983 -> a2 * (a3 + a * b2 + 2 * b3 - a2 * c + b2 * c - a * c2 + c3)
					* (a3 - a2 * b - a * b2 + b3 + a * c2 + b * c2 + 2 * c3);
			case 2984 -> a2 * (a4 - 2 * a2 * b2 + b4 - a2 * c2 - b2 * c2)
					* (a4 - a2 * b2 - 2 * a2 * c2 - b2 * c2 + c4)
					* (-a6 + 2 * a4 * b2 - 3 * a2 * b4 + 2 * b6 + a4 * c2
					- 4 * a2 * b2 * c2 - 3 * b4 * c2 + a2 * c4
					+ 2 * b2 * c4 - c6)
					* (-a6 + a4 * b2 + a2 * b4 - b6 + 2 * a4 * c2
					- 4 * a2 * b2 * c2 + 2 * b4 * c2 - 3 * a2 * c4
					- 3 * b2 * c4 + 2 * c6);
			case 2985 -> (a3 - a2 * b - a * b2 + b3 + a2 * c + b2 * c)
					* (a3 + a2 * b - a2 * c - a * c2 + b * c2 + c3);
			case 2986 -> (a6 - a4 * b2 - a2 * b4 + b6 - 2 * a4 * c2 + 2 * a2 * b2 * c2
					- 2 * b4 * c2 + a2 * c4 + b2 * c4)
					* (a6 - 2 * a4 * b2 + a2 * b4 - a4 * c2 + 2 * a2 * b2 * c2
					+ b4 * c2 - a2 * c4 - 2 * b2 * c4 + c6);
			case 2987 -> a2 * (a4 - a2 * b2 + 2 * b4 - 2 * a2 * c2 - b2 * c2 + c4)
					* (a4 - 2 * a2 * b2 + b4 - a2 * c2 - b2 * c2 + 2 * c4);
			case 2988 -> (a6 - a5 * b - a4 * b2 + 2 * a3 * b3 - a2 * b4 - a * b5 + b6
					+ a4 * b * c - a3 * b2 * c - a2 * b3 * c + a * b4 * c
					- 2 * a4 * c2 + a3 * b * c2 + 2 * a2 * b2 * c2 + a * b3 * c2
					- 2 * b4 * c2 - a2 * b * c3 - a * b2 * c3 + a2 * c4
					+ b2 * c4)
					* (a6 - 2 * a4 * b2 + a2 * b4 - a5 * c + a4 * b * c
					+ a3 * b2 * c - a2 * b3 * c - a4 * c2 - a3 * b * c2
					+ 2 * a2 * b2 * c2 - a * b3 * c2 + b4 * c2
					+ 2 * a3 * c3 - a2 * b * c3 + a * b2 * c3 - a2 * c4
					+ a * b * c4 - 2 * b2 * c4 - a * c5 + c6);
			case 2989 -> (a5 - a3 * b2 - a2 * b3 + b5 - a4 * c + 2 * a2 * b2 * c
					- b4 * c - a3 * c2 - b3 * c2 + a2 * c3 + b2 * c3)
					* (a5 - a4 * b - a3 * b2 + a2 * b3 - a3 * c2
					+ 2 * a2 * b * c2 + b3 * c2 - a2 * c3 - b2 * c3
					- b * c4 + c5);
			case 2990 -> a * (a4 - 2 * a2 * b2 + b4 - a3 * c + a2 * b * c + a * b2 * c
					- b3 * c - a2 * c2 - b2 * c2 + a * c3 + b * c3)
					* (a4 - a3 * b - a2 * b2 + a * b3 + a2 * b * c + b3 * c
					- 2 * a2 * c2 + a * b * c2 - b2 * c2 - b * c3 + c4);
			case 2991 -> a * (a3 - a2 * b - a * b2 + b3 - 2 * a * b * c + a * c2
					+ b * c2)
					* (a3 + a * b2 - a2 * c - 2 * a * b * c + b2 * c - a * c2
					+ c3);
			case 2992 -> 1 / (2 * a10 - 4 * a8 * b2 + 2 * a6 * b4 - 2 * a4 * b6
					+ 4 * a2 * b8 - 2 * b10 - 4 * a8 * c2 + 9 * a6 * b2 * c2
					- 6 * a4 * b4 * c2 - 5 * a2 * b6 * c2 + 6 * b8 * c2
					+ 2 * a6 * c4 - 6 * a4 * b2 * c4 + 2 * a2 * b4 * c4
					- 4 * b6 * c4 - 2 * a4 * c6 - 5 * a2 * b2 * c6 - 4 * b4 * c6
					+ 4 * a2 * c8 + 6 * b2 * c8 - 2 * c10
					- u(3) * a4 * b2 * c2 * S - u(3) * a2 * b4 * c2 * S
					+ 2 * u(3) * b6 * c2 * S - u(3) * a2 * b2 * c4 * S
					- 4 * u(3) * b4 * c4 * S + 2 * u(3) * b2 * c6 * S);
			case 2993 -> 1 / (2 * a10 - 4 * a8 * b2 + 2 * a6 * b4 - 2 * a4 * b6
					+ 4 * a2 * b8 - 2 * b10 - 4 * a8 * c2 + 9 * a6 * b2 * c2
					- 6 * a4 * b4 * c2 - 5 * a2 * b6 * c2 + 6 * b8 * c2
					+ 2 * a6 * c4 - 6 * a4 * b2 * c4 + 2 * a2 * b4 * c4
					- 4 * b6 * c4 - 2 * a4 * c6 - 5 * a2 * b2 * c6 - 4 * b4 * c6
					+ 4 * a2 * c8 + 6 * b2 * c8 - 2 * c10
					+ u(3) * a4 * b2 * c2 * S + u(3) * a2 * b4 * c2 * S
					- 2 * u(3) * b6 * c2 * S + u(3) * a2 * b2 * c4 * S
					+ 4 * u(3) * b4 * c4 * S - 2 * u(3) * b2 * c6 * S);
			case 2994 -> (-a3 - a2 * b + a * b2 + b3 + a2 * c + b2 * c + a * c2
					- b * c2 - c3)
					* (-a3 + a2 * b + a * b2 - b3 - a2 * c - b2 * c + a * c2
					+ b * c2 + c3);
			case 2995 -> b * c * (-a3 - b3 - a * b * c + a * c2 + b * c2)
					* (-a3 + a * b2 - a * b * c + b2 * c - c3);
			case 2996 -> (-a2 + 3 * b2 - c2) * (-a2 - b2 + 3 * c2);
			case 2997 -> b * c * (-a3 - b3 + a * b * c + a * c2 + b * c2)
					* (-a3 + a * b2 + a * b * c + b2 * c - c3);
			case 2998 -> (a2 * b2 - a2 * c2 + b2 * c2)
					* (-(a2 * b2) + a2 * c2 + b2 * c2);
			case 2999 -> a * (a2 + 2 * a * b + b2 + 2 * a * c - 2 * b * c + c2);
			default -> Double.NaN;
		};
	}

	private double weight3000plus(int k, double a, double b, double c) {

		return switch (k) {
			case 3000 -> a * (a3 * b - 2 * a2 * b2 + a * b3 + a3 * c + 2 * a2 * b * c
					- a * b2 * c - 2 * b3 * c - 2 * a2 * c2 - a * b * c2
					+ 4 * b2 * c2 + a * c3 - 2 * b * c3);
			case 3001 -> a2 * (a2 * b4 - b6 + a2 * c4 - c6);
			case 3002 -> a2 * (a3 * b2 - a2 * b3 - a * b4 + b5 + a2 * b2 * c - b4 * c
					+ a3 * c2 + a2 * b * c2 - a2 * c3 - a * c4 - b * c4 + c5);
			case 3003 -> a2 * (a4 * b2 - 2 * a2 * b4 + b6 + a4 * c2 + 2 * a2 * b2 * c2
					- b4 * c2 - 2 * a2 * c4 - b2 * c4 + c6);
			case 3004 -> (b - c) * (a * b + b2 + a * c + c2);
			case 3005 -> a2 * (b2 - c2) * R;
			case 3006 -> a * b2 - b3 + a * c2 - c3;
			case 3007 -> a3 * b2 + a2 * b3 - a * b4 - b5 - 2 * a2 * b2 * c
					+ 2 * b4 * c + a3 * c2 - 2 * a2 * b * c2 + 2 * a * b2 * c2
					- b3 * c2 + a2 * c3 - b2 * c3 - a * c4 + 2 * b * c4 - c5;
			case 3008 -> 2 * a2 - a * b + b2 - a * c - 2 * b * c + c2;
			case 3009 -> a2 * (a * b2 - b2 * c + a * c2 - b * c2);
			case 3010 -> a2 * (a3 * b2 - 2 * a2 * b3 + a * b4 + a2 * b2 * c - b4 * c
					+ a3 * c2 + a2 * b * c2 - 2 * a * b2 * c2 + b3 * c2
					- 2 * a2 * c3 + b2 * c3 + a * c4 - b * c4);
			case 3011 -> 2 * a3 - a2 * b + b3 - a2 * c - b2 * c - b * c2 + c3;
			case 3012 -> 2 * a5 - a4 * b - 4 * a2 * b3 + 2 * a * b4 + b5 - a4 * c
					+ 4 * a2 * b2 * c - 3 * b4 * c + 4 * a2 * b * c2
					- 4 * a * b2 * c2 + 2 * b3 * c2 - 4 * a2 * c3 + 2 * b2 * c3
					+ 2 * a * c4 - 3 * b * c4 + c5;
			case 3013 -> a * (b + c)
					* (a6 - 2 * a4 * b2 + a2 * b4 + 2 * a4 * b * c - a2 * b3 * c
					- b5 * c - 2 * a4 * c2 + a2 * b2 * c2 - a2 * b * c3
					+ 2 * b3 * c3 + a2 * c4 - b * c5);
			case 3014 -> a6 * b2 - b8 + a6 * c2 - 4 * a4 * b2 * c2 + a2 * b4 * c2
					+ 2 * b6 * c2 + a2 * b2 * c4 - 2 * b4 * c4 + 2 * b2 * c6
					- c8;
			case 3015 -> 2 * a7 - 2 * a5 * b2 - 2 * a4 * b3 + a3 * b4 + a2 * b5
					- a * b6 + b7 + 2 * a4 * b2 * c - a2 * b4 * c - b6 * c
					- 2 * a5 * c2 + 2 * a4 * b * c2 + a * b4 * c2 - 3 * b5 * c2
					- 2 * a4 * c3 + 3 * b4 * c3 + a3 * c4 - a2 * b * c4
					+ a * b2 * c4 + 3 * b3 * c4 + a2 * c5 - 3 * b2 * c5 - a * c6
					- b * c6 + c7;
			case 3016 -> a2 * (a6 * b2 - 2 * a4 * b4 + a2 * b6 + a6 * c2 - 2 * b6 * c2
					- 2 * a4 * c4 + 4 * b4 * c4 + a2 * c6 - 2 * b2 * c6);
			case 3017 -> a4 + 3 * a3 * b + a2 * b2 + b4 + 3 * a3 * c + 3 * a2 * b * c
					+ a2 * c2 - 2 * b2 * c2 + c4;
			case 3018 -> 2 * a8 - 2 * a6 * b2 - a4 * b4 + b8 - 2 * a6 * c2
					+ 4 * a4 * b2 * c2 - 4 * b6 * c2 - a4 * c4 + 6 * b4 * c4
					- 4 * b2 * c6 + c8;
			case 3019 -> 3 * a6 - a5 * b - 4 * a4 * b2 - a3 * b3 + 2 * a2 * b4
					+ 2 * a * b5 - b6 - a5 * c - a4 * b * c - a3 * b2 * c
					- a2 * b3 * c + 2 * a * b4 * c + 2 * b5 * c - 4 * a4 * c2
					- a3 * b * c2 - 2 * a2 * b2 * c2 - 4 * a * b3 * c2 + b4 * c2
					- a3 * c3 - a2 * b * c3 - 4 * a * b2 * c3 - 4 * b3 * c3
					+ 2 * a2 * c4 + 2 * a * b * c4 + b2 * c4 + 2 * a * c5
					+ 2 * b * c5 - c6;
			case 3020 -> a * p(b - c, 2) * (a + b - c) * (a - b + c);
			case 3021 -> (a - b - c)
					* p(2 * a2 - a * b + b2 - a * c - 2 * b * c + c2, 2);
			case 3022 -> a2 * p(a - b - c, 3) * p(b - c, 2);
			case 3023 -> (a - b - c) * p(b - c, 2) * p(a2 + b * c, 2);
			case 3024 -> a2 * (a - b - c) * p(b - c, 2) * p(a2 - b2 - b * c - c2, 2);
			case 3025 -> a2 * (a - b - c) * p(b - c, 2) * p(a2 - b2 + b * c - c2, 2);
			case 3026 -> (a - b - c) * p(b - c, 2)
					* p(a2 + a * b + a * c + 2 * b * c, 2);
			case 3027 -> (a + b - c) * (a - b + c) * p(b + c, 2) * p(a2 - b * c, 2);
			case 3028 -> a2 * (a + b - c) * (a - b + c) * p(b + c, 2)
					* p(a2 - b2 + b * c - c2, 2);
			case 3029 -> p(b + c, 2) * (a3 - a * b2 - b3 + a2 * c)
					* (a3 + a2 * b - a * c2 - c3);
			case 3030 -> a2 * (a * b + b2 + a * c - 2 * b * c - c2)
					* (a * b - b2 + a * c - 2 * b * c + c2);
			case 3031 -> a2 * p(b + c, 2) * (a3 + b3 + a2 * c - b2 * c - a * c2 - c3)
					* (a3 + a2 * b - a * b2 - b3 - b * c2 + c3);
			case 3032 -> a * (a2 * b - b3 + a2 * c - a * b * c - b2 * c + a * c2)
					* (a2 * b + a * b2 + a2 * c - a * b * c - b * c2 - c3);
			case 3033 -> a2 * (a2 * b + b3 + a2 * c - b2 * c - b * c2 - c3)
					* (a2 * b - b3 + a2 * c - b2 * c - b * c2 + c3);
			case 3034 -> a * (a2 * b + b3 + a2 * c - a * b * c - b2 * c - a * c2)
					* (a2 * b - a * b2 + a2 * c - a * b * c - b * c2 + c3);
			case 3035 -> 2 * a3 - 2 * a2 * b - a * b2 + b3 - 2 * a2 * c
					+ 4 * a * b * c - b2 * c - a * c2 - b * c2 + c3;
			case 3036 -> (a - b - c) * (2 * a3 - 2 * a2 * b - a * b2 + 3 * b3
					- 2 * a2 * c + 4 * a * b * c - 3 * b2 * c - a * c2
					- 3 * b * c2 + 3 * c3);
			case 3037 -> a * (a - b - c)
					* (a4 * b4 + a3 * b5 - a3 * b4 * c - 2 * a2 * b4 * c2
					- 2 * a * b5 * c2 + 2 * a * b4 * c3 + a4 * c4
					- a3 * b * c4 - 2 * a2 * b2 * c4 + 2 * a * b3 * c4
					+ 2 * b4 * c4 + a3 * c5 - 2 * a * b2 * c5);
			case 3038 -> a * (a - b - c)
					* (a2 * b2 + a * b3 - 3 * a * b2 * c - 2 * b3 * c + a2 * c2
					- 3 * a * b * c2 + 6 * b2 * c2 + a * c3
					- 2 * b * c3);
			case 3039 -> (a - b - c) * (2 * a3 - 2 * a2 * b + 3 * a * b2 - b3
					- 2 * a2 * c - 4 * a * b * c + b2 * c + 3 * a * c2 + b * c2
					- c3);
			case 3040 -> a * (a - b - c) * (a6 * b2 + a5 * b3 - 2 * a4 * b4
					- 2 * a3 * b5 + a2 * b6 + a * b7 - 3 * a5 * b2 * c
					+ 2 * a4 * b3 * c + 6 * a3 * b4 * c - 4 * a2 * b5 * c
					- 3 * a * b6 * c + 2 * b7 * c + a6 * c2 - 3 * a5 * b * c2
					+ 2 * a4 * b2 * c2 - 4 * a3 * b3 * c2 - a2 * b4 * c2
					+ 7 * a * b5 * c2 - 2 * b6 * c2 + a5 * c3 + 2 * a4 * b * c3
					- 4 * a3 * b2 * c3 + 8 * a2 * b3 * c3 - 5 * a * b4 * c3
					- 2 * b5 * c3 - 2 * a4 * c4 + 6 * a3 * b * c4 - a2 * b2 * c4
					- 5 * a * b3 * c4 + 4 * b4 * c4 - 2 * a3 * c5
					- 4 * a2 * b * c5 + 7 * a * b2 * c5 - 2 * b3 * c5 + a2 * c6
					- 3 * a * b * c6 - 2 * b2 * c6 + a * c7 + 2 * b * c7);
			case 3041 -> a * (a - b - c)
					* (a4 * b2 - a3 * b3 - a2 * b4 + a * b5 - a3 * b2 * c
					- a * b4 * c + 2 * b5 * c + a4 * c2 - a3 * b * c2
					+ 4 * a2 * b2 * c2 - 2 * b4 * c2 - a3 * c3 - a2 * c4
					- a * b * c4 - 2 * b2 * c4 + a * c5 + 2 * b * c5);
			case 3042 -> a * (a6 * b2 - a5 * b3 - 2 * a4 * b4 + 2 * a3 * b5 + a2 * b6
					- a * b7 - a5 * b2 * c + 2 * a4 * b3 * c + 2 * a3 * b4 * c
					- 4 * a2 * b5 * c - a * b6 * c + 2 * b7 * c + a6 * c2
					- a5 * b * c2 + 2 * a4 * b2 * c2 - 4 * a3 * b3 * c2
					- a2 * b4 * c2 + 5 * a * b5 * c2 - 2 * b6 * c2 - a5 * c3
					+ 2 * a4 * b * c3 - 4 * a3 * b2 * c3 + 8 * a2 * b3 * c3
					- 3 * a * b4 * c3 - 2 * b5 * c3 - 2 * a4 * c4
					+ 2 * a3 * b * c4 - a2 * b2 * c4 - 3 * a * b3 * c4
					+ 4 * b4 * c4 + 2 * a3 * c5 - 4 * a2 * b * c5
					+ 5 * a * b2 * c5 - 2 * b3 * c5 + a2 * c6 - a * b * c6
					- 2 * b2 * c6 - a * c7 + 2 * b * c7);
			case 3043 -> a4 * U * p(a2 - b2 - b * c - c2, 2)
					* p(a2 - b2 + b * c - c2, 2) * V;
			case 3044 -> a2 * (a4 - a2 * b2 + b4 - a2 * c2)
					* (a4 - a2 * b2 - a2 * c2 + c4);
			case 3045 -> a3 * (a3 - a2 * b - a * b2 + b3 + a * b * c - a * c2)
					* (a3 - a * b2 - a2 * c + a * b * c - a * c2 + c3);
			case 3046 -> a4 * (a3 - a2 * b - a * b2 + b3 + b * c2 - c3)
					* (a3 - b3 - a2 * c + b2 * c - a * c2 + c3);
			case 3047 -> a4 * (a4 - 2 * a2 * b2 + b4 + b2 * c2 - c4)
					* (a4 - b4 - 2 * a2 * c2 + b2 * c2 + c4);
			case 3048 -> a4 * (a4 - 4 * a2 * b2 + b4 + 3 * b2 * c2 - c4)
					* (a4 - b4 - 4 * a2 * c2 + 3 * b2 * c2 + c4);
			case 3049 -> a4 * (b2 - c2) * T;
			case 3050 -> a2 * (b2 - c2) * (a4 - a2 * b2 - a2 * c2 - b2 * c2);
			case 3051 -> a4 * R;
			case 3052 -> a2 * (3 * a - b - c);
			case 3053 -> a2 * (3 * a2 - b2 - c2);
			default -> Double.NaN;
		};
	}
}
