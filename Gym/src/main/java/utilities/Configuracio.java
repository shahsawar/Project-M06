package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/***
 @author ronald
 */
public class Configuracio {

    public static String URL_DB_JDBC;
    public static String MONGO_SERVER_ADDRESS;
    public static String COLLECTION_USERS;
    public static String DBNAME;
    public static String USER;
    public static String PASSWORD;

    static String FILENAME = "configuration.properties";

    public static void getData(){
        Properties properties= new Properties();
        try {
            properties.load(new FileInputStream(new File(FILENAME)));
            DBNAME = (String) properties.get("DB");
            URL_DB_JDBC = (String) properties.get("URL_DB_JDBC");
            COLLECTION_USERS = (String) properties.get("COLLECTION_USERS");
            MONGO_SERVER_ADDRESS = (String) properties.get("MONGO_SERVER_ADDRESS");
            USER = (String) properties.get("USER");
            PASSWORD = (String) properties.get("PASSWORD");
        } catch (FileNotFoundException e) {
            Log.severe("Properties file " + FILENAME + " not found");
        } catch (IOException e) {
            Log.severe("I/O error while reading file " + FILENAME);
        }
    }
}
