package designpattern.customlogger;

public enum LogLevel {
    INFO(1),DEBUG(2),ERROR(3);
    private final int level;
    LogLevel(int l){
        this.level=l;
    }

    public int getLevel(){return level;}

}
