import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Peter
 */
public class fillWorld {

    
    private Connection connect() throws SQLException {
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
     
    public ArrayList<Person> createPopulation() throws SQLException {

        String data = null;
        String data2 = null;
        Integer data3 =null;
        Boolean isAlive = true;
        String job = null;
        Integer fame = null;
        BigDecimal money = null;
        ArrayList<Person> people = new ArrayList<Person>();
        char[] g = new char[40];
        int i = 0;
        while(i < 40){

            Random r = new Random();
            int x = r.nextInt(100);
            String query = "SELECT name FROM firstNames WHERE id = '" + x + "'";
            try(Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)){
                data = rs.getString(1);
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }
            if(x > 50)
            {
            	g[i] = 'F';
            }
            else
            {
            	g[i] = 'M';
            }
            x = r.nextInt(100);           
            query = "SELECT name FROM surnames WHERE id = '" + x + "'";
            try(Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)){
                data2 = rs.getString(1);
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }           

            x = r.nextInt(100);
            data3 = x;
            
            isAlive= true;
            
            x = r.nextInt(10);           
            query = "SELECT jobTitle FROM occupations WHERE id = '" + x + "'";
            try(Connection conn = this.connect();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(query)){
                job = rs.getString(1);
            } catch(SQLException e){
                System.out.println(e.getMessage());
            }      
            
            fame = r.nextInt(100);
            
            query = "SELECT salary FROM occupations WHERE jobTitle = '" + job + "'";
            try(Connection conn = this.connect();
                    Statement stmt = conn.createStatement();
                    ResultSet rs = stmt.executeQuery(query)){
                    money = rs.getBigDecimal(1);
            } catch(SQLException e){
                    System.out.println(e.getMessage());
            }      
            
            fillWorld app = new fillWorld();       
            app.generatePopulation(data, data2, data3, isAlive, job, fame, money);
            i++;
            
        }
	    Connection m_Connection = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/game.db");    
	    Statement m_Statement = m_Connection.createStatement();
	    String query = "SELECT * FROM people";
	    ResultSet m_ResultSet = m_Statement.executeQuery(query);
	    ResultSetMetaData rsMetaData = m_ResultSet.getMetaData();
	    int k = 0;
	    while (m_ResultSet.next() )
	    {	
	    	String fname = m_ResultSet.getString(2);
	    	String sname = m_ResultSet.getString(3);
	    	int age = m_ResultSet.getInt(4);
	    	String jobs = m_ResultSet.getString(6);
	    	int famous = m_ResultSet.getInt(7);
	    	BigDecimal moneyBD = m_ResultSet.getBigDecimal(8);
	    	Person p = new Person(fname, sname, age, jobs, famous, g[k], moneyBD);
	    	people.add(p);
	    	k++;
	    }       
       return people;
    }
    
    
    public void generatePopulation(String data, String data2, Integer data3, 
                                          Boolean isAlive, String job, Integer Fame, BigDecimal money) throws SQLException 
    {
        String sql = "INSERT into people (fName, sName, age, isAlive, job, fame, money) VALUES(?, ?, ?, ?, ?, ?, ?)";
      
         try(Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){  

            pstmt.setString(1, data);
            pstmt.setString(2, data2);
            pstmt.setInt(3, data3);
            pstmt.setBoolean(4, isAlive);
            pstmt.setString(5, job);
            pstmt.setInt(6, Fame);
            pstmt.setBigDecimal(7, money);           
            pstmt.executeUpdate();
         } catch (SQLException e) {
                System.out.println(e.getMessage());
        }  
        
    }

    
    public static void main(String args[]) throws SQLException {
        //fillWorld app = new fillWorld();
        //app.createPopulation();
        
        

   // "occupation" locations

        
       }
    
}