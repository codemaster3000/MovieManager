package gui.util;

import java.io.IOException;

import org.hibernate.id.GUIDGenerator;

import gui.controller.ContentMovieController;
import gui.controller.ContentReleasesController;
import gui.controller.ContentSettingsController;
import gui.controller.ContentStatisticsController;
import gui.controller.ContentTVController;
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
	
	public void LoadContentStatisticsView(Pane parent) throws IOException {
		GuiServiceRegistry.instance.getViewLoader().Load(parent, "ContentStatistics", new ContentStatisticsController());
	}
	
	public void LoadContentSettingsView(Pane parent) throws IOException {
		GuiServiceRegistry.instance.getViewLoader().Load(parent, "ContentSettings", new ContentSettingsController());
	}
	
	public void LoadContentTVView(Pane parent) throws IOException {
		GuiServiceRegistry.instance.getViewLoader().Load(parent, "ContentTV", new ContentTVController());
	}
}
