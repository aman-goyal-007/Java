
public class Racer implements Runnable{
	public void run() {
		System.out.println(Thread.currentThread().getName());
	}
	
	public static void main(String...args) {
		Thread t1= new Thread(new Racer());
		Thread t2= new Thread(new Racer());
		t1.start();
		t2.start();
	}
}
