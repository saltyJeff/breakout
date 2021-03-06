package breakout;

import java.awt.Dimension;
/**
 * Configuration constants for the game
 */
public final class Config { //cuz static classes aren't a thing :P
	private Config () {};
	public static final int BOARD_WIDTH = 14;
	public static final int BOARD_HEIGHT = 12;
	public static final double BALL_RADIUS = 0.1;
	public static final int PHYSICS_TICK = 1000/110;
	public static final int RENDER_TICK = 1000/70;
	public static final int JPC = 70;
	public static final int MAX_BLOCK = 3;
	public static final Vector INITIAL_POS = new Vector(0.5,Config.BOARD_HEIGHT - 3);
	public static final Vector INITIAL_VEL = new Vector(1,1).multiply(18);
	public static final double SPEED_FACTOR = 0.02;
    public static final double PADDLE_HEIGHT = 0.7;
    public static final int PADDLE_WIDTH = 5;
    public static final int PADDLE_SPEED = 20;
	//dont touch
	public static final double PHYSICS_DELTA = PHYSICS_TICK * 0.001;
	public static final double RENDER_DELTA = RENDER_TICK * 0.001;
	public static final Dimension DIMENSIONS = new Dimension(Config.BOARD_WIDTH*Config.JPC, Config.BOARD_HEIGHT*Config.JPC);
	public static final Vector FASTER_VEC = new Vector(SPEED_FACTOR, SPEED_FACTOR);
    public static int PADDLE_X = (Config.BOARD_WIDTH*Config.JPC)/2- ((Config.BOARD_WIDTH*Config.JPC)/10);
}
