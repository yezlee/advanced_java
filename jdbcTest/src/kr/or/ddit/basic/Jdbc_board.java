package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil3;

public class Jdbc_board {

	Scanner scan = new Scanner(System.in);
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	int board_no = 0;
	int board_cnt = 0; 
	String board_title  = "";
	String board_writer  = "";
	String board_content  = "";
	
	public static void main(String[] args) {
		
		new Jdbc_board().start(); //시작이면서 게시판 전체출력 메소드
		
		
	}

	//시작이면서 게시판 전체출력 메소드
	private void start() {
		
		while(true) {
		
			try {
				
				conn = DBUtil3.getConnection();
			
				String sql_Start = "SELECT board_no, board_title, board_writer, board_cnt FROM jdbc_board";
				stmt = conn.createStatement();
				rs = stmt.executeQuery(sql_Start);
				
				System.out.println("-------------------------------------------------------------");
				System.out.println(" No\t제 목\t\t작성자\t조회수");
				System.out.println("-------------------------------------------------------------");
				
			while(rs.next()) {			
				System.out.println(" " + rs.getInt(1) +"\t"+ rs.getString(2) +"\t\t"+ rs.getString(3) +"\t"+ rs.getInt(4));
			}
			
			/*System.out.println("-------------------------------------------------------------");
			System.out.println("메뉴 :  1.새글작성     2.게시글보기     3.검색     0.작업끝");
			System.out.print("작업선택 >>");
			
			int input = scan.nextInt();
			scan.nextLine();
			
			
			switch(input) {
			case 1:
				insert();
				break;
			case 2:
				select();
				break;
			case 3:
				search();
			case 0:
				System.exit(0);
			} 
			
			 여기다 하면 안되는 이유가, 스위치문 돌릴때까지는 캐치문이 실행 되는데, 여기서 벗어나서 select문으로 갔을때는 셀렉안에서의 캐치문을 다시 실행해야 하는거
			 즉  finally를 실행이 안된상태에서 셀렉문으로 가는거지
			 */
			
			
			
			} catch (SQLException e) {
				e.printStackTrace();
			}finally {
				if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
				if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
				if(conn!=null) try {conn.close();}catch(SQLException e) {}
				if(rs!=null) try {rs.close();}catch(SQLException e) {} 
			}
			
			System.out.println("-------------------------------------------------------------");
			System.out.println("메뉴 :  1.새글작성     2.게시글보기     3.검색     0.작업끝");
			System.out.print("작업선택 >>");
			
			int input = scan.nextInt();
			scan.nextLine();
			
			
			switch(input) {
			case 1:
				insert();
				break;
			case 2:
				select();
				break;
			case 3:
				search();
			case 0:
				System.exit(0);
			}
		
		}
	}

	private void search() {
		System.out.println();
		System.out.println("검색 작업 하기");
		System.out.println("-------------------------------------------------------------");
		
		try {
			conn = DBUtil3.getConnection();
			
			System.out.print("- 검색할 제목 입력 :");
			String board_search = scan.nextLine();
			
			String sql_search = "SELECT board_no, board_title, board_writer, board_cnt FROM jdbc_board WHERE board_title LIKE '%'|| ? ||'%' ";
			
			pstmt = conn.prepareStatement(sql_search);
			pstmt.setString(1, board_search);
			
			rs = pstmt.executeQuery();
			
			
			System.out.println("-------------------------------------------------------------");
			System.out.println(" No\t제 목\t\t작성자\t조회수");
			System.out.println("-------------------------------------------------------------");
			
			while(rs.next()) {			
				System.out.println(" " + rs.getInt(1) +"\t"+ rs.getString(2) +"\t\t"+ rs.getString(3) +"\t"+ rs.getInt(4));
			}
				
			System.out.println("-------------------------------------------------------------");
			System.out.println();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		
		select();
		
	}

	private void insert() {
		System.out.println("-------------------------------------------------------------");
		
		System.out.println("제목을 입력하세요.");
		board_title = scan.nextLine();
		System.out.println("작성자를 입력하세요.");
		board_writer = scan.nextLine();
		System.out.println("내용을 입력하세요.");
		board_content = scan.nextLine();
		
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql_seq = "SELECT board_seq_test.nextVal FROM dual";
			String sql_insert = "INSERT INTO jdbc_board (board_no, board_title, board_writer, board_content, board_date) VALUES (?, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(sql_seq);
			rs = pstmt.executeQuery();
			rs.next();
			
			int seq = rs.getInt(1);
			
			pstmt.close();
			
			//그리고 다시 sql_insert 시작	
			
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setInt(1, seq);
			pstmt.setString(2, board_title);
			pstmt.setString(3, board_writer);
			pstmt.setString(4, board_content);
			
			//위에 sql_insert 성공됐나 안됐나 확인 여부
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println("데이터 입력이 완료 되었습니다.");
			}else {
				System.out.println("데이터 입력이 되지 않았습니다. 다시 입력해주세요.");
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		
		}
		
	}

