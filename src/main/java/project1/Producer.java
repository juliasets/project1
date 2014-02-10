package project1;

public class Producer extends Thread {
	private Drop drop;
	
	public Producer(Drop d) {
		drop = d;
	}
	
	public void run() {
		Job j = new Job(1, -0.5, 0.5, -0.5, 0.5, 10);
		System.out.println("Producer");
		drop.put(j);
		System.out.println("P put job");
		int[][] data = drop.getData(j);
		System.out.println("P received data");
	}
}
