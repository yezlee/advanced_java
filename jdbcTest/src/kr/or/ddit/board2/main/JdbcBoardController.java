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
			String boardTitle = null; //검색할 제목이 저장될변수
			int choice = -1;
			
			while(true) {
				
				//검색작업이 아니면 전체리스트가 출력되어야 한다.
				if(choice!=3) {
					boardTitle = null;
				}
				choice = displayMenu(boardTitle);
				
				switch(choice) {
					case 1 : insertBoard();	// 새글 작성
						break;
					case 2 : viewBoard();	// 게시글 보기
						break;
					case 3 : boardTitle = searchBoard();	// 검색
						break;
					case 0 :	// 작업 끝
						System.out.println("게시판 프로그램 종료...");
						return;
					default : 
						System.out.println("번호를 잘못 입력했습니다. 다시 입력하세요.");
				}
			}
		}
	
	// 게시글 검색에 필요한 제목을 입력받아 반환하는 메소드
	private String searchBoard() {
			System.out.println();
			scan.nextLine();//입력 버퍼 비우기
			System.out.println("검색 작업");
			System.out.println("---------------------------------");
			System.out.print(" - 검색할 제목 입력 :");
			String title = scan.nextLine();
			return title;
		}

	// 게시판 목록을 보여주고 메뉴를 나타내명, 사용자가 입력한 메뉴 번호를 반환하는 메서드
	private int displayMenu(String title) {
		List<JdbcBoardVO> boardList;
		if(title==null || "".equals(title)) {
			//전체 게시글들을 가져온다.
			boardList = service.getAllBoardList();
		}else {
			boardList = service.getSearchBoardList(title);
		}
		
		// 전체 게시글들을 가져온다.
		
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
		System.out.println();
		
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

	//게시글 내용을 보여주는 메소드
	private void viewBoard() {
		System.out.println();
		System.out.println("보기를 원하는 게시물 번호 입력>>");
		int num = scan.nextInt();
		
		JdbcBoardVO boardVo = service.getBoard(num);
		if(boardVo==null) {//이건 오류생긴거나 마찬가지니까
			System.out.println(num + "번의 게시글이 존재하지 않습니다.");
			return;
		}
		System.out.println();
		System.out.println(num + "번글 내용");
		System.out.println("-----------------------------------------------");
		System.out.println(" - 제   목 : " + boardVo.getBoard_title());
		System.out.println(" - 작성자 : " + boardVo.getBoard_writer());
		System.out.println(" - 내   용 : " + boardVo.getBoard_content());
		System.out.println(" - 작성일 : " + boardVo.getBoard_date());
		System.out.println(" - 조회수 : " + boardVo.getBoard_cnt());
		System.out.println("-----------------------------------------------");
		System.out.println("메뉴 : 1.수정	2.삭제	3.리스트로가기");
		System.out.println("작업 선택>>");
		int choice = scan.nextInt();
		
		switch(choice) {
		case 1:	updateBoard(num);		//수정
			break;
		case 2:	deleteBoard(num);		//삭제
			break;
		case 3:			//리스트로 가기
			return;
		}
		
		
	}

	

	//게시글을 수정하는 메소드
	private void updateBoard(int boardNo) {
		System.out.println();
		System.out.println("수정 작업하기");
		System.out.println("-----------------------------------");
		scan.nextLine(); //버퍼 비우기
		System.out.print(" - 제   목 : ");
		String title = scan.nextLine();

		System.out.print(" - 내   용 : ");
		String content = scan.nextLine();
		
		// 입력받은 수정할 데이터를 VO객체에 담는다.
		JdbcBoardVO boardVo = new JdbcBoardVO();
		boardVo.setBoard_no(boardNo);
		boardVo.setBoard_title(title);
		boardVo.setBoard_content(content);
		
		int cnt = service.updateBoard(boardVo);
		
		if(cnt>0) {
			System.out.println(boardNo + "번글이 수정 되었습니다.");
		}else {
			System.out.println(boardNo + "번글 수정 실패!");
		}
	}
	
	
	//게시글을 삭제하는 메소드
	private void deleteBoard(int boardNo) {
		int cnt = service.deleteBoard(boardNo);
		if(cnt>0) {
			System.out.println(boardNo + "번글이 삭제 되었습니다.");
		}else {
			System.out.println(boardNo + "번글 삭제 실패!");
		}
		
	}
	
	
	
	

}
