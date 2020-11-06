package kr.or.ddit.basic;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/*
  문제) 서버와 클라이언트가 접속이 되면 클라이언트에서 'd:/D_Other/'폴더에 있는 '극한직업.jpg'파일을 
  	  서버쪽에 전송한다.
  	  서버는 클라이언트가 보내온 이미지데이터를 'd:/D_Other/연습용/'폴더에 '극한직업_전송파일.jpg'로 저장한다.
  
 */


//클라이언트가 보내온 파일데이터를 받아서 'd:/D_Other/연습용/'폴더에 '극한직업_전송파일.jpg'로 저장한다.
public class TcpFileServer {

	private ServerSocket server;
	private Socket socket;
	private BufferedInputStream bis;
	private BufferedOutputStream bos;
	
	private void serverStart() {
		File saveDir = new File("D:/D_Other/연습용"); //저장할 폴더
		if(!saveDir.exists()) { // 저장할 폴더가 없으면 새로 생성한다.
			saveDir.mkdir();
		}
		
		try {
			server = new ServerSocket(7878);
			System.out.println("서버가 준비되었습니다.");
			
			socket = server.accept(); //클라이언트의 요청을 기다린다.
			
			System.out.println("파일 다운로드 시작....");
			
			File saveFile = new File(saveDir, "극한직업_전송파일.jpg");
			
			//소켓에서 데이터를 입력받을 스트림 객체 생성
			bis = new BufferedInputStream(socket.getInputStream());
			
			//파일에 저장할 스트림 객체 생성
			bos = new BufferedOutputStream(new FileOutputStream(saveFile));
			
			byte[] temp = new byte[1024];
			//사진 한장이 대략 1500kb인데 , new byte[1024] 이게 1kb, 그래서 빠르게 보내기 위해서 1키로 바이트 짜리로 보내면 1500번만 보냄 되니까(반복을 1500번만 하면 되니까)
			//그냥 하면, 배열이 아니고 바이트로 쓰면. 약 150만번을 반복해야함
			int length = 0;
			
			//소켓으로 읽어온 데이터를 파일로 출력한다.
			while((length = bis.read(temp)) > 0 ) {
				bos.write(temp, 0, length); //temp만 출력하면 문제가 생겨. 버퍼사용했을때 생긴문제처럼. 모자라는거 안나오는거
			}
			
			/* 원래 사진 카피하는건 이렇게 했었음. 
			 
			 int filebyte = 0;
			
			System.out.println("복사 작업 시작!");
			while( (filebyte = fin.read()) != -1) {
				fout.write(filebyte);
			} 
			 */
			
			
			bos.flush();
			System.out.println("파일 다운로드 완료..");
			
		} catch (Exception e) {
			System.out.println("파일 다운로드 실패!");
			e.printStackTrace();
		}finally {
			if(bis!=null) try {bis.close(); } catch (IOException e) {}
			if(bos!=null) try {bos.close(); } catch (IOException e) {}
			if(socket!=null) try {socket.close(); } catch (IOException e) {}
			if(server!=null) try {server.close(); } catch (IOException e) {}
		}
		
	}
	public static void main(String[] args) {
		new TcpFileServer().serverStart();
		
	
	}
	

}
