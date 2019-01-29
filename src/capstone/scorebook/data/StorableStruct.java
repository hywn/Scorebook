package capstone.scorebook.data;

import java.sql.ResultSet;

public class StorableStruct {

	public interface StorableResultSetFactory {
		public Storable produce(ResultSet rs);
	}

	public final String TABLE;
	public final StorableResultSetFactory FACTORY_RS; // makes a version of it out of an rs, kind of a workaround

	public StorableStruct(String table, StorableResultSetFactory rsFactory) { TABLE = table; FACTORY_RS = rsFactory; }

}