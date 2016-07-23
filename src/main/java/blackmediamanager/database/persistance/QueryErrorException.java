package blackmediamanager.database.persistance;

import org.hibernate.HibernateException;

public class QueryErrorException extends Exception {
	private static final long serialVersionUID = 1L;

	public QueryErrorException(HibernateException e) {
		super(e);
	}

}
