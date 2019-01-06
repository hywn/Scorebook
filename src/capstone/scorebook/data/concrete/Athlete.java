package capstone.scorebook.data.concrete;

import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.Util;
import capstone.scorebook.data.datatype.IDStorable;

import java.sql.ResultSet;

public class Athlete extends IDStorable {

	public static final StorableStruct STRUCT =
		new StorableStruct("athlete", rs -> new Athlete(rs));

	private static final String // the goal is to keep all of the database implementation private
		COLUMN_SCHOOL_ID = "sid",
		COLUMN_YEAR_OF_GRADUATION = "yog",
		COLUMN_NAME = "name";

	public Athlete(ResultSet rs) { super(STRUCT, rs); } // unsightly

	public Athlete(String schoolID, int yearOfGraduation, String fullName) { // TODO: make private and add static constructor that verifies data

		super(STRUCT,
		      COLUMN_SCHOOL_ID, schoolID,
		      COLUMN_YEAR_OF_GRADUATION, yearOfGraduation,
		      COLUMN_NAME, fullName);

	}

	@Override
	protected String generateID() { // TODO: some IDs still might clash... maybe add a counter?

		return getSchoolID() +
			getYearOfGraduation() +
			Util.firstNLoN(getFirstName(), 5) +
			Util.firstNLoN(getLastName(), 5);

	}

	public String getSchoolID() { return getValue(COLUMN_SCHOOL_ID); }
	public int getYearOfGraduation() { return getValue(COLUMN_YEAR_OF_GRADUATION); }
	public String getFullName() { return getValue(COLUMN_NAME); }
	public String getFirstName() { return getFullName().split(" ")[0]; }

	// TODO: this might be trouble actually if middle names are implemented and are optional. might need a completely different name-storing system
	public String getLastName() {

		String[] names = getFullName().split(" ");

		if (names.length < 2) return "Last";

		return names[1];

	}

}
