package utilities;

import java.util.logging.Level;
import java.util.logging.Logger;

/***
 @author ronald
 */
public class Log {

    static Logger logger = Logger.getLogger("LOG");

    public static void info(String msg){
        logger.info(msg);
    }

    public static void severe(String msg){//Serious failure.
        logger.severe(msg);
    }

    public static void config(String msg){//Configuration messages.
        logger.config(msg);
    }

    public static void warning(String msg){//Warning messages.
        logger.warning(msg);
    }

}
