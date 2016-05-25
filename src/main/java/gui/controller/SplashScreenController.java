package gui.controller;

import application.setup.LoadStateCallbackHandler;
import application.setup.LoadTask;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;

public class SplashScreenController implements LoadStateCallbackHandler {

    @FXML
    private Label currentlyLoadingLoading;

    @FXML
    private ProgressIndicator loadProcessIndicator;

	@Override
	public void loadTaskFinished(LoadTask loadTask, double percent) {
		System.out.println(percent);
		currentlyLoadingLoading.setText(loadTask.getTaskId());
		//loadProcessIndicator.setProgress(percent);
	}
}
