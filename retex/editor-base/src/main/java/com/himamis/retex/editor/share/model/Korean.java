package com.himamis.retex.editor.share.model;

import java.util.HashMap;

/**
 * @author Michael Borcherds
 * 
 *         Various methods for converting between Korean Unicode formats
 * 
 *         http://gernot-katzers-spice-pages.com/var/korean_hangul_unicode.html
 * 
 *         https://en.wikipedia.org/wiki/Hangul_Compatibility_Jamo
 * 
 *         https://en.wikipedia.org/wiki/Hangul_Syllables
 * 
 *         https://en.wikipedia.org/wiki/Korean_language_and_computers#Example
 * 
 *         https://en.wikipedia.org/wiki/Hangul_Jamo_(Unicode_block)
 * 
 *
 */
public class Korean {

	private static StringBuilder sb;
	private static HashMap<Character, Character> leadToTail = null;
	private static HashMap<Character, Character> tailToLead = null;
	// table to convert a nibble to a hex char.

	/**
	 * @return HashMap for converting Lead char to Tail char
	 */
	static HashMap<Character, Character> getKoreanLeadToTail() {

		if (leadToTail == null) {
			HashMap<Character, Character> lt = new HashMap<>();

			lt.put('\u1100', '\u11a8');
			lt.put('\u1101', '\u11a9');
			lt.put('\u1102', '\u11ab');
			lt.put('\u1103', '\u11ae');

			// map to itself
			lt.put('\u1104', '\u1104');

			lt.put('\u1105', '\u11af');
			lt.put('\u1106', '\u11b7');
			lt.put('\u1107', '\u11b8');

			// map to itself
			lt.put('\u1108', '\u1108');

			lt.put('\u1109', '\u11ba');
			lt.put('\u110a', '\u11bb');
			lt.put('\u110b', '\u11bc');
			lt.put('\u110c', '\u11bd');

			// map to itself
			lt.put('\u110d', '\u110d');

			lt.put('\u110e', '\u11be');
			lt.put('\u110f', '\u11bf');
			lt.put('\u1110', '\u11c0');
			lt.put('\u1111', '\u11c1');
			lt.put('\u1112', '\u11c2');
			leadToTail = lt;
		}

		return leadToTail;
	}

	private static Character tailToLead(char ch) {

		if (Korean.isKoreanLeadChar(ch, false)) {
			return ch;
		}

		return getKoreanTailToLead().get(ch);
	}

	private static HashMap<Character, Character> getKoreanTailToLead() {

		if (tailToLead == null) {
			HashMap<Character, Character> tl = new HashMap<>();

			tl.put('\u11a8', '\u1100');
			tl.put('\u11a9', '\u1101');
			tl.put('\u11ab', '\u1102');
			tl.put('\u11ae', '\u1103');

			// map to itself
			// koreanTailToLead.put('\u1104','\u1104');

			tl.put('\u11af', '\u1105');
			tl.put('\u11b7', '\u1106');
			tl.put('\u11b8', '\u1107');

			// map to itself
			// koreanTailToLead.put('\u1108', '\u1108');

			tl.put('\u11ba', '\u1109');
			tl.put('\u11bb', '\u110a');
			tl.put('\u11bc', '\u110b');
			tl.put('\u11bd', '\u110c');

			// map to itself
			// koreanTailToLead.put('\u110d', '\u110d');

			tl.put('\u11be', '\u110e');
			tl.put('\u11bf', '\u110f');
			tl.put('\u11c0', '\u1110');
			tl.put('\u11c1', '\u1111');
			tl.put('\u11c2', '\u1112');
			tailToLead = tl;
		}

		return tailToLead;

	}

