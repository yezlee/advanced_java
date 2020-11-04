package kr.or.ddit.basic;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ListSortTest3_answer {

	//등수를 구하는 메소드
	public void setRanking(List<Students> stdList) {
		
		for(Students std : stdList) { //기존데이터를 구하기 위한 반복문
			int rank = 1; //처음에는 1등으로 설정해놓고 시작한다.
			for(Students std2 : stdList) { //처음 반복문이랑 비교 대상을 나타내는 반복문
				
				//기준보다 비교대상의 값이 크면 rank값을 증가시킨다.
				if(std.getTotal() < std2.getTotal()) {
					rank++;
				}
			}
			
			//구해진 등수를  Student객체의 rank변수에 저장한다.
			std.setRank(rank);
		}
	}
	
	public static void main(String[] args) {
		
		ListSortTest3_answer stdTest = new ListSortTest3_answer();
		
		List<Students> stdList = new ArrayList<>();
		
		stdList.add(new Students(1004, "강감찬", 85, 35, 65));
		stdList.add(new Students(1006, "변학도", 10, 58, 36));
		stdList.add(new Students(1003, "성춘향", 80, 95, 20));
		stdList.add(new Students(1005, "알지매", 45, 65, 89));
		stdList.add(new Students(1007, "이몽룡", 100, 78, 98));
		stdList.add(new Students(1002, "이순신", 75, 65, 58));
		stdList.add(new Students(1001, "홍길동", 78, 50, 70));
		
		// 등수 구하는 메서드를 호출해서 등수를 구한다.
		stdTest.setRanking(stdList);
		
		System.out.println("정렬전...");
		for(Students std : stdList) {
			System.out.println(std);
		}
		System.out.println();
		
		// 학번의 오름차순 정렬
		Collections.sort(stdList);  // 내부 정렬 기준으로 정렬한다.
		
		System.out.println("학번의 오름차순 정렬후...");
		for(Students std : stdList) {
			System.out.println(std);
		}
		System.out.println();
		
		// 총점의 역순으로 정렬
		Collections.sort(stdList, new SortByTotal());  // 외부 정렬 기준으로 정렬한다.
		
		System.out.println("총점의 내림차순 정렬후...");
		for(Students std : stdList) {
			System.out.println(std);
		}
		System.out.println();	
		

	}
}



class Students implements Comparable<Students>{
	private int num;
	private String name;
	private int kor;
	private int eng;
	private int math;
	private int total;
	private int rank;
	
	
	public Students(int num, String name, int kor, int eng, int math) {
		super();
		this.num = num;
		this.name = name;
		this.kor = kor;
		this.eng = eng;
		this.math = math;
		this.total = kor + eng + math;
	}


	public int getNum() {
		return num;
	}


	public void setNum(int num) {
		this.num = num;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getKor() {
		return kor;
	}


	public void setKor(int kor) {
		this.kor = kor;
		this.total = kor + eng + math;
	}


	public int getEng() {
		return eng;
	}


	public void setEng(int eng) {
		this.eng = eng;
		this.total = kor + eng + math;
	}


	public int getMath() {
		return math;
	}


	public void setMath(int math) {
		this.math = math;
		this.total = kor + eng + math;
	}


	public int getTotal() {
		return total;
	}


	public void setTotal(int total) {
		this.total = total;
	}


	public int getRank() {
		return rank;
	}


	public void setRank(int rank) {
		this.rank = rank;
	}


	@Override
	public String toString() {
		return "Students [num=" + num + ", name=" + name + ", kor=" + kor + ", eng=" + eng + ", math=" + math
				+ ", total=" + total + ", rank=" + rank + "]";
	}

	// 학번의 오름차순 정렬 기준
	@Override
	public int compareTo(Students std) {
		return Integer.compare(this.num, std.getNum());
	}

}



/*
 * 총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬 기준 클래스를 작성
 */
class SortByTotal implements Comparator<Students> {

	@Override
	public int compare(Students std1, Students std2) {
		if (std1.getTotal() == std2.getTotal()) { // 총점이 같을 때
			return std1.getName().compareTo(std2.getName());
		} else {
			return Integer.compare(std1.getTotal(), std2.getTotal()) * -1;
		}
	}

}


 












