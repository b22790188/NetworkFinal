package rename_test;

class Table{
	private int moneyA = 0;
	private int moneyB = 0;
	
	public synchronized void addMoneyA(int donate) {
		this.moneyA += donate;
		try {
			Thread.sleep((int)(1000*Math.random()));
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("MoneyA = "+moneyA);
	}
	
	public void addMoneyB(int donate) {
		this.moneyB += donate;
		try {
			Thread.sleep((int)(1000*Math.random()));
			
		}
		catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("MoneyB = "+moneyB);
	}
//	public int getMoney() {
//		return money;
//	}
}

public class SynchronizeTest {
	public static void main(String argc[]) {
		final Table obj = new Table();
		Thread t1 = new Thread() {
			public void run() {
				for(int i = 0; i < 3; i++) {					
					obj.addMoneyA(20);
					obj.addMoneyB(20);
				}
			}
		};
		
		Thread t2 = new Thread() {
			public void run() {
				for(int i = 0; i < 3; i++) {					
					obj.addMoneyA(20);
					obj.addMoneyB(20);
				}
			}
		};
		
		t1.start();
		t2.start();
		
	}
}
