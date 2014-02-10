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
	int cores = Runtime.getRuntime().availableProcessors();
	ArrayList<Consumer> consumers;
	for (int i = 1; i <= cores; ++i)
	    {
		Consumer c = new Consumer(d, i);
		consumers.add(c);
	    }
        p1.start();
	for (int i = 0; i < cores; ++i)
	    {
		consumers.get(i).start();
	    }
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
