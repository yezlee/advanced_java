package kr.or.ddit.basic;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class ByteArrayIOTest01 {

	public static void main(String[] args) {
		//byte배열 데이터를 입력 스트림으로 읽어서 출력 스트림으로 출력하는 예제
		
		byte[] inSrc = {0,1,2,3,4,5,6,7,8,9};
		byte[] outSrc = null;
		
		//입력용 스트림 객체 생성
		ByteArrayInputStream input = new ByteArrayInputStream(inSrc);
		
		//출력용 스트림 객체 생성
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		
		int data; //읽어온 자료가 저장될 변수		
		
		// read()메소드는 ==> 더 이상 읽어올 자료가 없으면 -1을 반환한다.
		while( ( data = input.read() ) != -1 ) { //코딩하는 양을 줄이기 위해 이렇게 씀 항상 그런건 아니고 
			//읽어온 데이터를 사용하여 처리하는 내용을 기술한다.
			
			output.write(data); // 출력용 스트림을 이용하여 읽어온 데이터(data)를 출력한다.
			
		}   // 이러면 입력과 출력이 다 끝남
		
		//출력된 스트림 값을 배열로 반환하기
		outSrc = output.toByteArray(); //출력용 스트림에 있던걸 바이트로 변환해라
		
		try {
			//사용했던 자원(스트림 객체)을 반납한다.
			input.close();
			output.close();
		} catch (IOException e) {
		}
		
		System.out.println("inSrc => " + Arrays.toString(inSrc));
		System.out.println("outSrc => " + Arrays.toString(outSrc));
		
		
		
	}

}
