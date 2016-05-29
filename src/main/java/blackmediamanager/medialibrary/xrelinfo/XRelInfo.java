package blackmediamanager.medialibrary.xrelinfo;

import com.fasterxml.jackson.databind.ObjectMapper;

import blackmediamanager.medialibrary.xrelinfo.jsondata.latest.XRlatest;
import blackmediamanager.medialibrary.xrelinfo.jsondata.media.XRcovers;
import blackmediamanager.medialibrary.xrelinfo.jsondata.result.XRresults;

import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author fabian
 * 
 * API limits:
 * 300 calls per hour
 * 2 calls per 10 seconds
 * 
 */
public class XRelInfo {

    XRresults results;
    
    public XRelInfo() throws IOException{
        // empty
    }
    
    public XRlatest getLatestHDMovieReleases(String category, String type, int pages) throws IOException{
        ArrayList<String> jsonPages = new ArrayList<String>();
        jsonPages.add("https://api.xrel.to/v2/release/browse_category.json?category_name=" + category + "&ext_info_type=" + type + "&per_page=100&page=1");

        for (int p = 2; p <= pages; p++) {
            jsonPages.add("https://api.xrel.to/v2/release/browse_category.json?category_name=" + category + "&ext_info_type=" + type + "&per_page=100&page=" + p);    
        }
        
        ObjectMapper mapper = new ObjectMapper();
        ArrayList<XRlatest> latest = new ArrayList<XRlatest>();
        
        for (String jsonPage : jsonPages){
            try {
                String json = readUrl(jsonPage);
                latest.add(mapper.readValue(json, XRlatest.class));
            } catch (IOException ex) {
                Logger.getLogger(XRelInfo.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        // do more filtering
        XRlatest latestFiltered = new XRlatest();
        Integer year = Calendar.getInstance().get(Calendar.YEAR);
        
        for (XRlatest latestpage : latest){
            for (int i = 0; i < latestpage.getList().size(); i++) {
                if (checkStringContains(latestpage.getList().get(i).getDirname(), "1080p") &&
                        latestpage.getList().get(i).getAudioType() != "LineDubbed" &&
                        checkStringContains(latestpage.getList().get(i).getDirname(), "BluRay") &&
                        checkStringContains(latestpage.getList().get(i).getDirname(), year.toString())){

                    latestFiltered.getList().add(latestpage.getList().get(i));
                }
            }
        }
        
        return latestFiltered;
    }
    
    public String getCoverURL(String XRelID) throws IOException{
        String jsonurl = "https://api.xrel.to/v2/ext_info/media.json?id=" + XRelID;
        
        String json = readUrl(jsonurl);
        ObjectMapper mapper = new ObjectMapper();
        
        try {
            List<XRcovers> covers = mapper.readValue(json, mapper.getTypeFactory().constructCollectionType(
                        List.class, XRcovers.class));

            if (covers.get(0).getType() == "image"){
                return covers.get(0).getUrl_full();
            } else {
                return "";
            }
        } catch (Exception e){
            Logger.getLogger(XRelInfo.class.getName()).log(Level.WARNING, "Json Cover data deserialization failed", "");
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
            Logger.getLogger(XRelInfo.class.getName()).log(Level.WARNING, null, "");
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
            Logger.getLogger(XRelInfo.class.getName()).log(Level.WARNING, "Malformed URL: " + urlString, "");
        } catch (FileNotFoundException ex) {
            Logger.getLogger(XRelInfo.class.getName()).log(Level.WARNING, "HTTP File not found: " + urlString, "");
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        
        return "";
    }
}
