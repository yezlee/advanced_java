package kr.or.ddit.member.main;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import javax.print.attribute.HashAttributeSet;

import kr.or.ddit.member.service.IMemberService;
import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.member.vo.MemberVO;


/*
	회원을 관리하는 프로그램 작성하기
	(DB시스템의 MYMEBMER테이블 이용)
	
	처리조건)
	1> 아래 메뉴의 기능을 모두 구현한다.(CRUD 구현하기)
	2> '자료추가'에서는 입력한 회원 ID가 중복되는지 여부를 검사해서 중복되면 다시 입력 받도록 한다.(pk : 회원 ID) 
	3> '자료삭제'는 회원 ID를 입력 받아 삭제한다.
	4> '자료수정'은 회원 ID를 제외한 전체 자료를 수정한다.
	5> '자료수정2' 메뉴를 선택하면
	 		1. 회원이름 수정 		2.회원전화번호 수정		3.회원주소 수정 		4. 취소
	 		메뉴를 출력하고 각 부 메뉴에 해당하는 데이터를 수정한다.
	
	C create - insert
	R read  
	U update
	D delete
	
	메뉴예시)
		--작업선택--
	1. 자료 추가 - insert
	2. 자료 삭제 - delete
	3. 자료 수정 - update
	4. 전체 자료 출력 - select
	0. 작업 끝.
	--------------
	작업 번호 >>

*/
public class MemberController {
	private Scanner sc = new Scanner(System.in);
	private IMemberService service;		// Service객체가 저장될 변수 선언
	
	//생성자
	public MemberController() {
//		service = new MemberServiceImpl();	//오류가 나는 이유는 new를 써서 그래(생성자가 프라이빗이라 다른클래스에서 못불러옴 - 프라이빗은 자기 클래스에서만 쓸수있으니까)
		/*
		 *싱글톤 패턴 설명중에-
		 *- singleton 패턴 ==> 객체가 1개만 만들어지게 하는 방법
    				 !!!!!!!!!!! (외부에서 new명령을 사용하지 못하게 한다.) !!!!!!!!!!!!!!!!!
		 */
		service = MemberServiceImpl.getInstance();
	}
	
	
	public static void main(String[] args) {
		new MemberController().start();
	}
	
	private void start() {
		while (true) {
			System.out.println("----- 작업 선택 -----");
			System.out.println("1. 자료추가");
			System.out.println("2. 자료삭제");
			System.out.println("3. 자료수정");
			System.out.println("4. 자료수정2");
			System.out.println("5. 자료수정2_선생님버전");
			System.out.println("6. 전체 자료 출력");
			System.out.println("0. 작업 끝.");
			System.out.println("------------------");
			System.out.print("작업 번호 >> ");
			int input = Integer.parseInt(sc.nextLine());
			
			switch (input) {
			case 1:
				insert();
				break;
			case 2:
				delete();
				break;
			case 3:
				update();
				break;
			case 4:
				update2();
				break;
			case 5:
				updateMember2();
				break;
			case 6:
				display();
				break;
			case 0:
				System.out.println("프로그램을 종료합니다");
				System.exit(0);
				break;

			default:
				System.out.println("메뉴에 있는 번호만 선택하세요");
				break;
			}
			
		}
		
	}
	
	//회원정보수정2 - 선생님버전
	private void updateMember2() {
		System.out.println();
		System.out.println("수정할 회원정보를 입력하세요. ");
		System.out.print("회원ID를 입력하세요 : ");
		String memId = sc.next();
		
		int count = service.getMemberCount(memId);
		if(count==0) { //없는 회원이면
				System.out.println(memId + "는(은) 없는 회원ID 입니다.");
				System.out.println("수정 작업을 종료합니다.");
				return;
		}
		
		System.out.println();
		System.out.println("수정할 항목을 선택하세요.");
		System.out.println("1. 회원 이름 수정		2. 전화번호 수정		3. 회원주소 수정 		4. 취소" );
		System.out.println("---------------------------------------------------------");
		System.out.print("수정 항목 선택 >>");
		int num = sc.nextInt();
		
		String updateField = null; //선택한 컬럼명이 저장될 변수
		String updateStr = null; //선택한 컬럼의 제목이 저장될 변수
		
		switch(num) {
			case 1: updateField = "mem_name";
					updateStr = "회원이름";
					break;
			case 2: updateField = "mem_tel";
					updateStr = "전화번호";
					break;
			case 3: updateField = "mem_addr";
					updateStr = "회원주소";
					break;
			default : return;
		}
		
		//수정할 데이터 입력받기
		System.out.println();
		sc.nextLine(); //입력버퍼 비우기
		System.out.print("새로운" + updateStr + " >> ");
		String updateData = sc.nextLine();
		
		//수정할 정보를 Map에 추가한다.
		//우선 맵객체를 만들어야지
		Map<String, String>	paramMap = new HashMap<>();
		paramMap.put("memId", memId);		
		paramMap.put("field", updateField);		
		paramMap.put("data", updateData);	
		
		int cnt = service.updateMember2(paramMap);
		
		if(cnt>0) {
			System.out.println("수정작업성공!");
		}else {
			System.out.println("수정작업실패!");
		}
		
	}


