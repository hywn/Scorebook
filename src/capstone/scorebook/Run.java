package capstone.scorebook;

import capstone.scorebook.data.Database;
import capstone.scorebook.data.concrete.*;

import java.sql.SQLException;

public class Run {

	public static void main(String[] args) {

		Database db;

		try { db = new Database("test.db"); }

		catch (SQLException e) {

			System.err.println("Could not find specified database.");
			e.printStackTrace();
			return;

		}

		new Address("36811 Allder School Rd", "Purcellville", "VA", 20132).insert(db);

		String addressID = db.getAllAddresses().get(0).getID(); // will work since there's only one address (which is the one above)

		new School(addressID, "Woodgrove", "abc", "abc", "abc").insert(db); // I don't know how districts, regions, or divisions work

		String schoolID = db.getAllSchools().get(0).getID();

		Athlete bob = new Athlete(schoolID, 2019, "Bob Smith");
		bob.insert(db);
		new Athlete(schoolID, 2019, "Sally Jones").insert(db);

		// print all athletes
		System.out.println();
		db.getAllAthletes().forEach(a -> System.out.printf("name: %s\nyear of graduation: %s\nschool: %s\n\n",
								   a.getFullName(),
								   a.getYearOfGraduation(),
								   a.getSchoolID()));

		Meet meet = new Meet(addressID, "2018-12-25", "winter", 0, 34);// a meet outdoors at Woodgrove on Christmas with an avg. temp of 34 F
		meet.insert(db);

		ScoreDiscus score = new ScoreDiscus(meet.getID(), bob.getID(), 2, 2000);

		score.insert(db);

		new ScoreDiscus(meet.getID(), bob.getID(), 3, 4320).insert(db);

		// now there should be 2 scores

		//TODO: add searching scores by athlete ID and get PRs and stuff

	}

}
