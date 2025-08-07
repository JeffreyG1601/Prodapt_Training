package d12c2;
import java.util.Scanner;
class EmpDetails {
			int uid, age;
			String name, designation;
			int salary;
			EmpDetails(){
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter User ID:");
				uid = sc.nextInt();
				sc.nextLine(); // Consume newline
				System.out.println("Enter Name:");
				name = sc.nextLine();
				System.out.println("Enter Age:");
				age = sc.nextInt();
				sc.nextLine(); // Consume newline
			}
		}
		class Clerk extends EmpDetails {
			int salary=10000;
			String designation = "Clerk";
			void display(){
				System.out.println("==========================");
				System.out.println("User ID: "+uid);
				System.out.println("Name: "+name);
				System.out.println("Age: "+age);
				System.out.println("Designation: "+designation);
				System.out.println("Salary: "+salary);
				System.out.println("==========================");
			}
			void raise() {
				int x;
				Scanner sc = new Scanner(System.in);
				System.out.println("==========================");
				System.out.println("Enter the amount to be raised");
				x = sc.nextInt();
				salary += x;
				System.out.println("Increased Salary :"+salary);
				System.out.println("==========================");
				
			}
		}
		class Tester extends EmpDetails {
			int salary=15000;
			String designation = "Tester";
			void display(){
				System.out.println("==========================");
				System.out.println("User ID: "+uid);
				System.out.println("Name: "+name);
				System.out.println("Age: "+age);
				System.out.println("Designation: "+designation);
				System.out.println("Salary: "+salary);
				System.out.println("==========================");
			}
			void raise() {
				int x;
				Scanner sc = new Scanner(System.in);
				System.out.println("==========================");
				System.out.println("Enter the amount to be raised");
				x = sc.nextInt();
				salary += x;
				System.out.println("Increased Salary :"+salary);
				System.out.println("==========================");
				
			}
		}
		class Manager extends EmpDetails {
			int salary=20000;
			String designation = "Manager";
			void display(){
				System.out.println("==========================");
				System.out.println("User ID: "+uid);
				System.out.println("Name: "+name);
				System.out.println("Age: "+age);
				System.out.println("Designation: "+designation);
				System.out.println("Salary: "+salary);
				System.out.println("==========================");
			}
			void raise() {
				int x;
				Scanner sc = new Scanner(System.in);
				System.out.println("==========================");
				System.out.println("Enter the amount to be raised");
				x = sc.nextInt();
				salary += x;
				System.out.println("Increased Salary :"+salary);
				System.out.println("==========================");
				
			}
		}
		class Developer extends EmpDetails {
			int salary=25000;
			String designation = "Developer";
			void display(){
				System.out.println("==========================");
				System.out.println("User ID: "+uid);
				System.out.println("Name: "+name);
				System.out.println("Age: "+age);
				System.out.println("Designation: "+designation);
				System.out.println("Salary: "+salary);
				System.out.println("==========================");
			}
			void raise() {
				int x;
				Scanner sc = new Scanner(System.in);
				System.out.println("==========================");
				System.out.println("Enter the amount to be raised");
				x = sc.nextInt();
				salary += x;
				System.out.println("Increased Salary :"+salary);
				System.out.println("==========================");
				
			}
		}
		public class D12c4 {
			public static void main(String[] args) {
				Scanner sc = new Scanner(System.in);
				Clerk emp1 = null;
				Tester emp2 = null;
				Manager emp3 = null;
				Developer emp4 = null;
				int i=0;
				while(i!=1){
				System.out.println("=======================================");
				System.out.println("Select one of the following options:");
				System.out.println("1. Create \n2. Display \n3. Raise \n4.Exit");
				System.out.println("=======================================");
				int choice = sc.nextInt();
				sc.nextLine(); 
				
				switch (choice) {
					case 1:

						System.out.println("Select the designation of the employee:");
						System.out.println("1. Clerk \n2. Tester \n3. Manager \n4. Developer");
						System.out.print("=======================================");
						System.out.println("Enter your choice:");
						int empChoice = sc.nextInt();
						sc.nextLine();
						switch (empChoice) {
							case 1:
								emp1 = new Clerk();
								break;
							case 2:
								emp2 = new Tester();
								break;
							case 3:
								emp3 = new Manager();
								break;
							case 4:
								emp4 = new Developer();
								break;
							default:
								System.out.println("Invalid choice.");
						}
						break;
						
					case 2:
						System.out.println("Select the designation of the employee to display:");
						System.out.println("1. Clerk \n2. Tester \n3. Manager \n4. Developer");
						System.out.print("=======================================");
						System.out.println("Enter your choice:");
						int displayChoice = sc.nextInt();
						sc.nextLine(); // Consume newline
						switch (displayChoice) {
							case 1:
								if (emp1 != null) emp1.display();
								else System.out.println("No Clerk details available. Please create first.");
								break;
							case 2:
								if (emp2 != null) emp2.display();
								else System.out.println("No Tester details available. Please create first.");
								break;
							case 3:
								if (emp3 != null) emp3.display();
								else System.out.println("No Manager details available. Please create first.");
								break;
							case 4:
								if (emp4 != null) emp4.display();
								else System.out.println("No Developer details available. Please create first.");
								break;
							default:
								System.out.println("Invalid choice.");
						}
						break;
					case 3:
						System.out.println("Select the designation of the employee:");
						System.out.println("1. Clerk \n2. Tester \n3. Manager \n4. Developer");
						System.out.print("=======================================");
						System.out.println("Enter your choice:");
						int ch = sc.nextInt();
						sc.nextLine();
						switch (ch) {
							case 1:
								emp1.raise();
								break;
							case 2:
								emp2.raise();
								break;
							case 3:
								emp3.raise();
								break;
							case 4:
								emp4.raise();
								break;
							default:
								System.out.println("Invalid choice.");
						}
						break;	
					case 4:
						i=1;

						System.out.println("Exiting...");
						break;
				}
			}
			}
		}
