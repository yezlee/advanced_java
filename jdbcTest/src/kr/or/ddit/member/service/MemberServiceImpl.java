package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.dao.IMemberDao;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;

public class MemberServiceImpl implements IMemberService {
	private IMemberDao dao; //DAO객체가 저장될 변수 선언
	
	//1번
	private static MemberServiceImpl service;
	
	//생성자 2번 - 싱글톤으로 바꿔주고 싶은데 이미 생성자가 있는경우(주로 public으로 되어있음 ) 그냥 private로 바꿔주면됨
	private MemberServiceImpl() {
		//dao = new MemberDaoImpl();
		dao = MemberDaoImpl.getInstance(); //싱글톤으로 바꿔서 위에거가 안되는거. 
	}
	
	//3번
	public static MemberServiceImpl getInstance() {
		if(service == null) service = new MemberServiceImpl();
		return service;
		
	}

	
	@Override
	public int insertMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.insertMember(memVo);
	}

	@Override
	public int deleteMember(String memId) {
		// TODO Auto-generated method stub
		return dao.deleteMember(memId);
	}

	@Override
	public int updateMember(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.updateMember(memVo);
	}
		
	@Override
	public List<MemberVO> getAllMemberList() {
		// TODO Auto-generated method stub
		return dao.getAllMemberList();
	}

	@Override
	public int getMemberCount(String memID) {
		// TODO Auto-generated method stub
		return dao.getMemberCount(memID);
	}

	@Override
	public int update_name_Member(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.update_name_Member(memVo);
	}

	@Override
	public int update_tel_Member(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.update_tel_Member(memVo);
	}

	@Override
	public int update_addr_Member(MemberVO memVo) {
		// TODO Auto-generated method stub
		return dao.update_addr_Member(memVo);
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// TODO Auto-generated method stub
		return dao.updateMember2(paramMap);
	}

}
