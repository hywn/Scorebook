package capstone.scorebook.data.concrete;

import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.Util;
import capstone.scorebook.data.datatype.Location;

import java.sql.ResultSet;

public class School extends Location {

	private static final String
		COLUMN_NAME = "name",
		COLUMN_DISTRICT = "district",
		COLUMN_REGION = "region",
		COLUMN_DIVISION = "division";

	public static final StorableStruct STRUCT =
		new StorableStruct("school", School::new);

	private School(ResultSet rs) { super(STRUCT, rs); }

	public School(String addressID, String name, String district, String region, String division) { // TODO: verification

		super(STRUCT, addressID,
		      COLUMN_NAME, name,
		      COLUMN_DISTRICT, district,
		      COLUMN_REGION, region,
		      COLUMN_DIVISION, division);

	}

	@Override
	protected String generateID() {

		return Util.firstNLoN(getName(), 5) + getDistrict() + getRegion() + getDivision();

	}

	public String getName() { return getValue(COLUMN_NAME); }
	public String getDistrict() { return getValue(COLUMN_DISTRICT); }
	public String getRegion() { return getValue(COLUMN_REGION); }
	public String getDivision() { return getValue(COLUMN_DIVISION); }

}
