import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class IDStorable extends Storable {

	private static final String COLUMN_ID = "id"; // column names

	public IDStorable(StorableStruct struct, ResultSet rs) { super(struct, rs); }

	public IDStorable(StorableStruct struct, Object... values) {

		super(struct, values);

		register(COLUMN_ID, generateID());

	}

	protected abstract String generateID(); // makes an ID out of data, e.g. bsmith or something for a student ID

	public String getID() { return getValue(COLUMN_ID); }

	// update this thing in the database
	public void update(Database db) {

		HashMap<String, Object> values = getRawValues();

		// update ___ set ___=___, ___=___ ... where ___=___;


		StringBuilder b = new StringBuilder("update "); b.append(getStruct().TABLE); b.append(" set "); // update ___ set

		String separator = "";

		for (String key : values.keySet()) {

			b.append(separator);

			Util.appendCleanEquals(b, key, values.get(key)); // ___=___

			separator = ", ";

		}

		b.append(" where "); Util.appendCleanEquals(b, COLUMN_ID, getID()); // where ___=___

		db.execute(b.toString());

	}

	// TODO: make Util methods for repeated stuff like where clauses

	public static <T extends IDStorable> T querySelectByID(Database db, StorableStruct struct, String id) { // select * from ___ where ___=___

		StringBuilder query = new StringBuilder("select * from "); query.append(struct.TABLE); query.append(" where "); // select * from ___ where
		Util.appendCleanEquals(query, COLUMN_ID, id); // ___=___

		ArrayList<T> results = db.query(struct, query.toString());

		if (results.size() == 0) return null;

		return results.get(0); // this should always work; there will always be either 0 or 1 matching entries assuming the database is properly set up because PRIMARY KEY will always enforce UNIQUE

	}

}
