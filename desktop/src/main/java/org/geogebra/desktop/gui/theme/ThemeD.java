package org.geogebra.desktop.gui.theme;

import static com.jogamp.opengl.math.FloatUtil.pow;
import static org.geogebra.desktop.gui.theme.BuiltInThemeD.DEFAULT;

import java.awt.Color;
import java.awt.Image;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.stream.Collectors;

import javax.swing.LookAndFeel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.geogebra.common.awt.GColor;

import edu.umd.cs.findbugs.annotations.NonNull;
import edu.umd.cs.findbugs.annotations.Nullable;

/**
 * Base class for Desktop themes.<br>
 * Themes map from {@link Key}s to colors.<br><br>
 * Only a single theme can be active at any time<br>
 * Mutable {@link ThemeColor}s are used whenever possible, so usages
 * can be automatically updated on theme changes, similar to how
 * {@link java.awt.SystemColor}s do.
 */
public class ThemeD {
	private static ThemeD activeTheme;
	private static final Set<ThemeImageIcon> themeIcons =
			Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap<>()));
	private static final Map<Key, ThemeColor> themeColors =
			Collections.synchronizedMap(new HashMap<>());
	private static final Map<GColor, ThemeColor> gThemeColors =
			Collections.synchronizedMap(new HashMap<>());
	private static final Map<GColor, ThemeColor> textColors =
			Collections.synchronizedMap(new HashMap<>());

	public static ThemeD getTheme(String name) {
		return getAvailableThemes().stream()
				.filter(t -> t.getName().equals(name)).findFirst()
				.orElse(null);
	}

	public static List<ThemeD> getAvailableThemes() {
		return Arrays.stream(BuiltInThemeD.BUILT_IN_THEMES)
				.collect(Collectors.toList());
	}

	/**
	 * Get currently active theme
	 */
	public static @NonNull ThemeD getTheme() {
		if (activeTheme == null) activeTheme = getDefaultTheme();
		return activeTheme;
	}

	/**
	 * Get the default theme
	 */
	public static ThemeD getDefaultTheme() {
		return BuiltInThemeD.FLAT_DARK;
	}

	public static ThemeImageIcon icon(Image image) {
		return icon(image.getSource());
	}

	public static ThemeImageIcon icon(ImageProducer source) {
		ThemeImageIcon icon = new ThemeImageIcon(source);
		themeIcons.add(icon);
		return icon;
	}

	/**
	 * Get color for key.<br>
	 * The returned color is mutable and will be changed on theme changes,
	 * so it can be cached.
	 */
	public static Color color(Key key) {
		return getTheme().getAwtColor(key);
	}

	/**
	 * Get {@link GColor} for key.
	 * @deprecated Since {@link GColor} are not mutable.
	 */
	@Deprecated public static GColor gColor(Key key) {
		return getTheme().getGColor(key);
	}

	public static InversionPreferences inversionPreferences() {
		return getTheme().getInversionPreferences();
	}

	/**
	 * Get {@link java.awt.Color} from {@link GColor} according to current theme.
	 */
	public static Color awtColor(GColor color) {
		return getTheme().getAwtColor(color);
	}

	/**
	 * Filter a color according to the current theme.<br>
	 * <b>Uses must be reloaded on theme change, since GColors are not mutable</b>
	 */
	public static GColor filterColor(GColor color) {
		return getTheme().transformColor(color);
	}

	/**
	 * Change the active theme.
	 * Should be followed by a UI update.
	 */
	public static void setTheme(ThemeD theme) {
		activeTheme = theme;

		try {
			LookAndFeel lnf = theme.getLookAndFeel();
			if (lnf != null) {
				UIManager.setLookAndFeel(lnf);
			} else UIManager.setLookAndFeel(theme.getLookAndFeelName());
		} catch (
				UnsupportedLookAndFeelException | ClassNotFoundException |
				InstantiationException | IllegalAccessException ignored
		) {}

		// Update ThemeColor instances of each kind
		themeColors.forEach((key, value) ->
				value.setARGB(theme.getColor(key)));
		gThemeColors.forEach((key, value) ->
				value.setARGB(theme.transformColor(key.getARGB())));
		Color bgColor = theme.getAwtColor(ColorKeys.BACKGROUND_ALGEBRA);
		textColors.forEach((key, value) ->
				value.setARGB(theme.updateForBackground(
						theme.transformColor(key), bgColor)));
		themeIcons.forEach(ThemeImageIcon::themeUpdate);
	}

	public static void setTheme(String name) {
		ThemeD theme = getTheme(name);
		if (theme != null) {
			setTheme(theme);
		} else {
			throw new IllegalArgumentException("Unknown theme: " + name);
		}
	}


	protected final String name;
	protected ThemeD parentTheme = DEFAULT;
	protected LookAndFeel lookAndFeel = null;
	protected String lookAndFeelName = null;
	protected InversionPreferences inversionPreferences = new InversionPreferences();

	public ThemeD(String name) {
		this.name = name;
	}

	public ThemeD(String name, ThemeD parent) {
		this(name);
		parentTheme = parent;
	}

	public ThemeD(String name, LookAndFeel lookAndFeel) {
		this(name);
		this.lookAndFeel = lookAndFeel;
	}

	public ThemeD(String name, String lookAndFeel) {
		this(name);
		lookAndFeelName = lookAndFeel;
	}

	public final String getName() {
		return name;
	}

	/**
	 * Parent theme used to find missing definitions.
	 */
	public ThemeD getParent() {
		return parentTheme;
	}

	public @Nullable LookAndFeel getLookAndFeel() {
		ThemeD parent = getParent();
		return lookAndFeel != null? lookAndFeel : parent != null? parent.getLookAndFeel() : null;
	}

	public String getLookAndFeelName() {
		LookAndFeel lnf = getLookAndFeel();
		if (lnf != null) return lnf.getClass().getCanonicalName();
		ThemeD parent = getParent();
		return lookAndFeelName != null? lookAndFeelName : parent != null
				? parent.getLookAndFeelName() : UIManager.getSystemLookAndFeelClassName();
	}

	/**
	 * Get color for key as ARGB.
	 * Should be overridden by subclasses.
	 */
	public int getColor(Key key) {
		final ThemeD parent = getParent();
		if (parent != null) {
			return parent.getColor(key);
		}
		throw new UnknownColorKeyException(key);
	}

	/**
	 * Transform a color's ARGB.
	 * Used for dynamic (user) color filtering.
	 */
	public int transformColor(int argb) {
		return getInversionPreferences().isInvertColors() ? doTransformColor(argb) : argb;
	}

	public int transformIconColor(int argb) {
		return getInversionPreferences().isInvertIcons() ? doTransformColor(argb) : argb;
	}

	public int transformImageColor(int argb) {
		return getInversionPreferences().isInvertImages() ? doTransformColor(argb) : argb;
	}

	protected int doTransformColor(int argb) {
		ThemeInvertOptions options = getInversionPreferences().getOptions();
		float inv = options.getInvertStrength();
		float min = options.getMinBrightness();
		float max = options.getMaxBrightness();
		int r = (argb >> 16) & 0xFF;
		int g = (argb >> 8) & 0xFF;
		int b = argb & 0xFF;

		int rr = clampComponent((int)
				((inv * (0xFF - (g + b) / 2) + (1F - inv) * r) * (max - min) + min * 255F));
		int gg = clampComponent((int)
				((inv * (0xFF - (r + b) / 2) + (1F - inv) * g) * (max - min) + min * 255F));
		int bb = clampComponent((int)
				((inv * (0xFF - (r + g) / 2) + (1F - inv) * b) * (max - min) + min * 255F));

		return argb & 0xFF_00_00_00 | rr << 16 | gg << 8 | bb;
	}

	public boolean isDarkTheme() {
		return false;
	}

	public InversionPreferences getInversionPreferences() {
		return inversionPreferences;
	}

	protected final GColor getGColor(Key key) {
		return GColor.newColorRGB(getColor(key));
	}

	protected final GColor transformColor(GColor color) {
		if (color == null) return null;
		final int argb = transformColor(color.getARGB());
		return GColor.newColor((argb >> 16) & 0xFF, (argb >> 8) & 0xFF, argb & 0xFF, argb >> 24);
	}

	/**
	 * Get {@link Color} for key.<br>
	 * Returns the same {@link ThemeColor} if already cached.
	 */
	public final Color getAwtColor(Key key) {
		ThemeColor ret = themeColors.get(key);
		if (ret == null && key != null) {
			ret = new ThemeColor(getColor(key), true);
			themeColors.put(key, ret);
		}
		return ret;
	}

	/**
	 * Get filtered {@link Color} from {@link GColor}.<br>
	 * Returns the same {@link ThemeColor} if already cached.
	 */
	public final Color getAwtColor(GColor color) {
		ThemeColor ret = gThemeColors.get(color);
		if (ret == null && color != null) {
			ret = new ThemeColor(transformColor(color.getARGB()), true);
			gThemeColors.put(color, ret);
		}
		return ret;
	}

	/**
	 * Get filtered {@link Color} from {@link GColor}, corrected for readable
	 * contrast against the theme's background ({@link ColorKeys#BACKGROUND_ALGEBRA}).<br>
	 * Returns the same {@link ThemeColor} if already cached.
	 */
	public final Color transformColorForText(GColor color) {
		ThemeColor ret = textColors.get(color);
		if (ret == null && color != null) {
			int textColor = updateForBackground(
					transformColor(color), getAwtColor(ColorKeys.BACKGROUND_ALGEBRA));
			ret = new ThemeColor(textColor, false);
			textColors.put(color, ret);
		}
		return ret;
	}

	public static void loadThemeProperties(String properties) {
		Properties props = new Properties();
		try {
			props.load(new StringReader(properties));
			loadThemeProperties(props);
		} catch (IllegalArgumentException | IOException ignored) {}
	}

	private static void loadThemeProperties(Properties props) {
		ThemeD theme = getTheme(props.getProperty("theme"));
		if (theme != null) {
			ThemeD.setTheme(theme);
		}
		for (ThemeD t : getAvailableThemes()) {
			String name = t.getName();
			InversionPreferences prefs = t.getInversionPreferences();
			ThemeInvertOptions opt = prefs.getOptions();
			prefs.setInvertColors(getBoolean(props, name + ".invert-colors"));
			prefs.setInvertIcons(getBoolean(props, name + ".invert-icons"));
			prefs.setInvertImages(getBoolean(props, name + ".invert-images"));
			opt.setInvertStrength(getFloat(props, name + ".invert-strength", 0.95F));
			opt.setMinBrightness(getFloat(props, name + ".min-brightness", 0.15F));
			opt.setMaxBrightness(getFloat(props, name + ".max-brightness", 0.95F));
		}
	}

	private static boolean getBoolean(Properties props, String name) {
		return "true".equals(props.getProperty(name));
	}

	private static float getFloat(Properties props, String name, float def) {
		String str = props.getProperty(name);
		if (str != null) {
			try {
				return Float.parseFloat(str);
			} catch (NumberFormatException ignored) {}
		}
		return def;
	}

	public static String saveThemeProperties() {
		// Create the string directly to add blank lines
		StringBuilder sb = new StringBuilder();
		sb.append("# Theme Settings\ntheme = ");
		sb.append(ThemeD.getTheme().getName());
		sb.append("\n");
		for (ThemeD t : getAvailableThemes()) {
			String name = t.getName();
			InversionPreferences prefs = t.getInversionPreferences();
			ThemeInvertOptions opt = prefs.getOptions();
			setProperty(sb, name, "invert-colors", prefs.isInvertColors());
			setProperty(sb, name, "invert-icons", prefs.isInvertIcons());
			setProperty(sb, name, "invert-images", prefs.isInvertImages());
			setProperty(sb, name, "invert-strength", opt.getInvertStrength());
			setProperty(sb, name, "min-brightness", opt.getMinBrightness());
			setProperty(sb, name, "max-brightness", opt.getMaxBrightness());
			sb.append("\n");
		}
		sb.append("\n");
		return sb.toString();
	}

	private static void setProperty(StringBuilder sb, String name, String key, Object value) {
		sb.append("\n");
		sb.append(name.replace(" ", "\\ "));
		sb.append(".");
		sb.append(key);
		sb.append(" = ");
		sb.append(value);
	}

	/**
	 * Create a more readable version of a color, to make it readable on a
	 * background color. Does not change the color, if it already fulfills
	 * the requirements.
	 *
	 * The color is assumed opaque, and the returned color will always be opaque.
	 *
	 * Uses the W3C standard for contrast and brightness.
	 *
	 * @param color the base color
	 * @param bg the background color
	 * @return a darker/brighter version of the input color that can be read on the
	 *         background color
	 */
	protected int updateForBackground(GColor color, Color bg) {
		int bgR = bg.getRed(), bgG = bg.getGreen(), bgB = bg.getBlue(),
			fgR = color.getRed(), fgG = color.getGreen(), fgB = color.getBlue();
		// Brighter if background is dark, darker if light
		int step = bgR + bgG + bgB > 0x80 * 3? -5 : 5;
		for (int i = 0; i < 50 && !checkColorRatioBackground(fgR, fgG, fgB, bgR, bgG, bgB); i++) {
			// Darken/lighten slightly
			fgR = clampComponent(fgR + step);
			fgG = clampComponent(fgG + step);
			fgB = clampComponent(fgB + step);
		}

		if (!checkColorRatioBackground(fgR, fgG, fgB, bgR, bgG, bgB)) {
			return getColor(ColorKeys.TEXT); // Fallback
		}

		return 0xFF_00_00_00 | fgR << 16 | fgG << 8 | fgB;
	}

	protected static int clampComponent(int v) {
		return Math.max(0x00, Math.min(0xff, v));
	}

	/**
	 * Uses the color contrast ratio of the W3C, which can be found at:<br>
	 * <a href="http://www.w3.org/TR/WCAG20-TECHS/G18.html">http://www.w3.org/TR/WCAG20-TECHS/G18.html</a><br>
	 * <a href="http://web.mst.edu/~rhall/web_design/color_readability.html">http://web.mst.edu/~rhall/web_design/color_readability.html</a>
	 *
	 * @param fgR red
	 * @param fgG green
	 * @param fgB blue
	 * @return if the contrast ration sufficient (true) or not (false)
	 */
	protected static boolean checkColorRatioBackground(int fgR, int fgG, int fgB, int bgR, int bgG, int bgB) {
		return contrastRatio(fgR, fgG, fgB, bgR, bgG, bgB) >= 4.5F;
	}

	/**
	 * <a href="https://www.w3.org/TR/WCAG20/#contrast-ratiodef">W3ACG 2.0 Contrast ratio</a>
	 */
	protected static float contrastRatio(int r1, int g1, int b1, int r2, int g2, int b2) {
		float l1 = relativeLuminance(r1, g1, b1);
		float l2 = relativeLuminance(r2, g2, b2);
		return l1 >= l2? l1 / l2 : l2 / l1;
	}

	/**
	 * <a href="https://www.w3.org/TR/WCAG20/#relativeluminancedef">W3ACG 2.0 Relative luminance</a>,
	 * simplified for int input
	 */
	protected static float relativeLuminance(int r, int g, int b) {
		float R = r <= 10 ? r / 3294.6F : pow((r + 14.025F) / 269.025F, 2.4F);
		float G = g <= 10 ? g / 3294.6F : pow((g + 14.025F) / 269.025F, 2.4F);
		float B = b <= 10 ? b / 3294.6F : pow((b + 14.025F) / 269.025F, 2.4F);
		return 0.2126F * R + 0.7152F * G + 0.0722F * B;
	}

	public interface Key {
		String getName();
		Key getParent();
	}

	public static class UnknownThemeException extends RuntimeException {
		public UnknownThemeException(String name) {
			super("Unknown theme: \"" + name + "\"");
		}
	}

	public static class UnknownColorKeyException extends RuntimeException {
		public UnknownColorKeyException(Key key) {
			super("Unknown color key: \"" + key + "\"");
		}
	}
}
