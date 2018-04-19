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
    
    public static void createNewTable(String fileName){
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        String sql = "CREATE TABLE IF NOT EXISTS firstNames (\n"
                   + "id integer PRIMARY KEY, \n"
                   + "name text NOT NULL\n"
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
        createNewTable("people.db");
        

    }
    
}
