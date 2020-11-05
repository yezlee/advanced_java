package kr.or.ddit.basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class URLTest02 {

	public static void main(String[] args) throws IOException {
		// URLConnection ==> 어플리케이션과 URL간의 통신 연결을 위한 클래스
		
		// 특정 서버의 정보와 파일내용을 가져와 출력하는 예제0
		URL url = new URL("https://www.naver.com/index.html");

		// URLConnection 객체 구하기
		URLConnection urlCon = url.openConnection();
		
		// Header 정보 가져오기
		Map<String, List<String>> headerMap = urlCon.getHeaderFields();
		
		//headerMap의 key값과  value값 출력하기
		for(String headerKey : headerMap.keySet() ) {
			System.out.println(headerKey + ":" + headerMap.get(headerKey));
		}
		System.out.println("-------------------------------------");

		//해당 문서의 내용을 가져오기
		
		//방법1 ==> URLConnection객체를 이용하는 방법
		
/*		//서버에 있는 파일의 내용을 가져오기 위한 입력용 스트림 객체 생성
		InputStream is = urlCon.getInputStream();
		InputStreamReader isr = new InputStreamReader(is, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		//자료를 읽어봐 출력하기
		while(true){
			String str = br.readLine();  // 할줄씩 읽어온다.
			if(str==null) break;  		 // 읽어온 값이 null이면 반복문 탈출
			System.out.println(str);
		}
		br.close();
		
*/				

		
		//방법2 ==> URL객체의 openStream()메소드 이용하기
		InputStream is2 = url.openStream(); 
		InputStreamReader isr = new InputStreamReader(is2, "utf-8");
		BufferedReader br = new BufferedReader(isr);
		
		//자료를 읽어봐 출력하기
		while(true){
			String str = br.readLine();  // 할줄씩 읽어온다.
			if(str==null) break;  		 // 읽어온 값이 null이면 반복문 탈출
			System.out.println(str);
		}
		br.close();
		
		
	}

}
