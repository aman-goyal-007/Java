import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


// Printing numbers in sequence using n number of threads. Thread 1 should print 1, thread should print 2 and so on...
public class FourThreadPrinting {

	public static void main(String...args){
		NumberPrinting num = new NumberPrinting(4);
		Thread t1 = new Thread(num,"a");
		Thread t2 = new Thread(num,"b");
		Thread t3 = new Thread(num,"c");
		Thread t4 = new Thread(num,"d");
		num.addToList(t1);
		num.addToList(t2);
		num.addToList(t3);
		num.addToList(t4);
		t1.start();
		t2.start();
		t3.start();
		t4.start();
	}
}

class MyThread{
	Thread currentThread;
	Thread nextThread;
}

class NumberPrinting implements Runnable{
	int sharedNumber=0;
	volatile int currentAccess=0;
	List<Thread> myThreadList;
	final int size;
	AtomicInteger index = new AtomicInteger(0);
	public NumberPrinting(int size) {
		this.size=size;
		myThreadList = new ArrayList<Thread>(this.size);
	}
	public void addToList(Thread t){
		myThreadList.add(t);
	}
	@Override
	public  void run() {
		try{
			while(true){
				Thread.sleep(200);
				Thread threadFromList = myThreadList.get(index.get());
				synchronized(threadFromList){
					//System.out.println(" Aman : "+threadFromList+","+index.get()+","+Thread.currentThread().getName());
					if(threadFromList.getName().equals(Thread.currentThread().getName())){
						System.out.println(Thread.currentThread().getName()+","+sharedNumber++);
						index.getAndIncrement();
						if(index.get()>=size) index.set(0);
					}
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
}

































/*
import java.util.concurrent.atomic.AtomicInteger;

public class PrintAlternate {
	public static void main(String...args){
		Print p = new Print(5);
		Thread t1 = new Thread(p,"a");
		Thread t2 = new Thread(p,"b");
		Thread t3 = new Thread(p,"c");
		Thread t4 = new Thread(p,"c");
		Thread t5 = new Thread(p,"c");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
	}
}

class Print implements Runnable {
	final int MAX;
	AtomicInteger threadNumber = new AtomicInteger(0);
	AtomicInteger sharedNumber = new AtomicInteger(0);
	Print(int numberOfThreads) {
		MAX = numberOfThreads;
	}

	@Override
	public void run() {
		ThreadLocal<Integer> i = new ThreadLocal<>();
		i.set(threadNumber.incrementAndGet());
		while(true){
			try{
				if(((sharedNumber.get()+1)%MAX==(i.get()%MAX))){
					synchronized(this){
						System.out.println(sharedNumber.getAndIncrement());
					}
				}
				//System.out.println(Thread.currentThread().getName()+"--"+sharedNumber.get());
				Thread.sleep(500);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
	}
}
*/
