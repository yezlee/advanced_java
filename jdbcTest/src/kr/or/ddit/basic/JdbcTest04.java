package kr.or.ddit.basic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class JdbcTest04 {

	//은행 계좌 번호 정보를 추가하는 예제
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		//resultSet은 안만들어도 돼. 왜냐 셀렉문이 한번도 실행 안하면 필요없음
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521/xe", "yez", "java"						
					);
					
			System.out.println("계좌번호 정보 추가하기");
			System.out.print("계좌번호  : ");
			String bankNo = scan.next();

			System.out.print("은행명  : ");
			String bankName = scan.next();
			
			System.out.print("예금주명  : ");
			String userName = scan.next();
			
			
			//statement객체를 이용하여 추가하기
		
			/*
				insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) values ('123-456-78', '하나은행', '홍길동', sysdate); 
			 */
			
			/*
			String sql = " insert into bankinfo (bank_no, bank_name, bank_user_name, bank_date) "
					+ " values ( '" + bankNo + "', '" + bankName + "', '" + userName + "', sysdate)" ;
			
			System.out.println(sql);
			System.out.println();
			
			stmt = conn.createStatement();
			
			// SQL문이 select문일 경우에는 executeQuery()메소드를 사용했는데
			// SQL문이 select문이 아닐 경우에는 executeUpdate()메소드를 사용한다.
			
			// executeUpdate()메소드의 반환값 ==> 해당 작업에 성공한 레코드 수
			int cnt = stmt.executeUpdate(sql);
			*/
			
			
			//PreparedStatement 객체를 이용하여 추가하기
			// ==> SQL문에서 데이터가 들어갈 자리를 물음표(?)로 표시하여 작성한다.
			
			String sql = " insert into bankinfo "
					+ " (bank_no, bank_name, bank_user_name, bank_date) "
					+ " values ( ?, ?, ?, sysdate)" ;
			
			//PreparedStatement 객체 생성하기
			// ==> 객체를 생성할 때 처리할 SQL문을 넣어준다.
			pstmt = conn.prepareStatement(sql);
			//sql문을 넣어주면 컴터 언어로 컴파일을 미리 해준대, 이런 insert구조를 한번이 아니고 여러번 실행할때,이렇게 컴파일 해놓고 바뀌는것만 아래처럼 넣어주면 속도가 빨라
			//근데 createStatement이건 매번 insert할때마다 컴파일을 해줘야함. 그래서 속도도 느리고. 
			//실무에선 주로 prepareStatement 이걸 씀. 얘는 입력하면 자동으로 ''이게 생겨서 '5 or 1=1' 이렇게 입력되면 문자열로 처리되니까. 오류뜨지
			
			
			//SQL문의 물음표(?) 자리에 들어갈 데이터를 셋팅한다.
			//형식> pstmt.set자료형이름(물음표순번, 셋팅할 데이터)
			pstmt.setString(1, bankNo);
			pstmt.setString(2, bankName);
			pstmt.setString(3, userName);
			
			//데이터세팅이 완료되면 SQL문을 실행하여 결과를 얻어온다.
			
			int cnt = pstmt.executeUpdate(); //sql파라미터 값이 안들어감. ???????
			
			System.out.println("반환값 cnt = " + cnt);
			
					
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
	}
}
