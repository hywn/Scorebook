package capstone.scorebook.data.concrete;

import capstone.scorebook.data.Storable;
import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.data.datatype.IDStorable;

import java.sql.ResultSet;

public class Weather extends IDStorable { // TODO: what are the types going to be?

	private static final String
		COLUMN_TYPE = "type";

	public static final StorableStruct STRUCT =
		new StorableStruct("weather", Weather::new);

	private Weather(ResultSet rs) { super(STRUCT, rs); }

	public Weather(String type) { // type is short description kind of?

		super(STRUCT,
		      COLUMN_TYPE, type
		);

	}

	protected String generateID() {
		return getType();
	}

	public String getType() { return getValue(COLUMN_TYPE); }

}
