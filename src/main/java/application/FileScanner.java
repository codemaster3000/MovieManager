package application;

import com.omertron.themoviedbapi.MovieDbException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import application.helpers.AppConfig;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.StringTokenizer;
import org.apache.commons.io.FilenameUtils;

public class FileScanner {

    /* TODO: 
    
    - setaudioline methode prüfen obs passt --> DTS X etc
    - DTS-mod aus dateiname auslesen
    - TMDB Jahr isch zur zit vo da file.. eventl ändern uf TMDB-info
    - Genre ist immer null, warum?? genreID gibt es
    - tagline ist null??
    - alle SET auf null überprüfen, sunsch "" --> sus gits nullpointer-exepitions
    - setOwner() wer es hinzufügt muss iirgndwo festglegt si als "eingeloggt" odr so
    - auf duplikat prüfen!!! neuen active(1) --> alte auf active(2) setzen
    
    
     */
    private String _directoryPath;
    private File _currentMovieFile;
    private ArrayList<File> _moviePathList;
    private AppConfig cfg;

    private Collection<String> videoExtensions = new HashSet<>();

    public FileScanner() {
        cfg = AppConfig.getInstance();
        videoExtensions = stringTokenize(cfg.EXTENSIONS_VIDEO, ",");
    }

    public void searchDirectory(String directoryPath) throws IOException, MovieDbException {
        MediaAdder adder = new MediaAdder();
        _directoryPath = directoryPath;
        _currentMovieFile = null;
        _moviePathList = new ArrayList();

        solvePath(_directoryPath);

        // wenn currentmoviefile nicht null ist, wurde nur ein film hinzugefügt, ansonsten wurde eine hierachie hinzugefügt (=mehrere)
        if (_currentMovieFile != null) {
            adder.movieToAdd(_currentMovieFile);
        } else if (_moviePathList.size() > 0) {
            for (File file : _moviePathList) {
                adder.movieToAdd(file);
            }
        }

    }

    // schnellerer filescan mit java 7 nio library (rekursiv)
    public ArrayList<String> getScannedFileList(ArrayList<String> fileNames, Path dir) throws IOException {
        String ext = "";
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
            for (Path path : stream) {
                if (path.toFile().isDirectory()) {
                    getScannedFileList(fileNames, path);
                } else {
                    ext = FilenameUtils.getExtension(path.getFileName().toString());
                    if (videoExtensions.contains(ext)){
                        fileNames.add(path.toAbsolutePath().toString());
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fileNames;
    }

    private void solvePath(String directoryPath) {
        File directory = new File(directoryPath);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        for (File file : fList) {
            if (file.isFile()) {
                String ext = FilenameUtils.getExtension(file.getName());
                if (videoExtensions.contains(ext)) {
                    System.out.println(file.toString());
                    _currentMovieFile = file;
                }
            } else if (file.isDirectory()) {
                _moviePathList.add(file);
            }
        }
    }

    //auf duplikat prüfen
    private boolean proofIsDuplicate(String name) {
        //TODO
        //alle filme durchgehen
        return false;
    }

    // convert string list to collection
    private Collection<String> stringTokenize(String sourceString, String delimiter) {
        StringTokenizer st = new StringTokenizer(sourceString, delimiter);
        Collection<String> keywords = new HashSet<>();
        while (st.hasMoreTokens()) {
            keywords.add(st.nextToken());
        }
        return keywords;
    }
}
