import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class PullEvents 
{
	
	private ArrayList<Event> events = new ArrayList<Event>();
	
	private Connection connect() throws SQLException 
	{
	       // SQLite connection string
	       String url = "jdbc:sqlite:C://sqlite/db/game.db";
	       Connection conn = null;        
	       try 
	       {
	            conn = DriverManager.getConnection(url);
	       } 
	       catch (SQLException e) 
	       {
	           System.out.println(e.getMessage());
	       }
	       return conn;                    
	}
	 
	public ArrayList<Event> createArrayList() throws SQLException
	{
		String query = "SELECT * FROM events";
		ResultSet rs;
		try(Connection conn = this.connect())
		{
			Statement stmt = conn.createStatement();
			rs = stmt.executeQuery(query);
			while(rs.next())
			{
				//int id, String title, int min, int max, boolean interactive, Person p, String event
				int id = rs.getInt(1);
				String title = rs.getString(2);
				int min = rs.getInt(3);
				int max = rs.getInt(4);
				boolean interactive = rs.getBoolean(5);
				String evntDetails = rs.getString(6);
				String outcome = rs.getString(7);
				Event e = new Event(id, title, min, max, interactive, evntDetails, outcome);
				events.add(e);
			}
		}
		catch( Exception e )
		{
			e.printStackTrace();
		}
		
		
		return events;
	}
}
