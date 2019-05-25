package capstone.scorebook.data.concrete;

import capstone.scorebook.data.Database;
import capstone.scorebook.data.StorableStruct;
import capstone.scorebook.data.datatype.IDStorable;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;

public class ScorebookDatabase extends Database {

	public ScorebookDatabase(Path path) throws SQLException { super(path); }

	public static ScorebookDatabase getDB() { // TODO: this might get overloaded or something one day
		try { return new ScorebookDatabase(Paths.get("test.db")); }
		catch (SQLException e) { System.err.println("Could not access database ):"); e.printStackTrace(); }
		return null;
	}

	// select all records methods
	public ArrayList<Address> getAllAddresses() { return querySelectAll(Address.STRUCT); }
	public ArrayList<Athlete> getAllAthletes() { return querySelectAll(Athlete.STRUCT); }
	public ArrayList<Meet> getAllMeets() { return querySelectAll(Meet.STRUCT); }
	public ArrayList<School> getAllSchools() { return querySelectAll(School.STRUCT); }

	// query by ID methods
	private <T extends IDStorable> T querySelectByID(StorableStruct struct, String id) { return querySingle(struct, IDStorable.toQuerySelectByID(struct, id)); }
	public Address getAddress(String aid) { return querySelectByID(Address.STRUCT, aid); }
	public Athlete getAthlete(String tid) { return querySelectByID(Athlete.STRUCT, tid); }
	public Meet getMeet(String mid) { return querySelectByID(Meet.STRUCT, mid); }
	public School getSchool(String sid) { return querySelectByID(School.STRUCT, sid); }

	// scores and weather should always be selected by meet/student

}
