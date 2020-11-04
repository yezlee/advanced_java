package kr.or.ddit.basic;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class DataIOTest {

	public static void main(String[] args) {
		try {
			FileOutputStream fout = new FileOutputStream("d:/D_other/test.dat");
			
			//자료형 단위를 출력한 보조스트림 객체(DataoutputStream) 생성
			DataOutputStream dos = new DataOutputStream(fout);
			
			dos.writeInt(200);              //정수형으로 출력
			dos.writeFloat(123.45f);		//실수형(float)으로 출력
			dos.writeBoolean(true);		//논리형으로 출력
			dos.writeUTF("ABcd123가나");		//문자열 형식으로 출력
			
			System.out.println("출력 완료");
			dos.close(); 
			
			//-----------------------------------------
			//출력한 자료 읽어오기
			FileInputStream fin = new FileInputStream("d:/D_other/test.dat");
			DataInputStream din = new DataInputStream(fin);
			
			System.out.println("정수형 : " + din.readInt());
			System.out.println("정수형 : " + din.readFloat());
			System.out.println("정수형 : " + din.readBoolean());
			System.out.println("정수형 : " + din.readUTF());
			
			System.out.println("읽기 작업 완료");
				
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
