package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_미세먼지_17144 {

	static int R,C,T;
	static int[][] room;
	static int[] dy = {-1,0,1,0};
	static int[] dx = {0,1,0,-1};
	
	static int[] dyrev = {1,0,-1,0};
	static int[] dxrev = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		room = new int[R][C];
		int[] air = new int[2]; //공기청정기 위치 담기
		
		for(int i=0; i<R; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<C; j++) {
				room[i][j] = Integer.parseInt(st.nextToken());
				
				if(room[i][j] == -1) {
					if(air[0] == 0) air[0] = i;
					else air[1] = i;
				}
			}
		}
		while(T>0) {
			T--;
			move();
			room[air[0]][0] = -1;
			room[air[1]][0] = -1;
			
			airOn(air);
		}
		
		int ans=0;
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				ans+=room[i][j];
			}
		}
		ans+=2;
		System.out.println(ans);
		
		
	}
	static void move() { //미세먼지 이동
		int[][] newroom = new int[R][C];
		for(int i=0; i<R; i++) {
			for(int j=0; j<C; j++) {
				
				//미세먼지 있으니까 확산
				if(room[i][j] > 0) {
					newroom[i][j] += room[i][j];
					int spread = room[i][j]/5;
					if(spread == 0) continue;
					
					for(int d=0; d<4; d++) {
						int Y = i + dy[d];
						int X = j + dx[d];
						if(Y<0 || X<0 || Y>=R || X >=C || room[Y][X] == -1) continue;
						newroom[i][j] -= spread;
						newroom[Y][X] += spread;
					}
				}
			}
		}
		
		room = newroom;
		
	}
	static void airOn(int[] air) {
		
		int y=air[0];
		int x=0;
		//반시계방향
		for(int d=0; d<4; d++) {
			//while(x>=0 && y>=0 && y<=air[0] && x<C) {
			while(true) {
				int Y = y+dy[d];
				int X = x+dx[d];
				if(Y<0 || X<0 || Y>air[0] || X >=C ) break;
	
				if(room[Y][X] == -1) {
					room[y][x] = 0;
				}else if(room[y][x] != -1) {
					room[y][x] = room[Y][X];
				}
				y=Y;
				x=X;
			}
		}
		
		y=air[1];
		x=0;
		//시계방향
		for(int d=0; d<4; d++) {
			//while(x>=0 && y>=0 && y<=air[0] && x<C) {
			while(true) {
				int Y = y+dyrev[d];
				int X = x+dxrev[d];
				if(Y<air[1] || X<0 || Y>=R || X >=C ) break;
				
				if(room[Y][X] == -1) {
					room[y][x] = 0;
				}else if(room[y][x] != -1) {
					room[y][x] = room[Y][X];
				}
				y=Y;
				x=X;
			}
		}
		
	}

}
