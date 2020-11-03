package kr.or.ddit.basic1028;

import java.util.Calendar;

//스레드에서 객체를 공통으로 사용하는 예제
/*
  	원주율을 계산하는 스레드와
  	계산된 원주율을 출력하는 스레드가 있다.

  	(위 스레드 둘다 원주율을 다룸)
  	
  	원주율을 저장하는 객체가 필요하다.
  	이 객체를 두 스레드에서 공통으로 사용해서 처리한다.
  	(참조값을 전달해주면됨)
 */

public class ThreadTest15 {

	public static void main(String[] args) {
		//공통으로 사용할 객체를 생성
		ShareData sd = new ShareData();
		
		//스레드 객체를 생성하고, 공통으로 사용할 객체를 스레드에 주입한다.
		PrintPIThread printPi = new PrintPIThread(sd); //아까 생성자로 만든걸 초기화할때 이렇게
		
		CalcPIThread calcPi = new CalcPIThread();
		calcPi.setSd(sd);
		
		System.out.println("계산 시작...");
		calcPi.start();
		printPi.start();
		
	}

}


//원주율을 계산하는 스레드
class CalcPIThread extends Thread{
	private ShareData sd;

/*	//생성자에서 꼭 안해도돼. 하면 좋은데. 그게 오직 방법은 아님
	public CalcPIThread(ShareData sd) {
		this.sd = sd;
	}
*/
	
	
	//setter이용하기
	public void setSd(ShareData sd) {
		this.sd = sd;
	}
	
	
	@Override
	public void run() {
	/*
		 원주율 = (1/1 - 1/3 + 1/5 - 1/7 + 1/9 - 1/11 +........) * 4;
		 		1 -	3 + 5 - 7 +	9 - ........
		 		0 -	1 + 2 - 3 +	4 - ........ 2로 나눈 몫/ 홀수일때 마이너스고 짝수일때 플러스
		 		(i/2)이렇게하면 몫이 구해짐
		 		(i/2) % 2  이건 위의 나머지
	 */
		double sum = 0.0;
		for(int i = 1; i <= 1000000000; i+=2) {
			if( (i/2) % 2 == 0 ) { //2로 나눈 몫이 짝수라면
				sum += (1.0 / i);
			}else {
				sum -= (1.0 / i);
			}
		}//반복문 다 끝난후에
		sd.result  = sum * 4; // 계산된 원주율 저장
		sd.isOk = true;		  // 계산이 완료됨을 설정한다.
		
	}
	
}




//원주율을 출력하는 스레드
class PrintPIThread extends Thread{
	//세어데이터를 쓸 참조값을 저장해야해 .그걸 저장할 변수가 필요
	private ShareData sd;  //공통으로 사용할 객체의 참조갓이 저장될 변수 선언
	
	public PrintPIThread(ShareData sd) {
		this.sd = sd;
	}
	
	@Override
	public void run() {
		while(true) {
			if(sd.isOk == true) { //계산이 완료 되었는지 검사
				break; //완료되면 이 반복문을 빠져나오게				
			}
		}
		System.out.println();
		System.out.println("결과 : " + sd.result);
		System.out.println("PI : " + Math.PI);
		
	}
	
}



//공통으로 사용할 클래스 ==> 원주율을 관리하는 클래스
class ShareData{
	public double result;  //계산된 원주율이 저장될 변수
	
	// volatile ==> 캐시같은걸 써. 캐시를 제일 많이 쓰는게, 웹브라우저 접속할때. 캐시(임시기억저장소)
	//캐시를 거치지 말고 무조건 메모리에서 가져올때 쓰느게 volatile
	// volatile ==> 이 키워드가 붙은 변수는 컴파일러의 최적화 대상에서 제외된다. 
	//			==> 즉 값이 변경되는 즉시 변수에 적용시킨다.
	public volatile boolean isOk;   //계산이 완료되었는지 여부를 나타내는 변수 - 기다려야하니까
	
	
	
}