	private void update2() {
		
			
		System.out.println();
		System.out.println("----- 작업 선택 -----");
		System.out.println("1. 회원이름 수정");
		System.out.println("2. 회원전화번호 수정");
		System.out.println("3. 회원주소 수정");
		System.out.println("4. 취소");
		System.out.println("------------------");
		System.out.print("작업 번호 >> ");
		int input = Integer.parseInt(sc.nextLine());
		
		switch (input) {
		case 1:
			modify_name();
			break;
		case 2:
			modify_tel();
			break;
		case 3:
			modify_addr();
			break;
		case 4:
			start();
			break;
		}
	}


	

	private void modify_addr() {
		System.out.print("수정할 주소의 아이디를 입력하세요 : ");
		String memId = sc.nextLine();
	
		int count = service.getMemberCount(memId);
		
		if (count == 0) {
			System.out.println("없는 사용자 ID입니다");
			return;
		} 
		
		System.out.print("수정할 주소를 입력하세요 : ");
		String memAddr = sc.nextLine();
			
		// 입력된 수정할 데이터를 MemberVO객체를 생성해서 저장한다.
		MemberVO memVo = new MemberVO();
		
		memVo.setMem_id(memId);
		memVo.setMem_addr(memAddr);
		
		// Service 객체의 데이터를 수정하는 메서드 호출하기
		int cnt = service.update_addr_Member(memVo);
		System.out.println();
		if(cnt>0) {
			System.out.println("회원 정보 수정 완료!");
		}else {
			System.out.println("회원 정보 수정 실패!");
		}
		System.out.println();
		
		
	}

	private void modify_tel() {
		System.out.print("수정할 전화번호의 아이디를 입력하세요 : ");
		String memId = sc.nextLine();
	
		int count = service.getMemberCount(memId);
		
		if (count == 0) {
			System.out.println("없는 사용자 ID입니다");
			return;
		} 
		
		System.out.print("수정할 전화번호 입력하세요 : ");
		String memTel = sc.nextLine();
		
			
		// 입력된 수정할 데이터를 MemberVO객체를 생성해서 저장한다.
		MemberVO memVo = new MemberVO();
		
		memVo.setMem_id(memId);
		memVo.setMem_tel(memTel);
		
		// Service 객체의 데이터를 수정하는 메서드 호출하기
		int cnt = service.update_tel_Member(memVo);
		System.out.println();
		if(cnt>0) {
			System.out.println("회원 정보 수정 완료!");
		}else {
			System.out.println("회원 정보 수정 실패!");
		}
		System.out.println();
		
	}

	
	private void modify_name() {
		
		System.out.print("수정할 이름의 아이디를 입력하세요 : ");
		String memId = sc.nextLine();
	
		int count = service.getMemberCount(memId);
		
		if (count == 0) {
			System.out.println("없는 사용자 ID입니다");
			return;
		} 
		
		System.out.print("수정할 이름을 입력하세요 : ");
		String memName = sc.nextLine();
		
			
		// 입력된 수정할 데이터를 MemberVO객체를 생성해서 저장한다.
		MemberVO memVo = new MemberVO();
		
		memVo.setMem_id(memId);
		memVo.setMem_name(memName);
		
		// Service 객체의 데이터를 수정하는 메서드 호출하기
		int cnt = service.update_name_Member(memVo);
		System.out.println();
		if(cnt>0) {
			System.out.println("회원 정보 수정 완료!");
		}else {
			System.out.println("회원 정보 수정 실패!");
		}
		System.out.println();
		
	}


