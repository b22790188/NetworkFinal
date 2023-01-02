package rename_test;

public class TestGarbage {
	public static void main(String argc[]) {
		TestGarbage g1 = new TestGarbage();
		TestGarbage g2 = new TestGarbage();
		g1 = null;
		g2 = null;
		System.gc();
		
	}
}
