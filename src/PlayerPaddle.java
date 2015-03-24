import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class PlayerPaddle {
	int x;
	int y;
	int width = 15;
	int height = 40;
	int speed = 2;

	Rectangle boundingBox;

	boolean goingUp = false;
	boolean goingdown = false;

	public PlayerPaddle(int x, int y) {
		this.x = x;
		this.y = y;

		boundingBox = new Rectangle(x, y, width, height);
		boundingBox.setBounds(x, y, width, height); // Creates the rectangle
													// when he
		// spawns and creates the collision

	}

	public void tick(Game game) // this tick takes an instance of the class Game
								// game
	{
		boundingBox.setBounds(x, y, width, height);
		if (goingUp && y > 0) {
			y--;
		}
		if (goingdown && y < game.getHeight() - height) {
			y++;
		}

		System.out.println();
	}

	public void render(Graphics g) {
		g.setColor(Color.GREEN);
		g.fillRect(x, y, width, height);
	}

}
