package kr.or.ddit.room.dao;

import java.sql.SQLException;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.room.vo.RoomVo;
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
		
	
	@Override
	public int checkRoomNum(int roomNum) {
		RoomVo roomVo = null;		
		try {
			roomVo = (RoomVo)smc.queryForObject("room.checkRoomNum", roomNum);
			
		} catch (SQLException e) {
			roomVo = null;
			e.printStackTrace();
		}
		return roomNum;
	}

	@Override
	public int checkRoomAval(int roomNum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateGuestName(RoomVo roomVo) {
		int cnt = 0;
		try {
			cnt = smc.update("room.updateGuestName", roomVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int updateGuestNull(RoomVo roomVo) {
		int cnt = 0;
		try {
			cnt = smc.update("room.updateGuestNull", roomVo);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int checkAllRoom(int roomNum) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	
	
	
	
	
	
	
}
