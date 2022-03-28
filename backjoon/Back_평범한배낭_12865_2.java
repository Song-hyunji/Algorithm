package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Back_평범한배낭_12865_2 {
//N개의 물건. 각 물건은 무게W와 가치V. 물건은 여러개 못넣음
//최대 K 무게 가능, 가치의 합이 최대가 되도록
	
	static int N, K; //물품의 수, 버틸수있는 무게
	static int[][] things; //각 물건의 무게W[0], 가치V[1]
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		things = new int[N][2];
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			things[i][0] = Integer.parseInt(st.nextToken());
			things[i][1] = Integer.parseInt(st.nextToken());
		}
		
		Arrays.sort(things, (a,b)->a[0]-b[0]); //무게 오름차순 정렬
		
		int[][] dp = new int[N+1][K+1]; //dp[i][j] : i번까지의 물건으로 최대 K무게 만들었을 때 얻는 최대가치
		
		for(int i=1; i<=N; i++) {
			int w = things[i-1][0]; //무게
			int v = things[i-1][1]; //가치
			
			for(int j=1; j<=K; j++) {
				dp[i][j] = dp[i-1][j] > dp[i][j-1] ? dp[i-1][j] : dp[i][j-1];
				if(j-w >=0 ) {
					dp[i][j] = dp[i-1][j-w]+v > dp[i][j] ? dp[i-1][j-w]+v : dp[i][j];
				}
			}
		}
		System.out.println(dp[N][K]);
		

	}

}
