package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Back_알파벳_1987 {

	static int R, C, ans; //행,열, 정답
	static char[][] map;
	static int[] dx= {-1,0,1,0}; //상 우 하 좌
	static int[] dy = {0,1,0,-1};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][];
		for(int r=0; r<R; r++) {
			map[r] = br.readLine().toCharArray();
		}
		
		ans=0;
		
		dfs(0,0,""+map[0][0],1);
		System.out.println(ans);

	}
	
	static void dfs(int a, int b, String visit, int count) { //visit에 지금까지 지나온 알파벳들 저장되어 있음
		
		for(int d=0; d<4; d++) {
			int X = a+dx[d];
			int Y = b+dy[d];
			
			if( X<0 || Y<0 || X>=R || Y>=C ) continue; //인덱스 체크
			
			if(diff(map[X][Y], visit)) { //탐색할 수 있으면
				dfs(X, Y, visit+map[X][Y], count+1);
			}else { //탐색할 수 없으면
				ans = Math.max(ans, count);
			}
			
		}
		
	}
	static boolean diff(char a, String visit) { //지금까지 지나온 모든 칸에 적혀있는 알파벳과 다른지 확인. 다르면 true, 같으면 false
		for(int i=0; i<visit.length(); i++) {
			if(visit.charAt(i) == a) return false;
		}
		return true;
	}

}
