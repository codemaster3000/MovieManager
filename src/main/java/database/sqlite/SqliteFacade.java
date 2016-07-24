package database.sqlite;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Fabian
 */
public class SqliteFacade {
    
    public SqliteFacade(){
        
    }
    
    public void getListTest() throws SQLException{
        Connection c = SqliteSession.getInstance();
        Statement stmt = null;
        stmt = c.createStatement();
        
        
    }
}
