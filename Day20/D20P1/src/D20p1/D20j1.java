package D20p1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
public class D20j1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList a = new ArrayList(5);
		a.add(100);
		a.add(200);
		a.add(500);
		a.add(400);
		a.add(300);
		System.out.println(a);
		for (int i=0;i<a.size();i++) {
			System.out.println(a.get(i));
		}
		Iterator i = a.iterator();
		while(i.hasNext()) {
			System.out.println(i.next());
		}
	}

}
