import java.awt.Color;
import java.math.BigDecimal;
import java.util.Random;

public class Person implements GameObject{
	private int age;
	private boolean isAlive;
	private String sname;
	private String fname;
	private char gender;
	private Job job;
	private BigDecimal money;
	private int alignment;
	private int fame;
	private Color textColour;

	public Person(String fn, String sn, int a, Job j, int i, int f, char g) {
		this.fname = fn;
		this.sname = sn;
		this.age = a;
		this.isAlive = true;
		this.job = j;
		this.alignment = i;
		this.fame = f;
		this.gender = g;
		this.money = (this.job).getSalary();
		this.textColour = randomColor();
	}
	
	public Person(String fn, char g, Job j)
	{
		this.fname = fn;
		this.sname = randomSurname();
		this.gender = g;
		this.age = 0;
		this.isAlive = true;
		this.job = j;
		this.money = null;
		this.fame = 0; 
		this.alignment = 0;
		this.textColour = randomColor();
	}
	
	public Person()
	{
		this.fname = "";
		this.sname = "";
		this.age = 18;
		this.gender = randomGender();
		this.isAlive = true;
		this.job = null;
		this.alignment = 0;
		this.fame = 0;
		this.money = null;
		this.textColour = randomColor();
	}
	
	private char randomGender()
	{
		Random r = new Random();
		int i = r.nextInt(2);
		char c = 'F';
		if(i == 0)
		{
			c = 'M';
		}
		return c;
	}
	
	private Color randomColor()
	{
		Random l = new Random();
		float r = (float) (l.nextFloat() / 2f + 0.5);
		float g = (float) (l.nextFloat() / 2f + 0.5) ;
		float b = (float) (l.nextFloat() / 2f + 0.5);
		return new Color(r, g, b);
	}
	
	private String randomSurname()
	{
		Random sn = new Random();
		int n = sn.nextInt(100);
		String surname = "";
		//surname = selectSurname("Surname", n);
		return surname;
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
			fameString += ((getGender() == 'M') ? "He " : "She ") + (getStatus() ? "is" : "was") + " not famous.";
		}
		if(getFame() > 20 && getFame() < 50)
		{
			fameString += ((getGender() == 'M') ? "He " : "She ") + (getStatus() ? "is" : "was") + " sorta famous.";
		}
		else if(getFame() > 50 && getFame() < 100)
		{
			fameString += ((getGender() == 'M') ? "He " : "She ") + (getStatus() ? "is" : "was") + " quite famous.";
		}
		else if(getFame() >= 100)
		{
			fameString += ((getGender() == 'M') ? "He " : "She ") + (getStatus() ? "is" : "was") + " pretty goddamn famous.";
		}
		else if(getFame() < 0)
		{
			fameString += ((getGender() == 'M') ? "He " : "She ") + (getStatus() ? "is" : "was") + " infamous.";
		}
		return fameString;
	}

	public String toString()
	{
		String toBeReturned = "";
		if(getStatus())
		{
			toBeReturned = "This person's name is " + getfName() + " " + getsName() + ". " + ((getGender() == 'M') ? "He" : "She") + " is " + getAge() + " years old. ";
			if(getJob() != null)
			{
				toBeReturned +=  ((getGender() == 'M') ? "He" : "She") + " works as a " + getJob() + ". "; 
			}
			else if(getJob() == null)
			{
				toBeReturned += ((getGender() == 'M') ? "He" : "She") + " is unemployed. "; 
			}
			if(getAlignment() < 0)
			{
				toBeReturned += ((getGender() == 'M') ? "He" : "She") + " is a bad person. ";
			}
			else if (getAlignment() == 0)
			{
				toBeReturned += ((getGender() == 'M') ? "He" : "She") + " is neutral. ";
			}
			else if(getAlignment() > 0)
			{
				toBeReturned += ((getGender() == 'M') ? "He" : "She") + " is a good person. ";
			}
		}
		else
		{
			toBeReturned = "This person's name was " + getfName() + " " + getsName() + ". They were " + getAge() + " years old. ";
			if(getJob() != null)
			{
				toBeReturned += ((getGender() == 'M') ? "He " : "She ") + " worked as a(an) " + getJob() + ". "; 
			}
			else if(getJob() == null)
			{
				toBeReturned += ((getGender() == 'M') ? "He " : "She ") + " were unemployed. "; 
			}
			if(getAlignment() < 0)
			{
				toBeReturned += ((getGender() == 'M') ? "He " : "She ") + " were a bad person. ";
			}
			else if (getAlignment() == 0)
			{
				toBeReturned += ((getGender() == 'M') ? "He " : "She ") + " were neutral. ";
			}
			else if(getAlignment() > 0)
			{
				toBeReturned += ((getGender() == 'M') ? "He " : "She ") + " were a good person. ";
			}
		}
		toBeReturned+= fameCalculator();
		return toBeReturned;
	}

	public String getfName()
	{
		return this.fname;
	}

	public String getsName()
	{
		return this.sname;
	}

	public int getAge()
	{
		return this.age;
	}
	
	public char getGender()
	{
		return this.gender;
	}

	public boolean getStatus()
	{
		return this.isAlive;
	}

	public String getJob(){
		return (this.job).toString();
	}

	public int getAlignment(){
		return this.alignment;
	}

	public int getFame(){
		return this.fame;
	}
}