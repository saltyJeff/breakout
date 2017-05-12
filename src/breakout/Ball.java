package breakout;

public class Ball {
	public Vector position;
	public Vector velocity;
	public boolean recentlyHit = false;
	public Ball() {
		position = Config.INITIAL_POS;
		velocity = Config.INITIAL_VEL;
	}

	public Vector[] getBounds() {
		Vector[] arr = new Vector[4];
		arr[0] = position.add(Vector.UP.multiply(Config.BALL_RADIUS));
		arr[1] = position.add(Vector.DOWN.multiply(Config.BALL_RADIUS));
		arr[2] = position.add(Vector.LEFT.multiply(Config.BALL_RADIUS));
		arr[3] = position.add(Vector.RIGHT.multiply(Config.BALL_RADIUS));
		return arr;
	}

	public void goToNext() {
		position = position.add(velocity.multiply(Config.PHYSICS_DELTA));
		if(recentlyHit) {
			recentlyHit = false;
		}
	}

	@Override
	public String toString() {
		return "Ball: " + position.toString() + " going: "
				+ velocity.toString();
	}

	public void invertAndShiftX() {
		velocity.x = velocity.x * -1.0;
		position = position.add(Vector.LEFT.multiply(velocity.x * (Config.BALL_RADIUS)));
		recentlyHit = true;
	}

	public void invertAndShiftY() {
		velocity.y = velocity.y * -1.0;
		position = position.add(Vector.UP.multiply(velocity.y * (Config.BALL_RADIUS)));
		recentlyHit = true;
	}

	public void invertAndShiftBoth() {
		velocity = velocity.multiply(-1);
		position = position.add(Vector.TOPRIGHT.multiply(Config.BALL_RADIUS));
		recentlyHit = true;
	}
}
