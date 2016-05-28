package application.setup;

public abstract class LoadTask implements Runnable {
	
	private String _taskId;
	
	public LoadTask(String taskId) {
		_taskId = taskId;
	}
	
	
	public String getTaskId() {
		return _taskId;
	}
}
