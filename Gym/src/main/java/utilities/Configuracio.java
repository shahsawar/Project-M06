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

    public static void main(String[] args) {
        Properties properties= new Properties();
        try {
            properties.load(new FileInputStream(new File("configuration.properties")));
            System.out.println(properties.get("DB"));
            System.out.println(properties.get("USER"));
            System.out.println(properties.get("PASSWORD"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
