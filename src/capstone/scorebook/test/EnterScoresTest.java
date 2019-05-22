package capstone.scorebook.test;

import capstone.scorebook.data.concrete.Athlete;
import capstone.scorebook.data.concrete.Meet;
import capstone.scorebook.data.concrete.ScoreDiscus;
import capstone.scorebook.data.concrete.ScorebookDatabase;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EnterScoresTest {

	public static void main(String[] args) {

		ScorebookDatabase db;

		try { db = new ScorebookDatabase(Paths.get("test.db")); }

		catch (SQLException e) {

			System.err.println("Could not find specified database.");
			e.printStackTrace();
			return;

		}

		Scanner s = new Scanner(System.in);

		// pick meet

		List<Meet> meets = db.getAllMeets();
		Meet meetMeet = null;

		System.out.printf("Please choose a meet (1-%d).\n", meets.size());

		for (int i = 0; i < meets.size(); i++)
			System.out.printf("Meet %d:\n\tdate: %s\n\tlocation: %s\n",
					  i + 1,
					  meets.get(i).getDate(),
					  db.getAddress(meets.get(i).getAddressID()).getStreetAddress());

		meetMeet = meets.get(Integer.parseInt(s.nextLine()) - 1);

		// pick athletes

		List<Athlete> athletes = db.getAllAthletes();
		ArrayList<Athlete> meetAthletes = new ArrayList();

		System.out.printf("Please enter number (1-%d) of each athlete attending meet separated by commas.\n", athletes.size());

		for (int i = 0; i < athletes.size(); i++)
			System.out.printf("Athlete %d:\n\tname: %s\n\tyear of graduation: %s\n\tschool: %s\n",
					  i + 1,
					  athletes.get(i).getFullName(),
					  athletes.get(i).getYearOfGraduation(),
					  athletes.get(i).getSchoolID());

		for (String num : s.nextLine().split(","))
			meetAthletes.add(athletes.get(Integer.parseInt(num.trim()) - 1));

		System.out.println("Chosen athletes: " + meetAthletes.stream().map(Athlete::getFullName).collect(Collectors.joining(", ")));

		for (int order = 1; order <= 4; order++)
			for (Athlete a : meetAthletes)
				db.insert(new ScoreDiscus(meetMeet.getID(),
							  a.getID(),
							  "good",
							  order,
							  Integer.parseInt(prompt(s, String.format("How far did %s throw on throw #%d?", a.getFullName(), order)))
					  )
				);

	}

	static String prompt(Scanner s, String prompt) {

		System.out.println(prompt);
		return s.nextLine();

	}

}
