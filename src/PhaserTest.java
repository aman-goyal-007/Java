import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Phaser;

public class PhaserTest {
	
	public static void main(String...args) throws InterruptedException{
		Phaser phaser = new Phaser(1);
		MyTest test = new MyTest(phaser);
		Thread t1 = new Thread(test,"First Thread");
		Thread t2 = new Thread(test,"Second Thread");
		Thread t3 = new Thread(test,"Third Thread");
		t1.start();
		t2.start();
		t3.start();
		
	}

}


class MyTest implements Runnable{
	Phaser phaser;
	public MyTest(Phaser phaser) {
		this.phaser=phaser;
	}
	@Override
	public void run() {
		System.out.println();
		phaser.register();
		phaser.awaitAdvance(2);
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Done : "+Thread.currentThread().getName());
		
		
	}
}
