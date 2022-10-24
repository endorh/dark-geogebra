package org.geogebra.desktop.gui.theme;

public class ThemeInvertOptions {
	private float invertStrength = 1;
	private float minBrightness = 0;
	private float maxBrightness = 1;

	public float getInvertStrength() {
		return invertStrength;
	}

	public void setInvertStrength(float strength) {
		this.invertStrength = strength;
	}

	public float getMinBrightness() {
		return minBrightness;
	}

	public void setMinBrightness(float brightness) {
		this.minBrightness = brightness;
	}

	public float getMaxBrightness() {
		return maxBrightness;
	}

	public void setMaxBrightness(float brightness) {
		this.maxBrightness = brightness;
	}
}
