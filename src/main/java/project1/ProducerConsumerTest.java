package project1;

import org.eclipse.swt.widgets.*;

public class ProducerConsumerTest {

	public void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Julia");
		DisplayPixels dp = new DisplayPixels(shell);

		// Updater related
	    UpdateScreen updater = new UpdateScreen(dp);
	    updater.start();

        Drop d = new Drop();
        Producer p1 = new Producer(d);
        Consumer c1 = new Consumer(d, 1);
        p1.start();
        c1.start();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		// Updater related
		updater.interrupt();
		try {
			updater.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		display.dispose();
	}

	public static void main(String[] args) {
		new ProducerConsumerTest().run();
    }
}