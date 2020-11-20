package kr.or.ddit.room.main;

import java.util.Scanner;

import kr.or.ddit.room.service.IRoomService;
import kr.or.ddit.room.service.RoomServiceImpl;
import kr.or.ddit.room.vo.RoomVo;

public class RoomController {
	
	private Scanner scan = new Scanner(System.in);
	private IRoomService service; // Service객체가 저장될 변수 선언
	RoomVo roomVo = new RoomVo();
	
	//생성자
	public RoomController() {
		service = RoomServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new RoomController().start();
	}

	private void start() {
		System.out.println();
		System.out.println("*********************************************");
		System.out.println("      호텔문을 열었습니다. 어서오십시요.");
		System.out.println("*********************************************");
		System.out.println();
		
		while(true) {
			int choice = displayMenu();
			switch(choice) {
				case 1 :   // 체크인
					checkIn();
					break;
				case 2 :   // 체크아웃
					checkOut();
					break;
				case 3 :   // 객실상태
					showRoom();
					break;
				case 4 :   // 업무종료
					System.out.println("*********************************************");
					System.out.println("       호텔문을 닫았습니다.");
					System.out.println("*********************************************");
					return;
				default : 
					System.out.println("작업번호를 잘못 입력했습니다. 다시 선택하세요.");
			}
		}
		
	}
	
	
		// 체크인을 처리하는 메서드
		private void checkIn() {
			System.out.println();
			System.out.println("------------------------------------");
			System.out.println("    체크인 작업");
			System.out.println("------------------------------------");
			System.out.println(" * 201~209 : 싱글룸");
			System.out.println(" * 301~309 : 더블룸");
			System.out.println(" * 401~409 : 스위트룸");
			System.out.println("------------------------------------");
			System.out.print(" 방 번호 입력 >> ");
			int roomNum = scan.nextInt();
			
			
		// 입력한 방번호가 있는지 검사
		int cnt = service.checkRoomAval(roomNum);
		
		if(cnt==0) {
			System.out.println(roomNum + "호 객실은 존재하지 않습니다.");
			return;
		}
		
		// 입력한 방번호에 사람이 있는지 없는지 검사
		int cnt2 = service.checkRoomNum(roomNum);
	
		
		
		if(cnt2==0) {
			System.out.println("누구를 체크인 하시겠습니까?");
			System.out.print("이름 입력 >> ");
			String name = scan.next();
			
			/*// 입력받은 객실의 투숙객 명단에 입력받은 이름을 저장한다.
			RoomVo.getRm_num(roomNum).setGuest_name(guest_name);*/
			
		}else {
			System.out.println(roomNum + "호 객실에는 이미 손님이 있습니다.");
			return;
		}
		
			
	
		}
	
	
	
		// 체크아웃을 처리하는 메서드
		private void checkOut() {
			System.out.println();
			System.out.println("------------------------------------");
			System.out.println("    체크아웃 작업");
			System.out.println("------------------------------------");
			System.out.println("체크 아웃할 방 번호를 입력하세요.");
			System.out.print(" 방 번호 입력 >> ");
			int roomNum = scan.nextInt();
			
		// 입력한 방번호가 있는지 검사
			
			
			
			
		}
		
		
		// 객실 상태를 출력하는 메서드
		private void showRoom() {
			System.out.println();
			
			System.out.println();
			System.out.println("-----------------------------");
			System.out.println("        현재 객실 상태");
			System.out.println("-----------------------------");
			System.out.println("방번호\t 방종류\t 투숙객 이름");
			System.out.println("-----------------------------");
					
			
		}
		
		
		// 메뉴를 출력하고 작업번호를 입력받아 반환하는 메서드
		private int displayMenu() {
			System.out.println();
			
			System.out.println("-----------------------------------------------------------");
			System.out.println("어떤 업무를 하시겠습니까?");
			System.out.println("1. 체크인    2. 체크아웃    3. 객실상태    4. 업무종료");
			System.out.println("-----------------------------------------------------------");
			System.out.print(" 선택>> ");
			int num = scan.nextInt();
			return num;
		}
		
		
		

}
