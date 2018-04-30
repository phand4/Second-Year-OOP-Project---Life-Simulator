import java.awt.Color;
import java.math.BigDecimal;
import java.util.Random;

public class Person implements GameObject{
	private int age;
	private boolean isAlive;
	private String sname;
	private String fname;
	private char gender;
	private String job;
	private BigDecimal money;
	private int fame;
	private Color textColour;

	public Person(String fn, String sn, int a, String j, int f, char g, BigDecimal m) {
		this.fname = fn;
		this.sname = sn;
		this.age = a;
		this.isAlive = true;
		this.job = j;
		this.fame = f;
		this.gender = g;
		this.money = m;
		this.textColour = randomColor();
	}
	
	public Person(String fn, char g, String j)
	{
		this.fname = fn;
		this.sname = randomSurname();
		this.gender = g;
		this.age = 0;
		this.isAlive = true;
		this.job = j;
		this.money = null;
		this.fame = 0; 
		this.textColour = randomColor();
	}
	
	public Person()
	{
		this.fname = "";
		this.sname = "";
		this.age = 18;
		this.gender = randomGender();
		this.isAlive = true;
		this.job = "";
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
	
	
	public String checkFunds()
	{
		return money.toString();
	}
	
	public String fameCalculator()
	{
		String fameString = "";
		if(getFame() < 20 && getFame() > 0)
		{
			fameString += ((getGender() == 'M') ? ">He " : ">She ") + (getStatus() ? "is" : "was") + " not famous.";
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
				toBeReturned +=  ((getGender() == 'M') ? "He" : "She") + " has a yearly salary of €" + checkFunds() + ". ";
			}
			else if(getJob() == null)
			{
				toBeReturned += ((getGender() == 'M') ? "He" : "She") + " is unemployed. "; 
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
				toBeReturned += ((getGender() == 'M') ? "He " : "She ") + " was unemployed. "; 
			}
		}
		toBeReturned+= fameCalculator();
		return toBeReturned;
	}
	
	public void increaseFame()
	{
		this.fame += 10;
	}
	
	public void decreaseFame()
	{
		this.fame -= 10;
	}
	
	public void loseJob()
	{
		this.job = null;
	}
	
	public void ageIncrease()
	{
		this.age += 1;
	}
	
	public void isWizard()
	{
		this.job = "Wizard";
	}
	
	public void isSherriff()
	{
		this.job = "Sherriff";
	}
	
	public void died() 
	{
		this.isAlive = false;
	}
	
	public void moneyUp()
	{
		Random r = new Random();
		double i = r.nextDouble();
		this.money.add(BigDecimal.valueOf(i));
	}
	
	public void moneyDown()
	{
		Random r = new Random();
		double i = r.nextDouble();
		this.money.subtract(BigDecimal.valueOf(i));
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
		String jobs = this.job.replaceAll("_", " ");
		return jobs;
	}

	public int getFame(){
		return this.fame;
	}
	public BigDecimal getMoney()
	{
		return this.money;
	}
}