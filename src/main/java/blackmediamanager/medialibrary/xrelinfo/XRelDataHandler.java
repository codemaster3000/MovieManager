package blackmediamanager.medialibrary.xrelinfo;


import blackmediamanager.medialibrary.xrelinfo.jsondata.latest.XRlatest;

/**
 *
 * @author fabian
 */
public class XRelDataHandler {

    private static XRlatest xlistHDMoviesLatest;

    private static XRelDataHandler instance = null;

    protected XRelDataHandler() {
        xlistHDMoviesLatest = new XRlatest();
    }

    public static XRelDataHandler getInstance() {
        if (instance == null) {
            instance = new XRelDataHandler();
        }
        return instance;
    }

    public XRlatest getXlistHDMoviesLatest() {
        return xlistHDMoviesLatest;
    }

    public void setXlistHDMoviesLatest(XRlatest xlist) {
        xlistHDMoviesLatest = xlist;
    }
}
