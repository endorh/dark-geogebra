package org.geogebra.desktop.cas.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.geogebra.common.awt.GColor;
import org.geogebra.common.main.App;
import org.geogebra.desktop.awt.GColorD;
import org.geogebra.desktop.gui.theme.ColorKeys;
import org.geogebra.desktop.gui.theme.ThemeD;
import org.geogebra.desktop.main.AppD;

/**
 * Panel for CAS output, can contain LaTeX or normal output
 */
public class CASOutputPanel extends JPanel {

	private static final long serialVersionUID = 1L;

	// public static final int INDENT = 20; // pixel

	/**
	 * The text color of the output
	 */

	private static Color ERROR_COLOR = ThemeD.color(ColorKeys.TEXT_ERROR);
	private static Color OUTPUT_PREFIX_COLOR = ThemeD.color(ColorKeys.OUTLINE);

	private JLabel outputSign;
	private JLabel outputArea;
	private LaTeXPanel latexPanel;
	private AppD app;
	private JPanel centerPanel;

	/**
	 * @param app
	 *            application
	 */
	public CASOutputPanel(AppD app) {
		this.app = app;
		setBackground(ThemeD.color(ColorKeys.BACKGROUND));
		setLayout(new BorderLayout(5, 0));

		outputSign = new JLabel();
		outputSign.setForeground(OUTPUT_PREFIX_COLOR);

		outputArea = new JLabel();
		latexPanel = new LaTeXPanel(app);
		// will be overwritten later
		latexPanel.setForeground(ThemeD.color(ColorKeys.FOREGROUND));
		latexPanel.setBackground(ThemeD.color(ColorKeys.BACKGROUND));

		add(outputSign, app.getLocalization().borderWest());
		centerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		centerPanel.setBackground(ThemeD.color(ColorKeys.BACKGROUND));
		centerPanel.add(outputArea);
		centerPanel.add(latexPanel);
		add(centerPanel, BorderLayout.CENTER);
	}

	/**
	 * @param c
	 *            foreground color
	 */
	public void setForeground(GColor c) {
		outputArea.setForeground(GColorD.getAwtAlgebraColor(c));
		latexPanel.setForeground(GColorD.getRawAwtColor(c));
	}

	/**
	 * @param output
	 *            plain output (used when latexOutput is null or isError)
	 * @param latexOutput
	 *            LaTeX output (used when not null and !isError)
	 * @param cmd
	 *            top level command
	 * @param isError
	 *            whether outpput is error
	 * @param c
	 *            color
	 */
	public void setOutput(String output, String latexOutput, String cmd,
			boolean isError, GColor c, App app) {
		boolean useLaTeXpanel = latexOutput != null && !isError;
		outputArea.setVisible(!useLaTeXpanel);
		latexPanel.setVisible(useLaTeXpanel);
		setForeground(c);
		if (useLaTeXpanel) {
			latexPanel.setLaTeX(latexOutput);
		} else {
			outputArea.setText(output);
			if (isError) {
				outputArea.setForeground(ERROR_COLOR);
			}
		}

		outputSign.setText(cmd);
	}

	@Override
	final public void setFont(Font ft) {
		super.setFont(ft);

		if (ft == null) {
			return;
		}

		if (latexPanel != null) {
			latexPanel.setFont(ft.deriveFont(ft.getSize() + 2f));
		}

		if (outputArea != null) {
			outputArea.setFont(ft);
		}
		if (outputSign != null) {
			outputSign.setFont(ft);
		}
	}

	public void setOrientation() {
		remove(outputSign);
		add(outputSign, app.getLocalization().borderWest());
		app.setFlowLayoutOrientation(centerPanel);
	}
}