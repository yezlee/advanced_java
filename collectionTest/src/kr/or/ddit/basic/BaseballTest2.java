package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class BaseballTest2 {

	public static void main(String[] args) {
				//입력
				Scanner scan = new Scanner(System.in);
					
				//랜덤값		
				HashSet<Integer> random = new HashSet<>();
				while(random.size()<3) {
					random.add( (int)(Math.random() * 9 ) + 1 );
				}
				ArrayList<Integer> randomList = new ArrayList<>(random);
				
				//입력값을 넣어줄 배열
				ArrayList<Integer> insertList = new ArrayList<>();
				System.out.println("1-9까지 랜덤숫자 3개를 입력하시오");
				

	}

}
