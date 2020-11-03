package kr.or.ddit.basic1028;

public class ThreadTest01 {
	
	//싱글 스레드 *200개 찍기, %200개 찍기 두가지의 기능을 가지고 있음
	public static void main(String[] args) {
		for(int i = 1; i <=200; i++) {
			System.out.print("*");
		}
		
		System.out.println();
		System.out.println();
		
		for(int j = 1; j <= 200; j++) {
			System.out.print("$");
		}

	}

}
