/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
public void selectAll(){
    String sql = "SELECT name FROM firstNames";
     try(Connection conn = this.connect();
         Statement stmt = conn.createStatement();
         ResultSet rs = stmt.executeQuery(sql)){
         while(rs.next()) {
             System.out.println(rs.getString("name"));
         }
     } catch(SQLException e){
         System.out.println(e.getMessage());
     }
}
 
public static void main(String[] args){
    ReadData app = new ReadData();
    app.selectAll();
    
}
}
