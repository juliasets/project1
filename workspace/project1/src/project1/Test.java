package project1;

import org.eclipse.swt.widgets.*;

public class Test {

	public void run() {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Julia");
		new DisplayPixels(shell);
		shell.open();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		display.dispose();
	}

	public static void main (String[] args) {
		new Test().run();
	}

}
