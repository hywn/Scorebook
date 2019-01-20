package capstone.scorebook.data;

import java.sql.*;
import java.util.ArrayList;
import java.nio.file.Path;

public abstract class Database {

	private Connection connection; // could probably be final

	public Database(Path path) throws SQLException {

		connection = DriverManager.getConnection("jdbc:sqlite:" + path.toString());

	}

	protected void execute(String... sqls) {

		for (String sql : sqls) System.out.println(sql); // for debugging; remove when not debugging

		try {

			Statement s = connection.createStatement();

			for (String sql : sqls) s.execute(sql);

			s.close();

		}

		catch (SQLException e) { e.printStackTrace(); }

	}

	protected <T extends Storable> ArrayList<T> query(StorableStruct struct, String sql) {

		//System.out.println (sql); // for debugging; remove when not debugging

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

	protected <T extends Storable> T querySingle(StorableStruct struct, String sql) {

		ArrayList<T> list = query(struct, sql);

		if (list.size() == 1) return list.get(0);
		if (list.size() == 0) return null;

		System.err.println("Duplicate results for single query: " + sql);
		return null;

	}

	public void delete(Storable storable) { execute(storable.toDeleteStatement()); }
	public void insert(Storable storable) { execute(storable.toInsertStatement()); }

	// select all records methods
	protected <T extends Storable> ArrayList<T> querySelectAll(StorableStruct struct) { return query(struct, "select * from " + struct.TABLE); }

}