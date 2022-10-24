package org.geogebra.desktop.gui.theme;

public class InversionPreferences {
	private boolean invertColors = false;
	private boolean invertIcons = false;
	private boolean invertImages = false;

	private ThemeInvertOptions options = new ThemeInvertOptions();

	public boolean isInvertColors() {
		return invertColors;
	}

	public void setInvertColors(boolean invertColors) {
		this.invertColors = invertColors;
	}

	public boolean isInvertIcons() {
		return invertIcons;
	}

	public void setInvertIcons(boolean invertIcons) {
		this.invertIcons = invertIcons;
	}

	public boolean isInvertImages() {
		return invertImages;
	}

	public void setInvertImages(boolean invertImages) {
		this.invertImages = invertImages;
	}

	public ThemeInvertOptions getOptions() {
		return options;
	}

	public void setOptions(ThemeInvertOptions options) {
		this.options = options;
	}
}
