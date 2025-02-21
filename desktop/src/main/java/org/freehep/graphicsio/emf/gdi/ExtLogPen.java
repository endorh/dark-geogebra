// Copyright 2002, FreeHEP.
package org.freehep.graphicsio.emf.gdi;

import java.awt.Color;
import java.io.IOException;

import org.freehep.graphicsio.emf.EMFConstants;
import org.freehep.graphicsio.emf.EMFInputStream;
import org.freehep.graphicsio.emf.EMFOutputStream;

/**
 * EMF ExtLogPen
 * 
 * @author Mark Donszelmann
 * @version $Id: ExtLogPen.java,v 1.4 2009-08-17 21:44:44 murkle Exp $
 */
public class ExtLogPen implements EMFConstants {

	private int penStyle;

	private int width;

	private int brushStyle;

	private Color color;

	private int hatch;

	private int[] style;

	public ExtLogPen(int penStyle, int width, int brushStyle, Color color,
			int hatch, int[] style) {
		this.penStyle = penStyle;
		this.width = width;
		this.brushStyle = brushStyle;
		this.color = color;
		this.hatch = hatch;
		this.style = style;
	}

	public ExtLogPen(EMFInputStream emf) throws IOException {
		penStyle = emf.readDWORD();
		width = emf.readDWORD();
		brushStyle = emf.readUINT();
		color = emf.readCOLORREF();
		hatch = emf.readULONG();
		int nStyle = emf.readDWORD();
		// it seems we always have to read one!
		if (nStyle == 0) {
			emf.readDWORD();
		}
		style = emf.readDWORD(nStyle);
	}

	public void write(EMFOutputStream emf) throws IOException {
		emf.writeDWORD(penStyle);
		emf.writeDWORD(width);
		emf.writeUINT(brushStyle);
		emf.writeCOLORREF(color);
		emf.writeULONG(hatch);
		emf.writeDWORD(style.length);
		// it seems we always have to write one!
		if (style.length == 0) {
			emf.writeDWORD(0);
		}
		emf.writeDWORD(style);
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("  ExtLogPen\n");
		s.append("    penStyle: ").append(Integer.toHexString(penStyle)).append("\n");
		s.append("    width: ").append(width).append("\n");
		s.append("    brushStyle: ").append(brushStyle).append("\n");
		s.append("    color: ").append(color).append("\n");
		s.append("    hatch: ").append(hatch).append("\n");
		for (int i = 0; i < style.length; i++) {
			s.append("      style[").append(i).append("]: ").append(style[i]).append("\n");
		}
		return s.toString();
	}
}
