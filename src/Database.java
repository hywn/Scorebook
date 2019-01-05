import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Database {

	private Connection connection;

	public Database(String path) throws SQLException {

		connection = DriverManager.getConnection("jdbc:sqlite:" + path);

	}

	public void execute(String... sqls) {

		for (String sql : sqls) System.out.println(sql);

		try {

			Statement s = connection.createStatement();

			for (String sql : sqls) s.execute(sql);

			s.close();

		}

		catch (SQLException e) { e.printStackTrace(); }

	}

	private <T extends Storable> ArrayList<T> query(StorableStruct struct, String sql) {

		ArrayList<T> list = new ArrayList();

		try {

			Statement s = connection.createStatement();

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) list.add((T) struct.FACTORY_RS.produce(rs));

			s.close();

		}

		catch (SQLException e) { e.printStackTrace(); }

		return list;

	}

	// implement every single one
	private <T extends Storable> ArrayList<T> querySelectAll(StorableStruct struct) { return query(struct, "select * from " + struct.TABLE); }

	public ArrayList<Address> getAllAddresses() { return querySelectAll(Address.STRUCT); }
	public ArrayList<Athlete> getAllAthletes() { return querySelectAll(Athlete.STRUCT); }


	// TODO: enforce only query if struct is type IDStorable
	private <T extends Storable> T querySelectByID(StorableStruct struct, String id) { // select * from ___ where ___=___

		StringBuilder query = new StringBuilder("select * from "); query.append(struct.TABLE); query.append(" where "); // select * from ___ where
		Util.appendCleanEquals(query, IDStorable.COLUMN_ID, id); // ___=___

		ArrayList<T> results = query(struct, query.toString());

		if (results.size() == 0) return null;

		return results.get(0); // this should always work; there will always be either 0 or 1 matching entries assuming the database is properly set up because PRIMARY KEY will always enforce UNIQUE

	}

	public Address getAddress(String aid) { return querySelectByID(Address.STRUCT, aid); }
	public Athlete getAthlete(String tid) { return querySelectByID(Athlete.STRUCT, tid); }

}
