public class Job implements GameObject{
	
	private String jobTitle;
	private String company;
	private float salary;
	
	public Job(String job, String company, float salary)
	{
		this.jobTitle = job;
		this.company = company;
		this.salary = salary;
	}
	
	public String getTitle()
	{
		return this.jobTitle;
	}
	
	public String getCompany()
	{
		return this.company;
	}
	
	public float getSalary()
	{
		return this.salary;
	}
	
	public String toString()
	{
		return getTitle() + " for " + getCompany();
	}
}
