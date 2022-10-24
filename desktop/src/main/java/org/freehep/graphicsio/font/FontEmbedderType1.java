// Copyright 2001-2005 freehep
package org.freehep.graphicsio.font;

import java.awt.Shape;
import java.awt.font.FontRenderContext;
import java.awt.font.GlyphMetrics;
import java.awt.geom.Rectangle2D;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;

import org.freehep.graphics2d.font.CharTable;
import org.freehep.util.io.ASCIIHexOutputStream;
import org.freehep.util.io.CountedByteOutputStream;
import org.freehep.util.io.EEXECEncryption;

/**
 * Font embedder for type 1 fonts. The output can be directly fed into a ps file
 * or as a FontFile to a pdf file.
 * <h3>Todo</h3>
 * <ul>
 * <li>use subroutines for accents
 * <li>add more hints
 * </ul>
 * 
 * @author Simon Fischer
 * @version $Id: FontEmbedderType1.java,v 1.4 2009-08-17 21:44:45 murkle Exp $
 */
public class FontEmbedderType1 extends FontEmbedder {

	/** Defines whether or not eexec encryption is used. */
	private static final boolean ENCRYPT = true;

	/**
	 * Defines whether or not encrypted part should be hex encoded (otherwise it
	 * is binary).
	 */
	private static final boolean HEX_ENC = true;

	/**
	 * Defines whether or not encrypted charstrings should be hex encoded
	 * Ghostview crashes when set to true. The freehep ps interpreter handles it
	 * correctly.
	 */
	private static final boolean HEX_ENC_CHARSTRINGS = false;

	private PrintStream fontFile, encrypted;

	private CountedByteOutputStream byteCounter;

	private int asciiEnd, encEnd; // remember the lengths of the three
									// portions

	private boolean addZeros;

	public FontEmbedderType1(FontRenderContext context, OutputStream out,
			boolean addZeros) {
		super(context);
		this.byteCounter = new CountedByteOutputStream(out);
		this.fontFile = new PrintStream(byteCounter);
		this.addZeros = addZeros;
		asciiEnd = encEnd = -1;
	}

	@Override
	protected void writeWidths(double[] w) throws IOException {
	}

	@Override
	protected void writeEncoding(CharTable t) throws IOException {
		fontFile.println("/Encoding 256 array");
		fontFile.println("0 1 255 {1 index exch /.notdef put} for"); // set
																		// undefined
																		// to
																		// .notdef
																		// ??
		for (int i = 0; i < 256; i++) {
			String charName = t.toName(i);
			if (charName != null) {
				fontFile.println("dup " + i + " /" + charName + " put");
			}
		}
		fontFile.println("readonly def");
	}

	@Override
	protected void openIncludeFont() throws IOException {

		// begin clear text ascii portion
		fontFile.println("%!FontType1-1.0: " + getFont().getName()); // unknown
																		// version
																		// number
		// fontFile.println("%%CreationDate: " +
		// DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.FULL).
		// format(new Date()));
		fontFile.println("% Generated by: " + getClass().getName());
		fontFile.println("11 dict begin");

		fontFile.println("/FontInfo 8 dict dup begin");
		fontFile.println(
				"/FullName (" + getFont().getPSName() + ") readonly def");
		fontFile.println(
				"/FamilyName (" + getFont().getFamily() + ") readonly def");
		fontFile.println("end readonly def");

		fontFile.println("/FontName /" + getFontName() + " def");
		fontFile.println("/PaintType 0 def");
		fontFile.println("/FontType 1 def");
		fontFile.println("/FontMatrix [" + 1 / FONT_SIZE + " 0.0 0.0 "
				+ 1 / FONT_SIZE + " 0.0 0.0] readonly def");
	}

