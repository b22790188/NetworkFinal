package rename_test;

import java.util.*;
import java.net.*;
import java.io.*;

public class UDPtestServer {
	/**
	 * @param SERVICE_PORT is specified to UDP port which serivce is bound.
	 * @param BUFFER_SIZE is specified to MAX size of packet.
	 */
	public static final int SERVICE_PORT = 10000;
	public static final int BUFSIZE = 4096;
	private DatagramSocket socket;
	
	public UDPtestServer() {
		try {
			socket = new DatagramSocket(SERVICE_PORT);
			System.out.println("Server active on port" + socket.getLocalPort());
		}
		catch(Exception e) {
			System.err.println("Unable to bound port");
		}
	}
	
	public void service() {
		//Create a buffer for incoming DatagramPacket.
		byte[] buffer = new byte[BUFSIZE];
		while(true) {
			
			try {
				/* Create a DatagramPacket to receive UDP packets,
				 * and show sender's IP address and the port that sender's socket bind.
				 */
				DatagramPacket d_packet = new DatagramPacket(buffer,BUFSIZE);
				socket.receive(d_packet);
				System.out.println("Packet received from" + d_packet.getAddress()+":"+d_packet.getPort()+" of length "
						+d_packet.getLength());
				
				ByteArrayInputStream bin = new ByteArrayInputStream(d_packet.getData());
				for(int i = 0;i < d_packet.getLength(); i++) {
					int data = bin.read();
					if(data == -1) {
						break;
					}
					else {
						System.out.print((char)data);
						//
					}
	
				}
				
				
			}
			catch(IOException ioe) {
				System.out.println("Error"+ioe);
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
