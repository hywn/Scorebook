package capstone.scorebook.data.concrete;

import capstone.scorebook.data.StorableStruct;

import java.sql.ResultSet;

public class ScoreShotput extends ScoreThrow { // TODO: one day it would be nice to have consistent naming

	public static final StorableStruct STRUCT =
		new StorableStruct("score_shotput", ScoreShotput::new);

	private ScoreShotput(ResultSet rs) { super(STRUCT, rs); }

	public ScoreShotput(String meetID, String athleteID, String weatherID, int temp, int round, int order, int distance) {

		super(STRUCT, meetID, athleteID, weatherID, temp, round, order, distance);

	}

}
