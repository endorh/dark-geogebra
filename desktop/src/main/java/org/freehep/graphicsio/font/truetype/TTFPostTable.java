// Copyright 2001, FreeHEP.
package org.freehep.graphicsio.font.truetype;

import java.io.IOException;

/**
 * POST Table.
 * 
 * @author Simon Fischer
 * @version $Id: TTFPostTable.java,v 1.5 2009-08-17 21:44:45 murkle Exp $
 */
public class TTFPostTable extends TTFTable {

	public double format;

	public double italicAngle;

	public short underlinePosition, underlineThickness;

	public long isFixedPitch;

	public long minMemType42, maxMemType42, minMemType1, maxMemType1;

	public int[] glyphNameIndex;

	@Override
	public String getTag() {
		return "post";
	}

	@Override
	public void readTable() throws IOException {
		format = ttf.readFixed();

		italicAngle = ttf.readFixed();

		underlinePosition = ttf.readFWord();
		underlineThickness = ttf.readFWord();

		isFixedPitch = ttf.readULong();

		minMemType42 = ttf.readULong();
		maxMemType42 = ttf.readULong();
		minMemType1 = ttf.readULong();
		maxMemType1 = ttf.readULong();

		if (format == 2.0) {
			glyphNameIndex = ttf.readUShortArray(ttf.readUShort());
		} else if (format == 2.5) {
			System.err.println("Format 2.5 for post notimplemented yet.");
		}
	}

	@Override
	public String toString() {
		StringBuilder str =
				new StringBuilder(super.toString() + " format: " + format + "\n  italic:"
						+ italicAngle + " ulPos:" + underlinePosition + " ulThick:"
						+ underlineThickness + " isFixed:" + isFixedPitch);
		if (glyphNameIndex != null) {
			str.append("\n  glyphNamesIndex[").append(glyphNameIndex.length).append("] = {");
			for (int i = 0; i < glyphNameIndex.length; i++) {
				if (i % 16 == 0) {
					str.append("\n    ");
				}
				str.append(glyphNameIndex[i]).append(" ");
			}
			str.append("\n  }");
		}
		return str.toString();
	}
}
