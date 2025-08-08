package D13p4;

class Priv{
	private int id;
	private String name;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
public class D13j5 {
	public static void main(String[] args) {
		Priv e = new Priv();
		e.setId(10);
		e.setName("Jeff");
		System.out.println("ID:"+e.getId()+"\nName:"+e.getName());
	}
}
