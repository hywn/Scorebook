import java.sql.ResultSet;
import java.util.HashMap;

public abstract class IDStorable extends Storable {

	public static final String COLUMN_ID = "id"; // column names

	public IDStorable(StorableStruct struct, ResultSet resultSet) { super(struct, resultSet); }

	public IDStorable(StorableStruct struct, Object... values) {

		super(struct, values);

		register(COLUMN_ID, generateID());

	}

	protected abstract String generateID(); // makes an ID out of data, e.g. bsmith or something for a student ID

	public String getID() { return getValue(COLUMN_ID); }

	// update this thing in the database
	public void update(Database db) {

		HashMap<String, Object> values = getValues();

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

}
