package designpattern.customlogger;

// Chain of responsibility
public class UseLogger {
    public static void main(String[] args) {
        ErrorLogger errorLogger = new ErrorLogger(LogLevel.ERROR);
        DebugLogger debugLogger = new DebugLogger(LogLevel.DEBUG);
        InfoLogger infoLogger = new InfoLogger(LogLevel.INFO);
        errorLogger.setNextLogger(debugLogger);
        debugLogger.setNextLogger(infoLogger);
        // basically info logger itself will print all level messages
        // while in case of error logger, messages will be printing by respective log level

        errorLogger.logMessage(LogLevel.INFO,"Message using error logger and info level."); //  message will be printed by info logger
        infoLogger.logMessage(LogLevel.ERROR,"Message using info logger and error level."); // message will be printed by info logger
    }
}
