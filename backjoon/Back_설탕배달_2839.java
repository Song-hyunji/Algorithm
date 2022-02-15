package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Back_설탕배달_2839 {

	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		
		int five = N/5;
		int three = 0;
		N %= 5; 
		
		for(int i=five; i>=0; i--) {
			if(N % 3 == 0) {
				three = N/3;
				break;
			}else {
				five--;
				N += 5;
			}
		}
		if(five==0 && three==0) System.out.println(-1);
		else System.out.println(five+three);
		
	}
}
