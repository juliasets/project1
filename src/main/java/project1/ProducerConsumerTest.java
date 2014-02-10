package project1;

import org.eclipse.swt.widgets.*;

public class ProducerConsumerTest {

	public void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Julia");
		DisplayPixels dp = new DisplayPixels(shell);


        Drop d = new Drop();
        Producer p1 = new Producer(d, dp);
        Consumer c1 = new Consumer(d, 1);
        Consumer c2 = new Consumer(d, 2);
        Consumer c3 = new Consumer(d, 3);
        Consumer c4 = new Consumer(d, 4);
        p1.start();
        c1.start();
        c2.start();
        c3.start();
        c4.start();

		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}

		display.dispose();
	}

	public static void main(String[] args) {
		new ProducerConsumerTest().run();
    }
}
