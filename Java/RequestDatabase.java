import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;
public class RequestDatabase {
	public static Person getPerson(int r) throws SQLException
	{
		Connection m_Connection = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/game.db");    
	    Statement m_Statement = m_Connection.createStatement();
	    String query = "SELECT * FROM people WHERE id ='" + r +"'";
	    ResultSet m_ResultSet = m_Statement.executeQuery(query);
	    Person p = new Person();
	    while (m_ResultSet.next() )
	    {	
	    	String fname = m_ResultSet.getString(2);
	    	String sname = m_ResultSet.getString(3);
	    	int age = m_ResultSet.getInt(4);
	    	String g = m_ResultSet.getString(6);
	    	String jobs = m_ResultSet.getString(7);
	    	int famous = m_ResultSet.getInt(8);
	    	BigDecimal moneyBD = m_ResultSet.getBigDecimal(8);
	    	p = new Person(fname, sname, age, jobs, famous, g.charAt(0), moneyBD);
	    }
	    return p;
	}
	
	public static Event getEvent(int i) throws SQLException
	{
		Event e = new Event();
		Connection m_Connection = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/game.db");    
	    Statement m_Statement = m_Connection.createStatement();
		String query = "SELECT * FROM events WHERE id = '" + i + "'";
		ResultSet m_ResultSet = m_Statement.executeQuery(query);
		while(m_ResultSet.next())
		{
			int id = m_ResultSet.getInt(1);
			String title = m_ResultSet.getString(2);
			int min = m_ResultSet.getInt(3);
			int max = m_ResultSet.getInt(4);
			boolean alive = m_ResultSet.getBoolean(5);
			String event = m_ResultSet.getString(7);
			String eventOC = m_ResultSet.getString(8);
			e = new Event(id, title, min, max, alive, event, eventOC);
			
		}
		return e;
	}
}
