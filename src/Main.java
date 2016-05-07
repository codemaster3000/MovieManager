import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

    private Stage _primaryStage;
    
    //private String usedStyle = "theme_default.css"
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        _primaryStage = primaryStage;
        
        //FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui/presentation/MainView.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui/presentation/MainViewTests.fxml"));
        Scene scene = new Scene((Parent) fxmlLoader.load());
        scene.getStylesheets().add(getClass().getResource("/styles/theme_default.css").toExternalForm());
        _primaryStage.setScene(scene);
        _primaryStage.setTitle("Black Movie Manager");
        _primaryStage.setMaximized(false);
        _primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("gui/presentation/icons/black_folder-icon.png")));   
        _primaryStage.show();

        // debug console outputs
        TestStuff stuff = new TestStuff(false);
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public Stage getPrimaryStage() {
        return _primaryStage;
    }
}
