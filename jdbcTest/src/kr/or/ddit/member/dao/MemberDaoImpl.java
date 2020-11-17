package kr.or.ddit.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.DBUtil;
import kr.or.ddit.util.DBUtil3;

public class MemberDaoImpl implements IMemberDao{
	
	//1번 자기자신 클래스의 참조값이 저장될걸 프라이빗으로 만들어
	private static MemberDaoImpl dao;
	
	//2번
	private MemberDaoImpl() {}
	
	//3번
	public static MemberDaoImpl getInstance() {
		if(dao == null) dao = new MemberDaoImpl();
		return dao;
	}

	
	
	@Override
	public int insertMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수(작업성공 : 1, 실패 : 0)
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql_insert = "INSERT INTO mymember VALUES (?, ?, ?, ?)";
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setString(1, memVo.getMem_id());
			pstmt.setString(2, memVo.getMem_name());
			pstmt.setString(3, memVo.getMem_tel());
			pstmt.setString(4, memVo.getMem_addr());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
			
		return cnt;
	}

	@Override
	public int deleteMember(String memId) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수(작업성공 : 1, 실패 : 0)
		
		try {
			
			conn = DBUtil3.getConnection();
			
			String sql_delete = "DELETE FROM mymember WHERE mem_id = ?";
			pstmt = conn.prepareStatement(sql_delete);
			pstmt.setString(1, memId);
			
			cnt = pstmt.executeUpdate();
			
			System.out.println(cnt + "행이 삭제 완료 되었습니다.");
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}

		return cnt;
	}

	@Override
	public int updateMember(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수(작업성공 : 1, 실패 : 0)
		
		try {
			
			conn = DBUtil3.getConnection();
						
			String sql_update = "UPDATE mymember SET mem_name = ?, mem_tel = ?, mem_addr = ? WHERE mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql_update);
			
			pstmt.setString(1, memVo.getMem_name()); //컨트롤러에 정보를 담았다가? 가져오는거니까 memVo에서 get해야지.
			pstmt.setString(2, memVo.getMem_tel());
			pstmt.setString(3, memVo.getMem_addr());
			pstmt.setString(4, memVo.getMem_id());
			
			cnt = pstmt.executeUpdate(); //이걸 꼭 해줘야지 쿼리문이 업데이트가 되는것.
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int update_name_Member(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수(작업성공 : 1, 실패 : 0)
		
		try {
			
			conn = DBUtil3.getConnection();
						
			String sql_update = "UPDATE mymember SET mem_name = ? WHERE mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql_update);
			
			pstmt.setString(1, memVo.getMem_name()); //컨트롤러에 정보를 담았다가? 가져오는거니까 memVo에서 get해야지.
			pstmt.setString(2, memVo.getMem_id());
			
			cnt = pstmt.executeUpdate(); //이걸 꼭 해줘야지 쿼리문이 업데이트가 되는것.
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int update_tel_Member(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수(작업성공 : 1, 실패 : 0)
		
		try {
			
			conn = DBUtil3.getConnection();
						
			String sql_update = "UPDATE mymember SET mem_tel = ? WHERE mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql_update);
			
			pstmt.setString(1, memVo.getMem_tel()); //컨트롤러에 정보를 담았다가? 가져오는거니까 memVo에서 get해야지.
			pstmt.setString(2, memVo.getMem_id());
			
			cnt = pstmt.executeUpdate(); //이걸 꼭 해줘야지 쿼리문이 업데이트가 되는것.
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	@Override
	public int update_addr_Member(MemberVO memVo) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수(작업성공 : 1, 실패 : 0)
		
		try {
			
			conn = DBUtil3.getConnection();
						
			String sql_update = "UPDATE mymember SET mem_addr = ? WHERE mem_id = ? ";
			
			pstmt = conn.prepareStatement(sql_update);
			
			pstmt.setString(1, memVo.getMem_addr()); //컨트롤러에 정보를 담았다가? 가져오는거니까 memVo에서 get해야지.
			pstmt.setString(2, memVo.getMem_id());
			
			cnt = pstmt.executeUpdate(); //이걸 꼭 해줘야지 쿼리문이 업데이트가 되는것.
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}

	
	
	
	@Override
	public List<MemberVO> getAllMemberList() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
	
		List<MemberVO> memList = null; //MemberVO객체가 저장될 List객체 변수 선언
		
		try {
			conn = DBUtil3.getConnection();
			String sql_select = "SELECT * FROM mymember";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql_select);
			
			memList = new ArrayList<>(); //List객체 생성

			while(rs.next()) {
				MemberVO memVo = new MemberVO(); //MemberVO객체 생성
				
				
				memVo.setMem_id(rs.getString("mem_id"));
				memVo.setMem_name(rs.getString("mem_name"));
				memVo.setMem_tel(rs.getString("mem_tel"));
				memVo.setMem_addr(rs.getString("mem_addr"));
				
				memList.add(memVo); //List에 MemberVO객체 추가
			}
		} catch (SQLException e) {
			memList = null;
			e.printStackTrace(); 
		}finally {
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		
		return memList;
	}

	
	@Override
	public int getMemberCount(String memID) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int cnt = 0; 
				
		try {
			conn = DBUtil3.getConnection();
						
				String sql_check = "SELECT count(*) FROM mymember WHERE mem_id = ?";
				pstmt = conn.prepareStatement(sql_check);
				pstmt.setString(1, memID);
				
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					cnt = rs.getInt(1);
				}
				
		} catch (SQLException e) {
			cnt = 0; //쿼리에 아무것도 없어도 오류내지 말아라
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {} 
		}
		
		return cnt;	
	}

	@Override
	public int updateMember2(Map<String, String> paramMap) {
		// key값 ==> 회원ID(memId), 변경할컬럼명(field), 변경할데이터(data)
		Connection conn = null;
		PreparedStatement pstmt = null;
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "UPDATE mymember SET " + paramMap.get("field") + " = ? WHERE mem_id = ?";
			//변경할컬럼명 이자리는 ''이게 들어가면 안되서 쿼리문을 끊고 자바로 겟해서 
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, paramMap.get("data"));
			pstmt.setString(2, paramMap.get("memId"));
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
		
		return cnt;
	}
	

}
