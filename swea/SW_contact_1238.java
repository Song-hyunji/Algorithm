package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class SW_contact_1238 {

	//bfs
	//사람 번호는 1~100
	//현재 런타임에러
	static int N, start;
	static int[] visit;
	static boolean[][] matrix;
	static int count;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T=0;
		while(true) {
			T++;
			if(T == 11) break; //테스트케이스 10개

			StringTokenizer st = new StringTokenizer(br.readLine());
			
			N = Integer.parseInt(st.nextToken());
			start = Integer.parseInt(st.nextToken());
			
			matrix = new boolean[101][101];
			visit = new int[101];
			//visit = new boolean[N+1];
			
			//인접행렬 입력받기
			st = new StringTokenizer(br.readLine());
			
			for(int i=0; i<N; i+=2) {
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				matrix[from][to] = true;
			}
			
			count = 0;
			bfs();			
			
			int ans = 0;
			for(int i=1; i<=100; i++) {
				if(visit[i] == count) {
					ans = Math.max(ans, i);
				}
			}
			System.out.println("#"+T+" "+ans);
			
		}
	
		
	}
	
	public static void bfs() {
		
		Queue<Integer> queue = new ArrayDeque<Integer>();
		
		visit[start] = 1; //visit에 숫자를 넣어서 몇번째 연락받는건지 표시
		queue.offer(start);
		
		
		while(!queue.isEmpty()) {
			int num = queue.poll();
			
			for(int i=1; i<=100; i++) {
				if( !matrix[num][i] || visit[i] !=0 ) continue; //연락할수없거나, 이미 방문했으면 넘김
				visit[i] = visit[num]+1;
				count = Math.max(count, visit[i]);
				queue.offer(i);
			}
		}
	}

}
