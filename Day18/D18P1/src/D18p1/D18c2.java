package D18p1;
class Car extends Thread{
	public void run() {
		for (int i =0;i<=10;i++) {
			try {
				if(Thread.currentThread().getName().equals("BMW")) {
					System.out.println(Thread.currentThread().getName()+": "+i);
				}
				if(Thread.currentThread().getName().equals("Benz")) {
					System.out.println("	"+Thread.currentThread().getName()+": "+i);
				}
				Thread.sleep(1000);
			}
			catch (Exception e) {
		}
	}
}

public class D18c2 {
	public static void main(String[] args) {
		Car a = new Car();
		Thread t1= new Thread(a);
		Thread t2= new Thread(a);
		t1.start();
		t2.start();
		t1.setName("Bmw");
		t2.setName("Benz");
		for (int i =0;i<=10;i++) {
			try {
				System.out.println("           K:"+i);
				if(i==4) {t1.suspend();}
				if(i==6) {t1.resume();}
			}
			catch(Exception e) {}
		}
	}
}
}
