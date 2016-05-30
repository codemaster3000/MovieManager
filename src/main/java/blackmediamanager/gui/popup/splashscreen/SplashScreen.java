package blackmediamanager.gui.popup.splashscreen;

import java.io.IOException;

import blackmediamanager.application.setup.LoadStateCallbackHandler;
import blackmediamanager.application.setup.task.LoadTask;
import blackmediamanager.gui.util.GuiServiceRegistry;
import blackmediamanager.util.ApplicationServices;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.Separator;
import javafx.scene.effect.Bloom;
import javafx.scene.effect.GaussianBlur;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SplashScreen implements LoadStateCallbackHandler {
	@FXML
	private Label currentlyLoadingLable;
	@FXML
	private Label labelTitle;
	@FXML
	private AnchorPane paneBack;
	@FXML
	private Separator splashSeparator;
	@FXML
	private BorderPane paneMain;

	@FXML
	private ProgressIndicator loadProcessIndicator;

	private Stage _stage;
	private String _styleFileName;

	@Override
	public void loadTaskFinished(LoadTask loadTask, double percent) {
		Platform.runLater(new Runnable() {
			
			@Override
			public void run() {
				currentlyLoadingLable.setText(loadTask.getTaskId());
			}
		});
	}

	public SplashScreen(String styleFileName) {
		_stage = new Stage();
		_styleFileName = styleFileName;
	}

	public void show() throws IOException {
		Scene scene = new Scene(GuiServiceRegistry.instance.getViewLoader().Load("SplashScreen", this));
		scene.getStylesheets().add(ApplicationServices.instance.getResourcePathResolver()
				.resolveCssStyle(_styleFileName).toExternalForm());

		_stage = new Stage(StageStyle.UNDECORATED);
		_stage.setScene(scene);
		_stage.centerOnScreen();
		
		setupStyle();
		_stage.show();
	}

	private void setupStyle() {
		paneMain.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		splashSeparator
				.setBackground(new Background(new BackgroundFill(Color.WHITESMOKE, CornerRadii.EMPTY, Insets.EMPTY)));
		currentlyLoadingLable.setText("Loading...");
		currentlyLoadingLable.setEffect(new Bloom());
		splashSeparator.setEffect(new GaussianBlur());
	}

	public void close() {
		_stage.close();
	}
}
