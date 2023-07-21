class ThreadStatus extends Thread {
	 public void run() {
		 System.out.println("I am in Run Function and Thread Status "
		     + Thread.currentThread().isAlive());
		}
	 public static void main(String [] args )  throws Exception {
		 ThreadStatus t = new ThreadStatus();
		 t.start();
		 t.join();
		 System.out.println("I am in Main Function and Thread Status   " + t.isAlive());
	}   }