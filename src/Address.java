import java.sql.ResultSet;

public class Address extends IDStorable {

	public static final StorableStruct STRUCT = new StorableStruct("address", rs -> new Athlete(rs)); // I wish Java had something akin to static abstract

	private static final String
		COLUMN_STREET_ADDRESS = "saddress", // column names
		COLUMN_STATE_CODE = "state",
		COLUMN_ZIP_CODE = "zip";

	public Address(ResultSet resultSet) { super(STRUCT, resultSet); }

	public Address(String streetAddress, String stateAbbreviation, int zip) {

		super(STRUCT,
		      COLUMN_STREET_ADDRESS, streetAddress,
		      COLUMN_STATE_CODE, stateAbbreviation,
		      COLUMN_ZIP_CODE, zip);

	}

	public String getStreetAddress() { return getValue(COLUMN_STREET_ADDRESS); }

	public String getStateAbbreviation() { return getValue(COLUMN_STATE_CODE); }

	public int getZIPCode() { return getValue(COLUMN_ZIP_CODE); }

	@Override
	protected String generateID() {

		return Util.firstNLoN(getStreetAddress(), 12) + getStateAbbreviation() + getZIPCode();

	}

}