/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Peter
 */
public class Main {
    
    public static void createNewDatabase(String fileName) {
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        
        try ( Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The drivers name is " + meta.getDriverName());
                
                System.out.println("A new Database has been created.");
            }
        
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args){
        createNewDatabase("People.db");
    }
}
