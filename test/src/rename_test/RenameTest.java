package rename_test;
import java.lang.*;

class ThreadNew extends Thread{
	
	ThreadNew(String tName, ThreadGroup tgrp){
		super(tgrp,tName);
		start();
	}
	
	public void run() {
		
	}
}

public class RenameTest {

	public static void main(String[] args) {
		ThreadGroup tg = new ThreadGroup("Thread Group 1");
		
		
	}

}
