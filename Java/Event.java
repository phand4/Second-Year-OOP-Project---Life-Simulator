
public class Event {
	private int eventID; //ID
	private String eventTitle; //Title of event
	private int type; //
	private int minAge; //Minimum age the player has to be to get this event
	private int maxAge; //Maximum age the player can be to get this event
	private boolean interactive; //Whether the event is interactive or not
	private String event; //Text of the event
	private String eventOutcome; //Event outcome string
	
	public Event()
	{
		this.eventID = 0;
		this.eventTitle = "";
		this.minAge = 0;
		this.maxAge = 90;
		this.interactive = false;
		this.event = "";
		this.eventOutcome = "";
	}
	
	public Event(int id, String title, int min, int max, boolean interactive, String event, String outcome)
	{
		this.eventID = id;
		this.eventTitle = title;
		this.minAge = min;
		this.maxAge = max;
		this.interactive = interactive;
		this.event = event;
		this.eventOutcome = outcome;
	}
	
	
	public boolean ageTrigger(Player player)
	{
		if(player.getAge() < minAge || player.getAge() > maxAge)
		{
			return false;
		}
		return true;
	}
	
	public String printEvent()
	{
		String printedEvent = (this.event).replaceAll("_", " ");
		String toBePrinted = "";
		if(printedEvent.length() > 60)
		{
			int mid = printedEvent.length() / 2;
			String[] printedEvent1 = {printedEvent.substring(0, mid), printedEvent.substring(mid)};
			toBePrinted = printedEvent1[0] + "\n" + printedEvent1[1];
			return "\n>" + toBePrinted + "\n";
		}
		return "\n>" + printedEvent + "\n";
	}
	
	public String printEventOutcome()
	{
		String outcome = (this.eventOutcome).replaceAll("_", " ");
		return outcome;
	}
	
	public void runEvent(Player player)
	{
		this.printEvent();
		if(interactive)
		{
			//Player interaction goes here
		}
		else
		{
			if(eventOutcome.equalsIgnoreCase("fameUp"))
			{
				player.increaseFame();
			}
			else if(eventOutcome.equalsIgnoreCase("fameDown"))
			{
				player.decreaseFame();
			}
			else if(eventOutcome.equalsIgnoreCase("jobLost"))
			{
				player.loseJob();
			}
			else if(eventOutcome.equalsIgnoreCase("ageup"))
			{
				player.ageIncrease();
			}
			else if(eventOutcome.equalsIgnoreCase("wizard"))
			{
				player.isWizard();
			}
			else if(eventOutcome.equalsIgnoreCase("isAlive"))
			{
				player.died();
			}
			else if(eventOutcome.equalsIgnoreCase("sheriff"))
			{
				player.isSherriff();
			}
			else if(eventOutcome.equalsIgnoreCase("moneyUp"))
			{
				player.moneyUp();
			}
			else if(eventOutcome.equalsIgnoreCase("moneyDown"))
			{
				player.moneyDown();
			}
		}
			
	}
	
	public int getMinAge()
	{
		return this.minAge;
	}
	
	public int getMaxAge()
	{
		return this.maxAge;
	}
}
