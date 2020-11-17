package kr.or.ddit.board2.main;

import java.util.List;
import java.util.Scanner;

import kr.or.ddit.board2.service.IJdbcBoardService;
import kr.or.ddit.board2.service.JdbcBoardServiceImpl;
import kr.or.ddit.board2.vo.JdbcBoardVO;

public class JdbcBoardController {
	private Scanner scan;
	private IJdbcBoardService service;
	
	public JdbcBoardController() {
		scan = new Scanner(System.in);
		service = JdbcBoardServiceImpl.getInstance();
	}
	
	public static void main(String[] args) {
		new JdbcBoardController().jdbcBoardStart();
	}

	// 게시판 시작 메서드
		private void jdbcBoardStart() {
			while(true) {
				int choice = displayMenu();
				
				switch(choice) {
					case 1 : insertBoard();	// 새글 작성
						break;
					case 2 :	// 게시글 보기
						break;
					case 3 :	// 검색
						break;
					case 0 :	// 작업 끝
						System.out.println("게시판 프로그램 종료...");
						return;
					default : 
						System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
				}
			}
		}
	
	// 게시판 목록을 보여주고 메뉴를 나타내명, 사용자가 입력한 메뉴 번호를 반환하는 메서드
	private int displayMenu() {
		// 전체 게시글들을 가져온다.
		List<JdbcBoardVO> boardList = service.getAllBoardList();
		
		System.out.println();
		System.out.println("---------------------------------------------------");
		System.out.println("  No	         제 목                      작성자           	조회수");
		System.out.println("---------------------------------------------------");
		
		if(boardList==null || boardList.size()==0) {
			System.out.println(" 출력할 게시글이 하나도 없습니다.");
		}else {
			for(JdbcBoardVO boardVo : boardList) {
				System.out.println(boardVo.getBoard_no() + "    " 
							+ boardVo.getBoard_title()  + "    "
							+ boardVo.getBoard_writer() + "    "
							+ boardVo.getBoard_cnt()) ;
			}
		}
		
		System.out.println("---------------------------------------------------");
		System.out.println("메뉴 : 1.새글작성    2.게시글보기    3.검색    0.작업끝.");
		System.out.print("작업 선택 >> ");
		return scan.nextInt();
	}

	// 새글을 작성하는 메서드
	private void insertBoard() {
		System.out.println();
		scan.nextLine(); // 버퍼 비우기
		
		
		System.out.println("\t새글 작성하기");
		System.out.println("----------------------------------------");
		System.out.print(" - 제  목 : ");
		String title = scan.nextLine();
		
		System.out.print(" - 작성자 : ");
		String writer = scan.nextLine();
		
		System.out.print(" - 내  용 : ");
		String content = scan.nextLine();
		
		// 입력 받은 데이터를 VO객체에 담는다.
		JdbcBoardVO boardVo = new JdbcBoardVO();
		
		boardVo.setBoard_title(title);
		boardVo.setBoard_writer(writer);
		boardVo.setBoard_content(content);
		
		int cnt = service.insertBoard(boardVo);
		
		if(cnt>0) {
			System.out.println("새글이 추가되었습니다.");
		}else {
			System.out.println("새글 추가 실패!!!!");
		}
		
	}

	
	
	
	
	

}
