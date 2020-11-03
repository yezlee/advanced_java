package kr.or.ddit.basic;

public class ArgsTest {

	
	/*
	 	아래 방법이 좀 별로?여서, 불편한점이 있어서 자바에서는 이런걸 알려?줘
	 	가변형인수 ==> 메소드의 매개변수에 주어지는 인수의 개수가 실행될 때마다 다를때 사용한다.
	 		   - 가변형 인수는 메소드 내부에서는 배열로 처리한다.
	 		   - 가변형 인수는 한가지 자료형만 사용 가능하다.
	  
	 */		
	
	public static void main(String[] args) {
		ArgsTest test = new ArgsTest(); // 클래스를 호출해서 써야해 저 아래 저거 쓰고 싶으면
		
		int[] nums = {100, 200, 300};
		
		System.out.println(test.sumData(nums)); //nums 이걸 인수값이라고 해
		//이게 sumdata의 반복문에다가 num는 넣고.........
		
		
		int[] nums2 = new int[] {100,200,300};
		
		System.out.println(test.sumData(new int[] {1,2,3,4,5})); //data를 부르고 싶음 어떡해???, data라고 쓰는게 아니고 이렇게 다 넣어줘야함
		System.out.println("----------------------------------");
		
		System.out.println(test.sumArgs(100,200,300));
		System.out.println(test.sumArgs(1,2,3,4,5));
		System.out.println(test.sumArgs2("이순신", 80,70,90,100,150,20)); // 숫자는 얼마든지 와도돼
		
		
		
		//이런 방법도 있어
//		int k = 100;
//		test.test1(k);
//		
//		test.test1(300);
		

	}
	
	//
	public void test1(int a) {
		// TODO Auto-generated method stub
		
	}


	//매개변수들의 합을 계산해서 반환하는 메소드
	public int sumData(int[] data) { //data에 배열로 데이터가 들어있다고 생각하고
		//배열을 매개변수로 쓰고 싶으면 public int sumData(매개변수) -> int[] data
		
		int sum = 0;
		for (int i = 0; i <data.length; i++) {
			//sum += data[i];
			sum = sum + data[i];
		}
		
		return sum;	
	}
	
	
	//매개변수들의 합을 계산해서 반환하는 메소드 ==> 가변형 인수를 이용한 메소드
	public int sumArgs(int...data) {
		//이 메소드안에서 변수 'int...data'는 'int[] data'와 같다. 즉 배열과 같다.
		
		int sum = 0;
		for (int i = 0; i <data.length; i++) {
			//sum += data[i];
			sum = sum + data[i];
		}
		
		return sum;	
	}
		
		
	//가변형 인수와 일반 인수를 같이 사용할 경우에는 가변형 인수를 뒤쪽에 배치한다.
	public String sumArgs2(String name, int...data) { //인트랑 스트링 순서 바꾸면 안돼. 일반인수가 앞에오고, 가변형 인수가 뒤에 와야해!!!!!!!*** 그리고 위에 적었듯이 한가지 자료형밖에 못와.  
		//public String sumArgs2(int name, int k, int b, int...data) { // int...data 이것만 맨뒤에오면 앞에 변수?인수? 가 여러개 올수있어  
		
		int sum = 0;
		for (int i = 0; i <data.length; i++) {
			//sum += data[i];
			sum = sum + data[i];
		}
		
		return name + "님의 합계 : " + sum;
	}
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

