/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dit.ie;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
    
/**
 *
 * @author Peter
 */
@DatabaseTable(tableName = "firstnames")
public class Firstname {
    public static final String FNAME_FIELD_NAME ="fName";
    
    @DatabaseField(generatedId = true)
    private int Id;
    
    @DatabaseField(columnName = FNAME_FIELD_NAME, canBeNull = false)
    private String fname;
    
    Firstname(){
        
    }
    
    public Firstname(String fname) {
        this.fname = fname;
    }
    
    public String getfName(){
        return fname;
    }
    
    public void setName(String fname){
        this.fname = fname;
    }

}
