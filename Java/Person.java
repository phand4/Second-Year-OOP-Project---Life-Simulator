public class Person implements GameObject{
	private int age;
	private boolean isAlive;
	private String sname;
	private String fname;
	private Job job;
	private float money;
	private int alignment;
	private int fame;

	public Person(String fn, String sn, int a, Job j, int i, int f) {
		this.fname = fn;
		this.sname = sn;
		this.age = a;
		this.isAlive = true;
		this.job = j;
		this.alignment = i;
		this.fame = f;
		this.money = (this.job).getSalary();
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
		this.money = 0;
	}
	
	public float checkFunds()
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
}