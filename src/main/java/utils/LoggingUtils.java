package utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingUtils {
    private static final Logger logger = LogManager.getLogger(LoggingUtils.class);

    public static void logError(String message, Exception e) {
        logger.error(message, e);
    }
}