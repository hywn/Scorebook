import java.sql.ResultSet;

public class Weather extends Storable { // TODO: what are the types going to be?

	public static final StorableStruct STRUCT =
		new StorableStruct("weather", rs -> new Weather(rs));

	public Weather(ResultSet rs) { super(STRUCT, rs); }

	private static final String
		COLUMN_MEET_ID = "mid",
		COLUMN_TYPE = "type",
		COLUMN_DESCRIPTION = "description";

	public Weather(String meetID, String type, String description) { // TODO: public verification

		super(STRUCT,
		      COLUMN_MEET_ID, meetID,
		      COLUMN_TYPE, type,
		      COLUMN_DESCRIPTION, description
		);

	}

	public String getMeetID() { return getValue(COLUMN_MEET_ID); }
	public String getType() { return getValue(COLUMN_TYPE); }
	public String getColumnDescription() { return getValue(COLUMN_TYPE); }

}
