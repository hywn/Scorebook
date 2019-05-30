package windowgui;

import capstone.scorebook.data.concrete.Meet;
import capstone.scorebook.data.concrete.ScorebookDatabase;
import windowgui.controller.RegisterMeetController;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;

public class Util {

	public static ArrayList<Meet> getSortedMeets() {

		ArrayList<Meet> meets = ScorebookDatabase.getDB().getAllMeets();

		meets.sort((m2, m1) -> {
			try {
				Date d1 = RegisterMeetController.DATE_FORMAT.parse(m1.getDate());
				Date d2 = RegisterMeetController.DATE_FORMAT.parse(m2.getDate());

				return d1.compareTo(d2);
			}
			catch (ParseException e) {
				e.printStackTrace();
				return 0;
			}
		});

		return meets;

	}

}
