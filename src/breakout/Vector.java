package breakout;

public class Vector {
	public double x;
	public double y;

	public Vector(double ex, double why) {
		x = ex;
		y = why;
	}

	public Vector(Vector v) {
		x = v.x;
		y = v.y;
	}

	public Vector multiply(double d) {
		return new Vector(x * d, y * d);
	}

	public Vector add(Vector v) {
		return new Vector(x + v.x, y + v.y);
	}

	public double magnitude() {
		return Math.sqrt(x * x + y * y);
	}

	@Override
	public String toString() {
		return String.format("(%.02f, %.02f)", x, y);
	}

	//for your convinience
	public static Vector DOWN = new Vector(0, 1);
	public static Vector UP = new Vector(0, -1);
	public static Vector LEFT = new Vector(1, 0);
	public static Vector RIGHT = new Vector(-1, 0);
	public static Vector ZERO = new Vector(0,0);
}
