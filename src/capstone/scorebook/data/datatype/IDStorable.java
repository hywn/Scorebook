package capstone.scorebook.data.datatype;

import capstone.scorebook.data.Database;
import capstone.scorebook.data.Storable;
import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.Util;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class IDStorable extends Storable {

	private static final String COLUMN_ID = "id"; // column names

	public IDStorable(StorableStruct struct, ResultSet rs) { super(struct, rs); }

	public IDStorable(StorableStruct struct, Object[] values, Object... append) {

		super(struct, values, append);

		addValue(COLUMN_ID, generateID()); // must register after constructor because requires all possible info/values to be registered

	}

	public IDStorable(StorableStruct struct, Object... values) {

		super(struct, values);

		addValue(COLUMN_ID, generateID()); // must register after constructor because requires all possible info/values to be registered

	}

	protected abstract String generateID(); // makes an ID out of data, e.g. bsmith or something for a student ID

	public String getID() { return getValue(COLUMN_ID); }

	public String toUpdateStatement() { // would ideally be package-private with ScorebookDatabase

		// update ___ set ___=___, ___=___ ... where ___=___;

		return String.format("update %s set %s where %s",
				     getStruct().TABLE,
				     toCVMapIncludeAll(", "),
				     toCVPair(COLUMN_ID));

	}

	public static String toQuerySelectByID(StorableStruct struct, String id) {

		// select * from ___ where ___=___

		return String.format("select * from %s where %s=%s",
				     struct.TABLE,
				     toCVPair(COLUMN_ID, id));

	}

}
