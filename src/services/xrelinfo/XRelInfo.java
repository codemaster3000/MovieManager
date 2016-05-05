package services.xrelinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import services.xrelinfo.jsondata.media.XRcovers;
import services.xrelinfo.jsondata.latest.XRlatest;
import services.xrelinfo.jsondata.results.XRresults;

/**
 *
 * @author fabian
 */
public class XRelInfo {

    XRlatest latest;
    XRresults results;
    
    public XRelInfo() throws IOException{
        // empty
    }
    
    public XRlatest getLatestHDMovieReleases() throws IOException{
        String jsonurl = "https://api.xrel.to/v2/release/browse_category.json?category_name=HDTV&ext_info_type=movie&per_page=100";
        
        String json = readUrl(jsonurl);
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            latest = mapper.readValue(json, XRlatest.class);
        } catch (IOException ex) {
            Logger.getLogger(XRelInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        // do more filtering
        XRlatest latestFiltered = new XRlatest();
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        
        for (int i = 0; i < latest.getList().size(); i++) {
            if (checkStringContains(latest.getList().get(i).getDirname(), "1080p") &&
                    latest.getList().get(i).getAudioType() != "LineDubbed" &&
                    checkStringContains(latest.getList().get(i).getDirname(), year.toString())){
                
                latestFiltered.getList().add(latest.getList().get(i));
            }
        }
        
        return latestFiltered;
    }
    
    public String getCoverURL(String XRelID) throws IOException{
        XRelID = "08f068b020443";
        String jsonurl = "https://api.xrel.to/v2/ext_info/media.json?id=" + XRelID;
        
        String json = readUrl(jsonurl);
        ObjectMapper mapper = new ObjectMapper();

        List<XRcovers> covers = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(
                    List.class, XRcovers.class));
        
        if (covers.get(0).getType() == "image"){
            return covers.get(0).getUrl_full();
        } else {
            return "";
        }
    }
    
    public XRresults searchRelease(String keyword) throws IOException{
        String jsonurl = "https://api.xrel.to/v2/search/releases.json?q=" + keyword + "&scene=1&p2p=1";
        String json = readUrl(jsonurl);
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            results = mapper.readValue(json, XRresults.class);
        } catch (IOException ex) {
            Logger.getLogger(XRelInfo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return results;
       
    }
    
    private boolean checkStringContains(String checkString, String match){
        return checkString.toLowerCase().contains(match.toLowerCase());
    }
    
    private String readUrl(String urlString) throws IOException{
        BufferedReader reader = null;
        try {
            URL url = new URL(urlString);
            reader = new BufferedReader(new InputStreamReader(url.openStream()));
            StringBuffer buffer = new StringBuffer();
            int read;
            char[] chars = new char[1024];
            while ((read = reader.read(chars)) != -1) {
                buffer.append(chars, 0, read);
            }

            return buffer.toString();
        } catch (MalformedURLException ex) {
            Logger.getLogger(XRelInfo.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        
        return "";
    }
}
