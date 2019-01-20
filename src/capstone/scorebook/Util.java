package capstone.scorebook;

import java.util.Collection;
import java.util.StringJoiner;

public class Util {

	private static char QUOTE = '\''; // might be better to use ``?

	public interface ItemConstructor<E> {
		String construct(E e);
	}

	public static <E> String formList(String delim, ItemConstructor<E> constructor, Collection<E> collection) {

		StringJoiner j = new StringJoiner(delim);

		for (E e : collection) {

			String item = constructor.construct(e);

			if (item != null) j.add(item);

		}

		return j.toString();

	}

	public static String getFormatted(Object o) {

		if (o instanceof String) return QUOTE + ((String) o).replaceAll("'", "''") + QUOTE;
		else return o.toString();

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
