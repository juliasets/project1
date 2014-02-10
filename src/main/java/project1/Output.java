package project1;

import java.util.*;

public class Output{
	private int[][] data;
	
	private boolean[][] set;
	
	private double xmin;
	private double xmax;
	private double ymin;
	private double ymax;
	private double resolution;
	private boolean full;
	
	public Output(Job j)
	{
		xmin = j.getXmin();
		xmax = j.getXmax();
		ymin = j.getYmin();
		ymax = j.getYmax();
		resolution = j.getRes();
		
		int width = (int)Math.ceil((xmax - xmin)*resolution);
		int height = (int)Math.ceil((ymax - ymin)*resolution);
		data = new int[width][height];
		set = new boolean[width][height];
		
		full = false;
	}
	
	
	public synchronized void insertData(Job j, int[][] data)
	{
		int xOff = (int)Math.ceil((j.getXmin() - xmin)*resolution);
		int yOff = (int)Math.ceil((j.getYmin() - ymin)*resolution);
		
		System.out.println("xOff " + xOff);
		System.out.println("yOff " + yOff);
		
		if ((xOff < 0) || (yOff < 0))
			return;
		
		int width = (int)Math.ceil((j.getXmax() - j.getXmin())*resolution);
		int height = (int)Math.ceil((j.getYmax() - j.getYmin())*resolution);
		
		System.out.println("width " + width);
		System.out.println("height " + height);
		
		if (((xOff + width) > set.length) || ((yOff + width) > set[0].length))
			return;
			
		System.out.println("w " + set.length);
		System.out.println("h " + set[0].length);
		
		System.out.println("wd " + data.length);
		System.out.println("hd " + data[0].length);
		
		for (int row = 0; row < width; row++)
		{
			for (int col = 0; col < height; col++)
			{
				this.data[xOff + row][yOff + col] = data[row][col];
				set[xOff + row][yOff + col] = true;
			}
		}
		
		for (int row = 0; row < set.length; row++)
		{
			for (int col = 0; col < set[row].length; col++)
			{
				full = full || set[row][col];
				if (full)
					break;
			}
			if (full)
				break;
		}
		System.out.println("full " + full);
		/*if (full)
			notifyAll();*/
	}
	
	public boolean isFull()
	{
		return full;
	}
	
	public int[][] getData()
	{
		return data;
	}
}
