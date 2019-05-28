package windowgui.tabledata;

import capstone.scorebook.data.concrete.Meet;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class TableMeet {

	private final SimpleStringProperty meetID;
	private final SimpleIntegerProperty meetTemp;

	private TableMeet(Meet meet) {
		this.meetID = new SimpleStringProperty(meet.getID());
		this.meetTemp = new SimpleIntegerProperty(meet.getTempAvgFahrenheit());
	}

}
