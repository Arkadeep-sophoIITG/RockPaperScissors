package utils;

import java.util.logging.Level;
import java.util.logging.Logger;

public class RPSLogger {
    private final static Logger LOGGER =
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    public void logWarn(String msg) {
        LOGGER.log(Level.WARNING, msg);
    }

    public void logSevereError(String msg, Exception exception) {
        LOGGER.log(Level.SEVERE, "Exception is raised :", exception);
    }

    public void logInfo(String msg) {
        LOGGER.log(Level.INFO, msg);
    }

    public void logFineDetails(String msg, Object o) {
        LOGGER.log(Level.FINE, msg, o);
    }

}
