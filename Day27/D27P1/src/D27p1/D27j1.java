package D27p1;

//1. Normal Interface
interface Vehicle {
 void start();
 void stop();
}

//2. Functional Interface
@FunctionalInterface
interface Greeting {
 void sayHello(String name);
}

//3. Marker Interface
interface SerializableMarker { }  // no methods

//4. Nested Interface
class OuterClass {
 interface InnerInterface {
     void display();
 }
}

//Implementation Classes
class Car implements Vehicle, SerializableMarker {
 @Override
 public void start() {
     System.out.println("Car started.");
 }
 @Override
 public void stop() {
     System.out.println("Car stopped.");
 }
}

//A class implementing nested interface
class InnerImpl implements OuterClass.InnerInterface {
 @Override
 public void display() {
     System.out.println("Inside Nested Interface implementation.");
 }
}

//Main Driver Class
public class D27j1 {
 public static void main(String[] args) {
     // Normal interface usage
     Vehicle car = new Car();
     car.start();
     car.stop();

     // Functional interface using Lambda
     Greeting g = (name) -> System.out.println("Hello, " + name + "!");
     g.sayHello("Jeffrey");

     // Marker interface usage (check with instanceof)
     if (car instanceof SerializableMarker) {
         System.out.println("Car is Serializable (marked).");
     }

     // Nested interface usage
     OuterClass.InnerInterface inner = new InnerImpl();
     inner.display();
 }
}
