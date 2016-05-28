package gui.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.controller.ContentReleaseAppController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
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
	public ListView<String> listNewReleases;

	private int pos = 0;
	private final int minPos = 0;
	private final int maxPos = (50 * 160);

	private ObservableList<String> xrelList;

	private ContentReleaseAppController contentReleaseAppController;

	@FXML
	public void setPaneSelected(Event e) {
		((Pane) e.getSource()).getChildren().get(0).getStyleClass().add("labelSelected");
	}

	public void setPaneUnselected(Event e) {
		((Pane) e.getSource()).getChildren().get(0).getStyleClass().remove("labelSelected");
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {
		showNewReleases();
	}

	private void showNewReleases() {
		xrelList = FXCollections.observableArrayList();

		XRlatest latest = contentReleaseAppController.getNewReleases();
		for (int i = 0; i < latest.getList().size(); i++) {
			xrelList.add(latest.getList().get(i).getDirname());
		}

		System.out.print(latest.getTotalCount());

		listNewReleases.setItems(xrelList);
		listNewReleases.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
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

	public Task<?> createFetchCoversTask() {
		return new Task<Object>() {
			@Override
			protected Object call() throws Exception {
				// cover code here
				return true;
			}
		};
	}

}
