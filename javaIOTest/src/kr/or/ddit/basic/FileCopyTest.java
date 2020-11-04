package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
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


		File file = new File("D:/D_Other/극한직업.jpg"); 
		File file_copy = new File("D:/D_Other/연습용/극한직업.jpg"); //파일이 있는지 없는지 먼저 확인해주기위해서 변수 선언 해주는게 좋아
		
		if(!file.exists()) {
			System.out.println(file.getPath() + "파일이 없습니다.");
			System.out.println("복사 작업을 중지합니다.");
			return;
		}
			
		
		try {
			//복사할 파일 스트림 객체 생성(원본파일용)
			FileInputStream fin = new FileInputStream("D:/D_Other/극한직업.jpg"); 
			//()안에 있는 파일을 읽어라.  FileInputStream이 읽어라 라고 컴퓨터한테 명령을 내가 내리는거.
			// FileInputStream = 입력. 파일을입력. () = 어디에서?
			
			
			//+추가. 보조스트림사용
			BufferedInputStream bin = new BufferedInputStream(fin); //보조니까 위에있는 애거를 여기에 넣는거지
			
			
			//복사될 파일 스트림 객체 생성(저장된 파일용)
			FileOutputStream fout = new FileOutputStream("D:/D_Other/연습용/극한직업.jpg");

			//+추가. 보조스트림사용
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			
			
			int filebyte = 0;
			
			System.out.println("복사 작업 시작!");
			while( (filebyte = fin.read()) != -1) {
				fout.write(filebyte);
			}
			fout.flush(); // 파일작업을 하고나면 확실하게 하기위해서 플러시 해주는게 좋아
			

			//사용했던 스트림 닫기
			fin.close(); 
			fout.close();
			
			System.out.println("복사 작업 완료!");
			
		} catch (IOException e) {
			// TODO: handle exception
		}

		
		
		
	}

}


