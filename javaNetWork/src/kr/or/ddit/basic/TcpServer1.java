package kr.or.ddit.basic;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer1 {
	// 서버가 클라이언트한테 메시지를 보낼거야

	public static void main(String[] args) throws IOException {
		// TCP소켓 통신을 위해 ServerSocket 객체를 생성한다.
		ServerSocket server = new ServerSocket(7777);

		System.out.println("접속을 기다립니다..");

		//accept()메소드
		//			==> 클라이언트에서 연결 요청이 올 때까지 계속 기다린다.
		//			==> 연결 요청이 오면 Socket객체를 생성해서 클라이언트의 Socket과 연결한다.
		Socket socket = server.accept(); //socket에는 클라이어트와 연결된 소켓이 들어있어!!!
		
		//accept()메소드 이후의 소스는 연결이 완료된 후에만 실행된다.
		//여기서부턴 연결이 된 후의 코딩이다 라고 생각해
		System.out.println("클라이언트와 연결되었습니다.");
		System.out.println();

		System.out.println("접속한 클라이언트 정보");
		System.out.println("IP 주소 : " + socket.getInetAddress().getHostAddress());
		System.out.println("PORT번호 : " + socket.getPort());
		System.out.println();
		
		System.out.println("연결된 서버 정보");
		System.out.println("IP 주소 : " + socket.getLocalAddress());
		System.out.println("PORT번호 : " + socket.getLocalPort());
		System.out.println();
		
		//클라이언트에게 메시지 보내기 ==> 소켓의 OutputStream객체를 이용해서 전송한다.
		OutputStream out = socket.getOutputStream(); //소켓의 OutputStream객체를 구한다.
		DataOutputStream dout = new DataOutputStream(out);//보조스트림
		
		//메시지전송 ==> 출력 명령으로 전송한다.
		dout.writeUTF("환영하니다. 어서오세요.");
		System.out.println("메시지를 보냈습니다.");
		
		//소켓과 연결스트림 닫기
		dout.close();
		socket.close();
		server.close();
		
	}

}

/*
  통신을 하려면 포트를 열어놔야지.
  포트는 자동으로 설정해.
  안쓰는거 찾아다가
  
  
 */ 
