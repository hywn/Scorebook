import java.sql.ResultSet;

public abstract class Score extends Storable { // a score belonging to a meet and an athlete

	private final static String
		COLUMN_MEET_ID = "mid",
		COLUMN_ATHLETE_ID = "tid";

	public Score(StorableStruct struct, ResultSet rs) { super(struct, rs); }

	public Score(StorableStruct struct, String meetID, String studentID, Object... values) {

		super(struct, values);

		register(COLUMN_MEET_ID, meetID);
		register(COLUMN_ATHLETE_ID, studentID);

	}

	public String getMeetID() { return getValue(COLUMN_MEET_ID); }
	public String getAthleteID() { return getValue(COLUMN_ATHLETE_ID); }

	public Meet getMeet(Database db) { return db.getMeet(getMeetID()); }
	public Athlete getAthlete(Database db) { return db.getAthlete(getAthleteID()); }

}
