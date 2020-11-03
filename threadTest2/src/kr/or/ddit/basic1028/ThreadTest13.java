package kr.or.ddit.basic1028;

/*
  	문제) 10마리의 말들이 경주하는 경마 프로그램 작성하기
  	
  		경주마는 Horse란 이름의 클래스로 구성하고
  		이 클래스는 말이름(String), 등수(int), 현재위치(int)를 멤버변수로 갖는다.
  		그리고, 이 클래스에는 등수를 오름차순으로 처리하는 내부 정렬 기준이 있다.
  		 	  (Comparable인터페이스 구현하기)
	 	- 이 Horse클래스는 스레드로 작성한다.
	 	
	 	- 경기 구간은 1~50구간으로 되어있다.
	 	
	 	- 경기 중간 중간에 각 말들의 위치를 나타내야한다.
	 	예) 
	 	01번말 : ------>--------------------------
	 	02번말 : ---->----------------------------
	 	...
	 	10번말 : -------->------------------------

	 	- 경기가 끝나면 등수 순으로 경기 결과를 출력한다.
	 	
 */
public class ThreadTest13 {

	public static void main(String[] args) {
		Horse[] racers = new Horse[] {
			new Horse("1번말   :", 0, 0),	
			new Horse("2번말   :", 0, 0),	
			new Horse("3번말   :", 0, 0),	
			new Horse("4번말   :", 0, 0),	
			new Horse("5번말   :", 0, 0),	
			new Horse("6번말   :", 0, 0),	
			new Horse("7번말   :", 0, 0),	
			new Horse("8번말   :", 0, 0),	
			new Horse("9번말   :", 0, 0),	
			new Horse("10번말 :", 0, 0)	
		};

		
		for(int i = 0 ; i < 10; i++) {
			racers[i].start();
		}
		
		
		
		Racing r = new Racing(racers);
		
		r.start();
		
		
			try {
				r.join();
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		
		
		
		
		/*Horse h = new Horse(null);
		Thread th = new Thread(h);
		th.start();*/
		
	}

}


//경기 구간 1~50구간을 출력하는 스레드
class Horse extends Thread implements Comparable<Horse>{ 
	
	private String horseName = "";
	private int rank = 0;
	private int loc = 0;
	private static int count = 0; //static은 값을 공유! compare해주려고 추가

	public Horse(String horseName, int rank, int loc) {
		this.horseName = horseName;
		this.rank = rank;
		this.loc = loc;
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

	public int getLoc() {
		return loc;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}




	@Override
	public void run() {
		
		//location정해주는 반복문
		for(int i = 1; i < 51; i++) {
			loc++;	
			
			if(loc == 50) {
				count++;
				rank = count;
			}
			try {
				//101 ~ 500 사이의 난수 설정하기(0.1초~0.5초)
				Thread.sleep( (int)(Math.random() * 500 + 101) );
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
		}
		
	}

	@Override
	public int compareTo(Horse horse) {
		return Integer.compare(this.rank, horse.getRank());
	}
}




class Racing extends Thread{
	
	//말의 정보를 담을 배열(변수를 담을 배열 - 변수같은느낌)
	Horse[] horse_info;
	int count;
	
	
	public Racing(Horse[] horse_info) {
		this.horse_info = horse_info;
	}
	
	@Override
	public void run() {
		
		
		//10마리가 다 안들어오면 계속 while문을 돌게끔
		while(count != 10) {
			
			try {
				Thread.sleep(1500);//1.5초마다 10마리가 움직이는거처럼 보이게
			} catch (InterruptedException e) {
				// TODO: handle exception
			}
			
			//배열이니까 0부터 9까지
			for(int i = 0; i < 10; i++) {
				String dash = "";
				System.out.print(horse_info[i].getHorseName());
								
				//dash 50번돌려서 붙여주는 반복문
				for(int j = 1; j < 51; j++) {
					if(j == horse_info[i].getLoc()) {
						dash += ">";
					}else {
						dash += "-";
					}
				}
				System.out.println(dash);				
			}//for문끝				
			
			//말의 정보가 담겨있는 배열의 길이만큼 반복하는 반복문
			//그 안에 dash가 50개가 되어서 
			for(int i = 0; i < horse_info.length; i++) {			
				if(horse_info[i].getLoc() == 50) {
					count++;
				}
			}	
			
			//만약에 10마리 다 카운트 되지 않았다면 0으로 초기화 시켜준다
			if(count !=  10) {
				count = 0;
			}//그렇게 if문이 끝나고 나면 다시 위로 올라가 처음부터 while문을 다시 시작
		}
	}	
}










