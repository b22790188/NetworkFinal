package rename_test;

import java.util.*;


class People{
	int height;
	int weight;
	
	People(int height, int weight){
		this.height = height;
		this.weight = weight;
	}
}


public class testLIst {

	public static void main(String[] args) {
		People p1 = new People(10,20);
		People p2 = new People(30,40);
		
		List<People> li = new ArrayList<People>();
		
		li.add(p1);
		li.add(p2);
		
		p1.height = 25;
		People p = li.get(0);
		System.out.println(p.height);
		
	}

}
