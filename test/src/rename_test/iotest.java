package rename_test;

import java.io.*;
import java.util.*;

public class iotest {

	public static void main(String[] args) {
		System.out.println("simple message");
		System.err.println("error message");
		Scanner sc = new Scanner(System.in);
		
		byte[] buf = new byte[256];
		buf[0] = 0x11;
		buf[1] = 0x12;
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream(256);
		ByteArrayInputStream bais = new ByteArrayInputStream(buf);
		DataInputStream dis = new DataInputStream(bais);
		buf[2] = 0x13;
		
		int c;
		try {
			while((c = bais.read()) != -1) {
				System.out.println(c);
			}
		}
		catch(Exception ioe) {
			ioe.printStackTrace();
		}
		
		
		try {
			
			FileOutputStream fout = new FileOutputStream("D:\\testout.txt");
			String s = sc.next();
			byte b[] = s.getBytes();
			
			
			fout.write(b);
			fout.close();
			System.out.println("success");
			
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}

	}

}
