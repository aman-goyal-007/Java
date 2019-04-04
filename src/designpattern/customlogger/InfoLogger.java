package designpattern.customlogger;

public class InfoLogger extends AbstractLogger {
    InfoLogger(LogLevel logLevel){
        this.currentLevel = logLevel;
    }

    public void setNextLogger(AbstractLogger nextLogger) {
        this.nextLogger = nextLogger;
    }

    @Override
    protected void write(String message){
        System.out.println("Info Logger: "+message);
    }

}
