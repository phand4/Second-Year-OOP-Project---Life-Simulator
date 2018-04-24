/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Peter
 */
public class ReadData {
    private Connection connect() {
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
public void selectAll(String tableName){
    String sql = "SELECT * FROM'" + tableName + "'";
         try(Connection conn = this.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){
             while(rs.next()) {
                 //System.out.println(rs.getString(tableName));
             }
         } catch(SQLException e){
             System.out.println(e.getMessage());
         }
    /* try(Connection conn = this.connect();
     Statement stmt = conn.createStatement();
     ResultSet rs = stmt.executeQuery("Select * From'" + tableName + "'")){
         ResultSetMetaData rsMetaData = rs.getMetaData();
         int numberOfColumns = rsMetaData.getColumnCount();
         System.out.println("resultSet MetaData column Count=" + numberOfColumns);
         
         for(int i = 1; i < numberOfColumns; i++){
             System.out.println("column MetaData ");
             System.out.println("column number " + i);
             System.out.println(rsMetaData.getColumnName(i));
         }
             } catch (SQLException e){
                System.out.println(e.getMessage());
             }*/
}
 
public static void main(String[] args) throws SQLException{
    ReadData app = new ReadData();
    String tableName = "firstNames";

    app.selectAll(tableName);
    tableName = "surnames";
    app.selectAll(tableName);

    tableName = "locations";
    app.selectAll(tableName);

    tableName = "occupations";
    app.selectAll(tableName);     

    Connection m_Connection = DriverManager.getConnection("jdbc:sqlite:C://sqlite/db/people.db");
    
    Statement m_Statement = m_Connection.createStatement();
    String query = "SELECT * FROM locations";
   // "occupation" locations
    ResultSet m_ResultSet = m_Statement.executeQuery(query);
    ResultSetMetaData rsMetaData = m_ResultSet.getMetaData();
    
    System.out.println(rsMetaData.getColumnName(1));
    System.out.println(rsMetaData.getColumnName(2));
    System.out.println(rsMetaData.getColumnName(3));
    System.out.println(rsMetaData.getColumnName(4));
    while (m_ResultSet.next()){
        System.out.println(m_ResultSet.getString(1) + ", " + m_ResultSet.getString(2) 
        + ", " + m_ResultSet.getString(3) + ", " + m_ResultSet.getString(4));               
    }
   
    query = "SELECT * FROM firstNames";
    m_ResultSet = m_Statement.executeQuery(query);
    rsMetaData = m_ResultSet.getMetaData();    
    System.out.println(rsMetaData.getColumnName(1));
    System.out.println(rsMetaData.getColumnName(2));
    while (m_ResultSet.next()){
        System.out.println(m_ResultSet.getString(1) + ", " + m_ResultSet.getString(2));               
    }
    
    query = "SELECT * FROM surnames";
    m_ResultSet = m_Statement.executeQuery(query);
    rsMetaData = m_ResultSet.getMetaData();    
    System.out.println(rsMetaData.getColumnName(1));
    System.out.println(rsMetaData.getColumnName(2));
    while (m_ResultSet.next()){
        System.out.println(m_ResultSet.getString(1) + ", " + m_ResultSet.getString(2));               
    }
    
    query = "SELECT * FROM occupations";
    m_ResultSet = m_Statement.executeQuery(query);
    rsMetaData = m_ResultSet.getMetaData();    
    System.out.println(rsMetaData.getColumnName(1));
    System.out.println(rsMetaData.getColumnName(2));
    System.out.println(rsMetaData.getColumnName(3));
    System.out.println(rsMetaData.getColumnName(4));
    while (m_ResultSet.next()){
        System.out.println(m_ResultSet.getString(1) + ", " + m_ResultSet.getString(2)
        + ", " + m_ResultSet.getString(3)+ ", " + m_ResultSet.getString(4));               
    }   
    
    query = "SELECT * FROM events";
    m_ResultSet = m_Statement.executeQuery(query);
    rsMetaData = m_ResultSet.getMetaData();    
    System.out.println(rsMetaData.getColumnName(1));
    System.out.println(rsMetaData.getColumnName(2));
    System.out.println(rsMetaData.getColumnName(3));
    System.out.println(rsMetaData.getColumnName(4));
    System.out.println(rsMetaData.getColumnName(5));
    System.out.println(rsMetaData.getColumnName(6));
    System.out.println(rsMetaData.getColumnName(7));
  
    while (m_ResultSet.next()){
        System.out.println(m_ResultSet.getString(1) + ", " + m_ResultSet.getString(2)
        + ", " + m_ResultSet.getString(3)+ ", " + m_ResultSet.getString(4)
        + ", " + m_ResultSet.getString(5)+ ", " + m_ResultSet.getString(6)  
        + ", " + m_ResultSet.getString(7)+ ", " + m_ResultSet.getString(8));        
    }
}
}
