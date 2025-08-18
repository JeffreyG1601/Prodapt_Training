package D18p1;
class person implements Runnable
{
	@Override
	public synchronized void run() {
		for (int i=0;i<=10;i++) {
			try {
				System.out.println(Thread.currentThread().getName()+": "+i);
				Thread.sleep(1000);
			}
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
public class D18c5 {
	public static void main(String[] args) {
		person p = new person();
		Thread p1= new Thread(p);
		Thread p2=new Thread(p);
		p1.setName("Varun");
		p2.setName("Yashwanth");
		p1.start();p2.start();
	}

}
