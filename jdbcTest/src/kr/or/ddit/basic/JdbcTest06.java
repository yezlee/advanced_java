package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.util.Scanner;

import javax.swing.text.html.HTMLDocument.HTMLReader.SpecialAction;

import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil2;
import kr.or.ddit.util.DBUtil3;

/*
	 회원을 관리하는 프로그램 작성하기
	(DB시스템의 MYMEBMER테이블 이용)
	
	처리조건)
	 1> 아래 메뉴의 기능을 모두 구현한다.(CRUD 구현하기)
	 2> '자료추가'에서는 입력한 회원 ID가 중복되는지 여부를 검사해서 중복되면 다시 입력 받도록 한다.(pk : 회원 ID) 
	 3> '자료삭제'는 회원 ID를 입력 받아 삭제한다.
	 4> '자료수정'은 회원 ID를 제외한 전체 자료를 수정한다.
	 
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

public class JdbcTest06 {
	
	Scanner scan = new Scanner(System.in);
	
	Connection conn = null;
	Statement stmt = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	String mem_id = "";
	String mem_name = "";
	String mem_tel = "";
	String mem_addr = "";
	
	

	public static void main(String[] args) {
		new JdbcTest06().start();
	}

	
	private void start() {

		
		while (true) {
			
			System.out.println("------------ 작업 선택 ------------");
			System.out.println("1. 자료 추가");
			System.out.println("2. 자료 삭제");
			System.out.println("3. 자료 수정");
			System.out.println("4. 전체 자료 출력");
			System.out.println("0. 작업 끝");
			System.out.println("--------------------------------");
			System.out.print("작업번호를 입력 하세요 >>");
			System.out.println();

			int input = scan.nextInt();
			scan.nextLine(); //엔터값을 비워주려고

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
				select();
				break;
			case 0:
				System.out.println("프로그램이 종료되었습니다.");
				System.exit(0);
			}
		}//while문이라서 switch문에서 빠져나와도 여기 반복문때문에 다시 while문 한테 올라간다.
	}

	private void select() {
		try {
		
//			conn = DBUtil.getConnection();
//			conn = DBUtil2.getConnection();
			conn = DBUtil3.getConnection();
			
			String sql_select = "SELECT * FROM mymember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql_select);
			
			while(rs.next()) { 
				//rs는 처음에 칼럼위에 제목있는데 그 근처에서 있어. 근데 행이 있으면 그 아래로 내려와서 칼럼들을 읽어. 
				//반복해줘야지 다음행도 또읽고 그러니까. while문에 넣어주는거
				
				System.out.println("회원 ID(Mem_id) : " + rs.getString(1));
				System.out.println("이름(Mem_name) : " + rs.getString(2));
				System.out.println("전화번호(Mem_tel) : " + rs.getString(3));
				System.out.println("주소(Mem_id) : " + rs.getString(4));
				System.out.println();
				System.out.println("---------------------------------");
				System.out.println();
			}
			System.out.println("전체자료 출력 끝");
			System.out.println();
			System.out.println();
			
			
		} catch (SQLException e) {
			e.printStackTrace(); // 어떤오류인지 print해주는 메소드()
			//만약 이게 없으면 그냥 프로그램이 끝나는데, 이걸 해줌으로써 뭐가 오류인지 한번 써주고 끝남.
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		
	}

	private void update() {
		
				
		System.out.println("수정할 회원의 이름을 입력해주세요.");
		
		
		try {
			
			conn = DBUtil.getConnection();
			
			int count = 0;
			
			//수정할 아이디 여부 확인
			do {
				System.out.println("수정할 회원 ID를 입력하세요.");
				
				mem_id = scan.nextLine();

				String sql_check = "SELECT count(*) FROM mymember WHERE mem_id = ?";
				pstmt = conn.prepareStatement(sql_check);
				pstmt.setString(1, mem_id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count == 0) {
					System.out.println("입력한  " + mem_id + "는 없는 ID입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}
				
			}while(count == 0);
			
			
			System.out.println("수정할 회원 이름을 입력하세요.");
			mem_name = scan.nextLine();
			System.out.println("수정할 회원 전화번호를 입력하세요.");
			mem_tel = scan.nextLine();
			System.out.println("수정할 회원 주소를 입력하세요.");
			mem_addr = scan.nextLine();
			
			String sql_update = "UPDATE mymember SET mem_name = ?, mem_tel = ?, mem_addr = ? WHERE mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql_update);
			
			pstmt.setString(1, mem_name);
			pstmt.setString(2, mem_tel);
			pstmt.setString(3, mem_addr);
			pstmt.setString(4, mem_id);

			//위에 sql_insert 성공됐나 안됐나 확인 여부
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

	private void delete() {
		
		try {
			
			conn = DBUtil.getConnection();
			
			int count = 0;
			
			//아이디 여부 확인
			do {
				System.out.println("삭제할 회원 ID를 입력하세요.");
				mem_id = scan.nextLine();

				String sql_check = "SELECT count(*) FROM mymember WHERE mem_id = ?";
				pstmt = conn.prepareStatement(sql_check);
				pstmt.setString(1, mem_id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count == 0) {
					System.out.println("입력한  " + mem_id + "는 없는ID입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}
				
			}while(count == 0);
			
			
			String sql_delete = "DELETE FROM mymember WHERE mem_id = ?";
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setString(1, mem_id);
			
			int cnt = pstmt.executeUpdate();
			
			System.out.println(cnt + "행이 삭제 완료 되었습니다.");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		
	}

	private void insert() {
		
		try {
			conn = DBUtil.getConnection();
			
			int count = 0;
			
			//아이디 중복체크하기
			do {
				System.out.println("추가할 회원 ID를 입력하세요.");
				mem_id = scan.nextLine();

				String sql_check = "SELECT count(*) FROM mymember WHERE mem_id = ?";
				pstmt = conn.prepareStatement(sql_check);
				pstmt.setString(1, mem_id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count > 0) {
					System.out.println("입력한  " + mem_id + "는 이미 등록된 회원 ID입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}
				
			}while(count > 0);
		
			System.out.println("추가할 회원 이름을 입력하세요.");
			mem_name = scan.nextLine();
			System.out.println("추가할 회원 전화번호를 입력하세요.");
			mem_tel = scan.nextLine();
			System.out.println("추가할 회원 주소를 입력하세요.");
			mem_addr = scan.nextLine();
			
			String sql_insert = "INSERT INTO mymember VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setString(1, mem_id);
			pstmt.setString(2, mem_name);
			pstmt.setString(3, mem_tel);
			pstmt.setString(4, mem_addr);
			
			//위에 sql_insert 성공됐나 안됐나 확인 여부
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println("데이터 입력이 완료 되었습니다.");
			}else {
				System.out.println("데이터 입력이 되지 않았습니다. 다시 입력해주세요.");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
	}
	
	/*
	//중복확인 메소드
	//매개변수로 회원아이디를 받아서 해당회원 아이디의 개수를 반환하는 메소드
	private int getMemberCount(String mem_id) {
		
		try {
			conn = DBUtil.getConnection();
			
			int count = 0;
			
			//아이디 중복체크하기
			do {
				System.out.println("추가할 회원 ID를 입력하세요.");
				mem_id = scan.nextLine();

				String sql_check = "SELECT count(*) FROM mymember WHERE mem_id = ?";
				pstmt = conn.prepareStatement(sql_check);
				pstmt.setString(1, mem_id);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					count = rs.getInt(1);
				}
				
				if(count > 0) {
					System.out.println("입력한  " + mem_id + "는 이미 등록된 회원 ID입니다.");
					System.out.println("다시 입력하세요.");
					System.out.println();
				}
				
			}while(count > 0);
		
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		return count;		
	}*/
	
}
