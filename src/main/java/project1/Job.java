package project1;

import java.util.concurrent.ThreadLocalRandom;

public class Job{
	private double c;
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	private double resolution;
	protected String id;
	//private String shortid;
	
	public Job(double c, double xmin, double xmax, double ymin, double ymax, 
		double resolution)
	{
		this.c = c;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.resolution = resolution;
		this.id = String.format("%f-%f-%f-%f-%f-%f-%d", c, resolution, xmin, xmax, ymin, ymax, ThreadLocalRandom.current() .nextInt(0, 65536));
		//this.shortid = String.format("%f-%f", c, resolution);
	}
	
	public double getC(){ return c;}
	public double getXmin(){ return xmin;}
	public double getXmax(){ return xmax;}
	public double getYmin(){ return ymin;}
	public double getYmax(){ return ymax;}
	public double getRes(){ return resolution;}
	public String getId(){ return id;}
	public String getSubId(){ return null;}
}