
import java.util.ArrayList;
import java.math.BigDecimal;

public class Main
{
	
	
	public static void main(String[] args){
		ArrayList<Person> people = new ArrayList<Person>();
		ArrayList<Job> jobs = new ArrayList<Job>();
		Job j1 = new Job("President", "Google", BigDecimal.valueOf(80000));
		Job j2 = new Job("Vice-President", "Google", BigDecimal.valueOf(60000));
		jobs.add(j1);
		jobs.add(j2);
		Player player = new Player("Peter", 'M', j2);
 		Person p1 = new Person("John", "Doe", 30, j1, 1, 10, 'M');
		System.out.println("The person's name is " + p1.getfName() + " " + p1.getsName());
		Person p2 = new Person("Jane", "Doe", 20, j2, 1, 10, 'F');
		System.out.println("The person's name is " + p2.getfName() + " " + p2.getsName());
		Person p3 = new Person();
		people.add(p1);
		people.add(p2);
		people.add(p3);
		/*for(Person p : people)
		{
			System.out.println("The person's name is " + p.getfName() + " " + p.getsName());
		}*/
		System.out.println(p1.toString());
		System.out.println(player.toString());
	}
}