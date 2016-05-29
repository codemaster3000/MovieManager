package blackmediamanager.medialibrary;

import java.io.File;
import java.io.IOException;

import com.omertron.themoviedbapi.MovieDbException;

import blackmediamanager.database.domain.Movie;
import blackmediamanager.database.persistance.DBFacade;

/**
 *
 * @author fabian
 */
public class MediaAdder {

	// TODO(refactor): change input parameter from file to MediaInfo
	public boolean movieToAdd(File file) throws IOException, MovieDbException {
		Movie movie = MediaInfoToMovieConverter.convert(file);

		DBFacade.instance.saveMovie(movie);

		if (movie != null) {
			return true;
		} else {
			return false;
		}
	}

}
