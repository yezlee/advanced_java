package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Scanner;

/*
  	문제> 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고
  		Map을 이용해서 전화번호 정보를 관리하는 프로그램을 작성하시오.
  		이 프로그램에서는 전화번호를 등록, 수정, 삭제, 검색, 전체 출력하는 기능이 있다.
  		(Map의 구조는 key값으로 '이름'을 사용하고, value값으로 'phone클래스의 인스턴스'로 한다.
  		
  		- 삭제, 검색 기능은 '이름'을 입력받아 처리한다.
  		
  		
	- 추가 조건>
		1) 메뉴에 '6. 전화번호 저장' 메뉴를 추가하고 전화번호를 저장하는 기능을 구현한다.
			(저장 파일명 : phoneData.dat)
		2) 프로그램이 시작될 때 저장된 파일이 있으면 그 데이터를 읽어와 Map에 저장한다.
		3) 프로그램을 종료할 때 전화번호 데이터가 변경되거나 추가 또는 삭제되었으면 변경된 데이터를 저장한 후에 종료되도록 한다.
			(즉, 데이터가 변경되었는데 저장이 되지않은 상태이면 저장한다.)
  		
  		
  	-------------------------------------------------------------
  	
  	실행예시)
  	------------------
  	다음 메뉴를 선택하세요.
  	1. 전화번호 등록
  	2. 전화번호 수정
  	3. 전화번호 삭제
  	4. 전화번호 검색
  	5. 전화번호 전체출력
  	0. 프로그램 종료
  	------------------
  	번호 입력 >> 1
  	
  	새롭게 등록할 전화번호 정보를 입력하세요.
  	
  	이름 >> 홍길동
  	전화번호 >> 010-1234-5678
  	주소 >> 대전
  	
  	'홍길동' 전화번호 등록 완료!!
  	
  	------------------
  	다음 메뉴를 선택하세요.
  	1. 전화번호 등록
  	2. 전화번호 수정
  	3. 전화번호 삭제
  	4. 전화번호 검색
  	5. 전화번호 전체출력
  	0. 프로그램 종료
  	------------------
  	번호 입력 >> 1
  	
  	새롭게 등록할 전화번호 정보를 입력하세요.
  	
  	이름 >> 홍길동
  		 
  	'홍길동'은 이미 등록된 사람입니다.
  	
  	------------------
  	다음 메뉴를 선택하세요.
  	1. 전화번호 등록
  	2. 전화번호 수정
  	3. 전화번호 삭제
  	4. 전화번호 검색
  	5. 전화번호 전체출력
  	0. 프로그램 종료
  	------------------
  	번호 입력 >> 5
  	
  	---------------------------------------
  	번호	  이름              전화번호		             주소
     1     홍길동    010-1234-5678     대전시 중구 대흥동  	
     .
     .
     .
     
  	---------------------------------------
  	출력완료.
  	
  	------------------
  	다음 메뉴를 선택하세요.
  	1. 전화번호 등록
  	2. 전화번호 수정
  	3. 전화번호 삭제
  	4. 전화번호 검색
  	5. 전화번호 전체출력
  	0. 프로그램 종료
  	------------------
  	번호 입력 >> 0
  	
  	프로그램을 종료합니다.
  	
  	 	
 */

public class PhoneBookTest {
	
	HashMap<String, Phone> map = new HashMap<>(); 
	static Scanner scan = new Scanner(System.in);	

	//데이터가 변경되었는지 여부를 나타내는 변수선언
	boolean notice; //boolean은 기본형이 false라서 초기화 안해줘도됨
	
	public PhoneBookTest() {
		loadnumber();
	}
	
	public static void main(String[] args) {
		new PhoneBookTest().start(); //new를 붙여줘서 생성자를 만드는걸 해버리면 static안붙여줘도 됨.
		
	}

	private int start() {
		
		System.out.println("------------------");
		System.out.println("다음 메뉴를 선택하세요.");
		System.out.println("1. 전화번호 등록");
		System.out.println("2. 전화번호 수정");
		System.out.println("3. 전화번호 삭제");
		System.out.println("4. 전화번호 검색");
		System.out.println("5. 전화번호 전체출력");
		System.out.println("6. 전화번호 저장");
		System.out.println("0. 프로그램 종료");
		System.out.println("------------------");
		System.out.print("번호 입력 >>");
		
		int input = scan.nextInt();
		scan.nextLine();
		
		switch(input) {
		case 1: 			//등록
			ph_insert();
		case 2: 			//수정
			ph_modify();
		case 3: 			//삭제
			ph_delete();
		case 4: 			//검색
			ph_search();
		case 5: 			//목록
			ph_list();
		case 6: 			//저장
			ph_save();
		case 0: 			
			//종료
			ph_exit();
			
		}
		return input;
	}
	
  	

	private void ph_exit() {

		if(notice = true) {
			ph_save(); 
		}
		System.out.println("변경된 내용을 저장합니다.");
		System.exit(0);
	}

