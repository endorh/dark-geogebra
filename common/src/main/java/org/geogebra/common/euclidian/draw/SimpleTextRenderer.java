package org.geogebra.common.euclidian.draw;

import org.geogebra.common.awt.GFont;
import org.geogebra.common.awt.GGraphics2D;
import org.geogebra.common.awt.GRectangle;
import org.geogebra.common.euclidian.EuclidianStatic;
import org.geogebra.common.factories.AwtFactory;
import org.geogebra.common.kernel.geos.GeoInputBox;
import org.geogebra.common.main.App;

import com.google.j2objc.annotations.Weak;

/**
 * Renders the text as a "flat" string.
 */
public class SimpleTextRenderer implements TextRenderer {

	private App app;

	@Weak
	private DrawInputBox drawable;

	/**
	 * @param drawable
	 *            drawable
	 */
	SimpleTextRenderer(App app, DrawInputBox drawable) {
		this.drawable = drawable;
		this.app = app;
	}

	@Override
	public void drawText(GeoInputBox geo, GGraphics2D graphics,
						 GFont font, String text,
						 double xPos, double yPos) {
		double textBottom = yPos + drawable.getTextBottom();
		double boxContentWidth = drawable.getContentWidth();
		String truncated = text.substring(0, getTruncIndex(text, graphics, boxContentWidth));
		double newXPos = xPos + getTextOffset(truncated, geo, app, (int) boxContentWidth, graphics);
		EuclidianStatic.drawIndexedString(app, graphics, truncated, newXPos, textBottom, false);
	}

	private static int getTextOffset(String text, GeoInputBox geoInputBox, App app,
								 int boxWidth, GGraphics2D graphics2D) {
		return switch (geoInputBox.getAlignment()) {
			case CENTER -> (boxWidth - getTextWidth(app, graphics2D, text)) / 2;
			case RIGHT -> boxWidth - getTextWidth(app, graphics2D, text);
			default -> 0;
		};
	}

	private static int getTextWidth(App app, GGraphics2D graphics2D, String text) {
		return EuclidianStatic.drawIndexedString(app, graphics2D, text,
				0, 0, false, false, null, null).x;
	}

	@Override
	public GRectangle measureBounds(GGraphics2D graphics, GeoInputBox geo, GFont font,
									String labelDescription) {
		drawable.measureLabel(graphics, geo, labelDescription);
		int height = Math.max(drawable.boxHeight, DrawInputBox.MIN_HEIGHT);
		return AwtFactory.getPrototype().newRectangle(
				drawable.boxLeft, drawable.computeBoxTop(height), drawable.boxWidth, height);
	}

	private int getTruncIndex(String text, GGraphics2D g2, double boxWidth) {
		int idx = text.length();
		int mt = drawable.measureTextWidth(text, g2.getFont(), g2);
		while (mt > boxWidth && idx > 0) {
			idx--;
			mt = drawable.measureTextWidth(text.substring(0, idx), g2.getFont(), g2);

		}
		return idx;
	}
}
