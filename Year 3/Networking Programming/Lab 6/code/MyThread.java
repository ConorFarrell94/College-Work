class MyThread extends Thread {
	 public void run() {
		 System.out.println("thread is running");
		}
	 public static void main(String [] args ) {
		 MyThread t = new MyThread();
		 t.start();
		 System.out.println("Main function is ended");
		}
	}