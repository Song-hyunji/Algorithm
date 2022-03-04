package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Back_인구이동_16234 {

	static int N, L, R; // NxN땅 크기 , 인구차이 L명이상, R명 이하
	static int map[][]; //인구 수 저장 배열
	static boolean visit[][];
	
	static int[] dy = {-1,1,0,0};
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		map = new int[N][N];
		
		//입력받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		int count = 0;
		while(true) {
			boolean keep = false;
			visit = new boolean[N][N];
			
			//인구수 차이 체크
			for(int i=0; i<N; i++) {
				for(int j = 0; j<N; j++) {
					
					if(visit[i][j]) continue; //방문여부 체크 (인구이동이 동시에 여러군데에서 발생할 수도 있기 때문)
					
					for(int d=0; d<4; d++) {
						int Y = i + dy[d];
						int X = j + dx[d];
						
						if(Y<0 || X<0 || Y>=N || X>=N || visit[Y][X] || visit[i][j]) continue; //인덱스 체크, 방문 체크
						
						//차이값 체크
						int dif = Math.abs(map[i][j] - map[Y][X]);
						if(dif>=L && dif<=R) {
							if(!keep) { //한번만 카운트 해주기 위함
								keep = true;
								count++;
							}
							bfs(i,j); //인구 이동
						}
					}
				}
			}
			
			if(!keep) break; //더이상 인구이동이 없으면 break
		}
		
		System.out.println(count);
		
	}
	
	static void bfs(int a, int b) { //bfs로 인구수 차이 체크하고, 조정하는 것 까지 실행
		
		Queue<location> move = new ArrayDeque<>(); //인구수 차이 체크하기 위한 큐
		Queue<location> changePeople = new ArrayDeque<>(); //나중에 인구수 변경해주기 위한 큐
		
		//처음 위치 큐에 넣고 방문true
		move.add(new location(a,b));
		changePeople.add(new location(a,b));
		visit[a][b] = true;
		
		//인구 이동하기 위한 count, sum 
		int count = 1;
		int sum = map[a][b];
		
		//bfs 
		while(!move.isEmpty()) {
			location loc = move.poll();
			
			for(int d=0; d<4; d++) { //사방탐색하며 인구이동 가능한지 확인
				int Y = loc.y + dy[d];
				int X = loc.x + dx[d];
				
				if(Y<0 || X<0 || Y>=N || X>=N || visit[Y][X]) continue; //인덱스 체크, 방문 체크
				
				//차이값 체크
				int dif = Math.abs(map[loc.y][loc.x] - map[Y][X]);
				
				//인구이동 가능하면
				if(dif>=L && dif<=R) { 
					move.add(new location(Y,X));
					changePeople.add(new location(Y,X));
					visit[Y][X] = true;
					count++;
					sum+=map[Y][X];
				}
			}
		}
		
		//인구 이동
		int avg = sum / count;
		while(!changePeople.isEmpty()) {
			location loc = changePeople.poll();
			map[loc.y][loc.x] = avg;
		}
	}
	
	static class location{ //위치 저장하기 위한 class
		int y;
		int x;
		location(int y, int x){
			this.y=y;
			this.x=x;
		}
	}
}
