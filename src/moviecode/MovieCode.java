package moviecode;

import com.omertron.themoviedbapi.MovieDbException;
import moviecode.helpers.FileChecksum;
import moviecode.mediainfo.MediaInfoGetter;
import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import moviecode.helpers.AppConfig;
import moviecode.movieinfo.TmdbInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MovieCode {

    private static AppConfig cfg = AppConfig.getInstance();
    private static final Logger log = LoggerFactory.getLogger(MovieCode.class);


    /*
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, ParseException, MovieDbException {
        cfg.loadAppConfiguration();
        log.info("testlog entry");

        File videofile = new File("D:\\Downloads\\02 FILME\\new\\The Dark Knight Rises (IMAX) (2012)\\The Dark Knight Rises (IMAX) (2012).mkv");
        if (videofile.exists()) {
            showAppSettings();
            showTmdbInfo();
            showVideoInfo(videofile);
        } else {
            System.out.println("file not found");
        }
    }
*/
    
    
    
    public static void showTmdbInfo() throws MovieDbException {
        TmdbInfo inf = new TmdbInfo(cfg.API_KEY);
        inf.getMovieSearchResults("Armageddon", 1998);  // ohne jahr: null eingeben
        inf.getMovieInfoByID(9802);
        inf.showMovieCoverPopup(9802);
    }

    public static void showAppSettings() {
        System.out.println("Config ApiKey: " + cfg.API_KEY);
    }

    public static void showVideoInfo(File videofile) throws IOException, NoSuchAlgorithmException {

        MediaInfoGetter inf = new MediaInfoGetter(videofile);
        FileChecksum check = new FileChecksum();

        System.out.println("---------------------------------------------------");
        System.out.println("" + videofile.getName());
        //System.out.println("" + check.createChecksum("MD5",videofile)); langsam

        System.out.println("-------FILE INFOS----------------------------------");
        System.out.println("Filesize: " + inf.getFileSize() + " mb");
        System.out.println("Fileformat: " + inf.getFileFormat());
        System.out.println("Encoder Info: " + inf.getFileEncodedAppInfo());
        System.out.println("Library Info: " + inf.getFileLibraryAppInfo());

        System.out.println("-------VIDEO INFOS---------------------------------");
        System.out.println("Videostream count: " + inf.getVideostreamCount());
        System.out.println("Duration: " + inf.getVideoDurationMinutes() + " min");
        System.out.println("Display Ratio: " + inf.getDisplayAspectRatio());
        System.out.println("Resolution: " + inf.getVideoResolutionWidth() + "*" + inf.getVideoResolutionHeight() + "(" + inf.getVideoResolutionVerticalFormat() + ")");
        System.out.println("Framerate: " + inf.getVideoFramerate() + " per second");
        System.out.println("Bitrate: " + inf.getVideoBitrateKbps() + " kbps");
        System.out.println("Overall Bitrate: " + inf.getVideoOverallBitrateKbps() + " kbps");
        System.out.println("Video Source Language: " + inf.getVideoSourceLanguage());
        System.out.println("Format: " + inf.getVideoFormat());
        System.out.println("Bitrate mode: " + inf.getVideoBitrateMode());
        //System.out.println("Format2: " + inf.getVideoTest());

        System.out.println("-------AUDIO INFOS---------------------------------");
        System.out.println("Audiotracks count: " + inf.getAudioStreamCount());
        for (int i = 0; i < inf.getAudioStreamCount(); i++) {
            System.out.println("AUDIO #" + (i + 1));
            System.out.println("Format: " + inf.getAudioFormats().get(i));
            System.out.println("Language: " + inf.getAudioLanguages().get(i));
            System.out.println("Bitrate: " + inf.getAudioBitratesKbps().get(i) + " kbps");
            System.out.println("Channels: " + inf.getAudioChannels().get(i) + ".1");
        }

        System.out.println("-------SUB INFOS-----------------------------------");
        System.out.println("Subs count: " + inf.getSubCount());
        for (int i = 0; i < inf.getSubCount(); i++) {
            System.out.println("SUB #" + (i + 1));
            System.out.println("Language: " + inf.getSubLanguages().get(i));
            System.out.println("Forced: " + inf.getSubForced().get(i));
        }
    }
}
