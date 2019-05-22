package capstone.scorebook.test;

import capstone.scorebook.data.concrete.Meet;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.System.out;

public class PrettyPrinter {

	public static void print(List<Meet> meets) {

		out.printf("");

	}

	private static <E> int max(List<E> items, String header, Function<E, String> strFunct) {

		return 1 + Collections.max(Stream.concat(items.stream().map(strFunct), Stream.of(header)).map(String::length).collect(Collectors.toList()));

	}

	public static <E> String toTable(List<E> items, String[] headers, Function<E, String>... functions) {

		int[] colWidths = new int[headers.length];

		for (int i = 0; i < colWidths.length; i++) colWidths[i] = max(items, headers[i], functions[i]);

		StringBuilder b = new StringBuilder();

		for (int i = 0; i < headers.length; i++)
			b.append(String.format("%" + colWidths[i] + "s", headers[i]));

		b.append('\n');

		for (int i = 0; i < items.size(); i++) {

			List<String> rowData = items.stream().map(functions[i]).collect(Collectors.toList());

			for (int j = 0; j < headers.length; j++)
				b.append(String.format("%" + colWidths[j] + "s", functions[j].apply(items.get(i))));

			b.append('\n');

		}

		return b.toString();

	}

}