	/**
	 * same as Normalizer.normalize(s, Normalizer.Form.NFD) but GWT compatible
	 * 
	 * convert eg \uB458 to \u1103\u116e\u11af
	 * 
	 * and \uB450 to \u1103\u116E
	 * 
	 * @param s
	 *            input string
	 * @return flattened string
	 */
	public static String flattenKorean(String s) {

		if (sb == null) {
			sb = new StringBuilder();
		} else {
			sb.setLength(0);
		}

		boolean lastWasVowel = false;

		for (int i = 0; i < s.length(); i++) {
			char c = convertFromCompatibilityJamo(s.charAt(i), !lastWasVowel);
			if (isKoreanMultiChar(c)) {
				appendKoreanMultiChar(sb, c);
			} else {
				// if a "lead char" follows a vowel, turn into a "tail char"
				if (lastWasVowel && isKoreanLeadChar(c, false)) {
					sb.append(getKoreanLeadToTail().get(c)
							.charValue());
				} else {
					sb.append(c);
				}
			}
			lastWasVowel = isKoreanVowelChar(sb.charAt(sb.length() - 1), false);
		}

		return sb.toString();
	}

	/**
	 * from 0xac00 to 0xd788, every 28th character is a combination of 2
	 * characters not 3
	 * 
	 * @param c
	 *            character
	 * @return whether the char is combination of three simple chars
	 */
	public static boolean isKoreanLeadPlusVowelChar(char c) {
		if (c >= 0xac00 && c <= 0xd7af) {
			int ch = c - 0xac00;
			return (ch % 28) == 0;
		}

		return false;
	}

	private static boolean isKoreanMultiChar(char c) {
		return c >= 0xac00 && c <= 0xd7af;
	}

	private static boolean isKoreanLeadChar(char c0, boolean convertJamo) {
		char c = c0;
		if (convertJamo) {
			c = convertFromCompatibilityJamo(c, true);
		}

		return c >= 0x1100 && c <= 0x1112;
	}

	private static boolean isKoreanVowelChar(char c0, boolean convertJamo) {
		char c = c0;
		if (convertJamo) {
			c = convertFromCompatibilityJamo(c, true);
		}

		return c >= 0x1161 && c <= 0x1175;
	}

	private static boolean isKoreanTailChar(char c0, boolean convertJamo) {
		char c = c0;
		if (convertJamo) {
			c = convertFromCompatibilityJamo(c, false);
		}

		return c >= 0x11a8 && c <= 0x11c2;
	}

	/**
	 * 
	 * Does the same as Normalizer.normalize(s, Normalizer.Form.NFKC) but GWT
	 * compatible
	 * 
	 * convert eg \u1103\u116e\u11af to \uB458
	 * 
	 * also converts 2 chars eg \u1103\u116E to \uB450
	 * 
	 * @param str
	 *            string to convert
	 * @return converted string
	 */
	public static StringBuilder unflattenKorean(String str) {

		StringBuilder ret = new StringBuilder();

		char lead = 0;
		char vowel = 0;
		char tail = 0;

		for (int i = 0; i < str.length(); i++) {

			boolean korean = false;

			char c = convertFromCompatibilityJamo(str.charAt(i), lead == 0);

			if (isKoreanLeadChar(c, false)) {
				korean = true;
				if (lead != 0) {
					appendKoreanChar(ret, lead, vowel, tail);
					lead = 0;
					vowel = 0;
					tail = 0;
				}
				lead = c;
			}
			if (isKoreanVowelChar(c, false)) {
				korean = true;
				vowel = c;
			}
			if (isKoreanTailChar(c, false)) {
				korean = true;
				tail = c;
				appendKoreanChar(ret, lead, vowel, tail);
				lead = 0;
				vowel = 0;
				tail = 0;
			}

			if (!korean) {
				// needed for eg
				// "\uD56D\uC131\uC740 \uD56D\uC0C1 \uD63C\uC790 \uC788\uB294
				// \uAC83\uC774 \uC544\uB2C8\uB77C, \uB450 \uAC1C
				// \uC774\uC0C1\uC758"
				// to stop order changing
				if (lead != 0) {
					appendKoreanChar(ret, lead, vowel, tail);
					lead = 0;
					vowel = 0;
					tail = 0;

				}
				ret.append(c);
			}
		}

		// make sure last char done!
		if (lead != 0) {
			appendKoreanChar(ret, lead, vowel, tail);
		}

		return ret;
	}

