package blackmediamanager.database.dao;

import blackmediamanager.database.domain.Genrepos;
import blackmediamanager.database.persistance.DatabaseAccessFacade;

public class GenreposDao extends BaseDao<Genrepos> {

	public GenreposDao(DatabaseAccessFacade databaseAccessFacade) {
		super(databaseAccessFacade, "Genrepos");
	}

}
