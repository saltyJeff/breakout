package breakout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.*;

import javax.swing.*;

public class BreakoutPanel extends JPanel {
	private static final int TICK_DELTA = 1000/30;
	private Timer timer = new Timer(TICK_DELTA, new ActionListener () {
		public void actionPerformed(ActionEvent e) {
			repaint();
		}
	});
	public BreakoutPanel() {
		super();
		timer.start();
	}
	private static final long serialVersionUID = 1591952185726646237L;
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Dimension d = getSize();
		int x = (int)(d.getWidth()*Math.random());
		int y = (int)(d.getHeight()*Math.random());
		g.fillOval(x,y,10,10);
	}
}
