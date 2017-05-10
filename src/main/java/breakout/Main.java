package breakout;
import java.awt.event.*;

import javax.swing.*;
public class Main {
	static Thread thread;
	private static Timer timer = new Timer(1000/30, new ActionListener () { //30 ticks a second
		public void actionPerformed(ActionEvent e) {
			graphicUpdate();
		}
	});
	private static BreakoutPanel breakoutPanel;
	public static void main(String[] args) {
		//init swing stuff
		JFrame frmBreakout = new JFrame();    
		frmBreakout.setTitle("Breakout");
		frmBreakout.setSize(400,600);          
		frmBreakout.getContentPane().setLayout(null);//using no layout managers  
		
		breakoutPanel = new BreakoutPanel();
		breakoutPanel.setBounds(0, 0, 384, 384);
		frmBreakout.getContentPane().add(breakoutPanel);
		txtWhatACool.setText("What a cool text component");
		txtWhatACool.setBounds(0, 384, 384, 68);
		frmBreakout.getContentPane().add(txtWhatACool);
		txtWhatACool.setColumns(10);
		frmBreakout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//dieee
		frmBreakout.setVisible(true);//making the frame visible  
		
		timer.start();
		
		//init game stuff
		BreakoutGame bg = new BreakoutGame(callbacks);
		thread = new Thread(bg);
		thread.start();
		
	}
	private static void graphicUpdate () {
		breakoutPanel.repaint();
	}
	private static BreakoutCallbacks callbacks = new BreakoutCallbacks() {
		public void ready () {
			//System.out.println("We have signs of life");
		}
		public void onUpdate (int[][] newBoard, Ball newBall) {
			//System.out.println("Heartbeat");
		}
	};
	private static final JTextField txtWhatACool = new JTextField();
}
