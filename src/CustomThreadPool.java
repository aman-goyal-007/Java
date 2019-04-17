
import java.util.concurrent.LinkedBlockingQueue;



public class CustomThreadPool {

	private final LinkedBlockingQueue<Runnable> queue;
	private final int poolSize;
	private final Worker[] workers;
	private volatile boolean isShutdown = false;

	public CustomThreadPool(int poolSize) {
		this.poolSize = poolSize;
		queue = new LinkedBlockingQueue<>();
		workers = new Worker[poolSize];
		for(int i=0;i<poolSize;i++){
			workers[i] = new Worker();
			workers[i].start();
		}
	}
	
	public void execute(Runnable task) {
		if(!isShutdown) {
			synchronized (queue) {
				queue.add(task);
				queue.notify();
			}
		}
	}


	public void shutdown(){
		isShutdown = true;
		/*while(true) {
			while (!queue.isEmpty()) {
			}

			for(int i=0;i<poolSize;i++){
				workers[i].interrupt();

			}
		}*/
	}

	private class Worker extends Thread{

		public void run(){
			Runnable task;
			while(!isShutdown) {
				synchronized (queue) {
					while (queue.isEmpty()) {
						try {
							queue.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
					System.out.println(Thread.currentThread().getName());
					task = queue.poll();
				}

				try {
					task.run();
				} catch (Exception e) {
				}
			}
		}
	}
}
