package breakout;

public class BreakoutGame implements Runnable {
	private BreakoutCallbacks callback;
	private long lastTick = System.currentTimeMillis();
	private int[][] blocks;
	public static final double TICK_DELTA = 0.04; // 25 ticks per second
	public static final int BOARD_WIDTH = 20;
	public static final int BOARD_HEIGHT = 30;
	private Ball ball;

	public BreakoutGame(BreakoutCallbacks bc) {
		callback = bc;
		ball = new Ball();
		blocks = new int[BOARD_HEIGHT][BOARD_WIDTH];
	}

	public void run() {
		for (int i = BOARD_HEIGHT / 2; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				blocks[i][j] = i - BOARD_HEIGHT / 2 + 1;
			}
		}
		callback.ready();
		while (true) { // edgy
			if (System.currentTimeMillis() < lastTick + TICK_DELTA * 1000) {
				continue; // not time for an update
			}
			simulate();
			callback.onUpdate(blocks, ball); // placeholder
			lastTick = System.currentTimeMillis();
		}
	}

	private void simulate() {
		ball.goToNext();
		int reflectOpt; // reflect option
		// TODO: REFLECT CORNERS + BOTTOM
		reflectOpt = collisionCheck();
		switch (reflectOpt) {
		case 1:
			ball.invertXVector();
			break;
		case 2:
			ball.invertYVector();
			break;
		case 3:
			ball.invertBoth();
			break;
		default:
			break;
		}
	}

	private int collisionCheck() {
		// Checks to see if ball intersects with the x or y comp of a table
		// cell, inverts if true
		// 0 = nothing, 1 = x-invert, 2= y-invert, 3= all-invert
		Vector[] bounds = ball.getBounds();
		int row;
		int col;
		int block;
		for (Vector v : bounds) {
			row = (int) (v.y);
			col = (int) (v.x);
			block = blocks[row][col];
			if (block == 0)
				continue;
			Vector blockPos = new Vector(col + 0.5, row + 0.5);
			double xDist = Math.abs(blockPos.x - v.x);
			double yDist = Math.abs(blockPos.y - v.y);
			if (xDist > yDist) {
				return 1;
			} else if (yDist > xDist) {
				return 2;
			} else {
				return 3;
			}
		}
		return 0;
	}
}
