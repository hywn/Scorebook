package capstone.scorebook.data.datatype;

import capstone.scorebook.data.Storable;
import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.data.Database;
import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.Meet;

import java.sql.ResultSet;

public abstract class Score extends Storable { // a score belonging to a meet and an athlete

	private final static String
		COLUMN_MEET_ID = "mid",
		COLUMN_ATHLETE_ID = "tid",
		COLUMN_WEATHER_ID = "wid"; // weather is stored via ID

	public Score(StorableStruct struct, ResultSet rs) { super(struct, rs); }

	public Score(StorableStruct struct, String meetID, String studentID, String weatherID, Object... values) {

		super(struct, values,
		      COLUMN_MEET_ID, meetID,
		      COLUMN_ATHLETE_ID, studentID,
		      COLUMN_WEATHER_ID, weatherID); // don't really need to be passed into constructor since there's no generateID or something that'll need them ASAP, but I think it's best to pass values in ASAP anyways.

	}

	public String getMeetID() { return getValue(COLUMN_MEET_ID); }
	public String getAthleteID() { return getValue(COLUMN_ATHLETE_ID); }
	public String getWeatherID() { return getValue(COLUMN_WEATHER_ID); }

}
