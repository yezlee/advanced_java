package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Scanner;

/*
  	문제> 이름, 주소, 전화번호를 멤버로 갖는 Phone클래스를 만들고
  		Map을 이용해서 전화번호 정보를 관리하는 프로그램을 작성하시오.
  		이 프로그램에서는 전화번호를 등록, 수정, 삭제, 검색, 전체 출력하는 기능이 있다.
  		(Map의 구조는 key값으로 '이름'을 사용하고, value값으로 'phone클래스의 인스턴스'로 한다.
  		
  		- 삭제, 검색 기능은 '이름'을 입력받아 처리한다.
  		
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
		System.out.println("0. 프로그램 종료");
		System.out.println("------------------");
		System.out.print("번호 입력 >>");
		
		int input = scan.nextInt();
		
		switch(input) {
		case 1: //등록
			ph_insert();
		case 2:
			ph_modify();
		case 3:
			ph_delete();
		case 4:
			ph_search();
		case 5:
			ph_list();
		case 0:
			System.exit(0);
			
		}
		return input;
		
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
			
		return start();
	}


	private int ph_modify() {
		System.out.println("수정할 이름을 입력하세요.");
		String name = scan.next();	
		if(map.containsKey(name)){
			System.out.println("수정할 전화번호 >>");
			String phone_num = scan.next();
			
			System.out.print("주소>>");
			String addr = scan.next();
			map.put(name, new Phone(name, phone_num, addr));
		}
		System.out.println("수정 완료!");
		
		return start();
	}


	public int ph_insert(){
		
		System.out.println("새롭게 등록할 전화번호 정보를 입력하세요.");
		
		System.out.print("이름 >> ");
		String name = scan.next();
		
		System.out.print("전화번호 >> ");
		String phonenum = scan.next();
		
		System.out.print("주소 >> ");
		String addr = scan.next();
		
//		Phone info = new Phone(name, phonenum, addr); //위에서 입력받은걸 여기에 모아서 생성자에 넣은거. 이것도 생성자
		
		for(String key : map.keySet()) {
			if(name.equals(key)) {
				System.out.println("'" + name + "'" + "이미 등록된 이름 입니다.");
			}
		}
		
		System.out.println("등록이 완료되었습니다.");
		
		map.put(name, new Phone(name, phonenum, addr)); //키값. 밸류는 
		
		return start();
	}


}


class Phone{
	
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






