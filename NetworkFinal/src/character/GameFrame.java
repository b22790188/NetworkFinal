package character;

import java.util.*; // guo added
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import javax.swing.JFrame;

import Msg.New_Stickman_Msg;
import Msg.Stickman_Move_Msg;
import map.Map;
import Net.NetClient;


public class GameFrame extends JFrame {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 576;
	public static final double OBJECT_SIDE = 32;
	
	private static final Graphics Graphics = null;
	private Controller controller = new Controller(this);
	
	private List<StickMan> StickManSet = new ArrayList<StickMan>(); //StickManSet is used to record Stickman online
	private int clientID;
	private StickMan stickMan;
	public Map map;
	private NetClient nc;                     //用來連線的NetClient
	
	private int deathNum = 0;
	

	public GameFrame() throws Exception {
		nc = new NetClient(this);
		map = new Map(nc.getMapID(),nc.getImageID());
		stickMan = new StickMan(this,StickMan.initialX,StickMan.initialY);
		stickMan.setID(clientID);
		
		New_Stickman_Msg msg = new New_Stickman_Msg(this.getStickMan());
		nc.send(msg);
		
		/*
		 * 當遊戲一開始時，先將自己加入Set中。
		 */
		
		StickManSet.add(this.stickMan);                     
		
		(new Thread() {
			public void run() {
				while(true) {
					GameFrame.this.repaint();
					
					//test
//					System.out.println("StickMansetsize : "+StickManSet.size());
					
					/*
					 * 當僅剩下一人在場上時，就進入這個if，準備關閉遊戲。
					 */
					if(StickManSet.size() > 1 && deathNum == StickManSet.size() - 1) {
						
						/*
						 * 僅不顯示舊的的GameFrame，但其仍存在，待改進。
						 */
						GameFrame.this.setVisible(false);
						
						/*
						 * 送出Disconnect訊息，讓Server端關閉連線。
						 */
						nc.sendDisconnectRequest();
						
						new InitialScene();
						
						break;
					}
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
//		Map mapPanel = new Map();
		this.add(map);
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
			if(!tempStickMan.getStickManDie()) {
				big.drawImage(tempStickMan.getStickMan(), tempStickMan.getStickManX(), tempStickMan.getStickManY(), 32, 32, (ImageObserver)null);				
			}
		}

		controller.drawBullets(big);
		this.map.drawMap(big);
		this.stickMan.drawBloods(big);
		g.drawImage(bi, 0, 0, (ImageObserver)null);
	}
	
	/*
	 * code below added by guo
	 */
	public void setClientID(int clientID) {
		this.clientID = clientID;
	}
	
	
	public NetClient getNetClient() {
		return nc;
	}
	
	public List<StickMan> getStickManSet(){
		return StickManSet;
	}
	
	public void setDeathNum(int deadNum) {
		this.deathNum = deadNum;
	}
	
	public int getDeathNum() {
		return deathNum;
	}
	
	public int getClientID() {
		return clientID;
	}
}
