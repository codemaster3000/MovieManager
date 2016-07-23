package blackmediamanager.application.setup;

import blackmediamanager.application.setup.task.LoadTask;

public interface StartNextLoadTaskCallback {
	void invoke(LoadTask nextTask);
}
