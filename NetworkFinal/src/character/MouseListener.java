package character;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class MouseListener extends MouseAdapter {
    private GameFrame gf;

    public MouseListener(GameFrame gf) {
        this.gf = gf;
    }

    public void mousePressed(MouseEvent e) {
        int mx = e.getX()-16;
        int my = e.getY()-16;
//        if(!is_shooting){
        try {
            gf.getController().addBullet(new Bullet(gf.getStickMan().getStickManX(), gf.getStickMan().getStickManY(), (mx-gf.getStickMan().getStickManX()), (my- gf.getStickMan().getStickManY()), gf));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        }

    }

}
