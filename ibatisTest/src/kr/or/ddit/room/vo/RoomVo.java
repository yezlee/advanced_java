package kr.or.ddit.room.vo;
/**
 * VO는 DB테이블에 있는 컬럼을 기준으로 데이터를 객체화할 클래스이다.
 * DB테이블의 '컬럼명'이 이 VO클래스의 '멤버변수명'이 된다.
 * 
 * DB테이블의 컬럼과 클래스의 멤버변수를 매핑하는 역할
 */
public class RoomVo {
	
	private int rm_num;
	private int rm_code;
	private String guest_name;
	private String rm_check;
	private String rm_type;
	
	//만약 기본생성자 이외의 생성자를 생성할 때에는 반드시 기본 생성자도 생성해 주도록 한다.
	
	public int getRm_num() {
		return rm_num;
	}
	public void setRm_num(int rm_num) {
		this.rm_num = rm_num;
	}
	public int getRm_code() {
		return rm_code;
	}
	public void setRm_code(int rm_code) {
		this.rm_code = rm_code;
	}
	public String getGuest_name() {
		return guest_name;
	}
	public void setGuest_name(String guest_name) {
		this.guest_name = guest_name;
	}
	public String getRm_check() {
		return rm_check;
	}
	public void setRm_check(String rm_check) {
		this.rm_check = rm_check;
	}
	public String getRm_type() {
		return rm_type;
	}
	public void setRm_type(String rm_type) {
		this.rm_type = rm_type;
	}
	
}
