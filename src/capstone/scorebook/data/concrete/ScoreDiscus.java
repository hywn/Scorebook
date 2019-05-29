package capstone.scorebook.data.concrete;

import capstone.scorebook.data.StorableStruct;

import java.sql.ResultSet;

public class ScoreDiscus extends ScoreThrow { // TODO: one day it would be nice to have consistent naming

	public static final StorableStruct STRUCT =
		new StorableStruct("score_discus", ScoreDiscus::new);

	private ScoreDiscus(ResultSet rs) { super(STRUCT, rs); }

	public ScoreDiscus(String meetID, String athleteID, String weatherID, int round, int order, int distance) { // might need to change to double distance later, but that's OK

		super(STRUCT, meetID, athleteID, weatherID, round, order, distance);

	}

}
