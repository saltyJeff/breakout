package breakout;

public class Ball {
	public Vector position;
	public Vector velocity;
	public static final double RADIUS = 0.1;

	public Ball() {
		position = new Vector(BreakoutGame.BOARD_WIDTH / 2, 2);
		velocity = Vector.DOWN;
	}

	public Vector[] getBounds() {
		Vector[] arr = new Vector[4];
		arr[0] = position.add(Vector.TOPLEFT.multiply(RADIUS));
		arr[1] = position.add(Vector.TOPRIGHT.multiply(RADIUS));
		arr[2] = position.add(Vector.BOTLEFT.multiply(RADIUS));
		arr[3] = position.add(Vector.BOTRIGHT.multiply(RADIUS));
		return arr;
	}

	public void goToNext() {
		position = position.add(velocity.multiply(BreakoutGame.TICK_DELTA));
	}

	@Override
	public String toString() {
		return "Ball: " + position.toString() + " going: "
				+ velocity.toString();
	}

	public void invertAndShiftX() {
		velocity.x = velocity.x * -1;
		position = position.add(Vector.RIGHT.multiply(RADIUS));
	}

	public void invertAndShiftY() {
		velocity.y = velocity.y * -1;
		position = position.add(Vector.UP.multiply(RADIUS));
	}

	public void invertAndShiftBoth() {
		velocity = velocity.multiply(-1);
		//position = position.add(Vector.UP.multiply(RADIUS));
	}
}
