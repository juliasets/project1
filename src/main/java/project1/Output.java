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
		
		int width = (int)Math.round((xmax - xmin)*resolution);
		int height = (int)Math.round((ymax - ymin)*resolution);
		data = new int[width][height];
		set = new boolean[width][height];
		
		full = false;
	}
	
	
	public void insertData(Job j, int[][] data)
	{
		int xOff = (int)Math.round((j.getXmin() - xmin)*resolution);
		int yOff = (int)Math.round((j.getYmin() - ymin)*resolution);

		
		if ((xOff < 0) || (yOff < 0))
			return;
		
		int width = (int)Math.round((j.getXmax() - j.getXmin())*resolution);
		int height = (int)Math.round((j.getYmax() - j.getYmin())*resolution);
		
		/*if (((xOff + width) > set.length) || ((yOff + height) > set[0].length))
			return;*/
		
		for (int row = 0; (row < data.length)&&(xOff + row < set.length); row++)
		{
			for (int col = 0; (col < data[0].length)&&(yOff + col < set[0].length); col++)
			{
				this.data[xOff + row][yOff + col] = data[row][col];
				set[xOff + row][yOff + col] = true;
			}
		}
		
		boolean hole = false;
		
		for (int row = 0; row < set.length; row++)
		{
			for (int col = 0; col < set[row].length; col++)
			{
				hole = hole || (!set[row][col]);
				if (hole)
				{
					full = false;
					break;
				}
			}
			if (hole)
			{
				full = false;
				break;
			}
		}
		if (!hole)
		{
			full = true;
		}
		//System.out.println("full " + full);
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
