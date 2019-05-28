package capstone.scorebook.test;

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
		System.out.println(PrettyPrinter.toCSV(db.getAllAthletes(), PrettyPrinter.FORMAT_ATHLETE));
		System.out.println(PrettyPrinter.toTable(db.getAllAthletes(), PrettyPrinter.FORMAT_ATHLETE, 5));

	}

	static String prompt(Scanner s, String prompt) {

		System.out.println(prompt);
		return s.nextLine();

	}

}
