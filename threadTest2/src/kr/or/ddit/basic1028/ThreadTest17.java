package kr.or.ddit.basic1028; //1030

// 은행의 입출금을 스레드로 처리하는 예제
// (동기화 처리 예제)

/*이거는 클래스가 하나야
   스레드를 상속받을 때는 클래스를 하나 생성해서 만들어야 하는데 
   이거는 runnable을 쓰기 때문에 하나의 클래스 안에서 다 할수가 있어
   러너블은 메인메소드 안에서  
   Runnable test = new Runnable() {}
   이렇게 생성자를 만들고
   그 생성자를 불러오고 범위지정{}안에서  run을 오버라이딩해준다.
   (참고로 생성자는 보통그냥 불러오지. 불러오고 뒤에 세미콜론 하나 찍어주는게 그냥 생성자를 불러오는거.
   근데 그 생성자 불러올때 메소드를 오버라이딩 해주고 싶으면  그 생성자 불러올때 {}이렇게 해주고 그 안에서 재정의(오버라이딩)해준다.)
   
   그리고 그안에 있는 run에서 기능을 구현해준다
   그 기능은 출금. 
   
   
    ( 생성자를 불러온다는건 그 클래스 안에있는 메소드를 쓰고 싶을때
     그 기능을 쓰고싶은 클래스안에 메인메소드 안에서 객체생성해주는게 생성자를 만드는것임!!!!! )
   
    
 */
public class ThreadTest17 {
	private int balance; //잔액이 저장될 변수
		
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}


	//입금하는 메소드
	public void deposit(int money) {
		balance += money;
	}
	
	/*
	//출금하는 메소드(출금 성공 : true,  출금 실패 : false 반환한다.)
	public synchronized boolean withdraw(int money) {
		if(balance >= money) { //출금 가능 여부를 판단한다.
			for(int i=1; i <= 1000000000; i++) {} //시간지연용. 너무 빠르면 넘어가지않고 끝날까봐
			balance -= money;
			System.out.println("메소드 안에서 balance = " + getBalance());
				return true;
			}else {
				return false;
			}
			
		}*/		
		
	

	//출금하는 메소드(출금 성공 : true,  출금 실패 : false 반환한다.)
	public boolean withdraw(int money) {
		synchronized (this) {
		
			if(balance >= money) { //출금 가능 여부를 판단한다.
				for(int i=1; i <= 1000000000; i++) {} //시간지연용. 너무 빠르면 넘어가지않고 끝날까봐
				balance -= money;
				System.out.println("메소드 안에서 balance = " + getBalance());
					return true;
				}else {
					return false;
				}			
			}
			
		}
		
			

	
	public static void main(String[] args) {
		ThreadTest17 account = new ThreadTest17();
		account.setBalance(10000); //잔액을 10000원으로 솔종
		
		
		//익명구현제로 스레드 구현
		Runnable test = new Runnable() {
			
			@Override
			public void run() {
				boolean result = account.withdraw(6000);
				System.out.println("스레드에서 result = " + result + ", balnace = " + account.getBalance());
				
			}
		};

		//---------------------------
		
		
		Thread th1 = new Thread(test); 
		Thread th2 = new Thread(test);
		
		th1.start();
		th2.start();
		
		
		
	}

}
