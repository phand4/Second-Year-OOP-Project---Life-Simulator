/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

/**
 *
 * @author Peter
 */

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class Oop {

    /**
     * @param args the command line arguments
     */
    
    public static void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        
        try (Connection conn = DriverManager.getConnection(url)) {
            if(conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The God's name is " + meta.getDriverName());
                System.out.println("A world has been created.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }   
        
    }
    
    public static void createNewTable(String fileName, String tableName, String colName){
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        String sql = "CREATE TABLE IF NOT EXISTS '" + tableName + "' (\n"
                   + "id integer PRIMARY KEY, \n"
                   + "'"+ colName + "'text"
                   + (("occupations".equals(tableName))?", \n company text, \n salary Decimal\n" : 
                     (("locations".equals(tableName))?", \n location text, \n company text\n" : 
                     (("event".equals(tableName))?", \n eventKey integer NOT NULL\n" :
                     (("eventDesc".equals(tableName))?", \n eventData text NOT NULL, \n eventEffect text NOT NULL, \n eventKey integer NOT NULL\n" : 
                     (("people".equals(tableName))?", \n sName text NOT NULL, \n age integer, \n isAlive boolean, \n job text, \n morality integer, \n fame integer, \n money BigDecimal\n" : "")))))                                      
                   + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       
    }
    
    public static void main(String[] args) {
        // TODO code application logic here
        createNewDatabase("people.db");
        createNewTable("people.db", "firstNames", "name");
        createNewTable("people.db", "surnames", "name");
        createNewTable("people.db", "occupations", "jobTitle");
        createNewTable("people.db", "locations", "building");
        createNewTable("people.db", "event", "title");
        createNewTable("people.db", "evenDesc", "eventTitle");
        createNewTable("people.db", "people", "fName");
        
        
    }  
}
