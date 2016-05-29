package blackmediamanager.medialibrary.mediainfo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author fabian
 */
public class MediaInfoFile {

    private MediaInfoFileReader info = new MediaInfoFileReader();
    private int currentStream = 0;   //default is 0
    private int streamAudioCount;
    private int streamVideoCount;
    private int streamSubCount;

    public MediaInfoFile(File videofile) throws IOException {
        info.open(videofile);
        streamAudioCount = info.streamCount(MediaInfoFileReader.StreamKind.Audio);
        streamVideoCount = info.streamCount(MediaInfoFileReader.StreamKind.Video);
        streamSubCount = info.streamCount(MediaInfoFileReader.StreamKind.Text);
    }

    // -------------------------------------------------------------------------
    // FILE STUFF
    // -------------------------------------------------------------------------
    public double getFileSize() {
        String vidFileSize = info.get(MediaInfoFileReader.StreamKind.General, currentStream, "FileSize",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        double vidSize = ((Double.parseDouble(vidFileSize) / 1024) / 1024);  // convert to megabyte
        vidSize = round(vidSize, 2);
        return vidSize;
    }

    public String getFileFormat() {
        String vidFileFormat = info.get(MediaInfoFileReader.StreamKind.General, currentStream, "Format",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return vidFileFormat;
    }

    public String getFileEncodedAppInfo() {
        String vidInfo = info.get(MediaInfoFileReader.StreamKind.General, currentStream, "Encoded_Application",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return vidInfo;
    }

    public String getFileLibraryAppInfo() {
        String vidInfo = info.get(MediaInfoFileReader.StreamKind.General, currentStream, "Encoded_Library",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return vidInfo;
    }

    // -------------------------------------------------------------------------
    // VIDEO STUFF
    // -------------------------------------------------------------------------
    
    public int getVideostreamCount(){
        return streamVideoCount;
    }
    public long getVideoBitrateKbps() {
        String vidVideoBitrate = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "BitRate",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        long vidKbps = parseLongFromString(vidVideoBitrate) / 100;  // convert to kbps
        return vidKbps;
    }
    
    public long getVideoOverallBitrateKbps() {
        String vidOverallBitrate = info.get(MediaInfoFileReader.StreamKind.General, currentStream, "OverallBitRate",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        long vidOverallKbps = parseLongFromString(vidOverallBitrate) / 100;   // convert to kbps
        return vidOverallKbps;
    }

    public String getDisplayAspectRatio() {
        String vidInfo = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "DisplayAspectRatio/String",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return vidInfo;
    }

    public int getVideoResolutionWidth() {
        String vidWidth = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "Width",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return parseIntFromString(vidWidth);
    }

    public int getVideoResolutionHeight() {
        String vidHeight = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "Height",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return parseIntFromString(vidHeight);
    }
    
    public String getVideoResolutionVerticalFormat() {
        String vidHeight = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "Height",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
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
        String vidDuration = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "Duration",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return parseLongFromString(vidDuration);
    }

    public long getVideoDurationMinutes() {
        String vidDuration = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "Duration",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        long vidDurationMinutes = parseLongFromString(vidDuration);
        return TimeUnit.MILLISECONDS.toMinutes(vidDurationMinutes);
    }

    public String getVideoDurationFormatted() {
        String vidDuration = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "Duration/String3",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return vidDuration;
    }

    public double getVideoFramerate() {
        String vidFramerate = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "FrameRate",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return Double.parseDouble(vidFramerate);
    }

    public String getVideoSourceLanguage() {
        String vidInfo = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "Language/String",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return vidInfo;
    }
    
    public String getVideoFormat() {
        String vidInfo = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "Format",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return vidInfo;
    }
    
    public String getVideoBitrateMode() {
        String vidInfo = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "BitRate_Mode/String",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
        return vidInfo;
    }
    
    public String getVideoTest() {
        String vidInfo = info.get(MediaInfoFileReader.StreamKind.Video, currentStream, "BitRate_Nominal",
                MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
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
            String audioInf = info.get(MediaInfoFileReader.StreamKind.Audio, i, "Format",
                    MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
            list.add(audioInf);
        }
        return list;
    }

    public ArrayList<String> getAudioLanguages() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < streamAudioCount; i++) {
            String audioInf = info.get(MediaInfoFileReader.StreamKind.Audio, i, "Language/String",
                    MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
            list.add(audioInf);
        }
        return list;
    }

    public ArrayList<Long> getAudioBitratesKbps() {
        ArrayList<Long> list = new ArrayList<Long>();
        for (int i = 0; i < streamAudioCount; i++) {
            String audioInf = info.get(MediaInfoFileReader.StreamKind.Audio, i, "BitRate",
                    MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
            list.add((parseLongFromString(audioInf) / 1000));  // kbps
        }
        return list;
    }

    public ArrayList<Integer> getAudioChannels() {
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < streamAudioCount; i++) {
            String audioInf = info.get(MediaInfoFileReader.StreamKind.Audio, i, "Channel(s)",
                    MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
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
            String subInf = info.get(MediaInfoFileReader.StreamKind.Text, i, "Language/String",
                    MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
            list.add(subInf);
        }
        return list;
    }

    public ArrayList<String> getSubForced() {
        ArrayList<String> list = new ArrayList<String>();
        for (int i = 0; i < streamSubCount; i++) {
            String subInf = info.get(MediaInfoFileReader.StreamKind.Text, i, "Forced",
                    MediaInfoFileReader.InfoKind.Text, MediaInfoFileReader.InfoKind.Name);
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
