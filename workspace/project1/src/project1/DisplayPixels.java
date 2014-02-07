package project1;

import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

public class DisplayPixels {

	private int[][] data;

	public DisplayPixels (Shell shell) {
		Random rand = new Random();
		data = new int[10][20];
		for (int i = 0; i < data.length; ++i) {
			for (int j = 0; j < data[i].length; ++j) {
				data[i][j] = rand.nextInt(256);
			}
		}

		shell.setLayout(new FillLayout());
		Canvas canvas = new Canvas(shell, SWT.NONE);
		canvas.addPaintListener(new PaintListener(){
			public void paintControl (PaintEvent e) {
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

}
