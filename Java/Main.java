
import java.util.Random;
import java.util.ArrayList;

public class Main
{
	public static void main(String[] args){
		Person p1 = new Person("John", "Doe", 30, "President", 1, 10);
		System.out.println("The person's name is " + p1.getfName() + " " + p1.getsName());
		Person p2 = new Person("Jane", "Doe", 20, "VicePresident", 1, 10);
		System.out.println("The person's name is " + p2.getfName() + " " + p2.getsName());
		Person p3 = new Person();
	}
}