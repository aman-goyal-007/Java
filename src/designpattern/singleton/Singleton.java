package designpattern.singleton;

public class Singleton {
    private Singleton(){}

    private static class SingletonHelper{
        private static final Singleton singleton = new Singleton();
    }

  public static Singleton getSingleton(){
        return SingletonHelper.singleton;
  }
}
