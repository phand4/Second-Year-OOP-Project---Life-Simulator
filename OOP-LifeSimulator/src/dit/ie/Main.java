/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dit.ie;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.misc.TransactionManager;
import com.j256.ormlite.stmt.PreparedQuery;
import com.j256.ormlite.stmt.QueryBuilder;
import com.j256.ormlite.stmt.SelectArg;
import com.j256.ormlite.table.TableUtils;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;





/**
 *
 * @author Peter
 */
public class Main {
    
    private final static String DATABASE_URL = "jdbc:sqlite:C:/sqlite/db/test.db";   
    private Dao<Person, Integer> personDao;
    

    private Dao<Firstname, Integer> firstnameDao;
    
    
    public static void main(String[] args) throws Exception {
        new Main().doMain(args);
    }
    
    private void doMain(String[] args) throws Exception {
        
        try (ConnectionSource connectionSource = new JdbcConnectionSource(DATABASE_URL)) {
            setupDatabase(connectionSource);
            readWriteData();
            readWriteBunch();
            useSelectArgFeature();
            useTransactions(connectionSource);
            System.out.println("Success");
        }
    }
    
    private void setupDatabase(ConnectionSource connectionSource)
    throws Exception {
        personDao = DaoManager.createDao(connectionSource, Person.class);
        
        TableUtils.createTable(connectionSource, Person.class);
    }
    
    private void readWriteData() 
    throws Exception {
        String fn = "Test";
        String sn = "McTest";
        int a = 18;
        String j = "none";
        int f = 0;
        BigDecimal m = null;
        int i = 2;
        boolean al = true; 
        Person person = new Person(fn, sn, a, j, i, f, al, m);
        
        personDao.create(person);
        int id = person.getId();
       
        verifyDB(id, person);
        
        personDao.update(person);
        verifyDB(id, person);
        
        List<Person> people = personDao.queryForAll();
        assertEquals("Found 1 person matching query", 1, people.size());
        verifyPerson(person, people.get(0));
      
        int personC = 0;
        for ( Person person2 : personDao) {
            verifyPerson(person, person2);
            personC++;
        }
        assertEquals("Atleast 1 person in loop", 1, personC);
        
        QueryBuilder<Person, Integer> statementBuilder = personDao.queryBuilder();
    
        statementBuilder.where().like(Person.FNAME_FIELD_NAME, "hello");
        people = personDao.query(statementBuilder.prepare());
        assertEquals("No person found", 0, people.size());
        
        statementBuilder.where().like(Person.FNAME_FIELD_NAME, fn.substring(0,4) +  "%");
        people = personDao.query(statementBuilder.prepare());
        assertEquals("Found one person matching query", 1, people.size());
        verifyPerson(person, people.get(0));
        
        personDao.delete(person);
        assertNull("Person was deleted", personDao.queryForId(id));
        
        //writing names to table
        File file = new File("C:\\Users\\Peter\\Documents\\Year 2\\Object Oriented Programming\\Project\\OOP-LifeSimulator\firstnames");
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
        String line = null;
        int c = 0;
        while( (line = br.readLine())!= null ){
            while(c < 50){
                String[] parts = line.split("\\s+");
                String char1 = parts[0];
                c++;
                Firstname firstname = new Firstname(char1);
        
                firstnameDao.create(firstname);
            }
        }
        List<Firstname> fnames = firstnameDao.queryForAll();
        
        
    }
    
    private void readWriteBunch()
    throws Exception {
        
        Map<String, Person> people = new HashMap<>();
        for (int i = 1; i < 100; i++) {
            String fname = Integer.toString(i);
            String sname = Integer.toString(i);
            int age = i;
            String job = Integer.toString(i);
            int morality = i;
            int fame = i;
            boolean isAlive = true;
            BigDecimal money = BigDecimal.valueOf(i).movePointLeft(2);
            //String fn, String sn, int a, String j, int i, int f, BigDecimal m
            Person person = new Person(fname, sname, age, job, morality, fame, isAlive, money);
            personDao.create(person);
            people.put(fname, person);
        }
        
        List<Person> all = personDao.queryForAll();
        assertEquals("Same number of people in map", people.size(), all.size());
        for(Person person : personDao) {
            assertTrue("Person found in map", people.containsValue(person));
            verifyPerson(people.get(person.getfName()), person);
        }
        
        int personC = 0;
        for(Person person: personDao) {
            assertTrue("Found person in map", people.containsValue(person));
            verifyPerson(people.get(person.getfName()), person);
            personC++;
        }
        assertEquals("Correct number of people found?", people.size(), personC);
            
        
    }
    
