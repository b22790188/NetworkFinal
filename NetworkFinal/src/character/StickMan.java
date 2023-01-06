package character;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.List;

import javax.swing.ImageIcon;

import Msg.*;
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
	private Image stickMan = (new ImageIcon("girl_left.png")).getImage();
	private boolean left = false;
	private boolean right = false;
	private boolean up = false;
	private boolean down = false;
	private boolean jumping = false;
	private Map map;
	private List<StickMan> StickManSet;
	
	//added by guo
	private boolean live = true;
	private int blood = 5;
	private boolean die = false;
	
	/*
	 * 0的時候代表火柴人方向為左邊，1的時候代表火柴人方向為右邊。
	 */
	private int dir = 0;
	

	/*
	 * 建構子新增參數 by guo
	 */
	public StickMan(GameFrame gf,int initialX,int initialY) throws IOException {
		this.gf = gf;
		StickManSet = this.gf.getStickManSet();
		this.map = gf.map;
		this.setStickManX(initialX);
		this.setStickManY(initialY);
		this.setSpeedX(regularSpeedX);
		this.setSpeedY(regularSpeedY);
		this.gravity();
		(new Thread(this)).start();
	}
	/*
	 * 
	public setMap(int r1, int r2) {
		gf.setMap(r1, r2);
	}
	 */

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
	public boolean getStickManDie() {
		return this.die;
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
					dir = 0;
					this.x -= this.speedX;
					
					stickMan = (new ImageIcon("girl_left.png")).getImage();
					
				}
				
				/*
				 * below added by guo
				 */
				Stickman_Move_Msg msg = new Stickman_Move_Msg(this.id,this.x,this.y,dir);
				gf.getNetClient().send(msg);

				this.speedX = 3;
			}

			if (this.right) {
				
				
				if (this.hit("right")) {
					this.speedX = 0;

				}

				if (this.x + 32 <= 1024) {
					dir = 1;
					this.x += this.speedX;
					stickMan = (new ImageIcon("girl_right.png")).getImage();
				}
				/*
				 * below added by guo
				 */
				Stickman_Move_Msg msg = new Stickman_Move_Msg(id,this.x,this.y,dir);
				gf.getNetClient().send(msg);

				this.speedX = 3;
			}

			if (this.up && this.hit("down") && !this.jumping) {
				(new Thread() {
					public void run() {
						StickMan.this.jump();
						
					}
				}).start();
			}
			
			
			//扣血
			if(this.hitBullet()){
			    blood--;
			}
			
			if(blood == 0){
//				(new Thread(this)).interrupt();
				die = true;
				int deathNum = gf.getDeathNum();
				gf.setDeathNum(++deathNum);
				
				break;
			}

			try {
				Thread.sleep(15);
			} catch (InterruptedException var2) {
				var2.printStackTrace();
			}
		}
	}

	public void jump() {
		this.jumping = true;

		for(int i = 0; i < 96; ++i) {
			if (this.hit("up")) {
				break;
			}

			this.y -= this.speedY;
			
			//guo
			
			Stickman_Move_Msg msg = new Stickman_Move_Msg(id,x,y,dir);
			gf.getNetClient().send(msg);

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
		Rectangle stickManRec = new Rectangle(this.x, this.y, WIDTH, HEIGHT);

		for(int i = 0; i < gf.map.getTileNum(); ++i) {
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
		
		for(int i = 0; i < StickManSet.size(); i++) {
			Rectangle object = null;
			if(StickManSet.get(i).getID() != gf.getClientID()) {
				int objectX = StickManSet.get(i).getStickManX();
				int objectY = StickManSet.get(i).getStickManY();
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
//					System.out.println(objectX + " " + objectY);
//					System.out.println(this.x + " " + this.y);
					return true;
				}
			}
			
		}

		return false;
	}
	
	public void gravity() {
		(new Thread() {
			public void run() {
				while(true) {
					if (!jumping && !hit("down")) {
						y += StickMan.this.speedY;
						Stickman_Move_Msg msg = new Stickman_Move_Msg(id,x,y,dir);
						gf.getNetClient().send(msg);
					}
					
					if(y>GameFrame.HEIGHT) blood = 0;

					try {
						Thread.sleep(7L);
					} catch (InterruptedException var2) {
						var2.printStackTrace();
					}
				}
			}
		}).start();
	}
	
	public boolean hitBullet() {
		  Rectangle stickManRec = new Rectangle(this.x, this.y, 32, 32);

		  for(int i = 0; i < this.gf.getController().getBulletList().size(); i++) {
			  Bullet tempBullet = this.gf.getController().getBulletList().get(i);
			  Rectangle object = tempBullet.getBounds();

			  if (stickManRec.intersects(object)) {
				  this.gf.getController().removeBullet(tempBullet);
				  return true;
			  }
		  }

		  return false;
	}
	
	public void drawBloods(Graphics g) {
		for(int i = 0; i<blood; i++) {
		   g.drawImage((new ImageIcon("heart.png")).getImage(), 980-i*32, 32, 32, 32, (ImageObserver)null);
		 }

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
	public int getInitialX() {
		return this.initialX;
	}
	public int getInitialY() {
		return this.initialY;
	}
	
	public void setStickManDie(boolean die) {
		this.die = die;
	}
	
	public void setDir(int dir) {
		this.dir = dir;
	}
	
	public int getDir() {
		return dir;
	}
}
