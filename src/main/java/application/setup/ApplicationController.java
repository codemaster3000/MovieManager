package application.setup;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Platform;
import util.ResourcePathResolver;

public class ApplicationController {
	private ApplicationState _applicationState;
	
	private List<LoadTask> _loadTasks;
	
	public static final ApplicationController instance = new ApplicationController();
	
	private ApplicationController() {
		_applicationState = ApplicationState.Unitialized;
		_loadTasks = new ArrayList<>();
	}
	
	public void init() {
		if(_applicationState == ApplicationState.Unitialized) {
			_loadTasks.add(new DatabaseLoadTask());
			_loadTasks.add(new XrelLoadTask());
			
			// initialize dependencies
			 ClassLoader classLoader = ResourcePathResolver.class.getClassLoader();
			 System.setProperty("jna.library.path", classLoader.getResource("lib/").getPath());
			
			_applicationState = ApplicationState.Initialized;
		}	
	}
	
	public void load(LoadStateCallbackHandler loadStateCallback, LoadFinishedCallbackHandler loadFinishedCallback) {
		if(_applicationState == ApplicationState.Initialized) {
			Platform.runLater(new Runnable() {			
				@Override
				public void run() {
					int loadTaskExecutedCount = 0;
					for(LoadTask currentLoadTask : _loadTasks) {
						currentLoadTask.run();
						++loadTaskExecutedCount;
						
						double percentageLoaded = (1.0 / _loadTasks.size())* loadTaskExecutedCount;
						
						loadStateCallback.loadTaskFinished(currentLoadTask, percentageLoaded); 
					}
					
					loadFinishedCallback.allLoadTaskFinished();
					
					_applicationState = ApplicationState.Loaded;
				}
			});
		}
	}
	
	public void tearDown() {
		if(_applicationState == ApplicationState.Loaded) {
			//free resources
			//teardown connections
			_applicationState = ApplicationState.Unitialized;
		}
	}
	
	
}
