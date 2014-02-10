package project1;

public class SubJob extends Job{
	private String masterId;
	
	public SubJob(double c1, double c2, double xmin, double xmax, double ymin, double ymax, 
		double resolution, String masterId)
	{
		super(c1, c2, xmin, xmax, ymin, ymax, resolution);
		this.masterId = masterId;
	}
	
	public String getId() { return masterId;}
	public String getSubId() { return id;}
}
