package blackmediamanager.application.controller;

import blackmediamanager.medialibrary.xrelinfo.XRelDataHandler;
import blackmediamanager.medialibrary.xrelinfo.jsondata.latest.XRlatest;

public class ContentReleaseAppController {

	public XRlatest getNewReleases() {
		return XRelDataHandler.getInstance().getXlistHDMoviesLatest();
	}
}
