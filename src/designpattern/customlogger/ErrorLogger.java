package designpattern.customlogger;

public class ErrorLogger extends AbstractLogger {
    ErrorLogger(LogLevel logLevel){
        this.currentLevel = logLevel;
    }

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    @Override
    protected void write(String message){
        System.out.println("Error Logger: "+message);
    }

}
