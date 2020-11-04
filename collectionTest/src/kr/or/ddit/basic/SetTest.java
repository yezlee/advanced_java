package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;


public class SetTest {

	public static void main(String[] args) {
	/*
		 - List와 Set의 차이점
		 
		 1. List의 특징(책꽂이에 책이 순서대로 있는거)
		 	- 데이터의 순서(index)가 있다.
		 	- 중복되는 데이터를 저장할 수 있다.
		 	
	 	 2. Set의 특징(주머니에 책이 순서 없이 섞여있는거)
	 	 	- 데이터의 순서(index)가 없다
		 	- 중복되는 데이터를 저장할 수 없다. 	
	 */
		
		HashSet hs1 = new HashSet<>();
		
		//Set에 데이터르 추가할 때 add()메소드를 사용한다.
		//object를 저장해주지 않으면 아래처럼 막 다 저장할 수 있어
		//ass()메소드의 반환값 : 추가성공(true), 추가실패(false)
		hs1.add("DD");
		hs1.add("AA");
		hs1.add(2);
		hs1.add("CC");
		hs1.add("BB");
		hs1.add(1);
		hs1.add(3);
		
		System.out.println("Set 데이터 : " + hs1);
		//Set 데이터 : [DD, AA, CC, BB, 1, 2, 3] 순서가 없어 근데 나름대로 꺼내오는 순서가 있는지.. 이렇게 나옴.
		System.out.println("Set의 갯수 : " + hs1.size());
		System.out.println();
		
		//Set에는 중복되는 데이터를 추가하면 false를 반환하고 데이터는 추가되지 않는다.
		boolean isAdd = hs1.add("FF");
		System.out.println("중복되지 않을 때 : " +  isAdd);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		isAdd = hs1.add("CC"); //중복되는 데이터를 넣어보자
		System.out.println("중복될 때 : " + isAdd);
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
		
		//+ 얘는 수정하는 기능이 없어, 왜? 몇번째에 뭐가 있는지 모르거덩
		//Set의 데이터를 수정하려면 수정하는 명령이 따로 없기 때문에
		//수정하려는 데이터를 삭제한 후에 새로운 데이터를 추가하는 방법을 사용한다.
		
		//삭제하는 메소드 : remove(삭제할데이터)
		//		반환값 : 삭제성공(true), 삭제실패(false)
		//전체 자료 삭제 메소드 : clear()
		
		//"FF"데이터를 "EE"로 변경하기 - remove는 그냥 삭제이고, add는 그냥 추가한거
		hs1.remove("FF");
		System.out.println("삭제 후 Set 데이터 : " + hs1);
		hs1.add("EE");
		System.out.println("Set 데이터 : " + hs1);
		System.out.println();
		
//		hs1.clear();
//		System.out.println("clear 후 Set 데이터 : " + hs1);
//		System.out.println();
		
		/*
		 	Set의 데이터는 순서(index)가 없기 때문에 List처럼 index로
		 	데이터를 하나씩 불러올 수 없다.
		 	그래서 데이터를 하나씩 얻기 위해서는 Iterator형의 객체로 변환해야 한다.
		 	
		 	- Set형의 데이터들을 Iterator형 객체로 변환해 주는 메소드 ==> Iterator()
		 	켈럭션에서 출력하는 과정을 동일한 메커니즘으로 처리할수있게 도와주는 메소드
		 	예전엔 무조건 사용해야했는데 요즘은 향상된 포문으로 그게 더쉬워서 잘 사용안함
		 */
		
			Iterator it = hs1.iterator();
			
			//Iterator의 hasNext()메소드
			//	=> Iterator의 포인터가 가리키는 곳의 다음번째 자리에 데이터가 있으면  true, 없으면 false를 반환한다.
			
			//Iterator의 next()메소드
			//	=> Iterator의 포인터를 다음번째 위치로 이동한 후 그 곳의 데이터를 반환한다.
			
			while(it.hasNext()){
				System.out.println(it.next());
			}
			System.out.println("----------------------------------------");
			
			
			//----------------------------------------

			//향상된 for문을 사용하면  Iterator를 사용하지 않고 처리할 수 있다.
			for(Object obj : hs1) {
				System.out.println(obj);
			}
			
			//----------------------------------------
			
			
			//우리반 학생들 중 번호를 이용하여 추첨하는 프로그램을 작성하기(Set을 사용해서 만들면 진짜 쉽다!)
			//번호는 1번부터 25번까지 있고, 추첨할 인원은 3명이다.
			//당첨자를 출력하시오.
			// ==> 1부터 25사이에 중복되는 난수 3개 만들기
			
			HashSet<Integer> testSet = new HashSet<>();
			
			while(testSet.size()<3) {
				testSet.add( (int)(Math.random() * 25 - 1 + 1 ) + 1 );
			}
			System.out.println("당첨자  번호 : " + testSet);
			
			
			//난수만드는것도 하나의 공식이 있음. 
			/*
			  	최소값 ~ 최대값 사이의 정수형 난수 만들기
			  	(int)(Math.random() * (최대값 - 최소값 + 1) + 최소값)
			 */
			
			// Set유형의 자료를 List형으로 변환하기
			ArrayList<Integer> testList = new ArrayList<>(testSet);	//testSet으로 세팅하라 라고 넣어준거???????
			
			System.out.println("List 데이터 출력..");
			for(int i = 0; i < testList.size(); i++) {
				System.out.println(testList.get(i));
				
			}
		
		
	}
}
	




