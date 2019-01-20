package capstone.scorebook.data.concrete;

import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.Util;
import capstone.scorebook.data.datatype.IDStorable;

import java.sql.ResultSet;

public class Address extends IDStorable { // IDStorable is a Storable that has a unique ID.

	private static final String // column names. kept private to keep implementation contained.
		COLUMN_STREET_ADDRESS = "saddress",
		COLUMN_CITY = "city",
		COLUMN_STATE_CODE = "state",
		COLUMN_ZIP_CODE = "zip";

	public static final StorableStruct STRUCT = // this is a small packet of info that stores this Storable's table and
		new StorableStruct("address", Address::new); // constructor that takes a ResultSet. used in Database and related classes.

	// note: `Address::new` is equivalent to `rs -> new Address(rs)`.

	private Address(ResultSet rs) { super(STRUCT, rs); } // constructor that takes a ResultSet.

	public Address(String streetAddress, String city, String stateAbbreviation, int zip) throws IllegalArgumentException { // constructor to be used with user input

		super(STRUCT,
		      COLUMN_STREET_ADDRESS, streetAddress,
		      COLUMN_CITY, city,
		      COLUMN_STATE_CODE, stateAbbreviation,
		      COLUMN_ZIP_CODE, zip);

		Util.check("Invalid state abbreviation", // throws an IllegalArgumentException with the specified message if all of the following conditions aren't true
			   stateAbbreviation.length() == 2);

	}

	@Override
	protected String generateID() { // IDStorable method. generates ID.

		return Util.firstNLoN(getStreetAddress(), 12) + getStateAbbreviation() + getZIPCode();

	}

	public String getStreetAddress() { return getValue(COLUMN_STREET_ADDRESS); } // various getters. everything is publicly accessible without knowledge of actual database implementation
	public String getCity() { return getValue(COLUMN_CITY); }
	public String getStateAbbreviation() { return getValue(COLUMN_STATE_CODE); }
	public int getZIPCode() { return getValue(COLUMN_ZIP_CODE); }

}