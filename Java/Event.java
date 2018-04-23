
public class Event {
	private int eventID;
	private String eventTitle;
	private int type;
	private int minAge;
	private int maxAge;
	private int alignmentTrigger;
	private boolean interactive;
	private Person eventPerson;
	private String event;
	private String eventOutcome;
	
	public Event()
	{
		this.eventID = 0;
		this.eventTitle = "";
		this.minAge = 0;
		this.maxAge = 90;
		this.alignmentTrigger = 0;
		this.interactive = false;
		this.eventPerson = null;
		this.event = "";
		this.eventOutcome = "";
	}
	
	public Event(int id, String title, int min, int max, int trigger,boolean interactive, Person p, String event)
	{
		this.eventID = id;
		this.eventTitle = title;
		this.minAge = min;
		this.maxAge = max;
		this.alignmentTrigger = trigger;
		this.interactive = interactive;
		this.eventPerson = p;
		this.event = event;
		this.eventOutcome = "";
	}
	
	public boolean ageTrigger(Player player)
	{
		if(player.getAge() < minAge || player.getAge() > maxAge)
		{
			return false;
		}
		return true;
	}
	
	public boolean alignmentTrigger(Player player)
	{
		boolean trigger = true;
		if(this.alignmentTrigger < 0)
		{
			if(player.getAlignment() > this.alignmentTrigger)
			{
				trigger = false;
			}
		}
		else if(this.alignmentTrigger >= 0)
		{
			if(player.getAlignment() < this.alignmentTrigger)
			{
				trigger = false;
			}
		}
		return trigger;
	}
	
	public String printEvent()
	{
		return this.event;
	}
	
	public String printEventOutcome()
	{
		return this.eventOutcome;
	}
	
	public void runEvent(Player player)
	{
		if(interactive)
		{
			//Player interaction goes here
		}
		else
		{
			//
		}
			
	}
}
