package capstone.scorebook;

import capstone.scorebook.data.Database;
import capstone.scorebook.data.concrete.*;

import java.nio.file.Paths;
import java.sql.SQLException;

public class Run {

	public static void main(String[] args) {

		ScorebookDatabase db; // Jimmy likes dogs.

		try { db = new ScorebookDatabase(Paths.get("test.db")); }

		catch (SQLException e) {

			System.err.println("Could not find specified database.");
			e.printStackTrace();
			return;

		}

		db.insert(new Address("36811 Allder School Rd", "Purcellville", "VA", 20132));

		String addressID = db.getAllAddresses().get(0).getID(); // gets first address in table

		db.insert(new School(addressID, "Woodgrove", "abc", "abc", "abc")); // I don't know how districts, regions, or divisions work

		String schoolID = db.getAllSchools().get(0).getID();

		Athlete bob = new Athlete(schoolID, 2019, "Bob Smith");
		db.insert(bob);
		db.insert(new Athlete(schoolID, 2019, "Sally Jones"));

		// print all athletes
		System.out.println();
		db.getAllAthletes().forEach(a -> System.out.printf("name: %s\nyear of graduation: %s\nschool: %s\n\n",
								   a.getFullName(),
								   a.getYearOfGraduation(),
								   a.getSchoolID()));

		Meet meet = new Meet(addressID, "2018-12-25", "winter", 0, 34);// a meet outdoors at Woodgrove on Christmas with an avg. temp of 34 F
		db.insert(meet);

		ScoreDiscus score = new ScoreDiscus(meet.getID(), bob.getID(), 2, 2000);

		db.insert(score);

		db.insert(new ScoreDiscus(meet.getID(), bob.getID(), 3, 4320));

		// now there should be 2 scores

		//TODO: add searching scores by athlete ID and get PRs and stuff

	}

}
