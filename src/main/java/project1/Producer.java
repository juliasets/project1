package project1;

public class Producer extends Thread {
	private Drop drop;
	private DisplayPixels dp;
	
	public Producer(Drop d, DisplayPixels dp) {
		drop = d;
		this.dp = dp;
	}
	
	public void run() {
		Job j = new Job(-0.4, 0.6, -1, 1, -1, 1, 200);
		System.out.println("Producer");
		drop.put(j);
		System.out.println("P put job");
		int[][] data = drop.getData(j);
		dp.updatePixels(data);
		System.out.println("P received data");
		for (int i = 0; i < 100; i++)
		{
			System.out.println("" + i + " " + data[50][i]);
		}
	}
}
