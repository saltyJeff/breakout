package breakout;

public interface BreakoutCallbacks {
	public void ready ();
	public void onUpdate(int[][] newBoard, Vector ballPos, Vector ballVel, double paddlePos);
	public void lose();
	public void win();
}
