package utilities;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/***
 @author ronald
 */
public class Log {

    static Logger logger = Logger.getLogger("LOG");
    static FileHandler fh;

    /**
     * Set the log file to the logger
     */
    public static void start(){
        try {
            // This block configure the logger with handler and formatter
            fh = new FileHandler("logFile.log", true);//True in order to not remove the pasts logs
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            logger.setUseParentHandlers(false);
            logger.addHandler(fh);


        }catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

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
