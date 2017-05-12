package breakout;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.*;

public class BallPanel extends JPanel {
	private int guessTicks;
	private Vector position = Vector.ZERO;
	private Vector velocity = Vector.ZERO;
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
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		guessTicks++;
		g.setColor(Color.red);
		int x = (int) ((position.x - Config.BALL_RADIUS) * Config.JPC + velocity.x * guessTicks * Config.RENDER_DELTA);
		int y = (int) ((position.y - Config.BALL_RADIUS) * Config.JPC + velocity.y * guessTicks * Config.RENDER_DELTA);
		int di = (int) (2*Config.BALL_RADIUS * Config.JPC);
		g.fillOval(x, y, di, di);
	}
}
