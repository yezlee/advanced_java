package kr.or.ddit.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystemNotFoundException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.inf.RmiTestInterface;
import kr.or.ddit.vo.FileInfoVo;
import kr.or.ddit.vo.TestVO;

public class RemoteClient {
	
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		// RMI용 객체를 서버에서 구해와서 사용하는 순서 
		try {
			//1. 서버에서 등록한 RMI용 객체를 찾기 위해 Registry객체를 생성한다.
			//	 (서버의 IP주소와 포트번호를 지정하여 생성한다.)
			Registry reg = LocateRegistry.getRegistry("localhost", 9988);
			
			//2. 서버에서 등록한 '객체의Alias명'을 이용하여 객체를 불러온다.
			//형식> Registry객체변수.lookup('객체의Alias명')
			RmiTestInterface inf = (RmiTestInterface)reg.lookup("server");
			
			//3. 이제부터 불러온 객체의 메소드를 호출해서 사용할 수 있다.
			System.out.print("서버로 전송할 메시지 입력>> ");
			String msg = scan.nextLine();
			
			int a = inf.doRemotePrint(msg);
			System.out.println("서버의 반환값 : " + a);
			System.out.println();
			
		
			List<String> list = new ArrayList<>();
			list.add("복숭아");
			list.add("사과");
			list.add("감");
			list.add("수박");
			list.add("딸기");
			
			inf.doPrintList(list);
			System.out.println("List 전송 끝...");
			
			TestVO testVo = new TestVO();
			testVo.setId("A001");
			testVo.setNum(12345);
			inf.doPrintVo(testVo);
			System.out.println("VO객체 전송 끝..");
			System.out.println();
			
			//파일 전송하기
			
			File file = new File("D:\\A_TeachingMaterial\\5.JQuery\\images/Koala.jpg");
			if(!file.exists()) {
				System.out.println(file.getName() + "파일이 없습니다.");
				return;
			}
			
			FileInfoVo fileVo = new FileInfoVo();
			
			//파일의 용량을 구해서 FileInfoVo의 fileData배열의 길이를 결정한다.
			int len = (int)file.length();
			byte[] data = new byte[len];
			
			//파일 입력용 스트림 객체 생성
			FileInputStream fin = new FileInputStream(file);
			
			//파일 내용을 읽어와 byte형 배열에 저장한다.
			fin.read(data);
			
			//구해온 정보를 FileInfoVO객체에 세팅한다.
			fileVo.setFileName(file.getName()); //파일명 저장
			fileVo.setFileData(data);			//파일내용 저장
			
			//서버의 파일 전송용 메소드를 호출한다.
			inf.transFile(fileVo);
			
			System.out.println("파일 전송 작업 끝!");
			
			
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
