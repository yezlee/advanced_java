package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest2 {

	public static void main(String[] args) {
		ArrayList<Member> memList = new ArrayList<>();
		
		memList.add(new Member(1, "홍길동", "010-1111-1111"));
		memList.add(new Member(5, "이순신", "010-2222-1111"));
		memList.add(new Member(10, "성춘향", "010-3333-1111"));
		memList.add(new Member(3, "강감찬", "010-4444-1111"));
		memList.add(new Member(6, "일지매", "010-5555-1111"));
		memList.add(new Member(2, "변학도", "010-6666-1111"));

		System.out.println("처음 데이터...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------------");
		
		Collections.sort(memList);
		
		System.out.println("회원 이름의 오름차순 정렬 후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------------");
		
		
		Collections.sort(memList, new SortNumDesc());
		
		System.out.println("회원 번호의 내림차순 정렬 후...");
		for(Member mem : memList) {
			System.out.println(mem);
		}
		System.out.println("--------------------------------------------");
		
	}

}



//아까 comparator은 외부정렬 데이터
//지금 배우는건 내부정렬
//사용자가 작성하는 클래스 내부에 정렬기준을 지정하려면 Comparable인터페이스를 구현해서 작성해야한다.
//(내부 정렬기준을 넣어서 클래스를 작성한다.)

//Comparable 인터페이스는 compareTo()메소드가 선언되어 있다.
//그래서 이 CompareTo()메소드를 재정의해서 정렬 기준을 지정한다.

//예) Member클래스의 회원 이름을 기준으로 오름차순이 되도록 하는 내부 정렬 기준 추가하기
//	 ()
class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;
	//아래처럼 생성자 만드는 단축키 alt+shift+s -> Generate Constructor Using Fields  
	public Member(int num, String name, String tel) {
		super();
		this.num = num;
		this.name = name;
		this.tel = tel;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	@Override
	public String toString() {
		return "Member [num=" + num + ", name=" + name + ", tel=" + tel + "]";
	}
	
	//toString 해주는 이유는 
	//안해주면 출력했을때 그냥 주소값만 나오고 문자가 안나와. 
	
	@Override
	public int compareTo(Member mem) { //현재멤버(앞에있는 데이터)하고 파라미터에 들어온 mem 데이터랑
		return this.getName().compareTo(mem.getName());
	}//string은 그 자체가 wrapper class라서 상위객체가 없다. 그 자체로 compare라는 메소드를 쓸수있다.
	//char는 Character로, int는 Integer로 형변환 해줘야함.
	//return new Integer(this.getStu_num()).compareTo(stu.getStu_num());
	//위처럼
	
}



// Member 클래스의 회원번호(num)의 내림차순으로 정렬할 외부 정렬 기준 클래스 작성
// 바로 위에 이름으로 정렬하겠다고 해줘서. 다른 걸로 정렬 하고 싶으면 외부정렬을 사용해야돼. 

class SortNumDesc implements Comparator<Member>{
	@Override
	public int compare(Member mem1, Member mem2) {
//		if(mem1.getNum() > mem2.getNum()) {
//			return -1;
//		}else if(mem1.getNum() == mem2.getNum()) {
//			return 0;
//		}else {
//			return 1; //양수를 반환하면 순서가 바뀐다! 그것만 잘 기억하자.
//		}
		
		// Wrapper 클래스를 이용하는 방법 1. (2가지가 있음)
//		return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
		
		// Wrapper 클래스를 이용하는 방법 2. (2가지가 있음)
		return Integer.compare(mem1.getNum(), mem2.getNum() * -1);
	}
}

/*
 compare할때 위에처럼 그냥 int자체(데이터 자체)를 비교하거나
 wrapper클래스를 이용해서 할수 있는데 두가지 방법이 있다. 
 
 1.
 return new Integer(mem1.getNum()).compareTo(mem2.getNum()) * -1;
==> 이건 그냥 식처럼 외워야함. mem1.getNum()를 Integer로 wrapper클래스로 만들어줌(형변환처럼)
    그리고 그렇게 만들어줌 클래스에서 compareTo하는 거지. mem2.getNum() 이거랑.
     
 
 
  
 */





/*

class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;

	==> 위에 메소드  comparable에는 기준을 넣어주고 위에서는 int랑 String으로 이루어진, num와 name과 tel을 기준으로 넣어주고

  ------------
  
  	@Override
	public int compareTo(Member mem) {                        //현재멤버(앞에있는 데이터)하고 파라미터에 들어온 mem 데이터랑 --뭔말이지?
		return this.getName().compareTo(mem.getName());
	}
  
  
   ==> compareTo 에는 () 파라미터 안에는 mem 이 들어가있지. 그게 위에  <Member>이거를 mem으로 변수를 준거고. 
       Member안에 들어있는 그 기준 3개. 를 우선 파라미터로 넣어주면서
                 리턴해줘서 이름으로. -> 이름을 기준으로 삼고 컴페어해라. 
  
  	즉, compareTo에서 return값은 파라미터 안에 있는 기준들 중에서 하나를 정해주는 그런 메소드
  	
  	
  	---------------
  	
  	
  	@Override
	public int compare(String str1, String str2) { //서로 인접해있는 데이터를 비교하는데...
						
	==> compare는 파라미터값에 들어있는 두개를 비교하는 메소드
	  	return str1.compareTo(str2); 그냥 바로 이렇게 리턴해주면 
	  	단순히 저 두개 값을 오름차순으로 정렬해서 출력해주는 거고
	  	
	  	이걸 내림차순으로 정렬하고 싶을때
	  	if문 써서 바꿔주거나
	  	그냥 -1곱해주면 오름차순으로 정렬되는거지.
		
		그 if문은
		// 내림 차순으로 정렬되도록 구현하기
		if(str1.compareTo(str2) > 0) {
			return -1; //음수중에 아무거나 쓰면됨  (반환값이 음수 ==> 순서를 변경하지 않는다.)
		}else if(str1.compareTo(str2) == 0) {
			return 0;
		}else {
			return 1;
		}
 

*/



