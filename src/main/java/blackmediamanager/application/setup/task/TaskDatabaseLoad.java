package blackmediamanager.application.setup.task;

import blackmediamanager.database.persistance.DBFacade;

public class TaskDatabaseLoad extends LoadTask {

	public TaskDatabaseLoad() {
		super(TaskDatabaseLoad.class.getName());
	}

	@Override
	public void run() {
		DBFacade.instance.load();
	}

}
