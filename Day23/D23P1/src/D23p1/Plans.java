package D23p1;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.*;
class Plan{
	protected char Di;
	protected double Days,Data,Voice,SMS,Price,Validity,Overchargem,Overchargec;
	protected boolean Hotstar,Spotify,Amazon_Prime,Netflix;
	public Plan(double Days,double Data,char Di,double Voice,double SMS,double Price,double Validity,double Overchargem,double Overchargec,boolean Hotstar,boolean Spotify,boolean Amazon_Prime,boolean Netflix) {
		this.Days=Days;
		this.Data=Data;
		this.Di=Di;
		this.Voice=Voice;
		this.SMS=SMS;
		this.Price=Price;
		this.Overchargec=Overchargec;
		this.Validity=Validity;
		this.Overchargem=Overchargem;
		this.Hotstar=Hotstar;
		this.Amazon_Prime=Amazon_Prime;
		this.Spotify=Spotify;
		this.Netflix=Netflix;
		scale();
	}
	protected void scale() {
		if(this.Days!= 30.0) {
			double x = 30.0/this.Days;
			if(this.Data!= Double.POSITIVE_INFINITY) this.Data*=x;
			if(this.SMS!= Double.POSITIVE_INFINITY) this.SMS*=x;
			if(this.Voice!= Double.POSITIVE_INFINITY) this.Voice*=x;
			this.Price*=x;
			this.Days=30.0;
		}
	}
	public boolean OTTmatch(boolean H,boolean A,boolean S,boolean N) {
		if(H && this.Hotstar) return false;
		if(A && this.Hotstar) return false;
		if(S && this.Hotstar) return false;
		if(N && this.Hotstar) return false;
		else return true;
	}
	public int match(double udata,double uvoice,double usms) {
		int score=0;
		if (this.Data >= udata || this.Data == Double.POSITIVE_INFINITY) score++;
		if (this.Voice >= uvoice || this.Voice == Double.POSITIVE_INFINITY) score++;
		if (this.SMS >= usms || this.SMS == Double.POSITIVE_INFINITY) score++;
		return score;
	}
	public void display(String name) {
		System.out.println("==============================");
		System.out.println("\t\t"+name);
		System.out.println("Days    :"+Days);
		System.out.println("Data(GB):"+Data);
		System.out.println("Voice(mins):"+(Voice == Double.POSITIVE_INFINITY ? "Unlimited" : Voice));
		System.out.println("SMS        :"+SMS);
		System.out.println("Price(in RS):"+Price);
		System.out.println("OTT List:");
		if(Hotstar == true) System.out.println("Hotstar");
		if(Amazon_Prime== true) System.out.println("Amazon Prime");
		if(Spotify == true) System.out.println("Spotify");
		if(Netflix == true) System.out.println("Netflix");
	}
}
class Basic_Lite extends Plan{
	public Basic_Lite(){
		super(28.0,1.0,'D',100.0,0.20,249.0,28.0,0.0,0.75,false,false,false,false);	
		this.Days=30;
		
	}
}
class Saver30 extends Plan{
	public Saver30() {
		super(30.0,1.5,'D',300.0,100.0,499.0,30.0,0.2,0.75,true,false,false,false);
	}
}
class Unlimited_Talk_30 extends Plan{
	public Unlimited_Talk_30() {
		super(30.0,5.0,'T',(1.0/0.0),(1.0/0.0),650.0,30.0,0.0,0.0,false,true,false,false);
	}
}
class Data_Max_20 extends Plan{
	public Data_Max_20() {
		super(20.0,(1.0/0.0),'T',100.0,(1.0/0.0),749.0,20.0,0.0,0.75,true,false,false,false);
	}
}
class Student_Stream_56 extends Plan{
	public Student_Stream_56() {
		super(30.0,2.0,'D',300,200,435.0,30.0,0.20,0.75,false,true,false,false);
	}
}
class Family_Share extends Plan{
	public Family_Share() {
		super(28.0,50,'T',1000,500,500.0,28.0,0.20,0.6,false,false,true,false);
	}
}
class Data_Max_Plus extends Plan{
	public Data_Max_Plus() {
		super(30.0,(1.0/0.0),'T',300,200,1499.0,30.0,0.20,0.75,true,false,true,false);
	}
}
class Premium extends Plan{
	public Premium() {
		super(30.0,(1.0/0.0),'T',(1.0/0.0),(1.0/0.0),2999.0,30.0,0.0,0.0,true,true,true,true);
	}
}
public class Plans {
	public static void main(String[] args) {
		Plan[] plans= {
				new Basic_Lite(),
				new Saver30(),
				new Unlimited_Talk_30(),
				new Data_Max_20(),
				new Student_Stream_56(),
				new Family_Share(),
				new Data_Max_Plus(),
				new Premium()
		};
		for (Plan p : plans) {
			p.display(p.getClass().getSimpleName());
			System.out.println("\n");
		}
		double uvoice,usms,udata;
		String i,j;
		boolean H= false,S= false,A= false,N = false;
		Scanner sc= new Scanner(System.in);
		System.out.println("========================");
		System.out.println("Telecom Smart Plan Recommender");
		System.out.println("========================");
		System.out.println("\n\nEnter the Talk time you require(in minutes): ");
		uvoice = sc.nextDouble();
		System.out.println("Enter the SMS count that you want :");
		usms = sc.nextDouble();
		System.out.println("Enter the amount of Data you want (in GB):");
		udata=sc.nextDouble();
		System.out.println("Do you want any OTT(Y,N):");
		i=sc.next();
		if(i.equalsIgnoreCase("y")) {
			System.out.println("Do you want Hotstar(Y/N):");
			j=sc.next();
			if(j.equalsIgnoreCase("y")) {
				H=true;
				j=null;
			}
			System.out.println("Do you want Spotify(Y/N):");
			j=sc.next();
			if(j.equalsIgnoreCase("y")) {
				S=true;
				j=null;
			}	
			System.out.println("Do you want Amazon Prime(Y/N):");
			j=sc.next();
			if(j.equalsIgnoreCase("y")) {
				A=true;
				j=null;
			}	
			System.out.println("Do you want Netflix (Y/N):");
			j=sc.next();
			if(j.equalsIgnoreCase("y")) {
				N=true;
				j=null;
			}	
		}
		List<Plan> filtered = new ArrayList<>();
		for (Plan p: plans) {
			if(p.OTTmatch(H, A, S, N)) {
				filtered.add(p);
			}
		}
		filtered.sort((p1,p2)->{
			int s1 =p1.match(udata,uvoice,usms);
			int s2 =p2.match(udata,uvoice,usms);
			if (s1!=s2) return Integer.compare(s1, s2);
			return Double.compare(p1.Price,p2.Price);
		});
		System.out.println("Recommended Plans :");
		for (Plan p:filtered) {
			p.display(p.getClass().getSimpleName());
			System.out.println("\n");
		}
		sc.close();
	}
}
