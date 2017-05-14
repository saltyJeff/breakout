package breakout;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.*;

public class BlockPanel extends JPanel {

	private static final long serialVersionUID = -1379508343106406529L;
	private double period = Math.PI * 2 / Config.MAX_BLOCK;
	private double step = Math.PI * 2 / 3;
	private Color[] colors = new Color[Config.MAX_BLOCK];
	public BlockPanel() {
		super();
		setOpaque(false);
		for(int i = 0; i < Config.MAX_BLOCK; i++) {
			colors[i] = rainbowify(i+1);
		}
	}
	private Color rainbowify (int blockVal) {
		int r = (int)Math.round(127 * Math.cos(period * (blockVal))) + 128;
		int g = (int)Math.round(127 * Math.cos(period * (blockVal - step))) + 128;
		int b = (int)Math.round(127 * Math.cos(period * (blockVal - 2*step))) + 128;
		return new Color(r,g,b);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D graphics = (Graphics2D) g;
		graphics.setStroke(new BasicStroke(1));
		for (int r = 0; r < Config.BOARD_HEIGHT; r++) {
			for (int c = 0; c < Config.BOARD_WIDTH; c++) {
				int val = BreakoutGame.getBlocks()[r][c];
				if(val == 0) {
					continue;
				}
				g.setColor(colors[val-1]);
				g.fillRect(c * Config.JPC, r * Config.JPC, Config.JPC, Config.JPC);
				g.setColor(Color.WHITE);
				g.drawRect(c * Config.JPC, r * Config.JPC, Config.JPC, Config.JPC);
			}
		}
	}
}
