/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Peter
 */
public class generateEvents {
    
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

    public static void createNewTable(String fileName, String tableName){
        String url = "jdbc:sqlite:C:/sqlite/db/" + fileName;
        String sql = "CREATE TABLE IF NOT EXISTS '" + tableName + "' (\n"
                   + "id integer PRIMARY KEY, \n"
                   + "eventTitle text, \n"
                   + "minAge int, \n"
                   + "maxAge int, \n"
                   + "interactive boolean, \n"
                   + "event VARCHAR(300), \n"
                   + "eventOutcome text\n"
                   + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }      
    }
   
  /*  public static void eventRead(BufferedReader br, int fileSize) throws IOException{
        String line = null;
        String eventTitle = "eventTitle";
        int i = fileSize;
        while((line = br.readLine())!= null){
            while(i < fileSize){ 
                String [] token = line.split("\\s+");
                String charname = token[i];
                generateEvents app = new generateEvents();
                app.fillEvents(charname, eventTitle);
                i++;
            }
        }
    } 
    
    public void fillEvents(String charname, String columnName){
        String sql = "INSERT INTO events('" + columnName + "') VALUES(?)";

             try(Connection conn = this.connect();
                 PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, charname);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }  
                         
    }*/
    
    public static void main(String[] args) throws IOException, SQLException{
        createNewTable("people.db", "events");
        
        //File file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventTitle.txt");
        //BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        //int fileSize = 6;
        //eventRead(br, fileSize);

      
    }
}
