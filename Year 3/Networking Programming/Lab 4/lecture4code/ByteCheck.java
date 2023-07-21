//Java byte accepts a value between −128 and 127 
public class ByteCheck {
	  public static void main( String[] args ){
			byte b = (byte) 129;
			System.out.println(b);
			System.out.println((b < 0 ? 256 + b : b));
		}	
	}