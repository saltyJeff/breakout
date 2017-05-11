package breakout;

public class Ball {
	public Vector position;
	public Vector velocity;

	public Ball() {
		position = new Vector(Config.BOARD_WIDTH / 2, 2);
		velocity = Vector.DOWN;
	}

	public Vector[] getBounds() {
		Vector[] arr = new Vector[4];
		arr[0] = position.add(Vector.TOPLEFT.multiply(Config.BALL_RADIUS));
		arr[1] = position.add(Vector.TOPRIGHT.multiply(Config.BALL_RADIUS));
		arr[2] = position.add(Vector.BOTLEFT.multiply(Config.BALL_RADIUS));
		arr[3] = position.add(Vector.BOTRIGHT.multiply(Config.BALL_RADIUS));
		return arr;
	}

	public void goToNext() {
		position = position.add(velocity.multiply(Config.PHYSICS_TICK/1000.0));
	}

	@Override
	public String toString() {
		return "Ball: " + position.toString() + " going: "
				+ velocity.toString();
	}

	public void invertAndShiftX() {
		velocity.x = velocity.x * -1;
		position = position.add(Vector.RIGHT.multiply(Config.BALL_RADIUS+0.0001)); //float randomness is too weird
	}

	public void invertAndShiftY() {
		velocity.y = velocity.y * -1;
		position = position.add(Vector.UP.multiply(Config.BALL_RADIUS+0.0001));
	}

	public void invertAndShiftBoth() {
		velocity = velocity.multiply(-1);
		//position = position.add(Vector.UP.multiply(RADIUS));
	}
}
