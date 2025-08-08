package D13p3;

//Interface
interface Animal {
 void eat(); // Abstract method in interface (always public & abstract)
 void sleep();
}

//Abstract Class
abstract class Mammal implements Animal {
 protected String name;

 public Mammal(String name) {
     this.name = name;
 }

 // Abstract method specific to Mammals
 abstract void makeSound();

 // Concrete method in abstract class
 public void sleep() {
     System.out.println(name + " is sleeping.");
 }
}

//Normal (Concrete) Class
class Dog extends Mammal {
 public Dog(String name) {
     super(name);
 }

 // Implementing abstract method from Mammal
 @Override
 void makeSound() {
     System.out.println(name + " says: Woof Woof!");
 }

 // Implementing method from interface
 @Override
 public void eat() {
     System.out.println(name + " is eating dog food.");
 }
}

public class D13j3 {
 public static void main(String[] args) {
     Dog dog = new Dog("Buddy");

     dog.eat();       // From interface
     dog.makeSound(); // From abstract class
     dog.sleep();     // Concrete method from abstract class
 }
}
