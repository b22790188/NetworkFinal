package rename_test;

public class RuntimeTest {
	public static void main(String argc[]) throws Exception{
//		Runtime.getRuntime().exec("BurpSuite");
		
		Runtime r = Runtime.getRuntime();
		System.out.println(Runtime.getRuntime().availableProcessors());
		
		System.out.println(r.freeMemory());
	}
}
