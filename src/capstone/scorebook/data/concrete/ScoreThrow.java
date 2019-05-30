package capstone.scorebook.data.concrete;

import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.data.datatype.Score;

import java.sql.ResultSet;

public abstract class ScoreThrow extends Score { // TODO: one day it would be nice to have consistent naming

	private final static String
		COLUMN_ROUND = "round",
		COLUMN_ORDER = "ord",
		COLUMN_DISTANCE = "distance";

	public ScoreThrow(StorableStruct struct, ResultSet rs) { super(struct, rs); }

	public ScoreThrow(StorableStruct struct, String meetID, String athleteID, String weatherID, int temp, int round, int order, int distance) {

		super(struct, meetID, athleteID, weatherID, temp,
		      COLUMN_ROUND, round,
		      COLUMN_ORDER, order,
		      COLUMN_DISTANCE, distance);

	}

	public int getOrder() { return getValue(COLUMN_ORDER); }
	public int getRound() { return getValue(COLUMN_ROUND); }
	public int getDistance() { return getValue(COLUMN_DISTANCE); }

}