package kr.or.ddit.basic;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BufferedIOTest01 {

	public static void main(String[] args) {
		// 입출력의 성능 향상을 위해서 Buffered스트림을 사용한다.
		try {
			FileOutputStream fout = new FileOutputStream("d:/D_Other/bufferTest.txt");
			
			//버퍼의 크기가 5인 Buffered스트림 객체 생성
			//버퍼의 크기를 지정하지 않으면 기본크기인 8192byte로 버퍼의 크기가 정해진다.
			BufferedOutputStream bout = new BufferedOutputStream(fout, 5); //버퍼를 사용하는 outputstream
			
			for(char i = '1'; i <= '9'; i++) {
				bout.write(i); //i는 버퍼에 가득 찰때까지 하고 출력해. 근데 지금 크기가 5야. 그래서 12345만 나오고 6789는 그냥 버퍼에 남아있고 작업끝.
				//클로즈도 없고 플러시도 없어서.
			}
			bout.flush(); //이렇게 플러시해주면 버퍼에 남아있는 애들 다 출력
			
			fout.close(); //안으로 들어간 스트림부터 먼저 닫고 바깥에 있는 bout를 닫는게 순서상 맞음 근데 지금은 굳이 안해줘도돼.
			bout.close(); //이렇게 클로즈해도 남아있는 애들까지 다 나와 , 보조스트림을 닫으면 보조스트림에서 사용한 기반스트림도 자동으로 클로즈됨
			//클로즈를 하더라도 플러시 해주고 하는게 안전함
			
			System.out.println("작업 끝!");
			
			
			} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
