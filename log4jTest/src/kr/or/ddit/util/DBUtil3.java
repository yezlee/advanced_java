package kr.or.ddit.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

//JDBC드라이버를 로딩하고, Connection객체를 생서하여 반환하는 메소드로 구성된 class

//DBUtil2,3 이거 다 실무에서 쓰여!
//이게 젤 최신버전! 이걸 쓰는게 좋다!
//ResourceBundle객체 이용하기
public class DBUtil3 {
	
	static Logger logger = Logger.getLogger(DBUtil3.class);
	
	static ResourceBundle bundle;
	
	static {
		bundle = ResourceBundle.getBundle("dbinfo"); 
		logger.info("ResourceBundle 객체 생성 - dbinfo.propertied 파일 읽기");
		
		try {
//			Class.forName("oracle.jdbc.driver.OracleDriver");
			Class.forName(bundle.getString("driver"));
			logger.info("DB드라이버 로딩 성공!");
		} catch (ClassNotFoundException e) {
			//System.out.println("드라이버 로딩 실패!");
			logger.error("드라이버 로딩 실패! " + e) ;
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		try {
//			return DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "yez", "java");
			return DriverManager.getConnection(bundle.getString("url"),
					bundle.getString("user"), bundle.getString("pass"));
			
		} catch (SQLException e) {
			//System.out.println("DB 연결 실패!");
			logger.error("DB시스템 연결실패!" + e);
			return null;
		}
	}
}
