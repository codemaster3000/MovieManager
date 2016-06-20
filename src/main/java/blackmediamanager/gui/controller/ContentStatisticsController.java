package blackmediamanager.gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import blackmediamanager.application.controller.StatisticsCalculator;
import blackmediamanager.database.dao.DataAccessException;
import blackmediamanager.database.domain.Genrepos;
import blackmediamanager.database.domain.Movie;
import blackmediamanager.gui.controller.helper.StatisticsControllerHelperPieChart;
import blackmediamanager.gui.controller.helper.StatisticsControllerHelperTop10;
import blackmediamanager.util.ApplicationServices;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
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

	private StatisticsControllerHelperTop10 helperTop10;
	private StatisticsControllerHelperPieChart helperPieChart;
	private StatisticsCalculator statisticsCalculator;

	private List<Movie> movies;
	private List<Genrepos> genres;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		try {
			movies = ApplicationServices.instance.getRemoteDatabaseRegistry().getMovieDao().getAll();
			genres = ApplicationServices.instance.getRemoteDatabaseRegistry().getGenreposDao().getAll();
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		helperTop10 = new StatisticsControllerHelperTop10();
		statisticsCalculator = new StatisticsCalculator();
		helperPieChart = new StatisticsControllerHelperPieChart();

		// ----------------------------------------------------------------------------------------------------------------
		// TOP 10
		// ----------------------------------------------------------------------------------------------------------------

		// Create Comboboxes
		comboBoxMovies.getItems().addAll(helperTop10.createListComboBoxMovies());
		comboBoxMovies.setValue(comboBoxMovies.getItems().get(0));

		comboBoxDocumentaries.getItems().addAll(helperTop10.createListComboBoxDocumentaries());
		comboBoxDocumentaries.setValue(comboBoxDocumentaries.getItems().get(0));

		comboBoxTVshows.getItems().addAll(helperTop10.createListComboBoxTVshows());
		comboBoxTVshows.setValue(comboBoxTVshows.getItems().get(0));

		// Default Selected
		try {
			listViewMovies.setItems(statisticsCalculator.calcTop10MovieBiggestSize());
		} catch (DataAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// ----------------------------------------------------------------------------------------------------------------
		// Movies
		// ----------------------------------------------------------------------------------------------------------------

		// Genre PieChart
		pieChartMovies.setData(helperPieChart.createPieChartMovieGenres(movies, genres));

	}

	@FXML
	public void setListviewMovies() {

		if (comboBoxMovies.getSelectionModel().getSelectedItem().equals("Top 10 - Biggest Size")) {
			listViewMovies.getItems().clear();
			try {
				listViewMovies.setItems(statisticsCalculator.calcTop10MovieBiggestSize());
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else if (comboBoxMovies.getSelectionModel().getSelectedItem().equals("Top 10 - Oldest Movies")) {
			listViewMovies.getItems().clear();
			try {
				listViewMovies.setItems(statisticsCalculator.calcTop10MovieOldestMovies());
			} catch (DataAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
