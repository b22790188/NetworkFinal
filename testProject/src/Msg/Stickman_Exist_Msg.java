package Msg;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.DataInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import character.GameFrame;
import character.StickMan;


public class Stickman_Exist_Msg implements Msg{
	
	private int msgType = Msg.STICKMAN_EXIST_MSG;
	private StickMan stickman;
	private GameFrame client;
	
	public Stickman_Exist_Msg(GameFrame client) {
		this.client = client;
	}
	
	public Stickman_Exist_Msg(StickMan stickman) {
		this.stickman = stickman;
	}
	
	@Override
	public void send(DatagramSocket ds, String serverIP, int Server_UDP_Port) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream(30);
		DataOutputStream dos = new DataOutputStream(baos);
		
		try {	
			dos.writeInt(msgType);
			dos.writeInt(stickman.getID());
			dos.writeInt(stickman.getStickManX());
			dos.writeInt(stickman.getStickManY());
			
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		
		byte[] buf = baos.toByteArray();
		
		try {
			DatagramPacket d_packet = new DatagramPacket(buf,buf.length,new InetSocketAddress(serverIP,Server_UDP_Port));
			ds.send(d_packet);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	@Override
	public void parse(DataInputStream dis) {
		try {
			/*
			 * 遇到自己或從set中找到已存在的Stickman就return，表示現有的stickMan都以經被加入set中，
			 * 反之則代表，過往還有stickMan沒有被加入set中，需要增加。
			 */
			
			System.out.println("sitckman exist");
			int id = dis.readInt();
			if(id == this.client.getStickMan().getID()) {
				return;
			}
			
			boolean exist = false;
			for(StickMan s : this.client.getStickManSet()) {
				if(id == s.getID()) {
					exist = true;
					return;
				}
			}
			
			if(!exist){
				int update_x = dis.readInt();
				int update_y = dis.readInt();
				
				StickMan existStickman = new StickMan(client,update_x,update_y);
				existStickman.setID(id);
				client.getStickManSet().add(existStickman);
			}
			
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
