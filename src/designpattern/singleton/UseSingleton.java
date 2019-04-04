package designpattern.singleton;

public class UseSingleton {
    public static void main(String[] args) {
        System.out.println(Singleton.getSingleton().hashCode());
        System.out.println(Singleton.getSingleton().hashCode());
    }
}
