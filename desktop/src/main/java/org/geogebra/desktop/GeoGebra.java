/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

 */

package org.geogebra.desktop;

import java.awt.Frame;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.ImageProducer;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

import org.geogebra.common.GeoGebraConstants;
import org.geogebra.common.main.GeoGebraPreferencesXML;
import org.geogebra.common.util.Util;
import org.geogebra.common.util.debug.Log;
import org.geogebra.desktop.gui.app.GeoGebraFrame;
import org.geogebra.desktop.gui.theme.ThemeD;
import org.geogebra.desktop.main.AppD;
import org.geogebra.desktop.main.GeoGebraPreferencesD;
import org.geogebra.desktop.main.GeoGebraServer;
import org.geogebra.desktop.util.ImageManagerD;

public class GeoGebra {

	private static Frame splashFrame = null;

	protected GeoGebra() {
	}

	public static void main(String[] cmdArgs) {
		(new GeoGebra()).doMain(cmdArgs);
	}

	/**
	 * calculate the default font size and according to some heuristics
	 * 
	 * @param screenDPI
	 *            eg 96 for regular screen
	 *            https://technet.microsoft.com/en-GB/library/dn528846.aspx
	 * @param screenResX
	 *            horizontal screen size
	 * @param screenResY
	 *            vertical screen size
	 */
	public static void setDefaults(int screenDPI, int screenResX,
			int screenResY) {

		int fontSize = (int) Math.round(screenDPI / 8.0);

		GeoGebraPreferencesXML
				.setDefaultFontSize(Util.getValidFontSize(fontSize));

		// 96 corresponds to 100%
		// 192 to 200%
		double sf = screenDPI / 96.0;
		GeoGebraPreferencesXML.setDefaultWindowX((int) (800.0 * sf));
		GeoGebraPreferencesXML.setDefaultWindowY((int) (600.0 * sf));
	}

	protected void doMain(String[] cmdArgs) {

		CommandLineArguments args = new CommandLineArguments(cmdArgs);

		if (args.containsArg("screenDPI") && args.containsArg("screenX")
				&& args.containsArg("screenY")) {
			int screenDPI = Integer.parseInt(args.getStringValue("screenDPI"));
			int screenX = Integer.parseInt(args.getStringValue("screenX"));
			int screenY = Integer.parseInt(args.getStringValue("screenY"));

			setDefaults(screenDPI, screenX, screenY);
		}

		boolean showSplash = true;
		if (!args.getBooleanValue("showSplash", true)) {
			showSplash = false;
		}
		if (args.containsArg("startHttpServer")) {
			Log.error("startHttpServer");
			new GeoGebraServer().start();
			return;
		}
		if (args.containsArg("help") || args.containsArg("proverhelp")
				|| args.containsArg("v")
				|| args.containsArg("regressionFile")) {
			showSplash = false;
		}

		// Theme settings must be loaded before the splash
		GeoGebraPreferencesD.loadThemePreferences();
		if (args.containsArg("theme")) {
			String name = args.getStringValue("theme");
			try {
				ThemeD.setTheme(name);
			} catch (IllegalArgumentException ignored) {}
		}

		if (showSplash) {
			// Show splash screen
			URL imageURL = GeoGebra.class.getResource(
					"/org/geogebra/desktop/" + GeoGebraConstants.SPLASH_STRING);
			try {
				if (imageURL != null) {
					ImageProducer source = (ImageProducer) imageURL.getContent();
					if (ThemeD.getTheme().isDarkTheme()) {
						source = ImageManagerD.addInvertFilter(source);
					}
					Image im = Toolkit.getDefaultToolkit().createImage(source);
					splashFrame = SplashWindow.splash(im);
				} else {
					throw new FileNotFoundException("Could not find splash image");
				}
			} catch (IOException e) {
				System.err.println("Splash image not found");
			}
		}

		// Start GeoGebra
		try {
			startGeoGebra(args);
		} catch (Throwable e) {
			e.printStackTrace();
			System.err.flush();
			AppD.exit(10);
		}

		// Hide splash screen
		if (splashFrame != null) {
			splashFrame.setVisible(false);
		}
	}

	protected void startGeoGebra(CommandLineArguments args) {
		// create and open first GeoGebra window
		GeoGebraFrame.main(args);
	}

	public static void hideSplash() {
		if (splashFrame != null) {
			splashFrame.setVisible(false);
		}
	}

}