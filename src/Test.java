import java.util.*;
import java.util.concurrent.*;

public class Test {
    public static void main(String[] args) {
    	Print p = new Print();
    	Thread t1 = new Thread(new MyOwn(p,true));
    	Thread t2 = new Thread(new MyOwn(p,false));
    	t1.start();
    	t2.start();
    }
    
    
}

class MyOwn implements Runnable{
	private boolean isEven = false;
	Print p;
	MyOwn(Print pp,boolean b){
		p = pp;
		isEven = b;
		
	}
	@Override
	public void run() {
		int number = isEven?0:1;
		while(true && number<10) {
			if(isEven) {
				p.printEven(number);
			}else {
				p.printOdd(number);
			}
			number+=2;
		}
	}
	
}
class Print{
	volatile boolean isEven=true;
	
	synchronized public void printEven(int number){
		while(!isEven) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(number +Thread.currentThread().getName());
		isEven = false;
		notifyAll();
	}
	synchronized public void printOdd(int number){
		while(isEven) {
			try {
				wait();
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.println(number +Thread.currentThread().getName());
		isEven = true;
		notifyAll();
	}
}