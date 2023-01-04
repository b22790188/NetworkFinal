package character;

import map.Map;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Bullet implements Runnable{
	
	//added by guo
	private static int ID = 10;
	private int stickmanID;
	private int id; 
	
    private Image bullet = (new ImageIcon("磚頭.png")).getImage();
    private GameFrame gf;
    private double valX;
    private double valY;
    private double x;
    private double y;
    private Thread th;
    private Map map = new Map();

    //修改建構子型態 by guo
    public Bullet(double x, double y, double valX, double valY, GameFrame gf) throws IOException {
    	/*
    	 * add by guo
    	 * Stickman取得火柴人的ID，用來判斷子彈是誰的。
    	 */
    	this.stickmanID = gf.getStickMan().getID();
    	this.id = ID++; 
    	
        this.gf = gf;
        double len = Math.pow(Math.pow(valX, 2)+ Math.pow(valY, 2), 0.5);
        this.x = x;
        this.y = y;
        this.valX = valX/len;
        this.valY = valY/len;
//        System.out.println("valX: "+this.valX);
//        System.out.println("valY: "+this.valY);
        th = new Thread(this);
        th.start();
    }

    public void run() {
        while(true) {
            x += valX;
            y += valY;
//            System.out.println(x+" "+y);

            if(x <= 0) {
                gf.getController().removeBullet(this);
                th.stop();
                
                //added by guo
                break;
            }
            if(x >= 1024) {
                gf.getController().removeBullet(this);
                th.stop();
                
                //added by guo
                break;
            }
            if(y <= 0) {
                gf.getController().removeBullet(this);
                th.stop();
                
                //added by guo
                break;
            }
            if(y >= 576) {
                gf.getController().removeBullet(this);
                th.stop();
                
                //added by guo
                break;
            }
            hit();

            try {
                Thread.sleep(20L);
            } catch (InterruptedException var2) {
                var2.printStackTrace();
            }
        }
    }

    public boolean hit() {
        Rectangle bulletRec = new Rectangle((int)x, (int)y, 32, 32);

        for(int i = 0; i < this.map.getTileNum(); ++i) {
            Rectangle object = null;
            int objectX = (Integer)this.map.mapList.map1_barrier[0][i].get(0);
            int objectY = (Integer)this.map.mapList.map1_barrier[0][i].get(1);
            object = new Rectangle(objectX, objectY, 32, 32);

//            if (this.valX<0) {
//                object = new Rectangle(objectX - 3, objectY, 32, 32);
//            } else if (this.valX>=0) {
//                object = new Rectangle(objectX + 3, objectY, 32, 32);
//            } else if (this.valY>0) {
//                object = new Rectangle(objectX, objectY - 3, 32, 32);
//            } else if (this.valY<=0) {
//                object = new Rectangle(objectX, objectY + 3, 32, 32);
//            }

            if (bulletRec.intersects(object)) {
//				System.out.println(objectX + " " + objectY);
//				System.out.println(this.x + " " + this.y);
                gf.getController().removeBullet(this);
            }
        }

        return false;
    }

    public Image getBulletImage() {
        return this.bullet;
    }
    public double getBulletX(){
        return x;
    }
    public double getBulletY(){
        return y;
    }
    public double getValX(){
        return valX;
    }
    public double getValY(){
        return valY;
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
    
    public int getStickmanID() {
    	return stickmanID;
    }
}
