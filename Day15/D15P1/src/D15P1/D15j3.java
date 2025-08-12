package D15P1;
import java.io.IOException;
class E{
	
	void display() throws InvalidException1, IOException,ClassNotFoundException
	{
		for(int i=0;i<=20;i++) {
			System.out.println("I :"+i);
			if(i==19) throw new InvalidException1("Invalid age ");		// raise exp manully
			if(i==17) throw new NullPointerException();
			if(i==15) throw new IOException();
			if(i==14) throw new ClassNotFoundException();
		}
	}
}
public class D15j3 {
	public static void main(String[] args) {
		try {																// to ad dthe risky code
			E a = new E();
			a.display();
		}
		catch (NullPointerException e) {									// to handle to give alternate msg
			System.out.println("NullPointerException");
		}
		catch (InvalidException1 e) {
			System.out.println(e.getMessage());
		}
		
		catch (IOException e) {
			System.out.println("IOException");
		}
		catch (ClassNotFoundException e) {
			System.out.println("SQLException");
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		finally {															// finally will execute alsways
			System.out.println("Thank you");
		}
	}
}
class InvalidException1 extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InvalidException1(String msg){
		super(msg);
	}
}