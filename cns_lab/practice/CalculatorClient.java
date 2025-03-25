import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry; 
import java.util.Scanner; 

public class CalculatorClient { 
public static void main(String[] args) { 
try { 
Registry registry = LocateRegistry.getRegistry("localhost", 1099); 
Calculator stub = (Calculator) registry.lookup("CalculatorService"); 
Scanner scanner = new Scanner(System.in); 
System.out.print("Enter first number: "); 
int num1 = scanner.nextInt(); 
System.out.print("Enter second number: "); 
int num2 = scanner.nextInt(); 
int sum = stub.add(num1, num2); 
int difference = stub.subtract(num1, num2); 
System.out.println("Addition Result: " + sum); 
System.out.println("Subtraction Result: " + difference); 
} catch (Exception e) { 
e.printStackTrace(); 
}}}
