package character;

import java.util.*; // guo added
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.swing.JFrame;
import map.Map;
import Net.NetClient;


public class GameFrame extends JFrame {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 576;
	private static final Graphics Graphics = null;
	private StickMan stickMan = new StickMan(this);
	private Controller controller = new Controller(this);
	public Map map = new Map();
	
	private NetClient nc = new NetClient(this);             //added by guo
	private List<Bullet> BulletSet = new ArrayList<>();     //BulletSet is used to record bullet on screen 
	private List<StickMan> StickManSet = new ArrayList<>(); //StickManSet is used to record Stickman online
	

	public GameFrame() throws Exception {
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
		
		for(StickMan s: StickManSet) {	
			big.drawImage(s.getStickMan(), s.getStickManX(), s.getStickManY(), 32, 32, (ImageObserver)null);
			System.out.println("x : "+s.getStickManX()+"y : "+s.getStickManY());
		}

//		big.drawImage(this.stickMan.getStickMan(), this.stickMan.getStickManX(), this.stickMan.getStickManY(), 32, 32, (ImageObserver)null);
		controller.drawBullets(big);
		this.map.drawMap(big);
		g.drawImage(bi, 0, 0, (ImageObserver)null);
	}
	/*
	 * code below added by guo
	 */
	public List<Bullet> getBulletSet() {
		return BulletSet;
	}
	
	public List<StickMan> getStickManSet(){
		return StickManSet;
	}
}
