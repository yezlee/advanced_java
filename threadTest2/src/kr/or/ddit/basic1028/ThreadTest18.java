package kr.or.ddit.basic1028; //1102 - 1


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

/*
 	(일종의 소개....하고 넘어감)
 	 
  	Vector, HashTable(Map이랑 거의 같음) 등의 예전부터 존재하던 Collection객체들은 내부에 동기화 처리 되어있다.
  	
  	그런데, 최근에 새로 구성된 Collection들은 동기화 처리가 되어 있지 않다. - Map, set..
  	따라서 동기화가 필요한 프로그램에서 이런 Collection들을 사용하려면 동기화 처리를 한 후에 사용해야 한다.
  	  
 */

public class ThreadTest18 {

	public static void main(String[] args) {
		Vector<Integer> vec = new Vector<>();
		
		//동기화 처리가 되지않은 List
		List<Integer> list1 = new ArrayList<>(); // 스레드를 사용하지 않는 곳에선 이렇게만
		
		//동기화 처리를 한 경우
		List<Integer> list2 = Collections.synchronizedList(new ArrayList<>()); //이게 동기화 처리 하는거 , 스레드를 사용하는 곳에선 동기화를 해줌
		
				
		//익명 구형체로 스레드 구현
		Runnable r = new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0; i < 10000; i ++) {
				//	vec.add(i);					
				//	list1.add(i);					
					list2.add(i);					
				}
			}
		};
		
		//------------------------------
		
		Thread[] ths = new Thread[] {
			new Thread(r), new Thread(r), new Thread(r), new Thread(r), new Thread(r)	
		};
		
		for(Thread th : ths) {
			th.start();
		}
		
		for(Thread th : ths) {
			try {
				th.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
		System.out.println("vec의 개수 : " + vec.size());
		System.out.println("List1의 개수 : " + list1.size());
		System.out.println("List2의 개수 : " + list2.size());
		
	}

}
