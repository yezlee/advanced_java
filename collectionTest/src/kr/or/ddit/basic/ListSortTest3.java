package kr.or.ddit.basic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 문제) 학번(int), 이름(String), 국어점수, 영어점수, 수학점수, 총점, 등수를 멤버로 갖는
 	Student 클래스를 만든다.
 	이 Student 클래스의 생성자에서는 학번, 이름, 국어점수, 영어점수, 수학점수만 매개변수로 받아서
 	초기화 처리를 한다.
 	
 	- 이 Student객체는 List에 저장하여 관리한다.
 	
 	- List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
 	총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬기준 클래스를 작성하여
 	정렬된 결과를 출력하시오.
 	
 	(단, 등수는 List에 전체 데이터가 추가된 후에 저장되도록 한다. )
 */

public class ListSortTest3 {

	public static void main(String[] args) {

		ArrayList<Student> stuList = new ArrayList<>();
		
		stuList.add(new Student(1004, "강감찬", 85, 35, 65));
		stuList.add(new Student(1006, "변학도", 10, 58, 36));
		stuList.add(new Student(1003, "성춘향", 80, 95, 20));
		stuList.add(new Student(1005, "알지매", 45, 65, 89));
		stuList.add(new Student(1007, "이몽룡", 100, 78, 98));
		stuList.add(new Student(1002, "이순신", 75, 65, 58));
		stuList.add(new Student(1001, "홍길동", 78, 50, 70));
		
		System.out.println("처음 학생들 정렬");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("--------------------------------------------");
		
		Collections.sort(stuList);
		System.out.println("학생들 학번 오름차순 정렬 후 ");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("--------------------------------------------");
		
		Collections.sort(stuList, new TotalScoreDesc());
		System.out.println("학생들 총점의 역순으로 정렬 후, 다만 총점이 같으면 이름으로 오름차순");
		for(Student stu : stuList) {
			System.out.println(stu);
		}
		System.out.println("--------------------------------------------");
		
		
		
 		System.out.println("등수");
		int count = 0;
		for(int i = 0; i < stuList.size(); i++) {
				count = 1;
			 for(int j = 0; j < stuList.size(); j++) {
				 if( stuList.get(i).getTotal_score() < stuList.get(j).getTotal_score()) {
					 count++;
				 }
				 stuList.get(i).setRank(count);
			 }
		}	
		for(Student stu : stuList) {
			System.out.println(stu);		
		}
		
		
	}



	
}

class Student implements Comparable<Student>{

	private int stu_num; //학번
	private String stu_name; //이름
	private int kor_score; //국어점수
	private int eng_score; //영어점수
	private int math_score; //수학점수
	private int total_score; //총점
	private int rank; //등수
		
	public Student(int stu_num, String stu_name, int kor_score, int eng_score, int math_score) {
		super();
		this.stu_num = stu_num;
		this.stu_name = stu_name;
		this.kor_score = kor_score;
		this.eng_score = eng_score;
		this.math_score = math_score;
		this.total_score = kor_score + eng_score + math_score;
	} 
	
	


	public int getStu_num() {
		return stu_num;
	}

	public void setStu_num(int stu_num) {
		this.stu_num = stu_num;
	}

	public String getStu_name() {
		return stu_name;
	}
	
	public void setStu_name(String stu_name) {
		this.stu_name = stu_name;
	}
	
	public int getKor_score() {
		return kor_score;
	}

	public void setKor_score(int kor_score) {
		this.kor_score = kor_score;
	}

	public int getEng_score() {
		return eng_score;
	}

	public void setEng_score(int eng_score) {
		this.eng_score = eng_score;
	}

	public int getMath_score() {
		return math_score;
	}

	public void setMath_score(int math_score) {
		this.math_score = math_score;
	}

	public int getTotal_score() {
		return total_score;
	}

	public void setTotal_score(int total_score) {
		this.total_score = total_score;
	}

	public int getRank() {
		return rank;
	}

	public void setRank(int rank) {
		this.rank = rank;
	}
	
	@Override
	public String toString() {
		return "Student [stu_num=" + stu_num + ", stu_name=" + stu_name + ", kor_score=" + kor_score + ", eng_score="
				+ eng_score + ", math_score=" + math_score + ", total_score=" + total_score + ", rank=" + rank + "]";
	}

	@Override
	public int compareTo(Student stu) {
		return new Integer(this.getStu_num()).compareTo(stu.getStu_num());
	}
}

/*
 - List에 저장된 데이터들을 학번의 오름차순으로 정렬할 수 있는 내부 정렬 기준을 구현하고,
 	총점의 역순으로 정렬하는데 총점이 같으면 이름의 오름차순으로 정렬이 되는 외부 정렬기준 클래스를 작성하여
 	정렬된 결과를 출력하시오.
 */

class TotalScoreDesc implements Comparator<Student>{

	@Override
	public int compare(Student score1, Student score2) {
		
		if(score1.getTotal_score() > score2.getTotal_score() ) {
			return -1;
		}else if(score1.getTotal_score() == score2.getTotal_score()){
			return score1.getStu_name().compareTo(score2.getStu_name());
		}else {
			return 1;
		}
	}
	
}




