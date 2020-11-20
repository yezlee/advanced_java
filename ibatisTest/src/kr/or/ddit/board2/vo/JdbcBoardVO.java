package kr.or.ddit.board2.vo;

public class JdbcBoardVO {
	private int board_no;
	private String board_title;
	private String board_writer;
	private String board_date;
	private int board_cnt;
	private String board_content;
	
	/*
	  id속성 ==> Java프로그램에서 실행할 쿼리문을 호출할 때 사용되는 이름으로
 	 			<sqlMap>태그의 namespqce속성값과 연결해서 사용한다.
 	 			(예 : lprod.insertLprod)
 	 			
	  parameterClass속성 ==> SQL문에 사용될 데이터가 들어있는 객체를 기술한다.
 	 			(보통 VO클래스, 자바의 자료형이름등이 사용된다.)
				(VO클래스 등을 기술할 때 해당 클래스의 전체이름(패키지명까지 포함된 이름)을 기술해야한다.
	 */
	
	
	
	
	//VO - Value Object 
	
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getBoard_title() {
		return board_title;
	}
	public void setBoard_title(String board_title) {
		this.board_title = board_title;
	}
	public String getBoard_writer() {
		return board_writer;
	}
	public void setBoard_writer(String board_writer) {
		this.board_writer = board_writer;
	}
	public String getBoard_date() {
		return board_date;
	}
	public void setBoard_date(String board_date) {
		this.board_date = board_date;
	}
	public int getBoard_cnt() {
		return board_cnt;
	}
	public void setBoard_cnt(int board_cnt) {
		this.board_cnt = board_cnt;
	}
	public String getBoard_content() {
		return board_content;
	}
	public void setBoard_content(String board_content) {
		this.board_content = board_content;
	}
	
	
}
