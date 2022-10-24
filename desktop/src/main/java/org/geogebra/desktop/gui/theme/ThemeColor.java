package org.geogebra.desktop.gui.theme;

import java.awt.Color;

/**
 * Mutable color updated by {@link ThemeD} in response to a theme change.
 */
public class ThemeColor extends Color {
	private int argb;

	public ThemeColor(int r, int g, int b) {
		this((r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF);
	}

	public ThemeColor(int r, int g, int b, int a) {
		this((a & 0xFF) << 24 | (r & 0xFF) << 16 | (g & 0xFF) << 8 | b & 0xFF);
	}

	public ThemeColor(int rgb) {
		this(rgb, false);
	}

	public ThemeColor(int argb, boolean hasAlpha) {
		super(argb, hasAlpha);
		if (!hasAlpha) argb = argb | 0xFF000000;
		this.argb = argb;
	}

	public void setARGB(int argb) {
		this.argb = argb;
	}

	@Override
	public int getRed() {
		return argb >> 16 & 0xFF;
	}

	@Override
	public int getGreen() {
		return argb >> 8 & 0xFF;
	}

	@Override
	public int getBlue() {
		return argb & 0xFF;
	}

	@Override
	public int getAlpha() {
		return argb >> 24 & 0xFF;
	}

	@Override
	public int getRGB() {
		return argb;
	}

	@Override
	public int hashCode() {
		return Integer.hashCode(argb);
	}

	@Override
	public boolean equals(Object obj) {
		return obj instanceof ThemeColor && ((ThemeColor) obj).getRGB() == getRGB();
	}

	@Override
	public String toString() {
		return getClass().getName() + "[r=" + getRed() + ",g=" + getGreen() + ",b=" + getBlue() + ",a=" + getAlpha() + "]";
	}
}
