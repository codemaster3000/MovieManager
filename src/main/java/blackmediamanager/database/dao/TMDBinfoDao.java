package blackmediamanager.database.dao;

import blackmediamanager.database.domain.Tmdbinfo;
import blackmediamanager.database.persistance.DatabaseAccessFacade;

public class TMDBinfoDao extends BaseDao<Tmdbinfo> {

	public TMDBinfoDao(DatabaseAccessFacade databaseAccessFacade) {
		super(databaseAccessFacade, "tmdbinfo");
	}

}
