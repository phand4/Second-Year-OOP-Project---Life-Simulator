import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
*/

/**
*
* @author Peter
*
*/
public class CreateDatabase {

	/**
	* @param args the command line arguments
	*/
	
	public CreateDatabase()
	{
		createNewDatabase("game.db");
		createNewTable("game.db", "firstNames", "name");
        createNewTable("game.db", "surnames", "name");
        createNewTable("game.db", "occupations", "jobTitle");
        createNewTable("game.db", "locations", "building");
        createNewTable("game.db", "people", "fName");    
        createEventTable("game.db", "events");
        createNPCEventTable("game.db", "npcEvents");
	}
	    
	public static void createNewDatabase(String fileName) {
		String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;    
	    try (Connection conn = DriverManager.getConnection(url)) 
	    {
	    	if(conn != null) 
	    	{
	    		DatabaseMetaData meta = conn.getMetaData();
	            System.out.println("The God's name is " + meta.getDriverName());
	            System.out.println("A world has been created.");
	        }
	    } 
	    catch (SQLException e) 
	    {
	    	System.out.println(e.getMessage());
	    }          
	}
	    
	public static void createNewTable(String fileName, String tableName, String colName)
	{
		String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
	    String sql = "CREATE TABLE IF NOT EXISTS '" + tableName + "' (\n"
           + "id integer PRIMARY KEY, \n"
           + "'"+ colName + "'text"
           + (("occupations".equals(tableName))?", \n company text, \n salary Decimal\n" : 
             (("locations".equals(tableName))?", \n location text, \n company text\n" : 
             (("event".equals(tableName))?", \n eventKey integer NOT NULL\n" :
             (("eventDesc".equals(tableName))?", \n eventData text NOT NULL, \n eventEffect text NOT NULL, \n eventKey integer NOT NULL\n" : 
             (("people".equals(tableName))?", \n sName text NOT NULL, \n age integer, \n gender text, \n isAlive boolean, \n job text, \n fame integer, \n money BigDecimal\n" : "")))))                                      
           + ");";
    
	    try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) 
	    {
	    	stmt.execute(sql);
	    } 
	    catch (SQLException e) 
	    {
	    	System.out.println(e.getMessage());
	    }
	 }
	 public static void createEventTable(String fileName, String tableName)
	 {
	    String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
	    String sql = "CREATE TABLE IF NOT EXISTS '" + tableName + "' (\n"
	    				+ "id integer PRIMARY KEY, \n"
	    				+ "eventTitle text, \n"
	    				+ "minAge integer, \n"
	    				+ "maxAge integer, \n"
	    				+ "interactive boolean, \n"
	    				+ "person text, \n"
	    				+ "event text, \n"
	    				+ "eventOutcome text\n"
	    				+ ");";
	        
	    try (Connection conn = DriverManager.getConnection(url); Statement stmt = conn.createStatement()) 
	    {
	      	stmt.execute(sql);
	    } 
	    catch (SQLException e) 
	    {
	      	System.out.println(e.getMessage());
	    }      
	 }
	 
	 public static void createNPCEventTable(String fileName, String tableName){
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        String sql = "CREATE TABLE IF NOT EXISTS '" + tableName + "' (\n"
                   + "id integer PRIMARY KEY, \n"
                   + "npcEventTitle text, \n"
                   + "npcEvent VARCHAR(300), \n"
                   + "npcEventOutcome text\n"
                   + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
        		Statement stmt = conn.createStatement()) 
        {
             stmt.execute(sql);
        } 
        catch (SQLException e) {
            System.out.println(e.getMessage());
        }      
	 }
}