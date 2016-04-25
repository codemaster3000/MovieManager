
import Controller.MainViewController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("view/MainView.fxml"));
        primaryStage.setScene(new Scene((Parent) fxmlLoader.load()));
        primaryStage.setTitle("Movie Manager");
        primaryStage.setMaximized(true);
        //primaryStage.getIcons().add(new Image(Main.class.getResourceAsStream("icon.png")));   --> icon
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