	private static void appendKoreanChar(StringBuilder ret, char lead,
			char vowel, char tail) {

		int lead0 = lead - 0x1100 + 1;
		int vowel0 = vowel - 0x1161 + 1;
		int tail0 = tail == 0 ? 0 : tail - 0x11a8 + 1;

		// http://gernot-katzers-spice-pages.com/var/korean_hangul_unicode.html
		char unicode = (char) (tail0 + (vowel0 - 1) * 28 + (lead0 - 1) * 588
				+ 44032);

		ret.append(unicode);
	}

	/**
	 * https://en.wikipedia.org/wiki/Hangul_Compatibility_Jamo
	 * 
	 * @param ch
	 *            jamo
	 * @param lead
	 *            whether it's the lead char
	 * @return hangul character
	 */
	public static char convertFromCompatibilityJamo(char ch, boolean lead) {
		return switch (ch) {
			case '\u3131' -> lead ? '\u1100' : '\u11a8';
			case '\u3132' -> lead ? '\u1101' : '\u11a9';
			case '\u3133' -> '\u11aa';
			case '\u3134' -> lead ? '\u1102' : '\u11ab';
			case '\u3135' -> '\u11ac';
			case '\u3136' -> '\u11ad';
			case '\u3137' -> lead ? '\u1103' : '\u11ae';
			case '\u3138' -> '\u1104';
			case '\u3139' -> lead ? '\u1105' : '\u11af';
			case '\u313a' -> '\u11b0';
			case '\u313b' -> '\u11b1';
			case '\u313c' -> '\u11b2';
			case '\u313d' -> '\u11b3';
			case '\u313e' -> '\u11b4';
			case '\u313f' -> '\u11b5';
			case '\u3140' -> '\u11b6';
			case '\u3141' -> lead ? '\u1106' : '\u11b7';
			case '\u3142' -> lead ? '\u1107' : '\u11b8';
			case '\u3143' -> '\u1108';
			case '\u3144' -> lead ? '\u1121' : '\u11b9';
			case '\u3145' -> lead ? '\u1109' : '\u11ba';
			case '\u3146' -> lead ? '\u110a' : '\u11bb';
			case '\u3147' -> lead ? '\u110b' : '\u11bc';
			case '\u3148' -> lead ? '\u110c' : '\u11bd';
			case '\u3149' -> '\u110d';
			case '\u314a' -> lead ? '\u110e' : '\u11be';
			case '\u314b' -> lead ? '\u110f' : '\u11bf';
			case '\u314c' -> lead ? '\u1110' : '\u11c0';
			case '\u314d' -> lead ? '\u1111' : '\u11c1';
			case '\u314e' -> lead ? '\u1112' : '\u11c2';
			case '\u314f' -> '\u1161';
			case '\u3150' -> '\u1162';
			case '\u3151' -> '\u1163';
			case '\u3152' -> '\u1164';
			case '\u3153' -> '\u1165';
			case '\u3154' -> '\u1166';
			case '\u3155' -> '\u1167';
			case '\u3156' -> '\u1168';
			case '\u3157' -> '\u1169';
			case '\u3158' -> '\u116a';
			case '\u3159' -> '\u116b';
			case '\u315a' -> '\u116c';
			case '\u315b' -> '\u116d';
			case '\u315c' -> '\u116e';
			case '\u315d' -> '\u116f';
			case '\u315e' -> '\u1170';
			case '\u315f' -> '\u1171';
			case '\u3160' -> '\u1172';
			case '\u3161' -> '\u1173';
			case '\u3162' -> '\u1174';
			case '\u3163' -> '\u1175';
			default -> ch;
		};

	}

