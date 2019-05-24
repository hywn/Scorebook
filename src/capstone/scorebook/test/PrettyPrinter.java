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

	private static <E> HashMap<String, Integer> widths(List<E> items, TableFormat<E> format) {

		HashMap<String, Integer> widths = new HashMap();

		for (String colName : format.table.keySet())
			widths.put(colName, Collections.max(Stream.concat(Stream.of(colName), items.stream().map(format.table.get(colName))).map(String::length).collect(Collectors.toList())));

		return widths;

	}

	public static <E> String toCSV(List<E> items, TableFormat<E> format) {

		StringBuilder b = new StringBuilder();

		appendRow(b, ",", format.table.keySet(), colName -> colName);

		for (E item : items)
			appendRow(b, ",", format.table.keySet(), colName -> format.table.get(colName).apply(item));


		return b.toString();

	}

	public static <E> String toTable(List<E> items, TableFormat<E> format, int padding) {

		HashMap<String, Integer> widths = widths(items, format);

		StringBuilder b = new StringBuilder();

		appendRow(b, format.table.keySet(), colName -> padded(colName, widths.get(colName) + padding));

		for (E item : items)
			appendRow(b, format.table.keySet(), colName -> padded(format.table.get(colName).apply(item), widths.get(colName) + padding));

		return b.toString();

	}

	public static void appendRow(StringBuilder b, Collection<String> colNames, Function<String, String> colNameToRowData) { appendRow(b, "", colNames, colNameToRowData); }
	public static void appendRow(StringBuilder b, String delim, Collection<String> colNames, Function<String, String> colNameToRowData) {

		b.append(String.join(delim, colNames.stream().map(colNameToRowData).collect(Collectors.toList())));
		b.append('\n');

	}

	private static String padded(String str, int padding) { return String.format("%" + padding + "s", str); }

}
