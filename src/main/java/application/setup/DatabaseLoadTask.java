package application.setup;

public class DatabaseLoadTask extends LoadTask {

	public DatabaseLoadTask() {
		super(DatabaseLoadTask.class.getName());
	}

	@Override
	public void run() {
		//TODO: load from database
		System.out.println("Currently no functionality implemented!");
	}

}
