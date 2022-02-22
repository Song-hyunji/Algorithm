package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SW_최소스패닝트리_3124 {
	//kruskal
	
	static int V, E; //정점개수, 간선개수
	static Node[] tree;
	static int[] parent;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=T; t++) 
		{
			StringTokenizer st = new StringTokenizer(br.readLine());
			V = Integer.parseInt(st.nextToken());
			E = Integer.parseInt(st.nextToken());
			
			tree = new Node[E];
			parent = new int[V+1];
			
			//간선 입력받기
			for(int i=0; i<E; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				int w = Integer.parseInt(st.nextToken());
				tree[i] = new Node(a,b,w);
			}
			
			//가중치 기준 오름차순
			Arrays.sort(tree);
			
			//parent 배열 만들기
			makeSet();
			
			long ans = 0;
			int count = 0; //선택된 간선 수
			
			//최소신장트리 만들기
			for(int i=0; i<E; i++) {
				Node node = tree[i];
				
				if(findSet(node.a) == findSet(node.b)) continue;
				
				ans += node.weight;
				union(node.a, node.b);
				if(++count == V-1) break;
			}
			
			System.out.println("#"+t+" "+ans);
			
			
		}
		
	}
	//부모가 어떤 인자인지 담는 배열 초기화
	static void makeSet() {
		parent = new int[V+1];
		for(int i=0; i<=V; i++) {
			parent[i] = i;
		}
	}
	
	//부모인자가 무엇인지 찾음
	static int findSet(int x) {
		if(parent[x] == x) {
			return x;
		}
		return parent[x] = findSet(parent[x]);
	}
	
	//합치려는 인자 각각 부모를 찾아서 합쳐줌
	static void union(int a, int b) {
		int aParent = findSet(a);
		int bParent = findSet(b);
		
		if(aParent > bParent) parent[bParent] = aParent;
		else parent[aParent] = bParent;
	}
}

class Node implements Comparable<Node>{
	int a;
	int b;
	long weight;
	
	public Node(int a, int b, long weight) {
		super();
		this.a = a;
		this.b = b;
		this.weight = weight;
	}

	@Override
	public int compareTo(Node o) { //가중치 기준 오름차순
		return (this.weight - o.weight)>0 ? 1:-1;
	}
}
