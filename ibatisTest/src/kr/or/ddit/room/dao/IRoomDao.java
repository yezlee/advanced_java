package kr.or.ddit.room.dao;

import kr.or.ddit.room.vo.RoomVo;

/**
 * 실제 DB와 연결해서 SQL문을 수행하여 결과를 작성해서 Service에 전달하는 DAO의 interface
 */
public interface IRoomDao {
	
	public int checkRoomNum(int roomNum);	
	
	public int checkRoomAval(int roomNum);	
	
	public int updateGuestName(RoomVo roomVo);	

	public int updateGuestNull(RoomVo roomVo);	
	
	public int checkAllRoom(int roomNum);	
	
	
	
	
}
