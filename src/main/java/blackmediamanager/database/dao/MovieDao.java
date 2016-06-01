package blackmediamanager.database.dao;

import java.util.Collection;

import blackmediamanager.database.domain.Movie;
import blackmediamanager.database.persistance.DBFacade;

public class MovieDao {

	public void save(Movie movie) {
		DBFacade.instance.saveMovie(movie);
	}

	public Collection<Movie> getAll() {
		return DBFacade.instance.getAllMovies();
	}

}
