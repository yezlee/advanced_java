package kr.or.ddit.basic;

/*
 	- 제네릭을 사용하는 클래스 만드는 방법
	형식)
	class 클래스명<제네릭타입글자,....>이렇게 여러개 쓸수도 있음 그냥 하나만 쓰기도 하고
 	class 클래스명<제네릭타입글자>{
 		제네릭타입글자 변수명;				// 변수 선언에 제네릭을 사용할 경우 (public이런거 접근자 생략한거)
 		...
 		
 		제네릭타입글자 메소드명(매개변수들...){			// 메소드의 반환값으로 제네릭을 사용할 경우
 			실행문들....
 			
 			return 값;
 		} 
 		...
 		
 		반환값타입 메소드명(제네릭타입글자 변수명){ 		//메소드의 매개변수에 제네릭을 사용할 경우
 			실행문들....
 			return 값; 		 
 		}
	}
	
	
	//제네릭타입글자로 사용하는 것들(꼭 정해진건아니지만 주로)
	  
	  	T ==> Type
	  	K ==> Key
	  	V ==> Value
	  	E ==> Element
 */


//제네릭을 사용하는 class
class GenericClass<T>{ //갯수가 여러개면 ,써서 여러개 써도 되고
	private T val;
	
	public void setVal(T val) {
		this.val = val;
	}
	
	public T getVal(){
		return val;
	}
	
}




//제네릭을 사용하지 않는 class
class NonGenericClass{ //제네릭을 사용하지 않는 대신 아무거나 다 저장가능
	private Object val;
	
	public void setVal(Object val) {
		this.val = val;
	}
	
	public Object getVal() {
		return val;
	}
}


public class GenericTest {

	public static void main(String[] args) {
		NonGenericClass ng1 = new NonGenericClass(); // 클래스 객체를 만들고
		ng1.setVal("가나다라"); //setter를 이용해 초기화를 함
		
		NonGenericClass ng2 = new NonGenericClass();
		ng2.setVal(100);
		
		//위는 저장한것
		
		String str1 = (String)ng1.getVal();//꺼낼때 오브젝으로 꺼내서 형변환 해줘야함
		System.out.println("문자열 반환값 str = " + str1);
		
		Integer num1 = (Integer)ng2.getVal();
		System.out.println("정수형 반환값 num1 = " + num1);
		
		
		
		//제네릭 있는 클래스 
		GenericClass<String> gc1 = new GenericClass();
		GenericClass<Integer> gc2 = new GenericClass();
		
		gc1.setVal("우리나라");
		//gc1.setVal(100); //제네릭과 다른 데이터를 사용하면 오류난다
		
		gc2.setVal(200);
		
		String str2 = gc1.getVal();
		int num2 = gc2.getVal();
		
		System.out.println("제네릭 문자열 반환값 str2 = " + str2);
		System.out.println("제네릭 문자열 반환값 num2 = " + num2);
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
