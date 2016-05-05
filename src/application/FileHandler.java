package application;

import com.omertron.themoviedbapi.MovieDbException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import application.helpers.AppConfig;



public class FileHandler {

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



    public FileHandler() {
        // empty constructor
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

    //auf duplikat prüfen
    private boolean proofIsDuplicate(String name) {

        //alle filme durchgehen
        return false;
    }
}
