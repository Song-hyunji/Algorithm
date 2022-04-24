package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;
//dfs + 백트래킹
public class Back_사다리조작_15684 {
	
	static int N, M, H;
	static int ans;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); //세로선 개수
		M = Integer.parseInt(st.nextToken()); //작은 가로선 개수
		H = Integer.parseInt(st.nextToken()); //가로선 개수
		int[][] line = new int[N+1][H+1]; //line[1][2] : 1번 세로줄의 2번 높이 위치에서 연결되어 있는 줄의 번호. 0번에 줄 개수 저장
		
		for(int m=0; m<M; m++) {
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken()); //b번 세로선과 b+1번 세로선을 a높이에서 연결
			int b = Integer.parseInt(st.nextToken());
			line[b][a] = b+1; //b번 세로선의 a 높이에서 b+1과 연결
			line[b+1][a] = b; //b+1번 세로선의 a 높이에서 b번과 연결
		}
		
		ans = 4;
		
		link(0, 1, 1, line);
		
		System.out.println(ans <= 3 ? ans : "-1");
		
	}
	
	//선 긋기
	static void link(int count, int lineNum, int h, int[][] line) {
		if(count >= ans) return;
		
		if(canAnswer(line)) { //정답이 되면 리턴
			ans = Math.min(ans, count);
			return;
		}
	
		//가로선 하나씩 긋기
		for(int i=h; i<=H; i++) { //높이 차례대로
			for(int j = i == h ? lineNum : 1; j<N; j++) { //세로선 선택
				if(line[j][i] != 0 || line[j+1][i] != 0) continue; //이미 줄이 그어져있으면 넘김
				line[j][i] = j+1; line[j+1][i] = j; //줄 하나 그음
				link(count+1, j, i, line);
				line[j][i] = 0; line[j+1][i] = 0;
			}
		}
	}
	
	//정답이 되는지 확인 (출발 i -> 도착i) 
	static boolean canAnswer(int[][] line) {
		for(int i=1; i<=N; i++) {
			int where = i;
			for(int j=1; j<=H; j++) {
				if(line[where][j] == 0) continue;
				where = line[where][j];
			}
			if(where != i) return false;
		}
		return true;
	}
}
