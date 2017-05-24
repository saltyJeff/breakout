package breakout;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;
/**
 * A panel used to show the ball/paddle
 */
public class BallPanel extends JPanel {
	private int guessTicks;
	private Vector position = Vector.ZERO;
	private Vector velocity = Vector.ZERO;
	private double paddlePos = 0;
	public BallPanel() {
		super();
		setOpaque(false);
	}
	private static final long serialVersionUID = 1591952185726646237L;
	public void updateInfo(Vector p, Vector v, double pp) {
		position = p;
		velocity = v;
		guessTicks = 0;
		paddlePos = pp;
	}
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		guessTicks++;
		g.setColor(Color.WHITE);
		int x = (int) (position.x  * Config.JPC + velocity.x * guessTicks * Config.RENDER_DELTA);
		int y = (int) (position.y * Config.JPC + velocity.y * guessTicks * Config.RENDER_DELTA);
		int di = (int) (2*Config.BALL_RADIUS * Config.JPC);
		g.fillOval(x, y, di, di);
        g.fillRect((int)(paddlePos*Config.JPC), (int)(Config.BOARD_HEIGHT*Config.JPC), (int)(Config.PADDLE_WIDTH * Config.JPC), (int)(Config.PADDLE_HEIGHT * Config.JPC));
		g.setColor(Color.RED);
		g.drawLine(0, (int)(Config.BOARD_HEIGHT*Config.JPC)+1, (int)(Config.BOARD_WIDTH*Config.JPC), (int)(Config.BOARD_HEIGHT*Config.JPC)+1);
	}
}
