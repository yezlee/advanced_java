package kr.or.ddit.basic1028;

//스레드의 상태를 출력하는 예제
public class ThreadTest10 {

	public static void main(String[] args) {
		TargetThread t = new TargetThread();
		StatePrintThread th = new StatePrintThread(t);
		

	}

}


// 스레드 상태의 검사 대상이 되는 스레드
class TargetThread extends Thread{
	@Override
	public void run() {
		for(long i = 1L; i <= 20_000_000_000L; i++) {} //시간지연용
		
		try {
			Thread.sleep(1500); // 1.5초동안 쉬어라. 아래 0.5초마다 실행하라고 했으니 이게 3번나오지
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		for(long i = 1L; i <= 20_000_000_000L; i++) {} //시간지연용
	}
}

// TargetThread의 상태를 출력하는 스레드
class StatePrintThread extends Thread{
	private TargetThread target; //TargetThread가 저장될 변수 선언
	
	//생성자
	public StatePrintThread(TargetThread target) {
		this.target = target;
	}
	
	@Override
	public void run() {
		while(true) { //타겟스레드가 끝날때 얘도 끝날거야
			//TargetThread의 상태 구하기
			Thread.State state = target.getState();
			System.out.println("TargerThread의 현재 상태값" + state);
			
			if(state == Thread.State.NEW) { //NEW는 객체는 만들어졌는데 아직 시작하기전
				//스레드의 현재 상태가 new상태이면...
				target.start();
			}
			
			if(state == Thread.State.TERMINATED) { //스레드가 종료 상태이면
				break; //무한반복문을 빠져나가지.
			}
			
			try {
				Thread.sleep(500); //0.5초마다 저 위에걸 한번씩 실행시켜라?
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}
}








