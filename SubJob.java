package project1

public class SubJob extends Job{
	private String masterId;
	
	public SubJob(c, xmin, xmax, ymin, ymax, resolution, masterId)
	{
		super(c, xmin, xmax, ymin, ymax, resolution);
		this.masterId = masterId;
	}
	
	public String getId() { return masterId;}
}
