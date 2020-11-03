package kr.or.ddit.basic1028;


//
public class ThreadTest16 {

	public static void main(String[] args) {
		ShareObject sobj = new ShareObject();
		
		TestThread th1 = new TestThread("1번스레드", sobj);
		TestThread th2 = new TestThread("2번스레드", sobj);
		
		th1.start();
		th2.start();
	}

}


class TestThread extends Thread{
	private ShareObject sObj;
	
	//생성자
	public TestThread(String name, ShareObject sObj) { //어떤 스레드인지 구분을 해야하니까 이름을 적어주자
		super(name); //스레드의 name을 설정한다.
		this.sObj = sObj;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			sObj.add();
		}
		
	}
	
}



//공통으로 사용할 클래스
class ShareObject {
	private int sum = 0;
	
	//public synchronized void add() { //방법1 ==> 메소드 자체에 동기화 설정을 한다.

	public void add() {
		synchronized (this) { //방법2 ==> 동기화 블럭으로 설정한다.
			
			int n = sum;
			
			n += 10;
			
			sum = n;
			
			System.out.println(Thread.currentThread().getName() + "합계 : " + sum);
		}
	}
	
}





/*
 1번스레드합계 : 20
1번스레드합계 : 30
1번스레드합계 : 40
1번스레드합계 : 50
2번스레드합계 : 20
2번스레드합계 : 70
2번스레드합계 : 80
2번스레드합계 : 90
2번스레드합계 : 100
2번스레드합계 : 110
2번스레드합계 : 120
2번스레드합계 : 130
2번스레드합계 : 140
2번스레드합계 : 150
1번스레드합계 : 60
1번스레드합계 : 160
1번스레드합계 : 170
1번스레드합계 : 180
1번스레드합계 : 190
1번스레드합계 : 200

 이걸 방지하기위해 동기화처리.
 */


/*
 1번스레드합계 : 10
1번스레드합계 : 20
1번스레드합계 : 30
1번스레드합계 : 40
1번스레드합계 : 50
1번스레드합계 : 60
2번스레드합계 : 70
2번스레드합계 : 80
2번스레드합계 : 90
2번스레드합계 : 100
2번스레드합계 : 110
2번스레드합계 : 120
2번스레드합계 : 130
2번스레드합계 : 140
2번스레드합계 : 150
2번스레드합계 : 160
1번스레드합계 : 170
1번스레드합계 : 180
1번스레드합계 : 190
1번스레드합계 : 200

동기화 처리를 했을때
 
 */


