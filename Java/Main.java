
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;

public class Main
{
	public static void deleteDB()
	{
		try
		{
			File file = new File("C:\\sqlite\\db\\game.db");
			if(file.delete())
			{
				System.out.println("Starting anew");
			}
			else
			{
				System.out.println("Deletion failed.");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws SQLException, IOException{
		deleteDB();
		InitialiseGame setup = new InitialiseGame();
		setup.fillWorld();
		ArrayList<Person> people = new ArrayList<Person>();
		ArrayList<Event> events = new ArrayList<Event>();
		
		people = setup.returnPeopleArray();
		events = setup.returnEventArray();
		/*
		Player player = new Player("Peter", 'M', j2);
 		Person p1 = new Person("John", "Doe", 30, "President", 1, 10, 'M');
		System.out.println("The person's name is " + p1.getfName() + " " + p1.getsName());
		Person p2 = new Person("Jane", "Doe", 20, j2, 1, 10, 'F');
		System.out.println("The person's name is " + p2.getfName() + " " + p2.getsName());
		Person p3 = new Person();
		people.add(p1);
		people.add(p2);
		people.add(p3);*/
		/*for(Person p : people)
		{
			System.out.println("The person's name is " + p.getfName() + " " + p.getsName());
		}*/
		//System.out.println(p1.toString());
		//System.out.println(player.toString());
		
		for(int i = 0; i < 10; i++)
		{
			System.out.println((people.get(i)).toString());
		}
		System.out.println("");
		
		for(int i = 0; i < 5; i++)
		{
			System.out.println((events.get(i)).printEvent());
		}
	}
}