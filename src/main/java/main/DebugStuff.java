package main;


import java.io.File;
import java.io.IOException;
import services.mediainfo.MediaInfoGetter;
import services.xrelinfo.XRelInfo;
import services.xrelinfo.jsondata.latest.XRlatest;
import services.xrelinfo.jsondata.results.XRresults;

/**
 *
 * For debugging
 *
 * @author fabian
 */
public class DebugStuff {

    public DebugStuff(boolean enabled) throws Exception {
        if (enabled) {
            //testXRel();
            testMediaInfo();
        }
    }
    
    public void testMediaInfo() throws IOException{
        System.out.println("MediaInfo dll tests*");
        File file = new File("C://FABIAN//example.mkv");
        if(!file.exists()){
            System.out.println("file not found: " + file.getPath());
        }
        MediaInfoGetter inf = new MediaInfoGetter(file);
    }

    public void testXRel() throws IOException, Exception {
        // for debug use only
        XRelInfo xrel = new XRelInfo();

        // get latest releases example
        System.out.println("**********************************************");
        System.out.println("Latest releases (filtered):");
        XRlatest latest = xrel.getLatestHDMovieReleases("HDTV", "movie", 5);
        for (int i = 0; i < latest.getList().size(); i++) {
            System.out.println(latest.getList().get(i).getDirname());
        }

        // search example
        System.out.println("**********************************************");
        System.out.println("Search results example:");
        XRresults results = xrel.searchRelease("1080p");
        for (int i = 0; i < results.getResults().size(); i++) {
            System.out.println(results.getResults().get(i).getDirname());
        }
        
        // cover example
        System.out.println("**********************************************");
        System.out.println("Cover url example:");
        System.out.println(xrel.getCoverURL(latest.getList().get(0).getId()));
    }
}
