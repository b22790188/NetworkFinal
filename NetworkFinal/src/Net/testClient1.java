package Net;

import java.util.*;
import java.net.*;
import java.io.*;

public class testClient1 {
	public static void main(String[] args) {
		String Server_ip;
		int Server_UDP_Port;
		byte[] buf = new byte[256];
		
		try {
			/*
			 * code below is used to create connection between server and client
			 */
			Socket s = new Socket("localhost",10000);
			DatagramSocket UDPSocket = new DatagramSocket();
			
			DataInputStream instream = new DataInputStream(s.getInputStream());
			DataOutputStream outstream = new DataOutputStream(s.getOutputStream());
			outstream.writeUTF(InetAddress.getLocalHost().getHostAddress());
			outstream.writeInt(UDPSocket.getLocalPort());
			
			Server_ip = instream.readUTF();
			System.out.println(Server_ip);
			Server_UDP_Port = instream.readInt();
			System.out.println(Server_UDP_Port);
			
			/*
			 * code below is used to send packet using udp socket.
			 */
			String msg = "This is test from client1";
			UDPSocket.setSoTimeout(2*1000);
			DatagramPacket dp = new DatagramPacket(msg.getBytes(),msg.getBytes().length,
					new InetSocketAddress(Server_ip,Server_UDP_Port));
			UDPSocket.send(dp);
			
			DatagramPacket dp_receive = new DatagramPacket(buf,buf.length);
			UDPSocket.receive(dp_receive);
			
			String str = new String(dp_receive.getData(),0,dp.getLength());
			System.out.println(str);
			
			/*
			 * Free resources.
			 */
			s.close();
			UDPSocket.close();
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
	}
	
	private class UDPThread{
		
	}
}
