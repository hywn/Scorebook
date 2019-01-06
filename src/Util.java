public class Util {

	private static char QUOTE = '\''; // might be better to use ``?

	public static void appendCleanEquals(StringBuilder b, String key, Object o) { b.append(key); b.append('='); appendClean(b, o); }

	public static void appendClean(StringBuilder b, Object o) { // TODO: appendEqualsClean?

		if (o instanceof String) {

			b.append(QUOTE);
			b.append(o); // TODO: sanitize strings
			b.append(QUOTE);

		} else {

			b.append(o);

		}

	}

	// first N letters or numbers
	public static String firstNLoN(String string, int n) {

		StringBuilder b = new StringBuilder();

		char[] chars = string.toCharArray();

		for (int i = 0; i < chars.length && b.length() < n; i++) {

			char c = chars[i];

			if (!(Character.isLetter(c) || Character.isDigit(c))) continue;

			b.append(c);

		}

		return b.toString();

	}

	// converts int to boolean
	public static boolean toBoolean(int integer) { return integer == 1; }

}