	/**
	 * https://en.wikipedia.org/wiki/Hangul_Compatibility_Jamo
	 * 
	 * @param ch
	 *            hangul character
	 * @return compatibility jamo
	 */
	public static char convertToCompatibilityJamo(char ch) {
		return switch (ch) {
			case '\u1100', '\u11a8' -> '\u3131';
			case '\u1101', '\u11a9' -> '\u3132';
			case '\u11aa' -> '\u3133';
			case '\u1102', '\u11ab' -> '\u3134';
			case '\u11ac' -> '\u3135';
			case '\u11ad' -> '\u3136';
			case '\u1103', '\u11ae' -> '\u3137';
			case '\u1104' -> '\u3138';
			case '\u1105', '\u11af' -> '\u3139';
			case '\u11b0' -> '\u313a';
			case '\u11b1' -> '\u313b';
			case '\u11b2' -> '\u313c';
			case '\u11b3' -> '\u313d';
			case '\u11b4' -> '\u313e';
			case '\u11b5' -> '\u313f';
			case '\u11b6' -> '\u3140';
			case '\u1106', '\u11b7' -> '\u3141';
			case '\u1107', '\u11b8' -> '\u3142';
			case '\u1108' -> '\u3143';
			case '\u1121', '\u11b9' -> '\u3144';
			case '\u1109', '\u11ba' -> '\u3145';
			case '\u110a', '\u11bb' -> '\u3146';
			case '\u110b', '\u11bc' -> '\u3147';
			case '\u110c', '\u11bd' -> '\u3148';
			case '\u110d' -> '\u3149';
			case '\u110e', '\u11be' -> '\u314a';
			case '\u110f', '\u11bf' -> '\u314b';
			case '\u1110', '\u11c0' -> '\u314c';
			case '\u1111', '\u11c1' -> '\u314d';
			case '\u1112', '\u11c2' -> '\u314e';
			case '\u1161' -> '\u314f';
			case '\u1162' -> '\u3150';
			case '\u1163' -> '\u3151';
			case '\u1164' -> '\u3152';
			case '\u1165' -> '\u3153';
			case '\u1166' -> '\u3154';
			case '\u1167' -> '\u3155';
			case '\u1168' -> '\u3156';
			case '\u1169' -> '\u3157';
			case '\u116a' -> '\u3158';
			case '\u116b' -> '\u3159';
			case '\u116c' -> '\u315a';
			case '\u116d' -> '\u315b';
			case '\u116e' -> '\u315c';
			case '\u116f' -> '\u315d';
			case '\u1170' -> '\u315e';
			case '\u1171' -> '\u315f';
			case '\u1172' -> '\u3160';
			case '\u1173' -> '\u3161';
			case '\u1174' -> '\u3162';
			case '\u1175' -> '\u3163';
			default -> ch;
		};

	}

	/**
	 * Splits double vowels and consonants and returns the
	 * last component
	 * DOES NOT SPLIT LONG CONSONANTS SUCH AS ᆻ
	 * @param ch korean character
	 * @return last compatibility JAMO character
	 */
	public static char unmergeDoubleCharacterForEditor(char ch) {
		return switch (ch) {
			// vowels
			case '\u116a', '\u1161' -> '\u314f';
			case '\u1162' -> '\u3150';
			case '\u1163' -> '\u3151';
			case '\u116b', '\u1164' -> '\u3152';
			case '\u116f', '\u1165' -> '\u3153';
			case '\u1170', '\u1166' -> '\u3154';
			case '\u1167' -> '\u3155';
			case '\u1168' -> '\u3156';
			case '\u1169' -> '\u3157';
			case '\u116d' -> '\u315b';
			case '\u116e' -> '\u315c';
			case '\u1172' -> '\u3160';
			case '\u1173' -> '\u3161';
			case '\u116c', '\u1171', '\u1174' -> '\u3163';

			// consonants
			case '\u3133', '\u11aa', '\u313d', '\u11b3', '\u3144', '\u1121', '\u11b9' -> '\u3145';
			case '\u3135', '\u11ac' -> '\u3148';
			case '\u3136', '\u11ad', '\u3140', '\u11b6' -> '\u314e';
			case '\u313a', '\u11b0' -> '\u3131';
			case '\u313b', '\u11b1' -> '\u3141';
			case '\u313c', '\u11b2' -> '\u3142';
			case '\u313e', '\u11b4' -> '\u314c';
			case '\u313f', '\u11b5' -> '\u314d';
			default -> convertToCompatibilityJamo(ch);
		};

	}

