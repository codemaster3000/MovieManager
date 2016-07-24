package database.sqlite;

import application.helpers.AppConfig;
import database.sqlitemodels.CompanyModel;
import org.javalite.activejdbc.Base;

/**
 *
 * @author Fabian
 */
public class ActiveSession {
    
    private static final String DB_NAME = AppConfig.getInstance().DBNAME_LOCAL;
    
    public ActiveSession(String dbpath){
        Base.open("org.sqlite.JDBC", "jdbc:sqlite:" + DB_NAME, "root", "p@ssw0rd");
        System.out.println("Active ORM for sqlite is initiated: " + Base.hasConnection());
        
        CompanyModel cmod = new CompanyModel();
        
        cmod.set("first_name", "John");
        cmod.set("last_name", "Doe");
        cmod.saveIt();
        
        //CompanyModel c = CompanyModel.findFirst("first_name = ?", "John");
        //System.out.println("DB result test: " + c.toString());
        
        Base.close();
    }
}
