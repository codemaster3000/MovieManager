package application;

import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import database.domain.Audioline;
import database.domain.Audiolinepos;
import database.domain.Genre;
import database.domain.Genrepos;
import database.domain.Movie;
import database.domain.Ownerpos;
import database.domain.Tmdbinfo;
import database.domain.Videoline;
import database.persistance.DBFacade;
import database.persistance.DBSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import moviecode.mediainfo.MediaInfoGetter;

import static java.lang.Math.toIntExact;
import moviecode.helpers.AppConfig;
import moviecode.movieinfo.TmdbInfo;
import org.hibernate.Session;

public class FileHandler {

    private static AppConfig cfg = AppConfig.getInstance();

    /* TODO: 
    
    - setaudioline methode prüfen obs passt --> DTS X etc
    - DTS-mod aus dateiname auslesen
    - TMDB Jahr isch zur zit vo da file.. eventl ändern uf TMDB-info
    - Genre ist immer null, warum?? genreID gibt es
    - tagline ist null??
    
     */
    private String _directoryPath;
    private File _currentMovieFile;
    private ArrayList<File> _moviePathList;

    public FileHandler() {

    }

    public void searchDirectory(String directoryPath) throws IOException, MovieDbException {
        _directoryPath = directoryPath;
        _currentMovieFile = null;
        _moviePathList = new ArrayList();

        solvePath(_directoryPath);

        // wenn currentmoviefile nicht null ist, wurde nur ein film hinzugefügt, ansonsten wurde eine hierachie hinzugefügt (=mehrere)
        if (_currentMovieFile != null) {
            movieToAdd(_currentMovieFile);
        } else if (_moviePathList.size() > 0) {
            for (File file : _moviePathList) {
                movieToAdd(file);
            }
        }

    }

    private void solvePath(String directoryPath) {
        File directory = new File(directoryPath);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                if (file.toString().endsWith(".mkv")) {
                    System.out.println(file.toString());
                    _currentMovieFile = file;
                }
            } else if (file.isDirectory()) {
                _moviePathList.add(file);
            }
        }
    }

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

    private void movieToAdd(File file) throws IOException, MovieDbException {

        tmdbinfo = new Tmdbinfo();
        MediaInfoGetter inf = new MediaInfoGetter(file);
        TmdbInfo tmdb = new TmdbInfo("dec0b87fe8f35746c1e96d2fa8ba4873");
        MovieInfo movieInfo = tmdb.getSearchMovieInfo(file.getName());
        
        active = 0;
        edition = getVersion(file.getName());
        fileName = getName(file.getName());
        fileFormat = getFileFormat(file.getName());
        duration = (int) inf.getVideoDurationMinutes();
        fileSize = inf.getFileSize();
        fileFormat = inf.getFileFormat();
        date = new Date(); //aktuelles Datum
        videoline = setVideoline(inf);
        audiolines = setAudiolines(inf);

        //TMDB Infos
        tmdbinfo.setOverview(movieInfo.getOverview());
        tmdbinfo.setRating(movieInfo.getUserRating());
        tmdbinfo.setReleasedYear(getYear(file.getName()));      //eventl ändern 
        tmdbinfo.setTagline(movieInfo.getTagline());
        tmdbinfo.setTitle(movieInfo.getTitle());
        tmdbinfo.setTmdbId(movieInfo.getId());
        //tmdbinfo.setCoverUrl(tmdb.getMovieCoverURL(movieInfo.getId()));
        tmdbinfo.setCoverUrl("-");
        
        genres = setGenres(movieInfo);
        owners = setOwner();

        int year = getYear(file.getName());

        String hdd = getHDD(file.getAbsolutePath());

        Movie movie = new Movie();
        movie.setActive((byte) 1);
        movie.setAudiolines(audiolines);
        movie.setDate(date);
        movie.setDuration(0);
        movie.setEdition(edition);
        movie.setFileFormat(fileFormat);
        movie.setFileName(fileName);
        movie.setFileSize(fileSize);
        movie.setGenres(genres);
        movie.setNote("");
        movie.setOwners(owners);
        movie.setRemux(remux);
        movie.setTmdbinfo(tmdbinfo);
        movie.setVideoline(videoline);
        

        Session session = DBSession.getInstance();
        DBFacade dbfacade = new DBFacade();
               
        dbfacade.saveMovie(movie);

    }

    //auf duplikat prüfen
    private boolean proofIsDuplicate(String name) {

        //alle filme durchgehen
        return false;
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

    private Videoline setVideoline(MediaInfoGetter inf) {

        Videoline line = new Videoline();
        line.setAspectRatio(inf.getDisplayAspectRatio());
        line.setResolutionHeight(inf.getVideoResolutionHeight());
        line.setResolutionWidth(inf.getVideoResolutionWidth());
        line.setVideoBitrate((int) inf.getVideoOverallBitrateKbps());
        line.setVideoBitrateMode(inf.getVideoBitrateMode());
        line.setFramerate(inf.getVideoFramerate());

        return line;
    }

    private Integer id;
    private String audioLanguage;
    private int audioBitrate;
    private String audioFormat;
    private String audioChannels;
    private boolean dtsMod;

    private Set<Audiolinepos> setAudiolines(MediaInfoGetter inf) {

        Set<Audiolinepos> lines = new HashSet();

        for (int i = 0; i < inf.getAudioStreamCount(); i++) {
            Audiolinepos line = new Audiolinepos();
            line.setAudioLanguage(inf.getAudioLanguages().get(i));
            line.setAudioFormat(inf.getAudioFormats().get(i));
            line.setAudioBitrate(toIntExact(inf.getAudioBitratesKbps().get(i)));
            line.setAudioChannels(setAudioChannels(inf.getAudioChannels().get(i), line.getAudioFormat()));
            line.setDtsMod(false);

            lines.add(line);
        }

        return lines;
    }

    private String setAudioChannels(int channels, String format) {
        if (channels == 2) {
            return "2.0";
        } else if (channels == 3) {
            return "2.1";
        } else if (channels == 6) {
            return "5.1";
        } else if (channels == 8) {
            if (format.equals("Dolby Atmos") || format.equals("DTS X")) {
                return "5.1.2";
            } else {
                return "7.1";
            }
        } else if (channels == 10) {
            return "7.1.2";
        } else {
            return null;
        }
    }

    private Set<Genrepos> setGenres(MovieInfo movieInfo) {

        Set<Genrepos> genres = new HashSet();

        for (int i = 0; i < movieInfo.getGenreIds().size(); i++) {
            Genrepos pos = new Genrepos();
            pos.setType(movieInfo.getGenreIds().get(i).toString());
            genres.add(pos);
        }

        return genres;
    }

    private Set<Ownerpos> setOwner() {

        Set<Ownerpos> owner = new HashSet();

        Ownerpos pos = new Ownerpos();
        pos.setOwnerName("Ladurner"); 
        
        owner.add(pos);

        return owner;
    }

}