	/*
	 * http://www.kfunigraz.ac.at/~katzer/korean_hangul_unicode.html
	 * http://gernot-katzers-spice-pages.com/var/korean_hangul_unicode.html
	 */
	private static void appendKoreanMultiChar(StringBuilder sBuilder, char c) {
		char tail = (char) (0x11a7 + (c - 44032) % 28);
		char vowel = (char) (0x1161
				+ ((c - 44032 - (tail - 0x11a7)) % 588) / 28);
		char lead = (char) (0x1100 + (c - 44032) / 588);
		// Application.debug(Util.toHexString(c)+" decoded to
		// "+Util.toHexString(lead)+Util.toHexString(vowel)+Util.toHexString(tail));
		sBuilder.append(lead);
		sBuilder.append(vowel);
		if (!isKoreanLeadPlusVowelChar(c)) {
			sBuilder.append(tail);
		}
	}

	private static String unmergeDoubleCharacterToLeadTail(char c) {

		return switch (c) {
			case '\u3149', '\u110d' -> "\u11bd\u110c";
			case '\u3143', '\u1108' -> "\u11b8\u1107";
			case '\u3132', '\u1101', '\u11a9' -> "\u11a8\u1100";
			case '\u3133', '\u11aa' -> "\u11a8\u1109";
			case '\u3135', '\u11ac' -> "\u11ab\u110c";
			case '\u3136', '\u11ad' -> "\u11ab\u1112";
			case '\u313a', '\u11b0' -> "\u11af\u1100";
			case '\u313b', '\u11b1' -> "\u11af\u1106";
			case '\u313c', '\u11b2' -> "\u11af\u1107";
			case '\u313d', '\u11b3' -> "\u11af\u1109";
			case '\u313e', '\u11b4' -> "\u11af\u1110";
			case '\u313f', '\u11b5' -> "\u11af\u1111";
			case '\u3140', '\u11b6' -> "\u11af\u1112";
			case '\u3144', '\u1121', '\u11b9' -> "\u11b8\u1109";
			case '\u110A', '\u11BB', '\u3145' ->
				// tail + lead
					"\u11ba\u1109";
			default -> c + "";
		};

	}

