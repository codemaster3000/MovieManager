package gui.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.concurrent.Worker;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import services.xrelinfo.XRelInfo;
import services.xrelinfo.jsondata.latest.XRlatest;

/**
 * FXML Controller class
 *
 * @author Fabian Ladurner
 */
public class ContentReleasesController implements Initializable {

    @FXML
    public AnchorPane anchorPaneContent;
    @FXML
    public ImageView imageCover;
    @FXML
    public AnchorPane paneMovieMenu;
    @FXML
    public BorderPane borderPaneForContent;
    @FXML
    public FlowPane flowpane;
    @FXML
    public ScrollPane scrollPane;
    @FXML
    public ListView listNewReleases;

    private ObservableList<String> xrelList;

    @FXML
    public void setPaneSelected(Event e) {
        ((Pane) e.getSource()).getChildren().get(0).getStyleClass().add("labelSelected");
    }

    public void setPaneUnselected(Event e) {
        ((Pane) e.getSource()).getChildren().get(0).getStyleClass().remove("labelSelected");
    }

    int pos = 0;
    final int minPos = 0;
    final int maxPos = (50 * 160);

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initCoverFlow();
            showNewReleases();
        } catch (IOException ex) {
            Logger.getLogger(ContentReleasesController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void initCoverFlow() {
        ImageView imageCover;
        Pane pane;

        borderPaneForContent.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {

                if (event.getDeltaY() > 0) {
                    scrollPane.setHvalue(pos == minPos ? minPos : pos--);
                } else {
                    scrollPane.setHvalue(pos == maxPos ? maxPos : pos++);
                }

            }
        });

        scrollPane.setHmin(minPos);
        scrollPane.setHmax(maxPos / 25);
        scrollPane.setPannable(true);
        scrollPane.setFitToHeight(true);
        scrollPane.setContent(flowpane);

        for (int i = 0; i < 10; i++) {
            imageCover = new ImageView();
            pane = new Pane();
            pane.setPadding(new Insets(0, 5, 0, 5));
            String imageSource = "https://image.tmdb.org/t/p/w396/vMlVYL15WMlvnkuGdnuEb5uEck7.jpg";
            imageCover.setImage(new Image(imageSource));
            imageCover.setFitWidth(140);
            imageCover.setFitHeight(210);

            pane.getChildren().add(imageCover);

            flowpane.setPrefWidth(50 * 150);
            flowpane.getChildren().add(pane);
        }
    }

    private void showNewReleases() throws IOException {
        Task xrelservice = doFetchNewReleases();
        
        xrelservice.stateProperty().addListener(new ChangeListener<Worker.State>() {
            @Override
            public void changed(ObservableValue<? extends Worker.State> observableValue, Worker.State oldState, Worker.State newState) {
                if (newState == Worker.State.SUCCEEDED) {
                    listNewReleases.setItems(xrelList);
                    listNewReleases.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                    System.out.println("xrelxervice task done");
                    // task done
                }
            }
        });

        new Thread(xrelservice).start();
    }

    public Task doFetchNewReleases() {
        return new Task() {
            @Override
            protected Object call() throws Exception {
                xrelList = FXCollections.observableArrayList();
                XRelInfo xrel = new XRelInfo();

                XRlatest latest = xrel.getLatestHDMovieReleases("HDTV", "movie", 5);
                for (int i = 0; i < latest.getList().size(); i++) {
                    xrelList.add(latest.getList().get(i).getDirname());
                }

                return true;
            }
        };
    }
    
    public Task doFetchCovers(){
        return new Task() {
            @Override
            protected Object call() throws Exception {
                // cover code here
                return true;
            }
        };
    }

}
