package capstone.scorebook.data.datatype;

import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.data.Database;
import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.Meet;

import java.sql.ResultSet;

public abstract class Score extends Storable { // a score belonging to a meet and an athlete

	private final static String
		COLUMN_MEET_ID = "mid",
		COLUMN_ATHLETE_ID = "tid";

	public Score(StorableStruct struct, ResultSet rs) { super(struct, rs); }

	public Score(StorableStruct struct, String meetID, String studentID, Object... values) {

		super(struct, values,
		      COLUMN_MEET_ID, meetID,
		      COLUMN_ATHLETE_ID, studentID); // don't really need to be passed into constructor since there's no generateID or something that'll need them ASAP, but I think it's best to pass values in ASAP anyways.

	}

	public String getMeetID() { return getValue(COLUMN_MEET_ID); }
	public String getAthleteID() { return getValue(COLUMN_ATHLETE_ID); }

	public Meet getMeet(Database db) { return db.getMeet(getMeetID()); }
	public Athlete getAthlete(Database db) { return db.getAthlete(getAthleteID()); }

}
