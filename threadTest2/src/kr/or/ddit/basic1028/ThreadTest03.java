package kr.or.ddit.basic1028;

public class ThreadTest03 {
	//스레드가 수행되는 시간을 체크해 보자
	
	public static void main(String[] args) {
		Thread th = new Thread( new MyRunner() ); //앞에 방법2 그걸 합친게 이거야
		
		//1970년 1월 1일 0시 0분 0초(표준시간)로부터 경과한 시간을 
		//밀리세컨드(1/1000초) 단위로 반환한다.
		long startTime = System.currentTimeMillis();
		th.start();
		try {
			th.join();  //현재 실행중인 쓰레드에서 대상이 되는 쓰레드(지금은 변수th)가 끝날때까지 기다린다.
			//조인을 안써주면 분명 따로따로 실행을 하지만 이게 순식간에 실행이 되어버려서 실행시간이 0초로 뜬다.
			//근데 조인을 써주면, 스타트타임 시작하고, 엔드타임까지 갈때까지 기다리는거야.
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		long endTime = System.currentTimeMillis();
		
		System.out.println("실행시간 : " + (endTime - startTime));
	
		System.out.println("startTime : " + startTime);
		System.out.println("endTime : " + endTime);
	}

}


class MyRunner implements Runnable{

	@Override
	public void run() {
		long sum = 0L;
		for(long i = 1L; i <= 1_000_000_000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
}