	private void display() {
	
			List<MemberVO> memList = service.getAllMemberList();
			
			System.out.println("-----------------------------------");
			System.out.println("회원ID\t회원이름\t전화번호\t\t주소");
			System.out.println("-----------------------------------");
			if(memList == null || memList.size()==0) {
				System.out.println("회원 정보가 하나도 없습니다.");
			}else {
				
				for(MemberVO memVo : memList) {
					
					System.out.print(memVo.getMem_id()+"\t");
					System.out.print(memVo.getMem_name()+"\t");
					System.out.print(memVo.getMem_tel()+"\t");
					System.out.println(memVo.getMem_addr());
					
				}
				
				System.out.println("-----------------------------------");
				
				System.out.println("아무키나 누르면 돌아갑니다");
				
				String a = sc.nextLine();
			}
		
	}

	private void update() {
	

			System.out.print("수정할 아이디를 입력하세요 : ");
			String memId = sc.nextLine();
		
			int count = service.getMemberCount(memId);
			
			if (count == 0) {
				System.out.println("없는 사용자 ID입니다");
				return;
			} 
			
			System.out.print("수정할 이름을 입력하세요 : ");
			String memName = sc.nextLine();
			
			System.out.print("수정할 전화번호 입력하세요 : ");
			String memTel = sc.nextLine();
			
			System.out.print("수정할 주소를 입력하세요 : ");
			String memAddr = sc.nextLine();
		
			// 입력된 수정할 데이터를 MemberVO객체를 생성해서 저장한다.
			MemberVO memVo = new MemberVO();
			
			memVo.setMem_id(memId);
			memVo.setMem_name(memName);
			memVo.setMem_tel(memTel);
			memVo.setMem_addr(memAddr);
			
			
			// Service 객체의 데이터를 수정하는 메서드 호출하기
			int cnt = service.updateMember(memVo);
			System.out.println();
			if(cnt>0) {
				System.out.println("회원 정보 수정 완료!");
			}else {
				System.out.println("회원 정보 수정 실패!");
			}
			System.out.println();
			
		
		
	}

	private void delete() {
		


			System.out.print("삭제할 아이디를 입력하세요 : ");
			String memId = sc.nextLine();
			
			int count = service.getMemberCount(memId);
			
			
			if (count == 0) {
				System.out.println("없는 사용자 ID입니다");
				return;
			} 
			
			int cnt = service.deleteMember(memId);
			
			System.out.println();
			if(cnt>0) {
				System.out.println("회원 삭제 완료!");
			}else {
				System.out.println("회원 삭제 실패!");
			}
			System.out.println();
			
		
		
	}

	private void insert() {
		
			
			
			
			String memId = "";
			while (true) {
				System.out.print("아이디를 입력하세요 : ");
				memId = sc.nextLine();

				if (memId.equals("")) {
					System.out.println("빈 내용은 입력할수 없습니다.");
					System.out.println("다시 입력하세요");
					continue;
				}

				int count = service.getMemberCount(memId);

			

		

			
				if (count == 1) {
					System.out.println("이미 등록된 이름입니다.");
					System.out.println("다시 입력하세요");
				} else {
					break;
				}

			}
			
			System.out.print("이름을 입력하세요 : ");
			String memName = sc.nextLine();
			
			System.out.print("전화번호 입력하세요 : ");
			String memTel = sc.nextLine();
			
			System.out.print("주소를 입력하세요 : ");
			String memAddr = sc.nextLine();
			
			//입력한 회원 정보를 저장할 MemberVO객체 생성
			MemberVO memVo = new MemberVO();
			
			// 입력한 데이터를 MemberVo 객체에 저장한다.
			memVo.setMem_id(memId);
			memVo.setMem_name(memName);
			memVo.setMem_tel(memTel);
			memVo.setMem_addr(memAddr);
			
			// Service객체에서 회원 정보를 추가하는 메서드 호출하기
			int cnt = service.insertMember(memVo);
			
			System.out.println();
			if(cnt>0) {
				System.out.println("회원 가입 성공!");
			}else {
				System.out.println("회원 가입 실패!");
			}
			System.out.println();
			
		
		}
	
}
