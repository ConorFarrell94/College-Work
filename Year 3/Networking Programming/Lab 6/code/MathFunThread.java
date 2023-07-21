import java.lang.Math;
class MathSin extends Thread {
	 public double deg;
	 public double res;
	 public MathSin(int degree) {
			deg = degree;
		}
	 public void run() {
		 System.out.println("Executing sin of" + deg);
		 double Deg2Rad = Math.toRadians(deg);
		 res = Math.sin(Deg2Rad);
		 System.out.println("Exit from MathSin. Res = "+res);
	}	}

class MathCos extends Thread {
	 public double deg;
	 public double res;
	 public MathCos(int degree) {
		 deg = degree;
	    }
	public void run() {
		 System.out.println("Executing cos of" + deg);
		 double Deg2Rad = Math.toRadians(deg);
		 res = Math.cos(Deg2Rad);
			System.out.println("Exit from MathCos. Res = "+res);
	}	}
class MathTan extends Thread {
		public double deg;
		public double res;
		public MathTan(int degree) {
		 deg = degree;
		}
	 public void run() {
		 System.out.println("Executing tan of" + deg);
		 double Deg2Rad = Math.toRadians(deg);
		 res = Math.tan(Deg2Rad);
		 System.out.println("Exit from MathTan. Res = "+res);
	}	}
	
class MathFunThread {
	 public static void main(String args[]) {
		 MathSin st = new MathSin(45);
		 MathCos ct = new MathCos(60);
		 MathTan tt = new MathTan(30);
		 st.start();
		 ct.start();
		 tt.start();
		 try { // wait for completion of all thread and then sum
		     st.join();
			 ct.join(); //wait for completion of MathCos object
			 tt.join();
			 double z = st.res + ct.res + tt.res;
			 System.out.println("Sum of sin cos tan = " +z);
			}
		 catch(Exception e) {
			 System.out.println(e);}
	}	}