	@Override
	protected void closeIncludeFont() {

		Rectangle2D boundingBox = getFontBBox();
		int llx = (int) Math.round(boundingBox.getX());
		int lly = (int) Math.round(boundingBox.getY());
		int urx = (int) Math.round(boundingBox.getX() + boundingBox.getWidth());
		int ury = (int) Math
				.round(boundingBox.getY() + boundingBox.getHeight());
		fontFile.println("/FontBBox {" + llx + " " + lly + " " + urx + " " + ury
				+ "} readonly def");

		fontFile.println("currentdict end");

		// begin encrypted portion
		if (ENCRYPT) {
			fontFile.print("currentfile eexec ");
			asciiEnd = byteCounter.getCount();
		}
		fontFile.flush();
	}

	@Override
	protected void openGlyphs() throws IOException {
		// begin encryption
		if (ENCRYPT) {
			if (HEX_ENC) {
				encrypted = new PrintStream(
						new EEXECEncryption(new ASCIIHexOutputStream(fontFile),
								EEXECEncryption.EEXEC_R));
			} else {
				encrypted = new PrintStream(
						new EEXECEncryption(fontFile, EEXECEncryption.EEXEC_R));
			}
		} else {
			encrypted = fontFile;
		}

		// begin the Private dictionary
		encrypted.println("dup /Private 8 dict dup begin");
		encrypted.println(
				"/RD {string currentfile exch readstring pop} executeonly def");
		encrypted.println("/ND {noaccess def} executeonly def");
		encrypted.println("/NP {noaccess put} executeonly def");
		encrypted.println("/BlueValues [] def"); // ???
		encrypted.println("/MinFeature {16 16} def");
		encrypted.println("/password 5839 def");
		encrypted.print("2 index ");
		encrypted.println("/CharStrings " + (getNODefinedChars() + 1)
				+ " dict dup begin");
	}

	@Override
	protected void closeGlyphs() throws IOException {
		encrypted.println("end"); // end Private
		encrypted.println("end"); // end CharStrings
	}

	@Override
	protected void closeEmbedFont() throws IOException {
		encrypted.println("readonly put");
		encrypted.println("noaccess put");
		encrypted.println("dup /FontName get exch definefont pop");
		encrypted.print("mark");
		if (ENCRYPT) {
			encrypted.print(" currentfile closefile ");
		}
		encrypted.flush();
		encEnd = byteCounter.getCount();
		if (!ENCRYPT) {
			asciiEnd = encEnd;
		}

		if (addZeros) {
			fontFile.println();
			for (int i = 0; i < 16; i++) {
				fontFile.println("00000000000000000000000000000000");
			}
			fontFile.println("cleartomark");
		}
	}

	@Override
	protected void writeGlyph(String characterName, Shape glyph,
			GlyphMetrics glyphMetrics) throws IOException {

		// FIXME: find out why Acrobat Reader displays some characters displaced
		// when
		// using the correct sidebearing. A value of 0 looks good
		double sidebearing = glyphMetrics != null ? glyphMetrics.getLSB() : 0;
		// double sidebearing = 0;

		// write the binary charstring to a buffer
		ByteArrayOutputStream bytes = new ByteArrayOutputStream();
		CharstringEncoder charString = (HEX_ENC_CHARSTRINGS
				? new CharstringEncoder(
						new EEXECEncryption(new ASCIIHexOutputStream(bytes),
								EEXECEncryption.CHARSTRING_R))
				: new CharstringEncoder(new EEXECEncryption(bytes,
						EEXECEncryption.CHARSTRING_R)));

		charString.startChar(sidebearing, (glyphMetrics != null
				? glyphMetrics.getAdvance() : getUndefinedWidth())); // bounds.getWidth());
		charString.drawPath(glyph);
		charString.endchar();

		// write the buffer to the encrypted fontFile
		byte[] binaryString = bytes.toByteArray();
		encrypted.print(
				"/" + characterName + " " + binaryString.length + " RD ");
		for (byte b : binaryString) {
			encrypted.write(b & 0x00ff);
		}
		encrypted.println("ND");
		encrypted.flush();
	}

	/** Returns the length of the ascii portion of the output. */
	public int getAsciiLength() {
		return asciiEnd;
	}

	/** Returns the length of the encrypted portion of the output. */
	public int getEncryptedLength() {
		return encEnd - asciiEnd;
	}

}
