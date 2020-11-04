package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ListSortTest {

	public static void main(String[] args) {

		ArrayList<String> list = new ArrayList<>();
		
		list.add("일지매");
		list.add("홍길동");
		list.add("성춘향");
		list.add("변학도");
		list.add("이순신");
		
		System.out.println("정렬 전 : " + list);
		
		//정렬은 Collections.sort() 메소드를 이용하여 정렬한다.
		//문자열의 정렬은 기본적으로 오름차순으로 정렬을 수행한다.
		Collections.sort(list); 					 //가나다로 정렬됨.
		System.out.println("정렬 후 : " + list);
		
		Collections.shuffle(list); //자료를 섞어주는거 . 데이터 섞기
		System.out.println("자료 섞기 후 : " + list);
	
		
		//외부 정렬 기준 클래스를 지정해서 정렬하기
		Collections.sort(list, new Desc());
		System.out.println("내림차순 정렬 후 : "  + list);

	}

}


// 정렬 방식을 정해주는 Class를 작성한다. (외부 정렬 기준 클래스 라고 한다.)
// Comparator 인터페이스를 구현해서 작성한다.
class Desc implements Comparator<String>{
	// compare() 메소드를 재정의 해서 정렬하고자 하는 기준을 정한다. 

	// compare()메소드의 반환값
	// 반환값이 0 ==> 두 값이 값다. 
	// 반환값이 양수 ==> 앞, 뒤 값의 순서를 변경한다. 
	// 반환값이 음수 ==> 순서를 변경하지 않는다.
	
	// 예) 오름차순일 경우 ==> 앞의 값이 크면 (반환값이) 양수(가 되야한다), 같으면 0, 뒤의 값이 크면 음수
	
	// String객체에는 정렬을 위해서 compareTo()메소드가 구현되어 있는데
	// 이 메소드의 반환값은 오름차순에 맞게 반환하다록 구현 되어있다. 
	// 형식) 앞문자열.compareTo(뒤문자열);
	
	// (Wrapper클래스들,  Date, File클래스에도 구현되어 있다.)
	//==> 위 말인 즉슨, Comparator(상위객체), compare, compareTo..메소드를 쓸수 있는 거는 Wrapper한 클래스(객체)거나 date, file 클래스밖에 안된다고.
	
	
	
	
	@Override
	public int compare(String str1, String str2) { //서로 인접해있는 데이터를 비교하는데...
		//그냥 여기서 return str1.compareTo(str2); 이렇게 해주면 원래 디폴트(오름차순)대로 
		
		
		/* 
		 !!! ------if문쓸때 -----
		   아래 str1.compareTo 이거는 str1 이라는 변수 의 타입인 String안에 compareTo라는 메소드가 들어있어서 부르는 방법이 str1.blahblah 
		   그 메소드 -> compareTo 이거 불러오고 () 괄호 해주면 괄호안에거랑 비교하라는 소리
		 
		 그리고 성적 등수 구할때
		 등수가 같으면 이름으로 오름차순 정렬하라고 했잖아.
		 else if 안에서 다시 정렬하는거를 써줌 되는데 if문써서 안해도 되고 디폴트 정렬 하면됨  
		 그냥 mem. ~~ get.name    
		    
		 */
		
		
		// 내림 차순으로 정렬되도록 구현하기
//		if(str1.compareTo(str2) > 0) {
//			return -1; //음수중에 아무거나 쓰면됨  (반환값이 음수 ==> 순서를 변경하지 않는다.)
//		}else if(str1.compareTo(str2) == 0) {
//			return 0;
//		}else {
//			return 1;
//		}
		return str1.compareTo(str2); // 이건 지금 그냥 오름차순으로 구현해서 나오겠지
		//근데 위에 if문을 써서 바꿔주면 내림차순으로 나오는데
		//그걸 더 간단하게 하나만 바꿔주면 
//		return str1.compareTo(str2) * -1;
		//이렇게 하면 리턴값이 다바뀌니까. 내림차순으로 정렬되어 나옴.
		
	}
	
}












/*
 
 class Member implements Comparable<Member>{
	private int num;
	private String name;
	private String tel;
 
 	==> 위에 메소드  comparable에는 기준을 넣어주고 위에서는 int랑 String으로 이루어진, num와 name과 tel을 기준으로 넣어주고
 
   ------------
   
   	@Override
	public int compareTo(Member mem) { //현재멤버(앞에있는 데이터)하고 파라미터에 들어온 mem 데이터랑
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
		
		
  
 
 */