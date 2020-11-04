package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

//문제1. 5명의 사람이름을 입력 받아서 ArrayList에 저장한 후에 이들 중에서
//'김'씨 성을 가진 사람의 이름을 모두 출력하시오.
//(입력은 Scanner 객체를 이용한다.)
//
//문제2. 5명의 별명을 입력 받아서 ArrayList에 저장한 후에 이들 중에서 별명의 길이가  
//제일 긴 별명을 출력 하시오.
//
//문제3. 문제2에서 별명의 길이가 같은것이 여러개 있을 경우를 처리하시오.
//(즉, 제일 긴 별명 모두 출력한다.)


public class ListTest2_answer {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		ArrayList<String> nameList = new ArrayList<>();
		
		System.out.println("5명의 이름을 입력하세요.");
		for(int i = 1; i <= 5; i++) {
			System.out.print(i + "번째 사람 이름 : ");
			String name = scan.nextLine();
			nameList.add(name);			
		}
		
		System.out.println();
		for(int i = 0; i < nameList.size(); i++) {
//			if(nameList.get(i).substring(0, 1).equals("김") ) { //이렇게하면 0번째부터 1번째까지 글자를 빼오는거. 즉 앞에 성만 
//				System.out.println(nameList.get(i));
//			}
			
//			if(nameList.get(i).charAt(0) == '김') {   //charAt => 한글자만 빼와라
//				System.out.println(nameList.get(i));				
//			}
			
//			if(nameList.get(i).indexOf("김") == 0) {
//				System.out.println(nameList.get(i));
//			}
			
			if(nameList.get(i).startsWith("김") == true){
				System.out.println(nameList.get(i));
			}
		}
				
				

	}

}
