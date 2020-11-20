package kr.or.ddit.board2.service;

import java.util.List;

import kr.or.ddit.board2.dao.IJdbcBoardDao;
import kr.or.ddit.board2.dao.JdbcBoardDaoImpl;
import kr.or.ddit.board2.vo.JdbcBoardVO;

public class JdbcBoardServiceImpl implements IJdbcBoardService{

	private IJdbcBoardDao dao;
	private static JdbcBoardServiceImpl service;
	
	private JdbcBoardServiceImpl() {
		dao = JdbcBoardDaoImpl.getInstance();
	}
	
	public static JdbcBoardServiceImpl getInstance() {
		if(service==null) service = new JdbcBoardServiceImpl();
		return service;
	}
	
	@Override
	public int insertBoard(JdbcBoardVO boardVo) {
		return dao.insertBoard(boardVo);
	}
	@Override
	public int deleteBoard(int boardNo) {
		return dao.deleteBoard(boardNo);
	}
	@Override
	public int updateBoard(JdbcBoardVO boardVo) {
		return dao.updateBoard(boardVo);
	}
	@Override
	public List<JdbcBoardVO> getAllBoardList() {
		return dao.getAllBoardList();
	}
	@Override
	public JdbcBoardVO getBoard(int boardNo) {
		
		//보통은 서비스에선 다오 호출하고 그런일만 할거같지만
		//이렇게 뭔가 추가?하고 이런일은 서비스에서 추가함.(다오에서도 할수있지만 서비스에서 주로 함)
		int cnt = setCountIncrement(boardNo); //조회수를 증가시킨다.
		if(cnt==0) { //조회수 증가가 실패했을때, 아예 널값을 가져오게
			return null;
		}
		return dao.getBoard(boardNo);
	}
	@Override
	public List<JdbcBoardVO> getSearchBoardList(String title) {
		return dao.getSearchBoardList(title);
	}
	@Override
	public int setCountIncrement(int boardNo) {
		return dao.setCountIncrement(boardNo);
	}
	

}
