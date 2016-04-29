
import gui.controller.MainViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("gui/presentation/MainView.fxml"));
        primaryStage.setScene(new Scene((Parent) fxmlLoader.load()));
        primaryStage.setTitle("Movie Manager");
        primaryStage.setMaximized(true);
        primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("gui/presentation/icons/black_folder-icon.png")));   
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
