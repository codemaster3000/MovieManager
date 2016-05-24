package application.setup;

public interface LoadStateCallbackHandler {
	void loadTaskFinished(LoadTask loadTask, int percent);
	
	void allLoadTaskFinished();
}

