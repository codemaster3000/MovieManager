package application.helpers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import util.ApplicationServices;

/**
 *
 * @author fabian
 */
public class AppConfig {

    private static AppConfig instance = null;
    
    public String APP_NAME;
    public String APP_VERSION;
    public String API_KEY;
    public String DB_TABLE;
    public String DB_USER;
    public String DB_PASSWORD;
    public String TMDB_IMAGEROOTPATH;
    public String TMDB_LANGUAGE;
    public String TMDB_COVERLANGUAGE;
    public String EXTENSIONS_VIDEO;
    public String EXTENSIONS_AUDIO;
    public String DBNAME_LOCAL;
    public String STARTUP_WINDOWMAXIMIZED;
    public String STARTUP_WINDOWWIDTH;
    public String STARTUP_WINDOWHEIGHT;

    protected AppConfig() {
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
            APP_NAME = prop.getProperty("appname");
            APP_VERSION = prop.getProperty("appversion");
            API_KEY = prop.getProperty("apikey");
            TMDB_IMAGEROOTPATH = prop.getProperty("tmdbImagePathRoot");
            TMDB_LANGUAGE = prop.getProperty("tmdbLanguage");
            TMDB_COVERLANGUAGE  =  prop.getProperty("tmdbCoverLanguage");
            EXTENSIONS_VIDEO = prop.getProperty("videoextensions");
            EXTENSIONS_AUDIO = prop.getProperty("audioextensions");
            DBNAME_LOCAL = prop.getProperty("localdbname");
            
            // user settings
            STARTUP_WINDOWMAXIMIZED = prop.getProperty("startup_windowmaximized");
            STARTUP_WINDOWWIDTH = prop.getProperty("startup_windowwith");
            STARTUP_WINDOWHEIGHT = prop.getProperty("startup_windowheight");
            
            // unused (yet)
            DB_TABLE = prop.getProperty("database");
            DB_USER = prop.getProperty("dbuser");
            DB_PASSWORD = prop.getProperty("dbpassword");

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

    public void setAppConfiguration(String field, String value){
        Properties prop = new Properties();
        InputStream input = null;
        OutputStream output = null;
        
        try {
            input = ApplicationServices.instance.getResourcePathResolver().resolveResource("app/", "config", ".properties").openStream();
            prop.load(input);
            
            prop.setProperty(field, value);

            File f = new File(ApplicationServices.instance.getResourcePathResolver().resolveResource("app/", "config", ".properties").getFile());
            System.out.println(f.getPath());
            //output = new FileOutputStream( f );
            //prop.store(output, null);
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
    
    public String getPropertiesList(){
        Properties prop = new Properties();
        InputStream input = null;

        try {
            input = ApplicationServices.instance.getResourcePathResolver().resolveResource("app/", "config", ".properties").openStream();
            prop.load(input);
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
        return prop.toString();
    }
}
