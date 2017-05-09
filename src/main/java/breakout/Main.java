package breakout;

public class Main {
	static Thread thread;
	public static void main(String[] args) {
		BreakoutGame bg = new BreakoutGame(callbacks);
		thread = new Thread(bg);
		thread.start();
	}
	private static BreakoutCallbacks callbacks = new BreakoutCallbacks() {
		public void ready () {
			System.out.println("We have signs of life");
		}
		public void onUpdate (int[][] newBoard, Ball newBall) {
			System.out.println("Heartbeat");
		}
	};
}
