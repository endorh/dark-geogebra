package com.himamis.retex.renderer.share;

public enum Unit {

	EM, 	// 1 em = the width of the capital 'M' in the current font
	EX, 	// 1 ex = the height of the character 'x' in the current font
	PIXEL, 	//
	POINT, 	// postscript point
	PICA, 	// 1 pica = 12 point
	MU, 	// 1 mu = 1/18 em (em taken from the "mufont")
	CM, 	// 1 cm = 28.346456693 point
	MM, 	// 1 mm = 2.8346456693 point
	IN, 	// 1 in = 72 point
	SP, 	// 1 sp = 65536 point
	PT, 	// 1 pt = 1/72.27 in(or Standard Anglo-American point)
	DD, 	//
	CC, 	//
	X8, 	// 1 x8 = 1 default rule thickness
	NONE;

	public double getFactor(TeXEnvironment env) {
		switch (this) {
		case EM:
			return env.getTeXFont().getEM(env.getStyle());
		case EX:
			return env.getTeXFont().getXHeight(env.getStyle(),
					env.getLastFont());
		case PIXEL:
			return 1. / env.getSize();
		case POINT:
			return TeXFormula.PIXELS_PER_POINT / env.getSize();
		case PICA:
			return (12. * TeXFormula.PIXELS_PER_POINT) / env.getSize();
		case MU:
			final TeXFont tf = env.getTeXFont();
			return tf.getQuad(env.getStyle(), TeXFont.MUFONT) / 18.;
		case CM:
			return (28.346456693 * TeXFormula.PIXELS_PER_POINT) / env.getSize();
		case MM:
			return (2.8346456693 * TeXFormula.PIXELS_PER_POINT) / env.getSize();
		case IN:
			return (72. * TeXFormula.PIXELS_PER_POINT) / env.getSize();
		case SP:
			return (65536. * TeXFormula.PIXELS_PER_POINT) / env.getSize();
		case PT:
			return (0.9962640099 * TeXFormula.PIXELS_PER_POINT) / env.getSize();
		case DD:
			return (1.0660349422 * TeXFormula.PIXELS_PER_POINT) / env.getSize();
		case CC:
			return (12.7924193070 * TeXFormula.PIXELS_PER_POINT)
					/ env.getSize();
		case X8:
			return env.getTeXFont().getDefaultRuleThickness(env.getStyle());
		case NONE:
			return 1.;
		default:
			return 0.;
		}
	}

	@Override
	public String toString() {
		return switch (this) {
			case EM -> "em";
			case EX -> "ex";
			case PIXEL -> "pixel";
			case POINT -> "bp";
			case PICA -> "pica";
			case MU -> "mu";
			case CM -> "cm";
			case MM -> "mm";
			case IN -> "in";
			case SP -> "sp";
			case PT -> "pt";
			case DD -> "dd";
			case CC -> "cc";
			case X8 -> "x8";
			default -> "";
		};
	}
}
