package designpattern.customlogger;

public class DebugLogger extends AbstractLogger {
    DebugLogger(LogLevel logLevel){
        this.currentLevel = logLevel;
    }

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    @Override
    protected void write(String message){
        System.out.println("Debug Logger: "+message);
    }

}
