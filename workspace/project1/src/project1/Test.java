package project1;

import org.eclipse.swt.widgets.*;

public class Test {

	public void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Julia");
	    UpdateScreen updater = new UpdateScreen(new DisplayPixels(shell));
	    updater.start();
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		updater.interrupt();
		try {
			updater.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		display.dispose();
	}

	public static void main (String[] args) {
		new Test().run();
	}

}
