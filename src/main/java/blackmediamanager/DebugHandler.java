package blackmediamanager;

import java.io.File;
import java.io.IOException;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.results.ResultList;

import blackmediamanager.mediascanner.util.StringHelper;
import blackmediamanager.scrapers.mediainfo.MediaInfoFile;
import blackmediamanager.scrapers.tmdbinfo.TmdbInfo;
import blackmediamanager.scrapers.xrelinfo.XRelInfo;
import blackmediamanager.scrapers.xrelinfo.jsondata.latest.XRlatest;
import blackmediamanager.scrapers.xrelinfo.jsondata.result.XRresults;
import blackmediamanager.util.ResourcePathResolver;

/**
 *
 * For debugging
 *
 * @author fabian
 */
public class DebugHandler {

	private static final ClassLoader classLoader = ResourcePathResolver.class.getClassLoader();

	public static void show() throws Exception {
		testMediaInfo();
		testTmdbInfo();
		testXRel();
	}

	public static void testMediaInfo() throws IOException {
		System.setProperty("jna.library.path", classLoader.getResource("lib/").getPath());
		System.out.println("JVM Bit version: " + System.getProperty("sun.arch.data.model"));
		System.out.println("Java library path: " + System.getProperty("jna.library.path"));
		File file = new File("C://FABIAN//example.mkv");
		if (!file.exists()) {
			System.out.println("file not found: " + file.getPath());
		}

		MediaInfoFile inf = new MediaInfoFile(file);
		System.out.println(inf.getFileFormat());
	}

	public static void testTmdbInfo() throws MovieDbException, IOException {
		// Tmdbinfo tmdbinfo = new Tmdbinfo();

		ResultList<MovieInfo> movieInfo = TmdbInfo.instance.getMovieSearchResultsList("armageddon", 1998);
		System.out.println("Total results: " + movieInfo.getTotalResults());

		String pf = "C:\\FABIAN\\_Serien\\London.Has.Fallen.2016.WEBRip.AC3LD.German.XviD-LoC\\LoC-london has fallen.nfo";
		MovieInfo inf = TmdbInfo.instance.getMovieInfoByID(StringHelper.getTmdbFromNfoFile(pf));
		System.out.println(inf.getOriginalTitle());
	}

	public static void testXRel() throws IOException, Exception {
		// for debug use only
		XRelInfo xrel = new XRelInfo();

		// get latest releases example
		System.out.println("**********************************************");
		System.out.println("Latest releases (filtered):");
		XRlatest latest = xrel.getLatestHDMovieReleases("HDTV", "movie", 5);
		for (int i = 0; i < latest.getList().size(); i++) {
			System.out.println(latest.getList().get(i).getDirname());
		}

		// search example
		System.out.println("**********************************************");
		System.out.println("Search results example:");
		XRresults results = xrel.searchRelease("1080p");
		for (int i = 0; i < results.getResults().size(); i++) {
			System.out.println(results.getResults().get(i).getDirname());
		}

		// cover example
		System.out.println("**********************************************");
		System.out.println("Cover url example:");
		System.out.println(xrel.getCoverURL(latest.getList().get(0).getId()));
	}
}
