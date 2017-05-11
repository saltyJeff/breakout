package breakout;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

public class BreakoutPanel extends JPanel {	
	public BreakoutPanel() {
		super();
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
