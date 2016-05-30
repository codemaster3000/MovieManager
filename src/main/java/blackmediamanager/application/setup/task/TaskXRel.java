package blackmediamanager.application.setup.task;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import blackmediamanager.scrapers.xrelinfo.XRelDataHandler;
import blackmediamanager.scrapers.xrelinfo.XRelInfo;
import blackmediamanager.scrapers.xrelinfo.jsondata.latest.XRlatest;

public class TaskXRel extends LoadTask {

	public TaskXRel() {
		super(TaskXRel.class.getName());
	}

	@Override
	public void run() {
                XRelInfo xrel;
                try {
                    System.out.println("Setup: Load Xrel newest releases");
                    xrel = new XRelInfo();
                    XRlatest latest = xrel.getLatestHDMovieReleases("HDTV", "movie", 5);
                    XRelDataHandler xhand = XRelDataHandler.getInstance();
                    xhand.setXlistHDMoviesLatest(latest);
                } catch (IOException ex) {
                    Logger.getLogger(TaskXRel.class.getName()).log(Level.SEVERE, null, ex);
                }
	}
}
