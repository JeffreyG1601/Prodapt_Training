package D13p1;
class A{
	A(){
		System.out.println("Printing Area");
		System.out.println("=======================");
	}
	public void area() {
		System.out.println("Area of the Object being calculated.....");
		System.out.println("========================");
	}
	public void area(int r) {
		System.out.println("Area of Circle :"+ 3.14*r*r);
		System.out.println("========================");
	}
	public void area(int l,int b) {
		System.out.println("Area of Rectangle :"+ l*b);
		System.out.println("========================");
	}
}
class B extends A{
	@Override
	public void area(int s) {
		System.out.println("Area of square :"+ s*s);
		System.out.println("========================");
	}
}
public class D13j1 {

	public static void main(String[] args) {
		A a = new A();
		a.area(10);
		a.area(20,10);
		B b= new B();
		b.area(10);
	}

}
