package blackmediamanager.database.persistance;


import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Properties;
import org.hibernate.cfg.AnnotationConfiguration;


public class DBSession {

    private static Session _session;
    
    public DBSession() {
        
    }
    
    
    
    public static Session getInstance() {
        if (_session != null && _session.isConnected()) {
            return _session;
        } else {
            
            /*   --> FÜR SPÖTER
            
            File configFile = new File("config.properties");
            String url = null, driver = null, username = null, password = null, charset = null, useUnicode = null;
            try {
                FileReader reader = new FileReader(configFile);
                Properties props = new Properties();
                props.load(reader);
                url = props.getProperty("url");
                driver = props.getProperty("driver_class");
                username = props.getProperty("username");
                password = props.getProperty("password");
                charset = props.getProperty("charset");
                useUnicode = props.getProperty("useunicode");
                reader.close();

                Configuration configuration = new Configuration();
                configuration.setProperty("hibernate.connection.url", url);
                configuration.setProperty("hibernate.connection.driver_class", driver);
                configuration.setProperty("hibernate.connection.username", username);
                configuration.setProperty("hibernate.connection.password", password);
                configuration.setProperty("hibernate.connection.CharSet", charset);
                configuration.setProperty("hibernate.connection.characterEncoding", charset);
                configuration.setProperty("hibernate.connection.useUnicode", useUnicode);
                configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
                configuration.configure();

                ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
                SessionFactory factory = configuration.buildSessionFactory(serviceRegistry);

                */
            
                SessionFactory factory = new AnnotationConfiguration().configure().buildSessionFactory();
                
                Transaction tx = null;

                if (factory != null) {
                    try {
                        _session = factory.openSession();
                        tx = _session.beginTransaction();
                        return _session;
                    } catch (Exception ex) {
                        if (tx != null) {
                            tx.rollback();
                        }
                    }
                }
                
            /*
            } catch (HibernateException e) {
                e.printStackTrace();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            */
                
                
            return _session;
        }
    }
}
