package breakout;

public class Ball {
	private double xPosition;
	private double yPosition;
	private double xVelocity;
	private double yVelocity;
	private static final double WIDTH = 0.25;
	public Ball () {
		xPosition = 10;
		yPosition = 10;
		xVelocity = 0;
		yVelocity = -1;
	}

	public double getxPosition() {
		return xPosition;
	}
	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}
	public double getyPosition() {
		return yPosition;
	}
	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}
	public double getxVelocity() {
		return xVelocity;
	}
	public void setxVelocity(double xVelocity) {
		this.xVelocity = xVelocity;
	}
	public double getyVelocity() {
		return yVelocity;
	}
	public void setyVelocity(double yVelocity) {
		this.yVelocity = yVelocity;
	}
	
}
