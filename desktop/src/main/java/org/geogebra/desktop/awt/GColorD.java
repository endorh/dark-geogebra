package org.geogebra.desktop.awt;

import java.awt.Color;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.geogebra.common.awt.GColor;
import org.geogebra.common.kernel.geos.GeoCasCell;
import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.desktop.factories.AwtFactoryD;
import org.geogebra.desktop.gui.theme.ThemeD;
import org.geogebra.desktop.util.StringUtilD;

/**
 * Static methods for AWT &lt;-> multiplatform color conversion
 *
 */
public class GColorD {

	private static Map<GColor, Color> map = Collections.synchronizedMap(new HashMap<>());
	private static final Pattern FONT_COLOR_PATTERN = Pattern.compile(
			"<font[^>]+?color=\"#(?<hex>[\\dA-Fa-f]{6})\"");

	/**
	 * @param gColor
	 *            multiplatform color
	 * @return awt color
	 */
	public static Color getRawAwtColor(GColor gColor) {
		Color ret = map.get(gColor);

		if (ret == null && gColor != null) {
			// color hasn't been used yet, need to create it
			ret = new Color(gColor.getARGB(), true);
			map.put(gColor, ret);
		}

		return ret;
	}

	/**
	 * @param color
	 *            awt color
	 * @return multiplatform color
	 */
	public static GColor newColor(Color color) {
		return color == null ? null
				: GColor.newColor(color.getRed(), color.getGreen(),
						color.getBlue());
	}

	public static Color getAwtAlgebraColor(GColor c) {
		return ThemeD.getTheme().transformColorForText(c);
	}

	public static Color getAwtAlgebraColor(GeoElement element) {
		final GColor gc = getAlgebraColor(element);
		final Color gac = getAwtAlgebraColor(gc);
		return gac;
	}

	private static GColor getAlgebraColor(GeoElement element) {
		if (element instanceof GeoCasCell) {
			final GeoCasCell casCell = (GeoCasCell) element;
			return !casCell.hasTwinGeo() ? GColor.BLACK : casCell.getTwinGeo().getObjectColor();
		}
		return element.getObjectColor();
	}

	public static String patchHTMLFontColors(String html) {
		if (html != null) {
			// Patch tooltip color
			StringBuffer sb = new StringBuffer();
			Matcher m = FONT_COLOR_PATTERN.matcher(html);
			while (m.find()) {
				Color color = ThemeD.getTheme().transformColorForText(
						GColor.newColorRGB(Integer.decode("#" + m.group("hex"))));
				String rep = m.group().substring(0, m.start("hex") - m.start()) +
						StringUtilD.toHexString(
								(byte) color.getRed(), (byte) color.getGreen(),
								(byte) color.getBlue()
						) + m.group().substring(m.end("hex") - m.start());
				m.appendReplacement(sb, rep);
			}
			m.appendTail(sb);
			html = sb.toString();
		}
		return html;
	}
}
