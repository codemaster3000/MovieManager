package moviecode.helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

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

    protected AppConfig() {
        // ....
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
            input = new FileInputStream("app.config.txt");
            prop.load(input);

            // get the property values
            API_KEY = prop.getProperty("apikey");
            DB_TABLE = prop.getProperty("database");
            DB_USER = prop.getProperty("dbuser");
            DB_PASSWORD = prop.getProperty("dbpassword");
            TMDB_IMAGEROOTPATH = prop.getProperty("tmdbImagePathRoot");

        } catch (IOException ex) {
            ex.printStackTrace();
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