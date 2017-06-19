import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;
/**
 * 
 * @author Jodi Henderson
 *
 */
@SuppressWarnings("serial")
public class GameScreen extends JPanel implements Runnable, KeyListener {


	public static final int WIDTH = 400;
	public static final int HEIGHT = 400;
	//Render
	private Graphics2D g2d;
	private BufferedImage image;
	//Game loop
	private Thread thread;
	private boolean running;
	private long targetTime;
	//For Game
	private int SIZE = 10;
	Entity head;
	ArrayList<Entity> snake;
	
	public GameScreen(){
		setPreferredSize(new Dimension(WIDTH,HEIGHT));
		setFocusable(true);
		requestFocus();
		addKeyListener(this);
		
	}
	
	@Override
	public void addNotify(){
		super.addNotify();
		thread = new Thread(this);
		thread.start();
	}
	private void setFPS(int fps){
		targetTime = 1000/fps;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		if(running) 
			return;
		init();
		long startTime;
		long elapsed;
		long wait;
		while(running){
			startTime = System.nanoTime();
			
			update();
			requestRender();
			
			elapsed = System.nanoTime() - startTime;
			wait = targetTime - elapsed - 1000000;
			if(wait >0){
				try{
					Thread.sleep(wait);
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		}
	}
	private void init() {
		image = new BufferedImage(WIDTH,HEIGHT,BufferedImage.TYPE_INT_ARGB);
		g2d = image.createGraphics();
		running = true;
		setUp();
		setFPS(10);
	}
	
	private void setUp(){
		snake = new ArrayList<Entity>();
		head = new Entity(SIZE);
		head.setPosition(WIDTH/2, HEIGHT/2);
		snake.add(head);
		for (int i = 1; i < 10; i++){
			Entity e = new Entity(SIZE);
			e.setPosition(head.getX()+(i*SIZE), head.getY());
			snake.add(e);
			
		}
	}
	
	private void requestRender() {
		render(g2d);
		Graphics g = getGraphics();
		g.drawImage(image, 0, 0,null);
		g.dispose();
		
	}

	private void update() {
		// TODO Auto-generated method stub
		
	}
	public void render(Graphics2D g2d){
		g2d.clearRect(0, 0, WIDTH, HEIGHT);
		g2d.setColor(Color.GREEN);
		
		// for each e in snake
		for(Entity e : snake){
			e.render(g2d);
		}
	}
	


	
	
	
	
}
