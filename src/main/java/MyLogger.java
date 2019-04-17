import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class MyLogger {

    public static void generateLogger(String url) {
        Logger logger = Logger.getLogger("ApiLog");
        FileHandler fh;

        try {


            fh = new FileHandler("./ApiLog.log");
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);


            logger.info(url);

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        logger.info("Hi How r u?");

        }
    }

