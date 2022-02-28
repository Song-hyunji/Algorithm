package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_유기농배추_1012 {

	static int M, N, K; //가로길이, 세로길이, 배추개수
	static int map[][]; //배추 위치
	
	static int dy[] = {-1,1,0,0};
	static int dx[] = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			M = Integer.parseInt(st.nextToken());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			map = new int[N][M];
			
			//배추 위치 입력받기
			for(int i=0; i<K; i++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				map[y][x] = 1;
			}
			
			int count = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<M; j++) {
					if(map[i][j] == 1) {
						count++;
						dfs(i,j);
					}
				}
			}
			
			System.out.println(count);
		}
	}
	
	static void dfs(int a, int b) { //배추 탐색
		for(int d=0; d<4; d++) {
			int Y = a + dy[d];
			int X = b + dx[d];
			if(Y<0 || X<0 || Y>=N || X>=M) continue; //인덱스 체크
			if(map[Y][X] == 1) {
				map[Y][X] = 0;
				dfs(Y, X);
			}
		}
	}
}
