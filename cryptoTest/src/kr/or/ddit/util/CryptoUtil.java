package kr.or.ddit.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


//이건 단방향방식.
//단방향 암호화를 처리하는 클래스
public class CryptoUtil {
	
	//문자열을 MD5방식으로 암호화한다.(자리수 : 32bytes)
	public String md5(String msg) throws NoSuchAlgorithmException {
		//단방향
		//암호화 방식을 지정한 객체 생성
		MessageDigest md = MessageDigest.getInstance("MD5");		
		md.update(msg.getBytes()); //암호화 하기 - 암호화는 요 두줄. 그리고 아래줄로 해서 불러와
		
		return byteToHexString(md.digest()); //암호화된 데이터를 가져와 16진수로 변환해서 반환 
	}
	
	
	//MD5방식이 문제가 많다했잖아 그래서 다른방식 !
	//문자열을 SHA-256방식으로 암호화한다. (자리수 : 64 byte)
	public String sha256(String msg) throws NoSuchAlgorithmException {
		MessageDigest sha256 = MessageDigest.getInstance("SHA-256");
		sha256.update(msg.getBytes());
		return byteToHexString(sha256.digest());
	}
	
	
	//256보다 한단계 업그레이드 된거
	//문자열을 SHA-512방식으로 암호화한다. (자리수 : 128byte)
	public String sha512(String msg) throws NoSuchAlgorithmException {
		MessageDigest sha512 = MessageDigest.getInstance("SHA-512");
		sha512.update(msg.getBytes());
		return byteToHexString(sha512.digest());
	}
	
	
	
	
	// 1111 1111  - 이게 1바이트(비트가 8개 있어서 바이트가 됨) 
	//
	// 1111 ==> 15 ==> 
	// 8421 ==> 이걸 10진수로 한게 15?
	//16진수는 0에서 15까지 
	//근데 16진수는 112라는 거를 1,12 일수도 있고 1,1,2일수도 11,2일수도 있어서 
	//1 2 3 4 5 6 7 8 9 다음에 10을 쓰지않고 a부터 f 까지 쓴다.
	
	//16진수 한자리는 2진수 4자리와 같고
	//8진수 한자리는 2진수 3자리와 같다.
	
	//8진수는 0에서 7까지 수를 사용할수 있음
	//그래서 2진수는 0이랑 1로만
	
	//숫자앞에 0이랑 x를 붙이면 16진수라는 뜻   => 0x111
	//숫자앞에 0을 붙이면 8진수라는 뜻 => 0112
	//숫자만 그냥 쓰면 10진수라는 뜻 => 1111
	
	
	//그래서 0xff는 f가 15라는 뜻이고 15는 1111이야?? 그래서 저건 1111 1111 이라는거.............
	
	
	
	
	// byte배열의 데이터를 16진수 값으로 변환하는 메소드
	public String byteToHexString(byte[] data) {
		StringBuilder sb = new StringBuilder();
		for(byte b : data) {
			//(b & 0xff) + 0x100 ==> 16진수 2자리 만들려고 하는거
			// a & 0xff ==> 0x0a ==> 0xa + 0x100 ==> 0x10a ==> toHexString이 저걸 이렇게 만들어줌 "10a" ==> subString까지 합치면 이게 "0a"가 된다............ 
			sb.append(Integer.toHexString((b & 0xff) + 0x100).substring(1));
		}
		
		return sb.toString();
	}
}
