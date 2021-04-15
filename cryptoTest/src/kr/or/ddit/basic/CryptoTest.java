package kr.or.ddit.basic;

import java.security.NoSuchAlgorithmException;

import kr.or.ddit.util.AES256Util;
import kr.or.ddit.util.CryptoUtil;

public class CryptoTest {

	public static void main(String[] args) throws Exception {
		CryptoUtil crypto = new CryptoUtil();

		String plainText = "Hello, World";
		
		System.out.println("MD5 : " + crypto.md5(plainText));
		System.out.println("SHA-256 :" + crypto.sha256(plainText));
		System.out.println("SHA-512 : " + crypto.sha512(plainText));
		
		/*
		    "Hello, World" 얘를 암호화 시켰을때 이렇게 나온거 
		    
		    MD5 :82bb413746aee42f89dea2b59614f9ef
			SHA-256 :03675ac53ff9cd1535ccc7dfcdfa2c458c5218371f418dc136f2d19ac1fbe8a5
			SHA-512 :45546d4d71407e82ecda31eba5bf74b65bc092b0436a2409a6b615c1f78fdb2d3da371758f07a65b5d2b3ee8fa9ea0c772dd1eff884c4c77d4290177b002ccdc
	 
	 		이건 단방향 이라서 원래대로 복원 못시켜
	 
		 */
		System.out.println("------------------------------------------");
		
		AES256Util aes256 = new AES256Util();
		String str = aes256.encrypt(plainText);
		System.out.println("원래의 데이터  => " + plainText);		
		System.out.println("AES256암호화  => " + str);
		System.out.println("암호화 문자열 길이: " + str.length());
		System.out.println("AES256복호화 => " +  aes256.decrypt(str) );	
		
		System.out.println("------------------------------------------");
		String tmp = "";
		for(int i=0; i<=9; i++) {
			for(int j=0; j<=9; j++) {
				tmp += j;
				str = aes256.encrypt(tmp);
				System.out.println("tmp => " + tmp);
				System.out.println("암호화 => " + str);
				System.out.println("암호화 길이 => " + str.length());
				String deStr = aes256.decrypt(str);
				System.out.println("복호화 => " + deStr);
				System.out.println();
			}
		}
		
	}

}
