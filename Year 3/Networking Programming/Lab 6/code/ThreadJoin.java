class ThreadJoin extends Thread {
	 public void run() {
		 System.out.println("Thread started:::"+Thread.currentThread().getName());
		}
	 public static void main(String [] args )  throws Exception {
		 ThreadJoin thread1 = new ThreadJoin();
		 ThreadJoin thread2 = new ThreadJoin();
		 ThreadJoin thread3 = new ThreadJoin();
		 thread1.start();
		 thread1.join();
		 System.out.println("Thread-0 status:::" +thread1.isAlive());
		 thread2.start();
		 thread2.join();
		 System.out.println("Thread-1 status:::" +thread2.isAlive());
		 thread3.start();
		 thread3.join();
		 System.out.println("Thread-2 status:::" +thread3.isAlive());
		 System.out.println("All threads are dead, exiting main thread");
	}	}
	