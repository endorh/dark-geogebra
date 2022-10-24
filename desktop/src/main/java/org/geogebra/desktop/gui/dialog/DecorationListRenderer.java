package org.geogebra.desktop.gui.dialog;

import java.awt.Component;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListCellRenderer;

import org.geogebra.common.kernel.geos.GeoElement;
import org.geogebra.desktop.gui.theme.ColorKeys;
import org.geogebra.desktop.gui.theme.ThemeD;

/**
 * @author Le Coq Loic 30/10/2006 This class defines the renderer for the
 *         ComboBox where the user chooses the decoration for GeoSegment
 * 
 */
public class DecorationListRenderer extends JPanel implements ListCellRenderer {
	private static final long serialVersionUID = 1L;
	int id = 0;

	public DecorationListRenderer() {
		setOpaque(true);
	}

	@Override
	public Component getListCellRendererComponent(JList list, Object value,
			int index, boolean isSelected, boolean cellHasFocus) {
		// Get the selected index. (The index param isn't
		// always valid, so just use the value.)
		int selectedIndex = (Integer) value;
		this.id = selectedIndex;
		if (isSelected) {
			setBackground(ThemeD.color(ColorKeys.OUTLINE_LIGHT));
		} else {
			setBackground(list.getBackground());
		}

		setBorder(BorderFactory.createEmptyBorder(2, 2, 2, 2));
		return this;
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);

		g.setColor(getBackground());

		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(getForeground());
		int mid = getHeight() / 2;
		g.drawLine(0, mid, getWidth(), mid);

		switch (id) {
		default:
		case GeoElement.DECORATION_NONE:
			break;
		case GeoElement.DECORATION_SEGMENT_ONE_TICK:
			int quart = mid / 2;
			int mid_width = getWidth() / 2;
			g.drawLine(mid_width, quart, mid_width, mid + quart);
			break;
		case GeoElement.DECORATION_SEGMENT_TWO_TICKS:
			quart = mid / 2;
			mid_width = getWidth() / 2;
			g.drawLine(mid_width - 1, quart, mid_width - 1, mid + quart);
			g.drawLine(mid_width + 2, quart, mid_width + 2, mid + quart);
			break;
		case GeoElement.DECORATION_SEGMENT_THREE_TICKS:
			quart = mid / 2;
			mid_width = getWidth() / 2;
			g.drawLine(mid_width, quart, mid_width, mid + quart);
			g.drawLine(mid_width + 3, quart, mid_width + 3, mid + quart);
			g.drawLine(mid_width - 3, quart, mid_width - 3, mid + quart);
			break;
		case GeoElement.DECORATION_SEGMENT_ONE_ARROW:
			quart = mid / 2;
			mid_width = getWidth() / 2;
			g.drawLine(mid_width, mid, mid_width - quart, mid - quart);
			g.drawLine(mid_width, mid, mid_width - quart, mid + quart);
			break;
		case GeoElement.DECORATION_SEGMENT_TWO_ARROWS:
			quart = mid / 2;
			mid_width = getWidth() / 2;
			g.drawLine(mid_width - 3, mid, mid_width - quart - 3, mid - quart);
			g.drawLine(mid_width - 3, mid, mid_width - quart - 3, mid + quart);
			g.drawLine(mid_width + 3, mid, mid_width - quart + 3, mid - quart);
			g.drawLine(mid_width + 3, mid, mid_width - quart + 3, mid + quart);
			break;
		case GeoElement.DECORATION_SEGMENT_THREE_ARROWS:
			quart = mid / 2;
			mid_width = getWidth() / 2;
			g.drawLine(mid_width, mid, mid_width - quart, mid - quart);
			g.drawLine(mid_width, mid, mid_width - quart, mid + quart);
			g.drawLine(mid_width + 6, mid, mid_width - quart + 6, mid - quart);
			g.drawLine(mid_width + 6, mid, mid_width - quart + 6, mid + quart);
			g.drawLine(mid_width - 6, mid, mid_width - quart - 6, mid - quart);
			g.drawLine(mid_width - 6, mid, mid_width - quart - 6, mid + quart);
			break;
		}
	}
}
