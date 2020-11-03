package kr.or.ddit.basic1028;

import java.util.ArrayList;

import javax.swing.JOptionPane;

/*
   	문제) 컴퓨터와 가위 바위 보를 진행하는 프로그램을 작성하시오.
   		- 컴퓨터의 가위 바위 보는 난수를 이용해서 구하고
   		- 사용자는 showInputDialog()메소드를 이용해서 입력 받는다.
   		
   		- 입력시간은 5초로 제한하고 카운트 다운을 진행한다.
   		- 5초안에 입력이 없으면 게임에 진것으로 처리하고 끝낸다.
   		
   		- 5초안에 입력이 완료되면 승패를 구해서 출력한다.
   		
	-->	5초안에 입력이 완료 되었을 때 결과 예시 ) 
		-------------결과--------------
		컴퓨터 : 가위
		사용자 : 바위
		결   과 : 당신이 이겼습니다.
		
		비길 경우, 질 경우 다 따져서

	
	-->	5초안에 입력이 없을 때 결과 예시 ) 
		-------------결과--------------
   	 	시간이 초과되어 당신이 졌습니다.
 */
public class ThreadTest07 {

	public static void main(String[] args) {
		Thread th1 = new showInputDialog();
		Thread th2 = new CountDownGame();
		
		th2.start();
		th1.start();
		
	}

}

// 가위바위보를 입력하는 스레드
class showInputDialog extends Thread {

	public static boolean inputCheck;
	public static String user_rps;

	@Override
	public void run() {
		user_rps = JOptionPane.showInputDialog("가위, 바위, 보를 입력하세요.");
		inputCheck = true;

	}
}

// 카운트 다운을 진행하는 스레드
class CountDownGame extends Thread {

	@Override
	public void run() {

		int random = (int) (Math.random() * 3) + 1;

		String rps = null;

		if (random == 1) {
			rps = "가위";
		} else if (random == 2) {
			rps = "바위";
		} else {
			rps = "보";
		}

		for (int i = 5; i >= 1; i--) {
			if (showInputDialog.inputCheck == true) {
				
				System.out.println("-------------결과--------------");
				System.out.println("컴퓨터 : " + rps);
				System.out.println("사람 : " + showInputDialog.user_rps);
				
				if (showInputDialog.user_rps.equals("가위") && rps.equals("보") || showInputDialog.user_rps.equals("바위") && rps.equals("가위")
						|| showInputDialog.user_rps.equals("보") && rps.equals("바위")) {		
					System.out.println("축하합니다. 이겼습니다.");
					System.exit(0);
				} else if (showInputDialog.user_rps.equals(rps)) {					
					System.out.println("비겼습니다.");
					System.exit(0);
				} else {					
					System.out.println("졌습니다.");
					System.exit(0);
				}
			}

			System.out.println(i);

			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		System.out.println("5초가 지났습니다. 프로그램을 종료합니다.");
		System.exit(0);

	}
}

/*
 * //입력이 완료되었는지 여부를 검사해서 입력이 완료 되었으면 그 스레드를 비교..해야지 if(DataInput.inputCheck ==
 * true)
 */
