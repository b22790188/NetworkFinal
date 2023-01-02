package character;

import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;
import javax.swing.ImageIcon;
import map.Map;

public class StickMan implements Runnable {
	private int id; //added by guo
	
	public static final int WIDTH = 32;
	public static final int HEIGHT = 32;
	public static final int initialX = 300;
	public static final int initialY = 100;
	public static final int regularSpeedX = 3;
	public static final int regularSpeedY = 1;
	private int x;
	private int y;
	private int speedX;
	private int speedY;
	private GameFrame gf;
	private Image stickMan = (new ImageIcon("磚頭.png")).getImage();
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private boolean jumping = false;
	private Map map = new Map();

	public StickMan(GameFrame gf) throws IOException {
		this.gf = gf;
		this.setStickManX(initialX);
		this.setStickManY(initialY);
		this.setSpeedX(regularSpeedX);
		this.setSpeedY(regularSpeedY);
		this.gravity();
		(new Thread(this)).start();
	}

	public Image getStickMan() {
		return this.stickMan;
	}

	public void setStickManX(int xx) {
		this.x = xx;
	}
	public void setStickManY(int yy) {
		this.y = yy;
	}
	public void setSpeedX(int sx) {
		this.speedX = sx;
	}
	public void setSpeedY(int sy) {
		this.speedY = sy;
	}
	public int getStickManX() {
		return this.x;
	}
	public int getStickManY() {
		return this.y;
	}
	public int getSpeedX() {
		return this.speedX;
	}
	public int getSpeedY() {
		return this.speedY;
	}

	public void moveLeft() {
		this.left = true;
	}
	public void moveRight() {
		this.right = true;
	}
	public void moveUp() {
		this.up = true;
	}
	public void stopMoveLeft() {
		this.left = false;
	}
	public void stopMoveRight() {
		this.right = false;
	}
	public void stopMoveUp() {
		this.up = false;
	}

	public void run() {
		while(true) {
			if (this.left) {
				if (this.hit("left")) {
					this.speedX = 0;
				}

				if (this.x >= 0) {
					this.x -= this.speedX;
				}

				this.speedX = 3;
			}

			if (this.right) {
				if (this.hit("right")) {
					this.speedX = 0;
				}

				if (this.x + 32 <= 1024) {
					this.x += this.speedX;
				}

				this.speedX = 3;
			}

			if (this.up && this.hit("down") && !this.jumping) {
				(new Thread() {
					public void run() {
						StickMan.this.jump();
					}
				}).start();
			}

			try {
				Thread.sleep(15L);
			} catch (InterruptedException var2) {
				var2.printStackTrace();
			}
		}
	}

	public void jump() {
		this.jumping = true;

		for(int i = 0; i < 32; ++i) {
			if (this.hit("up")) {
				this.speedY = 0;
			}

			this.y -= this.speedY;

			try {
				Thread.sleep(7L);
			} catch (InterruptedException var3) {
				var3.printStackTrace();
			}

			this.speedY = 1;
		}

		this.jumping = false;
	}

	public boolean hit(String direction) {
		Rectangle stickManRec = new Rectangle(this.x, this.y, 32, 32);

		for(int i = 0; i < this.map.getTileNum(); ++i) {
			Rectangle object = null;
			int objectX = (Integer)this.map.mapList.map1_barrier[0][i].get(0);
			int objectY = (Integer)this.map.mapList.map1_barrier[0][i].get(1);
			if (direction.equals("left")) {
				object = new Rectangle(objectX + 3, objectY, 32, 32);
			} else if (direction.equals("right")) {
				object = new Rectangle(objectX - 3, objectY, 32, 32);
			} else if (direction.equals("up")) {
				object = new Rectangle(objectX, objectY + 2, 32, 32);
			} else if (direction.equals("down")) {
				object = new Rectangle(objectX, objectY - 1, 32, 32);
			}

			if (stickManRec.intersects(object)) {
//				System.out.println(objectX + " " + objectY);
//				System.out.println(this.x + " " + this.y);
				return true;
			}
		}

		return false;
	}

	public void gravity() {
		(new Thread() {
			public void run() {
				while(true) {
					if (!StickMan.this.jumping && !StickMan.this.hit("down")) {
						StickMan var10000 = StickMan.this;
						var10000.y += StickMan.this.speedY;
					}

					try {
						Thread.sleep(7L);
					} catch (InterruptedException var2) {
						var2.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	/*
	 * code below added by guo
	 */
	public void setID(int id) {
		this.id = id;
	}
	public int getID() {
		return this.id;
	}
}