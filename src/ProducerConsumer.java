import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class ProducerConsumer {
	public static void main(String...args){
		List<String> list = new ArrayList<String>();
		Lock lock = new ReentrantLock();
		PC pc= new PC(list,lock);
		TestProducer producer = new TestProducer(pc);
		TestConsumer consumer = new TestConsumer(pc);
		Thread producerThread= new Thread(producer);
		Thread consumerThread= new Thread(consumer);
		producerThread.start();
		consumerThread.start();
	}

}

class TestProducer implements Runnable{
	
	PC pc;
	int i;
	public TestProducer(PC pc) {
		this.pc=pc;
	}

	@Override
	public void run() {
		while(true){
			System.out.println("Producing ");
			pc.put("Aman->"+i++);
		}
	}
	
}
class TestConsumer implements Runnable{
	
	PC pc;
	int i;
	public TestConsumer(PC pc) {
		this.pc=pc;
	}

	@Override
	public void run() {
		while(true){
			System.out.println(("Consuming Aman->"+pc.get()));
		}
	}
	
}

class PC{
	final int SIZE=5;
	List<String> list;
	Lock lock;
	Condition isFull ;
	Condition isEmpty;
	
	PC(List<String> list, Lock lock){
		this.list = list;
		this.lock=lock;
		isFull =lock.newCondition();
		isEmpty = lock.newCondition();
	}
	
	void put(String element){
		
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
	
	String get(){
		String element=null;
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