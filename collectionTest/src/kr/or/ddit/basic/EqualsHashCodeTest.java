package kr.or.ddit.basic;

import java.util.HashSet;

public class EqualsHashCodeTest {

	public static void main(String[] args) {
		Person p1 = new Person();
		p1.setId(1);
		p1.setName("홍길동");

		Person p2 = new Person();
//		p1.setId(2);
//		p1.setName("일지매");

		p2.setId(1);
		p2.setName("홍길동");
		
		Person p3 = p1;
		
		System.out.println(p1 == p2);
		System.out.println(p1 == p3);


		System.out.println(p1.equals(p2)); //상속을 안하면 object을 상속한다. object안에 여러가지가 만들어져있다.
		System.out.println("----------------------------------------------------");
		
		
		
		HashSet<Person> testSet = new HashSet<>();
		//hash라는 것이 들어가는것들은 내부적으로 객체를 비교할때 hashCode(번지를 이용해서(기반으로) 만든 중복되지 않는 값)라는걸 비교해.
		//번지로 만든거니까 p1이랑 p2랑 다르지! 
		
		testSet.add(p1);
		testSet.add(p2);
		
		System.out.println("set의 크기 : " + testSet.size());
		//그래서 2개
		
		System.out.println("p1 : " + p1.hashCode());
		System.out.println("p2 : " + p2.hashCode());
		System.out.println("p3 : " + p3.hashCode());
		// p1하고 p2도 같게 하려면 hashCode도 재정의 해줘서 같다고 해야함. 해싱알고리즘을 써야해 .근데 쉽지않아
		//그래서 이클립스에서 만들어놓은 메소드가 있어. 근데 그건 또 equals 를 재정의 같이 해줘!!!!! ㅋㅋㅋㅋ
		//보통은 equals만 재정의 해주면 되긴해. 근데 해시코드도 해줘야하면 이클립스에 있는 메소드를 써!
		
		
		
		/*
		 	- equals()메소드 ==> 두 객체의 내용이 같은지 검사
		 	- hashCode()메소드  ==> 두 객체의 동일성을 검사 
		 		- 이것도 object에서 만들어준? 메소드 - 참조값을 기반으로 만든값이 같냐
		  
		  	- HashSet, HashTable, HashMap과 같이 Hash로 시작하는 컬렉션 객체들은
		             객체의 의미상의 동일성을 비교하기 위해서 hashCode()메소드를 호출하여 비교한다.
		             그러므로, 객체가 같은지 여부를 결정하려면 hashCode()메소드를 재정의 해야한다.
		             
         	- hashCode()메소드에서 사용하는 '해싱 알고리즘'은 서로 다른 객체들에 대해서 같은 
         	  haseCode를 나타낼 수 있다.
		  
		 
		 */
		
	}

}


class Person{
	private int id;
	private String name;
	
		
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;		
	}
	
	public String getName() {
		return name;
	}	
	public void setName(String name) {
		this.name = name;		
	}
	
	@Override //재정의
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Person other = (Person) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
//	@Override
//	public boolean equals(Object obj) { 	//최상위객체인 object로 받기위해서
//		if (obj == null) return false;
//		
//		if(this.getClass() != obj.getClass()) //같은 유형의 클래스인지검사
//			return false;
//		
//		if(this == obj) //참조값이 같 - 참조값(주소값)이 같은지 검사
//			return true;
//		
//		//여기까지는 거의 항상같음 아래부터 비교하는 내용에 따라 길어질수도있고
//		
//		Person myObj = (Person)obj; //매개변수의 객체를 현재 객체 유형으로 형변환 한다.
//	
//		if(this.name == null && myObj.name != null) {
//			return false;	
//		}
//						
//		if(this.id == myObj.id && this.name == myObj.name) {
//			return true;				
//		}
//		
//		if(this.id == myObj.id && this.name.equals(myObj.name)) {
//			return true;				
//		}
//		
//		//혹시 우리가 거르지 못한게있을까봐
//		return false;
//		
//	}
//	

	
	
	
	
	
	
}




















