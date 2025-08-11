package D14p1;

class car{
	private int model =2000;
	private String colour="bLUE";
	final int speed=200;
	final void gear() {
		System.out.println("5th Gear");
	}
}

class BMW extends car{
	void ABC() {
		System.out.println("6th Gear");
	}
//	@Override
//	void gear() {
//		System.out.println("6th");
//	}
}

public class D14j1 {

	public static void main(String[] args) {
		BMW x = new BMW();
		x.ABC();
		car y = new car();
		y.gear();

	}

}
