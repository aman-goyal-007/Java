import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;



public class UncaughtHandler {
	public static void main(String...args) {
	Thread t = new Thread(new Aman());
	t.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {
		
		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("Hi");
			
		}
	});
	t.start();
	
	}


	public static class CustomThreadFactory implements ThreadFactory{
		private static final ThreadFactory defaultFactory = Executors.defaultThreadFactory();
		private final UncaughtExceptionHandler handler;
		public CustomThreadFactory(UncaughtExceptionHandler handler) {
			this.handler = handler;
		}
		
		
		
		@Override
		public Thread newThread(Runnable r) {
			Thread thread = defaultFactory.newThread(r);
			thread.setUncaughtExceptionHandler(handler);
			return null;
	}
	
	public static class MyUncaughtExceptionHandler implements UncaughtExceptionHandler{

		@Override
		public void uncaughtException(Thread t, Throwable e) {
			System.out.println("Inside Uncaught Exception Handler");
			
		}
		
	}
	
	}
}

class Aman implements Runnable{

	@Override
	public void run() {
		throw new NullPointerException("Hehe");

	}

}

