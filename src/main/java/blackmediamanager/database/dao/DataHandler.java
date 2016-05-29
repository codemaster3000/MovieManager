package blackmediamanager.database.dao;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import blackmediamanager.database.domain.Genrepos;
import blackmediamanager.database.domain.Movie;
import blackmediamanager.database.persistance.DBFacade;

/**
 *
 * @author Fabian Ladurner
 *
 *         desription: gets the data from the DB
 *
 */
// TODO(refactor) refactor general datahandler into daos that are domain object
// specific
public class DataHandler {

	// Methods
	public List<Movie> getAllMovies() {
		return DBFacade.instance.getAllMovies();
	}

	public List<Movie> getAllMoviesOrderByFileSize() {
		return DBFacade.instance.getAllMoviesOrderByFileSize();
	}

	public List<Movie> getAllMoviesOrderByOldest() {
		return DBFacade.instance.getAllMoviesOrderByOldest();
	}

	public List<Genrepos> getAllGenrePoses() {
		return DBFacade.instance.getAllGenrePoses();
	}

	public List<Movie> getFilteredMovies(final String movie) {
		Predicate<Movie> filter = new Predicate<Movie>() {
			@Override
			public boolean test(Movie mov) {
				String m = (movie == null || movie.isEmpty()) ? "" : movie;
				return (mov.getFileName().toLowerCase().contains(m.toLowerCase()) || m.isEmpty());
			}
		};
		return DBFacade.instance.getAllMovies().parallelStream().filter(filter).collect(Collectors.<Movie> toList());
	}
}
