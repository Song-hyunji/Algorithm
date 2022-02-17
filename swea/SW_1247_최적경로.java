package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1247_최적경로 {

	static int N, endX, endY, ans;
	static int[][] map; //고객 집
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		
		for(int t=1; t<=T; t++) {
			//입력받기
			N = Integer.parseInt(br.readLine()); //집 수
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			int startX = Integer.parseInt(st.nextToken());
			int startY = Integer.parseInt(st.nextToken());
			
			endX = Integer.parseInt(st.nextToken());
			endY = Integer.parseInt(st.nextToken());
			
			map = new int[N][2]; //n번째 고객 집의 x, y
			for(int n=0; n<N; n++) {
				map[n][0] = Integer.parseInt(st.nextToken());
				map[n][1] = Integer.parseInt(st.nextToken());
			}
			
			ans = Integer.MAX_VALUE;
			
			dfs(startX, startY, 0, 0);
			
			System.out.println("#"+t+" "+ans);
			
		}
		

	}
	static void dfs(int x, int y, int visit, int distance) { //현재 x위치, y위치, 방문여부, 거리
		if(visit == Math.pow(2, N)-1) { //다 방문했으면
			distance += Math.abs(endX-x) + Math.abs(endY-y); //회사까지의 거리 더해주기 
			ans = Math.min(ans, distance); //최단경로 비교
			return;
		}
		if(distance >= ans) return; //가지치기
		
		for(int n=0; n<N; n++) {
			if((visit & 1<<n) != 0) continue; //이미 방문했으면 통과
			
			if(distance + Math.abs(x-map[n][0]) + Math.abs(y-map[n][1]) >=ans) continue;
			dfs(map[n][0], map[n][1], visit | 1<<n, distance + Math.abs(x-map[n][0]) + Math.abs(y-map[n][1]));
		}
	}
}
