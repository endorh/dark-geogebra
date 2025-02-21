package org.freehep.graphicsio.font.truetype;

/**
 * FIXME: These methods are not really tested yet.
 * 
 * @author Simon Fischer
 * @version $Id: TTFMemoryInput.java,v 1.5 2009-08-17 21:44:45 murkle Exp $
 */
public class TTFMemoryInput extends TTFInput {

	private byte[] data;

	private int pointer;

	public TTFMemoryInput(byte[] data) {
		this.data = data;
	}

	@Override
	public void seek(long offset) {
		pointer = (int) offset;
	}

	@Override
	long getPointer() {
		return pointer;
	}

	// ---------- Simple Data Types --------------

	@Override
	public byte readChar() {
		return data[pointer++];
	}

	@Override
	public int readRawByte() {
		return data[pointer++] & 0x00ff;
	}

	@Override
	public int readByte() {
		return data[pointer++] & 0x00ff;
	}

	@Override
	public short readShort() {
		int result = data[pointer++];
		return (short) ((result << 8) | data[pointer++]);
	}

	@Override
	public int readUShort() {
		return (data[pointer++] << 8) | data[pointer++];
	}

	@Override
	public int readLong() {
		int result = data[pointer++];
		return (short) ((result << 24) | data[pointer++] << 16
				| data[pointer++] << 8 | data[pointer++]);
	}

	@Override
	public long readULong() {
		byte[] temp = new byte[4];
		readFully(temp);
		long l = 0;
		for (int i = 0; i < temp.length; i++) {
			l |= (long) (temp[3 - i] & 255) << (8 * i);
		}
		return l;
	}

	// ---------------- Arrays -------------------

	@Override
	public void readFully(byte[] b) {
		for (int i = 0; i < b.length; i++) {
			b[i] = data[pointer++];
		}
	}
}
