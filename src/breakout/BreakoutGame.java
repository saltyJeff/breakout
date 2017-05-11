package breakout;

public class BreakoutGame implements Runnable {
	private BreakoutCallbacks callback;
	private long lastTick = System.currentTimeMillis();
	private int[][] blocks;
	public static final double DELTA_TICK = Config.PHYSICS_TICK / 1000.0; // 25 ticks per second
	private Ball ball;

	public BreakoutGame(BreakoutCallbacks bc) {
		callback = bc;
		ball = new Ball();
		blocks = new int[Config.BOARD_HEIGHT][Config.BOARD_WIDTH];
	}

	public void run() {
		/*for (int i = BOARD_HEIGHT / 2; i < BOARD_HEIGHT; i++) {
			for (int j = 0; j < BOARD_WIDTH; j++) {
				blocks[i][j] = i - BOARD_HEIGHT / 2 + 1;
			}
		}*/ //normal setup
		//<CUSTOM TESTING SETUP>
		for(int i = 0; i < Config.BOARD_WIDTH; i++) {
			blocks[Config.BOARD_HEIGHT - 1][i] = 5;
		}
		callback.ready();
		while (true) { // edgy
			if (System.currentTimeMillis() < lastTick + DELTA_TICK) {
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

	private int collisionCheck() {
		// Checks to see if ball intersects with the x or y comp of a table
		// cell, inverts if true
		// 0 = nothing, 1 = x-invert, 2= y-invert, 3= all-invert
		Vector[] bounds = ball.getBounds();
		int row;
		int col;
		int block;
		for (Vector v : bounds) {
			row = Config.BOARD_HEIGHT - (int) (v.y);
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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int[] row : blocks) {
			sb.append('|');
			for(int n : row) {
				if(n != 0) {
					sb.append("["+n+"]");
				}
				else {
					sb.append("   ");
				}
			}
			sb.append("|\n");
		}
		return sb.toString();
	}
}
