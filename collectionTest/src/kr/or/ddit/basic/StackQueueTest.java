package kr.or.ddit.basic;

import java.util.LinkedList;

public class StackQueueTest {

	/*
	  Stack => Last in, First out. = 후입선출
	  Queue => First in, First out. = 선입선출
	  
	  List를 이용해서 Queue나 Stack을 사용함.
	    만들때는 리스트로 만들고, 리스트에서 제공해주는 명령어중에 스택에 관련된 명령어가 있어 그걸로 실행시키면 똑같이 작동을? 해
	    
	  Stack과 Queue는 LinkedList로 구현해서 사용할 수 있다.
	 */
	
	public static void main(String[] args) {
		// Stack 명령
		// 1. 자료 입력 : push(입력데이터);
		// 2. 자료 출력 : pop(); ==> 자료를 꺼낸온 후, 꺼내온 데이터를 Stack에서 지운다.
		// 			  peek(); ==> 삭제없이 자료를 꺼내온다.
		LinkedList<String> stack = new LinkedList<>();
		
		stack.push("홍길동");
		stack.push("일지매");
		stack.push("이순신");
		stack.push("변학도");
		System.out.println("stack 값 : " + stack); 
		//stack 값 : [변학도, 이순신, 일지매, 홍길동] ==> 넣은 순서대로 나온다. 홍길동부터 넣었으니 홍길동부터 나옴.
		
		
		String data = stack.pop();
		System.out.println("꺼내온 값 : " + data); // 꺼내온 값 : 변학도
		System.out.println("stack 값 : " + stack); // stack 값 : [이순신, 일지매, 홍길동]
		System.out.println("꺼내온 값 : " + stack.pop()); // 꺼내온 값 : 이순신
		System.out.println("stack 값 : " + stack); // stack 값 : [일지매, 홍길동]
		
		
		stack.push("성춘향");
		System.out.println("추가 후 stack 값 : " + stack); // 추가 후 stack 값 : [성춘향, 일지매, 홍길동]
		System.out.println();
		
		System.out.println("꺼내온 값 : " + stack.pop()); // 꺼내온 값 : 성춘향
		System.out.println("stack 값 : " + stack); // stack 값 : [일지매, 홍길동]
		
		System.out.println("삭제없이 꺼내온 값 : " + stack.peek()); // 삭제없이 꺼내온 값 : 일지매
		System.out.println("stack 값 : " + stack); // stack 값 : [일지매, 홍길동]
		System.out.println("-----------------------------------------------------------------");
		System.out.println();
		
		
		/*
		 	Queue명령
		 	1. 자료 입력 : offer(입력 데이터);
		 	2. 자료 출력 : poll();  ==> 자료를 꺼내온 후 꺼내온 데이터를  Queue에서 삭제한다.
		 			   peek();  ==> 삭제없이 데이터를 꺼내온
		 */
		
		LinkedList<String> queue = new LinkedList<>();
		
		queue.offer("홍길동");
		queue.offer("일지매");
		queue.offer("이순신");
		queue.offer("변학도");
		
		System.out.println("queue 값 : " + queue); //queue 값 : [홍길동, 일지매, 이순신, 변학도] 
		// 홍길동 부터 넣어서 홍길동부터 나오는거야. 파이프처럼. 출구가 왼쪽인거라고 생각.
		
		String temp = queue.poll();
		System.out.println("꺼내온 값 : " + temp); // 꺼내온 값 : 홍길동
		System.out.println("꺼내온 값 : " + queue.poll());  // 꺼내온 값 : 일지매
		//이거랑 위에거랑 같은말
		System.out.println("queue 값 : " + queue); // queue 값 : [이순신, 변학도]
		
		queue.offer("성춘향");
		System.out.println("추가 후 queue 값 : " + queue); // 추가 후 queue 값 : [이순신, 변학도, 성춘향]
		System.out.println("꺼내온 값 : " + queue.poll());  // 꺼내온 값 : 이순신
		System.out.println("queue 값 : " + queue); // queue 값 : [변학도, 성춘향]
		System.out.println();
		
		System.out.println("삭제없이 꺼내온 값 : " + queue.peek()); //삭제없이 꺼내온 값 : 변학도
		System.out.println("queue 값 : " + queue); // queue 값 : [변학도, 성춘향]
		

		//Stack을 사용하는 프로그램기능
		//되돌리기 기능도 스택중 하나. 작업한거는 스택에 다 저장이 되다가. 되돌리기하면 최근에 했던거부터 하나둘씩 나옴.
		//메소드 호출하면 그게 콜. 그걸 관리하는 스택이라서 콜스택. a라는 메소드를 호출하면 ....
		//웹브라우저 - 뒤로가기, 앞으로가기
		
		//Queue는 프린트. 프린트중인데 또 프린트하라고 명령을 내릴수있지.
		//명령한 순서대로 나와. 
		
		
		
		
		
		
	}

}
