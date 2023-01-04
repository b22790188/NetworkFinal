package Msg;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

import character.GameFrame;
import character.StickMan;

public class Stickman_Dead_Msg implements Msg{
	private int msgType = Msg.STICKMAN_DEAD_MSG;
	private int stickmanID;
	private GameFrame client;
	
	public Stickman_Dead_Msg(GameFrame client) {
		this.client = client;
	}
	
	public Stickman_Dead_Msg(int stickmanID) {
		this.stickmanID = stickmanID;
	}
	
	@Override
	public void send(DatagramSocket ds, String Server_IP, int Server_UDP_Port) {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		DataOutputStream dos = new DataOutputStream(baos);
		
		try {
			dos.writeInt(msgType);
			dos.writeInt(stickmanID);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
		byte[] buf = baos.toByteArray();
		
		try {
			DatagramPacket d_packet = new DatagramPacket(buf,buf.length,new InetSocketAddress(Server_IP,Server_UDP_Port));
			ds.send(d_packet);
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	@Override
	public void parse(DataInputStream dis) {
		try {
			int removing_stickman_id = dis.readInt();
			
			if(removing_stickman_id == stickmanID) {
				return;
			}
			
			for(StickMan s : client.getStickManSet()) {
				if(removing_stickman_id == s.getID()) {
					s.setLive(false);
					break;
				}
			}
		}
		
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
}
