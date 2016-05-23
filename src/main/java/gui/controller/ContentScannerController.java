package gui.controller;

import application.FileScanner;
import application.MediaAdder;
import main.Main;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    ListView listFiles;

    ArrayList<String> fileNames;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        fileNames = new ArrayList<String>();
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

        Task scanner = doFilescanning();

        progressScan.setProgress(0);
        progressScan.progressProperty().unbind();
        progressScan.progressProperty().bind(scanner.progressProperty());

        scanner.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    progressScan.progressProperty().unbind();
                    progressScan.setProgress(0);
                   
                    listFiles.setItems(FXCollections.observableArrayList(fileNames));
                    listFiles.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                    System.out.println("filescanner task done " + fileNames.size());
                    // task done
                    addFileData();
                }
            }
        });

        new Thread(scanner).start();
    }
    
    public void addFileData(){
        Task adddata = doAddFilesToDatabase();
        
        labelScaninfo.setText("Extract data and add files to database...");
        progressScan.setProgress(0);
        progressScan.progressProperty().unbind();
        progressScan.progressProperty().bind(adddata.progressProperty());
        
        
        adddata.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    labelScaninfo.setText("Scan done [added files]: " + fileNames.size());
                    buttonScan.setDisable(false);
                    //progressScan.progressProperty().unbind();
                    //progressScan.setProgress(0);
                   
                    System.out.println("adding files to database done " + fileNames.size());
                    // task done
                }
            }
        });

        new Thread(adddata).start();
    }

    public Task doFilescanning() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                FileScanner fh = new FileScanner();
                Path dir = Paths.get(labelScanpath.getText());
                fileNames = fh.getScannedFileList(fileNames, dir);
                return true;
            }
        };
    }

    public Task doAddFilesToDatabase() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                MediaAdder adder = new MediaAdder();

                if (fileNames.size() > 0) {
                    int i = 0;
                    for (String filename : fileNames) {
                        File f = new File(filename);
                        adder.movieToAdd(f);
                        updateProgress(i++, fileNames.size() - 1);
                    }
                }

                return true;
            }
        };
    }
}
