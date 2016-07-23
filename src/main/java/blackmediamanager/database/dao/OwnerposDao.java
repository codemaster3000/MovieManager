package blackmediamanager.database.dao;

import blackmediamanager.database.domain.Ownerpos;
import blackmediamanager.database.persistance.DatabaseAccessFacade;

public class OwnerposDao extends BaseDao<Ownerpos> {

	public OwnerposDao(DatabaseAccessFacade databaseAccessFacade) {
		super(databaseAccessFacade, "Ownerpos");
	}

}
