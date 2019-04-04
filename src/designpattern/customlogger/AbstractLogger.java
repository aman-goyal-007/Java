package designpattern.customlogger;

public abstract class AbstractLogger {
    protected LogLevel currentLevel;

    protected AbstractLogger nextLogger;

    public void setNextLogger(AbstractLogger nextLogger){
        this.nextLogger = nextLogger;
    }

    public void logMessage(LogLevel level, String message){
        if(this.currentLevel.getLevel()<=level.getLevel()){
            write(message);
        }
        if(nextLogger !=null){

            nextLogger.logMessage(level, message);
        }
    }
        abstract protected void write(String message);

}
