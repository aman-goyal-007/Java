import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


// Implement Consumer and Producer algorithm, over a List object. Ensure that read/write to this list is thread-safe
public class CustomBlockingList<E> {

	private final int size;
	private List<E> list;
	private Lock lock = new ReentrantLock();
	private Condition notFull =lock.newCondition();
	private Condition notEmpty=lock.newCondition();

	CustomBlockingList(int size){
		this.size=size;
		list = new ArrayList<>(this.size);
	}
	
	void put(E element){
		try {
			lock.lock();
			while(list.size()>=size){
				notFull.await();
			}
			list.add(element);
			notEmpty.signal();
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		}
	}
	
	E get(){
		E element=null;
		try {
			lock.lock();
			while(list.isEmpty()){
					notEmpty.await();
			}
			element = list.remove(list.size()-1);
			notFull.signal();
			lock.unlock();
		} catch (InterruptedException e) {
			e.printStackTrace();
				Thread.currentThread().interrupt();
			}
		return element;
		}
	
}