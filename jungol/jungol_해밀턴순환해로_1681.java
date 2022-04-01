package jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jungol_해밀턴순환해로_1681 {

	static int N;
	static int[][] map;
	static int ans = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		//입력받기
		for(int i=0; i<N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//dfs를 위한 for문
		for(int i=1; i<N; i++) {
			if(map[0][i] == 0) continue;
			boolean[] visit = new boolean[N];
			visit[0] = true;
			visit[i] = true;
			dfs(0,i,map[0][i],visit);
			
		}
		System.out.println(ans);

	}
	
	static void dfs(int a, int b, int cost, boolean[] visit) {
		if(cost >= ans) return;
		int count = 0;
		
		for(int i=1; i<N; i++) {
			if(visit[i]) continue; //방문했으면 넘김
			count++;
			
			if(map[b][i] == 0) continue; //갈 수 없으면 넘김
			boolean[] newvisit = copyarr(visit);
			newvisit[i] = true;
			dfs(b, i, cost+map[b][i], newvisit);
		}
		
		if(count == 0 && map[b][0] !=0 ) { //다 방문 했고, 회사로 돌아갈 수 있으면
			ans = Math.min(ans, cost+map[b][0]);
			return;
		}
		
	}
	
	static boolean[] copyarr(boolean[] visit) {
		boolean[] newvisit = new boolean[N];
		for(int i=0; i<N; i++) {
			newvisit[i] = visit[i];
		}
		return newvisit;
	}

}
