package blackmediamanager.application.setup;

import blackmediamanager.application.setup.task.LoadTask;

public interface LoadStateCallbackHandler {
	void loadTaskFinished(LoadTask loadTask, double percent);
}

