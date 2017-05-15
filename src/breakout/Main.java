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
	private static JFrame frame;
	private static BreakoutGame bg;
	private static BallPanel ballPanel;
	private static BlockPanel blockPanel;

	public static void main(String[] args) {
		// init swing stuff
		frame = new JFrame();
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		frame.setTitle("Breakout");
		frame.setResizable(false);
		Container p = frame.getContentPane();
		p.setPreferredSize(Config.DIMENSIONS);
		p.setLayout(null);
		p.setBackground(Color.BLACK);

		ballPanel = new BallPanel();
		blockPanel = new BlockPanel();
                Rectangle bpdims = new Rectangle(0,0,Config.DIMENSIONS.width,Config.DIMENSIONS.height + (int)(0.25*Config.JPC) );
		ballPanel.setBounds(bpdims);
		blockPanel.setBounds(new Rectangle(Config.DIMENSIONS));
		p.add(ballPanel);
		p.add(blockPanel);

		frame.pack();
		frame.setVisible(true);

		// init game stuff
		bg = new BreakoutGame(callbacks);
		thread = new Thread(bg);
		thread.start();
		timer.start();
	}

	private static void graphicUpdate() {
		ballPanel.repaint();
	}

	private static BreakoutCallbacks callbacks = new BreakoutCallbacks() {
		public void ready() {
			// System.out.println("We have signs of life");
		}

		public void onUpdate(int[][] newBoard, Vector ballPos, Vector ballVel) {
			ballPanel.updateInfo(ballPos, ballVel);
			blockPanel.repaint();
		}
	};
        
         public static void panelMover(){
            class ArrowListener implements ActionListener{
                public void actionPerformed(ActionEvent e) {
                     if(ballPanel.addKeyListener(new KeyListener(){
                         @override
                     }));
                }
)
            }
        }
}
