class A{
	static int a =100;
	int x=101;
	public static void display(){
		System.out.println("Hi Method!!!!");
}
	void show(){
		System.out.println("Hi Show Method!!!!");
	}
}
public class d3d
{
	public static void main(String args[])
	{
		A a=new A();
		a.show();
		System.out.println(a.x);
		System.out.println("Hi Demo!!!!");
		System.out.println(A.a);
		A.display();
	}
}