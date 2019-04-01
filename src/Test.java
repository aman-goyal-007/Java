import java.util.*;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        try {
            List<Future> list = new ArrayList<>();
            ExecutorService service = Executors.newFixedThreadPool(5);
//            ExecutorCompletionService completionService = new ExecutorCompletionService(service);
            service.submit(new Goyal("one", 5000));
            service.submit(new Goyal("two", 4000));
            service.submit(new Goyal("three", 3000));
            service.submit(new Goyal("four", 2000));
            service.shutdown();
/*
            while(true){
                Future f = completionService.take();
                System.out.println(f.get());
            }
*/
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
class Goyal implements Callable<String> {
    String a;
    long sec;
    Goyal(String aa, long s){
        this.a=aa;
        this.sec=s;

    }
    @Override
    public String call() throws Exception {
        Thread.sleep(sec);
        return a;
    }
}
