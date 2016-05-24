package main;

import application.setup.LoadStateCallbackHandler;
import application.setup.LoadTask;

import java.io.IOException;

import application.setup.ApplicationController;
import gui.util.GuiServiceRegistry;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.ApplicationServices;
import util.ResourcePathResolver;
import util.ResourcePathResolver.ImageType;

public class Main extends Application {
    private final static String appName = "Black Movie Manager";
    private final static String themeFileName = "theme_default";
    private final static String iconFile = "black_folder-icon";
    
    private static Stage _primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
    	_primaryStage = primaryStage;
    	
    	ApplicationController.instance.init();
    	//replace with handler from splash screen controller
    	ApplicationController.instance.load(new LoadStateCallbackHandler() {
			
			@Override
			public void loadTaskFinished(LoadTask loadTask, int percent) {
				System.out.println("Finished: " + loadTask.getTaskId() + ", " + percent);
			}
			
			@Override
			public void allLoadTaskFinished() {
				System.out.println("Everyting was Loaded");
			}
		});
    	
    	showMainWindow();
    	
        DebugHandler.show();
    }

    @Override
    public void stop() throws Exception {
    	ApplicationController.instance.tearDown();
    	super.stop();
    }
    
    public static Stage getPrimaryStage() {
        return _primaryStage;
    }
    
    public static void showMainWindow() throws IOException {  
        Scene scene = new Scene(GuiServiceRegistry.instance.getViewLoader().LoadMainViewController());
        scene.getStylesheets().add(ApplicationServices.instance.getResourcePathResolver().resolveCssStyle(themeFileName).toExternalForm());
        _primaryStage.setScene(scene);
        _primaryStage.setTitle(appName);
        _primaryStage.setMinHeight(650);
        _primaryStage.setMinWidth(1000);
        _primaryStage.setMaximized(false);
        _primaryStage.getIcons().add(new Image(ApplicationServices.instance.getResourcePathResolver().resovleIconPath(iconFile, ImageType.PNG).openStream()));   
        _primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
