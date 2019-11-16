package kybmig.ssm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utility {

    private static Logger logger = LoggerFactory.getLogger(Utility.class);

    static public void log(String format, Object...args) {
        String s = String.format(format, args);
        logger.info(s);
    }
}
