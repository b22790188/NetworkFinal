package character;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	private GameFrame gf;

	public KeyListener(GameFrame gf) {
		this.gf = gf;
	}

	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code) {
			case 37:
				this.gf.getStickMan().moveLeft();
				break;
			case 38:
				this.gf.getStickMan().moveUp();
				break;
			case 39:
				this.gf.getStickMan().moveRight();
		}

	}

	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		switch(code) {
			case 37:
				this.gf.getStickMan().stopMoveLeft();
				break;
			case 38:
				this.gf.getStickMan().stopMoveUp();
				break;
			case 39:
				this.gf.getStickMan().stopMoveRight();
		}

	}
}
