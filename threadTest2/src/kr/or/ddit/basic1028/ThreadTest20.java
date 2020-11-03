package kr.or.ddit.basic1028; //1102 -3

import javax.xml.crypto.Data;

public class ThreadTest20 {

	public static void main(String[] args) {
		DataBox box = new DataBox(); //공통으로 사용할 객체 생성하고
		
		PruducerThread th1 = new PruducerThread(box);
		ConsumerThread th2 = new ConsumerThread(box);
		
		th2.start();
		th1.start(); //이거 순서 바꿔도 결과는 같음
		
		//이렇게하면 어떤 스레드가 먼저 실행되도 공급, 소비 번갈아가면서 함.
		//순서도 바뀌지않고 똑같음 왜냐면 if문안에 데이터가 널이면 어차피 기다리라고함
				

	}

}


//데이터를 공통으로 관리하는 클래스
class DataBox{
	private String data;
	
	//데이터를 가져가는 메소드
	//처리과정 ==> data변수의 값이 null이면 data변수에 문자열이 채워질 때까지 기다리고,
	// 			data변수에 값이 있으면 해당 문자열을 반환한다.
	//			data변수의 값을 반환한 후에는 data변수의 값을 null로 만든다.
	public synchronized String getData(){ //데이터를 꺼내가는 역할. 값이 널이면 데이터가 없단뜻. 그러면 문자열이 채워질때까지 기다린대.
		if(data == null) {
			try {
				wait();
			} catch (InterruptedException e) {
			}
		}
		
		String returnData = data; //data가 널이 아니면. 일로 넘어옴. 그럼 그걸 returnData에 담고
		System.out.println("스레드가 읽은 데이터 : " + returnData);
		
		data = null;  //data는 다시 널처리해줌
		notify();
		return returnData; 
		
	}
	
	//데이터를 넣어주는 메소드
	//처리과정 ==> data변수의 값이 있으면 data변수의 값이 null이 될때까지 기다린다.
	//			data변수의 값이 null이  되면 data변수에 새로운 값으로 저장된다. 
	public synchronized void setData(String data) {
			if(this.data != null) { //데이터에 뭐가 있단 뜻이니까 그럼 일단 기다려.
				try {
					wait();
				} catch (InterruptedException e) {
					// TODO: handle exception
				}
			}
			
			this.data = data;
			System.out.println("Thread에서 새로 저장한 데이터 : " + data);
			
			notify();
	}
}


//데이터를 넣어주는 스레드
class PruducerThread extends Thread{
	private DataBox box; //공통으로 사용할 객체변수를 하나 만들고

	public PruducerThread(DataBox box) {
		super();
		this.box = box;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 5; i++) {
			box.setData("공급 데이터 " + i);
		}
	}
}



//데이터를 꺼내서 사용하는 스레드
class ConsumerThread extends Thread{
	private DataBox box;

	public ConsumerThread(DataBox box) {
		super();
		this.box = box;
	}
	
	@Override
	public void run() {
		for(int i = 1; i <= 5; i++) {
			String data = box.getData();
		}
	}
}

















