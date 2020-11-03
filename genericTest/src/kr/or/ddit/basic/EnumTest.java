package kr.or.ddit.basic;

import javax.swing.plaf.synth.SynthSeparatorUI;

/*
  enum 열거형       ==> 서로 관련있는 상수들의 집합을 나타낸다.
			  ==> 클래스처럼 보이게 하는 상수
			  ==> 열거형은 클래스처럼 독립된 java파일에 만들 수 있고, 
			  	    하나의 java파일에 다른 클래스와 같이 만들 수있고,
			  	    클래스안에 내부 클래스처럼 만들 수 있다.
	
  - 열거형의 속성 및 메소드
  	name()	 			    ==> 열거형 상수의 이름을 문자열로 반환한다.
  	ordinal()				==> 열거형 상수가 정의된 순서값(일종의 index값)을 반환한다. (0부터 시작)
  	valueOf("열거형 상수명")  	==> 지정된 열거형에서 '열거형상수명'과 일치하는 상수를 반환한다.
         열거형이름, 상수명			==> valueOf("상수명")와 같다.
           			  
  - 열거형 선언하기
  	방법1)  enum 열거형이름{ 상수명1, 상수명2, ...}   //가장 단순한모양
  	방법2)  enum 열거형이름{
  		             상수명1(값들...),
  			    상수명2(값들...),
  			  .
  			  .
  			   상수명n(값들...);
  			 
  			 // '값들'이 저장될 변수를 선언한다. (private로 선언한다.)
  			  	private 자료형이름 변수명;
  			  	...
  			  	
  			 // 열거형의 생성자를 만들다.
  			 // 열거형의 생성자는 '열거형 상수'에 '값들'을 세팅하는 역할을 수행한다.
  			 // 열거형 생성자는 목시적(아무것도 안하면 자동적으로)으로 private이다. 다른거 쓰면 에러남. 프라이빗만 써야돼
  			 
  			 //'변수명'은 '값들'과 갯수가 같고, 각각의 '값들'과 자료형이 일치해야 한다.
  			 private 열거형이름(자료형 변수명, ...){
  			 	위에 선언된 변수들의 초기화 작업...
  			 }    		
  			 
  			 // 구성된 '값들'을 외부에서 불러올 수 있는 getter메소드를 만든다. 
  			   	(위에 선언된 변수들의 getter를 만든다.)
  		  }
 
 */


public class EnumTest {
	public enum Color { RED, GREEN, BLUE } //생긴거 클래스랑 똑같이 생김 아래 주석처리한거처럼
	/*public enum Color { 
		RED, 
		GREEN, 
		BLUE 
		}
	*/
	public enum Num { ONE, TWO, THREE } 
	
	public enum Season {
		봄(3, "5월"),			// 상수명(값들...) 형식의 선언
		여름(6, "8월"),		//
		가을(9, "11월"),
		겨울(12, "2월");
		
		private int startMonth; //값들이 저장될 변수 선언
		private String endMonth;
		
		// 생성자 - 이걸 만들면 위에 봄여름- 이거 만든거 에러 사라짐
		Season(int sMonth, String eMonth){ // = private Season(int sMonth, String eMonth){ 앞에 프라이빗 붙은거랑 같음. 뭔말이지? 
			startMonth = sMonth;
			endMonth = eMonth;
		}
		
		//getter메소드 작성
		public int getStartMonth() {
			return startMonth;
		}
		
		public String getEndMonth() {
			return endMonth;			
		}
	} 
	
	
	
	public static void main(String[] args) {
		/*System.out.println("RED : " + ConstTest.RED);
		System.out.println("THREE : " + ConstTest.THREE);
		
		if(ConstTest.RED == ConstTest.TWO) { //문법적으로는 틀린게 없지만 imp값으로 1이고 2야
			//똑같은 상수를 써서 문제점은 없지만 꼬일가능성이 많다. 그래서 상수를 일종의 클래스처럼 쓰자. => 열거형(enum)
			System.out.println("~~~~~~");
		}else {
			System.out.println("@@@@@@");
		}*/
		
		Color mycol = Color.valueOf("GREEN"); //방법1
		Color mycol2 = Color.GREEN;			  //방법2	
		
		Num myNum = Num.ONE;
		
		System.out.println("mycol : " + mycol.name());
		System.out.println("myNum : " + myNum.name());
		System.out.println();
		
		System.out.println("mycol의 oridinal : " + mycol.ordinal());
		System.out.println("myNum의 oridinal : " + myNum.ordinal());
		
		
		/*
		// 서로 다른 종류의 열거형끼리의 비교
		if(Color.BLUE == Num.THREE) {  //에러뜸!
			System.out.println("..........");
		}
		*/
		
		if(mycol == Color.RED) {
			System.out.println("같다.");
		}else {
			System.out.println("다르다.");
		}
		
		switch(mycol) {
		case RED:		//case문 옆에 지정한 '상수명'은 '열거형.상수명'에서 '열거형'이름을 빼고 작성한다. 굳이 Color.RED이렇게 안해줘. 이렇게 쓰면 에러남.
			System.out.println("red"); break;
		case BLUE:
			System.out.println("blue"); break;
		case GREEN:
			System.out.println("green"); break;
		}
		System.out.println("-------------------------------------");
		
		
//		Season ss = Season.valueOf("봄");
		//위처럼 해도되고 아래처럼 해도돼
		Season ss = Season.가을;
		System.out.println("name : " + ss.name());
		System.out.println("ordinal : " + ss.ordinal());
		System.out.println("startMonth : " + ss.getStartMonth());
		System.out.println("endMonth : " + ss.getEndMonth());
		
	
		// 열거형명.values() ==> 열거형에 있는 모든 상수들을 배열로 가져온다.
		for(Season time : Season.values()) { //이건 시즌에 있는 모든 열거형 상수들을 가져온다.
			System.out.println(time.name() + " == " + time + " ==> " + time.getStartMonth() + "월부터 " + time.getEndMonth() + "까지");
		}
		
		
		System.out.println();
		
		for(Color col : Color.values()) {
			System.out.println(col + " ==> " + col.ordinal());
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
 