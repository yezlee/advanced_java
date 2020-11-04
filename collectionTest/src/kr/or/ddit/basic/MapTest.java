package kr.or.ddit.basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;



public class MapTest {

	public static void main(String[] args) {
		/*
		 
		 Map  ==> Key값과 value값을 한 쌍으로 관리하는 객체
		  
		 - key값은 중복을 허용하지 않고 순서(index)가 없다. (Set의 특징을 갖고 있다.)
		 - value값은 중복을 허용한다.
		  
		 		 
		 */

		HashMap<String, String> map = new HashMap<>(); //앞에 키값, 뒤에 밸류값<K,V> 여기선 둘다 string으로 한거
		
		//자료 추가하기 ==> put(key값, value값)
		map.put("name", "홍길동");
		map.put("addr", "대전");
		map.put("tel", "010-1234-5678");
		
		System.out.println("map의 데이터 : " + map); //순서는 지멋대로 나옴-> map의 데이터 : {name=홍길동, tel=010-1234-5678, addr=대전}
		
		
		
		//자료 수정하기 ==> Map의 데이터를 수정하려면 put()메소드의 key값이 같게해서 추가한다.
		//		   ==> Map은 추가할 때 key값이 같으면 나중에 추가한 데이터가 남는다. -> 덮어쓰기 하면 그게 수정
		map.put("addr", "서울");
		System.out.println("map의 데이터 : " + map);
		
		
		
		//자료 삭제하기 ==> remove(key값) : key값이 같은 자료를 찾아서 삭제한다.
		//					     반환값    : 삭제된 데이터의 value값이 반환된다. 
//		String removeTel = map.remove("tel");
//		System.out.println("삭제 후 map : " + map);
//		System.out.println("삭제된 value값 : " + removeTel);
		
		
		
		//자료 읽기 ==> get(key값) : key값이 같은 자료의 value값을 반환한다.
		// 		==> key값이 없을 때는 null을 반환한다. null이 뜨지않게, 즉 키값이 있나없나 확인 먼저 하기 위해서 아래 contains~를 쓴다. 
		System.out.println("이름 : " + map.get("name"));
		System.out.println("전화 : " + map.get("tel"));
		System.out.println("주소 : " + map.get("addr"));
		System.out.println();
		
		
		
		//key값의 존재여부를 확인하는 메소드 : containsKey(key값)
		// 		==> 해당 key값이 있으면 true, 없으면 false를 반환한다. 
		System.out.println("name 키값의 존재 여부 : " + map.containsKey("name"));
		System.out.println("age 키값의 존재 여부 : " + map.containsKey("age"));
		
		
		
		//Map에 저장된 데이터를 차례로 읽어와 데이터를 처리하는 방법
		
		//방법1> keySet()메소드 이용하기  !!!!예전엔 이걸 많이 썼음. 이것도 기억해야함!!! 숙지!!!!!!!!!!!
		//      keySet() ==> Map의 key값을들 읽어와 Set형으로 반환한다.
		Set<String> keySet = map.keySet(); // 키값들만 셋에 들어가 있음
	
		
		Iterator<String> it = keySet.iterator();
		while(it.hasNext()) {	
			String key = it.next(); //key값 가져오기
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("-----------------------------------");
		
		
		
		//방법2> keySet()을 향상된 for문으로 처리하기 !!!!!!!!제일많이씀!!!!!!!!!!!!! 당연 숙지!!!!!!!!!!!
		for(String key : map.keySet()) {
			String value = map.get(key);
			System.out.println(key + " : " + value);
		}
		System.out.println("-----------------------------------");
		
		
		
		//방법3> 	value값만 읽어와서 처리하기 ==> values() 메소드 이용
		for(String value : map.values()) {
			System.out.println(value); //value값만 나올수 있음. key는안돼
		}
		
		
		
		/*
		 	방법 4>  이건 이런게 있다라고만 우선 지금만 알아두자.
		 	==> Map에는 Entry라고 하는 내부 class가 만들어져 있다.
		 		이 Entry class는 key와 value라는 멤버변수로 구성되어 있다.
		 		Map에서는 이 Entry클래스들을 Set형식으로 저장해서 관리한다.
		 		
		 		(맵의 구조가 이렇게 생겼대)
		 		
		   - Entry객체 전체를 가져와서 처리하면 되는데, (==> 가져온 Entry들은 Set형식으로 되어 있다.)
		   ==> entrySet() 메소드 이용	
		 		
		 */
		
		//Entry라는 내부객체 전체 가져오기
		Set<Map.Entry<String, String>> mapSet = map.entrySet();
		Iterator<Map.Entry<String, String>> entryIt = mapSet.iterator();
		
		while(entryIt.hasNext()) {
			Map.Entry<String, String> entry = entryIt.next(); //Entry 객체 가져오기
			
			System.out.println("key값  : " + entry.getKey());
			System.out.println("value값  : " + entry.getValue());
			System.out.println();
			
		}
		
		
	}

}
