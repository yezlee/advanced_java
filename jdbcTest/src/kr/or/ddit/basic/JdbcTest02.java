package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제1) 사용자로부터 Lprod_id값을 입력 받아 입력한 값보다 Lprod_id값이 큰 자료드을 출력하시오. 
//3을 입력하면 4567~ 나오게

/*public class JdbcTest02 {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		// DB작업에 필요한 객체변수 선언
		Connection conn = null;
		Statement stmt = null; 
		ResultSet rs = null; //select문일 경우에는 select한 결과가 ResultSet객체에 저장되어 반환
		
		
		System.out.println("숫자를 입력해주세요.");
		int input = Integer.parseInt(scan.nextLine());
		
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB연결 ==> Connection객체 생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe", "yez", "java"	
					);
			
			//3-1. 실행할 SQL문 작성 3번은 Connection -> Statement -> ResultSet 순서대로
			String sql = "select * from lprod";
			
			//3-2. Statement 객체를 생성 ==> Connection 객체를 이용한다.
			stmt = conn.createStatement();
			
			//4. SQL문을 DB서버로 전송해서 실행하고 결과를 얻어온다.
			//	 (지금은 실행할 SQL문이 select문이기 때문에 결과가 ResultSet객체에 저장되어 반환한다.)
			rs = stmt.executeQuery(sql);
			
			//5. 결과 처리하기
			System.out.println("== 처리 결과 출력 ==");
			
			
			while(rs.next()) {
				if(rs.getInt(1)>input) {
					System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
					System.out.println("Lprod_gu : " + rs.getString("Lprod_gu"));
					System.out.println("Lprod_nm : " + rs.getString("Lprod_nm"));
					System.out.println("-------------------");
				}
			}
			System.out.println("전체자료 출력 끝");
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// 6. 사용했던 자원 반납하기
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}

	}

}*/


public class JdbcTest02 {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		// DB작업에 필요한 객체변수 선언
		Connection conn = null;
		Statement stmt = null; 
		ResultSet rs = null; //select문일 경우에는 select한 결과가 ResultSet객체에 저장되어 반환
		
		
		System.out.println("숫자를 입력해주세요.");
		int input = Integer.parseInt(scan.nextLine());
		
		
		try {
			// 1. 드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2. DB연결 ==> Connection객체 생성
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe", "yez", "java"	
					);
			
			//3-1. 실행할 SQL문 작성 3번은 Connection -> Statement -> ResultSet 순서대로
			String sql = "select * from lprod where lprod_id >" + input;
			
			//3-2. Statement 객체를 생성 ==> Connection 객체를 이용한다.
			stmt = conn.createStatement();
			
			//4. SQL문을 DB서버로 전송해서 실행하고 결과를 얻어온다.
			//	 (지금은 실행할 SQL문이 select문이기 때문에 결과가 ResultSet객체에 저장되어 반환한다.)
			rs = stmt.executeQuery(sql);
			
			//5. 결과 처리하기
			System.out.println("== 처리 결과 출력 ==");
			
			
			while(rs.next()) {
				
					System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
					System.out.println("Lprod_gu : " + rs.getString("Lprod_gu"));
					System.out.println("Lprod_nm : " + rs.getString("Lprod_nm"));
					System.out.println("-------------------");
				
			}
			System.out.println("전체자료 출력 끝");
			
		} catch (SQLException e) {
			// TODO: handle exception
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			// 6. 사용했던 자원 반납하기
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}

	}

}

