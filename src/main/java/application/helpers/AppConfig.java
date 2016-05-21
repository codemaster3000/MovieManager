package application.helpers;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import util.ApplicationServices;

/**
 *
 * @author fabian
 */
public class AppConfig {

    private static AppConfig instance = null;
    
    public String API_KEY;
    public String DB_TABLE;
    public String DB_USER;
    public String DB_PASSWORD;
    public String TMDB_IMAGEROOTPATH;
    public String TMDB_LANGUAGE;
    public String TMDB_COVERLANGUAGE;
    public String EXTENSIONS_VIDEO;

    protected AppConfig() {
        // ....
        loadAppConfiguration();
    }

    public static AppConfig getInstance() {
        if (instance == null) {
            instance = new AppConfig();
        }
        return instance;
    }

    public void loadAppConfiguration() {
        Properties prop = new Properties();
        InputStream input = null;

        try {
        	input = ApplicationServices.instance.getResourcePathResolver().resolveResource("app/", "config", ".properties").openStream();
        	prop.load(input);

            // get the property values
            API_KEY = prop.getProperty("apikey");
            DB_TABLE = prop.getProperty("database");
            DB_USER = prop.getProperty("dbuser");
            DB_PASSWORD = prop.getProperty("dbpassword");
            TMDB_IMAGEROOTPATH = prop.getProperty("tmdbImagePathRoot");
            TMDB_LANGUAGE = prop.getProperty("tmdbLanguage");
            TMDB_COVERLANGUAGE  =  prop.getProperty("tmdbCoverLanguage");
            EXTENSIONS_VIDEO = prop.getProperty("videoextensions");

        } catch (IOException ex) {
            ex.printStackTrace();
            System.out.println("Error: Config file not found");
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
