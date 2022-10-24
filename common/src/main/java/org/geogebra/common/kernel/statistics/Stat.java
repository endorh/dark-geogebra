package org.geogebra.common.kernel.statistics;

import org.geogebra.common.kernel.commands.Commands;
import org.geogebra.common.main.Localization;

import com.himamis.retex.editor.share.util.Unicode;

public enum Stat {
	NULL(null), LENGTH(Commands.Length), MEAN(Commands.mean, "\\overline{%v}"),
	SD(Commands.SD, "%t%v"), SAMPLE_SD(Commands.SampleSD, "%t%v"),
	SUM(Commands.Sum, "\\Sigma %v"),
	SIGMAXX(Commands.SigmaXX, "\\Sigma %v" + Unicode.SUPERSCRIPT_2),
	MIN(Commands.Min, "%t(%v)"), Q1(Commands.Quartile1), MEDIAN(Commands.Median),
	Q3(Commands.Q3), MAX(Commands.Max, "%t(%v)"), MEANX(Commands.MeanX),
	MEANY(Commands.MeanY), SX(Commands.SampleSDX), SY(Commands.SampleSDY), PMCC(Commands.PMCC),
	SPEARMAN(Commands.Spearman), SXX(Commands.SXX), SYY(Commands.SYY), SXY(Commands.SXY),
	RSQUARE(Commands.RSquare), SSE(Commands.SumSquaredErrors),
	SIGMAXY(Commands.SigmaXY, "\\Sigma %v"),
	COVARIANCE(Commands.Covariance, "cov");

	private final Commands cmd;
	private final String lhsPattern;

	Stat(Commands cmd) {
		this.cmd = cmd;
		lhsPattern = "%t";
	}

	Stat(Commands cmd, String pattern) {
		this.cmd = cmd;
		this.lhsPattern = pattern;
	}

	public String getCommandName() {
		return cmd.name();
	}

	/**
	 * Used by classic data analysis
	 * @return translation key
	 */
	public String getTranslationKey() {
		return switch (this) {
			case LENGTH -> "Length.short";
			case MEAN -> "Mean";
			case SD -> "StandardDeviation.short";
			case SAMPLE_SD -> "SampleStandardDeviation.short";
			case SUM -> "Sum";
			case SIGMAXX -> "Sum2";
			case MIN -> "Minimum.short";
			case Q1 -> "LowerQuartile.short";
			case MEDIAN -> "Median";
			case Q3 -> "UpperQuartile.short";
			case MAX -> "Maximum.short";
			case MEANX -> "MeanX";
			case MEANY -> "MeanY";
			case SX -> "Sx";
			case SY -> "Sy";
			case PMCC -> "CorrelationCoefficient.short";
			case SPEARMAN -> "Spearman.short";
			case SXX -> "Sxx";
			case SYY -> "Syy";
			case SXY -> "Sxy";
			case RSQUARE -> "RSquare.Short";
			case SSE -> "SumSquaredErrors.short";
			default -> null;
		};
	}

	public String getLocalizedName(Localization loc) {
		return loc.getCommand(getCommandName());
	}

	/**
	 * @param loc localization
	 * @param variableName variable name
	 * @return localized LHS for value row in stats dialog
	 */
	public String getLHS(Localization loc, String variableName) {
		return lhsPattern.replace("%v", variableName)
					.replace("%t", loc.getMenu(getTranslationKey()));
	}
}
