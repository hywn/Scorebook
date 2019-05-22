package capstone.scorebook.test;

import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.Meet;

import java.util.Collections;
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

	private static <E> int max(List<E> items, String header, Function<E, String> strFunct) {

		return 5 + Collections.max(Stream.concat(items.stream().map(strFunct), Stream.of(header)).map(String::length).collect(Collectors.toList()));

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

	public static <E> String toTable(List<E> items, TableFormat<E> format) {

		return null;

	}

}
