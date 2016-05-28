package gui.controller;

import application.DataHandler;
import application.StatisticsCalculator;
import com.sun.prism.paint.Color;
import database.domain.Genre;
import database.domain.Genrepos;
import database.domain.Movie;
import database.persistance.DBFacade;
import gui.controller.statisticsControllerHelper.StatisticsControllerHelperPieChart;
import gui.controller.statisticsControllerHelper.StatisticsControllerHelperTop10;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author fabian
 */
public class ContentStatisticsController implements Initializable {

    @FXML
    private PieChart pieChartMovies;
    @FXML
    private VBox vboxForLabels;
    @FXML
    private ComboBox comboBoxMovies;
    @FXML
    private ListView listViewMovies;
    @FXML
    private ComboBox comboBoxDocumentaries;
    @FXML
    private ListView listViewDocumentaries;
    @FXML
    private ComboBox comboBoxTVshows;
    @FXML
    private ListView listViewTVshows;

    private final int TOP10 = 10;

    private DataHandler dataHandler;
    private StatisticsControllerHelperTop10 helperTop10;
    private StatisticsControllerHelperPieChart helperPieChart;
    private StatisticsCalculator statisticsCalculator;

    private List<Movie> movies;
    private List<Genrepos> genres;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        dataHandler = new DataHandler();
        movies = dataHandler.getAllMovies();
        genres = dataHandler.getAllGenrePoses();
        helperTop10 = new StatisticsControllerHelperTop10();
        statisticsCalculator = new StatisticsCalculator();
        statisticsCalculator.setDataHandler(dataHandler);
        helperPieChart = new StatisticsControllerHelperPieChart();
        
        //  ----------------------------------------------------------------------------------------------------------------
        //                                                      TOP 10
        //  ----------------------------------------------------------------------------------------------------------------
        
        // Create Comboboxes
        comboBoxMovies.getItems().addAll(helperTop10.createListComboBoxMovies());
        comboBoxMovies.setValue(comboBoxMovies.getItems().get(0));
        
        comboBoxDocumentaries.getItems().addAll(helperTop10.createListComboBoxDocumentaries());
        comboBoxDocumentaries.setValue(comboBoxDocumentaries.getItems().get(0));
        
        comboBoxTVshows.getItems().addAll(helperTop10.createListComboBoxTVshows());
        comboBoxTVshows.setValue(comboBoxTVshows.getItems().get(0));
        
        //Default Selected
        listViewMovies.setItems(statisticsCalculator.calcTop10MovieBiggestSize());
        
        
        //  ----------------------------------------------------------------------------------------------------------------
        //                                                      Movies
        //  ----------------------------------------------------------------------------------------------------------------
        
        //  Genre PieChart
        pieChartMovies.setData(helperPieChart.createPieChartMovieGenres(movies, genres));
        
        

    }


    @FXML
    public void setListviewMovies() {

        if (comboBoxMovies.getSelectionModel().getSelectedItem().equals("Top 10 - Biggest Size")) {
            listViewMovies.getItems().clear();
            listViewMovies.setItems(statisticsCalculator.calcTop10MovieBiggestSize());
        } else if(comboBoxMovies.getSelectionModel().getSelectedItem().equals("Top 10 - Oldest Movies")) {
            listViewMovies.getItems().clear();
            listViewMovies.setItems(statisticsCalculator.calcTop10MovieOldestMovies());
        }

    }
}
