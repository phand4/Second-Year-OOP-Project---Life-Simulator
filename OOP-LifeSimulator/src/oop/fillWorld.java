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
    private static Connection conn = null;
    
    private static void connect() throws SQLException {
        // SQLite connection string
        String url = "jdbc:sqlite:C://sqlite/db/people.db";
        
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
                
       
    }
     
    public static void createPopulation() throws SQLException {
       
    
        Statement fW_Statement = conn.createStatement();
        
        String data = null;
        String data2 = null;
        Integer data3 =null;
        Boolean isAlive = true;
        String job = null;
        Integer morality= null;
        Integer fame = null;
        BigDecimal money = null;
              
        int i = 0;
        while(i < 10){
            Random r = new Random();
            int x = r.nextInt(100);
            String query = "SELECT name FROM firstNames WHERE id = '" + x + "'";
            ResultSet fW_ResultSet = fW_Statement.executeQuery(query);
           

            data = fW_ResultSet.getString(1);
            
            x = r.nextInt(100);
            query = "SELECT name FROM surnames WHERE id = '" + x + "'";
            fW_ResultSet = fW_Statement.executeQuery(query);
            data2 = fW_ResultSet.getString(1);
            
            data3 = 10;
            
            isAlive= true; 
            
            job = "doctor";
            
            morality = 5;
            
            fame = 10;
            money = BigDecimal.valueOf(99900);
            
            fillWorld app = new fillWorld();       
            app.generatePopulation(data, data2, data3, isAlive, job, morality, fame, money);
            i++;
            
        }
    Statement m_Statement = conn.createStatement();
    String query = "SELECT * FROM people";
    ResultSet m_ResultSet = fW_Statement.executeQuery(query);
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
        + ", " + m_ResultSet.getString(7) + ", " + m_ResultSet.getString(8));
    }       
       
    }
    
    
    public void generatePopulation(String data, String data2, Integer data3, 
                                          Boolean isAlive, String job, Integer morality, 
                                          Integer Fame, BigDecimal money) throws SQLException {
        String sql = "INSERT into people (fName, sName, age, isAlive, job, morality, fame, money) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
      
        
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, data);
            pstmt.setString(2, data2);
            pstmt.setInt(3, data3);
            pstmt.setBoolean(4, isAlive);
            pstmt.setString(5, job);
            pstmt.setInt(6, morality);
            pstmt.setInt(7, Fame);
            pstmt.setBigDecimal(8, money);           
            pstmt.executeUpdate();
      
        
    }

    
    public static void main(String args[]) throws SQLException {
        connect();
        createPopulation();
        
        
    Statement m_Statement = conn.createStatement();
    String query = "SELECT * FROM people";
   // "occupation" locations

        
       }
    
    }



/*read me file
technology 
team members 
what each team member small paragraph

youtube video boo

*/