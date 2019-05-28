package capstone.scorebook.test;

import java.util.LinkedHashMap;
import java.util.function.Function;

public class TableFormat<E> {

	final LinkedHashMap<String, Function<E, String>> table;

	public TableFormat() {

		table = new LinkedHashMap();

	}

	public TableFormat<E> add(String colName, Function<E, String> function) {

		table.put(colName, function);
		return this;

	}

}
