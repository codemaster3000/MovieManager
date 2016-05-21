package main;

import gui.util.GuiServiceRegistry;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import util.ApplicationServices;
import util.ResourcePathResolver.ImageType;

public class Main extends Application {

    private static Stage _primaryStage;
    
    private final String appName = "Black Movie Manager";
    
    private final String themeFileName = "theme_default";
    private final String iconFile = "black_folder-icon";
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        _primaryStage = primaryStage;
        
        /*Pane pane = new Pane();       
        Scene scene = new Scene(pane);
        GuiServiceRegistry.instance.getViewLoader().LoadMainViewController(pane);
        scene.getStylesheets().add(ApplicationServices.instance.getResourcePathResolver().resolveCssStyle(themeFileName).toExternalForm());
        */
        
        Scene scene = new Scene(GuiServiceRegistry.instance.getViewLoader().LoadMainViewController());
        scene.getStylesheets().add(ApplicationServices.instance.getResourcePathResolver().resolveCssStyle(themeFileName).toExternalForm());
        _primaryStage.setScene(scene);
        _primaryStage.setTitle(appName);
        _primaryStage.setMinHeight(650);
        _primaryStage.setMinWidth(1000);
        _primaryStage.setMaximized(false);
        _primaryStage.getIcons().add(new Image(ApplicationServices.instance.getResourcePathResolver().resovleIconPath(iconFile, ImageType.PNG).openStream()));   
        _primaryStage.show();

        // debug console outputs
        @SuppressWarnings("unused")
        DebugStuff stuff = new DebugStuff(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public static Stage getPrimaryStage() {
        return _primaryStage;
    }
}
