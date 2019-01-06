package capstone.scorebook.data.concrete;

import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.data.datatype.Location;

import java.sql.ResultSet;

public class Meet extends Location {

	public static final StorableStruct STRUCT =
		new StorableStruct("meet", rs -> new Meet(rs));

	private static final String // remember that meet ID itself is handled by capstone.scorebook.data.datatype.IDStorable and address ID is handled by capstone.scorebook.data.datatype.Location
		COLUMN_DATE = "date",
		COLUMN_SEASON = "season",
		COLUMN_INDOOR = "indoor", // note: this is an integer; 0 is false, 1 is true
		COLUMN_FAHRENHEIT_AVG = "tempAvg";

	public Meet(ResultSet rs) { super(STRUCT, rs); }

	public Meet(String addressID, String date, String season, int isIndoor, int tempFahrenheitAvg) { // TODO: make private and make static creation methods with data verification

		super(STRUCT, addressID,
		      COLUMN_DATE, date,
		      COLUMN_SEASON, season,
		      COLUMN_INDOOR, isIndoor,
		      COLUMN_FAHRENHEIT_AVG, tempFahrenheitAvg);

	}

	@Override
	protected String generateID() {

		return getAddressID() + getDate() + getTempAvgFahrenheit();

	}

	public String getDate() { return getValue(COLUMN_DATE); }
	public String getSeason() { return getValue(COLUMN_SEASON); }
	public int getIsIndoor() { return getValue(COLUMN_INDOOR); }
	public int getTempAvgFahrenheit() { return getValue(COLUMN_FAHRENHEIT_AVG); }

}
