package blackmediamanager.medialibrary;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.apache.commons.io.FilenameUtils;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.results.ResultList;

import blackmediamanager.database.domain.Movie;
import blackmediamanager.database.domain.Tmdbinfo;
import blackmediamanager.medialibrary.mediainfo.MediaInfoFile;
import blackmediamanager.medialibrary.tmdbinfo.TmdbInfo;
import blackmediamanager.medialibrary.util.StringHelper;

public class MediaInfoToMovieConverter {
	public static Movie convert(File file) throws MovieDbException, IOException {

		MediaInfoFile mediaInfoFile = new MediaInfoFile(file);
		MovieInfo movieInfo = readMovieInfo(file);

		if (movieInfo == null) {
			return null;
		}

		Date date = new Date(); // current date or file creation date?

		// create actual movie instance and fill it with values
		Movie movie = new Movie();
		movie.setActive((byte) 1);
		movie.setAudiolines(MediaInfoConverterHelper.getAudiolines(mediaInfoFile));
		movie.setDateAdded(date);
		movie.setDateModified(date);
		movie.setDuration((int) mediaInfoFile.getVideoDurationMinutes());
		movie.setEdition("");
		// getVersion(file.getName()); // todo: check brackets
		movie.setFileFormat(mediaInfoFile.getFileFormat());
		movie.setFileName(getName(file.getName())); // with or without
													// extensions?
		movie.setFileSize(mediaInfoFile.getFileSize());
		movie.setGenres(MediaInfoConverterHelper.getGenres(movieInfo));
		movie.setNote("");//
		movie.setOwners(MediaInfoConverterHelper.getOwner());
		movie.setRemux(false);
		movie.setTmdbinfo(readTmdbData(movieInfo));
		movie.setVideoline(MediaInfoConverterHelper.getVideoline(mediaInfoFile));

		return movie;
	}

	private static MovieInfo readMovieInfo(File file) throws MovieDbException, IOException {
		// search movietitle and year on TMDB, returns list of results
		String movieName = StringHelper.getMovieNameOnly(file.getName());
		int movieYear = StringHelper.getYearFromMovieFilename(file.getName());
		ResultList<MovieInfo> movieSearchResults = TmdbInfo.instance.getMovieSearchResultsList(movieName, movieYear);

		MovieInfo movieInfo = null;
		if (movieSearchResults.getTotalResults() > 0) {
			// use first searchresult from list (should be the best result)
			movieInfo = movieSearchResults.getResults().get(0);
		} else {
			File nfofile = new File(file.getParent() + "/" + FilenameUtils.removeExtension(file.getName()) + ".nfo");
			if (nfofile.exists()) {
				// if there are no tmdb results found with search then check for
				// nfo file (with tmdb id)
				int fetchTmdbId = StringHelper.getTmdbFromNfoFile(nfofile.getPath());
				System.out.println(fetchTmdbId);
				if (fetchTmdbId != -1) {
					movieInfo = TmdbInfo.instance.getMovieInfoByID(StringHelper.getTmdbFromNfoFile(nfofile.getPath()));
				}
			}
		}

		return movieInfo;
	}

	private static Tmdbinfo readTmdbData(MovieInfo movieInfo) throws MovieDbException {
		Tmdbinfo tmdbinfo = new Tmdbinfo();
		tmdbinfo.setOverview(movieInfo.getOverview());
		tmdbinfo.setRating((double) movieInfo.getUserRating());
		// tmdbinfo.setReleasedYear(movieInfo.getReleaseDate());
		tmdbinfo.setTagline(MediaInfoConverterHelper.getTagline(movieInfo));
		tmdbinfo.setTitle(movieInfo.getTitle());
		tmdbinfo.setTmdbId(movieInfo.getId());
		tmdbinfo.setCoverUrl(TmdbInfo.instance.getMovieCoverURL(movieInfo.getId()));

		return tmdbinfo;
	}

	private static String getVersion(String fileName) {

		StringBuilder sb = new StringBuilder(fileName);
		StringBuilder sbVersion = new StringBuilder("");
		int endPos = 0;
		int startPos = 0;

		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '(') {
				// Schaue ob es ein Jahr ist, wenn nicht gibt es false zurück
				// und es wird ausgeführt
				if (isYear(fileName, i + 1, i + 5) == false) {
					for (int j = i; j < sb.length(); j++) {
						if (sb.charAt(j) == ')') {
							startPos = i + 1;
							endPos = j;
							if (sbVersion.length() == 0) {
								sbVersion.append(sb.substring(startPos, endPos));
							} else {
								sbVersion.append(", "); // wenn schon eine Info
														// vorhanden ist, füge
														// ein Komma hinzu
								sbVersion.append(sb.substring(startPos, endPos));
							}
							j = sb.length();
						}
					}
				}
			}
		}
		return sbVersion.toString();
	}

	private static String getName(String fileName) {

		StringBuilder sb = new StringBuilder(fileName);
		int endPos = 0;

		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '(') {
				endPos = i - 1;
				i = sb.length();
			}
		}
		return sb.substring(0, endPos);
	}

	private static int getYear(String fileName) {

		StringBuilder sb = new StringBuilder(fileName);
		int startPos = 0;
		int endPos = 0;
		int year = -1;

		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '(') {
				startPos = i + 1;
				endPos = 5 + i;

				if (isYear(fileName, startPos, endPos) == true) {
					year = Integer.valueOf(sb.substring(startPos, endPos));
					i = sb.length();
				}
			}
		}
		return year;
	}

	private static boolean isYear(String fileName, int startPos, int endPos) {

		StringBuilder sb = new StringBuilder(fileName);

		try {
			Integer.valueOf(sb.substring(startPos, endPos));
			return true;
		} catch (NumberFormatException e) {
			// TODO: etwas anzeigen??
		}

		return false;
	}

	private static String getFileFormat(String fileName) {

		StringBuilder sb = new StringBuilder(fileName);

		for (int i = 0; i < sb.length(); i++) {
			if (sb.charAt(i) == '.') {
				int len = sb.length() - i + 1;
				// Dateiendung muss 3 Buchstaben haben, zB mkv, avi etc
				if (len == 3) {
					sb.substring(i + 1, sb.length());
				}
			}
		}

		if (sb.length() < 3) {
			return sb.toString();
		} else {
			return null;
		}
	}

	// Pfad auslesen und infos herausnehmen
	private static String getHDD(String filePath) {

		String hdd = "";
		StringBuilder sb = new StringBuilder(filePath);

		for (int i = 3; i < filePath.length(); i++) {
			if (filePath.charAt(i) == '\\') {
				hdd = sb.substring(3, i);
				i = filePath.length();
			}
		}

		return hdd;
	}
}
