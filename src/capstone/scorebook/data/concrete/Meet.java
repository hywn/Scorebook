package capstone.scorebook.data.concrete;

import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.data.datatype.Location;

import java.sql.ResultSet;

public class Meet extends Location {

	private static final String // remember that meet ID itself is handled by IDStorable and address ID is handled by Location
		COLUMN_DATE = "date",
		COLUMN_ROUNDS = "rounds",
		COLUMN_SEASON = "season",
		COLUMN_INDOOR = "indoor", // note: this is an integer; 0 is false, 1 is true
		COLUMN_FAHRENHEIT_AVG = "tempAvg";

	public static final StorableStruct STRUCT =
		new StorableStruct("meet", Meet::new); // I wish Java had something akin to static abstract

	private Meet(ResultSet rs) { super(STRUCT, rs); }

	public Meet(String addressID, String date, int rounds, String season, int isIndoor, int tempFahrenheitAvg) {

		super(STRUCT, addressID,
		      COLUMN_DATE, date,
		      COLUMN_ROUNDS, rounds,
		      COLUMN_SEASON, season,
		      COLUMN_INDOOR, isIndoor,
		      COLUMN_FAHRENHEIT_AVG, tempFahrenheitAvg);

	}

	@Override
	protected String generateID() {

		return getRounds() + getIsIndoor() + getAddressID() + getDate() + getTempAvgFahrenheit();

	}

	public String getDate() { return getValue(COLUMN_DATE); }
	public int getRounds() { return getValue(COLUMN_ROUNDS); }
	public String getSeason() { return getValue(COLUMN_SEASON); }
	public int getIsIndoor() { return getValue(COLUMN_INDOOR); }
	public int getTempAvgFahrenheit() { return getValue(COLUMN_FAHRENHEIT_AVG); }

	public String toString() {
		return String.format("%s at %s, %s", getDate(), getAddressID(),
				     (getRounds() == 0) ? "practice" : getRounds() + " rounds");
	}
}
