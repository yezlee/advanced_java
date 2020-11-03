package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FileIOTest02 {

	public static void main(String[] args) {
		//앞에서 FileInputStream객체를 이용한 파일 내용 읽기만 했는데 이번엔 출력!
		
		try {
			File file = new File("d:/D_Other/out.txt"); //없는 파일은 만들어주고 있으면 덮어씀
			
			//출력용 파일 스트림 객체 생성
			/*FileOutputStream fout = new FileOutputStream(file);*/
			//위에거나 아래거나 똑같
			FileOutputStream fout = new FileOutputStream("d:/D_Other/out.txt");
			
			for(char ch = 'A'; ch <= 'Z'; ch++) {
				fout.write(ch); //출력스트림을 이용해서 파일에 출력하기 
			}
			System.out.println("출력 작업 완료!");
			
			fout.close(); //스트림 닫기
			
			
			//==================================================
			// 저장된 파일 내용을 읽어와 화면에 출력하기
			System.out.println();
			System.out.println("읽어온 파일 내용---");
			
			FileInputStream fin = new FileInputStream(file);
			
			int c;
			while(true) { //앞이랑 다른 방법으로 해보기
				c = fin.read();
				if(c == -1) {
					break;
				}
				System.out.print((char) c);
			}
			
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
