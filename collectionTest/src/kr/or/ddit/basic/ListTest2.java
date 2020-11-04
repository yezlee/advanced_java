package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Scanner;

//문제1. 5명의 사람이름을 입력 받아서 ArrayList에 저장한 후에 이들 중에서
//	   '김'씨 성을 가진 사람의 이름을 모두 출력하시오.
//     (입력은 Scanner 객체를 이용한다.)
//
//문제2. 5명의 별명을 입력 받아서 ArrayList에 저장한 후에 이들 중에서 별명의 길이가  
//      제일 긴 별명을 출력 하시오.
//
//문제3. 문제2에서 별명의 길이가 같은것이 여러개 있을 경우를 처리하시오.
//      (즉, 제일 긴 별명 모두 출력한다.)


public class ListTest2 {

	public static void main(String[] args) {
		
		
//	//문제1>
//	ArrayList<String> Namelist = new ArrayList<>();
//	Scanner sc = new Scanner(System.in);	
//	
//	for(int i = 0; i < 5; i++) {	
//		System.out.println("이름을 입력 하세요>");
//		String input = sc.nextLine();			
//		Namelist.add(input);
//	}	
//	for(String str : Namelist) {	
//		if(str.indexOf("김") == 0) {				
//			System.out.println("'김'씨 성을 가진 사람은 : " + str);
//		}

		
		
			
//	//문제2>
//	ArrayList<String> Nickname = new ArrayList<>();
//	
//	for(int i = 0; i < 5; i++){
//		System.out.println("별명을 입력하세요>");
//		String input = sc.nextLine();
//		Nickname.add(input);
//	}
//	
//	int longest = 0;
//	for(int i = 0; i < Nickname.size(); i++) {
//		if(Nickname.get(i).length() > Nickname.get(longest).length()) {
//			longest = i;
//		}
//	}
//	System.out.println("가장 긴 별명은 : " + Nickname.get(longest));
//	
//	
	
	//문제3>
		
		ArrayList<String> Nickname = new ArrayList<>();
		ArrayList<String> Nickname_array = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		
		for(int i = 0; i < 5; i++){
			System.out.println("별명을 입력하세요>");
			String input = sc.nextLine();
			Nickname.add(input);
		}
		
		int longest = 0;
		int second_longest = 0;
		
		for(int i = 0; i < Nickname.size(); i++) {
			if(Nickname.get(i).length() > Nickname.get(longest).length()) {
				longest = i;
			}
			
					
			System.out.println(Nickname.get(longest).length());
			for(int j = 0; j < Nickname.size()-1; j++) {
				if(Nickname.get(0).length() == Nickname_array.get(j).length()) {
					Nickname_array.add(Nickname.get(j));
				}
		}
		}
		System.out.println("가장 긴 별명은 : " + Nickname_array);
		
		
		
		
		
		
		
		
	         
		
		
		
		
		
/*	ArrayList<String> Nickname = new ArrayList<>();
	for(int i = 0; i < 5; i++){
		System.out.println("별명을 입력하세요>");
		String input = sc.nextLine();
		Nickname.add(input);
	}
	int longest = 0;
	int longest2 = 0;
	for(int i = 0; i < Nickname.size(); i++) {
		if(Nickname.get(i).length() > Nickname.get(longest2).length()) {
			longest2 = i; // i =3
		}
		for(int j = 0; j < Nickname.size(); j++) {
			if(Nickname.get(j).length() == 0 ) {
				longest2 = j;
			}
		}
	}
	*/
	
	
	
	
	
	
	/*문제2
	 * System.out.println("----------------------------------------------");
	
	for(String str : nickname) {
//		System.out.println(str.length()); //str 하나씩 돌면서 배열 하나씩의 길이를 세는거
		int cnt = str.length();
		
		for(int i = 0; i < nickname.size(); i++) {
			int min = i;
			System.out.println();
			
		}
	}
	
	
	for(int i = 0; i < nickname.size(); i++) {
		int min = i;
		for(int j = i + 1; j < nickname.size(); j++) {
			System.out.println(nickname.get(i));
			//if(nickname.get(i) < min) {
				
			}
		}
		
	}
	 
	*/
	
	/*public static void selectionSort(int arr[]) {
		int min = 0, i = 0, j = 0;
		for (i = 0; i < arr.length; i++) {
			min = i;
			for (j = i + 1; j < arr.length; j++) {
				if (arr[j] < arr[min])
					min = j;
			}
			int temp = arr[i];
			arr[i] = arr[min];
			arr[min] = temp;
	
		
		}
		}
		*/
/*	
	String s = "abcdefg";
    int s_len = s.length();
		
		
*/	
	
	
	
		
		
}		

}

