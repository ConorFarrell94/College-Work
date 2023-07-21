class MyThread2 implements Runnable {
	 public void run() {
		 System.out.println("thread is running");
		}
	 public static void main(String [] args ) {
		 Thread t = new Thread(new MyThread2());
		 t.start();
		 System.out.println("Main function is ended");
		}
	}