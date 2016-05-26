package gui.popup.splashscreen;

import java.io.IOException;

import application.setup.LoadStateCallbackHandler;
import application.setup.LoadTask;
import gui.util.GuiServiceRegistry;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.ApplicationServices;

public class SplashScreen implements LoadStateCallbackHandler{
    @FXML
    private Label currentlyLoadingLable;

    @FXML
    private ProgressIndicator loadProcessIndicator;
	
	private Stage _stage;
	private String _styleFileName;
	

	@Override
	public void loadTaskFinished(LoadTask loadTask, double percent) {
		currentlyLoadingLable.setText(loadTask.getTaskId());
	}

	public SplashScreen(String styleFileName) {
		_stage = new Stage();
		_styleFileName = styleFileName;
	}

	public void show() throws IOException {
		Scene scene = new Scene(
				GuiServiceRegistry.instance.getViewLoader().Load("SplashScreen", this));
		scene.getStylesheets().add(
				ApplicationServices.instance.getResourcePathResolver().resolveCssStyle(_styleFileName).toExternalForm());

		_stage = new Stage(StageStyle.UNDECORATED);
		_stage.setScene(scene);
		_stage.centerOnScreen();
		_stage.show();
	}
	
	public void close() {
		_stage.close();
	}
}
