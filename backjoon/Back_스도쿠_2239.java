package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Back_스도쿠_2239 {

	static int map[][];
	static boolean ans;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		map = new int[10][10];
		
		for(int i=1; i<=9; i++) {
			String s = br.readLine();
			for(int j=1; j<=9; j++) {
				map[i][j] = s.charAt(j-1) - '0';
			}
		}
		
		ans=false;
		dfs(1,1); //사전순으로 차례대로 확인
		
	}
	static void dfs(int a, int b) {
		if(ans) return; //정답을 이미 구했으므로 리턴
		
		//다 채웠으면 출력
		if(a==10 && b==1) {
			for(int i=1; i<=9; i++) {
				for(int j=1; j<=9; j++) {
					System.out.print(map[i][j]);
				}
				System.out.println();
			}
			ans = true;
			return;
		}
		
		if(map[a][b] != 0) { //map에 숫자가 이미 있으면 dfs 탐색만 더 들어감
			if(b==9) dfs(a+1, 1);
			else dfs(a, b+1);
			return;
		}
		
		//map[a][b] == 0일 때
		for(int num=1; num<=9; num++) {
			if(cansolve(a,b,num)) {
				map[a][b] = num;
				if(b==9) dfs(a+1, 1);
				else dfs(a, b+1);
				map[a][b] = 0;
			}
		}
		return;
	}
	
	static boolean cansolve(int a, int b, int num) { ///map[a][b]에 num을 넣을 수 있는지 확인
		//열 확인
		for(int i=1; i<=9; i++) {
			if(map[i][b] == num) return false;
		}
		
		//행 확인
		for(int i=1; i<=9; i++) {
			if(map[a][i] == num) return false;
		}
		
		//스도쿠 퍼즐 확인
		int startY = ((a-1)/3)*3+1;
		int startX = ((b-1)/3)*3+1;
		for(int i=startY; i<startY+3; i++) {
			for(int j=startX; j<startX+3; j++) {
				if(map[i][j] == num) return false;
			}
		}
		return true;
	}

}
