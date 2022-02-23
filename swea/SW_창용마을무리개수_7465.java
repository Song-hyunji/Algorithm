package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_창용마을무리개수_7465 {

	static int N, M; //사람 수, 관계 수
	static boolean people[][];
	static boolean visit[];
	static int count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) 
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			people = new boolean[N+1][N+1];
			visit = new boolean[N+1];
			
			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				people[a][b] = true;
				people[b][a] = true;
			}
			
			count = 0;
			for(int i=1; i<=N; i++) {
				if(visit[i]) continue;
				count++;
				dfs(i);
			}
			System.out.println("#"+t+" "+count);
			
		}

	}
	static void dfs(int n) {
		visit[n] = true;
		for(int i=1; i<=N; i++) {
			if(!visit[i] && people[n][i]) dfs(i);
		}
	}

}
