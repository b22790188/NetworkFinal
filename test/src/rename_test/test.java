package rename_test;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ThreadGroupDemo implements Runnable{
	public void run(){
		System.out.println(Thread.currentThread().getName());
	}
}

class WorkerThread implements Runnable{
	private String message;
	public WorkerThread(String s) {
		this.message = s;
	}
	public void run() {
		System.out.println(Thread.currentThread().getName()+"(start) message= "+message);
		System.out.println(Thread.currentThread().getName()+"(end)");
		
	}
}

public class test {

	public static void main(String[] args) {
//		ExecutorService  executor = Executors.newFixedThreadPool(5);
//			for(int i = 0; i < 10; i++) {
//				Runnable worker = new WorkerThread(""+i);
//				executor.execute(worker);
//			}
			
//			executor.shutdown();
//			System.out.println("Finished all threads");
		ThreadGroupDemo runnable = new ThreadGroupDemo();
		ThreadGroup tg = new ThreadGroup("Thead Group 1");
		
		Thread t1 = new Thread(tg,runnable, "1");
		Thread t2 = new Thread(tg,runnable, "2");
		Thread t3 = new Thread(tg,runnable, "3");
		
		t1.start();
		t2.start();
		t3.start();
		
			
	}
	
}
