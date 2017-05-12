package breakout;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.*;

import javax.swing.*;


public class Main {
	private static Thread thread;
	private static Timer timer = new Timer(Config.RENDER_TICK,
		new ActionListener() { // 30 ticks a second
			public void actionPerformed(ActionEvent e) {
				graphicUpdate();
			}
	});
	private static BreakoutGame bg;
	private static BallPanel ballPanel;
	private static BlockPanel blockPanel;
	public static void main(String[] args) {
		// init swing stuff
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		Container p = frame.getContentPane();
		p.setPreferredSize(Config.DIMENSIONS);
		p.setLayout(null);
		p.setBackground(new Color(49,237,237));
		
		ballPanel = new BallPanel();
		blockPanel = new BlockPanel();
		ballPanel.setBounds(new Rectangle(Config.DIMENSIONS));
		blockPanel.setBounds(new Rectangle(Config.DIMENSIONS));
		p.add(ballPanel);
		p.add(blockPanel);
		
		frame.pack();
		frame.setVisible(true);
		timer.start();

		// init game stuff
		bg = new BreakoutGame(callbacks);
		thread = new Thread(bg);
		thread.start();

	}

	private static void graphicUpdate() {
		ballPanel.repaint();
		blockPanel.repaint();
	}

	private static BreakoutCallbacks callbacks = new BreakoutCallbacks() {
		public void ready() {
			// System.out.println("We have signs of life");
		}

		public void onUpdate(int[][] newBoard, Vector ballPos, Vector ballVel) {
			ballPanel.updateInfo(ballPos, ballVel);
		}
	};
}
