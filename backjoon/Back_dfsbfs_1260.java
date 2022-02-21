package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
//ArrayList 배열로 품

public class Back_dfsbfs_1260 {

	static int N, M, V; //정점개수, 간선개수, 시작정점번호
	static boolean select[];
	static List<Integer>[] arr;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());

		arr = new ArrayList[N+1];
		for(int i=1; i<=N; i++) {
			arr[i] = new ArrayList<Integer>();
		}
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			
			arr[from].add(to);
			arr[to].add(from);			
		}
		
		for(int i=1; i<=N; i++) {
			Collections.sort(arr[i]);
		}
		
		select = new boolean[N+1];
		dfs(V);
		System.out.println();
		
		Arrays.fill(select, false);
		bfs(V);
	}
	
	public static void dfs(int vertex) {
		System.out.print(vertex+" ");
		select[vertex] = true;
		
		for(int i=0; i<arr[vertex].size(); i++) {
			int nextNum = arr[vertex].get(i);
			if(select[nextNum] == true) continue;
			dfs(nextNum);
		}
	}
	
	public static void bfs(int vertex) {
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		queue.offer(vertex);
		select[vertex] = true;
		
		while(!queue.isEmpty()) {
			int pollNum = queue.poll();
			System.out.print(pollNum+" ");
			
			for(int i=0; i<arr[pollNum].size(); i++){
				int nextNum = arr[pollNum].get(i);
				if(select[nextNum] == true) continue;
				select[nextNum] = true;
				queue.offer(nextNum);
			}
		}
	}

}
