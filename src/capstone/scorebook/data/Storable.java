package capstone.scorebook.data;

import capstone.scorebook.Util;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;

public abstract class Storable {

	private StorableStruct struct; // useful information used with database like what table this thing goes in

	private HashMap<String, Object> data; // stores data that was in SQL database

	public Storable(StorableStruct struct) { this.struct = struct; data = new HashMap(); } // no data
	public Storable(StorableStruct struct, Object... data) { this(struct); addValues(data); } // some data
	public Storable(StorableStruct struct, Object[] data, Object... append) { this(struct, data); addValues(append); } // some data plus some more data

	private void addValues(Object[] dataArray) { // adds data from a list that alternates between String (column name) and Object (value in that column)

		for (int i = 0; i < dataArray.length; i++) {

			// get column name
			if (!(dataArray[i] instanceof String))
				throw new IllegalArgumentException("Column name is not a String :("); // error if not a String key

			data.put((String) dataArray[i++], dataArray[i]);

		}

	}

	public Storable(StorableStruct struct, ResultSet rs) { // constructs capstone.scorebook.data.Storable from ResultSet; is used with JDBC

		this(struct);

		try {

			final int columns = rs.getMetaData().getColumnCount();

			for (int i = 1; i <= columns; i++) data.put(rs.getMetaData().getColumnName(i), rs.getObject(i));

		}

		catch (SQLException e) { e.printStackTrace(); }

	}

	// insert/delete statement formers are kept package-private so that only Database can use them in its insert/delete methods.

	String toInsertStatement() { // generates SQL to insert this capstone.scorebook.data.Storable into the SQL database

		return String.format("insert into %s (%s) values (%s)",
				     struct.TABLE,
				     toColumnsList(),
				     toValuesList());

	}

	String toDeleteStatement() { // generates SQL to delete this capstone.scorebook.data.Storable from the SQL database

		return String.format("delete from %s where %s", // TODO: limit 1 doesn't work; must find other way
				     struct.TABLE,
				     toCVMapIncludeAll(" and "));

	}

	// TODO: make everything immutable?

	/* all of the following are protected because they shouldn't be used beyond concrete implementations of capstone.scorebook.data.Storable. */

	// returns comma-separated lists of columns/values for use in SQL construction
	protected String toColumnsList() { return Util.formList(", ", e -> e, getColumns()); }
	protected String toValuesList() { return Util.formList(", ", e -> Util.getFormatted(e), getValues()); }

	// generates ___=___ delim ___=___ ... for where clauses and updates
	protected String toCVMapInclude(String delim, Collection<String> columns) { return Util.formList(delim, column -> toCVPair(column), columns); }
	protected String toCVMapIncludeAll(String delim) { return toCVMapInclude(delim, getColumns()); }
	protected String toCVMapInclude(String delim, String... columns) { return toCVMapInclude(delim, Arrays.asList(columns)); }
	protected String toCVMapExclude(String delim, String... columns) {

		Collection<String> modifiedColumns = getColumns();
		modifiedColumns.removeAll(Arrays.asList(columns));

		return toCVMapInclude(delim, modifiedColumns);

	}

	// generates column to value pair in form ___=___
	protected String toCVPair(String column) { return toCVPair(column, getValue(column)); }
	protected static String toCVPair(String column, Object value) { return String.format("%s=%s", column, Util.getFormatted(value)); }

	protected void addValue(String key, Object value) { data.put(key, value); }
	protected <T> T getValue(String key) { return (T) data.get(key); }

	protected StorableStruct getStruct() { return struct; }

	protected Collection<String> getColumns() { return data.keySet(); }
	protected Collection<Object> getValues() { return data.values(); }

}