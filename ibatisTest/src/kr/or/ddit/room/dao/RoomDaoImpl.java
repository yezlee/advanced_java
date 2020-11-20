package kr.or.ddit.room.dao;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.util.BuiltSqlMapClient;

public class RoomDaoImpl implements IRoomDao{
	private SqlMapClient smc; //iBatis용 sqlMapClient객체 변수 선언
	
	//1번 자기자신 클래스의 참조값이 저장될걸 프라이빗으로 만들어
	private static RoomDaoImpl dao;
	
	//2번
	// DAO의 생성자에서 iBatis환경 설정과 SqlMapClient객체를 생성한다.
	private RoomDaoImpl() {
		smc = BuiltSqlMapClient.getSqlMapClient();
	}
	
	//3번
	public static RoomDaoImpl getInstance() {
		if(dao == null) dao = new RoomDaoImpl();
		return dao;
	}
	
	// ---------------------- 위에는 싱글톤을 만들기 위한
	
	
	
	
}
