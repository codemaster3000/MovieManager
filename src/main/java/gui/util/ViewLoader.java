package gui.util;

import java.io.IOException;

import gui.controller.ContentMovieController;
import gui.controller.ContentReleasesController;
import gui.controller.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import util.ApplicationServices;

public class ViewLoader {
	
	protected ViewLoader() {
		
	}

	public void Load(Pane parent, String fxmlFileName, Object controller) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();
		
		fxmlLoader.setLocation(ApplicationServices.instance.getResourcePathResolver().resolveFxml(fxmlFileName));
		fxmlLoader.setController(controller);
		
		parent.getChildren().clear();
		parent.getChildren().add(fxmlLoader.load());
	}
	
	public void LoadMainViewController(Pane parent) throws IOException {
		GuiServiceRegistry.instance.getViewLoader().Load(parent, "MainView", new MainViewController());
	}
	
	public void LoadContentMovieView(Pane parent) throws IOException {
		GuiServiceRegistry.instance.getViewLoader().Load(parent, "ContentMovie", new ContentMovieController());
	}
	
	public void LoadContentReleaseView(Pane parent) throws IOException {
		GuiServiceRegistry.instance.getViewLoader().Load(parent, "ContentReleases", new ContentReleasesController());
	}
}
