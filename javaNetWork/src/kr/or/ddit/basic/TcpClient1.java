package kr.or.ddit.basic;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class TcpClient1 {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// 현재 자신 컴퓨터를 나타내는 방법
		// 1) 원래의 IP 주소 :  예 ) 192.168.42.2   -이거나
		// 2) 지정된 IP 주소 : 127.0.0.1			  -이거나	
		// 3) 원래의 컴퓨터 이름 : 예) SEM
		// 4) 지정된 컴퓨터 이름 : localhost         -이거중에 암거나 아래에 적음됨
		
		String serverIp = "localhost";
		//서버의 아이피를 적어주는거

		System.out.println(serverIp + "서버에 연결중입니다.");
		
		//서버의 IP주소와 Port번호를 지정하여 Socket객체를 생성한다.
		//Socket객체는 생성이 완료되면 해당 서버로 요청신호를 자동으로 보낸다.
		Socket socket = new Socket(serverIp, 7777);
		//이 소켓에는 클라이언트에 대한 소켓이 들어있다?
		
		//이 부분부터는 서버와 연결이 완료된 이후의 처리 내용을 기술하면 된다.
		System.out.println("서버에 연결되었습니다.");
		System.out.println();
		
		//서버에서 보내온 메시지를 받아서 화면에 출력하기
		
		//Socket의 InputStream객체생성
		InputStream is = socket.getInputStream();
		DataInputStream dis = new DataInputStream(is);
		
		//서버가 보낸 자료를 읽어와서 출력한다.
		System.out.println("서버가 보낸 메시지 : " + dis.readUTF());
		System.out.println();
		
		System.out.println("연결을 종료합니다.");
		
		//스트림과 소켓 닫기
		dis.close();
		socket.close();
		
		
	}

}
