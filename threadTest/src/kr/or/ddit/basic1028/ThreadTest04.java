package kr.or.ddit.basic1028;

import javax.swing.plaf.synth.SynthSeparatorUI;

/*
   1~20억까지의 합계를 구하는 프로그램을
    하나의 스레드가 단독으로 처리했을때와 여러개의 스레드가 협력해서 처리할 때의 처리시간을 비교해보자. 
 */

public class ThreadTest04 {

	public static void main(String[] args) {
		//단독으로 처리하는 스레드
		SumThread sm = new SumThread(1L, 2_000_000_000L);

		//여럿이 협력해서 처리하는 스레드
		SumThread[] sums = new SumThread[] {
				new SumThread(1L, 500_000_000L),
				new SumThread(500_000_001L, 1_000_000_000L),
				new SumThread(1_000_000_001L, 1_500_000_000L),
				new SumThread(1_500_000_001L, 2_000_000_000L),
		};
		
		//단독으로 처리하기
		long startTime = System.currentTimeMillis();
		sm.start();
		try {
			sm.join();
		}catch (InterruptedException e) {
			// TODO: handle exception
		}
		long endTime = System.currentTimeMillis();
		System.out.println("단독으로 처리했을 때 처리시간 : " + (endTime - startTime));
		System.out.println();
		
		
		//여러 스레드가 협력해서 처리하는 경우
		startTime = System.currentTimeMillis();
		
//		for(int i = 0; i < sums.length; i++) {
//			sums[i].start();
//		}
//		
		for(SumThread th : sums) {
			th.start();
		}
		
		for(SumThread th : sums) {
			try {
				th.join();
			}catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		endTime = System.currentTimeMillis();
		
		System.out.println("협력해서 처리했을 때 처리시간 : " + (endTime - startTime));
		
	}

}



class SumThread extends Thread{
	//합계를 구할 영역의 시작값과 종료값을 저장할 변수 선언
	private long startNum;
	private long endNum;
	
	//생성자	
	public SumThread(long startNum, long endNum) {
		super();
		this.startNum = startNum;
		this.endNum = endNum;
	}

	@Override
	public void run() {
		long sum = 0L;
		for(long i = startNum; i <= endNum; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
}




/* 클래스를 이렇게 여러개 만들지말고, 이걸 for문으로 만들어
class Sumthread extends Thread{
	@Override
	public void run() {
		long sum = 0L;
		for(long i = 1L; i <= 1_000_000_000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
}

class Sumthread2 extends Thread{
	@Override
	public void run() {
		long sum = 0L;
		for(long i = 1L; i <= 2_000_000_000L; i++) {
			sum += i;
		}
		System.out.println("합계 : " + sum);
	}
	
}*/