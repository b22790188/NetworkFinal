package Msg;

import java.io.*;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.InetSocketAddress;
import character.GameFrame;
import character.StickMan;

public class Stickman_Move_Msg implements Msg{
	
	/**
	 * @param msgType 訊息類型
	 * @param x,y     要更新的x,y座標
	 * @param client  客戶端資料，用來後續做更新
	 * @param id      自己火柴人的ID
	 */
	private int msgType = Msg.STICKMAN_MOVE_MSG;
	private int x, y;
	private GameFrame client;
	private int id;
	
	public Stickman_Move_Msg(GameFrame client){
		this.client = client;
	}
	
	public Stickman_Move_Msg(int id, int x, int y){
		this.id = id;
		this.x = x;
		this.y = y;
	}
	
	/*
	 * Create stream, write parameter into stream, then send packet to server.
	 */
	@Override
	public void send(DatagramSocket ds, String serverIP, int Server_UDP_Port) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(30);
		DataOutputStream dos = new DataOutputStream(baos);
		
		try {
			dos.writeInt(msgType);
			dos.writeInt(id);
			dos.writeInt(x);
			dos.writeInt(y);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		byte[] buf = baos.toByteArray();
		
		try {
			DatagramPacket dp = new DatagramPacket(buf, buf.length, new InetSocketAddress(serverIP,Server_UDP_Port));
			ds.send(dp);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
	
	@Override
	public void parse(DataInputStream dis) {
		try {
			
			/*
			 * 當訊息內的ID符合某一個stickmanID時，代表那個stickman需要被更新座標，遇到自己就reutrn。
			 */
			int id = dis.readInt();
			if(id == this.client.getStickMan().getID()) {
				return;
			}
			
			int update_x = dis.readInt();
			int update_y = dis.readInt();
			
			for(StickMan c : client.getStickManSet()) {
				if(c.getID() == id) {
					c.setStickManX(update_x);
					c.setStickManY(update_y);
					break;
				}
			}			
			
			System.out.println("Msg Parse Successfuly");
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
