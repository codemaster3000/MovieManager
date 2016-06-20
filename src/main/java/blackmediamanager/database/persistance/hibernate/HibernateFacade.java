package blackmediamanager.database.persistance.hibernate;

import java.io.Closeable;
import java.io.IOException;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import blackmediamanager.database.persistance.InvalideSessionException;
import blackmediamanager.database.persistance.InvalideTransactionException;

public class HibernateFacade implements Closeable {

	private SessionFactory _sessionFactory;

	public HibernateFacade(Configuration configuration) {
		ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties())
				.buildServiceRegistry();
		_sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	}

	public boolean CanConnect() {
		Session session = openSession();
		return session.isConnected() && session.isOpen();
	}

	public void teardown() {
		_sessionFactory.close();
		_sessionFactory = null;
	}

	public Session openSession() {
		return _sessionFactory.getCurrentSession();
	}

	public void closeSession(Session session) throws InvalideSessionException {
		validateSession(session);
		session.close();
	}

	public Transaction createTransaction(Session session) throws InvalideSessionException {
		validateSession(session);
		return session.beginTransaction();
	}

	public void commitTransaction(Transaction transaction) throws InvalideTransactionException {
		validateTransaction(transaction);
		transaction.commit();
	}

	public void rollback(Transaction transaction) throws InvalideTransactionException {
		validateTransaction(transaction);
		transaction.rollback();
	}

	public Query createQuery(Session session, String queryString) throws InvalideSessionException {
		validateSession(session);
		return session.createQuery(queryString);
	}

	private void validateSession(Session session) throws InvalideSessionException {
		if (session == null) {
			throw InvalideSessionException.SessionNull();
		} else if (!session.isConnected()) {
			throw InvalideSessionException.SessionNotConnected();
		} else if (session.isOpen()) {
			throw InvalideSessionException.SessionNotOpen();

		}
	}

	private void validateTransaction(Transaction transaction) throws InvalideTransactionException {
		if (transaction == null) {
			throw InvalideTransactionException.TransactionNull();
		} else if (transaction.isActive()) {
			throw InvalideTransactionException.TransactionNotActive();
		}
	}

	@Override
	public void close() throws IOException {
		teardown();
	}
}
