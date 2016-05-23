package application;

import database.domain.Movie;
import database.persistance.DBFacade;
import java.util.List;

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

}
