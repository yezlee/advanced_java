package kr.or.ddit.basic1028; //1102 - 2

import org.omg.PortableServer.THREAD_POLICY_ID;

/*
	 wait(), notify() 메소드를 이용한 예제 
	 (두 스레드가 번갈아 한번씩 실행하는 예제)
	 
	 - wait(), notify(), notifyAll()메소드는 동기화 영역에서만 사용 가능하다.
	  
 */

public class ThreadTest19 {

	public static void main(String[] args) {
		WorkObject workObj = new WorkObject();
		
		ThreadA tha = new ThreadA(workObj);
		ThreadB thb = new ThreadB(workObj);
		
		tha.start();
		thb.start();

	}

}


//공통으로 사용할 객체
class WorkObject{
	public synchronized void testMethod1() {
		System.out.println("testmethod1() 메소드 실행 중 ...");
		notify(); //두개의 스레드중 뭐가 먼저 실행될지 모르니까 나 notify부터 시작. 아무도 일시정지 영역에 없어도, 야!일어나! 하고선 자기 할일해.
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
	public synchronized void testMethod2() {
		System.out.println("testmethod2() 메소드에서 작업 실행 중 ...");
		notify();
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO: handle exception
		}
	}
	
}


//WorkObject의 testMethod1()메소드만 실행하는 스레드
class ThreadA extends Thread{
	private WorkObject workObj;

	public ThreadA(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			workObj.testMethod1();
		}
		synchronized (workObj) { //마지막에 남아있는 wait 상태를 깨워주기 위해서!
			workObj.notify();
		}
	}
}



//WorkObject의 testMethod2()메소드만 실행하는 스레드
class ThreadB extends Thread{
	private WorkObject workObj;

	public ThreadB(WorkObject workObj) {
		super();
		this.workObj = workObj;
	}
	
	@Override
	public void run() {
		for(int i = 0; i < 10; i++) {
			workObj.testMethod2();
		}
		synchronized (workObj) { //마지막에 남아있는 wait 상태를 깨워주기 위해서!
			workObj.notify();
		}
	}
}













