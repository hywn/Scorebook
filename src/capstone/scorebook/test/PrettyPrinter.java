package capstone.scorebook.test;

import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.Meet;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
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

		b.append(String.join(",", format.table.keySet())); b.append('\n');

		for (E item : items) {
			b.append(String.join(",", format.table.values().stream().map(func -> func.apply(item)).collect(Collectors.toList())));
			b.append('\n');
		}

		return b.toString();

	}

	public static <E> String toTable(List<E> items, TableFormat<E> format, int padding) {

		HashMap<String, Integer> widths = widths(items, format);

		StringBuilder b = new StringBuilder();

		for (E item : items)
			b.append(String.join(" ".repeat(padding), format.table.keySet().stream().map(colName -> String.format("%" + widths.get(colName) + "s", format.table.get(colName).apply(item))).collect(Collectors.toList())) + "\n");

		return b.toString();

	}

}
