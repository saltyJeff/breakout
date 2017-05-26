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
        private long a = System.currentTimeMillis();
        /*private JLabel titlegamething = new JLabel(
				"<html><center>"
						+ "<font size='30' color='white'>BREAKOUT!DUDNDUNG</font><br>"
				+"</center><html>"
			, JLabel.CENTER);
*/
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
                Vector flip = new Vector(velocity);
                flip = flip.multiply(1/flip.magnitude());
                flip.x *= -5;
                flip.y *= -5;
                g.setColor(Color.WHITE);
                if(System.currentTimeMillis() - a < 5e3){
                    
                    g.drawString("BREAKOUT", (Config.BOARD_WIDTH*Config.JPC)/2 - 70, (Config.BOARD_HEIGHT*Config.JPC)/2);
                }
                
                //g.drawLine((int)(x + Config.BALL_RADIUS*Config.JPC), (int)(y + Config.BALL_RADIUS*Config.JPC), (int)(x + Config.BALL_RADIUS*Config.JPC) + (int)flip.x*Config.JPC, (int)(y + Config.BALL_RADIUS*Config.JPC) + (int)flip.y*Config.JPC);
                
                //int [] xPoints = {((int)(x ) + (int)flip.x*Config.JPC),(int)(x + Config.BALL_RADIUS*Config.JPC + Config.BALL_RADIUS*Config.JPC),((int)(x + Config.BALL_RADIUS*Config.JPC+ Config.BALL_RADIUS*Config.JPC) + (int)flip.x*Config.JPC),(int)(x )};
                //int [] yPoints = {((int)(y ) + (int)flip.y*Config.JPC),(int)(y + Config.BALL_RADIUS*Config.JPC + Config.BALL_RADIUS*Config.JPC),((int)(y + Config.BALL_RADIUS*Config.JPC+ Config.BALL_RADIUS*Config.JPC) + (int)flip.y*Config.JPC),(int)(y )};
                g.setColor(Color.pink);
                //g.fillPolygon(xPoints, yPoints, xPoints.length);
                g.setColor(Color.CYAN);
                //g.setFont(new Font());
                g.drawString("Score: " + (BreakoutGame.initialBlocks - BreakoutGame.blocksLeft), (Config.BOARD_WIDTH*Config.JPC)- 70, 20);
        }
}
