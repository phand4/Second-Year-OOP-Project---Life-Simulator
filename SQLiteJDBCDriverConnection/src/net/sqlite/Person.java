package net.sqlite;

/**
 *
 * @author Peter
 */

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import java.math.BigDecimal;

@DatabaseTable(tableName = "persons")
public final class Person {
    
    public static final String FNAME_FIELD_NAME ="fName";
    public static final String SNAME_FIELD_NAME ="sName";
    public static final String AGE_FIELD_NAME ="age";
    public static final String JOB_FIELD_NAME ="job";
    public static final String ALIGNMENT_FIELD_NAME ="alignment";
    public static final String FAME_FIELD_NAME ="fame";
    public static final String ALIVE_FIELD_NAME ="isAlive";
    public static final String MONEY_FIELD_NAME ="money";
    
    @DatabaseField(generatedId = true)
    private int Id;
    
    @DatabaseField(columnName = FNAME_FIELD_NAME, canBeNull = false)
    private String fname;
    @DatabaseField(columnName = SNAME_FIELD_NAME, canBeNull = false)
    private String sname;
    @DatabaseField(columnName = AGE_FIELD_NAME, canBeNull = false)
    private int age;
    @DatabaseField(columnName = JOB_FIELD_NAME, canBeNull = true)
    private String job;
    @DatabaseField(columnName = ALIGNMENT_FIELD_NAME, canBeNull = false)
    private int alignment;
    @DatabaseField(columnName = FAME_FIELD_NAME, canBeNull = false)
    private int fame;
    @DatabaseField(columnName = ALIVE_FIELD_NAME, canBeNull = false)
    private boolean isAlive;
    @DatabaseField(columnName = MONEY_FIELD_NAME, canBeNull = true)
    private BigDecimal money;
      
    public Person(String fn, String sn, int a, String j, int i, int f, boolean al, BigDecimal m) {
            this.fname = fn;
            this.sname = sn;
            this.age = a;
            this.isAlive = al;
            this.job = j;
            this.alignment = i;
            this.fame = f;
            this.money = m;
    }
	
    public Person()
    {
	this.fname = "";
	this.sname = "";
	this.age = 18;
	this.isAlive = true;
	this.job = null;
	this.alignment = 0;
	this.fame = 0;
	this.money = null;
    }
	
	public BigDecimal checkFunds()
	{
		return money;
	}
	
	public String fameCalculator()
	{
		String fameString = "";
		if(getFame() < 20 && getFame() > 0)
		{
			fameString += "They " + (getStatus() ? "are" : "were") + " not famous.";
		}
		if(getFame() > 20 && getFame() < 50)
		{
			fameString += "They " + (getStatus() ? "are" : "were") + " sorta famous.";
		}
		else if(getFame() > 50 && getFame() < 100)
		{
			fameString += "They " + (getStatus() ? "are" : "were") + " quite famous.";
		}
		else if(getFame() >= 100)
		{
			fameString += "They " + (getStatus() ? "are" : "were") + " pretty goddamn famous.";
		}
		else if(getFame() < 0)
		{
			fameString += "They " + (getStatus() ? "are" : "were") + " infamous.";
		}
		return fameString;
	}

	public String toString()
	{
		String toBeReturned = "";
		if(getStatus())
		{
			toBeReturned = "This person's name is " + getfName() + " " + getsName() + ". They are " + getAge() + " years old.";
			if(getJob() != null)
			{
				toBeReturned += " They work as a " + getJob() + "."; 
			}
			else if(getJob() == null)
			{
				toBeReturned += " They are unemployed."; 
			}
			if(getAlignment() < 0)
			{
				toBeReturned += " They are a bad person.";
			}
			else if (getAlignment() == 0)
			{
				toBeReturned += " They are neutral.";
			}
			else if(getAlignment() > 0)
			{
				toBeReturned += "They are a good person.";
			}
		}
		else
		{
			toBeReturned = "This person's name was " + getfName() + " " + getsName() + ". They were " + getAge() + " years old.";
			if(getJob() != null)
			{
				toBeReturned += " They worked as a(an) " + getJob() + "."; 
			}
			else if(getJob() == null)
			{
				toBeReturned += " They were unemployed."; 
			}
			if(getAlignment() < 0)
			{
				toBeReturned += " They were a bad person.";
			}
			else if (getAlignment() == 0)
			{
				toBeReturned += " They were neutral.";
			}
			else if(getAlignment() > 0)
			{
				toBeReturned += "They were a good person.";
			}
		}
		toBeReturned+= fameCalculator();
		return toBeReturned;
	}

	public String getfName(){
		return fname;
	}

	public String getsName(){
		return sname;
	}

	public int getAge(){
		return age;
	}

	public boolean getStatus(){
		return isAlive;
	}

	public String getJob(){
		return job.toString();
	}

	public int getAlignment(){
		return alignment;
	}

	public int getFame(){
		return fame;
	}
        
        public int getId(){
            return Id;
        }
}

/*
	
	
	
	
	
	
	

*/