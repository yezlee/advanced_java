package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

//문제2) lprod_id값 2개를 차례로 입력 받아서 두값중 작은 값부터 큰값사이의 자료들을 출력하시오.
// 1,5입력하면 1번부터 5번까지

/*public class JdbcTest03 {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println("숫자를 입력해주세요.");
		int input = Integer.parseInt(scan.nextLine());
		int input2 = Integer.parseInt(scan.nextLine());
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe", "yez", "java"	
					);
			String sql = "select * from lprod";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("== 처리 결과 출력 ==");
			
			while(rs.next()) { //while문안에서 하는것보다 밖에서 조건을 다 처리해주고 반복문 안으로 들어오는게 더 좋음
				
				if(input >= rs.getInt(1) && rs.getInt(1) >= input2 || input <= rs.getInt(1) && rs.getInt(1) <= input2) {
					System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
					System.out.println("Lprod_gu : " + rs.getString("Lprod_gu"));
					System.out.println("Lprod_nm : " + rs.getString("Lprod_nm"));
					System.out.println("-------------------");
				}
			}
			System.out.println("전체자료 출력 끝");
			
						
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}	
	}

}
*/


public class JdbcTest03 {

	static Scanner scan = new Scanner(System.in);
	
	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		System.out.println("첫번째 숫자를 입력해주세요.");
		int input = Integer.parseInt(scan.nextLine());
		System.out.println("두번째 숫자를 입력해주세요.");
		int input2 = Integer.parseInt(scan.nextLine());
		System.out.println();
		
		//둘중에 누가 더큰지 미리 여기서 검사하는게 나아
		
		int max, min;
		if(input>input2) {
			max = input;
			min = input2;
		}else {
			max = input2;
			min = input;
		}
		
		
//		max = Math.max(input, input2);	//이렇게 하면 둘중에 큰값이 찾아짐
//		min = Math.min(input, input2);	//이렇게 하면 둘중에 작은값이 찾아짐
		
		
		try {
			
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe", "yez", "java"	
					);
//			String sql = "select * from lprod where lprod_id >= " + min + " and lprod_id <= " + max;
			String sql = "select * from lprod where lprod_id between " + min + " and  " + max;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			System.out.println("== 처리 결과 출력 ==");
			System.out.println();
			System.out.println("실행한 SQL문 : " + sql);
			System.out.println();
			
			while(rs.next()) { //while문안에서 하는것보다 밖에서 조건을 다 처리해주고 반복문 안으로 들어오는게 더 좋음
				
					System.out.println("Lprod_id : " + rs.getInt("lprod_id"));
					System.out.println("Lprod_gu : " + rs.getString("Lprod_gu"));
					System.out.println("Lprod_nm : " + rs.getString("Lprod_nm"));
					System.out.println("-------------------");
				
			}
			System.out.println("전체자료 출력 끝");
			
						
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}	
	}

}
