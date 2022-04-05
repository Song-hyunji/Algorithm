package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_벽돌깨기_5656 {

	static int N, W, H;
	static int[][] map;
	static int[] dy = {0,0,1,-1};
	static int[] dx = {1,-1,0,0};
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			H = Integer.parseInt(st.nextToken());
			map = new int[H][W];
			
			for(int i=0; i<H; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<W; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			ans = Integer.MAX_VALUE;
			drop(0, map);
			
			System.out.println("#"+t+" " + ans);
			
		}
		

	}
	static void drop(int n, int[][] mapArr) { //구슬 떨어트리기
		if(n == N) {
			ans = Math.min(ans, count(mapArr));
			return;
		}
		
		int removeCount = 0;
		for(int i=0; i<W; i++) {
			int[][] newArr = copyArr(mapArr);
			if(remove(i, newArr)) {
				removeCount++;
				clean(newArr); //정리하기
				drop(n+1, newArr); //다음 구슬 떨어트리기
			}
		}
		if(removeCount == 0) {
			ans = 0;
			return;
		}
		
	}
	
	static void clean(int[][] mapArr) { //정리
		for(int w=0; w<W; w++) { //한 열씩 정리
			int zeroloc = -1;
			for(int h=H-1; h>=0; h--) {
				if(mapArr[h][w] == 0 && zeroloc == -1) zeroloc = h;
				else if(mapArr[h][w] != 0 && zeroloc != -1) {
					mapArr[zeroloc][w] = mapArr[h][w];
					mapArr[h][w] = 0;
					zeroloc--;
				}
			}
		}
	}
	
	static boolean remove(int loc, int[][] mapArr) { //구슬 loc 위치에서 떨어트리고 없애기
		Queue<Node> queue = new ArrayDeque<>();
		
		for(int i=0; i<H; i++) {
			if(mapArr[i][loc] != 0) {
				queue.add(new Node(i, loc, mapArr[i][loc]));
				mapArr[i][loc] = 0;
				break;
			}
		}
		if(queue.isEmpty()) return false; //구슬을 떨어트렸는데 닿는게 없으면 false 리턴
		
		while(!queue.isEmpty()) {
			Node node = queue.poll();
			
			for(int i=1; i<=node.num-1; i++) {
				for(int d=0; d<4; d++) {
					int Y = node.y + dy[d]*i;
					int X = node.x + dx[d]*i;
					if(Y<0 || X<0 || Y>=H || X>=W || mapArr[Y][X] == 0) continue;
					
					if(mapArr[Y][X] == 1) mapArr[Y][X] = 0;
					else if(mapArr[Y][X] > 1) {
						queue.add(new Node(Y, X, mapArr[Y][X]));
						mapArr[Y][X] = 0;
					}
				}
			}
			
		}
		
		
		return true;
	}
	
	static int count(int[][] mapArr) { //남아있는 벽돌 개수 세기
		int num = 0;
		
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				if(mapArr[i][j] != 0) num++;
			}
		}
		
		return num;
	}
	
	static int[][] copyArr(int[][] mapArr){ //배열 복사
		int[][] newArr = new int[H][W];
		for(int i=0; i<H; i++) {
			for(int j=0; j<W; j++) {
				newArr[i][j] = mapArr[i][j];
			}
		}
		return newArr;
	}
	
	static class Node{
		int y;
		int x;
		int num;
		public Node(int y, int x, int num) {
			super();
			this.y = y;
			this.x = x;
			this.num = num;
		}
	}

}
