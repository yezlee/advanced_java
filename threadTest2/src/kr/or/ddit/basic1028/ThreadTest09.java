package kr.or.ddit.basic1028;

//데몬 스레드 연습  ==> 자동 저장하는 스레드
public class ThreadTest09 {
	
	public static void main(String[] args) {
		AutoSaveThread autoSave = new AutoSaveThread();
		
		//데몬스레드로 설정하기 ==> 반드시 start()메소드 호출전에 설정한다.!!!!
		autoSave.setDaemon(true);
		autoSave.start();
		
		try {
			for(int i = 1; i <= 20; i++) {
				System.out.println(i);
				Thread.sleep(1000);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		System.out.println("main 스레드 종료.");//메인메소드를 스레드처럼 사용
	}
}



//자동 저장하는 스레드 ==> 3초에 한번씩 자동 저장하는 스레드
class AutoSaveThread extends Thread{
	
	//작업 내용을 저장하는 메소드(지금은 그냥 메시지만 저장해보자)
	public void save() {
		System.out.println("작업 내용을 저장합니다.");
	}
	
	@Override
	public void run() {
		while(true) {
			try {
				Thread.sleep(3000);
				save();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}





































