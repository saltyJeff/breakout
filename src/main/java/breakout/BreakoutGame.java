package breakout;

public class BreakoutGame implements Runnable {
	private BreakoutCallbacks callback;
	private long lastTick = System.currentTimeMillis();
	private int[][] blocks;
	public static final double TICK_DELTA = 0.04; //25 ticks per second
	public static final int BOARD_WIDTH = 20;
	public static final int BOARD_HEIGHT = 30;
	private Ball ball;
	
	public BreakoutGame(BreakoutCallbacks bc) {
		callback = bc;
		ball = new Ball();
	}
	public void run () {
		blocks = new int[BOARD_HEIGHT][];
		for(int i = BOARD_HEIGHT/2; i < BOARD_HEIGHT; i++) {
			blocks[i] = new int[BOARD_WIDTH];
			for(int j = 0; j < BOARD_WIDTH; j++) {
				blocks[i][j] = i - BOARD_HEIGHT/2 + 1;
			}
		}
		callback.ready();
		while (true) { //edgy
			if(System.currentTimeMillis() < lastTick + TICK_DELTA*1000) {
				continue; //not time for an update
			}
			simulate();
			callback.onUpdate(blocks, ball); //placeholder
			lastTick = System.currentTimeMillis();
		}
	}
	private void simulate () {
		ball.goToNext();
	}
}
