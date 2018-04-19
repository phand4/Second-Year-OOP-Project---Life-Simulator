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
import static oop.Oop.createNewDatabase;
import static oop.Oop.createNewTable;

/**
 *
 * @author Peter
 */
public class InsertData {
    
    
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
    
    public void insert(String firstname){
        String sql = "INSERT INTO firstNames(name) VALUES(?)";
        
        try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, firstname);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        File file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\firstnames.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        int i = 0;
        while( (line = br.readLine())!= null){
            while(i < 100){
                String [] token = line.split("\\s+");
                String charname = token[i];
                InsertData app = new InsertData();
                app.insert(charname);
                i++;
            }
        }
    }  

}
