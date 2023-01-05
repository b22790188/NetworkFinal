package character;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import Msg.Msg;
import Msg.New_Bullet_Msg;

public class MouseListener extends MouseAdapter {
    private GameFrame gf;
    
    /*
     * code below added by guo
     */
    
    public MouseListener(GameFrame gf) {
        this.gf = gf;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX()-16;
        int my = e.getY()-16;
        double w = 45;
        double x = gf.getStickMan().getStickManX();
        double y = gf.getStickMan().getStickManY();
        
        if(!this.gf.getStickMan().getStickManDie()  && !((mx < x + GameFrame.OBJECT_SIDE/2 && mx > x - GameFrame.OBJECT_SIDE/2) && (my < y + GameFrame.OBJECT_SIDE/2 && my > y - GameFrame.OBJECT_SIDE/2))) {
	        try {
	        	double valX = mx-gf.getStickMan().getStickManX();
	            double valY = my-gf.getStickMan().getStickManY();
	            double len = Math.pow(Math.pow(valX, 2)+ Math.pow(valY, 2), 0.5);

	            valX = valX/len ;
	            valY = valY/len;
	            x = x + valX * w;
	            y = y + valY * w;
	        	valX = valX * 10;
	        	valY = valY * 10;
	        	
	        	Bullet newBullet = new Bullet(x,y,valX,valY,gf);
	        	System.out.println("newBulletx: "+newBullet.getBulletX()+" newBullety: "+newBullet.getBulletY()+"new valx"+newBullet.getValX());
	        	gf.getController().getBulletList().add(newBullet);
	        	
	        	New_Bullet_Msg msg = new New_Bullet_Msg(newBullet);        	
	        	gf.getNetClient().send(msg);
	            
	//        	gf.getController().addBullet(new Bullet(gf.getStickMan().getStickManX(), gf.getStickMan().getStickManY(), (mx-gf.getStickMan().getStickManX()), (my- gf.getStickMan().getStickManY()), gf));
	   
	        	
	        } catch (IOException ex) {
	            ex.printStackTrace();
	        }
        }
    }
    
    
    /*
     * code below added by guo
    public void setValx(int valx) {
    	this.valx = valx;
    }
    
    public void setValy(int valy) {
    	this.valy = valy;
    }
    
    public int getValx() {
    	return valx;
    }
    
    public int getValy() {
    	return valy;
    }
     */

}
