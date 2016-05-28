package gui.controller;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import database.domain.Genre;
import database.domain.Genrepos;
import database.domain.Movie;
import database.persistance.DBFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author fabian
 */
public class ContentStatisticsController implements Initializable {

	@FXML
	private PieChart chartMovies;
	@FXML
	private VBox vboxForLabels;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		List<Movie> movies = DBFacade.instance.getAllMovies();
		List<Genrepos> genres = DBFacade.instance.getAllGenrePoses();

		// pie chart test
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for (int i = 0; i < genres.size(); i++) {
			String genre = genres.get(i).getType();
			int counter = calcGenresFromAllMovies(genre, movies);
			if (counter > 0) {
				pieChartData.add(new PieChart.Data(genre, counter));

				// create Label
				Label l = new Label();
				l.setPadding(new Insets(5, 0, 0, 20));
				l.setStyle("-fx-font: 13 arial; -fx-text-fill: rgb(74, 75, 78);");
				l.setText(genre + ": " + counter);
				vboxForLabels.getChildren().add(l);
			}
		}

		PieChart chart = new PieChart(pieChartData);
		setMouseEvent(chart);
		chartMovies.setData(pieChartData);

	}

	private int calcGenresFromAllMovies(String genre, List<Movie> movies) {

		int counter = 0;

		for (int i = 0; i < movies.size(); i++) {
			Set<Genre> set = movies.get(i).getGenres();
			for (Genre g : set) {
				if (g.getGenrepos().getType().equals(genre)) {
					counter++;
				}
			}

		}
		return counter;
	}

	public void setMouseEvent(PieChart chart) {
		final Label caption = new Label("");
		caption.setStyle("-fx-font: 15 arial; -fx-text-fill: rgb(74, 75, 78); -fx-font-weight: bold;");
		for (final PieChart.Data data : chart.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent e) {
					caption.setTranslateX(e.getSceneX());
					caption.setTranslateY(e.getSceneY());
					System.out.println(data.getPieValue());
					caption.setText(String.valueOf(data.getPieValue()) + "%");
				}
			});
		}
	}

}
