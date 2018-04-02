public class Person {
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