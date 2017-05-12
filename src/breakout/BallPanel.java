package breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.*;

public class BallPanel extends JPanel {
	private int guessTicks;
	private Vector position;
	private Vector velocity;
	public BallPanel() {
		super();
		setOpaque(false);
	}

	private static final long serialVersionUID = 1591952185726646237L;
	//INVERT ALL THE Y-VARIABLES (Y U SO STUPID SWING)
	public void updateInfo(Vector p, Vector v) {
		position = p;
		position.y = Config.BOARD_HEIGHT - position.y;
		velocity = v;
		velocity.y *= -1;
		guessTicks = 0;
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		guessTicks++;
		g.setColor(Color.red);
		int x = (int) ((position.x - Config.BALL_RADIUS) * Config.JPC + velocity.x * guessTicks/1000);
		int y = (int) ((position.y + Config.BALL_RADIUS) * Config.JPC + velocity.y * guessTicks/1000);
		int rad = (int) (Config.BALL_RADIUS * Config.JPC);
		g.fillOval(x, y, rad, rad);
	}
}
