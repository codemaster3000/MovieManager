package blackmediamanager.medialibrary;

import java.io.File;
import java.io.IOException;

import com.omertron.themoviedbapi.MovieDbException;

import blackmediamanager.database.domain.Movie;

/**
 *
 * @author fabian
 */
public class MediaAdder {

	public boolean movieToAdd(File file) throws IOException, MovieDbException {
		Movie movie = MediaInfoToMovieConverter.convert(file);

		// Film speichern
		// DBFacade dbfacade = new DBFacade();
		// dbfacade.saveMovie(movie);
		if (movie != null) {
			return true;
		} else {
			return false;
		}
	}

}
