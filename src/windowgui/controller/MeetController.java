package windowgui.controller;

import capstone.scorebook.data.concrete.Meet;

public abstract class MeetController extends BaseController {

	protected Meet meet;

	public void setMeet(Meet meet) {
		this.meet = meet;
		onSetMeet();
	}

	abstract void onSetMeet();

}
