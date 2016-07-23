package blackmediamanager.database.persistance;

import org.hibernate.HibernateException;

public class SaveErrorException extends Exception {
	private static final long serialVersionUID = 3981999268679208770L;

	public SaveErrorException(HibernateException e) {
		super(e);
	}

}
