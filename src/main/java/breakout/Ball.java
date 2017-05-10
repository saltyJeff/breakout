package breakout;

public class Ball {
	public Vector position;
	public Vector velocity;
	public static final double RADIUS = 0.1;
	public Ball () {
		position = new Vector(BreakoutGame.BOARD_WIDTH/2, 2);
		velocity = Vector.DOWN;
	}
	public Vector[] getBounds () {
		Vector[] arr = new Vector[4];
		arr[0] = position.add(Vector.TOPLEFT.multiply(BreakoutGame.TICK_DELTA));
		arr[0] = position.add(Vector.TOPRIGHT.multiply(BreakoutGame.TICK_DELTA));
		arr[0] = position.add(Vector.BOTLEFT.multiply(BreakoutGame.TICK_DELTA));
		arr[0] = position.add(Vector.BOTRIGHT.multiply(BreakoutGame.TICK_DELTA));
		return arr;
	}
	public void goToNext () {
		position = position.add(velocity.multiply(BreakoutGame.TICK_DELTA));
	}
	@Override
	public String toString() {
		return "The ball is at: "+position.toString();
	}
}
