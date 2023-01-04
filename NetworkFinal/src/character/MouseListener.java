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
    int valx;
    int valy;
    
    public MouseListener(GameFrame gf) {
        this.gf = gf;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX()-16;
        int my = e.getY()-16;
//        if(!is_shooting){
        try {
        	

        	Bullet newBullet = new Bullet(gf.getStickMan().getStickManX(), gf.getStickMan().getStickManY(), (mx-gf.getStickMan().getStickManX()), (my- gf.getStickMan().getStickManY()), gf);
        	System.out.println("newBulletx: "+newBullet.getBulletX()+" newBullety: "+newBullet.getBulletY()+"new valx"+newBullet.getValX());
        	gf.getController().getBulletList().add(newBullet);
        	
        	New_Bullet_Msg msg = new New_Bullet_Msg(newBullet);        	
        	gf.getNetClient().send(msg);
            
//        	gf.getController().addBullet(new Bullet(gf.getStickMan().getStickManX(), gf.getStickMan().getStickManY(), (mx-gf.getStickMan().getStickManX()), (my- gf.getStickMan().getStickManY()), gf));
   
        	
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        }

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
