package breakout;

import java.util.ArrayList;
import java.util.List;

public class BreakoutGame implements Runnable {

	private BreakoutCallbacks callback;
	private long lastTick = System.currentTimeMillis();
	private static int[][] blocks;
	private static Ball ball;
	private int dir = 0;
	private double paddlePos = Config.BOARD_WIDTH / 2 - Config.PADDLE_WIDTH;
	private int status = 0; //0 = normal, -1 = loss, 1 = win
	public void setDir(int newDir) {
		dir = newDir;
	}
	public BreakoutGame(BreakoutCallbacks bc) {
		callback = bc;
		ball = new Ball();
	}
	public void run() {
		getBlocks();
		for(int r = 2; r < Config.MAX_BLOCK + 2; r++) {
			for(int c = 0; c < Config.BOARD_WIDTH; c++) {
				blocks[r][c] = Config.MAX_BLOCK - r + 2;
			}
		}
		//blocks[2][11] = 10;
		callback.ready();
		while (status == 0) { // edgy
			if (System.currentTimeMillis() < lastTick + Config.PHYSICS_TICK) {
				continue; // not time for an update
			}
			simulate();
			//System.out.println(String.format("Delta time: %.05f", (System.currentTimeMillis() - lastTick)/1000.0));
			callback.onUpdate(blocks, new Vector(ball.position), new Vector(ball.velocity), paddlePos);
			lastTick = System.currentTimeMillis();
		}
		if(status == -1) {
			callback.lose();
		}
		else {
			callback.win();
		}
	}
	public static int[][] getBlocks() {
		if(blocks == null) {
			blocks = new int[Config.BOARD_HEIGHT][Config.BOARD_WIDTH];
		}
		return blocks;
	}
	private void simulate() {
		paddlePos+= dir * Config.PHYSICS_DELTA * Config.PADDLE_SPEED;
		if(paddlePos < 0){
			paddlePos = 0;
		}
		else if(paddlePos > Config.BOARD_WIDTH- Config.PADDLE_WIDTH){
			paddlePos = Config.BOARD_WIDTH- Config.PADDLE_WIDTH;
		}
		//collides stores the collisions
		Collision[] collides = borderCheck();
		if(inArray(collides, Collision.OUT)) {
			handleLoss();
		}
		if(inArray(collides, Collision.BOTH) || (inArray(collides, Collision.X) && inArray(collides, Collision.Y))) {
			//System.out.println("Border has either both or a x and a y");
			handleCollision(Collision.BOTH);
		}
		else if(inArray(collides, Collision.X)) {
			//System.out.println("Border fosho has a x");
			Collision[] col2s = collisionCheck();
			if(inArray(col2s, Collision.Y) || inArray(col2s, Collision.BOTH)) {
				//System.out.println("Mix n Match");
				handleCollision(Collision.BOTH);
			}
			else {
				handleCollision(Collision.X);
			}
		}
		else if(inArray(collides, Collision.Y)) {
			//System.out.println("Border fosho has a y");
			Collision[] col2s = collisionCheck();
			if(inArray(col2s, Collision.X) || inArray(col2s, Collision.BOTH)) {
				//System.out.println("Mix n Match");
				handleCollision(Collision.BOTH);
			}
			else {
				handleCollision(Collision.Y);
			}
		}
		else {
			Collision[] col2s = collisionCheck();
			if(inArray(col2s, Collision.BOTH) || (inArray(col2s, Collision.X) && inArray(col2s, Collision.Y))) {
				System.out.println("Double collision or has both an x and y");
				System.out.println(printCollisions(col2s));
				handleCollision(Collision.BOTH);
			}
			else if(inArray(col2s, Collision.X)) {
				handleCollision(Collision.X);
			}
			else if(inArray(col2s, Collision.Y)) {			
				handleCollision(Collision.Y);
			}
		}
		ball.goToNext();
	}
	private void handleLoss() {
		status = -1;
	}
	private String printCollisions(Collision [] cols) {
		StringBuilder sb = new StringBuilder();
		for(Collision c : cols) {
			if(c != null) {
				sb.append(c.name());
			}
			else {
				sb.append('?');
			}
			sb.append('\n');
		}
		return sb.toString();
	}
	private boolean inArray(Collision[] cols, Collision c) {
		for(Collision col : cols) {
			if(c == col) {
				return true;
			}
		}
		return false;
	}
	private void handleCollision(Collision type) {
		switch (type) {
			case X:
				ball.invertAndShiftX();
				ball.goToNext();
				break;
			case Y:
				ball.invertAndShiftY();
				ball.goToNext();
				break;
			case BOTH:
				ball.invertAndShiftX();
				ball.invertAndShiftY();
				ball.goToNext();
				break;
			default:
				break;
		}
	}
	private Collision[] borderCheck() {
		Vector[] bounds = ball.getBounds();
		Collision[] r = new Collision[bounds.length];
		for (int i = 0; i < bounds.length; i++) {
			Vector v = bounds[i];
			boolean inPaddleSpan = paddlePos <= v.x && v.x <= paddlePos+Config.PADDLE_WIDTH; 
			boolean onTheTop = v.y < 0; //floor negative number = rounding up??
			boolean outOfCol = v.x < 0 || !(0 <= v.x && v.x < Config.BOARD_WIDTH);
			if(v.y > Config.BOARD_HEIGHT && !inPaddleSpan) {
				r[i] = Collision.OUT;
				return r;
			}
			else if ((onTheTop || (v.y > Config.BOARD_HEIGHT && inPaddleSpan)) && outOfCol) {
				r[i] = Collision.BOTH;
			}
			else if (outOfCol) {
				r[i] = Collision.X;
			}
			else if ((v.y > Config.BOARD_HEIGHT && inPaddleSpan)) {
				r[i] = Collision.Y;
			}
			else {
				r[i] = null;
			}
		}
		return r;
	}
	private Collision[] collisionCheck() {
		Vector[] bounds = ball.getBounds();
		Collision[] r = new Collision[bounds.length];
		int row;
		int col;
		boolean hasTicked = false;
		for(int i = 0; i < bounds.length; i++) {
			Vector v = bounds[i];
			row = (int)v.y;
			col = (int)v.x;
			if(row < 0 || row >= Config.BOARD_HEIGHT || col < 0 || col >= Config.BOARD_WIDTH) {
				r[i] = null;
				continue;
			}
			int block = blocks[row][col];
			if(block <= 0) {
				r[i] = null;
				continue;
			}
			if(!hasTicked) {
				blocks[row][col]--;
				hasTicked = true;
			}
			Collision colType = getCollisionType(new Vector(col, row), ball.position);
			r[i] = colType;
		}
		return r;
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
			return null;
		}
	}
	enum Collision {
		CLEAR, X, Y, BOTH, OUT
	}
}

