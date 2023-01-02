package rename_test;

import java.io.*;

public class iotest1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s = "test";
		byte[] buf = new byte[255];
		buf = s.getBytes();
		
		ByteArrayInputStream bais = new ByteArrayInputStream(buf,0,buf.length);
		ByteArrayOutputStream baos = new ByteArrayOutputStream(100);
		
		
		
	}

}
