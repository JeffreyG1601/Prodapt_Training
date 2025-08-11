package D14p1;
sealed class Human permits Varun1,Varun2
{
	public void printname() {
		System.out.println("Default");
	}
}
non-sealed class Varun1 extends Human{
	public void printname() {
		System.out.println("Varun 1");
	}
}
non-sealed class Varun2 extends Human{
	public void printname() {
		System.out.println("Varun 2");
	}
}
final class Jeff extends Varun2 {
	public void printname() {
		System.out.println("Jeff ");
	}
}
public class D14j2 {
	public static void main(String[] args) {
		Varun1 x = new Varun1();
		Varun2 y = new Varun2();
		Jeff z = new Jeff();
		Human a = new Human();
		a.printname();
		x.printname();
		y.printname();
		z.printname();
		
	}
}
