
public class Player extends Person{
	
	public Player(String fname, char gender, Job j)
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
			if(getAlignment() < 0)
			{
				toBeReturned += " You are a bad person.";
			}
			else if (getAlignment() == 0)
			{
				toBeReturned +=  " You are neutral.";
			}
			else if(getAlignment() > 0)
			{
				toBeReturned += " You are a good person.";
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
			if(getAlignment() < 0)
			{
				toBeReturned += "You were a bad person. ";
			}
			else if (getAlignment() == 0)
			{
				toBeReturned += "You were neutral. ";
			}
			else if(getAlignment() > 0)
			{
				toBeReturned += "You were a good person. ";
			}
		}
		toBeReturned+= fameCalculator();
		return toBeReturned;
	}

}
