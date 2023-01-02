package rename_test;

import java.io.*;
import java.net.*;

public class testServer {
	
	public static void main(String[] args) {
		try {
			
			ServerSocket ss = new ServerSocket(10000);
//			System.out.println("This is Server from "+InetAddress.getLocalHost().getHostAddress());
			Socket s = ss.accept();
			
			System.out.println("Waiting for connection from Client...");
			System.out.println("Connectd from Client"+s.getInetAddress().getHostAddress()+" "+s.getPort());
			DataInputStream dis = new DataInputStream(s.getInputStream());
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			
			while(true) {
				
				String str = (String)dis.readUTF();
				System.out.println(str);
				
				dout.writeUTF(str);
				
			}
			
			//ss.close();
			
		}
		catch(SocketException e) {
			System.out.println("Take too many time");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