	//파일에 저장된 전화번호 정보를 읽어오는 메소드
	private void loadnumber() {
		// 입력용 스트림 객체 생성

		try {
			FileInputStream fin = new FileInputStream("d:/D_Other/phoneData.dat");
			BufferedInputStream bin = new BufferedInputStream(fin);
			ObjectInputStream ois = new ObjectInputStream(bin);
			
			/*ObjectInputStream ois = new ObjectInputStream(
					new BufferedInputStream(new FileInputStream("d:/D_Other/phoneData.dat")));*/
			
			Object obj;
						
			try {
				while ((obj = ois.readObject()) != null) {
					Phone ph = (Phone) obj;
					map.put(ph.getName(), new Phone(ph.getName(), ph.getPhonenum(), ph.getAddr()));
				}

			} catch (EOFException e) {
				System.out.println("저장된 전화번호 불러오기 성공");
				System.out.println();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} finally {
				ois.close();
			}

		} catch (IOException e) {
			// TODO: handle exception
		}
	}

	//전화번호를 정보를 파일에 저장하는 메소드
	private void ph_save() {
		
		try {
			//객체 저장하기
			FileOutputStream fout = new FileOutputStream("d:/D_Other/phoneData.dat");
			BufferedOutputStream bout = new BufferedOutputStream(fout);
			ObjectOutputStream oos = new ObjectOutputStream(bout);
			
			System.out.println("전화번호 저장 시작");
			
//			oos.writeObject(map); // 이렇게 맵을 (as객체) 통째로 파일에 저장 - 선생님답
			
			
			
			for(String key : map.keySet()) { //맵에 있는 키(name)를 가져와서 그걸 key에 넣어줌 
				oos.writeObject(map.get(key)); //key를 가져와서 writeObject해줌
			}
			System.out.println("전화번호 저장 끝");
			
			oos.close(); //스트림닫기
		
			//저장하는 작업 끝
									
			
			notice = false; // 저장완료되었단 뜻으로 false해줌. 안그러면 종료할때마다 계속 저장을 또 할테니까. 그럼 속도가 느려짐.
			
		} catch (IOException e) {
			// TODO: handle exception
		}
		
	}


	private int ph_list() {
		System.out.println("====================================================");
		System.out.println("번호\t 이름\t 전화번호\t\t주소");
		int count = 0;
		
		for(String key : map.keySet()) {
			Phone value = map.get(key);
			count++;
			System.out.println(count + "\t" + value + "\t");
			
			
		}
		return start();
		
	}


	private int ph_search() {
		System.out.println("이름 : ");
		String name = scan.next();
		System.out.println("--------------------------");
		System.out.println("번호\t 이름\t 전화번호\t\t주소");
		System.out.println(map.get(name));
		
		return start();
	}


	private int ph_delete() {
		System.out.println("이름 : ");
		String name = scan.next();	
		map.remove(name);
		System.out.println("삭제 되었습니다.");
			
		notice = true; //삭제가 되었다는 표시
		return start();
	}


	private int ph_modify() {
		System.out.println("수정할 이름을 입력하세요.");
		String name = scan.next();	
		if(map.containsKey(name)){
			System.out.println("수정할 전화번호 >>");
			String phone_num = scan.next();
			scan.nextLine();
			
			System.out.print("주소>>");
			String addr = scan.nextLine();
			map.put(name, new Phone(name, phone_num, addr));
		}
		System.out.println("수정 완료!");
		
		notice = true; //수정이 되었다는 표시
		return start();
	}


	public int ph_insert(){
		
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		
		System.out.print("이름 >> ");
		String name = scan.next();
		
		System.out.print("전화번호 >> ");
		String phonenum = scan.next();
		scan.nextLine();
		
		System.out.print("주소 >> ");
		String addr = scan.nextLine();
		
//		Phone info = new Phone(name, phonenum, addr); //위에서 입력받은걸 여기에 모아서 생성자에 넣은거. 이것도 생성자
		
		for(String key : map.keySet()) {
			if(name.equals(key)) {
				System.out.println("'" + name + "'" + "이미 등록된 이름 입니다.");
			}
		}
		
		System.out.println("등록이 완료되었습니다.");
		
		map.put(name, new Phone(name, phonenum, addr)); //키값. 밸류는
		
		notice = true; //등록이 되었다는 표시
		return start();
	}


}

class Phone implements Serializable{
	
	private static final long serialVersionUID = -4845152742960394503L;

	//생성자. 위에서 Phone info = new Phone(name, phonenum, addr); 여기다 넣기위해서
	//그리고 이거 만드는 방법(ListSortTest2 에서 했음)
	//아래처럼 생성자 만드는 단축키 alt+shift+s -> Generate Constructor Using Fields
	public Phone(String name, String phonenum, String addr) {
		super();
		this.name = name;
		this.phonenum = phonenum;
		this.addr = addr;
	}

	
	private String name;
	private String phonenum; 
	private String addr;
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhonenum() {
		return phonenum;
	}
	public void setPhonenum(String phonenum) {
		this.phonenum = phonenum;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	@Override
	public String toString() {
		return name +"\t" + phonenum +"\t" + addr;
	}
	
	
	
}






