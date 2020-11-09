package kr.or.ddit.tcp;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TcpMultiChatClient {

	
	public static void main(String[] args) {
		new TcpMultiChatClient().clientStart();

	}

	//클라이언트 시작 메소드
	private void clientStart() {
		Socket socket = null;
		try {
			String serverIp = "localhost"; //여기에 서버 ip주소 연결
			socket = new Socket(serverIp, 7777); //서버에 접속하기
			
			System.out.println("서버와 연결이 되었습니다.");
			System.out.println();
			
			// 메시지 전송용 스레드 생성 및 실행
			ClientSender sender = new ClientSender(socket);
			
			// 메시지 수신용 스레드 생성 및 실행
			ClientReceiver receiver = new ClientReceiver(socket);
			
			sender.start();
			receiver.start();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}//클라이언트 시작 메소드 끝
	
	//------------------------------------------------------
	
	//메시지 전송용 스레드
	class ClientSender extends Thread{
		private Socket socket;
		private DataInputStream dis;
		private DataOutputStream dos;
		private String name; // 접속자 이름
		
		private Scanner scan;
		
		//생성자
		public ClientSender(Socket socket) {
			this.socket = socket;
			scan = new Scanner(System.in);
			try {
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
				
				
				if(dos!=null) {
					// 처음 프로그램이 시작되면 자신의 대화명(이름)을 서버로 전송하고
					// 대화명의 중복여부를 feedback 받아서 확인한다.
					System.out.println("대화명 : ");
					String name = scan.nextLine();
					
					while(true) { //옆에 서버페이지에서 이름 넣었을때 중복이 되면 반복되게 하려고 이걸 씀
						dos.writeUTF(name);
						
						String feedback = dis.readUTF(); //대화명 중복여부를 응답으로 받는다.
						
						if("이름중복!".equals(feedback)) {
							System.out.println(name + "은 대화명이 중복됩니다.");
							System.out.println("다른 대화명을 입력하세요.");
							System.out.println("대화명 : ");
							name = scan.nextLine();
						}else {
							this.name = name;
							System.out.println(name + " 이름으로 대화방에 입장했습니다.");
							break; //반복문 탈출
						}
						
					}//while문 끝
					
				}//if문 끝
				
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}//생성자끝
		
		
		@Override
		public void run() {
			try {
				while(dos!=null) {
					//키보드로 입력한 메시지를 서버로 전송한다.
					dos.writeUTF("[" + name + "] " + scan.nextLine());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}	

		}//run() 메소드 끝
		
	}//전송용 스레드 끝
	
	
	//메시지 수신용 스레드
	class ClientReceiver extends Thread{
		private Socket socket;
		private DataInputStream dis;
		
		public ClientReceiver(Socket socket) {
			this.socket = socket;
			
			try {
				dis = new DataInputStream(socket.getInputStream());
			} catch (Exception e) {
				
			}
		} // 생성자 끝
		
		@Override
		public void run() {
			try {
				while(dis!=null) {
					//서버로부터 바은 메시지를 화면에 출력한다.
					System.out.println(dis.readUTF());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		
		
	}
	
}
