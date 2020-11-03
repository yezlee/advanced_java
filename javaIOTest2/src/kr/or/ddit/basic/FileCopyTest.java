package kr.or.ddit.basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/*
  'd:/D_Other/' 폴더에 있는 '극한직업.jpg'파일을 
  'd:/D_Other/연습용' 폴더에 '극한직업_복사본.jpg'이름으로 복사하는 프로그램을 작성하시오. 
 */
public class FileCopyTest {

	public static void main(String[] args) {


		/*File file = new File("D:/D_Other/극한직업.jpg"); 
		File file_copy = new File("D:/D_Other/연습용/극한직업.jpg");*/ 
		
		
		
		try {
			
			FileInputStream fin = new FileInputStream("D:/D_Other/극한직업.jpg"); 
			//()안에 있는 파일을 읽어라.  FileInputStream이 읽어라 라고 컴퓨터한테 명령을 내가 내리는거.
			// FileInputStream = 입력. 파일을입력. () = 어디에서?
			
			FileOutputStream fout = new FileOutputStream("D:/D_Other/연습용/극한직업.jpg");
			// 
			
			int filebyte = 0;
			
			while( (filebyte = fin.read()) != -1) {
				fout.write(filebyte);
			}
			
			System.out.println("출력 작업 완료!");

			fin.close(); 
			fout.close();
			
			
		} catch (IOException e) {
			// TODO: handle exception
		}

		
		
		
	}

}


