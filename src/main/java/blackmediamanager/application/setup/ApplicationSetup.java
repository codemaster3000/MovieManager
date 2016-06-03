package blackmediamanager.application.setup;

import java.util.ArrayList;
import java.util.List;

import com.omertron.themoviedbapi.MovieDbException;

import blackmediamanager.application.setup.task.DebugHandleLoadTask;
import blackmediamanager.application.setup.task.LoadTask;
import blackmediamanager.application.setup.task.TaskDatabaseLoad;
import blackmediamanager.application.setup.task.TaskXRel;
import blackmediamanager.scrapers.tmdbinfo.TmdbInfo;
import blackmediamanager.util.ResourcePathResolver;

public class ApplicationSetup {
	private ApplicationState _applicationState;

	private List<LoadTask> _loadTasks;

	public static final ApplicationSetup instance = new ApplicationSetup();

	private ApplicationSetup() {
		_applicationState = ApplicationState.Unitialized;
		_loadTasks = new ArrayList<>();
	}

	public void init() {
		if (_applicationState == ApplicationState.Unitialized) {
			// initialize load tasks
			_loadTasks.add(new TaskDatabaseLoad());
			_loadTasks.add(new TaskXRel());
			_loadTasks.add(new DebugHandleLoadTask());

			// initialize dependencies
			ClassLoader classLoader = ResourcePathResolver.class.getClassLoader();
			System.setProperty("jna.library.path", classLoader.getResource("lib/").getPath());

			// initialize api wrapper
			try {
				TmdbInfo.instance.init();
			} catch (MovieDbException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			_applicationState = ApplicationState.Initialized;
		}
	}

	public void load(StartNextLoadTaskCallback startedNextLoadTaskCallback,
			LoadFinishedCallbackHandler loadFinishedCallback) {
		if (_applicationState == ApplicationState.Initialized) {
			Thread loadThread = new Thread(new Runnable() {
				@Override
				public void run() {
					for (LoadTask currentLoadTask : _loadTasks) {
						startedNextLoadTaskCallback.invoke(currentLoadTask);

						try {
							currentLoadTask.run();
						} catch (Exception e) {
							// TODO(logging)
							e.printStackTrace();
						}
					}

					loadFinishedCallback.invoke();

					_applicationState = ApplicationState.Loaded;
				}
			});

			loadThread.start();
		}
	}

	public void tearDown() {
		if (_applicationState == ApplicationState.Loaded) {
			// free resources
			// teardown connections
			_applicationState = ApplicationState.Unitialized;
		}
	}

}