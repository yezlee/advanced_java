package kr.or.ddit.member.service;

import java.util.List;
import java.util.Map;

import kr.or.ddit.member.vo.MemberVO;

public interface IMemberService {
	
	/**
	 * MemberVO에 담겨진 자료를 DB에 insert하는 메소드
	 * 
	 * @param memVo DB에 insert할 자료가 저장된 MemberVO객체
	 * @return insert 작업 성공 : 1, 실패 : 0
	 */
	public int insertMember(MemberVO memVo);
	
	
	/**
	 * 회원 ID를 매개값으로 받아서 해당 회원 정보를 삭제하는 메소드
	 *  
	 * @param memId 삭제할 회원 id
	 * @return 삭제 성공 : 1, 삭제 실패 : 0
	 */
	public int deleteMember(String memId);
	
	
	
	/**
	 * MemberVO에 담겨진 정보를 이용하여 회원 정보를 수정하는 메소드
	 * @param memVo 수정할 정보가 저장된 MemberVO객체
	 * @return 수정 성공 : 1, 수정 실패 : 0
	 */
	public int updateMember(MemberVO memVo);
	
	public int update_name_Member(MemberVO memVo); //이름변경 추가
	public int update_tel_Member(MemberVO memVo); //전화번호변경 추가
	public int update_addr_Member(MemberVO memVo); //주소변경 추가
	
	/**
	 * DB의 회원테이블의 전체 레코드를 가져와서 List에 담아서 반환하는 메소드
	 * @return MemberVO객체를 담고 있는 List객체
	 */
	public List<MemberVO> getAllMemberList(); //파라미터 없는 이유는, 가져오기만 하는거니깐 컨트롤러에서 보낼게 없겠지
	
	

	/**
	 * 회원ID를 매개값으로 받아서 해당 회원의 개수를 반환하는 메소드
	 * @param memID 검색할 회원ID
	 * @return 검색된 회원ID 갯수
	 */
	public int getMemberCount(String memID);
	
	
	
	/**
	 * Map의 정보를 이용하여 회원 정보들 중 원하는 컬럼을 수정하는 메소드
	 *  Map의 key값 : 회원ID(memId), 변경할컬럼명(field), 변경할데이터(data)
	 *  
	 * @param paramMap 회원ID, 변경할컬럼, 변경할데이터가 저장된 Map객체
	 * @return 수정성공 : 1, 실패 : 0
	 */
	public int updateMember2(Map<String , String> paramMap);
	
	
}
