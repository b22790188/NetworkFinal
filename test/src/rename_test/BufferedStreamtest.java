package rename_test;
import java.io.*;

public class BufferedStreamtest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		try {
			
			FileOutputStream fout = new FileOutputStream("D:\\testout.txt");
			BufferedOutputStream bout = new BufferedOutputStream(fout,4);
			
			String s = "Welcome to java point";
			byte b[] = s.getBytes();
			bout.write(b);
			bout.flush();
			bout.close();
			fout.close();
			System.out.println("success");
			
			
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		
	}

}
