package application;

import application.helpers.AppConfig;
import application.helpers.StringStuff;
import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import com.omertron.themoviedbapi.results.ResultList;
import database.domain.Movie;
import database.domain.Tmdbinfo;
import database.domain.Videoline;
import database.persistance.DBFacade;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import org.apache.commons.io.FilenameUtils;
import services.mediainfo.MediaInfoGetter;
import services.tmdbinfo.TmdbInfo;

/**
 *
 * @author fabian
 */
public class MediaAdder {
    TmdbInfo tmdb;
    StringStuff strstuff;
    MediaInfoGetter inf;
    MediaHandler medHand;
    
    public MediaAdder() throws MovieDbException{
        tmdb = new TmdbInfo(cfg.API_KEY);
        strstuff = new StringStuff();
        medHand = new MediaHandler();
    }
    
    private AppConfig cfg = AppConfig.getInstance();
    
    private Tmdbinfo tmdbinfo;
    private Videoline videoline;
    private byte active;
    private String note;
    private boolean remux;
    private String edition;
    private int duration;
    private String fileName;
    private double fileSize;
    private String fileFormat;
    private Date date;
    private Set owners = new HashSet(0);
    private Set genres = new HashSet(0);
    private Set audiolines = new HashSet(0);
    
    public boolean movieToAdd(File file) throws IOException, MovieDbException {
        tmdbinfo = new Tmdbinfo();
        inf = new MediaInfoGetter(file);
        boolean tmdbFound = false;
        
        // search movietitle and year on TMDB, returns list of results
        String movieName = strstuff.getMovieNameOnly(file.getName());
        int movieYear = strstuff.getYearFromMovieFilename(file.getName());
        ResultList<MovieInfo> movieSearchResults = tmdb.getMovieSearchResultsList(movieName, movieYear);
        
        // add file information
        active = 0;
        edition = "";//getVersion(file.getName());          // todo: check brackets
        fileName = getName(file.getName());                 // mit oder ohne extension ?
        duration = (int) inf.getVideoDurationMinutes();
        fileSize = inf.getFileSize();
        fileFormat = inf.getFileFormat();
        date = new Date();                                  // aktuelles Datum (dateierstellungsdatum nehmen ?)
        videoline = medHand.setVideoline(inf);
        audiolines = medHand.setAudiolines(inf);

        // add TMDB Infos
        MovieInfo movieInfo = new MovieInfo();
        if (movieSearchResults.getTotalResults() > 0){
            // use first searchresult from list (should be the best result)
            tmdbFound = true;
            movieInfo = movieSearchResults.getResults().get(0);
            addTmdbData(movieInfo);
        } else {
            File nfofile = new File(file.getParent() + "/" + FilenameUtils.removeExtension(file.getName()) + ".nfo");
            if (nfofile.exists()){
                // if there are no tmdb results found with search then check for nfo file (with tmdb id)
                int fetchTmdbId = strstuff.getTmdbFromNfoFile(nfofile.getPath());
                System.out.println(fetchTmdbId);
                if (fetchTmdbId != -1){
                    tmdbFound = true;
                    movieInfo = tmdb.getMovieInfoByID(strstuff.getTmdbFromNfoFile(nfofile.getPath()));
                    addTmdbData(movieInfo);
                }
            }   
        }
        
        //genres = medHand.setGenres(movieInfo);
        owners = medHand.setOwner();

        Movie movie = new Movie();
        movie.setActive((byte) 1);
        movie.setAudiolines(audiolines);
        movie.setDateAdded(date);
        movie.setDateModified(date);
        movie.setDuration(0);
        movie.setEdition(edition);
        movie.setFileFormat(fileFormat);
        movie.setFileName(fileName);
        movie.setFileSize(fileSize);
        movie.setGenres(genres);
        movie.setNote("");//
        movie.setOwners(owners);
        movie.setRemux(remux);
        movie.setTmdbinfo(tmdbinfo);
        movie.setVideoline(videoline);

        // Film speichern
        //DBFacade dbfacade = new DBFacade();
        //dbfacade.saveMovie(movie);
        
        return tmdbFound;
    }
    
    private void addTmdbData(MovieInfo movieInfo) throws MovieDbException{
            tmdbinfo.setOverview(movieInfo.getOverview());
            tmdbinfo.setRating((double) movieInfo.getUserRating());
            //tmdbinfo.setReleasedYear(movieInfo.getReleaseDate());      
            tmdbinfo.setTagline(medHand.setTagline(movieInfo));
            tmdbinfo.setTitle(movieInfo.getTitle());
            tmdbinfo.setTmdbId(movieInfo.getId());
            tmdbinfo.setCoverUrl(tmdb.getMovieCoverURL(movieInfo.getId()));
    }
    
    private String getVersion(String fileName) {

        StringBuilder sb = new StringBuilder(fileName);
        StringBuilder sbVersion = new StringBuilder("");
        int endPos = 0;
        int startPos = 0;

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '(') {
                //Schaue ob es ein Jahr ist, wenn nicht gibt es false zurück und es wird ausgeführt
                if (isYear(fileName, i + 1, i + 5) == false) {
                    for (int j = i; j < sb.length(); j++) {
                        if (sb.charAt(j) == ')') {
                            startPos = i + 1;
                            endPos = j;
                            if (sbVersion.length() == 0) {
                                sbVersion.append(sb.substring(startPos, endPos));
                            } else {
                                sbVersion.append(", ");		//wenn schon eine Info vorhanden ist, füge ein Komma hinzu
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

    private String getName(String fileName) {

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

    private int getYear(String fileName) {

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

    private boolean isYear(String fileName, int startPos, int endPos) {

        StringBuilder sb = new StringBuilder(fileName);

        try {
            Integer.valueOf(sb.substring(startPos, endPos));
            return true;
        } catch (NumberFormatException e) {
            //TODO: etwas anzeigen??
        }

        return false;
    }

    private String getFileFormat(String fileName) {

        StringBuilder sb = new StringBuilder(fileName);

        for (int i = 0; i < sb.length(); i++) {
            if (sb.charAt(i) == '.') {
                int len = sb.length() - i + 1;
                //Dateiendung muss 3 Buchstaben haben, zB mkv, avi etc
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
    
        //Pfad auslesen und infos herausnehmen
    private String getHDD(String filePath) {

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
