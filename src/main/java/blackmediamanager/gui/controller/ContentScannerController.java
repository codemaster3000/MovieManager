package blackmediamanager.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Set;

import blackmediamanager.Main;
import blackmediamanager.application.converter.MediaInfoToMovieConverter;
import blackmediamanager.application.helpers.AppConfig;
import blackmediamanager.database.dao.MovieDao;
import blackmediamanager.database.domain.Movie;
import blackmediamanager.medialibrary.FileScanner;
import blackmediamanager.medialibrary.util.StringHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.SelectionMode;
import javafx.stage.DirectoryChooser;

/**
 * FXML Controller class
 *
 * @author fabian salzgeber
 */
// TODO(refactor): create ContentScannerAppController and refector application
// logic into it
// TODO(refactor): sysout into log
// TODO(refactor): tasks
public class ContentScannerController implements Initializable {

	@FXML
	Button buttonChooseScanpath;
	@FXML
	Label labelScanpath;
	@FXML
	Label labelScaninfo;
	@FXML
	Button buttonScan;
	@FXML
	ProgressBar progressScan;
	@FXML
	ListView<String> listFiles;
	@FXML
	ListView<String> listNotFound;

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		labelScanpath.setText("no scanpath selected");
		labelScaninfo.setText("");
		buttonScan.setDisable(true);
	}

	public void chooseScanpath() {
		DirectoryChooser directoryChooser = new DirectoryChooser();

		File selectedDirectory = directoryChooser.showDialog(Main.getPrimaryStage());

		if (selectedDirectory == null) {
			labelScanpath.setText("No Directory selected");
		} else {
			labelScanpath.setText(selectedDirectory.getAbsolutePath());
			buttonScan.setDisable(false);
		}
	}

	public void scanFolders() throws IOException {
		buttonScan.setDisable(true);
		labelScaninfo.setText("Scanning files...");

		Task<List<String>> fileScannerTask = createFileScannerTask();

		progressScan.setProgress(0);
		progressScan.progressProperty().unbind();
		progressScan.progressProperty().bind(fileScannerTask.progressProperty());

		fileScannerTask.stateProperty().addListener(new ChangeListener<Worker.State>() {
			@Override
			public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState,
					Worker.State newState) {
				if (newState == Worker.State.SUCCEEDED) {
					progressScan.progressProperty().unbind();
					progressScan.setProgress(0);

					List<String> fileNames = fileScannerTask.getValue();

					listFiles.setItems(FXCollections.observableList(fileNames));
					listFiles.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
					System.out.println("filescanner task done " + fileNames.size());

					addFileData(fileNames);
				}
			}
		});

		new Thread(fileScannerTask).start();
	}

	public void addFileData(Collection<String> fileNames) {
		Task<List<String>> adddata = createAddFilesToDatabaseTask(fileNames);

		labelScaninfo.setText("Extract data and add files to database...");
		progressScan.setProgress(0);
		progressScan.progressProperty().unbind();
		progressScan.progressProperty().bind(adddata.progressProperty());

		adddata.stateProperty().addListener(new ChangeListener<Worker.State>() {
			@Override
			public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState,
					Worker.State newState) {
				if (newState == Worker.State.SUCCEEDED) {
					labelScaninfo.setText("Scan done [added files]: " + fileNames.size());
					buttonScan.setDisable(false);

					listNotFound.setItems(FXCollections.observableArrayList(adddata.getValue()));
					listNotFound.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
					System.out.println("adding files to database done " + fileNames.size());
				}
			}
		});

		new Thread(adddata).start();
	}

	public Task<List<String>> createFileScannerTask() {
		return new Task<List<String>>() {
			@Override
			protected List<String> call() throws Exception {
				Path dir = Paths.get(labelScanpath.getText());
				return scanDirectory(dir, StringHelper.stringTokenize(AppConfig.instance.EXTENSIONS_VIDEO, ","));
			}
		};
	}

	private List<String> scanDirectory(Path dir, Set<String> extensions) throws IOException {
		return FileScanner.scanForMovieFiles(dir, extensions);
	}

	public Task<List<String>> createAddFilesToDatabaseTask(Collection<String> fileNames) {
		return new Task<List<String>>() {
			@Override
			protected List<String> call() throws Exception {
				MovieDao movieDao = new MovieDao();
				List<String> tmdbNotFound = new LinkedList<>();
				if (fileNames.size() > 0) {
					int i = 0;
					for (String filename : fileNames) {
						File file = new File(filename);

						Movie foundMovie = MediaInfoToMovieConverter.convert(file);

						if (foundMovie == null) {
							tmdbNotFound.add(file.getName());
						} else {
							movieDao.save(foundMovie);
						}

						updateProgress(i++, fileNames.size() - 1);
					}
				}

				return tmdbNotFound;
			}
		};
	}
}
