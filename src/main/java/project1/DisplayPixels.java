package project1;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class DisplayPixels {

	private int[][] data;
	private Canvas canvas;

	public DisplayPixels (Shell shell) {
		data = new int[1][1];
		data[0][0] = 0;
		shell.setLayout(new FillLayout());
		canvas = new Canvas(shell, SWT.NONE);
		canvas.addPaintListener(new PaintListener(){
			public synchronized void paintControl (PaintEvent e) {
				Rectangle rect = ((Canvas) e.widget).getBounds();
				for (int i = 0; i < data.length; ++i) {
					for (int j = 0; j < data[i].length; ++j) {
						Color color = new Color(Display.getCurrent(), 0, 0, data[i][j]);
						e.gc.setBackground(color);
						color.dispose();
						int left = j * rect.width / data[i].length;
						int right = (j + 1) * rect.width / data[i].length;
						int top = i * rect.height / data.length;
						int bottom = (i + 1) * rect.height / data.length;
						e.gc.fillRectangle(left, top, right - left, bottom - top);
					}
				}
			}
		});
	}

	public synchronized void updatePixels (int[][] newdata) {
		data = newdata;
		Display.getDefault().asyncExec(new Runnable() {
            public void run() {
            	canvas.redraw();
            	canvas.update();
            }
         });
	}

}
