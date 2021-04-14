package kr.or.ddit.basic;
// javaDoc 파일 만들기 예제

/**
 * 
 * @author 홍길동
 * @version 1.0
 * 
 * 여기서 쓰는 내용은 매뉴얼에 들어가는 내용이라 생각하면됨
 * <p>
 * 파일명 : JavaDocTestInf.java<br>
 * 설명 : JacaDoc문서 작성을 위한 연습용 Interface<br><br>
 * 
 * 수정이력<br>
 * ---------------------------------<br>
 * 수정일자 : 2020-11-11<br>
 * 작 성 자 : 홍길동 <br>
 * 수정내용 : 최초 생성<br> 
 * ---------------------------------<br>
 * </p>
 */
public interface JavaDocTestInf {
	
	
	/**
	 * 메소드명 : methoTest
	 * 설명 : 반환값이 없는 메소드
	 * @param a 첫번째 매개변수 (정수형)
	 * @param b 두번째 매개변수 (정수형)
	 */	
	public void methodTest(int a, int b);
	
	
	/**
	 * 메소드명 : methodAdd
	 * 설명 : 반환값이 있는 메소드
	 * @param x 정수형 첫번째 매개변수
	 * @param y 정수형 두번째 매개변수
	 * @return 처리된 결과가 정수형으로 반환된다.
	 */
	public int methodAdd(int x, int y);
	
	
	/**
	 * 메소드명 : methodSub
	 * 설명 : 매개변수가 없는 메소드
	 * @return 정수형으로 반환한다.
	 */
	public int methodSub();
}
