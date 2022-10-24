package org.geogebra.desktop.euclidian;

import static java.lang.Math.max;
import static java.lang.Math.min;

import java.awt.AWTException;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.Robot;

import org.geogebra.common.awt.GPoint;
import org.geogebra.common.euclidian.EuclidianConstants;
import org.geogebra.common.euclidian.EuclidianController;
import org.geogebra.common.util.debug.Log;

import edu.umd.cs.findbugs.annotations.Nullable;

public class DragWarpHandler {
	private @Nullable Robot robot;
	private int robotMaxTries = 20;
	private int robotMaxRequired = 3;
	private int mouseWarpMargin = 24;

	private final EuclidianController controller;

	public DragWarpHandler(EuclidianController controller) {
		this.controller = controller;
		try {
			robot = new Robot();
		} catch (AWTException e) {
			Log.error("Could not create robot. Mouse warping features will not work.");
		}
	}

	public boolean isWarpEnabled() {
		int mode = controller.getMode();
		return (mode == EuclidianConstants.MODE_TRANSLATEVIEW
				|| mode == EuclidianConstants.MODE_ROTATEVIEW
		) && controller.getApplication().getSettings().getControlsSettings().isEnableWarpTranslate();
	}

	public void handleDragWarp() {
		if (!isWarpEnabled()) return;
		int width = controller.getView().getViewWidth();
		int height = controller.getView().getViewHeight();
		GPoint mouseLoc = controller.mouseLoc;
		GPoint startLoc = controller.startLoc;
		if (robot != null) {
			Point pos = MouseInfo.getPointerInfo().getLocation();
			int warpX = 0;
			int warpY = 0;
			int margin = max(8, min(mouseWarpMargin, min(width, height) / 8));
			if (mouseLoc.x > width - margin) {
				warpX = -width + 3 * margin;
			} else if (mouseLoc.x < margin) {
				warpX = width - 3 * margin;
			}
			if (mouseLoc.y > height - margin) {
				warpY = -height + 3 * margin;
			} else if (mouseLoc.y < margin) {
				warpY = height - 3 * margin;
			}
			if ((warpX != 0 || warpY != 0) && mouseMove(pos.x + warpX, pos.y + warpY)) {
				startLoc.x += warpX;
				startLoc.y += warpY;
				mouseLoc.x += warpX;
				mouseLoc.y += warpY;
			}
		}
	}

	/**
	 * {@link Robot#mouseMove} doesn't always work the first try on some platforms.
	 */
	public boolean mouseMove(int x, int y) {
		if (robot == null) return false;
		int success = 0;
		for (int count = 0; count < robotMaxTries; count++) {
			robot.mouseMove(x, y);
			Point loc = MouseInfo.getPointerInfo().getLocation();
			if ((loc.x == x || loc.y == y) && success++ >= robotMaxRequired) return true;
		}
		return false;
	}
}
