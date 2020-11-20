package kr.or.ddit.basic1028;

public class ThreadTest16_answer {

	public static void main(String[] args) {
		ShareObject_2 sobj = new ShareObject_2();
		
		TestThread_2 th1 = new TestThread_2("1번쓰레드", sobj);
		TestThread_2 th2 = new TestThread_2("2번쓰레드", sobj);
		
		th1.start();
		th2.start();

	}

}

class TestThread_2 extends Thread{
	private ShareObject_2 sObj;
	
	// 생성자
	public TestThread_2(String name, ShareObject_2 sObj) {
		super(name);   // 쓰레드의 name설정한다.
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i=0; i<10; i++) {
			sObj.add();
		}
	}
	
}


// 공통으로 사용할 클래스
class ShareObject_2 {
	private int sum = 0;
	
//	public synchronized void add() {  // 방법1 ==> 메서드에 동기화 설정을 한다.
	public void add() {  
		synchronized (this) {  // 방법2 ==> 동기화 블럭으로 설정한다.
			int n = sum;
			
			n += 10; 
			
			sum = n;
					
			System.out.println(Thread.currentThread().getName() + " 합계 : " + sum);
		}
	}
	
}










