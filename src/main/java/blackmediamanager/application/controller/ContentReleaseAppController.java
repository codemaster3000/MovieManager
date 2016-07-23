package blackmediamanager.application.controller;

import blackmediamanager.scrapers.xrelinfo.XRelDataHandler;
import blackmediamanager.scrapers.xrelinfo.jsondata.latest.XRlatest;

public class ContentReleaseAppController {

	public XRlatest getNewReleases() {
		return XRelDataHandler.getInstance().getXlistHDMoviesLatest();
	}
}
