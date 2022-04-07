package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;
//bfs로 모두 탐색
public class swea_보급로_1249 {

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
		int visit[][] = new int[N][N];
		for(int i=0; i<N; i++)
			Arrays.fill(visit[i], Integer.MAX_VALUE);
		
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(0,0,0));
		visit[0][0] = 0;
		int ans = Integer.MAX_VALUE;
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			if(node.y == N-1 && node.x == N-1) {
				ans = Math.min(ans, node.len);
				continue;
			}
			for(int d=0; d<4; d++) {
				int Y = node.y + dy[d];
				int X = node.x + dx[d];
				if(Y<0 || X<0 || Y>=N || X>=N || visit[Y][X] <= node.len+map[Y][X]) continue;
				visit[Y][X] = node.len + map[Y][X];
				queue.add(new Node(Y,X,visit[Y][X]));
			}
		}
		
		return ans;
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
