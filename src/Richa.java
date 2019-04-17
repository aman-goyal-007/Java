public class Richa {
    public static void main(String[] args) {
        CustomThreadPool threadPool = new CustomThreadPool(3);
        threadPool.execute(new Deoband("a"));
        threadPool.execute(new Deoband("b"));
        threadPool.execute(new Deoband("c"));
        threadPool.execute(new Deoband("d"));
        threadPool.shutdown();
    }
}

class Deoband implements Runnable{

    private String name;
    Deoband(String n){
        name = n;
    }
    @Override
    public void run() {
        try {
            Thread.sleep(3000);
            System.out.println(name);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
