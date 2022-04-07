package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;
//pq로 효율적으로 bfs 탐색
public class swea_보급로_1249_2 {

	static int N;
	static int map[][];
	static int dy[] = {0,0,1,-1};
	static int dx[] = {1,-1,0,0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			//입력 받기
			for(int i=0; i<N; i++) {
				String s = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = s.charAt(j)-'0';
				}
			}
			
			System.out.println("#"+t+" "+ bfs());
			
			
		}

	}
	static int bfs() {
		boolean visit[][] = new boolean[N][N];
		
		PriorityQueue<Node> queue = new PriorityQueue<>((o1,o2)->o1.len-o2.len);
		queue.add(new Node(0,0,0));
		visit[0][0] = true;
		int ans = Integer.MAX_VALUE;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(node.y == N-1 && node.x == N-1) return node.len;
			for(int d=0; d<4; d++) {
				int Y = node.y + dy[d];
				int X = node.x + dx[d];
				if(Y<0 || X<0 || Y>=N || X>=N || visit[Y][X]) continue;
				visit[Y][X] = true;
				queue.add(new Node(Y,X,node.len + map[Y][X]));
			}
		}
		return 0;
	}
	
	static class Node{
		int y;
		int x;
		int len;
		
		public Node(int y, int x, int len) {
			super();
			this.y = y;
			this.x = x;
			this.len = len;
		}
	}

}
