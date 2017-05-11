package breakout;

public interface BreakoutCallbacks {
	public void ready ();
	public void onUpdate(int[][] newBoard, Ball newBall);
}
