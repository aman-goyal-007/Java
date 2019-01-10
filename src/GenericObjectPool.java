import java.util.concurrent.atomic.AtomicInteger;

// Need to create a Generic Object Pool
public class GenericObjectPool {

}

class MyGenericObjectPool<E>{
	final int size;
	AtomicInteger currentlyTaken=new AtomicInteger(0);
	
	MyGenericObjectPool(int givenSize){
		this.size=givenSize;
	}
	
	
}



