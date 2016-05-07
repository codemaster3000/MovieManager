package gui.controller;

import application.FileHandler;
import com.omertron.themoviedbapi.MovieDbException;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Fabian Ladurner
 */
public class MovieContentController implements Initializable {

    @FXML
    public AnchorPane anchorPaneMain;
    @FXML
    public TableView tableMovies;
    @FXML
    private ImageView imageCover;
    @FXML
    private Label labelMovieHeadline;
    @FXML
    private Label labelTagline;
    @FXML
    private TextArea textAreaOverview;
    @FXML
    private Label labelMovieTitel;
    @FXML
    private Label labelYear;
    @FXML
    private Label labelRating;
    @FXML
    private Label labelEdition;
    @FXML
    private CheckBox labelRemux;
    @FXML
    private TextArea textareaNote;
    @FXML
    private Label labelTMDBid;
    @FXML
    private Label labelDuration;
    @FXML
    private Label labelGenre;
    @FXML
    private Label labelFileName;
    @FXML
    private Label labelFileSize;
    @FXML
    private Label labelFormat;
    @FXML
    private Label labelOwner;
    @FXML
    private Label labelBitrate;
    @FXML
    private Label labelMode;
    @FXML
    private Label labelAspectRatio;
    @FXML
    private Label labelResolution;
    @FXML
    private Label labelFrameRate;
    @FXML
    private TableView<?> tableAudioLine;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String imageSource = "https://image.tmdb.org/t/p/w396/i68IvNkUvqaKPY0UbadXcQ23aik.jpg";
        imageCover.setImage(new Image(imageSource));

    }

    public void addMovies() throws MovieDbException {

        String directoryPath;

        DirectoryChooser fileChooser = new DirectoryChooser();
        fileChooser.setTitle("Neue(r) Film(e) hinzufügen");
        File file = fileChooser.showDialog(anchorPaneMain.getScene().getWindow());
        if (file != null) {
            directoryPath = file.getAbsolutePath();
            FileHandler fileHandler = new FileHandler();
            try {
                fileHandler.searchDirectory(directoryPath);
            } catch (IOException ex) {
                Logger.getLogger(MovieContentController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
