
public class Event {
	private int eventID; //ID
	private String eventTitle; //Title of event
	private int type; //
	private int minAge; //Minimum age the player has to be to get this event
	private int maxAge; //Maximum age the player can be to get this event
	private boolean interactive; //Whether the event is interactive or not
	private Person eventPerson; //Additional people associated with the event
	private String event; //Text of the event
	private String eventOutcome; //Event outcome string
	
	public Event()
	{
		this.eventID = 0;
		this.eventTitle = "";
		this.minAge = 0;
		this.maxAge = 90;
		this.interactive = false;
		this.eventPerson = null;
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
		this.eventPerson = null;
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
		return printedEvent;
	}
	
	public String printEventOutcome()
	{
		String outcome = (this.eventOutcome).replaceAll("_", " ");
		return outcome;
	}
	
	public void runEvent(Player player)
	{
		if(interactive)
		{
			//Player interaction goes here
		}
		else
		{
			if(eventOutcome.equals("fameUp"))
			{
				
			}
		}
			
	}
}
