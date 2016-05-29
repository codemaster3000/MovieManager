package blackmediamanager.gui.util;

import java.io.IOException;

import blackmediamanager.gui.controller.ContentMovieController;
import blackmediamanager.gui.controller.ContentReleasesController;
import blackmediamanager.gui.controller.ContentScannerController;
import blackmediamanager.gui.controller.ContentSettingsController;
import blackmediamanager.gui.controller.ContentStatisticsController;
import blackmediamanager.gui.controller.ContentTVController;
import blackmediamanager.gui.controller.MainViewController;
import blackmediamanager.util.ApplicationServices;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;

public class ViewLoader {

	protected ViewLoader() {

	}

	public void Load(Pane parent, String fxmlFileName, Object controller) throws IOException {
		parent.getChildren().clear();
		parent.getChildren().add(Load(fxmlFileName, controller));
	}

	public <T> T Load(String fxmlFileName, Object controller) throws IOException {
		FXMLLoader fxmlLoader = new FXMLLoader();

		fxmlLoader.setLocation(ApplicationServices.instance.getResourcePathResolver().resolveFxml(fxmlFileName));
		fxmlLoader.setController(controller);

		return fxmlLoader.load();
	}

	public Pane LoadMainViewController() throws IOException {
		return Load("MainView", new MainViewController());
	}

	public void LoadMainViewController(Pane parent) throws IOException {
		Load(parent, "MainView", new MainViewController());
	}

	public void LoadContentMovieView(Pane parent) throws IOException {
		Load(parent, "ContentMovie", new ContentMovieController());
	}

	public void LoadContentReleaseView(Pane parent) throws IOException {
		Load(parent, "ContentReleases", new ContentReleasesController());
	}

	public void LoadContentStatisticsView(Pane parent) throws IOException {
		Load(parent, "ContentStatistics", new ContentStatisticsController());
	}

	public void LoadContentSettingsView(Pane parent) throws IOException {
		Load(parent, "ContentSettings", new ContentSettingsController());
	}

	public void LoadContentTVView(Pane parent) throws IOException {
		Load(parent, "ContentTV", new ContentTVController());
	}

	public void LoadContentScannerView(Pane parent) throws IOException {
		Load(parent, "ContentScanner", new ContentScannerController());
	}
}
