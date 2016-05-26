package application;

import database.domain.Movie;
import database.persistance.DBFacade;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;



/**
 *
 * @author Fabian Ladurner
 *
 * desription: gets the data from the DB
 *
 */
public class DataHandler {

    public DataHandler() {

    }

    // Methods
    public List<Movie> getAllMovies() {
        DBFacade db = new DBFacade();
        return db.getAllMovies();
    }

    public List<Movie> getFilteredMovies(final String movie) {
        DBFacade db = new DBFacade();
        Predicate<Movie> filter = new Predicate<Movie>() {
            @Override
            public boolean test(Movie mov) {
                String m = (movie == null || movie.isEmpty()) ? "" : movie;
                return (mov.getFileName().toLowerCase().contains(m.toLowerCase()) || m.isEmpty());
            }
        };
        return db.getAllMovies().parallelStream().filter(filter).collect(Collectors.<Movie>toList()); 
    }
}
