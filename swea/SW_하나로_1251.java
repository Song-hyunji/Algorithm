package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class SW_하나로_1251 {
//kruskal
	
	static int N; //섬 개수
	static Node[] island; 
	static ArrayList<Line> distance;
	static int[] parent;
	static double E; //환경 부담 세율
	static double ans;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) 
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			island = new Node[N+1];
			parent = new int[N+1];
			ans = 0;
			
			//x좌표 설정
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				island[i] = new Node(Integer.parseInt(st.nextToken()));
			}
			
			//y좌표 설정
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=N; i++) {
				island[i].y = Integer.parseInt(st.nextToken());
			}
			
			E = Double.parseDouble(br.readLine());
			
			distance = new ArrayList<Line>();
			for(int i=1; i<=N; i++) {
				for(int j=i+1; j<=N; j++) {
					double dis = Math.pow((island[i].x-island[j].x),2)+Math.pow((island[i].y-island[j].y),2);
					distance.add(new Line(i, j, dis));
				}
			}
			Collections.sort(distance, (e1, e2) -> Double.compare(e1.weight, e2.weight));
			
			makeSet();
			int count = 0;
			for(int i=0; i<distance.size(); i++) {
				Line line = distance.get(i);
				if(union(line.x, line.y)) {
					count++;
					ans += line.weight*E;
				}
				if(count == N-1) break;
			}
			System.out.printf("#%d %.0f\n", t, ans);
		}
	}
	static void makeSet() {
		for(int i=1; i<=N; i++) {
			parent[i] = i;
		}
	}
	static int findSet(int x) {
		if(parent[x] == x) return x;
		else return parent[x] = findSet(parent[x]);
	}
	static boolean union(int x, int y) {
		int px = findSet(x);
		int py = findSet(y);
		
		if(px == py) return false;
		if(px < py) parent[px] = py;
		else parent[py] = px;
		return true;
	}
	
	static class Node{
		int x;
		int y;
		Node(int x, int y){
			this.x = x;
			this.y = y;
		}
		Node(int x){
			this.x = x;
			this.y = 0;
		}
	}
	static class Line {
		int x; 
		int y;
		double weight;
		Line(int x, int y, double weight){
			this.x = x;
			this.y = y;
			this.weight = weight;
		}
	}
}

