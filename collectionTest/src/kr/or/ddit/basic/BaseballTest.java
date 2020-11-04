package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/*
  문제>
 Set과 List를 이용하여 숫자 야구 게임 프로그램을 작성하시오.
  컴퓨터의 숫자는 난수를 이용하여 구한다. [이때 set을 쓰고 set에 있는 데이터를 list에 담고 ]
  
 (스트라이크는 S, 볼은 B로 나타낸다.)
  	 
 예시> 
 컴퓨터의 난수 ==> 9 5 7 (실제로는 안보여줌)
 
 실행예시>
 숫자입력 =====> 3 5 8 (1S 0B) [입력받은 데이터를 똑같이 set에 담고] 입력받는 기능, 검사하는기능 따로따로 메소드로 만들어서 하면 더 쉬울거야. 분리하는게
 숫자입력 =====> 7 8 9 (0S 2B)
 숫자입력 =====> 9 7 5 (1S 2B)
 숫자입력 =====> 9 5 7 (3S 0B)
 
 축하합니다! 당신은 4번째만에 맞췄습니다.  
 
 */

public class BaseballTest {

	public static void main(String[] args) {
		
		//입력
		Scanner scan = new Scanner(System.in);
			
		//랜덤값		
		HashSet<Integer> random = new HashSet<>();
		while(random.size()<3) {
			random.add( (int)(Math.random() * 9 ) + 1 );
		
		}
		System.out.println(random);
		
		//입력값을 넣어줄 배열
		ArrayList<Integer> insertList = new ArrayList<>();
		
		
		System.out.println(random);
		
		int strike = 0;
		
		while(strike != 3) {
			System.out.println("1부터 9까지 랜덤수를 3개 입력하시오.");
			strike = 0;
			int ball = 0;
			int count = 0;
			int count2 = 0;  //변수를 안에서 해주는거랑 밖에서 해주는거랑 무슨차이지?			
			
			insertList.add(scan.nextInt());
			insertList.add(scan.nextInt());
			insertList.add(scan.nextInt());
			for(int a : random) {
				count++;
				for(int b : insertList) {
					count2++;
					if(a == b) {
						if(count == count2) {
							strike++;
						}else if(count != count2) {
							ball++;
						}
					}
				}
				count2 = 0;
			}
			insertList.clear();
			System.out.println("S : " + strike + " B : " + ball);		
		}
		
		
	}
}
	
	
	
	