	private void select() {
		
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int input = scan.nextInt();
		
		try {
			conn = DBUtil3.getConnection();
			
			
			String sql_check = "UPDATE jdbc_board SET board_cnt = (SELECT nvl(MAX(board_cnt),0) +1 FROM jdbc_board WHERE board_no = ?) WHERE board_no = ?"; 
			
			pstmt = conn.prepareStatement(sql_check);
			pstmt.setInt(1, input);
			pstmt.setInt(2, input);
			pstmt.executeQuery();
			
			pstmt.close();
			
			
			//----------------------				
			
			String sql_select = "SELECT board_title, board_writer, board_content, board_date, board_cnt FROM jdbc_board WHERE board_no = ?";
			
			pstmt = conn.prepareStatement(sql_select);
			pstmt.setInt(1, input);
			
			rs = pstmt.executeQuery();
			
			rs.next();
			
			System.out.println();
			System.out.println("-------------------------------------------------------------");
			System.out.println("- 제  목  : " + rs.getString(1));
			System.out.println("- 작성자 : " + rs.getString(2));
			System.out.println("- 내  용  : " + rs.getString(3));
			System.out.println("- 작성일 : " + rs.getDate(4));
			System.out.println("- 조회수 : " + rs.getInt(5));
			System.out.println("-------------------------------------------------------------");
			System.out.println("메뉴 :  1.수정     2.삭제     3.목록     0.작업끝");
			System.out.print("작업선택 >>");
			
			int input2 = scan.nextInt();
			
			
			switch(input2) {
			case 1:
				update(input); //수정
				break;
			case 2:
				delete(input); //삭제
				break;
			case 3:
				start();
				break;
			case 0:
				System.exit(0);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		
		
		
		
	}

	private void delete(int board_seq) {
		try {
			conn = DBUtil3.getConnection();
			
			String sql_delete = "DELETE FROM jdbc_board WHERE board_no = ?";
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setInt(1, board_seq);
			
						
			//위에 sql_delete 성공됐나 안됐나 확인 여부
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println(board_seq + "번 게시물이 삭제 되었습니다.");
			}else {
				System.out.println("삭제가 되지 않았습니다. 다시 시도해주세요.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		
	}

	private void update(int board_seq) {
		
		System.out.println();
		System.out.println("수정 작업 하기");
		System.out.println("-------------------------------------------------------------");
		
		try {
			conn = DBUtil3.getConnection();
			
			scan.nextLine();
			System.out.print("수정할 제목을 입력하세요 >>");
			board_title = scan.nextLine();
			
			System.out.print("수정할 내용을 입력하세요 >>");
			board_content = scan.nextLine();
			System.out.println("-------------------------------------------------------------");
			
			String sql_update = "UPDATE jdbc_board SET board_title = ?, board_content = ?  WHERE board_no = ?";
			
			pstmt = conn.prepareStatement(sql_update);
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_content);
			pstmt.setInt(3, board_seq);
			
			//위에 sql_update 성공됐나 안됐나 확인 여부
			int cnt = pstmt.executeUpdate(); //이걸 꼭 해줘야지 쿼리문이 업데이트가 되는것.
			if(cnt>0) {
				System.out.println("데이터 수정이 완료 되었습니다.");
			}else {
				System.out.println("데이터 수정이 되지 않았습니다. 다시 입력해주세요.");
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		
	}

	
	
	
	
	
	
	
	/* 중복체크용 메소드
	 * 
	private int getMemberCount(String mem_id) { 
		int count = 0;
		
		try {
			conn = DBUtil3.getConnection();
						
				String sql_check = "SELECT count(*) FROM mymember WHERE mem_id = ?";
				pstmt = conn.prepareStatement(sql_check);
				pstmt.setString(1, mem_id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				
		} catch (SQLException e) {
			count = 0; //쿼리에 아무것도 없어도 오류내지 말아라
			e.printStackTrace();
		} finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		return count;	
		
		
	}
	
	*/
	
	
}
