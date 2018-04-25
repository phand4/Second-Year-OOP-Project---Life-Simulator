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
    public static void fileManage1f(BufferedReader br, Integer fileSize, String tableName,
                                    String columnName) throws IOException{
        int i = 0;
        String line = null;
        while((line = br.readLine()) != null){
            while(i < fileSize){
                Integer value = null;
                String [] token = line.split("\\s+");
                String charname = token[i];
                InsertData app = new InsertData();
                app.insert(charname, tableName, columnName);
                i++;
            }
        }
    }
    
    public void insert(String data, String tableName, String columnName){
        String sql = "INSERT INTO '" + tableName + "'('" + columnName + "') VALUES(?)";
            try(Connection conn = this.connect();
                    PreparedStatement pstmt = conn.prepareStatement(sql)){
                pstmt.setString(1, data);
                pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    }
    
    public static void fileManage3fs(BufferedReader br, BufferedReader br2, BufferedReader br3,
                                  Integer fileSize, String tableName,String columnName,
                                  String columnName2, String columnName3
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
                    InsertData app = new InsertData();
                    app.insert3fs(charname, charname2, charname3, tableName, columnName, columnName2, columnName3);                   
                    i++;
                }
                i = 0;
            }  
                              
    }
   
    public void insert3fs(String data, String data2, String data3, String tableName, String columnName, String columnName2, String columnName3){
        
        String sql = "INSERT INTO'" + tableName +"'('"+ columnName + "','" + columnName2 + "','" + columnName3 +"') VALUES(?, ?, ?)";
        
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
    

    public static void fileManage7fs(BufferedReader br, BufferedReader br2, BufferedReader br3,
                                       String columnName, String columnName2, String columnName3, 
                                       Integer fileSize, Integer miAge, Integer mxAge) throws IOException{
                   String line, line2, line3 = null;
                   String tableName = "events";
                   String eventTitle = null;             
                   int minAge = miAge;
                   int maxAge = mxAge;
                   Boolean interactive = false;
                   String event = null;
                   String eventOutcome = null;
                   int i = 0;
        while( (line = br.readLine())!= null && (line2 = br2.readLine()) !=  null && (line3 = br3.readLine()) != null){                               
            while(i < fileSize){
                    Integer value = null;
                    String [] token = line.split("\\s+");
                    String charname = token[i];
                    String [] token2 = line2.split("\\s+");
                    String charname2 = token2[i];
                    String [] token3 = line3.split("\\s+");
                    String charname3 = token3[i];
                    InsertData app = new InsertData();
                    app.insert7fs(tableName, charname, minAge, maxAge, interactive, charname2, charname3);                   
                    i++;
                }
                i = 0;
        }        
    }
    
    public void insert7fs(String tableName, String charname, int minAge,int maxAge,Boolean interactive,String charname2,String charname3){
        String sql = "INSERT INTO '" + tableName + "'( eventTitle , minAge , maxAge , interactive , event , eventOutcome ) VALUES (?, ?, ?, ?, ?, ?)";
    
        try(Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setString(1, charname);
            pstmt.setInt(2, minAge);
            pstmt.setInt(3, maxAge);
            pstmt.setBoolean(4, interactive);
            pstmt.setString(5, charname2);
            pstmt.setString(6, charname3);
            pstmt.executeUpdate();
        } catch (SQLException e)
        {
            System.out.println(e.getMessage());
        }}
    
    public static void main(String[] args) throws IOException{
        // TODO code application logic here
        String columnName2 = null;
        String columnName3 = null;
        BufferedReader br2 = null;
        BufferedReader br3 = null;
        
        //Filling the firstname table
        File file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\firstnames.txt");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String tableName = "firstNames";
        String columnName = "name";
        Integer fileSize = 100;
        fileManage1f(br,fileSize, tableName, columnName);   
        
        //Filling the surname table
        file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\surnames.txt");
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        tableName = "surnames";
        fileSize = 100;
        fileManage1f(br, fileSize, tableName, columnName);   
        
        //filling the job table
        file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\jobs.txt");
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        File file2 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\jobCompanies.txt");
        br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
        tableName = "occupations";
        File file3 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\salaries.txt");
        br3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));
       
        columnName = "jobTitle";
        columnName2 = "company";
        columnName3 = "salary";
        fileSize = 10;
        fileManage3fs(br, br2, br3, fileSize, tableName, columnName, columnName2, columnName3);   

       
        
       /* first names, events and surnames 2 fields
        occupations and locations are 3 fields */

        
        //filling location table
        file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\places.txt");
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        fileSize = 10;
        tableName = "locations";
        columnName = "building";              
        file2 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\address.txt");
        br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));        
        columnName2 = "location";              
        file3 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\jobCompanies.txt");
        br3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3)));       
        columnName3 = "company";
        fileManage3fs(br, br2, br3, fileSize, tableName, columnName, columnName2, columnName3);  

         //filling event table for under 18s
        file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventTitleu18.txt");
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        fileSize = 7;
        columnName = "eventTitle";
        file2 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventu18.txt");
        br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
        columnName2 = "event";
        file3 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventOCu18.txt");
        br3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3))); 
        columnName3 = "eventOutcome" ;
        int minAge = 1;
        int maxAge = 18;
        fileManage7fs(br, br2, br3, columnName, columnName2, columnName3, fileSize, minAge, maxAge);
    
         //filling event table for plus 18s
        file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventTitlep18.txt");
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        fileSize = 7;
        columnName = "eventTitle";
        file2 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventp18.txt");
        br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
        columnName2 = "event";
        file3 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventOCp18.txt");
        br3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3))); 
        columnName3 = "eventOutcome" ;
        minAge = 18;
        maxAge = 100;
        fileManage7fs(br, br2, br3, columnName, columnName2, columnName3, fileSize, minAge, maxAge);    
    
   
        //filling event table
        file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventTitle.txt");
        br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        fileSize = 7;
        columnName = "eventTitle";
        file2 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\events.txt");
        br2 = new BufferedReader(new InputStreamReader(new FileInputStream(file2)));
        columnName2 = "event";
        file3 = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\\data\\eventOC.txt");
        br3 = new BufferedReader(new InputStreamReader(new FileInputStream(file3))); 
        columnName3 = "eventOutcome" ;
        minAge = 1;
        maxAge = 100;
        fileManage7fs(br, br2, br3, columnName, columnName2, columnName3, fileSize, minAge, maxAge); 
    }  

}
