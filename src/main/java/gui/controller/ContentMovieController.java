package gui.controller;

import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.omertron.themoviedbapi.MovieDbException;

import application.controller.ContentMovieAppController;
import database.domain.Audioline;
import database.domain.Audiolinepos;
import database.domain.Movie;
import gui.util.converter.CollectionConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author fabian
 */
public class ContentMovieController implements Initializable {

	@FXML
	private TableView<Movie> tableMovies;
	@FXML
	private TableColumn<Movie, String> tableColumnTitel;
	@FXML
	private TableColumn<Movie, Integer> tableColumnYear;
	@FXML
	private TableColumn<Movie, Double> tableColumnSize;
	@FXML
	private AnchorPane anchorPaneMain;
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
	private TableView tableAudioLine;
	@FXML
	private TableColumn<Audiolinepos, String> tableColumnAudioLineLanguage;
	@FXML
	private TableColumn<Audiolinepos, String> tableColumnAudioLineFormat;
	@FXML
	private TableColumn<Audiolinepos, Integer> tableColumnAudioLineBitrate;
	@FXML
	private TableColumn<Audiolinepos, String> tableColumnAudioLineChannels;
	@FXML
	private TableColumn<Audiolinepos, Boolean> tableColumnAudioLineDTSMod;
	@FXML
	private TextField textfieldSearch;
	@FXML
	HBox hboxMovieContent;

	private ContentMovieAppController dataHandler;

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		// Load data
		dataHandler = new ContentMovieAppController();
		List<Movie> movies = dataHandler.getAllMovies();
		setMoviesIntoTable(movies);

		// Table movies
		tableColumnTitel.setSortType(TableColumn.SortType.ASCENDING);
		tableColumnTitel.setCellValueFactory(new PropertyValueFactory<Movie, String>("fileName"));
		tableColumnYear.setCellValueFactory(new PropertyValueFactory<Movie, Integer>("year"));
		tableColumnSize.setCellValueFactory(new PropertyValueFactory<Movie, Double>("fileSize"));
		tableMovies.getSortOrder().add(tableColumnTitel);

		// Table audiolines
		tableColumnAudioLineLanguage.setSortType(TableColumn.SortType.ASCENDING);
		tableColumnAudioLineLanguage
				.setCellValueFactory(new PropertyValueFactory<Audiolinepos, String>("audioLanguage"));
		tableColumnAudioLineFormat.setCellValueFactory(new PropertyValueFactory<Audiolinepos, String>("audioFormat"));
		tableColumnAudioLineBitrate
				.setCellValueFactory(new PropertyValueFactory<Audiolinepos, Integer>("audioBitrate"));
		tableColumnAudioLineChannels
				.setCellValueFactory(new PropertyValueFactory<Audiolinepos, String>("audioChannels"));
		tableColumnAudioLineDTSMod.setCellValueFactory(new PropertyValueFactory<Audiolinepos, Boolean>("dtsMod"));

		// add Filter
		textfieldSearch.textProperty().addListener((observable, oldValue, newValue) -> {
			setMoviesIntoTable(dataHandler.getFilteredMovies(textfieldSearch.getText()));
		});
		/*
		 * tableMovies.setOnMouseClicked(new EventHandler<MouseEvent>() {
		 * 
		 * @Override public void handle(MouseEvent event) { if
		 * (event.getClickCount() == 2 || event.getClickCount() == 1) { try {
		 * showSelectedItemData((Movie)
		 * tableMovies.getSelectionModel().getSelectedItem()); } catch
		 * (MovieDbException ex) {
		 * Logger.getLogger(ContentMovieController.class.getName()).log(Level.
		 * SEVERE, null, ex); } } } });
		 */
	}

	private void setMoviesIntoTable(List<Movie> movies) {

		ObservableList<Movie> masterData = FXCollections.observableList(movies);

		// 1. Set FilteredList and add Listeners to all TextFields
		FilteredList<Movie> filteredData = new FilteredList<>(masterData, p -> true);
		// 2. Wrap the FilteredList in a SortedList.
		SortedList<Movie> sortedData = new SortedList<>(filteredData);
		// 3. Bind the SortedList comparator to the TableView comparator.
		sortedData.comparatorProperty().bind(tableMovies.comparatorProperty());
		// 4. Add sorted (and filtered) data to the table.
		tableMovies.setItems(masterData);

		tableMovies.setRowFactory(tv -> {
			TableRow<Movie> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					try {
						showSelectedItemData(tableMovies.getSelectionModel().getSelectedItem());
					} catch (MovieDbException ex) {
						Logger.getLogger(ContentMovieController.class.getName()).log(Level.SEVERE, null, ex);
					}
				}
			});
			return row;
		});
	}

	private void showSelectedItemData(Movie movie) throws MovieDbException {
		labelMovieHeadline.setText(movie.getTmdbinfo().getTitle());
		labelTagline.setText(movie.getTmdbinfo().getTagline());
		imageCover.setImage(new Image(movie.getTmdbinfo().getCoverUrl()));
		textAreaOverview.setText(movie.getTmdbinfo().getOverview());
		labelMovieTitel.setText(movie.getTmdbinfo().getTitle());
		labelYear.setText(String.valueOf(movie.getYear()));
		labelRating.setText(String.valueOf(movie.getTmdbinfo().getRating()));
		labelEdition.setText(movie.getEdition());
		textareaNote.setText(movie.getNote());
		labelTMDBid.setText(String.valueOf(movie.getTmdbinfo().getTmdbId()));
		labelDuration.setText(String.valueOf(movie.getDuration()) + " min");
		labelFileName.setText(movie.getFileName());
		labelFileSize.setText(String.valueOf(movie.getFileSize()));
		labelFormat.setText(movie.getFileFormat());
		labelBitrate.setText(String.valueOf(movie.getVideoline().getVideoBitrate()));
		labelMode.setText(movie.getVideoline().getVideoBitrateMode());
		labelAspectRatio.setText(movie.getVideoline().getAspectRatio());
		labelResolution.setText(
				movie.getVideoline().getResolutionWidth() + " x " + movie.getVideoline().getResolutionHeight());
		labelFrameRate.setText(String.valueOf(movie.getVideoline().getFramerate()));

		if (movie.getRemux()) {
			labelRemux.setSelected(true);
		}

		labelOwner.setText(CollectionConverter.getGenresToString(movie.getGenres()));
		labelGenre.setText(CollectionConverter.getGenresToString(movie.getGenres()));
		initializeAudioLineTable(movie);

		// Order table audioline
		tableAudioLine.getSortOrder().add(tableColumnAudioLineLanguage);

		hboxMovieContent.setVisible(true);
	}

	private void initializeAudioLineTable(Movie movie) {

		List<Audiolinepos> audiolines = new LinkedList();

		for (Audioline l : movie.getAudiolines()) {
			audiolines.add(l.getAudiolinepos());
		}

		ObservableList<Audiolinepos> masterData = FXCollections.observableList(audiolines);

		tableAudioLine.setItems(masterData);

		tableAudioLine.setRowFactory(tv -> {
			TableRow<Movie> row = new TableRow<>();
			row.setOnMouseClicked(event -> {
				if (event.getClickCount() == 1 && (!row.isEmpty())) {
					// TODO:
				}
			});
			return row;
		});
	}

}
