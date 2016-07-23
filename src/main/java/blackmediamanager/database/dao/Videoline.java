package blackmediamanager.database.dao;

import blackmediamanager.database.persistance.DatabaseAccessFacade;

public class Videoline extends BaseDao<Videoline> {

	public Videoline(DatabaseAccessFacade databaseAccessFacade) {
		super(databaseAccessFacade, "Videoline");
	}

}
