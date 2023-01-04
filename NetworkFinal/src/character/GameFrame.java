package character;

import java.util.*; // guo added
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.swing.JFrame;

import Msg.Stickman_Move_Msg;
import map.Map;
import Net.NetClient;


public class GameFrame extends JFrame {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 576;
	private static final Graphics Graphics = null;
	private StickMan stickMan = new StickMan(this,StickMan.initialX,StickMan.initialY);
	private Controller controller = new Controller(this);
	public Map map = new Map();
	
	private List<Bullet> BulletSet = new ArrayList<>();     //BulletSet is used to record bullet on screen 
	
	private List<StickMan> StickManSet = new ArrayList<StickMan>(); //StickManSet is used to record Stickman online
	private NetClient nc = new NetClient(this);             //added by guo

	public GameFrame() throws Exception {
		
		StickManSet.add(this.stickMan);                     //added by guo
		
		(new Thread() {
			public void run() {
				while(true) {
					GameFrame.this.repaint();
				}
			}
		}).start();
		this.initFrame();
	}

	public StickMan getStickMan() {
		return this.stickMan;
	}
	public Controller getController() {
		return this.controller;
	}

	public void initFrame() throws IOException {
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Window");
		this.setResizable(false);
		this.setLocationRelativeTo((Component)null);
		this.setDefaultCloseOperation(3);
		Map mapPanel = new Map();
		this.add(mapPanel);
		this.setBackground(Color.WHITE);
		this.setVisible(true);
		KeyListener kl = new KeyListener(this);
		this.addKeyListener(kl);
		MouseListener ml = new MouseListener(this);
		this.addMouseListener(ml);
	}

	public void paint(Graphics g) {
		BufferedImage bi = (BufferedImage)this.createImage(1024, 576);
		Graphics big = bi.getGraphics();
		
		StickMan tempStickMan;
		for(int i = 0 ; i < StickManSet.size() ; i++) {
			
			tempStickMan = StickManSet.get(i);
			
			big.drawImage(tempStickMan.getStickMan(), tempStickMan.getStickManX(), tempStickMan.getStickManY(), 32, 32, (ImageObserver)null);
//			System.out.println("setx : "+this.StickManSet.get(i).getStickManX()+"sety : "+this.StickManSet.get(i).getStickManY());
//			System.out.println("x : "+this.stickMan.getStickManX()+"y : "+this.stickMan.getStickManY());
		}

		/*
		 *commented by guo 
		big.drawImage(this.stickMan.getStickMan(), this.stickMan.getStickManX(), this.stickMan.getStickManY(), 32, 32, (ImageObserver)null);
		 */
		controller.drawBullets(big);
		this.map.drawMap(big);
		g.drawImage(bi, 0, 0, (ImageObserver)null);
	}
	
	/*
	 * code below added by guo
	 */
	public NetClient getNetClient() {
		return nc;
	}
	public List<Bullet> getBulletSet() {
		return BulletSet;
	}
	
	public List<StickMan> getStickManSet(){
		return StickManSet;
	}
}
