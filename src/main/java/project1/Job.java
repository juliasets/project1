package project1;

import java.util.concurrent.ThreadLocalRandom;

public class Job{
	private double c1;
	private double c2;
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	private double resolution;
	protected String id;
	//private String shortid;
	
	public Job(double c1, double c2, double xmin, double xmax, double ymin, double ymax, 
		double resolution)
	{
		this.c1 = c1;
		this.c2 = c2;
		this.xmin = xmin;
		this.xmax = xmax;
		this.ymin = ymin;
		this.ymax = ymax;
		this.resolution = resolution;
		this.id = String.format("%f-%f-%f-%f-%f-%f-%f-%d", c1, c2, resolution, xmin, xmax, ymin, ymax, ThreadLocalRandom.current() .nextInt(0, 65536));
		//this.shortid = String.format("%f-%f", c, resolution);
	}
	
	public double getC1(){ return c1;}
	public double getC2(){ return c2;}
	public double getXmin(){ return xmin;}
	public double getXmax(){ return xmax;}
	public double getYmin(){ return ymin;}
	public double getYmax(){ return ymax;}
	public double getRes(){ return resolution;}
	public String getId(){ return id;}
	public String getSubId(){ return null;}
}
