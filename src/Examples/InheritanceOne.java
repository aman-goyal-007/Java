package Examples;

public class InheritanceOne {
    public static void main(String[] args) {
        One a = new One();
        One b = new Two();

        a.add1();

    }

}

class One{
    int x= 5;
    static int y = 10;

    public Number getVal(){
        return 2d;
    }

    public synchronized void add1(){
        System.out.println("add1");
    }

    public synchronized void add2(){
        System.out.println("add2");
    }

    public synchronized static void add3(){
        System.out.println("add 3");
    }

    public synchronized static void add4(){
        System.out.println("add4");
    }
}

class Two extends One{
    int x = 65;
    static int y = 70;

    public Number getVal(){
        return 1;
    }

}