    private void useSelectArgFeature()
    throws Exception {
        String fname1 = "Test2";
        String sname1 = "mctest2";
        int age1 = 20;
        String job1 = "Doctor";
        int morality1 = 30;
        int fame1 = 5;
        BigDecimal money1 = new BigDecimal(20.00);
        String fname2 = "Test3";
        String sname2 = "mctest3";
        int age2 = 21;
        String job2 = "Teacher";
        int morality2 = 15;
        int fame2 = 20;
        BigDecimal money2 = new BigDecimal(30);
        String fname3 = "Test4";
        String sname3 = "mctest4";
        int age3 = 50;
        String job3 = "President";
        int morality3 = 60;
        int fame3 = 60;
        BigDecimal money3 = new BigDecimal(60);
        boolean isal = true;
        assertEquals(1, personDao.create(new Person(fname1, sname1, age1, job1, morality1, fame1, isal, money1)));
        assertEquals(1, personDao.create(new Person(fname2, sname2, age2, job2, morality2, fame2, isal, money2)));
        assertEquals(1, personDao.create(new Person(fname3, sname3, age3, job3, morality3, fame3, isal, money3)));
        
        QueryBuilder<Person, Integer> statementBuilder = personDao.queryBuilder();
        SelectArg selectArg = new SelectArg();
        
        statementBuilder.where().like(Person.FNAME_FIELD_NAME, selectArg);
        PreparedQuery<Person> preparedQuery = statementBuilder.prepare();
        
        selectArg.setValue(fname1);
        List<Person> results = personDao.query(preparedQuery);
        assertEquals("1 result matching query", 1, results.size());
        assertEquals(fname1, results.get(0).getfName());
        
        selectArg.setValue(fname2);
        results = personDao.query(preparedQuery);
        assertEquals("1 result matching query", 1, results.size());
        assertEquals(fname2, results.get(0).getfName());
        
        selectArg.setValue(fname3);
        results = personDao.query(preparedQuery);
        assertEquals("1 result matching query", 1, results.size());
        assertEquals(fname3, results.get(0).getfName());
        
        
    }
    
    private void useTransactions(ConnectionSource connectionSource)
    throws Exception {
        String fname = "trnsfr";
        String sname = "mctrasfr";
        int age = 70;
        String job = "tester";
        int morality = 90;
        int fame = 85;
        BigDecimal money = new BigDecimal(20.00);
        boolean isal = true;
        final Person person = new Person(fname, sname, age, job, morality, fame, isal, money);
        assertEquals(1, personDao.create(person));
        
        TransactionManager transactionManager = new TransactionManager(connectionSource);
        try {
             transactionManager.callInTransaction(new Callable<Void>() {
                 @Override
                 public Void call() throws Exception {
                     assertEquals(1, personDao.delete(person));
                     assertNull(personDao.queryForId(person.getId()));
                     throw new Exception("We throw to roll back!!");                     
                 }
             });
             fail("Should have been thrown");
        } catch(SQLException e) {
            
        }
        
        assertNotNull(personDao.queryForId(person.getId()));
    }    
    private void verifyDB(int id, Person expected)
    throws SQLException, Exception {
        Person person2 = personDao.queryForId(id);
        if (person2 == null) {
            throw new Exception("Should have found id '" + id + "'in the database");
        } verifyPerson(expected, person2);
    }  

    private static void verifyPerson(Person expected, Person person2) {
        assertEquals("expected person does not exist", expected, person2);
    }
       
    

    /*public static void createNewDatabase(String fileName) {
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
    
    public static void createNewTable(){
        String url = "jdbc:sqlite:C://sqlite/db/tests.db";
        String sql = "CREATE TABLE IF NOT EXISTS warehouses(\n"
                        + " id integer PRIMARY KEY, \n"
                        + " name text NOT NULL, \n"
                        + " capacity real\n"
                        + ");";
        
        try(Connection conn = DriverManager.getConnection(url);
            Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public static void main(String[] args){
        createNewDatabase("People.db");
        createNewTable();
    }*/
}

