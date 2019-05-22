package capstone.scorebook;

import capstone.scorebook.data.concrete.*;

import java.nio.file.Paths;
import java.sql.SQLException;

public class Run {

	public static void main(String[] args) {

		ScorebookDatabase db; // jaassson likes cats.

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

		db.insert(meet);

		//TODO: add searching scores by athlete ID and get PRs and stuff

	}

}
