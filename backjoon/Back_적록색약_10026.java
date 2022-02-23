package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Back_적록색약_10026 {

	static int N;
	static char[][] color;
	static boolean[][] visitO; //적록색약인 사람
	static int countO = 0;
	static boolean[][] visitX; //적록색약 아닌 사람
	static int countX = 0;
	
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		color = new char[N][];
		visitO = new boolean[N][N];
		visitX = new boolean[N][N];
		
		for(int i=0; i<N; i++) {
			color[i] = br.readLine().toCharArray();
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visitO[i][j]) continue;
				countO++;
				dfs1(i,j);
			}
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(visitX[i][j]) continue;
				countX++;
				dfs2(i,j);
			}
		}
		System.out.println(countX + " " + countO);
		
		
	}
	
	static void dfs1(int a, int b) { //적록색약인 사람
		visitO[a][b] = true;
		
		for(int d=0; d<4; d++) {
			int Y = a+dy[d];
			int X = b+dx[d];
			if(X<0 || Y<0 || X>=N || Y>=N || visitO[Y][X]) continue; //인덱스 넘거가거나 이미 방문했으면 패스
			if(color[a][b] == color[Y][X] || (color[a][b] == 'R' && color[Y][X] == 'G') || (color[a][b] == 'G' && color[Y][X] == 'R')) {
				dfs1(Y, X);
			}
		}
	}
	
	static void dfs2(int a, int b) { //적록색약 아닌 사람
		visitX[a][b] = true;
		
		for(int d=0; d<4; d++) {
			int Y = a+dy[d];
			int X = b+dx[d];
			if(X<0 || Y<0 || X>=N || Y>=N || visitX[Y][X]) continue; //인덱스 넘거가거나 이미 방문했으면 패스
			if(color[a][b] == color[Y][X]) {
				dfs2(Y, X);
			}
		}
	}

}
