package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class bj_색종이_2563 {

	static int[][] paper = new int[101][101];
	static int[][] visit = new int[101][101];
	static int black = 0; //색칠된 넓이
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		for(int n=1; n<=N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			for(int i=a; i<a+10; i++) {
				for(int j=b; j<b+10; j++) {
					if(i>100 || j>100) continue;
					if(visit[i][j] == 1) continue;
					visit[i][j]=1;
					black++;
				}
			}
		}
		System.out.println(black);
	}
}
