package application.controller;

import services.xrelinfo.XRelDataHandler;
import services.xrelinfo.jsondata.latest.XRlatest;

public class ContentReleaseAppController {

	public XRlatest getNewReleases() {
		return XRelDataHandler.getInstance().getXlistHDMoviesLatest();
	}
}
