package application;

public class StartupController {
	
	public enum ApplicationState {
		Unitialized,
		Initialized,
		Loaded,
		Closing
	}
	
	public interface LoadStateCallbackHandler {
		void percentageLoaded(int percent);
		
		void resourceLoaed(String ressourceName);
	}
	
	private ApplicationState _applicationState;
	
	public StartupController() {
		_applicationState = ApplicationState.Unitialized;
	}
	
	public void init() {
		if(_applicationState == ApplicationState.Unitialized) {
			// initialize dependencies
			
			_applicationState = ApplicationState.Initialized;
		}	
	}
	
	public void load() {
		if(_applicationState == ApplicationState.Initialized) {
			//load files from db etz.
			
			_applicationState = ApplicationState.Loaded;
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
