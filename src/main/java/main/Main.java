package main;

import application.helpers.AppConfig;
import java.io.IOException;
import application.setup.ApplicationController;
import application.setup.LoadFinishedCallbackHandler;
import gui.popup.splashscreen.SplashScreen;
import gui.util.GuiServiceRegistry;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.ApplicationServices;
import util.ResourcePathResolver.ImageType;

public class Main extends Application {
	private final static String themeFileName = "theme_default";
	private final static String iconFile = "black_folder-icon";
	private static Stage _primaryStage;
        private AppConfig _cfg = AppConfig.getInstance();

	@Override
	public void start(Stage primaryStage) throws Exception {
		_primaryStage = primaryStage;

		ApplicationController.instance.init();
		SplashScreen splashScreen = new SplashScreen(themeFileName);
		splashScreen.show();
		
		ApplicationController.instance.load(splashScreen, new LoadFinishedCallbackHandler() {
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
		ApplicationController.instance.tearDown();
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
		stage.setTitle(_cfg.APP_NAME + " " + _cfg.APP_VERSION);
                
                try{
                    stage.setMinHeight(Integer.parseInt(_cfg.STARTUP_WINDOWHEIGHT));
                    stage.setMinWidth(Integer.parseInt(_cfg.STARTUP_WINDOWWIDTH));
                } catch (Exception e) {
                    // parse error, use default min width & height
                    System.out.println("App config error: no valid window width & height values");
                    stage.setMinHeight(720);
                    stage.setMinWidth(1000);
                }
                if(_cfg.STARTUP_WINDOWMAXIMIZED.equals("true")){
                    stage.setMaximized(true);
                } else {
                    stage.setMaximized(false);
                }
		stage.centerOnScreen();
		stage.getIcons().add(new Image(ApplicationServices.instance.getResourcePathResolver()
				.resovleIconPath(iconFile, ImageType.PNG).openStream()));
		stage.show();
	}

	public static void main(String[] args) {
		launch(args);
	}
}
