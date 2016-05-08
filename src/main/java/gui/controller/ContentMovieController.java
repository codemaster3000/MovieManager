package gui.controller;

import application.helpers.AppConfig;
import com.omertron.themoviedbapi.MovieDbException;
import com.omertron.themoviedbapi.model.movie.MovieInfo;
import database.domain.Movie;
import database.domain.Tmdbinfo;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import services.tmdbinfo.TmdbInfo;

/**
 * FXML Controller class
 *
 * @author fabian
 */
public class ContentMovieController implements Initializable {

    @FXML
    private TableView tablelist;
    @FXML
    private ImageView imgCover;
    
    private AppConfig cfg = AppConfig.getInstance();
    private TmdbInfo tminf;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initTable();
        try {
            tminf = new TmdbInfo(cfg.API_KEY);
        } catch (MovieDbException ex) {
            //Logger.getLogger(ContentMovieController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        tablelist.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2 || event.getClickCount() == 1) {
                    try {
                        showSelectedItemData((Movie) tablelist.getSelectionModel().getSelectedItem());
                    } catch (MovieDbException ex) {
                        Logger.getLogger(ContentMovieController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
        
        String imageSource = "https://image.tmdb.org/t/p/w396/i68IvNkUvqaKPY0UbadXcQ23aik.jpg";
        imgCover.setImage(new Image(imageSource));
    }

    private void initTable() {
        // some test content
        Tmdbinfo inf1 = new Tmdbinfo();
        Tmdbinfo inf2 = new Tmdbinfo();
        inf1.setTmdbId(106);
        inf2.setTmdbId(218);
        
        final ObservableList<Movie> data = FXCollections.observableArrayList(
                new Movie(0, inf1, null, (byte) 1, "", false, "", 10, "Predator", 1.0, "", null),
                new Movie(1, inf2, null, (byte) 1, "", false, "", 10, "Terminator", 1.0, "", null)
        );

        TableColumn col01 = new TableColumn();
        col01.setText("ID");
        col01.setCellValueFactory(new PropertyValueFactory("id"));
        TableColumn col02 = new TableColumn();
        col02.setText("Title");
        col02.setCellValueFactory(new PropertyValueFactory("fileName"));

        tablelist.setItems(data);
        tablelist.getColumns().addAll(col01, col02);
    }
    
    private void showSelectedItemData(Movie mov) throws MovieDbException{
        // loads movie info
        System.out.println(mov.getFileName());
        String imageSource = tminf.getMovieCoverURL(mov.getTmdbinfo().getTmdbId());
        imgCover.setImage(new Image(imageSource));
    }
}
