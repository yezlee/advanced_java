package kr.or.ddit.basic1028;

import javax.swing.JOptionPane;

public class ThreadTest05 {

	public static void main(String[] args) {
		String str = JOptionPane.showInputDialog("아무거나 입력하세요."); //입력을 취소하면 null값 나옴
		System.out.println("입력한 값 : " + str);

		for(int i = 10; i >= 1; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000); // 1초동안 잠시 멈춘다.
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
	}

	// 이렇게 같이? 하나의 스레드로 하면 입력값을 같이 받고 실행하는게 잘 안됨.
	// 그래서 스레드 2개 해줘야돼. 
}
