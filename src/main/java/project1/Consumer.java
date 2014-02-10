package project1;

import java.lang.Math;

public class Consumer extends Thread {
	private Drop drop;
	private int number;
	private int mlimit = 2000;
	private int ylimit = 200;
	private double diver = 2;
	
	public Consumer(Drop d, int number) {
		drop = d;
		this.number = number;
	}
	
	public int julia(double a, double b, double c, int d) {
		if (Math.sqrt(a*a+b*b) > diver || d > 255) {
			return d;
		}
		i = a*a - b*b + c;
		j = 2*a*b;
		return julia(i,j,c,d+1);
	}

	public void run() {
		//only handles single job
		//need to loop and check if claimed and unclaimed empty before closing thread
		Job j;
		while ((j=drop.claim())!=NULL)
		{
			//check size of job
			double jc = j.getC();
			double jxmax = j.getXmax();
			double jxmin = j.getXmin();
			double jymax = j.getYmax();
			double jymin = j.getYmin();
			double jres = j.getRes();
			int xrange = ceil((jxmax-jxmin)*jres);
			int yrange = ceil(jymax-jymin*jres);
			int size = xrange*yrange;
			//job too big
			if (size > mlimit && yrange > 1) {
				//break into subjobs
				int subs = Math.ceil(yrange/ylimit); //number of new subjobs
				int lines = Math.floor(yrange/subs); //max number of lines per sub
				double cap = jymax; //max jymax for subjobs
				for (int dex = 0; dex < subs; dex = dex + 1) {
					//update rows
					jymin = jymin + dex * lines;
					jymax = jymin + lines > cap ? cap : jymin + lines;
					//make new job
					jnew = Job(jc,jxmin,jxmax,jymin,jymax,jres);
					drop.put(jnew);
				}
			}
			else {
				//do job
				int[][] depths = new int[xrange][yrange];
				for (int x = 0; x < xrange; x = x + 1) {
	 				for (int y = 0; y < yrange; y = y + 1) {
						d = julia(jxmin + x * jres, jymin + y * jres, jc, 0);
						depths[x][y] = d;
					}
				}
				drop.putData(j,depths);
			}
			//close job
			drop.finish(j);
		}
	}
}