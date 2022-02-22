package sw;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_서로소집합_3289 {

	static int N, M;
	static int parents[];
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int t=1; t<=T; t++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			//각 원소들 자신을 부모로 설정
			parents = new int[N+1];
			for(int i=0; i<=N; i++) {
				parents[i] = i;
			}
			
			StringBuilder sb = new StringBuilder();
			
			for(int m=0; m<M; m++) {
				st = new StringTokenizer(br.readLine());
				int cal = Integer.parseInt(st.nextToken());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				
				//cal 0:합집합, 1:같은집합인지 확인
				if(cal == 0) {
					union(a,b);
				}else if(cal == 1) {
					if(findSet(a) == findSet(b)) sb.append(1);
					else sb.append(0);
				}
			}
			
			System.out.println("#"+t+" "+sb);
		}

	}
	
	//a의 집합 찾기 : a의 대표자 찾기
	public static int findSet(int a) { 
		if(a == parents[a]) return a;
		return parents[a] = findSet(parents[a]); //내 부모의 부모를 찾아서 내 부모로 저장 : path compression
	}
	
	//a,b 두 집합 합치기 : 합쳐지면 true, 못했으면 false
	public static boolean union(int a, int b) {
		int aRoot = findSet(a);
		int bRoot = findSet(b);
		if(aRoot == bRoot) return false; //이미 같은 집합이라서 합칠 수 없음
		
		parents[bRoot] = aRoot;
		return true;
	}

}
