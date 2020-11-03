package kr.or.ddit.basic1028;
/*
 	Thread의 stop()메소드를 호출하면 스레드가 바로 멈춘다.
 	이 때 사용하던 자원을 정리하지 못하고 프로그램이 종료되어
 	나중에 실행되는 프로그램에 영행을 줄 수있다.
 	그래서 stop()메소드는 비추천으로 되어 있다. 
 */
public class ThreadTest14 {
	public static void main(String[] args) {
		
		/*
		ThreadStopTest1 th1 = new ThreadStopTest1();
		th1.start();
	
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		//th1.stop(); //얘는 자원정리가 안떠
		th1.setStop(true); //stop대신에
		*/
		
		
		// interrupt()메소드를 이용한 스레드 멈추기
		ThreadStopTest2 th2 = new ThreadStopTest2();
		th2.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		th2.interrupt();
		
		
	}
}




//스레드를 멈추게 하는 연습용 스레드(stop대신)
class ThreadStopTest1 extends Thread{
	private boolean stop; //스레드의 종료를 제어할 때 사용하는 변수 선언
	
	public void setStop(boolean stop) {
		this.stop = stop;
	}
	
	@Override
	public void run() {
		while(!stop) {
			System.out.println("스레드 실행 중...");
		}
		
		System.out.println("자원 정리..."); //자원은 대기하고 있는 애들..? 캐쉬나 메모리에서, 객체도 자원?
		System.out.println("스레드 종료...");
	}
	
}



//interrupt()메소드를 이용하여 스레드를 멈추게 하는 방법
class ThreadStopTest2 extends Thread{
	@Override
	public void run() {
		/*
		//방법1) interrupt()메소드와 sleep()메소드를 이용하는 방법
		//스레드가 일시정지 상태에서 interrupt()메소드가 호출되면
		//해당 스레드는 일시정지 상태에서 풀려나고 동시에 InterruptException을 발생시킨다.
		
		try {
			while (true) {
				System.out.println("...실행중...");
				Thread.sleep(1);
			}
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
		
		*/
		
		
		//방법2) 얘는 try catch를 거의 안쓴다보면됨
		while(true){
			System.out.println("Thread 실행중...");
			
			//interrupt()메소드가 호출되었는지 검사한다.
			
			/*
			//검사방법1) 스레드의 인스턴스 메소드인 isInterrupted()메소드를 이용한다.
			// isInterrupted()메소드는 interrupt()메소드가 호출되면 true를 반환한다.
			if(this.isInterrupted()) { //isInterrupted() 인스턴스메소드
				break;
			}			
			*/
			
			//검사방법2) 스레드의 정적메소드인 interrupted()메소드 이용하기
			//interrupted()메소드 ==> interrupt()메소드가 호출되면 true를 반환한다.
			if(Thread.interrupted()) { // interrupted() 정적메소드
				break;
			}
			
		}
		
		
		System.out.println("자원 정리.................."); //위에가 무한반복이라 실행될일이 없다고 빨간줄 뜸
		System.out.println("스레드 종료.................");
	}
}











