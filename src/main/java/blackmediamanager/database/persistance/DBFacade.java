
package blackmediamanager.database.persistance;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import blackmediamanager.database.domain.Genrepos;
import blackmediamanager.database.domain.Movie;
import blackmediamanager.database.domain.Ownerpos;
import blackmediamanager.database.domain.Tmdbinfo;
import blackmediamanager.database.domain.Videoline;

public class DBFacade {

	private DBFacade() {

	}

	public static DBFacade instance = new DBFacade();

	// gibt nur die aktiven zurück, inaktive und doppelte werden nicht
	// zurückgegeben
	public List<Movie> getAllMovies() {
		Session session = DBSession.getInstance();
		List<Movie> results;
		try {
			Query query = session.createQuery("FROM Movie WHERE active = 1");
			results = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return results;
	}

	public List<Genrepos> getAllGenrePoses() {
		Session session = DBSession.getInstance();
		List<Genrepos> results;
		try {
			Query query = session.createQuery("FROM Genrepos");
			results = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return results;
	}

	public List<Ownerpos> getAllOwnerPoses() {
		Session session = DBSession.getInstance();
		List<Ownerpos> results;
		try {
			Query query = session.createQuery("FROM Ownerpos");
			results = query.list();
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
		return results;
	}

	public void saveMovie(Movie movie) {
		Session session = DBSession.getInstance();
		try {
			session.saveOrUpdate(movie);
			session.getTransaction().commit();
			session.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void saveVideoline(Videoline line) {
		Session session = DBSession.getInstance();
		try {
			session.saveOrUpdate(line);
			session.getTransaction().commit();
			session.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void saveTMDBinfo(Tmdbinfo info) {
		Session session = DBSession.getInstance();
		try {
			session.saveOrUpdate(info);
			session.getTransaction().commit();
			session.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// setzt inaktiv auf 0.. macht eg gleiche wia save ?!
	public void deleteMovie(Movie movie) {
		Session session = DBSession.getInstance();
		try {
			session.saveOrUpdate(movie);
			session.getTransaction().commit();
			session.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// gibt nur die aktiven zurück, inaktive und doppelte werden nicht
	// zurückgegeben
	public int countMovies() {
		Session session = DBSession.getInstance();
		int result;
		try {
			Query query = session.createQuery("count(*) FROM movie WHERE active = 1");
			result = query.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
		return result;
	}

	// gibt nur die aktiven zurück, inaktive und doppelte werden nicht
	// zurückgegeben
	public double countMoviesSize() {
		Session session = DBSession.getInstance();
		double result;
		try {
			Query query = session.createQuery("sum(fileSize) FROM movie WHERE active = 1");
			result = query.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			return -1;
		}
		return result;
	}

}
