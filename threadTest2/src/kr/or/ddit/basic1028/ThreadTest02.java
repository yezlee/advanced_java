package kr.or.ddit.basic1028;

//멀티 스레드
public class ThreadTest02 {

	public static void main(String[] args) {
		// Thread를 작성해서 사용하는 방법
		
		// 방법1
		// Thread클래스를 상속한 class의 인스턴스를 생성한 후 
		// 이 인스턴스의 start()메소드를 호출해서 실행한다.
		MyThread1 th1 = new MyThread1();
		th1.start(); //run을 호출하는게 아니고 start를 호출함...읭
		

		// 방법2
		// Runnable 인터페이스를 구현한 class의 인스턴스를 생성한 후
		// 이 인스턴스를 Thread의 인스턴스를 생성할 때 생성자의 인수값으로 넘겨준다.
		// 이 때 생성된 Thread의 인스턴스의 start()메소드를 호출해서 실행한다.
		MyThread2 r2 = new MyThread2();
		//바로 실행은 못하고 스레드 객체를 만들어야해
		Thread th2 = new Thread(r2);
		th2.start(); //스레드 시작은 항상 start()!!!
		
		
		// 방법3 (2번째 방법을 조금 다르게 하는거)  ==> 익명 구현체(클래스 구조(스레드를 상속하는 클래스) 안하고 Runnable 이 인터페이스를 오버라이드하는거.. )를 이용하는 방법 - 이름이 없는 구현체라고해서 익명이야
		Runnable r3 = new Runnable() {
			
			@Override
			public void run() {
				for (int i = 1; i <= 200; i++) {
					System.out.println("%");
				}
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
				}
			}

		};
		Thread th3 = new Thread(r3); //멀티스레드는 모든게 다 끝나야 끝난거. 하나의 메소드 끝났다고 끝이 아니야
		th3.start();
		
		System.out.println("main메소드 끝");
	}
}

// 방법1
class MyThread1 extends Thread{ //자바는 단일상속이야. 하나밖에 상속을 못해. 근데 두개 상속받고 싶을때를 대비해서 방법2가 있어
	@Override
	public void run() {
		// 이 run()메소드 안에 Thread가 처리할 내용을 기술한다.
		for(int i = 1; i <=200; i++) {
			System.out.print("*");
			try { //너무 빨라서 스레드가 다른스레드로 넘어가는게 불가능 할수도 있어 그래서 트라이캐치
				// Thread.sleep(시간); ==> 주어진 '시간'동안 작업을 잠시 멈춘다.
				// '시간'은 밀리세컨드 단위를 사용한다. 즉, 1000은 1초를 의미한다.
				Thread.sleep(100);
			}catch(InterruptedException e) {
				
			}
		}
	}
}

//방법2
class MyThread2 implements Runnable{ //얘는 방법이 3단계야. 위에보면 상속도하고 인터페이스 기능도 쓰고??? 
	
	@Override
	public void run() {
		// 이 run()메소드 안에 Thread가 처리할 내용을 기술한다.
		for(int j = 1; j <= 200; j++) {
			System.out.print("$");
			try {
				Thread.sleep(100);
			}catch(InterruptedException e) {
			}
		}
	}
}
































