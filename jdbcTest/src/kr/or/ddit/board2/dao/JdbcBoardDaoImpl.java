package kr.or.ddit.board2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kr.or.ddit.board2.vo.JdbcBoardVO;
import kr.or.ddit.util.DBUtil3;

public class JdbcBoardDaoImpl implements IJdbcBoardDao{

	//Implement하고 싱글톤으로 작성해
	private static JdbcBoardDaoImpl dao;
	
	//생성자 만들기 - 프라이빗으로
	private JdbcBoardDaoImpl() { }
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null) dao = new JdbcBoardDaoImpl();
		return dao;
	}
	
	// ---------------------- 위에는 싱글톤을 만들기 위한
	
	
	// DB작업에 필요한 객체 전역 변수로 선언
	private Connection conn;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;
	//이렇게 전역변수로 해놔도 나주에 리턴하는거 잊지말아야해
	
	//private Connection conn; 회선수가 제한 되어 있으니까 디비하고 커넥되고나서 바로 연결을 끊어버려(finally를 사용해서)
	//쟤네들을 전역으로 만들던 지역으로 만들던 상관없어. 사용하고 반납을 잘해야돼. 그래서 아싸리 반납하는 메소드를 만드는 방법도 있어
	
	//사용한 자원을 반납하는 메소드
	private void disconnect() {
		if(rs!=null)try { rs.close(); } catch(SQLException e) {} 
		if(stmt!=null)try { stmt.close(); } catch(SQLException e) {} 
		if(pstmt!=null)try { pstmt.close(); } catch(SQLException e) {} 
		if(conn!=null)try { conn.close(); } catch(SQLException e) {} 
	}
	
	
	
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "insert into jdbc_board "
					+ " (BOARD_NO, BOARD_TITLE, BOARD_WRITER, BOARD_DATE" 
					+ ", BOARD_CNT, BOARD_CONTENT) "
					+ " values ( board_seq.nextval, ?, ?, sysdate, 0, ? )";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_writer());
			pstmt.setString(3, boardVo.getBoard_content());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			conn = DBUtil3.getConnection();
			String sql = "delete from jdbc_board where board_no = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "update jdbc_board set "
					+ " board_title = ?, "
					+ " board_date = sysdate, "
					+ " board_content = ? "
					+ " where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardVo.getBoard_title());
			pstmt.setString(2, boardVo.getBoard_content());
			pstmt.setInt(3, boardVo.getBoard_no());
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		List<JdbcBoardVO> boardList = null;
		try {
			conn = DBUtil3.getConnection();
			String sql = "select board_no, board_title, board_writer, "
					+ " to_char(board_date, 'YYYY-MM-DD') board_date, "
					+ " board_cnt, board_content "
					+ " from jdbc_board "
					+ " order by board_no desc ";
			stmt = conn.createStatement();
			
			rs = stmt.executeQuery(sql);
			
			boardList = new ArrayList<>();
			while(rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO();
				
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVo);
			}
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return boardList;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO boardVo = null;
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "select board_no, board_title, board_writer, "
					+ " to_char(board_date, 'YYYY-MM-DD') board_date, "
					+ " board_cnt, board_content "
					+ " from jdbc_board "
					+ " where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				boardVo = new JdbcBoardVO();
				
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
			}
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		List<JdbcBoardVO> boardList = null;
		
		try {
			conn = DBUtil3.getConnection();
			String sql = "select board_no, board_title, board_writer, "
					+ " to_char(board_date, 'YYYY-MM-DD') board_date, "
					+ " board_cnt, board_content "
					+ " from jdbc_board "
					+ " where board_title like '%' || ? || '%' "
					+ " order by board_no desc ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, title);
			
			rs = pstmt.executeQuery();
			
			boardList = new ArrayList<>();
			while(rs.next()) {
				JdbcBoardVO boardVo = new JdbcBoardVO();
				
				boardVo.setBoard_no(rs.getInt("board_no"));
				boardVo.setBoard_title(rs.getString("board_title"));
				boardVo.setBoard_writer(rs.getString("board_writer"));
				boardVo.setBoard_date(rs.getString("board_date"));
				boardVo.setBoard_cnt(rs.getInt("board_cnt"));
				boardVo.setBoard_content(rs.getString("board_content"));
				
				boardList.add(boardVo);
			}
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		
		try {
			conn = DBUtil3.getConnection();
			
			String sql = "update jdbc_board set "
					+ " board_cnt = board_cnt + 1 "
					+ " where board_no = ? ";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, boardNo);
			
			cnt = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}finally {
			disconnect();
		}
		
		return cnt;
	}

}
