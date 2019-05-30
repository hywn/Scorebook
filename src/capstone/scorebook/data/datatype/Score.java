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
		COLUMN_WEATHER_ID = "wid",
		COLUMN_TEMP = "temp";

	public Score(StorableStruct struct, ResultSet rs) { super(struct, rs); }

	public Score(StorableStruct struct, String meetID, String athleteID, String weatherID, int temp, Object... values) {

		super(struct, values,
		      COLUMN_MEET_ID, meetID,
		      COLUMN_ATHLETE_ID, athleteID,
		      COLUMN_WEATHER_ID, weatherID,
		      COLUMN_TEMP, temp);

	}

	public String getMeetID() { return getValue(COLUMN_MEET_ID); }
	public String getAthleteID() { return getValue(COLUMN_ATHLETE_ID); }
	public String getWeatherID() { return getValue(COLUMN_WEATHER_ID); }
	public Integer getTemp() { return getValue(COLUMN_TEMP); }

	public static String queryGetAllByMeetID(String mid, StorableStruct struct) {
		return String.format("select * from %s where %s",
				     struct.TABLE,
				     toCVPair(COLUMN_MEET_ID, mid));
	}

}
