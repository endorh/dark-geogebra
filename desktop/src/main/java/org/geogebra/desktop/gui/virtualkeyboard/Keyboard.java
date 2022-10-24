package org.geogebra.desktop.gui.virtualkeyboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

import org.geogebra.common.util.debug.Log;

//http://stackoverflow.com/questions/1248510/convert-string-to-keyevents

/**
 * adapted from
 * http://stackoverflow.com/questions/1248510/convert-string-to-keyevents
 */
public class Keyboard {

	protected Robot robot;

	public static void mainx(String... args) throws Exception {
		Keyboard keyboard = new Keyboard();
		keyboard.type("Hello there, how are you?");
	}

	public Keyboard() throws AWTException {
		this.robot = new Robot();
	}

	public Keyboard(Robot robot) {
		this.robot = robot;
	}

	public void type(CharSequence characters) {
		int length = characters.length();
		for (int i = 0; i < length; i++) {
			char character = characters.charAt(i);
			type(character);
		}
	}

	public void type(boolean altPressed, boolean ctrlPressed,
			boolean shiftPressed, CharSequence characters) {

		if (altPressed)
		 {
			robot.keyPress(KeyEvent.VK_ALT);// */
		}
		if (ctrlPressed) {
			robot.keyPress(KeyEvent.VK_CONTROL);
		}
		if (shiftPressed)
		 {
			robot.keyPress(KeyEvent.VK_SHIFT);// */
		}

		int length = characters.length();
		for (int i = 0; i < length; i++) {
			char character = characters.charAt(i);
			type(character);
		}

		robot.keyRelease(KeyEvent.VK_ALT);// */
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_SHIFT);// */

	}

	public void type(char character) {
		switch (character) {
		case 'a' -> doType(KeyEvent.VK_A);
		case 'b' -> doType(KeyEvent.VK_B);
		case 'c' -> doType(KeyEvent.VK_C);
		case 'd' -> doType(KeyEvent.VK_D);
		case 'e' -> doType(KeyEvent.VK_E);
		case 'f' -> doType(KeyEvent.VK_F);
		case 'g' -> doType(KeyEvent.VK_G);
		case 'h' -> doType(KeyEvent.VK_H);
		case 'i' -> doType(KeyEvent.VK_I);
		case 'j' -> doType(KeyEvent.VK_J);
		case 'k' -> doType(KeyEvent.VK_K);
		case 'l' -> doType(KeyEvent.VK_L);
		case 'm' -> doType(KeyEvent.VK_M);
		case 'n' -> doType(KeyEvent.VK_N);
		case 'o' -> doType(KeyEvent.VK_O);
		case 'p' -> doType(KeyEvent.VK_P);
		case 'q' -> doType(KeyEvent.VK_Q);
		case 'r' -> doType(KeyEvent.VK_R);
		case 's' -> doType(KeyEvent.VK_S);
		case 't' -> doType(KeyEvent.VK_T);
		case 'u' -> doType(KeyEvent.VK_U);
		case 'v' -> doType(KeyEvent.VK_V);
		case 'w' -> doType(KeyEvent.VK_W);
		case 'x' -> doType(KeyEvent.VK_X);
		case 'y' -> doType(KeyEvent.VK_Y);
		case 'z' -> doType(KeyEvent.VK_Z);
		case 'A' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_A);
		case 'B' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_B);
		case 'C' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_C);
		case 'D' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_D);
		case 'E' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_E);
		case 'F' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_F);
		case 'G' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_G);
		case 'H' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_H);
		case 'I' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_I);
		case 'J' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_J);
		case 'K' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_K);
		case 'L' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_L);
		case 'M' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_M);
		case 'N' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_N);
		case 'O' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_O);
		case 'P' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_P);
		case 'Q' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Q);
		case 'R' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_R);
		case 'S' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_S);
		case 'T' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_T);
		case 'U' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_U);
		case 'V' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_V);
		case 'W' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_W);
		case 'X' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_X);
		case 'Y' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Y);
		case 'Z' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_Z);
		case '`' -> doType(KeyEvent.VK_BACK_QUOTE);

		/*
		 * case '0': doType(KeyEvent.VK_0); break; case '1':
		 * doType(KeyEvent.VK_1); break; case '2': doType(KeyEvent.VK_2); break;
		 * case '3': doType(KeyEvent.VK_3); break; case '4':
		 * doType(KeyEvent.VK_4); break; case '5': doType(KeyEvent.VK_5); break;
		 * case '6': doType(KeyEvent.VK_6); break; case '7':
		 * doType(KeyEvent.VK_7); break; case '8': doType(KeyEvent.VK_8); break;
		 * case '9': doType(KeyEvent.VK_9); break;
		 */
		case '0' -> doType(KeyEvent.VK_NUMPAD0);
		case '1' -> doType(KeyEvent.VK_NUMPAD1);
		case '2' -> doType(KeyEvent.VK_NUMPAD2);
		case '3' -> doType(KeyEvent.VK_NUMPAD3);
		case '4' -> doType(KeyEvent.VK_NUMPAD4);
		case '5' -> doType(KeyEvent.VK_NUMPAD5);
		case '6' -> doType(KeyEvent.VK_NUMPAD6);
		case '7' -> doType(KeyEvent.VK_NUMPAD7);
		case '8' -> doType(KeyEvent.VK_NUMPAD8);
		case '9' -> doType(KeyEvent.VK_NUMPAD9);
		case '-' -> doType(KeyEvent.VK_MINUS);
		case '=' -> doType(KeyEvent.VK_EQUALS);
		case '~' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_QUOTE);
		case '!' -> doType(KeyEvent.VK_EXCLAMATION_MARK);
		case '@' -> doType(KeyEvent.VK_AT);
		case '#' -> doType(KeyEvent.VK_NUMBER_SIGN);
		case '$' -> doType(KeyEvent.VK_DOLLAR);
		case '%' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_5);
		case '^' -> doType(KeyEvent.VK_CIRCUMFLEX);
		case '&' -> doType(KeyEvent.VK_AMPERSAND);
		case '*' -> doType(KeyEvent.VK_ASTERISK);
		case '(' -> doType(KeyEvent.VK_LEFT_PARENTHESIS);
		case ')' -> doType(KeyEvent.VK_RIGHT_PARENTHESIS);
		case '_' -> doType(KeyEvent.VK_UNDERSCORE);
		case '+' -> doType(KeyEvent.VK_PLUS);
		case '\t' -> doType(KeyEvent.VK_TAB);
		case '\n' -> doType(KeyEvent.VK_ENTER);
		case '[' -> doType(KeyEvent.VK_OPEN_BRACKET);
		case ']' -> doType(KeyEvent.VK_CLOSE_BRACKET);
		case '\\' -> doType(KeyEvent.VK_BACK_SLASH);
		case '{' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_OPEN_BRACKET);
		case '}' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_CLOSE_BRACKET);
		case '|' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_BACK_SLASH);
		case ';' -> doType(KeyEvent.VK_SEMICOLON);
		case ':' -> doType(KeyEvent.VK_COLON);
		case '\'' -> doType(KeyEvent.VK_QUOTE);
		case '"' -> doType(KeyEvent.VK_QUOTEDBL);
		case ',' -> doType(KeyEvent.VK_COMMA);
		case '<' -> doType(KeyEvent.VK_LESS);
		case '.' -> doType(KeyEvent.VK_PERIOD);
		case '>' -> doType(KeyEvent.VK_GREATER);
		case '/' -> doType(KeyEvent.VK_SLASH);
		case '?' -> doType(KeyEvent.VK_SHIFT, KeyEvent.VK_SLASH);
		case ' ' -> doType(KeyEvent.VK_SPACE);
		default -> throw new IllegalArgumentException(
				"Cannot type character " + character);
		}
	}

	/*
	 * process strings such as <enter>
	 */
	public void doType(boolean altPressed, boolean ctrlPressed,
			boolean shiftPressed, String text) {
		if (!text.startsWith("<") || !text.endsWith(">")) {
			type(altPressed, ctrlPressed, shiftPressed, text);
		} else {
			switch (text) {
			case "<escape>" -> doType(altPressed, ctrlPressed, shiftPressed,
					KeyEvent.VK_ESCAPE);
			case "<left>" -> doType(altPressed, ctrlPressed, shiftPressed, KeyEvent.VK_LEFT);
			case "<right>" -> doType(altPressed, ctrlPressed, shiftPressed,
					KeyEvent.VK_RIGHT);
			case "<up>" -> doType(altPressed, ctrlPressed, shiftPressed, KeyEvent.VK_UP);
			case "<down>" -> doType(altPressed, ctrlPressed, shiftPressed, KeyEvent.VK_DOWN);
			case "<backspace>" -> doType(altPressed, ctrlPressed, shiftPressed,
					KeyEvent.VK_BACK_SPACE);
			default -> Log.debug("unknown keycode:" + text);
			}
		}

	}

	public void doType(boolean altPressed, boolean ctrlPressed,
			boolean shiftPressed, int... keyCodes) {

		if (altPressed)
		 {
			robot.keyPress(KeyEvent.VK_ALT); // */
		}
		if (ctrlPressed) {
			robot.keyPress(KeyEvent.VK_CONTROL);
		}
		if (shiftPressed)
		 {
			robot.keyPress(KeyEvent.VK_SHIFT);// */
		}

		doType(keyCodes, 0, keyCodes.length);

		robot.keyRelease(KeyEvent.VK_ALT);// */
		robot.keyRelease(KeyEvent.VK_CONTROL);
		robot.keyRelease(KeyEvent.VK_SHIFT);// */
	}

	public void doType(int... keyCodes) {
		doType(keyCodes, 0, keyCodes.length);
	}

	public void altPressed(boolean press) {
		if (press) {
			robot.keyPress(KeyEvent.VK_ALT);
		} else {
			robot.keyRelease(KeyEvent.VK_ALT);
		}
	}

	private void doType(int[] keyCodes, int offset, int length) {
		if (length == 0) {
			return;
		}

		robot.keyPress(keyCodes[offset]);
		doType(keyCodes, offset + 1, length - 1);
		robot.keyRelease(keyCodes[offset]);
	}

}
