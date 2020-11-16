package kr.or.ddit.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.member.dao.MemberDaoImpl;
import kr.or.ddit.member.vo.MemberVO;
import kr.or.ddit.util.DBUtil3;

public class BoardDaoImpl implements BoardDao{

	//1번 자기자신 클래스의 참조값이 저장될걸 프라이빗으로 만들어
	private static BoardDaoImpl board;
	
	//2번
	private BoardDaoImpl() {}
	
	//3번
	public static BoardDaoImpl getInstance() {
		if(board == null) board = new BoardDaoImpl();
		return board;
	}
		
		
	@Override
	public int insertBoard(BoardVO boardVo) {
		Connection conn = null;
		ResultSet rs = null;
		PreparedStatement pstmt = null;
		int cnt = 0; // 반환값이 저장될 변수(작업성공 : 1, 실패 : 0)
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql_seq = "SELECT board_seq_test.nextVal FROM dual";
			String sql_insert = "INSERT INTO jdbc_board (board_no, board_title, board_writer, board_content, board_date) VALUES (?, ?, ?, ?, sysdate)";
			
			pstmt = conn.prepareStatement(sql_seq);
			rs = pstmt.executeQuery();
			rs.next();
			
			int seq = rs.getInt(1);
			
			pstmt.close();
			
			//그리고 다시 sql_insert 시작	
			
			pstmt = conn.prepareStatement(sql_insert);
			pstmt.setInt(1, seq);
			pstmt.setString(2, boardVo.getBoard_title());
			pstmt.setString(3, boardVo.getBoard_writer());
			pstmt.setString(4, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if(pstmt!=null) try {pstmt.close();}catch(SQLException e) {}
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(conn!=null) try {conn.close();}catch(SQLException e) {}
		}
			
		return cnt;
	}

	@Override
	public int deleteBoard(int seqNum) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int updateBoard(BoardVO boardVo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getBoardCount(int seqNum) {
		// TODO Auto-generated method stub
		return 0;
	}
	
		
}
