package breakout;

public class BreakoutGame implements Runnable {
	private BreakoutCallbacks callback;
	private long lastTick = System.currentTimeMillis();
	private int[][] blocks;
	private static final long TICK_DELTA = 40; //25 ticks per second
	private static final int BOARD_WIDTH = 20;
	private static final int BOARD_HEIGHT = 20;

	public BreakoutGame(BreakoutCallbacks bc) {
		callback = bc;
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
			if(System.currentTimeMillis() < lastTick + TICK_DELTA) {
				continue; //not time for an update
			}
			callback.onUpdate(blocks, null); //placeholder
			lastTick = System.currentTimeMillis();
		}
	}
}
