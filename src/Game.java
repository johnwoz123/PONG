import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static PlayerPaddle player;
	public static AIPaddle ai;
	public static Ball ball;

	InputHandler IH;

	JFrame frame; // Window of the game
	public final int WIDTH = 400; // Width of the entire window
	public final int HEIGHT = WIDTH / 40 * 22; // Height of the entire window
	public final Dimension gameSize = new Dimension(WIDTH, HEIGHT); // Condense
																	// WIDTH &
																	// HEIGHT
																	// into one
																	// variable
	public final String TITLE = "PONG!";

	BufferedImage image = new BufferedImage(WIDTH, HEIGHT,
			BufferedImage.TYPE_INT_RGB); // creating a new image to put stuff on
											// the screen using RGB

	static boolean gameRunning = false; // whether the game is running
	int p1Score, p2Score;

	Thread thread;

	public void run() {
		// This is implied that gameRunning = true
		while (gameRunning) {
			tick();
			render();

			try {
				Thread.sleep(7);
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}

	public synchronized void start() // for applets
	{
		gameRunning = true;
		thread = new Thread(this);
		thread.start();
	}

	public synchronized void stop() {
		gameRunning = false;
		System.exit(0);
	} // Find the stop method

	public Game() {
		frame = new JFrame();

		setMinimumSize(gameSize);
		setPreferredSize(gameSize);
		setMaximumSize(gameSize);

		// this refers to the canvas class

		frame.add(this, BorderLayout.CENTER); // in the frame container add the
												// game class and canvas
		frame.pack(); // pack everything in JFrame

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setTitle(TITLE);
		frame.setLocationRelativeTo(null);
		player = new PlayerPaddle(10, 60);
		ai = new AIPaddle(getWidth() - 20, 60);
		IH = new InputHandler(this);
		ball = new Ball(getWidth() / 2, getHeight() / 2);

	}

	public void tick() {
		player.tick(this);
		ai.tick(this);
		ball.tick(this);

	}

	public void render() {
		BufferStrategy bs = getBufferStrategy(); // How the game buffers
		{
			if (bs == null) // as in there is not a buffer. and buffer three
							// times reduce tearing
			{
				createBufferStrategy(3);
				return; // go back to the top and go from there
			}

			Graphics g = bs.getDrawGraphics(); // g is a reference variable to
												// draw stuff to the screen

			g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
			g.setColor(Color.BLACK);
			g.fillRect(0, 0, getWidth(), getHeight());

			player.render(g);
			ai.render(g);
			ball.render(g);

			g.dispose();
			bs.show();

		}
	}

	public static void main(String[] args) {
		Game game = new Game(); // this creates a new Game constructor and
								// constructs the class. It references the
								// enitire class
		game.start();
	}

}
