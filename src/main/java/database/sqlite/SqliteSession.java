package database.sqlite;

import application.helpers.AppConfig;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Fabian
 */
public class SqliteSession {

    private static Connection _session;
    private static final String DB_NAME = AppConfig.getInstance().DBNAME_LOCAL;

    public SqliteSession() {}

    public static Connection getInstance() {
        if (_session != null) {
            return _session;
        } else {
            // check if db file exists else create tables into new db
            boolean newDB = false;
            File dbFile = new File(DB_NAME);

            if (!dbFile.exists()) {
                newDB = true;
            }

            // open existing db or create new db if db file is not available
            _session = null;
            try {
                Class.forName("org.sqlite.JDBC");
                _session = DriverManager.getConnection("jdbc:sqlite:" + DB_NAME);
            } catch (Exception e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
            }

            // add sql tables if new db is created
            if (newDB) {
                System.out.println("Sqlite: local db not found");
                setupNewDB();
            }
            return _session;
        }
    }
    
    public static void closeConnection(){
        try {
            _session.close();
        } catch (SQLException ex) {
            //Logger.getLogger(sqliteSession.class.getName()).log(Level.SEVERE, null, ex);
            System.err.println(ex.getClass().getName() + ": " + ex.getMessage());
        }
        System.out.println("Sqlite: connection closed");
    }
    
    // create a table in previously created database
    private static void setupNewDB() {
        Statement stmt = null;
        try {

            System.out.println("Sqlite: setup new local db");

            stmt = _session.createStatement();
            
            // TODO add sql setup script
            String sql = "CREATE TABLE COMPANY_MODELS "
                    + "(ID INTEGER PRIMARY KEY   AUTOINCREMENT,"
                    + " first_name VARCHAR(56), "
                    + " last_name VARCHAR(56))";
            stmt.executeUpdate(sql);
            stmt.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
        }
        System.out.println("Sqlite: new database created succesfully");
    }
    
    public static String getDbFilePath(){
        File dbFile = new File(DB_NAME);
        return dbFile.getAbsolutePath();
    }
    
    
}
