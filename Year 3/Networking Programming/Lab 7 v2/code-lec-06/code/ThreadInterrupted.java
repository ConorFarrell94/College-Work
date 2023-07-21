class ThreadInterrupted extends Thread{  
      public void run(){  
			int counter =0;
			while(!Thread.interrupted())
			 {
			 System.out.println(counter++);
            }
		}  
    public static void main(String args[]) throws Exception {  
         ThreadInterrupted t1=new ThreadInterrupted();  
         t1.start();     
         System.in.read();   
         t1.interrupt();  
        }  
    }  