package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//dijkstra
public class Back_녹색옷젤다_4485 {

	static int N;
	static int[][] money; //원본 배열 
	static int[][] distance; //최소거리(돈) 저장하는 배열
	static boolean[][] visit; //방문여부
	static PriorityQueue<Edge> pqueue = new PriorityQueue<Edge>((e1, e2)->e1.m-e2.m);
	
	static int[] dy = {-1,1,0,0}; //상하좌우
	static int[] dx = {0,0,-1,1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = 0;
		
		while(true) {
			T++;
			N = Integer.parseInt(br.readLine());
			if(N == 0) break;
			
			money = new int[N][N];
			distance = new int[N][N];
			visit = new boolean[N][N];
			
			//입력받기
			for(int i=0; i<N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					money[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			//배열 초기화
			for(int i=0; i<N; i++) {
				Arrays.fill(distance[i], Integer.MAX_VALUE);
			}
			
			//dijkstra
			
			//시작점(0,0) 거리 설정 후 pq에 넣기
			distance[0][0] = money[0][0];
			pqueue.offer(new Edge(0,0,distance[0][0]));
			
			while(!pqueue.isEmpty()) {
				Edge e = pqueue.poll(); //현재 시작점에서 가장 짧은 정점 꺼내기
				
				if(visit[e.a][e.b]) continue; //이미 방문했으면 패스
				visit[e.a][e.b] = true; //꺼낸 정점 방문처리 하기
				
				for(int d=0; d<4; d++) { //꺼낸 정점의 인접정점(상하좌우) 체크하기
					int Y = e.a + dy[d];
					int X = e.b + dx[d];
					if(Y<0 || X<0 || Y>=N || X>=N) continue; //인덱스 체크
					
					//방문하지 않았고 선택된 정점을 경유하는게 더 짧으면(돈이 적게들면)
					if(!visit[Y][X] && distance[Y][X] > distance[e.a][e.b]+money[Y][X]) { 
						distance[Y][X] = distance[e.a][e.b]+money[Y][X]; //거리배열 갱신
						pqueue.offer(new Edge(Y, X, distance[Y][X])); //pq에 넣어줌
					}
					
				}
			}
			
			System.out.println("Problem "+T+": "+distance[N-1][N-1]);

		}
	}
	static class Edge{
		int a;
		int b;
		int m;
		public Edge(int a, int b, int m) {
			super();
			this.a = a;
			this.b = b;
			this.m = m;
		}
	}
}
