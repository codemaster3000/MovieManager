package blackmediamanager.application.setup;

import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskSleep extends LoadTask {

	public TaskSleep() {
		super(TaskSleep.class.getName());
	}

	@Override
	public void run() {
            try {
                System.out.println("Setup: Sleep 3 seconds");
                Thread.sleep(3000);
            } catch (InterruptedException ex) {
                Logger.getLogger(TaskSleep.class.getName()).log(Level.SEVERE, null, ex);
            }
	}

}
