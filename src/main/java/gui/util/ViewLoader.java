package gui.util;

import java.io.IOException;
import java.util.HashMap;

import gui.controller.ContentMovieController;
import gui.controller.ContentReleasesController;
import gui.controller.ContentSettingsController;
import gui.controller.ContentStatisticsController;
import gui.controller.ContentTVController;
import gui.controller.MainViewController;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.Pane;
import util.ApplicationServices;
import util.pattern.FactoryMethod;

public class ViewLoader {
	
	protected ViewLoader() {
		//Todo: remove, is just for testing
		addLoadConfiguration("MainView", "MainView", new MainViewController());
	}
	
	
	public void Load(Pane parent, String fxmlFileName, Object controller) throws IOException {
		parent.getChildren().clear();
		parent.getChildren().add(Load(fxmlFileName, controller));
	}
	
	public <T> T Load(String fxmlFileName, Object controller) throws IOException{
		FXMLLoader fxmlLoader = new FXMLLoader();
		
		fxmlLoader.setLocation(ApplicationServices.instance.getResourcePathResolver().resolveFxml(fxmlFileName));
		fxmlLoader.setController(controller);
		
		return fxmlLoader.load();
	}
	
	private class LoadConfiguration {
		private String _fileName;
		private FactoryMethod _factoryMethod;
		
		public LoadConfiguration(String fileName, FactoryMethod factoryMethod) {
			_fileName = fileName;
			_factoryMethod = factoryMethod;
		}
		
		public String getFileName() {
			return _fileName;
		}
		
		public Object getController() {
			return _factoryMethod.create();
		}	
	}
	
	private HashMap<String, LoadConfiguration> _loadConfigurations;
	
	public void addLoadConfiguration(String identifier, String fileName, FactoryMethod factoryMethod) {
		if(_loadConfigurations == null) {
			_loadConfigurations = new HashMap<>();
		}
		
		_loadConfigurations.put(identifier, new LoadConfiguration(fileName, factoryMethod));
	}
	
	public Pane LoadViewController(String identifier) throws IOException {
		LoadConfiguration loadConfig = _loadConfigurations.get(identifier);
		
		return Load(loadConfig.getFileName(), loadConfig.getController());
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
}
