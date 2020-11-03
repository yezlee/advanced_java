package kr.or.ddit.basic1028;

//yield 연습
public class ThreadTest11 {

	public static void main(String[] args) {
		YieldThread th1 = new YieldThread("1번 스레드");
		YieldThread th2 = new YieldThread("2번 스레드");

		th1.start();
		th2.start();
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("========================================");
		th1.work = false;
		
		
		
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&&");
		th1.work = true;
		
		
		
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		th1.stop = true;
		th2.stop = true;
	}

}


//yield()메소드 연습용 스레드
class YieldThread extends Thread{
	public boolean stop = false; //스레드의 종료 여부를 나타내는 갓을 저장하는 변수 선언
	public boolean work = true; //작업을 처리하는 여부를 나타내는 값을 저장하는 변수 선언
	
	//생성자
	public YieldThread(String name) {
		super(name); //스레드의 이름을 설정한다.
	}
	
	@Override
	public void run() {
		while(!stop) { //stop이 true가 되면 반복문이 종료된다.
			//stop이 true가 아닐동안 반복해라.
			if(work) {
				//getName()메소드는 => 현재 스레드의 name값을 반환한다.
				System.out.println(getName() + " 작업중...");
			}else {
				System.out.println(getName() + " 양보...");
				Thread.yield();
			}
		}
	}
}



