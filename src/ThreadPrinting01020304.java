public class ThreadPrinting01020304 {
    public static void main(String[] args) {
        Print print = new Print();

        Thread t1 = new Thread(new MultiPrintThread(print, true));
        Thread t2 = new Thread(new MultiPrintThread(print, false));
        t1.start();
        t2.start();
    }

    static class MultiPrintThread implements Runnable{

        Print print;
        private boolean isEvenNumber;
        int max=10;

        public MultiPrintThread(Print print, boolean isEvenNumber) {
            this.print = print;
            this.isEvenNumber = isEvenNumber;
        }

        @Override
        public void run() {
            int number = isEvenNumber?0:1;
            while(number<=5){
                if(isEvenNumber)print.printEven(number);
                else {
                    print.printOdd(number);
                    number += 1;
                }
            }
        }
    }

    static class Print{
        volatile boolean isEven=true;
        synchronized public void printEven(int number){
            while(!isEven){
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+number);
            isEven = false;
            notifyAll();
        }

        synchronized public void printOdd(int number){
            while(isEven){
                try {
                    wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println(Thread.currentThread().getName()+":"+number);
            isEven = true;
            notifyAll();
        }

    }
}

