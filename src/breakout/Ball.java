package breakout;
/**
 * A breakout game ball
 */
public class Ball {
	public Vector position;
	public Vector velocity;
	public Ball() {
		position = Config.INITIAL_POS;
		velocity = Config.INITIAL_VEL;
	}

	public Vector[] getBounds() {
		Vector[] arr = new Vector[4];
		arr[0] = new Vector(position);
		/*arr[1] = new Vector(position);
		arr[2] = new Vector(position);
		arr[3] = new Vector(position);*/
		
		arr[1] = position.add(Vector.RIGHT.multiply(Config.BALL_RADIUS));
		arr[2] = position.add(Vector.DOWN.multiply(Config.BALL_RADIUS));
		arr[3] = position.add(new Vector(1,1).multiply(Config.BALL_RADIUS));
		return arr;
	}

	public void goToNext() {
		position = position.add(velocity.multiply(Config.PHYSICS_DELTA));
	}

	@Override
	public String toString() {
		return "Ball: " + position.toString() + " going: "
				+ velocity.toString();
	}

	public void invertAndShiftX() {
		velocity.x = velocity.x * -1.0;
		position = position.add(velocity.multiply(Config.PHYSICS_DELTA));
	}

	public void invertAndShiftY() {
		velocity.y = velocity.y * -1.0;
		position = position.add(velocity.multiply(Config.PHYSICS_DELTA));
	}
}
