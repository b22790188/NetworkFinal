package rename_test;

import java.io.*;
import java.net.*;
import java.util.*;

public class testClient {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		byte[] buf = new byte[256];
		
		try {
			Socket s = new Socket("localhost",10000);
			DataOutputStream dout = new DataOutputStream(s.getOutputStream());
			DataInputStream din = new DataInputStream(s.getInputStream());
			
			
//			while(true) {			
//				String input = sc.next();
//				dout.writeUTF(input);
//				dout.flush();
//				
//				String str = (String)din.readUTF();
//				System.out.println(str);
//				
//			}
//			dout.close();
//			s.close();
			System.out.println(buf.length);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

}
