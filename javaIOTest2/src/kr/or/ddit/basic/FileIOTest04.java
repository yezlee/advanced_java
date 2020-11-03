package kr.or.ddit.basic;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class FileIOTest04 {
	//콘솔에서 사용자가 입력한 내용을 그대로 파일에 저장하기 - (콘솔로 입력했다 = 키보드로 출력했다)
	//콘솔은 어떤 장치를 얘기함. 컴퓨터 시스템내에서 가장 기본적인 입출력장치
	//입출력 장치를 한데 묶어서 콘솔이라고도 함
	//가장 기본적인 입력장치는 키보드, 출력은 모니터
	//cmd창을 자바에선 콘솔창
	
	public static void main(String[] args) {
	
		try {
			//System.out ==> 이게 콘솔(표준입출력장치) 출력장치 ==> 바이트기반 출력용 스트림
			//System.in  ==> 콘솔 입력장치(스캐너쓸때) 		 ==> 바이트 기반 입력용 스트림
			
			//바이트 기반의 스트림을 문자 기반의 스트림으로 변환해주는 스트림 객체 생성
			//콘솔로 입력한 데이터를 가져오기 위한 스트림 객체
			InputStreamReader isr = new InputStreamReader(System.in); //괄호속에 있는 바이트 기반의 스트링을 문자기반의 스트링으로 바꿔주는거야
			
			//파일로 저장하는 문자기반 스트림 객체 생성
			FileWriter fw = new FileWriter("d:/D_Other/testChar.txt");
			
			System.out.println("아무 내용이나 입력하세요. (입력의 끝은 Ctrl + Z키 입니다.)");
			
			int c;
			while((c = isr.read()) != -1) {
				fw.write(c); //콘솔로 입력한 값을 파일로 출력한다.
			}
			
			isr.close();
			fw.close();
			
			
			
			} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
