package capstone.scorebook.data.concrete;

import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.Util;
import capstone.scorebook.data.datatype.IDStorable;

import java.sql.ResultSet;

public class Address extends IDStorable {

	private static final String
		COLUMN_STREET_ADDRESS = "saddress", // column names
		COLUMN_CITY = "city",
		COLUMN_STATE_CODE = "state",
		COLUMN_ZIP_CODE = "zip";

	public static final StorableStruct STRUCT =
		new StorableStruct("address", Address::new); // I wish Java had something akin to static abstract

	public Address(ResultSet rs) { super(STRUCT, rs); }

	public Address(String streetAddress, String city, String stateAbbreviation, int zip) {

		super(STRUCT,
		      COLUMN_STREET_ADDRESS, streetAddress,
		      COLUMN_CITY, city,
		      COLUMN_STATE_CODE, stateAbbreviation,
		      COLUMN_ZIP_CODE, zip);

	}

	@Override
	protected String generateID() {

		return Util.firstNLoN(getStreetAddress(), 12) + getStateAbbreviation() + getZIPCode();

	}

	public String getStreetAddress() { return getValue(COLUMN_STREET_ADDRESS); }
	public String getCity() { return getValue(COLUMN_CITY); }
	public String getStateAbbreviation() { return getValue(COLUMN_STATE_CODE); }
	public int getZIPCode() { return getValue(COLUMN_ZIP_CODE); }

}