package rename_test;
import java.util.*;
class ABC implements Runnable{
	public void run() {
		try {
			Thread.sleep(100);
		}catch(InterruptedException ie) {
			ie.printStackTrace();
		}
	}
}
public class testfile extends Thread{
	public static void main(String[] args) {
		testfile t1 = new testfile("user-1");
		testfile t2 = new testfile("user-2");
		t1.setPriority(6);
		t2.setPriority(4);
		
		t1.start();
		t2.start();
		
	}
	testfile(){
	
	}
	
	testfile(String threadName){
		super(threadName);
	}
	
	public void run() {
		for(int i = 0 ;i < 5; i++) {
			try {
				sleep(500);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(getName());
			System.out.println(getPriority());
		}
	}
}
