import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class InitialiseGame {
	
	private ArrayList<Person> people;
	private ArrayList<Event> events;
	
	public InitialiseGame() throws IOException
	{
		initialiseConnection();
		insertData();
	}
	
	public void initialiseConnection()
	{
		System.out.println("I'm fucked here");
		CreateDatabase db = new CreateDatabase();
		
	}
	
	public void insertData() throws IOException
	{
		InsertData insert = new InsertData();
	}
	
	public void fillWorld() throws SQLException
	{
		fillWorld world = new fillWorld();
		people = world.createPopulation();
		PullEvents dbevents = new PullEvents();
		events = dbevents.createArrayList();
	}
	
	public ArrayList<Person> returnPeopleArray()
	{
		return people;
	}
	
	public ArrayList<Event> returnEventArray()
	{
		return events;
	}
	
	public static void main(String[] args)
	{
		
	}
}
