package blackmediamanager.gui.controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import blackmediamanager.Main;
import blackmediamanager.application.controller.ContentScannerAppController;
import blackmediamanager.application.controller.ContentScannerAppController.UpdateProgressCallback;
import blackmediamanager.application.helpers.AppConfig;
import blackmediamanager.medialibrary.util.StringHelper;
import javafx.application.Platform;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
// TODO(refactor): sysout into log
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

	private ContentScannerAppController contentScannerAppController;

	public ContentScannerController() {
		contentScannerAppController = new ContentScannerAppController();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		labelScanpath.setText("no scanpath selected");
		labelScaninfo.setText("");
		buttonScan.setDisable(true);

		listFiles.itemsProperty().bind(new SimpleListProperty<>(contentScannerAppController.getScannedFiles()));
		listFiles.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

		listNotFound.itemsProperty().bind(new SimpleListProperty<>(contentScannerAppController.getTmdbNotFoundFiles()));
		listNotFound.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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

		Task<Boolean> fileScannerTask = createFileScannerTask();

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

					System.out.println("filescanner task done " + contentScannerAppController.getScannedFiles().size());

					addFileData();
				}
			}
		});

		Platform.runLater(fileScannerTask);
	}

	private void addFileData() {
		Task<Boolean> adddata = createAddFilesToDatabaseTask();

		labelScaninfo.setText("Extract data and add files to database...");
		progressScan.setProgress(0);
		progressScan.progressProperty().unbind();
		progressScan.progressProperty().bind(adddata.progressProperty());

		adddata.stateProperty().addListener(new ChangeListener<Worker.State>() {
			@Override
			public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState,
					Worker.State newState) {
				if (newState == Worker.State.SUCCEEDED) {
					labelScaninfo.setText(
							"Scan done [added files]: " + contentScannerAppController.getScannedFiles().size());
					buttonScan.setDisable(false);

					// TODO(logging)
					System.out.println(
							"adding files to database done " + contentScannerAppController.getScannedFiles().size());
				}
			}
		});

		Platform.runLater(adddata);
	}

	public Task<Boolean> createFileScannerTask() {
		return new Task<Boolean>() {

			@Override
			protected Boolean call() throws Exception {
				System.out.println("Scann files");
				Path dir = Paths.get(labelScanpath.getText());
				try {
					contentScannerAppController.scanDirectory(dir,
							StringHelper.stringTokenize(AppConfig.instance.EXTENSIONS_VIDEO, ","));
				} catch (Exception e) {
					// TODO(logging)
					e.printStackTrace();
					return false;
				}

				return true;
			}

		};
	}

	public Task<Boolean> createAddFilesToDatabaseTask() {
		return new Task<Boolean>() {
			@Override
			protected Boolean call() throws Exception {
				try {
					contentScannerAppController.addFilesToDatabase(new UpdateProgressCallback() {

						@Override
						public void invoke(int workDone, int maxWork) {
							updateProgress(workDone, maxWork);
						}

					});

				} catch (Exception e) {
					// TODO(logging)
					e.printStackTrace();
					return false;
				}
				return true;
			}
		};
	}
}
