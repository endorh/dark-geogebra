package org.geogebra.desktop.cas.view;

import java.awt.Component;
import java.awt.Font;

import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

import org.geogebra.common.kernel.geos.GeoCasCell;
import org.geogebra.desktop.gui.theme.ThemeD;

/**
 * CAS cell renderer
 */
public class CASTableCellRenderer extends CASTableCell
		implements TableCellRenderer {

	private static final long serialVersionUID = 1L;

	/**
	 * @param view
	 *            CAS view
	 */
	CASTableCellRenderer(CASViewD view) {
		super(view);
	}

	@Override
	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		Font casFont = view.getCASViewComponent().getFont();
		if (value instanceof GeoCasCell cell) {
			// set CASTableCell value
			setValue(cell);

			// update font and row height
			if (cell.isUseAsText()) {
				setFont(casFont.deriveFont(cell.getFontStyle(),
						(float) (casFont.getSize()
								* (cell.getFontSizeMultiplier()))));

				setForeground(ThemeD.awtColor(cell.getFontColor()));
				dummyField.setForeground(
						ThemeD.awtColor(cell.getFontColor()));
				this.getInputArea().setForeground(
						ThemeD.awtColor(cell.getFontColor()));
			} else {
				setFont(casFont);
			}
			updateTableRowHeight(table, row);

			// set inputPanel width to match table column width
			// -1 = set to table column width (even if larger than viewport)
			setInputPanelWidth(-1);
		}
		return this;
	}

}
