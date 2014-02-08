package project1;

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
		xmin = j.getXmin;
		xmax = j.getXmax;
		ymin = j.getYmin;
		ymax = j.getYmax;
		resolution = j.getRes;
		
		int width = Math.ceil((xmax - xmin)*resolution);
		int height = Math.ceil((ymax - ymin)*resolution);
		data = new int[width][height];
		set = new boolean[width][height];
		
		full = false;
	}
	
	
	public void insertData(Job j, int[][] data)
	{
		int xOff = Math.ceil((j.getXmin - xmin)*resolution);
		int yOff = Math.ceil((j.getYmin - ymin)*resolution);
		
		if ((xOff < 0) || (yOff < 0))
			return;
		
		int width = Math.ceil((j.getXmax - j.getXmin)*resolution);
		int height = Math.ceil((j.getYmax - j.getYmin)*resolution);
		
		if (((xOff + width) > set.length) || ((yOff + width) > set[0].length))
			return;
		
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
		if (full)
			notifyAll();
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
