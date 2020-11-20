package kr.or.ddit.member.dao;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.Charset;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.BuiltSqlMapClient;

public class MemberDaoImpl implements IMemberDao{
	private SqlMapClient smc; //iBatis용 sqlMapClient객체 변수 선언	
	Reader rd = null;
	
	//1번 자기자신 클래스의 참조값이 저장될걸 프라이빗으로 만들어
	private static MemberDaoImpl dao;
	
	//2번
	// DAO의 생성자에서 iBatis환경 설정과 SqlMapClient객체를 생성한다.
	private MemberDaoImpl() {
		/*try {
			//1-1. 문자 인코딩 캐릭터셋 설정하기
			Charset charset = Charset.forName("UTF-8");
			Resources.setCharset(charset);
			
			//1-2. 환경 설정 파일을 읽어온다.
			rd = Resources.getResourceAsReader("sqlMapConfig.xml");
			
			//1-3. 위에서 읽어온 Reader객체를 이용하여 실제 환경설정을 완성한 후 
			//	   SQL문을 호출해서 실행 할 수 있는 객체를 생성한다.
			smc = SqlMapClientBuilder.buildSqlMapClient(rd);
			
			rd.close(); // Reader객체 닫기
			
			// 환경 설정 끝
			
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(rd!=null) try {rd.close();} catch(IOException e2) {}
		}*/
		
		smc = BuiltSqlMapClient.getSqlMapClient();
	}
	
	//3번
	public static MemberDaoImpl getInstance() {
		if(dao == null) dao = new MemberDaoImpl();
		return dao;
	}

	// ---------------------- 위에는 싱글톤을 만들기 위한
	
	
	
	//insert는 성공하면 null을 반환해서 그걸 오브젝트에 넣어주는거고
	//그외 delete랑 update는 성공하면, 그 행의 갯수를 반환한다. 그래서 그걸 int에 담아주는거고
	//지금 짜놓은 코드로는 어차피 삭제나 수정이나 한번에 한개씩밖에 못해서 반환값이 1이거나 0일수밖에 없음
	
	@Override
	public int insertMember(MemberVO memVo) {
	
		int cnt = 0; // 반환값이 저장될 변수(작업성공 : 1, 실패 : 0)
		
		try {
			Object obj = smc.insert("mymember.insertMember", memVo);
			if(obj==null) cnt = 1; //insert는 실행에 성공하면 null을 반환한다(ibatis는)
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
			
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		int cnt = 0;
		
		try {
			cnt= smc.delete("mymember.deleteMember", memId);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}

		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		int cnt = 0; 
		
		try {
			cnt = smc.update("mymember.updateMember", memVo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public int update_name_Member(MemberVO memVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("mymember.update_name_Member", memVo);			
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
		}
		
		return cnt;
	}

	@Override
	public int update_tel_Member(MemberVO memVo) {
		int cnt = 0; 
		
		try {
			cnt = smc.update("mymember.update_tel_Member", memVo);	
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
		}
		
		return cnt;
	}

	@Override
	public int update_addr_Member(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; 
		
		try {
			cnt = smc.update("mymember.update_addr_Member", memVo);	
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		return cnt;
	}

	
	
	
	@Override
	public List<MemberVO> getAllMemberList() {

		List<MemberVO> memList = null; //MemberVO객체가 저장될 List객체 변수 선언
		
		try {
			memList = smc.queryForList("mymember.selectAllmember");
			/*smc.queryForObject(")*/		
			
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace(); 
		}
		
		return memList;
	}

	
	@Override
	public int getMemberCount(String memID) {
	
		int cnt = 0; 
				
		try {
			cnt = (int)(smc.queryForObject("mymember.countMember", memID));	
			//queryForObject얘는 꼭 형변환을 해줘야해	
			
		} catch (SQLException e) {
			cnt = 0; //쿼리에 아무것도 없어도 오류내지 말아라
			e.printStackTrace();
		} 
		
		return cnt;	
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// key값 ==> 회원ID(memId), 변경할컬럼명(field), 변경할데이터(data)
		
		int cnt = 0;
		try {
			cnt = smc.update("mymember.updateMember2", paramMap);
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}
	

}
