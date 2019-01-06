import java.sql.ResultSet;

public class ScoreDiscus extends Score { // TODO: one day it would be nice to have consistent naming

	public static final StorableStruct STRUCT =
		new StorableStruct("score_discus", rs -> new ScoreDiscus(rs));

	public final static String
		COLUMN_ORDER = "order",
		COLUMN_DISTANCE = "distance";

	public ScoreDiscus(ResultSet rs) { super(STRUCT, rs); }

	public ScoreDiscus(String meetID, String athleteID, int order, int distance) { // might need to change to double distance later, but that's OK

		super(STRUCT, meetID, athleteID,
		      COLUMN_ORDER, order,
		      COLUMN_DISTANCE, distance);

	}

	public int getOrder() { return getValue(COLUMN_ORDER); }
	public int getDistance() { return getValue(COLUMN_DISTANCE); }

}
