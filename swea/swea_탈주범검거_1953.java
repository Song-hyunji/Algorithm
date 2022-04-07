package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class swea_탈주범검거_1953 {

	static int N, M, R, C, L; 
	static int map[][];
	
	static int[] dy = {0,0,1,-1}; //우좌하상
	static int[] dx = {1,-1,0,0};
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			
			//입력 받기
			N = Integer.parseInt(st.nextToken()); //세로길이
			M = Integer.parseInt(st.nextToken()); //가로길이
			R = Integer.parseInt(st.nextToken()); //맨홀의 세로위치
			C = Integer.parseInt(st.nextToken()); //맨홀의 가로위치
			L = Integer.parseInt(st.nextToken()); //탈출 후 소요된 시간 L
			map = new int[N][M];
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<M; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			System.out.println("#"+t+" "+bfs());
			
		}

	}
	static int bfs() {
		boolean[][] visit = new boolean[N][M];
		Queue<Node> queue = new ArrayDeque<>();
		queue.add(new Node(R,C));
		visit[R][C] = true;
		int count = 1;
		
		for(int l=1; l<L; l++) { //시간만큼 반복
			int size = queue.size();
			
			for(int s=0; s<size; s++) {
				Node node = queue.poll();
				
				for(int d=0; d<4; d++) {
					int Y = node.y + dy[d];
					int X = node.x + dx[d];
					if(Y<0 || X<0 || Y>=N || X>=M || visit[Y][X] || map[Y][X] == 0) continue;
					if(connect(map[node.y][node.x], map[Y][X], d)) {
						queue.add(new Node(Y,X));
						visit[Y][X] = true;
						count++;
					}
				}
			}
		}
		
		return count;
	}
	static boolean connect(int a, int b, int d) { //a의 d방향에 b가 있음 //우좌하상
		if(a == 1) {
			if(b==1) return true;
			else if(d == 0 && (b==3||b==6||b==7)) return true;
			else if(d == 1 && (b==3||b==4||b==5)) return true;
			else if(d == 2 && (b==2||b==4||b==7)) return true;
			else if(d == 3 && (b==2||b==5||b==6)) return true;
			else return false;
		}
		if(a == 2) {
			if(d == 2 && (b==1||b==2||b==4||b==7)) return true;
			else if(d == 3 && (b==1||b==2||b==5||b==6)) return true;
			else return false;
		}
		if(a == 3) {
			if(d == 0 && (b==1||b==3||b==6||b==7)) return true;
			else if(d==1 && (b==1||b==3||b==4||b==5)) return true;
			else return false;
		}
		if(a == 4) {
			if(d == 0 && (b==1||b==3||b==6||b==7)) return true;
			else if(d == 3 &&(b==1||b==2||b==5||b==6)) return true;
			else return false;
		}
		if(a == 5) {
			if(d == 0 && (b==1||b==3||b==6||b==7)) return true;
			else if(d == 2 && (b==1||b==2||b==4||b==7)) return true;
			else return false;
		}
		if(a == 6) {
			if(d == 1 && (b==1||b==3||b==4||b==5)) return true;
			else if(d == 2 && (b==1||b==2||b==4||b==7)) return true;
			else return false;
		}
		if(a == 7) {
			if(d==1 && (b==1||b==3||b==4||b==5)) return true;
			else if(d == 3 &&(b==1||b==2||b==5||b==6)) return true;
			else return false;
		}
		
		return false;
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
