package kr.or.ddit.board.dao;

import java.util.List;

import kr.or.ddit.board.vo.BoardVO;
import kr.or.ddit.member.vo.MemberVO;

public interface BoardDao {

	public int insertBoard(BoardVO boardVo);
	public int deleteBoard(int seqNum); //delete할때 boardNum 시퀀스로 만든거
	public int updateBoard(BoardVO boardVo);
	
	public List<BoardVO> getAllBoardList();
	
	public int getBoardCount(int seqNum);
	
}
