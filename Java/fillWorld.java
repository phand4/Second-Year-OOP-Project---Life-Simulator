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
     
    public void createPopulation() throws SQLException {

        String data = null;
        String data2 = null;
        Integer data3 =null;
        Boolean isAlive = true;
        String job = null;
        Integer fame = null;
        BigDecimal money = null;
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
            String g;
            if(x > 50)
            {
            	g = "F";
            }
            else
            {
            	g = "M";
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
            app.generatePopulation(data, data2, data3, g, isAlive, job, fame, money);
            i++;
            
        }
    }
    
    public void generatePopulation(String data, String data2, Integer data3, String g,
                                          Boolean isAlive, String job, Integer Fame, BigDecimal money) throws SQLException 
    {
        String sql = "INSERT into people (fName, sName, age, gender, isAlive, job, fame, money) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
      
        try(Connection conn = this.connect();PreparedStatement pstmt = conn.prepareStatement(sql))
        {  
        	pstmt.setString(1, data);
            pstmt.setString(2, data2);
            pstmt.setInt(3, data3);
            pstmt.setString(4, g);
            pstmt.setBoolean(5, isAlive);
            pstmt.setString(6, job);
            pstmt.setInt(7, Fame);
            pstmt.setBigDecimal(8, money);           
            pstmt.executeUpdate();
        } 
        catch (SQLException e) 
        {
        	System.out.println(e.getMessage());
        }  
    }

    
    public static void main(String args[]) throws SQLException {
        //fillWorld app = new fillWorld();
        //app.createPopulation();
        
        

   // "occupation" locations

        
       }
    
}