/* 
GeoGebra - Dynamic Mathematics for Everyone
http://www.geogebra.org

This file is part of GeoGebra.

This program is free software; you can redistribute it and/or modify it 
under the terms of the GNU General Public License as published by 
the Free Software Foundation.

*/

package org.geogebra.common.gui.view.algebra;

import org.geogebra.common.gui.Editing;
import org.geogebra.common.gui.SetLabels;
import org.geogebra.common.kernel.geos.GeoElement;

/**
 * Algebra view -- shows algebraic representation of the objects either as
 * value, definition or command
 */
public interface AlgebraView extends Editing, SetLabels {
	/**
	 * Returns whether this view is currently visible
	 * 
	 * @return whether this view is currently visible
	 */
	public boolean isVisible();

	public void startEditItem(GeoElement geo);

	public boolean isEditItem();

	// For WebSimple
	public GeoElement getDraggedGeo();

	/**
	 * DEPENDENCY: Tree mode where the objects are categorized by their
	 * dependency (free, dependent, auxiliary) -- default value TYPE: Tree mode
	 * where the objects are categorized by their type (points, circles, ..)
	 * VIEW: Tree mode where the objects are categorized by the view on which
	 * their value is computed (xOyPlane, space, ...) ORDER: Construction
	 * Protocol order
	 */
	public static enum SortMode {
		DEPENDENCY {
			@Override
			public String toString() {
				return "Dependency";
			}
		},
		TYPE {
			@Override
			public String toString() {
				return "ObjectType";
			}
		},
		ORDER {
			@Override
			public String toString() {
				return "ConstructionOrder";
			}
		},
		LAYER {
			@Override
			public String toString() {
				return "Layer";
			}
		};

		/**
		 * @param mode
		 *            mode XML value
		 * @return mode
		 */
		public static SortMode fromInt(int mode) {
			return switch (mode) {
				case 0 -> SortMode.DEPENDENCY;
				case 1 -> SortMode.TYPE;
				case 2 -> SortMode.LAYER;
				case 3 -> SortMode.ORDER;
				default -> SortMode.TYPE;
			};
		}

		/**
		 * @return XML value
		 */
		public int toInt() {
			return switch (this) {
				case DEPENDENCY -> 0;
				case TYPE -> 1;
				case LAYER -> 2;
				case ORDER -> 3;
			};
		}

	}

	public void setFocus(boolean b);

	public GeoElement getLastSelectedGeo();

	public void setLastSelectedGeo(GeoElement geo);

	public boolean isAttachedToKernel();

	public SortMode getTreeMode();

	void setTreeMode(SortMode value);

	/**
	 * This is just used from Html5/Web, but interface is in Common
	 * 
	 * @param visible
	 *            whether to show AV input
	 */
	public void setShowAlgebraInput(boolean visible);

    /**
     * remove the geo (with no check)
     * @param geo geo
     */
    void doRemove(GeoElement geo);

}
