import java.util.*;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
        try {
            List<Integer> list = Arrays.asList(3,6,9);
            List<Integer> list1 = new ArrayList<>();
            list.stream().forEach(l->list1.add(l*2));
            list1.stream().forEach(System.out::println);
        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
