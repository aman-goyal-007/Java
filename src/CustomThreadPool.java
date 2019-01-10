
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


 interface Pool {

	void execute(Runnable r);
}

public class CustomThreadPool implements Pool{

	BlockingQueue<Runnable> queue;
	final int MAXPOOLSIZE;
	volatile int current=0;
	volatile boolean isShutdown=false;
	public CustomThreadPool(int poolSize) {
		MAXPOOLSIZE=poolSize;
		queue=new LinkedBlockingQueue<>(MAXPOOLSIZE);
	}
	
	@Override
	public void execute(Runnable r) {
			try {
				if(current!=MAXPOOLSIZE)
				{
					new Worker(queue).start();
					current++;
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
					
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
		
	


	
	

}
