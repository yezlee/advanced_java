package exercise;

/*
1> for문을 이용해서 1~100까지 1씩 증가하면서 5의 배수만 누적해서 더해주는 프로그램

2> 구구단 2~9단까지 세로로 출력(for문사용) 
2x1=2
2x2=4
.
.
9x9=81

3> 구구단 2~9단까지 가로로 출력 (for문사용)
2x1=2    3x1=3 ... 9x1=9
2x2=4
.
.
2x9=18

4> 출력내용이 다음과 같이 나오게 (for문사용)
    **
   ****
  ******
 ********
**********
**********
 ********
  ******
    **

5>
    *
   ***
  *****
 *******
*********
 *******
  *****
   ***
    *
*/
public class ex1 {

	int i = 0;
	int j = 0;
	
	public static void main(String[] args) {
		
/*		
		// 1> for문을 이용해서 1~100까지 1씩 증가하면서 5의 배수만 누적해서 더해주는 프로그램

		int sum = 0;
		for(int i = 1; i<= 100; i++) {
			
			if(i % 5 == 0) {
				sum += i;
				System.out.println(i + " /" +sum); //검토용
			}
		}
		System.out.println(sum);
		
*/		
		
		
/*		//2> 구구단 2~9단까지 세로로 출력(for문사용)  
		
		int res = 0;
		for(int i = 2; i <= 9; i++) { //n단
			for(int j = 1; j <= 9; j++) { 
				res = i*j;	
				System.out.println( i + " x " + j + " = " +res);
			}
			System.out.println();
		}
*/
		
/*		// 3> 구구단 2~9단까지 가로로 출력 (for문사용)
		
				
		
		for(j = 1; j <= 9; j++) {
			for(i = 2; i <= 9; i++) {
			System.out.print(i + " x " + j + " = " + i*j + "\t");
			}
			System.out.println();
		}
		*/
		
		
		//4> 출력내용이 다음과 같이 나오게 (for문사용)
		/*
				    **
				   ****
				  ******
				 ********
				**********
				**********
				 ********
				  ******
				    **
		*/
		
	/*	for(int i = 1; i <= 9; i++) {
			for(int j = 1; j <= 10; j++) {
				if(i >= j) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			for(int j = 10; j >= 1; j--) {
				if(i <= j) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		*/
		
		for(int i = 2; i <= 5; i++) {           //첫번째 i포문
			for(int j = 5; j >= 2; j--) {       //첫번째 j포문        //j가 0일때는 아예 for문 자체가 안돌아. 조건이 안맞아서
				if(i <= j) {
					System.out.print(" ");
				}else {
					System.out.print("*");
				}
			}
			for(int j = 1; j <= 5; j++) {       //두번째 j포문 
				if(i >= j) {
					System.out.print("*");
				}else {
					
					System.out.print(" ");
				}
			}			
			System.out.println();
		}
//-----------------------------------------------------------------------						
		for(int i = 2; i <= 5; i++) {          //두번째 i포문  
			for(int j = 2; j <= 5; j++) {
				if(i >= j) {
					System.out.print(" ");
				}else {
					System.out.print("*");
				}
			}
			for(int j = 5; j >= 1; j--) {
				if(i <= j) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		
		
	/*	
	     **   
	    ****  
	   ****** 
	  ********
	  *******
	   ***** 
	    ***  
	     *   */

		
		
		/*//5번
		
		for(int i = 1; i <= 5; i++) {           //첫번째 i포문
			for(int j = 5; j >= 1; j--) {       //첫번째 j포문        //j가 0일때는 아예 for문 자체가 안돌아. 조건이 안맞아서
				if(i <= j) {
					System.out.print(" ");
				}else {
					System.out.print("*");
				}
			}
			for(int j = 1; j <= 5; j++) {       //두번째 j포문 
				if(i >= j) {
					System.out.print("*");
				}else {
					
					System.out.print(" ");
				}
			}			
			System.out.println();
		}
//-----------------------------------------------------------------------						
		for(int i = 2; i <= 5; i++) {          //두번째 i포문  
			for(int j = 1; j <= 5; j++) {
				if(i >= j) {
					System.out.print(" ");
				}else {
					System.out.print("*");
				}
			}
			for(int j = 5; j >= 1; j--) {
				if(i <= j) {
					System.out.print("*");
				}else {
					System.out.print(" ");
				}
			}
			System.out.println();
		}
		
		
		*/
	}

}
