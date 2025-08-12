package D15P1;
import java.util.Scanner;
class A{
	Scanner sc = new Scanner(System.in);
	void display() {
		System.out.println("Enter the age:-");
		int age = sc.nextInt();
		if(age>60) {
			throw new InvalidException("Age should not be more than 60");
		}
		else if(age <18) {
			throw new InvalidException("Age can't be less than 18");
		}
		else {
			System.out.println("Age is okay");
		}
	}
}

public class D15j1 {
	public static void main(String[] args) {
		try {
			A a = new A();
			a.display();
		}
		catch (InvalidException e) {
			System.out.println(e.getMessage());
		}
	}
}
class InvalidException extends RuntimeException{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	InvalidException(String msg){
		super(msg);
	}
}