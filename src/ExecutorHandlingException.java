import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorHandlingException {

	
	public static void main(String...args) throws InterruptedException{
		MyThreadClass object = new MyThreadClass();
		Thread t1 = new Thread(object,"First");
		Thread t2 = new Thread(object,"Second");
		Thread t3 = new Thread(object,"Third");
		Thread t4 = new Thread(object,"Fourth");
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1), new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				// TODO Auto-generated method stub
				return null;
			}
		}, new MyRejectedExecutionHandler());
//		Executor executor = Executors.newFixedThreadPool(2);
		executor.execute(t1);
		executor.execute(t2);
		executor.execute(t3);
		executor.execute(t4);
	}
	
}


class MyThreadClass implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
		throw new RuntimeException();
	}
	
}

class MyRejectedExecutionHandler implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("MyRejectedExecutionHandler");
	}
	
}
