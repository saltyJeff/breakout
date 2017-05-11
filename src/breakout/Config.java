package breakout;

public final class Config { //cuz static classes aren't a thing :P
	private Config () {};
	public static final int BOARD_WIDTH = 4;
	public static final int BOARD_HEIGHT = 7;
	public static final double BALL_RADIUS = 0.1;
	public static final int PHYSICS_TICK = 1000/20;
	public static final int RENDER_TICK = 1000/50;
}
