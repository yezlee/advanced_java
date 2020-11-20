package kr.or.ddit.board2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import kr.or.ddit.board2.vo.JdbcBoardVO;
import kr.or.ddit.util.BuiltSqlMapClient;

public class JdbcBoardDaoImpl implements IJdbcBoardDao{

	private SqlMapClient smc; //iBatis용 sqlMapClient객체 변수 선언
	
	//Implement하고 싱글톤으로 작성해
	private static JdbcBoardDaoImpl dao;
	
	//생성자 만들기 - 프라이빗으로
	private JdbcBoardDaoImpl() { 
		
		smc = BuiltSqlMapClient.getSqlMapClient();
	}
	
	public static JdbcBoardDaoImpl getInstance() {
		if(dao==null) dao = new JdbcBoardDaoImpl();
		return dao;
	}
	
	// ---------------------- 위에는 싱글톤을 만들기 위한
	
			
	
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		try {
			Object obj = smc.insert("myboard.insertBoard", boardVo); 
			//<sqlMap namespace="myboard"> 여기 마이보드.insertBoard임. myboard.xml에서 마이보드가 아니고
			if(obj==null) cnt = 1; //insert는 실행에 성공하면 null을 반환한다(ibatis는)
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int cnt = 0;
		try {
			cnt = smc.delete("myboard.deleteBoard", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		} 
		return cnt;
	}

	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("myboard.updateBoard", boardVo);
			
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
			boardList = smc.queryForList("myboard.getAllBoardList");
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		return boardList;
	}

	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		JdbcBoardVO boardVo = null;
		try {
			boardVo = (JdbcBoardVO)smc.queryForObject("myboard.selectGetboard", boardNo);
			
		} catch (SQLException e) {
			boardVo = null;
			e.printStackTrace();
		} 
		return boardVo;
	}

	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		List<JdbcBoardVO> boardList = null;
		
		try {
			boardList = smc.queryForList("myboard.getSearchBoardList", title);
			
			
		} catch (SQLException e) {
			boardList = null;
			e.printStackTrace();
		} 
		return boardList;
	}

	@Override
	public int setCountIncrement(int boardNo) {
		int cnt = 0;
		
		try {
			cnt = smc.update("myboard.setCountIncrement", boardNo);
			
		} catch (SQLException e) {
			cnt = 0;
			e.printStackTrace();
		}
		
		return cnt;
	}

}
