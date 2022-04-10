package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Queue;

public class Back_움직이는미로탈출_16954 {

	static char[][] map;
	static ArrayList<Node> wall;
	static Queue<Node> queue;
	static int dy[] = {-1,-1,0,1,1,1,0,-1,0};
	static int dx[] = {0,1,1,1,0,-1,-1,-1,0};
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new char[8][];
		wall = new ArrayList<>();
		
		for(int i=0; i<8; i++) {
			map[i] = br.readLine().toCharArray();
			for(int j=0; j<8; j++) {
				if(map[i][j] == '#') wall.add(new Node(i,j)); 
			}
		}
		ans=0;
		bfs();
		System.out.println(ans);
	}
	
	static void bfs() {
		Collections.sort(wall, (o1,o2)->o2.y-o1.y);
		
		queue = new ArrayDeque<>();
		queue.add(new Node(7, 0));
		while(!queue.isEmpty()) {
			
			int size = queue.size();
			
			for(int i=0; i<size; i++) {
				Node node = queue.poll();
				if(map[node.y][node.x] == '#') continue;
				if(node.y == 0 && node.x == 7) {
					ans = 1; return;
				}
				
				for(int d=0; d<dy.length; d++) {
					int ny = node.y + dy[d];
					int nx = node.x + dx[d];
					if(ny<0 || nx<0 || ny>=8 || nx>=8 || map[ny][nx] == '#') continue;
					queue.add(new Node(ny, nx));
				}
			}
			move(); //벽 움직이기
		}
		
	}
	static void move() { //벽 내리기
		for(int i=0; i<wall.size(); i++) {
			Node node = wall.get(i);
			if(node.y == 7) {
				map[node.y][node.x] = '.';
				wall.remove(i);
				i--;
			}else {
				map[node.y][node.x] = '.';
				map[node.y+1][node.x] = '#';
				node.y++;
			}
		}
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

