package blackmediamanager.application.setup.task;

import blackmediamanager.database.dao.DaoRegistry;
import blackmediamanager.database.persistance.DatabaseAccessFacade;
import blackmediamanager.util.ApplicationServices;

public class TaskDatabaseLoad extends LoadTask {

	public TaskDatabaseLoad() {
		super(TaskDatabaseLoad.class.getName());
	}

	@Override
	public void run() {
		DatabaseAccessFacade databaseAccessFacade = new DatabaseAccessFacade();
		// TODO: initialize database
		// databaseAccessFacade.init(annotationFileDirectory,
		// databaseConnectionProperties);

		DaoRegistry remoteDaoRegistry = new DaoRegistry(databaseAccessFacade);
		ApplicationServices.instance.setRemoteDatabaseRegistry(remoteDaoRegistry);
	}

}
