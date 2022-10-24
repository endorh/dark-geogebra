// Copyright 2001-2005 freehep
package org.freehep.graphicsio.pdf;

import java.awt.Font;
import java.awt.font.FontRenderContext;
import java.io.IOException;

import org.freehep.graphics2d.font.CharTable;
import org.freehep.graphicsio.font.FontIncluder;

/**
 * Includes one of the 14 Type1 fonts in PDF documents
 * 
 * @author Simon Fischer
 * @version $id$
 */
public class PDFFontIncluder extends FontIncluder {

	private static final int PLAIN = 0;

	private static final int BOLD = 1;

	private static final int ITALIC = 2;

	private static final int BOLDITALIC = 3;

	private static final int COURIER = 0;

	private static final int HELVETICA = 1;

	private static final int TIMES = 2;

	private static final int SYMBOL = 3;

	private static final int DINGBATS = 4;

	private static final String[][] STANDARD_FONT = {
			{ "Courier", "Courier-Bold", "Courier-Oblique",
					"Courier-BoldOblique" },
			{ "Helvetica", "Helvetica-Bold", "Helvetica-Oblique",
					"Helvetica-BoldOblique" },
			{ "Times-Roman", "Times-Bold", "Times-Italic", "Times-BoldItalic" },
			{ "Symbol" }, { "ZapfDingbats" } };

	private PDFWriter pdf;

	private String reference;

	private PDFRedundanceTracker redundanceTracker;

	public PDFFontIncluder(FontRenderContext context, PDFWriter pdf,
			String reference, PDFRedundanceTracker redundanceTracker) {
		super(context);
		this.pdf = pdf;
		this.reference = reference;
		this.redundanceTracker = redundanceTracker;
	}

	@Override
	protected void openIncludeFont() throws IOException {

		int style = getFontStyle(getFont());
		int fontBaseIndex = getFontBaseIndex(getFont());

		PDFDictionary font = pdf.openDictionary(reference);
		font.entry("Type", pdf.name("Font"));
		font.entry("Subtype", pdf.name("Type1"));
		font.entry("Name", pdf.name(reference));
		font.entry("BaseFont", pdf.name(STANDARD_FONT[fontBaseIndex][style]));
		// font.entry("Encoding", pdf.ref(reference+"Encoding"));
		font.entry("Encoding", redundanceTracker.getReference(
				getEncodingTable(), PDFCharTableWriter.getInstance()));
		pdf.close(font);
	}

	@Override
	protected void writeEncoding(CharTable charTable) throws IOException {
	}

	public static boolean isStandardFont(Font font) {
		String fontName = font.getName().toLowerCase();
		return (fontName.contains("helvetica"))
				|| (fontName.contains("times"))
				|| (fontName.contains("courier"))
				|| (fontName.contains("symbol"))
				|| (fontName.contains("dingbats"));
	}

	/** Returns the index of the standard font according to STANDARD_FONT. */
	private static int getFontBaseIndex(Font font) {
		String fontName = font.getName().toLowerCase();
		if (fontName.contains("helvetica")) {
			return HELVETICA;
		} else if (fontName.contains("times")) {
			return TIMES;
		} else if (fontName.contains("courier")) {
			return COURIER;
		} else if (fontName.contains("symbol")) {
			return SYMBOL;
		} else if (fontName.contains("dingbats")) {
			return DINGBATS;
		} else {
			// use HELVETICA as default
			return HELVETICA;
		}
	}

	/**
	 * Returns the index of the respective entry in STANDARD_FONT[fontIndex].
	 */
	private static int getFontStyle(Font font) {
		int fontBase = getFontBaseIndex(font);
		if ((fontBase >= 0) && (STANDARD_FONT[fontBase].length == 1)) {
			return PLAIN;
		}
		if (fontBase < 0) {
			return -1;
		}
		if (font.isBold()) {
			if (font.isItalic()) {
				return BOLDITALIC;
			}
			return BOLD;
		}
		if (font.isItalic()) {
			return ITALIC;
		}
		return PLAIN;
	}

}
