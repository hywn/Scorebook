package capstone.scorebook.data.concrete;

import capstone.scorebook.data.datatype.Score;
import capstone.scorebook.data.StorableStruct;

import java.sql.ResultSet;

public class ScoreShotput extends Score { // TODO: one day it would be nice to have consistent naming

	private final static String
		COLUMN_ORDER = "ord", // "order" is a keyword and sql and needs to be escaped; just avoid it altogether
		COLUMN_DISTANCE = "distance";

	public static final StorableStruct STRUCT =
		new StorableStruct("score_shotput", ScoreShotput::new);

	private ScoreShotput(ResultSet rs) { super(STRUCT, rs); }

	public ScoreShotput(String meetID, String athleteID, String weatherID, int order, int distance) { // might need to change to double distance later, but that's OK

		super(STRUCT, meetID, athleteID, weatherID,
		      COLUMN_ORDER, order,
		      COLUMN_DISTANCE, distance);

	}

	public int getOrder() { return getValue(COLUMN_ORDER); }
	public int getDistance() { return getValue(COLUMN_DISTANCE); }

}
