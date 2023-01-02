package rename_test;
import java.util.*;
import java.io.*;

public class Fileinputstream {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileInputStream fin = new FileInputStream("D:\\testout.txt");
			int i = 0;
			while((i=fin.read())!= -1) {
				System.out.print((char)i);
			}
			System.out.println((char)i);
			
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

}
