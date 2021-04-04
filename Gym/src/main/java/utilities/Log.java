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

    /**
     * Writes a INFO type log
     * @param msg
     */
    public static void info(String msg){
        logger.info(msg);
    }

    /**
     * Writes a SEVERE type log, used to show errors
     * @param msg
     */
    public static void severe(String msg){//Serious failure.
        logger.severe(msg);
    }

    /**
     * Writes a CONFIG type log
     * @param msg
     */
    public static void config(String msg){//Configuration messages.
        logger.config(msg);
    }

    /**
     * Writes a WARNING type log, used for not so severe errors
     * @param msg
     */
    public static void warning(String msg){//Warning messages.
        logger.warning(msg);
    }

}
