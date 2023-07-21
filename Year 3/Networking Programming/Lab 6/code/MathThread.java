class MathThread {
	 public static void main(String [] args ) {
		 MathPow mp = new MathPow();
		 MathSqrt ms  = new MathSqrt();
		 MathFloor mf = new MathFloor();
		 mp.start();
		 ms.start();
		 mf.start();
	}	}

class MathPow extends Thread {
	 public void run() {
	     System.out.println( "Calculating Power  " + Math.pow(2,3) );
	}	}

class MathSqrt extends Thread {
	 public void run() {
		 System.out.println( "Calculating Sqrt   " + Math.sqrt(900) );
	}	}

	
class MathFloor extends Thread {
	 public void run() {
		 System.out.println("Calculating Floor   " + Math.floor(9.4) );
	}	}