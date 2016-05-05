package services.xrelinfo;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStreamReader;
import java.net.URL;
import java.io.BufferedReader;
import services.xrelinfo.jsondata.XRlatest;

/**
 *
 * @author fabian
 */
public class XRelInfo {

    public XRelInfo() {
        // empty
    }
    
    public void testApi() throws Exception{
        String json = readUrl("https://api.xrel.to/v2/release/latest.json");
        ObjectMapper mapper = new ObjectMapper();
        XRlatest obj = mapper.readValue(json, XRlatest.class);
        
        for (int i = 0; i < obj.getList().size(); i++) {
            System.out.println(obj.getList().get(i).getDirname());
        }
    }

    private String readUrl(String urlString) throws Exception {
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
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
    }
}
