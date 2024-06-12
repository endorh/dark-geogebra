package org.geogebra.common.spreadsheet.core;

import javax.annotation.Nonnull;

import org.geogebra.common.util.shape.Rectangle;

import com.himamis.retex.editor.share.editor.MathFieldInternal;

/**
 * An abstraction for spreadsheet cell editing.
 *
 * (This prevents dependencies on the platform-specifics of cell editors)
 */
public interface SpreadsheetCellEditor {

	/**
	 * Show the spreadsheet cell editor.
	 * @param editorBounds The editor (=cell) bounds in viewport-relative coordinates.
	 * @param viewport The current visible viewport.
	 * @param textAlignment The text alignment of the editor.
	 */
	// TODO introduce enum for alignment
	void show(Rectangle editorBounds, Rectangle viewport, int textAlignment);

	/**
	 * Hide the spreadsheet cell editor.
	 */
	void hide();

	/**
	 * TODO implement if necessary here, or remove/replace
	 * Scroll the cell editor if necessary to bring the cursor into view.
	 */
	void scrollCursorVisible();

	/**
	 * @return The underlying `MathFieldInternal` of the (platform-specific) cell editor.
	 */
	@Nonnull MathFieldInternal getMathField();

	/**
	 * @return A {@link SpreadsheetCellProcessor} (which basically abstracts the kernel code away
	 * from the spreadsheet code). See {@link org.geogebra.common.spreadsheet.kernel.DefaultSpreadsheetCellProcessor}
	 * for a default implementation.
	 */
	@Nonnull SpreadsheetCellProcessor getCellProcessor();

	/**
	 * @return A {@link SpreadsheetCellDataSerializer} (which basically abstracts the kernel code away
	 * from the spreadsheet code). See {@link org.geogebra.common.spreadsheet.kernel.DefaultSpreadsheetCellDataSerializer}
	 * for a default implementation.
	 */
	@Nonnull SpreadsheetCellDataSerializer getCellDataSerializer();
}