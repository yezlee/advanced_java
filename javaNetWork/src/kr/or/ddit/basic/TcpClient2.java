package kr.or.ddit.basic;

import java.net.Socket;
import java.util.Scanner;

public class TcpClient2 {

	public static void main(String[] args) {
		// 서버에 접속하는 소켓을 생성하고, 서버와 접속이 성공하면
		// 메시를 보내는 클래스와 메시지를 받는 클래스를 생성할 때 이 소켓을 넣어준다.
		
		try {
			Socket socket = new Socket("192.168.42.39", 7788); //"localhost라고 입력하거나, 아이피주소  쓰거나"
			System.out.println("서버에 연결 되었습니다.");
			
			Sender sender = new Sender(socket);
			Receiver receiver = new Receiver(socket);
			
			sender.start();
			receiver.start();
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
