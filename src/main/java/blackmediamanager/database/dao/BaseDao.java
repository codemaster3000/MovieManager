package blackmediamanager.database.dao;

import java.util.List;

import blackmediamanager.database.persistance.DatabaseAccessFacade;
import blackmediamanager.database.persistance.InvalideSessionException;
import blackmediamanager.database.persistance.QueryErrorException;
import blackmediamanager.database.persistance.SaveErrorException;

public abstract class BaseDao<T> {
	protected DatabaseAccessFacade databaseAccessFacade;
	protected String entityName;

	public BaseDao(DatabaseAccessFacade databaseAccessFacade, String entityName) {
		this.databaseAccessFacade = databaseAccessFacade;

		this.entityName = entityName;
	}

	public void save(T entity) throws DataAccessException {
		try {
			databaseAccessFacade.saveOUpdate(entity);
		} catch (InvalideSessionException | SaveErrorException e) {
			// TODO(Logging)
			throw new DataAccessException(e);
		}
	}

	public List<T> getAll() throws DataAccessException {
		try {
			return databaseAccessFacade.getAll(entityName);
		} catch (QueryErrorException | InvalideSessionException e) {
			// TODO(Logging)
			throw new DataAccessException(e);
		}
	}

	public int count() throws InvalideSessionException, DataAccessException {
		try {
			return databaseAccessFacade.getCount(entityName);
		} catch (QueryErrorException e) {
			// TODO(Logging)
			throw new DataAccessException(e);
		}
	}

	public void delete(T entity) throws DataAccessException {
		try {
			databaseAccessFacade.delete(entity);
		} catch (InvalideSessionException | SaveErrorException e) {
			// TODO(Logging)
			throw new DataAccessException(e);
		}
	}
}
