package kr.or.ddit.basic;

import java.util.Vector;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class VectorTest {

	public static void main(String[] args) {
		Vector v1 = new Vector();
		
		System.out.println("크기 : " + v1.size());
		
		//데이터 추가 : add(추가할 데이터)
		//반환값 : 추가성공(true) , 실패(false)
		
		v1.add("aaaa");
		v1.add(new Integer(111));
		v1.add(123L); //자동으로 위에처험 wrapper 해준다그래 
		//자동으로 객체화해주는걸 오토박싱.
		
		v1.add('a');
		v1.add(true);
		
		boolean r = v1.add(3.14f);
		
		System.out.println("현재크기 : " + v1.size());
		System.out.println("반환값 : " + r);
		
		
		
		//데이터 추가 2 : add라는 메소드인데 예전에는 addElement를 썼어(추가할 데이터) ==> 이전 버전에서 사용하던 메소드
		//이전 버전의 프로그램도 사용할 수 있도록 하기 위해서 남아 있는 메소드(vector)
		v1.addElement("CCC");
		
		System.out.println("v1 : " +  v1.toString()); 
		System.out.println("v1 : " +  v1); //위아래 결과가같다
		
		
		
		//데이터 추가 3 : add(index, 데이터)
		// ==> 'index'번째에 '데이터'를 끼워 넣는다.
		// ==> 'index'는 0부터 시작한다.
		// ==> 반환값이 없다.
	 	
		//배열 중간에 데이터를 끼워넣고싶으면
		v1.add(1,"kkk");
		System.out.println("v1 : " + v1);
		
		
		
		//데이터 수정 : set(index, 새로운 데이터)
		// ==> 'index'번째 데이터를 '새로운 데이터'로 덮어쓴다.
		// ==> 반환값 : 원레의 데이터 (변경전 데이터)
		String temp = (String)v1.set(0, "zzz");
		System.out.println("v1 : " + v1);
		System.out.println("반환값(원래의 데이터) : " + temp);
       		
		
		
		//데이터 삭제 : remove(index)
		// ==> 'index'번째의 데이터를 삭제한다.
		// ==> 반환값 : 삭제의 데이터
		
		v1.remove(0);
		System.out.println("삭제 후 v1" + v1);
		
		temp = (String)v1.remove(0);
		System.out.println("삭제된 자료 : " + temp);
		System.out.println("삭제 후 v1 : " + v1);
		
		
		
		//데이터 삭제 : remove(삭제할 데이터)
		// ==> '삭제할 데이터'를 찾아서 삭제한다.
		// ==> '삭제할 데이터'가 여러개이면 앞에서부터 한번에 하나씩 삭제된다.
		// ==> 반환값 : 삭제성공(true), 삭제실패(false)
		// ==> 삭제할 데이터가 '정수형'이거나 'char'형 일경우에는 반드시 객체형으로 변환해서 사용해야한다.
		
		
		v1.remove("CCC");
		System.out.println("삭제 후 v1 : " + v1);
		
		//v1.remove(123);
		
		// Array index out of range: 123
		// 이건 에러뜨는데 이유는, 데이터 123번째를 지워라 .라고 해석을해서
		// 이건 오토박싱이 안된거야. 그래서 삭제를 하려면 객체화 시켜줘야해.
		//그래서 이렇게
		v1.remove(new Integer(123));
		System.out.println("삭제 후 v1 : " + v1); 
		
		
		//v1.remove('a');
		// Array index out of range: 97
		// a문자의 코드의 숫자를 읽어서! 그래서 에러가남 이것도 객체형을 변환해서 사용해야돼.
		v1.remove(new Character('a'));
		System.out.println("삭제 후 v1 : " + v1); 

		
		v1.remove(3.14);
		v1.remove(true);
		System.out.println("삭제 후 v1 : " + v1);
		
		
		
		
		
		//데이터 꺼내오기 : get(index)
		// ==> 'index'번째 데이터를 반환한다.
		int data = (int)v1.get(0);
		System.out.println("0번째 자료 : " + data);
		
		
		
		
		//-------------------------------------------------------
		
		/*
		
		제네릭타입(Generic Type) 
		==> 객체를 선언할 때 <> 괄호 안에 그 Collection이
		         사용할 데이터의 타입을 정해주는 것을 말한다.
	                  이런식으로 객체를 선언하게 되면 그 데이터 타입 이외의 다른 종류의 데이터를 저장할 수 있다.
	                  단, 제네릭으로 선언 될 수 있는 데이터 타입은 클래스형이어야 한다.
	                  그래서, int는 Integer, boolean은 Boolean, char은 Character등으로 
	                  대체해서 사용해야한다.
			제네릭 타입으로 선언하게 되면 데이터를 꺼내올 때 형변환이 필요하다.
		*/

		Vector<String> v2 = new Vector<String>(); //String만 저장할 수 있는 Vector객체 선언
		Vector<Integer> v3 = new Vector<>(); //int형 데이터만 저장할 Vector객체 선언
		
		v2.add("안녕하세요");
		// v2.add(123); //오류 : 제네릭타입과 다른 종류의 데이터를 저장할 수 없다.
		
		String temp2 = v2.get(0);
		
		Vector<Vector> vv = new Vector<>();
		Vector<Vector<Vector>> vvv = new Vector<>(); //3차원배열처럼 이렇게 할 수도 있어. 물론 잘 안씀..
		System.out.println("v2의 clear 전 size : " + v2.size());
		
		
		// 데이터 전체 삭제 : clear() 몇개가 됐던 한번에 다 지우는 메소드
		v2.clear();
		System.out.println("v2의 clear 후 size : " + v2.size());
		System.out.println("------------------------------------------------------");
		
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		
		Vector<String> v4 = new Vector<>();
		v4.add("BBB");
		v4.add("EEE");
				
		System.out.println("v2 : " + v2);
		System.out.println("v4 : " + v4);

		
		//데이터 부분 삭제 : removeAll(Collection객체) 
		//removeAll은 괄호안에 있는 거를 다 지워라.
		// ==> 'Collection객체'가 가지고 있는 데이터를 Vector에서 찾아서 모두 삭제한다.
		// ==> 반환값 : 작업성공(true), 작업실패(false)
		v2.removeAll(v4);
		System.out.println("삭제 후 v2 : " + v2);
		System.out.println("삭제 후 v4 : " + v4);
		System.out.println("------------------------------------------------------");

		v2.clear();
		v2.add("AAA");
		v2.add("BBB");
		v2.add("CCC");
		v2.add("DDD");
		v2.add("EEE");
		
				
		//벡터의 데이터를 순서대로 가져와 모두 사용하고 싶으면 반복문을 사용하면 된다.
		// for문도 while문도 상관없지만 주로 for문을 사용한다.
		
		for(int i = 0; i < v2.size(); i++){
			System.out.println(i + "번째 자료 : " + v2.get(i));			
		}
		
		
		//향상된 for문
		for(String str : v2) { // v2가 배열이 될수도 있어. 그 갯수만큼/. 첫번째 데이터를 꺼내오고 str에 저장해서 실행하고 그 안에있는거 다 할때까지
			//인덱스를 사용하지 않고 도 데이터를 꺼낼수 있음. 인덱스를 쓸수 없음. 위에 i처럼.
			//무조건 순서대로 데이터만 꺼내옴. 선택해서 꺼낼수 없음. 무조건 순서대로.
			System.out.println(str);
		}
		
		
		
		
		
		
	}

}
