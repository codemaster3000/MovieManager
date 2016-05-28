package application.controller;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import database.domain.Movie;
import database.persistance.DBFacade;

/**
 *
 * @author Fabian Ladurner
 *
 *         desription: gets the data from the DB
 *
 */
public class MovieDataController {

	// Methods
	public List<Movie> getAllMovies() {
		return DBFacade.instance.getAllMovies();
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
