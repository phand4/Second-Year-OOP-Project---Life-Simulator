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
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import static oop.Oop.createNewDatabase;
import static oop.Oop.createNewTable;
import static oop.InsertData.fileManage;

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
    
    public static void fileManage(BufferedReader br, BufferedReader br2, BufferedReader br3, 
                                  Integer fileSize,  String tableName, int x, String columnName,
                                  String columnName2, String columnName3) throws IOException{

        String line = null;
        int i = 0;
        
        switch(x){
            case 1: //job
                if(columnName3.equals("salary")){                   
                    while(i < fileSize){
                        //BigDecimal value = (Math.random()*100);
                        int value = 100;
                        String charname = null;
                        InsertData app = new InsertData();
                        app.insert(charname, tableName, columnName3, value);
                        i++;
                    }         
                    i = 0;
                }
                while( (line = br3.readLine())!= null){                               
                    while(i < fileSize){
                        Integer value = null;
                        String [] token = line.split("\\s+");
                        String charname = token[i];
                        InsertData app = new InsertData();
                        app.insert(charname, tableName, columnName3, value);
                        i++;
                    }
                    i = 0;
               }                
            
            case 2: //location
                while( (line = br3.readLine())!= null){   
                    while(i < fileSize){
                        Integer value = null;
                        String [] token = line.split("\\s+");
                        String charname = token[i];
                        InsertData app = new InsertData();
                        app.insert(charname, tableName, columnName2, value);
                        i++;
                    }
                    i = 0;
                }
                
                
            default: //first and second
                while( (line = br.readLine())!= null){
                    while(i < fileSize){
                        Integer value = null;
                        String [] token = line.split("\\s+");
                        String charname = token[i];
                        InsertData app = new InsertData();
                        app.insert(charname, tableName, columnName, value);
                        i++;
                    }                        
                }
                break;
        }
                       

    }
    
    public void insert(String data, String tableName, String columnName, Integer value){
        String sql = "INSERT INTO'" + tableName +"'('"+columnName+"') VALUES(?)";
        if(value == null){
            try(Connection conn = this.connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, data);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }   
        } else {
            try(Connection conn = this.connect();
                PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setInt(1, value);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }  
        }
    }
    
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        
        //Filling the firstname table
        File file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\firstnames.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String tableName = "firstNames";
        String columnName = "name";
        String columnName2 = null;
        String columnName3 = null;
        Integer fileSize = 100;
        int x = 0;
        BufferedReader br2 = null;
        BufferedReader br3 = null;
        fileManage(br, br2, br3, fileSize, tableName, x, columnName, columnName2, columnName3);   
        
        //Filling the surname table
        file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\surnames.txt");
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        tableName = "surnames";
        fileSize = 100;
        fileManage(br, br2, br3, fileSize, tableName, x, columnName, columnName2, columnName3);   
        
        //filling the job table
        file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\jobs.txt");
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        File file2 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\jobCompanies.txt");
        br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        tableName = "occupations";
        x = 3;
        columnName = "jobTitle";
        columnName2 = "company";
        columnName3 = "salary";
        fileSize = 10;
        fileManage(br, br2, br3, fileSize, tableName, x, columnName, columnName2, columnName3);   
    
        //filling location table
        file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\places.txt");
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        file2 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\address.txt");
        br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        File file3 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\jobCompanies.txt");
        br3 = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        tableName = "locations";
        columnName = "building";
        columnName2 = "location";
        columnName3 = "company";
        fileSize = 10;
        fileManage(br, br2, br3, fileSize, tableName, x, columnName, columnName2, columnName3);   
    }  
}



/*

        while( (line = br.readLine())!= null){
            if(br2 == null) {
                break;
            } else {
                
                while( (line = br2.readLine())!= null){
                    if(br3 == null) {
                       if(columnName == "salary"){
                                while(i < fileSize){
                                    BigDecimal value = (Math.random()*100);
                                    String charname = null;
                                    InsertData app = new InsertData();
                                    app.insert(charname, tableName, columnName3, value);
                                    i++;
                                }
                                i = 0;                           
                       } else {
                       break;
                       }
                    } 
                    else {                    
                            while( (line = br3.readLine())!= null){
                                while(i < fileSize){
                                    Integer value = null;
                                    String [] token = line.split("\\s+");
                                    String charname = token[i];
                                    InsertData app = new InsertData();
                                    app.insert(charname, tableName, columnName3, value);
                                    i++;
                                }
                                i = 0;
                            }
                        }
                    while(i < fileSize){
                        Integer value = null;
                        String [] token = line.split("\\s+");
                        String charname = token[i];
                        InsertData app = new InsertData();
                        app.insert(charname, tableName, columnName2, value);
                        i++;
                    }
                    i = 0;
                }
                                                                                
                while(i < fileSize){
                    Integer value = null;
                    String [] token = line.split("\\s+");
                    String charname = token[i];
                    InsertData app = new InsertData();
                    app.insert(charname, tableName, columnName, value);
                    i++;
                }                                
            }           
        }
*/