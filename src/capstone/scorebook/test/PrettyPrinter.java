package capstone.scorebook.test;

import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.Meet;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PrettyPrinter {

	final static TableFormat FORMAT_ATHLETE = new TableFormat<Athlete>()
		.add("Name", Athlete::getFullName)
		.add("YOG", a -> String.valueOf(a.getYearOfGraduation()));

	final static TableFormat FORMAT_MEET = new TableFormat<Meet>()
		.add("Date", Meet::getDate)
		.add("Address", Meet::getAddressID)
		.add("Season", Meet::getSeason);

	public static <E> String toCSV(List<E> items, TableFormat<E> format) {

		return toTable(items, format.table.keySet(), ",",
			       colName -> colName,
			       (colName, item) -> format.table.get(colName).apply(item));

	}

	public static <E> String toTable(List<E> items, TableFormat<E> format, int padding) {

		HashMap<String, Integer> widths = widths(items, format);

		return toTable(items, format.table.keySet(), "",
			       colName -> padded(colName, widths.get(colName) + padding),
			       (colName, item) -> padded(format.table.get(colName).apply(item), widths.get(colName) + padding));

	}

	interface TwoFunction<X, Y, Out> { // function with two inputs
		Out apply(X x, Y y);
	}

	public static <E> String toTable(List<E> items, Collection<String> colNames, String delim, Function<String, String> colNameFunc, TwoFunction<String, E, String> rowDataFunc) {

		StringBuilder b = new StringBuilder();

		b.append(String.join(delim, colNames.stream().map(colNameFunc).collect(Collectors.toList()))); // col names
		b.append('\n');

		for (E item : items) {

			b.append(String.join(delim, colNames.stream().map(colName -> rowDataFunc.apply(colName, item)).collect(Collectors.toList()))); // table data
			b.append('\n');

		}

		return b.toString();

	}

	private static String padded(String str, int padding) { return String.format("%" + padding + "s", str); }

	private static <E> HashMap<String, Integer> widths(List<E> items, TableFormat<E> format) {

		HashMap<String, Integer> widths = new HashMap();

		for (String colName : format.table.keySet())
			widths.put(colName, Collections.max(Stream.concat(Stream.of(colName), items.stream().map(format.table.get(colName))).map(String::length).collect(Collectors.toList())));

		return widths;

	}

}
