package blackmediamanager.database.persistance;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import blackmediamanager.database.persistance.hibernate.DatabaseConnectionProperties;
import blackmediamanager.database.persistance.hibernate.HibernateFacade;

public class DatabaseAccessFacade {

	private HibernateFacade hibernateFacade;
	private boolean initalized;

	public void init(File annotationFileDirectory, DatabaseConnectionProperties databaseConnectionProperties) {
		if (initalized) {
			// TODO(logging)
			return;
		}

		if (!annotationFileDirectory.isDirectory()) {
			throw new IllegalArgumentException("AnnotationFileDirectory cant be null");
		}

		Configuration configuration = new Configuration();

		// set configuration
		configuration.setProperty("hibernate.dialect", databaseConnectionProperties.getDialect());
		configuration.setProperty("hibernate.connection.driver_class", databaseConnectionProperties.getDriver());
		configuration.setProperty("hibernate.connection.url", databaseConnectionProperties.getUrl());
		configuration.setProperty("hibernate.connection.username", databaseConnectionProperties.getUser());
		configuration.setProperty("hibernate.connection.password", databaseConnectionProperties.getPassword());

		// add annotated files
		for (File annotationFile : annotationFileDirectory.listFiles()) {
			configuration.addCacheableFile(annotationFile);
		}

		// TODO(debugging): only set in debug mode
		configuration.setProperty("hibernate.show_sql", "true");

		hibernateFacade = new HibernateFacade(configuration);

		initalized = true;
	}

	public boolean CanConnect() {
		return hibernateFacade.CanConnect();
	}

	private void validateState() {
		if (!initalized) {
			throw new IllegalStateException("DatabaseAccessFacade was not initialized!");
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T getById(String entityName, Serializable id) throws QueryErrorException {
		validateState();

		Session session = hibernateFacade.openSession();
		try {
			return (T) session.get(entityName, id);
		} catch (HibernateException e) {
			// TODO(Logging)
			throw new QueryErrorException(e);
		} finally {
			try {
				hibernateFacade.closeSession(session);
			} catch (InvalideSessionException e) {
				// TODO(Logging)
				// eat up
			}
		}
	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(String entityName) throws QueryErrorException, InvalideSessionException {
		Session session = hibernateFacade.openSession();
		Query query = hibernateFacade.createQuery(session, "FROM " + entityName + " WHERe active = 1");

		try {
			return query.list();
		} catch (HibernateException e) {
			// TODO(Logging)
			throw new QueryErrorException(e);
		} finally {
			try {
				hibernateFacade.closeSession(session);
			} catch (InvalideSessionException e) {
				// TODO(Logging)
				// eat up
			}
		}

	}

	@SuppressWarnings("unchecked")
	public <T> List<T> getAll(String entityName, String script) throws InvalideSessionException, QueryErrorException {
		Session session = hibernateFacade.openSession();

		try {
			Query query = hibernateFacade.createQuery(session, "FROM " + entityName + " WHERe active = 1 " + script);
			return query.list();
		} catch (HibernateException e) {
			// TODO (Logging)
			throw new QueryErrorException(e);
		} finally {
			try {
				hibernateFacade.closeSession(session);
			} catch (InvalideSessionException e) {
				// TODO(Logging)
				// eat up
			}
		}
	}

	public <T> void saveOUpdate(T entity) throws InvalideSessionException, SaveErrorException {
		Session session = hibernateFacade.openSession();

		Transaction transaction = hibernateFacade.createTransaction(session);
		try {
			session.saveOrUpdate(entity);
			session.flush();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw new SaveErrorException(e);
		} finally {
			try {
				hibernateFacade.closeSession(session);
			} catch (InvalideSessionException e) {
				// TODO(Logging)
				// eat up
			}
		}
	}

	public <T> void delete(T entity) throws InvalideSessionException, SaveErrorException {
		saveOUpdate(entity);
	}

	public int getCount(String entityName) throws InvalideSessionException, QueryErrorException {
		Session session = hibernateFacade.openSession();
		Query query = hibernateFacade.createQuery(session, "count(*) FROM " + entityName + " WHERE active = 1");

		int result = -1;
		try {
			result = query.executeUpdate();
		} catch (HibernateException e) {
			throw new QueryErrorException(e);
		} finally {
			try {
				hibernateFacade.closeSession(session);
			} catch (InvalideSessionException e) {
				// TODO(Logging)
				// eat up
			}
		}
		return result;
	}
}
