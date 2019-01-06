package capstone.scorebook.data;

import capstone.scorebook.data.datatype.IDStorable;
import capstone.scorebook.data.datatype.Storable;
import capstone.scorebook.data.concrete.Address;
import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.Meet;
import capstone.scorebook.data.concrete.School;

import java.sql.*;
import java.util.ArrayList;

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

	public <T extends Storable> ArrayList<T> query(StorableStruct struct, String sql) {

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

	// select all records methods
	private <T extends Storable> ArrayList<T> querySelectAll(StorableStruct struct) { return query(struct, "select * from " + struct.TABLE); }
	public ArrayList<Address> getAllAddresses() { return querySelectAll(Address.STRUCT); }
	public ArrayList<Athlete> getAllAthletes() { return querySelectAll(Athlete.STRUCT); }

	// query by ID methods
	public Address getAddress(String aid) { return IDStorable.querySelectByID(this, Address.STRUCT, aid); }
	public Athlete getAthlete(String tid) { return IDStorable.querySelectByID(this, Athlete.STRUCT, tid); }
	public Meet getMeet(String mid) { return IDStorable.querySelectByID(this, Meet.STRUCT, mid); }
	public School getSchool(String sid) { return IDStorable.querySelectByID(this, School.STRUCT, sid); }

}
