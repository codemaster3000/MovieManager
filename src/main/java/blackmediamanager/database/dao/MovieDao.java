package blackmediamanager.database.dao;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import blackmediamanager.database.domain.Movie;
import blackmediamanager.database.persistance.DatabaseAccessFacade;
import blackmediamanager.database.persistance.InvalideSessionException;
import blackmediamanager.database.persistance.QueryErrorException;

public class MovieDao extends BaseDao<Movie> {

	public MovieDao(DatabaseAccessFacade databaseAccessFacade) {
		super(databaseAccessFacade, "Movie");
	}

	public List<Movie> getAllMoviesOrderByFileSize() throws DataAccessException {
		try {
			return databaseAccessFacade.getAll(entityName, "ORDER BY fileSize DESC");
		} catch (QueryErrorException | InvalideSessionException e) {
			// TODO(Logging)
			throw new DataAccessException(e);
		}
	}

	public List<Movie> getAllMoviesOrderByOldest() throws DataAccessException {
		try {
			return databaseAccessFacade.getAll(entityName, "ORDER BY year ASC");
		} catch (InvalideSessionException | QueryErrorException e) {
			// TODO(Logging)
			throw new DataAccessException(e);
		}
	}

	public List<Movie> getFilteredMovies(final String movie) throws DataAccessException {
		Predicate<Movie> filter = new Predicate<Movie>() {
			@Override
			public boolean test(Movie mov) {
				String m = (movie == null || movie.isEmpty()) ? "" : movie;
				return (mov.getFileName().toLowerCase().contains(m.toLowerCase()) || m.isEmpty());
			}
		};

		try {
			List<Movie> movies = databaseAccessFacade.getAll(entityName);
			return movies.parallelStream().filter(filter).collect(Collectors.<Movie> toList());
		} catch (QueryErrorException | InvalideSessionException e) {
			// TODO(Logging)
			throw new DataAccessException(e);
		}
	}
}
