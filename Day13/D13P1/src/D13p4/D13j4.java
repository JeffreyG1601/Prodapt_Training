package D13p4;
abstract class Bank{
	abstract void roi();
	abstract void ATM();
}
class SBI extends Bank{
	@Override
	void roi() {
		System.out.println("ROI : 10%");
	}
	@Override
	void ATM() {
		System.out.println("Debit ");
	}
}
class Axis extends Bank{
	@Override
	void roi() {
		System.out.println("ROI : 12%");
	}
	@Override
	void ATM() {
		System.out.println("Debit , Credit ");
	}
}
 class D13j4{
	 public static void main(String[] args) {
		Axis a = new Axis();a.roi();
		SBI s= new SBI();s.roi();
	}
 }