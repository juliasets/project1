package project1;

import java.util.Random;

public class UpdateScreen extends Thread {

	private int[][] data;
	private DisplayPixels disp;

	public UpdateScreen (DisplayPixels display) {
		data = new int[10][20];
		for (int i = 0; i < data.length; ++i) {
			for (int j = 0; j < data[i].length; ++j) {
				data[i][j] = 0;
			}
		}
		disp = display;
	}

	public void run () {
		try {
			for (;;) {
				Thread.sleep(16);
				Random rand = new Random();
				int[][] data = new int[10][20];
				for (int i = 0; i < data.length; ++i) {
					for (int j = 0; j < data[i].length; ++j) {
						data[i][j] = (this.data[i][j] + rand.nextInt(8)) % 256;
					}
				}
				this.data = data;
				disp.updatePixels(data);
			}
		} catch (InterruptedException e) {
			return;
		}
	}

}
