package rename_test;

import java.net.*;
import java.util.*;

public class InetTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
//		InetAddress ip = InetAddress.getByName();
		System.out.println(System.getenv("COMPUTERNAME"));
		String computerName = System.getenv("COMPUTERNAME");
		
		Scanner sc = new Scanner(System.in);
		
		String s = sc.next();		
		Socket cs = new Socket();
		
		try {
//			InetAddress ip = InetAddress.getByName(computerName);
			InetAddress ipAddr = InetAddress.getLocalHost();
			System.out.println(ipAddr.getHostAddress());
			
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
