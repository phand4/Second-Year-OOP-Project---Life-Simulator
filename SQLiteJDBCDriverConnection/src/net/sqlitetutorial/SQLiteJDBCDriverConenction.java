/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.sqlitetutorial;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Peter
 */
public class SQLiteJDBCDriverConenction {

    public static void connect() {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:C:/sqlite/db/chinook.db";
            
            conn = DriverManager.getConnection(url);
            
            System.out.println("Connection to SQLite has been established.");
            
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                if ( conn != null){
                    conn.close();
                }
            } catch(SQLException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        connect();
    }
    
}
