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
import static oop.InsertData.fileManage7fs;
import static oop.generateEvents.createNewTable;

/**
 *
 * @author Peter
 */
public class npcEvent {
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
                   + "npcEventTitle text, \n"
                   + "npcEvent VARCHAR(300), \n"
                   + "npcEventOutcome text\n"
                   + ");";
        
        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }      
    }
    
    public static void fileManage(BufferedReader br, BufferedReader br2, BufferedReader br3,
                                  String columnName, String columnName2, String columnName3, Integer fileSize 
                                  ) throws IOException{
        int i =0;
        String line = null;
        String line2 = null;
        String line3 = null;
        while( (line = br.readLine())!= null && (line2 = br2.readLine()) !=  null && (line3 = br3.readLine()) != null){                               
            while(i < fileSize){
                    Integer value = null;
                    String [] token = line.split("\\s+");
                    String charname = token[i];
                    String [] token2 = line2.split("\\s+");
                    String charname2 = token2[i];
                    String [] token3 = line3.split("\\s+");
                    String charname3 = token3[i];
                    npcEvent app = new npcEvent();
                    app.insert(charname, charname2, charname3, columnName, columnName2, columnName3);                   
                    i++;
                }
                i = 0;
            }  
                              
    }   

    public void insert(String data, String data2, String data3, String columnName, String columnName2, String columnName3){
        
        String sql = "INSERT INTO npcEvents ('"+ columnName + "','" + columnName2 + "','" + columnName3 +"') VALUES(?, ?, ?)";
        
            try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)){             
                pstmt.setString(1, data);
                pstmt.setString(2, data2);
                pstmt.setString(3, data3);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }   

    }  
    
    
    public static void main(String[] args) throws IOException, SQLException{
        createNewTable("people.db", "npcEvents");

        //filling event table
        File file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventTitle.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        int fileSize = 7;
        String columnName = "npcEventTitle";
        File file2 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\events.txt");
        BufferedReader br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
        String columnName2 = "npcEvent";
        File file3 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventOC.txt");
        BufferedReader br3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3))); 
        String columnName3 = "npcEventOutcome" ;
        fileManage(br, br2, br3, columnName, columnName2, columnName3, fileSize); 
      
    }    
}
