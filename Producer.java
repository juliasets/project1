package project1;

public class Producer extends Thread {
	private Drop drop;
	
	public Producer(Drop d) {
		drop = d;
	}
	
	public void run() {
		Job j = new Job(1, -0.5, 0.5, -0.5, 0.5, 10);
		drop.put(j);
		
		int[][] data = drop.getData(j);
	}
}