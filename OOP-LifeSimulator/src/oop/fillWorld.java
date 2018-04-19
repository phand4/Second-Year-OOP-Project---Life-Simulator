/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Peter
 */
public class fillWorld {

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

    public void generatePopulation(String firstName, String surname, Integer age, 
                                          Boolean isAlive, String job, Integer morality, 
                                          Integer Fame, BigDecimal money) {
        String sql = "INSERT into firstName, surname, age, isAlive, job, morality, fame, money VALUES(?)";
        
        try( Connection conn = this.connect();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt(setString(1, firstName, surname, age, isAlive, job, morality, Fame, money));
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        
    }
    public static void createPopulation() {
        String url = "jdbc:sqlite:C:/sqlite/db/people.db";
        String sql = "CREATE TABLE IF NOT EXISTS population (\n"
                   + "id integer PRIMARY KEY, \n"
                   + "firstName text NOT NULL, \n"
                   + "surname text NOT NULL, \n"
                   + "age integer NOT NULL, \n"
                   + "isAlive boolean NOT NULL, \n"
                   + "job text NOT NULL, \n"
                   + "morality integer NOT NULL, \n"
                   + "fame integer NOT NULL, \n"
                   + "money BigDecimal NOT NULL\n"
                   + ");";
        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
                stmt.execute(sql);           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String args[]) {
        createPopulation();
    }

}
