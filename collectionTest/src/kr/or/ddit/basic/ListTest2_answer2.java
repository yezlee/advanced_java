package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

//문제2. 5명의 별명을 입력 받아서 ArrayList에 저장한 후에 이들 중에서 별명의 길이가  
//제일 긴 별명을 출력 하시오.

public class ListTest2_answer2 {
	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		ArrayList<String> aliasList = new ArrayList<>();
		
		System.out.println("서로 다른 길이의 별명을 5번 입력하세요.");
		
		for(int i = 1; i <= 5; i++) {
			System.out.println(i + "번째 별명 : ");
			String alias = scan.next();
			aliasList.add(alias);			
		}
		
		System.out.println();
		
		//제일 긴 별명이 저장될 변수 선언 ==> List의 첫번째 데이터로 초기화한다. (꼭 그래야하는건아니지만, 해주면 반복문 한번 덜 돌아도 되니까)
		String maxAlias =  aliasList.get(0); //첫번째가 가장 길다라고 가정을 하고 시작
		
		for(int i = 1; i < aliasList.size(); i++) {
			if(maxAlias.length() < aliasList.get(i).length()) {
				maxAlias = aliasList.get(i);
			}
		}
		System.out.println("제일 긴 별명 : " + maxAlias);
		
	}
}
