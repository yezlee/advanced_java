package kr.or.ddit.basic1028;

import javax.swing.JOptionPane;

public class ThreadTest06 {

	public static void main(String[] args) {
		Thread th1 = new DataInput(); //thread를 써준이유는 부모객체에 ?를 써줄수 있어서???
		Thread th2 = new CountDown();
		
		th1.start();
		th2.start();

	}

}



//데이터를 입력하는 스레드
class DataInput extends Thread{
	
	//입력 여부를 확인하기 위한 변수 선언 ==> 스레드에서 공통으로 사용할 변수
	public static boolean inputCheck; //초기화를 안하면 그냥 false가 들어감
	
	@Override
	public void run() {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요."); //입력하기 전까진 여기서 머물러있다가 뭔가 입력하면 str에 들어가 그 값이
		inputCheck = true; //뭔가 입력해서 여기로 넘어오면 true!
		System.out.println("입력한 값 : " + str); 
	}
}

//카운트 다운을 진행하는 스레드
class CountDown extends Thread{
	@Override
	public void run() {
		for(int i = 10; i >= 1; i--) {
			
			// 입력이 완료되었는지 여부를 검사해서 입력이 완료되면 스레드를 종료시킨다.
			if(DataInput.inputCheck == true) {
				return;		//run()메소드가 종료되면 해당 스레드도 종료된다.
				//break만 넣으면 for문으로 돌아가. return하면 저 아래 10초지났다 저거 문구 안나와
			}
			
			System.out.println(i);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println("10초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);
	}
}