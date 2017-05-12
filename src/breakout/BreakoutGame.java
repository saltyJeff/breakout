package breakout;

public class BreakoutGame implements Runnable {
	private BreakoutCallbacks callback;
	private long lastTick = System.currentTimeMillis();
	private static int[][] blocks;
	private static Ball ball;

	public BreakoutGame(BreakoutCallbacks bc) {
		callback = bc;
		ball = new Ball();
		blocks = new int[Config.BOARD_HEIGHT][Config.BOARD_WIDTH];
	}

	public void run() {
		/*
		 * for (int i = BOARD_HEIGHT / 2; i < BOARD_HEIGHT; i++) { for (int j =
		 * 0; j < BOARD_WIDTH; j++) { blocks[i][j] = i - BOARD_HEIGHT / 2 + 1; }
		 * }
		 */ // normal setup
		// <CUSTOM TESTING SETUP>
		for (int i = 0; i < Config.BOARD_WIDTH; i++) {
			blocks[Config.BOARD_HEIGHT - 1][i] = 5;
		}
		callback.ready();
		while (true) { // edgy
			if (System.currentTimeMillis() < lastTick + Config.PHYSICS_TICK) {
				continue; // not time for an update
			}
			simulate();
			callback.onUpdate(blocks, new Vector(ball.position), new Vector(ball.velocity)); // placeholder
			lastTick = System.currentTimeMillis();
		}
	}

	private void simulate() {
		ball.goToNext();
		int reflectOpt; // reflect option
		reflectOpt = borderCheck();
		if (reflectOpt == 0) {
			reflectOpt = collisionCheck();
		}
		switch (reflectOpt) {
		case 1:
			ball.invertAndShiftX();
			break;
		case 2:
			ball.invertAndShiftY();
			break;
		case 3:
			ball.invertAndShiftBoth();
			break;
		default:
			break;
		}
	}

	/**
	 * Checks the borders for collisions
	 * 
	 * @return 0 for nothing, 1 for x-invert, 2 for y-invert, 3 for all-invert
	 */
	private int borderCheck() {
		Vector[] bounds = ball.getBounds();
		int row;
		int col;
		for (Vector v : bounds) {
			row = Config.BOARD_HEIGHT - 1 - (int) (v.y);
			col = (int) (v.x);
			boolean outOfRow = v.y < 0 || !(0 <= row && row < Config.BOARD_HEIGHT); //floor negative number = rounding up??
			boolean outOfCol = v.x < 0 || !(0 <= col && col < Config.BOARD_WIDTH);
			if (outOfRow && outOfCol) {
				return 3;
			} else if (outOfRow) {
				return 2;
			} else if (outOfCol) {
				return 1;
			}
		}
		return 0;
	}

	/**
	 * Checks the borders for collisions
	 * 
	 * @return 0 for nothing, 1 for x-invert, 2 for y-invert, 3 for all-invert
	 */
	private int collisionCheck() {
		Vector[] bounds = ball.getBounds();
		int row;
		int col;
		int block;
		for (Vector v : bounds) {
			row = Config.BOARD_HEIGHT - 1 - (int) (v.y);
			col = (int) (v.x);
			block = blocks[row][col];

			if (block == 0) {
				continue;
			}
			blocks[row][col]--;
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
