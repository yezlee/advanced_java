package kr.or.ddit.basic1028;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import javax.swing.plaf.synth.SynthSeparatorUI;

public class ThreadTest13_answer {

	public static void main(String[] args) {
		Horse_a[] horses = new Horse_a[] {
			new Horse_a("01번말"), 
			new Horse_a("02번말"), 
			new Horse_a("03번말"), 
			new Horse_a("04번말"), 
			new Horse_a("05번말"), 
			new Horse_a("06번말"), 
			new Horse_a("07번말"), 
			new Horse_a("08번말"), 
			new Horse_a("09번말"), 
			new Horse_a("10번말") 
		};
		
		//현재경기의 상태를 알려주는 객체도 생성해야해
		GameStatus gs = new GameStatus(horses);
				
		System.out.println("경기시작!");
		
		for(Horse_a h : horses) {
			h.start();			
		}
		
		gs.start();
		
		
		for(Horse_a h : horses) {
			try {
				h.join();
			} catch (InterruptedException e) {
			}
		}		
				
			try {
				gs.join();
			} catch (InterruptedException e) {
			}
		
			
		System.out.println("경기 끝!");
		
		/*
		//방법 1. 배열을 직접 정렬하고 출력하기
		//정렬
		Arrays.sort(horses); //그냥 이것만 넣어주면 돼.
		
		//출력
		for(Horse_a h : horses) {
			System.out.println(h);
		}		
		*/
		
		
		//방법2. 배열의 데이터를 List에 담고 List를 정렬하려 출력하기
		ArrayList<Horse_a> horseList = new ArrayList<>();
		for(Horse_a h : horses) {
			horseList.add(h);
		}
		
		Collections.sort(horseList);
		
		for(Horse_a h : horseList) {
			System.out.println(h);
		}
		
	}

}


class Horse_a extends Thread implements Comparable<Horse_a>{
	public static int currentRank = 0; //말들의 등수 계산에 사용되는 변수 선언
	
	private String horseName; //말이름
	private int rank; //등수                                      
	private int position; //말의 현재 위치
	
	
	//등수랑 현재위치는 이건 게임이 진행되면서 초기화되서 상관없지만 말이름은 미리 해야해서 생성자 만들자
	//생성자
	public Horse_a(String horseName) {
		this.horseName = horseName;
	}


	public String getHorseName() {
		return horseName;
	}
	public void setHorseName(String horseName) {
		this.horseName = horseName;
	}
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}


	@Override
	public String toString() {
		return "경주마 " + horseName + "의 등수는 " + rank + "등입니다.";
	}
	
	//등수를 오름차순
	@Override
	public int compareTo(Horse_a horse_a) {
		return Integer.compare(rank, horse_a.getRank());
	}
	
	//말이 달리는 부분을 스레드로 처리한다.
	@Override
	public void run() {
		
		//이 반복문 진행이 달리는거랑 마찬가지임
		for(int i = 1; i <=50; i++) { 
			position = i; //말의 현재위치를 저장한다.
			
			try {
				Thread.sleep( (int)(Math.random() * 500) );
			} catch (InterruptedException e) {
				// TODO: handle exception
			}			
		}//for문
		
		//한마리의 말이 경주가 끝나면 등수를 구해서 rank에 설정한다.
		currentRank++; //얘가 static값이니까 처음에 말이 들어오면 1이돼. 스태틱이니까 1이라고 기억하고 있어. 그 다음에 또 들어오면2.   전체 말이 몇마리가 들어왔나 기억하는거
		rank = currentRank; //랭크는 등수...
		
	}
}





//경기중인 말의 현재 위치를 출력하는 스레드
class GameStatus extends Thread{
	private Horse_a[] ho; //10마리 말들이 저장된 배열 
	//변수만 만들었다고 값이 자동으로 들어가는게 아니니까 생성자 만들자

	public GameStatus(Horse_a[] ho) {
		this.ho = ho;
	}
	
	@Override
	public void run() {
		while(true) { //경기가 언제 끝날지 모르니까 무한반복
			
			//모든 말들의 경주가 끝났는지의 여부를 검사
			if(Horse_a.currentRank == ho.length) { //배열의 길이로 해주면 위에 말을추가해도 노프라블럼! 
				break;
			}
			
			//빈 줄 출력하기 => 이걸 해줌으로서 화면상에선 얼핏보면 같은 장소에서 움직이는 것처럼 보임
			for(int i = 1; i <= 15; i++) {
				System.out.println();
			}
			
			
			for(int i = 0; i < ho.length; i++) {
				System.out.print(ho[i].getHorseName() + " : "); // 01번말 : <= 요기까지 출력된거임
				
				for(int j = 1; j <= 50; j++) {
					if(ho[i].getPosition() == j) {
						System.out.print(">");
					}else {
						System.out.print("-");
					}
				}
				System.out.println();//한마리 말 끝나고 그 다음말 출력할때 줄바꿔줘야하니까
			}//이거 끝나면 10마리가 돌고있는거 한번 출력하는거
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
			
		}
	}
	
	
	
}











