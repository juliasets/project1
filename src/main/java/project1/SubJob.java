package project1;

public class SubJob extends Job{
	private String masterId;
	
	public SubJob(double c, double xmin, double xmax, double ymin, double ymax, 
		double resolution, String masterId)
	{
		super(c, xmin, xmax, ymin, ymax, resolution);
		this.masterId = masterId;
	}
	
	public String getId() { return masterId;}
	public String getSubId() { return id;}
}
