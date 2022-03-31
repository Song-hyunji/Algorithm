package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Back_1로만들기_1463 {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int num = Integer.parseInt(br.readLine());
		int[] dp = new int[num+1];
		
		for(int i=2; i<=3 && i<=num ; i++) {
			dp[i] = 1;
		}
		
		for(int i=4; i<=num; i++) {
			dp[i] = dp[i-1] + 1;
			if(i%3 == 0) {
				dp[i] = Math.min(dp[i], dp[i/3]+1);
			}
			if(i%2 == 0) {
				dp[i] = Math.min(dp[i], dp[i/2]+1);
			}
		}
		
		System.out.println(dp[num]);
	}

}
