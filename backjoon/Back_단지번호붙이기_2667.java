package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.PriorityQueue;
import java.util.Queue;

public class Back_단지번호붙이기_2667 {

	static int N; //지도 크기
	static char[][] map; //집 지도 배열
	static int[][] isVisit; //bfs 방문했는지 여부
	static Queue<int[]> queue = new ArrayDeque<>(); //bfs를 위한 큐
	static PriorityQueue<Integer> ans = new PriorityQueue<>(); //단지수 저장 큐
	static int[] dx= {-1,0,1,0}; //bfs에서 4방 확인하기 위함
	static int[] dy = {0,1,0,-1};	
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new char[N][];
		isVisit = new int[N][N];
		
		//입력받기
		for(int n=0; n<N; n++) {
			map[n] = br.readLine().toCharArray();
		}
		
		//bfs
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(map[i][j]=='0' || isVisit[i][j] == 1) continue; //집이 없거나 이미 방문했으면 넘김
				ans.offer(bfs(i,j));
				
			}
		}
		System.out.println(ans.size());
		while(!ans.isEmpty()) {
			System.out.println(ans.poll());
		}
	}
	static int bfs(int i, int j) {
		int house = 1; //한 단지의 처음 집을 큐에 넣어줌
		isVisit[i][j]=1;
		queue.offer(new int[]{i,j});
		
		while(!queue.isEmpty()) { //큐에 있는거 하나 꺼내서 4방 집 확인함. 집이 존재하면 큐에 넣어줌
			int[] current = queue.poll(); 
			for(int d=0; d<4; d++) { //4방 순회하며 집이 있고 방문한적 없으면 queue에 넣어줌
				int X = current[0]+dx[d];
				int Y = current[1]+dy[d];
				if(X<0 || X>=N || Y<0 || Y>=N) continue; //인덱스 처리
				if(map[X][Y]=='1' && isVisit[X][Y]==0) { 
					isVisit[X][Y]=1;
					queue.offer(new int[] {X,Y});
					house++; //집의 수 카운트
				}
			}
		}
		return house;
	}
}
