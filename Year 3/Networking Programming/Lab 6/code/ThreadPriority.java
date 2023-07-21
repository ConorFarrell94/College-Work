class A extends Thread {
	 public void run() {
	     System.out.println("ThreadA in Run Function");
		 for(int i = 1; i <= 4; i++) 
			 System.out.println("\t From ThreadA: i= " + i);
		 System.out.println("Exit from A");
	}	}
	
class B extends Thread {
	 public void run() {
		 System.out.println("ThreadB in Run Function");
		 for(int j = 1; j <= 4; j++) 
			 System.out.println("\t From ThreadB: j= " + j);
		 System.out.println("Exit from B");
	}	}
	
class C extends Thread {
	 public void run() {
	     System.out.println("ThreadC in Run Function");
	     for(int k = 1; k <= 4; k++) 
			 System.out.println("\t From ThreadC: k= " + k);
		 System.out.println("Exit from C");
	}	}
	
public class ThreadPriority {
	 public static void main(String args[]) {
    	 A threadA = new A();
	     B threadB = new B();
		 C threadC = new C();
		 threadC.setPriority(Thread.MAX_PRIORITY);
		 threadB.setPriority(threadA.getPriority() + 1);
		 threadA.setPriority(Thread.MIN_PRIORITY);
		 System.out.println("Started Thread A");
		 threadA.start();
		 System.out.println("Started Thread B");
		 threadB.start();
		 System.out.println("Started Thread C");
		 threadC.start();
		 System.out.println("End of main thread");
	}   }