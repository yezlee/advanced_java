package kr.or.ddit.basic;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import kr.or.ddit.util.DBUtil;

/*
  MVC패턴에 대하여 조사하여 학습하기 - 실무에서 기본적으로 사용하는 패턴임.
 */


/*
 	LPROD테이블에 새로운 데이터 추가하기
 	
 	추가할 데이터 중 Lprod_gu와 Lprod_nm은 직접 입력 받아서 처리하는데
 	입력받은 Lprod_gu가 이미 등록되어 있으면 다시 입력 받아서 처리한다.
 	그리고,
 	Lprod_id값은 현재의 Lprod_id값 중 제일 큰 값보다 1 증가된 값으로 처리한다.
 	
	Lprod_gu가 pk임
 */

/*public class JdbcTest05 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "yez", "java"
					);
			
			String lprod_gu = "";
			
			//조건문을 쓰고 싶은데 조건에 만족하지 않으면 다시 반복하게 하고싶을때 while문안에 조건문, if문을 쓰는거야
			while(true) {  
				System.out.print("Lprod_gu 추가하기 >>");
				lprod_gu = scan.next();
				
				String sql2 = " SELECT count(*) FROM lprod WHERE lprod_gu = ? ";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, lprod_gu);
				
				rs = pstmt.executeQuery();	
				
				rs.next(); //이거 왜 해주는거지?
				//==> 한칸앞에 데이터를 읽게해주려고
				//rs.next() 이걸 해줌으로써 지금 rs는 count(*)의 값이 나온 1행을 가리키고 있어
				//그렇기 때문에 아래 if문을 실행할때 rs.getInt(1) 이게 가능한거.
				
				// + 주로 rs.next() 이걸 사용하는 이유는 얘는 리턴값을 가지고 있어. rs가 값이 있으면 true 아니면 false인 boolean리턴값을 갖고있어
				//그래서 주로 while문에 많이 쓰지. 왜? 다음 행으로 rs를 보내주려고. 근데 위에 select문의 데이터는 1행밖에 없기때문에 
				//반복할 필요가 없어서 while문을 쓰지 않아.
				
				
				//if(sql2 == "1") 이렇게는 안되나?
				//==>그치 count(*)를 써서 구한 데이터인데, 그건 숫자니까.
				
				if(rs.getInt(1) == 1) {
					System.out.println(lprod_gu + "은 이미 존재합니다. 다시 입력해 주세요");
				}else {
					break; //브레이크는 자기를 감싸고 있는 가장 가까운 반복문을 빠져나감.
				}
			}
			
			
			
			System.out.print("Lprod_nm 추가하기 >>");
			String lprod_nm = scan.next();
			
			
			String sql = " INSERT INTO lprod "
					+ " VALUES ((SELECT nvl(MAX(lprod_id),0) +1 FROM lprod), ?, ?) ";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, lprod_gu);
			pstmt.setString(2, lprod_nm);
						
			int cnt = pstmt.executeUpdate();
			System.out.println("반환값 cnt = " + cnt);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
	}
}
*/


public class JdbcTest05 {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null; //조건붙을땐 이게 안전함
		ResultSet rs = null;
		
		try {
/*			Class.forName("oracle.jdbc.driver.OracleDriver");

			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "yez", "java"
					);*/
			
			
			conn = DBUtil.getConnection();
			
			//lprod_id값 구하기 => 현재의 lprod_id값 중에서 제일 큰 값보다 1 크게 한다.
			String sql = "SELECT nvl(MAX(lprod_id),0) maxnum FROM lprod";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			int maxNum = 0;
			//혹시 데이터가 없을수도 있으니까 if문으로 감싸서 rs.next()해줘
			if(rs.next()) { //쿼리문결과가 하나밖에 없어서 while문 안쓰고 그냥 한번 써준거
				maxNum = rs.getInt("maxnum"); //위에 셀렉문에서 alias준거
			}
			maxNum++; //이거 번호(제일큰번호 - 순번) 넣어주려고
			
			
			//입력 받은 Lprod_gu가 이미 등록된 데이터이면 다시 입력받아서 처리한다.
			String gu; //상품분류 코드(Lprod_gu)가 저장될 변수
			int count = 0; //입력한 상품 분류 코드 갯수가 저장될 변수
			
			do {
				System.out.print("상품분류코드를 입력하세요 : ");
				gu = scan.next();
				
				String sql2 = "SELECT count(*) cnt FROM lprod WHERE lprod_gu = ? ";
				pstmt = conn.prepareStatement(sql2);
				pstmt.setString(1, gu);
				
				rs = pstmt.executeQuery(); //select문일 경우
				
				if(rs.next()) {
					count = rs.getInt("cnt"); 
				}
				
				if(count > 0) {
					System.out.println("입력한 상품 분류 코드 " + gu + "는 이미 등록된 코드입니다.");
					System.out.println("다시 입력하세요.");
				}
				
			}while(count > 0); //중복되는거니까, 다시 do로 올라가게끔.
			
			//while문이 반복이 되면 위에서부터 다시 입력하는거니까, 일부러 true로 만들어줘서 do를 다시 하게끔.
			//do-while은 do는 한번은 해라. 결과가 참이든 거짓이든 한번은 하고 나머지는 while문에게 맡긴다 
			//근데 이 while문이 참이면 do안에 있는 {}얘를 한다.
			
			
			System.out.print("상품분류명 입력 :");
			String nm = scan.next();
			
			String sql3 = "INSERT INTO lprod VALUES (?, ?, ?) "; 
			pstmt = conn.prepareStatement(sql3);
			pstmt.setInt(1, maxNum);
			pstmt.setString(2, gu);
			pstmt.setString(3, nm);
			
			int cnt = pstmt.executeUpdate();
			if(cnt>0) {
				System.out.println("DB 추가 성공!");
			}else {
				System.out.println("DB 추가 실패!");
			}
			
			scan.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {} //이걸 제일 먼저 닫아줘야해
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
	}
}
