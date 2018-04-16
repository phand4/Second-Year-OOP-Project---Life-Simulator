public class Person implements GameObject{
	private int age;
	private boolean isAlive;
	private String sname;
	private String fname;
	private String job;
	private int alignment;
	private int fame;

	public Person(String fn, String sn, int a, String j, int i, int f) {
		this.fname = fn;
		this.sname = sn;
		this.age = a;
		this.isAlive = true;
		this.job = j;
		this.alignment = i;
		this.fame = f;
			
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
	}
	
	public String fameCalculator()
	{
		String fameString = "";
		if(getFame() < 20 && getFame() > 0)
			fameString += "They are not famous."
		if(getFame() > 20 && getFame() < 50)
		{
			fameString += "They are sorta famous.";
		}
		else if(getFame() > 50 && getFame() < 100)
		{
			fameString += "They are quite famous.";
		}
		else if(getFame() >= 100)
		{
			fameString += "They are pretty goddamn famous.";
		}
		return fameString;
	}

	public String toString()
	{
		String toBeReturned = "";
		if(getStatus()){
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
			if(getFame() > 20 && getFame() < 50)
			{
				toBeReturned += "They are sorta famous.";
			}
			else if(getFame() > 50 && getFame() < 100)
			{
				toBeReturned += "They are quite famous.";
			}
			else if(getFame() >= 100)
			{
				toBeReturned += "They are pretty goddamn famous.";
			}
			else if()
		}
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
		return job;
	}

	public int getAlignment(){
		return alignment;
	}

	public int getFame(){
		return fame;
	}
}