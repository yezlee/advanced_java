package kr.or.ddit.singleton;

public class SingletonTest {

	public static void main(String[] args) {
//		MySingleton test1 = new MySingleton();  //외부에서 new 명령으로 생성 불가.
		//private MySingleton() { 얘가 public이면 되는데 프라이빗이라 안돼

		MySingleton test2 = MySingleton.getInstance();
		MySingleton test3 = MySingleton.getInstance();
		
		System.out.println("test2 = " + test2.toString());
		System.out.println("test3 = " + test3.toString());
		System.out.println();
		
		System.out.println(test2 == test3); //true
		System.out.println(test2.equals(test3)); //true
		
		test2.displayTest();
		
		
	}

}
