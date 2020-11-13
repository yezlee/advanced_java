package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;
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
		
		
		
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		
		
	}

	private void search() {
		// TODO Auto-generated method stub
		
	}

	private void insert() {
		System.out.println("-------------------------------------------------------------");
		
		try {
			conn = DBUtil3.getConnection();
		
			System.out.println("제목을 입력하세요.");
			board_title = scan.nextLine();
			System.out.println("작성자를 입력하세요.");
			board_writer = scan.nextLine();
			System.out.println("내용을 입력하세요.");
			board_content = scan.nextLine();
			
			String sql_insert = "INSERT INTO jdbc_board VALUES (?, ?, ?)";
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setString(1, board_title);
			pstmt.setString(2, board_writer);
			pstmt.setString(3, board_content);
			
			//위에 sql_insert 성공됐나 안됐나 확인 여부
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println("데이터 입력이 완료 되었습니다.");
			}else {
				System.out.println("데이터 입력이 되지 않았습니다. 다시 입력해주세요.");
			}
			
			
			
		} catch (SQLException e) {
			// TODO: handle exception
		}
		
	}

	private void select() {
		
		System.out.print("보기를 원하는 게시물 번호 입력 >> ");
		int input = scan.nextInt();
		scan.nextLine();
		
		try {
//			conn = DBUtil3.getConnection(); 이거안해도되나???
			
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
			scan.nextLine();
			
			
			switch(input2) {
			case 1:
				update();
				break;
			case 2:
				delete();
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

	private void delete() {
		// TODO Auto-generated method stub
		
	}

	private void update() {
		// TODO Auto-generated method stub
		
	}

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
	
	
	
	
}
