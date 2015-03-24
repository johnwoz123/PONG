import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class InputHandler implements KeyListener {

	public InputHandler(Game game) {
		game.addKeyListener(this);
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode(); // whenever we press a key it changes the
										// key to an int

		if (keyCode == KeyEvent.VK_W) {
			Game.player.goingUp = true;
		}
		if (keyCode == KeyEvent.VK_S) {
			Game.player.goingdown = true;
		}

	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode(); // whenever we press a key it changes the
										// key to an int

		if (keyCode == KeyEvent.VK_W) {
			Game.player.goingUp = false;
		}
		if (keyCode == KeyEvent.VK_S) {
			Game.player.goingdown = false;
		}

	}

}
