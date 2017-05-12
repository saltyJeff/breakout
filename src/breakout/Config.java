package breakout;

import java.awt.Dimension;

public final class Config { //cuz static classes aren't a thing :P
	private Config () {};
	public static final int BOARD_WIDTH = 20;
	public static final int BOARD_HEIGHT = 17;
	public static final double BALL_RADIUS = 0.2;
	public static final int PHYSICS_TICK = 1000/20;
	public static final int RENDER_TICK = 1000/60;
	public static final int JPC = 40;
	public static final Vector INITIAL_POS = new Vector(BOARD_WIDTH - 1, BOARD_HEIGHT*0.25);
	public static final Vector INITIAL_VEL = Vector.BOTLEFT.multiply(7);
	//dont touch
	public static final double PHYSICS_DELTA = PHYSICS_TICK * 0.001;
	public static final double RENDER_DELTA = RENDER_TICK * 0.001;
	public static final Dimension DIMENSIONS = new Dimension(Config.BOARD_WIDTH*Config.JPC, Config.BOARD_HEIGHT*Config.JPC);
}
