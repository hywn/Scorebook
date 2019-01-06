import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public abstract class Storable {

	private StorableStruct struct; // useful information used with Database like what table this thing goes in
	private HashMap<String, Object> values; // I think this is better than actual variables

	public Storable(StorableStruct struct) { this.struct = struct; }

	public Storable(StorableStruct struct, Object... values) {

		this(struct);

		this.values = new HashMap();

		for (int i = 0; i < values.length; i++) {

			// get key
			if (!(values[i] instanceof String))
				throw new IllegalArgumentException(); // error if not a String key

			this.values.put((String) values[i++], values[i]);

		}

	}

	public Storable(StorableStruct struct, ResultSet rs) { // construct from ResultSet

		this(struct);

		values = new HashMap();

		try {

			final int columns = rs.getMetaData().getColumnCount();

			for (int i = 1; i <= columns; i++) {

				values.put(rs.getMetaData().getColumnName(i), rs.getObject(i));

			}

		}

		catch (SQLException e) { e.printStackTrace(); }

	}

	// insert this thing into the database
	public void insert(Database db) {

		// insert into ___ (___, ___, ...) values (___, ___, ...)

		StringBuilder b = new StringBuilder("insert into ");
		b.append(struct.TABLE);
		b.append(" (");

		String separator = "";

		for (String key : values.keySet()) { // need this because hashmap has no order

			b.append(separator);

			b.append(key);

			separator = ", ";

		}

		b.append(") values (");

		separator = "";

		for (Object o : values.values()) {

			b.append(separator);

			Util.appendClean(b, o);

			separator = ", ";

		}

		b.append(')');

		db.execute(b.toString());

	}

	public void delete(Database db) {

		// delete from ___ where ___=___ and ___=___ and ... limit 1
		StringBuilder b = new StringBuilder("delete from ");
		b.append(struct.TABLE);

		b.append(" where ");

		String separator = "";

		for (String key : values.keySet()) {
			b.append(separator);
			Util.appendCleanEquals(b, key, values.get(key)); // ___=___

			separator = " and ";
		}

		b.append(" limit 1"); // TODO: this probably won't work

		db.execute(b.toString());

	}

	protected StorableStruct getStruct() { return struct; }

	protected HashMap<String, Object> getRawValues() { return values; }

	protected void register(String key, Object value) {values.put(key, value); } // should be used extremely rarely

	protected <T> T getValue(String key) { return (T) values.get(key); } // should use getters and setters to use outside of subclasses

}
