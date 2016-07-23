package blackmediamanager.application.controller;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import blackmediamanager.database.dao.DataAccessException;
import blackmediamanager.database.domain.Movie;
import blackmediamanager.util.ApplicationServices;

/**
 *
 * @author Fabian Ladurner
 *
 *         desription: gets the data from the DB
 *
 */
public class ContentMovieAppController {

	// Methods
	public List<Movie> getAllMovies() throws DataAccessException {
		return ApplicationServices.instance.getRemoteDatabaseRegistry().getMovieDao().getAll();
	}

	public List<Movie> getFilteredMovies(final String movie) throws DataAccessException {
		Predicate<Movie> filter = new Predicate<Movie>() {
			@Override
			public boolean test(Movie mov) {
				String m = (movie == null || movie.isEmpty()) ? "" : movie;
				return (mov.getFileName().toLowerCase().contains(m.toLowerCase()) || m.isEmpty());
			}
		};
		return ApplicationServices.instance.getRemoteDatabaseRegistry().getMovieDao().getAll().parallelStream()
				.filter(filter).collect(Collectors.<Movie> toList());
	}
}
