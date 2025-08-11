package D14p2;
import java.io.*;
public class D14j5 {
	public static void main(String[] args) {
		A();
	}
	static void A() throws FileNotFoundException
	{
		B();
		System.out.println("Hi A");
	}
	static void B() throws FileNotFoundException
	{
		C();
		System.out.println("Hi B");
	}
	static void C() throws FileNotFoundException
	{
		File f = new File("T.txt");
		System.out.println("Hi C");
		FileReader fr = new FileReader(f);
	}
}
