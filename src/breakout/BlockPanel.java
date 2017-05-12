package breakout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

public class BlockPanel extends JPanel {
	private static final long serialVersionUID = -1379508343106406529L;

	public BlockPanel() {
		super();
		setOpaque(false);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		graphics.setStroke(new BasicStroke(1));
		for (int r = Config.BOARD_HEIGHT-1; r >= 0; r--) {
			for (int c = Config.BOARD_WIDTH; c >= 0; c--) {
				g.drawRect(c*Config.JPC, r*Config.JPC, Config.JPC, Config.JPC);
			}
		}
	}
}
