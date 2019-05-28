package capstone.scorebook;

import capstone.scorebook.data.concrete.*;

import java.nio.file.Paths;
import java.sql.SQLException;

public class Run {

	public static void main(String[] args) {

		ScorebookDatabase db; // Tommy loves dogs.

		try { db = new ScorebookDatabase(Paths.get("test.db")); }

		catch (SQLException e) {

			System.err.println("Could not find specified database.");
			e.printStackTrace();
			return;

		}

		Address schoolAddress = new Address("36811 Allder School Rd", "Purcellville", "VA", 20132);
		School school = new School(schoolAddress.getID(), "Woodgrove", "abc", "abc", "abc"); // I don't know how districts, regions, or divisions work

		db.insert(schoolAddress);
		db.insert(school);

		Athlete bob = new Athlete(school.getID(), 2019, "Bob Smith");
		Athlete sally = new Athlete(school.getID(), 2019, "Sally Jones");

		db.insert(bob);
		db.insert(sally);

		Meet meet = new Meet(schoolAddress.getID(), "2018-12-25", "winter", 0, 34);// a meet outdoors at Woodgrove on Christmas with an avg. temp of 34 F
		Meet meet2 = new Meet(schoolAddress.getID(), "2019-12-25", "winter", 0, 50);// a meet outdoors at Woodgrove on Christmas with an avg. temp of 50 F

		db.insert(meet);
		db.insert(meet2);

		db.insert(new ScoreDiscus(meet.getID(), bob.getID(), "Sunny", 1, 100));
		db.insert(new ScoreDiscus(meet.getID(), bob.getID(), "Sunny", 2, 200));
		db.insert(new ScoreDiscus(meet.getID(), bob.getID(), "Sunny", 3, 300));
		db.insert(new ScoreDiscus(meet.getID(), bob.getID(), "Sunny", 4, 400));

		db.insert(new ScoreDiscus(meet2.getID(), sally.getID(), "What", 1, 100));
		db.insert(new ScoreDiscus(meet2.getID(), sally.getID(), "Is", 2, 200));
		db.insert(new ScoreDiscus(meet2.getID(), sally.getID(), "Up", 3, 300));
		db.insert(new ScoreDiscus(meet2.getID(), sally.getID(), "My Dudes", 4, 400));

		//TODO: add searching scores by athlete ID and get PRs and stuff

	}

}
