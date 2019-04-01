public class SingletonTest {

    public static void main(String[] args) {
        Singleton sing = Singleton.getSingleton();
    }
}

class Singleton{

    private Singleton(){}

    static class SingletonHelper{
        private static Singleton singleton = new Singleton();
    }

    public static Singleton getSingleton(){
        return SingletonHelper.singleton;
    }

    protected Object readResolve(){
        return getSingleton();
    }
}
