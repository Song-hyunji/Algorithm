package backjoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Back_드래곤커브_15685 {

	static boolean map[][] = new boolean[101][101]; //커브가 지나가는지 표시
	static int[] dy = {0,-1,0,1}; //우상좌하
	static int[] dx = {1,0,-1,0};
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine()); //커브 개수
		
		//커브 받기
		for(int n=0; n<N; n++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken()); //방향
			int g = Integer.parseInt(st.nextToken()); //세대
			
			//방향 저장
			ArrayList<Integer> dir = new ArrayList<>(); 
			dir.add(d); //첫 방향 저장
			
			//뒤에서부터 앞으로 순회하면서 규칙대로 방향 저장 (ex. 01 -> 01 21 / 0121 -> 0121 2321)
			for(int i=1; i<=g; i++) {
				int start = dir.size()-1;  
				while(start >=0) { 
					int addDir = (dir.get(start)+1) % 4;
					dir.add(addDir);
					start--;
				}
			}
			
			//방향 따라가면서 map에 커브가 지나가는거 표시
			map[y][x] = true;
			while(!dir.isEmpty()) {
				int i = dir.remove(0);
				y += dy[i];
				x += dx[i];
				map[y][x] = true;
			}
		}
		
		//정사각형 갯수 확인
		int count = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(map[i][j] && map[i+1][j] && map[i][j+1] && map[i+1][j+1]) count++;
			}
		}
		System.out.println(count);
		
	}
}
