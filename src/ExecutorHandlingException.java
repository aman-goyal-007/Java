import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ExecutorHandlingException {

	
	public static void main(String...args){
		ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 3, 1000, TimeUnit.SECONDS, new LinkedBlockingQueue<>(1), Executors.defaultThreadFactory(), new MyRejectedExecutionHandler());
		executor.execute(new MyThreadClass());
		executor.execute(new MyThreadClass());
		executor.execute(new MyThreadClass());
		executor.execute(new MyThreadClass());
	}
	
}


class MyThreadClass implements Runnable{

	@Override
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
	
}

class MyRejectedExecutionHandler implements RejectedExecutionHandler{

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.out.println("MyRejectedExecutionHandler");
	}
	
}
