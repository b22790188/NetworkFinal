package character;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.LinkedList;
import java.util.Random;

public class Controller {
    private LinkedList<Bullet> bulletList = new LinkedList<Bullet>();
//    private LinkedList<OtherPlayer> otherPlayerList = new LinkedList<OtherPlayer>();
    Bullet tempBullet;
//    StickMan tempOtherPlayer;

    private GameFrame gr;
    Random r = new Random();

    Controller(GameFrame gr){
        this.gr = gr;
    }

    public void drawBullets(Graphics g) {
        for(int i = 0; i<bulletList.size(); i++) {
            tempBullet = bulletList.get(i);
            
//            System.out.println("bulletx: "+(int)tempBullet.getBulletX()+" bullety: "+(int)tempBullet.getBulletY());
            g.drawImage(tempBullet.getBulletImage(), (int)tempBullet.getBulletX(), (int)tempBullet.getBulletY(), 32, 32, (ImageObserver)null);
        }
    }

    
//    public void createEnemy(int enemy_count){
//        for(int i=0; i<enemy_count; i++){
//            addEntity(new Enemy(r.nextInt(640), -10, tex, this, game));
//        }
//    }

//    public void tick(){
//        // A class
//        for(int i = 0; i < ea.size(); i++){
//            enta = ea.get(i);
//            enta.tick();
//        }
//        // B class
//        for(int i = 0; i < eb.size(); i++){
//            entb = eb.get(i);
//            entb.tick();
//        }
//    }
//
//    public void render(Graphics g){
//        // A class
//        for(int i = 0; i < ea.size(); i++){
//            enta = ea.get(i);
//            enta.render(g);
//        }
//        // B class
//        for(int i = 0; i < eb.size(); i++){
//            entb = eb.get(i);
//            entb.render(g);
//        }
//    }

    public void addBullet(Bullet block){
        bulletList.add(block);
    }
    public void removeBullet(Bullet block){
        bulletList.remove(block);
    }
//    public void addOtherPlayer(OtherPlayer block){
//        otherPlayerList.add(block);
//    }
//    public void removeOtherPlayer(OtherPlayer block){
//        otherPlayerList.remove(block);
//    }

    public LinkedList<Bullet> getBulletList(){
        return bulletList;
    }
//    public LinkedList<StickMan> getOtherPlayerList(){
//        return otherPlayerList;
//    }
}
