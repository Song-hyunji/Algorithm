package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_치즈_2636 {
	
	static int sero, garo; //행 수, 열 수
	
	static int[][] cheese;
	static boolean[][] visit;
	static int cheesenum;
	
	static int[] dy = {1,-1,0,0};
	static int[] dx = {0,0,1,-1};
	
	//치즈 가장자리만 녹으니까 치즈 없는 부분(0,0)부터 없는부분으로 dfs 탐색하며, 그 없는부분과 치즈와 맞닿아있으면 그 치즈는 녹음
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		sero = Integer.parseInt(st.nextToken());
		garo = Integer.parseInt(st.nextToken());
		cheese = new int[sero][garo];
		
		//입력받기
		for(int i=0; i<sero; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<garo; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int time = 0; //시간
		int lastcount = 0; //마지막 치즈개수
		
		while(true) {
			
			cheesenum = 0;
			visit = new boolean[sero][garo];
			
			dfs(0,0);
			
			if(cheesenum != 0) {
				time++;
				lastcount = cheesenum;
			}
			else break;
			
		}
		System.out.println(time);
		System.out.println(lastcount);

	}
	static void dfs(int a, int b) {
		visit[a][b] = true;
		
		//치즈가 녹는지 확인
		for(int d=0; d<4; d++) {
			int Y = a+dy[d];
			int X = b+dx[d];
			
			if(Y<0 || X<0 || Y>=sero || X>=garo ) continue;
			
			if(cheese[Y][X] == 1 && !visit[Y][X]) {
				visit[Y][X] = true;
				cheese[Y][X] = 0;
				cheesenum++;
			} else if(cheese[Y][X] == 0 && !visit[Y][X]) {
				dfs(Y, X);
			}
		}
		
	}

}
