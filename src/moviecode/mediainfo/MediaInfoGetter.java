package moviecode.mediainfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author fabian
 */
public class MediaInfoGetter {

    private MediaInfo info = new MediaInfo();
    private int currentStream = 0;   //default is 0
    private int streamAudioCount;
    private int streamVideoCount;
    private int streamSubCount;

    public MediaInfoGetter(File videofile) throws IOException {
        info.open(videofile);
        streamAudioCount = info.streamCount(MediaInfo.StreamKind.Audio);
        streamVideoCount = info.streamCount(MediaInfo.StreamKind.Video);
        streamSubCount = info.streamCount(MediaInfo.StreamKind.Text);
    }

    // -------------------------------------------------------------------------
    // FILE STUFF
    // -------------------------------------------------------------------------
    public double getFileSize() {
        String vidFileSize = info.get(MediaInfo.StreamKind.General, currentStream, "FileSize",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        double vidSize = ((Double.parseDouble(vidFileSize) / 1024) / 1024);  // convert to megabyte
        vidSize = round(vidSize, 2);
        return vidSize;
    }

    public String getFileFormat() {
        String vidFileFormat = info.get(MediaInfo.StreamKind.General, currentStream, "Format",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return vidFileFormat;
    }

    public String getFileEncodedAppInfo() {
        String vidInfo = info.get(MediaInfo.StreamKind.General, currentStream, "Encoded_Application",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return vidInfo;
    }

    public String getFileLibraryAppInfo() {
        String vidInfo = info.get(MediaInfo.StreamKind.General, currentStream, "Encoded_Library",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return vidInfo;
    }

    // -------------------------------------------------------------------------
    // VIDEO STUFF
    // -------------------------------------------------------------------------
    
    public int getVideostreamCount(){
        return streamVideoCount;
    }
    public long getVideoBitrateKbps() {
        String vidVideoBitrate = info.get(MediaInfo.StreamKind.Video, currentStream, "BitRate",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        long vidKbps = parseLongFromString(vidVideoBitrate) / 100;  // convert to kbps
        return vidKbps;
    }
    
    public long getVideoOverallBitrateKbps() {
        String vidOverallBitrate = info.get(MediaInfo.StreamKind.General, currentStream, "OverallBitRate",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        long vidOverallKbps = parseLongFromString(vidOverallBitrate) / 100;   // convert to kbps
        return vidOverallKbps;
    }

    public String getDisplayAspectRatio() {
        String vidInfo = info.get(MediaInfo.StreamKind.Video, currentStream, "DisplayAspectRatio/String",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return vidInfo;
    }

    public int getVideoResolutionWidth() {
        String vidWidth = info.get(MediaInfo.StreamKind.Video, currentStream, "Width",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return parseIntFromString(vidWidth);
    }

    public int getVideoResolutionHeight() {
        String vidHeight = info.get(MediaInfo.StreamKind.Video, currentStream, "Height",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return parseIntFromString(vidHeight);
    }
    
    public String getVideoResolutionVerticalFormat() {
        String vidHeight = info.get(MediaInfo.StreamKind.Video, currentStream, "Height",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        int vert = parseIntFromString(vidHeight);
        String vertReturn = "";
        
        if (vert > 400 && vert < 500){ vertReturn = "480p";}
        if (vert > 700 && vert < 800){ vertReturn = "720p";}
        if (vert > 1000 && vert < 1100){ vertReturn = "1080p";}
        if (vert > 2000 && vert < 2200){ vertReturn = "2K";}
        if (vert > 4000 && vert < 4500){ vertReturn = "4K";}
        
        return vertReturn;
    }

    public long getVideoDurationMilliseconds() {
        String vidDuration = info.get(MediaInfo.StreamKind.Video, currentStream, "Duration",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return parseLongFromString(vidDuration);
    }

    public long getVideoDurationMinutes() {
        String vidDuration = info.get(MediaInfo.StreamKind.Video, currentStream, "Duration",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        long vidDurationMinutes = parseLongFromString(vidDuration);
        return TimeUnit.MILLISECONDS.toMinutes(vidDurationMinutes);
    }

    public String getVideoDurationFormatted() {
        String vidDuration = info.get(MediaInfo.StreamKind.Video, currentStream, "Duration/String3",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return vidDuration;
    }

    public double getVideoFramerate() {
        String vidFramerate = info.get(MediaInfo.StreamKind.Video, currentStream, "FrameRate",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return Double.parseDouble(vidFramerate);
    }

    public String getVideoSourceLanguage() {
        String vidInfo = info.get(MediaInfo.StreamKind.Video, currentStream, "Language/String",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return vidInfo;
    }
    
    public String getVideoFormat() {
        String vidInfo = info.get(MediaInfo.StreamKind.Video, currentStream, "Format",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return vidInfo;
    }
    
    public String getVideoBitrateMode() {
        String vidInfo = info.get(MediaInfo.StreamKind.Video, currentStream, "BitRate_Mode/String",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return vidInfo;
    }
    
    public String getVideoTest() {
        String vidInfo = info.get(MediaInfo.StreamKind.Video, currentStream, "BitRate_Nominal",
                MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
        return vidInfo;
    }

    // -------------------------------------------------------------------------
    // AUDIO STUFF
    // -------------------------------------------------------------------------
    public int getAudioStreamCount() {
        return streamAudioCount;
    }

    public ArrayList<String> getAudioFormats() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < streamAudioCount; i++) {
            String audioInf = info.get(MediaInfo.StreamKind.Audio, i, "Format",
                    MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            list.add(audioInf);
        }
        return list;
    }

    public ArrayList<String> getAudioLanguages() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < streamAudioCount; i++) {
            String audioInf = info.get(MediaInfo.StreamKind.Audio, i, "Language/String",
                    MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            list.add(audioInf);
        }
        return list;
    }

    public ArrayList<Long> getAudioBitratesKbps() {
        ArrayList<Long> list = new ArrayList<Long>();
        for (int i = 0; i < streamAudioCount; i++) {
            String audioInf = info.get(MediaInfo.StreamKind.Audio, i, "BitRate",
                    MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            list.add((parseLongFromString(audioInf) / 1000));  // kbps
        }
        return list;
    }

    public ArrayList<Integer> getAudioChannels() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < streamAudioCount; i++) {
            String audioInf = info.get(MediaInfo.StreamKind.Audio, i, "Channel(s)",
                    MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            list.add(parseIntFromString(audioInf));
        }
        return list;
    }

    // -------------------------------------------------------------------------
    // SUBS
    // -------------------------------------------------------------------------
    public int getSubCount() {
        return streamSubCount;
    }

    public ArrayList<String> getSubLanguages() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < streamSubCount; i++) {
            String subInf = info.get(MediaInfo.StreamKind.Text, i, "Language/String",
                    MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            list.add(subInf);
        }
        return list;
    }

    public ArrayList<String> getSubForced() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < streamSubCount; i++) {
            String subInf = info.get(MediaInfo.StreamKind.Text, i, "Forced",
                    MediaInfo.InfoKind.Text, MediaInfo.InfoKind.Name);
            list.add(subInf);
        }
        return list;
    }

    // -------------------------------------------------------------------------
    // HELPERS
    // -------------------------------------------------------------------------
    private double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    private long parseLongFromString(String value) {
        if (value == null) {return 0;}
        if (value == ""){return 0;}
        int sz = value.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(value.charAt(i)) == false) {
                return 0;
            }
        }
        
        // if all checks ok then do parsing
        try {
            return Long.parseLong(value);
        } catch (NumberFormatException nfe) {
            //System.out.println("Error string: " + value);
            return 0;
        }
    }
    
    private int parseIntFromString(String value) {
        if (value == null) {return 0;}
        if (value == ""){return 0;}
        
        int sz = value.length();
        for (int i = 0; i < sz; i++) {
            if (Character.isDigit(value.charAt(i)) == false) {
                return 0;
            }
        }
        
        // if all checks ok then do parsing
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException nfe) {
            //System.out.println("Error string: " + value);
            return 0;
        }
    }
}
