class Counter{
     int count = 0;
     public synchronized void add(){
          this.count ++;     
          System.err.println(Thread.currentThread().getName() + "updated the counter to "+this.count);
		}
    }          
public class MyThread3 extends Thread{
	 private Counter counter;
	 public MyThread3(Counter counter){
         this.counter = counter;
        }
     public void run() {
          for(int i=0; i<5; i++)
         counter.add();
        }
     public static void main(String[] args){
         Counter counter = new Counter();
         Thread  threadA = new MyThread3(counter);
         Thread  threadB = new MyThread3(counter);
         threadA.start();
         threadB.start();
        }
    }
	