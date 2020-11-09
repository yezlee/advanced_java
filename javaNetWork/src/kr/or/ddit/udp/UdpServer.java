package kr.or.ddit.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/*
   UDP방식 : 비연결 지향, 신뢰성이 없다(TCP랑 비교해서), 데이터가 순서대로 도착한다는 보장이 없다.
    	       그렇지만 TCP보다 속도가 빠르다. 
    	       전체 데이터를 한번에 보내는게 아니고 그걸 잘라. 자른걸 packet이라 함. 이 안에는 많은 정보가 들어있음.
    	       그렇게 쪼갠 데이터를 인터넷에 뿌려버려. 번호가 있지만 순서대로 가지않고 어떤게 먼저 도착할지 모름.
    	       프로그램 끝날때까지 파일이 안도착할수도 있음 (유실된 데이터라함)
    	       스타크래프트 처럼 피시방에서만 할수있는거? 인터넷 연결 안하고. 그건 UDP방식
   	       TCP는 0번데이터를 보내면 잘 받았고 신호가고 그러면 다시 1번데이터 보내고. 확인하는 작업이 필요해서 속도가 느려짐.
   	       
   DatagramSocket객체와 DatagramPacket객체를 이용해서 통신한다.
   - DatagramSocket : 데이터의 송수신과 관련된 작업을 수행한다. (우체부)
   - DatagramPacket : 주고 받는 데이터와 관련된 작업을 수행한다. (소포)
   			==> 수신용 생성자와 송신용 생성자를 따로 제공해준다.
   			
   TCP의 경우에는 스트림을 이용해서 송수신하지만
   UDP의 경우에는 데이터그램을 이용해서 송수신한다.
   
 */
public class UdpServer {

	public static void main(String[] args) {
		try {
			// 통신할 포트번호를 지정하여 UDP용 소켓을 생성한다.
			DatagramSocket socket = new DatagramSocket(8888);
			
			// 수신용 패킷변수와 송신용 패킷변수 선언
			DatagramPacket inPacket, outpacket;
			System.out.println("서버 실행중...");
			
			while(true) {
				//데이터가 저장된 byte형 배열 선언
				byte[] bMsg = new byte[512];
				
				//수신용 패킷 객체 생성
				// 	==> new DatagramPacket(데이터가 저장될 byte형 배열, 배열의 길이);
				inPacket = new DatagramPacket(bMsg, bMsg.length); 
				
				//데이터를 수신한다. ==> Socket의 receive()메소드를 이용한다.
				//수신된 데이터의 패킷정보는 지정된 패킷변수에 저장된다.
				socket.receive(inPacket); //데이터가 올때까지 기다린다.
				
				//수신받은 패킷에서 상대방의 주소, 포트번호등을 알 수있다.
				InetAddress address = inPacket.getAddress();
				int port = inPacket.getPort();
				
				System.out.println("상대방의 IP정보 : " + address);
				System.out.println("상대방의 port번호 : " + port);
				System.out.println();
				
				//상대방이 보낸 메시지 출력하기
				//수신용패킷.getData()   ==> 실제 읽어온 데이터를 byte형 배열로 반환한다.
				//수신용패킷.getLength() ==> 실제 읽어온 데이터의 길이를 반환한다.
				//수신된 데이터는 수신용 패킷객체를 생성 할 때 사용했던 byte형 배열에도 저장된다.
				
				//수신된 문자열 메시지가 byte형 배열로 오기 때문에 이 byte형 배열을 다시
				//문자열로 반환해야한다.
				//형식) new String(byte형 배열, 0, 실제읽어온 길이)
//				String msg = new String(bMsg, 0, inPacket.getLength()); //방법1
				String msg = new String(inPacket.getData(), 0, inPacket.getLength()); //방법2
				
				System.out.println("상대방이 보낸 메시지 : " + msg);
				System.out.println();
				
				//--------------------------------------------------------------------------
				
				//상대방에게 메시지 보내기(수신받은 메시지 그대로 송신하는 예제)
				
				//송신할 메시지를 byte형 배열로 만든다.
				byte[] sendMsg = msg.getBytes();
				
				//송신용 패킷 객체 생성- 송신용엔 4개가 들어감
				//	==> new DatagramPacket(전송할 데이터가 저장된 byte형 배열, 
				//						      전송할 데이터의 길이(배열의 길이), 상대방 주소정보, 포트번호) 
				outpacket = new DatagramPacket(sendMsg, sendMsg.length, address, port);
				
				//송신하기 ==> Socket의 send()메소드를 이용한다.
				socket.send(outpacket);
				System.out.println("전송 완료");
				
				
				
			
				
				
				
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

}