	/*
	 * avoid having to press shift by merging eg \u1100\u1100 to \u1101
	 * http://www.kfunigraz.ac.at/~katzer/korean_hangul_unicode.html (not
	 * needed/used currently - can be entered with <shift>)
	 * 
	 * and merging doubled vowel characters (is used)
	 */
	private static String mergeDoubleCharacters(String str) {

		if (str.length() < 2) {
			return str;
		}

		if (sb == null) {
			sb = new StringBuilder();
		} else {
			sb.setLength(0);
		}

		char c, c2;

		for (int i = 0; i < str.length() - 1; i++) {
			switch (c = str.charAt(i)) {

			case '\u11ab':
			case '\u1102':
				switch (str.charAt(i + 1)) {
				case '\u110c', '\u11bd' -> {
					sb.append('\u11ac');
					i++;
				}
				case '\u1112', '\u11c2' -> {
					sb.append('\u11ad');
					i++;
				}
				default -> sb.append(c);
				}
				break;

			case '\u1105':
			case '\u11af':
				switch (str.charAt(i + 1)) {
				case '\u3131', '\u1100', '\u11a8' -> {
					sb.append('\u11b0');
					i++;
				}
				case '\u3141', '\u1106', '\u11b7' -> {
					sb.append('\u11b1');
					i++;
				}
				case '\u3142', '\u11b8', '\u1107' -> {
					sb.append('\u11b2');
					i++;
				}
				case '\u3145', '\u1109', '\u11ba' -> {
					sb.append('\u11b3');
					i++;
				}
				case '\u314c', '\u1110', '\u11c0' -> {
					sb.append('\u11b4');
					i++;
				}
				case '\u314d', '\u1111', '\u11c1' -> {
					sb.append('\u11b5');
					i++;
				}
				case '\u314e', '\u1112', '\u11c2' -> {
					sb.append('\u11b6');
					i++;
				}
				default -> sb.append(c);
				}
				break;
			case '\u1169':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1161') {
					sb.append('\u116a'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1162') {
					sb.append('\u116b'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1175') {
					sb.append('\u116c'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u116e':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1165') {
					sb.append('\u116f'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1166') {
					sb.append('\u1170'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1175') {
					sb.append('\u1171'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u1173':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1175') {
					sb.append('\u1174'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u1100':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1100') {
					// assume we want lead \u1101 not tail \u11a9
					// eg \u3131 + \u3131 + \u314F -> \uAE4C
					sb.append('\u1101'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1109') {
					sb.append('\u11aa'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			// case '\u1102':
			// c2 = str.charAt(i + 1);
			// if (c2 == '\u110c') {
			// sb.append('\u11ac'); // eg \u1101 ie doubled char
			// i++;
			// } else if (c2 == '\u1112') {
			// sb.append('\u11ad'); // eg \u1101 ie doubled char
			// i++;
			// } else {
			// sb.append(c);
			// }
			// break;
			case '\u1111':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1111') {
					sb.append('\u11b5'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u1107':
				c2 = str.charAt(i + 1);
				if (c2 == '\u1109') {
					sb.append('\u11b9'); // eg \u1101 ie doubled char
					i++;
				} else if (c2 == '\u1107') {
					sb.append('\u1108'); // eg \u1101 ie doubled char
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u11b8':
				c2 = str.charAt(i + 1);
				if (c2 == '\u11ba') {
					sb.append('\u11b9');
					i++;
				} else {
					sb.append(c);
				}
				break;
			case '\u11a8':
				c2 = str.charAt(i + 1);
				if (c2 == '\u11ba') {
					sb.append('\u11aa');
					i++;
				} else if (c == '\u1109') {
						sb.append('\u11aa');
						i++;
			} else {
					sb.append(c);
				}
				break;
			default:
				sb.append(c);
			}
			if (i == str.length() - 2) {
				sb.append(str.charAt(str.length() - 1));
			}

		}

		return sb.toString();
	}

	/**
	 * tries to combine lastChar+newChar as a single char or two chars if
	 * necessary
	 * 
	 * not as simple as doing this!! unflatten(flatten(lastChar)+newChar)
	 * 
	 * @param lastChar
	 *            already typed character
	 * @param newChar
	 *            new character just typed
	 * @return {char, char2} if 2 chars are needed, otherwise just {char, 0}
	 */
	public static char[] checkMerge(char lastChar, char newChar) {
		char[] ret = { lastChar, newChar };
		// case 0: two characters as a shortcut for one
		// eg \u3131\u3145 -> \u3133
		String check = mergeDoubleCharacters(convertFromCompatibilityJamo(lastChar, true)
				+ "" + convertFromCompatibilityJamo(newChar, true));
		if (check.length() == 1) {
			// merge succeeded
			ret[0] = convertToCompatibilityJamo(check.charAt(0));
			ret[1] = 0;
			return ret;
		}

		// case 1
		// we already have Jamo lead + vowel as single unicode

		if (Korean.isKoreanLeadPlusVowelChar(lastChar)
				&& Korean.isKoreanTailChar(newChar, true)) {

			String strToFlatten = Korean.flattenKorean(lastChar + "") + ""
					+ newChar;

			String replaceChar = Korean.unflattenKorean(strToFlatten)
					.toString();

			char c = replaceChar.charAt(0);

			ret[0] = c;
			ret[1] = 0;
			return ret;

		}

		// case 2
		// we already have just Jamo lead char as single unicode

		if (Korean.isKoreanLeadChar(lastChar, true)
				&& Korean.isKoreanVowelChar(newChar, true)) {
			String replaceChar = Korean.unflattenKorean(lastChar + "" + newChar)
					.toString();
			char c = replaceChar.charAt(0);

			ret[0] = c;
			ret[1] = 0;
			return ret;

		}

		// case 4
		// we have something like
		// \u3141 \u3163 \u3142 \u315C \u3134
		// which has been grouped as
		// (\u3141 \u3163 \u3142) + \u315C
		// but when \u3134 is typed it needs to change to
		// (\u3141 \u3163) + (\u3142 \u315C \u3134)
		// ie "\u3134" needs to change from tail (\u11ab) to lead (\u1102)

		String lastCharFlat = Korean.flattenKorean(lastChar + "");

		if (lastCharFlat.length() == 3
				&& Korean.isKoreanVowelChar(newChar, true)) {

			// not needed, useful for debugging
			// newChar = Korean.convertFromCompatibilityJamo(newChar,
			// false);

			char newLastChar = Korean
					.unflattenKorean(lastCharFlat.substring(0, 2)).charAt(0);

			char newNewChar = Korean.unflattenKorean(
					Korean.tailToLead(lastCharFlat.charAt(2)) + "" + newChar)
					.charAt(0);

			if (Korean.tailToLead(lastCharFlat.charAt(2)) != null) {
				ret[0] = newLastChar;
				ret[1] = newNewChar;
				return ret;
			}

			String unmerged = unmergeDoubleCharacterToLeadTail(
					lastCharFlat.charAt(2));

			newLastChar = Korean.unflattenKorean(
					lastCharFlat.substring(0, 2) + "" + unmerged.charAt(0))
					.charAt(0);

			newNewChar = Korean.unflattenKorean(
					Korean.tailToLead(unmerged.charAt(1)) + "" + newChar)
					.charAt(0);
			ret[0] = newLastChar;
			ret[1] = newNewChar;
			return ret;

		}

		// case5: a tailed char is doubled
		// entered as two key presses
		// eg \u3131 \u314F \u3142 \u3145 needs to give \uAC12
		// or tail char made from 2 separate chars which can be combined
		// eg \u3141 \u3157 \u3131 \u3145 needs to give \uBAAB

		if (lastCharFlat.length() == 3
				&& Korean.isKoreanTailChar(newChar, true)) {
			char newChar1 = newChar;
			newChar1 = Korean.convertFromCompatibilityJamo(newChar1, false);

			char lastChar2 = lastCharFlat.charAt(2);

			// if this is length 1, merge succeeded
			String doubleCheck = Korean
					.mergeDoubleCharacters(lastChar2 + "" + newChar1);

			if (doubleCheck.length() == 1) {
				newChar1 = Korean
						.unflattenKorean(
								lastCharFlat.substring(0, 2) + "" + doubleCheck)
						.charAt(0);

				ret[0] = newChar1;
				ret[1] = 0;
				return ret;
			}
		}

		// case 6
		// a vowel character is a "doubled" char
		// case 1
		// we already have Jamo lead + vowel as single unicode

		if (Korean.isKoreanLeadPlusVowelChar(lastChar)
				&& Korean.isKoreanVowelChar(newChar, true)) {

			char lastChar1 = lastCharFlat.charAt(1);

			// if this is length 1, merge succeeded
			String doubleCheck = Korean.mergeDoubleCharacters(lastChar1 + ""
					+ Korean.convertFromCompatibilityJamo(newChar, true));

			if (doubleCheck.length() == 1) {
				char newChar1 = Korean
						.unflattenKorean(
								lastCharFlat.charAt(0) + "" + doubleCheck)
						.charAt(0);

				ret[0] = newChar1;
				ret[1] = 0;
				return ret;
			}
		}

		return ret;
	}

	/**
	 * @param ch
	 *            character
	 * @return whether it's compatibility character
	 */
	public static boolean isCompatibilityChar(char ch) {
		return ch >= '\u3131' && ch <= '\u318e';
	}

	/**
	 * @param ch
	 *            character to test
	 * @return if ch is a single char (ie not a compound one)
	 */
	public static boolean isSingleKoreanChar(char ch) {
		return isKoreanLeadChar(ch, true) || isKoreanVowelChar(ch, true)
				|| isKoreanTailChar(ch, true);
	}
}
