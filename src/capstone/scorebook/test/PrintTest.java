package capstone.scorebook.test;

import capstone.scorebook.data.concrete.Meet;
import capstone.scorebook.data.concrete.ScorebookDatabase;

import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Scanner;

public class PrintTest {

	public static void main(String[] args) {

		ScorebookDatabase db;

		try { db = new ScorebookDatabase(Paths.get("test.db")); }

		catch (SQLException e) {

			System.err.println("Could not find specified database.");
			e.printStackTrace();
			return;

		}

		System.out.println(PrettyPrinter.toTable(db.getAllMeets(), new String[]{"Date", "Season"}, Meet::getDate, Meet::getSeason));
		System.out.println(PrettyPrinter.toTable(db.getAllAthletes(), new String[]{""}));

	}

	static String prompt(Scanner s, String prompt) {

		System.out.println(prompt);
		return s.nextLine();

	}

}
