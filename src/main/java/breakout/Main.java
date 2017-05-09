package breakout;
import javax.swing.*;
public class Main {
	static Thread thread;
	public static void main(String[] args) {
		//init swing stuff
		JFrame frmBreakout = new JFrame();    
		frmBreakout.setTitle("Breakout");
		frmBreakout.getContentPane().add(new BreakoutPanel());
		frmBreakout.setSize(400,600);          
		frmBreakout.getContentPane().setLayout(null);//using no layout managers  
		
		BreakoutPanel breakoutPanel = new BreakoutPanel();
		breakoutPanel.setBounds(0, 0, 384, 384);
		frmBreakout.getContentPane().add(breakoutPanel);
		frmBreakout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//dieee
		frmBreakout.setVisible(true);//making the frame visible  

		//init game stuff
		BreakoutGame bg = new BreakoutGame(callbacks);
		thread = new Thread(bg);
		thread.start();
	}
	private static BreakoutCallbacks callbacks = new BreakoutCallbacks() {
		public void ready () {
			//System.out.println("We have signs of life");
		}
		public void onUpdate (int[][] newBoard, Ball newBall) {
			//System.out.println("Heartbeat");
		}
	};
}
