import gui.util.ResourcePathResolver;
import gui.util.ResourcePathResolver.ImageType;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

    private Stage _primaryStage;

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        _primaryStage = primaryStage;
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(ResourcePathResolver.resolveFxml("MainView"));
        Scene scene = new Scene((Parent) fxmlLoader.load());
        scene.getStylesheets().add(ResourcePathResolver.resolveCssStyle("theme_default").toExternalForm());
        _primaryStage.setScene(scene);
        _primaryStage.setTitle("Black Movie Manager");
        _primaryStage.setMinHeight(650);
        _primaryStage.setMinWidth(1000);
        _primaryStage.setMaximized(false);
        _primaryStage.getIcons().add(new Image(ResourcePathResolver.resovleIconPath("black_folder-icon", ImageType.PNG).openStream()));   
        _primaryStage.show();

        // debug console outputs
        DebugStuff stuff = new DebugStuff(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public Stage getPrimaryStage() {
        return _primaryStage;
    }
}
