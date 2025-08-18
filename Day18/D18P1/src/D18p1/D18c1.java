package D18p1;
class A extends Thread{
	public void run() {
		for (int i=0;i<=10;i++) {
			System.out.println("I:"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class B extends Thread{
	public void run() {
		for (int i=0;i<=10;i++) {
			System.out.println("	J:"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class C extends Thread{
	public void run() {
		for (int i=0;i<=10;i++) {
			System.out.println("		K:"+i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
public class D18c1 {
	public static void main(String[] args) {
		A a = new A();a.start();
		B b = new B();b.start();
		C c = new C();c.start();
	}
}
