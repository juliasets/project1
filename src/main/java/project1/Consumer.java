package project1;

import java.lang.Math;

public class Consumer extends Thread {
	private Drop drop;
	//limit is nominal limit, limit2 is strict limit
	private int mlimit = 5000;
	//private int mlimit2 = 2000;
	private int ylimit = 250;
	//private int ylimit2 = 200;
	private double diver = 2;
	
	public Consumer(Drop d) {
		drop = d;
	}
	
	public int julia(double a, double b, double c1, double c2, int d) {
		if (Math.sqrt(a*a+b*b) > diver || d >= 255) {
			return d;
		}
		double i = a*a - b*b + c1;
		double j = 2*a*b + c2;
		return julia(i,j,c1, c2,d+1);
	}

	public void run() {
		//only handles single job
		//need to loop and check if claimed and unclaimed empty before closing thread
		Job j =drop.claim();
		System.out.println("Consumer");
		System.out.println(j == null);
		while (j != null)
		{
			//check size of job
			//System.out.println("C received job");
			double jc1 = j.getC1();
			double jc2 = j.getC2();
			double jxmax = j.getXmax();
			double jxmin = j.getXmin();
			double jymax = j.getYmax();
			double jymin = j.getYmin();
			double jres = j.getRes();
			String jid = j.getId();
			int xrange = (int)Math.ceil((jxmax-jxmin)*jres) + 1;
			int yrange = (int)Math.ceil((jymax-jymin)*jres) + 1;
			int size = xrange*yrange;
			//job too big, i.e. too many cells
			if (size > mlimit && yrange > 10) {
				int subs;
				int lines;
				//too many lines
				//System.out.println("a");
				/*if (yrange > ylimit) {
					//break into subjobs
					//System.out.println("ylim");
					subs = (int)Math.ceil((double)yrange/ylimit2); //number of new subjobs
					lines = (int)Math.floor(yrange/subs); //max number of lines per sub
				}
				else {
					//break into subjobs
					//System.out.println("mlim");
					subs = (int)Math.ceil(size/mlimit2); //number of new subjobs
					lines = (int)Math.floor(size/subs/xrange); //max number of lines per sub
				}
				System.out.println("resizing " + lines);*/
				//System.out.println("subs " + subs);
				/*double cap = jymax; //max jymax for subjobs
				for (int dex = 0; dex < 4; dex = dex + 1) {
					//update rows
					jymax = (jymin + yrange / jres) > cap ? cap : (jymin + lines / jres);
					//make new job
					SubJob jnew = new SubJob(jc1, jc2,jxmin,jxmax,jymin,jymax,jres,jid);
					drop.put(jnew);
					jymin = jymax;
				}*/
				
				double middle = Math.ceil((jymin + (jymax - jymin)/2)*jres);
				
				SubJob jnew = new SubJob(jc1, jc2, jxmin, jxmax, jymin, (middle + 1)/jres, jres, jid);
				drop.put(jnew);
				jnew = new SubJob(jc1, jc2, jxmin, jxmax, (middle - 1)/jres, jymax, jres, jid);
				drop.put(jnew);
			}
			else {
				//do job
				//System.out.println("C doing");
				int[][] depths = new int[xrange][yrange];
				for (int x = 0; x < xrange; x = x + 1) {
	 				for (int y = 0; y < yrange; y = y + 1) {
						int d = julia(jxmin + x / jres, jymin + y / jres, jc1, jc2, 0);
						depths[x][y] = d;
					}
				}
				drop.putData(j,depths);
			}
			//close job
			drop.finish(j);
			//System.out.println("C finished job");
			j =drop.claim();
		}
		System.out.println("C exiting");
	}
}
