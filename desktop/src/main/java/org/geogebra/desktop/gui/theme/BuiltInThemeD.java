package org.geogebra.desktop.gui.theme;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

public class BuiltInThemeD extends ThemeD {
	public static final ThemeD LEGACY = Builder.of(
					"Legacy", getLookAndFeel(UIManager.getSystemLookAndFeelClassName()),
					false, new Color(0xFFFFFF), new Color(0x000000))
			.set(ColorKeys.BACKGROUND_SELECTED, new Color(210, 210, 225))
			.set(ColorKeys.SELECTION, new Color(0x4080FF))
			.set(ColorKeys.SELECTION_DISABLED, new Color(210, 210, 225))
			.set(ColorKeys.OUTLINE_LIGHT, Color.LIGHT_GRAY)
			.set(ColorKeys.OUTLINE, Color.GRAY)
			.set(ColorKeys.OUTLINE_DARK, Color.DARK_GRAY)
			.set(ColorKeys.CONTROL_SHADOW, Color.LIGHT_GRAY)
			.set(ColorKeys.CONTROL_HIGHLIGHT, Color.LIGHT_GRAY)
			.set(ColorKeys.BACKGROUND_CONTROL, Color.LIGHT_GRAY.brighter())
			.set(ColorKeys.ERROR, new Color(190, 40, 40))
			.set(ColorKeys.TEXT_ERROR, Color.RED)
			.set(ColorKeys.TEXT_DISABLED, Color.GRAY)
			.set(ColorKeys.BACKGROUND_SURFACE, Color.LIGHT_GRAY)
			.set(ColorKeys.TEXT_SURFACE, Color.WHITE)
			.build();
	public static final ThemeD CROSS_PLATFORM_LEGACY = Builder.of(
			"Cross Platform Legacy",
			getLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName()),
			false, new Color(0xFFFFFF), new Color(0x000000))
			.set(ColorKeys.BACKGROUND_SELECTED, new Color(210, 210, 225))
			.set(ColorKeys.SELECTION, new Color(0x4080FF))
			.set(ColorKeys.SELECTION_DISABLED, new Color(210, 210, 225))
			.set(ColorKeys.OUTLINE_LIGHT, Color.LIGHT_GRAY)
			.set(ColorKeys.OUTLINE, Color.GRAY)
			.set(ColorKeys.OUTLINE_DARK, Color.DARK_GRAY)
			.set(ColorKeys.CONTROL_SHADOW, Color.LIGHT_GRAY)
			.set(ColorKeys.CONTROL_HIGHLIGHT, Color.LIGHT_GRAY)
			.set(ColorKeys.BACKGROUND_CONTROL, Color.LIGHT_GRAY.brighter())
			.set(ColorKeys.ERROR, new Color(190, 40, 40))
			.set(ColorKeys.TEXT_ERROR, Color.RED)
			.set(ColorKeys.TEXT_DISABLED, Color.GRAY)
			.set(ColorKeys.BACKGROUND_SURFACE, Color.LIGHT_GRAY)
			.set(ColorKeys.TEXT_SURFACE, Color.WHITE)
			.build();
	public static final ThemeD FLAT_LIGHT = Builder.of(
			"Flat Light", new FlatLightLaf(),
			false, new Color(0xF2F2F2), new Color(0x000000))
			.set(ColorKeys.BACKGROUND_SELECTED, new Color(210, 210, 225))
			.set(ColorKeys.SELECTION, new Color(0x4080FF))
			.set(ColorKeys.SELECTION_DISABLED, new Color(210, 210, 225))
			.set(ColorKeys.OUTLINE_LIGHT, Color.LIGHT_GRAY)
			.set(ColorKeys.OUTLINE, Color.GRAY)
			.set(ColorKeys.OUTLINE_DARK, Color.DARK_GRAY)
			.set(ColorKeys.CONTROL_SHADOW, Color.LIGHT_GRAY)
			.set(ColorKeys.CONTROL_HIGHLIGHT, Color.LIGHT_GRAY)
			.set(ColorKeys.BACKGROUND_CONTROL, Color.LIGHT_GRAY.brighter())
			.set(ColorKeys.ERROR, new Color(190, 40, 40))
			.set(ColorKeys.TEXT_ERROR, Color.RED)
			.set(ColorKeys.TEXT_DISABLED, Color.GRAY)
			.set(ColorKeys.BACKGROUND_SURFACE, Color.LIGHT_GRAY)
			.set(ColorKeys.TEXT_SURFACE, Color.WHITE)
			.build();
	public static final ThemeD INTELLI_J_LIGHT = Builder.of(
					"IntelliJ Light", new FlatIntelliJLaf(),
					false, new Color(0xFFFFFF), new Color(0x000000))
			.set(ColorKeys.BACKGROUND_SELECTED, new Color(210, 210, 225))
			.set(ColorKeys.SELECTION, new Color(0x4080FF))
			.set(ColorKeys.SELECTION_DISABLED, new Color(210, 210, 225))
			.set(ColorKeys.OUTLINE_LIGHT, Color.LIGHT_GRAY)
			.set(ColorKeys.OUTLINE, Color.GRAY)
			.set(ColorKeys.OUTLINE_DARK, Color.DARK_GRAY)
			.set(ColorKeys.CONTROL_SHADOW, Color.LIGHT_GRAY)
			.set(ColorKeys.CONTROL_HIGHLIGHT, Color.LIGHT_GRAY)
			.set(ColorKeys.BACKGROUND_CONTROL, Color.LIGHT_GRAY.brighter())
			.set(ColorKeys.ERROR, new Color(190, 40, 40))
			.set(ColorKeys.TEXT_ERROR, Color.RED)
			.set(ColorKeys.TEXT_DISABLED, Color.GRAY)
			.set(ColorKeys.BACKGROUND_SURFACE, Color.LIGHT_GRAY)
			.set(ColorKeys.TEXT_SURFACE, Color.WHITE)
			.build();
	public static final ThemeD FLAT_DARK = Builder.of(
			"Flat Dark", new FlatDarkLaf(),
			true, new Color(0x3c3f41), new Color(0xBBBBBB))
			.set(ColorKeys.BACKGROUND_ALGEBRA, new Color(0x424246))
			.set(ColorKeys.BACKGROUND_SELECTED, new Color(0x4C4F51))
			.set(ColorKeys.BACKGROUND_SURFACE, new Color(0x505560))
			.set(ColorKeys.SELECTION, new Color(0x4080FF))
			.set(ColorKeys.SELECTION_DISABLED, new Color(0x505050))
			.set(ColorKeys.OUTLINE_LIGHT, new Color(0x707070))
			.set(ColorKeys.OUTLINE, new Color(0x808080))
			.set(ColorKeys.OUTLINE_DARK, new Color(0x909090))
			.set(ColorKeys.CONTROL_SHADOW, new Color(0x5C5F61))
			.set(ColorKeys.CONTROL_HIGHLIGHT, new Color(0x707070))
			.set(ColorKeys.BACKGROUND_CONTROL, new Color(0x5C5F61))
			.set(ColorKeys.TEXT_SURFACE, new Color(0xDDDDDD))
			.set(ColorKeys.TEXT_DISABLED, new Color(0x808080))
			.set(ColorKeys.TEXT_ERROR, new Color(0xFF5050))
			.set(ColorKeys.ERROR, new Color(0xBE2828))
			.configureInversion(p -> {
				p.setInvertColors(true);
				p.setInvertIcons(true);
				p.setInvertImages(true);
				ThemeInvertOptions options = p.getOptions();
				options.setInvertStrength(0.95F);
				options.setMinBrightness(0.15F);
				options.setMaxBrightness(1F);
			})
			.build();
	public static final ThemeD DARCULA = Builder.of(
					"Darcula", new FlatDarculaLaf(),
					true, new Color(0x3C3F41), new Color(0xBBBBBB))
			.set(ColorKeys.BACKGROUND_ALGEBRA, new Color(0x424246))
			.set(ColorKeys.BACKGROUND_SELECTED, new Color(0x4C4F51))
			.set(ColorKeys.BACKGROUND_SURFACE, new Color(0x505560))
			.set(ColorKeys.SELECTION, new Color(0x4080FF))
			.set(ColorKeys.SELECTION_DISABLED, new Color(0x505050))
			.set(ColorKeys.OUTLINE_LIGHT, new Color(0x707070))
			.set(ColorKeys.OUTLINE, new Color(0x808080))
			.set(ColorKeys.OUTLINE_DARK, new Color(0x909090))
			.set(ColorKeys.CONTROL_SHADOW, new Color(0x5C5F61))
			.set(ColorKeys.CONTROL_HIGHLIGHT, new Color(0x707070))
			.set(ColorKeys.BACKGROUND_CONTROL, new Color(0x5C5F61))
			.set(ColorKeys.TEXT_SURFACE, new Color(0xDDDDDD))
			.set(ColorKeys.TEXT_DISABLED, new Color(0x808080))
			.set(ColorKeys.TEXT_ERROR, new Color(0xFF5050))
			.set(ColorKeys.ERROR, new Color(0xBE2828))
			.configureInversion(p -> {
				p.setInvertColors(true);
				p.setInvertIcons(true);
				p.setInvertImages(true);
				ThemeInvertOptions options = p.getOptions();
				options.setInvertStrength(0.95F);
				options.setMinBrightness(0.15F);
				options.setMaxBrightness(1F);
			})
			.build();
	public static final ThemeD DEFAULT = LEGACY;

	public static final ThemeD[] BUILT_IN_THEMES = {
			LEGACY, CROSS_PLATFORM_LEGACY, FLAT_LIGHT, INTELLI_J_LIGHT,
			FLAT_DARK, DARCULA
	};

	private final Map<Key, Color> colors;
	private final boolean isDark;

	public BuiltInThemeD(String name, LookAndFeel lookAndFeel, Map<Key, Color> colors, boolean isDark) {
		super(name, lookAndFeel);
		this.colors = colors;
		this.isDark = isDark;
	}

	@Override
	public int getColor(Key key) {
		Color color = colors.get(key);
		Key k = key.getParent();
		while (color == null && k != null) {
			color = colors.get(k);
			k = k.getParent();
		}
		if (color != null) {
			return color.getRGB();
		}
		// Fallback to parent theme
		return super.getColor(key);
	}

	@Override
	public boolean isDarkTheme() {
		return isDark;
	}

	public static class Builder {
		private final String name;
		private final LookAndFeel lnf;
		private final Map<Key, Color> colors = new HashMap<>();
		private final boolean isDark;
		private Consumer<InversionPreferences> configureInversion = null;

		public static Builder of(String name, LookAndFeel lnf, boolean isDark, Color bg, Color fg) {
			return new Builder(name, lnf, isDark)
				.set(ColorKeys.BACKGROUND, bg)
				.set(ColorKeys.FOREGROUND, fg);
		}

		private Builder(String name, LookAndFeel lnf, boolean dark) {
			this.name = name;
			this.lnf = lnf;
			this.isDark = dark;
		}

		/**
		 * Set color
		 */
		public Builder set(Key key, Color color) {
			colors.put(key, color);
			return this;
		}

		public Builder configureInversion(Consumer<InversionPreferences> configure) {
			this.configureInversion = configure;
			return this;
		}

		public BuiltInThemeD build() {
			final BuiltInThemeD theme = new BuiltInThemeD(name, lnf, colors, isDark);
			if (configureInversion != null) {
				configureInversion.accept(theme.getInversionPreferences());
			}
			return theme;
		}
	}
}
