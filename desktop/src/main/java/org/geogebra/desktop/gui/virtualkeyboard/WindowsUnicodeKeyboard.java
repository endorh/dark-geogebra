package org.geogebra.desktop.gui.virtualkeyboard;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

//
/**
 * adapted from
 * http://stackoverflow.com/questions/1248510/convert-string-to-keyevents
 */
public class WindowsUnicodeKeyboard extends Keyboard {

	private Robot windowsRobot;

	/**
	 * @param robot
	 *            robot
	 */
	public WindowsUnicodeKeyboard(Robot robot) {
		super(robot);
		this.windowsRobot = robot;
	}

	/**
	 * @throws AWTException
	 *             when super fails
	 */
	public WindowsUnicodeKeyboard() throws AWTException {
		super();
		this.windowsRobot = super.robot;
	}

	@Override
	public void type(char character) {
		try {
			super.type(character);
		} catch (IllegalArgumentException e) {

			int unicodeDigits = character;
			// Application.debug(unicodeDigits+"");
			windowsRobot.keyPress(KeyEvent.VK_ALT);

			try { // make sure Alt is released!

				// convert to decimal (with leading zero(es))
				int digit = unicodeDigits / 1000;
				unicodeDigits -= digit * 1000;
				typeNumPad(digit);
				digit = unicodeDigits / 100;
				unicodeDigits -= digit * 100;
				typeNumPad(digit);
				digit = unicodeDigits / 10;
				unicodeDigits -= digit * 10;
				typeNumPad(digit);
				typeNumPad(unicodeDigits);
			} catch (Exception ex) {
				ex.printStackTrace();
			}

			windowsRobot.keyRelease(KeyEvent.VK_ALT);

		}
	}

	private void typeNumPad(int digit) {
		switch (digit) {
		default -> {
		}
		// do nothing
		case 0 -> doType(KeyEvent.VK_NUMPAD0);
		case 1 -> doType(KeyEvent.VK_NUMPAD1);
		case 2 -> doType(KeyEvent.VK_NUMPAD2);
		case 3 -> doType(KeyEvent.VK_NUMPAD3);
		case 4 -> doType(KeyEvent.VK_NUMPAD4);
		case 5 -> doType(KeyEvent.VK_NUMPAD5);
		case 6 -> doType(KeyEvent.VK_NUMPAD6);
		case 7 -> doType(KeyEvent.VK_NUMPAD7);
		case 8 -> doType(KeyEvent.VK_NUMPAD8);
		case 9 -> doType(KeyEvent.VK_NUMPAD9);
		}
	}

}
// */
