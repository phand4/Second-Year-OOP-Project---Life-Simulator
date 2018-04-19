import java.math.BigDecimal;

public class Job implements GameObject{
	
	private String jobTitle;
	private String company;
	private BigDecimal salary;
	
	public Job(String job, String company, BigDecimal salary)
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
	
	public BigDecimal getSalary()
	{
		return this.salary;
	}
	
	public String toString()
	{
		return getTitle() + " for " + getCompany();
	}
}
