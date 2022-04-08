package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class Back_연구소_14502 {

	static int N, M, wallNum;
	static Queue<Node> virus = new ArrayDeque<>();
	
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	
	static int virusCount; //바이러스 퍼지는 칸 수
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		wallNum = 0; //벽 개수
		
		//입력받기
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) wallNum++;
				if(map[i][j] == 2) virus.add(new Node(i,j));
			}
		}
		
		int[][] newMap = copyArr(map);
		wallNum+=3;
		virusCount = Integer.MAX_VALUE;
		wall(0, 0,-1,newMap);
		System.out.println(N*M-wallNum-virusCount);

	}
	static void wall(int depth,int a, int b, int[][] map) { //벽 놓기
		if(depth == 3) { //벽을 3개 뒀으면 바이러스 확산
			spread(map);
			return;
		}
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++) {
				if(i<=a && j<=b) continue;
				
				if(map[i][j] == 0) {
					map[i][j] = 1;
					wall(depth+1, i,j,map);
					map[i][j] = 0;
				}
			}
		}
	}
	
	static void spread(int[][] map) { //바이러스 퍼트리고 바이러스 갯수 카운트
		Queue<Node> queue = new ArrayDeque<>();
		queue.addAll(virus);
		boolean visit[][] = new boolean[N][M];
		int count = 0;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(visit[node.y][node.x]) continue;
			visit[node.y][node.x] = true;
			count++;
			if(count >= virusCount) return;
			
			for(int d=0; d<4; d++) {
				int ny = node.y + dy[d];
				int nx = node.x + dx[d];
				if(ny<0 || nx<0 || ny>=N || nx>= M || visit[ny][nx] || map[ny][nx] == 1|| queue.contains(new Node(ny,nx))) continue;
				queue.add(new Node(ny, nx));
			}
		}
		virusCount = Math.min(count, virusCount);
	}
	
	
	static int[][] copyArr(int[][] map){ //배열 복사
		int[][] newMap = new int[N][M];
		for(int i=0; i<N; i++) {
			for(int j=0; j<M; j++)
				newMap[i][j] = map[i][j];
		}
		return newMap;
	}
	
	static class Node{
		int y;
		int x;
		Node(int y, int x){
			this.y=y;
			this.x=x;
		}
	}

}
