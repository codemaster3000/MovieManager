
import gui.controller.MainViewController;
import java.io.IOException;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import services.xrelinfo.XRelInfo;

public class Main extends Application {

    private Stage _primaryStage;
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        _primaryStage = primaryStage;
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui/presentation/MainView.fxml"));
        _primaryStage.setScene(new Scene((Parent) fxmlLoader.load()));
        _primaryStage.setTitle("Movie Manager");
        _primaryStage.setMaximized(true);
        _primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("gui/presentation/icons/black_folder-icon.png")));   
        _primaryStage.show();

        // debug console outputs
        testOutput();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
    public Stage getPrimaryStage() {
        return _primaryStage;
    }
    
    public static void testOutput() throws IOException, Exception{
        // for debug use only
        XRelInfo xrel = new XRelInfo();
        xrel.testApi();
    }
}
