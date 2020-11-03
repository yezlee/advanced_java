package kr.or.ddit.basic; //1102 -6

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class FileTest03 {

	public static void main(String[] args) {
		FileTest03 test = new FileTest03();// 이건 객체를 생성
		//FileTest03 이 타입의 test라는 이름의 객체를 생성하겠다. new한 FileTest03()메소드를 만들어서
		//저장공간안에 test라는 객체가 만들어지고
		//test변수에는 저장공간 안에 있는 test의 주소를 저장한다. 
		//test는 변수인데 주소값을 갖고 있는 변수이다. 
		
		//이해를 돕기위해
		//FileTest03안에 멤버(변수나 메소드)를 사용하기위해서 new FileTest03();를 해줘야함.
		//불러오는 방법은 test.멤버이름 <= 이렇게
		
		//클래스안에 있는 메소드를 쓰기위해서 클래스 이름의 생성자를 만들어줘야함.
		//객체를 만들기위해서 생성자가 필요
		
		File viewFile = new File("d:/D_Other"); //보고싶은 디렉터리 설정
		
		test.displayFileList(viewFile);
	
	}

	// 디렉토리(폴더)를 매개값으로 받아서 해당 디렉토리(폴더)에 이쓴ㄴ
	// 모든 파일과 디렉토리(폴더) 목록을 출력하는 메소드
	public void displayFileList(File dir) {
		if(!dir.isDirectory()) {
			System.out.println("디렉토리(폴더)만 가능합니다.");
			return;
		}
		
		System.out.println("[" + dir.getAbsolutePath() + "] 디렉토리 내용");
		System.out.println();
		
		// 해당 디렉토리 안에 있는 모든 파일과 디렉토리 목록을 구해 온다.
		File[] files = dir.listFiles();
//		String[] filestrs = dir.list(); //전체목록을 가져올때 둘중에 하나 선택
		
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd a hh:mm");
		
		// 가져온 파일과 디렉토리 목록 개수만큼 반복처리하기
		for(int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			String attr = ""; 	//파일의 속성(읽기, 쓰기, 히든, 디렉토리를 구분)
			String size = "";   //파일의 크기
								//원래는 숫자인데 문자형으로
			
			if(files[i].isDirectory()) {
				attr = "<DIR>";
			}else {
				size = files[i].length() + "";
				attr = files[i].canRead() ? "R" : "";
				attr += files[i].canWrite() ? "W" : "";
				attr += files[i].isHidden() ? "H" : "";
			}
			
			//%5s 자릿수를 5자리 유지하겠다. 이랬는데 실제 데이터가 10글자면 그냥 10글자 나옴. 틀이 깨짐.
			System.out.printf("%s %5s %12s %s\n", 
					df.format(new Date(files[i].lastModified())), //이게 날짜
					attr, size, fileName
					);
		}
		
	}
}
