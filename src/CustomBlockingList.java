//3.	Implement Consumer and Producer algorithm, over a List object. Ensure that read/write to this list is thread-safe
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class CustomBlockingList {

	public static void main(String...args){
		
	}
}

class CustomList<E>{
	final int SIZE;
	List<E> list;
	Lock lock = new ReentrantLock();
	Condition isFull =lock.newCondition();
	Condition isEmpty=lock.newCondition();
	
	CustomList(int size){
		this.SIZE=size;
		list = new ArrayList<>(this.SIZE);
	}
	
	void put(E element){
		
		try {
			lock.lock();
			if(list.size()>=SIZE){
				isFull.await();
			}
			list.add(element);
			isEmpty.signal();
			lock.unlock();
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	
	E get(){
		E element=null;
		try {
			lock.lock();
			if(list.isEmpty()){
					isEmpty.await();
			}
			element = list.remove(list.size()-1);
			isFull.signal();
			lock.unlock();
		} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return element;
		}
	
}