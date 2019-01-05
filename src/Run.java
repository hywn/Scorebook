import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Run {

	public static void main(String[] args) {

		Database db;

		try { db = new Database("test.db"); }

		catch (SQLException e) {

			System.err.println("Could not find specified database.");
			e.printStackTrace();
			return;

		}

		//new Athlete("whs", 2018, "Sally Ajkflds").insert(db); // this is example insert. don't uncomment it though because it'll throw an error because the database will complain about non-unique IDs.

		db.getAllAthletes().forEach(a -> System.out.printf("name: %s\nyear of graduation: %s\nschool: %s\n\n",
								   a.getFullName(),
								   a.getYearOfGraduation(),
								   a.getSchoolID()));

		System.out.println(db.getAthlete("whs2018BobSmith").getFullName()); // you can get people by their IDs

	}

}
