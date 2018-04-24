/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Random;

/**
 *
 * @author Peter
 */
public class fillWorld {

    
    private Connection connect() throws SQLException {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/people.db";
        Connection conn = null;        
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
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
        Integer morality= null;
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
            
            morality = r.nextInt(100);
            fame = r.nextInt(100);
            
            money = BigDecimal.valueOf(r.nextInt(9999999));
            
            fillWorld app = new fillWorld();       
            app.generatePopulation(data, data2, data3, isAlive, job, morality, fame, money);
            i++;
            
        }
    Connection m_Connection = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/people.db");    
    Statement m_Statement = m_Connection.createStatement();
    String query = "SELECT * FROM people";
    ResultSet m_ResultSet = m_Statement.executeQuery(query);
    ResultSetMetaData rsMetaData = m_ResultSet.getMetaData();
    
    System.out.println(rsMetaData.getColumnName(1));
    System.out.println(rsMetaData.getColumnName(2));
    System.out.println(rsMetaData.getColumnName(3));
    System.out.println(rsMetaData.getColumnName(4));
    System.out.println(rsMetaData.getColumnName(5));
    System.out.println(rsMetaData.getColumnName(6));
    System.out.println(rsMetaData.getColumnName(7));
    System.out.println(rsMetaData.getColumnName(8));
    System.out.println(rsMetaData.getColumnName(9));
    while (m_ResultSet.next()){
        System.out.println(m_ResultSet.getString(1) + ", " + m_ResultSet.getString(2) 
        + ", " + m_ResultSet.getString(3) + ", " + m_ResultSet.getString(4)  
        + ", " + m_ResultSet.getString(5) + ", " + m_ResultSet.getString(6)
        + ", " + m_ResultSet.getString(7) + ", " + m_ResultSet.getString(8)
        + ", " + m_ResultSet.getString(9));
    }       
       
    }
    
    
    public void generatePopulation(String data, String data2, Integer data3, 
                                          Boolean isAlive, String job, Integer morality, 
                                          Integer Fame, BigDecimal money) throws SQLException {
        String sql = "INSERT into people (fName, sName, age, isAlive, job, morality, fame, money) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
      
         try(Connection conn = this.connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)){  

            pstmt.setString(1, data);
            pstmt.setString(2, data2);
            pstmt.setInt(3, data3);
            pstmt.setBoolean(4, isAlive);
            pstmt.setString(5, job);
            pstmt.setInt(6, morality);
            pstmt.setInt(7, Fame);
            pstmt.setBigDecimal(8, money);           
            pstmt.executeUpdate();
         } catch (SQLException e) {
                System.out.println(e.getMessage());
        }  
        
    }

    
    public static void main(String args[]) throws SQLException {
        fillWorld app = new fillWorld();
        app.createPopulation();
        
        

   // "occupation" locations

        
       }
    
    }



/*read me file
technology 
team members 
what each team member small paragraph

youtube video boo

*/