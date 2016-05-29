package services.tmdbinfo;

import java.awt.image.BufferedImage;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.TheMovieDbApi;
import com.omertron.themoviedbapi.enumeration.SearchType;
import com.omertron.themoviedbapi.model.artwork.Artwork;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.results.ResultList;

import application.helpers.AppConfig;
import application.helpers.StringStuff;

/**
 *
 * @author fabian
 */
public class TmdbInfo {

	private TheMovieDbApi tmdb;
	private AppConfig cfg = AppConfig.getInstance();
	private StringStuff chk;
	private String LANG; // langage setting

	public final static TmdbInfo instance = new TmdbInfo();

	public TmdbInfo() {
		// setup tmdb
	}

	private boolean isInitialized;

	public void init() throws MovieDbException {
		if (!isInitialized) {
			isInitialized = true;
			tmdb = new TheMovieDbApi(cfg.API_KEY);
			chk = new StringStuff();
			LANG = cfg.TMDB_LANGUAGE;
		}
	}

	public MovieInfo getMovieInfoByID(int movieId) throws MovieDbException {
		MovieInfo inf = tmdb.getMovieInfo(movieId, LANG, null);
		return inf;
	}

	public ResultList<MovieInfo> getMovieSearchResultsList(String searchKeyword, int year) throws MovieDbException {
		ResultList<MovieInfo> results = new ResultList<MovieInfo>();
		if (year != 0) {
			results = tmdb.searchMovie(searchKeyword, null, LANG, true, year, null, SearchType.PHRASE);
		} else {
			results = tmdb.searchMovie(searchKeyword, null, LANG, true, null, null, SearchType.PHRASE);
		}
		return results;
	}

	public MovieInfo getSearchMovieInfo(String movieFilename) throws MovieDbException {
		// returns 0 if no year available in filename
		int year = chk.getYearFromMovieFilename(movieFilename);
		int posBracket = movieFilename.indexOf('(');

		// extract moviename from filename without bracket content and file
		// extension
		String moviename = movieFilename.substring(0, posBracket - 1);

		ResultList<MovieInfo> results = new ResultList<MovieInfo>();
		if (year != 0) {
			results = tmdb.searchMovie(moviename, null, LANG, true, year, null, SearchType.PHRASE);
		} else {
			results = tmdb.searchMovie(moviename, null, LANG, true, null, null, SearchType.PHRASE);
		}

		// return first search result if available
		if (results.isEmpty()) {
			return null;
		} else {
			return results.getResults().get(0);
		}
	}

	public String getMovieCoverURL(int movieId) throws MovieDbException {
		ResultList<Artwork> imgs = tmdb.getMovieImages(movieId, "en");
		if (imgs.isEmpty()) {
			return null;
		} else {
			return cfg.TMDB_IMAGEROOTPATH + imgs.getResults().get(0).getFilePath();
		}
	}

	public void showMovieCoverPopup(int movieId) throws MovieDbException {
		ResultList<Artwork> imgs = tmdb.getMovieImages(movieId, "en");

		try {
			String path = cfg.TMDB_IMAGEROOTPATH + imgs.getResults().get(0).getFilePath();
			URL url = new URL(path);
			BufferedImage image = ImageIO.read(url);
			JLabel label = new JLabel(new ImageIcon(image));
			JFrame f = new JFrame();
			f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			f.getContentPane().add(label);
			f.pack();
			f.setLocation(200, 200);
			f.setVisible(true);
		} catch (Exception exp) {
			exp.printStackTrace();
		}
	}
}
