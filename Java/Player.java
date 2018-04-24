
public class Player extends Person{
	
	public Player(String fname, char gender, String j)
	{
		super(fname, gender, j);
	}
	
	
	@Override
	public String fameCalculator()
	{
		String fameString = "";
		if(getFame() < 20 && getFame() >= 0)
		{
			fameString += " You "+ (getStatus() ? "are" : "were") + " not famous.";
		}
		if(getFame() > 20 && getFame() < 50)
		{
			fameString += " You " + (getStatus() ? "are" : "were") + " sorta famous.";
		}
		else if(getFame() > 50 && getFame() < 100)
		{
			fameString += " You " + (getStatus() ? "are" : "were") + " quite famous.";
		}
		else if(getFame() >= 100)
		{
			fameString += " You " + (getStatus() ? "are" : "were") + " pretty goddamn famous.";
		}
		else if(getFame() < 0)
		{
			fameString += " You " + (getStatus() ? "are" : "were") + " infamous.";
		}
		return fameString;
	}
	
	@Override
	public String toString()
	{
		String toBeReturned = "";
		if(getStatus())
		{
			toBeReturned = "Your name is " + getfName() + " " + getsName() + "." + " You are " + getAge() + " years old.";
			if(getJob() != null)
			{
				toBeReturned +=  " You work as a " + getJob() + "."; 
			}
			else if(getJob() == null)
			{
				toBeReturned +=  " You are unemployed."; 
			}
		}
		else
		{
			toBeReturned = "Your name was " + getfName() + " " + getsName() + ". You were " + getAge() + " years old when you died. ";
			if(getJob() != null)
			{
				toBeReturned +=  "You worked as a(an) " + getJob() + ". "; 
			}
			else if(getJob() == null)
			{
				toBeReturned += "You were unemployed. "; 
			}
		}
		toBeReturned+= fameCalculator();
		return toBeReturned;
	}

}
