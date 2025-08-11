package D14p2;

import java.util.Scanner;

public class D14j4 {
	public static void main(String[] args) {
		try {
			System.out.println("Enter A:- ");
			Scanner sc = new Scanner(System.in);
			int a = sc.nextInt();
		    System.out.println("Enter B:- ");
		    int b = sc.nextInt();
		    int c = a/b;
		    System.out.println("Result:- "+c);
		}
		catch(ArithmeticException e) {
			System.out.println("Can't divide by 0 ");
		}
	}
}
