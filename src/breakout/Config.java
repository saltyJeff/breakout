package breakout;

import java.awt.Dimension;

public final class Config { //cuz static classes aren't a thing :P
	private Config () {};
	public static final int BOARD_WIDTH = 10;
	public static final int BOARD_HEIGHT = 20;
	public static final double BALL_RADIUS = 0.4;
	public static final int PHYSICS_TICK = 1000;
	public static final int RENDER_TICK = 1000/50;
	public static final int JPC = 40;
	//dont touch
	public static final double PHYSICS_DELTA = PHYSICS_TICK * 0.001;
	public static final Dimension DIMENSIONS = new Dimension(Config.BOARD_WIDTH*Config.JPC, Config.BOARD_HEIGHT*Config.JPC);
}
