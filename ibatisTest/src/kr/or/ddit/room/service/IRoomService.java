package kr.or.ddit.room.service;

import kr.or.ddit.room.vo.RoomVo;

public interface IRoomService {

	public int checkRoomNum(int roomNum);	
	
	public int checkRoomAval(int roomNum);	
	
	public int updateGuestName(RoomVo roomVo);	

	public int updateGuestNull(RoomVo roomVo);	
	
	public int checkAllRoom(int roomNum);	
	
	
}
