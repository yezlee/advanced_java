package kr.or.ddit.basic;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressTest {

	public static void main(String[] args) throws UnknownHostException {
		// InetAddress 클래스 ==> IP주소를 다루기 위한 클래스
		
		// www.naver.com의 IP정보 가져오기
		
		InetAddress naverIp = InetAddress.getByName("www.naver.com");

		System.out.println("HostName : " + naverIp.getHostName());
		System.out.println("HostAddress : " + naverIp.getHostAddress());
		System.out.println();
		
		
		//자신의 컴퓨터의 IP저오 가져오기
		InetAddress localIp = InetAddress.getLocalHost();
		
		System.out.println("내 컴의 HostName : " + localIp.getHostName());
		System.out.println("내 컴의 HostAddress : " + localIp.getHostAddress());
		System.out.println();
		
		//IP주소가 여러개인 호스트의 정보 가져오기
		InetAddress[] naver = InetAddress.getAllByName("www.google.com");
		for(InetAddress nIp : naver) {
			System.out.println(nIp.toString());
		}
		
		
	}

}
