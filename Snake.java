import java.awt.Dimension;

import javax.swing.JFrame;

public class Snake {

	
	public static void main(String[] args){
		JFrame frame = new JFrame("Snake");
		frame.setContentPane(new GameScreen());
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(true);
		frame.setPreferredSize(new Dimension(GameScreen.WIDTH, GameScreen.HEIGHT));
		frame.setVisible(true);
	}
}
