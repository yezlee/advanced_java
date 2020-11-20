package kr.or.ddit.room.service;

import kr.or.ddit.member.service.MemberServiceImpl;
import kr.or.ddit.room.dao.IRoomDao;
import kr.or.ddit.room.dao.RoomDaoImpl;

public class RoomServiceImpl implements IRoomService{
	private IRoomDao dao; //DAO객체가 저장될 변수 선언
	
	//생성자 1번
	private static RoomServiceImpl service;
	
	//생성자 2번
	//싱글톤으로 바꿔주고 싶은데 이미 생성자가 있는경우(주로 public으로 되어있음 ) 그냥 private로 바꿔주면됨
	private RoomServiceImpl() {
		dao = RoomDaoImpl.getInstance();
	}
	
	//3번
		public static RoomServiceImpl getInstance() {
			if(service == null) service = new RoomServiceImpl();
			return service;
			
		}

		@Override
		public int checkRoomNum(int roomNum) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public int checkRoomAval(int roomNum) {
			// TODO Auto-generated method stub
			return 0;
		}
	
		
		
	
		
		
}
