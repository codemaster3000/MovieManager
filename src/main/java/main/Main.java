package main;

import java.io.IOException;

import com.omertron.themoviedbapi.MovieDbException;

import application.setup.ApplicationController;
import application.setup.LoadFinishedCallbackHandler;
import gui.controller.SplashScreenController;
import gui.util.GuiServiceRegistry;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.ApplicationServices;
import util.ResourcePathResolver.ImageType;

public class Main extends Application {
    private final static String appName = "Black Movie Manager";
    private final static String themeFileName = "theme_default";
    private final static String iconFile = "black_folder-icon";
    
    private static Stage _primaryStage;
    private Stage _splashScreenStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	_primaryStage = primaryStage;
    	
    	ApplicationController.instance.init();
    	
    	SplashScreenController splashScreenController = new SplashScreenController();
    	
    	showSplashWindow(splashScreenController);
    	ApplicationController.instance.load(splashScreenController, new LoadFinishedCallbackHandler() {		
			@Override
			public void allLoadTaskFinished() {
				/*try {
					_splashScreenStage.close();
					showMainWindow(_primaryStage);
					DebugHandler.show();
				} catch (IOException | MovieDbException e) {
					e.printStackTrace();
				} */ 
			}
		});
    }

    @Override
    public void stop() throws Exception {
    	ApplicationController.instance.tearDown();
    	super.stop();
    }
    
    //TODO: find better solution
    public static Stage getPrimaryStage() {
        return _primaryStage;
    }
    
    public void showMainWindow(Stage stage) throws IOException {  
        Scene scene = new Scene(GuiServiceRegistry.instance.getViewLoader().LoadMainViewController());
        scene.getStylesheets().add(ApplicationServices.instance.getResourcePathResolver().resolveCssStyle(themeFileName).toExternalForm());
        
        stage.initStyle(StageStyle.DECORATED);
        stage.setScene(scene);
        stage.setTitle(appName);
        stage.setMinHeight(650);
        stage.setMinWidth(1000);
        stage.setMaximized(false);
        stage.centerOnScreen();
        stage.getIcons().add(new Image(ApplicationServices.instance.getResourcePathResolver().resovleIconPath(iconFile, ImageType.PNG).openStream()));   
        stage.show();
    }
    
    public void showSplashWindow(SplashScreenController splashScreenController) throws IOException  {
    	Scene scene = new Scene(GuiServiceRegistry.instance.getViewLoader().Load("SplashScreen", splashScreenController));
        scene.getStylesheets().add(ApplicationServices.instance.getResourcePathResolver().resolveCssStyle(themeFileName).toExternalForm());
        
        _splashScreenStage = new Stage(StageStyle.UNDECORATED);
        _splashScreenStage.setScene(scene);
        _splashScreenStage.centerOnScreen();     
        _splashScreenStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
