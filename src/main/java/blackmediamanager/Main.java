package blackmediamanager;

import java.io.IOException;

import blackmediamanager.application.setup.ApplicationSetup;
import blackmediamanager.application.setup.LoadFinishedCallbackHandler;
import blackmediamanager.gui.popup.splashscreen.SplashScreen;
import blackmediamanager.gui.util.GuiServiceRegistry;
import blackmediamanager.util.ApplicationServices;
import blackmediamanager.util.ResourcePathResolver.ImageType;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
	private final static String appName = "Black Movie Manager";
	private final static String themeFileName = "theme_default";
	private final static String iconFile = "black_folder-icon";
	private static Stage _primaryStage;

	@Override
	public void start(Stage primaryStage) throws Exception {
		_primaryStage = primaryStage;

		ApplicationSetup.instance.init();
		SplashScreen splashScreen = new SplashScreen(themeFileName);
		splashScreen.show();
		
		ApplicationSetup.instance.load(splashScreen, new LoadFinishedCallbackHandler() {
			@Override
			public void allLoadTaskFinished() {
				Platform.runLater(new Runnable() {	
					@Override
					public void run() {
						try {
							showMainWindow(_primaryStage);
		                    splashScreen.close();
							//DebugHandler.show();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
	}

	@Override
	public void stop() throws Exception {
		ApplicationSetup.instance.tearDown();
		super.stop();
	}

	// TODO: find better solution
	public static Stage getPrimaryStage() {
		return _primaryStage;
	}

	// TODO: refactor into own class
	public void showMainWindow(Stage stage) throws IOException {
		Scene scene = new Scene(GuiServiceRegistry.instance.getViewLoader().LoadMainViewController());
		scene.getStylesheets().add(
				ApplicationServices.instance.getResourcePathResolver().resolveCssStyle(themeFileName).toExternalForm());

		stage.initStyle(StageStyle.DECORATED);
		stage.setScene(scene);
		stage.setTitle(appName);
		stage.setMinHeight(650);
		stage.setMinWidth(1000);
		stage.setMaximized(false);
		stage.centerOnScreen();
		stage.getIcons().add(new Image(ApplicationServices.instance.getResourcePathResolver()
				.resovleIconPath(iconFile, ImageType.PNG).openStream()));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
