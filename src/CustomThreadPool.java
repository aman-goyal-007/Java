
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;


interface Pool {

	void execute(Runnable r);
}

public class CustomThreadPool implements Pool{

	private BlockingQueue<Runnable> queue;
	private final int MAXPOOLSIZE;
	private AtomicInteger current = new AtomicInteger(0);
	private volatile boolean isShutdown=false;

	private CustomThreadPool(){
		MAXPOOLSIZE = 1;
	}

	public CustomThreadPool(int poolSize) {
		MAXPOOLSIZE=poolSize;
		queue=new LinkedBlockingQueue<>(MAXPOOLSIZE);
	}
	
	@Override
	public void execute(Runnable r) {
			try {
				if(current.get()!=MAXPOOLSIZE)
				{
					new Worker(queue).start();
					current.incrementAndGet();
				}
				queue.put(r);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
	}
	
	public void shutdown(){
		isShutdown=true;
	}
	
	class Worker extends Thread{
		BlockingQueue<Runnable> queue;
		
		Worker(BlockingQueue<Runnable> queue){
			this.queue=queue;
		}
		public void run(){
			while(true && !isShutdown){
				try{
					queue.take().run();
					System.out.println(Thread.currentThread().getName());
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
}
