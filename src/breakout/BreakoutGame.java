package breakout;

public class BreakoutGame implements Runnable {

	private BreakoutCallbacks callback;
	private long lastTick = System.currentTimeMillis();
	private static int[][] blocks;
	private static Ball ball;

	public BreakoutGame(BreakoutCallbacks bc) {
		callback = bc;
		ball = new Ball();
	}

	public void run() {
		getBlocks();
		for(int r = 2; r < Config.MAX_BLOCK + 2; r++) {
			for(int c = 1; c < Config.BOARD_WIDTH - 1; c++) {
				blocks[r][c] = Config.MAX_BLOCK - r + 2;
			}
		}
		//blocks[2][11] = 10;
		callback.ready();
		while (true) { // edgy
			if (System.currentTimeMillis() < lastTick + Config.PHYSICS_TICK) {
				continue; // not time for an update
			}
			simulate();
			//System.out.println(String.format("Delta time: %.05f", (System.currentTimeMillis() - lastTick)/1000.0));
			callback.onUpdate(blocks, new Vector(ball.position), new Vector(ball.velocity)); // placeholder
			lastTick = System.currentTimeMillis();
		}
	}
	public static int[][] getBlocks() {
		if(blocks == null) {
			blocks = new int[Config.BOARD_HEIGHT][Config.BOARD_WIDTH];
		}
		return blocks;
	}
	private void simulate() {
		Collision reflectOpt; // reflect option
		reflectOpt = borderCheck();
		if (reflectOpt == Collision.CLEAR) {
			reflectOpt = collisionCheck();
		}
		switch (reflectOpt) {
			case X:
				ball.invertAndShiftX();
				ball.goToNext();
				break;
			case Y:
				ball.invertAndShiftY();
				ball.goToNext();
				break;
			case BOTH:
				System.out.println("Should never be called");
				ball.invertAndShiftX();
				ball.invertAndShiftY();
				ball.goToNext();
				break;
			default:
				break;
		}
		ball.goToNext();
	}

	private Collision borderCheck() {
		Vector[] bounds = ball.getBounds();
		int row;
		int col;
		for (Vector v : bounds) {
			row = (int) (v.y);
			col = (int) (v.x);
			boolean outOfRow = v.y < 0 || !(0 <= row && row < Config.BOARD_HEIGHT); //floor negative number = rounding up??
			boolean outOfCol = v.x < 0 || !(0 <= col && col < Config.BOARD_WIDTH);
			if (outOfRow && outOfCol) {
				return Collision.BOTH;
			} else if (outOfRow) {
				return Collision.Y;
			} else if (outOfCol) {
				return Collision.X;
			}
		}
		return Collision.CLEAR;
	}
	private Collision collisionCheck() {
		Vector[] bounds = ball.getBounds();
		int row;
		int col;
		for(Vector v : bounds) {
			row = (int)v.y;
			col = (int)v.x;
			int block = blocks[row][col];
			if(block == 0) {
				continue;
			}
			blocks[row][col]--;
			Collision colType = getCollisionType(new Vector(col, row), ball.position);
			System.out.println("Col type: "+colType+" @ "+v);
			//ball.velocity = ball.velocity.add(Config.FASTER_VEC);
			return colType;
		}
		return Collision.CLEAR;
	}
	private Collision getCollisionType(Vector boxCorner, Vector check) {
		//diagonals in the form y-y1 = m(x-x1) -> y = m(x-x1)+y1
		Vector lowerBoxCorner = new Vector(boxCorner.x, boxCorner.y+1);
		boolean aboveLeftDiag = check.y < 1 * (check.x - boxCorner.x) + boxCorner.y;
		boolean aboveRightDiag = check.y < -1 * (check.x - lowerBoxCorner.x) + lowerBoxCorner.y;
		boolean belowLeftDiag = check.y > 1 * (check.x - boxCorner.x) + boxCorner.y;
		boolean belowRightDiag = check.y > -1 * (check.x - lowerBoxCorner.x) + lowerBoxCorner.y;
		if(aboveLeftDiag && aboveRightDiag) {
			return Collision.Y;
		}
		else if(belowLeftDiag && belowRightDiag) {
			return Collision.Y;
		}
		else if(belowLeftDiag && aboveRightDiag){
			return Collision.X;
		}
		else if(aboveLeftDiag && belowRightDiag){
			return Collision.X;
		}
		else {
			return Collision.BOTH;
		}
	}
	enum Collision {
		CLEAR, X, Y, BOTH
	}
}

