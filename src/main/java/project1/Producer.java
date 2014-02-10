package project1;

public class Producer extends Thread {
	private Drop drop;
	private DisplayPixels dp;
	
	public Producer(Drop d, DisplayPixels dp) {
		drop = d;
		this.dp = dp;
	}
	
	public void run() {
	    for (double res = 10; res < 500; res *= 1.2) {
		    Job j = new Job(-0.4, 0.6, -1, 1, -1, 1, res);
		    System.out.println("Producer");
		    drop.put(j);
		    System.out.println("P put job");
		    int[][] data = drop.getData(j);
		    dp.updatePixels(data);
		    System.out.println("P received data");
	    }
	}
}
