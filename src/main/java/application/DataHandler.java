package application;

import database.domain.Genrepos;
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

    private DBFacade dBFacade;

    public DataHandler() {
        if (dBFacade == null) {
            dBFacade = new DBFacade();
        }
    }

    // Methods
    public List<Movie> getAllMovies() {
        return dBFacade.getAllMovies();
    }

    public List<Movie> getAllMoviesOrderByFileSize() {
        return dBFacade.getAllMoviesOrderByFileSize();
    }

    public List<Movie> getAllMoviesOrderByOldest() {
        return dBFacade.getAllMoviesOrderByOldest();
    }
    

    public List<Genrepos> getAllGenrePoses() {
        return dBFacade.getAllGenrePoses();
    }

    public List<Movie> getFilteredMovies(final String movie) {
        Predicate<Movie> filter = new Predicate<Movie>() {
            @Override
            public boolean test(Movie mov) {
                String m = (movie == null || movie.isEmpty()) ? "" : movie;
                return (mov.getFileName().toLowerCase().contains(m.toLowerCase()) || m.isEmpty());
            }
        };
        return dBFacade.getAllMovies().parallelStream().filter(filter).collect(Collectors.<Movie>toList());
    }
}
