public class ThreadTest {
		public static void main( String args[] ) {
		 PrintThread thread1, thread2, thread3, thread4;
		 thread1 = new PrintThread( "thread1" );
		 thread2 = new PrintThread( "thread2" );
		 thread3 = new PrintThread( "thread3" );
		 thread4 = new PrintThread( "thread4" );
		 thread1.start();
		 thread2.start();
		 thread3.start();
		 thread4.start();
    }   }

class PrintThread extends Thread {
	 public PrintThread( String name ){
		 super( name );
		 System.out.println( getName() + " Constructor" );
		}
	 public void run(){
		 System.out.println( getName() + " is Running" );
	}   }