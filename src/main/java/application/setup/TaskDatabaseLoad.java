package application.setup;

import database.sqlite.ActiveSession;
import database.sqlite.SqliteSession;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TaskDatabaseLoad extends LoadTask {

    public TaskDatabaseLoad() {
        super(TaskDatabaseLoad.class.getName());
    }

    @Override
    public void run() {
        // load & setup local database
        System.out.println("Sqlite: Load local database");
        SqliteSession.getInstance();
        SqliteSession.closeConnection();
        
        // init orm 
        ActiveSession as = new ActiveSession(SqliteSession.getDbFilePath());
        
        // load remote database (hibernate shit)
        //...
    }

}
