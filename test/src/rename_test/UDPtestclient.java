package rename_test;

import java.io.IOException;
import java.net.*;

public class UDPtestclient {
	public static void main(String[] args) {
		try {
			DatagramSocket s = new DatagramSocket();			
			System.out.println(s.getLocalAddress());
			System.out.println(s.getLocalPort());
		}
		catch(IOException ioe) {
			ioe.printStackTrace();
		}
		
	}
}
